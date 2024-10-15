package com.dsy.dsu.LayerBunessLogic.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Class_Get_Json_1C;
import com.dsy.dsu.LayerBunessLogic.CnangeServers.PUBLIC_CONTENT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Service_Notificatios_Для_Согласования extends IntentService {////Service
    ////////
    private Integer PROCESS_IDСогласования;
    private  String ИмяСлужбыУведомленияДляСогласование;
    private  HashMap<String, String> hashMapХэшДляЗапоминиялUUID = new HashMap();
    private   PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;

    public LocalBinderДляСогласования localBinderДляСогласования = new LocalBinderДляСогласования();
    private  Context context;

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;

    @Inject
    MutableLiveData<Intent> getHiltMutableLiveDataPay;


    public Service_Notificatios_Для_Согласования() {
        super("Service_Notificatios_Для_Согласования");
    }
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляСогласования extends Binder {
        public Service_Notificatios_Для_Согласования getService() {
            try {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Service_Notificatios_Для_Согласования.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(   context.getClass().getName(), "     public IBinder onBind(@NonNull Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());//todo super.onBind(intent)
        return  localBinderДляСогласования ;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(   context.getClass().getName(), " onCreate СЛУЖБА Service_Notificatios_Для_Согласования "
                + " время: "
                + new Date());
    }

    @Override
    public void onDestroy() {
        Log.i(   context.getClass().getName(), "  \n" +
                "      onDestroy; Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА Service_Notificatios_Для_Согласования   ДЛЯ ЧАТА onDestroy() время " + new Date());
        super.onDestroy();
    }


// TODO: 16.11.2021


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try{
            this.context=   getApplicationContext();
        Log.i(   context.getClass().getName(), "    protected void onHandleWork(@NonNull Intent intent) { " + new Date()+ " intent " +intent+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(   context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(   context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования onHandleWork Exception  PROCESS_ID   " + PROCESS_IDСогласования);

    }


    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(   context.getClass().getName(), "    public boolean onUnbind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(   context.getClass().getName(), "     public void onRebind(Intent intent) { " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        this.context=newBase;
        Log.i(context.getClass().getName(), "  attachBaseContext      protected void attachBaseContext(Context newBase) {  " + new Date()+"\n"+
                " Thread.currentThread().getName()  " +Thread.currentThread().getName());
    }
    @Override
    public boolean stopService(Intent name) {

        Log.i(   context.getClass().getName(), "  \n" +
                "        stopService  Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА Service_Notificatios_Для_Согласования   ДЛЯ ЧАТА onDestroy() время " + new Date());

        /*  this.stopSelf();*/
        return super.stopService(name);
    }




    public StringBuffer МетодЗапускаСогласованияВнутриСлужбы(@Nullable Intent intent ,@NonNull Context context,@NonNull String getHiltCommintgPays) {
        // TODO: 24.03.2022
        StringBuffer БуферРезультатаПолучаемОтветДанных =new StringBuffer();
        try {
            this.context=context;
     Bundle   bundleДляПришлиВСлужбуСогласования = intent.getExtras();
        Log.d(context.getClass().getName(), " onStartCommand СЛУЖБА bundleДляПришлиВСлужбу ЗАДАЧА  " +
                bundleДляПришлиВСлужбуСогласования + " ntent.getAction() " + intent.getAction()+  "context " +context);
            // TODO: 24.03.2022
        if (intent.getAction().equalsIgnoreCase("ЗапускаемСогласованиеОтказИлилУспешное")) {


            // TODO: 27.03.2022  главный метод СМЕНЫ СТАТУСА СОГЛСОВАНИЯ ОТМЕНЫ
            БуферРезультатаПолучаемОтветДанных=    МетодГлавныйМетодСменыСтатусаСогласованияЧерезСлужбу(intent,getHiltCommintgPays,bundleДляПришлиВСлужбуСогласования);

            Log.i(context.getClass().getName(), "" + " intent" + intent+ " БуферРезультатаПолучаемОтветДанных "+БуферРезультатаПолучаемОтветДанных);

            bundleДляПришлиВСлужбуСогласования.clear();
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования onHandleWork Exception  PROCESS_ID   " + PROCESS_IDСогласования);

    }
        return  БуферРезультатаПолучаемОтветДанных;
// TODO: 26.03.2022 запуск код только при ЗАКРЫТИЕ
    }


    private StringBuffer МетодГлавныйМетодСменыСтатусаСогласованияЧерезСлужбу(@NonNull Intent intent
            ,@NonNull String getHiltCommintgPays,
         @NonNull  Bundle   bundleДляПришлиВСлужбуСогласования ) {
        StringBuffer БуферРезультатаПолучаемОтветДанных =new StringBuffer();
        try {
            int  ПубличныйidPay = bundleДляПришлиВСлужбуСогласования.getInt("ПубличныйidPay", 0);  // TODO: 24.03.2022
            String   NumberDoc = bundleДляПришлиВСлужбуСогласования.getString("NumberDoc", "").trim();
            String      DataDoc = bundleДляПришлиВСлужбуСогласования.getString("DataDoc", "");
            int    StatusDoc = bundleДляПришлиВСлужбуСогласования.getInt("ПередаемСтатусСогласования", 0);  // TODO: 24.03.2022

// TODO: 18.01.2024  вырианты подключения к серверу соглавования  

             БуферРезультатаПолучаемОтветДанных =
                    new Class_Get_Json_1C(context,
                            getHiltCommintgPays)//TODO old debug "http://192.168.254.145/dsutest/hs/jsonto1c/listofdocuments"
                            .МетодОтправляемSimpleJSONОт1С(context,
                                    NumberDoc,
                                    DataDoc,
                                    StatusDoc,
                                    "action",
                                    ПубличныйidPay,
                                    getHiltJaksonObjectMapper);
            // TODO  http://192.168.254.145/TestWLS/hs/jsonto1c/get/  "http://192.168.254.145/TestWLS/hs/jsonto1c/Test"*/


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " БуферРезультатаПолучаемОтветДанных " +БуферРезультатаПолучаемОтветДанных);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования PROCESS_ID   " + PROCESS_IDСогласования);
        }
        ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет
 return  БуферРезультатаПолучаемОтветДанных;

    }


    public byte[]  МетодПолучаемNewFile1CСогласованияЧерезСлужбу(@NonNull Intent intent, @NonNull Context context,@NonNull    String getHiltCommintgPays ) {
        byte[]    getFileNewOt1cPayCommit=null;
        try {
            int  ПубличныйidPay = intent.getExtras().getInt("ПубличныйidPay", 0);  // TODO: 24.03.2022
            int  ПередаемСтатусСогласования = intent.getExtras().getInt("ПередаемСтатусСогласования", 0);  // TODO: 24.03.2022
            String  backfile = intent.getExtras().getString("backfile", "");  // TODO: 24.03.2022
            String  НомерТекущегоДокумента = intent.getExtras().getString("dsu1number", "");  // TODO: 24.03.2022






             getFileNewOt1cPayCommit =
                    new Class_Get_Json_1C(context,
                            getHiltCommintgPays)
                            .МетодПолучениеФайлаBinatyJSONОт1С(context,
                                    backfile,
                                    ПередаемСтатусСогласования,
                                    "binary",ПубличныйidPay,НомерТекущегоДокумента,getHiltJaksonObjectMapper);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getFileNewOt1cPayCommit " +getFileNewOt1cPayCommit  + " backfile "+ backfile);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошиюбка СЛУЖБА Service_Notificatios_Для_Согласования PROCESS_ID   " + PROCESS_IDСогласования);
        }
        ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет
        return  getFileNewOt1cPayCommit;

    }








}