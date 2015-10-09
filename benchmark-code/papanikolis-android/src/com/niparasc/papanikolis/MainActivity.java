package com.niparasc.papanikolis;

import android.app.Instrumentation;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.niparasc.papanikolis.Papanikolis;

public class MainActivity extends AndroidApplication {

	public static final String LOG = MainActivity.class.getSimpleName();

	private VibratorManager vibratorManager;
	private Papanikolis papanikolis;

	public Papanikolis getPapanikolis() {
		return papanikolis;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		vibratorManager = new VibratorManager(
				(Vibrator) getSystemService(Context.VIBRATOR_SERVICE));
		papanikolis = new Papanikolis(vibratorManager);

		initialize(papanikolis, cfg);

		Gdx.app.log(LOG, "== onCreate ==");
		
		new Thread(new Runnable() {         
	        @Override
	        public void run() {
	            try {
	            	Thread.sleep(2000);
	                Instrumentation inst = new Instrumentation();
	                for (int i = 0; i <= 10; i++){
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                	inst.sendKeySync(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DPAD_CENTER));
	                	Thread.sleep(500);
	                }
	            }catch(InterruptedException e){
	            }
	        }   
	    }).start();
	}

	public void onDestroy() {
		super.onDestroy();

		Gdx.app.log(LOG, "== onDestroy ==");
	}

	public void onPause() {
		super.onPause();
		Gdx.app.log(LOG, "== onPause ==");
	}

	public void onResume() {
		super.onResume();
		Gdx.app.log(LOG, "== onResume ==");
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}