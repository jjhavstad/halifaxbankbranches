package com.halifaxbankbranches.models.halifax.data;

public class Day {
    private String Name;

    private OpeningHours[] OpeningHours;

    public String getName () {
        return Name;
    }

    public void setName (String Name) {
        this.Name = Name;
    }

    public OpeningHours[] getOpeningHours () {
        return OpeningHours;
    }

    public void setOpeningHours (OpeningHours[] OpeningHours) {
        this.OpeningHours = OpeningHours;
    }
}
