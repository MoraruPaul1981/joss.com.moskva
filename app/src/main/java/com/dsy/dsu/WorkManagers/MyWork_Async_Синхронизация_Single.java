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
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import com.dsy.dsu.BusinessLogicAll.Class_Find_Setting_User_Network;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.OneSignals.ClassOneSingnalGenerator;
import com.dsy.dsu.Services.Service_For_Remote_Async_Binary;
import com.dsy.dsu.WorkManagers.BL_WorkMangers.ClassAnalyasStartingForWorkManager;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
@SuppressLint("RestrictedApi")
public class MyWork_Async_Синхронизация_Single extends Worker {
    private  String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data Disposable";

   private ClassOneSingnalGenerator class__oneSignallass;
    private  ServiceConnection serviceConnectionAsyns;

    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsyncSingleWorkManager;

    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Single(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        Log.i(getApplicationContext().getClass().getName(), " public  " +
                "MyWork_Async_Синхронизация_Single(@NonNull Context context, @NonNull WorkerParameters workerParams) " +
                "{  КонтекстОдноразовая "+"\n");
            МетодПодключениекСлубе();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
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
             getApplicationContext().  bindService(intentОбноразоваяСинхронизациия, serviceConnectionAsyns,Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
    }


    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
            if (serviceConnectionAsyns !=null) {
                getApplicationContext().unbindService(serviceConnectionAsyns);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER MyWork_Async_Public из FaceApp в MyWork_Async_Public Exception  ошибка в классе MyWork_Async_Public"
                    + e.toString());
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
        Long     ФинальныйРезультатAsyncBackgroud = 0l;
        Data   myDataОтветОдноразовойСлужбы=null;
        try{
     class__oneSignallass = new ClassOneSingnalGenerator(getApplicationContext());
      Integer ПубличныйID = getInputData().getInt("ПубличныйID",0);
      Boolean StartSingleWorker = getInputData().getBoolean("StartSingleWorker",false);
     if (ПубличныйID>0 ) {

         // TODO: 07.10.2023 анализ нужно ли запускаать сихрониазцию
         ClassAnalyasStartingForWorkManager classAnalyasStartingForWorkManager=new ClassAnalyasStartingForWorkManager(getApplicationContext());
         Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое= classAnalyasStartingForWorkManager.metodAnalyzaStaringSinleWorkManger();

         if(РешениеЗапускатьWorkManagerИлиНетАктивтиКакое==true  && StartSingleWorker==true) {


             ФинальныйРезультатAsyncBackgroud = МетодЗапускаОднаразовая();

         }
         Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                 + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud  + " StartSingleWorker  " +StartSingleWorker);
     }

            Map<String,Object> objectMap=new HashMap<>();
            objectMap.putIfAbsent("dataSingleWork",ФинальныйРезультатAsyncBackgroud);

       myDataОтветОдноразовойСлужбы = new Data.Builder()
               .putLong("ReturnSingleAsyncWork", ФинальныйРезультатAsyncBackgroud)
               .putAll(objectMap)
               .build();

            // TODO: 07.10.2023  clear
            if (localBinderAsyncSingleWorkManager !=null) {
                getApplicationContext().unbindService(serviceConnectionAsyns);
            }
// TODO: 26.03.2023
     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
             + " ФинальныйРезультатAsyncBackgroud "+ФинальныйРезультатAsyncBackgroud );

 } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
        if (ФинальныйРезультатAsyncBackgroud > 0) {
            return Result.success(myDataОтветОдноразовойСлужбы);
        }else {
               return Result.failure(myDataОтветОдноразовойСлужбы);
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



}





























