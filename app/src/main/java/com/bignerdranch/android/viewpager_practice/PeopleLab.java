package com.bignerdranch.android.viewpager_practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.viewpager_practice.database.PeopleBaseHelper;
import com.bignerdranch.android.viewpager_practice.database.PeopleCursorWrapper;
import com.bignerdranch.android.viewpager_practice.database.PeopleDbSchema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class PeopleLab {
    private static PeopleLab sPeopleLab;

    private List<People> mPeoples;

    SQLiteDatabase mDatabase;
    Context mContext;

    public static PeopleLab get(Context context) {
        if(sPeopleLab == null) {
            sPeopleLab = new PeopleLab(context);
        }
        return sPeopleLab;
    }

    private PeopleLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PeopleBaseHelper(mContext).getWritableDatabase();
    }

    public void addPeople(People people) {
        ContentValues values = getContentValues(people);
        mDatabase.insert(PeopleDbSchema.PeopleTable.NAME, null, values);
    }

    public List<People> getPeople() {
        List<People> peoples = new ArrayList<>();

        PeopleCursorWrapper cursor = queryPoeple(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                peoples.add(cursor.getPeople());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return peoples;
    }

    public People getPeople(UUID id) {
        PeopleCursorWrapper cursor = queryPoeple(
                PeopleDbSchema.PeopleTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPeople();
        } finally {
            cursor.close();
        }
    }


    public void removePeople(UUID id) {
        PeopleCursorWrapper cursor = queryPoeple(
                PeopleDbSchema.PeopleTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if(cursor.getCount()==0) {
                return;
            }
            cursor.moveToFirst();
            mDatabase.delete(PeopleDbSchema.PeopleTable.NAME,
                    PeopleDbSchema.PeopleTable.Cols.UUID + " = ?",
                    new String[] { id.toString()});
        } finally {
            cursor.close();
        }

    }

    private PeopleCursorWrapper queryPoeple(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                PeopleDbSchema.PeopleTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new PeopleCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(People people) {
        ContentValues values = new ContentValues();
        values.put(PeopleDbSchema.PeopleTable.Cols.UUID, people.getId().toString());
        values.put(PeopleDbSchema.PeopleTable.Cols.NAME, people.getName());

        return values;
    }

}
