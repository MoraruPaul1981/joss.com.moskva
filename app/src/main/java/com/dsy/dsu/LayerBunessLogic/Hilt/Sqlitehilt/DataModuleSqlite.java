
package com.dsy.dsu.LayerBunessLogic.Hilt.Sqlitehilt;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

 
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerDatabase.SQLTE.*;


import org.jetbrains.annotations.NotNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class DataModuleSqlite {





    @NotNull
    @Singleton
    @Provides
    public SQLiteDatabase getSqldatabase (@ApplicationContext Context context) {
        SQLiteDatabase getSQLites=null;
        try{
        getSQLites=       new GetSQLiteDatabase(context).getССылкаНаСозданнуюБазу();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " SQLiteDatabase " +getSQLites);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return getSQLites;
    }


}
