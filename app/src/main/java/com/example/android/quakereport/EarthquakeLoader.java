package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{

    private String mUrl;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /**
     * Constructs a new {@link EarthquakeLoader}
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.e(LOG_TAG,"loadInBackground method called");
        if (mUrl == null) {
            return null;
        }
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
