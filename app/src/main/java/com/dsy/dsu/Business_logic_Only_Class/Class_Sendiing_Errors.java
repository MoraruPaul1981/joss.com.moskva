package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.CREATE_DATABASE;
import com.dsy.dsu.AllDatabases.Error.CREATE_DATABASE_Error;

import java.util.concurrent.ExecutionException;

public class Class_Sendiing_Errors {

    Context context;
    ////

    public Class_Sendiing_Errors(Context context) {
        //
        this.context =context;

    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ


    public void МетодПослываемОшибкиАдминистаторуПо(StringBuffer ЗаписьОшибковВстврочку,
                                                    Activity activity,Integer ПубличноеIDПолученныйИзСервлетаДляUUID) {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"errorsousabto@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "errorsousabto@gmail.com"+" ID пользователя чьи ошибки: " +ПубличноеIDПолученныйИзСервлетаДляUUID);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            i.putExtra(Intent.EXTRA_TEXT   , ЗаписьОшибковВстврочку.toString());
                /////TODO ВТОРОЙ ШАГ СИНХРОНИЗАЦИИ ПОЛУЧАЕМ СПИСОК ТАБЛИЦ КОТОРЫЕ НУЖНО  СИНХРОНИЗИРОВАТЬ 100% процентов , И ПРОВЕРМЯЕМ ЕСЛИ СВЯЗЬ С ИНТЕНТОМ
                    // TODO: 02.06.2022 код удаление ошибков после успешной отпавки ошибок на почту
                Handler.Callback callback=new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull android.os.Message msg) {
                        msg.getTarget().postDelayed(()->{
                            Toast.makeText(activity, "Отправляем...", Toast.LENGTH_LONG).show();
                        },2000);

                        return true;
                    }
                };
              Handler  handlerОшибки = new Handler(callback);

                Message message = new Message();
                Bundle bundle=new Bundle();
                handlerОшибки.sendMessage(message);
                activity. startActivity(Intent.createChooser(i, "Отправка ошибок..."));

            // TODO: 28.06.2023 очищаем таблиц
            МетодУдаланиеОшибок();
                activity.finish();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }


    // TODO: 28.06.2023 Запись Ошибков 
    private void МетодУдаланиеОшибок() throws ExecutionException, InterruptedException {
        try {
            SQLiteDatabase sqLiteDatabaseError= new CREATE_DATABASE_Error(context).getССылкаНаСозданнуюБазу();

            sqLiteDatabaseError.beginTransaction();
        SQLiteStatement sqLiteStatementУдаление= sqLiteDatabaseError.compileStatement("delete from   errordsu1");
          long РезультатУдаление=  sqLiteStatementУдаление.executeInsert();
            long РезультатУдалениеДопол=      sqLiteStatementУдаление.simpleQueryForLong();

            sqLiteDatabaseError.setTransactionSuccessful();
            sqLiteDatabaseError.endTransaction();
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +  " РезультатУдаление  " +РезультатУдаление +
                     " РезультатУдалениеДопол  "+РезультатУдалениеДопол);

    } catch (Exception e) {
        e.printStackTrace();
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}


