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
import edu.cat.Card;
import java.util.LinkedList;
import java.util.List;

/**
 * Basic players in Blackjack.
 *
 * @author Christopher Towner <christopher.allen.towner@gmail.com>
 */
public class Player {

    private final String name;
    private final List<Card> cards;
    public int wins;
    public boolean dealer;

    public Player(String name) {
        this.name = name;
        this.cards = new LinkedList<>();
        this.wins = 0;
        this.dealer = false;
    }

    /**
     * Player draws cards until over hand value over 16. Dealer will draw until
     * he beats the high hand or gets 21.
     *
     * @param deck deck to draw from
     * @param high current high value, only matters to dealer
     * @return total value of hand
     */
    public int takeTurn(Deck deck, int high) {
        if (dealer) {
            while (getTotal() < high && getTotal() != 21) {
                draw(deck);
            }
        } else {
            while (getTotal() < 16) {
                draw(deck);
            }
        }
        return getTotal();
    }

    @Override
    public String toString() {
        return String.format("%s (hand: %s, total: %d)", name, cards, getTotal());
    }

    /**
     * Draw a card from deck.
     *
     * @param deck
     * @return if card was drawn
     */
    public boolean draw(Deck deck) {
        Card c = deck.draw();
        return (c == null || cards.add(c));
    }

    public void clearHand() {
        this.cards.clear();
    }

    /**
     * Return total value of hand. Evaluates all aces as (11) unless brings
     * total over 21.
     *
     * @return
     */
    public int getTotal() {
        int total = cards.stream().mapToInt(c -> c.getRank().getValue()).sum();
        int aces = (int) cards.stream().filter(c -> c.getRank() == Rank.ACE).count();

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    /**
     * Set this hand as dealers.
     *
     * @param dealer
     */
    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }
    
    public void won() {
        wins++;
    }
    
    public int getWins() {
        return wins;
    }
}
