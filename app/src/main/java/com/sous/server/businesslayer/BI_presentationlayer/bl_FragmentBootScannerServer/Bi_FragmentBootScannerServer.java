package com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sous.server.R;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Services.ServiceGattServer;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Bi_FragmentBootScannerServer {

    protected  Context context;
    protected  FragmentManager fragmentManager;
    protected Long version;
    protected Activity getactivity;

    public Bi_FragmentBootScannerServer(Context context, FragmentManager fragmentManager, Activity getactivity, Long version) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.version = version;
        this.getactivity = getactivity;
    }

    public void МетодЗапускаФрагментаСканированияСДАнными(@NonNull Fragment fragmentServerbleRecyclerView ,
                                                          @NonNull ConcurrentHashMap<String,ContentValues> concurrentHashMapSucceesDataOtClient) {  ///new FragmentServerbleRecyclerView();
        try {
            FragmentTransaction    fragmentTransactionBoot = fragmentManager.beginTransaction();
            Bundle bundleFragmentBoottoServerFragment = new Bundle();
            bundleFragmentBoottoServerFragment.putSerializable("fromFragmentServer", (Serializable) concurrentHashMapSucceesDataOtClient);
            fragmentTransactionBoot.addToBackStack(null);
            fragmentServerbleRecyclerView.setArguments(bundleFragmentBoottoServerFragment);
            fragmentTransactionBoot.replace(R.id.id_frameLayoutmain_boot, fragmentServerbleRecyclerView);//.layout.activity_for_fragemtb_history_tasks
            fragmentTransactionBoot.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();//FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
            fragmentTransactionBoot.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransactionBoot.show(fragmentServerbleRecyclerView);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "concurrentHashMapSucceesDataOtClient  " +concurrentHashMapSucceesDataOtClient);

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




    @SuppressLint("ResourceType")
    public void МетодЗапускаФрагментаСканирования(@NonNull Fragment fragmentServerbleRecyclerView ) {  ///new FragmentServerbleRecyclerView();
        try {
            FragmentTransaction    fragmentTransactionBoot = fragmentManager.beginTransaction();
            fragmentManager.getFragments().remove(0);
            fragmentTransactionBoot.addToBackStack(null);
            fragmentTransactionBoot.replace(R.id.id_frameLayoutmain_boot, fragmentServerbleRecyclerView);//.layout.activity_for_fragemtb_history_tasks
            fragmentTransactionBoot.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).commit();//FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
            fragmentTransactionBoot.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransactionBoot.show(fragmentServerbleRecyclerView);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

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
            startGATTServiceGattServer.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startGATTServiceGattServer.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startGATTServiceGattServer.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            context.startService(startGATTServiceGattServer);

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

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
