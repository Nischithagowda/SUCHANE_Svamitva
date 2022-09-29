package com.bmc.suchane_svamitva.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.TokenRes;

import retrofit2.Call;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class TokenUtility {

    private static String saveToken(TokenRes tokenRes, Context context) {
        SharedPreferences.Editor sharedPreferences = context.getSharedPreferences(context.getString(R.string.Auth), MODE_PRIVATE).edit();
        sharedPreferences.putString(context.getString(R.string.token), tokenRes.getAccessToken());
        sharedPreferences.apply();
        return tokenRes.getAccessToken();
    }

  public   static  String getSavedToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.Auth), MODE_PRIVATE);
        return sharedPreferences.getString(context.getString(R.string.token), null);
    }

    static  String getAccessToken(Context context) {
        String userName = context.getString(R.string.api_user_id);
        String pwd = context.getString(R.string.api_password);
        // request new key
        API_Interface_Suchane apiService = APIClient_Suchane.getClientWithoutToken(context.getString(R.string.api_url)).create(API_Interface_Suchane.class);
        Call<TokenRes> call = apiService.requestAccessToken(userName, pwd, "password");
        String accessToken = null;
        Response<TokenRes> response;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                TokenRes tokenRes = response.body();
                assert tokenRes != null;
                accessToken = saveToken(tokenRes,context);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accessToken;
    }

}
