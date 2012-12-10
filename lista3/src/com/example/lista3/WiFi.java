package com.example.lista3;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WiFi extends Activity {

	private WiFiSampleReceiver myReceiver;

	static public MenuItem onOff;
	static public MenuItem refresh;
	static public TextView wifiTitle;
	static public WifiManager wifiManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi);

		WiFi.wifiTitle = (TextView) findViewById(R.id.wifi_title);

		this.myReceiver = new WiFiSampleReceiver();

		IntentFilter intentFilter = new IntentFilter();
		intentFilter
			.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		registerReceiver(this.myReceiver, intentFilter);

		this.wifiManager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);

		WiFiService.checkWiFiStatus();
	}

	/**Obsługa zdarzeń na naciśnięcia w menu
	 * @param MenuItem item
	 * @return boolean
	 * */
	public boolean onOptionsItemSelected(MenuItem item) {
		WifiManager wifiManager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);

		switch (item.getItemId()) {
		case R.id.menu_wifi_on_off:
			if (wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(false);
			} else {
				wifiManager.setWifiEnabled(true);
			}
			break;
		case R.id.menu_switch_on_bluetooth:
			View view = this.getCurrentFocus();
			Intent myIntent = new Intent(getApplicationContext(),
					Blutooth.class);
			startActivityForResult(myIntent, 0);
			finish();
			break;
		default:
			break;
		}
		return true;
	}

	/**Inicjacja zmiennych wskazujących na pozycje w menu
	 * @param Menu menu
	 * @return boolean
	 * */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.wifi, menu);
		this.onOff = menu.getItem(0);
		return true;
	}
}

class WiFiSampleReceiver extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {
		WiFiService.checkWiFiStatus();
	}
}
