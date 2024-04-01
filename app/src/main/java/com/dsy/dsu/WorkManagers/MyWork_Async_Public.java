package com.dsy.dsu.WorkManagers;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dsy.dsu.BusinessLogicAll.Class_Find_Setting_User_Network;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.Service_For_Remote_Async_Binary;

import com.dsy.dsu.WorkManagers.BL_WorkMangers.ListenableFutures;
import com.dsy.dsu.WorkManagers.BL_WorkMangers.WorkInfoStates;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyWork_Async_Public extends Worker {
    private String ИмяСлужбыWorkManger ="WorkManager Synchronizasiy_Data";
    private  String ИмяСлужбыSingleWorkManger ="WorkManager Synchronizasiy_Data Disposable";
    private ServiceConnection serviceConnectionWorkManager;



    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsyncWorkmanager;


    // TODO: 28.09.2022
    @SuppressLint("RestrictedApi")
    public MyWork_Async_Public(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
            // TODO: 22.12.2022
            МетодБиндингаОбщая();
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_AsyncSingle из FaceApp в  MyWork_AsyncSingle Exception  ошибка в классе  MyWork_AsyncSingle" + e.toString());
    }
    }




    @NonNull
    @Override
    public ListenableFuture<ForegroundInfo> getForegroundInfoAsync() {
        return super.getForegroundInfoAsync();
    }


    @NonNull
    @Override
    public ForegroundInfo getForegroundInfo() {
        return super.getForegroundInfo();
    }



    @NonNull
    @Override
    public Result doWork() {
        Long     ФинальныйРезультатAsyncBackgroud = 0l;
        Data data = null;
        try {
            // TODO: 01.04.2024  запускаем Listertable
            WorkInfo.State stateSingle = new ListenableFutures(getApplicationContext()).listenableFutureWorkManager(ИмяСлужбыSingleWorkManger);
            if (stateSingle != WorkInfo.State.RUNNING) {
                // TODO: 01.04.2024
                ФинальныйРезультатAsyncBackgroud= МетодЗапускаОбщей();
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud+
                     " stateSingle " +stateSingle);

            Data.Builder      myDataОтветОбщейСлужбы = new Data.Builder()
                    .putLong("ReturnWorklong", ФинальныйРезультатAsyncBackgroud)
                  .putInt("ReturnWorkint", ФинальныйРезультатAsyncBackgroud.intValue());

             data=   myDataОтветОбщейСлужбы.build();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Result.failure();
        }
     return      Result.success(data);
    }



    private void МетодБиндингаОбщая() throws InterruptedException {
        try {
            Intent intentГлавнаяСинхрониазция = new Intent(getApplicationContext(), Service_For_Remote_Async_Binary.class);
                serviceConnectionWorkManager = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        if (service.isBinderAlive()) {
                            localBinderAsyncWorkmanager = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;
                            //TODO СИНХРОНИАЗЦИЯ ГЛАВНАЯ
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + "localBinderAsyncWorkmanager " + localBinderAsyncWorkmanager.isBinderAlive());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                + "onServiceConnected  ОБЩАЯ messengerActivity  ");
                    }
                };
            // TODO: 01.04.2024
            WorkManager.getInstance().cancelAllWorkByTag(ИмяСлужбыWorkManger);

                getApplicationContext().bindService(intentГлавнаяСинхрониазция, serviceConnectionWorkManager, Context.BIND_AUTO_CREATE);


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

    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
            if (serviceConnectionWorkManager !=null) {
                getApplicationContext().unbindService(serviceConnectionWorkManager);
            }
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




    // TODO: 16.12.2021 МЕтод ЗАпуска  Сихрониазации Чисто В форне без актвтити
    @SuppressLint("SuspiciousIndentation")
    private Long    МетодЗапускаОбщей() {
        Long ФинальныйРезультатAsyncBackgroud=0l;
        try {
            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
            Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                    + ВыбранныйРежимСети);

            if (ВыбранныйРежимСети == true && localBinderAsyncWorkmanager!=null ) {

              ФинальныйРезультатAsyncBackgroud = localBinderAsyncWorkmanager.getService().metodStartingSync( getApplicationContext());
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ФинальныйРезультатAsyncBackgroud " + " localBinderAsyncWorkmanager.isBinderAlive( " + localBinderAsyncWorkmanager.isBinderAlive());
            }
       } catch (Exception e) {
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                   " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                   Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
        return ФинальныйРезультатAsyncBackgroud;
    }

}





























