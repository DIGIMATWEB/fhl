package com.fhl.sistemadedistribucionfh.evidence.videos.adaoter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.fhl.sistemadedistribucionfh.R;

import java.util.ArrayList;
import java.util.List;

public class adapterVideoRecord extends RecyclerView.Adapter<adapterVideoRecord.ViewHolder> {
    private Context context;
    public adapterVideoRecord( Context context) {
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_videocollection, parent, false);
        return new adapterVideoRecord.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final adapterVideoRecord.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
    }
    @Override
    public int getItemCount() {
        return 18;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView evidence;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            evidence=itemView.findViewById(R.id.evidenceVideo);
        }
    }
}
