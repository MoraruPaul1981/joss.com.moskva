package com.dsy.dsu.WorkManagers.BL_WorkMangers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Executors;

public class WorkInfoStates {

    private Context context;


    public WorkInfoStates(@NonNull Context context) {
        this.context = context;
    }

    public void startingForAnalizWorkManager(@NonNull String NameWorkManger) {
        try {

            if (!WorkManager.getInstance(context).getWorkInfosByTag(NameWorkManger).get().isEmpty()) {
                ListenableFuture<List<WorkInfo>> listListenableFuture =
                        WorkManager.getInstance(context).getWorkInfosByTag(NameWorkManger);

                List<WorkInfo> workInfoList = listListenableFuture.get();
                for (WorkInfo workInfo : workInfoList) {
                    WorkInfo.State state = workInfo.getState();
                        Log.d(this.getClass().getName(),"\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "" +
                                "state " +state);

                }

            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 07.10.2023 single


}


