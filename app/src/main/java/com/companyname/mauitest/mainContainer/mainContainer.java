package com.companyname.mauitest.mainContainer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.companyname.mauitest.Dialogs.mainMenu;
import com.companyname.mauitest.nmanifest.*;
import com.companyname.mauitest.R;

public class mainContainer extends AppCompatActivity {
    public static final String TAG = mainContainer.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView cover;
    private  tabBar tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        cover=findViewById(R.id.cover);
       //cover.setVisibility(View.GONE);
        manifiestos();
        showFragmentNavigationButtons();
        showTab();
    }
    private void manifiestos() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifest manifest= new mmanifest();
        transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
    }

    private void showFragmentNavigationButtons() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        menu menu = new menu();
        transaction.replace(R.id.menu, menu, menu.TAG).commit();
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
        mainMenu menu=new mainMenu();
        menu.show(this.getSupportFragmentManager(), mainMenu.TAG);
    }

    public void hideCover() {
        cover.setVisibility(View.GONE);
        tab.setvisiblemenuButon();
    }
}
