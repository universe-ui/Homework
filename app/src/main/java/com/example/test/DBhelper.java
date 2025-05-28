package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "pass.db";
    public static final String TB_NAME = "tb_pass";
    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    public DBhelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TB_NAME+"(ACCOUNT TEXT PRIMARY KEY,PASSWORD TEXT)");
    }

    public void onUpgrade(SQLiteDatabase arg0,int arg1,int arg2){}
}
