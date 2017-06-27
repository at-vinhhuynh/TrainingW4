package com.example.vinh.trainingw4.tue;

import android.util.Log;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
public class MyThread extends Thread {
    private static final String TAG = MyThread.class.getSimpleName();
    private int mCount;

    MyThread(int count) {
        mCount = count;
    }

    @Override
    public void run() {
        Log.d(TAG, "run: start");
        for (int i = 0; i < mCount; i++) {
            try {
                sleep(100);
                Log.d(TAG, "run: " + i);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
