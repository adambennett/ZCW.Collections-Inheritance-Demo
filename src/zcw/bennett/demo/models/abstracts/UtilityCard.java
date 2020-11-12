package zcw.bennett.demo.models.abstracts;

import zcw.bennett.demo.interfaces.*;

import java.util.concurrent.*;

public abstract class UtilityCard extends AbstractCard implements Attacker, Defender {

    public UtilityCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
    }

    // Hooks
    public void onAttack() {}
    public void onDefend() {}

    @Override
    public Integer attack() {
        return ThreadLocalRandom.current().nextInt(0, this.getPower());
    }

    @Override
    public Boolean defend(int damage) {
        return ThreadLocalRandom.current().nextInt(0, this.getDefense()) >= damage;
    }
}
