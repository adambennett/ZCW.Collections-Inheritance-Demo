package zcw.bennett.demo.models.abstracts;

import java.util.*;
import java.util.concurrent.*;

public abstract class AbstractDeck<CardType extends AbstractCard> extends ArrayList<CardType> {

    private String deckName;
    private Integer power = 0;
    private Boolean containsBannedCards = false;
    private final Boolean allowsBannedCards;
    private final Map<String, Integer> cardMap;

    public abstract Boolean isBanned(CardType cardToCheck);

    public AbstractDeck(String name) { this(name, new ArrayList<>(), false); }

    public AbstractDeck(String name, List<CardType> cards) {
        this(name, cards, false);
    }

    public AbstractDeck(String name, List<CardType> cards, boolean skipBannedCards) {
        this.cardMap = new HashMap<>();
        this.allowsBannedCards = !skipBannedCards;
        this.deckName = name;
        for (CardType card : cards) {
            this.addCard(card);
            this.containsBannedCards = this.containsBannedCards || isBanned(card);
        }
    }

    public void addCard(CardType card) {
        if (this.allowsBannedCards || !isBanned(card)) {
            this.add(card);
            this.power += card.getPower();
            this.cardMap.compute(card.getId(), (k, v) -> v == null ? 1 : v + 1);
        }
    }

    public List<CardType> drawCards(int amount) {
        int counter = 0;
        List<CardType> output = new ArrayList<>();
        while (this.size() > 0 && counter < amount) {
            int endSize = this.size() > 1 ? this.size() - 1 : -1;
            if (endSize > -1) {
                output.add(this.remove(ThreadLocalRandom.current().nextInt(0, endSize)));
                counter++;
            } else {
                counter = amount;
            }
        }
        return output;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public Integer getPower() {
        return power;
    }

    public Boolean getContainsBannedCards() {
        return containsBannedCards;
    }
}
