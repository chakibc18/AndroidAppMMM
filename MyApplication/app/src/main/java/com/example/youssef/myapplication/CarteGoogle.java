/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.youssef.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.youssef.myapplication.data.DbContract;
import com.example.youssef.myapplication.map.OnMapAndViewReadyListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * The main activity of the API library demo gallery.
 * <p>
 * The main layout lists the demonstrated features, with buttons to launch them.
 */
public final class CarteGoogle extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    SupportMapFragment mapFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_events);
        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Context context = getApplicationContext();
        mMap = map;
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);
        // Add lots of markers to the map.
        //addMarkersToMap();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",marker.getTitle());
                setResult(2,intent);
                finish();
            }
        });

        mMap.setContentDescription("Map with markers.");

        this.fillMap();
    }




    public void setMarkers(GoogleMap map,String nom,int id ,LatLng latLng){
        map.addMarker(new MarkerOptions().title(String.valueOf(id))
                .snippet(nom)
                .position(latLng));
    }


    public void fillMap(){
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                DbContract.MenuEntry.CONTENT_URI,  // The content URI of the words table
                new String[]{
                        DbContract.MenuEntry.COLUMN_GEOLOCALISATION,
                        DbContract.MenuEntry.COLUMN_NOM_DU_LIEU,
                        DbContract.MenuEntry._ID
                },                        // The columns to return for each row
               null,
                null,
                null);

        LatLng init = new LatLng(48.864716, 2.349014);


        for (int i = 0; i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            String geo;
            geo = cursor.getString(0);
            String nom_lieu = cursor.getString(1);
            int id = cursor.getInt(2);
            Log.d(this.getClass().getName(), geo);
            LatLng latLng = StringToLatLng(geo);
            setMarkers(this.mMap,nom_lieu, id,latLng);
        }
        mMap.moveCamera(CameraUpdateFactory.
                newLatLngZoom
                        (init, 5));

    }


    LatLng StringToLatLng(String s){
        String[] list = s.split(",");
       return new LatLng(Double.parseDouble(list[0]), Double.parseDouble(list[1]));
    }


}
