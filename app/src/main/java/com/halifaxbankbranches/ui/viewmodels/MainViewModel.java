package com.halifaxbankbranches.ui.viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.halifaxbankbranches.ui.data.BranchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the ViewModel used by the main screen, including the search component.
 */
public class MainViewModel extends AndroidViewModel {
    private static final String DATA_BUNDLE_KEY = "branch_data";
    private MutableLiveData<ArrayList<BranchModel>> mData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        mData.setValue(new ArrayList<>());
    }

    public void setData(ArrayList<BranchModel> data) {
        mData.postValue(data);
    }

    public void saveData(@NonNull Bundle outState) {
        outState.putParcelableArrayList(DATA_BUNDLE_KEY, mData.getValue());
    }

    public boolean restoreData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(DATA_BUNDLE_KEY) != null) {
            mData.postValue(savedInstanceState.getParcelableArrayList(DATA_BUNDLE_KEY));
            return true;
        }
        return false;
    }

    public List<BranchModel> getHintsByQuery(String searchQuery) {
        if (!searchQuery.isEmpty()) {
            List<BranchModel> result = new ArrayList<>();
            if (mData.getValue() != null) {
                for (BranchModel branch : mData.getValue()) {
                    if (matchesCity(searchQuery, branch)) {
                        result.add(branch);
                    }
                }
            }
            return result;
        }
        return mData.getValue();
    }

    private boolean matchesCity(@NonNull String city, BranchModel branch) {
        if (branch != null &&
                branch.getPostalAddressModel() != null &&
                branch.getPostalAddressModel().getTownName() != null) {
            return branch.getPostalAddressModel().getTownName().toLowerCase().startsWith(city);
        }

        return false;
    }
}
