package com.example.videoplayer;

import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnCompletionListener, OnPreparedListener {
    // Video source file
    private static final String fileName = "/data/movie.mp4";

    private VideoView videoPlayer;
    private static final String TAG="VIDEO";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);

             // Assign a VideoView object to the video player and set its properties.  It
             // will be started by the onPrepared(MediaPlayer vp) callback below when the
             // file is ready to play.

             videoPlayer = (VideoView) findViewById(R.id.videoview);   
             videoPlayer.setOnPreparedListener(this);
             videoPlayer.setOnCompletionListener(this);
             videoPlayer.setKeepScreenOn(true); 

             // Find and log the root of the external storage file system.  We assume file system is
             // mounted and writable (see the project WriteSDCard for ways to check this). This is
             // to display information only, since we will find the full path to the external files
             // directory below using getExternalFilesDir(null).

             
             videoPlayer.setVideoPath(fileName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
             // Inflate the menu; this adds items to the action bar if it is present.
             getMenuInflater().inflate(R.menu.main, menu);
             return true; 
    }

    /** This callback will be invoked when the file is ready to play */
    @Override
    public void onPrepared(MediaPlayer vp) {

             // Don't start until ready to play.  The arg of seekTo(arg) is the start point in
             // milliseconds from the beginning. Normally we would start at the beginning but,
             // for purposes of illustration, in this example we start playing 1/5 of
             // the way through the video if the player can do forward seeks on the video.

             if(videoPlayer.canSeekForward()) videoPlayer.seekTo(videoPlayer.getDuration()/5);
             videoPlayer.start();
    }

    /** This callback will be invoked when the file is finished playing */
    @Override
    public void onCompletion(MediaPlayer  mp) {
             // Statements to be executed when the video finishes.
             this.finish();	
    }

    /**  Use screen touches to toggle the video between playing and paused. */
    @Override
    public boolean onTouchEvent (MotionEvent ev){	
             if(ev.getAction() == MotionEvent.ACTION_DOWN){
                      if(videoPlayer.isPlaying()){
                            videoPlayer.pause();
                      } else {
                            videoPlayer.start();
                      }
                      return true;		
             } else {
                      return false;
             }
    }
}

