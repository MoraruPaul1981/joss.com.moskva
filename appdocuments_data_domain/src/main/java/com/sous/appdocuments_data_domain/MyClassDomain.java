package com.sous.appdocuments_data_domain;

import java.util.Date;

public class MyClassDomain {

    public  String getDomain(){
        return  new Date().toLocaleString();
    }

}