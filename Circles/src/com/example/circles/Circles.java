package com.example.circles;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Circles extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circles);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.circles, menu);
        return true;
    }
}
