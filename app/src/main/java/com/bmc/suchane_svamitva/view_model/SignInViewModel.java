package com.bmc.suchane_svamitva.view_model;

import android.view.View;

import com.bmc.suchane_svamitva.view.interfaces.SignInInterface;

import androidx.databinding.ObservableField;

/**
 * Created by Nischitha on 19,April,2022
 **/
public class SignInViewModel {
    SignInInterface signInInterface;

    public SignInViewModel(SignInInterface signInInterface) {
        this.signInInterface = signInInterface;
    }

    public final ObservableField<String> mobileNumber = new ObservableField<>("");
    public final ObservableField<String> mobileNumberError = new ObservableField<>();

    public void onClickNext(View view) {
        String number = mobileNumber.get().trim();
        if (isMobileValid(number)) {
            signInInterface.sendOtp(number);
        } else {
            mobileNumberError.set("Valid Mobile Number is required");
        }
    }

    public void mobileNumberWatcher(CharSequence charSequence) {
        mobileNumber.set(String.valueOf(charSequence));
        mobileNumberError.set(null);
    }

    public boolean isMobileValid(String number) {
        return android.util.Patterns.PHONE.matcher(number).matches() & number.length() == 10;
    }
}
