package com.fhl.sistemadedistribucionfh.evidence.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Adapter.adapterSellos;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Sello;
import com.fhl.sistemadedistribucionfh.Salida.View.sellos;

import java.util.ArrayList;
import java.util.List;

public class adapterEvidence extends RecyclerView.Adapter<adapterEvidence.ViewHolder> {
    private Context context;


    public adapterEvidence( Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evidence, parent, false);//item_carrito
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEvidence.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        switch (position) {
            case 0://Firma siempre va
                // Handle logic for position 0
                Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_signature_ico);
                holder.image.setBackground(background);
                holder.description.setText("Firma");
                break;
            case 1:
                // Handle logic for position 1
                break;
            case 2:
                // Handle logic for position 1
                break;
            case 3:
                // Handle logic for position 1
                break;
            case 4:
                // Handle logic for position 1
                Drawable background4 = ContextCompat.getDrawable(context, R.drawable.ic_rankico);
                holder.image.setBackground(background4);
                holder.description.setText("Encuesta");
                break;
            // Add more cases as needed
            default:
                // Handle default logic for other positions
                break;
        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView   description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageMenu3video);
            description=itemView.findViewById(R.id.menuName3video);
        }
    }
}
