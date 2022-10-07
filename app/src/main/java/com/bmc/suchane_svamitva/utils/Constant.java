package com.bmc.suchane_svamitva.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.model.TokenRes;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class Constant {
    public static final String MY_SHARED_PREF = "my_pref";
    public static final String LOGIN_STATUS = "login_status";
    public static final String USER_MOBILE = "useR_PH_MOB";
    public static final String SHOW_DATA_REQ = "show_data";
    public static final String SHOW_DATA_REQ_UPOR = "show_data_UPOR";
    public static final int IMAGE_CAPTURE_REQ = 77;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 7;
    public static final int CAMERA_REQUEST= 11;
    public static final int CAMERA_REQUEST_PropertyOrLand = 11;
    public static final int CAMERA_REQUEST_ServingNotice = 12;
    public static final String APP_IMAGE = "SvamitvaNotice/Pictures";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String REFRESH_COUNT = "refresh_count";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String PLACE = "Location_User";
    public static final String BUNDLE_DATA = "bundle";
    public static final String LOCATION_DATA = "location_data";
    public static final int LOCATION_REQ = 100;
    Activity activity;

    public Constant(Activity activity) {
        this.activity = activity;
    }

    public static Integer convertToInt(String otp) {
        try {
            return Integer.valueOf(otp);
        } catch (Exception e) {
            return 0;
        }
    }

    public void getRefreshToken() {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Refreshing token wait..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        String API_UNAME = activity.getString(R.string.api_user_id);
        String API_PWD = activity.getString(R.string.api_password);
        Retrofit retrofit1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane service1 = retrofit1.create(API_Interface_Suchane.class);
        Observable<TokenRes> observable1 = service1.getToken(API_UNAME, API_PWD, "password");
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    dialog.dismiss();
                    saveToken(result);
                    Toast.makeText(activity, activity.getString(R.string.refresh_token), Toast.LENGTH_LONG).show();

                }, error -> {
                    error.printStackTrace();
                    Toast.makeText(activity, activity.getString(R.string.token_error), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
    }

    private boolean saveToken(TokenRes output1) {
        SharedPreferences.Editor editor1 = activity.getSharedPreferences(activity.getString(R.string.Auth), MODE_PRIVATE).edit();
        editor1.putString(activity.getString(R.string.token), output1.getAccessToken());
        editor1.putString(activity.getString(R.string.token_type), output1.getTokenType());
        editor1.putString(activity.getString(R.string.refresh_tkn),output1.getRefreshToken());
        editor1.apply();

        return true;

    }

}
