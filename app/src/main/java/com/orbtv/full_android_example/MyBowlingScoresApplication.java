package com.orbtv.full_android_example;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import  static  com.orbtv.full_android_example.BowlingScroesDatabaseHelper.*;

/**
 * Created by michaelpierre on 10/20/15.
 */
public class MyBowlingScoresApplication extends Application {

    private ArrayList<BowlingScores> allBowlingScores;
    private SQLiteDatabase bowlingScoresDB;
    @Override
    public void onCreate(){
        super.onCreate();

        BowlingScroesDatabaseHelper databaseHelper = new BowlingScroesDatabaseHelper(this);
        bowlingScoresDB = databaseHelper.getWritableDatabase();

        readBowlingScoresFromDB();

    }

    public void readBowlingScoresFromDB() {
        allBowlingScores = new ArrayList<BowlingScores>();

        Cursor bowlingScoreCursor;
        bowlingScoreCursor = bowlingScoresDB.query(BOWLING_SCORES_TABLE,
                new String[]{RECORD_ID, DATE, GAME1, GAME2, GAME3},
                null, null, null, null, DATE);

        bowlingScoreCursor.moveToFirst();
        BowlingScores tempBS;

        if (!bowlingScoreCursor.isAfterLast()) {
            do {
                long id = bowlingScoreCursor.getLong(0);
                long dateEpoch = bowlingScoreCursor.getLong(1);
                int game1 = bowlingScoreCursor.getInt(2);
                int game2 = bowlingScoreCursor.getInt(3);
                int game3 = bowlingScoreCursor.getInt(4);

                tempBS = new BowlingScores(id, dateEpoch, game1, game2, game3);

                allBowlingScores.add(tempBS);
            } while (bowlingScoreCursor.moveToNext());

        }
        bowlingScoresDB.close(); // close connection.
    }

    public void addBowlingScores(BowlingScores bowlingScores){
        assert bowlingScores != null;

        // add to database

        ContentValues cv = new ContentValues();
        cv.put(BowlingScroesDatabaseHelper.DATE, bowlingScores.getDateEpoch());
        cv.put(BowlingScroesDatabaseHelper.GAME1, bowlingScores.getGame1());
        cv.put(BowlingScroesDatabaseHelper.GAME2, bowlingScores.getGame2());
        cv.put(BowlingScroesDatabaseHelper.GAME3, bowlingScores.getGame3());

        Log.d("Bowling Database", "Before Inserting a record" + bowlingScores);

        long idPassedBack = bowlingScoresDB.insert(BowlingScroesDatabaseHelper.BOWLING_SCORES_TABLE, null, cv);
        bowlingScores.setId(idPassedBack);

        Log.d("Bowling Database", "After Inserting a record" + bowlingScores);

        allBowlingScores.add(bowlingScores);
    }

    public ArrayList<BowlingScores> getAllBowlingScores() {
        return allBowlingScores;
    }

    public void setAllBowlingScores(ArrayList<BowlingScores> allBowlingScores) {
        this.allBowlingScores = allBowlingScores;
    }

}
