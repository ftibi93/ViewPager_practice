package com.bignerdranch.android.viewpager_practice.database;

/**
 * Created by ftibi on 2017. 02. 25..
 */

public class PeopleDbSchema {
    public static final class PeopleTable {
        public static final String NAME = "people";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
        }
    }
}
