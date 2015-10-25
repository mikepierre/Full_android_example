package com.orbtv.full_android_example;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by michaelpierre on 10/24/15.
 */
public class HistoryActivity extends ListActivity{
private ArrayList<BowlingScores> allBowlingScores;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.history_layout);

        MyBowlingScoresApplication app = (MyBowlingScoresApplication) getApplication();
        allBowlingScores = app.getAllBowlingScores();
        BowlingScores.updateRunningAverage(allBowlingScores);

        setListAdapter(
                new ArrayAdapter<BowlingScores>(this,
                        android.R.layout.simple_list_item_1,
                        allBowlingScores
                        )
        );
    }
}
