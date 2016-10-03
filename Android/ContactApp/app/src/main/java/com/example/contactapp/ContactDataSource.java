package com.example.contactapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME1, MySQLiteHelper.COLUMN_NAME2 };

	public ContactDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() {
		try {
			database = dbHelper.getWritableDatabase();
		} catch (Exception e) {

		}
	}

	public void close() {
		dbHelper.close();
	}

	public Contact createContact(String name, String telno) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME1, name);
		values.put(MySQLiteHelper.COLUMN_NAME2, telno);
		long insertId = database.insert(MySQLiteHelper.TABLE_CONTACTS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTACTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Contact newContact = cursorToContact(cursor);
		cursor.close();
		long id = newContact.getId();
		System.out.println("Contact created with id: " + id);
		return newContact;
	}

	private Contact cursorToContact(Cursor cursor) {
		Contact contact = new Contact();
		contact.setId(cursor.getLong(0));
		contact.setName(cursor.getString(1));
		contact.setTelno(cursor.getString(2));
		return contact;
	}

	public Contact getContact(long rowId) {
		Cursor mCursor = null;
		try {
			mCursor = database.query(true, MySQLiteHelper.TABLE_CONTACTS,
					new String[] { MySQLiteHelper.COLUMN_ID,
							MySQLiteHelper.COLUMN_NAME1,
							MySQLiteHelper.COLUMN_NAME2 },
					MySQLiteHelper.COLUMN_ID + "=" + rowId, null, null, null,
					null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
			}
		} catch (Exception e) {
		}
		return cursorToContact(mCursor);
	}

	public boolean updateContact(long rowId, String name, String telno) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME1, name);
		values.put(MySQLiteHelper.COLUMN_NAME2, telno);
		return database.update(MySQLiteHelper.TABLE_CONTACTS, values,
				MySQLiteHelper.COLUMN_ID + "=" + rowId, null) > 0;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CONTACTS,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Contact contact = cursorToContact(cursor);
			contacts.add(contact);
			cursor.moveToNext();
		}
		cursor.close();
		return contacts;
	}

	public void deleteContact(Contact contact) {
		long id = contact.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_CONTACTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
}