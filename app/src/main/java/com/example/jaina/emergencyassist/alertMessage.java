package com.example.jaina.emergencyassist;

/**
 * Created by Jaina on 11/27/2017.
 */
import android.telephony.SmsManager;
import android.widget.Toast;


public class alertMessage {



    //sets up variables for the message string
    //as well as strings to hold up to 5 contact numbers
    private String msg;
    private String pn1;
    private String pn2;
    private String pn3;
    private String pn4;
    private String pn5;
    private String[] contacts;

    alertMessage(){

        //sets up variables for the message string
        //as well as strings to hold up to 5 contact numbers
        msg = "HERE IS THE DEFAULT MESSAGE";
        pn1 = null;
        pn2 = null;
        pn3 = null;
        pn4 = null;
        pn5 = null;
        contacts = new String[] {pn1, pn2, pn3, pn4, pn5};

    }

    //Getters and setters for all variables
    public  String getMsg() {
        return msg;
    }
    public void   setMsg(String msg) {
        this.msg = msg;
    }
    public String getPn1() {
        return pn1;
    }
    public void   setPn1(String pn1) {
        this.pn1 = pn1;
    }
    public String getPn2() {
        return pn2;
    }
    public void   setPn2(String pn2) {
        this.pn2 = pn2;
    }
    public String getPn3() {
        return pn3;
    }
    public void   setPn3(String pn3) {
        this.pn3 = pn3;
    }
    public String getPn4() {
        return pn4;
    }
    public void   setPn4(String pn4) {
        this.pn4 = pn4;
    }
    public String getPn5() {
        return pn5;
    }
    public void   setPn5(String pn5) {
        this.pn5 = pn5;
    }


    //Method used on button click to send messages
    public void sendSMS(SmsManager smsM) {

        //loops through the contacts and if the contact is not null
        //sends an sms to the contact
        for (int i = 0; i <= 4; i++) {
            if (contacts[i] != null) {
                try {

                    smsM.sendTextMessage(contacts[i], null, msg, null, null);

                } catch (Exception e) {

                    e.printStackTrace();

                } //end catch
            }//end if
        }//end for

    }//end sendSMS


}
