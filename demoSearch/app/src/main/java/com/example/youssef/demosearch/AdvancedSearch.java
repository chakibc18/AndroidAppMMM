package com.example.youssef.demosearch;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by youssef on 30/12/17.
 */

public class AdvancedSearch extends Fragment {

    DatePicker datePicker;
    TextView date;
    MainActivity activity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        advancedSearch();
    }

    public void setActivity(MainActivity mainActivity){
        this.activity = mainActivity;
    }

    public void advancedSearch() {
        date = (TextView) activity.findViewById(R.id.date);

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.date_picker);
        dialog.setTitle("Title...");
        datePicker = (DatePicker) dialog.findViewById(R.id.datePicker);

        Log.e("visib", String.valueOf(datePicker.getVisibility()));

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // datePicker.setVisibility(View.VISIBLE);
                datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.i("DatePicker", year + "-" + monthOfYear + "-" + dayOfMonth);
                        int month = monthOfYear + 1;
                        date.setText(dayOfMonth + "/" + month + "/" + year);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        search();

    }

    public  void search(){
        Button button = (Button) activity.findViewById(R.id.button_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ToString!!", getArgs().toString());
            }
        });
    }

    public SearchArgs getArgs(){
        TextView searchedView = (TextView) activity.findViewById(R.id.searched);
        String searched = String.valueOf(searchedView.getText());


        RadioGroup radioGroup = (RadioGroup) activity.findViewById(R.id.radio_group);
        int checkedRadio = radioGroup.getCheckedRadioButtonId();

        CheckBox checkBox_date = (CheckBox) activity.findViewById(R.id.checkBox_date);
        Date date = null;
        if (checkBox_date.isChecked()) {

            TextView dateView = (TextView) activity.findViewById(R.id.date);
            date = stringToDate(dateView.getText());
        }
        return new SearchArgs(searched,checkedRadio==R.id.mot_cle, checkedRadio==R.id.theme, date);
    }


    public Date stringToDate(CharSequence charSequence){
        Date date = new Date();
        String s = String.valueOf(charSequence);
        String[] tmp = s.split("/");
        date.setDate(Integer.parseInt(tmp[0]));
        date.setMonth(Integer.parseInt(tmp[1]));
        date.setYear(Integer.parseInt(tmp[2]));
        return date;
    }

    public class SearchArgs{

        private String searched;
        private boolean keyword;
        private boolean thematic;
        private java.util.Date date;

        @Override
        public String toString() {
                String dateC;
             if (date == null) dateC = "non";
             else {
                 dateC = date.toString();
             }
            return "SearchArgs{" +
                    "searched='" + searched + '\'' +
                    ", keyword=" + keyword +
                    ", thematic=" + thematic +
                    ", Date=" + dateC +
                    '}';
        }

        public SearchArgs(String searched, boolean keyword, boolean thematic, java.util.Date date) {
            this.searched = searched;
            this.keyword = keyword;
            this.thematic = thematic;
            this.date = date;
        }

        public String getSearched() {
            return searched;
        }

        public boolean isKeyword() {
            return keyword;
        }

        public boolean isThematic() {
            return thematic;
        }

        public java.util.Date getDate() {
            return date;
        }
    }
}
