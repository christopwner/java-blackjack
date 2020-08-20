/*
 * Copyright (C) 2020 Christopher Towner <christopher.allen.towner@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.cat.blackjack;

import edu.cat.Deck;
import edu.cat.Rank;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple automatic blackjack game with configurable shoe (number of decks).
 *
 * @author Christopher Towner <christopher.allen.towner@gmail.com>
 */
public class Blackjack {

    private static final int CARDS_PER_HAND = 2;
    private static final int SHOE_SIZE = 6;
    
    private static final int PLAYER_COUNT = 3;

    private final Deck deck;
    private final List<Player> players;
    private final Player dealer;
    private int handsPlayed;

    public Blackjack() {
        //set face values to 10
        Rank.JACK.setValue(10);
        Rank.QUEEN.setValue(10);
        Rank.KING.setValue(10);

        //set ace value to 11
        Rank.ACE.setValue(11);

        //create deck and shuffle
        deck = new Deck(SHOE_SIZE);
        deck.shuffle();

        //create players/dealer
        players = new LinkedList<>();
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            players.add(new Player(String.format("Player %d", i)));
        }

        dealer = new Player("Dealer");
        dealer.setDealer(true);
        players.add(dealer);

        handsPlayed = 0;
    }

    /**
     * Deal each of the players, one card, until hand full (2).
     *
     * @return false if not enough cards available
     */
    public boolean dealHand() {
        for (int cards = 0; cards < CARDS_PER_HAND; cards++) {
            for (Player p : players) {
                if (!p.draw(deck)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Play the current hand.
     *
     * @return winner, or null if push
     */
    public Player playHand() {
        Player winner = null;
        int v, high = 0;
        for (Player p : players) {
            v = p.takeTurn(deck, high);
            if (v == -1) {
                //we ran out of cards
                return null;
            } else if (v <= 21 && v > high) {
                //new high hand
                high = v;
                winner = p;
            } else if (v == high && p.dealer) {
                //dealer matches high hand
                return null;
            }

        }
        return winner;
    }

    /**
     * Clear all players hands and increment hands dealt.
     */
    public void clearHand() {
        players.forEach(Player::clearHand);
        handsPlayed++;
    }

    public int getHandsPlayed() {
        return handsPlayed;
    }

    public Player getDealer() {
        return dealer;
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();

        //loop until we run out of cards
        while (game.dealHand()) {
            Player winner = game.playHand();
            if (winner != null) {
                winner.won();
                System.out.println(String.format("Winner is: %s", winner));
            } else {
                System.out.println("Push");
            }

            game.clearHand();
        }

        // sout hands played with house percentage
        System.out.println(String.format("Number of games: %d", game.getHandsPlayed()));
        System.out.println(String.format("Dealer success: %f",
                (float) game.getDealer().getWins() * 100 / game.getHandsPlayed()));
    }
}
