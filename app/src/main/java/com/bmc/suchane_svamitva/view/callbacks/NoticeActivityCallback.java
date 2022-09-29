package com.bmc.suchane_svamitva.view.callbacks;

import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;
import com.bmc.suchane_svamitva.view.ui.NoticeActivity;

public class NoticeActivityCallback implements NoticeActivityInterface {
    NoticeActivity activity;

    public NoticeActivityCallback(NoticeActivity activity) {
        this.activity = activity;
    }
}
