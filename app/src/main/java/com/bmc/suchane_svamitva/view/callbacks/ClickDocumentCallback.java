package com.bmc.suchane_svamitva.view.callbacks;

import static android.app.Activity.RESULT_CANCELED;
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static com.bmc.suchane_svamitva.utils.Constant.IMAGE_CAPTURE_REQ;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.BuildConfig;
import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.databinding.DocsNameDialogBinding;
import com.bmc.suchane_svamitva.model.DocumentTbl;
import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.ClickDocumentInterface;
import com.bmc.suchane_svamitva.view.ui.ClickDocumentActivity;
import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClickDocumentCallback implements ClickDocumentInterface {
    ClickDocumentActivity activity;

    public ClickDocumentCallback(ClickDocumentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadData(ClickDocumentViewModel viewModel) {
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
            viewModel.noticeNumber.set(intent.getStringExtra("NOTICE_NO"));
            viewModel.propertyNo.set(intent.getStringExtra("Property_no"));
            viewModel.docsName.set(intent.getStringExtra("docsName"));

            loadImageList(viewModel);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean checkPermission_API30() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void captureDocumentPhoto(ClickDocumentViewModel viewModel) {
        if (checkPermission_API30()){
            requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, IMAGE_CAPTURE_REQ);
        } else {
            takeDocumentPicture(viewModel);
        }

    }

    public void takeDocumentPicture(ClickDocumentViewModel viewModel) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createMediaFileForDocsImg(viewModel);
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

    private File createMediaFileForDocsImg(ClickDocumentViewModel viewModel) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String fileName = "JPEG_" + timeStamp + "_" + viewModel.propertyNo;
        String folder_main = "DocsImages";

        File mydir = activity.getDir( folder_main, MODE_PRIVATE); //Creating an internal dir;
        File file = File.createTempFile(fileName, ".jpg", mydir); //Getting a file within the dir.
        // Get the path of the file created
        viewModel.mDocsImagePath.set(file.getAbsolutePath());
        return file;
    }

    @Override
    public void ProcessDocsImage(ClickDocumentViewModel viewModel) {
        try {
            Uri sourceUri = Uri.fromFile(new File(viewModel.mDocsImagePath.get()));
            Log.d("sourceUri", ""+sourceUri);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String fileName = "JPEG_" + timeStamp + "_";
            String folder_main = "DocsImagesCrop";
            File myDir = activity.getDir( folder_main, MODE_PRIVATE);
            File file = File.createTempFile(fileName, ".jpg", myDir);
            String filePath = file.getAbsolutePath();
            Uri destinationUri=Uri.fromFile(new File(filePath));
            Log.d("destinationUri", ""+destinationUri);

            UCrop.Options options = new UCrop.Options();
            options.setCompressionQuality(10);
            // applying UI theme
            options.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary));
            options.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
            options.withAspectRatio(3,4);

            UCrop.of(sourceUri, destinationUri)
                    .withOptions(options)
                    .start(activity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleUCropResult(Intent data, ClickDocumentViewModel viewModel){

        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Uri resultUri = UCrop.getOutput(data);
        Log.d("UCropResultUri", "" + resultUri.getPath());

        viewModel.ImageCapturedCount.set(viewModel.ImageCapturedCount.get()+1);
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
        String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

        Date date = new Date();
        String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);

        ImageTempTbl imageTempTbl = new ImageTempTbl();
        imageTempTbl.setDocumentName(viewModel.ImageCapturedCount.get()+"");
        imageTempTbl.setDocumentPath(resultUri.getPath());
        imageTempTbl.setNOTICE_NO(viewModel.noticeNumber.get());
        imageTempTbl.setProperty_no(viewModel.propertyNo.get());
        imageTempTbl.setDOC_TIMESTAMP(todayDate);
        imageTempTbl.setUSER_ID(mobNum);

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .InsertTempImage(imageTempTbl))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            dialog.dismiss();
                            loadImageList(viewModel);
                        }, error -> {
                            error.printStackTrace();
                            dialog.dismiss();
                        }
                );
    }

    @Override
    public void setResultCancelled() {
        Intent intent = new Intent();
        activity.setResult(RESULT_CANCELED, intent);
        activity.finish();
    }

    @Override
    public void onClickDeletePhoto(ClickDocumentViewModel viewModel, List<ImageTempTbl> SelectedImagelist){
        for (int i = 0;i<SelectedImagelist.size();i++) {
            deleteImageByImageID(viewModel, SelectedImagelist.get(i).getDocumentID(), SelectedImagelist.get(i).getDocumentPath(), SelectedImagelist.get(i));
        }
    }

    @Override
    public void onClickCreatePDF(ClickDocumentViewModel viewModel, List<ImageTempTbl> SelectedImagelist){

        try {
            SharedPreferences sharedPreferences = activity.getSharedPreferences(Constant.MY_SHARED_PREF, MODE_PRIVATE);
            String mobNum = sharedPreferences.getString(Constant.USER_MOBILE, null);

            Date date = new Date();
            String todayDate = new SimpleDateFormat(Constant.DATE_TIME_FORMAT, Locale.ENGLISH).format(date);
            String folder_main_PDF = "DocumentPDF";
            File mydir = activity.getDir(folder_main_PDF, MODE_PRIVATE); //Creating an internal dir;
            Document document = new Document();
            String directoryPath = mydir.getAbsolutePath();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String destinationDocsPath = directoryPath + "/Docs" + viewModel.propertyNo.get() + "_" + timeStamp + viewModel.docsName.get() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(destinationDocsPath)); //  Change pdf's name.
            document.open();
            for (int i = 0; i < SelectedImagelist.size(); i++) {
                int finalI = i;
                Log.d("SelectedImageList", "List :"+SelectedImagelist.get(finalI).getDocumentPath());
                Observable
                        .fromCallable(() -> DBConnection.getConnection(activity)
                                .getDataBaseDao()
                                .getTempImageDataByDocsID(viewModel.noticeNumber.get(), viewModel.propertyNo.get(), SelectedImagelist.get(finalI).getDocumentID()))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result ->
                        {
                            if (!TextUtils.isEmpty(result.getDocumentPath())) {
                                String filePathString = result.getDocumentPath();
                                File f = new File(filePathString);
                                if (f.exists()) {
                                    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(filePathString);  // Change image's name and extension.
                                    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                                            - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
                                    image.scalePercent(scaler);
                                    image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER | com.itextpdf.text.Image.ALIGN_TOP);
                                    document.add(image);
                                    Log.d("document", "Page :"+finalI);
                                    if (finalI == SelectedImagelist.size() - 1) {
                                        document.close();

                                        DocumentTbl documentTbl = new DocumentTbl();
                                        documentTbl.setDocumentName(viewModel.docsName.get());
                                        documentTbl.setDocumentPath(destinationDocsPath);
                                        documentTbl.setNOTICE_NO(viewModel.noticeNumber.get());
                                        documentTbl.setProperty_no(viewModel.propertyNo.get());
                                        documentTbl.setDOC_TIMESTAMP(todayDate);
                                        documentTbl.setUSER_ID(mobNum);

                                        PdfReader reader = new PdfReader(documentTbl.getDocumentPath());
                                        int pageNumber = reader.getNumberOfPages();
                                        Log.d("document", "pageNumber :"+pageNumber);
                                        Observable
                                                .fromCallable(() -> DBConnection.getConnection(activity)
                                                        .getDataBaseDao()
                                                        .InsertDocument(documentTbl))
                                                .subscribeOn(Schedulers.computation())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(result1 ->
                                                {
                                                    deleteImages(viewModel);
                                                    File dir1 = activity.getDir("app_DocsImagesCrop", MODE_PRIVATE);
                                                    File dir2 = activity.getDir("app_DocsImages", MODE_PRIVATE);
                                                    if (dir1.exists())
                                                        dir1.delete();
                                                    if (dir2.exists())
                                                        dir2.delete();
                                                    Toast.makeText(activity, "Document Saved Successfully", Toast.LENGTH_SHORT).show();
                                                    activity.onBackPressed();
                                                }, error -> {
                                                    Toast.makeText(activity, "Some Issue in saving the document", Toast.LENGTH_SHORT).show();
                                                    error.printStackTrace();
                                                });
                                    }
                                } else {
                                    deleteImageByImageID(viewModel, result.getDocumentID(), result.getDocumentPath(), SelectedImagelist.get(finalI));
                                }

                            } else {
                                deleteImageByImageID(viewModel, result.getDocumentID(), result.getDocumentPath(), SelectedImagelist.get(finalI));
                            }

                        }, error -> {
                            document.close();
                            error.printStackTrace();
                        });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void loadImageList(ClickDocumentViewModel viewModel){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Data Wait ..");
        dialog.show();

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getTempImagePath(viewModel.noticeNumber.get(), viewModel.propertyNo.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result1 -> {
                            dialog.dismiss();
                            viewModel.imageTempTblList.clear();
                            if (result1.size()>0) {
                                viewModel.imageTempTblList.addAll(result1);
                                viewModel.canCreatePDF.set(true);
                            } else {
                                viewModel.canCreatePDF.set(false);
                            }

                        }, error -> {
                            dialog.dismiss();
                            error.printStackTrace();
                        }
                );
    }

    public void deleteImageByImageID(ClickDocumentViewModel viewModel, int ImageId, String ImagePath, ImageTempTbl imageTempTbl){
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteTempImageDetailsByID(viewModel.noticeNumber.get(), viewModel.propertyNo.get(), ImageId))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result1 ->
                {
                    if (!TextUtils.isEmpty(ImagePath)) {
                        File dir1 = new File(ImagePath);
                        if (dir1.exists())
                            dir1.delete();
                    }
                    viewModel.SelectedImagelist.remove(imageTempTbl);
                    loadImageList(viewModel);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void deleteImages(ClickDocumentViewModel viewModel){
        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .deleteTempImageDetails(viewModel.noticeNumber.get(), viewModel.propertyNo.get()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result1 ->
                {
                }, error -> {
                    error.printStackTrace();
                });
    }
}
