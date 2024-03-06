package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.WorkManagers.MyWork_Async_Синхронизация_Single;

import org.jetbrains.annotations.NotNull;


public class Class_Generator_One_WORK_MANAGER extends  Class_GRUD_SQL_Operations {
    Context context;
        Class_GRUD_SQL_Operations class_grud_sql_operations=null;
    public Class_Generator_One_WORK_MANAGER( @NonNull Context context) {
        super(context);
        this.context =context;
        Log.w(this.context.getClass().getName(), "contextДляКлассаОдноразоваяСлужба " + this.context);
    }
    // TODO: 28.12.2021 srart one work manager
    public void МетодОдноразовыйЗапускВоерМенеджера(@NotNull Context context, @NonNull   Data myDataSingleWorker) {
        String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
        // com.dsy.dsu.providerdatabase
        Integer РезультатОноразовойСинхрониазции=0;
        Uri uri = Uri.parse("content://data/data/com.dsy.dsu/databases/Database DSU-1.db");
        try {

            if (!WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                WorkInfo  workInfo=   WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get() .get(0);

                if (workInfo.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            }





            Constraints constraintsЗапускСинхОдноразоваяСлужба = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(false)
                    .setRequiresStorageNotLow(false)
                    .build();
            OneTimeWorkRequest OneTimeWorkЗапускФОновойСинхрониазциииИзНУтриТабеля =
                    new OneTimeWorkRequest.Builder(MyWork_Async_Синхронизация_Single.class)
                            .setConstraints(constraintsЗапускСинхОдноразоваяСлужба)
                            .setInputData(myDataSingleWorker)
                            .addTag(ИмяСлужбыСинхронизацииОдноразовая)
                            .build();//      .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)

            WorkManager.getInstance(context).enqueueUniqueWork(ИмяСлужбыСинхронизацииОдноразовая,
                    ExistingWorkPolicy.KEEP, OneTimeWorkЗапускФОновойСинхрониазциииИзНУтриТабеля);

//////////20.15
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString()
                    , this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



}
