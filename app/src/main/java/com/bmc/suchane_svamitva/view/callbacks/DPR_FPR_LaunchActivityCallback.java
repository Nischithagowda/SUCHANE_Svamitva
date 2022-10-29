package com.bmc.suchane_svamitva.view.callbacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_FinalActivity;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchApprovedFragment;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchPendingFragment;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DPR_FPR_LaunchActivityCallback implements DPR_FPR_LaunchActivityInterface {
    FragmentActivity activity;

    public DPR_FPR_LaunchActivityCallback(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadOwnerList(DPR_FPR_LaunchActivityViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        try {
            Intent intent = activity.getIntent();
            viewModel.districtCode.set(intent.getStringExtra("districtCode"));
            viewModel.districtName.set(intent.getStringExtra("districtName"));
            viewModel.talukCode.set(intent.getStringExtra("talukCode"));
            viewModel.talukName.set(intent.getStringExtra("talukName"));
            viewModel.hobliCode.set(intent.getStringExtra("hobliCode"));
            viewModel.hobliName.set(intent.getStringExtra("hobliName"));
            viewModel.villageCode.set(intent.getStringExtra("villageCode"));
            viewModel.LGD_VILLAGE_CODE.set(intent.getStringExtra("LGD_VILLAGE_CODE"));
            viewModel.villageName.set(intent.getStringExtra("villageName"));

            Observable
                    .fromCallable(() -> DBConnection.getConnection(activity)
                            .getDataBaseDao()
                            .getPendingDPRDetails(viewModel.LGD_VILLAGE_CODE.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                dialog.dismiss();
                                viewModel.ownerPendingList.clear();
                                viewModel.ownerPendingList.addAll(result);
                            }, error -> {
                                error.printStackTrace();
                                dialog.dismiss();
                            }
                    );

            Observable
                    .fromCallable(() -> DBConnection.getConnection(activity)
                            .getDataBaseDao()
                            .getApprovedDPRDetails(viewModel.LGD_VILLAGE_CODE.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                dialog.dismiss();
                                viewModel.ownerApprovedList.clear();
                                viewModel.ownerApprovedList.addAll(result);
                            }, error -> {
                                error.printStackTrace();
                                dialog.dismiss();
                            }
                    );


        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(activity, ""+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadDefaultFragment() {
        androidx.fragment.app.FragmentTransaction fragmentTransaction =
                activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dpr_fpr_frame_layout, new DPR_FPR_LaunchPendingFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onTypeCheckChanged(int checkedId) {
        if (checkedId == R.id.pending){
            androidx.fragment.app.FragmentTransaction fragmentTransaction1 =
                    activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.dpr_fpr_frame_layout, new DPR_FPR_LaunchPendingFragment());
            fragmentTransaction1.commit();
        } else if (checkedId == R.id.completed){
            androidx.fragment.app.FragmentTransaction fragmentTransaction2 =
                    activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.dpr_fpr_frame_layout, new DPR_FPR_LaunchApprovedFragment());
            fragmentTransaction2.commit();
        }
//        switch (checkedId) {
//            case R.id.pending:
//                androidx.fragment.app.FragmentTransaction fragmentTransaction1 =
//                        activity.getSupportFragmentManager().beginTransaction();
//                fragmentTransaction1.replace(R.id.dpr_fpr_frame_layout, new DPR_FPR_LaunchPendingFragment());
//                fragmentTransaction1.commit();
//                break;
//            case R.id.completed:
//                androidx.fragment.app.FragmentTransaction fragmentTransaction2 =
//                        activity.getSupportFragmentManager().beginTransaction();
//                fragmentTransaction2.replace(R.id.dpr_fpr_frame_layout, new DPR_FPR_LaunchApprovedFragment());
//                fragmentTransaction2.commit();
//                break;
//        }
    }

    @Override
    public void onNavigateToDPR_FPR_Final(){
        Intent intent = new Intent(activity, DPR_FPR_FinalActivity.class);
        activity.startActivity(intent);
    }
}
