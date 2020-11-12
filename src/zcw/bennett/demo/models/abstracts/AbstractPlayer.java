package zcw.bennett.demo.models.abstracts;

import java.util.*;

public abstract class AbstractPlayer<CardType extends AbstractCard> {

    private String name;
    private Integer health;
    private final Integer startingHealth;
    private final AbstractDeck<CardType> deck;
    private final List<CardType> hand;

    public AbstractPlayer(AbstractDeck<CardType> deck, Integer startingHealth) {
        this.deck = deck;
        this.startingHealth = startingHealth;
        this.health = startingHealth;
        this.hand = new ArrayList<>();
    }

    public abstract void playCard(CardType card, CardType opponentCard, AbstractPlayer<CardType> opponent);

    public abstract void draw(int amount);

    public void addToHand(List<CardType> cards) {
        this.hand.addAll(cards);
    }

    public void damage(int damageAmount) {
        this.setHealth(this.getHealth() - damageAmount);
    }

    public void heal(int healAmount) {
        this.setHealth(this.getHealth() + healAmount);
        if (this.getHealth() > this.getStartingHealth()) {
            this.setHealth(this.getStartingHealth());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractDeck<CardType> getDeck() {
        return deck;
    }

    public List<CardType> getHand() {
        return hand;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getStartingHealth() {
        return startingHealth;
    }
}
