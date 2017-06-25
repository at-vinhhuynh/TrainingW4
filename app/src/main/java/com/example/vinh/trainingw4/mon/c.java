package com.example.vinh.trainingw4.mon;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/25/2017.
 */
class c extends ViewPager {
    public c(Context context) {
        this(context, null);
    }

    public c(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new b());
    }
}
