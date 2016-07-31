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

import android.graphics.*;
import android.view.View;
import android.content.Context;

import java.util.TreeSet;

/**
 * This class controls the layout of the
 * game board during the functioning,
 * i.e., the simulation.
 */

public class GameView extends View {
    private Paint paint;
    private World world = null;
    private float colstep, rowstep;
    private int height, width;
    private TreeSet<Pair> blackCells;

    public GameView(Context context, World w) {
        super(context);
        this.setWillNotDraw(false);
        world = w;
        blackCells = new TreeSet<>(new PairComparator());

        paint = new Paint();
    }

    public void completeCanvas(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawRect(25, 0, width - 25, height, paint);

        // Fill the cells
        paint.setStyle(Paint.Style.FILL);
        for (Pair p : blackCells) {
            float startX = 25 + p.first * colstep;
            float startY = p.second * rowstep;

            if (world.getCell(p.first, p.second)) {
                paint.setColor(Color.BLACK);

            } else {
                paint.setColor(Color.WHITE);
            }

            canvas.drawRect(startX, startY, startX + colstep, startY + rowstep,
                            paint);
        }

        // Drawing the cell boundaries
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawRect(25, 0, width - 25, height, paint);

        paint.setColor(Color.LTGRAY);
        float rowmark = rowstep;
        for (int i = 1; i < world.getHeight(); ++i) {
            canvas.drawLine(25, rowmark, width - 25, rowmark, paint);
            rowmark += rowstep;
        }

        float colmark = 25 + colstep;
        for (int j = 1; j < world.getWidth(); ++j) {
            canvas.drawLine(colmark, 0, colmark, height, paint);
            colmark += colstep;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = 1080;

        float dps = 350;
        float pxs = dps * getResources().getDisplayMetrics().density;
        height = (int) pxs;

        colstep = (float) (width - 50) / (float) (world.getWidth());
        rowstep = (float) height / (float) (world.getHeight());

        completeCanvas(canvas);
    }

    public void reDraw(World w, TreeSet<Pair> mBlackCells) {
        world = w;
        blackCells = mBlackCells;
        invalidate();
    }
}
