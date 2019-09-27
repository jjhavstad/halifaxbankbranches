package com.halifaxbankbranches.models.halifax.data;

public class Branch {
    private Availability Availability;

    private PostalAddress PostalAddress;

    private String Type;

    private String[] CustomerSegment;

    private String SequenceNumber;

    private String Identification;

    private ContactInfo[] ContactInfo;

    private String Name;

    private OtherServiceAndFacility[] OtherServiceAndFacility;

    public Availability getAvailability () {
        return Availability;
    }

    public void setAvailability (Availability Availability) {
        this.Availability = Availability;
    }

    public PostalAddress getPostalAddress () {
        return PostalAddress;
    }

    public void setPostalAddress (PostalAddress PostalAddress) {
        this.PostalAddress = PostalAddress;
    }

    public String getType () {
        return Type;
    }

    public void setType (String Type) {
        this.Type = Type;
    }

    public String[] getCustomerSegment () {
        return CustomerSegment;
    }

    public void setCustomerSegment (String[] CustomerSegment) {
        this.CustomerSegment = CustomerSegment;
    }

    public String getSequenceNumber () {
        return SequenceNumber;
    }

    public void setSequenceNumber (String SequenceNumber) {
        this.SequenceNumber = SequenceNumber;
    }

    public String getIdentification () {
        return Identification;
    }

    public void setIdentification (String Identification) {
        this.Identification = Identification;
    }

    public ContactInfo[] getContactInfo () {
        return ContactInfo;
    }

    public void setContactInfo (ContactInfo[] ContactInfo) {
        this.ContactInfo = ContactInfo;
    }

    public String getName () {
        return Name;
    }

    public void setName (String Name) {
        this.Name = Name;
    }

    public OtherServiceAndFacility[] getOtherServiceAndFacility () {
        return OtherServiceAndFacility;
    }

    public void setOtherServiceAndFacility (OtherServiceAndFacility[] OtherServiceAndFacility) {
        this.OtherServiceAndFacility = OtherServiceAndFacility;
    }
}
