package com.sous.appdocuments_data_domain;

import android.content.Context;

import java.util.Date;


public class MyClassDomain {



    public  String getDomain(){
        Context context = null;
        System.out.printf("context"+context);
        return  new Date().toLocaleString();
    }

}