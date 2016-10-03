// ViewContact.java
// Activity for viewing a single contact.
package com.example.contactapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewContact extends Activity {
	private long rowId;
	private ContactDataSource ds;
	private TextView nameTextView;
	private TextView phoneTextView;
	private Contact c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_contact);
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		phoneTextView = (TextView) findViewById(R.id.phoneTextView);
		// get the selected contact's row ID
		Bundle extras = getIntent().getExtras();
		rowId = extras.getLong(MainActivity.ROW_ID);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//loadContact();
		new LoadContactTask().execute((Object[]) null); //calls the async task below
	}

	private class LoadContactTask extends AsyncTask<Object, Object, Object>{
		@Override
		//the task to execute
		protected Object doInBackground(Object... params){
			ds = new ContactDataSource(ViewContact.this);
			ds.open();
			c = ds.getContact(rowId);
			return null;
		}

		//when the execution is complete do the following
		@Override
		protected void onPostExecute(Object result){
			nameTextView.setText(c.getName());
			phoneTextView.setText(c.telno);
			ds.close();
		}
	}

	private void loadContact() {
		ContactDataSource ds = new ContactDataSource(this);
		ds.open();
		c = ds.getContact(rowId);
		nameTextView.setText(c.getName());
		phoneTextView.setText(c.telno);
		ds.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view_contact_menu, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.editItem:
			Intent addEditContact = new Intent(this, AddEditContact.class);
			addEditContact.putExtra(MainActivity.ROW_ID, rowId);
			addEditContact.putExtra("name", nameTextView.getText());
			addEditContact.putExtra("phone", phoneTextView.getText());
			startActivity(addEditContact);
			finish();
			return true;
		case R.id.deleteItem:
			deleteContact();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void deleteContact() {
		AlertDialog.Builder builder = new AlertDialog.Builder(ViewContact.this);
		builder.setTitle(R.string.confirmTitle); 
		builder.setMessage(R.string.confirmMessage); 
		builder.setPositiveButton(R.string.button_delete,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int button) {
                        //create async task to delete the list item
                        AsyncTask<Contact, Object, Object> deleteTask = new AsyncTask<Contact,Object, Object>(){
                            @Override
                            protected Object doInBackground(Contact... params){
                                ds.open();
                                ds.deleteContact(params[0]);
                                return null;
                            }
                        }
						ContactDataSource ds = new ContactDataSource(ViewContact.this);
						ds.open();
						ds.deleteContact(c);
						ds.close();
						finish();
					} 
				} 
		); 
		builder.setNegativeButton(R.string.button_cancel, null);
		builder.show();
	}
}
