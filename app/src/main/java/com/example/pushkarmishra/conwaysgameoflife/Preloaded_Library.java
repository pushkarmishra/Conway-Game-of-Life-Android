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
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.DialogInterface;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * This class loads the library of
 * starting patterns available at
 * http://www.cl.cam.ac.uk/teaching/1516/OOProg/life.txt
 */

public class Preloaded_Library extends AppCompatActivity {
    private ArrayList<String> names = null;
    private List<Pattern> list = null;
    private int selectedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloaded__library);

        ListView listView = (ListView) findViewById(R.id.listView);
        String source = "http://www.cl.cam.ac.uk/teaching/1516/OOProg/life.txt";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;
            }
        });

        try {
            list = loadFromURL(source);

        } catch (java.io.IOException e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setMessage("No internet connection. Please try manual editing mode.");
            alert.setCancelable(true);

            alert.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });
            alert.show();
        }

        this.setPatterns(list);
        if (names != null) {
            listView.setAdapter(new ArrayAdapter<>(this,
                                    android.R.layout.simple_list_item_1,
                                    names));
        }
    }

    public static List<Pattern> load(Reader r) throws java.io.IOException {
        BufferedReader buff = new BufferedReader(r);
        List<Pattern> resultList = new LinkedList<Pattern>();

        String input;
        while ((input = buff.readLine()) != null) {
            try {
                Pattern p = new Pattern(input);
                resultList.add(p);

            } catch (PatternFormatException ex) {
                // Continue.
            }
        }

        return resultList;
    }

    public static List<Pattern> loadFromURL(String url) throws IOException {
        URL destination = new URL(url);
        URLConnection conn = destination.openConnection();
        return load(new InputStreamReader(conn.getInputStream()));
    }

    public void setPatterns(List<Pattern> list) {
        if (list == null) {
            return;
        }

        names = new ArrayList<>();
        for (Pattern p : list) {
            names.add(p.getName() + " (" + p.getAuthor() + ")");
        }
    }

    /**
     * The "buttonLibraryGame" button is clicked.
     * Defined in {@code content_preloaded_library.xml}
     */
    public void onGameButtonClick(View V) {
        if (selectedItem == -1) {
            return;
        }

        Pattern p = list.get(selectedItem);
        Intent intentStartGame = new Intent(this, MainGame.class);
        intentStartGame.putExtra("Pattern", p);

        // Pass the intent on to the main game
        startActivity(intentStartGame);
    }
}
