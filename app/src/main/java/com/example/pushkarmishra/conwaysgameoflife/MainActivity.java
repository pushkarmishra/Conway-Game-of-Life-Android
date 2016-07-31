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
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.content.Intent;

/**
 * This class contains the working of
 * introductory frame.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * The "buttonProceed" button is clicked.
     * Defined in {@code content_main.xml}
     */
    public void proceedButtonClick(View V) {
        RadioButton radio1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radioButton2);

        if (radio1.isChecked() == true) {
            Intent intent1 = new Intent(this, Preloaded_Library.class);
            startActivity(intent1);

        } else if (radio2.isChecked() == true) {
            Intent intent2 = new Intent(this, BoardDetails.class);
            startActivity(intent2);
        }
    }
}
