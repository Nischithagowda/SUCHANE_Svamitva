package com.bmc.suchane_svamitva.api;

import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoRequest;
import com.bmc.suchane_svamitva.model.AddressCodeNoticeNoResponse;
import com.bmc.suchane_svamitva.model.DistrictRequest;
import com.bmc.suchane_svamitva.model.DistrictResponse;
import com.bmc.suchane_svamitva.model.FnSVM_UploadDocumentRequest;
import com.bmc.suchane_svamitva.model.FnSVM_UploadDocumentResponse;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsRequest;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsResponse;
import com.bmc.suchane_svamitva.model.HobliRequest;
import com.bmc.suchane_svamitva.model.HobliResponse;
import com.bmc.suchane_svamitva.model.MultipartImageResponse;
import com.bmc.suchane_svamitva.model.SMS_Request;
import com.bmc.suchane_svamitva.model.SMS_Response;
import com.bmc.suchane_svamitva.model.TalukRequest;
import com.bmc.suchane_svamitva.model.TalukResponse;
import com.bmc.suchane_svamitva.model.TokenRes;
import com.bmc.suchane_svamitva.model.ValidateOtpRequest;
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
            @Body SMS_Request sms_request
            );

    @POST("api/values/FnValidateOTP/")
    Observable<SMS_Response> FnValidateOTP(
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

    @POST("api/values/FnSVM_UploadDocument/")
    Observable<FnSVM_UploadDocumentResponse> FnSVM_UploadDocument(
            @Header("Authorization") String accessToken,
            @Body FnSVM_UploadDocumentRequest fnSVM_uploadDocumentRequest
    );

    @POST("api/values/FnSvmInsertNoticeDetails/")
    Observable<FnSvmInsertNoticeDetailsResponse> FnSvmInsertNoticeDetails(
            @Header("Authorization") String accessToken,
            @Body FnSvmInsertNoticeDetailsRequest svmInsertNoticeDetailsRequest
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
