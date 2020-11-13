package zcw.bennett.demo.models.decks;

import zcw.bennett.demo.models.abstracts.*;
import zcw.bennett.demo.models.cards.*;

import java.util.*;
import java.util.concurrent.*;

public class CuratedDeck extends AbstractDeck<AbstractCard> {

    public CuratedDeck(String name) {
        super(name);
        this.deckSetup();
    }

    private void deckSetup() {
        List<AbstractCard> cards = new ArrayList<>();
        cards.add(new WarriorCard("Legolas" , "LOTR-1", 6, 2, 0));
        cards.add(new WarriorCard("Mario" , "NIN-1", 5, 3, 0));
        cards.add(new WarriorCard("Luke Skywalker" , "SW-1", 6, 6, 1));
        cards.add(new WarriorCard("Khal Bharbo" , "GOT-1", 11, 2, 1));
        cards.add(new WarriorCard("Genghis Khan" , "IRL-1", 10, 5, 2));
        cards.add(new WarriorCard("Xiahou Dun" , "IRL-2", 12, 3, 2));
        cards.add(new WarriorCard("The Mountain" , "GOT-2", 30, 0, 3));

        cards.add(new GuardCard("Talos" , "GRK-1", 0, 8, 0));
        cards.add(new GuardCard("Argus" , "GRK-2", 3, 5, 0));
        cards.add(new GuardCard("Zavala" , "DEST-1", 0, 12, 1));
        cards.add(new GuardCard("Johnnie Cochran" , "IRL-3", 4, 8, 1));
        cards.add(new GuardCard("The Giant" , "GIANT", 5, 16, 2));
        cards.add(new GuardCard("MegaMan" , "VG-1", 7, 14, 2));
        cards.add(new GuardCard("Labyrinth Wall" , "YGO-1", 0, 25, 3));

        cards.add(new RangerCard("Aragorn" , "LOTR-2", 5, 5, 0));
        cards.add(new RangerCard("Jon Snow" , "GOT-3", 6, 4, 0));
        cards.add(new RangerCard("William \"Bigfoot\" Wallace" , "IRL-4", 8, 4, 1));
        cards.add(new RangerCard("Walker Texas" , "PUN-1", 9, 3, 1));
        cards.add(new RangerCard("Master Chief" , "HALO-1", 9, 11, 2));
        cards.add(new RangerCard("Samus" , "NIN-2", 11, 9, 2));
        cards.add(new RangerCard("Kratos" , "SONY-1", 14, 10, 3));

        cards.add(new SuperheroCard(randomHero() , "HERO", 15, 15, 3));
        cards.add(new ClericCard("Friar Tuck" , "RH-1", 3, 3, 2));

        for (AbstractCard card : cards) {
            this.addCard(card);
        }
    }

    private String randomHero() {
        List<String> heroes = new ArrayList<>();
        heroes.add("Superman");
        heroes.add("Batman");
        heroes.add("Aquaman");
        heroes.add("Spiderman");
        heroes.add("Wonder Woman");
        heroes.add("Green Lantern");
        heroes.add("Iron Man");
        heroes.add("The Hulk");
        heroes.add("Mr. Fantastic");
        heroes.add("Homelander");
        heroes.add("Starlight");
        return heroes.get(ThreadLocalRandom.current().nextInt(0, heroes.size() - 1));
    }

    @Override
    public Boolean isBanned(AbstractCard cardToCheck) {
        return cardToCheck instanceof SuperheroCard;
    }
}
