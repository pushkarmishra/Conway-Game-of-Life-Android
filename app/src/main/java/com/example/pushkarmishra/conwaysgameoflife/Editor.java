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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.TreeSet;

/**
 * This class contains the editor module
 * that controls the behavior of the interface
 * where the user can specify a custom starting
 * pattern.
 */

public class Editor extends AppCompatActivity {
    private GameView editorView = null;
    private String input = "";
    private World world = null;
    private TreeSet<Pair> blackCells = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Set layouts for the board
        RelativeLayout.LayoutParams editorViewDetails =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        editorViewDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editorViewDetails.addRule(RelativeLayout.ALIGN_TOP);

        initialiseEditor();
        editorView = new GameView(this, world);

        RelativeLayout editorLayout =
                (RelativeLayout) findViewById(R.id.editorLayout);
        editorLayout.addView(editorView, editorViewDetails);

        // Initialise the set to note the points.
        blackCells = new TreeSet<>(new PairComparator());

        // Editing cells on touch
        editorView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float xCoord = event.getX();
                float yCoord = event.getY();
                float pxsHeight = 350 * getResources().getDisplayMetrics().density;

                xCoord /= ((float) 1278 / (float) world.getWidth());
                yCoord /= (pxsHeight / (float) world.getHeight());

                int intx = (int) xCoord;
                int inty = (int) yCoord;

                if (intx >= world.getWidth() || inty >= world.getHeight()) {
                    return false;
                }

                boolean alive = world.getCell(intx, inty);
                if (alive) {
                    Pair getRem = new Pair(intx, inty);
                    blackCells.remove(getRem);

                } else {
                    blackCells.add(new Pair(intx, inty));
                }

                world.setCell(intx, inty, !alive);
                editorView.reDraw(world, blackCells);

                return true;
            }
        });
    }

    private void initialiseEditor() {
        int height, width;
        String name, author;

        Intent intent = getIntent();

        height = intent.getIntExtra("Height", 8);
        width = intent.getIntExtra("Width", 8);

        name = intent.getStringExtra("Name");
        author = intent.getStringExtra("Author");

        input = name + ":" + author + ":" + String.valueOf(width)
                     + ":" + String.valueOf(height) + ":0:0:";
        world = new ArrayWorld(width, height);
    }

    /**
     * The "buttonEditorGame" button is clicked.
     * Defined in {@code content_editor.xml}
     */
    public void clickEditorGame(View V) {
        String newPattern = input;
        for (int i = 0; i < world.getHeight(); ++i) {
            for (int j = 0; j < world.getWidth(); ++j) {
                newPattern += world.getCell(j, i) ? "1" : "0";
            }
            newPattern += " ";
        }

        Pattern p = null;
        newPattern = newPattern.trim();

        try {
            p = new Pattern(newPattern);

        } catch (Exception e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setMessage(e.getMessage());
            alert.setCancelable(true);

            alert.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alert.show();
        }

        Intent intentStartGame = new Intent(this, MainGame.class);
        intentStartGame.putExtra("Pattern", p);
        startActivity(intentStartGame);
    }
}
