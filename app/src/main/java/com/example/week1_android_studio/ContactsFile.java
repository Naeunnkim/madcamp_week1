package com.example.week1_android_studio;

import android.app.Application;

import java.util.ArrayList;

public class ContactsFile extends Application {
    private ArrayList<String> nameArray;
    private ArrayList<String> telArray;
    private ArrayList<String> emailArray;
    private ArrayList<String> imgArray;

    public ArrayList<String> getNameArray(){
        return nameArray;
    }
    public ArrayList<String> getTelArray(){
        return telArray;
    }
    public ArrayList<String> getEmailArray(){
        return emailArray;
    }
    public ArrayList<String> getImgArray(){
        return imgArray;
    }
}
