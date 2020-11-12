package zcw.bennett.demo.models.cards;

import zcw.bennett.demo.models.abstracts.*;

public class RangerCard extends UtilityCard {

    public RangerCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
        this.setCost(this.getCost() > 1 ? 1 : this.getCost());
        this.setPower(this.getPower() > 5 ? 5 : this.getPower());
        this.setDefense(this.getDefense() > 5 ? 5 : this.getDefense());
    }

    @Override
    public Integer recklessAttack() {
        return null;
    }

    @Override
    public Boolean fortifiedDefend(int damage) {
        return null;
    }
}
