package com.companyname.mauitest.mainContainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.companyname.mauitest.nmanifest.*;
import com.companyname.mauitest.R;

public class mainContainer extends AppCompatActivity {
    public static final String TAG = mainContainer.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        manifiestos();
        showFragmentNavigationButtons();
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
}
/// manager = getActivity().getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//        Perfile perfile = new Perfile();
//        transaction.replace(R.id.ordenarViewImpl, perfile, Perfile.TAG).commit();
//        illuminateprofile();