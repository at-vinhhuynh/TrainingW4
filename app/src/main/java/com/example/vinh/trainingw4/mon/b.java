package com.example.vinh.trainingw4.mon;

import android.view.View;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
class b extends a {
    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }
}
