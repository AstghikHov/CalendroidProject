package com.example.calendroidproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPage extends Fragment {

    View viewhelperA;
    private static final String TAG = "AddEvent";

    private TextView mDisplayData;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    String amPm;
    int ap;
    TextView startTime, endTime;

    //Fill in Event Fields;
    AddNewEvent newEvent = new AddNewEvent();
    String eventName;
    DatePickerDialog dialog;
    int year, month, day, hour, minute;
    Date datedate;
    String spinnercolor;
    Spinner spinner;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewhelperA = inflater.inflate(R.layout.add_page, container, false);

        mDisplayData = (TextView) viewhelperA.findViewById(R.id.date);

        mDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                datedate = cal.getTime(); //TEMP ADDED

                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                dialog = new DatePickerDialog(viewhelperA.getContext(),
                        mDataSetListener, year, month, day);


                dialog.show();

                newEvent.SetDate(year, month, day);


            }
        });


//Initialize DatePicker
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyyy" + month + "/" + day + "/" + year);

                String date = (month + 1) + "/" + day + "/" + year;

                mDisplayData.setText(date);


            }
        };


//Start Time
        startTime = viewhelperA.findViewById(R.id.startTime);
        endTime = viewhelperA.findViewById(R.id.endTime);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                final int currentMinute = calendar.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(viewhelperA.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int pickedminutes) {

                        if (hourOfDay >= 12) {
                            amPm = "PM";
                            ap = 0;
                            if(hourOfDay>12) {
                                hourOfDay = hourOfDay - 12;
                            }else{
                               hourOfDay=12;
                            }

                        } else {
                            if(hourOfDay==0){
                                hourOfDay=12;
                            }
                            amPm = "AM";
                            ap = 1;
                        }
                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();//ADDED
                        startTime.setText(String.format("%02d:%02d", hourOfDay, pickedminutes) + amPm);
                        newEvent.AddStartTime(hourOfDay, pickedminutes, amPm);
                    }
                }, currentHour, currentMinute, false);


                timePickerDialog.show();




            }
        });


