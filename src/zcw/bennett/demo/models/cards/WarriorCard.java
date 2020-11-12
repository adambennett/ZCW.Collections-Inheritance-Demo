package zcw.bennett.demo.models.cards;

import zcw.bennett.demo.models.abstracts.*;

public class WarriorCard extends AttackCard {

    public WarriorCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
        this.setPower(this.getPower() < 10 ? 10 : this.getPower());
    }

    @Override
    public Integer recklessAttack() {
        return null;
    }
}
