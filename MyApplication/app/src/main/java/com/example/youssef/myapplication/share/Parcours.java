package com.example.youssef.myapplication.share;


import java.util.ArrayList;
import java.util.List;

/**
 * Notre classe model pour le parcours
 * Created by youssef on 26/12/17.
 */

public class Parcours {

    private List<Item> parcours;
    private TwitterUtil twitterUtil;

    public Parcours(TwitterUtil twitterUtil) {

        this.parcours = new ArrayList<Item>();
        this.twitterUtil = twitterUtil;
    }

    public void add(Item i) {
        parcours.add(i);
    }

    public boolean isIn(Item i) {
        return this.parcours.contains(i);
    }

    public void publish() {
        String s = "Mon parcours :\n";
        int i = 1;
        for (Item e : parcours) {
            String title = applyTwitterLimit(e.title);
            String link = e.link;
            s = s + i++ + " :" + title +/*" "+ link +*/ '\n';
        }
        twitterUtil.post(s);
    }


    public String applyTwitterLimit(String s) {
        if (s.length() < 59) return s;
        return s.substring(0, 55) + "...";
    }

    public class Item {
        private int id;
        private String title;
        private String link;

        public Item(int id, String title, String link) {
            this.title = title;
            this.link = link;
            this.id = id;
        }


        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Item) {
                return ((Item) object).id == this.id;
            }
            return false;
        }
    }
}
