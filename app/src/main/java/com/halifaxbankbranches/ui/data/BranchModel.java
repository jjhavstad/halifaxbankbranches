package com.halifaxbankbranches.ui.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BranchModel implements Parcelable {
    private String name;
    private PostalAddressModel postalAddressModel;
    private ContactInfoModel[] contactInfoModel;
    private boolean expanded;

    public BranchModel() {
    }

    protected BranchModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<BranchModel> CREATOR = new Creator<BranchModel>() {
        @Override
        public BranchModel createFromParcel(Parcel in) {
            return new BranchModel(in);
        }

        @Override
        public BranchModel[] newArray(int size) {
            return new BranchModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostalAddressModel getPostalAddressModel() {
        return postalAddressModel;
    }

    public void setPostalAddressModel(PostalAddressModel postalAddressModel) {
        this.postalAddressModel = postalAddressModel;
    }

    public ContactInfoModel[] getContactInfoModel() {
        return contactInfoModel;
    }

    public void setContactInfoModel(ContactInfoModel[] contactInfoModel) {
        this.contactInfoModel = contactInfoModel;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
