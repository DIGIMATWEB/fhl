package com.fhl.sistemadedistribucionfh.locator.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Profile.view.viewProfile;
import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehicles;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehiclesImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.fhl.sistemadedistribucionfh.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class locator extends Fragment implements OnMapReadyCallback, LocationListener, locatorView, View.OnClickListener {
        public static final String TAG = locator.class.getSimpleName();

        private GoogleMap mMap;
        private MapView mapView;
        private mainContainer mactivity;
        private LocationManager locationManager;
        private double latitude, longitude;
        private Marker mainmarker, vehicleMarker;
        private TextView smartphone;
        private List<dataVehicleLocation> mdata;
        private presenterVehicles presenter;
        private FragmentManager manager;
        private FragmentTransaction transaction;
        private loaderFH progress;
        private TextView textView49;
        private String meconomico;
        private CardView cardView2, cellphone;

        private List<Marker> markers = new ArrayList<>();  // List to store markers

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_maps, container, false);
                mactivity = (mainContainer) getActivity();
                progress = new loaderFH();
                initView(view);
                checkpermisionslevel();
                mapView.onCreate(savedInstanceState);
                if (mapView != null) {
                        mapView.getMapAsync(this);
                }

                return view;
        }

        private void checkpermisionslevel() {
                locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this.getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                }
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                        } else {
                                if (ActivityCompat.checkSelfPermission(this.getContext(),
                                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
                                }
                                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 5, this);

                        }
                } else {

                }
        }

        private void initView(View view) {
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                mapView = view.findViewById(R.id.map);
                smartphone = view.findViewById(R.id.modelSmarthphone);
                textView49 = view.findViewById(R.id.textView49);
                cardView2 = view.findViewById(R.id.cardView2);
                cardView2.setOnClickListener(this);
                cellphone = view.findViewById(R.id.cellphone);
                cellphone.setOnClickListener(this);
                presenter = new presenterVehiclesImpl(this, getContext());
                presenter.getVehicles();
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getActivity());
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(19.4851393, -99.150008);
                @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.locatorsvg2);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
                Marker sydneyMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                markers.add(sydneyMarker);  // Add marker to list
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4851393, -99.150008), 16.5f));

                @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.locatorsvg1);
                Bitmap b1 = bitmapdraw1.getBitmap();
                Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, 100, 100, false);

                mainmarker = mMap.addMarker(new MarkerOptions().position(new LatLng(19.4851393, -99.150008)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));
                mainmarker.setTag("Cellphone");
                markers.add(mainmarker);  // Add marker to list
        }

        private void drawLocation(Double latitude, Double longitude) {
                LatLng myloc = new LatLng(latitude, longitude);
                Log.e("myloc", " lat: " + latitude + " long: " + longitude);

                if (mMap != null) {
                        mainmarker.setPosition(myloc);
                        mainmarker.setTag("Cellphone");
                }
        }

        @Override
        public void onStart() {
                super.onStart();
                mapView.onStart();
        }

        @Override
        public void onDetach() {
                super.onDetach();
                mactivity.showtab();
        }

        @Override
        public void onResume() {
                super.onResume();
                mapView.onResume();
                if (getView() != null) {
                        getView().setFocusableInTouchMode(true);
                        getView().requestFocus();
                        getView().setOnKeyListener(new View.OnKeyListener() {
                                @Override
                                public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                                                if (manager != null && manager.getBackStackEntryCount() > 0) {
                                                        // Hay fragmentos en la pila, realiza popBackStack
                                                        Log.e("fragments", "detail" + manager.getBackStackEntryCount());
                                                        manager.popBackStack(viewProfile.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                                } else {
                                                        // No hay fragmentos en la pila, deja que la actividad maneje el evento de retroceso
                                                        requireActivity().onBackPressed();
                                                        Log.e("fragments", "detail 0");
                                                }
                                                return true;
                                        }
                                        return false;
                                }
                        });
                }
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {
                this.latitude = location.getLatitude();
                this.longitude = location.getLongitude();
                drawLocation(latitude, longitude);
        }

        @Override
        public void setVehicles(List<dataVehicleLocation> data) {
                if (data != null) {
                        this.mdata = data;
                        Gson gson = new Gson();
                        String jsonV = gson.toJson(data);
                        Log.e("vehiclesLoc", jsonV);

                }
                presenter.getVehicleinmanifestV2();
        }

        @Override
        public void showDialog() {
                if (progress != null && !progress.isVisible()) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("HAS_TITLE", false);
                        bundle.putString("title", "Cargando detalles");
                        progress.setArguments(bundle);
                        progress.show(getChildFragmentManager(), loaderFH.TAG);
                }
        }

        @Override
        public void hideDialog() {
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                if (progress != null && this != null)
                                        if (progress.isAdded()) {
                                                progress.dismiss();
                                        }
                        }
                }, 2000);
        }

        @Override
        public void setVehicleinmanifestV2(String economico) {
                this.meconomico = economico;
                textView49.setText("" + economico);
                smartphone.setText("" + mainmarker.getPosition().latitude + " , " + mainmarker.getPosition().longitude);
                if(mdata!=null) {
                        for (dataVehicleLocation vehicle : mdata) {
//                                if(vehicle.getEconomico().equals("646")){
//                                        vehicle.setLatitud(19.4831363);
//                                        vehicle.setLongitud(-99.150004);
//                                }
                                if(vehicle.getEconomico().equals(meconomico)) {
                                        if (vehicle.getLatitud() != null && vehicle.getLongitud() != null) {
                                                Log.e("vehiclesLoc", "" + vehicle.getId());
                                                Log.e("vehicleLoc", " Name " + vehicle.getPlaca() + " lat: " + vehicle.getLatitud() + " long: " + vehicle.getLongitud());
                                                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.locatorsvg2);
                                                Bitmap b2 = bitmapdraw.getBitmap();
                                                Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, 100, 100, false);
                                                Marker m = mMap.addMarker(new MarkerOptions().position(new LatLng(vehicle.getLatitud(), vehicle.getLongitud())).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)));
                                                m.setTag(vehicle.getEconomico());
                                                markers.add(m);  // Add marker to list
                                        }
                                }
                        }
                }
        }

        public void moveToMarkerWithTag(String tag) {
                if (mMap != null) {
                        for (Marker marker : markers) {
                                if (marker.getTag() != null && marker.getTag().equals(tag)) {
                                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
                                        break;
                                }
                        }
                }
        }
        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                        case R.id.cardView2:
                                for (dataVehicleLocation vehicle : mdata) {
                                        if(vehicle.getEconomico().equals(meconomico)) {
                                             if (vehicle.getLatitud() != null && vehicle.getLongitud() != null) {
                                                        moveToMarkerWithTag(meconomico);
                                                        break;
                                                }else {
                                                        Toast.makeText(getContext(), "Sin ubicación", Toast.LENGTH_SHORT).show();
                                                        break;
                                                }
                                        }
                                }
                                break;
                        case R.id.cellphone:
                                moveToMarkerWithTag("Cellphone");
                                break;
                }
        }
}