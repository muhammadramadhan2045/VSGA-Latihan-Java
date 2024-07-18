package com.example.mypens.db.student;

import android.provider.BaseColumns;

public final class StudentReaderContract {

    private StudentReaderContract() {}

    /* Inner class that defines the table contents */
    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_ID= "NUMBER";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_BIRTH = "BIRTH";
        public static final String COLUMN_NAME_GENDER = "GENDER";
        public static final String COLUMN_NAME_ADDRESS = "ADDRESS";
    }
}
