package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.view_model.OTPVerifyViewModel;

/**
 * Created by Nischitha on 18,May,2022
 **/
public interface OTPVerifyInterface {
    void validateOTPWithServer(OTPVerifyViewModel otpViewModel);


    void onClickResendOtp(OTPVerifyViewModel viewModel);

    void verifyOtpWithServer(OTPVerifyViewModel viewModel);

    void loadDefaultFromactivity(OTPVerifyViewModel otpViewModel);
}
