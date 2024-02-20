package com.fhl.sistemadedistribucionfh.evidence.signature;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class signature extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = signature.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private TextInputEditText editText;
    private Button saveSignature;
    private GestureOverlayView gestureOverlayView;
    private String signatureBase64,inputTextSignature;
    private ImageView backTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        initView();
        checkShared();

    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String signature = preferences.getString(GeneralConstants.SIGNATURE_B64, null);
        String inputText = preferences.getString(GeneralConstants.INPUT_TEXT_SIGTURE,null);

        if (signature!=null) {
            signatureBase64=signature;
            Bitmap bitmap = convertBase64ToBitmap(signatureBase64);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            gestureOverlayView.setBackground(drawable);
        }
        if (inputText!=null) {
            inputTextSignature= inputText;
            editText.setText(inputTextSignature);
        }

    }

    private Bitmap convertBase64ToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
    private void initView() {
        backTickets =findViewById(R.id.backTickets);
        backTickets.setOnClickListener(this);
        saveSignature =findViewById(R.id.saveSignature);
        saveSignature.setOnClickListener(this);
        editText=findViewById(R.id.inputEditText);
        gestureOverlayView = findViewById(R.id.signaturePad);
        gestureOverlayView.setDrawingCacheEnabled(true);
        captureSignature(gestureOverlayView);
    }

    // Method to capture the content of the GestureOverlayView as a bitmap
    private Bitmap captureSignature(GestureOverlayView gestureOverlayView) {
        Bitmap signatureBitmap = null;
        try {
            // Create a bitmap with the same size as the GestureOverlayView
            signatureBitmap = Bitmap.createBitmap(gestureOverlayView.getWidth(), gestureOverlayView.getHeight(), Bitmap.Config.ARGB_8888);

            // Create a canvas to draw on the bitmap
            Canvas canvas = new Canvas(signatureBitmap);

            // Draw a white background
            Paint paint = new   Paint();
            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            // Draw the content of the GestureOverlayView onto the bitmap
            gestureOverlayView.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signatureBitmap;
    }
    // Method to convert a bitmap to a base64 string
    private String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void manageSignature() {
        String inputText = editText.getText().toString();

        // Capture the content of the GestureOverlayView as a bitmap
        Bitmap signatureBitmap = captureSignature(gestureOverlayView);

        // Check if the captured bitmap is valid
        if (signatureBitmap != null) {

            // Save the signature as a file with a unique filename
            File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File imagesDir = new File(picturesDir, "MyImages");
            if (!imagesDir.exists()) {
                if (!imagesDir.mkdirs()) {
                    Log.e(TAG, "Failed to create directory: " + imagesDir.getAbsolutePath());
                    return;
                }
            }

            // Generate a unique filename for the signature
            String filename = "signature_" + System.currentTimeMillis() + ".png";
            File signatureFile = new File(imagesDir, filename);

            try {
                FileOutputStream out = new FileOutputStream(signatureFile);
                signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush(); // Flush the stream to ensure all data has been written to the file
                out.close(); // Close the output stream
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to save signature file: " + e.getMessage());
                return;
            }

            // Save the location of the signature file in preferences
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(GeneralConstants.SIGNATURE_B64, signatureFile.getAbsolutePath());
            editor.putString(GeneralConstants.INPUT_TEXT_SIGTURE, inputText);
            editor.apply(); // Use apply() instead of commit() for asynchronous save
        } else {
            // Handle case where signature is empty
            Log.e(TAG, "Empty signature. Not saving.");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveSignature:
                manageSignature();
                onBackPressed();

                break;
            case R.id.backTickets:
                onBackPressed();
                break;
        }
    }


}