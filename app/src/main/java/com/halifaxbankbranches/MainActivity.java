package com.halifaxbankbranches;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.halifaxbankbranches.databinding.ActivityMainBinding;
import com.halifaxbankbranches.models.halifax.client.BranchDataClientRequest;
import com.halifaxbankbranches.models.halifax.managers.BranchDataManager;
import com.halifaxbankbranches.ui.adapters.BankBranchAdapter;
import com.halifaxbankbranches.ui.converters.HalifaxBranchConverter;
import com.halifaxbankbranches.ui.data.BranchModel;
import com.halifaxbankbranches.ui.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private BankBranchAdapter mBankBranchAdapter;
    private BranchDataManager mBranchDataManager;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initialize(mBinding, savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBranchDataManager.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        mMainViewModel.saveData(outState);
        super.onSaveInstanceState(outState);
    }

    private void initialize(ActivityMainBinding binding, Bundle savedInstanceState) {
        mBranchDataManager = new BranchDataManager(new BranchDataClientRequest());
        setupRefreshView(binding);
        setupSearchView(binding);
        setupListView(binding);

        if (!mMainViewModel.restoreData(savedInstanceState)) {
            requestData(mBinding);
        }
    }

    private void setupSearchView(ActivityMainBinding binding) {
        mMainViewModel =
                ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(mMainViewModel);
        binding.searchBranches.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<BranchModel> result = mMainViewModel.getHintsByQuery(newText);

                if (!result.isEmpty()) {
                    mBankBranchAdapter.setData(result);
                    return true;
                }

                return false;
            }
        });
    }

    private void setupListView(ActivityMainBinding binding) {
        mBankBranchAdapter = new BankBranchAdapter(this, this::launchMap);
        ((RecyclerView) binding.branchListView).setAdapter(mBankBranchAdapter);
    }

    private void setupRefreshView(ActivityMainBinding binding) {
        binding.branchRefreshView.setOnRefreshListener(() -> {
            requestData(binding);
        });
    }

    private void requestData(ActivityMainBinding binding) {
        binding.branchRefreshView.setRefreshing(true);
        mBranchDataManager.requestData(response -> {
            if (response != null) {
                ArrayList<BranchModel> result = HalifaxBranchConverter.fromHalifaxBranches(response);
                mMainViewModel.setData(result);
                mBankBranchAdapter.setData(result);
                binding.branchRefreshView.setRefreshing(false);
            }
        });
    }

    private void launchMap(String lat, String lon, String address) {
        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon + "?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}
