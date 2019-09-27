package com.halifaxbankbranches.models.bank.client;

/**
 * This interface is implemented to retrieve data for a specific bank's data.
 * @param <T>
 */
public interface DataClientRequest<T> {
    void makeRequest(DataClientResponse<T> dataClientResponse);
    void stop();
}
