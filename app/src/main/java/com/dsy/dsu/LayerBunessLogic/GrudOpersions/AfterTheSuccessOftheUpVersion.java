package com.dsy.dsu.LayerBunessLogic.GrudOpersions;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

public class AfterTheSuccessOftheUpVersion {

    Context context;

    public AfterTheSuccessOftheUpVersion(Context context) {
        this.context = context;
    }


    public Integer afterTheSuccessOftheUpVersion(@NotNull  String tablenameForVersionUp, @NotNull  ContentValues contentVersionUp,@NotNull  String whereUUID)
            throws ExecutionException, InterruptedException {
        Integer afterTheSuccessOftheUpVersion = 0;
        try {
          //  GetVersionUp getVersionUp=new GetVersionUp(context);

            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/"+tablenameForVersionUp+"");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderError = context.getContentResolver();
            // TODO: 12.04.2023 UPDATER model_ssl
            afterTheSuccessOftheUpVersion=  contentProviderError.update(uri, contentVersionUp,null,null);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  tablenameForVersionUp " +tablenameForVersionUp);
            ///TODO увеличение версии UP
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "  afterTheSuccessOftheUpVersion " +afterTheSuccessOftheUpVersion+ " tablenameForVersionUp " +tablenameForVersionUp);

            // TODO: 21.03.2022
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        // TODO: 21.03.2022
        return afterTheSuccessOftheUpVersion;
    }



}
