package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;


import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyWork_Async_Синхронизация_Одноразовая extends Worker {
    private  Context context;
    private  String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data Disposable";
 @Inject
   private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal  class_generation_sendBroadcastReceiver_and_firebase_oneSignallass ;
    private Service_For_Remote_Async locaBinderAsync;
    private  Messenger           messengerWorkManager;
    private  ServiceConnection serviceConnection;

    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Одноразовая(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        this.context = context;
        Log.i(this.context.getClass().getName(), " public  " +
                "MyWork_Async_Синхронизация_Одноразовая(@NonNull Context context, @NonNull WorkerParameters workerParams) " +
                "{  КонтекстОдноразовая "+"\n"+ this.context);
            МетодПодключениекСлубе();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    private void МетодПодключениекСлубе() {
        try{
        Intent intentОбноразоваяСинхронизациия = new Intent(context, Service_For_Remote_Async.class);
             serviceConnection=         new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try{
                        messengerWorkManager =new Messenger(service);
                        Log.d(context.getClass().getName().toString(), "\n"
                                + "onServiceConnected  одноразовая messengerActivity  " + messengerWorkManager.getBinder().pingBinder());
                        if (service.isBinderAlive()) {
                            getTaskExecutor().postToMainThread(()->{
                                locaBinderAsync = new Service_For_Remote_Async();
                                Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                        + " МетодБиндингасМессажером onServiceConnected  binder.isBinderAlive()  " + locaBinderAsync.binderBinderRemoteAsync.isBinderAlive());
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(context.getClass().getName().toString(), "\n"
                            + "onServiceConnected  одноразовая  messengerActivity  " );
                }
            };
        context.    bindService(intentОбноразоваяСинхронизациия,serviceConnection,Context.BIND_AUTO_CREATE);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(context.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @NonNull
    @Override
    public TaskExecutor getTaskExecutor() {
        return super.getTaskExecutor();
    }

    @NonNull
    @Override
    public Result doWork() {
        Integer РезультатЗапускаСинх =0;
        Data    myDataОтветОдноразовойСлужбы=null;
 try{
     class_generation_sendBroadcastReceiver_and_firebase_oneSignallass=
             new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(context);
     // TODO: 12.10.2022  КТО ЗАПУСТИЛ
      Integer ПубличныйID = getInputData().getInt("СообщениеЧатаДляКонктерногоСотрудника",0);
      Boolean СтатусЗапускаОдноразованаяWorkManger= getInputData().getBoolean("StatusOneWokManagers",false);
     Log.i(context.getClass().getName(), "ПубличныйID"+"\n" + ПубличныйID  + " СтатусЗапускаОдноразованаяWorkManger "+СтатусЗапускаОдноразованаяWorkManger);
     if (ПубличныйID>0 && СтатусЗапускаОдноразованаяWorkManger==true) {
         ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                 (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
         String АктивностьЕслиЕстьTOP =null;
         // TODO: 03.02.2022
         List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
         Integer ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
         if (ПубличныйIDДляФрагмента == null) {
             ПубличныйIDДляФрагмента = 0;
         }
         Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n" + ПубличныйIDДляФрагмента);
         if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
             КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
         if (КоличествоЗапущенныйПроуессы.size() > 0) {
             for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                 if (ТекущаяАктивти!=null) {
                     Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                             "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                             + ТекущаяАктивти.getTaskInfo().numActivities);
                     // TODO: 20.02.2022
                     if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                         АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                     }
                     Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                             " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP +
                             "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                             + ТекущаяАктивти.getTaskInfo().numActivities);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                 }
                 if (АктивностьЕслиЕстьTOP!=null ) {
                     switch (АктивностьЕслиЕстьTOP) {// case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App" :
                    //     case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                         case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Tabels_Users_And_Passwords":
                         case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                         case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                             Log.i(context.getClass().getName(), " ВЫХОД  .....ТекущаяАктивтиАктивностьЕслиЕстьTOP" + ТекущаяАктивти +
                                     " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                             break;
                         // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                         default:
                             РезультатЗапускаСинх=
                                     МетодЗапускаКодаВнутриОдноразовойСинхронизации(ПубличныйID,
                                             ПубличныйIDДляФрагмента, КоличествоЗапущенныйПроуессы,
                                             АктивностьЕслиЕстьTOP);
                             // TODO: 26.03.2023
                             Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                     + " РезультатЗапускаСинх "+РезультатЗапускаСинх );
                             break;
                     }
                 }
             }
         }
     }
     }
     if(РезультатЗапускаСинх ==null){
         РезультатЗапускаСинх =0;
     }
     myDataОтветОдноразовойСлужбы = new Data.Builder()
             .putLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                     РезультатЗапускаСинх)
           .putBoolean("Proccesing_MyWork_Async_Синхронизация_Одноразовая",true)
             .build();
     // TODO: 28.03.2023 disebler
     if (serviceConnection!=null) {
         context.unbindService(serviceConnection);
     }
// TODO: 26.03.2023
     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
             + " РезультатЗапускаСинх "+РезультатЗапускаСинх );

 } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
        if (РезультатЗапускаСинх > 0) {
            return Result.success(myDataОтветОдноразовойСлужбы);
        }else {
               return Result.failure(myDataОтветОдноразовойСлужбы);
        }
    }






    private Integer МетодЗапускаКодаВнутриОдноразовойСинхронизации
            (Integer ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle,
             Integer ПубличныйIDДляФрагмента,
             List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы, String АктивностьЕслиЕстьTOP) {
        Integer   РезультатЗапускаСинх=0;
        try{
                // TODO: 24.11.2021  ЗАПУСК СИНХРОНИАЗХЦИИ СТРОГОВ ФОНЕ БЕЗ АКТИВТИ
               РезультатЗапускаСинх = МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(context);
                Log.i(context.getClass().getName(), " observableДляWorkmanagerОдноразовойСинхрогнизации "+
                        РезультатЗапускаСинх + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
                if (РезультатЗапускаСинх > 0) {
                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));

// TODO: 26.06.2022  ПОСЛЕ УСПЕШНОЙ СИНХРОНИАЗЦИИ ЗАПУСКАМ ONE SIGNAL  И УВЕДОМЛЕНИЯ
                    МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления
                            (ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle, ПубличныйIDДляФрагмента);
                    // TODO: 27.06.2022
                    Log.i(context.getClass().getName(), " observableДляWorkmanagerОдноразовойСинхрогнизации doOnComplete  выход "+
                            РезультатЗапускаСинх + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
                }
            // TODO: 27.06.2022
            Log.i(context.getClass().getName(), " выход observableДляWorkmanagerОдноразовойСинхрогнизации doOnComplete  выход "+
                    РезультатЗапускаСинх + " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
 return РезультатЗапускаСинх;

    }

    private void МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления
            (@NonNull  Integer ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle, @NonNull Integer ПубличныйIDДляФрагмента) {
        try{
        Log.i(context.getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                + ПубличныйIDДляФрагмента + " РезультатЗапускаФоновойОдноразовойСинхронизацииСтрогоВФОне  "   +
                " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " +ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
        // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияЧАТА();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияДляЗАДАЧ();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ  СЛУЖБА ТОЛЬКО ПРИ СМНИ СТТАУСА ОТКАЗИ ИЛ ВЫПОЛНИЛ
            class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.МетодЗапускаУведомленияДляЗАДАЧТолькоПриСменеСтатусаОтказВыполнил();
        // TODO: 14.11.2021  из оДНОРАЗОВГО ВОРК МЕНЕДЖЕРА ЗАПУСКАЕМ ONE SINGNAL
            String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
                // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL
                Log.d(this.getClass().getName(), "ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle "
                        + ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle +
                        " ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента);
                if ( ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle.compareTo(ПубличныйIDДляФрагмента)!=0)  {
                    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК Facebase and OneSignal  ///  КлючДляFirebaseNotification
                    class_generation_sendBroadcastReceiver_and_firebase_oneSignallass.
                            МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,
                                    ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
                }
            // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL
            Log.d(this.getClass().getName(), "РезультатCallsBackСинхрониазцииЧата " + "\n" + " МОДЕЛЬ ТЕЛЕФОНА  Build.DEVICE   " + Build.DEVICE +
                    "  ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle " + ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle+
                    " ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle "+ ПубличныйIDКомуНАдоОтправитьСообщениеЧерезOneSingle);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }













    // TODO: 24.11.2021  Метод КОТОРЫЕ ОПРЕДЕЛЯЕТ КАК МЫ БУДЕМ ЗАПУСКАТЬ СИНХРОНИЗАЦИЮ В ФОНЕ ИЛИ НЕТ ВЗАВИСИМОСТИ КАКАЯ АКТИВИТИ АКТИВНА

   protected Integer МетодЗапускаемприменяяДваВидаЕстьАктивнаяАктивтиИлиСтроговФоне() throws ExecutionException, InterruptedException {


       Integer РезультатЗапускаФоновойСинхронизации = 0;
        ///////
        boolean ФлагЗапущенолиКакоеннибутьАктивтиИлинет = false;

        try{
        ////////

        ///
        Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация фоновой (внутри потока) " + new Date()+
                "  ФлагЗапущенолиКакоеннибутьАктивтиИлинет "+ФлагЗапущенолиКакоеннибутьАктивтиИлинет );

// TODO: 02.07.2021 не запускать службу синхронизации



            // TODO: 29.09.2021  перед началом СИНХРОНИЗАЦИИ ПРОВЕРЯЕМ УСТАНОВКИ СЕТИ ПОЛЬЗОВАТЕЛЯ НА АКТИВТИ НАСТРОЙКИ

                Log.i(context.getClass().getName(), "ПОСЛЕ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER  внутри WORK MANGER  "
                        + new Date() + " СТАТУС WORKMANAGER  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER "
                        + WorkManager.getInstance(context).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState());


                // TODO: 24.11.2021  ЗАПУСК СИНХРОНИАЗХЦИИ СТРОГОВ ФОНЕ БЕЗ АКТИВТИ
               РезультатЗапускаФоновойСинхронизации=  МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(context);



            Log.i(context.getClass().getName(), "ПОСЛЕ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER  внутри WORK MANGER  "
                    + new Date() + " СТАТУС WORKMANAGER  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER " + WorkManager.getInstance(context).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState()+
                    "   РЕЗУЛЬТАТ  MyWork_Async_Синхронизация_Одноразовая  внутри WORK MANAGER   РезультатЗапускаФоновойСинхронизации    " +РезультатЗапускаФоновойСинхронизации );



    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
return  РезультатЗапускаФоновойСинхронизации;
}
    // TODO: 24.11.2021  Метод КОТОРЫЕ   ЗАПСУКАЮ СИНХРОНИАЗЦИЮ БЕЗ АКТИВТИ СТРГО В ФОНЕ
    protected  Integer МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити(@NonNull Context context) {
         Integer РезультатЗапускаСинхОдно = 0;
        Bundle data=new Bundle();
     try {
         boolean ФлагРазрешениеРаботысСетьюПользователем = new Class_Find_Setting_User_Network(context).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
         if (ФлагРазрешениеРаботысСетьюПользователем==true) {
                 ///////todo запускем синхронизации ОДНОРАЗОВАНАЯ
                       РезультатЗапускаСинхОдно = locaBinderAsync.МетодAsyncИзСлужбы(context);
                                 Log.d(context.getClass().getName().toString(), "\n"
                                         + "        MyWork_Async_Синхронизация_Одноразовая     РезультатЗапускаСинхОдно   "
                                         + РезультатЗапускаСинхОдно+
                                         "  serviceForTabelAsync " + locaBinderAsync);

             Log.d(this.getClass().getName(), " MyWork_Async_Синхронизация_Одноразовая ФлагРазрешениеРаботысСетьюПользователем "
                     + ФлагРазрешениеРаботысСетьюПользователем);
         }
     } catch (Exception e) {
         e.printStackTrace();
         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                 + Thread.currentThread().getStackTrace()[2].getLineNumber());
         new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                 Thread.currentThread().getStackTrace()[2].getMethodName(),
                 Thread.currentThread().getStackTrace()[2].getLineNumber());
     }
     return РезультатЗапускаСинхОдно;
    }
}





























