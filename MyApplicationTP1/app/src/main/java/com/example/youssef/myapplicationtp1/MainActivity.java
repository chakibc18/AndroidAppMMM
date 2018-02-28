package com.example.youssef.myapplicationtp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.content.Intent;
import android.net.Uri;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.constraint.ConstraintLayout;
public class MainActivity extends AppCompatActivity {

    String planet = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected
                    (AdapterView<?> parent, View view, int position, long id) {
                planet = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void valider(View view) {
        Intent intent = new Intent(view.getContext(), Main2Activity.class);
        EditText nom = (EditText) findViewById(R.id.name);
        EditText prenom = (EditText) findViewById(R.id.prenom);
        EditText date = (EditText) findViewById(R.id.date);
        EditText ville = (EditText) findViewById(R.id.ville);
        User user = new User(nom.getText().toString(),prenom.getText().toString(),
               date.getText().toString(), ville.getText().toString(), planet);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void wikipage(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://en.wikipedia.org/wiki/" + planet+"_(planet)"));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reset:
                EditText name = (EditText) findViewById(R.id.name);
                EditText prenom = (EditText) findViewById(R.id.prenom);
                EditText date = (EditText) findViewById(R.id.date);
                EditText ville = (EditText) findViewById(R.id.ville);
                name.setText("");
                prenom.setText("");
                date.setText("");
                ville.setText("");
                return true;
            case R.id.phone:
                ConstraintLayout h = (ConstraintLayout) findViewById(R.id.layout);
                EditText phones = new EditText(findViewById(R.id.layout).getContext());
                h.addView(phones);
                return true;
            default: return true;
        }
    }



}
