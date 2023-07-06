package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async_Binary;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyWork_Async_Синхронизация_Single extends Worker {
    private  Context context;
    private  String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data Disposable";
 @Inject
   private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal  class_generation_sendBroadcastReceiver_and_firebase_oneSignallass ;
    private  ServiceConnection serviceConnection;

    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsync;

    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Single(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        this.context = context;
        Log.i(this.context.getClass().getName(), " public  " +
                "MyWork_Async_Синхронизация_Single(@NonNull Context context, @NonNull WorkerParameters workerParams) " +
                "{  КонтекстОдноразовая "+"\n"+ this.context);
            МетодПодключениекСлубе();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
    }

    private void МетодПодключениекСлубе() {
        try{

        Intent intentОбноразоваяСинхронизациия = new Intent(context, Service_For_Remote_Async_Binary.class);
            Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=   МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет();
            if (РешениеЗапускатьWorkManagerИлиНетАктивтиКакое==true) {
                serviceConnection=         new ServiceConnection() {
                   @Override
                   public void onServiceConnected(ComponentName name, IBinder service) {
                       try{
                           if (service.isBinderAlive()) {
                               localBinderAsync = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;
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
            }
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
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
        Integer     ФинальныйРезультатAsyncBackgroud = 0;
        Data   myDataОтветОдноразовойСлужбы=null;
        try{
     class_generation_sendBroadcastReceiver_and_firebase_oneSignallass= new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(context);

      Integer ПубличныйID = getInputData().getInt("СообщениеЧатаДляКонктерногоСотрудника",0);

     if (ПубличныйID>0 ) {

       Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=   МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет();

         if(РешениеЗапускатьWorkManagerИлиНетАктивтиКакое==true) {

          ФинальныйРезультатAsyncBackgroud= МетодЗапускаОднаразовая();
         }
         Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                 + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое "+РешениеЗапускатьWorkManagerИлиНетАктивтиКакое
                 + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);
     }

            Map<String,Object> objectMap=new HashMap<>();
            objectMap.putIfAbsent("dataSingleWork",ФинальныйРезультатAsyncBackgroud);

       myDataОтветОдноразовойСлужбы = new Data.Builder()
               .putInt("ReturnSingleAsyncWork", ФинальныйРезультатAsyncBackgroud)
               .putAll(objectMap)
               .build();
     if (serviceConnection!=null) {
         context.unbindService(serviceConnection);
         localBinderAsync=null;
         serviceConnection=null;
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
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
        if (ФинальныйРезультатAsyncBackgroud > 0) {
            return Result.success(myDataОтветОдноразовойСлужбы);
        }else {
               return Result.failure(myDataОтветОдноразовойСлужбы);
        }
    }









    private Boolean МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет() {
        Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
        try{
        ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        String АктивностьЕслиЕстьTOP =null;
        List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
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
                            // TODO: 26.03.2023
                            РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP   + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое);
                            break;
                        // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                        default:
                            // TODO: 26.03.2023
                            // TODO: 26.03.2023
                            РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP  + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                            break;
                    }
                }
            }
        }
    }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
        return РешениеЗапускатьWorkManagerИлиНетАктивтиКакое;
    }

    private Integer МетодЗапускаОднаразовая() {
        Integer ФинальныйРезультатAsyncBackgroud=0;
        try{
            boolean ВыбранныйРежимСети = new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
            Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                    + ВыбранныйРежимСети);
            if (ВыбранныйРежимСети == true && localBinderAsync!=null ) {
              ФинальныйРезультатAsyncBackgroud = localBinderAsync.getService().metodStartingSyncWorkNamager(getApplicationContext());
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ФинальныйРезультатAsyncBackgroud " + " service_for_remote_async " +localBinderAsync);
            }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
 return ФинальныйРезультатAsyncBackgroud;

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

}





























