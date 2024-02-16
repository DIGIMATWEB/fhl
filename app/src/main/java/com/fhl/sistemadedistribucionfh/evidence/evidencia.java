package com.fhl.sistemadedistribucionfh.evidence;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.calificacion;
import com.fhl.sistemadedistribucionfh.evidence.signature.signature;

public class evidencia extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = evidencia.class.getSimpleName();
    private ConstraintLayout firma,foto,archivos,rating;
    private Float frating;
    private String signatureBase64,inputTextSignature;
    private ImageView star,signatureImage,imageMenu1;
    private Boolean mfirma,mfoto,mfiles,mrating=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        initView();
        checkShared();
    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String rate = preferences.getString(GeneralConstants.RATE_STARS, null);
        String signature = preferences.getString(GeneralConstants.SIGNATURE_B64, null);
        String inputText = preferences.getString(GeneralConstants.INPUT_TEXT_SIGTURE,null);
        String images=preferences.getString(GeneralConstants.IMAGE_DIRECTORY,null);

        if(rate!=null){
            frating = Float.valueOf( rate);
            star.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mrating=true;
        }
        if (signature!=null&&inputText!=null) {
            signatureImage.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mfirma=true;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);

        String images=preferences.getString(GeneralConstants.IMAGE_DIRECTORY,null);
        if(images!=null){
            imageMenu1.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mfoto=true;
        }
    }

    private void initView() {
        firma=findViewById(R.id.firma);
        foto=findViewById(R.id.foto);
        archivos=findViewById(R.id.archivos);
        rating=findViewById(R.id.ratingd);
        imageMenu1 =findViewById(R.id.imageMenu1);
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
//                if(signatureBase64!=null&&inputTextSignature!=null) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("signatureImage", signatureBase64);
//                    bundle.putString("inputText", inputTextSignature);
//                    intentfirma.putExtras(bundle);
//                }
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
//                if(frating!=null) {
//                    Bundle bundle = new Bundle();
//                    bundle.putFloat("ratingValue", frating);
//                    rating.putExtras(bundle);
//                }
                startActivity(rating);
                Log.e("evidence","rating ");
                break;
        }

    }
}
