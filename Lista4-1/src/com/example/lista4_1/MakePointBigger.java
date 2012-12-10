package com.example.lista4_1;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;


public class MakePointBigger extends Service {

	private Timer timer;
	private TT update;
	public int number;
	public Draw2d d;
	
	public MakePointBigger() {
		this.timer = new Timer();
	}
	
	public IBinder onBind(Intent intent) {
		return null;
	}

	public void start() {
		this.update = new TT();
		this.update.d = this.d;
		this.update.number = this.number;
		this.update.timeStart = System.currentTimeMillis();
		this.timer.scheduleAtFixedRate(this.update, 0, 1000);
		Log.i("moje", "start");
	}

	public void stop() {
		Log.i("moje", "stoppp");
		try {
			if (this.update != null) {
				Log.i("moje", this.update.toString());
				this.update.wait();
			} else {
				Log.i("moje", "update jest null'em");
			}
		} catch (Exception e) {
			Log.e("moje", e.toString());
		}
		this.timer.cancel();
		this.timer.purge();
	}
	
	
	
}


//public class MakePointBigger extends AsyncTask {
//
//	public int number;
//	public boolean stop;
//	public Draw2d d;
//
//	@Override
//	protected Object doInBackground(Object... params) {
//		int i = 1;
//		float radius = 0;
//		while (!this.stop) {
//			try {
////				radius = ((i * 40)) / 20;
//				radius = 100;
//				Log.i("moje", "radius: " + i + "|" + radius + " " + this.number);
//				d.makePointBigger(this.number, radius);
//				Thread.sleep(10);
//				i++;
//			} catch (Exception e) {
//				
//			}
//		}
//		return null;
//	}
//
//	
//}