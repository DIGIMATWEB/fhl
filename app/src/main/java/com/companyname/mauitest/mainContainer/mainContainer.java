package com.companyname.mauitest.mainContainer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.companyname.mauitest.Dialogs.mainMenu;
import com.companyname.mauitest.Profile.view.viewProfile;
import com.companyname.mauitest.checkList.view.checkList;
import com.companyname.mauitest.gastos.view.gastos;
import com.companyname.mauitest.locator.locator;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;
import com.companyname.mauitest.mainContainer.presenter.prensentermainContainerImpl;
import com.companyname.mauitest.mainContainer.presenter.presentermainContainer;
import com.companyname.mauitest.mainContainer.view.view;
import com.companyname.mauitest.mlkit.BarcodeScannerActivity;
import com.companyname.mauitest.R;
import com.companyname.mauitest.nmanifest.view.mmanifest;
import com.companyname.mauitest.resguardo.view.resguardo;

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
    private List<dataMenuItems> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        cover=findViewById(R.id.cover);
        cover.setVisibility(View.GONE);
        framTab=findViewById(R.id.tabbar);
        initPresenter();
        manifiestos();
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
               manifiestos();
                break;
            case "Validador"://este es el modulo de scanner
                mScanner("Validador");
                break;
            case "Ubicacion GPS":
                Locator();
                break;
            case "Salida":
                mScanner("Salida");
               // Toast.makeText(this, "modulo en desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case "Escaner":
                mScanner("Escaner");
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
                mScanner("Visor");
                break;

        }
    }
    private void initPresenter() {
        presenter= new prensentermainContainerImpl(this,getApplicationContext());
        presenter.requestMenus();
    }

    private void manifiestos() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifest manifest= new mmanifest();
        transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
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
        if(data!=null) {
            menu.setData(data);
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

    public void showtab() {
        framTab.setVisibility(View.VISIBLE);
    }
}
