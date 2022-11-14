package com.bmc.suchane_svamitva.view.callbacks;

import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchApprovedFragment;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchPendingFragment;
import com.bmc.suchane_svamitva.view.ui.DocsUploadActivity;

public class DocsUploadCallback implements DocsUploadInterface {
    DocsUploadActivity activity;

    public DocsUploadCallback(DocsUploadActivity activity) {
        this.activity = activity;
    }

}
