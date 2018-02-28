package com.example.youssef.myapplication.firebase;

/**
 * Notre model du message pour la notification
 * Created by youssef on 18/02/18.
 */

public class Message {
    String to;
    NotifyData notification;

    public Message(String to, NotifyData notification) {
        this.to = to;
        this.notification = notification;
    }

}
