package zcw.bennett.demo.models.players;

import zcw.bennett.demo.models.abstracts.*;

import java.util.*;
import java.util.concurrent.*;

public class StandardPlayer extends AbstractPlayer<AbstractCard> {

    private Integer energy = 0;

    public StandardPlayer(AbstractDeck<AbstractCard> deck, Integer startingHealth) {
        super(deck, startingHealth);
        for (AbstractCard card : this.getDeck()) {
            card.setOwner(this);
        }
    }

    public void startRound(int round) {
        if (round % 2 == 0) {
            this.energy = 2;
        } else if (round % 3 == 0) {
            this.energy = ThreadLocalRandom.current().nextInt(2, 3);
        } else if (round % 5 == 0) {
            this.energy = this.energy + ThreadLocalRandom.current().nextInt(1, 3);
        } else {
            this.energy = 1;
        }
        if (this.energy > 6) {
            this.energy = 6;
        }
    }

    public void endRound(AbstractCard card, AbstractCard opponentCard, AbstractPlayer<AbstractCard> opponent) {
        if (!(opponent instanceof StandardPlayer)) {
            return;
        }
        this.getDeck().addCard(card);
        opponent.getDeck().addCard(opponentCard);
        System.out.println("\n" + this.getName() + " played:        " + card.toString() + "\n");
        System.out.println(opponent.getName() + " played:        " + opponentCard.toString() + "\n\n");
        System.out.println(this.getName() + " HP: " + this.getHealth() + " / " + this.getStartingHealth() + "\n");
        System.out.println(opponent.getName()  + " HP: " + opponent.getHealth() + " / " + opponent.getStartingHealth());
        System.out.println("----------------------------------------------------------------------------------------\n\n\n");
    }

    public Boolean canUse(AbstractCard card) {
        return this.energy >= card.getCost();
    }

