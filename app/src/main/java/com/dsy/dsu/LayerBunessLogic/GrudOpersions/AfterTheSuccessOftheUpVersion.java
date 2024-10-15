package com.dsy.dsu.LayerBunessLogic.GrudOpersions;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import java.util.concurrent.ExecutionException;

public class AfterTheSuccessOftheUpVersion {

    Context context;

    public AfterTheSuccessOftheUpVersion(Context context) {
        this.context = context;
    }


    private Integer afterTheSuccessOftheUpVersion(String tablenameForVersionUp, ContentValues contentVersionUp) throws ExecutionException, InterruptedException {
        Integer afterTheSuccessOftheUpVersion = 0;
        try {
            GetVersionUp getVersionUp=new GetVersionUp(context);

            afterTheSuccessOftheUpVersion =
                    getVersionUp.writeVersionUp(contentVersionUp,tablenameForVersionUp);
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
