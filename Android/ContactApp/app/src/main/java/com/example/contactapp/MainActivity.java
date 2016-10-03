package com.example.contactapp;

import java.util.List;
import java.util.Objects;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	public static final String ROW_ID = "row_id";
	private ContactDataSource ds;
	List<Contact> values;
	public static ArrayAdapter<Contact> adapter;
	Contact c;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = getListView();
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				c = (Contact) parent.getItemAtPosition(position);
				Intent viewContact = new Intent(MainActivity.this,
						ViewContact.class);
				viewContact.putExtra(ROW_ID, c.getId());
				startActivity(viewContact);
			}
		});
	}

	private class GetContactTask extends AsyncTask<Object, Object, Object>{
		@Override
		protected Object doInBackground(Object... params){
			ds = new ContactDataSource(MainActivity.this);
			ds.open();
			values = ds.getAllContacts();
			return null;
		}

		@Override
		protected void onPostExecute(Object result){
			adapter = new ArrayAdapter<Contact>(MainActivity.this,android.R.layout.simple_list_item_1, values);
			setListAdapter(adapter);
			ds.close();
		}
	}

	@Override
	protected void onResume() {
		/*System.out.println("ONRESUME");
		ds = new ContactDataSource(this);
		ds.open();
		values = ds.getAllContacts();
		adapter = new ArrayAdapter<Contact>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		*/
		new GetContactTask().execute((Object[]) null);
		super.onResume();
	}

	@Override
	protected void onPause() {
		ds.close();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addressbook_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent addNewContact = new Intent(MainActivity.this,
				AddEditContact.class);
		startActivity(addNewContact);
		return super.onOptionsItemSelected(item);
	}
}