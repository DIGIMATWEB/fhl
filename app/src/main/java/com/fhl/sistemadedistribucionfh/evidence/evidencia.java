package com.fhl.sistemadedistribucionfh.evidence;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.ResoponseTicketsDetail;
import com.fhl.sistemadedistribucionfh.evidence.adapter.adapterEvidence;
import com.fhl.sistemadedistribucionfh.evidence.documents.documents;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenter;
import com.fhl.sistemadedistribucionfh.evidence.presenter.requestEvidencePresenterImpl;
import com.fhl.sistemadedistribucionfh.evidence.rateDriver.calificacion;
import com.fhl.sistemadedistribucionfh.evidence.signature.signature;
import com.fhl.sistemadedistribucionfh.evidence.videos.videoRecord;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

public class evidencia extends AppCompatActivity implements View.OnClickListener,evidenceView {
    public static final String TAG = evidencia.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ConstraintLayout firma,foto,archivos,rating,video;
    private Float frating;
    private String signatureBase64,inputTextSignature,currusel,ffiles,stars="";
    private ImageView star,signatureImage,cameraico,clipDocs;
    private Boolean mfirma,mfoto,mfiles,mrating=false;
    private Button sendEvidence;
    private ImageButton eraseShared;
    private requestEvidencePresenter presenter;

