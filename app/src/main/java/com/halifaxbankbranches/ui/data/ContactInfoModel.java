package com.halifaxbankbranches.ui.data;

public class ContactInfoModel {
    private String ContactType;

    private String ContactContent;

    public String getContactType () {
        return ContactType;
    }

    public void setContactType (String ContactType) {
        this.ContactType = ContactType;
    }

    public String getContactContent () {
        return ContactContent;
    }

    public void setContactContent (String ContactContent) {
        this.ContactContent = ContactContent;
    }
}
