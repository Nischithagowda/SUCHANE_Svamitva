package com.bmc.suchane_svamitva.binding;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.adapter.OwnerAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewBinding {
    @BindingAdapter({"android:checked"})
    public static void checkBox(AppCompatCheckBox spinner, boolean status) {
        spinner.setChecked(status);

    }

    @BindingAdapter({"inputType"})
    public static void inputType(TextInputEditText textInputEditText, int value) {
        if (value == 2) {
            textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        } else {
            textInputEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        }
    }

    @BindingAdapter("requestFocus")
    public static void requestFocus(androidx.appcompat.widget.AppCompatEditText editText, boolean focus) {
        if (focus) {
            editText.requestFocus();
        }
    }

    @BindingAdapter("visibility")
    public static void imageBinding(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("visibility")
    public static void spinnerBinding(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("visibility")
    public static void LayoutBinding(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }


    @BindingAdapter("visibility")
    public static void recyclerBinding(RecyclerView view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"imageBitMap"})
    public static void appCompatImageDataBinding(AppCompatImageView imageView, Bitmap bitmap) {
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"imageBitMap"})
    public static void imageDataBinding(ImageView imageView, Bitmap bitmap) {
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"items"})
    public static void districtSpinner1(com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner spinner, List<District> entries) {
        ArrayAdapter<District> adapter = new ArrayAdapter<>(spinner.getContext(),
                android.R.layout.simple_spinner_dropdown_item, entries);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"items"})
    public static void talukSpinner1(com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner spinner, List<Taluka> entries) {
        ArrayAdapter<Taluka> adapter = new ArrayAdapter<>(spinner.getContext(),
                android.R.layout.simple_spinner_dropdown_item, entries);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"items"})
    public static void hoblipinner1(com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner spinner, List<Hobli> entries) {
        ArrayAdapter<Hobli> adapter = new ArrayAdapter<>(spinner.getContext(),
                android.R.layout.simple_spinner_dropdown_item, entries);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"items"})
    public static void villageSpinner1(com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner spinner, List<Village> entries) {
        ArrayAdapter<Village> adapter = new ArrayAdapter<>(spinner.getContext(),
                android.R.layout.simple_spinner_dropdown_item, entries);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"data", "adapter"})
    public static void ownerAdapter(RecyclerView recyclerView, List<OwnerTbl> ownerTblList, OwnerAdapter adapter) {
        adapter.addData(ownerTblList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }
}
