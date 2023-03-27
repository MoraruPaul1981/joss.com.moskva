package com.dsy.dsu.Business_logic_Only_Class.AllboundServices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Code_For_Services.Service_Async_1C;

public class AllBindingService {
Context context;
    public Service_Async_1C.LocalBinderGET1С service_Async_СинхронизацияОБЩАЯ1С;
    public ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;
    public AllBindingService(Context context) {
        this.context = context;
    }
    // TODO: 27.03.2023 BINDING#2
    void ВиндингСлужбы1С() {
        try {
            ServiceConnection connectionОБЩЕЙ1СGet = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        Service_Async_1C.LocalBinderGET1С binder = (Service_Async_1C.LocalBinderGET1С) service;
                        if (binder.isBinderAlive() == true) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  " +
                                    "service_Async_СинхронизацияОБЩАЯ1С.getService()" + service_Async_СинхронизацияОБЩАЯ1С.getService());
                            ;
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
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  " +
                                "service_Async_СинхронизацияОБЩАЯ1С" + service_Async_СинхронизацияОБЩАЯ1С);
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
    }
    // TODO: 27.03.2023 BINDING#2


    
    
    
    
}
