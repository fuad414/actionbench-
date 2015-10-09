package com.niparasc.papanikolis;

import com.niparasc.papanikolis.VibratorInterface;

import android.os.Vibrator;

public class VibratorManager implements VibratorInterface {

	private Vibrator vibrator;

	public VibratorManager(Vibrator vibrator) {
		this.vibrator = vibrator;
	}

	@Override
	public void vibrateOnCrash() {
		vibrator.vibrate(120);
	}

}
