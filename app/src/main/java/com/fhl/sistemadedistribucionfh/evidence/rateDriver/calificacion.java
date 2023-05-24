package com.fhl.sistemadedistribucionfh.evidence.rateDriver;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;

public class calificacion extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = calificacion.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_driver);
        initView();
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.firma:
                break;
        }
    }
}
