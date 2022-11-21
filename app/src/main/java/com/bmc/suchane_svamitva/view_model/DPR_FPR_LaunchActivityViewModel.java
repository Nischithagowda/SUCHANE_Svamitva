package com.bmc.suchane_svamitva.view_model;

import android.widget.RadioGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.adapter.OwnerAdapterApproved;
import com.bmc.suchane_svamitva.view.adapter.OwnerAdapterPending;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view.interfaces.OwnerListInterface;

public class DPR_FPR_LaunchActivityViewModel implements OwnerListInterface, SearchView.OnQueryTextListener {
    DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface;
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableField<String> talukCode = new ObservableField<>();
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableField<String> hobliCode = new ObservableField<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> villageCode = new ObservableField<>();
    public final ObservableField<String> LGD_VILLAGE_CODE = new ObservableField<>();
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<OwnerAdapterPending> ownerPendingAdapter = new ObservableField<>();
    public final ObservableList<OwnerTbl> ownerPendingList = new ObservableArrayList<>();
    public final ObservableField<OwnerAdapterApproved> ownerApprovedAdapter = new ObservableField<>();
    public final ObservableList<OwnerTbl> ownerApprovedList = new ObservableArrayList<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerPending = new ObservableField<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerApproved = new ObservableField<>();
    public ObservableBoolean isNoPendingDataAvailable = new ObservableBoolean(false);
    public ObservableBoolean isNoApprovedDataAvailable = new ObservableBoolean(false);

    public DPR_FPR_LaunchActivityViewModel(DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface) {
        this.dpr_fpr_launchActivityInterface = dpr_fpr_launchActivityInterface;
        ownerPendingAdapter.set(new OwnerAdapterPending(this));
        ownerApprovedAdapter.set(new OwnerAdapterApproved(this));
        this.onQueryTextListenerPending.set(this);
        this.onQueryTextListenerApproved.set(this);
        load();
    }

    private void load() {
        dpr_fpr_launchActivityInterface.loadOwnerList(this);
//        dpr_fpr_launchActivityInterface.loadDefaultFragment();
    }

    public void onTypeCheckChanged(final RadioGroup group, int checkedId) {
        dpr_fpr_launchActivityInterface.onTypeCheckChanged(checkedId);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ownerPendingAdapter.get().getFilter().filter(newText);
        ownerApprovedAdapter.get().getFilter().filter(newText);
        return false;
    }

    @Override
    public void onClickOwner(OwnerTbl ownerTbl, String NoticeNo) {
        dpr_fpr_launchActivityInterface.onNavigateToDPR_FPR_Final(this, ownerTbl);
    }
}
