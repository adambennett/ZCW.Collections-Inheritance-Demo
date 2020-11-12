package zcw.bennett.demo.models.abstracts;

import zcw.bennett.demo.interfaces.*;
import zcw.bennett.demo.enums.*;

public abstract class AbstractCard {

    private final String name;
    private final String id;
    private Integer power;
    private Integer defense;
    private Integer cost;
    private AbstractPlayer<AbstractCard> owner;

    public AbstractCard(String name, String id, Integer power, Integer defense, Integer cost) {
        this.name = name;
        this.id = id;
        this.power = power;
        this.defense = defense;
        this.cost = cost;
    }

    // Hooks
    public void onDrawn() {}
    public void onBeforePlayed() {}
    public void onAfterPlayed()  {}
    public void onCannotAfford() {}

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public AbstractPlayer<AbstractCard> getOwner() {
        return owner;
    }

    public void setOwner(AbstractPlayer<AbstractCard> owner) {
        this.owner = owner;
    }

    public CardClass getCardClass() {
        if (this instanceof UtilityCard) {
            return CardClass.BOTH;
        } else if (this instanceof AttackCard) {
            return CardClass.ATTACK;
        } else if (this instanceof DefendCard) {
            return CardClass.DEFEND;
        } else {
            return CardClass.NONE;
        }
    }

    public boolean isAttack() {
        return this.getCardClass().equals(CardClass.ATTACK);
    }

    public boolean isDefend() {
        return this.getCardClass().equals(CardClass.DEFEND);
    }

    public boolean isBoth() {
        return this.getCardClass().equals(CardClass.BOTH);
    }

    @Override
    public String toString() {
        return "[ " + getName() + " ] (" + getCost() + ") [ " + getPower() + " / " + getDefense() + " ]";
    }
}
