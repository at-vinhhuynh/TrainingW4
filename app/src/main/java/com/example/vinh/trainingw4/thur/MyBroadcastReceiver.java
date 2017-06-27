package com.example.vinh.trainingw4.thur;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/27/2017.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = MyBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(TAG, "ACTION_SCREEN_OFF");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d(TAG, "ACTION_SCREEN_ON");
        }
    }
}
