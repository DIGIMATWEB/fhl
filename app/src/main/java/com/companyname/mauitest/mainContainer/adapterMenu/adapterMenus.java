package com.companyname.mauitest.mainContainer.adapterMenu;

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
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.Dialogs.mainMenu;
import com.companyname.mauitest.R;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;

import java.util.List;

public class adapterMenus extends RecyclerView.Adapter<adapterMenus.ViewHolder>{
    private Context context;
    private List<dataMenuItems> data;
    private mainMenu mview;
    public adapterMenus(mainMenu mainMenu, List<dataMenuItems> data, Context context) {
        this.mview=mainMenu;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Drawable drawable=null;
        switch (data.get(position).getMenuName()) {
            case "Perfil":
                drawable=context.getDrawable(R.drawable.ic_menu_perfil_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Manifiestos":
                drawable=context.getDrawable(R.drawable.ic_menu_manifiesto_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Salida":
                drawable=context.getDrawable(R.drawable.ic_menu_salida_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Validador":
                drawable=context.getDrawable(R.drawable.ic_menu_validador_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Escaner":
                drawable=context.getDrawable(R.drawable.ic_menu_scan_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Ubicacion GPS":
                drawable=context.getDrawable(R.drawable.ic_menu_gps_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "CheckList":
                drawable=context.getDrawable(R.drawable.ic_menu_checklist_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Gastos Operativos":
                drawable=context.getDrawable(R.drawable.ic_menu_gastos_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Resguardo":
                drawable=context.getDrawable(R.drawable.ic_menu_resguardo_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;
            case "Visor":
                drawable=context.getDrawable(R.drawable.ic_menu_visor_icon);
                holder.imageMenu.setImageDrawable(drawable);
                holder.menuName.setText(data.get(position).getMenuName());
                break;

        }
        holder.parentConstrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.get(position).getMenuName()) {
                    case "Perfil":
                        mview.closeDialog();
                        mview.invokeFragment("Perfil");
                        break;
                    case "Manifiestos":
                        mview.closeDialog();
                        mview.invokeFragment("Manifiestos");
                        break;
                    case "Salida":
                        mview.closeDialog();
                        mview.invokeFragment("Salida");
                        break;
                    case "Validador":
                        mview.closeDialog();
                        mview.invokeFragment("Validador");
                        break;
                    case "Escaner":
                        mview.closeDialog();
                        mview.invokeFragment("Escaner");
                        break;
                    case "Ubicacion GPS":
                        mview.closeDialog();
                        mview.invokeFragment("Ubicacion GPS");
                        break;
                    case "CheckList":
                        mview.closeDialog();
                        mview.invokeFragment("CheckList");
                        break;
                    case "Gastos Operativos":
                        mview.closeDialog();
                        mview.invokeFragment("Gastos");
                        break;
                    case "Resguardo":
                        mview.closeDialog();
                        mview.invokeFragment("Resguardo");
                        break;
                    case "Visor":
                        mview.closeDialog();
                        mview.invokeFragment("Visor");
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageMenu;
        private TextView menuName;
        private ConstraintLayout parentConstrain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu=itemView.findViewById(R.id.imageMenu);
            menuName=itemView.findViewById(R.id.menuName);
            parentConstrain=itemView.findViewById(R.id.parentConstrain);
        }
    }
}
