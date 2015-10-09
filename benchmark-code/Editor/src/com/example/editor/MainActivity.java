package com.example.editor;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Instrumentation;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Thread(new Runnable() {         
	        @Override
	        public void run() {
	            try {
	            Instrumentation inst = new Instrumentation();
	            for ( int i = 0; i < 5; ++i ) {
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_A);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_B);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_C);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_D);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_E);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_F);
	                Thread.sleep(500);
	                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_G);
	                Thread.sleep(500);
	            }
	            }
	            catch(InterruptedException e){
	            }
	        }   
	    }).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
