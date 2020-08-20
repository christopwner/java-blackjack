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
 * Suits of a card deck. The default value is HEART low (1) to SPADE high (4).
 *
 * @author Christopher Towner <christopher.allen.towner@gmail.com>
 */
public enum Suit implements Comparable<Suit>{
    HEART(1), CLUB(2), DIAMOND(3), SPADE(4);
    
    private int value;

    private Suit(int value) {
        this.value = value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
