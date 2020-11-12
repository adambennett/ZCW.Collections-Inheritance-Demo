package zcw.bennett.demo;

import zcw.bennett.demo.models.decks.*;
import zcw.bennett.demo.models.players.*;

public class Main {

    public static void main(String[] args) {

        CuratedDeck playerDeck   = new CuratedDeck("Player Deck");
        CuratedDeck opponentDeck = new CuratedDeck("Opponent Deck");

        StandardPlayer player    = new StandardPlayer(playerDeck, 100);
        StandardPlayer opponent  = new StandardPlayer(opponentDeck, 100);

        player.setName("Adam Bennett");
        opponent.setName("Kris Younger");

        int rounds = 1;
        while (player.getHealth() > 0 && opponent.getHealth() > 0) {
            Game.runStandardRound(player, opponent, rounds);
            rounds++;
        }

        if (player.getHealth() < 1 && opponent.getHealth() < 1) {
            System.out.println("\n\nAfter " + (rounds - 1) + " rounds: \n" + "... it's a... tie?");
        } else if (player.getHealth() > 0) {
            System.out.println("\n\nAfter " + (rounds - 1) + " rounds: \n" + "YOU WIN!!!!!");
        } else {
            System.out.println("\n\nAfter " + (rounds - 1) + " rounds: \n" + "YOU LOSE!");
        }
    }
}
