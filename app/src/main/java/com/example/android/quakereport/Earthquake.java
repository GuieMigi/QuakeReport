package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;

public class Earthquake extends AppCompatActivity {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mWebsite;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String website) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mWebsite = website;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getWebsite() {
        return mWebsite;
    }
}