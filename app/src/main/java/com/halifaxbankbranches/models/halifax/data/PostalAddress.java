package com.halifaxbankbranches.models.halifax.data;

public class PostalAddress {
    private String[] CountrySubDivision;

    private String[] AddressLine;

    private String TownName;

    private String Country;

    private GeoLocation GeoLocation;

    private String PostCode;

    public String[] getCountrySubDivision () {
        return CountrySubDivision;
    }

    public void setCountrySubDivision (String[] CountrySubDivision) {
        this.CountrySubDivision = CountrySubDivision;
    }

    public String[] getAddressLine () {
        return AddressLine;
    }

    public void setAddressLine (String[] AddressLine) {
        this.AddressLine = AddressLine;
    }

    public String getTownName () {
        return TownName;
    }

    public void setTownName (String TownName) {
        this.TownName = TownName;
    }

    public String getCountry () {
        return Country;
    }

    public void setCountry (String Country) {
        this.Country = Country;
    }

    public GeoLocation getGeoLocation () {
        return GeoLocation;
    }

    public void setGeoLocation (GeoLocation GeoLocation) {
        this.GeoLocation = GeoLocation;
    }

    public String getPostCode () {
        return PostCode;
    }

    public void setPostCode (String PostCode) {
        this.PostCode = PostCode;
    }
}
