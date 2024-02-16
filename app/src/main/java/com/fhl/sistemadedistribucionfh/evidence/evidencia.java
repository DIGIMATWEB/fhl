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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenterImpl;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.calificacion;
import com.fhl.sistemadedistribucionfh.evidence.signature.signature;

public class evidencia extends AppCompatActivity implements View.OnClickListener,evidenceView {
    public static final String TAG = evidencia.class.getSimpleName();
    private ConstraintLayout firma,foto,archivos,rating;
    private Float frating;
    private String signatureBase64,inputTextSignature,currusel,ffiles,stars="";
    private ImageView star,signatureImage,cameraico,clipDocs;
    private Boolean mfirma,mfoto,mfiles,mrating=false;
    private Button sendEvidence;
    private ImageButton eraseShared;
    private requestEvidencePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        initView();
        checkShared();
    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String signature = preferences.getString(GeneralConstants.SIGNATURE_B64, null);
        String inputText = preferences.getString(GeneralConstants.INPUT_TEXT_SIGTURE,null);
        String images=preferences.getString(GeneralConstants.IMAGE_DIRECTORY,null);
        String docs=preferences.getString(GeneralConstants.DOCS_DIRECTORY, null);
        String rate = preferences.getString(GeneralConstants.RATE_STARS, null);





        if (signature!=null&&inputText!=null) {
            signatureImage.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mfirma=true;
            signatureBase64=signature;
            inputTextSignature=inputText;
        }else{
            signatureImage.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            mfirma=false;
            signatureBase64="";
            inputTextSignature="";
        }
        if(images!=null){
            cameraico.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mfoto=true;
            currusel=images;

        }else{
            cameraico.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            mfoto=false;
            currusel="";
        }
        if(docs!=null){
            clipDocs.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mfiles=true;
            ffiles=docs;
        }else{
            clipDocs.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            mfiles=false;
            ffiles="";
        }
        if(rate!=null){
            frating = Float.valueOf( rate);
            star.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mrating=true;
            stars=rate;
        }else{
            star.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            mrating=false;
            stars="";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkShared();
        if(mfirma==true&&mfoto==true&&mfiles==true&&mrating==true)
        {
            sendEvidence.setVisibility(View.VISIBLE);
        }else{
            sendEvidence.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkShared();
    }

    private void initView() {

        firma=findViewById(R.id.firma);
        foto=findViewById(R.id.foto);
        archivos=findViewById(R.id.archivos);
        rating=findViewById(R.id.ratingd);
        cameraico =findViewById(R.id.cameraico);
        star=findViewById(R.id.imageMenu3);
        clipDocs=findViewById(R.id.clipDocs);
        eraseShared=findViewById(R.id.eraseShared);
        eraseShared.setOnClickListener(this);
        firma.setOnClickListener(this);
        foto.setOnClickListener(this);
        archivos.setOnClickListener(this);
        rating.setOnClickListener(this);
        signatureImage=findViewById(R.id.signatureImage);

        sendEvidence =findViewById(R.id.sendEvidence);
        sendEvidence.setOnClickListener(this);
        sendEvidence.setVisibility(View.GONE);
        //icons
        presenter=new requestEvidencePresenterImpl(this,getBaseContext());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firma:
                Log.e("evidence","firma ");
                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
                Intent intentfirma = new Intent(this, signature.class);
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
                startActivity(rating);
                Log.e("evidence","rating ");
                break;
            case R.id.eraseShared:
                 SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = preferences.edit();
                 editor.putString(GeneralConstants.SIGNATURE_B64, null);
                 editor.putString(GeneralConstants.INPUT_TEXT_SIGTURE,null);
                 editor.putString(GeneralConstants.IMAGE_DIRECTORY,null);
                 editor.putString(GeneralConstants.DOCS_DIRECTORY, null);
                 editor.putString(GeneralConstants.RATE_STARS, null);
                 editor.commit();
                signatureImage.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfirma = false;
                cameraico.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfoto = false;
                clipDocs.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfiles = false;
                star.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mrating = false;
                break;
            case R.id.sendEvidence:
                presenter.sendEvidence();
                Log.e("sendEvidence", "signatureBase64: " + signatureBase64 + "\n" +
                        "inputTextSignature: " + inputTextSignature + "\n" +
                        "carrusel: " + currusel + "\n" +
                        "ffiles: " + ffiles + "\n" +
                        "stars: " + stars);
                break;
        }

    }

    @Override
    public void setMessage() {

    }
}
