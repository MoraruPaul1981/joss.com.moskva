package com.dsy.dsu.BroadcastRecievers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.google.errorprone.annotations.NoAllocation;

import java.util.Date;

public class ClassRegistraAsyncBroadcast {


    public void metodRegistraBroadCastFroAsyns(@NonNull Context context){
try{
        // TODO: 08.10.2023
        BroadCastForAsyncWorkMangerMyReceiver
                broadCastForAsyncWorkMangerMyReceiver
                = new BroadCastForAsyncWorkMangerMyReceiver();

        IntentFilter intentFilter = new IntentFilter( Intent.ACTION_SCREEN_ON);
    intentFilter.addAction(Intent.ACTION_SCREEN_ON);
    intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
    intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
    intentFilter.addAction(Intent.ACTION_REBOOT);
    intentFilter.addAction(Intent.ACTION_SHUTDOWN);
    context.   registerReceiver( broadCastForAsyncWorkMangerMyReceiver  , intentFilter);
    Log.d(context.getClass().getName(), "\n"
            + " время: " + new Date()+"\n+" +
            " Класс в процессе... " +  this.getClass().getName()+"\n"+
            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
}
    public void metodSendBroadCastFroAsyns(@NonNull Context context,@NonNull String setAction){
        try{
            // TODO: 08.10.2023


            Intent intentBroadCastForAsyncWorkMangerMyReceiver = new Intent(context,BroadCastForAsyncWorkMangerMyReceiver.class);
            intentBroadCastForAsyncWorkMangerMyReceiver.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentBroadCastForAsyncWorkMangerMyReceiver.setAction(setAction);
            Bundle extras = new Bundle();
            extras.putString("send_data", "test");
            intentBroadCastForAsyncWorkMangerMyReceiver.putExtras(extras);
           context. sendBroadcast(intentBroadCastForAsyncWorkMangerMyReceiver);


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





}
