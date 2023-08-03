package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.dsy.dsu.Code_For_WorkManagers.MyWork_Async_Public;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Class_Generation_WORK_MANGER_DIRECT {
    Context contextДляКлассаВремени;
    public Class_Generation_WORK_MANGER_DIRECT(Context context) {
        contextДляКлассаВремени=context;
    }
    public void МетодЗапускаСинхоронизацииИзШироковещательногоПриёмника(@NotNull Context context, Integer ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника) {
        String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";
        try{
            Data myDataДляОбщейСинхрониазации = new Data.Builder()
                    .putInt("КтоЗапустилWorkManagerДляСинхронизации", ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника)
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

         Integer callbackRunnable= WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get().size();
            if (callbackRunnable==0) {
                Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА ....Внутри MyWork_Async_Public  callbackRunnable.name() ");
                WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                        ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequestСинхронизация);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}
