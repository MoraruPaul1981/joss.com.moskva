package com.dsy.dsu.BootAndAsync.BlBootAsync.Hilts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dsy.dsu.BootAndAsync.Service.IntentServiceBoot;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.PublicId.QualifierPublicId;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@QualifierEventAsyncOrUpdatePOUsers
@Module
@InstallIn(SingletonComponent.class)
public class StartingEventAsyncOrUpdatePOUsers {

Context context;

    public  @Inject StartingEventAsyncOrUpdatePOUsers(@ApplicationContext Context context) {
        this.context = context;
    }

    public void startServiceUpdatePO(){

        try{

            Intent intentstartServiceOneSignal=new Intent(context, IntentServiceBoot.class);
            intentstartServiceOneSignal.setAction("IntentServiceBootUpdatePo.com");
            context.startService(intentstartServiceOneSignal);


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
    public void startServiceBootAndAsync(){

        try{

            Intent intentstartServiceOneSignal=new Intent(context, IntentServiceBoot.class);
            intentstartServiceOneSignal.setAction("IntentServiceBootAsync.com");
            context.startService(intentstartServiceOneSignal);


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
}
