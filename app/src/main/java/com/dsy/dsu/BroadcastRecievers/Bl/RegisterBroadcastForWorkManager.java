package com.dsy.dsu.BroadcastRecievers.Bl;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@QualifierInRegisterBoradcastWork
@Module
@InstallIn(SingletonComponent.class)
public class RegisterBroadcastForWorkManager {

Context context;
    public  @Inject RegisterBroadcastForWorkManager() {
        // TODO: 22.03.202
        this.context=context;
    }

    // TODO: 22.03.2024 запускам work manager 


 public void statingPublicWorkMAnager(@NotNull Context context ) {
        try{
            String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";
            Integer callbackRunnable= WorkManager.getInstance(context).getWorkInfosByTag(ИмяСлужбыСинхронизации).get().size();

            if (callbackRunnable==0) {
                // TODO: 22.03.2024  регистрация work manager
                new getStartingWorkmanagerPublic().metodRegistraBroadCastFroPublicAsyns(context);
            }

            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}
