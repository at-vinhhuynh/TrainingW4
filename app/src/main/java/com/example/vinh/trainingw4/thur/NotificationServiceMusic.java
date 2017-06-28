package com.example.vinh.trainingw4.thur;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.vinh.trainingw4.R;

import java.io.IOException;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/28/2017.
 */
public class NotificationServiceMusic extends Service {
    private static final String TAG = NotificationServiceMusic.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private String mUrl;
    private int mLength;
    private CountDownTimer mCountDownTimer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getStringExtra("url") != null) {
            mUrl = intent.getStringExtra("url");
        }
        Log.d(TAG, "onStartCommand: " + mUrl);
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(Action.PAUSE.getValue())) {
                mMediaPlayer.pause();
                mLength = mMediaPlayer.getCurrentPosition();
            } else if (intent.getAction().equals(Action.START.getValue())) {
                mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.setDataSource(mUrl);
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        showNotification();
                        mMediaPlayer.start();
                    }
                });
                final Intent timeIntent = new Intent(Action.SEEK.getValue());
                mCountDownTimer = new CountDownTimer(mMediaPlayer.getDuration(), 1000) {
                    @Override
                    public void onTick(long l) {
                        timeIntent.putExtra("time", mMediaPlayer.getDuration() + "");
                        timeIntent.putExtra("second", mMediaPlayer.getCurrentPosition() + "");
                        sendBroadcast(timeIntent);
                    }

                    @Override
                    public void onFinish() {
                    }
                };
                mCountDownTimer.start();
            } else if (intent.getAction().equals(Action.RESUME.getValue())) {
                mMediaPlayer.seekTo(mLength);
                mMediaPlayer.start();
            } else if (intent.getAction().equals(
                    Action.STOP.getValue())) {
                stopForeground(true);
                stopSelf();
            } else if (intent.getAction().equals(Action.SEEK_TO.getValue())) {
                int time = intent.getIntExtra("chooseTime", 0);
                mMediaPlayer.seekTo(time);
                mMediaPlayer.start();
            }
        }
        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, AudioActivity.class);

        notificationIntent.setAction(Action.INTENT.getValue());
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent playIntent = new Intent(this, NotificationServiceMusic.class);
        playIntent.setAction(Action.START.getValue());
        PendingIntent playPIntent = PendingIntent.getService(this, 0,
                playIntent, 0);
        Intent resumeIntent = new Intent(this, NotificationServiceMusic.class);
        resumeIntent.setAction(Action.RESUME.getValue());
        PendingIntent resumePIntent = PendingIntent.getService(this, 0,
                resumeIntent, 0);

        Intent pauseIntent = new Intent(this, NotificationServiceMusic.class);
        pauseIntent.setAction(Action.PAUSE.getValue());
        PendingIntent pausePIntent = PendingIntent.getService(this, 0,
                pauseIntent, 0);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_notification);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(" Music Player")
                .setContentText("Song name....")
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(Bitmap.createScaledBitmap(bm, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous, "Play",
                        playPIntent)
                .addAction(android.R.drawable.ic_media_play, "Resume",
                        resumePIntent)
                .addAction(android.R.drawable.ic_media_next, "Pause",
                        pausePIntent).build();
        startForeground(111,
                notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
