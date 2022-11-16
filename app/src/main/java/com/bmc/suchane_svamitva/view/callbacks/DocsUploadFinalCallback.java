package com.bmc.suchane_svamitva.view.callbacks;

import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view.ui.DocsUploadFinalActivity;

public class DocsUploadFinalCallback implements DocsUploadFinalInterface {
    DocsUploadFinalActivity activity;

    public DocsUploadFinalCallback(DocsUploadFinalActivity activity) {
        this.activity = activity;
    }
}
