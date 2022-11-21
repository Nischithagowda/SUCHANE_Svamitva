package com.bmc.suchane_svamitva.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bmc.suchane_svamitva.databinding.DocsListBinding;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.DocumentTbl;
import com.bmc.suchane_svamitva.view.interfaces.DocumentListInterface;

import java.util.ArrayList;
import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.DataViewHolder> {
    private List<DocumentTbl> data;
    DocumentListInterface documentListInterface;


    public DocumentListAdapter(DocumentListInterface documentListInterface) {
        this.documentListInterface = documentListInterface;
        this.data = new ArrayList<>();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.docs_list,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        DocumentTbl documentData = data.get(position);
        holder.setVDataVariable(documentData);
        holder.setHandler(documentListInterface);
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

    public void updateData(@Nullable List<DocumentTbl> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        //notifyDataSetChanged();
    }

    public void addData(@Nullable List<DocumentTbl> data) {
        if(data==null||data.isEmpty()){
            this.data=new ArrayList<>();

        }else {
            this.data = data;
        }
        notifyDataSetChanged();
    }

    /* package */ static class DataViewHolder extends RecyclerView.ViewHolder {
        /* package */ DocsListBinding binding;

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

        /* package */ void setVDataVariable(final DocumentTbl dataVariable) {
            if (binding != null) {
                binding.setDataVariable(dataVariable);

            }
        }
        void setHandler(final DocumentListInterface handler) {
            if (binding != null) {
                binding.setHandler(handler);
            }
        }
    }

}