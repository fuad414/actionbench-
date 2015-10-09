package com.example.musicservice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

public class Service_PlaySong extends Service {
	MediaPlayer mp = null;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/**
	 * Initialize the media player
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		play();
		return(START_NOT_STICKY);
	}

	@Override
	public void onDestroy() {
		stopSelf();
	}


	public void play(){
		if(mp==null || !mp.isPlaying()){
			String songName = "Beethoven Piano Sonata No.2";
			// assign the song name to songName
			PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0,
					new Intent(getApplicationContext(), MainActivity.class),
					PendingIntent.FLAG_UPDATE_CURRENT);
			Notification notification = new Notification();
			notification.tickerText = "Music";
			notification.icon = R.drawable.ic_launcher;
			notification.flags |= Notification.FLAG_ONGOING_EVENT;
			notification.setLatestEventInfo(getApplicationContext(), "MusicPlayerSample",
					"Playing: " + songName, pi);
			startForeground(1337, notification);

			mp = MediaPlayer.create(this, R.raw.beethoven);
			mp.setOnCompletionListener(new OnCompletionListener(){
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.stop();
					stopForeground(true);
				}
			});
			mp.start();
		}

	}



}