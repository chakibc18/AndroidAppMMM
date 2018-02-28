package com.example.youssef.myapplication.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.youssef.myapplication.R;

/**
 * Cette classe permet de s'inscrire à un event
 * Created by youssef on 24/02/18.
 */

public class Register {
    private Dialog register_dialog;
    private Activity activity;
    private Parsing parsing;

    public Register(Activity activity, String raw) {
        parsing = new Parsing(raw);
        if (raw != null && !raw.isEmpty()) {
            this.activity = activity;
            register_dialog = new Dialog(activity);
            register_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            register_dialog.setContentView(R.layout.register_dialog);
            setView();
            register_dialog.show();
        } else {
            String msg = "Les données pour l'inscription ne figurent pas sur la base de données ):";
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
        }
    }


    void setView() {
        hundle_mail();
        hundle_phones();
        hundle_url();
    }


    private void hundle_mail() {
        Button mail = (Button) register_dialog.findViewById(R.id.mail);
        mail.setEnabled(parsing.getMail() != null);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                Log.e(this.getClass().getName(), parsing.getMail());
                emailIntent.setData(Uri.parse("mailto:" + parsing.getMail()));
                activity.startActivity(emailIntent);
            }
        });
    }

    private void hundle_url() {
        Button url_button = (Button) register_dialog.findViewById(R.id.link_register);
        url_button.setEnabled(parsing.getLinks() != null);
        url_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link_intent = new Intent(Intent.ACTION_VIEW);
                String url_string = parsing.getLinks();
                if (!url_string.startsWith("http://") && !url_string.startsWith("https://"))
                    url_string = "http://" + url_string;
                link_intent.setData(Uri.parse(url_string));
                activity.startActivity(link_intent);
            }
        });
    }

    private void hundle_phones() {
        Button phone1 = (Button) register_dialog.findViewById(R.id.phone1);
        phone1.setEnabled(parsing.getPhones().size() > 0 && parsing.getPhones().get(0) != null);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone_intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", parsing.getPhones().get(0), null));
                activity.startActivity(phone_intent);
            }
        });


        Button phone2 = (Button) register_dialog.findViewById(R.id.phone2);
        phone2.setEnabled(parsing.getPhones().size() > 1 && parsing.getPhones().get(1) != null);
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phone_intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", parsing.getPhones().get(1), null));
                activity.startActivity(phone_intent);
            }
        });


    }


}
