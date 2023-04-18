package com.companyname.mauitest.photoCollection.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.photoCollection.imageGallery;

public class adapterimageGallery extends RecyclerView.Adapter<adapterimageGallery.ViewHolder>{
    private Context context;
    private  int size;
  private imageGallery mView;
    public adapterimageGallery(imageGallery mView, int size, Context context) {
        this.mView=mView;
        this.size=size;
        this.context=context;
    }
    @NonNull
    @Override
    public adapterimageGallery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);//item_carrito
        return  new adapterimageGallery.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterimageGallery.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Drawable r= context.getDrawable(R.drawable.ic_launcher_background);
        holder.rowImage.setImageDrawable(r);
       /* holder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                //mView.gotoTickets(position);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return 30;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rowImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImage=itemView.findViewById(R.id.rowImage);
        }
    }
}