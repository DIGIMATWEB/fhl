package com.fhl.sistemadedistribucionfh.evidence.documents;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;

public class documents extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = documents.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
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
