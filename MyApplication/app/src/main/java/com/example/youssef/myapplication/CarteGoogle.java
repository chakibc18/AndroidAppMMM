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
import com.example.youssef.myapplication.map.MyEvent;
import com.example.youssef.myapplication.map.OnMapAndViewReadyListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

/**
 * The main activity of the API library demo gallery.
 * <p>
 * The main layout lists the demonstrated features, with buttons to launch them.
 */
public final class CarteGoogle extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private ClusterManager<MyEvent> mClusterManager;




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
        mClusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        this.fillMap();
        mClusterManager.cluster();

        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);
        // Add lots of markers to the map.
        //addMarkersToMap();

        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyEvent>() {
            @Override
            public void onClusterItemInfoWindowClick(MyEvent myEvent) {
                Intent intent=new Intent();
                intent.putExtra("MESSAGE", myEvent.getTag());
                setResult(2,intent);
                finish();
            }
        });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyEvent>() {
            @Override
            public boolean onClusterItemClick(MyEvent myEvent) {
                return false;
            }
        });

        mMap.setContentDescription("Map with markers.");
    }




    public void setMarkers(GoogleMap map,String nom,int id ,LatLng latLng){
        mClusterManager.addItem(new MyEvent(id, nom, latLng));
    }


    public void fillMap(){
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                DbContract.MenuEntry.CONTENT_URI,
                new String[]{
                        DbContract.MenuEntry.COLUMN_GEOLOCALISATION,
                        DbContract.MenuEntry.COLUMN_TITRE_FR,
                        DbContract.MenuEntry._ID
                },
                DbContract.MenuEntry.COLUMN_GEOLOCALISATION + " NOT LIKE ?",
                new String[]{""},
                null);

        LatLng init = new LatLng(48.864716, 2.349014);


        for ( int i = 0; i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            String geo;
            geo = cursor.getString(0);
            String nom_lieu = cursor.getString(1);
            int id = cursor.getInt(2);

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
