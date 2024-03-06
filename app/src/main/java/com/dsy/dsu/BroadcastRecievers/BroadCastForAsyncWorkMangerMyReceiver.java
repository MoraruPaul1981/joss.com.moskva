package com.dsy.dsu.BroadcastRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.dsy.dsu.BusinessLogicAll.ClassCreatePublicWorkManager;
import com.dsy.dsu.BusinessLogicAll.SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.Date;

public class BroadCastForAsyncWorkMangerMyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
        // TODO: This method is called when the BroadcastReceiver is receiving
            Integer ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish()
                    .МетодПолучениеяПубличногоID(context);

           // Toast.makeText(context, "ООО Союз-Автодор work Background !!! "+"\n"+new Date().toLocaleString().toString(), Toast.LENGTH_LONG).show();

             // TODO: 14.12.2023 REPLACE
             new ClassCreatePublicWorkManager(context).paspisaniyWorkMangerREPLACE(context,ПубличныйIDДляФрагмента );


            Log.d(context.getClass().getName(), "\n"
                    + " Starting.... время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " intent.getAction() " +intent.getAction());
            // TODO: 07.10.2023


            Log.d(context.getClass().getName(), "\n"
                    + " END ...время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

}
}