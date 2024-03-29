
package com.dsy.dsu.Hilt.OneSignalHils;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
public class DataModuleOneSignal {



    @Singleton
  @Provides
    @QualifierOneSignal
    public String KeyHiltOneSignal(@ApplicationContext Context context) {
        String KeyOneSignal = null;
        try{
           // KeyOneSignal="96cf1708-a138-48be-acaa-5819d1b2b3b5".trim();
           // KeyOneSignal="e9b94b0b-2cc6-44ac-9546-9418a39a48aa".trim();
            KeyOneSignal="2a1819db-60c8-4ca3-a752-1b6cd9cadfa1".trim();//4df090b1-f791-40fc-8231-766efb1fa6dd
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " KeyOneSignal " +KeyOneSignal);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return   KeyOneSignal;
    }


}
