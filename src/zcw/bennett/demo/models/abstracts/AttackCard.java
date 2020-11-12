package zcw.bennett.demo.models.abstracts;

import zcw.bennett.demo.interfaces.*;

import java.util.concurrent.*;

public abstract class AttackCard extends AbstractCard implements Attacker {

    public AttackCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
    }

    // Hooks
    public void onAttack() {}

    @Override
    public Integer attack() {
        return ThreadLocalRandom.current().nextInt(0, this.getPower());
    }
}
