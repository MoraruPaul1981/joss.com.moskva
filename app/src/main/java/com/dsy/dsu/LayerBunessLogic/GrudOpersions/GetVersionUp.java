package com.dsy.dsu.LayerBunessLogic.GrudOpersions;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

public class GetVersionUp {


    Context context;

    public GetVersionUp(Context context) {
        this.context = context;
    }


    public    Integer writeVersionUp(@NotNull ContentValues contentVersionUp, @NotNull String tableforUpversion){
        // TODO: 14.10.2024
        Integer writeVersionUp=0;
        try{
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/"+tableforUpversion+"");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProviderError = context.getContentResolver();
            // TODO: 12.04.2023 UPDATER model_ssl
            writeVersionUp=  contentProviderError.update(uri, contentVersionUp,null,null);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  getwiteNeError " +writeVersionUp);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return writeVersionUp;
    }


}
