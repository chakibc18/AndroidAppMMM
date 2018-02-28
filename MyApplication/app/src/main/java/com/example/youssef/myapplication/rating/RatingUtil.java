package com.example.youssef.myapplication.rating;

import android.util.Log;

import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.event.information.InfoEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * La classe de la fonctionnalité du vote
 * Created by youssef on 28/12/17.
 */

public class RatingUtil {

    private int nombre_de_vote;
    private float current_evaluation = 0;
    private InfoEvent infoEvent;
    private BaseRatingBar baseRatingBar;
    private boolean done = false;
    private FirebaseDatabase database;
    private DatabaseReference child_vote;
    private DatabaseReference child_nb_vote;

    public RatingUtil(InfoEvent infoEvent) {
        this.infoEvent = infoEvent;
        Log.e("", String.valueOf(infoEvent == null));
        database = FirebaseDatabase.getInstance();
        DatabaseReference vote = database.getReference("rate");
        DatabaseReference nb_vote = database.getReference("rate_nb");


        child_vote = vote.child(infoEvent.getPosition() + "");
        child_nb_vote = nb_vote.child("" + infoEvent.getPosition());

        Log.e("if null child_vote", child_vote.getKey() + "");

        baseRatingBar = (BaseRatingBar) this.infoEvent.getActivity().findViewById(R.id.baseratingbar_main);
        baseRatingBar.setClearRatingEnabled(false);
        baseRatingBar.setClickable(false);
        baseRatingBar.setScrollable(false);


        listeners();

    }

    private void listeners() {
        child_vote.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() == null) {
                    child_vote.setValue(0);
                } else current_evaluation = dataSnapshot.getValue(Integer.class);
                baseRatingBar.setRating(current_evaluation);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(this.getClass().getName(), "Failed to read value.", error.toException());
            }
        });

        child_nb_vote.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() == null) {
                    child_nb_vote.setValue(0);
                } else nombre_de_vote = dataSnapshot.getValue(Integer.class);
                Log.d(this.getClass().getName(), "Value is: " + nombre_de_vote);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(this.getClass().getName(), "Failed to read value.", error.toException());
            }
        });

    }

    public void updateRating(float rate) {
        done = true;
        int nb = nombre_de_vote;
        int new_nb = nb + 1;
        float newRate = (rate + current_evaluation * nb) / new_nb;
        Log.e("newRate", newRate + " " + rate);

        child_vote.setValue(newRate);
        child_nb_vote.setValue(new_nb);
        this.infoEvent.updateVoteState();
        setRating(newRate);

    }

    //on view and and updateVoteState db rating
    private void setRating(float f) {
        baseRatingBar.setRating(f);
    }

}
