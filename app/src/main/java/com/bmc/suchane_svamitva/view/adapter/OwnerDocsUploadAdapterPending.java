package com.bmc.suchane_svamitva.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.OwnerListDocsUploadBinding;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.interfaces.OwnerListDocsUploadInterface;

import java.util.ArrayList;
import java.util.List;

public class OwnerDocsUploadAdapterPending extends RecyclerView.Adapter<OwnerDocsUploadAdapterPending.DataViewHolder> implements Filterable {
    private List<OwnerTbl> data;
    List<OwnerTbl> mOwnerDisplayFilterList;
    private ValueFilter valueFilter;
    OwnerListDocsUploadInterface ownerListInterface;


    public OwnerDocsUploadAdapterPending(OwnerListDocsUploadInterface ownerListInterface) {
        this.ownerListInterface = ownerListInterface;
        this.data = new ArrayList<>();
        this.mOwnerDisplayFilterList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.owner_list_docs_upload,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        OwnerTbl citizenData = data.get(position);
        holder.setVDataVariable(citizenData);
        holder.setHandler(ownerListInterface);
        holder.setPosition(position);
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

    public void updateData(@Nullable List<OwnerTbl> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
            this.mOwnerDisplayFilterList.clear();
        } else {
            this.data.addAll(data);
            this.mOwnerDisplayFilterList.addAll(data);
        }
        //notifyDataSetChanged();
    }

    public void addData(@Nullable List<OwnerTbl> data) {
        if(data==null||data.isEmpty()){
            this.data=new ArrayList<>();
            this.mOwnerDisplayFilterList =new ArrayList<>();

        }else {
            this.data = data;
            this.mOwnerDisplayFilterList =data;
        }
        notifyDataSetChanged();
    }

    /* package */ static class DataViewHolder extends RecyclerView.ViewHolder {
        /* package */ OwnerListDocsUploadBinding binding;

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

        /* package */ void setVDataVariable(final OwnerTbl dataVariable) {
            if (binding != null) {
                binding.setDataVariable(dataVariable);

            }
        }
        void setHandler(final OwnerListDocsUploadInterface handler) {
            if (binding != null) {
                binding.setHandler(handler);
            }
        }
        void setPosition(final Integer position) {
            if (binding != null) {
                binding.setPosition(position);
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<OwnerTbl> filterList = new ArrayList<>();
                for (OwnerTbl data: mOwnerDisplayFilterList) {
                    if ((data.toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(data);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mOwnerDisplayFilterList.size();
                results.values = mOwnerDisplayFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            data = (List<OwnerTbl>) results.values;
            notifyDataSetChanged();
        }

    }

}