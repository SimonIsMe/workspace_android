package com.example.lista5;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewPerson extends Activity {

	private TextView name;
	private TextView surname;
	private DatePicker date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_person);
		
		this.name = (TextView) this.findViewById(R.id.name);
		this.surname = (TextView) this.findViewById(R.id.surname);
		this.date = (DatePicker) this.findViewById(R.id.date);
	}
	
	public void save(View view) {
		String text = null;
		if (this.name.getText().toString().equals("")) {
			text = "The name is required";
		}
		if (text == null && this.surname.getText().toString().equals("")) {
			text = "The surname is required";
		}
		
		if (text == null){

			text = "save";
		}
		
		Log.i("moje", text);
		
		Toast toast = Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_new_person, menu);
		return true;
	}

}