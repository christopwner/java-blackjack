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

/**
 * A playing card. Maintains a constant suit and rank but has an assignable
 * value.
 *
 * @author Christopher Towner <christopher.allen.towner@gmail.com>
 */
public class Card implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;
    private int value = Integer.MIN_VALUE;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%s of %ss", rank, suit);
    }

    @Override
    public int compareTo(Card o) {
        //no cards have manually set values (common)
        if (value == Integer.MIN_VALUE && o.getValue() == Integer.MIN_VALUE) {
            int v = Integer.compare(suit.getValue(), o.getSuit().getValue());
            if (v == 0) {
                return Integer.compare(rank.getValue(), o.getRank().getValue());
            } else {
                return v;
            }
        }

        //this card has manually set value (uncommon)
        if (value != Integer.MIN_VALUE && o.getValue() == Integer.MIN_VALUE) {
            int v = Integer.compare(value, o.getSuit().getValue());
            if (v == 0) {
                return Integer.compare(value, o.getRank().getValue());
            } else {
                return v;
            }
        }

        //other card has manually set value (uncommon)
        if (value == Integer.MIN_VALUE && o.getValue() != Integer.MIN_VALUE) {
            int v = Integer.compare(suit.getValue(), o.getValue());
            if (v == 0) {
                return Integer.compare(rank.getValue(), o.getValue());
            } else {
                return v;
            }
        }

        //both cards have manually set values (rare)
        if (value != Integer.MIN_VALUE && o.getValue() != Integer.MIN_VALUE) {
            return Integer.compare(value, o.getValue());
        }

        throw new ArithmeticException("Unable to compare cards.");
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
