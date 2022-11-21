package com.bmc.suchane_svamitva.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ImageListBinding;
import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.view.interfaces.ImageListInterface;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.DataViewHolder> {
    private List<ImageTempTbl> data;
    ImageListInterface imageListInterface;


    public ImageListAdapter(ImageListInterface imageListInterface) {
        this.imageListInterface = imageListInterface;
        this.data = new ArrayList<>();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        ImageTempTbl imageTempTbl = data.get(position);
        holder.setVDataVariable(imageTempTbl);
        holder.setHandler(imageListInterface);
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<ImageTempTbl> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        //notifyDataSetChanged();
    }

    public void addData(@Nullable List<ImageTempTbl> data) {
        if(data==null||data.isEmpty()){
            this.data=new ArrayList<>();

        }else {
            this.data = data;
        }
        notifyDataSetChanged();
    }

    /* package */ static class DataViewHolder extends RecyclerView.ViewHolder {
        /* package */ ImageListBinding binding;

        /* package */ DataViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        /* package */ void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        /* package */ void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        /* package */ void setVDataVariable(final ImageTempTbl dataVariable) {
            if (binding != null) {
                binding.setDataVariable(dataVariable);

            }
        }
        void setHandler(final ImageListInterface handler) {
            if (binding != null) {
                binding.setHandler(handler);
            }
        }
    }

}