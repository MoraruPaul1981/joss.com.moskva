package com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Services.ServiceGattServer;

import java.util.Date;

public class BiFragmentBootScannerServer {

    protected  Context context;
    protected   FragmentTransaction getTransactionscanner;
    protected Long version;
    protected FragmentManager fragmentManager;

    protected Activity getactivity;

    public BiFragmentBootScannerServer(Context context, FragmentTransaction getfragmentTransaction, Activity getactivity) {
        this.context = context;
        this.getTransactionscanner = getTransactionscanner;
        this.version = version;
        this.fragmentManager = fragmentManager;
        this.getactivity = getactivity;
    }

    public void МетодЗапускаФрагментаСканирования(Fragment fragmentServerUser, FragmentTransaction getfragmentTransaction) {  ///new FragmentServerUser();
        try {



              /*      getfragmentTransaction.addToBackStack("");
                    //fragmentTransaction.add(R.id.framelauoutScanner, fragment.getClass(),bundle);//.layout.activity_for_fragemtb_history_tasks
            getfragmentTransaction.replace(R.id.id_fragment_newscanner1, fragment).setPrimaryNavigationFragment(fragment);//.layout.activity_for_fragemtb_history_tasks
            getfragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            getfragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            getfragmentTransaction.show(fragment);*/
            Log.i(context.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );


// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данные
            Log.i(this.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }



    public void МетодЗапускаСлужбыСканированияСервер() {  ///new FragmentServerUser();
        try {

            Intent startGATTServiceGattServer = new Intent(context, ServiceGattServer.class);
            context.startService(startGATTServiceGattServer);

            Log.i(context.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );


// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данные
            Log.i(this.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }



}
