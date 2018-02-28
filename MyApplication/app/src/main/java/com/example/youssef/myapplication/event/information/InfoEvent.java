package com.example.youssef.myapplication.event.information;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.youssef.myapplication.registration.Register;
import com.example.youssef.myapplication.share.ShareClass;

/**
 * Fragement de l'affichage d'un evenement ainsi que les fonctionnalités :
 * notification,vote, parcours
 * Created by youssef on 24/12/17.
 */

public class InfoEvent extends Fragment {

    private RatingUtil rating;
    private Dialog vote_dialog;
    private Sender sender;
    private WebView webView;
    private ProgressBar spinner;
    private int position;
    private ContentResolver resolver;
    private String lien = "";
    private String title = "";
    private boolean register = false;
    private String registerData = "";
    private boolean isRated;
    private ShareClass shareClass;

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


    public void parcours_listener() {
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
     *
     * @param resolver
     * @param position
     */
    public void setAttribute(ContentResolver resolver, int position) {
        this.resolver = resolver;
        this.position = position;
        Cursor cursor = resolver.query(
                DbContract.MenuEntry.CONTENT_URI,  // The content URI of the words table
                new String[]{
                        DbContract.MenuEntry.COLUMN_LIEN,
                        DbContract.MenuEntry.COLUMN_VOTE,
                        DbContract.MenuEntry.COLUMN_TITRE_FR,
                        DbContract.MenuEntry.COLUMN_INSCRIPTION_NECESSAIRE,
                        DbContract.MenuEntry.COLUMN_LIEN_D_INSCRIPTION

                },                        // The columns to return for each row
                DbContract.MenuEntry._ID + "=?", new String[]{position + ""},
                null);
        cursor.moveToPosition(0);
        lien = cursor.getString(0);
        isRated = cursor.getString(1).equals("true");
        title = cursor.getString(2);

        if (cursor.getString(3) == null) {
            register = false;
        } else {
            String lower = cursor.getString(3).toLowerCase();
            register = lower.equals("oui");
        }
        registerData = (cursor.getString(4) != null) ? cursor.getString(4) : "";
        shareClass.setItem(position, title, lien);
    }

    /**
     *
     */
    private void setPushNotification() {
        Button notify_button = (Button) getActivity().findViewById(R.id.notify_button);

        sender = new Sender(this.getActivity());

        notify_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sender.show(title);
            }
        });
    }

    private void getCustomDialogPrepaed() {
        vote_dialog = new Dialog(getContext());
        vote_dialog.setCancelable(false);
        vote_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        vote_dialog.setContentView(R.layout.vote_dialog);
    }

    /**
     * methode de gestion du dialog de vote
     */
    public void dialog() {
        if (!isRated) {
            vote_dialog.show();
        } else Toast.makeText(getActivity(), "Vous avez deja voté..!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * annulation d'un vote
     */
    public void cancel() {
        vote_dialog.cancel();
    }

    /**
     * confirmation d'un vote
     */
    public void confirm() {
        BaseRatingBar vote = (BaseRatingBar) vote_dialog.findViewById(R.id.voteBar);
        rating.updateRating(vote.getRating());
        Toast.makeText(getContext(), vote.getRating() + "", Toast.LENGTH_SHORT).show();
        vote_dialog.cancel();
    }

    /**
     * construction de la vue de l'event
     */
    void setView() {

        Button register_button = (Button) getActivity().findViewById(R.id.register_button);
        register_button.setEnabled(register || !registerData.isEmpty());
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Register(getActivity(), registerData);
            }
        });

        webView = (WebView) getView().findViewById(R.id.webview);

        spinner = (ProgressBar) getView().findViewById(R.id.progressBar1);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.loadUrl(lien);

    }

    public int getPosition() {
        return position;
    }

    /**
     * mise a jour du vote
     */
    public void updateVoteState() {
        ContentValues con = new ContentValues();
        con.put(DbContract.MenuEntry.COLUMN_VOTE, "true");
        resolver.update(DbContract.MenuEntry.CONTENT_URI,
                con,
                DbContract.MenuEntry._ID + "= " + position,
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
