package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.FragmentPendingDocsUploadLaunchBinding;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadLaunchCallback;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadLaunchInterface;
import com.bmc.suchane_svamitva.view_model.DocsUploadLaunchViewModel;

public class DocsUploadLaunchPendingFragment extends Fragment {

    DocsUploadLaunchViewModel viewModel;

    public DocsUploadLaunchPendingFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPendingDocsUploadLaunchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pending_docs_upload_launch, container, false);
        DocsUploadLaunchInterface docsUploadLaunchInterface = new DocsUploadLaunchCallback(getActivity());
        viewModel = new DocsUploadLaunchViewModel(docsUploadLaunchInterface);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
