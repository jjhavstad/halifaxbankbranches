package com.halifaxbankbranches.ui.converters;

import androidx.annotation.NonNull;

import com.halifaxbankbranches.models.halifax.data.Branch;
import com.halifaxbankbranches.models.halifax.data.ContactInfo;
import com.halifaxbankbranches.models.halifax.data.GeographicCoordinates;
import com.halifaxbankbranches.models.halifax.data.PostalAddress;
import com.halifaxbankbranches.ui.data.BranchModel;
import com.halifaxbankbranches.ui.data.ContactInfoModel;
import com.halifaxbankbranches.ui.data.GeoLocationModel;
import com.halifaxbankbranches.ui.data.GeographicCoordinatesModel;
import com.halifaxbankbranches.ui.data.PostalAddressModel;

import java.util.ArrayList;

/**
 * This class is intended to convert the data from the Halifax API into the data
 * used by the UI components.  The idea is to decouple the network layer from
 * the UI layer through this level of abstraction.
 */
public final class HalifaxBranchConverter {
    public static @NonNull ArrayList<BranchModel> fromHalifaxBranches(@NonNull ArrayList<Branch> branches) {
        ArrayList<BranchModel> result = new ArrayList<>();
        for (Branch branch : branches) {
            result.add(fromHalifaxBranch(branch));
        }
        return result;
    }

    private static @NonNull BranchModel fromHalifaxBranch(@NonNull Branch branch) {
        BranchModel branchModel = new BranchModel();
        branchModel.setName(branch.getName());
        branchModel.setContactInfoModel(fromHalifaxContactInfo(branch.getContactInfo()));
        branchModel.setPostalAddressModel(fromHalifaxPostalAddress(branch.getPostalAddress()));
        return branchModel;
    }

    private static ContactInfoModel[] fromHalifaxContactInfo(ContactInfo[] contactInfos) {
        ContactInfoModel[] contactInfoModels = null;
        if (contactInfos != null) {
            contactInfoModels = new ContactInfoModel[contactInfos.length];
            for (int i = 0; i < contactInfos.length; i++) {
                ContactInfo contactInfo = contactInfos[i];
                contactInfoModels[i] = new ContactInfoModel();
                contactInfoModels[i].setContactType(contactInfo.getContactType());
                contactInfoModels[i].setContactContent(contactInfo.getContactContent());
            }
        }
        return contactInfoModels;
    }

    private static @NonNull PostalAddressModel fromHalifaxPostalAddress(PostalAddress postalAddress) {
        PostalAddressModel postalAddressModel = new PostalAddressModel();
        if (postalAddress != null) {
            postalAddressModel.setAddressLine(postalAddress.getAddressLine());
            postalAddressModel.setCountry(postalAddress.getCountry());
            postalAddressModel.setTownName(postalAddress.getTownName());
            postalAddressModel.setPostCode(postalAddress.getPostCode());
            if (postalAddress.getGeoLocation() != null &&
                    postalAddress.getGeoLocation().getGeographicCoordinates() != null) {
                GeographicCoordinates geographicCoordinates =
                        postalAddress.getGeoLocation().getGeographicCoordinates();
                GeoLocationModel geoLocationModel = new GeoLocationModel();
                GeographicCoordinatesModel geographicCoordinatesModel =
                        new GeographicCoordinatesModel();
                geographicCoordinatesModel.setLatitude(geographicCoordinates.getLatitude());
                geographicCoordinatesModel.setLongitude(geographicCoordinates.getLongitude());
                geoLocationModel.setGeographicCoordinatesModel(geographicCoordinatesModel);
                postalAddressModel.setGeoLocationModel(geoLocationModel);
            }
        }
        return postalAddressModel;
    }
}
