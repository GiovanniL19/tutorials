package com.example.contactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "contacts.db";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase database;
	public static final String TABLE_CONTACTS = "contacts";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME1 = "name";
	public static final String COLUMN_NAME2 = "telno";
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_CONTACTS + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME1 + ","
			+ COLUMN_NAME2 + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		onCreate(db);
	}

	} 