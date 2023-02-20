package com.sous.server.Workmanagers;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


import com.sous.server.Errors.SubClassErrors;
import com.sous.server.Services.ServiceControllerServer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MyWork_Retry_ScannerServers extends Worker {
    private Context context;
    private String ИмяСлужбыСинхронизации="WorkManager RetryServerScanners";
    private List<WorkInfo> WorkManagerScanner;
    private   ExecutorService executorServiceServerScanner =Executors.newSingleThreadExecutor();
    private ServiceControllerServer.LocalBinderСерверBLE binderСканнерServer;
    private Handler handler;
    private Long version=0l;
    private BluetoothManager bluetoothManager;
    private MutableLiveData<Bundle> mutableLiveDataGATTServer;

    // TODO: 28.09.2022
    public MyWork_Retry_ScannerServers(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
       this.context = context;
        Log.i(this.context.getClass().getName(), " public MyWork_Retry_ScannerServers(@NonNull Context context," +
                " @NonNull WorkerParameters MyWork_Retry_ScannerServers) {  Контекст "+"\n"+ this.context);
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            mutableLiveDataGATTServer=new MutableLiveData<>();
            // TODO: 22.12.2022
            МетодБиндингаОбщая();
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

    @SuppressLint("NewApi")
    private void МетодБиндингаОбщая() throws InterruptedException {
        try {
        Intent intentГлавнаяСинхрониазцияScanner = new Intent(context, ServiceControllerServer.class);
        context.bindService(intentГлавнаяСинхрониазцияScanner,Context.BIND_AUTO_CREATE,
                executorServiceServerScanner, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO: 31.01.2023 код
                Log.d(context.getClass().getName().toString(), "\n"
                        + "onServiceConnected  ОБЩАЯ MyWork_Retry_ScannerServers  ");
                binderСканнерServer = (ServiceControllerServer.LocalBinderСерверBLE) service;
                if(binderСканнерServer.isBinderAlive()){
                    Log.i(context.getClass().getName(), "    onServiceConnected  binderСканнерServer.isBinderAlive()"
                            + binderСканнерServer.isBinderAlive());
                    binderСканнерServer.linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            Log.i(context.getClass().getName(), "    onServiceConnected  binderСканнерServer.isBinderAlive()"
                                    + binderСканнерServer.isBinderAlive());
                            МетодHandler();

                        }
                    });
                }
                executorServiceServerScanner.shutdown();
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(context.getClass().getName().toString(), "\n"
                        + "onServiceConnected  ОБЩАЯ messengerActivity  ");
            }
        });
        executorServiceServerScanner.awaitTermination(1, TimeUnit.MINUTES);



            // TODO: 31.01.2023 конец work manger
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

    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(context.getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
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
    @NonNull
    @Override
    public Result doWork() {
        try {
                WorkManagerScanner = WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get();
            Log.i(context.getClass().getName(), " doWork doWork doWork RETRY MyWork_Retry_ScannerServers SERVER "+WorkManagerScanner );
         //   binderСканнерServer.getService().МетодГлавныйЗапускGattServer(handler, context,bluetoothManager,mutableLiveDataGATTServer);

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
            return Result.success();
    }
    void МетодHandler(){
        handler=          new Handler(Looper.getMainLooper(),new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull android.os.Message  msg) {
                try{
                    Bundle bundle=     msg.getData();

                    Log.d(this.getClass().getName(), "msg " + msg);
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
                return true;
            }
        });
    }
}





























