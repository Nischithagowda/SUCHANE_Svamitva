package com.bmc.suchane_svamitva.view.callbacks;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static com.bmc.suchane_svamitva.utils.Constant.IMAGE_CAPTURE_REQ;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.api.APIClient_Suchane;
import com.bmc.suchane_svamitva.api.API_Interface_Suchane;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsRequest;
import com.bmc.suchane_svamitva.model.FnSvmInsertNoticeDetailsResponse;
import com.bmc.suchane_svamitva.model.FnUpdateDRPServedRequest;
import com.bmc.suchane_svamitva.model.FnUpdateDRPServedResponse;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.MultipartImageResponse;
import com.bmc.suchane_svamitva.model.NoticeDetailsTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl_Updated;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_FinalActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_FinalActivity;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_FinalActivityViewModel;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class DPR_FPR_FinalActivityCallback implements DPR_FPR_FinalActivityInterface {
    DPR_FPR_FinalActivity activity;

    public DPR_FPR_FinalActivityCallback(DPR_FPR_FinalActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadData(DPR_FPR_FinalActivityViewModel viewModel){
        try {
            Intent intent = activity.getIntent();
            viewModel.districtCode.set(intent.getStringExtra("districtCode"));
            viewModel.districtName.set(intent.getStringExtra("districtName"));
            viewModel.talukCode.set(intent.getStringExtra("talukCode"));
            viewModel.talukName.set(intent.getStringExtra("talukName"));
            viewModel.hobliCode.set(intent.getStringExtra("hobliCode"));
            viewModel.hobliName.set(intent.getStringExtra("hobliName"));
            viewModel.villageCode.set(intent.getStringExtra("villageCode"));
            viewModel.LGD_VILLAGE_CODE.set(intent.getStringExtra("LGD_VILLAGE_CODE"));
            viewModel.villageName.set(intent.getStringExtra("villageName"));
            viewModel.noticeNumber.set(intent.getStringExtra("NoticeNo"));

            DecimalFormat df = new DecimalFormat("#.0000000");
            Observable
                    .fromCallable(() -> DBConnection.getConnection(activity)
                            .getDataBaseDao()
                            .getPendingDPRDetailsByNoticeNo(viewModel.noticeNumber.get()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        if (result.size() > 0) {
                            double dLat = Double.parseDouble(result.get(0).getNTC_LAT());
                            double dLong = Double.parseDouble(result.get(0).getNTC_LONG());
                            viewModel.Lat.set(""+df.format(dLat));
                            viewModel.Long.set(""+df.format(dLong));
                            viewModel.addressCode.set(result.get(0).getDPRFNL_ADD_CODE());
                            viewModel.ownerName.set(result.get(0).getDPROWNFNL_OWNERNAME());
                            viewModel.mobileNumber.set(result.get(0).getDPROWNFNL_MOBILENO());
                            viewModel.propertyNo.set(result.get(0).getDPRFNL_PROPERTYNO());
                            viewModel.doorNo.set(result.get(0).getNTC_ADD_DOORNO());
                            viewModel.doorNo_UPD.set(result.get(0).getNTC_ADD_DOORNO());
                            viewModel.building.set(result.get(0).getNTC_BUILDING());
                            viewModel.building_UPD.set(result.get(0).getNTC_BUILDING());
                            viewModel.street.set(result.get(0).getNTC_STREET_AREA());
                            viewModel.street_UPD.set(result.get(0).getNTC_STREET_AREA());
                            viewModel.landmark.set(result.get(0).getNTC_LANDMARK());
                            viewModel.landmark_UPD.set(result.get(0).getNTC_LANDMARK());
                        }
                    }, error -> error.printStackTrace());
            //getUserDistrict(viewModel);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void goHome() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure, Do you want to Discard the Changes?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    activity.onBackPressed();
                    activity.finish();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.setTitle("Confirmation");
        alert.show();

    }

    @Override
    public void getUserDistrict(DPR_FPR_FinalActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getUserDistrict())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.districtNameList.clear();
                    if (result.size()>0) {
                        viewModel.districtNameList.addAll(result);
                    } else {
                        District district = new District();
                        district.setDISTRICT_CODE("0");
                        district.setDISTRICT_NAME("No District");
                        viewModel.districtNameList.add(district);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getUserTaluk(DPR_FPR_FinalActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getUserTaluk(viewModel.districtCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.talukNameList.clear();
                    if (result.size()>0) {
                        viewModel.talukNameList.addAll(result);

                    } else {
                        Taluka taluka = new Taluka();
                        taluka.setDISTRICT_CODE("0");
                        taluka.setTALUKA_CODE("0");
                        taluka.setTALUKA_NAME("No Taluk");
                        viewModel.talukNameList.add(taluka);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getHobli(DPR_FPR_FinalActivityViewModel viewModel){
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getHobliDetails(viewModel.districtCode.get(), viewModel.talukCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.hobliNameList.clear();
                    if (result.size()>0) {
                        viewModel.hobliNameList.addAll(result);
                    } else {
                        Hobli hobli = new Hobli();
                        hobli.setDISTRICT_CODE("0");
                        hobli.setTALUKA_CODE("0");
                        hobli.setHOBLI_CODE("0");
                        hobli.setHOBLI_NAME("No Hobli");
                        viewModel.hobliNameList.add(hobli);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void getVillage(DPR_FPR_FinalActivityViewModel viewModel){

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getVillageDetails(viewModel.districtCode.get(), viewModel.talukCode.get(), viewModel.hobliCode.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    viewModel.villageNameList.clear();
                    if (result.size()>0) {
                        viewModel.villageNameList.addAll(result);
                    } else {
                        Village village = new Village();
                        village.setDISTRICT_CODE("0");
                        village.setTALUK_CODE("0");
                        village.setHOBLI_CODE("0");
                        village.setVILLAGE_CODE("0");
                        village.setVILLAGE_NAME("No Village");
                        viewModel.villageNameList.add(village);
                    }
                }, error -> error.printStackTrace());
    }

    @Override
    public void capturePropertyOrLandPhoto(DPR_FPR_FinalActivityViewModel viewModel) {
        if (checkPermission_API30()){
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, IMAGE_CAPTURE_REQ);
        } else {
            takePicturePropertyOrLand(viewModel);
        }

    }

    @Override
    public void captureServingDPRPhoto(DPR_FPR_FinalActivityViewModel viewModel) {
        if (checkPermission_API30()){
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, IMAGE_CAPTURE_REQ);
        } else {
            takePictureServingNotice(viewModel);
        }

    }

    public void takePicturePropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createMediaFilePropertyOrLand(viewModel);
            } catch (IOException ex) {
                Log.d("LOG_TAG", "Error occurred while creating the file");
            }
            if (photoFile != null) {

                Uri capturedUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID +".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedUri);
                activity.startActivityForResult(takePictureIntent, Constant.CAMERA_REQUEST_PropertyOrLand);

            }
        }
    }

    private File createMediaFilePropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        String folder_main = "PropertyOrLandPictures";

        File mydir = activity.getDir( folder_main, MODE_PRIVATE); //Creating an internal dir;
        File file = File.createTempFile(fileName, ".jpg", mydir); //Getting a file within the dir.
        // Get the path of the file created
        viewModel.mCurrentPropertyOrLandPhotoPath.set(file.getAbsolutePath());
        return file;
    }

    public void takePictureServingNotice(DPR_FPR_FinalActivityViewModel viewModel) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createMediaFileServingNotice(viewModel);
            } catch (IOException ex) {
                Log.d("LOG_TAG", "Error occurred while creating the file");
            }
            if (photoFile != null) {

                Uri capturedUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID +".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedUri);
                activity.startActivityForResult(takePictureIntent, Constant.CAMERA_REQUEST_ServingNotice);


            }
        }
    }

    private File createMediaFileServingNotice(DPR_FPR_FinalActivityViewModel viewModel) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        String folder_main = "ServingNoticePictures";

        File mydir = activity.getDir( folder_main, MODE_PRIVATE); //Creating an internal dir;
        File file = File.createTempFile(fileName, ".jpg", mydir); //Getting a file within the dir.
        // Get the path of the file created
        viewModel.mCurrentServingDPRPhotoPath.set(file.getAbsolutePath());
        return file;
    }

    private boolean checkPermission_API30() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void imageProcessPropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel) {
        try {
            Bitmap bitmap = scaleDownAndRotatePic(viewModel.mCurrentPropertyOrLandPhotoPath.get());
            viewModel.imageBitMapPropertyOrLand.set(bitmap);
            viewModel.isImageVisible.set(true);
            ImageCompressionAsyncTaskPropertyOrLand imagetask = new ImageCompressionAsyncTaskPropertyOrLand(activity, viewModel);
            String folder_main = "PropertyOrLandPictures";
            File myDir = activity.getDir( folder_main, MODE_PRIVATE);
            imagetask.execute(viewModel.mCurrentPropertyOrLandPhotoPath.get(), myDir + "/" + Constant.APP_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imageProcessServingDPR(DPR_FPR_FinalActivityViewModel viewModel) {
        try {
            Bitmap bitmap = scaleDownAndRotatePic(viewModel.mCurrentServingDPRPhotoPath.get());
            viewModel.imageBitMapServingDPR.set(bitmap);
            viewModel.isImageVisible.set(true);
            ImageCompressionAsyncTaskServingNotice imagetask = new ImageCompressionAsyncTaskServingNotice(activity, viewModel);
            String folder_main = "ServingNoticePictures";
            File myDir = activity.getDir( folder_main, MODE_PRIVATE);
            imagetask.execute(viewModel.mCurrentServingDPRPhotoPath.get(), myDir + "/" + Constant.APP_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap scaleDownAndRotatePic(String path) {//you can provide file path here
        int orientation;
        try {
            if (path == null) {
                return null;
            }
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 0;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale++;
            }
            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bm = BitmapFactory.decodeFile(path, o2);
            Bitmap bitmap = bm;

            ExifInterface exif = new ExifInterface(path);

            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

            Log.e("ExifInterface .........", "rotation =" + orientation);
            Log.e("orientation", "" + orientation);

            Matrix m = new Matrix();
            if ((orientation == ExifInterface.ORIENTATION_ROTATE_180)) {
                m.postRotate(180);
                Log.e("in orientation", "" + orientation);
                bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
                return bitmap;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                m.postRotate(90);
                Log.e("in orientation", "" + orientation);
                bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
                return bitmap;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                m.postRotate(270);
                Log.e("in orientation", "" + orientation);
                bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
                return bitmap;
            }
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }


    class ImageCompressionAsyncTaskPropertyOrLand extends AsyncTask<String, Void, String> {

        Context mContext;
        DPR_FPR_FinalActivityViewModel viewModel;


        public ImageCompressionAsyncTaskPropertyOrLand(Context context, DPR_FPR_FinalActivityViewModel viewModel) {
            mContext = context;
            this.viewModel = viewModel;

        }

        @Override
        protected String doInBackground(String... params) {

            return SiliCompressor.with(mContext).compress(params[0], new File(params[1]));

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onPostExecute(String s) {

            File imageFile = new File(s);
            genrateByteDataUsingFilePropertyOrLand(imageFile, viewModel);
            Uri compressStartPointUri = Uri.fromFile(imageFile);
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), compressStartPointUri);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void genrateByteDataUsingFilePropertyOrLand(File imageFile, DPR_FPR_FinalActivityViewModel viewModel) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmapObject = BitmapFactory.decodeFile(String.valueOf(imageFile));
        bitmapObject.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        viewModel.imageDataPropertyOrLand.set(imageBytes);
        viewModel.imageFilePropertyOrLand.set(imageFile);
    }


    class ImageCompressionAsyncTaskServingNotice extends AsyncTask<String, Void, String> {

        Context mContext;
        DPR_FPR_FinalActivityViewModel viewModel;


        public ImageCompressionAsyncTaskServingNotice(Context context, DPR_FPR_FinalActivityViewModel viewModel) {
            mContext = context;
            this.viewModel = viewModel;

        }

        @Override
        protected String doInBackground(String... params) {

            return SiliCompressor.with(mContext).compress(params[0], new File(params[1]));

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onPostExecute(String s) {

            File imageFile = new File(s);
            genrateByteDataUsingFileServingNotice(imageFile, viewModel);
            Uri compressStartPointUri = Uri.fromFile(imageFile);
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), compressStartPointUri);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void genrateByteDataUsingFileServingNotice(File imageFile, DPR_FPR_FinalActivityViewModel viewModel) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmapObject = BitmapFactory.decodeFile(String.valueOf(imageFile));
        bitmapObject.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        viewModel.imageDataServingDPR.set(imageBytes);
        viewModel.imageFileServingDPR.set(imageFile);
    }

    @Override
    public void showImagePropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel){
        Dialog settingsDialog = new Dialog(activity);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.image_popup);
        ImageView imageView = settingsDialog.findViewById(R.id.showImg);
        imageView.setImageBitmap(viewModel.imageBitMapPropertyOrLand.get());
        settingsDialog.show();
    }

    @Override
    public void showImageServingDPR(DPR_FPR_FinalActivityViewModel viewModel){
        Dialog settingsDialog = new Dialog(activity);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.image_popup);
        ImageView imageView = settingsDialog.findViewById(R.id.showImg);
        imageView.setImageBitmap(viewModel.imageBitMapServingDPR.get());
        settingsDialog.show();
    }

    @Override
    public void saveAndNext(DPR_FPR_FinalActivityViewModel viewModel) {
        if (viewModel.isChangesDone.get()) {
            updateNoticeData(viewModel);
        } else {
            updatePendingDPRData(viewModel);
        }
    }

    private void updatePendingDPRData(DPR_FPR_FinalActivityViewModel viewModel) {
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .isPendingDPRUpdatedAvailable(viewModel.noticeNumber.get(), viewModel.propertyNo.get()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (result){
                        Observable
                                .fromCallable(() -> DBConnection.getConnection(activity)
                                        .getDataBaseDao()
                                        .deletePendingDPRUpdatedDetails(viewModel.noticeNumber.get(), viewModel.propertyNo.get()))
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result1 ->
                                {
                                    InsertPendingDPRUpdatedDetailsLocally(viewModel);
                                }, error -> {
                                    error.printStackTrace();
                                });
                    } else {
                        InsertPendingDPRUpdatedDetailsLocally(viewModel);
                    }
                }, error -> {
                    error.printStackTrace();
                });
    }

    private void updateNoticeData(DPR_FPR_FinalActivityViewModel viewModel) {
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .isNoticeDetailsAvailable(viewModel.noticeNumber.get(), viewModel.addressCode.get(), viewModel.propertyNo.get()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (result){
                        Observable
                                .fromCallable(() -> DBConnection.getConnection(activity)
                                        .getDataBaseDao()
                                        .deleteNoticeDetails(viewModel.noticeNumber.get(), viewModel.addressCode.get(), viewModel.propertyNo.get()))
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result1 ->
                                {
                                    InsertNoticeDetailsLocally(viewModel);
                                }, error -> {
                                    error.printStackTrace();
                                });
                    } else {
                        InsertNoticeDetailsLocally(viewModel);
                    }
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void InsertPendingDPRUpdatedDetailsLocally(DPR_FPR_FinalActivityViewModel viewModel){

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        PendingDPRTbl_Updated pendingDPRTbl_updated = new PendingDPRTbl_Updated();
        pendingDPRTbl_updated.setNOTICE_NO(viewModel.noticeNumber.get());
        pendingDPRTbl_updated.setADDRESS_CODE(viewModel.addressCode.get());
        pendingDPRTbl_updated.setPROPERTY_CODE(viewModel.propertyNo.get());
        pendingDPRTbl_updated.setUSER_ID(mobNum);
        pendingDPRTbl_updated.setIsChangesDone(viewModel.isChangesDone.get()?1:0);
        pendingDPRTbl_updated.setUPD_FLAG(1);

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertPendingDPRUpdatedDetails(pendingDPRTbl_updated))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (isNetworkAvailable()) {
                        SendPendingDPRUpdatedDetailsToServer(viewModel);
                    } else {
                        savePropertyOrLandImageLocal(viewModel);
                        Toast.makeText(activity, activity.getString(R.string.please_switch_on_the_internet), Toast.LENGTH_LONG).show();
                    }
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void InsertNoticeDetailsLocally(DPR_FPR_FinalActivityViewModel viewModel) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        NoticeDetailsTbl noticeDetailsTbl = new NoticeDetailsTbl();
        noticeDetailsTbl.setNTC_VID(viewModel.propertyNo.get());
        noticeDetailsTbl.setNTC_DIST_CODE(viewModel.districtCode.get());
        noticeDetailsTbl.setNTC_TLK_TWN_CODE(viewModel.talukCode.get());
        noticeDetailsTbl.setNTC_WRD_VLG_CODE(viewModel.villageCode.get());
        noticeDetailsTbl.setNTC_LGD_VLG_CODE(viewModel.LGD_VILLAGE_CODE.get());
        noticeDetailsTbl.setNTC_AREA_TYPE("2");
        noticeDetailsTbl.setNTC_NOTICE_NO(viewModel.noticeNumber.get());
        noticeDetailsTbl.setNTC_ADD_CODE(viewModel.addressCode.get());
        noticeDetailsTbl.setNTC_OWNER_NAME(viewModel.ownerName.get());
        noticeDetailsTbl.setNTC_OWNER_MOBILE_NO(viewModel.mobileNumber.get());
        noticeDetailsTbl.setNTC_ADD_DOORNO(viewModel.doorNo.get());
        noticeDetailsTbl.setNTC_BUILDING(viewModel.building.get());
        noticeDetailsTbl.setNTC_STREET_AREA(viewModel.street.get());
        noticeDetailsTbl.setNTC_LANDMARK(viewModel.landmark.get());
        noticeDetailsTbl.setNTC_WARD_VILLAGE(viewModel.villageName.get());
        noticeDetailsTbl.setNTC_CTY_TLK(viewModel.talukName.get());
        noticeDetailsTbl.setNTC_DISTRICT(viewModel.districtName.get());
        noticeDetailsTbl.setNTC_LAT(viewModel.Lat.get());
        noticeDetailsTbl.setNTC_LONG(viewModel.Long.get());
        noticeDetailsTbl.setNTC_ACCURACY("0");
        noticeDetailsTbl.setNTC_CBY(mobNum);

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertNoticeDetailsDetails(noticeDetailsTbl))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    if (isNetworkAvailable()) {
                        SendNoticeDetailsToServer(viewModel);
                    } else {
                        savePropertyOrLandImageLocal(viewModel);
                        Toast.makeText(activity, activity.getString(R.string.please_switch_on_the_internet), Toast.LENGTH_LONG).show();
                    }
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void SendPendingDPRUpdatedDetailsToServer(DPR_FPR_FinalActivityViewModel viewModel){

        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        FnUpdateDRPServedRequest fnUpdateDRPServedRequest = new FnUpdateDRPServedRequest();
        fnUpdateDRPServedRequest.setNOTICE_NO(viewModel.noticeNumber.get());
        fnUpdateDRPServedRequest.setADDRESS_CODE(viewModel.addressCode.get());
        fnUpdateDRPServedRequest.setPROPERTY_CODE(viewModel.propertyNo.get());
        fnUpdateDRPServedRequest.setUSER_ID(mobNum);

        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        Observable<FnUpdateDRPServedResponse> responseObservable = apiService1.FnUpdateDRPServed(accessToken, fnUpdateDRPServedRequest);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result1) -> {
                    dialog.dismiss();
                    if (result1.getRESPONSE_CODE().contains("200")) {
                        Observable
                                .fromCallable(() -> DBConnection.getConnection(activity)
                                        .getDataBaseDao()
                                        .deletePendingDPRUpdatedDetails(viewModel.noticeNumber.get(), viewModel.propertyNo.get()))
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result2 ->
                                {
                                    sendPropertyOrLandImageToServer(viewModel);
                                }, error -> {
                                    error.printStackTrace();
                                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                });
                    } else {
                        Toast.makeText(activity, "" + result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                    }
                }, (error) -> {
                    dialog.dismiss();
                    error.printStackTrace();
                    String errMsg = error.getLocalizedMessage();
                    if (errMsg.contains("401")) {
                        new Constant(activity).getRefreshToken();
                    } else {
                        Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void SendNoticeDetailsToServer(DPR_FPR_FinalActivityViewModel viewModel) {

        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        FnSvmInsertNoticeDetailsRequest svmInsertNoticeDetailsRequest = new FnSvmInsertNoticeDetailsRequest();
        svmInsertNoticeDetailsRequest.setNTC_VID(viewModel.propertyNo.get());
        svmInsertNoticeDetailsRequest.setNTC_DIST_CODE(viewModel.districtCode.get());
        svmInsertNoticeDetailsRequest.setNTC_TLK_TWN_CODE(viewModel.talukCode.get());
        svmInsertNoticeDetailsRequest.setNTC_WRD_VLG_CODE(viewModel.villageCode.get());
        svmInsertNoticeDetailsRequest.setNTC_LGD_VLG_CODE(viewModel.LGD_VILLAGE_CODE.get());
        svmInsertNoticeDetailsRequest.setNTC_AREA_TYPE("2");
        svmInsertNoticeDetailsRequest.setNTC_NOTICE_NO(viewModel.noticeNumber.get());
        svmInsertNoticeDetailsRequest.setNTC_ADD_CODE(viewModel.addressCode.get());
        svmInsertNoticeDetailsRequest.setNTC_OWNER_NAME(viewModel.ownerName.get());
        svmInsertNoticeDetailsRequest.setNTC_OWNER_MOBILE_NO(viewModel.mobileNumber.get());
        svmInsertNoticeDetailsRequest.setNTC_ADD_DOORNO(viewModel.doorNo.get());
        svmInsertNoticeDetailsRequest.setNTC_BUILDING(viewModel.building.get());
        svmInsertNoticeDetailsRequest.setNTC_STREET_AREA(viewModel.street.get());
        svmInsertNoticeDetailsRequest.setNTC_LANDMARK(viewModel.landmark.get());
        svmInsertNoticeDetailsRequest.setNTC_WARD_VILLAGE(viewModel.villageName.get());
        svmInsertNoticeDetailsRequest.setNTC_CTY_TLK(viewModel.talukName.get());
        svmInsertNoticeDetailsRequest.setNTC_DISTRICT(viewModel.districtName.get());
        svmInsertNoticeDetailsRequest.setNTC_LAT(viewModel.Lat.get());
        svmInsertNoticeDetailsRequest.setNTC_LONG(viewModel.Long.get());
        svmInsertNoticeDetailsRequest.setNTC_ACCURACY("0");
        svmInsertNoticeDetailsRequest.setNTC_CBY(mobNum);

        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        Observable<FnSvmInsertNoticeDetailsResponse> responseObservable = apiService1.FnSvmInsertNoticeDetails(accessToken, svmInsertNoticeDetailsRequest);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result1) -> {
                    dialog.dismiss();
                    if (result1.getRESPONSE_CODE().contains("200")) {
                        Observable
                                .fromCallable(() -> DBConnection.getConnection(activity)
                                        .getDataBaseDao()
                                        .deleteNoticeDetails(viewModel.noticeNumber.get(), viewModel.addressCode.get(), viewModel.propertyNo.get()))
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(result2 ->
                                {
                                    updatePendingDPRData(viewModel);
                                    //sendPropertyOrLandImageToServer(viewModel);
                                }, error -> {
                                    error.printStackTrace();
                                    Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                });
                    } else {
                        Toast.makeText(activity, "" + result1.getRESPONSE_MESSAGE(), Toast.LENGTH_SHORT).show();
                    }
                }, (error) -> {
                    dialog.dismiss();
                    error.printStackTrace();
                    String errMsg = error.getLocalizedMessage();
                    if (errMsg.contains("401")) {
                        new Constant(activity).getRefreshToken();
                    } else {
                        Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void savePropertyOrLandImageLocal(DPR_FPR_FinalActivityViewModel viewModel) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Date date = new Date();
        String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);

        Image image = new Image();
        image.setNTC_PROPERTYCODE(viewModel.propertyNo.get());
        image.setNOTICE_NO(viewModel.noticeNumber.get());
        image.setADDRESS_CODE(viewModel.addressCode.get());
        image.setDOC_TYPE_ID("1");
        image.setDOC_NAME(viewModel.imageFilePropertyOrLand.get().getName());
        image.setDOC_PATH(viewModel.imageFilePropertyOrLand.get().getAbsolutePath());
        image.setUSER_ID(mobNum);
        image.setDOC_TIMESTAMP(todayDate);
        image.setNotSent(true);
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertImage(image))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    saveServingDPRImageLocal(viewModel);
                }, error -> {
                    error.printStackTrace();
                });

    }

    private void saveServingDPRImageLocal(DPR_FPR_FinalActivityViewModel viewModel) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Date date = new Date();
        String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);

        Image image = new Image();
        image.setNTC_PROPERTYCODE(viewModel.propertyNo.get());
        image.setNOTICE_NO(viewModel.noticeNumber.get());
        image.setADDRESS_CODE(viewModel.addressCode.get());
        image.setDOC_TYPE_ID("2");
        image.setDOC_NAME(viewModel.imageFileServingDPR.get().getName());
        image.setDOC_PATH(viewModel.imageFileServingDPR.get().getAbsolutePath());
        image.setUSER_ID(mobNum);
        image.setDOC_TIMESTAMP(todayDate);
        image.setNotSent(true);
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertImage(image))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                {
                    activity.onBackPressed();
                    activity.finish();
                    Toast.makeText(activity, "Data Saved in Local", Toast.LENGTH_SHORT).show();
                }, error -> {
                    error.printStackTrace();
                });

    }

    public void sendPropertyOrLandImageToServer(DPR_FPR_FinalActivityViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Date date = new Date();
        String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);

        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), viewModel.imageFilePropertyOrLand.get());
        MultipartBody.Part para9 = MultipartBody.Part.createFormData("File", viewModel.imageFilePropertyOrLand.get().getName(), requestBody);
        MultipartBody.Part para1 = MultipartBody.Part.createFormData("NTC_PROPERTYCODE", viewModel.propertyNo.get());
        MultipartBody.Part para2 = MultipartBody.Part.createFormData("NOTICE_NO", viewModel.noticeNumber.get());
        MultipartBody.Part para3 = MultipartBody.Part.createFormData("ADDRESS_CODE", viewModel.addressCode.get());
        MultipartBody.Part para4 = MultipartBody.Part.createFormData("DOC_TYPE_ID", "1");
        MultipartBody.Part para5 = MultipartBody.Part.createFormData("DOC_NAME", viewModel.imageFilePropertyOrLand.get().getName());
        MultipartBody.Part para6 = MultipartBody.Part.createFormData("DOC_PATH", viewModel.imageFilePropertyOrLand.get().getAbsolutePath());
        MultipartBody.Part para7 = MultipartBody.Part.createFormData("USER_ID", mobNum);
        MultipartBody.Part para8 = MultipartBody.Part.createFormData("DOC_TIMESTAMP", todayDate);
        Observable<MultipartImageResponse> imageResponseObservable = apiService1.FnSVM_UploadDocument_DPR(accessToken, para1, para2, para3, para4, para5, para6, para7, para8, para9);
        imageResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    dialog.dismiss();
                    if (result.getRESPONSE_CODE().contains("200")) {
                        sendServingDPRImageToServer(viewModel);
                    } else {
                        Toast.makeText(activity, result.getRESPONSE_MESSAGE(), Toast.LENGTH_LONG).show();
                        savePropertyOrLandImageLocal(viewModel);
                    }
                }, (error) -> {
                    error.printStackTrace();
                    dialog.dismiss();
                    String errMsg = error.getLocalizedMessage();
                    if (errMsg.contains("401")) {
                        new Constant(activity).getRefreshToken();
                    } else {
                        Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void sendServingDPRImageToServer(DPR_FPR_FinalActivityViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Sending Wait ..");
        dialog.show();

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences(activity.getString(R.string.Auth), Context.MODE_PRIVATE);
        String token = sharedPreferences1.getString(activity.getString(R.string.token), null);
        String tokenType = sharedPreferences1.getString(activity.getString(R.string.token_type), null);
        String accessToken=tokenType+" "+token;

        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Date date = new Date();
        String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);

        Retrofit client1 = APIClient_Suchane.getClientWithoutToken(activity.getString(R.string.api_url));
        API_Interface_Suchane apiService1 = client1.create(API_Interface_Suchane.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), viewModel.imageFileServingDPR.get());
        MultipartBody.Part para9 = MultipartBody.Part.createFormData("File", viewModel.imageFileServingDPR.get().getName(), requestBody);
        MultipartBody.Part para1 = MultipartBody.Part.createFormData("NTC_PROPERTYCODE", viewModel.propertyNo.get());
        MultipartBody.Part para2 = MultipartBody.Part.createFormData("NOTICE_NO", viewModel.noticeNumber.get());
        MultipartBody.Part para3 = MultipartBody.Part.createFormData("ADDRESS_CODE", viewModel.addressCode.get());
        MultipartBody.Part para4 = MultipartBody.Part.createFormData("DOC_TYPE_ID", "2");
        MultipartBody.Part para5 = MultipartBody.Part.createFormData("DOC_NAME", viewModel.imageFileServingDPR.get().getName());
        MultipartBody.Part para6 = MultipartBody.Part.createFormData("DOC_PATH", viewModel.imageFileServingDPR.get().getAbsolutePath());
        MultipartBody.Part para7 = MultipartBody.Part.createFormData("USER_ID", mobNum);
        MultipartBody.Part para8 = MultipartBody.Part.createFormData("DOC_TIMESTAMP", todayDate);
        Observable<MultipartImageResponse> imageResponseObservable = apiService1.FnSVM_UploadDocument_DPR(accessToken, para1, para2, para3, para4, para5, para6, para7, para8, para9);
        imageResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    dialog.dismiss();
                    if (result.getRESPONSE_CODE().contains("200")) {
                        activity.onBackPressed();
                        activity.finish();
                        Toast.makeText(activity, "Data uploaded successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(activity, result.getRESPONSE_MESSAGE(), Toast.LENGTH_LONG).show();
                        saveServingDPRImageLocal(viewModel);
                    }
                }, (error) -> {
                    error.printStackTrace();
                    dialog.dismiss();
                    String errMsg = error.getLocalizedMessage();
                    if (errMsg.contains("401")) {
                        new Constant(activity).getRefreshToken();
                    } else {
                        Toast.makeText(activity, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
