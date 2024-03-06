package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.WorkManagers.MyWork_Async_Public;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClassCreatePublicWorkManager {
    Context context;
    public ClassCreatePublicWorkManager(Context context) {
        this.context =context;
    }
    // TODO: 08.10.2023 вызов из БроадКста для Синхрониазции ТОлько


    public void paspisaniyWorkMangerREPLACE(@NotNull Context context,
                                         @NotNull Integer PublicId) {
        String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";
        try{
            Data myDataДляОбщейСинхрониазации = new Data.Builder()
                    .putInt("КтоЗапустилWorkManagerДляСинхронизации", PublicId)
                    .build();
            Constraints constraintsСинхронизация= new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            PeriodicWorkRequest   periodicWorkRequestСинхронизация = new PeriodicWorkRequest.Builder(MyWork_Async_Public.class,
                    20, TimeUnit.MINUTES)//MIN_PERIODIC_FLEX_MILLIS////  PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS
                    .addTag(ИмяСлужбыСинхронизации)
                    .setInputData(myDataДляОбщейСинхрониазации)
                    .setConstraints(constraintsСинхронизация)
                    //    .setInputData(new Data.Builder().putString("КтоЗапустилWorkmanager","BroadCastRecieve").build())
                    .build();


            Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА ....Внутри MyWork_Async_Public  callbackRunnable.name() ");

            Integer callbackRunnable= WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get().size();
            if (callbackRunnable==0) {

                WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                        ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequestСинхронизация);

                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " PublicId " +PublicId);

            }

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " PublicId " +PublicId);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 08.10.2023
}
