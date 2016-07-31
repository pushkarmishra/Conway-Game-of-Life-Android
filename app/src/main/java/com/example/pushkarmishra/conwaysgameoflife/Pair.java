/*
 * Author: Pushkar Mishra.
 * Date: March 2016
 *
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the condition
 * that the above ownership notice and this permission notice
 * shall be included in all copies or substantial portions
 * of the Software.
 */

package com.example.pushkarmishra.conwaysgameoflife;

import java.util.Comparator;

/**
 * This is a simple API
 * for a pair.
 */

public class Pair {
    int first;
    int second;

    public Pair(int x, int y) {
        first = x;
        second = y;
    }

    @Override
    public boolean equals(Object mObj) {
        Pair obj = (Pair) mObj;
        return (this.first == obj.first && this.second == obj.second);
    }
}

class PairComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return (a.first == b.first) ? a.second - b.second : a.first - b.first;
    }
}
