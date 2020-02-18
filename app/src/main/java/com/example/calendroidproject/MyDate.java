package com.example.calendroidproject;

public class MyDate {
    private int year=-1;
    private int month=-1;
    private int day=-1;

    private int hour=-1;
    private int minute=-1;

    private String am_pm;

    public MyDate() {
    }

    private MyDate(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }


    private MyDate(int y, int m, int d, int h, int min, String ampm) {
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = min;
        am_pm = ampm;
    }

    public void SetMyDate(int y, int m, int d){
        year=y;
        month=m;
        day=d;

    }

    public void AddMyTime(int h, int min, String ap) {
        this.hour=h;
        this.minute=min;
        this.am_pm=ap;
    }


    public String ToString() {

        String s="";
        String prefix="";

        if (year != -1 && hour == -1) {
            s = month + "/" + day + "/" + year;
        }
        if (year != -1 && hour != -1) {
            if(minute<10)
                prefix="0";
            s =" " + hour + ":" + prefix + minute +" "+ am_pm;
        }

        return s;
    }


    public MyDate stringToDate(String s){
        MyDate d=new MyDate(5,5,5);
        return d;

    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay() {
        return day;
    }
    public String getDate() {return month + "/" + day + "/" + year;}
    public int getHour(){
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public String getAMPM(){return am_pm;}

    public void setAm_pm(String am_pm) {
        this.am_pm = am_pm;
    }

    public String TimeToStringForDatabase(){

        String s="";
        String prefix="", prefix1="";
        int h = this.hour;
        if (hour != -1) {
            if (am_pm == "PM")
                h += 12;
            if(hour<10)
                prefix1="0";

            if (minute < 10)
                prefix = "0";
            s += prefix1+ h + prefix + this.minute;

        }
        return s;
    }

}
