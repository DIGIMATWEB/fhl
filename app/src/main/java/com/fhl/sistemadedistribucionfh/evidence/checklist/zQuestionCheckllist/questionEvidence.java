package com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.Cancelar.view.cancelarViaje;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.SendCheck;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.sendChecklist;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionsView;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.fhl.sistemadedistribucionfh.evidence.adapter.QuestionAdapterEvidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.presenter.presenterQuestionsEvidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.presenter.presenterQuestionsEvidenceImpl;
import com.fhl.sistemadedistribucionfh.evidence.checklist.view.checklistEvidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.view.questionsEvidenceView;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class questionEvidence extends Fragment implements View.OnClickListener , questionsEvidenceView {
    public static final String TAG = questionEvidence.class.getSimpleName();
    private Button buttonstartChecklist;
    private TextView helpertext;
    private ViewPager2 ViewPager;
    private List<Pregunta> mfdata;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String nombre,placa,vigencia,periodicida, folioTicket;
    private TextView namechecklist,vehiclTypeChecklist,manifestChecklist,statusChecklist;
    private Boolean ischeklistsetupok=false;
    private presenterQuestionsEvidence presenter;
    private sendChecklist sendChakclist;
    public List<SendCheck> checklist=null;
    private List<Pregunta> dataCheck = null;
    List<SendCheck> finalQ = new ArrayList<>();
    private Integer cvetemp, positionChecklist;
    private static Boolean indicatorRespuestasVacias;
    private File myimageFile;
    String currentPhotoPath;
    private Uri imageUri;
    Bitmap rotatedBitmap = null;
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private Integer positionTemp, vehiculoChkId, despachoId, vehiculoId, checklistId, llave;
    private String fechaAplicado;
    private Boolean checklistisOK=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Bundle args = getArguments();

        if (args != null) {
            nombre = args.getString("nombre", "");
            llave = args.getInt("llave", 0);
            folioTicket = args.getString("folioTicket", "");
            positionChecklist = args.getInt("posicionChecklist", 0);

            dataCheck = (List<Pregunta>) args.getSerializable("preguntas");
            Gson gson= new Gson();
            String json =gson.toJson(dataCheck);
            Log.e("jsonChecklist",""+json);

            /*if (vigencia.equals("Vigente")) {
                int mcolor = getContext().getColor(R.color.green);
                statusChecklist.setTextColor(mcolor);
            }else{
                int mcolor = getContext().getColor(R.color.red);
                statusChecklist.setTextColor(mcolor);
            }*/

            //vehiclTypeChecklist .setText(placa);
            //manifestChecklist.setText(periodicida );
            //statusChecklist .setText( vigencia );
        }

        initView(view);

        namechecklist.setText(nombre);

        return view;
    }


    private void initView(View view) {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        String vehicle= preferences.getString(GeneralConstants.VEHICLEID,null);

        buttonstartChecklist=view.findViewById(R.id.buttonstartChecklist);
        buttonstartChecklist.setOnClickListener(this);
        helpertext=view.findViewById(R.id.helpertext);
        ViewPager=view.findViewById(R.id.ViewPager);
        namechecklist=view.findViewById(R.id.namechecklist);
        vehiclTypeChecklist =view.findViewById(R.id.vehiclTypeChecklist);
        manifestChecklist=view.findViewById(R.id. manifestChecklist);
        statusChecklist  =view.findViewById(R.id. statusChecklist);
        ViewPager = view.findViewById(R.id.ViewPager);

        sendChakclist=new sendChecklist(checklist,user,2);
        presenter= new presenterQuestionsEvidenceImpl(this,getContext());

        // Esto no se necesita
        //presenter.requestQuestions(positionRespuestas,checklistId);
        setQuestiomns(dataCheck);
    }

    @Override
    public void setQuestiomns(List<Pregunta> mdata) {
        this.mfdata=mdata;
        if(mfdata!=null){
            checklist=new ArrayList<>();
            checklist.clear();
            for(Pregunta p: mfdata){
                checklist.add(new SendCheck(p.getId(),0,"",""));
            }
        }

        fillViewPager(mfdata);
        ischeklistsetupok=true;
    }

    @Override
    public void changeStatusManifestTicket() {
        // Datos de user y vehicle
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        String vehicle= preferences.getString(GeneralConstants.VEHICLEID,null);
        vehiculoId = Integer.valueOf(vehicle);
        checklistId = dataCheck.get(0).getCheckListId();

        // Este es el metodo que envia los datos
        Log.d("SendCheck: ", "data a enviar: " + Arrays.toString(checklist.toArray()));

        // Llenamos el arreglo de preguntas para su manejo correcto
        finalQ.clear();
        for(int i=0;i<checklist.size();i++) {
            finalQ.add(new SendCheck(checklist.get(i).getCvePregunta(), checklist.get(i).getCveAnswer(), checklist.get(i).getDescAnswerAbierta(), checklist.get(i).getArchivoDeEvidencia()));
        }

        // Creamos el json
        Gson gson = new Gson();
        String mfjson = gson.toJson(finalQ);

        // Mandamos los datos
        presenter.sendDataChecklist(mfjson, user, folioTicket, vehiculoId, checklistId);
    }

    @Override
    public void showDialog() {
        // Muestra el Dialog
    }

    @Override
    public void hideDialog() {
        // Oculta el DIalog
    }

    @Override
    public void closeCheckListError2() {
        //Limpiar variables
        checklist.clear();
        mfdata.clear();
        finalQ.clear();

        mangeF();
    }

    @Override
    public void successetCehcklist(Boolean status) {
        this.checklistisOK=status;
        //TODO hacer metodos faltantes
        //Limpiar variables
        checklist.clear();
        mfdata.clear();
        finalQ.clear();

        // Guardamos el resultado cuando es positivo
        if (status == false) {
            // Cuando es false
        } else {

            // Cuando es true
            // Verificamos si la posición es válida
            if (positionChecklist >= 0 && positionChecklist < checklistEvidence.checkList.size()) {
                // Obtenemos el objeto Check en la posición especificada
                Check sendCheck = checklistEvidence.checkList.get(positionChecklist);

                // Actualizamos el campo aplicado del objeto Check
                //sendCheck.setArchivoDeEvidencia(encoded);
                sendCheck.setAplicado(status);
            } else {
                // Manejar el caso en el que la posición especificada no es válida
                System.out.println("La posición especificada no es válida.");
            }

            // Recargamos la vista para cambiar el color
            //QuestionAdapterEvidence.updateViewPagerAtPosition();

            //mQuestionFragment.changeStatusCameraButton();
            Log.d("SendCheck despues de mandar el checklist: ", Arrays.toString(checklistEvidence.checkList.toArray()));
            //positionChecklist
        }
        //checklistEvidence.checkList.get(0).getAplicado();
        mangeF();
    }

    public void fillViewPager(List<Pregunta> mdata) {
        QuestionAdapterEvidence questionAdapter = new QuestionAdapterEvidence(mdata,this.getActivity(),this,ViewPager); // Pass the list of questions to the adapter
        ViewPager.setAdapter(questionAdapter);
    }

    public void showbutton() {
        buttonstartChecklist.setVisibility(View.VISIBLE);
        buttonstartChecklist.setText("Finalizar");
    }

    public void hidebutton() {
        buttonstartChecklist.setVisibility(View.GONE);
        buttonstartChecklist.setText("Iniciar");
    }

    private  void mangeF() {
        //TODO cambiar esto
//        Intent checklist = new Intent(this, checklistEvidence.class);
//        //checklist.putExtras(bundle);
//        startActivity(checklist);
        if(checklistisOK) {
            checklistEvidence checkEvidence = (checklistEvidence) getActivity();
            checkEvidence.setmypostandvalues(positionChecklist);
        }


        // Find the current fragment by tag if you have set a tag when adding/replacing the fragment
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    //Pressed return button - returns to the results menu
    public void onResume() { //onback
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("drahEventes"," "+keyCode+" "+event);
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    mangeF();
                    return true;
                }
                return false;
            }
        });
    }
    public void dismisedDialog() {
        // Toast.makeText(getContext(), "dismissed 4", Toast.LENGTH_SHORT).show();
        mangeF();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonstartChecklist:
                if (ischeklistsetupok) {
                    buttonstartChecklist.setVisibility(View.GONE);
                    helpertext.setVisibility(View.GONE);
                    ViewPager.setVisibility(View.VISIBLE);

                    // Si el boton dice Finalizar
                    if (buttonstartChecklist.getText().equals("Finalizar")) {
                        // Revisamos las preguntas antes de enviar
                        // Log.d("SendCheck: ","data a enviar: " + Arrays.toString(checklist.toArray()));
                        mostrarToastSiRespuestaVacias(checklist, mfdata);

                        // Dependiendo el resultado nos dejara avanzar o no
                        if (indicatorRespuestasVacias) {
                            // Es true
                            //Log.d("SendCheck: ","data a enviar: " + "True");
                            Toast.makeText(getContext(), "Send checklist ir a manifiestos", Toast.LENGTH_SHORT).show();

                            // Enviamos la data
                            changeStatusManifestTicket();

                        } else {
                            // Es false
                            // Log.d("SendCheck: ","data a enviar: " + "False");
                            Toast.makeText(getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                            showbutton();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "el checklist no esta seteado", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public static void mostrarToastSiRespuestaVacias(List<SendCheck> respuestas, List<Pregunta> questions) {
        // Log.d("SendCheck: ", "Datos en el PageSelector" + Arrays.toString(respuestas.toArray()));

        for (int position = 0; position < questions.size(); position++) {
            Pregunta question = questions.get(position);
            int tipoCampo = question.getTipoCampo();

            // Verificar si el tipo de campo es 1
            if (tipoCampo == 1) {
                // Obtener la respuesta correspondiente a esta pregunta
                SendCheck respuesta = respuestas.get(position);

                // Verificar si la respuesta está vacía o nula
                if (respuesta == null || respuesta.getCveAnswer() == null || respuesta.getCveAnswer().equals(0)) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La respuesta en la posición " + position + " está vacía");

                    //pagerIndicatorActual = position;
                    indicatorRespuestasVacias = false;

                    // Detener el proceso
                    return;
                }
            } else if (tipoCampo == 2) {
                // Verificar si la descripción de respuesta abierta está vacía o nula
                String descAnswerAbierta = respuestas.get(position).getDescAnswerAbierta();
                if (descAnswerAbierta == null || descAnswerAbierta.isEmpty()) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La descripción de respuesta abierta en la posición " + position + " está vacía");

                    //pagerIndicatorActual = position;
                    indicatorRespuestasVacias = false;

                    // Detener el proceso
                    return;
                }
            } else if (tipoCampo == 3) {
                // Obtener la respuesta correspondiente a esta pregunta
                SendCheck respuesta = respuestas.get(position);

                // Verificar si la respuesta está vacía o nula
                if (respuesta == null || respuesta.getCveAnswer() == null || respuesta.getCveAnswer().equals(0)) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La respuesta en la posición " + position + " está vacía");

                    //pagerIndicatorActual = position;
                    indicatorRespuestasVacias = false;

                    // Detener el proceso
                    return;
                }
            }
            indicatorRespuestasVacias = true;
        }

        // Si se completaron todas las validaciones, continuar con el proceso
        Log.d("SendCheck: ", "Todas las respuestas son válidas");
    }

    public void setAndswersF(int pos, Integer cvePregunta, Integer cveAnswer, String descAnswerAbierta, String evidencia) {
        this.positionTemp = pos;

        Log.d("SendCheck: ", "data: " + pos + cvePregunta + cveAnswer + descAnswerAbierta + evidencia);
        Log.d("SendCheck: ", "data necesaria: " + cvePregunta.toString() + cveAnswer.toString() + descAnswerAbierta + evidencia);

        //int ctmQuestion = checklist.get(position).getCveAnswer();
        //String observation = answersListFull.get(position).getObservation();

        //Creamos la variable para insertar los datos en el arreglo de Preguntas
        //SendCheck sendCheck = new SendCheck(cvePregunta, cveAnswer, descAnswerAbierta, evidencia);

        // Verifica que 'actualQuestion' sea una posición válida en el rango del ArrayList
        if (pos >= 0 && pos < checklist.size()) {
            // Realiza la actualización de 'CtmAnswer' en la posición 'actualQuestion'
            //checklist.set(pos, sendCheck);
            SendCheck sendCheck = checklist.get(pos);

            // Actualizamos los campos restantes del objeto SendCheck
            sendCheck.setCvePregunta(cvePregunta);
            sendCheck.setCveAnswer(cveAnswer);
            sendCheck.setDescAnswerAbierta(descAnswerAbierta);
        } else {
            // Error
            Log.e("TAG", "ERROR");
        }

        //Limpiamos las variables

        Log.d("SendCheck: ", Arrays.toString(checklist.toArray()));
    }

    public void updateArrayAnswersImages(String encoded) {
        Log.d("SendCheck despues de la foto: ", encoded);
        // Verificamos si la posición es válida
        if (positionTemp >= 0 && positionTemp < checklist.size()) {
            // Obtenemos el objeto SendCheck en la posición especificada
            SendCheck sendCheck = checklist.get(positionTemp);

            // Actualizamos el campo archivoDeEvidencia del objeto SendCheck
            sendCheck.setArchivoDeEvidencia(encoded);
        } else {
            // Manejar el caso en el que la posición especificada no es válida
            System.out.println("La posición especificada no es válida.");
        }

        // Recargamos la vista para cambiar el color
        QuestionAdapterEvidence.updateViewPagerAtPosition();

        //mQuestionFragment.changeStatusCameraButton();
        Log.d("SendCheck despues de la foto: ", Arrays.toString(checklist.toArray()));
    }

    public void takePick(Integer cveTripMgmQuestion, Integer position) {
        this.cvetemp = cveTripMgmQuestion;
        this.positionTemp = position;
        Log.e("SendCheck: ", "" + cveTripMgmQuestion);
        askCameraPermissions();
    }

    private void askCameraPermissions() {//todo pregunta por los permisos si existen va por la camara
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            //Metodo de abrir la Camara
            //openCamera();
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(requireActivity().getApplicationContext(),
                        "com.fhl.sistemadedistribucionfh.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        myimageFile = File.createTempFile(
                imageFileName,  /// prefix /
                ".jpg",         // suffix /
                storageDir      // directory /
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = myimageFile.getAbsolutePath();
        return myimageFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(imageUri!=null) {
        Log.e("photoFlow", "rC:   " + requestCode);
        Log.e("photoFlow", "resC: " + resultCode);
        Log.e("photoFlow", "data: " + data);
        //    Log.e("imagefromDecode1", "" + resultCode + "  " + imageUri);
        // Log.e("imagefromDecode1", "" + ":   " + "" + myimageFile);
        String pathImage = String.valueOf(imageUri);
        String substring = pathImage.substring(1);
        //urispahts.add(substring);

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getBase64();
                // File f = new File(currentPhotoPath);

                // imageViewP.setImageURI(Uri.fromFile(f));
                ErradicatedFiler();
            } else {
                //File file =new File(imageUri.getPath());

                ErradicatedFiler();
            }
        } else {
            Log.e("imagefromDecode1", "error decoding2");
        }

    }

    private void getBase64() {
        Bitmap bitmap1 = BitmapFactory.decodeFile(currentPhotoPath);
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, 460, 460, false);

        try {
            ExifInterface exifInterface = new ExifInterface(currentPhotoPath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 2, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        Log.e("photoFlow2", encoded);
        for (int i = 0; i < checklist.size(); i++) {
            if (checklist.get(i).getCvePregunta() == cvetemp) {
                checklist.get(i).setArchivoDeEvidencia(encoded);
            }
        }

        //Insertamos la imagen(base64) en el arreglo ImageInfoFull
        updateArrayAnswersImages(encoded);
    }

    private void ErradicatedFiler() {
        if (myimageFile.exists()) {
            try {
                myimageFile.getCanonicalFile().delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (myimageFile.exists()) {
                getContext().deleteFile(myimageFile.getName());
            }
        }
    }
}