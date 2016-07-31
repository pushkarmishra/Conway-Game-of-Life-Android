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

/**
 * This class provides the underlying array
 * structure for the game.
 */

public class ArrayWorld extends WorldImpl {
    private boolean[][] cells;

    public ArrayWorld(int w, int h) {
        super(w, h);
        cells = new boolean[h][w];
    }

    protected ArrayWorld(ArrayWorld Previous) {
        super(Previous);
        int w = Previous.getWidth();
        int h = Previous.getHeight();

        cells = new boolean[h][w];
    }

    public boolean getCell(int col, int row) {
        return cells[row][col];
    }

    public void setCell(int col, int row, boolean alive) {
        cells[row][col] = alive;
    }

    /**
     * This routine calculates the next generation
     * from the pattern that already exits.
     */
    protected WorldImpl nextGeneration() {
        WorldImpl world = new ArrayWorld(this);
        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                boolean value = computeCell(j, i);
                world.setCell(j, i, value);
            }
        }

        return world;
    }
}
