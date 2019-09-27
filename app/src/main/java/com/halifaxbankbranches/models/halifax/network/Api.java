package com.halifaxbankbranches.models.halifax.network;

import com.halifaxbankbranches.models.halifax.data.BankBranchesResult;

import io.reactivex.Single;
import retrofit2.http.GET;

interface Api {
    @GET("open-banking/v2.2/branches")
    Single<BankBranchesResult> getBankBranches();
}
