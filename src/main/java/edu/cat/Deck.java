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
package edu.cat;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Standard deck of cards. Can use multiple decks with {@link #Deck(int)}.
 *
 * @author Christopher Towner <christopher.allen.towner@gmail.com>
 */
public class Deck {

    private final LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();
        generate(1);
    }

    public Deck(int count) {
        cards = new LinkedList<>();
        generate(count);
    }

    /**
     * Generate a standard deck of cards.
     *
     * @param count
     */
    private void generate(int count) {
        for (int i = 1; i <= count; i++) {
            for (Suit s : Suit.values()) {
                for (Rank r : Rank.values()) {
                    cards.add(new Card(s, r));
                }
            }
        }
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Sorts the deck by suit and rank.
     */
    public void sort() {
        Collections.sort(cards);
    }
    
    /**
     * Draw top card.
     *
     * @return
     */
    public Card draw() {
        return cards.poll();
    }

    /**
     * Draw multiple cards from top of deck.
     *
     * @param count number of cards
     * @return
     */
    public List<Card> draw(int count) {
        List<Card> draw = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Card c = cards.poll();
            if (c != null) {
                draw.add(c);
            }
        }
        return draw;
    }

    public List<Card> getCards() {
        return cards;
    }
}
