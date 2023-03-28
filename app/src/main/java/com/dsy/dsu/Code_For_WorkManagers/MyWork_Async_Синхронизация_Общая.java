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
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyWork_Async_Синхронизация_Общая extends Worker {

    private String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";

    private WorkInfo WorkManagerОБЩИЙ;
    @Inject
    private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal class_generation_sendBroadcastReceiver_and_firebase_oneSignal;
    private Service_For_Remote_Async localBinderAsync;
    private  Messenger           messengerWorkManager;
    private      ServiceConnection serviceConnection;
    private  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Общая(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        Log.i(getApplicationContext().getClass().getName(), " public MyWork_Async_Синхронизация_Общая(@NonNull Context context, @NonNull WorkerParameters workerParams) {  Контекст "+"\n"+ this.getApplicationContext());
            // TODO: 22.12.2022
            МетодБиндингаОбщая();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    private void МетодБиндингаОбщая() throws InterruptedException {
        try {
        Intent intentГлавнаяСинхрониазция = new Intent(getApplicationContext(), Service_For_Remote_Async.class);
           serviceConnection=  new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    messengerWorkManager = new Messenger(service);
                    Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                            + "onServiceConnected  ОБЩАЯ messengerActivity  " + messengerWorkManager.getBinder().pingBinder());
                    IBinder binder = messengerWorkManager.getBinder();
                    if (binder.isBinderAlive()) {
                        getTaskExecutor().postToMainThread(()->{
                            localBinderAsync = new Service_For_Remote_Async();
                            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                    + " МетодБиндингасМессажером onServiceConnected  binder.isBinderAlive()  " + binder.isBinderAlive());
                        });
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                            + "onServiceConnected  ОБЩАЯ messengerActivity  ");
                }
            };
        getApplicationContext().bindService(intentГлавнаяСинхрониазция,serviceConnection,Context.BIND_AUTO_CREATE);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
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
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER MyWork_Async_Синхронизация_Общая из FaceApp в MyWork_Async_Синхронизация_Общая Exception  ошибка в классе MyWork_Async_Синхронизация_Общая"
                + e.toString());
    }
    }

    // TODO: 26.12.2021  метод регистации на СЕРВЕРА ONESIGNAL
    private void МетодРегистрацииУстройсвоНАFirebaseAndOneSignal() {
        try{
            Integer  ПубличныйIDДляФрагмента=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                    МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,ПубличныйIDДляФрагмента);
            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,0); " +
                    " РезультатЗаписиНовогоIDОтСервреаOneSignal  " );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
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
           Data myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = null;
        Integer    РезультатЗапускаОбщейСинх=0;
        try {
            // TODO: 25.03.2023  ждем биндинга с службой синхронизации
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal =
                    new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext());
// TODO: 10.12.2022  РЕГЕСТИРУЕМСЯ НА ONESIGNAL FIREBASE
            МетодРегистрацииУстройсвоНАFirebaseAndOneSignal();

            // TODO: 11.01.2022  СВОЕЙ ТЕКУЩИЙ ID ПОЛЬЗОВАТЕЛЯ
            Integer ПубличныйIDДляОбщейСинхрониазции =
                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            // TODO: 01.01.2022
            if (ПубличныйIDДляОбщейСинхрониазции == null) {
                ПубличныйIDДляОбщейСинхрониазции = 0;
            }
            Log.d(this.getClass().getName(), "ПубличныйIDДляОбщейСинхрониазции " + ПубличныйIDДляОбщейСинхрониазции);
            ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                    (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
            if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
                List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
              String  АктивностьЕслиЕстьTOP=new String();
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
                                    break;
                                // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                                default:
                                    // TODO: 26.03.2023
                                    РезультатЗапускаОбщейСинх=     МетодЗапускаОбщей();
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() +
                                            "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " КоличествоЗапущенныйПроуессы.size() " +КоличествоЗапущенныйПроуессы.size()
                                            +  "РезультатЗапускаОбщейСинх " +РезультатЗапускаОбщейСинх);
                                    break;
                            }
                        }
                    }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                          + " КоличествоЗапущенныйПроуессы.size() " +КоличествоЗапущенныйПроуессы.size());
                }else {
                    // TODO: 26.03.2023
                 РезультатЗапускаОбщейСинх=     МетодЗапускаОбщей();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() +
                            "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " КоличествоЗапущенныйПроуессы.size() " +КоличествоЗапущенныйПроуессы.size()
                            +  "РезультатЗапускаОбщейСинх " +РезультатЗапускаОбщейСинх);
                }
            } else {
                // TODO: 26.03.2023
                РезультатЗапускаОбщейСинх=     МетодЗапускаОбщей();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ЗапущенныйПроуессыДляОбщейСинхрониазации" +ЗапущенныйПроуессыДляОбщейСинхрониазации
                        +  "РезультатЗапускаОбщейСинх " +РезультатЗапускаОбщейСинх);
            }


            myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = new Data.Builder()
                    .putInt("ReturnPublicAsyncWorkMananger",
                            РезультатЗапускаОбщейСинх)
                    .putLong("WorkManangerVipolil",
                           Long.parseLong(РезультатЗапускаОбщейСинх.toString()))
                    .build();

            // TODO: 28.03.2023 diseble
            if (serviceConnection!=null) {
                getApplicationContext().unbindService(serviceConnection);
            }
// TODO: 25.03.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатЗапускаОбщейСинх "+РезультатЗапускаОбщейСинх );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER MyWork_Async_Синхронизация_Общая из FaceApp в MyWork_Async_Синхронизация_Общая Exception  ошибка в классе MyWork_Async_Синхронизация_Общая"
                    + e.toString());
        }
        if (РезультатЗапускаОбщейСинх>0 ) {
            return Result.success(myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы);
       /*    if (WorkManagerОБЩИЙ.getRunAttemptCount()<2) {
                return Result.retry();
            }else {*/
        }else{
               return Result.failure(myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы);
           }


    }

    // TODO: 16.12.2021 МЕтод ЗАпуска  Сихрониазации Чисто В форне без актвтити
    private Integer    МетодЗапускаОбщей() {
        Integer РезультатЗапускаОбщейСинх=0;
        try {
            // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
            boolean ВыбранныйРежимСети =
                    new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
            Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                    + ВыбранныйРежимСети);

            if (ВыбранныйРежимСети == true) {
                // TODO: 21.11.2021  НАЧАЛО СИХРОНИЗХАЦИИИ общая
                РезультатЗапускаОбщейСинх = localBinderAsync.МетодAsyncИзСлужбы(getApplicationContext());
                Log.d(getApplicationContext().getClass().getName().toString(),
                        "\n" + "      MyWork_Async_Синхронизация_Общая       РезультатЗапускаОбщейСинх[0]   " + РезультатЗапускаОбщейСинх);
                Log.d(this.getClass().getName(), "  serviceForTabelAsync " + localBinderAsync);
            }
            if (РезультатЗапускаОбщейСинх>0 ) {
                Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));
                // TODO: 16.11.2022 запускаем ondesingle FIREBASE
                МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления();
            }
       } catch (Exception e) {
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                   " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                   Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
        return РезультатЗапускаОбщейСинх;
    }


    private void МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления() {
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





























