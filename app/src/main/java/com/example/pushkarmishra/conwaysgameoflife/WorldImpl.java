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
 * This class contains the main methods
 * corresponding to the logic behind
 * the Conway's Game of Life's simulation.
 */

public abstract class WorldImpl implements World {
    private int width;
    private int height;
    private int generation;

    protected WorldImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.generation = 0;
    }

    protected WorldImpl(WorldImpl prev) {
        this.width = prev.width;
        this.height = prev.height;
        this.generation = prev.generation + 1;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    protected String getCellAsString(int col, int row) {
        return getCell(col, row) ? "#" : "_";
    }

    public World nextGeneration(int log2StepSize) {
        WorldImpl world = this;
        long num_of_steps = 1l << (log2StepSize);

        for (int i = 1; i <= num_of_steps; ++i) {
            world = world.nextGeneration();
        }
        return world;
    }

    public int countLiveCells() {
        int count = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j)
                count += (getCell(j, i)) ? 1 : 0;
        }

        return count;
    }

    public int getPopulation() {
        return countLiveCells();
    }

    public String stringWorld() {
        String ret = "";
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j)
                ret += getCellAsString(j, i);
        }

        return ret;
    }

    protected int countNeighbours(int col, int row) {
        int ret = 0;
        if (col - 1 >= 0) {
            if (getCell(col - 1, row))
                ret++;
        }

        if (col + 1 < width) {
            if (getCell(col + 1, row))
                ret++;
        }

        if (row - 1 >= 0) {
            if (getCell(col, row - 1))
                ret++;
        }

        if (row + 1 < height) {
            if (getCell(col, row + 1))
                ret++;
        }

        if (col + 1 < width && row + 1 < height) {
            if (getCell(col + 1, row + 1))
                ret++;
        }

        if (col - 1 >= 0 && row + 1 < height) {
            if (getCell(col - 1, row + 1))
                ret++;
        }

        if (col - 1 >= 0 && row - 1 >= 0) {
            if (getCell(col - 1, row - 1))
                ret++;
        }

        if (col + 1 < width && row - 1 >= 0) {
            if (getCell(col + 1, row - 1))
                ret++;
        }

        return ret;
    }

    protected boolean computeCell(int col, int row) {
        boolean liveCell = getCell(col, row);
        int cntneighbour = countNeighbours(col, row);

        if (liveCell) {
            return (cntneighbour == 2 || cntneighbour == 3);

        } else if (cntneighbour == 3) {
            return true;
        }

        return false;
    }

    // Will be implemented by child class. Return true if cell (col,row) is alive.
    public abstract boolean getCell(int col, int row);

    // Will be implemented by child class. Set a cell to be live or dead.
    public abstract void setCell(int col, int row, boolean alive);

    // Will be implemented by child class. Step forward one generation.
    protected abstract WorldImpl nextGeneration();
}
