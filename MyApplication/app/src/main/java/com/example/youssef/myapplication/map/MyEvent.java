package com.example.youssef.myapplication.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Notre model pour un event sur la carte
 * Created by youssef on 20/02/18.
 */

public class MyEvent implements ClusterItem {
    private final LatLng mPosition;
    private String title;
    private int id;

    public MyEvent(int id, String title, LatLng latLng) {
        this.title = title;
        this.id = id;
        this.mPosition = latLng;
    }


    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public String getTag() {
        return String.valueOf(id);
    }
}
