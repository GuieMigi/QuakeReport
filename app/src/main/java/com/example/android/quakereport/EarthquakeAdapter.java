package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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
        // Format the magnitude to show 1 decimal place
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        String formattedMagnitude = magnitudeFormat.format(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in the TextView.
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

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
        locationOffsetTextView.setAllCaps(false);

        // Find the TextView with view ID primary_location_textview.
        TextView primaryLocationTextView = listItemView.findViewById(R.id.primary_location_textview);
        // Display the location of the current earthquake in that TextView
        primaryLocationTextView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Find the TextView with view ID date_textview.
        TextView dateTextView = listItemView.findViewById(R.id.date_textview);
        SimpleDateFormat dateFormater = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String formattedDate = dateFormater.format(dateObject);
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time_textview.
        TextView timeTextView = listItemView.findViewById(R.id.time_textview);
        SimpleDateFormat timeFormater = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormater.format(dateObject);
        timeTextView.setText(formattedTime);
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}