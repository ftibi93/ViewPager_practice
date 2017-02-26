package com.bignerdranch.android.viewpager_practice.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.viewpager_practice.People;

import java.util.UUID;

/**
 * Created by ftibi on 2017. 02. 25..
 */

public class PeopleCursorWrapper extends CursorWrapper{
    public PeopleCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public People getPeople() {
        String uuidString = getString(getColumnIndex(PeopleDbSchema.PeopleTable.Cols.UUID));
        String name = getString(getColumnIndex(PeopleDbSchema.PeopleTable.Cols.NAME));

        People people = new People(UUID.fromString(uuidString));
        people.setName(name);

        return people;
    }
}
