package com.example.vinh.trainingw4.thur;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.vinh.trainingw4.R;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/28/2017.
 */
public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = AudioActivity.class.getSimpleName();
    private static final String URL_MP3 = "http://s1mp3.r9s70.vcdn.vn/4c3ebd35b7715e2f0760/5067047956779452462?key=F4G2CeQP1T13DsuHgp69GQ&expires=1498751084&filename=Nho-Oi-Ly-Hai.mp3";
    private int mLength = 0;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            processTime(intent);
        }
    };
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        initIntentFilter();

        // Init Listener
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        findViewById(R.id.btnResume).setOnClickListener(this);
        findViewById(R.id.btnPause).setOnClickListener(this);
    }

    private void initIntentFilter() {
        IntentFilter filter = new IntentFilter(Action.SEEK.getValue());
        registerReceiver(mBroadcastReceiver, filter);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intent playIntent = new Intent(AudioActivity.this, NotificationServiceMusic.class);
                playIntent.putExtra("chooseTime", seekBar.getProgress());
                playIntent.setAction(Action.SEEK_TO.getValue());
                startService(playIntent);
            }
        });
    }

    private void processTime(Intent intent) {
        if (mLength == 0) {
            mLength = Integer.parseInt(intent.getStringExtra("time"));
            mSeekBar.setMax(mLength);
            mSeekBar.setProgress(0);
            return;
        }
        int position = Integer.parseInt(intent.getStringExtra("second"));
        Log.d(TAG, "processTime: " + position);
        mSeekBar.setProgress(position);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnResume:
                Intent playIntent = new Intent(this, NotificationServiceMusic.class);
                playIntent.setAction(Action.RESUME.getValue());
                startService(playIntent);
                break;
            case R.id.btnStart:
                Intent startIntent = new Intent(this, NotificationServiceMusic.class);
                startIntent.setAction(Action.START.getValue());
                startIntent.putExtra("url", URL_MP3);
                startService(startIntent);
                break;
            case R.id.btnStop:
                Intent stopIntent = new Intent(this, NotificationServiceMusic.class);
                stopIntent.setAction(Action.STOP.getValue());
                startService(stopIntent);
                break;
            case R.id.btnPause:
                Intent pauseIntent = new Intent(this, NotificationServiceMusic.class);
                pauseIntent.setAction(Action.PAUSE.getValue());
                startService(pauseIntent);
                break;
        }
    }
}
