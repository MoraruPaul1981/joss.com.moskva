package com.dsy.dsu.Hilt.JbossAdrress;

import android.annotation.SuppressLint;
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
@QualifierJbossServer2
@SuppressLint("SuspiciousIndentation")
public class HiltJboss {



    @Provides
    @Singleton
    @QualifierJbossServer3
    public StringBuilder getHiltJbossDebug(@ApplicationContext Context context) {
        String АдресСеврераJbossDebug = new String();
        try {

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " АдресСеврераJbossDebug " + АдресСеврераJbossDebug);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return new StringBuilder(АдресСеврераJbossDebug);

    }




    @Provides
    @Singleton
    @QualifierJbossServer4
    public StringBuilder getHiltJbossReliz(@ApplicationContext Context context) {
        String АдресСеврера1сДляgetReliz = new String();
        try {

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " АдресСеврера1сДляgetReliz " + АдресСеврера1сДляgetReliz);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return new StringBuilder(АдресСеврера1сДляgetReliz);
    }


}


