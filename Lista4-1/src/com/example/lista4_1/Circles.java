package com.example.lista4_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Circles extends Activity implements OnTouchListener {
	
	public static final String SHARED_PREFERENCES = "Circles12";

	private long touchTimeStart;
	private Draw2d d;
	private int number = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circles);
        
        this.d = new Draw2d(this);
        this.d.setOnTouchListener(this);
        
        this.setContentView(d);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.circles, menu);
        return true;
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				this.number = d.addPoint(event.getX(), event.getY());
				this.touchTimeStart = event.getEventTime();
				break;
			case MotionEvent.ACTION_UP:
				d.setEnd(event.getEventTime() - this.touchTimeStart, this.number, this);
				Log.i("moje", (event.getEventTime() - this.touchTimeStart) + "");
				break;
			case MotionEvent.ACTION_MOVE:
				d.movePoint(this.number, event.getX(), event.getY());
				break;
		}
		Log.i("moje", event.getX() + " " + event.getY() + " " + event.getEventTime() + " -");
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent = 
				new Intent(getApplicationContext(), Read.class);
			startActivityForResult(myIntent, 0);
			finish();
		return true;
	}
	
}
