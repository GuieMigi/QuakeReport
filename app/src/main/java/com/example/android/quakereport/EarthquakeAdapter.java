package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes.
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView with view ID magnitude_textview.
        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude_textview);
        // Display the magnitude of the current earthquake in that TextView.
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        String location = currentEarthquake.getLocation();

        String primaryLocation;
        String locationOffset;

        // Check if the location contains the word "of".
        if (location.contains("of")) {
            //Split the current location into two Strings.
            String[] locationParts = location.split("(?<=of)");
            locationOffset = locationParts[0];
            primaryLocation = locationParts[1].trim();
        } else {
            locationOffset = "Near the";
            primaryLocation = location;
        }
        // Find the TextView with view ID location_offset_textview.
        TextView locationOffsetTextView = listItemView.findViewById(R.id.location_offset_textview);
        // Display the location of the current earthquake in that TextView.
        locationOffsetTextView.setText(locationOffset);

        // Find the TextView with view ID primary_location_textview.
        TextView primaryLocationTextView = listItemView.findViewById(R.id.primary_location_textview);
        // Display the location of the current earthquake in that TextView
        primaryLocationTextView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Find the TextView with view ID date_textview.
        TextView dateTextView = listItemView.findViewById(R.id.date_textview);
        SimpleDateFormat dateFormater = new SimpleDateFormat("EEEE, DD MMMM yyyy");
        String formattedDate = dateFormater.format(dateObject);
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time_textview.
        TextView timeTextView = listItemView.findViewById(R.id.time_textview);
        SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormater.format(dateObject);
        timeTextView.setText(formattedTime);
        return listItemView;
    }
}