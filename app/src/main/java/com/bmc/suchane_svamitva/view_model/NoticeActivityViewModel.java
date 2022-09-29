package com.bmc.suchane_svamitva.view_model;

import android.view.View;

import androidx.databinding.ObservableField;

import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;

public class NoticeActivityViewModel {
    NoticeActivityInterface noticeActivityInterface;
    public final ObservableField<String> noticeNumber = new ObservableField<>("1234");
    public final ObservableField<String> addressCode = new ObservableField<>("34567");
    public final ObservableField<String> Lat = new ObservableField<>("12.567");
    public final ObservableField<String> Long = new ObservableField<>("77.567");

    public NoticeActivityViewModel(NoticeActivityInterface noticeActivityInterface) {
        this.noticeActivityInterface = noticeActivityInterface;
    }

}
