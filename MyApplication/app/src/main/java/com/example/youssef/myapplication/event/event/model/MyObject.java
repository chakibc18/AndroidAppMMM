package com.example.youssef.myapplication.event.event.model;

import java.util.Date;

/**
 * Created by youssef on 23/12/17.
 */

public class MyObject {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String title;
    private String desc;

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    private Date debut;
    private Date fin;

    public MyObject(String t1, String t2, Date d1, Date d2) {
        this.title = t1;
        this.desc = t2;
        this.debut = d1;
        this.fin = d2;
    }


    //getters & setters
}
