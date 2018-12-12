package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }

    private static final String LOCATION_SEPARATOR = "of";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        // Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Find the textView with id magnitude
        TextView magView = listItemView.findViewById(R.id.mag);
        //Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magView.setText(formattedMagnitude);

        // Create a new Date Object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Find the TextView with view ID date
        TextView dateView = listItemView.findViewById(R.id.date);
        // Format the date string
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that textview
        dateView.setText(formattedDate);

        TextView timeView = listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        String OriginalLocation = currentEarthquake.getArea();
        String primaryLocation;
        String locationOffset;
        if (OriginalLocation.contains(LOCATION_SEPARATOR)) {
            String arr[] = OriginalLocation.split("of");
            locationOffset = arr[0] + LOCATION_SEPARATOR;
            primaryLocation = (arr[1]);
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = OriginalLocation;
        }
        TextView offset = listItemView.findViewById(R.id.location_offset);
        offset.setText(locationOffset);
        TextView primary = listItemView.findViewById(R.id.primary_location);
        primary.setText(primaryLocation);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradiantDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();
        // Get the appropriate background color based on the current Earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private  int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor)
        {
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
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
