package com.example.android.quakereport;

public class Earthquake {

    private double magnitude;
    private String area;
    private long timeInMilliseconds;

    private  String url;

    public Earthquake(double magnitude, String area, long timeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.area = area;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getArea() {
        return area;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
