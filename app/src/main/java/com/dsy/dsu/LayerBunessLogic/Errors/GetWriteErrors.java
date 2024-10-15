package com.dsy.dsu.LayerBunessLogic.Errors;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class GetWriteErrors {

    Context context;

    public GetWriteErrors(Context context) {
        this.context = context;
    }

    public    Integer writeErrors(@NotNull ContentValues contentValuesError){
        // TODO: 14.10.2024
        Integer getwiteNeError=0;
        try{
            String ИмяТаблицы= "errordsu1";
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/"+ИмяТаблицы+"");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderError = context.getContentResolver();
                // TODO: 12.04.2023 UPDATER model_ssl
                getwiteNeError=  contentProviderError.update(uri, contentValuesError,null,null);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  getwiteNeError " +getwiteNeError);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getwiteNeError;
    }



}
