
package com.dsy.dsu.Hilt.Mutable;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@QualifierMutableLiveData
@Module
@InstallIn(SingletonComponent.class)
public class ModuleMutableLiveData {

    @Provides
    public MutableLiveData<Intent> getHiltMutableLiveDataPay(@ApplicationContext Context context) {
        MutableLiveData<Intent> jsonNodeMutableLiveDataPayCommintg=null;
        try {
             jsonNodeMutableLiveDataPayCommintg = new MutableLiveData<>();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " jsonNodeMutableLiveDataPayCommintg " + jsonNodeMutableLiveDataPayCommintg);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return jsonNodeMutableLiveDataPayCommintg;


    }
}
