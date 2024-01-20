package com.fhl.sistemadedistribucionfh.mainContainer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.Dialogs.mainMenu;
import com.fhl.sistemadedistribucionfh.Profile.view.viewProfile;
import com.fhl.sistemadedistribucionfh.Visor.view.visorViewImpl;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.fhl.sistemadedistribucionfh.gastos.view.gastos;
import com.fhl.sistemadedistribucionfh.locator.locator;
import com.fhl.sistemadedistribucionfh.mainContainer.model.dataMenuItems;
import com.fhl.sistemadedistribucionfh.mainContainer.presenter.prensentermainContainerImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.presenter.presentermainContainer;
import com.fhl.sistemadedistribucionfh.mainContainer.view.view;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.presenterV2.presentermainContainerImplV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.presenterV2.presentermainContainerV2;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.view.mmanifest;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;
import com.fhl.sistemadedistribucionfh.resguardo.view.resguardo;

import java.util.List;

public class mainContainer extends AppCompatActivity  implements view {
    public static final String TAG = mainContainer.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView cover;
    private  tabBar tab;
    private mainMenu menu;
    private FrameLayout framTab;
    private presentermainContainer presenter;
    private presentermainContainerV2 presenterV2;
    private List<dataMenuItems> data;
    private List<dataMenuItemsV2> dataV2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        cover=findViewById(R.id.cover);
        cover.setVisibility(View.GONE);
        framTab=findViewById(R.id.tabbar);
        initPresenter();
        //manifiestos();
        manifiestosV2();
        showFragmentNavigationButtons();
        showTab();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(menu!=null) {
            menu.closeDialog();
        }


    }
    public void chooseFragment(String menu){

        switch (menu) {
            case "Perfil":
                profile();
                break;
           case "Manifiestos":
               //manifiestos();
               manifiestosV2();
                break;
            case "Validador"://este es el modulo de scanner
               mScanner("Validador");
                //Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case "Ubicacion GPS":
                Locator();
                break;
            case "Salida":
                mScanner("Salida");
                break;
            case "Escaner":
                //mScanner("Escaner");
                Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case "CheckList":
                //Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                checkList();
                break;
            case "Gastos":
                //Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                gastosF();
                break;
            case "Resguardo":
                //Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                resguardoF();
                break;
            case "Visor":
                visor();
                Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case "salida/recepción":
                mScanner("Salida");
                Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case "Custodio":
                mScanner("Custodio");
                break;
        }
    }
    private void initPresenter() {
        presenter= new prensentermainContainerImpl(this,getApplicationContext());
        presenterV2 = new presentermainContainerImplV2(this, getApplicationContext());
        presenter.requestMenus();
        presenterV2.requestMenusV2();
    }

    private void manifiestos() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifest manifest= new mmanifest();
        transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
    }

    private void manifiestosV2() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifestV2 manifestV2= new mmanifestV2();
        transaction.replace(R.id.fragments, manifestV2, mmanifestV2.TAG).commit();
    }
    private void mScanner(String scannerType){
        Bundle bundle = new Bundle();
        bundle.putString("scannerType", scannerType);
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private  void Locator(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        locator loc = new locator();
        transaction.replace(R.id.fragments, loc, locator.TAG).commit();
        framTab.setVisibility(View.GONE);

    }
    private void showFragmentNavigationButtons() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        menu menu = new menu();
        transaction.replace(R.id.menu, menu, menu.TAG).commit();
    }
    private void profile(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        viewProfile profile= new viewProfile();
        transaction.replace(R.id.fragments, profile, viewProfile.TAG).commit();
    }
    private void checkList(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        checkList mcheckList= new checkList();
        transaction.replace(R.id.fragments, mcheckList, checkList.TAG).commit();
    }
    private void gastosF(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        gastos mgastos= new gastos();
        transaction.replace(R.id.fragments, mgastos, gastos.TAG).commit();
    }
    private void resguardoF(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        resguardo mresguardo= new resguardo();
        transaction.replace(R.id.fragments, mresguardo, resguardo.TAG).commit();
    }
    private void visor(){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        visorViewImpl visor= new visorViewImpl();
        transaction.replace(R.id.fragments, visor, visorViewImpl.TAG).commit();
    }
    private void showTab() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        tab = new tabBar();
        transaction.replace(R.id.tabbar, tab, tabBar.TAG).commit();
    }
    public void action(){
        //Toast.makeText(getApplicationContext(), "menu", Toast.LENGTH_SHORT).show();
        cover.setVisibility(View.VISIBLE);
        menu=new mainMenu();

        //Data normal
        if(data!=null) {
            menu.setData(data);
        }

        //Data V2
        if (dataV2!=null) {
            menu.setDataV2(dataV2);
        } else {
            //Algo
        }
        menu.show(this.getSupportFragmentManager(), mainMenu.TAG);
    }

    public void hideCover() {
        cover.setVisibility(View.GONE);
        tab.setvisiblemenuButon();
    }

    @Override
    public void setMenus(List<dataMenuItems> data) {
        Log.e("menu",""+data);
        this.data=data;
    }

    @Override
    public void setMenusV2(List<dataMenuItemsV2> data) {
        Log.e("menuv2", "" + data);
        this.dataV2 = data;
    }

    public void showtab() {
        framTab.setVisibility(View.VISIBLE);
    }
}
