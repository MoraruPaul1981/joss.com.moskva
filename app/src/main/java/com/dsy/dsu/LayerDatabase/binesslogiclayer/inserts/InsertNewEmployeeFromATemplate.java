package com.dsy.dsu.LayerDatabase.binesslogiclayer.inserts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class InsertNewEmployeeFromATemplate {

    Context context;
    Long version;


    public  @Inject InsertNewEmployeeFromATemplate(@ApplicationContext Context hiltcontext) {
        this.context = hiltcontext;

    }


  public   Integer   insertingaNewEmployeeFromATemplate(@NotNull String tableoperations,
                                                 @NotNull ContentValues contentValuesInsertingaNewEmployeeFromATemplate){
        // TODO: 26.11.2024
        Integer   insertingaNewEmployeeFromATemplate=0;
        try{
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + tableoperations + "");
            ContentResolver contentResolver = context.getContentResolver();
            // TODO: 16.05.2023 Сама Вставка
            Uri urlРезультатNewOrderTranport=  contentResolver.insert(uri,  contentValuesInsertingaNewEmployeeFromATemplate);

            String ответОперцииВставки=    Optional.ofNullable(urlРезультатNewOrderTranport)
                    .map(Emmeter->Emmeter.toString().replace("content://","")).get();

            insertingaNewEmployeeFromATemplate= Integer.parseInt(ответОперцииВставки);

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " insertingaNewEmployeeFromATemplate " +insertingaNewEmployeeFromATemplate);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  insertingaNewEmployeeFromATemplate;
    }



    // TODO: 25.11.2024 end class 
}
