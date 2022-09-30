package com.bmc.suchane_svamitva.view.callbacks;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.SMS_Request;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.USER_DETAILS;
import com.bmc.suchane_svamitva.model.ValidateOtpRequest;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.OTPVerifyInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.OTPVerify;
import com.bmc.suchane_svamitva.view_model.OTPVerifyViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Nischitha on 18,May,2022
 **/
public class OTPVerifyCallback implements OTPVerifyInterface {
    OTPVerify activity;

    public OTPVerifyCallback(OTPVerify activity) {
        this.activity = activity;
    }

    @Override
    public void verifyOtpWithServer(OTPVerifyViewModel viewModel) {
        String otp = viewModel.value1.get() + viewModel.value2.get() + viewModel.value3.get() + viewModel.value4.get() + viewModel.value5.get() + viewModel.value6.get();
        boolean status = !(TextUtils.isEmpty(otp) | otp.length() != 6);
        if (status) {
            viewModel.otpNumber.set(Constant.convertToInt(otp));
            validateOTPWithServer(viewModel);
        } else {
            Toast.makeText(activity, "Enter Valid OTP To Proceed", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void loadDefaultFromactivity(OTPVerifyViewModel viewModel) {
        Intent intent = activity.getIntent();
        String USER_MOBILE = intent.getStringExtra(Constant.USER_MOBILE);
        viewModel.USER_MOBILE.set(USER_MOBILE);
        boolean btnVisible = false;
        viewModel.resendEnable.set(btnVisible);
    }

    @Override
    public void validateOTPWithServer(OTPVerifyViewModel viewModel) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Validating Please Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken = tokenType + " " + token;

        ValidateOtpRequest validateOtp = new ValidateOtpRequest();
        validateOtp.setMobileNumber(viewModel.USER_MOBILE.get());
        validateOtp.setOTP(""+viewModel.otpNumber.get());

        Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
        Observable<SMS_Response> responseObservable = apiService.FnValidateOTP(accessToken, validateOtp);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    dialog.dismiss();
                    if (result.getRESPONSE_CODE().contains("200")) {
                        if (result.getUserDetailsList().size() > 0) {
                            saveRequiredInfo(viewModel, result.getUserDetailsList());
                        } else {
                            onBackPressed();
                            Toast.makeText(activity, "" + result.getUserDetailsList(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        onBackPressed();
                        Toast.makeText(activity, "" + result.getUserDetailsList(), Toast.LENGTH_SHORT).show();
                    }
                }, (error) -> {
                    dialog.dismiss();
                    error.printStackTrace();
                    Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_LONG).show();
                });

    }

    public void saveRequiredInfo(OTPVerifyViewModel viewModel, List<USER_DETAILS> userDetailsList) {
        SharedPreferences.Editor sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE).edit();
        sharedPreferences.putBoolean(Constant.LOGIN_STATUS, true);
        sharedPreferences.putBoolean(Constant.REFRESH_COUNT, false);
        sharedPreferences.putString(Constant.USER_MOBILE, viewModel.USER_MOBILE.get());
        sharedPreferences.apply();
        checkUserDetails(userDetailsList);
    }

    public void checkUserDetails(List<USER_DETAILS> userDetailsList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .isUserDetailsAvailable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            if (result) {
                                deleteUserDetails(userDetailsList);
                            } else {
                                InsertUserDetails(userDetailsList);
                            }
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    public void deleteUserDetails(List<USER_DETAILS> userDetailsList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteUserDetails())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            InsertUserDetails(userDetailsList);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    public void InsertUserDetails(List<USER_DETAILS> userDetailsList){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertUserDetails(userDetailsList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            startNavigateToDeliveryLaunch();
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    private void startNavigateToDeliveryLaunch() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onClickResendOtp(OTPVerifyViewModel viewModel) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending PLease Wait ..");
        dialog.show();

        Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
        Observable<TokenRes> serviceToken = apiService.getToken(activity.getString(R.string.api_user_id), activity.getString(R.string.api_password), activity.getString(R.string.grant_type));
        serviceToken.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    SharedPreferences.Editor editor = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE).edit();
                    editor.putString(activity.getString(R.string.token), result.getAccessToken());
                    editor.putString(activity.getString(R.string.token_type),result.getTokenType());
                    editor.putString(activity.getString(R.string.refresh_tkn), result.getRefreshToken());
                    editor.apply();

                    String accessToken = result.getTokenType() + " " + result.getAccessToken();

                    SMS_Request sms_request = new SMS_Request();
                    sms_request.setMobileNumberToSendOTP(viewModel.USER_MOBILE.get());

                    Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                    API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);

                    Observable<SMS_Response> responseObservable = apiService1.FnSendOTP(accessToken, sms_request);
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result1) -> {
                                dialog.dismiss();
                                if (result1.getRESPONSE_STATUS().contains("Sent")) {
                                    viewModel.resendEnable.set(false);
                                } else {
                                    Toast.makeText(activity, ""+result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                                }
                            }, (error) -> {
                                dialog.dismiss();
                                Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            });
                }, (error) -> {
                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
    }

    private void onBackPressed(){
        Intent intent = new Intent(activity, SignInCallback.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
