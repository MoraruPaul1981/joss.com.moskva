package com.sous.server.Brodcasters;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;


import androidx.lifecycle.LiveData;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.multiprocess.ListenableCallback;


import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;
import com.sous.server.Firebases.MyFirebaseMessagingServiceServerScanners;
import com.sous.server.Workmanagers.MyWorkAsyncScannerServer;
import com.sous.server.Errors.SubClassErrors;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class BroadcastReceiverWorkManagerScannersServer extends BroadcastReceiver {
    private Long version=0l;
    private Context context;
    public BroadcastReceiverWorkManagerScannersServer() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  BroadcastReceiverWorkManagerScannersServer " + new Date().toLocaleString());
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            this.context=context;
            // TODO: 01.02.2023 запуск workmanager синхронизации
            МетодИнициализацийСинхронизацияДанныхWorkManager();
            // TODO: 01.02.2023   повтореый запуск службы Server Scanner
            МетодПолучениеServerСканеарКлюча_OndeSignal( "220d6edf-2b29-453e-97a8-d2aefe4a9eb0");
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

    private void МетодИнициализацийСинхронизацияДанныхWorkManager()
            throws ExecutionException, InterruptedException {
        try {
        String ИмяСлужбыСинхронизации = "WorkManager Synchronizasiy_DataScanners";
        Data myDataДляОбщейСинхрониазации = new Data.Builder()
                .putInt("КтоЗапустилWorkManagerДляСинхронизации", 1)
                .build();
        Constraints constraintsСинхронизация = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(false)
                .setRequiresStorageNotLow(false)
                .build();
        PeriodicWorkRequest periodicWorkRequestСинхронизация = new PeriodicWorkRequest.Builder(MyWorkAsyncScannerServer.class,
                PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)//MIN_PERIODIC_FLEX_MILLIS////  PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
                .addTag(ИмяСлужбыСинхронизации)
                .setInputData(myDataДляОбщейСинхрониазации)
                .setConstraints(constraintsСинхронизация)
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS)
                //    .setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                .build();


            List<WorkInfo> workInfo = WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get();
        if (  workInfo.size()>=0){
            Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА ..." +
                    ".Внутри BroadcastReceiverWorkManagerScannersServer  callbackRunnable.name() "
                    + "  workInfo " +workInfo+  "   workInfo.hasObservers() " +  workInfo + "  workInfo.getState() " +workInfo.get(0).getState());
            WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                    ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequestСинхронизация);
        }
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
    public  String МетодПолучениеServerСканеарКлюча_OndeSignal(@NonNull String КлючДляFirebaseNotification) {
        String ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = null;
        try {
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
                //TODO srating......  oneSignal
                Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ Scanner  OneSignal........ "+ КлючДляFirebaseNotification +"\n");
                OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
                // todo OneSignal Initialization
                OneSignal.initWithContext(context);
                ///////todo srating Google Notifications wits PUblic Key
                OneSignal.setAppId(КлючДляFirebaseNotification);
                OneSignal.disablePush(false);
                //TODO srating.......... firebase cloud --ПРИШЛО СООБЩЕНИЕ
                FirebaseMessagingService firebaseMessagingService =new MyFirebaseMessagingServiceServerScanners();
                //TODO srating......  oneSignal
                Log.d(this.getClass().getName(), "  FirebaseMessagingService"  );
                // TODO: 07.12.2021
                firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");
                Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ SERVER BLE КОНЕЦ  OneSignal........  220d6edf-2b29-453e-97a8-d2aefe4a9eb0 " );
                // TODO: 15.12.2021 настройки onesigmnal
                Map<String, String> params = new HashMap<String, String>();
                OneSignal.sendTag("Authorization", "Basic 220d6edf-2b29-453e-97a8-d2aefe4a9eb0");
                OneSignal.sendTag("Content-type", "application/json");
                OneSignal.sendTag("grp_msg", "serverscanners");
                OneSignal.sendTag("android_background_data", "true");
                OneSignal.sendTag("content_available", "true");
                // TODO: 14.02.2023 получаем uuid для onesingal
                String tokenOneSignal=   OneSignal.getDeviceState().getPushToken();
            String   tokenSmsNumber=   OneSignal.getDeviceState().getSMSNumber();
                //TODO srating......  oneSignal
                ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = OneSignal.getDeviceState().getUserId();
                // TODO: 15.12.2021
                Log.d(this.getClass().getName(), "  ПОСЛЕ КЛЮЧ ДЛЯ SERVER SCANNER  OneSignal........  220d6edf-2b29-453e-97a8-d2aefe4a9eb0  "+"\n"+
                        "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggerValueForKey("GT_PLAYER_ID")+
                        "     OneSignal.getTriggers() " +   OneSignal.getTriggers()+"\n"+
                        "    ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal ОТ СЕРВЕРА ::: " + ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal+ "tokenOneSignal" +tokenOneSignal+
                        " tokenSmsNumber " +tokenSmsNumber );
                // TODO: 13.12.2021
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                ContentValues valuesЗаписываемОшибки=new ContentValues();
                valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
                valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
                valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
                valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
                final Object ТекущаяВерсияПрограммы = version;
                Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
                new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
            }
            return ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal;

    }
}