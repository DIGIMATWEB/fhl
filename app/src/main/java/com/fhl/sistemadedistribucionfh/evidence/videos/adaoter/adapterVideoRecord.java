package com.fhl.sistemadedistribucionfh.evidence.videos.adaoter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.R;

import java.util.ArrayList;
import java.util.List;

public class adapterVideoRecord extends RecyclerView.Adapter<adapterVideoRecord.ViewHolder> {
    private Context context;
    private List<Uri> videoUriList=new ArrayList<>();
    private OnItemClickListener listener;

    public adapterVideoRecord( Context context,OnItemClickListener listener) {
        this.context=context;
        this.listener=listener;
    }
    public void addVideoUri(Uri videoUri) {
        videoUriList.add(videoUri);
        notifyDataSetChanged(); // Notify adapter that data set has changed
    }
    public void removeItem(int position) {
        if (position >= 0 && position < videoUriList.size()) {
            videoUriList.remove(position);
            notifyItemRemoved(position);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_videocollection, parent, false);
        return new adapterVideoRecord.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final adapterVideoRecord.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       // holder.overlay.setVisibility(View.GONE);
        if(!videoUriList.isEmpty()){
            Uri videoUri = videoUriList.get(position);
            Glide.with(context)
                    .load(videoUri)
                    .centerCrop()
                    .into(holder.evidence);
            holder.evidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
//                        if(holder.overlay.getVisibility()==View.GONE){
//                            holder.overlay.setVisibility(View.VISIBLE);
//                        }else{
//                            holder.overlay.setVisibility(View.GONE);
//                        }
                        listener.onItemClick(videoUri,position);
                    }
                }
            });

        }

    }
    @Override
    public int getItemCount() {
        return videoUriList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView evidence,overlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            evidence=itemView.findViewById(R.id.evidenceVideo);
          //  overlay=itemView.findViewById(R.id. overlay);
        }
    }
}
