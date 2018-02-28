package com.example.youssef.myapplication.util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youssef.myapplication.MainActivity;
import com.example.youssef.myapplication.R;
import com.example.youssef.myapplication.event.list.ListEvent;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;
import static android.view.inputmethod.EditorInfo.IME_FLAG_NO_EXTRACT_UI;

/**
 * Created by youssef on 31/12/17.
 */

public class MenuUtil implements SearchView.OnQueryTextListener {
    private SearchView filterView;
    private MainActivity activity;
    private Toolbar toolbar;
    private ListEvent listEvent;

    public MenuUtil(MainActivity activity, ListEvent listEvent) {
        this.listEvent =listEvent;
        this.activity = activity;
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
    }

    public void setFilterView(Menu menu){
        MenuItem mFilter = menu.findItem(R.id.action_search);
        filterView = (SearchView) mFilter.getActionView();
        setupFilterView();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listEvent.filter = query;
        activity.replaceFragment(activity.getRecyclerFragment(),R.id.flContainer);
        listEvent.getLoaderManager().restartLoader(0,null,listEvent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    private void setupFilterView() {
        filterView.setIconifiedByDefault(false);
        filterView.setOnQueryTextListener(this);
        filterView.setSubmitButtonEnabled(true);
        filterView.setImeOptions(IME_ACTION_DONE | IME_FLAG_NO_EXTRACT_UI);
        filterView.setQueryHint("Recherche dans titre ...");
    }


}
