package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.FragmentDprFprPendingLaunchBinding;
import com.bmc.suchane_svamitva.view.callbacks.DPR_FPR_LaunchActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;

public class DPR_FPR_LaunchPendingFragment extends Fragment {

    DPR_FPR_LaunchActivityViewModel viewModel;

    public DPR_FPR_LaunchPendingFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDprFprPendingLaunchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dpr_fpr_pending_launch, container, false);
        DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface = new DPR_FPR_LaunchActivityCallback(getActivity());
        viewModel = new DPR_FPR_LaunchActivityViewModel(dpr_fpr_launchActivityInterface);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}