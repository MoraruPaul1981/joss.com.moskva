package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async_Binary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyWork_Async_Public extends Worker {
    private String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";

    private      ServiceConnection serviceConnection;


    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsync;
    // TODO: 28.09.2022
    public MyWork_Async_Public(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        Log.i(getApplicationContext().getClass().getName(), " public MyWork_Async_Public(@NonNull Context context, @NonNull WorkerParameters workerParams) {  Контекст "+"\n"+ this.getApplicationContext());
            // TODO: 22.12.2022
            МетодБиндингаОбщая();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
    }
    }

    private void МетодБиндингаОбщая() throws InterruptedException {
        try {
        Intent intentГлавнаяСинхрониазция = new Intent(getApplicationContext(), Service_For_Remote_Async_Binary.class);
            Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=   МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет();
            if (РешениеЗапускатьWorkManagerИлиНетАктивтиКакое==true) {
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Messenger messengerWorkManager = new Messenger(service);
                        Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                + "onServiceConnected  ОБЩАЯ messengerActivity  " + messengerWorkManager.getBinder().pingBinder());
                        if (service.isBinderAlive()) {
                            localBinderAsync = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;
                            //TODO СИНХРОНИАЗЦИЯ ГЛАВНАЯ
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + "localBinderAsync " + localBinderAsync.isBinderAlive());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                + "onServiceConnected  ОБЩАЯ messengerActivity  ");
                    }
                };
                getApplicationContext().bindService(intentГлавнаяСинхрониазция, serviceConnection, Context.BIND_AUTO_CREATE);
            }
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





    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(getApplicationContext().getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @NonNull
    @Override
    public Result doWork() {
        Integer     ФинальныйРезультатAsyncBackgroud = 0;
        Data   myDataОтветОбщейСлужбы=null;
        // TODO: 25.03.2023  ждем биндинга с службой синхронизации
        Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal     class_generation_sendBroadcastReceiver_and_firebase_oneSignal =null;
        try {
            // TODO: 25.03.2023  ждем биндинга с службой синхронизации
               class_generation_sendBroadcastReceiver_and_firebase_oneSignal =
                    new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext());
            // TODO: 11.01.2022  СВОЕЙ ТЕКУЩИЙ ID ПОЛЬЗОВАТЕЛЯ
            Integer ПубличныйIDДляОбщейСинхрониазции =
                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            if (ПубличныйIDДляОбщейСинхрониазции>0  ) {
                Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=   МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет();
                if(РешениеЗапускатьWorkManagerИлиНетАктивтиКакое==true) {

                    ФинальныйРезультатAsyncBackgroud= МетодЗапускаОбщей();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое "+РешениеЗапускатьWorkManagerИлиНетАктивтиКакое  + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);
            }


            Map<String,Object> objectMap=new HashMap<>();
            objectMap.putIfAbsent("dataPublicWork",ФинальныйРезультатAsyncBackgroud);

            myDataОтветОбщейСлужбы = new Data.Builder()
                    .putInt("ReturnSingleAsyncWork", ФинальныйРезультатAsyncBackgroud)
                    .putAll(objectMap)
                    .build();

            if (serviceConnection!=null) {
                getApplicationContext().unbindService(serviceConnection);
                localBinderAsync=null;
                serviceConnection=null;
            }


            if (ФинальныйРезультатAsyncBackgroud>0 ) {
                МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления(class_generation_sendBroadcastReceiver_and_firebase_oneSignal);
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
        if (ФинальныйРезультатAsyncBackgroud>0 ) {

            return Result.success(myDataОтветОбщейСлужбы);
       /*    if (WorkManagerОБЩИЙ.getRunAttemptCount()<2) {
                return Result.retry();
            }else {*/
        }else{
               return Result.failure(myDataОтветОбщейСлужбы);
           }


    }



    private Boolean МетодОценкаЗапускатьWorkMangerИзАктивтитиИлиНет() {
        Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
        try{
            ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                    (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
            String АктивностьЕслиЕстьTOP =null;
            List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
            if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
                КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
                if (КоличествоЗапущенныйПроуессы.size() > 0) {
                    for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                        if (ТекущаяАктивти!=null) {
                            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);
                            // TODO: 20.02.2022
                            if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                                АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                            }
                            Log.i(getApplicationContext().getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
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
                                case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App":
                                    Log.i(getApplicationContext().getClass().getName(), " ВЫХОД  .....ТекущаяАктивтиАктивностьЕслиЕстьTOP" + ТекущаяАктивти +
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
                }else {
                    // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP  + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                }
            }else {
                // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP  + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
        }
        return РешениеЗапускатьWorkManagerИлиНетАктивтиКакое;
    }



    // TODO: 16.12.2021 МЕтод ЗАпуска  Сихрониазации Чисто В форне без актвтити
    private Integer    МетодЗапускаОбщей() {
        Integer ФинальныйРезультатAsyncBackgroud=0;
        try {
            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
            Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                    + ВыбранныйРежимСети);
            if (ВыбранныйРежимСети == true && localBinderAsync!=null ) {
              ФинальныйРезультатAsyncBackgroud = localBinderAsync.getService().metodStartingSyncWorkNamager(getApplicationContext());
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ФинальныйРезультатAsyncBackgroud " + " localBinderAsync.isBinderAlive( " + localBinderAsync.isBinderAlive());
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


    private void МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления(@NonNull Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal
                                                                                      class_generation_sendBroadcastReceiver_and_firebase_oneSignal ) {
        try{
         Integer   ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                    + ПубличныйIDДляФрагмента);
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияЧАТА();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияДляЗАДАЧ();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ  СЛУЖБА ТОЛЬКО ПРИ СМНИ СТТАУСА ОТКАЗИ ИЛ ВЫПОЛНИЛ
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияДляЗАДАЧТолькоПриСменеСтатусаОтказВыполнил();
                Log.d(this.getClass().getName(), "РезультатCallsBackСинхрониазцииЧата " + "\n" + " МОДЕЛЬ ТЕЛЕФОНА  Build.DEVICE   " + Build.DEVICE +
                        "  ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    getApplicationContext().getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

}





























