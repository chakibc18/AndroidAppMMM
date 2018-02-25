package com.example.youssef.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.youssef.myapplication.event.information.InfoEvent;
import com.example.youssef.myapplication.event.list.ListEvent;
import com.example.youssef.myapplication.event.search.AdvancedSearch;
import com.example.youssef.myapplication.share.ShareClass;
import com.example.youssef.myapplication.util.MenuUtil;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
        ListEvent recyclerFragment;
        InfoEvent detailFragment;
        AdvancedSearch searchFragment;
        MenuUtil menuUtil;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            FirebaseApp.initializeApp(this);

            recyclerFragment = new ListEvent();
            detailFragment = new InfoEvent();
            detailFragment.setShareView(new ShareClass(this));

            menuUtil = new MenuUtil(this, recyclerFragment);
            addFragment(recyclerFragment, R.id.flContainer);

 /*           if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                detailFragment.setAttribute(getContentResolver(), recyclerFragment.position);
                addFragment(detailFragment, R.id.flContainer2);                                                           // commit FragmentTransaction
            }*/
            searchFragment = new AdvancedSearch();
            searchFragment.setListEvent(recyclerFragment);

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2 && data !=null)
        {
            String message=data.getStringExtra("MESSAGE");
            recyclerFragment.position = Integer.parseInt(message);
            getInformation(null);
        }
    }

    public void addFragment(Fragment fragment, int container){
            getSupportFragmentManager().beginTransaction()// begin  FragmentTransaction
            .add(container, fragment)                               // add    Fragment
            .commit();
        }

    public void replaceFragment(Fragment fragment, int container){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment) // replace flContainer
                    .addToBackStack(null)
                    .commit();
    }


        public void getInformation(View v){
            detailFragment.setAttribute(getContentResolver(), recyclerFragment.position);
/*            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                replaceFragment(detailFragment, R.id.flContainer2);
            }else{
                replaceFragment(detailFragment, R.id.flContainer);
            }*/
            replaceFragment(detailFragment, R.id.flContainer);
        }

    //fontion appeler par le button button_map
    public void loadMap(View v){
        Intent myIntent = new Intent(this, CarteGoogle.class);
        startActivityForResult(myIntent, 2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuUtil.setFilterView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_advance_search) {
            replaceFragment(searchFragment, R.id.flContainer);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ListEvent getRecyclerFragment() {
        return recyclerFragment;
    }

    public InfoEvent getDetailFragment() {
        return detailFragment;
    }

    public AdvancedSearch getSearchFragment() {
        return searchFragment;
    }

    public void dialog(View view){
        detailFragment.dialog();
    }
    public void confirm(View view){
        detailFragment.confirm();
    }
    public void cancel(View view){
        detailFragment.cancel();
    }
}
