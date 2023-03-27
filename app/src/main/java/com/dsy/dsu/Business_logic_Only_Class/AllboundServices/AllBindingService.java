package com.dsy.dsu.Business_logic_Only_Class.AllboundServices;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Code_For_Services.Service_Async_1C;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.google.android.datatransport.runtime.dagger.Provides;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

public class AllBindingService {
Context context;
    Message message;
    public AllBindingService(@NonNull  Context context,@NonNull Message message) {
        this.context = context;
        this.message = message;
    }
    // TODO: 27.03.2023 BINDING#1
    @Provides
    @Singleton
  public   void ВиндингСлужбы1С() {
      final Service_Async_1C.LocalBinderGET1С[] service_Async_СинхронизацияОБЩАЯ1С =
              new Service_Async_1C.LocalBinderGET1С[1];
        try {
            ServiceConnection connectionОБЩЕЙ1СGet = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                           service_Async_СинхронизацияОБЩАЯ1С[0] = (Service_Async_1C.LocalBinderGET1С) service;
                        if (service.isBinderAlive() == true) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  " +
                                    "service_Async_СинхронизацияОБЩАЯ1С.getService()" + service.isBinderAlive());
                            Bundle bundle=new Bundle();
                            bundle.putBinder("allbinders",  service_Async_СинхронизацияОБЩАЯ1С[0]);
                            message.setData(bundle);
                            message.getTarget().dispatchMessage(message);
                           // message.sendToTarget();
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
    }








    // TODO: 27.03.2023 BINDING#2
    @Provides
    @Singleton
    public   void МетодБиндингаОбновлениеПО() {
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
                            Bundle bundle=new Bundle();
                            bundle.putBinder("allbinders",  serviceUpdatePoОбновлениеПО[0]);
                            message.setData(bundle);
                            message.getTarget().dispatchMessage(message);
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
    }

    // TODO: 27.03.2023 BINDING#3


    // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
    @Provides
    @Singleton
    public void   МетодБиндингМатериалы() {
        final Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов[] binderМатериалы =
                new Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов[1];
        try {
            ServiceConnection    serviceConnectionМатериалы = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                           binderМатериалы[0] = (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;
                        if (binderМатериалы[0].isBinderAlive()) {
                            Bundle bundle=new Bundle();
                            bundle.putBinder("allbinders",  binderМатериалы[0]);
                            message.setData(bundle);
                            message.getTarget().dispatchMessage(message);
                            Log.d(context.getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  Service_for_AdminissionMaterial" + " service "
                                    + service.isBinderAlive());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускБиндингаМатериалы = new Intent(context, Service_for_AdminissionMaterial.class);
            intentЗапускБиндингаМатериалы.setAction("com.Service_for_AdminissionMaterial");
            context. bindService(intentЗапускБиндингаМатериалы, serviceConnectionМатериалы,Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 27.03.2023 BINDING#4
    @Provides
    @Singleton
    private void МетодБиндингаСогласования() {
        final Service_Notificatios_Для_Согласования.LocalBinderДляСогласования[] binderСогласования1C
                = new Service_Notificatios_Для_Согласования.LocalBinderДляСогласования[1];
        try {
            ServiceConnection       connectionСогласования = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        binderСогласования1C[0] = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) service;
                        if (service.isBinderAlive()) {
                            Bundle bundle=new Bundle();
                            bundle.putBinder("allbinders",  binderСогласования1C[0]);
                            message.setData(bundle);
                            message.getTarget().dispatchMessage(message);
                            Log.i(context.getClass().getName(), "    onServiceConnected  service.isBinderAlive()"
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
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()" + binderСогласования1C[0].isBinderAlive());
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
            Intent intentЗапускСлужюыыСинхрониазцииБиндинг1C = new Intent(context, Service_Notificatios_Для_Согласования.class);
            intentЗапускСлужюыыСинхрониазцииБиндинг1C.setAction("com.Service_Notificatios_Для_Согласования");
          context.  bindService(intentЗапускСлужюыыСинхрониазцииБиндинг1C , connectionСогласования, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void МетодБиндингRemoteAsync() {
        try{
            Intent intentОбноразоваяСинхронизациия = new Intent(context, Service_For_Remote_Async.class);
            context.    bindService(intentОбноразоваяСинхронизациия, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try{
                        Messenger    messenger =new Messenger(service);
                        if (service.isBinderAlive()) {
                            Log.d(context.getClass().getName().toString(), "\n"
                                    + "onServiceConnected  одноразовая messengerActivity  " +  messenger.getBinder().pingBinder());
                                Service_For_Remote_Async    locaBinderAsync = new Service_For_Remote_Async();
                                Bundle bundle=new Bundle();
                                bundle.putParcelable("allbinders", (Parcelable) messenger);
                                message.setData(bundle);
                                message.getTarget().dispatchMessage(message);
                                Log.d(context.getClass().getName().toString(), "\n"
                                        + " МетодБиндингасМессажером onServiceConnected  binder.isBinderAlive()  " + locaBinderAsync.binderBinderRemoteAsync);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d(context.getClass().getName().toString(), "\n"
                            + "onServiceConnected  одноразовая  messengerActivity  " );
                }
            },Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
        }
    }

}
