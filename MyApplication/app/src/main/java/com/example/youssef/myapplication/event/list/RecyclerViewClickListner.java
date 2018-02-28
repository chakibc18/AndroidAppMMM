package com.example.youssef.myapplication.event.list;

import android.view.View;

/**
 * Interface utilisé pour recuperer la position=id d'un event
 * une implementation dans le Holder du RecyclerView
 * Created by youssef on 25/12/17.
 */

public interface RecyclerViewClickListner {

    void onClick(View view, int position);
}
