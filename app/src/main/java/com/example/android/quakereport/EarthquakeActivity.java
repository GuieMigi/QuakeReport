/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {

    // Tag for the log messages.
    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    //JSON response link for an USGS query
    private static final String USGS_JSON_QUERY = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=50";
    // Constant value for the earthquake loader ID. Useful when we have more loaders.
    private static final int EARTHQUAKE_LOADER_ID = 1;
    // Adapter for the list of earthquakes.
    private EarthquakeAdapter mAdapter;
    // TextView that is displayed when the list is empty.
    TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout.
        ListView earthquakeListView = findViewById(R.id.list);
        emptyStateTextView = findViewById(R.id.empty_state_text_view);
        earthquakeListView.setEmptyView(emptyStateTextView);
        // Create a new adapter that takes an empty list of earthquakes as input.
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface.
        earthquakeListView.setAdapter(mAdapter);
        // Initialise the LoaderManager.
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, EarthquakeActivity.this);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Find the current earthquake that was clicked on.
                Earthquake currentEarthquake = mAdapter.getItem(position);
                // Create a new intent to view the earthquake URI.
                Intent openDetailsWesite = new Intent(Intent.ACTION_VIEW);
                openDetailsWesite.setData(Uri.parse(currentEarthquake.getWebsite()));
                startActivity(openDetailsWesite);
            }
        });
        /* Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_JSON_QUERY);
        */
    }

    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_JSON_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> earthquakes) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        // If there is a valid list of Earthquakes, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        } else emptyStateTextView.setText("No earthquakes found that match your query.");
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    /** AsyncTask to perform the network request on a background thread, and then update the UI with the earthquakes in the response.
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            // Perform the HTTP request for earthquake data and process the response.
            ArrayList<Earthquake> result = QueryUtils.getEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();
            // If there is a valid list of Earthquakes, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
     */
}