package com.gnirt69.firebasenotificationexam;

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
