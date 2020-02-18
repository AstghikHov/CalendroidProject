package com.example.calendroidproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeeklyView extends Fragment {

    View viewhelperW;
    TextView textView;
    String datestrng;
    int daysfromtoday;
    FloatingActionButton add;
    ImageButton color;
    ImageButton monday;
    ImageButton tuesday;
    ImageButton wednesday;
    ImageButton thursday;
    ImageButton friday;
    ImageButton saturday;
    ImageButton sunday;


    //Global Variables
    Calendar calendar = Calendar.getInstance();
    ArrayList output = new ArrayList();
    SimpleDateFormat date = new SimpleDateFormat("EEEE d");
    SimpleDateFormat month = new SimpleDateFormat("MMMM, yyyy");

    //Add Button, Opens Add Page activity
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
    public void configureColorCoding(View view){
        color = (ImageButton) view.findViewById(R.id.ColorButton);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(4).select();
            }
        });
    }
    public void dateSetter(String day, String month){
        String str = day+", "+month;
        try {
            Date date = new SimpleDateFormat("EEEE d, MMMM, yyyy").parse(str);
            String formattedDate = new SimpleDateFormat("M/d/yyyy").format(date);

            ((MainActivity) getActivity()).setGloablDate(formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public String dateChecker(String earlyday){
        TextView sunView = viewhelperW.findViewById(R.id.sunTxt);
        TextView monthView = viewhelperW.findViewById(R.id.monthText);

        String firstday = earlyday.replaceAll("\\D+","");
        String laterday = sunView.getText().toString().replaceAll("\\D+","");

        if(Integer.valueOf(firstday) > Integer.valueOf(laterday)){

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat s = new SimpleDateFormat("MMMM, yyyy");
            try {
                cal.setTime(s.parse(monthView.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.add(Calendar.MONTH, -1);
            return s.format(cal.getTime());
        }
        return monthView.getText().toString();
    }
    public void configureMondayBttn(View view){
        monday = (ImageButton) view.findViewById(R.id.MonButton);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView monView = viewhelperW.findViewById(R.id.monTxt);

                dateSetter(monView.getText().toString(), dateChecker(monView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureTuesdayBttn(View view){
        tuesday = (ImageButton) view.findViewById(R.id.TuesButton);
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tuesView = viewhelperW.findViewById(R.id.tuesTxt);

                dateSetter(tuesView.getText().toString(), dateChecker(tuesView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureWedBttn(View view){
        wednesday = (ImageButton) view.findViewById(R.id.WedButton);
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView wedView = viewhelperW.findViewById(R.id.wedTxt);

                dateSetter(wedView.getText().toString(), dateChecker(wedView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureThursBttn(View view){
        thursday = (ImageButton) view.findViewById(R.id.ThursButton);
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView thursView = viewhelperW.findViewById(R.id.thursTxt);

                dateSetter(thursView.getText().toString(), dateChecker(thursView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureFriBttn(View view){
        friday = (ImageButton) view.findViewById(R.id.FriButton);
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView friView = viewhelperW.findViewById(R.id.friTxt);

                dateSetter(friView.getText().toString(), dateChecker(friView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureSatBttn(View view){
        saturday = (ImageButton) view.findViewById(R.id.SatButton);
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView satView = viewhelperW.findViewById(R.id.satTxt);

                dateSetter(satView.getText().toString(), dateChecker(satView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    public void configureSunBttn(View view){
        sunday = (ImageButton) view.findViewById(R.id.SunButton);
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sunView = viewhelperW.findViewById(R.id.sunTxt);

                dateSetter(sunView.getText().toString(), dateChecker(sunView.getText().toString()));

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(0).select();
            }
        });
    }
    //Displays Current Week on startup
    public void currentView(){
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DATE,1);
            output.add(date.format(calendar.getTime()));
        }
        TextView monthView = viewhelperW.findViewById(R.id.monthText);
        TextView monView = viewhelperW.findViewById(R.id.monTxt);
        TextView tuesView = viewhelperW.findViewById(R.id.tuesTxt);
        TextView wedView = viewhelperW.findViewById(R.id.wedTxt);
        TextView thursView = viewhelperW.findViewById(R.id.thursTxt);
        TextView friView = viewhelperW.findViewById(R.id.friTxt);
        TextView satView = viewhelperW.findViewById(R.id.satTxt);
        TextView sunView = viewhelperW.findViewById(R.id.sunTxt);
        monthView.setText(month.format(calendar.getTime()));
        monView.setText(output.get(0).toString());
        tuesView.setText(output.get(1).toString());
        wedView.setText(output.get(2).toString());
        thursView.setText(output.get(3).toString());
        friView.setText(output.get(4).toString());
        satView.setText(output.get(5).toString());
        sunView.setText(output.get(6).toString());
    }

    //method called upon back or forward button click
    public void buttonView(int weekInput){
        //Goes one week forward or backwards depending on which button clicked
        calendar.add(Calendar.DAY_OF_WEEK,weekInput);
        for(int i = 0; i < 7; i++){
            calendar.add(Calendar.DATE,1);
            output.add(date.format(calendar.getTime()));
        }
        TextView monthView = viewhelperW.findViewById(R.id.monthText);
        TextView monView = viewhelperW.findViewById(R.id.monTxt);
        TextView tuesView = viewhelperW.findViewById(R.id.tuesTxt);
        TextView wedView = viewhelperW.findViewById(R.id.wedTxt);
        TextView thursView = viewhelperW.findViewById(R.id.thursTxt);
        TextView friView = viewhelperW.findViewById(R.id.friTxt);
        TextView satView = viewhelperW.findViewById(R.id.satTxt);
        TextView sunView = viewhelperW.findViewById(R.id.sunTxt);
        monthView.setText(month.format(calendar.getTime()));
        monView.setText(output.get(0).toString());
        tuesView.setText(output.get(1).toString());
        wedView.setText(output.get(2).toString());
        thursView.setText(output.get(3).toString());
        friView.setText(output.get(4).toString());
        satView.setText(output.get(5).toString());
        sunView.setText(output.get(6).toString());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewhelperW = inflater.inflate(R.layout.weekly_view, container, false);
        //textView=viewhelperW.findViewById(R.id.monthText);


        //Creates current week view on startup
        currentView();

        //Forward Button
        final ImageButton nextButton = viewhelperW.findViewById(R.id.forwardButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.clear();
                buttonView(0);
            }
        });

        //Back Button
        final ImageButton prevButton = viewhelperW.findViewById(R.id.backButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.clear();
                buttonView(-14);
            }
        });

        configureAddButton(viewhelperW);
        configureColorCoding(viewhelperW);
        configureMondayBttn(viewhelperW);
        configureTuesdayBttn(viewhelperW);
        configureWedBttn(viewhelperW);
        configureThursBttn(viewhelperW);
        configureFriBttn(viewhelperW);
        configureSatBttn(viewhelperW);
        configureSunBttn(viewhelperW);

        return viewhelperW;
    }
}