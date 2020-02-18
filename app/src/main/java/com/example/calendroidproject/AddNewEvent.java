package com.example.calendroidproject;

public class AddNewEvent {
    String event;
    MyDate startTime;
    MyDate endTime ;
    String location;
    Long phoneNumber ;
    String email = null;
    String additionalDetails = null;
    String color = null;
    String tag = null;

    //Costruct
    public AddNewEvent(){

    }

    public void SetEventName(String event1)
    {
        this.event=event1;
    }

    private AddNewEvent(String event1){
        this.event = event1;
    }

    public void SetDate(int y, int m, int d){
        if(m == 12){

            this.startTime.SetMyDate(y,1,d);
            this.endTime.SetMyDate(y,1,d);
        }
        if(startTime==null){
            this.startTime = new MyDate();
        }
        if(endTime==null){
            this.endTime = new MyDate();
        }
        this.startTime.SetMyDate(y,m+1,d);
        this.endTime.SetMyDate(y,m+1,d);
    }

    public void AddStartTime(int h, int min, String ap){
        if(startTime==null){
            this.startTime = new MyDate();
        }
        this.startTime.AddMyTime(h,min,ap);
    }

    public void AddEndTime(int h, int min, String ap){
        if(endTime==null){
            this.endTime = new MyDate();
        }
        this.endTime.AddMyTime(h,min,ap);

    }

    public void SetLocation(String s){

        this.location=s;
    }

    public void SetPhoneNumber(Long number) {
        this.phoneNumber = number;
    }

    public void SetEmail(String e){
        this.email = e;
    }

    public void SetAdditionalDetail(String detail){
        this.additionalDetails = detail;
    }

    public void SetColor(String color){
        this.color=color;
    }

    public void SetTag(String tag){
        this.tag=tag;
    }

    public String EventToString(){
        if(this!=null){
            String s= this.event + "\n" + this.startTime.ToString() + "\n"+this.endTime.ToString()+
                    "\n"+this.location+"\n"+this.phoneNumber.toString()+
                    "\n"+this.email+"\n"+this.color+this.tag;
            return s;
        }
        else return "";
    }

    public String StartTimeToString(){
            if(this.startTime == null){
                return "";
            }

        return this.startTime.ToString();
    }

    public String EndTimeToString(){

        if(this.endTime == null){
            return "";
        }
        return this.endTime.ToString();
    }

    public String PhonenumtoString(){

        if(phoneNumber!=null){
            return formatPhoneNumber(phoneNumber);

        }
        return "";
    }

    private String formatPhoneNumber(Long number) {
        String p=number.toString();
        if(p.length() > 0){
            if(p.length()==10){
                return "(" + number/1000000 + ")" + " " + number/10000%1000 + "-" +number%10000;
            }
        }
        return p;
    }

    public String StartTimeAsKey(){
        return startTime.TimeToStringForDatabase();
    }

    public String EndTimeAsKey(){
        return endTime.TimeToStringForDatabase();
    }
}

