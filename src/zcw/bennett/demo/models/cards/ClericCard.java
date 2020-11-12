package zcw.bennett.demo.models.cards;

import zcw.bennett.demo.models.abstracts.*;

public class ClericCard extends UtilityCard {

    public ClericCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
        if (this.getPower() > 6 || this.getDefense() > 6) {
            this.setPower(6);
            this.setDefense(6);
        }
    }

    @Override
    public void onDrawn() {
        this.getOwner().heal(3);
        System.out.println("\nCleric healed " + this.getOwner().getName() + " for 3 HP!\n");
    }

    @Override
    public void onDefend() {
        this.getOwner().heal(5);
        System.out.println("\nCleric healed " + this.getOwner().getName() + " for 5 HP!\n");
    }

    @Override
    public Boolean fortifiedDefend(int damage) {
        return null;
    }

    @Override
    public Integer recklessAttack() {
        return null;
    }
}
