package zcw.bennett.demo.models.cards;

import zcw.bennett.demo.models.abstracts.*;

import java.util.concurrent.*;

public class SuperheroCard extends UtilityCard {

    public SuperheroCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
        if (this.getPower() < 20 && this.getDefense() < 20) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                this.setPower(ThreadLocalRandom.current().nextInt(20, 25));
            } else {
                this.setDefense(ThreadLocalRandom.current().nextInt(20, 25));
            }
        }
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
