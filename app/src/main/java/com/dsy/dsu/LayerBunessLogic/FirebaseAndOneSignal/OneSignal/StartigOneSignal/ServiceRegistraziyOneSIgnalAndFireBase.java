package com.dsy.dsu.LayerBunessLogic.FirebaseAndOneSignal.OneSignal.StartigOneSignal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Services.ServiceOneSignalForFirebase;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@QualifierInRegisterRegistraziyOneSignal
@Module
@InstallIn(SingletonComponent.class)
public class ServiceRegistraziyOneSIgnalAndFireBase {

    Context context;
    public  @Inject ServiceRegistraziyOneSIgnalAndFireBase(@ApplicationContext Context context) {
        this.context = context;
    }

   public void getStartingRegistraziyOneSignalFirebase( ){

try {
    Intent intentstartServiceOneSignal=new Intent(context, ServiceOneSignalForFirebase.class);
    intentstartServiceOneSignal.setAction("com.registariionesignal.net");
    // TODO: 28.03.2024
    context.  startService(intentstartServiceOneSignal);


    } catch (Exception e) {
    e.printStackTrace();
    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
            Thread.currentThread().getStackTrace()[2].getLineNumber());
}
    }
}
