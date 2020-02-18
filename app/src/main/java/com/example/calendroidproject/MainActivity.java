package com.example.calendroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private int int_items = 5 ;
    public String globalDate = "";
    DatabaseHelper myDb;

    public boolean AddData(String [] a) {//id MUST be a number

        boolean isInserted = myDb.insertData(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10]);

        Log.d("inserted ", String.valueOf(isInserted));

        return isInserted;
    }
    public DatabaseHelper getDataBase() {
        return this.myDb;
    }


    public String getGlobalDate() {
        return this.globalDate;
    }

    public void setGloablDate(String newValue) {
        this.globalDate = newValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        //////////////////////////////////////////////////////////////////
        myDb = new DatabaseHelper(this);
        /////////////////////////////////////////////////////////////////


        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3).setVisibility(View.GONE);
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(4).setVisibility(View.GONE);
            }
        });
        viewPager.setPagingEnabled(false);

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new DailyView();
                case 1 : return new WeeklyView();
                case 2 : return new MonthlyView();
                case 3 : return new AddPage();
                case 4 : return new ColorCoding();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Daily View         ";
                case 1 :
                    return "Weekly View         ";
                case 2 :
                    return "Monthly View";
                case 3 :
                    return "Add Page";
                case 4 :
                    return "ColorCoding";
            }
            return null;
        }
    }
}