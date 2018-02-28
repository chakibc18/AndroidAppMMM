package com.example.youssef.testtwtt;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssef on 26/12/17.
 */

public class Parcours {

    List<Item>  parcours;
    TwitterUtil twitterUtil;

    public Parcours(TwitterUtil twitterUtil) {

        this.parcours = new ArrayList<Item>();
        this.twitterUtil = twitterUtil;
    }

    public void add(Item i){
        parcours.add(i);
    }

    public void remove(Item i){
        parcours.remove(i);
    }

    public void publish(){
        String s = "";
        for (Item e: parcours) {
            String title = e.title;
            //String image = e.image;
            s = s +title + '\n';
        }
        twitterUtil.post(s);
    }

    public  class  Item {

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
