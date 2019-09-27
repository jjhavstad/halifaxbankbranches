package com.halifaxbankbranches.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.halifaxbankbranches.R;
import com.halifaxbankbranches.databinding.ItemBankBranchBinding;
import com.halifaxbankbranches.ui.data.BranchModel;
import com.halifaxbankbranches.ui.data.GeographicCoordinatesModel;
import com.halifaxbankbranches.ui.viewmodels.BranchViewModel;

import java.util.List;

public class BankBranchAdapter extends RecyclerView.Adapter<BankBranchAdapter.ViewHolder> {
    private List<BranchModel> mData;
    private LifecycleOwner mLifeCycleOwner;
    private OnAddressClicked mOnAddressClicked;

    public interface OnAddressClicked {
        void launchMap(String lat, String lon, String address);
    }

    public BankBranchAdapter(LifecycleOwner lifecycleOwner, @Nullable OnAddressClicked onAddressClicked) {
        mLifeCycleOwner = lifecycleOwner;
        mOnAddressClicked = onAddressClicked;
    }

    public void setData(List<BranchModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBankBranchBinding itemBankBranchBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_bank_branch, parent, false);
        itemBankBranchBinding.setViewModel(new BranchViewModel());
        return new ViewHolder(itemBankBranchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.getViewModel().setBranchModel(mData.get(position));
        holder.binding.setLifecycleOwner(mLifeCycleOwner);
        holder.binding.getRoot().setOnClickListener(v -> {
            holder.binding.getViewModel().setExpanded(!holder.binding.getViewModel().isExpandedRaw());
            notifyDataSetChanged();
        });
        holder.binding.bankBranchAddress.setOnClickListener(v -> onAddressClicked(position));
        holder.binding.bankBranchAddressMapImageView.setOnClickListener(v -> onAddressClicked(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    private void onAddressClicked(int position) {
        if (mOnAddressClicked != null) {
            BranchModel branch = mData.get(position);
            GeographicCoordinatesModel geographicCoordinates =
                    branch.getPostalAddressModel()
                            .getGeoLocationModel()
                            .getGeographicCoordinatesModel();
            String lat = geographicCoordinates.getLatitude();
            String lon = geographicCoordinates.getLongitude();
            String address = branch.getName() +
                    "," +
                    branch.getPostalAddressModel().getTownName() +
                    "," +
                    branch.getPostalAddressModel().getCountry();
            mOnAddressClicked.launchMap(lat, lon, address);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBankBranchBinding binding;
        public ViewHolder(@NonNull ItemBankBranchBinding itemBankBranchBinding) {
            super(itemBankBranchBinding.getRoot());
            binding = itemBankBranchBinding;
        }
    }
}
