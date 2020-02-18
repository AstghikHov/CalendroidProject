package com.example.calendroidproject;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class ColorCoding extends Fragment {


    TextView textView;
    String datestrng;
    FloatingActionButton add;
    ExpandableListView expandableListView;
    LinkedHashMap<String, List<String>> items;
    View viewhelperC;

    public void expandableListmaker (int numEvents){
        expandableListView = viewhelperC.findViewById(R.id.expandableListView);
        String [] colorarr = {"Red", "Green", "Orange", "Blue", "Yellow", "Purple"};

        items = new LinkedHashMap<>();

        for(int i = 0; i<colorarr.length; i++) {
            Cursor res1 = ((MainActivity) getActivity()).getDataBase().getDataByColor(colorarr[i]);
            if (res1.getCount() == 0) {
                continue;
            }

            List<String> info = new ArrayList<>();//Order: Color, Tag, date, start, name
            info.add(res1.getString(9));
            info.add(res1.getString(10));

            for (int x = 0; x < res1.getCount(); x++) {

                info.add(res1.getString(3));
                info.add(res1.getString(1));
                info.add(res1.getString(2));

                res1.moveToNext();

            }
            listmaker(info);
        }

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(items);
        expandableListView.setAdapter(adapter);
    }

        public void listmaker (List<String> list){

            ArrayList<String> event = new ArrayList<>();
            int x =1;

            for(int i =2; i<list.size(); i+=3){
                event.add(x+". " +" "+list.get(i+1) +" "+list.get(i+2)+" "+ list.get(i));
                x++;
            }

        items.put(list.get(0), event);

    }
    public void removeAll () {

        expandableListView = viewhelperC.findViewById(R.id.expandableListView);

        items = new LinkedHashMap<>();

        MyExpandableListAdapter adapter = new MyExpandableListAdapter(items);

        expandableListView.setAdapter(adapter);
    }

    private void configureAddButton(){
        add = (FloatingActionButton) viewhelperC.findViewById(R.id.AddBttn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabs = getActivity().findViewById(R.id.tabs);
                tabs.getTabAt(3).select();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewhelperC = inflater.inflate(R.layout.activity_color_coding, container, false);

        textView= viewhelperC.findViewById(R.id.datetext);
        datestrng = "Color Coding";
        textView.setText(datestrng);

        configureAddButton();

        expandableListmaker(3);

        return viewhelperC;
    }
}
