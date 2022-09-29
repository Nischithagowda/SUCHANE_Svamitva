package com.bmc.suchane_svamitva.view_model;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bmc.suchane_svamitva.view.interfaces.OTPVerifyInterface;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nischitha on 18,May,2022
 **/
public class OTPVerifyViewModel {
    public final ObservableField<Boolean> requestFocus1 = new ObservableField<>(true);
    public final ObservableField<Boolean> requestFocus2 = new ObservableField<>(false);
    public final ObservableField<Boolean> requestFocus3 = new ObservableField<>(false);
    public final ObservableField<Boolean> requestFocus4 = new ObservableField<>(false);
    public final ObservableField<String> value1 = new ObservableField<>("");
    public final ObservableField<String> value2 = new ObservableField<>("");
    public final ObservableField<String> value3 = new ObservableField<>("");
    public final ObservableField<String> value4 = new ObservableField<>("");
    public final ObservableField<String> USER_MOBILE = new ObservableField<>();
    public final ObservableBoolean resendEnable = new ObservableBoolean(false);
    public final ObservableInt otpNumber=new ObservableInt();

    OTPVerifyInterface otpVerifyInterface;

    public OTPVerifyViewModel(OTPVerifyInterface otpVerifyInterface) {
        this.otpVerifyInterface = otpVerifyInterface;
        setUp();
        startTimer();
    }

    private void setUp() {
        otpVerifyInterface.loadDefaultFromactivity(this);
    }



    public void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 1000 * 30);
    }

    public void value1Watcher(CharSequence text, int start, int end, int count) {
        requestFocus1.set(false);
        value1.set(text.toString());
        if (count == 1) {
            requestFocus2.set(true);
        } else if (count == 0) {
            requestFocus2.set(false);
        }
    }

    public void value2Watcher(CharSequence text, int start, int end, int count) {
        requestFocus2.set(false);
        value2.set(text.toString());
        if (count == 1) {
            requestFocus3.set(true);
            requestFocus1.set(false);
        } else if (count == 0) {
            requestFocus1.set(true);
            requestFocus3.set(false);
        }
    }

    public void value3Watcher(CharSequence text, int start, int end, int count) {
        requestFocus3.set(false);
        value3.set(text.toString());
        if (count == 1) {
            requestFocus4.set(true);
            requestFocus2.set(false);
        } else if (count == 0) {
            requestFocus2.set(true);
            requestFocus4.set(false);
        }
    }

    public void value4Watcher(CharSequence text, int start, int end, int count) {
        requestFocus4.set(false);
        value4.set(text.toString());
        if (count == 0) {
            requestFocus3.set(true);
        } else if (count == 1) {
            requestFocus3.set(false);
        }
    }


    public void verifyOtpWithServer(View view) {
        otpVerifyInterface.verifyOtpWithServer(this);
    }

    public void onClickMobileNumberChange(View view) {
    }

    public void onClickResendOtp(View view) {
        otpVerifyInterface.onClickResendOtp(this);
    }


    class MyTimer extends TimerTask {
        @Override
        public void run() {
            resendEnable.set(true);
        }
    }
}
