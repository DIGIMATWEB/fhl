package com.fhl.sistemadedistribucionfh.evidence.rateDriver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;

public class calificacion extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = calificacion.class.getSimpleName();
    private Button button;
    private RatingBar starRate;
    private Float frating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_driver);
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Retrieve the frating value from the Bundle
            frating = bundle.getFloat("ratingValue", 0f);
            if(frating!=null){
                starRate.setRating(frating);
            }
        }
    }

    private void initView() {
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
        Intent intent = new Intent(this, evidencia.class);

        // Put the frating value into a Bundle
        if(frating!=null) {
            Bundle bundle = new Bundle();
            bundle.putFloat("ratingValue", frating);
            intent.putExtras(bundle);
        }
        // Start the evidencia activity
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                onBackPressed();
                break;
        }
    }
}
