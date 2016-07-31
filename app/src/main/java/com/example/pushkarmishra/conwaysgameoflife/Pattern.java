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

import java.io.Serializable;

/**
 * This class deals with parsing of the
 * input pattern.
 */

public class Pattern implements Serializable {
    private String name;
    private String author;
    private int width;
    private int height;
    private int startCol;
    private int startRow;
    private String cells;

    public Pattern(String format) throws PatternFormatException {
        String arguments[] = format.split(":");

        if (arguments.length != 7)
            throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found " + arguments.length + ").");

        name = arguments[0];
        author = arguments[1];

        try {
            width = Integer.parseInt(arguments[2]);
        } catch (NumberFormatException b) {
            throw new PatternFormatException("Invalid pattern format: Could not interpret the width field as a number ('" + arguments[2] + "' given).");
        }

        try {
            height = Integer.parseInt(arguments[3]);
        } catch (NumberFormatException b) {
            throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('" + arguments[3] + "' given).");
        }

        try {
            startCol = Integer.parseInt(arguments[4]);
        } catch (NumberFormatException b) {
            throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('" + arguments[4] + "' given).");
        }

        try {
            startRow = Integer.parseInt(arguments[5]);
        } catch (NumberFormatException b) {
            throw new PatternFormatException("Invalid pattern format: Could not interpret the startY field as a number ('" + arguments[5] + "' given).");
        }

        cells = arguments[6];
    }

    public void initialise(World world) throws PatternFormatException {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                world.setCell(j, i, false);
            }
        }

        String cells_splitted[] = cells.split(" ");
        for (int i = 0; i < cells_splitted.length; ++i) {
            for (int j = 0; j < cells_splitted[i].length(); ++j) {
                if (cells_splitted[i].charAt(j) != '0' &&
                    cells_splitted[i].charAt(j) != '1') {

                    throw new PatternFormatException("Invalid pattern format: Malformed pattern '" + cells + "'.");
                }

                if (i + startRow >= height || j + startCol >= width) {
                    throw new PatternFormatException("Some index out of bounds.");
                }

                world.setCell(startCol + j, startRow + i,
                                (cells_splitted[i].charAt(j) == '1'));
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getAuthor() {
        return author;
    }
}
