package com.companyname.mauitest.photoCollection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.photoCollection.adapter.adapterimageGallery;

public class imageGallery extends AppCompatActivity {
    public static final String TAG = imageGallery.class.getSimpleName();
    private RecyclerView rv;
    private adapterimageGallery adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        initView();
    }
    private void initView() {
        rv = findViewById(R.id.rvimages);
        filldata();
    }

    private void filldata() {
        adapter=new adapterimageGallery(this,5,getApplicationContext());
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    }

