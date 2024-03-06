
package com.dsy.dsu.Hilt.Adress1cPrices;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@QualifierCommintgPrices
@Module
@InstallIn(SingletonComponent.class)
public class ModuleCommintgPrices {
    @Singleton
    @Provides
    public StringBuffer getHiltCommintgPrices(@ApplicationContext Context context) {
        StringBuffer АдресСеврера1сДляgetFilePrices=new StringBuffer();
        try {
            АдресСеврера1сДляgetFilePrices.append("http://uat.dsu1.ru:55080/dds/hs/jsonto1ccena/listofdocuments".trim());// TODO: 18.01.2024  RELEZ


          //  АдресСеврера1сДляgetFilePrices.append("http://192.168.3.10/dds_copy/hs/jsonto1ccena/listofdocuments".trim());// TODO: 18.01.2024  DEBUG

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " АдресСеврера1сДляgetFilePrices " + АдресСеврера1сДляgetFilePrices);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return АдресСеврера1сДляgetFilePrices;


    }
}
