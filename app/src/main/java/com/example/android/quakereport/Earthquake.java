package com.example.android.quakereport;

public class Earthquake {

    // Magnitude of the earthquake.
    private double mMagnitude;
    // Location of the earthquake.
    private String mLocation;
    // Time of the earthquake.
    private long mTimeInMilliseconds;
    // Website of the earthquake.
    private String mWebsite;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String website) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mWebsite = website;
    }

    // Returns the magnitude of the earthquake.
    public double getMagnitude() {
        return mMagnitude;
    }

    // Returns the location of the earthquake.
    public String getLocation() {
        return mLocation;
    }

    // Returns the time of the earthquake.
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    // Returns the website of the earthquake.
    public String getWebsite() {
        return mWebsite;
    }
}