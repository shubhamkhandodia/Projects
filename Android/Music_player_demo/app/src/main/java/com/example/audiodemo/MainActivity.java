package com.example.audiodemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {



    MediaPlayer mediaplayer;
    SeekBar volumecontrol;
    AudioManager audioManager;
    int maxvolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

         mediaplayer = MediaPlayer.create(this,R.raw.laugh);

        volumecontrol = findViewById(R.id.volumeseekbar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

         maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volumecontrol.setMax(maxvolume);


        volumecontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void play(View view)
    {
        mediaplayer.start();
    }

    public void pause(View view) { mediaplayer.pause(); }



}
