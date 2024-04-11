package com.fhl.sistemadedistribucionfh.Dialogs.ManifestStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.adapter.adapterReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.R;

import java.util.List;

public class adapterManifestStatus extends RecyclerView.Adapter<adapterManifestStatus.ViewHolder> {
    private Context context;
    private List<String> data;
    private int checkedPosition = -1;
    private manifestStatus mview;

    public adapterManifestStatus(manifestStatus mview,List<String> data, Context context) {
        this.context = context;
        this.data=data;
        this.mview=mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reasons, parent, false);
        return new adapterManifestStatus.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterManifestStatus.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDesc.setText(data.get(position));
        holder.checkBox.setChecked(position == checkedPosition);

        // Handle CheckBox click
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == checkedPosition) {
                    // If the same item is clicked again, uncheck it
                    holder.checkBox.setChecked(false);
                    checkedPosition = -1;
                } else {
                    // Update the checked position and notify the adapter to update UI
                    checkedPosition = position;
                    notifyDataSetChanged();
                    // Pass the checked item to the method
                    mview.showToast(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDesc);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}