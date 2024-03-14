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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehicles;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehiclesImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
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

import java.util.List;

public class locator  extends Fragment implements OnMapReadyCallback , LocationListener,locatorView {
        public static final String TAG = locator.class.getSimpleName();

        private GoogleMap mMap;
        private MapView mapView;
        private mainContainer mactivity;
        private LocationManager locationManager;
        private double latitude,longitude;
        private Marker mainmarker,vehicleMarker;
        String model = Build.MODEL;
        private TextView smartphone;
        private List<dataVehicleLocation> mdata;
        private presenterVehicles presenter;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_maps, container, false);
                mactivity= (mainContainer) getActivity();
                initView(view);
                checkpermisionslevel();
                mapView.onCreate(savedInstanceState);
                if (mapView != null) {
                        mapView.getMapAsync(this);
                }

                return view;
        }

        private void checkpermisionslevel() {
                locationManager= (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this.getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                }
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                        }else {
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
                mapView=view.findViewById(R.id.map);
                smartphone=view.findViewById(R.id.modelSmarthphone);
                smartphone.setText(model);
                presenter= new presenterVehiclesImpl(this,getContext());
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
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4851393, -99.150008), 16.5f));

                @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.locatorsvg1);
                Bitmap b1 = bitmapdraw1.getBitmap();
                Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, 100, 100, false);

                mainmarker=mMap.addMarker(new MarkerOptions().position(new LatLng(19.4851393, -99.150008)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));
        }
        private void drawLocation(Double latitude,Double longitude) {
                LatLng myloc = new LatLng(latitude, longitude);
                Log.e("myloc"," lat: "+latitude+" long: "+longitude);

                if(mMap!=null) {
                        // mainmarker=  mMap.addMarker(new MarkerOptions().position(myloc).title("aqui estoy"));

                                mainmarker.setPosition(myloc);


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
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {
                this.latitude=location.getLatitude();
                this.longitude=location.getLongitude();
                drawLocation(latitude,longitude);
        }
        @Override
        public void setVehicles(List<dataVehicleLocation> data) {
                this.mdata=data;
                for(dataVehicleLocation vehicle:mdata){
                        if(vehicle.getLatitud()!=null&&vehicle.getLongitud()!=null) {
                                Log.e("vehicleLoc", " Name " + vehicle.getPlaca() + " lat: " + vehicle.getLatitud() + " long: " + vehicle.getLongitud());
                                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.locatorsvg2);
                                Bitmap b2 = bitmapdraw.getBitmap();
                                Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, 100, 100, false);
                                vehicleMarker=mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)));
                                vehicleMarker.setPosition(new LatLng(vehicle.getLatitud(),vehicle.getLongitud()));
                                break;
                        }
                }
        }
}
