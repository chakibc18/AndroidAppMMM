package com.example.youssef.myapplication.event.list;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youssef.myapplication.data.DbContract;
import com.example.youssef.myapplication.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by youssef on 23/12/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();

    private Cursor mCursor = null;
    private Context mContext;
    private final LayoutInflater mInflater;
    private RecyclerViewClickListner mListener;

    public MyViewHolder getViewHolder() {
        return viewHolder;
    }

    private MyViewHolder viewHolder;

    public MyAdapter(Context context, RecyclerViewClickListner listener) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mListener = listener;
    }
    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        viewHolder = new MyViewHolder(view, mContext, mListener);
        return viewHolder;
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int curId = mCursor.getColumnIndex(DbContract.MenuEntry._ID);
                int curTitle = mCursor.getColumnIndex(DbContract.MenuEntry.COLUMN_TITRE_FR);
                int curDesc =  mCursor.getColumnIndex(DbContract.MenuEntry.COLUMN_DESCRIPTION_FR);
                int curImage = mCursor.getColumnIndex(DbContract.MenuEntry.COLUMN_APERCU);
                //Log.e("test getCulmnNames", Arrays.toString(mCursor.getColumnNames()));

                String title = mCursor.getString(curTitle);
                String image = mCursor.getString(curImage);
                String desc = mCursor.getString(curDesc);
                int id = mCursor.getInt(curId);

                holder.bind(title, image, desc, id);

                Log.e (TAG, "onBindViewHolder: is on point");
            } else {
                Log.e (TAG, "onBindViewHolder: Cursor is null.");
            }
        } else {
            Log.e (TAG, "onBindViewHolder: Cursor is null.");
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return -1;
        }
    }
}
