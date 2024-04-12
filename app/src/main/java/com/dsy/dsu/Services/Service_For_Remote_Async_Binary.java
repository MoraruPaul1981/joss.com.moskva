package com.dsy.dsu.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.JbossAdrress.QualifierJbossServer3;
import com.dsy.dsu.Hilt.JbossAdrress.QualifierJbossServer4;
import com.dsy.dsu.SynsProccessor.AsynsProccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.annotations.concurrent.Background;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
@AndroidEntryPoint
public class Service_For_Remote_Async_Binary extends IntentService {
    protected LocalBinderAsync binderBinderRemoteAsync = new LocalBinderAsync();
    private Service_For_Public.LocalBinderОбщий localBinderОбщий;
    private      Integer PublicID =0;
    @Inject
    ObjectMapper getHiltJaksonObjectMapper;
    @Inject
    SSLSocketFactory getsslSocketFactory2;
    String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";


    @Inject
    @QualifierJbossServer3
    public LinkedHashMap<Integer,String> getHiltJbossDebug;


    @Inject
    @QualifierJbossServer4
    public LinkedHashMap<Integer,String> getHiltJbossReliz;



    @Inject
    Integer getHiltPublicId;

    public Service_For_Remote_Async_Binary() {
        super("Service_For_Remote_Async");
    }
    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onCreate() {
        super.onCreate();
        try{
      /*     AHilt a = EntryPoints.get(getApplicationContext(), HiltInterface.class).metodA();
         String HILTTTT=   a.metodA();*/
          //  sqlite.isOpen();
            /// ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish().МетодПолучениеяПубличногоID(getApplicationContext());

            МетодБиндинuCлужбыPublic(getApplicationContext());

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  ПубличныйIDДляФрагмента " + PublicID);


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
   public class LocalBinderAsync extends Binder {
        public Service_For_Remote_Async_Binary getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return Service_For_Remote_Async_Binary.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
  try{
            data = Parcel.obtain();
            reply = Parcel.obtain();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            ///return super.onTransact(code, data, reply, flags);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return   true;
        }

        @Override
        public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
            super.linkToDeath(recipient, flags);
        }

        @Override
        public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
            return super.unlinkToDeath(recipient, flags);
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return binderBinderRemoteAsync;
    }








    @SuppressLint("WrongThread")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    @Override
    public void onDestroy() {
        try{
        super.onDestroy();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.attachBaseContext(newBase);
    }









// TODO: 05.12.2023 work manager

    @BinderThread
    @Background
    public Long metodStartingSync(  @NotNull Context context) {
        Long       ФинальныйРезультатAsyncBackgroud=0l;
        try{
            // TODO: 25.03.2023 ДОПОЛНИТЕОТНЕ УДЛАНИЕ СТАТУСА УДАЛЕНИЕ ПОСЛЕ СИНХРОНИАЗЦИИ
            ФинальныйРезультатAsyncBackgroud  = new AsynsProccessor(context,getHiltJaksonObjectMapper,
                    getsslSocketFactory2,getHiltJbossDebug,
                    getHiltJbossReliz,getHiltPublicId)
                    .МетодЗАпускаФоновойСинхронизации(context  );


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud + " getHiltPublicId " +getHiltPublicId);
            // TODO: 26.03.2023 дополнительное удаление после Удаление статсу удалнеенон
            if (ФинальныйРезультатAsyncBackgroud>0) {
                МетодПослеСинхрониазцииУдалениеСтатусаУдаленный(getApplicationContext());
            }

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ФинальныйРезультатAsyncBackgroud;
    }












    private void МетодПослеСинхрониазцииУдалениеСтатусаУдаленный(@NonNull Context context) {
        try {
            Intent intentПослеСинхроницииРегламентаняРаботаУдалениеДанных=new Intent();
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setClass(context, Service_For_Public.class);
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
            // TODO: 25.03.2023 дополнительное удаление после синхрониазции статус Удаленныц
            if (localBinderОбщий!=null) {
                localBinderОбщий.getService().МетодГлавныйPublicPO(context,intentПослеСинхроницииРегламентаняРаботаУдалениеДанных,
                        null);
            }
            Log.d(context.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +   "  localBinderОбщий  " +localBinderОбщий);

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


    public void МетодБиндинuCлужбыPublic(@NonNull Context context) {
        try {
            Intent intentЗапускPublicService = new Intent(context, Service_For_Public.class);
            intentЗапускPublicService.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
         ServiceConnection connectionPUBLIC;       connectionPUBLIC=     new ServiceConnection() {
                     @Override
                     public void onServiceConnected(ComponentName name, IBinder service) {
                         try {
                             if (service.isBinderAlive()) {
                                 localBinderОбщий = (Service_For_Public.LocalBinderОбщий) service;
                                 // TODO: 16.11.2022
                                 Log.d(context.getClass().getName(), "\n"
                                         + " время: " + new Date() + "\n+" +
                                         " Класс в процессе... " + this.getClass().getName() + "\n" +
                                         " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                         + "   service.pingBinder() " + service.pingBinder());
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
                         try {
                             Log.d(context.getClass().getName(), "\n"
                                     + " время: " + new Date() + "\n+" +
                                     " Класс в процессе... " + this.getClass().getName() + "\n" +
                                     " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                     + "    onServiceDisconnected  localBinderОбщий" + localBinderОбщий);
                         } catch (Exception e) {
                             e.printStackTrace();
                             Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                     + Thread.currentThread().getStackTrace()[2].getLineNumber());
                             new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                     Thread.currentThread().getStackTrace()[2].getMethodName(),
                                     Thread.currentThread().getStackTrace()[2].getLineNumber());
                             // TODO: 11.05.2021 запись ошибок

                         }
                     }
                 };
           context. bindService(intentЗапускPublicService, connectionPUBLIC ,Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}