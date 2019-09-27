package com.halifaxbankbranches.models.halifax.data;

public class OpeningHours {
    private String ClosingTime;

    private String OpeningTime;

    public String getClosingTime () {
        return ClosingTime;
    }

    public void setClosingTime (String ClosingTime) {
        this.ClosingTime = ClosingTime;
    }

    public String getOpeningTime () {
        return OpeningTime;
    }

    public void setOpeningTime (String OpeningTime) {
        this.OpeningTime = OpeningTime;
    }
}
