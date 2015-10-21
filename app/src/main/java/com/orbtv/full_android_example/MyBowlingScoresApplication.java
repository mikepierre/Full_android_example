package com.orbtv.full_android_example;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by michaelpierre on 10/20/15.
 */
public class MyBowlingScoresApplication extends Application {

    private ArrayList<BowlingScores> allBowlingScores;

    @Override
    public void onCreate(){
        super.onCreate();

        allBowlingScores = new ArrayList<BowlingScores>();

    }

    public void addBowlingScores(BowlingScores bowlingScores){
        assert bowlingScores != null;
        allBowlingScores.add(bowlingScores);
    }

    public ArrayList<BowlingScores> getAllBowlingScores() {
        return allBowlingScores;
    }

    public void setAllBowlingScores(ArrayList<BowlingScores> allBowlingScores) {
        this.allBowlingScores = allBowlingScores;
    }

}
