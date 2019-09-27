package com.halifaxbankbranches.models.halifax.data;

public class GeographicCoordinates {
    private String Latitude;

    private String Longitude;

    public String getLatitude () {
        return Latitude;
    }

    public void setLatitude (String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude () {
        return Longitude;
    }

    public void setLongitude (String Longitude) {
        this.Longitude = Longitude;
    }
}
