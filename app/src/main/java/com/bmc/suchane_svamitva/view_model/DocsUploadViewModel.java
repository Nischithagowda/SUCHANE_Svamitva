package com.bmc.suchane_svamitva.view_model;

import android.widget.RadioGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableField;

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
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerPending = new ObservableField<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerApproved = new ObservableField<>();

    public DocsUploadViewModel(DocsUploadInterface docsUploadInterface) {
        this.docsUploadInterface = docsUploadInterface;
    }
}
