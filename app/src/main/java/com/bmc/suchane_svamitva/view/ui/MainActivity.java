package com.bmc.suchane_svamitva.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.databinding.ActivityMainBinding;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsRequest;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsResponse;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.MultipartImageResponse;
import com.bmc.suchane_svamitva.model.NoticeDetailsTbl;
import com.bmc.suchane_svamitva.model.VillageResponse;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.MainActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view_model.MainActivityViewModel;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    MainActivityInterface mainActivityInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mainActivityInterface = new MainActivityCallback(this);
        MainActivityViewModel viewModel = new MainActivityViewModel(mainActivityInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.launcher_menu, menu);
        MenuItem item = menu.findItem(R.id.version);
        item.setTitle("Version : " + BuildConfig.VERSION_NAME);
        MenuBuilder menuBuilder = (MenuBuilder) menu;
        menuBuilder.setOptionalIconsVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.send){
            if (isNetworkAvailable()) {
                sendNotSentData();
            } else {
                Toast.makeText(this, getString(R.string.please_switch_on_the_internet), Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if(item.getItemId()==R.id.logout){
            if (isNetworkAvailable()) {
                logout();
            } else {
                Toast.makeText(this, getString(R.string.please_switch_on_the_internet), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure, Do you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    mainActivityInterface.logoutFromServer();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.setTitle("Confirmation");
        alert.show();

    }

    private void sendNotSentData() {
        Observable
                .fromCallable(() -> DBConnection.getConnection(this)
                        .getDataBaseDao()
                        .getNoticeDetailsTblValues())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (result.size() == 0) {
                        Toast.makeText(this, "No Data Pending", Toast.LENGTH_LONG).show();
                    } else {
                        sendNotSentDataToServer(result);
                    }


                }, error -> {
                    error.printStackTrace();
                });
    }

    private void sendNotSentDataToServer(List<NoticeDetailsTbl> noticeDetailsTblList) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Property data Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = this.getSharedPreferences(this.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(this.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(this.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        Retrofit client = APIClient_Suchane.getClientWithoutToken(getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client.create(API_Interface_Suchane.class);
        for (int i = 0; i < noticeDetailsTblList.size(); i++) {
            int finalI = i;

            FnSvmInsertNoticeDetailsRequest svmInsertNoticeDetailsRequest = new FnSvmInsertNoticeDetailsRequest();
            svmInsertNoticeDetailsRequest.setNTC_VID(noticeDetailsTblList.get(i).getNTC_VID());
            svmInsertNoticeDetailsRequest.setNTC_DIST_CODE(noticeDetailsTblList.get(i).getNTC_DIST_CODE());
            svmInsertNoticeDetailsRequest.setNTC_TLK_TWN_CODE(noticeDetailsTblList.get(i).getNTC_TLK_TWN_CODE());
            svmInsertNoticeDetailsRequest.setNTC_WRD_VLG_CODE(noticeDetailsTblList.get(i).getNTC_WRD_VLG_CODE());
            svmInsertNoticeDetailsRequest.setNTC_AREA_TYPE(noticeDetailsTblList.get(i).getNTC_AREA_TYPE());
            svmInsertNoticeDetailsRequest.setNTC_NOTICE_NO(noticeDetailsTblList.get(i).getNTC_NOTICE_NO());
            svmInsertNoticeDetailsRequest.setNTC_ADD_CODE(noticeDetailsTblList.get(i).getNTC_ADD_CODE());
            svmInsertNoticeDetailsRequest.setNTC_ADD_DOORNO(noticeDetailsTblList.get(i).getNTC_ADD_DOORNO());
            svmInsertNoticeDetailsRequest.setNTC_BUILDING(noticeDetailsTblList.get(i).getNTC_BUILDING());
            svmInsertNoticeDetailsRequest.setNTC_STREET_AREA(noticeDetailsTblList.get(i).getNTC_STREET_AREA());
            svmInsertNoticeDetailsRequest.setNTC_LANDMARK(noticeDetailsTblList.get(i).getNTC_LANDMARK());
            svmInsertNoticeDetailsRequest.setNTC_WARD_VILLAGE(noticeDetailsTblList.get(i).getNTC_WARD_VILLAGE());
            svmInsertNoticeDetailsRequest.setNTC_CTY_TLK(noticeDetailsTblList.get(i).getNTC_CTY_TLK());
            svmInsertNoticeDetailsRequest.setNTC_DISTRICT(noticeDetailsTblList.get(i).getNTC_DISTRICT());
            svmInsertNoticeDetailsRequest.setNTC_LAT(noticeDetailsTblList.get(i).getNTC_LAT());
            svmInsertNoticeDetailsRequest.setNTC_LONG(noticeDetailsTblList.get(i).getNTC_LONG());
            svmInsertNoticeDetailsRequest.setNTC_ACCURACY(noticeDetailsTblList.get(i).getNTC_ACCURACY());
            svmInsertNoticeDetailsRequest.setNTC_CBY(noticeDetailsTblList.get(i).getNTC_CBY());
            Observable<FnSvmInsertNoticeDetailsResponse> responseObservable = apiService1.FnSvmInsertNoticeDetails(accessToken, svmInsertNoticeDetailsRequest);
            responseObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> {
                        if (result.getRESPONSE_CODE().contains("200")) {
                            Observable
                                    .fromCallable(() -> DBConnection.getConnection(this)
                                            .getDataBaseDao()
                                            .deleteNoticeDetails(noticeDetailsTblList.get(finalI).getNTC_NOTICE_NO(),
                                                    noticeDetailsTblList.get(finalI).getNTC_ADD_CODE(), noticeDetailsTblList.get(finalI).getNTC_VID()))
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(result2 ->
                                    {
                                    }, error -> error.printStackTrace());
                            if (finalI == noticeDetailsTblList.size() - 1) {
                                dialog.dismiss();
                                sendImageToServer();
                            }
                        }

                    }, (error) -> {
                        dialog.dismiss();
                        if (finalI == noticeDetailsTblList.size() - 1) {
                            dialog.dismiss();
                        }
                        String errMsg = error.getLocalizedMessage();
                        if (errMsg.contains("401")) {
                            new Constant(this).getRefreshToken();
                        } else {
                            Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(this, "Property Details are Not Sent.", Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }

    private void sendImageToServer() {
        Observable
                .fromCallable(() -> DBConnection.getConnection(this)
                        .getDataBaseDao()
                        .getImageTblValues(true))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (result.size() == 0) {
                        Toast.makeText(this, "Property Details are sent..", Toast.LENGTH_LONG).show();
                    } else {
                        sendImageToServer(result);
                    }

                }, error -> {
                    error.printStackTrace();
                });
    }

    public void sendImageToServer(List<Image> images){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = this.getSharedPreferences(this.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(this.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(this.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        Retrofit client = APIClient_Suchane.getClientWithoutToken(getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client.create(API_Interface_Suchane.class);

        for (int i = 0; i < images.size(); i++) {
            int finalI = i;
            Image image = images.get(i);
            File file = new File(image.getDOC_PATH());
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part para9 = MultipartBody.Part.createFormData("File", images.get(i).getDOC_NAME(), requestBody);
            MultipartBody.Part para1 = MultipartBody.Part.createFormData("NTC_PROPERTYCODE",images.get(i).getVIRTUAL_ID());
            MultipartBody.Part para2 = MultipartBody.Part.createFormData("NOTICE_NO", images.get(i).getNOTICE_NO());
            MultipartBody.Part para3 = MultipartBody.Part.createFormData("ADDRESS_CODE", images.get(i).getADDRESS_CODE());
            MultipartBody.Part para4 = MultipartBody.Part.createFormData("DOC_TYPE_ID", images.get(i).getDOC_TYPE_ID());
            MultipartBody.Part para5 = MultipartBody.Part.createFormData("DOC_NAME", images.get(i).getDOC_NAME());
            MultipartBody.Part para6 = MultipartBody.Part.createFormData("DOC_PATH", images.get(i).getDOC_PATH());
            MultipartBody.Part para7 = MultipartBody.Part.createFormData("USER_ID", images.get(i).getUSER_ID());
            MultipartBody.Part para8 = MultipartBody.Part.createFormData("DOC_TIMESTAMP", images.get(i).getDOC_TIMESTAMP());
            Observable<MultipartImageResponse> imageResponseObservable = apiService1.FnSVM_UploadDocument(accessToken, para1, para2, para3, para4, para5, para6, para7, para8, para9);
            imageResponseObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> {
                        dialog.dismiss();
                        if (result.getRESPONSE_CODE().contains("200")) {
                            Observable
                                    .fromCallable(() -> DBConnection.getConnection(this)
                                            .getDataBaseDao()
                                            .deleteImageDetails(images.get(finalI).getNOTICE_NO(), images.get(finalI).getADDRESS_CODE()))
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(result1 ->
                                    {
                                    } , error ->{
                                        error.printStackTrace();
                                    });
                        } else {
                            Toast.makeText(this, "Images Are Not Sent.", Toast.LENGTH_LONG).show();
                        }
                        if (finalI == images.size() - 1) {
                            dialog.dismiss();
                            Toast.makeText(this, "Images are sent..", Toast.LENGTH_LONG).show();

                        }
                    }, (error) -> {
                        error.printStackTrace();
                        dialog.dismiss();
                        String errMsg = error.getLocalizedMessage();
                        if (errMsg.contains("401")) {
                            new Constant(this).getRefreshToken();
                        } else {
                            Toast.makeText(this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            Toast.makeText(this, "Images Are Not Sent.", Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}