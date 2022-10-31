package com.bmc.suchane_svamitva.view.callbacks;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static com.bmc.suchane_svamitva.utils.Constant.IMAGE_CAPTURE_REQ;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_FinalActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_FinalActivity;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_FinalActivityViewModel;
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
                            viewModel.building.set(result.get(0).getNTC_BUILDING());
                            viewModel.street.set(result.get(0).getNTC_STREET_AREA());
                            viewModel.landmark.set(result.get(0).getNTC_LANDMARK());
                        }
                    }, error -> error.printStackTrace());
            getUserDistrict(viewModel);
        } catch (Exception ex){
            ex.printStackTrace();
        }

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
    public void captureServingNoticePhoto(DPR_FPR_FinalActivityViewModel viewModel) {
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
        viewModel.mCurrentServingNoticePhotoPath.set(file.getAbsolutePath());
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
    public void imageProcessServingNotice(DPR_FPR_FinalActivityViewModel viewModel) {
        try {
            Bitmap bitmap = scaleDownAndRotatePic(viewModel.mCurrentServingNoticePhotoPath.get());
            viewModel.imageBitMapServingNotice.set(bitmap);
            viewModel.isImageVisible.set(true);
            ImageCompressionAsyncTaskServingNotice imagetask = new ImageCompressionAsyncTaskServingNotice(activity, viewModel);
            String folder_main = "ServingNoticePictures";
            File myDir = activity.getDir( folder_main, MODE_PRIVATE);
            imagetask.execute(viewModel.mCurrentServingNoticePhotoPath.get(), myDir + "/" + Constant.APP_IMAGE);
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
        viewModel.imageDataServingNotice.set(imageBytes);
        viewModel.imageFileServingNotice.set(imageFile);
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
    public void showImageServingNotice(DPR_FPR_FinalActivityViewModel viewModel){
        Dialog settingsDialog = new Dialog(activity);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.image_popup);
        ImageView imageView = settingsDialog.findViewById(R.id.showImg);
        imageView.setImageBitmap(viewModel.imageBitMapServingNotice.get());
        settingsDialog.show();
    }
}
