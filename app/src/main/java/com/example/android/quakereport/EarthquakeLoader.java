package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.nfc.Tag;

import java.net.URL;
import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    // Tag for log messages.
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    // Query URLs.
    private String[] mUrls;

    public EarthquakeLoader(Context context, String... urls) {
        super(context);
        mUrls = urls;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrls.length < 1 || mUrls[0] == null) {
            return null;
        }
        // Perform the HTTP request for earthquake data and process the response.
        ArrayList<Earthquake> earthquakes = QueryUtils.getEarthquakeData(mUrls[0]);
        return earthquakes;
    }
}
