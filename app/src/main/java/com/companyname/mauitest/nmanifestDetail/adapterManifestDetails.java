package com.companyname.mauitest.nmanifestDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.companyname.mauitest.R;
public class adapterManifestDetails extends RecyclerView.Adapter<adapterManifestDetails.ViewHolder>{
    private manifestDetail mview;
    private Context context;
    private int size;
    public adapterManifestDetails(manifestDetail mview, int i, Context context) {
        this.mview=mview;
        this.context=context;
        this.size=i;
    }
    @NonNull
    @Override
    public adapterManifestDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_singleticket, parent, false);//item_carrito
        return  new adapterManifestDetails.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterManifestDetails.ViewHolder holder, int position) {
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                mview.gotoTickets(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
        }
    }
}
