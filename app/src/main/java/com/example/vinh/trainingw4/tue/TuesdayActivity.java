package com.example.vinh.trainingw4.tue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vinh.trainingw4.R;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
public class TuesdayActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private MyThread mMyThread;
    private Handler mMyHandler = new Handler();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mProgressBar.setProgress(msg.arg1);
            mTvProgress.setText(msg.arg1 + " %");
        }
    };
    private TextView mTvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);
        mTvProgress = (TextView) findViewById(R.id.tvProgress);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            startProgress();
                testCustomThread();
            }
        });

        findViewById(R.id.btnDestroy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMyThread.interrupt();
            }
        });
    }

    private void startProgress() {
        mProgressBar.setProgress(0);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    SystemClock.sleep(100);
                    Message msg = mHandler.obtainMessage();
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }

    private void testCustomThread() {
        mMyThread = new MyThread(100);
        mMyThread.start();
    }
}
