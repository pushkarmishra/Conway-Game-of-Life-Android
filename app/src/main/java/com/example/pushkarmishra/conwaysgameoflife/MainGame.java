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
import android.view.View;
import android.content.Intent;
import android.widget.*;

import java.util.*;

public class MainGame extends AppCompatActivity
{
        private GameView gameView = null;
        private Pattern pattern = null;
        private World world = null;
        private Timer timer;
        private TimerTask timerTask;
        private int timeStep = 501;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main_game);

                RelativeLayout.LayoutParams gameViewDetails = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                gameViewDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
                gameViewDetails.addRule(RelativeLayout.ALIGN_TOP);

                Intent intentStartGame = getIntent();
                pattern = (Pattern) intentStartGame.getSerializableExtra("Pattern");

                try {
                        world = initialiseWorld(pattern);
                }
                catch (Exception e){
                        AlertDialog.Builder alert = new AlertDialog.Builder(this);

                        alert.setMessage("An error occurred while initialising the world: " + e.getMessage());
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

                gameView = new GameView(this, world);
                RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
                gameLayout.addView(gameView, gameViewDetails);

                animation();
                SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
                seekBar.setProgress(50);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                timer.cancel();
                                timeStep = 1 + (100 - progress) * 10;
                                animation();
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                                ;
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                                ;
                        }
                });
        }

        private void getBlackCells(World w, TreeSet<Pair> blackCells){

                blackCells.clear();
                for(int i = 0; i<w.getHeight(); ++i){

                        for(int j = 0; j<w.getWidth(); ++j) {

                                if(w.getCell(j, i) == true){

                                        blackCells.add(new Pair(j, i));
                                }
                        }
                }
        }

        private void animation()
        {
                final TreeSet<Pair> blackCells = new TreeSet<Pair>(new PairComparator());
                timer = new Timer();

                timerTask = new TimerTask() {
                        @Override
                        public void run() {
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                getBlackCells(world, blackCells);
                                                gameView.reDraw(world, blackCells);

                                                world = world.nextGeneration(0);
                                        }
                                });
                        }
                };
                timer.schedule(timerTask, 0, timeStep);
        }

        public void changeColor(View V)
        {
                ;
        }

        public World initialiseWorld(Pattern p) throws PatternFormatException
        {
                World result = new ArrayWorld(p.getWidth(),p.getHeight());

                if (result != null)  p.initialise(result);
                return result;
        }

}
