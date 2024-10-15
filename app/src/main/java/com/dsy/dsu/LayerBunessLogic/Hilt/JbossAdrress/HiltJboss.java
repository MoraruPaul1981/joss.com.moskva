package com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.intarfaces.HiltJbossBinessLogicIntarface;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.qualifiers.QualifierJbossServer3;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.debugadress.HiltJbossBinessLogicDebug;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.debugadress.HiltJbossBinessLogicSSlDebug;
import com.dsy.dsu.LayerApp.SettingsApp.Model.bl_SettingsActivity.SLLBenessLogicMode;

import java.util.LinkedHashMap;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
@SuppressLint("SuspiciousIndentation")
public class HiltJboss {

    private SharedPreferences preferencesJboss;

    @Provides

    @QualifierJbossServer3
    public  LinkedHashMap<Integer,String> getHiltPortJboss(@ApplicationContext Context context) {
        LinkedHashMap<Integer,String> getJbossPort= new LinkedHashMap();
        try {
            HiltJbossBinessLogicIntarface hiltJbossBinessLogicIntarface;


            preferencesJboss = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);


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


             // TODO: 18.03.2023 ДЕБАГ  сервер
           // getJbossPort.putIfAbsent(8080,"192.168.3.4");// TODO: 10.11.2022 Debug


         String   getMode_ssl=new SLLBenessLogicMode(context).getModeSLL();



         //TODO начало настроек



            // TODO: 18.03.2023 ДЕБАГ  сервер
            if(getMode_ssl.equalsIgnoreCase("https")){
                // TODO: 06.10.2024 SSL enable
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogicSSlDebug();
            }else {
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogicDebug();
            }


            // TODO: 18.03.2024 РЕЛИЗ  сервер
  /*          if(getMode_ssl.equalsIgnoreCase("https")){
                // TODO: 06.10.2024 SSL enable
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogicSSl();
            }else {
                hiltJbossBinessLogicIntarface=new HiltJbossBinessLogic();
         }
*/
            // TODO: 06.10.2024 ответ  сам адрес с чем подкбчаться
            getJbossPort=   hiltJbossBinessLogicIntarface.selectenableforSslrequests(preferencesJboss,context);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getJbossPort " + getJbossPort+ "getMode_ssl " +getMode_ssl);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getJbossPort;

    }






}


