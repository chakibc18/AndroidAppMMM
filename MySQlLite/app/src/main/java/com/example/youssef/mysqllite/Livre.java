package com.example.youssef.mysqllite;

/**
 * Created by youssef on 15/11/17.
 */

public class Livre {

    private int id;
    private String is;
    private String titre;

    public Livre(){}

    public Livre(int i, String b, String s){
        this.id = i;
        this.is = b;
        this.titre = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
