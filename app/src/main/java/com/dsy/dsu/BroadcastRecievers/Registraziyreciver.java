package com.dsy.dsu.BroadcastRecievers;

import android.app.Activity;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

public class Registraziyreciver {



 public void staringregistraziyreciver(@NotNull Activity activity) {
        try{

            ClassRegistraAsyncBroadcast classRegistraAsyncBroadcast=new ClassRegistraAsyncBroadcast();

            classRegistraAsyncBroadcast.metodRegistraBroadCastFroAsyns(activity);

           /// classRegistraAsyncBroadcast.metodSendBroadCastFroAsyns(activity);
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(activity.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(activity).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}
