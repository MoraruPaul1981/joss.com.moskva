package com.sous.server.businesslayer.Eventbus;

import android.os.Bundle;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParamentsScannerServer {



    private String currentTask;
    private String s2;
    private  String s3;



    private List<String> list;
    private  String s5;


    private     Boolean флагЗапускаФрагментRecyreView=false;
    private ConcurrentHashMap<String, Bundle> concurrentHashMapGattBundle=new ConcurrentHashMap();



    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
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
