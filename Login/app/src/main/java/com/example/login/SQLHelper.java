package com.example.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class SQLHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "DB.db";
    private static final String TABLE_NAME = "user";
    private static final int DB_VERSION = 1;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String query = "CREATE TABLE "+TABLE_NAME+"(" +
                    "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "USER TEXT, " +
                    "PASSWORD TEXT, " +
                    ");";
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("drop table if exists " + DB_NAME);
            onCreate(sqLiteDatabase);
        }
    }
    private void closeDB() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        if (contentValues != null)
            contentValues.clear();
        if (cursor != null)
            cursor.close();
    }
    public void insertUser(String user, int password) {
        try {
            sqLiteDatabase = getWritableDatabase();
            contentValues = new ContentValues();

            contentValues.put("USER", user);
            contentValues.put("PASSWORD", password);

            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        } finally {
            closeDB();
        }

    }
    public List<User> getAllProductAdvances(){
        List<User> users = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false,DB_NAME,null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            user = new User(cursor.getInt(cursor.getColumnIndex("ID")),cursor.getString(cursor.getColumnIndex("USER")),
                    cursor.getString(cursor.getColumnIndex("PASSWORD")));
            users.add(user);
        }
        return users;

    }
}
