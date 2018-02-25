package com.example.youssef.myapplication.registration;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by youssef on 24/02/18.
 */

public class Parsing {
    private String raw;
    private String mail;
    private String link;
    private List<String> phones;

    public Parsing(String raw) {
        this.raw = raw;
        parseMail();
        parsePhones();
        parseLinks();
    }

    private void parseMail() {
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(raw);
        if (m.find())
            mail = m.group();
    }

    private void parsePhones() {
        phones = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(0+\\d{9}|\\d{2} \\d{2} \\d{2} \\d{2} \\d{2})");
        Matcher m = pattern.matcher(raw);
        while (m.find()) {
            phones.add(m.group());
        }
    }

    private void parseLinks() {

        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(raw);
        if (m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            link = urlStr;
        }
    }

    public String getMail(){
        return mail;
    }
    public String getLinks(){
        return link;
    }

    public List<String> getPhones(){
        return phones;
    }

}
