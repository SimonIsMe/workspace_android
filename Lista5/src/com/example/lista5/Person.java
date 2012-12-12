package com.example.lista5;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class Person extends Activity implements OnTouchListener {

	public static final int MAX_NOTES = 4;
	public static int noteId;
	private TextView[] notes;
	private int[] ids;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		
		this.noteId = 0;
		
		this.ids = new int[MAX_NOTES];
		notes = new TextView[MAX_NOTES];
		notes[0] = (TextView) this.findViewById(R.id.note0);
		notes[1] = (TextView) this.findViewById(R.id.note1);
		notes[2] = (TextView) this.findViewById(R.id.note2);
		notes[3] = (TextView) this.findViewById(R.id.note3);
		
		for(int i = 0; i < MAX_NOTES; i++) {
			notes[i].setOnTouchListener(this);
		}
			
	}
	
	protected void onResume() {
		super.onResume();

		for (int i = 1; i < MAX_NOTES; i++) {
			notes[i].setVisibility(View.GONE);
		}

		TextView fullName = (TextView) this.findViewById(R.id.fullName);
		fullName.setText(PeopleList.fullName);
		
		SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
		Cursor cursor = db.query("notes", null, "pid=" + PeopleList.userId, null, null, null, null);

		notes[0].setText("(empty)");
		
		int i = 0;
		while (cursor.moveToNext()) {
			notes[i].setText(cursor.getString(1));
			notes[i].setVisibility(View.VISIBLE);
			this.ids[i] = Integer.parseInt(cursor.getString(0));
			i++;
		}
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent;
		switch (item.getItemId()) {
			 case R.id.addNote:
				 //	nowa notatka
				 myIntent = new Intent(this.getApplicationContext(), AddNewNote.class);
	             startActivityForResult(myIntent, 0);
				 break;
			 case R.id.delete:
				 //	usuwanie użtkownika
				 SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
				 db.delete("people", "pid=" + PeopleList.userId, null);
				 Toast toast = Toast.makeText(this.getApplicationContext(), "The person has been deleting", Toast.LENGTH_LONG);
				 toast.show();
	             finish();
				 break;
			 case R.id.edit:
				 //	edycja użytkownika
				 myIntent = new Intent(this.getApplicationContext(), AddNewPerson.class);
	             startActivityForResult(myIntent, 0);
				 break;
		}
		
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_person, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		for (int i = 0; i < MAX_NOTES; i++) {
			if (v.getId() == this.notes[i].getId()) {
				Person.noteId = this.ids[i];
				Log.i("moje", Person.noteId + "");
				Intent myIntent = new Intent(this.getApplicationContext(), AddNewNote.class);
	            startActivityForResult(myIntent, 0);
				break;
			}
		}
		return false;
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        PeopleList.userId = 0;
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
