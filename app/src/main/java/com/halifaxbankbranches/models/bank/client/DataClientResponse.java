package com.halifaxbankbranches.models.bank.client;

import java.util.ArrayList;

/**
 * This interface is implemented by the caller which wants to retrieve the specific
 * bank's data.
 * @param <T>
 */
public interface DataClientResponse<T> {
    void sendResponse(ArrayList<T> response);
}
