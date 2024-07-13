package com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sous.server.R;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Services.ServiceGattServer;
import com.sous.server.presentationlayer.FragmentServerbleRecyclerView;

import java.util.Date;

public class BiFragmentBootScannerServer {

    protected  Context context;
    protected   FragmentTransaction getTransactionscanner;
    protected Long version;
    protected Activity getactivity;

    public BiFragmentBootScannerServer(Context context, FragmentTransaction getTransactionscanner, Activity getactivity,Long version) {
        this.context = context;
        this.getTransactionscanner = getTransactionscanner;
        this.version = version;
        this.getactivity = getactivity;
    }

    public void МетодЗапускаФрагментаСканирования(   ) {  ///new FragmentServerbleRecyclerView();
        try {
            FragmentServerbleRecyclerView fragmentServerbleRecyclerView1=        new FragmentServerbleRecyclerView();
            getTransactionscanner.replace(R.id.id_frameLayoutmain_boot, fragmentServerbleRecyclerView1);//.layout.activity_for_fragemtb_history_tasks
            //getTransactionscanner.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();//FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
            getTransactionscanner.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            getTransactionscanner.show(fragmentServerbleRecyclerView1);


            /*getfragmentTransaction.addToBackStack(null);





            getfragmentTransaction.replace(R.id.id_fragment_newscanner1, fragmentServerbleRecyclerView1) ;


            getfragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

            getfragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            getfragmentTransaction.show(fragmentServerbleRecyclerView1);*/
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



    public void МетодЗапускаСлужбыСканированияСервер() {  ///new FragmentServerbleRecyclerView();
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
