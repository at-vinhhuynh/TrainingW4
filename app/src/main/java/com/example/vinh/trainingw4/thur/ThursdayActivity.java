package com.example.vinh.trainingw4.thur;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.vinh.trainingw4.R;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/27/2017.
 */

public class ThursdayActivity extends AppCompatActivity {
    private MyBroadcastReceiver mMyBroadcastReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday);
        mMyBroadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mMyBroadcastReceiver, screenStateFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadcastReceiver);
    }
}
