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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * This class contains the methods
 * to display the statistics for
 * the simulation that is running.
 */

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intentStats = getIntent();
        int generation = intentStats.getIntExtra("Generation", 1);
        int cycle = intentStats.getIntExtra("Cycle", -1);
        int maxPopulation = intentStats.getIntExtra("MaxPopulation", 0);
        int minPopulation = intentStats.getIntExtra("MinPopulation", 0);

        TextView textStatsGen = (TextView) findViewById(R.id.textStatsGen);
        textStatsGen.setText(Integer.toString(generation));

        TextView textStatsCycle = (TextView) findViewById(R.id.textStatsCycle);
        if(cycle >= 0) {
            textStatsCycle.setText("Generation " + Integer.toString(cycle));

        } else {
            textStatsCycle.setText("No cycle detect up till generation " + Integer.toString(generation));
        }

        TextView textStatsMaxPop = (TextView) findViewById(R.id.textStatsMaxPop);
        textStatsMaxPop.setText(Integer.toString(maxPopulation));

        TextView textStatsMinPop = (TextView) findViewById(R.id.textStatsMinPop);
        textStatsMinPop.setText(Integer.toString(minPopulation));
    }
}

