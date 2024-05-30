package com.fhl.sistemadedistribucionfh.evidence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.ResoponseTicketsDetail;
import com.fhl.sistemadedistribucionfh.evidence.adapter.adapterEvidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.view.checklistEvidence;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.evidence.model.objectEvidence;
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenterImpl;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.calificacion;
import com.fhl.sistemadedistribucionfh.evidence.signature.signature;
import com.fhl.sistemadedistribucionfh.evidence.videos.videoRecord;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.gson.Gson;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class evidencia extends AppCompatActivity implements View.OnClickListener,evidenceView {
    public static final String TAG = evidencia.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static final int REQUEST_CODE = 1234;
    private ConstraintLayout firma, foto, archivos, rating, video;
    private Float frating;
    private String signatureBase64,inputTextSignature,currusel,ffiles,stars,fvideos,fchecklist="";
    private ImageView star,signatureImage,cameraico,clipDocs, buttonBack;
    private Boolean mfirma,mfoto,mfiles,mrating,mvideos,mchecklist=false;
    private Button sendEvidence,checkLotes;
    private ImageButton eraseShared;
    private requestEvidencePresenter presenter;

    private Integer secuenceRequest = 1;
    private Integer flujoId = 0;
    private String folioTicket, changeStatusTicket;
    private List<dataTicketsManifestV2> data;
    private Integer iterateidTickets = 0;
    private String currentManifest, sentripPlusFlow;
    private Boolean isArrayofTickets = false;
    private loaderFH progress;
    private List<dataTicketsDetailsendtrip> dataTicketSendtrip;
    private String detailTicket;
    private Integer flowDetail = 0;
    private adapterEvidence adapter;
    private RecyclerView rv;
    private TextView textFol;
    private List<objectEvidence> evidenceList= new ArrayList<>();
    private Boolean showSendEvidenceAfterLotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSendEvidenceAfterLotes=false;
        setContentView(R.layout.activity_evidence);
        Intent intent = getIntent();

        // Retrieve the Bundle from the Intent
        Bundle bundle = intent.getExtras();

        // Check if the bundle is not null
        if (bundle != null) {
            // Retrieve the integer value using the key "key_integer"
            flujoId = bundle.getInt("flujoId");
            currentManifest = bundle.getString("currentManifest");
            sentripPlusFlow = bundle.getString("sentripPlusFlow");
            folioTicket = bundle.getString("folioTicket");
            detailTicket = bundle.getString("detailString");//esto es el detalle de tickets para el flujo cerrar viaje(Entregado)
            data = (List<dataTicketsManifestV2>) bundle.getSerializable("dataTcikets");

            // Now intValue contains the value passed from the previous activity
            // You can use this value as needed
            // For example, you can log it or display it in a TextView
            Log.d("EvidenciaActivity", "Retrieved integer value: " + flujoId);
            if (data != null) {
                Log.e("EvidenciaActivity", "folio " + folioTicket + " data " + data.size());
            } else {
                Log.e("EvidenciaActivity", "folio " + folioTicket + " data : null");
            }
            if (detailTicket != null) {//todo esto es si es cerrar viaje
                flowDetail = 1;
                Gson gson = new Gson();
                String jsonstring = gson.toJson(detailTicket);
                jsonstring = jsonstring.replace("\\", "");
                //    Type listType = new TypeToken<List<dataDetailTickets>>(){}.getType();
                ResoponseTicketsDetail jsonObje = gson.fromJson(detailTicket, ResoponseTicketsDetail.class);

                Log.e("EvidenciaActivity", "json   " + jsonstring);// esto es para uno
                if (jsonObje.getData().get(0).getEvidenciaLlegada() != null) {//esto en caso de no haber evidencias de llegada
                    Log.e("EvidenciaActivity", "" + jsonObje.getData().get(0).getEvidenciaLlegada().size());
                }
                if (jsonObje.getData().get(0).getEvidenciaSalida() != null) {//esto en caso de no haber evidencias de salida
                    Log.e("EvidenciaActivity", "" + jsonObje.getData().get(0).getEvidenciaSalida().size());
                }
                if (jsonObje.getData().get(0).getCheckList() != null) {//esto en caso de no haber checklist
                    Log.e("EvidenciaActivity", "" + jsonObje.getData().get(0).getCheckList().size());
                }
            } else {
                flowDetail = 2;//todo esto es si viene de recoleccion
            }
        }
        initView();
        setInitConditions();
        sendEvidence.setVisibility(View.GONE);
        checkShared();//todo este metodo se pone al inicio ya que setea valores que recive el on resume
    }
    private void setInitConditions(){//
        mfirma=true;
        mrating=true;
        mfoto=false;
        mfiles=false;
        mvideos=false;
        mchecklist = false;
    }

    private void checkShared() {//todo agregar video

        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String signature = preferences.getString(GeneralConstants.SIGNATURE_B64_DIR, null);
        String inputText = preferences.getString(GeneralConstants.INPUT_TEXT_SIGTURE, null);
        String images = preferences.getString(GeneralConstants.IMAGE_DIRECTORY, null);
        String docs = preferences.getString(GeneralConstants.DOCS_DIRECTORY, null);
        String rate = preferences.getString(GeneralConstants.RATE_STARS, null);
        String video = preferences.getString(GeneralConstants.VIDEO_DIRECTORY, null);
        String checklist = preferences.getString(GeneralConstants.CHECKLIST_EVIDENCE, null);


        if (signature != null && inputText != null) {
            signatureImage.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Firma")){
                    ev.setTaken(true);
                    break;
                }
            }
            mfirma = true;
            signatureBase64 = signature;
            inputTextSignature = inputText;
            if (adapter != null && !signature.equals("")) {
                adapter.updatefirma(mfirma);
            }
        } else {
            signatureImage.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Firma")){
                    ev.setTaken(false);
                    break;
                }
            }
            mfirma = false;
            signatureBase64 = "";
            inputTextSignature = "";
            if (adapter != null) {
                adapter.updatefirma(mfirma);
            }
        }
        if (images != null&&!images.equals("")) {
            cameraico.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Fotos")){
                    ev.setTaken(true);
                    break;
                }
            }
            mfoto = true;
            currusel = images;
            Log.e("FHvideoR","foto "+fvideos);
            if (adapter != null ) {
                adapter.foto(mfoto);
            }
        } else {
            cameraico.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Fotos")){
                    ev.setTaken(false);
                    break;
                }
            }
            mfoto = false;
            currusel = "";
            if (adapter != null) {
                adapter.foto(mfoto);
            }
        }
        if (docs != null) {
            clipDocs.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Documentos")){
                    ev.setTaken(true);
                    break;
                }
            }
            mfiles = true;
            ffiles = docs;
            if (adapter != null && !docs.equals("")) {
                adapter.archivo(mfiles);
            }
        } else {
            clipDocs.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Documentos")){
                    ev.setTaken(false);
                    break;
                }
            }
            mfiles = false;
            ffiles = "";
            if (adapter != null) {
                adapter.archivo(mfiles);
            }
        }
        if (rate != null&&!rate.equals("")) {
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Review")){
                    ev.setTaken(true);
                    break;
                }
            }
            frating = Float.valueOf(rate);
            star.setColorFilter(Color.rgb(0, 187, 41), PorterDuff.Mode.SRC_ATOP);
            mrating = true;
            stars = rate;
            if (adapter != null && !rate.equals("")) {
                adapter.encuesta(mrating);
            }
        } else {
            star.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Review")){
                    ev.setTaken(false);
                    break;
                }
            }
            mrating = false;
            stars = "";
            if (adapter != null) {
                adapter.encuesta(mrating);
            }
        }
        if (video != null&&!video.equals("")) {
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Videos")){
                    ev.setTaken(true);
                    break;
                }
            }
            mvideos = true;
            fvideos = video;
            Log.e("FHvideoR","video "+fvideos);
            if (adapter != null && !video.equals("")) {
                adapter.video(mvideos);
            }
        } else {
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Videos")){
                    ev.setTaken(false);
                    break;
                }
            }
            mvideos = false;
            fvideos = "";
            if (adapter != null) {
                adapter.video(mvideos);
            }
        }
        if (checklist != null) {
            if (checklist.equals("true")) {
                for(objectEvidence ev:evidenceList){
                    if(ev.getEvidence().equals("Checklist")){
                        ev.setTaken(true);
                        break;
                    }
                }
                mchecklist = true;
                if (adapter != null && !checklist.equals("")) {
                    adapter.checklist(mchecklist);
                }
            } else {
                for(objectEvidence ev:evidenceList){
                    if(ev.getEvidence().equals("Checklist")){
                        ev.setTaken(false);
                        break;
                    }
                }
                mchecklist = false;
                if (adapter != null) {
                    adapter.checklist(mchecklist);
                }

            }
        } else {
            for(objectEvidence ev:evidenceList){
                if(ev.getEvidence().equals("Checklist")){
                    ev.setTaken(false);
                    break;
                }
            }
            mchecklist = false;
            if (adapter != null) {
                adapter.checklist(false);
            }
        }
        validateAll();
    }
    private void validateAll(){
        if(evidenceList!=null&&!evidenceList.isEmpty()) {
          Gson gson= new Gson();
          String json= gson.toJson(evidenceList);
            if (allEvidenceTaken(evidenceList)) {
                sendEvidence.setVisibility(View.VISIBLE);
                Log.e("empaque", "" + json);
            } else {
                sendEvidence.setVisibility(View.GONE);
                Log.e("empaque", "" + json);
            }
        }else{
            sendEvidence.setVisibility(View.GONE);
        }
    }

    public void setinitValues(Integer hassignature, Integer hasReview, Integer hasphotos, Integer hasdocuments, Integer hasvideos, Integer haschecklist) {
        Log.e("empaque", "INIT VALUES mfirma: " + hassignature + " mfoto: " + hasphotos + " mfiles: " + hasdocuments + " mrating:" + hasReview + " mvideos:" + hasvideos + " mchecklist: " + haschecklist);
        evidenceList.clear();
        if(hassignature==1){
            evidenceList.add(new objectEvidence("Firma",mfirma,false));
        }
        if(hasReview==1){
            evidenceList.add(new objectEvidence("Review",mrating,false));
        }
        if(hasphotos==1){
            evidenceList.add(new objectEvidence("Fotos",true,false));
        }
        if(hasdocuments==1){
            evidenceList.add(new objectEvidence("Documentos",true,false));
        }
        if(hasvideos==1){
            evidenceList.add(new objectEvidence("Videos",true,false));

        }if(haschecklist==1){
            evidenceList.add(new objectEvidence("Checklist",true,false));
        }

        validateAll();
    }
    public static boolean allEvidenceTaken(List<objectEvidence> evidenceList) {
        for (objectEvidence evidence : evidenceList) {
            if (!evidence.getTaken()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkShared();
        validateAll();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //checkShared();
    }

    private void initView() {
        buttonBack = findViewById(R.id.imageView25);
        buttonBack.setOnClickListener(this);
        firma=findViewById(R.id.firma);
        foto=findViewById(R.id.foto);
        archivos=findViewById(R.id.archivos);
        rating=findViewById(R.id.ratingd);
        cameraico =findViewById(R.id.cameraico);
        star=findViewById(R.id.imageMenu3);
        clipDocs=findViewById(R.id.clipDocs);
        eraseShared=findViewById(R.id.eraseShared);

//        video= findViewById(R.id.video);
//        video.setOnClickListener(this);
        eraseShared.setOnClickListener(this);

        firma.setOnClickListener(this);
        foto.setOnClickListener(this);
        archivos.setOnClickListener(this);
        rating.setOnClickListener(this);
        signatureImage = findViewById(R.id.signatureImage);
        rv = findViewById(R.id.rvEvidence);
        textFol = findViewById(R.id.textFol);
        sendEvidence = findViewById(R.id.sendEvidence);
        sendEvidence.setOnClickListener(this);
        checkLotes = findViewById(R.id.checkLotes);
        checkLotes.setOnClickListener(this);
        sendEvidence.setVisibility(View.GONE);
        progress = new loaderFH();
        //icons
        presenter = new requestEvidencePresenterImpl(this, getBaseContext());
        presenter.tokenAvocado();

    }

    @Override// este metodo se ejecuta despues de que el token con walmart esta ok
    public void validateSendtrip() {
        if (folioTicket != null) {/** si es un solo folio**/
            // isArrayofTickets=false;
            Log.e("folioTSendtrip", "es un solo folio");
            presenter.requestDetailTicketsSendtriplus(false, iterateidTickets, currentManifest, null, folioTicket);
            changeStatusTicket = folioTicket;

        } else {
            if (data != null) {// si es un arreglo de folios
                Log.e("folioTSendtrip", "son varios folios");
                if (data.size() > 1) {// si solo es un folio
                    //  isArrayofTickets=true;
                    presenter.requestDetailTicketsSendtriplus(
                            true, iterateidTickets, currentManifest, data.get(iterateidTickets).getFolioTicket(), null);//
                    changeStatusTicket = data.get(iterateidTickets).getFolioTicket();
                } else {// si son varios folios
                    //    isArrayofTickets=false;
                    presenter.requestDetailTicketsSendtriplus(
                            true, iterateidTickets, currentManifest, data.get(0).getFolioTicket(), null);//
                    changeStatusTicket = data.get(0).getFolioTicket();
                }
            } else {

            }
        }
    }

    @Override
    public void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> dataTicketSendtrip) {
        this.dataTicketSendtrip = dataTicketSendtrip;
        dataTicketSendtrip.get(0).getFolioTicket();
        Gson gson = new Gson();
        String json = gson.toJson(dataTicketSendtrip);
        Log.e("detailticket", " json ticket:" + json);
        Log.e("detailticket", " flowdetail " + flowDetail);
        fillEvidenceRequired(flowDetail, dataTicketSendtrip);//2para test
        textFol.setText("Folio: " + dataTicketSendtrip.get(0).getFolioTicket());
    }

    private void fillEvidenceRequired(Integer flowDetail, List<dataTicketsDetailsendtrip> dataTicketSendtrip) {
        adapter = new adapterEvidence(this, flowDetail, getApplicationContext(), dataTicketSendtrip);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());//GridLayoutManager(getApplicationContext(),3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        //z checkShared();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView25:
                onBackPressed();
                break;

            case R.id.firma:
                Log.e("evidence", "firma ");
                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
                Intent intentfirma = new Intent(this, signature.class);
                startActivity(intentfirma);
                break;
            case R.id.foto:
                Log.e("evidence", "foto ");
                Intent fotos = new Intent(this, carrusel.class);
                startActivity(fotos);
                break;
            case R.id.archivos:
                Intent files = new Intent(this, documents.class);
                startActivity(files);
                Log.e("evidence", "archivos ");
                break;
            case R.id.ratingd:
                Intent rating = new Intent(this, calificacion.class);
                startActivity(rating);
                Log.e("evidence", "rating ");
                break;
            case R.id.video:
                Intent video = new Intent(this, videoRecord.class);
                startActivity(video);
                break;
            case R.id.eraseShared:
                for(objectEvidence ev: evidenceList){
                    ev.setTaken(false);
                }
                removeShared();
                signatureImage.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfirma = false;
                cameraico.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfoto = false;
                clipDocs.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfiles = false;
                star.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mrating = false;
                secuenceRequest = 1;
                checkShared();
                break;
            case R.id.checkLotes:
                Toast.makeText(this, "Verificar lotes", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(this.getApplicationContext(), BarcodeScannerActivity2.class);
                bundle.putSerializable("dataTcikets",(Serializable) dataTicketSendtrip);
                bundle.putString("currentManifest",currentManifest);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.sendEvidence://la primera vez la firma lo manda con esto

                if (folioTicket != null) {
                    Log.e("sendEvidence", "folio " + folioTicket);
                    isArrayofTickets = false;
                    presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket, fvideos);
                    Log.e("sendEvidence", "signatureBase64: " + signatureBase64 + "\n" +
                            "inputTextSignature: " + inputTextSignature + "\n" +
                            "carrusel:" + currusel + "\n" +
                            "ffiles: " + ffiles + "\n" +
                            "stars: " + stars + "\n" +
                            "secuenceRequest: " + secuenceRequest);
                } else {
                    if (data != null) {
                        Log.e("sendEvidence", " data " + data.size());
                        if (data.size() > 1) {
                            isArrayofTickets = true;
                            sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(iterateidTickets).getFolioTicket(), fvideos);
                        } else {
                            isArrayofTickets = false;
                            sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(0).getFolioTicket(), fvideos);
                        }
                    } else {
                        Toast.makeText(this, "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
//region  escaner verificar lotes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Recibe los datos desde Activity2
            String result = data.getStringExtra("result_key");
            Log.e("qrs","onactivityResult "+result);
            // Haz algo con el resultado, por ejemplo, pasarlo al fragmento
            sendResultToFragment(result);
        }
    }
    private void sendResultToFragment(String result) {
        this.showSendEvidenceAfterLotes=true;
        //presenter.saveLotes();
        // Aquí puedes manejar el resultado y actualizar tu UI en el DialogFragment
    }
    //endregion
    private void sendEvidenceIfArrayofTickets(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket, String fvideos) {
        presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket, fvideos);
    }

    private void removeShared() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(GeneralConstants.SIGNATURE_B64_DIR);
        editor.remove(GeneralConstants.INPUT_TEXT_SIGTURE);
        editor.remove(GeneralConstants.IMAGE_DIRECTORY);
        editor.remove(GeneralConstants.VIDEO_DIRECTORY);
        editor.remove(GeneralConstants.DOCS_DIRECTORY);
        editor.remove(GeneralConstants.RATE_STARS);
        editor.remove(GeneralConstants.CHECKLIST_EVIDENCE);
        editor.remove(GeneralConstants.PREFS_NAME);
        editor.remove(GeneralConstants.KEY_CHECK_LIST);
        editor.apply();
    }

    private void cleanFolder() {
        //.Toast.makeText(this, "Eliminar todos", Toast.LENGTH_SHORT).show();

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

    private void gotomanifestV2() {
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void setMessage() {
        Log.e("videoVar",""+fvideos);
        if (secuenceRequest < 5) {//continuea el carrusel los archivos y la encuesta
            secuenceRequest = secuenceRequest + 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("sendEvidence", "signatureBase64: " + signatureBase64 + "\n" +
                            "inputTextSignature: " + inputTextSignature + "\n" +
                            "carrusel: " + currusel + "\n" +
                            "ffiles: " + ffiles + "\n" +
                            "stars: " + stars + "\n" +
                            "secuenceRequest: " + secuenceRequest);
                    if (folioTicket != null) {
                        presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket, fvideos);
                    } else {
                        if (data != null) {
                            sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(iterateidTickets).getFolioTicket(), fvideos);
                        } else {
                            Toast.makeText(getApplicationContext(), "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, 4000);

        } else if (secuenceRequest == 5) {//envia la encuesta  falta el comentario
            secuenceRequest = secuenceRequest + 1;
            Log.e("sendEvidence", "sendEvidence: " + secuenceRequest + " sendRate: " + stars);
            int rating = (int) Math.round(Double.parseDouble(stars));
            if (folioTicket != null) {
                presenter.sendRate(rating, folioTicket);
                changeStatusTicket = folioTicket;
            } else {
                if (data != null) {
                    presenter.sendRate(rating, data.get(iterateidTickets).getFolioTicket());
                    changeStatusTicket = data.get(iterateidTickets).getFolioTicket();
                } else {
                    Toast.makeText(this, "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                }
            }
            //Toast.makeText(this, "mandar estrellas", Toast.LENGTH_SHORT).show();
        } else if (secuenceRequest == 6) {
            secuenceRequest = secuenceRequest + 1;
            Log.e("sendEvidence", "Se envia a sendtripplus");
            if (sentripPlusFlow.equals("Recoleccion")) {
                presenter.sendSentriplus(currentManifest, dataTicketSendtrip, sentripPlusFlow);
            } else if (sentripPlusFlow.equals("Entrega")) {
                //presenter.sendSentriplus(currentManifest,dataTicketSendtrip,sentripPlusFlow);
                secuenceRequest = secuenceRequest + 1;
                presenter.changeStatusManifestTicket(currentManifest, changeStatusTicket, sentripPlusFlow);

            }

            /// presenterchange statusmanifest ESTO YA LO HACEE JOSE

        } else if (secuenceRequest == 7) {
            secuenceRequest = secuenceRequest + 1;
            presenter.changeStatusManifestTicket(currentManifest, changeStatusTicket, sentripPlusFlow);

        } else if (secuenceRequest == 8) {//borra  lo relacionano y regresa
            Toast.makeText(this, "usar sendtrip plus Cambiar estatus y regresar a manifiestos", Toast.LENGTH_SHORT).show();

            if (!isArrayofTickets) {// si es solo uno manda el manifiesto
                presenter.hideDialog();
                removeShared();
                cleanFolder();
                gotomanifestV2();

            } else {// si son variso tickets repite el proceso

                iterateidTickets = iterateidTickets + 1;
                Log.e("sendEvidence", " tickets " + iterateidTickets + " data: " + data.size());
                if (iterateidTickets > (data.size() - 1)) {// si es el ultimo ticket va a manifiestos
                    presenter.hideDialog();
                    removeShared();
                    cleanFolder();
                    gotomanifestV2();

                } else {// si no es el ultimo ticket vuelve a iterar otra vez secuenceRequest sirve para mandar primero firma luego fotos luego archivos y por ultimo calificacion
                    secuenceRequest = 1;
                    //todo
                    //todo
                    /*****///TODO SI COMENTAS ESTA LINEA SE DEJA DE HACER EL ENVIO DE EVIDENCIAAS EN AUTOMATICO DE FORMA SECUENCIAL
                    //  sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId,data.get(iterateidTickets).getFolioTicket());

                    //todo
                    presenter.hideDialog();
                    removeShared();
                    cleanFolder();
                    presenter.requestDetailTicketsSendtriplus(true, iterateidTickets, currentManifest, data.get(iterateidTickets).getFolioTicket(), null);// revisar si esto se ejecuta correctamente ya que pide el detalle del ticket siguiente para el sendtriplus
                }
            }

        } else {
            Toast.makeText(this, "Todos los archivos se han enviado correctamente", Toast.LENGTH_SHORT).show();
            // regresar a manifiestos y limpiar toda la carpeta de archivos
            presenter.hideDialog();
        }
    }

    @Override
    public void showDialog() {
        if (progress != null && !progress.isVisible()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("HAS_TITLE", false);
            bundle.putString("title", "Cargando detalles");
            progress.setArguments(bundle);
            progress.show(this.getSupportFragmentManager(), loaderFH.TAG);
        }

    }

    @Override
    protected void onDestroy() {
        removeShared();
        cleanFolder();
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        removeShared();
        cleanFolder();
        super.onBackPressed();
    }

    @Override
    public void hideDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress != null && this != null)
                    progress.dismiss();
            }
        }, 300);
    }

    public void gosignature() {
        Intent intentfirma = new Intent(this, signature.class);
        startActivity(intentfirma);
    }

    public void goCarrusel(List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciallegada) {
        Log.e("evidence", "foto ");//TODO manejar bundle para el tipo de evidencias del carrusel
        Bundle bundle = new Bundle();
        if (evidenciaSalida != null) {
            bundle.putSerializable("evidenciaSalida", (Serializable) evidenciaSalida);
        }
        if (evidenciallegada != null) {
            bundle.putSerializable("evidenciaLlegada", (Serializable) evidenciallegada);
        }
        Intent fotos = new Intent(this, carrusel.class);
        fotos.putExtras(bundle);
        startActivity(fotos);
    }

    public void goVideos(List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciaLlegada, Integer flowDetail) {
        Bundle bundle = new Bundle();
        bundle.putInt("flowDetail", flowDetail);

        if (evidenciaSalida != null) {
            bundle.putSerializable("evidenciaSalida", (Serializable) evidenciaSalida);
            Log.e("detailticket","ES"+evidenciaSalida.size());
        }
        if (evidenciaLlegada != null) {
            bundle.putSerializable("evidenciaLlegada", (Serializable) evidenciaLlegada);
            Log.e("detailticket","ELl"+evidenciaLlegada.size());
        }
        Intent video = new Intent(this, videoRecord.class);
        video.putExtras(bundle);
        startActivity(video);
    }

    public void goDocuments(List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciaLlegada, Integer flowDetail) {
        Bundle bundle = new Bundle();
        bundle.putInt("flowDetail", flowDetail);
        if (evidenciaSalida != null) {
            bundle.putSerializable("evidenciaSalida", (Serializable) evidenciaSalida);
        }
        if (evidenciaLlegada != null) {
            bundle.putSerializable("evidenciaLlegada", (Serializable) evidenciaLlegada);
        }
        Intent files = new Intent(this, documents.class);

        files.putExtras(bundle);
        startActivity(files);
        Log.e("evidence", "archivos ");
    }


    public void goReview() {
        Intent rating = new Intent(this, calificacion.class);
        startActivity(rating);
        Log.e("evidence", "rating ");
    }


    public void gochecklist(List<Check> checkList) {
        Bundle bundle = new Bundle();
        //Toast.makeText(this, "Crear intent de checklist", Toast.LENGTH_SHORT).show();
        if (checkList != null) {
            bundle.putSerializable("checklist", (Serializable) checkList);
        }
        bundle.putString("folioTicket", dataTicketSendtrip.get(0).getFolioTicket());
        Intent checklist = new Intent(this, checklistEvidence.class);
        checklist.putExtras(bundle);
        startActivity(checklist);
    }


}