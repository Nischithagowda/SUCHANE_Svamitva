package com.bmc.suchane_svamitva.api;

import com.bmc.suchane_svamitva.model.MultipartImageResponse;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TokenRes;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nischitha on 20,May,2022
 **/
public interface API_Interface_Suchane {
    @FormUrlEncoded
    @POST("token")
    Observable<TokenRes> getToken(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );

    @FormUrlEncoded
    @POST("token")
    Call<TokenRes> requestAccessToken(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grant_type
    );

    @POST("api/values/FN_Login_UPOR/{MobileNum}")
    Observable<SMS_Response> FN_Login_UPOR(
            @Header("Authorization") String accessToken,
            @Path("MobileNum") String MobileNum
    );

    @POST("api/values/FN_Login_Svamitva/{MobileNum}")
    Observable<SMS_Response> FN_Login_Svamitva(
            @Header("Authorization") String accessToken,
            @Path("MobileNum") String MobileNum
    );

    @POST("api/values/FN_Logout_UPOR/{MobileNum}")
    Observable<SMS_Response> FN_Logout_UPOR(
            @Header("Authorization") String accessToken,
            @Path("MobileNum") String MobileNum
    );

    @POST("api/values/FN_Logout_Svamitva/{MobileNum}")
    Observable<SMS_Response> FN_Logout_Svamitva(
            @Header("Authorization") String accessToken,
            @Path("MobileNum") String MobileNum
    );


    @Multipart
    @POST("api/Images/Multipart_UploadImage")
    Observable<MultipartImageResponse> sendImage(@Header("Authorization") String accessToken,
                                                 @retrofit2.http.Part MultipartBody.Part file,
                                                 @retrofit2.http.Part MultipartBody.Part DistCode,
                                                 @retrofit2.http.Part MultipartBody.Part TalukCode,
                                                 @retrofit2.http.Part MultipartBody.Part HobliCode,
                                                 @retrofit2.http.Part MultipartBody.Part VillageCode,
                                                 @retrofit2.http.Part MultipartBody.Part HamletCode,
                                                 @retrofit2.http.Part MultipartBody.Part PropertyNo,
                                                 @retrofit2.http.Part MultipartBody.Part IS_DRAFT_COPY_ISSUED,
                                                 @retrofit2.http.Part MultipartBody.Part imageName,
                                                 @retrofit2.http.Part MultipartBody.Part todayDate,
                                                 @retrofit2.http.Part MultipartBody.Part s2,
                                                 @retrofit2.http.Part MultipartBody.Part lat,
                                                 @retrofit2.http.Part MultipartBody.Part lon,
                                                 @retrofit2.http.Part MultipartBody.Part mobile);
}
