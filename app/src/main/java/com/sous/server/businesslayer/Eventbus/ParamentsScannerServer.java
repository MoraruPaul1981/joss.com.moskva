package com.sous.server.businesslayer.Eventbus;

import android.os.Bundle;

import java.util.concurrent.ConcurrentHashMap;

public class ParamentsScannerServer {

   private String s1;
    private String s2;
    private  String s3;
    private String s4;
    private  String s5;


    private     Boolean флагЗапускаФрагментRecyreView=false;
    private ConcurrentHashMap<String, Bundle> concurrentHashMapGattBundle=new ConcurrentHashMap();






    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    private String getS2() {
        return s2;
    }

    private void setS2(String s2) {
        this.s2 = s2;
    }

    private String getS3() {
        return s3;
    }

    private void setS3(String s3) {
        this.s3 = s3;
    }

    private String getS4() {
        return s4;
    }

    private void setS4(String s4) {
        this.s4 = s4;
    }

    private String getS5() {
        return s5;
    }

    private void setS5(String s5) {
        this.s5 = s5;
    }


    public Boolean getФлагЗапускаФрагментRecyreView() {
        return флагЗапускаФрагментRecyreView;
    }

    public void setФлагЗапускаФрагментRecyreView(Boolean флагЗапускаФрагментRecyreView) {
        this.флагЗапускаФрагментRecyreView = флагЗапускаФрагментRecyreView;
    }

    public ConcurrentHashMap<String, Bundle> getConcurrentHashMapGattBundle() {
        return concurrentHashMapGattBundle;
    }

    public void setConcurrentHashMapGattBundle(ConcurrentHashMap<String, Bundle> concurrentHashMapGattBundle) {
        this.concurrentHashMapGattBundle = concurrentHashMapGattBundle;
    }
}