    private Integer secuenceRequest=1;
    private Integer flujoId=0;
    private String folioTicket,changeStatusTicket;
    private List<dataTicketsManifestV2> data;
    private Integer iterateidTickets=0;
    private String currentManifest,sentripPlusFlow;
    private Boolean isArrayofTickets=false;
    private loaderFH progress;
    private List<dataTicketsDetailsendtrip> dataTicketSendtrip;
    private String detailTicket;
    private Integer flowDetail=0;
    private adapterEvidence adapter;
    private RecyclerView rv;
    private TextView textFol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidence);
        Intent intent = getIntent();

        // Retrieve the Bundle from the Intent
        Bundle bundle = intent.getExtras();

        // Check if the bundle is not null
        if (bundle != null) {
            // Retrieve the integer value using the key "key_integer"
             flujoId= bundle.getInt("flujoId");
            currentManifest = bundle.getString("currentManifest");
            sentripPlusFlow= bundle.getString("sentripPlusFlow");
            folioTicket= bundle.getString("folioTicket");
            detailTicket=bundle.getString("detailString");//esto es el detalle de tickets para el flujo cerrar viaje(Entregado)
            data= (List<dataTicketsManifestV2>) bundle.getSerializable("dataTcikets");

            // Now intValue contains the value passed from the previous activity
            // You can use this value as needed
            // For example, you can log it or display it in a TextView
            Log.d("EvidenciaActivity", "Retrieved integer value: " + flujoId);
            if(data!=null){
                Log.e("EvidenciaActivity","folio "+folioTicket+" data "+data.size());
            }else{
              Log.e("EvidenciaActivity","folio "+folioTicket+" data : null");
            }
            if(detailTicket!=null){
                flowDetail=1;
                Gson gson=new Gson();
                String jsonstring= gson.toJson(detailTicket);
                jsonstring = jsonstring.replace("\\", "");
            //    Type listType = new TypeToken<List<dataDetailTickets>>(){}.getType();
                ResoponseTicketsDetail jsonObje= gson.fromJson(detailTicket,ResoponseTicketsDetail.class);

                Log.e("EvidenciaActivity","json   "+jsonstring);// esto es para uno
                if(jsonObje.getData().get(0).getEvidenciaLlegada()!=null) {//esto en caso de no haber evidencias de llegada
                    Log.e("EvidenciaActivity", "" + jsonObje.getData().get(0).getEvidenciaLlegada().size());
                }
                if(jsonObje.getData().get(0).getEvidenciaSalida()!=null){//esto en caso de no haber evidencias de salida
                    Log.e("EvidenciaActivity",""+jsonObje.getData().get(0).getEvidenciaSalida().size());
                }
                if(jsonObje.getData().get(0).getCheckList()!=null) {//esto en caso de no haber checklist
                    Log.e("EvidenciaActivity", "" + jsonObje.getData().get(0).getCheckList().size());
                }
            }else {
                flowDetail=2;
            }
        }
        initView();
        checkShared();
    }

    private void checkShared() {
        SharedPreferences preferences = getBaseContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String signature = preferences.getString(GeneralConstants.SIGNATURE_B64_DIR, null);
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
//        video= findViewById(R.id.video);
//        video.setOnClickListener(this);
        eraseShared.setOnClickListener(this);
        firma.setOnClickListener(this);
        foto.setOnClickListener(this);
        archivos.setOnClickListener(this);
        rating.setOnClickListener(this);
        signatureImage=findViewById(R.id.signatureImage);
        rv=findViewById(R.id.rvEvidence);
        textFol=findViewById(R.id.textFol);
        sendEvidence =findViewById(R.id.sendEvidence);
        sendEvidence.setOnClickListener(this);
        sendEvidence.setVisibility(View.GONE);
        progress = new loaderFH();
        //icons
        presenter=new requestEvidencePresenterImpl(this,getBaseContext());
        presenter.tokenAvocado();

    }
    @Override// este metodo se ejecuta despues de que el token con walmart esta ok
    public void validateSendtrip() {
        if(folioTicket!=null) {/** si es un solo folio**/
           // isArrayofTickets=false;
            Log.e("folioTSendtrip","es un solo folio");
            presenter.requestDetailTicketsSendtriplus(false,iterateidTickets,currentManifest, null,folioTicket);
            changeStatusTicket=folioTicket;

        }else {
            if(data!=null) {// si es un arreglo de folios
                Log.e("folioTSendtrip","son varios folios");
                if(data.size()>1){// si solo es un folio
                  //  isArrayofTickets=true;
                    presenter.requestDetailTicketsSendtriplus(
                            true,iterateidTickets, currentManifest,data.get(iterateidTickets).getFolioTicket(), null);//
                changeStatusTicket=data.get(iterateidTickets).getFolioTicket();
                }else{// si son varios folios
                //    isArrayofTickets=false;
                    presenter.requestDetailTicketsSendtriplus(
                            true,iterateidTickets, currentManifest, data.get(0).getFolioTicket(), null);//
                    changeStatusTicket=data.get(0).getFolioTicket();
                }
            }else{

            }
        }
    }

    @Override
    public void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> dataTicketSendtrip) {
        this.dataTicketSendtrip=dataTicketSendtrip;
        Gson gson= new Gson();
        String json= gson.toJson(dataTicketSendtrip);
        Log.e("detailticket"," flowdetail "+flowDetail+" json ticket:"+json);
        fillEvidenceRequired(flowDetail,dataTicketSendtrip);
        textFol.setText("Folio: "+dataTicketSendtrip.get(0).getFolioTicket());
    }

    private void fillEvidenceRequired(Integer flowDetail, List<dataTicketsDetailsendtrip> dataTicketSendtrip) {
        adapter=new adapterEvidence(flowDetail,getApplicationContext(),dataTicketSendtrip);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());//GridLayoutManager(getApplicationContext(),3);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
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
            case R.id.video:
                Intent video = new Intent(this, videoRecord.class);
                startActivity(video);
                break;
            case R.id.eraseShared:
                removeShared();
                signatureImage.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfirma = false;
                cameraico.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfoto = false;
                clipDocs.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mfiles = false;
                star.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                mrating = false;
                secuenceRequest=1;
                break;
            case R.id.sendEvidence://la primera vez la firma lo manda con esto

                if(folioTicket!=null) {
                    Log.e("sendEvidence","folio "+folioTicket);
                    isArrayofTickets=false;
                    presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket);
                    Log.e("sendEvidence", "signatureBase64: " + signatureBase64 + "\n" +
                            "inputTextSignature: " + inputTextSignature + "\n" +
                            "carrusel:" + currusel + "\n" +
                            "ffiles: " + ffiles + "\n" +
                            "stars: " + stars + "\n" +
                            "secuenceRequest: " + secuenceRequest);
                }else {
                    if(data!=null) {
                        Log.e("sendEvidence"," data "+data.size());
                        if(data.size()>1){
                        isArrayofTickets=true;
                        sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(iterateidTickets).getFolioTicket());
                        }else{
                        isArrayofTickets=false;
                        sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(0).getFolioTicket());
                        }
                    }else{
                        Toast.makeText(this, "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }
    private void sendEvidenceIfArrayofTickets(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket){
        presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket);
    }
    private void removeShared(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove(GeneralConstants.SIGNATURE_B64_DIR);
        editor.remove(GeneralConstants.INPUT_TEXT_SIGTURE);
        editor.remove(GeneralConstants.IMAGE_DIRECTORY);
        editor.remove(GeneralConstants.DOCS_DIRECTORY);
        editor.remove(GeneralConstants.RATE_STARS);

        editor.apply();
    }
    private void cleanFolder(){
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
    private void gotomanifestV2(){
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void setMessage() {
        if(secuenceRequest<4) {//continuea el carrusel los archivos y la encuesta
            secuenceRequest = secuenceRequest + 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("sendEvidence", "signatureBase64: " + signatureBase64 + "\n" +
                            "inputTextSignature: " + inputTextSignature + "\n" +
                            "carrusel: " + currusel + "\n" +
                            "ffiles: " + ffiles + "\n" +
                            "stars: " + stars+ "\n" +
                            "secuenceRequest: " +  secuenceRequest);
                    if(folioTicket!=null) {
                        presenter.sendEvidence(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, folioTicket);
                    }else {
                        if(data!=null) {
                            sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId, data.get(iterateidTickets).getFolioTicket());
                        }else{
                            Toast.makeText(getApplicationContext(), "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, 4000);

        }else if(secuenceRequest==4){//envia la encuesta  falta el comentario
            secuenceRequest = secuenceRequest + 1;
            Log.e("sendEvidence", "sendEvidence: " + secuenceRequest+" sendRate: "+ stars);
            int rating = (int) Math.round(Double.parseDouble(stars));
            if(folioTicket!=null) {
                presenter.sendRate(rating, folioTicket);
                changeStatusTicket=folioTicket;
            }else {
                if(data!=null) {
                    presenter.sendRate(rating, data.get(iterateidTickets).getFolioTicket());
                    changeStatusTicket=data.get(iterateidTickets).getFolioTicket();
                }else{
                    Toast.makeText(this, "No hay tickets al cual mandar evidencia", Toast.LENGTH_SHORT).show();
                }
            }
            //Toast.makeText(this, "mandar estrellas", Toast.LENGTH_SHORT).show();
        }else if(secuenceRequest==5){
            secuenceRequest = secuenceRequest + 1;
            Log.e("sendEvidence","Se envia a sendtripplus");
            if(sentripPlusFlow.equals("Recoleccion")){
                presenter.sendSentriplus(currentManifest,dataTicketSendtrip,sentripPlusFlow);
            }else if (sentripPlusFlow.equals("Entrega")){
                //presenter.sendSentriplus(currentManifest,dataTicketSendtrip,sentripPlusFlow);
                secuenceRequest = secuenceRequest + 1;
                presenter.changeStatusManifestTicket(currentManifest,changeStatusTicket,sentripPlusFlow);

            }

            /// presenterchange statusmanifest ESTO YA LO HACEE JOSE

        }else if(secuenceRequest==6){
            secuenceRequest = secuenceRequest + 1;
            presenter.changeStatusManifestTicket(currentManifest,changeStatusTicket,sentripPlusFlow);

        }else if(secuenceRequest==7){//borra  lo relacionano y regresa
             Toast.makeText(this, "usar sendtrip plus Cambiar estatus y regresar a manifiestos", Toast.LENGTH_SHORT).show();

            if(!isArrayofTickets) {// si es solo uno manda el manifiesto
                presenter.hideDialog();
                removeShared();
                cleanFolder();
                gotomanifestV2();

            }else{// si son variso tickets repite el proceso

                iterateidTickets=iterateidTickets+1;
                Log.e("sendEvidence"," tickets "+iterateidTickets+" data: "+data.size());
                if(iterateidTickets >(data.size()-1)){// si es el ultimo ticket va a manifiestos
                    presenter.hideDialog();
                    removeShared();
                    cleanFolder();
                    gotomanifestV2();

                }else {// si no es el ultimo ticket vuelve a iterar otra vez secuenceRequest sirve para mandar primero firma luego fotos luego archivos y por ultimo calificacion
                    secuenceRequest=1;
                    //todo
                    //todo
                    /*****///TODO SI COMENTAS ESTA LINEA SE DEJA DE HACER EL ENVIO DE EVIDENCIAAS EN AUTOMATICO DE FORMA SECUENCIAL
                  //  sendEvidenceIfArrayofTickets(secuenceRequest, signatureBase64, inputTextSignature, currusel, ffiles, flujoId,data.get(iterateidTickets).getFolioTicket());

                    //todo
                    presenter.hideDialog();
                    removeShared();
                    cleanFolder();
                    presenter.requestDetailTicketsSendtriplus(true,iterateidTickets, currentManifest, data.get(iterateidTickets).getFolioTicket(), null);// revisar si esto se ejecuta correctamente ya que pide el detalle del ticket siguiente para el sendtriplus
                }
            }

        }else{
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
            bundle.putString("title","Cargando detalles");
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
}
