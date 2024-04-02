package com.dsy.dsu.BootAndAsync.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.dsy.dsu.BootAndAsync.BlBootAsync.CompleteRemoteSyncService;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.FirebaseAndOneSignal.OneSignal.StartigOneSignal.ServiceRegistraziyOneSIgnalAndFireBase;
import com.dsy.dsu.Hilt.JbossAdrress.QualifierJbossServer3;
import com.dsy.dsu.Hilt.JbossAdrress.QualifierJbossServer4;

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
 *
 *
 */

@AndroidEntryPoint
public class IntentServiceBoot extends IntentService {



    @Inject
    CompleteRemoteSyncService completeRemoteSyncService;

    @Inject
    SSLSocketFactory getsslSocketFactory2;




    @Inject
    Integer getHiltPublicId;



    @Inject
    @QualifierJbossServer3
    public LinkedHashMap<Integer,String> getHiltJbossDebug;


    @Inject
    @QualifierJbossServer4
    public LinkedHashMap<Integer,String> getHiltJbossReliz;

    public IntentServiceBoot() {
        super("IntentServiceBoot");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("oncreate IntentServiceBoot");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {

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
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
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
    }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {


            // TODO: 24.01.2024 Синхрониазция и Обновление ПО  
            if (intent.getAction().contains("IntentServiceBootAsync.com")) {
                // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                  completeRemoteSyncService.startServiceAsybc(getApplicationContext(),getsslSocketFactory2,
                        getHiltPublicId,"IntentServiceBootAsync.com",intent.getData(), getHiltJbossDebug, getHiltJbossReliz);

            }else {
                // TODO: 24.01.2024 ТОлькоОбновления ПО
                
                if (intent.getAction().contains("IntentServiceBootUpdatePo.com")) {
                    // TODO: 19.01.2024  запуск класса бизнес логики службы Синхроиазции и Обновление ПО
                    completeRemoteSyncService.startServiceUpdatePO(getApplicationContext(),getsslSocketFactory2,
                            getHiltPublicId,"IntentServiceBootUpdatePo.com", getHiltJbossDebug, getHiltJbossReliz);
                }

            }


            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                     " intent.getAction() " +intent.getAction());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



}
