package com.fhl.sistemadedistribucionfh.evidence;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.calificacion;
import com.fhl.sistemadedistribucionfh.evidence.signature.signature;

public class evidencia extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = evidencia.class.getSimpleName();
    private ConstraintLayout firma,foto,archivos,rating;
    private Float frating;
    private String signatureBase64,inputTextSignature;
    private ImageView star,signatureImage;
    private Boolean mfirma,mfoto,mfiles,mrating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Retrieve the frating value from the Bundle
            frating = bundle.getFloat("ratingValue", 0f);
            signatureBase64=bundle.getString("signatureImage","");
            inputTextSignature=bundle.getString("inputText","");
            if(frating!=null){
                star.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            }
            if(!signatureBase64.isEmpty()&&!inputTextSignature.isEmpty()){
                signatureImage.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    private void initView() {
        firma=findViewById(R.id.firma);
        foto=findViewById(R.id.foto);
        archivos=findViewById(R.id.archivos);
        rating=findViewById(R.id.ratingd);
        firma.setOnClickListener(this);
        foto.setOnClickListener(this);
        archivos.setOnClickListener(this);
        rating.setOnClickListener(this);
        signatureImage=findViewById(R.id.signatureImage);

        //icons
        star=findViewById(R.id.imageMenu3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firma:
                Log.e("evidence","firma ");
                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
                Intent intentfirma = new Intent(this, signature.class);
                if(signatureBase64!=null&&inputTextSignature!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("signatureImage", signatureBase64);
                    bundle.putString("inputText", inputTextSignature);
                    intentfirma.putExtras(bundle);
                }
                startActivity(intentfirma);
                break;
            case R.id.foto:
                Log.e("evidence","foto ");
                Intent fotos = new Intent(this, carrusel.class);
                startActivity(fotos);
                break;
            case R.id.archivos:
                Intent files = new Intent(this, documents.class);
                startActivity(files);
                Log.e("evidence","archivos ");
                break;
            case R.id.ratingd:
                Intent rating = new Intent(this, calificacion.class);
                if(frating!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putFloat("ratingValue", frating);
                    rating.putExtras(bundle);
                }
                startActivity(rating);
                Log.e("evidence","rating ");
                break;
        }

    }
}
