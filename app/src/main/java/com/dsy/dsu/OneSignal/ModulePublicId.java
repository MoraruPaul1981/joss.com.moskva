package com.dsy.dsu.OneSignal;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.PublicId.QualifierPublicId;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@QualifierPublicId
@Module
@InstallIn(SingletonComponent.class)
public class ModulePublicId {
    @SuppressLint("SuspiciousIndentation")
    @Provides
    @Singleton
    public Integer getHiltPublicId(@ApplicationContext Context context) {
        Integer ПубличныйID = 0;
        try {
            ПубличныйID =
                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
            // TODO: 29.01.2024
   ///// ПубличныйID=96;
         ////  ПубличныйID=8;
          /// ПубличныйID=96;
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ПубличныйID " + ПубличныйID);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПубличныйID;


    }
}
