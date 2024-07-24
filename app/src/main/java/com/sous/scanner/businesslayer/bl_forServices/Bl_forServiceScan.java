package com.sous.scanner.businesslayer.bl_forServices;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;

public class Bl_forServiceScan {



    private String getWorkerStateClient;
    private UUID getPublicUUID;
    private BluetoothGatt gatt;
    private BluetoothAdapter bluetoothAdapterGATT;


    @NonNull Message handlerScan;
    @NonNull LocationManager locationManager;
    @NonNull BluetoothManager bluetoothManagerServer;
    @NonNull  BluetoothAdapter bluetoothAdapterPhoneClient;
    @NonNull  Long version;

    public Bl_forServiceScan(@NonNull Message handlerScan,
                             @NonNull LocationManager locationManager,
                             @NonNull BluetoothManager bluetoothManagerServer,
                             @NonNull BluetoothAdapter bluetoothAdapterPhoneClient,
                             @NonNull Long version) {
        this.handlerScan = handlerScan;
        this.locationManager = locationManager;
        this.bluetoothManagerServer = bluetoothManagerServer;
        this.bluetoothAdapterPhoneClient = bluetoothAdapterPhoneClient;
        this.version = version;
    }


    public   void startintgServiceScan(@NonNull Intent intent,
                                     @NonNull Context context ,
                                     @NonNull int flags,
                                     @NonNull  int startId){


        Flowable.fromAction(new Action() {
                    @Override
                    public void run() throws Throwable {



                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" new Date().toLocaleString() " + new Date().toLocaleString());

                    }
                })
                .onBackpressureBuffer(true)
                .repeatWhen(repeat->repeat.delay(10, TimeUnit.SECONDS))
                .takeWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Throwable {
                        return false;
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                }).subscribe();



    }



}
