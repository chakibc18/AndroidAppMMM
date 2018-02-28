package com.example.youssef.myapplication.event.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.util.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

/**
 * Created by youssef on 23/12/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView title;
    private TextView desc;

    private Context mContext;
    private RecyclerViewClickListner mListener;

    private int currentItemID;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView, Context context, RecyclerViewClickListner listner) {
        super(itemView);
        mListener = listner;
        mContext = context;
        itemView.setOnClickListener(this);
        //c'est ici que l'on fait nos findView
        title = (TextView) itemView.findViewById(R.id.title);
        imageView = (ImageView) itemView.findViewById(R.id.imageView1);
        desc = (TextView) itemView.findViewById(R.id.descCard);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(String name, String image, String description, int id) {
        this.currentItemID = id;
        title.setText(name);
        itemView.setOnClickListener(this);
        desc.setText(description);
        //CircleTransform circleTransform = new CircleTransform();
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(40, 30);
        if (image.isEmpty())
            image = "android.resource://com.example.youssef.myapplication/" + R.drawable.defaultimage;
        Picasso.with(mContext)
                .load(image)
                .fit()
                .transform(roundedCornersTransformation)
                .centerCrop()
                .into(imageView);

    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, currentItemID);

    }

}
