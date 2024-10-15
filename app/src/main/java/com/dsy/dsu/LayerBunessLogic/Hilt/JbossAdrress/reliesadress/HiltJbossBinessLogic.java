package com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.reliesadress;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.intarfaces.HiltJbossBinessLogicIntarface;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public class HiltJbossBinessLogic implements HiltJbossBinessLogicIntarface {


    /**
     *
     */
    @Override
    public LinkedHashMap<Integer,String>   selectenableforSslrequests(@NotNull SharedPreferences preferencesJboss, @NotNull Context context) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
        try{
            SharedPreferences.Editor editor = preferencesJboss.edit();
            getJbossPort.putIfAbsent(8888,"base.dsu1.ru");// TODO: 10.11.2022 Debug
            editor.putString("enablesll","http");




            // TODO: 18.03.2023 московский сервер ЧЕРЕЗ DNS // TODO: 10.11.2022 РЕЛИЗ  Москвовский
           /* getJbossPort.putIfAbsent(8888,"base.dsu1.ru");
            getJbossPort.putIfAbsent(8889,"base.dsu1.ru");
            getJbossPort.putIfAbsent(8890,"base.dsu1.ru");*/



/*
       // TODO: 18.03.2023 московский сервер
     getJbossPort.putIfAbsent(8888,"80.70.108.165");
            getJbossPort.putIfAbsent(8890,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
            getJbossPort.putIfAbsent(8889,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
*/


            editor.apply();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getJbossPort;
    }
}
