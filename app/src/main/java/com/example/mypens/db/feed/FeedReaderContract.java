package com.example.mypens.db.feed;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NRP = "nrp";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_ADDRESS = "address";
    }
}
