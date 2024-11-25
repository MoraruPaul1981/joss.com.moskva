package com.dsy.dsu.LayerDatabase.binesslogiclayer.updates;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class UpdatingDataSimple {

    Context context;

    public  @Inject UpdatingDataSimple(@ApplicationContext Context hiltcontext) {
        this.context = hiltcontext;

    }



    public Integer getupdatingData(@NotNull  String tablenameUpdate,
                                            @NotNull  ContentValues contentVersionUp)
            throws ExecutionException, InterruptedException {
        Integer getupdatingDataWithWhere = 0;
        try {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/"+tablenameUpdate+"");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderError = context.getContentResolver();
            // TODO: 12.04.2023 UPDATER model_ssl
            getupdatingDataWithWhere=  contentProviderError.update(uri, contentVersionUp,null,null);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  tablenameForVersionUp " +tablenameUpdate+
                    " getupdatingDataWithWhere " +getupdatingDataWithWhere);

            // TODO: 21.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        // TODO: 21.03.2022
        return getupdatingDataWithWhere;
    }



}
