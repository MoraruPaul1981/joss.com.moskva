package com.sous.appdocuments_data_domains;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class MyDomains {


    public String getDomains(@NotNull Context context){
        System.out.printf("context"+context);
        return  new Date().toLocaleString();
    }



}
