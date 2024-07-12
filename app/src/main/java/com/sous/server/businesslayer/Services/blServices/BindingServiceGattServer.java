package com.sous.server.businesslayer.Services.blServices;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Services.ServiceGattServer;

public class BindingServiceGattServer {

    private ServiceGattServer.LocalBinderСерверBLE localBinderСерверBLEGatt;
    private Long version;

    // TODO: 29.11.2022 служба Синхрониазции
    @SuppressLint("NewApi")
    public void МетодБиндингаСинхронизации(@NonNull Context context,@NonNull Message messenge) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            Intent intentБиндингсСлужбойCbyСнхрониазции = new Intent(context, ServiceGattServer.class);
            intentБиндингсСлужбойCbyСнхрониазции.setAction("sous.server.async");
            context.bindService(intentБиндингсСлужбойCbyСнхрониазции ,  new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        localBinderСерверBLEGatt = ( ServiceGattServer.LocalBinderСерверBLE) service;
                        if (service.isBinderAlive()) {
                            Log.i(context.getClass().getName(), "  onServiceConnected  onServiceConnected  МетодБиндингаСканирование"
                                    + service.isBinderAlive());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        ContentValues valuesЗаписываемОшибки = new ContentValues();
                        valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
                        valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
                        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
                        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
                        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
                        final Object ТекущаяВерсияПрограммы = version;
                        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
                        new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  localBinderСерверBLEGatt.isBinderAlive()" + localBinderСерверBLEGatt.isBinderAlive());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            },Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }



}
