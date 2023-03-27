package com.dsy.dsu.Business_logic_Only_Class.AllboundServices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Code_For_Services.Service_Async_1C;

import java.util.concurrent.Executors;

public class AllBindingService {
Context context;
    public AllBindingService(Context context) {
        this.context = context;
    }
    // TODO: 27.03.2023 BINDING#2
  public   Binder ВиндингСлужбы1С() {
      final Service_Async_1C.LocalBinderGET1С[] service_Async_СинхронизацияОБЩАЯ1С =
              new Service_Async_1C.LocalBinderGET1С[1];
        try {
            ServiceConnection connectionОБЩЕЙ1СGet = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                           service_Async_СинхронизацияОБЩАЯ1С[0] = (Service_Async_1C.LocalBinderGET1С) service;
                        if (service_Async_СинхронизацияОБЩАЯ1С[0].isBinderAlive() == true) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  " +
                                    "service_Async_СинхронизацияОБЩАЯ1С.getService()" + service_Async_СинхронизацияОБЩАЯ1С[0].getService());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  " );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускСлужбыОБЩЕЙ1СGet = new Intent(context, Service_Async_1C.class);
            intentЗапускСлужбыОБЩЕЙ1СGet.setAction("com.ServiceUpdatePoОбновлениеПО");
          context.  bindService(intentЗапускСлужбыОБЩЕЙ1СGet ,connectionОБЩЕЙ1СGet,Context.BIND_AUTO_CREATE );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return service_Async_СинхронизацияОБЩАЯ1С[0];
    }








    // TODO: 27.03.2023 BINDING#2
    public   Binder МетодБиндингаОбновлениеПО() {
        final ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО[] serviceUpdatePoОбновлениеПО =
                new ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО[1];
        try {
            ServiceConnection   connectionСогласования = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        serviceUpdatePoОбновлениеПО[0] = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;
                        if (service.isBinderAlive()) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  service)"
                                    + service.isBinderAlive());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускСлужбыОбновлениеПО = new Intent(context, ServiceUpdatePoОбновлениеПО.class);
            intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");
           context. bindService(intentЗапускСлужбыОбновлениеПО ,  connectionСогласования,Context.BIND_AUTO_CREATE );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  serviceUpdatePoОбновлениеПО[0];
    }

    // TODO: 27.03.2023 BINDING#3









    
    
}
