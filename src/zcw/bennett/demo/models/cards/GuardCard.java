package zcw.bennett.demo.models.cards;

import zcw.bennett.demo.models.abstracts.*;

public class GuardCard extends DefendCard {

    public GuardCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
        this.setDefense(this.getDefense() < 10 ? 10 : this.getDefense());
    }

    @Override
    public Boolean fortifiedDefend(int damage) {
        return null;
    }
}
