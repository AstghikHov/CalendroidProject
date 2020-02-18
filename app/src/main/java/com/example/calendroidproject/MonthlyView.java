package com.example.calendroidproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

public class MonthlyView extends Fragment {

    CalendarView cal;
    TextView text;
    String strngdate;
    FloatingActionButton add;

    private void configureAddButton(View view){
        add = (FloatingActionButton) view.findViewById(R.id.AddBttn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(3).select();
            }
        });
    }

    public void configureCal (View v){
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                if(month==12){
                    month= 1;
                }
                strngdate = (month+1)+"/"+dayOfMonth+"/"+year;

                //Set the value:
                ((MainActivity) getActivity()).setGloablDate(strngdate);

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.monthly_view, container, false);


        cal= (CalendarView) v.findViewById(R.id.calendarView);
        configureCal(v);
        configureAddButton(v);


        return v;
    }
}
