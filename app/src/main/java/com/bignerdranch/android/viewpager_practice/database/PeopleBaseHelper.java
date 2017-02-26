package com.bignerdranch.android.viewpager_practice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ftibi on 2017. 02. 25..
 */

public class PeopleBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "peopleBase.db";

    public PeopleBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PeopleDbSchema.PeopleTable.NAME +
        "(" + " _id integer primary key autoincrement, " +
        PeopleDbSchema.PeopleTable.Cols.UUID + ", " +
        PeopleDbSchema.PeopleTable.Cols.NAME + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
