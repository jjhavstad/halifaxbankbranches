package com.halifaxbankbranches.models.halifax.client;

import com.halifaxbankbranches.models.halifax.network.Service;
import com.halifaxbankbranches.models.bank.client.DataClientRequest;
import com.halifaxbankbranches.models.bank.client.DataClientResponse;
import com.halifaxbankbranches.models.halifax.data.Branch;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;

/**
 * This is the DataClientRequest implementation to retrieve Halifax specific bank data.
 */
public class BranchDataClientRequest implements DataClientRequest<Branch> {
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void makeRequest(final DataClientResponse<Branch> dataClientResponse) {
        disposable.add(Service.getInstance().getHalifaxBranches().subscribe(result -> {
            if (result != null &&
                    result.getData() != null &&
                    result.getData().length > 0 &&
                    result.getData()[0] != null &&
                    result.getData()[0].getBrand() != null &&
                    result.getData()[0].getBrand().length > 0 &&
                    result.getData()[0].getBrand()[0] != null &&
                    result.getData()[0].getBrand()[0].getBranch() != null &&
                    result.getData()[0].getBrand()[0].getBranch().length > 0) {
                dataClientResponse.sendResponse(new ArrayList<>(Arrays.asList(result.getData()[0].getBrand()[0].getBranch())));
            } else {
                dataClientResponse.sendResponse(new ArrayList<>());
            }
        }));
    }

    @Override
    public void stop() {
        disposeAll();
    }

    private void disposeAll() {
        disposable.clear();
    }
}