//End Time
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(viewhelperA.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                            ap = 1;
                            if(hourOfDay>12){
                                hourOfDay=hourOfDay-12;
                            }else {
                                hourOfDay = 12;
                            }
                        }
                        else {
                            if(hourOfDay==0){
                                hourOfDay=12;
                            }
                            amPm = "AM";
                            ap = 0;
                        }

                        endTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        newEvent.AddEndTime(hourOfDay, minutes, amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();

            }
        });

        //Color Coding ; Spinner
        spinner = (Spinner) viewhelperA.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(viewhelperA.getContext(),
                R.array.colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnercolor = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        if (spinner.toString().length() > 0) {
            newEvent.SetColor(adapter.toString());
        }

        final EditText event = (EditText) viewhelperA.findViewById(R.id.event);
        final EditText location = (EditText) viewhelperA.findViewById(R.id.location);
        final EditText phoneNumber = (EditText) viewhelperA.findViewById(R.id.phoneNumber);
        final EditText emailAdress = (EditText) viewhelperA.findViewById(R.id.email);
        final EditText additionalDetail = (EditText) viewhelperA.findViewById(R.id.additionalDetail);
        //final EditText tag = (EditText) viewhelperA.findViewById(R.id.tag);
        //Button viewAll = (Button) viewhelperA.findViewById(R.id.viewALL);
        //Button delete = (Button) viewhelperA.findViewById(R.id.delete);
        final TextView errorDate = (TextView) viewhelperA.findViewById(R.id.errordate);


        phoneNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        phoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
       // phoneNumber.setText(newEvent.PhonenumtoString());



        Button addEvent = viewhelperA.findViewById(R.id.layoutADD);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eventName = event.getText().toString();
                //TextView showEvent = findViewById(R.id.test);
                if (eventName.length() > 0) {
                    newEvent.event = eventName;

                    newEvent.SetEventName(eventName);

                }

                //Location
                if (location.length() > 0) {
                    String l = location.getText().toString();
                    newEvent.SetLocation(l);
                }

                //phone Number
                if (phoneNumber.length() > 0) {
                    String p = phoneNumber.getText().toString();
                    newEvent.SetPhoneNumber(Long.valueOf(p));
                }

                if (emailAdress.length() > 0) {
                    String e = emailAdress.getText().toString();
                    newEvent.SetEmail(e);
                }

                if (additionalDetail.length() > 0) {
                    newEvent.SetAdditionalDetail(additionalDetail.getText().toString());
                }


               /* if (tag.length() > 0) {
                    newEvent.SetTag(tag.getText().toString());
                }*/


                if (event.length() == 0) {
                    event.setError("Insert Event Name");


                }

                if (mDisplayData.getText().toString().length() <= 4) {
                    errorDate.setText("Enter Date");
                }


                //ADDED Crap/////////////////////////////////////////////////////
                if(eventName.length() > 0 && dialog!=null && checkEndTime(newEvent)){
                int StartTimeNum = (hour * 3600) + (minute * 60);
                String dtStart = mDisplayData.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                try {

                    Date date = format.parse(dtStart);
                    String dateAsNum = String.valueOf((int) ((date.getTime() / 1000)) + StartTimeNum);
                    Log.d("dateAsNum ", dateAsNum);

                    Cursor res1 = ((MainActivity) getActivity()).getDataBase().IDchecker(dateAsNum);
                    if (res1.getCount() != 0) {
                        dateAsNum+=1;
                        Log.d("dateAsNum ", dateAsNum);

                    }

                    String [] arr = {dateAsNum,eventName,mDisplayData.getText().toString(),newEvent.StartTimeToString(),
                            newEvent.EndTimeToString(),newEvent.location,newEvent.PhonenumtoString(),newEvent.email,
                            newEvent.additionalDetails,spinnercolor,""};


                    boolean addsuccess = ((MainActivity) getActivity()).AddData(arr);
                    if(addsuccess==true){

                        ((MainActivity) getActivity()).setGloablDate(mDisplayData.getText().toString());
                        Toast.makeText(viewhelperA.getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(viewhelperA.getContext(), "Data not Inserted", Toast.LENGTH_LONG).show();
                    }


                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }




                    event.getText().clear(); location.getText().clear();phoneNumber.getText().clear();
                    emailAdress.getText().clear(); additionalDetail.getText().clear();
                    spinner.setSelection(0);


                    TabLayout tabs = getActivity().findViewById(R.id.tabs);
                    tabs.getTabAt(0).select();
                }


            }

        });
 /*       viewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = ((MainActivity) getActivity()).getDataBase().getAllData();

                        if (res.getCount() == 0) {
                            // show message
                            //showMessage("Error", "Nothing found");
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(viewhelperA.getContext());
                            builder.setCancelable(true);
                            builder.setTitle("Error");
                            builder.setMessage("Nothing found");
                            builder.show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {

                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Date :" + res.getString(2) + "\n");
                            buffer.append("StartTime :" + res.getString(3) + "\n");
                            buffer.append("EndTime :" + res.getString(4) + "\n");
                            buffer.append("Location :" + res.getString(5) + "\n");
                            buffer.append("PhoneNum :" + res.getString(6) + "\n");
                            buffer.append("Email :" + res.getString(7) + "\n");
                            buffer.append("Notes :" + res.getString(8) + "\n");
                            buffer.append("Color :" + res.getString(9) + "\n");
                            buffer.append("Tag :" + res.getString(10) + "\n\n");

                        }

                        //showMessage("Data", buffer.toString());
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(viewhelperA.getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Data");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
        );
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = ((MainActivity) getActivity()).getDataBase().getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(viewhelperA.getContext());
                            builder.setCancelable(true);
                            builder.setTitle("Error");
                            builder.setMessage("Nothing Found");
                            builder.show();
                            return;
                        }
                        while (res.moveToNext()) {
                            Integer deletedRows = ((MainActivity) getActivity()).getDataBase().deleteData(res.getString(1));
                        }

                    }
                }
        );*/

        return viewhelperA;
    }

     public boolean checkEndTime( AddNewEvent event){
         final TextView errorTime = (TextView) viewhelperA.findViewById(R.id.errortime);
         if(newEvent.EndTimeAsKey().length()!= 0) {
             if (newEvent.StartTimeAsKey().length() == 0) {
                 errorTime.setText("Insert Start Time");
                 return false;
             } else {
                 if (event.StartTimeAsKey().compareTo(newEvent.EndTimeAsKey()) > 0) {
                     errorTime.setText("End Time Should be Later then Start Time");
                     return false;
                 }
             }
         }
         return true;
     }
}