    @Override
    public void playCard(AbstractCard card, AbstractCard opponentCard, AbstractPlayer<AbstractCard> opponent) {
        if (!(opponent instanceof StandardPlayer)) {
            return;
        }

        // Both cards are playable
        if (this.canUse(card) && ((StandardPlayer)opponent).canUse(opponentCard)) {
            card.onBeforePlayed();
            opponentCard.onBeforePlayed();
            this.setEnergy(this.getEnergy() - card.getCost());
            ((StandardPlayer) opponent).setEnergy(((StandardPlayer) opponent).getEnergy() - opponentCard.getCost());
            // Both players are Attack & Defend
            if (card.isBoth() && opponentCard.isBoth()) {
                int playerPower   = ((UtilityCard)card).attack();
                int oppPower      = ((UtilityCard)opponentCard).attack();
                boolean playerDef = ((UtilityCard)card).defend(oppPower);
                ((UtilityCard)card).onDefend();
                boolean oppDef    = ((UtilityCard)opponentCard).defend(playerPower);
                ((UtilityCard)opponentCard).onDefend();
                if (playerPower > oppPower && playerDef) {
                    opponent.damage(playerPower);
                    ((UtilityCard)card).onAttack();
                } else if (playerPower < oppPower && !playerDef) {
                    this.damage(oppPower);
                    ((UtilityCard)opponentCard).onAttack();
                } else {
                    int playerDamage = playerDef ? oppPower    / 2 : oppPower;
                    int oppDamage    = oppDef    ? playerPower / 2 : playerPower;
                    this.damage(playerDamage);
                    opponent.damage(oppDamage);
                    ((UtilityCard)card).onAttack();
                    ((UtilityCard)opponentCard).onAttack();
                }
            }

            // Player is Attack & Defend
            else if (card.isBoth()) {

                // Opponent attacking
                if (opponentCard.isAttack()) {
                    int oppPower      = ((AttackCard)opponentCard).attack();
                    boolean playerDef = ((UtilityCard)card).defend(oppPower);
                    ((UtilityCard)card).onDefend();
                    int playerPower   = ((UtilityCard)card).attack();
                    if (!playerDef) {
                        this.damage(oppPower);
                        ((AttackCard)opponentCard).onAttack();
                    } else {
                        opponent.damage(playerPower);
                        ((UtilityCard)card).onAttack();
                    }
                }

                // Opponent defending
                else {
                    int playerPower = ((UtilityCard)card).attack();
                    boolean oppDef  = ((DefendCard)opponentCard).defend(playerPower);
                    ((DefendCard)opponentCard).onDefend();
                    if (!oppDef) {
                        opponent.damage(playerPower);
                        ((UtilityCard)card).onAttack();
                    }
                }
            }

            // Opponent is Attack & Defend
            else if (opponentCard.isBoth()) {
                // Player attacking
                if (card.isAttack()) {
                    int playerPower = ((AttackCard)card).attack();
                    boolean oppDef  = ((UtilityCard)opponentCard).defend(playerPower);
                    ((UtilityCard)opponentCard).onDefend();
                    int oppPower    = ((UtilityCard)opponentCard).attack();
                    if (!oppDef) {
                        opponent.damage(playerPower);
                        ((AttackCard)card).onAttack();
                    } else {
                        this.damage(oppPower);
                        ((UtilityCard)opponentCard).onAttack();
                    }
                }

                // Player defending
                else {
                    int oppPower      = ((UtilityCard)opponentCard).attack();
                    boolean playerDef = ((DefendCard)card).defend(oppPower);
                    ((DefendCard)card).onDefend();
                    if (!playerDef) {
                        this.damage(oppPower);
                        ((UtilityCard)opponentCard).onAttack();
                    }
                }
            }

            // Both cards are either Attack or Defend
            else {
                // Both attacking
                if (card.isAttack() && opponentCard.isAttack()) {
                    int playerPower = ((AttackCard)card).attack();
                    int oppPower    = ((AttackCard)opponentCard).attack();
                    if (playerPower > oppPower) {
                        opponent.damage(playerPower);
                        ((AttackCard)card).onAttack();
                    } else if (oppPower > playerPower) {
                        this.damage(oppPower);
                        ((AttackCard)opponentCard).onAttack();
                    } else {
                        this.damage(1);
                        opponent.damage(1);
                        ((AttackCard)card).onAttack();
                        ((AttackCard)opponentCard).onAttack();
                    }
                }

                // Player attack, opponent defend
                else if (card.isAttack()) {
                    int playerPower = ((AttackCard)card).attack();
                    boolean oppDef  = ((DefendCard)opponentCard).defend(playerPower);
                    ((DefendCard)opponentCard).onDefend();
                    if (!oppDef) {
                        opponent.damage(playerPower);
                        ((AttackCard)card).onAttack();
                    }
                }

                // Both defending
                else if (card.isDefend() && opponentCard.isDefend())  {
                    this.heal(5);
                    opponent.heal(5);
                    ((DefendCard)card).onDefend();
                    ((DefendCard)opponentCard).onDefend();
                }

                // Player defend, opponent attack
                else {
                    int oppPower      = ((AttackCard)opponentCard).attack();
                    boolean playerDef = ((DefendCard)card).defend(oppPower);
                    ((DefendCard)card).onDefend();
                    if (!playerDef) {
                        this.damage(oppPower);
                        ((AttackCard)opponentCard).onAttack();
                    }
                }
            }

            card.onAfterPlayed();
            opponentCard.onAfterPlayed();
        }

        // Player cannot play card
        else if (!this.canUse(card)) {
            card.onCannotAfford();
            opponentCard.onBeforePlayed();
            ((StandardPlayer) opponent).setEnergy(((StandardPlayer) opponent).getEnergy() - opponentCard.getCost());
            if (opponentCard.isBoth()) {
                this.damage(((UtilityCard)opponentCard).attack());
            } else if (opponentCard.isAttack()) {
                this.damage(((AttackCard)opponentCard).attack());
            } else {
                opponent.heal(3);
            }
            opponentCard.onAfterPlayed();
        }

        // Opponent cannot play card
        else {
            card.onBeforePlayed();
            opponentCard.onCannotAfford();
            this.setEnergy(this.getEnergy() - card.getCost());
            if (card.isBoth()) {
                opponent.damage(((UtilityCard)card).attack());
            } else if (card.isAttack()) {
                opponent.damage(((AttackCard)card).attack());
            } else {
                this.heal(3);
            }
            card.onAfterPlayed();
        }
    }

    @Override
    public void draw(int amount) {
        List<AbstractCard> drawn = this.getDeck().drawCards(amount);
        for (AbstractCard card : drawn) {
            card.onDrawn();
        }
        this.addToHand(drawn);
    }

    public void emptyHand() {
        this.getHand().clear();
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
