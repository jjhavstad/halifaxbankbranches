package com.halifaxbankbranches.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.halifaxbankbranches.ui.data.BranchModel;
import com.halifaxbankbranches.ui.data.ContactInfoModel;

/**
 * This is the ViewModel implemented by the individual branch list items.
 */
public class BranchViewModel extends ViewModel {
    private MutableLiveData<Boolean> expanded;
    private MutableLiveData<String> name;
    private MutableLiveData<Boolean> showPhoneNumber;
    private MutableLiveData<String> addressLine1;
    private MutableLiveData<String> addressLine2;
    private MutableLiveData<Boolean> showAddresLine2;
    private MutableLiveData<String> townCountryAndZipCode;
    private MutableLiveData<String> phoneNumber;

    private BranchModel branchModel;

    public BranchViewModel() {
        this.expanded = new MutableLiveData<>();
        this.expanded.setValue(false);
        this.name = new MutableLiveData<>();
        this.addressLine1 = new MutableLiveData<>();
        this.addressLine2 = new MutableLiveData<>();
        this.showAddresLine2 = new MutableLiveData<>();
        this.phoneNumber = new MutableLiveData<>();
        this.showPhoneNumber = new MutableLiveData<>();
        this.townCountryAndZipCode = new MutableLiveData<>();
    }

    public LiveData<Boolean> isExpanded() {
        return expanded;
    }

    public boolean isExpandedRaw() {
        if (expanded != null && expanded.getValue() != null) {
            return expanded.getValue();
        }
        return false;
    }

    public void setExpanded(boolean expanded) {
        this.branchModel.setExpanded(expanded);
        this.expanded.postValue(expanded);
        if (phoneNumber.getValue() == null || phoneNumber.getValue().isEmpty() || !expanded) {
            showPhoneNumber.setValue(false);
        } else {
            showPhoneNumber.setValue(true);
        }
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<Boolean> getShowPhoneNumber() {
        return showPhoneNumber;
    }

    public LiveData<String> getAddressLine1() {
        return addressLine1;
    }

    public LiveData<String> getAddressLine2() {
        return addressLine2;
    }

    public LiveData<Boolean> getShowAddresLine2() {
        return showAddresLine2;
    }

    public LiveData<String> getTownCountryAndZipCode() {
        return townCountryAndZipCode;
    }

    public LiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public BranchModel getBranchModel() {
        return branchModel;
    }

    public void setBranchModel(@NonNull BranchModel branchModel) {
        this.branchModel = branchModel;

        this.expanded.setValue(branchModel.isExpanded());
        this.name.setValue(branchModel.getName());
        if (branchModel.getPostalAddressModel() != null &&
                branchModel.getPostalAddressModel().getAddressLine() != null &&
                branchModel.getPostalAddressModel().getAddressLine().length >= 1) {
            this.addressLine1.setValue(branchModel.getPostalAddressModel().getAddressLine()[0]);
        }
        if (branchModel.getPostalAddressModel() != null &&
                branchModel.getPostalAddressModel().getAddressLine() != null &&
                branchModel.getPostalAddressModel().getAddressLine().length >= 2) {
            this.addressLine2.setValue(branchModel.getPostalAddressModel().getAddressLine()[1]);
            this.showAddresLine2.setValue(true);
        } else {
            this.showAddresLine2.setValue(false);
        }
        String phoneNumberRaw = getPhoneNumberRaw();
        this.phoneNumber.setValue(phoneNumberRaw);
        this.showPhoneNumber.setValue(!phoneNumberRaw.isEmpty() && branchModel.isExpanded());
        if (branchModel.getPostalAddressModel() != null &&
            branchModel.getPostalAddressModel().getTownName() != null &&
            branchModel.getPostalAddressModel().getPostCode() != null &&
            branchModel.getPostalAddressModel().getCountry() != null)
        this.townCountryAndZipCode.setValue(branchModel.getPostalAddressModel().getTownName() +
                ", " +
                branchModel.getPostalAddressModel().getPostCode() +
                ", " +
                branchModel.getPostalAddressModel().getCountry());
    }

    private String getPhoneNumberRaw() {
        for (ContactInfoModel contactInfo : branchModel.getContactInfoModel()) {
            if (contactInfo.getContactType() != null &&
                    !contactInfo.getContactType().isEmpty() &&
                    contactInfo.getContactType().toLowerCase().equals("phone")) {
                return contactInfo.getContactContent();
            }
        }
        return "";
    }
}
