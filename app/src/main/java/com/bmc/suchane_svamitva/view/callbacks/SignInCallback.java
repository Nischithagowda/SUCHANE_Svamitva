package com.bmc.suchane_svamitva.view.callbacks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.model.SMS_Request;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.SignInInterface;
import com.bmc.suchane_svamitva.view.ui.OTPVerify;
import com.bmc.suchane_svamitva.view.ui.SignIn;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Nischitha on 28,April,2022
 **/
public class SignInCallback implements SignInInterface, ActivityCompat.OnRequestPermissionsResultCallback {
    SignIn activity;

    public SignInCallback(SignIn activity) {
        this.activity = activity;
    }

    @Override
    public void sendOtp(String number) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Checking Please Wait ..");
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
                    sms_request.setMobileNumberToSendOTP(number);

                    Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
                    API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                    Observable<SMS_Response> responseObservable = apiService1.FnSendOTP(accessToken, sms_request);
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result1) -> {
                                dialog.dismiss();
                                if (result1.getRESPONSE_CODE().contains("200")) {
                                    onNavigateToOtpVerify(number);
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

    public void onNavigateToOtpVerify(String number) {
        Intent intent = new Intent(activity, OTPVerify.class);
        intent.putExtra(Constant.USER_MOBILE, number);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
