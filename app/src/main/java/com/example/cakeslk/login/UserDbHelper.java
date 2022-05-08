package com.example.cakeslk.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeslk.database.Cakes_lk_Database;

public class UserDbHelper  extends Cakes_lk_Database {

    public UserDbHelper(Context context)
    {
        super(context);
    }

    public String GetUserByEmail(String email) {
        String password = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Password FROM " + AdminDB.USER_TABLE + " WHERE Email ='" + email + "' ", null);
        while (cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex(AdminDB.USER_PASSWORD));
        }
        cursor.close();
        return password;
    }



    public User getAdminData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + AdminDB.ADMIN_TABLE, null);
        User user = new User();

        while (cursor.moveToNext()) {
            user.setPosition(cursor.getString(cursor.getColumnIndex(AdminDB.POSITION)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(AdminDB.USER_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(AdminDB.USER_PASSWORD)));
        }
        return user;
    }

    public int getUserId(String email) {
        int id = -1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + AdminDB.USER_ID + " FROM " + AdminDB.USER_TABLE + " WHERE Email ='" + email + "' ", null);

        if (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(AdminDB.USER_ID));
        }

        cursor.close();
        return id;
    }

}

