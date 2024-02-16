package com.fhl.sistemadedistribucionfh.evidence.photos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class carrusel extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
        public static final String TAG = carrusel.class.getSimpleName();
        private Button guardarFotosButon;
        private ImageView backImage;
        private ImageButton imageView19, imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8, imageButton9;
        private static final int REQUEST_IMAGE_CAPTURE = 1;
        private ImageButton lastClickedImageButton; // Keep track of the last clicked ImageButton
        private File tempImageFile;
        private ArrayList<File> tempImageFiles = new ArrayList<>();
        private List<String> directories=new ArrayList<>();
        private ImageButton sawLast,eraseFolder;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_carrusel);
                initView();
                directories.clear();
        }

        private void initView() {
                backImage = findViewById(R.id.backImage);
                imageView19 = findViewById(R.id.imageView19);
                imageButton3 = findViewById(R.id.imageButton3);
                imageButton4 = findViewById(R.id.imageButton4);
                imageButton5 = findViewById(R.id.imageButton5);
                imageButton6 = findViewById(R.id.imageButton6);
                imageButton7 = findViewById(R.id.imageButton7);
                imageButton8 = findViewById(R.id.imageButton8);
                imageButton9 = findViewById(R.id.imageButton9);

                backImage.setOnClickListener(this);
                imageView19.setOnClickListener(this);
                imageView19.setOnLongClickListener(this);
                imageButton3.setOnClickListener(this);
                imageButton3.setOnLongClickListener(this);
                imageButton4.setOnClickListener(this);
                imageButton4.setOnLongClickListener(this);
                imageButton5.setOnClickListener(this);
                imageButton5.setOnLongClickListener(this);
                imageButton6.setOnClickListener(this);
                imageButton6.setOnLongClickListener(this);
                imageButton7.setOnClickListener(this);
                imageButton7.setOnLongClickListener(this);
                imageButton8.setOnClickListener(this);
                imageButton8.setOnLongClickListener(this);
                imageButton9.setOnClickListener(this);
                imageButton9.setOnLongClickListener(this);

                eraseFolder= findViewById(R.id.eraseFolder);
                eraseFolder.setOnClickListener(this);

                sawLast= findViewById(R.id.sawLast);
                sawLast.setOnClickListener(this);

                guardarFotosButon = findViewById(R.id.guardarFotosButon);
                guardarFotosButon.setOnClickListener(this);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        if (lastClickedImageButton != null) {
                                lastClickedImageButton.setImageBitmap(imageBitmap);
                                // Save the image to a temporary file
                              //  tempImageFile = saveTempImage(imageBitmap);
                                tempImageFiles.add(saveTempImage(imageBitmap));
                        }
                }
        }

        private File saveTempImage(Bitmap bitmap) {
                File tempDir = getCacheDir();
                File tempFile = null;
                try {
                        tempFile = File.createTempFile("temp_image", ".jpg", tempDir);
                        FileOutputStream out = new FileOutputStream(tempFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();
                        // Log the temporary file directory
                        Log.e("carrusel1", "Temporary file path: " + tempFile.getAbsolutePath());
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return tempFile;
        }

        private void showImageDialog(Bitmap imageBitmap) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_image_preview, null);
                ImageView imageView = dialogView.findViewById(R.id.dialogImageView);
                imageView.setImageBitmap(imageBitmap);
                // Find the 'x' button
                ImageButton closeButton = dialogView.findViewById(R.id.btnClose);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                // Set OnClickListener for the 'x' button
                closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // Dismiss the dialog
                                dialog.dismiss();
                                if (lastClickedImageButton != null) {
                                        lastClickedImageButton.setImageResource(R.drawable.ic_cameraportraitico);
                                }
                        }
                });

                dialog.show();
        }

        private void openCamera() {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
        }
        private void showLastClickedImagePaths() {
                if (!tempImageFiles.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Last Clicked Image Paths");

                        // Create a LinearLayout to hold the image views and path text views
                        LinearLayout layout = new LinearLayout(this);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                        for (File tempImageFile : tempImageFiles) {
                                // Inflate the custom layout for each image
                                View itemView = getLayoutInflater().inflate(R.layout.dialog_image_with_path, null);
                                ImageView imageView = itemView.findViewById(R.id.dialogImageView);
                                TextView pathTextView = itemView.findViewById(R.id.pathTextView);

                                // Set image and path for the current image
                                imageView.setImageURI(Uri.fromFile(tempImageFile));
                                pathTextView.setText("Path: " + tempImageFile.getAbsolutePath());

                                // Add the itemView to the layout
                                layout.addView(itemView);
                        }

                        // Add the layout to the dialog
                        ScrollView scrollView = new ScrollView(this);
                        scrollView.addView(layout);
                        builder.setView(scrollView);

                        // Set positive button
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                }
                        });

                        // Show the dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
                } else {
                        Toast.makeText(this, "No images have been clicked yet.", Toast.LENGTH_SHORT).show();
                }
        }
        private void moveImagesToPhotosFolder() {
                for (File tempImageFile : tempImageFiles) {
                        if (tempImageFile.exists()) {
                                try {
                                        // Convert image file to Base64
                                        String base64Image = convertImageToBase64(tempImageFile);

                                        if (!base64Image.isEmpty()) {
                                                // Save Base64 image in memory
                                                saveBase64ImageInMemory(base64Image, tempImageFile.getName());
                                                Log.e("carrusel1", "Image saved in memory: " + tempImageFile.getName());
                                                Log.e("carrusel1", "Image saved in memory: " + tempImageFile.getAbsolutePath());
                                                directories.add(tempImageFile.getAbsolutePath());
                                        } else {
                                                Log.e("carrusel1", "Base64 image string is empty");
                                        }
                                } catch (IOException e) {
                                        Log.e("carrusel1", "Error saving image in memory: " + e.getMessage());
                                }
                        } else {
                                Log.e("carrusel1", "Source file does not exist: " + tempImageFile.getAbsolutePath());
                        }
                }

               // tempImageFiles.clear(); // Clear the list of temporary image files
        }

        private String convertImageToBase64(File imageFile) throws IOException {
                FileInputStream inputStream = new FileInputStream(imageFile);
                byte[] buffer = new byte[(int) imageFile.length()];
                inputStream.read(buffer);
                inputStream.close();
                String base64Image = Base64.encodeToString(buffer, Base64.DEFAULT);
                Log.e("carrusel1", "Base64 image length: " + base64Image.length());
                return base64Image;
        }

        private void saveBase64ImageInMemory(String base64Image, String filename) throws IOException {
                byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);

                File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File imagesDir = new File(picturesDir, "MyImages");
                if (!imagesDir.exists()) {
                        if (!imagesDir.mkdirs()) {
                                Log.e("carrusel1", "Failed to create directory: " + imagesDir.getAbsolutePath());
                                return;
                        }
                }

                File imageFile = new File(imagesDir, filename);
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                outputStream.write(decodedBytes);
                outputStream.close();
        }
        private void cleanFolder(){
                //.Toast.makeText(this, "Eliminar todo", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.IMAGE_DIRECTORY, null);
                editor.commit();
                File picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File imagesDir = new File(picturesDir, "MyImages");
                if (imagesDir.exists() && imagesDir.isDirectory()) {
                        File[] files = imagesDir.listFiles();
                        if (files != null) {
                                for (File file : files) {
                                        if (file.isFile()) {
                                                if (file.delete()) {
                                                        Log.d("DeleteFiles", "Deleted file: " + file.getAbsolutePath());
                                                } else {
                                                        Log.e("DeleteFiles", "Failed to delete file: " + file.getAbsolutePath());
                                                }
                                        }
                                }
                        }
                } else {
                        Log.e("DeleteFiles", "Images directory not found: " + imagesDir.getAbsolutePath());
                }
        }
        private void savePathsOnShared(){
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < directories.size(); i++) {
                        stringBuilder.append(directories.get(i));
                        if (i < directories.size() - 1) {
                                stringBuilder.append(", "); // Append delimiter except for the last element
                        }
                }
                Log.e("",""+directories);
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstants.IMAGE_DIRECTORY, String.valueOf(stringBuilder));
                editor.commit();
        }


        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.eraseFolder:
                                cleanFolder();
                                break;
                        case  R.id.sawLast:
                                showLastClickedImagePaths();
                                break;
                        case R.id.guardarFotosButon:
                                // Handle saving photos
                                if(!tempImageFiles.isEmpty()) {
                                        moveImagesToPhotosFolder();
                                        savePathsOnShared();
                                }else {
                                        cleanFolder();
                                }
                                onBackPressed();

                                break;
                        case R.id.backImage:
                                onBackPressed();
                                break;
                        case R.id.imageView19:
                        case R.id.imageButton3:
                        case R.id.imageButton4:
                        case R.id.imageButton5:
                        case R.id.imageButton6:
                        case R.id.imageButton7:
                        case R.id.imageButton8:
                        case R.id.imageButton9:
                                // Set the lastClickedImageButton to the currently clicked ImageButton
                                lastClickedImageButton = (ImageButton) v;
                                // Open camera to capture image
                                openCamera();
                                break;
                }
        }

        @Override
        public boolean onLongClick(View v) {
                Drawable drawable = null;
                switch (v.getId()) {
                        case R.id.imageView19:
                                drawable = imageView19.getDrawable();
                                break;
                        case R.id.imageButton3:
                                drawable = imageButton3.getDrawable();
                                break;
                        case R.id.imageButton4:
                                drawable = imageButton4.getDrawable();
                                break;
                        case R.id.imageButton5:
                                drawable = imageButton5.getDrawable();
                                break;
                        case R.id.imageButton6:
                                drawable = imageButton6.getDrawable();
                                break;
                        case R.id.imageButton7:
                                drawable = imageButton7.getDrawable();
                                break;
                        case R.id.imageButton8:
                                drawable = imageButton8.getDrawable();
                                break;
                        case R.id.imageButton9:
                                drawable = imageButton9.getDrawable();
                                break;
                }
                if (drawable instanceof BitmapDrawable) {
                        Bitmap imageBitmap = ((BitmapDrawable) drawable).getBitmap();
                        // Show the image dialog
                        showImageDialog(imageBitmap);
                } else {
                        Toast.makeText(this, "No existe una imagen", Toast.LENGTH_SHORT).show();
                }
                return true; // Consume the long click event
        }
}
