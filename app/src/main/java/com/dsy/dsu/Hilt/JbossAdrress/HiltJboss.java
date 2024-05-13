package com.dsy.dsu.Hilt.JbossAdrress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.LinkedHashMap;

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
    public  LinkedHashMap<Integer,String> getHiltJbossDebug(@ApplicationContext Context context) {
        LinkedHashMap<Integer,String> МассивПортовСервераDebugandRelize= new LinkedHashMap();
        try {


            // TODO: 18.03.2023 московский сервер ЧЕРЕЗ DNS
 /*       МассивПортовСервера.putIfAbsent(8888,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8889,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8890,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский*/



    /*        // TODO: 18.03.2023 московский сервер
            МассивПортовСервераDebugandRelize.putIfAbsent(8888,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
            МассивПортовСервераDebugandRelize.putIfAbsent(8890,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
            МассивПортовСервераDebugandRelize.putIfAbsent(8889,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
*/


            // TODO: 18.03.2023 ДЕБАГ  сервер
            МассивПортовСервераDebugandRelize.putIfAbsent(8080,"192.168.3.4");// TODO: 10.11.2022 Debug


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивПортовСервераDebugandRelize " + МассивПортовСервераDebugandRelize);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return МассивПортовСервераDebugandRelize;

    }




    @Provides
    @Singleton
    @QualifierJbossServer4
    public LinkedHashMap<Integer,String> getHiltJbossReliz(@ApplicationContext Context context) {
        LinkedHashMap<Integer,String> МассивПортовСервераReliz= new LinkedHashMap();
        try {
    /*        // TODO: 18.03.2023 московский сервер ЧЕРЕЗ DNS
       МассивПортовСервера.putIfAbsent(8888,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8889,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8890,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский


        // TODO: 18.03.2023 московский сервер
            МассивПортовСервераReliz.putIfAbsent(8888,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
            МассивПортовСервераReliz.putIfAbsent(8890,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
            МассивПортовСервераReliz.putIfAbsent(8889,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
*/
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " МассивПортовСервераReliz " + МассивПортовСервераReliz);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return МассивПортовСервераReliz;
    }


}


