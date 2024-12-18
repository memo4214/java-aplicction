package com.example.bmpsvu;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
public class MyDbClass extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyDbClass";
    private static final int DB_VERSION = 1;
    public MyDbClass(Context context) {
        super(context,DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String mndob = "CREATE TABLE mndob(id INTEGER PRIMARY KEY AUTOINCREMENT,phone TEXT,name TEXT,photo BLOB,region TEXT,RegisterDate TEXT)";
        sqLiteDatabase.execSQL(mndob);
        String sales = "CREATE TABLE sales(id INTEGER PRIMARY KEY AUTOINCREMENT,north Real,south REAL,west REAL,east REAL,lebanon REAL,year TEXT,month TEXT,mndobID INTEGER,FOREIGN KEY (mndobID) REFERENCES mndob(id)  ON DELETE CASCADE)";
        sqLiteDatabase.execSQL(sales);
        String commissions = "CREATE TABLE commissions(id INTEGER PRIMARY KEY AUTOINCREMENT,north Real,south REAL,west REAL,east REAL,lebanon REAL,year TEXT,month TEXT,mndobID INTEGER,FOREIGN KEY (mndobID) REFERENCES mndob(id)  ON DELETE CASCADE)";
        sqLiteDatabase.execSQL(commissions);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mndob");
        db.execSQL("DROP TABLE IF EXISTS sales");
        db.execSQL("DROP TABLE IF EXISTS commissions");
    }

    public void InsertCommissions(double north, double south, double west, double east, double lebanon, String year, String month, int mndobID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("mndobID", mndobID);
        contentValues.put("north", north);
        contentValues.put("west", west);
        contentValues.put("east", east);
        contentValues.put("lebanon", lebanon);
        contentValues.put("south", south);
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        sqliteDatabase.insert("commissions", null, contentValues);
        sqliteDatabase.close();
    }

    public void updateCommission(double north, double south, double west, double east, double lebanon, int mndobID){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("north", north);
            contentValues.put("west", west);
            contentValues.put("east", east);
            contentValues.put("lebanon", lebanon);
            contentValues.put("south", south);
            db.update("commissions", contentValues,"mndobID=?", new String[]{String.valueOf(mndobID)});
            db.close();
        }catch (Exception e){
            Log.e( "updateCommission: ",e.getMessage() );
        }
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON");
    }
    public ArrayList<Mndob> getMndobs() {
        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        String cmd ="SELECT * FROM mndob ORDER BY id DESC";
        Cursor cursor = sqliteDatabase.rawQuery(cmd, null);
        ArrayList<Mndob> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new Mndob(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getBlob(3), cursor.getString(4),cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public void newmndob(String phone, String name, byte[] photo, String region, String RegisterDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("region", region);
        contentValues.put("RegisterDate", RegisterDate);
        contentValues.put("phone", phone);
        contentValues.put("name", name);
        contentValues.put("photo", photo);
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        sqliteDatabase.insert("mndob", null, contentValues);
        sqliteDatabase.close();
    }

    public boolean editmndob(int id, String phone, String name, byte[] Image) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("photo", Image);
            values.put("phone", phone);
            db.update("mndob", values, "id=?", new String[]{String.valueOf(id)});
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean deletemndob(int id, String tableName){
        try{
                SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
                sqliteDatabase.delete(tableName, "id=?", new String[]{String.valueOf(id)});
                sqliteDatabase.close();
                return true;
        } catch(Exception e) {
            return false;
        }
    }


    public Mndob getSingleMndob(int id) {
        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        String cmd ="SELECT * FROM mndob WHERE id="+id+" ORDER BY id DESC";
        Cursor cursor = sqliteDatabase.rawQuery(cmd, null);
        Mndob mndob =new Mndob();
        if (cursor.moveToFirst()) {
            do {
                mndob =new Mndob(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getBlob(3), cursor.getString(4),cursor.getString(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mndob;
    }

    public ArrayList<Sale> MndobSales(int mndobID, String month, String year) {

        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        String cmd ="SELECT * FROM sales WHERE sales.mndobID="+mndobID+""+month+""+year+"";
        Cursor cursor = sqliteDatabase.rawQuery(cmd, null);
        ArrayList<Sale> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new Sale(cursor.getInt(0),cursor.getDouble(1), cursor.getDouble(2),cursor.getDouble(3), cursor.getDouble(4),cursor.getDouble(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Commission> mndobCommissions(int mndobID, String month, String year) {
        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        String cmd ="SELECT * FROM commissions WHERE mndobID="+mndobID+""+month+""+year+"";
        Cursor cursor = sqliteDatabase.rawQuery(cmd, null);
        ArrayList<Commission> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add(new Commission(cursor.getInt(0),cursor.getDouble(1), cursor.getDouble(2),cursor.getDouble(3), cursor.getDouble(4),cursor.getDouble(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isCommission(int mndobID, String month, String year) {
        SQLiteDatabase sqliteDatabase = this.getReadableDatabase();
        String cmd ="SELECT * FROM commissions " +
                "WHERE mndobID="+mndobID+" AND month='"+month+"' AND year='"+year+"'";
        Cursor cursor = sqliteDatabase.rawQuery(cmd, null);
        return cursor.getCount()>0? true: false;
    }
    public void saveSales(double north, double south, double west, double east, double lebanon, String year, String month, int mndobID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("mndobID", mndobID);
        contentValues.put("north", north);
        contentValues.put("west", west);
        contentValues.put("east", east);
        contentValues.put("lebanon", lebanon);
        contentValues.put("south", south);

        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        sqliteDatabase.insert("sales", null, contentValues);
        sqliteDatabase.close();
    }







}
