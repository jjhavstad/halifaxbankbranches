package com.halifaxbankbranches.ui.data;

public class PostalAddressModel {
    private String[] addressLine;

    private String townName;

    private String country;

    private GeoLocationModel geoLocationModel;

    private String postCode;

    public String[] getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String[] addressLine) {
        this.addressLine = addressLine;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public GeoLocationModel getGeoLocationModel() {
        return geoLocationModel;
    }

    public void setGeoLocationModel(GeoLocationModel geoLocationModel) {
        this.geoLocationModel = geoLocationModel;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
