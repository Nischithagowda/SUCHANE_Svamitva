package com.bmc.suchane_svamitva.view_model;

import android.widget.RadioGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.adapter.OwnerAdapter;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view.interfaces.OwnerListInterface;

public class DPR_FPR_LaunchActivityViewModel implements OwnerListInterface, SearchView.OnQueryTextListener {
    DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface;
    public final ObservableInt districtCode = new ObservableInt(0);
    public final ObservableField<String> districtName = new ObservableField<>("District Name");
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukName = new ObservableField<>("Taluk Name");
    public final ObservableInt hobliCode = new ObservableInt(0);
    public final ObservableField<String> hobliName = new ObservableField<>("Hobli Name");
    public final ObservableField<String> villageCode = new ObservableField<>("");
    public final ObservableField<String> villageName = new ObservableField<>("Village Name");
    public final ObservableField<OwnerAdapter> ownerAdapter = new ObservableField<>();
    public final ObservableList<OwnerTbl> ownerList = new ObservableArrayList<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListener = new ObservableField<>();

    public DPR_FPR_LaunchActivityViewModel(DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface) {
        this.dpr_fpr_launchActivityInterface = dpr_fpr_launchActivityInterface;
        ownerAdapter.set(new OwnerAdapter(this));
        this.onQueryTextListener.set(this);
        dpr_fpr_launchActivityInterface.loadOwnerList(this);
    }

    public void onTypeCheckChanged(final RadioGroup group, int checkedId) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ownerAdapter.get().getFilter().filter(newText);
        return false;
    }

    @Override
    public void onClickOwner(OwnerTbl ownerTbl, String chalta_No) {
        dpr_fpr_launchActivityInterface.onNavigateToDPR_FPR_Final();
    }
}
