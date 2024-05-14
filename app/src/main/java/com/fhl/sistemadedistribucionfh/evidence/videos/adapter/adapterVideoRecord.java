package com.fhl.sistemadedistribucionfh.evidence.videos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private List<String> lisEvidenceVideo;

    public adapterVideoRecord(Context context, OnItemClickListener listener, List<String> lisEvidenceVideo) {
        this.context=context;
        this.listener=listener;
        this.lisEvidenceVideo=lisEvidenceVideo;
    }
    public void addVideoUri(Uri videoUri, Integer posVid) {
        Log.e("addVideoUri", "position " + posVid + "  video " + videoUri);
        if (lisEvidenceVideo != null) {
            int evidenceSize = lisEvidenceVideo.size();
            int currentSize = videoUriList.size();
            if (posVid < currentSize) {
                videoUriList.set(posVid, videoUri);
            } else if (posVid == currentSize) {
                videoUriList.add(videoUri);
            } else {
                // If the position exceeds the current size, you might want to handle this case.
                // For now, let's add null values until the position matches.
                while (currentSize < posVid) {
                    videoUriList.add(null);
                    currentSize++;
                }
                videoUriList.add(videoUri);
            }

            // If evidence size is greater than the current size of videoUriList,
            // add null values to videoUriList until it reaches the same size as lisEvidenceVideo.
            while (videoUriList.size() < evidenceSize) {
                videoUriList.add(null);
            }

            notifyDataSetChanged();
        }
    }



    public void removeItem(int position) {
        if (position >= 0 && position < videoUriList.size()) {
            videoUriList.set(position,null);
            //notifyItemRemoved(position);
        }
    }
    public void removeFile(Uri videoUri) {
        listener.removeformmedia(videoUri);
        notifyDataSetChanged();
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
        holder.descVideo.setText(lisEvidenceVideo.get(position));

        if (!videoUriList.isEmpty() && position < videoUriList.size()) {
            Log.e("addVideoUri","validation1 "+!videoUriList.isEmpty() +" validation2"+ (position < videoUriList.size())+" validation3 "+(videoUriList.get(position)!=null));
            if(videoUriList.get(position)!=null){
                Log.e("addVideoUri",videoUriList.get(position).toString());

            final Uri videoUri = videoUriList.get(position);
            Glide.with(context)
                    .load(videoUri)
                    .centerCrop()
                    .into(holder.evidence);
            holder.evidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(videoUri, position);
                    }
                }
            });
            }else{
                Glide.with(context)
                        .load(android.R.color.black) // Load a black placeholder
                        .centerCrop()
                        .into(holder.evidence);
                holder.evidence.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.startRecord(position);
                        }
                    }
                });
            }
        } else {
            holder.evidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.startRecord(position);
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return lisEvidenceVideo.size();//videoUriList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView evidence,overlay;
        TextView descVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            evidence=itemView.findViewById(R.id.evidenceVideo);
            descVideo=itemView.findViewById(R.id.descVideo);
          //  overlay=itemView.findViewById(R.id. overlay);
        }
    }
}
