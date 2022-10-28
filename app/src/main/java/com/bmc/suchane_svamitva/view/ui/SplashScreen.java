package com.bmc.suchane_svamitva.view.ui;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.LogoutResponse;
import com.bmc.suchane_svamitva.model.SMS_Request_Login;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.VersionRequest;
import com.bmc.suchane_svamitva.model.VersionResponse;
import com.bmc.suchane_svamitva.utils.Constant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Nischitha on 14,June,2022
 **/
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getRefreshToken();
    }

    public void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        boolean status = sharedPreferences.getBoolean(Constant.LOGIN_STATUS, false);
        if (status) {
            getUserLoggedInStatus();
        } else {
            Intent i = new Intent(this, SignIn.class);
            startActivity(i);
            finish();
        }
    }
    private void getRefreshToken() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Checking please wait..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        Retrofit retrofit1 = APIClient_Suchane.getClientWithoutToken(getString(R.string.api_url));
        API_Interface_Suchane service1 = retrofit1.create(API_Interface_Suchane.class);
        Observable<TokenRes> serviceToken = service1.getToken(getString(R.string.api_user_id), getString(R.string.api_password), getString(R.string.grant_type));
        serviceToken.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.Auth), MODE_PRIVATE).edit();
                    editor.putString(getString(R.string.token), result.getAccessToken());
                    editor.putString(getString(R.string.token_type),result.getTokenType());
                    editor.putString(getString(R.string.refresh_tkn), result.getRefreshToken());
                    editor.apply();

                    VersionRequest versionRequest = new VersionRequest();
                    versionRequest.setVERSION_CODE(""+BuildConfig.VERSION_NAME);

                    String accessToken = result.getTokenType() + " " + result.getAccessToken();

                    Retrofit client1 = APIClient_Suchane.getClient(getApplicationContext(), getString(R.string.api_url));
                    API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
                    Observable<VersionResponse> responseObservable = apiService1.FnGetVersion(accessToken, versionRequest);
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result1) -> {
                                dialog.dismiss();
                                if (result1.getRESPONSE_CODE().contains("200")) {
                                    SharedPreferences.Editor sharedPreferences = getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE).edit();
                                    sharedPreferences.putString(Constant.VersionCode, result1.getVERSION_CODE());
                                    sharedPreferences.putString(Constant.DistanceRange, result1.getDISTANCE_RANGE());
                                    sharedPreferences.apply();
                                    checkLoginStatus();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Application Version has been changed, Please contact Admin For clarification."+result1.getVERSION_CODE(), Toast.LENGTH_SHORT).show();
                                }
                            }, (error) -> {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            });
                }, (error) -> {
                    Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
    }

    public void getUserLoggedInStatus(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Checking Please wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = getSharedPreferences(getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);
        String androidId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        SMS_Request_Login sms_request_login = new SMS_Request_Login();
        sms_request_login.setMobileNumberToSendOTP(mobNum);
        sms_request_login.setDeviceId(androidId);

        Retrofit client1 = APIClient_Suchane.getClient(getApplicationContext(), getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        Observable<LogoutResponse> responseObservable = apiService1.FnCheckUserLoggedIn(accessToken, sms_request_login);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result1) -> {
                    dialog.dismiss();
                    if (result1.getRESPONSE_CODE().contains("200")) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        clearLocalDataBase();
                    }
                }, (error) -> {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                });

    }

    private void clearLocalDataBase() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading, Please Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(this)
                        .getDataBaseDao()
                        .deleteUserDetails())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    DBConnection.getConnection(this).getDataBaseDao().deleteHobliDetails();
                    DBConnection.getConnection(this).getDataBaseDao().deleteFullVillageDetails();
                    DBConnection.getConnection(this).getDataBaseDao().deleteFullNoticeDetails();
                    DBConnection.getConnection(this).getDataBaseDao().deleteFullImageDetails();
                    DBConnection.getConnection(this).getDataBaseDao().deleteFullPendingDPRDetails();
                    DBConnection.getConnection(this).getDataBaseDao().deleteFullApprovedDPRDetails();

                    dialog.dismiss();
                }, error -> {
                    dialog.dismiss();
                    error.printStackTrace();
                });

        SharedPreferences sharedPreferences = this.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        SharedPreferences.Editor editor_Auth = this.getSharedPreferences(this.getString(R.string.Auth), MODE_PRIVATE).edit();
        editor_Auth.clear();
        editor_Auth.apply();

        Intent intent = new Intent(this, SignIn.class);
        this.startActivity(intent);
        this.finish();
        Toast.makeText(this, "This User Logged In from another Device. Please Login again", Toast.LENGTH_SHORT).show();

    }
}
