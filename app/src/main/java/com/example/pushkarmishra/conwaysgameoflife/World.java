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
 * Basic interface for the
 * world object.
 */

public interface World {
    void setCell(int col, int row, boolean alive);
    boolean getCell(int col, int row);
    int getWidth();
    int getHeight();
    String stringWorld();

//        int getGeneration();
    int getPopulation();
    World nextGeneration(int log2StepSize);
}
