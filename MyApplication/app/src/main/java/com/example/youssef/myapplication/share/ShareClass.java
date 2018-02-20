package com.example.youssef.myapplication.share;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youssef.myapplication.R;

/**
 * Created by youssef on 18/02/18.
 */

public class ShareClass {
    private Parcours parcours;
    private TwitterUtil twitterUtil;
    private Dialog parcours_dialog;
    private Activity activity;

    private Parcours.Item item;

    private int added_events_count = 0;
    private int max_events = 5;


    public ShareClass(Activity activity){
        this.activity = activity;
        twitterUtil = new TwitterUtil(activity);
        parcours = new Parcours(twitterUtil);
        parcours_dialog = new Dialog(activity);
        parcours_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        parcours_dialog.setContentView(R.layout.parcours_dialog);
    }

    public void show_dialog(){
        parcours_dialog.show();
        //listeners
        Button twitter = (Button) parcours_dialog.findViewById(R.id.twitter_button);
        Button reinitialize = (Button) parcours_dialog.findViewById(R.id.reinitialize_button);
        TextView textView = (TextView) parcours_dialog.findViewById(R.id.text_dialog_parcours);

        reinitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reinitialize();
                updateTextView(textView);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(added_events_count>0){
                    tweet();
                    updateTextView(textView);
                }
                else {
                    Toast.makeText(activity,"Votre parcours est vide!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button adder = (Button) parcours_dialog.findViewById(R.id.add_item_button);
        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(added_events_count >= max_events) {
                    Toast.makeText(activity,"Votre parcours est au max!!",Toast.LENGTH_SHORT).show();
                }
                else if (parcours.isIn(item)){
                    Toast.makeText(activity,"Déjà ajouté!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    add_to_parcours(item);
                    updateTextView(textView);
                }
            }
        });
    }

    private void add_to_parcours(Parcours.Item item){
            parcours.add(item);
            added_events_count++;
    }

    public void tweet(){
        parcours.publish();
        parcours_dialog.cancel();
        reinitialize();
    }

    public void reinitialize(){
        added_events_count = 0;
        parcours = new Parcours(twitterUtil);
    }


    public void setItem(int id, String title, String lien){
        Parcours.Item item = parcours.new Item(id,title,lien);
        this.item = item;
    }


    private void updateTextView(TextView textView){
        textView.setText("Vous parcours contient "+added_events_count+" evenement(s)");
    }
}
