package com.fhl.sistemadedistribucionfh.evidence.documents.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private ArrayList<String> fileList;
    private documents mview;
    private  List<String> lisEvidence;

    public FileAdapter(ArrayList<String> fileList, documents mview, List<String> lisEvidence) {
        this.fileList = fileList;
        this.mview=mview;
        this.lisEvidence=lisEvidence;

    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!fileList.isEmpty() && position < fileList.size()) {
            String fileName = fileList.get(position);
            holder.textFileName.setText(fileName);
        }

        if (position < lisEvidence.size()) {
            holder.descriptionEvidence.setText(lisEvidence.get(position));
        }

        holder.descriptionEvidence.setText(lisEvidence.get(position));
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.getFile(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lisEvidence.size();//fileList.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView textFileName,descriptionEvidence;
        ImageView download;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            textFileName = itemView.findViewById(R.id.menuName);
            download= itemView.findViewById(R.id. download);
            descriptionEvidence= itemView.findViewById(R.id. descriptionEvidence);
        }
    }
}