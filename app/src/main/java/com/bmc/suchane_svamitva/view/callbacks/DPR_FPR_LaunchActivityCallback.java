package com.bmc.suchane_svamitva.view.callbacks;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_FinalActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeActivity;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;

public class DPR_FPR_LaunchActivityCallback implements DPR_FPR_LaunchActivityInterface {
    FragmentActivity activity;

    public DPR_FPR_LaunchActivityCallback(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadOwnerList(DPR_FPR_LaunchActivityViewModel viewModel){
        OwnerTbl ownerTbl = new OwnerTbl();
        ownerTbl.setOwner_id(1);
        ownerTbl.setChalta_No("123");
        ownerTbl.setMunicipal_GPFD("1234");
        ownerTbl.setOwner_Name("Test");

        viewModel.ownerList.add(ownerTbl);
    }

    @Override
    public void onNavigateToDPR_FPR_Final(){
        Intent intent = new Intent(activity, DPR_FPR_FinalActivity.class);
        activity.startActivity(intent);
    }
}
