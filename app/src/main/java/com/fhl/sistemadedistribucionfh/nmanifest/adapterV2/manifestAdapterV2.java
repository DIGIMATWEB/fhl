package com.fhl.sistemadedistribucionfh.nmanifest.adapterV2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

import org.jetbrains.annotations.NonNls;

import java.util.List;

public class manifestAdapterV2 extends RecyclerView.Adapter<manifestAdapterV2.ViewHolder> {
    private Context context;
    private int size;
    private mmanifestV2 mView;
    private List<dataManifestV2> data;

    public manifestAdapterV2(mmanifestV2 mmanifest, List<dataManifestV2> data, int size, Context context) {
        this.mView = mmanifest;
        this.size = size;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public manifestAdapterV2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manifest, parent, false);
        return new manifestAdapterV2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manifestAdapterV2.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView numberManifest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
