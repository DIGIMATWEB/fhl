package com.fhl.sistemadedistribucionfh.evidence.rateDriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;

public class calificacion extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = calificacion.class.getSimpleName();
    private Button button;
    private RatingBar starRate;
    private Float frating;
    private ImageView imageView20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_driver);
        initView();
        checkShared();

    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String rate = preferences.getString(GeneralConstants.RATE_STARS, null);

        if(rate!=null&&!rate.equals("")){
            frating = Float.valueOf( rate);
            starRate.setRating(frating);
        }
    }

    private void initView() {
        imageView20 =findViewById(R.id.imageView20);
        imageView20.setOnClickListener(this);
        button=findViewById(R.id.button);
        button.setOnClickListener(this);
        starRate = findViewById(R.id.starRate);
        starRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Do something with the new rating value
              frating=ratingBar.getRating();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Put the frating value into a Bundle
        if(frating!=null) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(GeneralConstants.RATE_STARS, String.valueOf(frating));
            editor.commit();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                onBackPressed();
                break;
            case R.id.imageView20:
                onBackPressed();
                break;
        }
    }
}
