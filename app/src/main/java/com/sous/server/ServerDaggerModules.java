package com.sous.server;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.datatransport.runtime.dagger.Module;
import com.sous.server.Errors.SubClassErrors;
import com.sous.server.Services.ServiceGattServer;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Provides;
@Module
public class ServerDaggerModules {
    PackageInfo pInfo = null;
    private Long version = 0l;
    private Context context;
    private ServiceGattServer.LocalBinderСерверBLE binderСканнерServer;

    public ServerDaggerModules(ServiceGattServer.LocalBinderСерверBLE binderСканнерServer) {
        this.binderСканнерServer = binderСканнерServer;
    }

    @Singleton
    @Provides
    public ServiceGattServer.LocalBinderСерверBLE МетодБиндингаОбщая(@NonNull Context context)
            throws InterruptedException, PackageManager.NameNotFoundException {
        try {
            this.context=context;
            Intent intentГлавнаяСинхрониазцияScanner = new Intent(context, ServiceGattServer.class);
            context.bindService(intentГлавнаяСинхрониазцияScanner, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    // TODO: 31.01.2023 код
                    Log.d(context.getClass().getName().toString(), "\n"
                            + "onServiceConnected  ОБЩАЯ messengerActivity  ");
                    binderСканнерServer = (ServiceGattServer.LocalBinderСерверBLE) service;
                    if(binderСканнерServer.isBinderAlive()){
                        Log.i(context.getClass().getName(), "    onServiceConnected  binderСканнерServer.isBinderAlive()"
                                + binderСканнерServer.isBinderAlive());
                        binderСканнерServer.linkToDeath(new IBinder.DeathRecipient() {
                            @Override
                            public void binderDied() {
                                Log.i(context.getClass().getName(), "    onServiceConnected  binderСканнерServer.isBinderAlive()"
                                        + binderСканнерServer.isBinderAlive());
                                Log.d(context.getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+ "  " );
                            }
                        });
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(context.getClass().getName().toString(), "\n"
                            + "onServiceConnected  ОБЩАЯ messengerActivity  ");
                }
            }, Context.BIND_AUTO_CREATE);
            // TODO: 31.01.2023 конец work manger
        } catch (Exception e) {
            e.printStackTrace();
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return  this.binderСканнерServer;
    }
}
