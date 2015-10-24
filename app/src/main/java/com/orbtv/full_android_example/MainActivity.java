package com.orbtv.full_android_example;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button historyButton;
    private EditText game1ScoreEditText;
    private EditText game2ScoreEditText;
    private EditText game3ScoreEditText;
    private TextView date;
    private TextView seriesTotal;
    private Button dateBtn;

    private int month;
    private int day;
    private int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpViews();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        // put todays date on the screen
        Date today = calendar.getTime();

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

        Log.d("EnterScores", "I hear the save btn");
        DatePickerDialog.OnDateSetListener datePickerListener;
        datePickerListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int yearOfYear, int monthOfYear, int dayOfMonth){

                year = yearOfYear;
                month = monthOfYear;
                day = dayOfMonth;

                Calendar cal = new GregorianCalendar(year, month, day);
                Date dateOfGames = cal.getTime();
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                String cs = df.format(dateOfGames);
                date.setText(cs);

            }
        };


        DatePickerDialog dpd = new DatePickerDialog(this,datePickerListener, year, month, day);
        dpd.show();

        if(isValid(score1) && isValid(score2) && isValid(score3)){
            // make object of bowling scores
            BowlingScores bowlingScores;
           // Date dateOfGames = new Date(year, month, day);
            Date dateOfGames = new GregorianCalendar(year, month, day).getTime();
            bowlingScores = new BowlingScores(score1, score2, score3, dateOfGames);
            seriesTotal.setText(" " + bowlingScores.calculateSeriesScore());
            // get application object to add bowling score object to array list
            MyBowlingScoresApplication app =
                    (MyBowlingScoresApplication) getApplication();
            app.addBowlingScores(bowlingScores);
        } else {
            // pop up dialog indicating data is invalid.
            // builder pattern.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Scores").setMessage("Bowling scores cannot ne greater than 300").setCancelable(false)
                    .setPositiveButton("Ok",

                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }
                    );
            AlertDialog alert = builder.create();
            alert.show();
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
