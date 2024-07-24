package com.sous.scanner.businesslayer.bl_forServices;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sous.scanner.businesslayer.Errors.SubClassErrors;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class Bl_forServiceScan {

    // TODO: 24.07.2024
    private String getWorkerStateClient;
    private UUID getPublicUUIDScan;
    private BluetoothGatt gatt;

    private Message handlerScan;
    private LocationManager locationManager;
    private BluetoothManager bluetoothManagerServer;
    private  BluetoothAdapter bluetoothAdapterPhoneClient;
    private Long version;
    private     @NonNull Context context;

    public Bl_forServiceScan(@NonNull Message handlerScan,
                             @NonNull LocationManager locationManager,
                             @NonNull BluetoothManager bluetoothManagerServer,
                             @NonNull BluetoothAdapter bluetoothAdapterPhoneClient,
                             @NonNull Long version,
                             @NonNull Context context ) {
        this.handlerScan = handlerScan;
        this.locationManager = locationManager;
        this.bluetoothManagerServer = bluetoothManagerServer;
        this.bluetoothAdapterPhoneClient = bluetoothAdapterPhoneClient;
        this.version = version;
        this.context = context;
    }


    public   void startintgServiceScan(@NonNull Intent intent,
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
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        ContentValues valuesЗаписываемОшибки = new ContentValues();
                        valuesЗаписываемОшибки.put("Error", throwable.toString().toLowerCase());
                        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
                        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
                        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
                        final Object ТекущаяВерсияПрограммы = version;
                        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
                        new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                }).subscribe();


        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" new Date().toLocaleString() " + new Date().toLocaleString());
    }

// TODO: 24.07.2024 end

}
