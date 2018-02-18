package com.example.youssef.myapplication.firebase;

/**
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
