package com.halifaxbankbranches.models.bank.managers;


import com.halifaxbankbranches.models.bank.client.DataClientResponse;

/**
 * This provides an abstraction of the network layer that correlates to bank location
 * data which is than converted in a common model that is used by the UI.
 * This interface is implemented to retrieve concrete specific a bank's APIs
 * (e.g. Halifax).
 * @param <T>
 */
public interface DataManager<T> {
    void requestData(DataClientResponse<T> dataClientResponse);
    void onStop();
}
