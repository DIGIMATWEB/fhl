package com.companyname.mauitest.mainContainer.adapterMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.Dialogs.mainMenu;
import com.companyname.mauitest.R;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;

import java.util.List;

public class adapterMenus extends RecyclerView.Adapter<adapterMenus.ViewHolder>{
    private Context context;
    private List<dataMenuItems> data;

    public adapterMenus(List<dataMenuItems> data, Context context) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menus, parent, false);
        return new adapterMenus.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
