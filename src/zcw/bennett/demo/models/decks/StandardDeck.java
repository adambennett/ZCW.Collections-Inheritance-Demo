package zcw.bennett.demo.models.decks;

import zcw.bennett.demo.models.abstracts.*;
import zcw.bennett.demo.models.cards.*;

import java.util.*;

public class StandardDeck extends AbstractDeck<AbstractCard> {

    public StandardDeck(String name, List<AbstractCard> cards) {
        super(name, cards, true);
    }

    public StandardDeck(String name, List<AbstractCard> cards, boolean skipBannedCards) {
        super(name, cards, skipBannedCards);
    }

    @Override
    public Boolean isBanned(AbstractCard cardToCheck) {
        return cardToCheck instanceof SuperheroCard;
    }
}
