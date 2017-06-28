package com.example.vinh.trainingw4.thur;

/**
 * Copyright © 2016 AsianTech inc.
 * Created by VinhHlb on 6/28/2017.
 */
enum Action {
    INTENT("INTENT"), PAUSE("PAUSE"), START("START"),
    RESUME("RESUME"), SEEK("SEEK"),
    SEEK_TO("SEEK_TO"), STOP("STOP");

    private String mValue;

    Action(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }
}