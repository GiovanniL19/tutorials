// AddEditContact.java
// Activity for adding a new entry to or  
// editing an existing entry in the address book.
package com.example.contactapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddEditContact extends Activity {
	private long rowID; 
	
	private EditText nameEditText;
	private EditText phoneEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);

		nameEditText = (EditText) findViewById(R.id.nameEditText);
		phoneEditText = (EditText) findViewById(R.id.phoneEditText);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			rowID = extras.getLong("row_id");
			nameEditText.setText(extras.getString("name"));
			phoneEditText.setText(extras.getString("phone"));
		}

		Button saveContactButton = (Button) findViewById(R.id.saveContactButton);
		saveContactButton.setOnClickListener(saveContactButtonClicked);
	}

	OnClickListener saveContactButtonClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (nameEditText.getText().length() != 0) {
				saveContact();
				// To do it on a separate thread
				/*
				 * AsyncTask<Object, Object, Object> saveContactTask = new
				 * AsyncTask<Object, Object, Object>() {
				 * 
				 * @Override protected Object doInBackground(Object... params) {
				 * saveContact(); // save contact to the database return null; }
				 * // end method doInBackground
				 * 
				 * @Override protected void onPostExecute(Object result) {
				 * finish(); // return to the previous Activity } // end method
				 * onPostExecute }; // end AsyncTask
				 * 
				 * // save the contact to the database using a separate thread
				 * saveContactTask.execute((Object[]) null);
				 */
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AddEditContact.this);
				builder.setTitle(R.string.errorTitle);
				builder.setMessage(R.string.errorMessage);
				builder.setPositiveButton(R.string.errorButton, null);
				builder.show();
			}
		}
	};

	// saves contact information to the database
	private void saveContact() {
		ContactDataSource ds = new ContactDataSource(this);
		ds.open();
		if (getIntent().getExtras() == null) {
			ds.createContact(nameEditText.getText().toString(), phoneEditText
					.getText().toString());
			finish();
		} else {
			ds.updateContact(rowID, nameEditText.getText().toString(),
					phoneEditText.getText().toString());
			finish();
		}
	}
}
