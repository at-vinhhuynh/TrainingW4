package com.example.vinh.trainingw4.fri;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.example.vinh.trainingw4.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in kcnAnDon and move the camera
        LatLng kcnAnDon = new LatLng(16.0762174, 108.233339);
        LatLng tpBridge = new LatLng(16.0940678, 108.217125);
        googleMap.addMarker(new MarkerOptions().position(kcnAnDon).title("Marker in An Don Da Nang"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kcnAnDon));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kcnAnDon, 5f));
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                // Handle map is moving
            }
        });
        googleMap.addPolyline(new PolylineOptions()
                .add(kcnAnDon, tpBridge)
                .color(ContextCompat.getColor(this, R.color.colorAccent))
                .width(5f)
        );
    }
}
