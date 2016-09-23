package com.qianfeng.day1_ormlite.bean.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xray on 16/9/22.
 */
public class MySqlHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table student_tb(id integer primary key autoincrement,name text,age integer,score float)";
    public static final String DROP_TABLE = "drop table student_tb";
    public static final int VERSION = 1;
    public static final String DB_NAME = "student_tb";

    public MySqlHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
