package com.example.test;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PassManager {
    private DBhelper dBhelper;
    private String TBNAME;

    public PassManager(Context context){
        dBhelper = new DBhelper(context);
        TBNAME = DBhelper.TB_NAME;
    }

    public void add(PassItem item){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account",item.getAccount());
        values.put("password",item.getPassword());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public boolean isAccountExists(String account) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = null;
        boolean exists = false;

        try {
            // 直接查询主键，利用索引快速定位
            cursor = db.query(
                    TBNAME,
                    new String[]{"account"},  // 只查询account字段
                    "account = ?",
                    new String[]{account},
                    null, null, null,
                    "1"  // LIMIT 1：找到即停止
            );
            exists = cursor.moveToFirst();  // 若能移动到第一条记录，说明存在
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return exists;
    }

    public boolean verifyAccount(String account, String password) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = null;
        boolean isVerified = false;

        try {
            // 查询账号对应的明文密码
            cursor = db.query(
                    TBNAME,
                    new String[]{"password"},
                    "account = ?",
                    new String[]{account},
                    null, null, null,
                    "1"
            );

            if (cursor.moveToFirst()) {
                String storedPassword = cursor.getString(0);
                // 直接比对明文密码（不推荐生产环境）
                isVerified = password.equals(storedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return isVerified;
    }

}
