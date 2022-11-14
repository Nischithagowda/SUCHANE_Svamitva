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
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadInterface;

public class DocsUploadViewModel {
    DocsUploadInterface docsUploadInterface;
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

    public DocsUploadViewModel(DocsUploadInterface docsUploadInterface) {
        this.docsUploadInterface = docsUploadInterface;
    }
}
