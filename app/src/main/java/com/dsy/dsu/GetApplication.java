package com.dsy.dsu;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;



import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class GetApplication extends Application {

    @Inject
  public   SQLiteDatabase getSqldatabase ;


    @Override
    public void onCreate() {
        super.onCreate();
        try{

          ///  getBinderAsync = EntryPoints.get(getApplicationContext(), HiltInterfaceBinderAsync.class).metodBinderAsync();
            // TODO: 02.09.2023  CREATE get SQLITE
          //  new GetSqlite().методGetSqlite(getApplicationContext());

                        // TODO: 29.08.2023  CREATE ROOM
      /// new GetApplication(getApplicationContext()).метоInizROOM();

// Reference to the application graph that is used across the whole app

     // Cursor c= sqlite.rawQuery("select * from fio",null);
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " getSqldatabase " +getSqldatabase);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



}




// TODO: 29.08.2023  КЛАСС   БИЗНЕС ЛОГИКУ





