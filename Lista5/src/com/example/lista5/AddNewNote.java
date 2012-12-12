package com.example.lista5;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewNote extends Activity {

	private TextView note;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_note);
		
		TextView fullName = (TextView) this.findViewById(R.id.fullName);
		fullName.setText(PeopleList.fullName);
		this.note = (TextView) this.findViewById(R.id.note);
		
		if (Person.noteId > 0) {
			SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
			Cursor c = db.query("notes", null, "nid=" + Person.noteId, null, null, null, null);
			c.moveToFirst();
			this.note.setText(c.getString(1));
			
			db.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_new_note, menu);
		
		menu.getItem(0).setVisible(true);
		
		return true;
	}
	
	public void save(View v) {
		boolean isError = false;
		String text = null;
		
		if (this.note.getText().toString().equals("")) {
			text = "The content is required.";
			isError = true;
		}
		
		if(text == null) {
			SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
			ContentValues values = new ContentValues();
			values.put("text", this.note.getText().toString());
			values.put("pid", PeopleList.userId);
			if (Person.noteId > 0) {
				db.update("notes", values, "nid=" + Person.noteId, null);
				Person.noteId = 0;
			} else {
				db.insert("notes", null, values);
			}
			db.close();
			
			text = "Saved";
		}
		
		Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG).show();
		
		if (!isError) {
			finish();
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.delete:
				SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
				db.delete("notes", "nid=" + Person.noteId, null);
				
				Person.noteId = 0;
				
				Toast toast = Toast.makeText(this.getApplicationContext(), "Deleted", Toast.LENGTH_LONG);
				toast.show();
				
				this.finish();
				break;
		}
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        Person.noteId = 0;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
