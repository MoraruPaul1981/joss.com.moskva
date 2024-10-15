package com.dsy.dsu.LayerBunessLogic.BroadcastRecievers.localBroadcastReciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.LayerApp.BootAndAsyncApp.Componets.BL_innerMainActivityBootAndAsync;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import java.util.Date;


public class PrograssBarManagerBloadcastReciever {

    @MainThread
    public void  eventbroadcastManagerPrograssBar(@NonNull Context context,@NonNull BL_innerMainActivityBootAndAsync blInnerMainActivityBootAndAsync){
        try{
            LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intentComunications) {


               // blInnerMainActivityBootAndAsync  .getEventBusPrograssBar(intentComunications);

                    Log.d(context.getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   starting... onRestart" + " starting... onRestart");
                }
            }, new IntentFilter("PrograssBarManagerBloadcastReciever"));




            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public void  setBroadcastManagerPrograssBar(@NonNull Context context,@NonNull  Intent intentComunications){
        try{

            // inside on click we are calling the intent with the action.
            if (intentComunications!=null) {
                intentComunications.setAction("PrograssBarManagerBloadcastReciever");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentComunications);
            }

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 25.09.2024  end class
}
