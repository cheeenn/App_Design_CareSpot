package com.example.meterstoinches.caresupport.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meterstoinches.caresupport.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity_a2 extends FragmentActivity implements OnMapReadyCallback {
    private Button attribute;
    private Button map;
    private Button chat;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_a2);
        setUi();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        attribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MapsActivity_a2.this,Map_Search_Activity_a10.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setUi() {
        attribute = (Button) findViewById(R.id.Attribute_a2);
        map = (Button) findViewById(R.id.Map_a2);
        chat= (Button) findViewById(R.id.Chat_a2);
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney / Nursing Room 1"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng n2 = new LatLng(38.6530169,-91.3835);
        mMap.addMarker(new MarkerOptions().position(n2).title("Nursing Room 1"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(n2));
        LatLng n3 = new LatLng(39.6530169,-90.3835);
        mMap.addMarker(new MarkerOptions().position(n3).title(" Nursing Room 2"));

        LatLng n4 = new LatLng(38.6530169,-93.3835);
        mMap.addMarker(new MarkerOptions().position(n4).title("Nursing Room 3"));

        LatLng n5 = new LatLng(39.6530169,-90.3635);
        mMap.addMarker(new MarkerOptions().position(n5).title("Nursing Room 4"));

        LatLng n6 = new LatLng(32.6530169,-91.3835);
        mMap.addMarker(new MarkerOptions().position(n6).title("Nursing Room 5"));

        LatLng n7 = new LatLng(33.6530169,-93.3835);
        mMap.addMarker(new MarkerOptions().position(n7).title("Nursing Room 7"));

        LatLng n8 = new LatLng(39.6530169,-93.3635);
        mMap.addMarker(new MarkerOptions().position(n8).title("Nursing Room 8"));

        LatLng n9 = new LatLng(33.6530169,-92.3835);
        mMap.addMarker(new MarkerOptions().position(n9).title("Nursing Room 9"));

        LatLng a = new LatLng(33.6530169,-91.3835);
        LatLng a2 = new LatLng(34.6530169,-91.3835);
        LatLng a3 = new LatLng(35.6530169,-88.3835);
        LatLng a4 = new LatLng(33.6530169,-93.3835);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions.position(a).title("parking lot1"));
        mMap.addMarker(markerOptions.position(a2).title("parking lot2"));
        mMap.addMarker(markerOptions.position(a3).title("parking lot3"));
        mMap.addMarker(markerOptions.position(a4).title("parking lot4"));
    }
}
