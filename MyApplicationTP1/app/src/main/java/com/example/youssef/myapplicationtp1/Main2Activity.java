package com.example.youssef.myapplicationtp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView nom = (TextView) findViewById(R.id.user);
        User user = getIntent().getExtras().getParcelable("user");
        nom.setText("nom: "+user.nom + "\n" + "prenom: "+user.prenom + "\n" +
        "ville: "+user.ville+ "\n" +"date: "+user.date+ "\n" +"planet: "+user.planet);
    }
}
