package com.example.lista5;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewPerson extends Activity {

	private TextView name;
	private TextView surname;
	private DatePicker date;
	private int userId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_person);
		
		this.name = (TextView) this.findViewById(R.id.name);
		this.surname = (TextView) this.findViewById(R.id.surname);
		this.date = (DatePicker) this.findViewById(R.id.date);
		
		Log.i("moje", PeopleList.userId + "");
		
		if (PeopleList.userId > 0) {
			SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
			Cursor c = db.query("people", null, "pid=" + PeopleList.userId, null, null, null, null);
			c.moveToFirst();
			this.name.setText(c.getString(1));
			this.surname.setText(c.getString(2));
			
			String[] date = c.getString(3).split("-");
			this.date.updateDate(
					Integer.parseInt(date[0]), 
					Integer.parseInt(date[1]) - 1, 
					Integer.parseInt(date[2])
				);
			
			db.close();
		}
		
	}
	
	public void save(View view) {
		boolean isError = false;
		String text = null;
		if (this.name.getText().toString().equals("")) {
			text = "The name is required";
			isError = true;
		}
		if (text == null && this.surname.getText().toString().equals("")) {
			text = "The surname is required";
			isError = true;
		}
		
		if (text == null){
			SQLiteDatabase db = openOrCreateDatabase("baza" , SQLiteDatabase.CREATE_IF_NECESSARY, null);
			ContentValues values = new ContentValues();
			values.put("name", this.name.getText().toString());
			values.put("surname", this.surname.getText().toString());
			values.put("birth", this.date.getYear() + "-" + (this.date.getMonth() + 1) + "-" + this.date.getDayOfMonth());
			
			if (PeopleList.userId > 0) {
				db.update("people", values, "pid=" + PeopleList.userId, null);	
				PeopleList.fullName = this.name.getText().toString() + " "  + this.surname.getText().toString();
			} else {
				db.insert("people", null, values);				
			}
			db.close();
			text = "Saved";
		}
		
		Toast toast = Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG);
		toast.show();
		
		if (!isError) {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_new_person, menu);
		return true;
	}
	

}
