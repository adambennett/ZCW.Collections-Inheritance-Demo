package zcw.bennett.demo.models.abstracts;

import zcw.bennett.demo.interfaces.*;

import java.util.concurrent.*;

public abstract class DefendCard extends AbstractCard implements Defender {

    public DefendCard(String name, String id, Integer power, Integer defense, Integer cost) {
        super(name, id, power, defense, cost);
    }

    // Hooks
    public void onDefend() {}

    @Override
    public Boolean defend(int damage) {
        return ThreadLocalRandom.current().nextInt(0, this.getDefense()) >= damage;
    }
}
