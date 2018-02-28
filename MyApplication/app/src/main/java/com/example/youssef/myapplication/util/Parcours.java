package com.example.youssef.myapplication.util;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 26/12/17.
 */

public class Parcours {

    List<Item> parcours;

    public Parcours() {
        this.parcours = new ArrayList<Item>();
    }

    public void add(Item i) {
        parcours.add(i);
    }

    public void remove(Item i) {
        parcours.remove(i);
    }

    public void publish() {
        for (Item e : parcours) {
            String title = e.title;
            String image = e.image;
        }
    }

    public class Item {

        String title;
        String image;

        public Item(String title, String image) {
            this.title = title;
            this.image = image;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Item) {
                Item i = (Item) obj;
                if (i.image == this.image && i.title == this.title) {
                    return true;
                }
            }
            return false;
        }
    }
}
