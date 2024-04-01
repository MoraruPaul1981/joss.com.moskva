package com.dsy.dsu.WorkManagers;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusEndAync;
import com.dsy.dsu.BusinessLogicAll.Class_Find_Setting_User_Network;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Services.Service_For_Remote_Async_Binary;
import com.dsy.dsu.WorkManagers.BL_WorkMangers.ListenableFutures;


import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@SuppressLint("RestrictedApi")
public class MyWork_AsyncSingle extends Worker {
    private String ИмяСлужбыWorkManger ="WorkManager Synchronizasiy_Data";
    private  String ИмяСлужбыSingleWorkManger ="WorkManager Synchronizasiy_Data Disposable";

    private  ServiceConnection serviceConnectionAsyns;

    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsyncSingleWorkManager;

    private String  uri;
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private ServiceConnection   connectionОбновлениеПО;

    // TODO: 28.09.2022
    public MyWork_AsyncSingle(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
            // TODO: 01.04.2024
            МетодПодключениекСлубе();
            // TODO: 01.04.2024
            МетодБиндингаОбновлениеПО();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_AsyncSingle из FaceApp в  MyWork_AsyncSingle Exception  ошибка в классе  MyWork_AsyncSingle" + e.toString());
    }
    }

    private void МетодПодключениекСлубе() {
        try{
        Intent intentОбноразоваяСинхронизациия = new Intent(getApplicationContext(), Service_For_Remote_Async_Binary.class);
                serviceConnectionAsyns =         new ServiceConnection() {
                   @Override
                   public void onServiceConnected(ComponentName name, IBinder service) {
                       try{
                           if (service.isBinderAlive()) {
                               localBinderAsyncSingleWorkManager = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;
                               //TODO СИНХРОНИАЗЦИЯ ГЛАВНАЯ
                               Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                       + "МетодПодключениекСлубе service.isBinderAlive() "+service.isBinderAlive());
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                   + Thread.currentThread().getStackTrace()[2].getLineNumber());
                           new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                   this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                   Thread.currentThread().getStackTrace()[2].getLineNumber());
                       }
                   }
                   @Override
                   public void onServiceDisconnected(ComponentName name) {
                       Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                               + "onServiceConnected  одноразовая  messengerActivity  " );
                   }
               };
            // TODO: 01.04.2024


             getApplicationContext().  bindService(intentОбноразоваяСинхронизациия, serviceConnectionAsyns,Context.BIND_AUTO_CREATE);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_AsyncSingle из FaceApp в  MyWork_AsyncSingle Exception  ошибка в классе  MyWork_AsyncSingle" + e.toString());
    }
    }
    // TODO: 03.10.2023  метод когда не биндинга
    private void МетодБиндингаОбновлениеПО(      ) {
        try {
            Boolean asBoolenОбновлениеПО = null;
            if (localBinderОбновлениеПО==null) {
                connectionОбновлениеПО = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 28.07.2023  Update
                                localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;
                            }

                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                    + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            localBinderОбновлениеПО = null;
                            Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускСлужбыОбновлениеПО = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
                intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");
                asBoolenОбновлениеПО =getApplicationContext(). bindService(intentЗапускСлужбыОбновлениеПО, connectionОбновлениеПО, Context.BIND_AUTO_CREATE);
            }

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " asBoolenОбновлениеПО " + asBoolenОбновлениеПО);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }

    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
            getunbindService(serviceConnectionAsyns != null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }
    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(getApplicationContext().getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public TaskExecutor getTaskExecutor() {
        return super.getTaskExecutor();
    }

    @NonNull
    @Override
    public Result doWork() {
        Long ФинальныйРезультатAsyncBackgroud = 0l;
        Data Data = null;
        try {

        uri =     getInputData().getString("uri");

            // TODO: 01.04.2024  запускаем Listertable
            WorkInfo.State statePublic = new ListenableFutures(getApplicationContext()).listenableFutureWorkManager(ИмяСлужбыWorkManger);
            if (statePublic != WorkInfo.State.RUNNING) {
                // TODO: 01.04.2024  запускаем сихпонизацию общую  
                ФинальныйРезультатAsyncBackgroud = МетодЗапускаОднаразовая();
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ФинальныйРезультатAsyncBackgroud " + ФинальныйРезультатAsyncBackgroud
                    + " statePublic  " + statePublic + " ФинальныйРезультатAsyncBackgroud" + ФинальныйРезультатAsyncBackgroud+
                    " uri " +uri);


            Map<String, Object> objectMap = new HashMap<>();
            objectMap.putIfAbsent("dataSingleWork", ФинальныйРезультатAsyncBackgroud);

            Data = new Data.Builder()
                    .putLong("ReturnSingleAsyncWork", ФинальныйРезультатAsyncBackgroud)
                    .putString("uri", uri)
                    .putAll(objectMap)
                    .build();

            // TODO: 07.10.2023  clear
            getunbindService(localBinderAsyncSingleWorkManager != null);

            // TODO: 01.04.2024  выходим
            if (uri.equalsIgnoreCase("MainActivityBootAndAsync")) {
                // TODO: 01.04.2024  
                metoEndingAsynsDashboard();
            }


// TODO: 26.03.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ФинальныйРезультатAsyncBackgroud " + ФинальныйРезультатAsyncBackgroud+ " uri " +uri);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.04.2024  
            Result.failure(Data);
        }

        return Result.success(Data);
        
    }

    
    
    
    
    
    private void getunbindService(boolean localBinderAsyncSingleWorkManager) {
        try{
        if (localBinderAsyncSingleWorkManager) {
            getApplicationContext().unbindService(serviceConnectionAsyns);
        }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.04.2024

    }


    }


    @SuppressLint("SuspiciousIndentation")
    private Long МетодЗапускаОднаразовая() {
        Long ФинальныйРезультатAsyncBackgroud=0l;
        try{
            boolean ВыбранныйРежимСети = new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
            Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                    + ВыбранныйРежимСети);

            if (ВыбранныйРежимСети == true && localBinderAsyncSingleWorkManager !=null ) {

                ФинальныйРезультатAsyncBackgroud = localBinderAsyncSingleWorkManager.getService().metodStartingSync( getApplicationContext());
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ФинальныйРезультатAsyncBackgroud " + " service_for_remote_async " + localBinderAsyncSingleWorkManager);
            }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
 return ФинальныйРезультатAsyncBackgroud;

    }

    void metoEndingAsynsDashboard() {
        try {

            Intent intentAnsycEnding=new Intent("AnsycEnding");
            intentAnsycEnding.setAction("Broad_messageAsyncOrUpdatePO");
            Bundle bundle=new Bundle();

            bundle.putBinder("callbackbinderdashbord",localBinderОбновлениеПО);
            bundle.putString("Статус",   "AnsycEnding");///"В процесс"
            intentAnsycEnding.putExtras(bundle);

            EventBus.getDefault().post(new MessageEvensBusEndAync(intentAnsycEnding));

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}





























