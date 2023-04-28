package com.companyname.mauitest.locator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.companyname.mauitest.mainContainer.mainContainer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.companyname.mauitest.R;
public class locator  extends Fragment implements OnMapReadyCallback {
        public static final String TAG = locator.class.getSimpleName();

        private GoogleMap mMap;
        private MapView mapView;
        private mainContainer mactivity;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_maps, container, false);
                mactivity= (mainContainer) getActivity();
                initView(view);

                mapView.onCreate(savedInstanceState);
                if (mapView != null) {
                        mapView.getMapAsync(this);
                }
                return view;
        }

        private void initView(View view) {
                mapView=view.findViewById(R.id.map);
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getActivity());
                mMap = googleMap;
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(19.4851393, -99.150008);
                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4851393, -99.150008), 16.5f));
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
}
