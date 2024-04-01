package com.dsy.dsu.WorkManagers.BL_WorkMangers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.List;
import java.util.function.Consumer;

public class WorkInfoStates {

    private Context context;


    public WorkInfoStates(@NonNull Context context) {
        this.context = context;
    }
    WorkInfo.State state;
    public  void workInfoStatesWorkManager(@NonNull String NameWorkManger, @NonNull LifecycleOwner lifecycle) {
        try {
            WorkManager.getInstance(context).getWorkInfosByTagLiveData(NameWorkManger)
                    .observe( lifecycle, workStatus -> {
                        if (workStatus != null && workStatus.get(0).getState().isFinished()) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                    });

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


