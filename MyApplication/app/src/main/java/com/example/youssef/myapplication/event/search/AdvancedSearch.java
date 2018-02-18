package com.example.youssef.myapplication.event.search;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.youssef.myapplication.MainActivity;
import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.data.DbContract;
import com.example.youssef.myapplication.event.list.ListEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by youssef on 30/12/17.
 */

public class AdvancedSearch extends Fragment {
    ListEvent listEvent;
    DatePicker datePicker;
    TextView date;
    MainActivity activity;
    String[] arrayArgs;


    public void setListEvent(ListEvent listEvent) {
        this.listEvent = listEvent;
    }

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

        Log.e("visible ?", String.valueOf(datePicker.getVisibility()));

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

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
                Log.e("Print args", getArgs().toString());
                listEvent.advancedSeach = buildSelection(getArgs());
                activity.replaceFragment(activity.getRecyclerFragment(),R.id.flContainer);
                listEvent.getLoaderManager().restartLoader(0,null,listEvent);
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


    public Pair<String, String[]> buildSelection(SearchArgs searchArgs){



        String select = "(";
        boolean isDate = !searchArgs.getDate().equals("");
        boolean isKey = !searchArgs.getSearched().equals("");
        List<String> args = new ArrayList<>();
        if(!isKey && !isDate) return new Pair<>("", null);
        if(isKey) {
            args.add("%"+searchArgs.getSearched()+"%");
            if (searchArgs.isKeyword()) {
                select += "( " + DbContract.MenuEntry.COLUMN_MOTS_CLES_FR + " LIKE ? )";
            } else if (searchArgs.isThematic()) {
                select += "( " +DbContract.MenuEntry.COLUMN_THEMATIQUES + " LIKE ? )";
            }
            if(isDate) select += " AND ";
        }

        if (isDate){
            select += "( "+ DbContract.MenuEntry.COLUMN_DATES +" LIKE ? )" ;
            args.add("%"+searchArgs.getDate()+"%");
        }
        select+=")";
        arrayArgs = new String[args.size()];
        for(int i =0; i<args.size();i++){
            arrayArgs[i] = args.get(i);
        }
        return new Pair<>(select,arrayArgs);
    }


    String formatDate(Date date){
        if(date!=null) {
            int day = date.getDate();
            String jour = (day < 10) ? "0" + day : "" + day;
            int month = date.getMonth();
            String mois = (month < 10) ? "0" + month : "" + month;
            return date.getYear() + "-" + mois + "-" + jour;
        }
        else {
            return "";
        }

    }



    public class SearchArgs{

        private String searched;
        private boolean keyword;
        private boolean thematic;
        private Date date;
        private String dateC ;

        @Override
        public String toString() {
            return "SearchArgs{" +
                    "searched='" + searched + '\'' +
                    ", keyword=" + keyword +
                    ", thematic=" + thematic +
                    ", Date=" + dateC +
                    '}';
        }

        public SearchArgs(String searched, boolean keyword, boolean thematic, Date date) {
            this.searched = searched;
            this.keyword = keyword;
            this.thematic = thematic;
            this.date = date;
            dateC = formatDate(date);
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

        public String getDate() {
            return dateC;
        }
    }

}
