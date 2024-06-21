package com.fhl.sistemadedistribucionfh.mainContainer.adapterMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mainContainer.mainMenu;
import com.fhl.sistemadedistribucionfh.mainContainer.menuV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;

import java.util.List;

public class adapterBottomMenu extends RecyclerView.Adapter<adapterBottomMenu.ViewHolder>{
private Context context;
private List<dataMenuItemsV2> dataV2;
private menuV2 mview;
public adapterBottomMenu(menuV2 mainMenu, List<dataMenuItemsV2> dataV2, Context context) {
        this.mview=mainMenu;
        this.context=context;

        this.dataV2=dataV2;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menus_bottom, parent, false);
        return new adapterBottomMenu.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Drawable drawable=null;
        Log.e("menuv2", "" + dataV2.size());
        Log.e("menuv2", "" + dataV2.get(position).getTitulo());
        //switch (data.get(position).getMenuName()) {
        switch (dataV2.get(position).getTitulo()) {
        case "Perfil":
        drawable=context.getDrawable(R.drawable.ic_menu_perfil_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Manifiestos":
        drawable=context.getDrawable(R.drawable.ic_menu_manifiesto_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Carga":
        drawable=context.getDrawable(R.drawable.ic_menu_salida_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Validador":
        drawable=context.getDrawable(R.drawable.ic_menu_validador_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Escaner":
        drawable=context.getDrawable(R.drawable.ic_menu_scan_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Ubicación de GPS":
        drawable=context.getDrawable(R.drawable.ic_menu_gps_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Checklist":
        drawable=context.getDrawable(R.drawable.ic_menu_checklist_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Gastos operativos":
        drawable=context.getDrawable(R.drawable.ic_menu_gastos_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Resguardo":
        drawable=context.getDrawable(R.drawable.ic_menu_resguardo_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Visor":
        drawable=context.getDrawable(R.drawable.ic_menu_visor_icon);
        holder.imageMenu.setImageDrawable(drawable);
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Custodio":
        drawable=context.getDrawable(R.drawable.ic_menu_scan_icon);
        holder.imageMenu.setImageDrawable(drawable);
        Log.e("custodio","");
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        case "Recepción":
        drawable=context.getDrawable(R.drawable.ic_menu_visor_icon);
        holder.imageMenu.setImageDrawable(drawable);
        Log.e("custodio","r");
        //holder.menuName.setText(data.get(position).getMenuName());
        holder.menuName.setText(dataV2.get(position).getTitulo());
        break;
        }
        holder.parentConstrain.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        //switch (data.get(position).getMenuName()) {
        switch (dataV2.get(position).getTitulo()) {
        case "Perfil":
        
        mview.invokeFragment("Perfil");
        break;
        case "Manifiestos":
        
        mview.invokeFragment("Manifiestos");
        break;
        case "Carga":
        
        mview.invokeFragment("Carga");
        break;
        case "Validador":
        
        mview.invokeFragment("Validador");
        break;
        case "Escaner":
        
        mview.invokeFragment("Escaner");
        break;
        case "Ubicación de GPS":
        
        mview.invokeFragment("Ubicación de GPS");
        break;
        case "Checklist":
        
        mview.invokeFragment("Checklist");
        break;
        case "Gastos operativos":
        
        mview.invokeFragment("Gastos operativos");
        break;
        case "Resguardo":
        
        mview.invokeFragment("Resguardo");
        break;
        case "Visor":
        
        mview.invokeFragment("Visor");
        break;
        case "Recepción":
        
        mview.invokeFragment("Recepción");
        break;
        }
        }
        });
        }

@Override
public int getItemCount() {
        //return data.size();
        return dataV2.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageMenu;
    private TextView menuName;
    private ConstraintLayout parentConstrain;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageMenu=itemView.findViewById(R.id.imageMenu2b);
        menuName=itemView.findViewById(R.id.menuName2b);
        parentConstrain=itemView.findViewById(R.id.parentConstrain2b);
    }
}
}