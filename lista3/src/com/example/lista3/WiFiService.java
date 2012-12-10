package com.example.lista3;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;

public class WiFiService extends Service {

	/**Sprawdza status karty bezprzewodowej
	 * @param void
	 * @return void
	 * */
	public static void checkWiFiStatus() {
		switch (WiFi.wifiManager.getWifiState()) {
		case WifiManager.WIFI_STATE_DISABLED:
			WiFi.wifiTitle.setText("WiFi is off");
			enableMenu();
			break;
		case WifiManager.WIFI_STATE_DISABLING:
			WiFi.wifiTitle.setText("WiFi is turning off");
			disableMenu();
			break;
		case WifiManager.WIFI_STATE_ENABLED:
			WiFi.wifiTitle.setText("WiFi is on");
			enableMenu();
			break;
		case WifiManager.WIFI_STATE_ENABLING:
			WiFi.wifiTitle.setText("WiFi is turning on");
			disableMenu();
			break;
		default:
			break;
		}
	}

	static public void enableMenu() {
		if (WiFi.onOff != null) {
			WiFi.onOff.setEnabled(true);
		}
	}

	static public void disableMenu() {
		if (WiFi.onOff != null) {
			WiFi.onOff.setEnabled(false);
		}
	}

	public IBinder onBind(Intent arg0) {
		return null;
	}

}
