package com.example.youssef.myapplication.event.information;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.data.DbContract;
import com.example.youssef.myapplication.firebase.Sender;
import com.example.youssef.myapplication.rating.BaseRatingBar;
import com.example.youssef.myapplication.rating.RatingUtil;
import com.example.youssef.myapplication.share.Parcours;
import com.example.youssef.myapplication.share.ShareClass;
import com.example.youssef.myapplication.share.TwitterUtil;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by youssef on 24/12/17.
 */

public class InfoEvent extends Fragment {

    private String lien = "";
    private String title="";
    private boolean isRated;
    private ShareClass shareClass;
    RatingUtil rating ;
    Dialog vote_dialog;
    Sender sender;

    WebView webView;
    ProgressBar spinner;

    int position;
    ContentResolver resolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rating = new RatingUtil(this);
        getCustomDialogPrepaed();
        setPushNotification();
        setView();
        parcours_listener();
    }

    public void setShareView(ShareClass shareClass) {
        this.shareClass = shareClass;
    }


    public void parcours_listener(){
        FloatingActionButton parcours = (FloatingActionButton) getActivity().findViewById(R.id.parcours);
        parcours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareClass.show_dialog();
            }
        });
    }

    /**
     * charge les informations de l'evenement ID = position depuis le la BDD SQLite
     * @param resolver
     * @param position
     */
    public void setAttribute(ContentResolver resolver, int position){
        this.resolver = resolver;
        this.position = position;
        Cursor cursor = resolver.query(
                DbContract.MenuEntry.CONTENT_URI,  // The content URI of the words table
                new String[]{
                        DbContract.MenuEntry.COLUMN_LIEN,
                        DbContract.MenuEntry.COLUMN_VOTE,
                        DbContract.MenuEntry.COLUMN_TITRE_FR
                },                        // The columns to return for each row
                DbContract.MenuEntry._ID + "=?", new String[]{position+""},
                null);
        cursor.moveToPosition(0);
        lien = cursor.getString(0);
        isRated = cursor.getString(1).equals("true");
        title = cursor.getString(2);
        shareClass.setItem(position, title, lien);
    }


    private void setPushNotification() {
        Button notify_button = (Button) getActivity().findViewById(R.id.notify_button);

        sender = new Sender(this.getActivity());

        notify_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sender.show(title);
            }
        });
    }

    private void getCustomDialogPrepaed(){
        vote_dialog = new Dialog(getContext());
        vote_dialog.setCancelable(false);
        vote_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        vote_dialog.setContentView(R.layout.vote_dialog);
    }

    public void dialog(){
        Toast.makeText(getActivity(), "Vous avez deja voté..!!", Toast.LENGTH_SHORT).show();
        if(!isRated)  vote_dialog.show();
    }

    public void cancel(){
        vote_dialog.cancel();
    }
    public void confirm(){
        BaseRatingBar vote = (BaseRatingBar) vote_dialog.findViewById(R.id.voteBar);
        rating.updateRating(vote.getRating());
        Toast.makeText(getContext(), vote.getRating()+"", Toast.LENGTH_SHORT).show();
        vote_dialog.cancel();
    }

    void setView(){

        webView = (WebView) getView().findViewById(R.id.webview);

        spinner = (ProgressBar)getView().findViewById(R.id.progressBar1);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.loadUrl(lien);

    }

    public int getPosition() {
        return position;
    }

    public void update() {
        ContentValues con = new ContentValues();
        con.put(DbContract.MenuEntry.COLUMN_VOTE, "true");
        resolver.update(DbContract.MenuEntry.CONTENT_URI,
                con,
                DbContract.MenuEntry._ID + "= "+position,
                null
        );
        isRated = true;
    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(webview.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            spinner.setVisibility(View.GONE);

            view.setVisibility(webView.VISIBLE);
            super.onPageFinished(view, url);

        }
    }
}
