package com.bmc.suchane_svamitva.view.ui;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
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
}
