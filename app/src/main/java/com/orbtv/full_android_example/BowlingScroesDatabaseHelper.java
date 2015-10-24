package com.orbtv.full_android_example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by michaelpierre on 10/24/15.
 */
public class BowlingScroesDatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "BowlingScroesDatabaseHelper.SQLite";
    public static final int DB_VERSION = 1;
    public static String BOWLING_SCORES_TABLE = "BowlingScoresTable";
    public static String RECORD_ID = "ID";
    public static String DATE = "Date";
    public static String GAME1 = "Game1";
    public static String GAME2 ="Game2";
    public static String GAME3 ="Game3";
    public BowlingScroesDatabaseHelper(Context context){
        super(context, DB_NAME,
                null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bowlingScoresDB) {
        String sqlStatement = "CREATE TABLE " +BOWLING_SCORES_TABLE
                +" (" + RECORD_ID + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +DATE + "INTEGER,"
                +GAME1 + "INTEGER"
                +GAME2 + "INTEGER"
                +GAME3 + "INTEGER"
                +");";

        Log.d("Bowing Database", sqlStatement);
        bowlingScoresDB.execSQL(sqlStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
