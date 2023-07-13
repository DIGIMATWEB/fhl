package com.fhl.sistemadedistribucionfh.checkList.Questions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterQ {/* extends RecyclerView.Adapter<adapterQ.ViewHolder>{// PagerAdapter {

    private Context context;
    private Questions mview;
    private  int sizedots;
    private List<dataQuestions> dataQuestions1;
    private QuestionsAdapter adapter;

    public adapterQ( List<dataQuestions> data, int size, Questions mview, Context context) {//(FragmentManager childFragmentManager, List<Banners> banners, Context context) {
        this.context= context;
        this.mview=mview;
        this.dataQuestions1=data;
        this.sizedots=size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pager_questions, viewGroup, false);
        return new adapterQ.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterQ.ViewHolder holder, int position) {
        adapter=new QuestionsAdapter(dataQuestions1.get(position),mview, context);//TODO cambiar esto por el iterador
        holder.rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return dataQuestions1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rv;
        public ViewHolder(View view) {
            super(view);
            rv=itemView.findViewById(R.id.rvQuestions);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(layoutManager);
        }
    }*/
}
