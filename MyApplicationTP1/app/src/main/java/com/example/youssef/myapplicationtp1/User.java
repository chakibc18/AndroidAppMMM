package com.example.youssef.myapplicationtp1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by youssef on 20/10/17.
 */

public class User implements Parcelable {

    String nom;
    String prenom;
    String ville;
    String date;
    String planet;

    public User(String inom,
            String iprenom,
            String idate,
            String iville,
            String iplanet){
        this.nom = inom;
        this.prenom = iprenom;
        this.ville  = iville;
        this.date =  idate;
        this.planet =  iplanet;
    }

    protected User(Parcel in) {
        this.nom = in.readString();
        this.prenom = in.readString();
        this.ville  = in.readString();
        this.date =  in.readString();
        this.planet =  in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(ville);
        dest.writeString(date);
        dest.writeString(planet);
    }

}
