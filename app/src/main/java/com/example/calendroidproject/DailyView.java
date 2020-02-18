package com.example.calendroidproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class DailyView extends Fragment {

    View viewhelperD;
    TextView textView;
    public String datestrng;
    int daysfromtoday = 0;
    Button next;
    Button back;
    FloatingActionButton add;
    ExpandableListView expandableListView;
    LinkedHashMap<String, List<String>> items;

    String retrievedDate;



///////////////Added
    //TextView dateText = (TextView) viewhelperD.findViewById(R.id.textView);

    //Button delete=(Button) viewhelperD.findViewById(R.id.delete);


    //Button delete = (Button) viewhelperD.findViewById(R.id.delete);

    public void expandableListmaker (String thedate){


        expandableListView = viewhelperD.findViewById(R.id.expandableListView);
        Cursor res1 = ((MainActivity) getActivity()).getDataBase().getSomeData(thedate);

        if (res1.getCount() == 0) {
            // show message
            removeAll();
            Toast.makeText(viewhelperD.getContext(), "Nothing Found", Toast.LENGTH_LONG).show();
            return;
        }

        items = new LinkedHashMap<>();


        //String color=res1.getString(9);

        for(int  x=0; x<res1.getCount(); x++) {
            listmaker(res1.getString(0), res1.getString(1), res1.getString(2),
                    res1.getString(3), res1.getString(4), res1.getString(5),
                    res1.getString(6), res1.getString(7), res1.getString(8),
                    res1.getString(9), res1.getString(10));



            res1.moveToNext();

        }

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(items);
        expandableListView.setAdapter(adapter);


    }
    public void listmaker (String id, String name, String date1, String starttime, String endtime1, String location,
                           String phonenum, String email, String notes, String color, String tag){

        ArrayList<String> event = new ArrayList<>();
        //event.add("ID is " +id);
        event.add("Date is " +date1);
        if(endtime1!=null)
            event.add("Event ends at " +endtime1);
        if(location!=null)
            event.add("Location: "+location);
        if (phonenum.length()!=0)
            event.add("Phone Number: "+phonenum);
        if(email!=null)
            event.add("Email: "+email);
        if (notes!=null)
            event.add("Notes: "+notes);
        if(color.compareTo("No Color")!= 0)
            event.add("Color: "+color);
        //event.add("Tag: "+tag);



       /* if(color.compareTo("Yellow")==0)
            dateText.setTextColor(Color.parseColor("#ffff66"));
        if(color.compareTo("Blue")==0)
            dateText.setBackgroundColor(Color.parseColor("#0099ff"));
            */
        items.put(starttime+" "+name, event);


    }



    public void removeAll () {

        expandableListView = viewhelperD.findViewById(R.id.expandableListView);

        items = new LinkedHashMap<>();

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(items);

        expandableListView.setAdapter(adapter);
    }

        //Gets the Date as a String
    public static String getCalculatedDate(String date, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("M/d/yyyy");
        try {
            cal.setTime(s.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(cal.getTime());
    }
    //Next Button For The Date
    private void configureNextButton(View view){
        next = (Button) view.findViewById(R.id.nxtbttn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datestrng =getCalculatedDate(datestrng, 1);
                textView.setText(datestrng);
                expandableListmaker(datestrng);

            }
        });
    }
    //Back Button For The Date
    private void configureBackButton(View view){
        back = (Button) view.findViewById(R.id.bckbttn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datestrng =getCalculatedDate(datestrng, -1);
                textView.setText(datestrng);
                expandableListmaker(datestrng);

            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    //This is basically the Main Method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setUserVisibleHint(false);

        viewhelperD= inflater.inflate(R.layout.daily_view, container, false);//you need one of these to do stuff in fragments/tabs
        textView=viewhelperD.findViewById(R.id.datetext);


        datestrng =getCalculatedDate("M/d/yyyy", daysfromtoday);
        retrievedDate = ((MainActivity) getActivity()).getGlobalDate();

        Log.d("datestrng", datestrng);

        textView.setText(datestrng);

        if(retrievedDate!=""){
            datestrng = retrievedDate;
            textView.setText(retrievedDate);
            Log.d("datestrng", datestrng);
            Log.d("retrievedDate", retrievedDate);

        }

        configureNextButton(viewhelperD);
        configureBackButton(viewhelperD);
        configureAddButton(viewhelperD, datestrng);



        expandableListmaker(datestrng);

        return viewhelperD;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Add Button, Opens Add Page PopUpWindow
    private void configureAddButton(View view, String date){
        add = (FloatingActionButton) viewhelperD.findViewById(R.id.AddBttn);
        final String d=date;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(3).select();

            }
        });

    }

    //private void configurateDeleteButton()
}
