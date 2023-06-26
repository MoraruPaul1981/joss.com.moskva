package com.dsy.dsu.Code_For_Starting_BroadcastReciever_ЗдесьКодЗапускаБроадКастеров;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

import java.util.Date;

public class BroadcastReceiver_Sous_Notificatioons_For_Chats extends BroadcastReceiver {

    //WorkManager workManagerДЛяСлужбПроекта;


    String ИмяСлужбыУведомленияДляЧатаОдноразовая = "WorkManager NOtofocationForChats";

    public BroadcastReceiver_Sous_Notificatioons_For_Chats() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ  " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА" +
                "" + new Date() + "\n" +
                " Build.BRAND.toString() Название Телефона " + Build.BRAND.toString());

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ////
        Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ " +
                "  public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА"
                +(intent.getAction().toString())+" время запуска  " +new Date()+"\n"+
                " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());

        try {

            Log.i(this.getClass().getName(), " ЗАПУСК BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ   public void onReceive(Context context, Intent intent) ........ СНАРУЖИ  BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ (intent.getAction()   СЛУЖБА" +(intent.getAction().toString())+" время запуска  " +new Date());

            // TODO: 18.04.2021 запувскает широковещатель

                    // TODO: 29.09.2021     ЗАПУСК BROADCAST УВЕДОМЕЛНИЯ  ТОЛЬКО ДЛЯ ЧАТА

                        Log.i(this.getClass().getName(), "ПОСЛЕ ВЫХПОЛЕНИЯ МЕТОДА              " +
                                "МетодЗапускаWorkManager_УведомленияДляЧата DESTROY (context); Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::"
                                +(intent.getAction().toString())+"\n"+
                                " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());




        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;
            Log.e(context.getClass().getName(), " ОШИБКА В BroadcastReceiver_Sous_Notificatioons_ОДНОРАЗОВАЯ СЛУЖБА  public void onReceive  "+" ОШИБКА ::"+e.toString());


        }

    }


}