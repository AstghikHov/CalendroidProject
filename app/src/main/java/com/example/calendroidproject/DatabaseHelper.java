package com.example.calendroidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "STARTTIME";
    public static final String COL_5 = "ENDTIME";
    public static final String COL_6 = "LOCATION";
    public static final String COL_7 = "PHONENUMBER";
    public static final String COL_8 = "EMAIL";
    public static final String COL_9 = "NOTES";
    public static final String COL_10 = "COLOR";
    public static final String COL_11 = "TAG";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DATE TEXT,STARTTIME TEXT, ENDTIME TEXT, LOCATION TEXT, PHONENUMBER INTEGER, EMAIL TEXT, NOTES TEXT, COLOR TEXT, TAG TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String date1, String starttime, String endtime, String location,
                              String phonenum, String email, String notes, String color, String tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,date1);
        contentValues.put(COL_4,starttime);
        contentValues.put(COL_5,endtime);
        contentValues.put(COL_6,location);
        contentValues.put(COL_7,phonenum);
        contentValues.put(COL_8,email);
        contentValues.put(COL_9,notes);
        contentValues.put(COL_10,color);
        contentValues.put(COL_11,tag);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getSomeData(String date1) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(TABLE_NAME, null,
                "DATE= ?", new String[] { "" + date1 }, null,
                null, null);
        if (cur != null) {
            cur.moveToFirst();
        }
        return cur;
    }
    public Cursor getDataByColor(String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(TABLE_NAME, null,
                "COLOR= ?", new String[] { "" + color }, null,
                null, null);
        if (cur != null) {
            cur.moveToFirst();
        }
        return cur;
    }
    public Cursor IDchecker(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.query(TABLE_NAME, null,
                "ID= ?", new String[] { "" + id }, null,
                null, null);
        if (cur != null) {
            cur.moveToFirst();
        }
        return cur;
    }

    public boolean updateData(String id, String name, String date1, String starttime, String endtime, String location,
                              String phonenum, String email, String notes, String color, String tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,date1);
        contentValues.put(COL_4,starttime);
        contentValues.put(COL_5,endtime);
        contentValues.put(COL_6,location);
        contentValues.put(COL_7,phonenum);
        contentValues.put(COL_8,email);
        contentValues.put(COL_9,notes);
        contentValues.put(COL_10,color);
        contentValues.put(COL_11,tag);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Name = ?",new String[] {name});
    }
}