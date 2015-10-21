package com.orbtv.full_android_example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button historyButton;
    private EditText game1ScoreEditText;
    private EditText game2ScoreEditText;
    private EditText game3ScoreEditText;
    private TextView date;
    private TextView seriesTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpViews();

        // put todays date on the screen
        Date today = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String cs = df.format(today);
        date.setText(cs);
    }
    // get the three scores from the interface, validate the scores, create an object to hold the scores.
    public void saveClickHandler(View v){
        String rawScore;
        rawScore = game1ScoreEditText.getText().toString();
        int score1 = Integer.parseInt(rawScore);
        rawScore = game2ScoreEditText.getText().toString();
        int score2 = Integer.parseInt(rawScore);
        rawScore = game3ScoreEditText.getText().toString();
        int score3 = Integer.parseInt(rawScore);
        if(isValid(score1) && isValid(score2) && isValid(score3)){
            // make object of bowling scores
            BowlingScores bowlingScores;
            bowlingScores = new BowlingScores(score1, score2, score3, new Date());
            seriesTotal.setText(" " + bowlingScores.calculateSeriesScore());
            // get aplication object to add bowling score object to array list
            MyBowlingScoresApplication app = (MyBowlingScoresApplication) getApplication();
            app.addBowlingScores(bowlingScores);
        } else {
            // pop up dialog indicating data is invalid.
        }

    }

    private boolean isValid(int score){
        if(score >= 0 && score <= 300)
            return true;
        return false;
    }

    private void setUpViews(){
        game1ScoreEditText = (EditText) findViewById(R.id.game1EditText);
        game2ScoreEditText = (EditText) findViewById(R.id.game2EditText);
        game3ScoreEditText = (EditText) findViewById(R.id.game3EditText);
        date = (TextView) findViewById(R.id.DatetextView);
        seriesTotal = (TextView) findViewById(R.id.seriesTotalText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
