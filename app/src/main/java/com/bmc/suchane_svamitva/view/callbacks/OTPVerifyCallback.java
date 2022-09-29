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
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.ValidateOtpRequest;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.OTPVerifyInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.OTPVerify;
import com.bmc.suchane_svamitva.view_model.OTPVerifyViewModel;

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
        dialog.setMessage("Validating Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken = tokenType + " " + token;

        ValidateOtpRequest validateOtp = new ValidateOtpRequest();
        validateOtp.setMobileNo(viewModel.USER_MOBILE.get());
        validateOtp.setOTP(viewModel.otpNumber.get());

        Retrofit client = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService = client.create(API_Interface_Suchane.class);
//        Observable<UserDetailsResponse> responseObservable = apiService.FN_ValidateOTP_Svamitva(accessToken, validateOtp);
//        responseObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((result) -> {
//                    dialog.dismiss();
//                    if (result.getUserDetailsList().size()>0) {
//                        saveRequiredInfo(viewModel, result.getUserDetailsList());
//                    } else {
//                        onBackPressed();
//                        Toast.makeText(activity, ""+result.getUserDetailsList(), Toast.LENGTH_SHORT).show();
//                    }
//                }, (error) -> {
//                    dialog.dismiss();
//                    error.printStackTrace();
//                    Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_LONG).show();
//                });

    }

//    public void saveRequiredInfo(OTPVerifyViewModel viewModel, List<UserDetails> userDetailsList) {
//        SharedPreferences.Editor sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, Context.MODE_PRIVATE).edit();
//        sharedPreferences.putBoolean(Constant.LOGIN_STATUS, true);
//        sharedPreferences.putBoolean(Constant.REFRESH_COUNT, false);
//        sharedPreferences.putString(Constant.USER_MOBILE, viewModel.USER_MOBILE.get());
//        sharedPreferences.apply();
//        checkUserDetails(userDetailsList);
//    }
//    public void saveRequiredUPOR(OTPVerifyViewModel viewModel, List<UserDetails_UPOR> userDetailsList) {
//        SharedPreferences.Editor sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, Context.MODE_PRIVATE).edit();
//        sharedPreferences.putBoolean(Constant.LOGIN_STATUS, true);
//        sharedPreferences.putBoolean(Constant.REFRESH_COUNT, false);
//        sharedPreferences.putString(Constant.USER_MOBILE, viewModel.USER_MOBILE.get());
//        sharedPreferences.apply();
//        checkUPOR_UserDetails(userDetailsList);
//    }

//    private void checkVillageMasterData(List<UserDetails> userDetailsList) {
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .isMasterDataAvailable())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result ->
//                {
//                    dialog.dismiss();
//                    if (result) {
//                        checkUserDetails(userDetailsList);
//                    } else {
//                        InsertVillageMasterData(userDetailsList);
//                    }
//                }, error -> {
//                    dialog.dismiss();
//                    error.printStackTrace();
//                });
//    }

//    private void InsertVillageMasterData(List<UserDetails> userDetailsList) {
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        List<SvamitvaVillageMaster> svamitvaVillageMasterList = new Gson().fromJson(new LoadDBFile(activity).load_svamitva_vill_master_Data(), new TypeToken<List<SvamitvaVillageMaster>>() {
//        }.getType());
//
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .InsertVillageMasterData(svamitvaVillageMasterList))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            dialog.dismiss();
//                            checkUserDetails(userDetailsList);
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }

//    public void checkUserDetails(List<UserDetails> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .isUserDetailsAvailable())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                    dialog.dismiss();
//                    if (result) {
//                        deleteUserDetails(userDetailsList);
//                    } else {
//                        InsertUserDetails(userDetailsList);
//                    }
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }
//    public void checkUPOR_UserDetails(List<UserDetails_UPOR> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .isUPOR_UserDetailsAvailable())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                    dialog.dismiss();
//                    if (result) {
//                        deleteUPOR_UserDetails(userDetailsList);
//                    } else {
//                        InsertUPOR_UserDetails(userDetailsList);
//                    }
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }
//
//    public void deleteUserDetails(List<UserDetails> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .deleteUserDetails())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            dialog.dismiss();
//                            InsertUserDetails(userDetailsList);
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }
//    public void deleteUPOR_UserDetails(List<UserDetails_UPOR> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .deleteUPOR_UserDetails())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            dialog.dismiss();
//                            InsertUPOR_UserDetails(userDetailsList);
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }
//
//    public void InsertUserDetails(List<UserDetails> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .InsertUserDetails(userDetailsList))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            dialog.dismiss();
//                            startNavigateToDeliveryLaunch();
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }
//
//    public void InsertUPOR_UserDetails(List<UserDetails_UPOR> userDetailsList){
//        ProgressDialog dialog = new ProgressDialog(activity);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading Data Wait ..");
//        dialog.show();
//
//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .InsertUPOR_UserDetails(userDetailsList))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            dialog.dismiss();
//                            startNavigateToDeliveryLaunch();
//                        }, error -> {
//                            error.printStackTrace();
//                            dialog.dismiss();
//                        }
//                );
//    }

    @Override
    public void onClickResendOtp(OTPVerifyViewModel viewModel) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Checking Wait ..");
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
                    Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                    API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);

                    Observable<SMS_Response> responseObservable = apiService1.FN_Login_UPOR(accessToken, viewModel.USER_MOBILE.get());
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result1) -> {
                                dialog.dismiss();
                                if (result1.isSuccessful()) {
                                    viewModel.resendEnable.set(false);
                                } else {
                                    Toast.makeText(activity, ""+result1.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void verifyOtpWithServer(OTPVerifyViewModel viewModel) {
        String otp = viewModel.value1.get() + viewModel.value2.get() + viewModel.value3.get() + viewModel.value4.get();
        viewModel.otpNumber.set(Constant.convertToInt(otp));
        boolean status = !(TextUtils.isEmpty(otp) | otp.length() != 4);
        if (status) {
            validateOTPWithServer(viewModel);
        } else {
            Toast.makeText(activity, "Enter OTP To Proceed", Toast.LENGTH_LONG).show();
        }

    }

    private void startNavigateToDeliveryLaunch() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private void onBackPressed(){
        Intent intent = new Intent(activity, SignInCallback.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
