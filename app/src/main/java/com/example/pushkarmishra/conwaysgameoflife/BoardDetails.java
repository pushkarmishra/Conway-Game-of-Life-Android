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

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * This class provides the interface where
 * where the user can customise the board
 * before specifying a starting pattern on it.
 */

public class BoardDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_details);
    }

    /**
     * The "Proceed" button is clicked.
     * Defined in {@code content_board_details.xml}
     */
    public void onProceedClick(View V) {
        EditText editTextName = (EditText) findViewById(R.id.editText);
        EditText editTextAuthor = (EditText) findViewById(R.id.editText2);
        EditText editTextRows = (EditText) findViewById(R.id.editText3);
        EditText editTextCols = (EditText) findViewById(R.id.editText4);

        if (editTextRows.length() < 1 || editTextRows.length() > 3) {
            editTextRows.setText("");
            editTextRows.setHint("Please enter a number between 1 and 100");
            return;
        }

        if (editTextCols.length() < 1 || editTextCols.length() > 3) {
            editTextCols.setText("");
            editTextCols.setHint("Please enter a number between 1 and 100");
            return;
        }

        int height = Integer.parseInt(editTextRows.getText().toString());
        int width = Integer.parseInt(editTextCols.getText().toString());
        String name = editTextName.getText().toString();
        String author = editTextAuthor.getText().toString();

        name = name.trim();
        author = author.trim();

        if (height < 1 || height > 100 ||
            editTextRows.length() < 1 ||
            editTextRows.length() > 3) {

            editTextRows.setText("");
            editTextRows.setHint("Please enter a number between 1 and 100");
            return;
        }

        if (width < 1 || width > 100 ||
            editTextCols.length() < 1 ||
            editTextCols.length() > 3) {

            editTextCols.setText("");
            editTextCols.setHint("Please enter a number between 1 and 100");
            return;
        }

        /**
         * Passing the gathered details to the Editor calss
         * so that it can be configured accordingly.
         */
        Intent intentStartEditor = new Intent(this, Editor.class);
        intentStartEditor.putExtra("Name", name);
        intentStartEditor.putExtra("Author", author);
        intentStartEditor.putExtra("Height", height);
        intentStartEditor.putExtra("Width", width);

        startActivity(intentStartEditor);
    }
}
