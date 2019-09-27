package com.halifaxbankbranches.models.halifax.network;

import com.halifaxbankbranches.models.halifax.data.BankBranchesResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private Api mApi;

    private static final Service sService = new Service();

    public static Service getInstance() {
        return sService;
    }

    private Service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.halifax.co.uk")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(Api.class);
    }

    public Single<BankBranchesResult> getHalifaxBranches() {
        return mApi.getBankBranches()
                .onErrorReturn(error -> new BankBranchesResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
