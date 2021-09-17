package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class database extends SQLiteOpenHelper {


    public database(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table students (name TEXT primary key, rollno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists students");
    }

    // CRUD -create read update delete
    // add edit delete list search

    //add
    public Boolean insertData(String name, String rollno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("rollno", rollno);
        long result = MyDB.insert("students", null, contentValues);

        //failure
        if (result == -1) return false;
        else
            return true;
    }

    //edit
    public Boolean updateData(String name, String rollno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollno", rollno);

        Cursor cursor = MyDB.rawQuery("select * from students where name=?", new String[]{name});

        if (cursor.getCount() > 0) {


            long result = MyDB.update("students", contentValues, "name=?", new String[]{name});

            //failure
            if (result == -1) return false;
            else {
                return true;
            }
        } else {
            return false;
        }
    }

    //delete
    public Boolean deleteData(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from students where name=?", new String[]{name});

        if (cursor.getCount() > 0) {


            long result = MyDB.delete("students", "name=?", new String[]{name});

            //failure
            if (result == -1) return false;
            else {
                return true;
            }
        } else {
            return false;
        }
    }

    //list
    public Cursor getData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from students", null);
        return cursor;
    }

    //search
    public Cursor searchData(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from students where name=?", new String[]{name});
        return cursor;
    }

}