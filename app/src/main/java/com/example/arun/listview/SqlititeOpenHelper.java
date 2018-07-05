package com.example.arun.listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlititeOpenHelper extends SQLiteOpenHelper {

    public static String table_name="expenses";

    public SqlititeOpenHelper(Context context) {
        super(context, table_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table " + table_name + "(id INTEGER PRIMARY KEY AUTOINCREMENT , expense TEXT , cost INTEGER, date TEXT, time TEXT )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
