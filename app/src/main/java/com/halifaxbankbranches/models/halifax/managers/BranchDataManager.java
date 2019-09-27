package com.halifaxbankbranches.models.halifax.managers;


import com.halifaxbankbranches.models.bank.client.DataClientRequest;
import com.halifaxbankbranches.models.bank.client.DataClientResponse;
import com.halifaxbankbranches.models.halifax.data.Branch;
import com.halifaxbankbranches.models.bank.managers.DataManager;

/**
 * This a DataManager implementation specific to Halifax data,
 * which can be converted by the implementation into a common model
 * used by the UI.
 */
public class BranchDataManager implements DataManager<Branch> {
    private DataClientRequest<Branch> mDataClientRequest;

    public BranchDataManager(DataClientRequest<Branch> dataClientRequest) {
        mDataClientRequest = dataClientRequest;
    }

    @Override
    public void requestData(DataClientResponse<Branch> dataClientResponse) {
        mDataClientRequest.makeRequest(dataClientResponse);
    }

    @Override
    public void onStop() {
        mDataClientRequest.stop();
    }
}
