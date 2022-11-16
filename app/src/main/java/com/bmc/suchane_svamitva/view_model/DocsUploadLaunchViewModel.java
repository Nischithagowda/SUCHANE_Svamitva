package com.bmc.suchane_svamitva.view_model;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.adapter.OwnerDocsUploadAdapterCompleted;
import com.bmc.suchane_svamitva.view.adapter.OwnerDocsUploadAdapterPending;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadLaunchInterface;
import com.bmc.suchane_svamitva.view.interfaces.OwnerListDocsUploadInterface;
public class DocsUploadLaunchViewModel implements OwnerListDocsUploadInterface, SearchView.OnQueryTextListener{
    DocsUploadLaunchInterface docsUploadLaunchInterface;
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableField<String> talukCode = new ObservableField<>();
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableField<String> hobliCode = new ObservableField<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> villageCode = new ObservableField<>();
    public final ObservableField<String> LGD_VILLAGE_CODE = new ObservableField<>();
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<OwnerDocsUploadAdapterPending> ownerPendingAdapter = new ObservableField<>();
    public final ObservableList<OwnerTbl> ownerPendingList = new ObservableArrayList<>();
    public final ObservableField<OwnerDocsUploadAdapterCompleted> ownerApprovedAdapter = new ObservableField<>();
    public final ObservableList<OwnerTbl> ownerApprovedList = new ObservableArrayList<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerPending = new ObservableField<>();
    public final ObservableField<SearchView.OnQueryTextListener> onQueryTextListenerApproved = new ObservableField<>();
    public ObservableBoolean isNoPendingDataAvailable = new ObservableBoolean(false);
    public ObservableBoolean isNoApprovedDataAvailable = new ObservableBoolean(false);
    public ObservableBoolean isDocsUploadBtnVisible = new ObservableBoolean(true);
    public ObservableInt last_checked_position = new ObservableInt(0);
    public RadioButton selected = null;

    public DocsUploadLaunchViewModel(DocsUploadLaunchInterface docsUploadLaunchInterface) {
        this.docsUploadLaunchInterface = docsUploadLaunchInterface;
        docsUploadLaunchInterface.loadOwnerList(this);
        ownerPendingAdapter.set(new OwnerDocsUploadAdapterPending(this));
        ownerApprovedAdapter.set(new OwnerDocsUploadAdapterCompleted(this));
        this.onQueryTextListenerPending.set(this);
        this.onQueryTextListenerApproved.set(this);
    }

    public void onTypeCheckChanged(final RadioGroup group, int checkedId) {
        docsUploadLaunchInterface.onTypeCheckChanged(this, checkedId);
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
    public void onClickOwner(int position, OwnerTbl ownerTbl, String NoticeNo, View view) {
//        Log.d("CurrentPosition", ""+position);
//        Log.d("LastCheckedPosition", ""+last_checked_position.get());
        RadioButton radioButton = view.findViewById(R.id.radioButton);
        if(selected != null && position != last_checked_position.get() && selected.isChecked())
        {
            Log.d("selectedGetId", ""+selected.getId());
            Log.d("last_checked_position", ""+last_checked_position.get());
            selected.setChecked(false);
        } else {
            Log.d("isChecked", ""+radioButton.isChecked());
            Log.d("CurrentPosition", ""+position);
            Log.d("getId", ""+radioButton.getId());
            radioButton.setChecked(true);
        }
        selected = radioButton;
        last_checked_position.set(position);
        //docsUploadLaunchInterface.onNavigateToDocsUploadFinal(this, ownerTbl, NoticeNo);
//        last_checked_position.set(position);
//        Log.d("NewLastCheckedPosition", ""+last_checked_position.get());
    }
}
