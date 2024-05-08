package com.dsy.dsu.BootAndAsync.BlBootAsync.Hilts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.WorkInfo;

import com.dsy.dsu.BootAndAsync.Service.IntentServiceBoot;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.PublicId.QualifierPublicId;
import com.dsy.dsu.WorkManagers.BL_WorkMangers.WorkInfoStates;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@QualifierEventAsyncOrUpdatePOUsers
@Module
@InstallIn(SingletonComponent.class)
@Named("startingEventAsyncOrUpdatePOUsers")
public class StartingEventAsyncOrUpdatePOUsers {

    private Context context;
    private  Activity activity;
    public  @Inject  StartingEventAsyncOrUpdatePOUsers(@ApplicationContext  Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void startServiceUpdatePO(){

        try{

            Intent intentstartServiceOneSignal=new Intent(context, IntentServiceBoot.class);
            intentstartServiceOneSignal.setAction("IntentServiceBootUpdatePo.com");
            activity.startService(intentstartServiceOneSignal);


            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public void startServiceBootAndAsync(@NonNull String uri){
        try{

                // TODO: 01.04.2024
                Intent intentstartServiceOneSignal=new Intent(context, IntentServiceBoot.class);
                intentstartServiceOneSignal.setAction("IntentServiceBootAsync.com");
                intentstartServiceOneSignal.setData( Uri.parse(uri));
                activity.startService(intentstartServiceOneSignal);

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}
