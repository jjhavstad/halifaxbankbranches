package com.halifaxbankbranches.models.halifax.data;

public class Brand {
    private String BrandName;

    private Branch[] Branch;

    public String getBrandName () {
        return BrandName;
    }

    public void setBrandName (String BrandName) {
        this.BrandName = BrandName;
    }

    public Branch[] getBranch () {
        return Branch;
    }

    public void setBranch (Branch[] Branch) {
        this.Branch = Branch;
    }
}
