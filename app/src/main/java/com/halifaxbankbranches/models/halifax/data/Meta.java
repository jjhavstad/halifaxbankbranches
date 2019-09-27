package com.halifaxbankbranches.models.halifax.data;

public class Meta {
    private String Agreement;

    private String TermsOfUse;

    private String License;

    private String TotalResults;

    private String LastUpdated;

    public String getAgreement () {
        return Agreement;
    }

    public void setAgreement (String Agreement) {
        this.Agreement = Agreement;
    }

    public String getTermsOfUse () {
        return TermsOfUse;
    }

    public void setTermsOfUse (String TermsOfUse) {
        this.TermsOfUse = TermsOfUse;
    }

    public String getLicense () {
        return License;
    }

    public void setLicense (String License) {
        this.License = License;
    }

    public String getTotalResults () {
        return TotalResults;
    }

    public void setTotalResults (String TotalResults) {
        this.TotalResults = TotalResults;
    }

    public String getLastUpdated () {
        return LastUpdated;
    }

    public void setLastUpdated (String LastUpdated) {
        this.LastUpdated = LastUpdated;
    }
}
