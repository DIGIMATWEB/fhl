package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.sellosSummary;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.ArrayList;
import java.util.List;

public class adapterSellosManifestDetail extends RecyclerView.Adapter<adapterSellosManifestDetail.ViewHolder> {
    private Context context;
    private List<Sello> data;
    private sellosSummary mview;
    private ViewHolder viewHolder;
    public adapterSellosManifestDetail(sellosSummary mview,List<Sello> sellos, Context context) {
        this.context = context;
        this.mview=mview;
        if (data != null) {
            this.data = sellos;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public adapterSellosManifestDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sellos_salida, parent, false);
        return new adapterSellosManifestDetail.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterSellosManifestDetail.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        viewHolder=holder;
        if(data.get(position).getNumeroSello()!=null) {
            if(!data.get(position).getNumeroSello().equals("")) {
                holder.razonDesc.setVisibility(View.VISIBLE);
                holder.razonDesc.setText("" + data.get(position).getNumeroSello());
                holder.editrazonDescSalida.setVisibility(View.GONE);
            }else{
                holder.razonDesc.setVisibility(View.GONE);
                holder.editrazonDescSalida.setVisibility(View.VISIBLE);
                holder.editrazonDescSalida.setText("");
            }
        }
        holder.editrazonDescSalida.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String inputText = holder.editrazonDescSalida.getText().toString();
                    if (!inputText.isEmpty()) {
                        data.get(position).setNumeroSello(inputText);
                        notifyItemChanged(position);
                        holder.razonDesc.setVisibility(View.VISIBLE);
                        holder.razonDesc.setText(inputText);
                        holder.editrazonDescSalida.setVisibility(View.GONE);
                       // mview.control(true);
                    } else {
                        // Set a default value if the input is empty
                        holder.editrazonDescSalida.setError("Porfavor introduce un valor");
                        mview.control(false);
                    }
                    return true;
                }
                return false;
            }
        });

    }
    public boolean validateFields() {
        for (int i = 0; i < getItemCount(); i++) {
                ViewHolder viewHolder = this.viewHolder;
                if (viewHolder.editrazonDescSalida.getVisibility() == View.VISIBLE) {
                    String text = viewHolder.editrazonDescSalida.getText().toString();
                    String text2 =viewHolder.razonDesc.getText().toString();
                    if (text.isEmpty()) {
                        viewHolder.editrazonDescSalida.setError("Porfavor introduce un valor.");
                        mview.control(false);
                        return false;
                    }else {
                        viewHolder.razonDesc.setText(text);
                      //  text2 =viewHolder.razonDesc.getText().toString();
                       // data.get(i).setNumeroSello(text2);
                        if(text2.isEmpty()){
                            viewHolder.editrazonDescSalida.setError("Porfavor guarda el valor .");
                            mview.control(false);
                            return false;
                        }else{

                        }

                    }

                }

        }
        mview.control(true);
        return true;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateSellos(Sello mdata){
        if (mdata != null) {
            this.data.add(mdata);
            mview.updateSellos(data);
            notifyDataSetChanged();

        }
    }
    public void removeItem(int position) {
        data.remove(position);
        mview.updateSellos(data);
        notifyItemRemoved(position);
    }

    public void updateData(List<Sello> sellos) {
        this.data= new ArrayList<>();
        this.data.addAll(sellos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc;
        private CheckBox check;
        private ImageView icticket;
        private EditText editrazonDescSalida;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDescSalida);
            check=itemView.findViewById(R.id.checkSalida );
            icticket=itemView.findViewById(R.id.icticket);
            editrazonDescSalida=itemView.findViewById(R.id.editrazonDescSalida);
        }
    }
}