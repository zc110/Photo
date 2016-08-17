package com.example.photo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	String creatt_table = "CREATE TABLE youxiang (usename  VARCHAR(20),password  VARCHAR(20))";

	public DatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(creatt_table);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
