package com.dsy.dsu.Code_For_Starting_BroadcastReciever_ЗдесьКодЗапускаБроадКастеров;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_WorkManagers.MyWork_Notifocations_Уведомления_Для_ОбновлениеПО;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BroadcastReceiver_Sous_Notificatios_UpdateSoft extends BroadcastReceiver {
    public BroadcastReceiver_Sous_Notificatios_UpdateSoft() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР  BroadcastReceiver_Sous_Notificatios_UpdateSoft   " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatios_UpdateSoft  (intent.getAction()   СЛУЖБА" + new Date() + "\n" +
                " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
       Bundle bundleПринудительныйЗапросОбновлениеПО= intent.getExtras();
                    Boolean ПринудительныйЗапросОбновлениеПО=bundleПринудительныйЗапросОбновлениеПО.getBoolean("ПринудительныйЗапросОбновлениеПО",false);
                    // TODO: 29.09.2021     ЗАПУСК BROADCAST УВЕДОМЕЛНИЯ  ТОЛЬКО ДЛЯ ЧАТА
                    Log.i(this.getClass().getName(), " ПринудительныйЗапросОбновлениеПО" + ПринудительныйЗапросОбновлениеПО);

                  МетодЗапускаWorkManager_УведомленияДляОбновления(context,ПринудительныйЗапросОбновлениеПО );

                    Log.i(this.getClass().getName(), "ПОСЛЕ ВЫХПОЛЕНИЯ МЕТОДА              " +
                            "МетодЗапускаWorkManager_УведомленияДляОбновлениеПО(context); Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::"
                            +(intent.getAction().toString())+"\n"+
                            " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;
            Log.e(context.getClass().getName(), " ОШИБКА В BroadcastReceiver_Sous_Notificatios_UpdateSoft  СЛУЖБА  public void onReceive  " + " ОШИБКА ::" + e.toString());


        }

    }




















    // TODO: 02.04.2021 метод запуска службы из BroadCastas


    private void МетодЗапускаWorkManager_УведомленияДляОбновления(@NotNull Context context, @NotNull  Boolean ПринудительныйЗапросОбновлениеПО) {
        ///
        String ИмяСлужбыУведомленияДляОбновлениеСофт="WorkManager NOtofocationforUpdateSoft";
        try{
            Log.i(context.getClass().getName(), "ЗАПУСК из BroadcastReceiver СЛУЖБА  УВЕДОМЛЕНИЯ  Обновлнение ПО  ДЛя Чата doWork   время "+"\n"
                    +new Date() + " СТАТУС WORKMANAGER" + WorkManager.getInstance(context.getApplicationContext()).getWorkInfosByTag(ИмяСлужбыУведомленияДляОбновлениеСофт)+
                       " ПринудительныйЗапросОбновлениеПО " +ПринудительныйЗапросОбновлениеПО);
            Data myDataобновлениеПО = new Data.Builder()
                    .putBoolean("ПринудительныйЗапросОбновлениеПО", ПринудительныйЗапросОбновлениеПО)
                    .build();
            Constraints constraintsУведомленияДляОбновлениеПО= new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            PeriodicWorkRequest periodicWorkRequestУведомленияДляОбновлениеПО=new PeriodicWorkRequest.Builder(MyWork_Notifocations_Уведомления_Для_ОбновлениеПО.class,
                   1, TimeUnit.DAYS)//MIN_PERIODIC_FLEX_MILLIS
                    .setConstraints(constraintsУведомленияДляОбновлениеПО)
                    .addTag(ИмяСлужбыУведомленияДляОбновлениеСофт)
                    .setInputData(myDataобновлениеПО)
                    //.setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                    .build();
// Queue the work
            WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыУведомленияДляОбновлениеСофт,
                    ExistingPeriodicWorkPolicy.REPLACE,periodicWorkRequestУведомленияДляОбновлениеПО);

            // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата         // TODO: 13.11.2021  ПОКАЗЫВАЕМ СТАТУС ПОСЛЕ ОТРАБОТАНГНЙО WORK MANAGER  ПРИ Уведомления для Чата

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;

            Log.e(context.getClass().getName(), "ОШИБКА CATCH  из BroadcastReceiver  СЛУЖБА УВЕДОМЛЕНИЯ для UpdateSoft  в BroadCasrReciver    private void МетодЗапускаWorkManager_УведомленияДляЧата "+e.toString());


        }


    }























}