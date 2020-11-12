package zcw.bennett.demo;

import zcw.bennett.demo.models.abstracts.*;
import zcw.bennett.demo.models.players.*;

public class Game {

    public static void runStandardRound(StandardPlayer player, StandardPlayer opponent, int round) {
        player.emptyHand();
        opponent.emptyHand();
        player.startRound(round);
        opponent.startRound(round);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("ROUND #" + round + "\n");
        System.out.println("Player   Energy: " + player.getEnergy() + "\n");
        System.out.println("Opponent Energy: " + opponent.getEnergy());
        player.draw(1);
        opponent.draw(1);
        AbstractCard playerCard   = player.getHand().get(0);
        AbstractCard opponentCard = opponent.getHand().get(0);
        player.playCard(playerCard, opponentCard, opponent);
        player.endRound(playerCard, opponentCard, opponent);
    }


}
