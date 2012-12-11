package com.example.lista4_2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private Draw2d d;
	public static String SHARED_PREFERENCES = "Circles12";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_WORLD_WRITEABLE);
		int size = preferences.getInt("size", -1);
		 
		Draw2d d = new Draw2d(this);
		d.isRead = true;
		
		Log.i("moje", "size " + size);
        
		for (int i = 0; i <= size; i++) {
			Log.i("moje", preferences.getInt("x_" + i, -10) + "x" + preferences.getInt("y_" + i, -10) + " " + preferences.getFloat("radius_" + i, 0));
			d.points_x.add(preferences.getInt("x_" + i, -10));
			d.points_y.add(preferences.getInt("y_" + i, -10));
			d.color.add(preferences.getInt("color_" + i, 1));
			d.radius.add(preferences.getFloat("radius_" + i, 0));
		}
		
		this.setContentView(d);
    }
    
    public void onDestroy() {
    	Log.i("moje", "--- THE END ---");
    	SharedPreferences preferences = this.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_WORLD_WRITEABLE);
    	Editor edit = preferences.edit();
    	edit.clear();
    	edit.commit();
    	super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
