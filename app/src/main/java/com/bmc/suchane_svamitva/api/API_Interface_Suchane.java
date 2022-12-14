package com.bmc.suchane_svamitva.api;

import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoRequest;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoResponse;
import com.bmc.suchane_svamitva.model.DistrictRequest;
import com.bmc.suchane_svamitva.model.DistrictResponse;
import com.bmc.suchane_svamitva.model.FnCheckUserLoggedInRequest;
import com.bmc.suchane_svamitva.model.FnGetApprovedDPRResponse;
import com.bmc.suchane_svamitva.model.FnGetPendingDPRRequest;
import com.bmc.suchane_svamitva.model.FnGetPendingDPRResponse;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsRequest;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsResponse;
import com.bmc.suchane_svamitva.model.FnUpdateDRPServedRequest;
import com.bmc.suchane_svamitva.model.FnUpdateDRPServedResponse;
import com.bmc.suchane_svamitva.model.HobliRequest;
import com.bmc.suchane_svamitva.model.HobliResponse;
import com.bmc.suchane_svamitva.model.LogoutRequest;
import com.bmc.suchane_svamitva.model.LogoutResponse;
import com.bmc.suchane_svamitva.model.MultipartImageResponse;
import com.bmc.suchane_svamitva.model.SMS_Request;
import com.bmc.suchane_svamitva.model.SMS_Request_Login;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.SMS_Response_Public;
import com.bmc.suchane_svamitva.model.TalukRequest;
import com.bmc.suchane_svamitva.model.TalukResponse;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.ValidateOtpRequest;
import com.bmc.suchane_svamitva.model.VersionRequest;
import com.bmc.suchane_svamitva.model.VersionResponse;
import com.bmc.suchane_svamitva.model.VillageRequest;
import com.bmc.suchane_svamitva.model.VillageResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

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

    @POST("api/values/FnSendOTP/")
    Observable<SMS_Response> FnSendOTP(
            @Header("Authorization") String accessToken,
            @Body SMS_Request_Login sms_request
            );

    @POST("api/Values/FnSendOTP_Public")
    Observable<SMS_Response_Public> FnSendOTP_Public(
            @Header("Authorization") String accessToken,
            @Body SMS_Request sms_request
            );

    @POST("api/values/FnLogout/")
    Observable<LogoutResponse> FnLogout(
            @Header("Authorization") String accessToken,
            @Body LogoutRequest logoutRequest
            );

    @POST("api/values/FnGetVersion/")
    Observable<VersionResponse> FnGetVersion(
            @Header("Authorization") String accessToken,
            @Body VersionRequest versionRequest
            );

    @POST("api/values/FnCheckUserLoggedIn/")
    Observable<LogoutResponse> FnCheckUserLoggedIn(
            @Header("Authorization") String accessToken,
            @Body FnCheckUserLoggedInRequest sms_request_login
            );

    @POST("api/values/FnValidateOTP/")
    Observable<SMS_Response> FnValidateOTP(
            @Header("Authorization") String accessToken,
            @Body ValidateOtpRequest validateOtpRequest
            );

    @POST("api/values/FnValidateOTP_Public/")
    Observable<SMS_Response_Public> FnValidateOTP_Public(
            @Header("Authorization") String accessToken,
            @Body ValidateOtpRequest validateOtpRequest
            );

    @POST("api/values/FnGetDistrict/")
    Observable<DistrictResponse> FnGetDistrict(
            @Header("Authorization") String accessToken,
            @Body DistrictRequest districtRequest
    );

    @POST("api/values/FnGetTaluk/")
    Observable<TalukResponse> FnGetTaluk(
            @Header("Authorization") String accessToken,
            @Body TalukRequest talukRequest
    );

    @POST("api/values/FnGetHobli/")
    Observable<HobliResponse> FnGetHobli(
            @Header("Authorization") String accessToken,
            @Body HobliRequest hobliRequest
    );

    @POST("api/values/FnGetVillage/")
    Observable<VillageResponse> FnGetVillage(
            @Header("Authorization") String accessToken,
            @Body VillageRequest hobliRequest
    );

    @POST("api/values/FnGenerateAddressCode_NoticeNo/")
    Observable<AddressCodeNoticeNoResponse> FnGenerateAddressCode_NoticeNo(
            @Header("Authorization") String accessToken,
            @Body AddressCodeNoticeNoRequest addressCodeNoticeNoRequest
    );

    @POST("api/values/FnSvmInsertNoticeDetails/")
    Observable<FnSvmInsertNoticeDetailsResponse> FnSvmInsertNoticeDetails(
            @Header("Authorization") String accessToken,
            @Body FnSvmInsertNoticeDetailsRequest svmInsertNoticeDetailsRequest
    );

    @POST("api/values/FnGetPendingDPR/")
    Observable<FnGetPendingDPRResponse> FnGetPendingDPR(
            @Header("Authorization") String accessToken,
            @Body FnGetPendingDPRRequest fnGetPendingDPRRequest
    );

    @POST("api/values/FnGetApprovedDPR/")
    Observable<FnGetApprovedDPRResponse> FnGetApprovedDPR(
            @Header("Authorization") String accessToken,
            @Body FnGetPendingDPRRequest fnGetPendingDPRRequest
    );

    @POST("api/values/FnUpdateDRPServed/")
    Observable<FnUpdateDRPServedResponse> FnUpdateDRPServed(
            @Header("Authorization") String accessToken,
            @Body FnUpdateDRPServedRequest fnUpdateDRPServedRequest
    );

    @Multipart
    @POST("api/values/FnSVM_UploadDocument")
    Observable<MultipartImageResponse> FnSVM_UploadDocument(@Header("Authorization") String accessToken,
                                                 @retrofit2.http.Part MultipartBody.Part NTC_PROPERTYCODE,
                                                 @retrofit2.http.Part MultipartBody.Part NOTICE_NO,
                                                 @retrofit2.http.Part MultipartBody.Part ADDRESS_CODE,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_TYPE_ID,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_NAME,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_PATH,
                                                 @retrofit2.http.Part MultipartBody.Part USER_ID,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_TIMESTAMP,
                                                 @retrofit2.http.Part MultipartBody.Part file);

    @Multipart
    @POST("api/values/FnSVM_UploadDocument_DPR")
    Observable<MultipartImageResponse> FnSVM_UploadDocument_DPR(@Header("Authorization") String accessToken,
                                                 @retrofit2.http.Part MultipartBody.Part NTC_PROPERTYCODE,
                                                 @retrofit2.http.Part MultipartBody.Part NOTICE_NO,
                                                 @retrofit2.http.Part MultipartBody.Part ADDRESS_CODE,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_TYPE_ID,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_NAME,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_PATH,
                                                 @retrofit2.http.Part MultipartBody.Part USER_ID,
                                                 @retrofit2.http.Part MultipartBody.Part DOC_TIMESTAMP,
                                                 @retrofit2.http.Part MultipartBody.Part file);
}
