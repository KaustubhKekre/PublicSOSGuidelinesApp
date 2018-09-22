package com.voidbrain.emergencysos;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng myloc;
    private double lat, longitude;
    private LocationManager lm;
    private double latloc2,longloc2;
    private String nameloc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        latloc2=getIntent().getDoubleExtra("latloc1",-1);
        nameloc2=getIntent().getStringExtra("nameloc");
        longloc2=getIntent().getDoubleExtra("longloc1",-1);
                System.out.println("aaaa"+latloc2+longloc2+nameloc2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        myloc = new LatLng(latloc2, longloc2);
        System.out.println("kkkk"+latloc2+longloc2);


            mMap.addMarker(new MarkerOptions().position(myloc).title(nameloc2));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));

    }


}
