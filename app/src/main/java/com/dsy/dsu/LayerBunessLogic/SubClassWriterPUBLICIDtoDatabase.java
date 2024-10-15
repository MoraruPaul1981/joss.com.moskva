package com.dsy.dsu.LayerBunessLogic;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.LayerBunessLogic.DATE.Class_Generation_Data;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import java.util.Date;

public class SubClassWriterPUBLICIDtoDatabase {

    Context context;

    public SubClassWriterPUBLICIDtoDatabase() {
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer aftersuccessfulsynchronizationWritedownthepublicidSuccessLogin(@NonNull  Context context,
                                                                               @NonNull  Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу,
                                                                               @NonNull  String ПубличноеИмяПользовательДлСервлета,
                                                                               @NonNull  String ПубличноеПарольДлСервлета) {


        Integer результатЗаписиНовогоПароляПользователявБазцуsuccesslogin = 0;
        try{
       ContentValues NewPublicWitnSussecLogin=new ContentValues();
       NewPublicWitnSussecLogin.put("publicid", ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       NewPublicWitnSussecLogin.put("success_users", ПубличноеИмяПользовательДлСервлета);
       ///
       NewPublicWitnSussecLogin.put("success_login",ПубличноеПарольДлСервлета);
       Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "
               + ПолученинныйПубличныйIDДлчЗаписиВБАзу +
               " ПубличноеПарольДлСервлета" + ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       ////TODO ДАТ
       String ДатаДЛяОчисткиИВстсвкиИмениИПароль=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
       NewPublicWitnSussecLogin.put("date_update", ДатаДЛяОчисткиИВстсвкиИмениИПароль);

            // TODO: 08.10.2024 Update or Insert  In table SuccessLogin PUBLIC ID

            //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
            результатЗаписиНовогоПароляПользователявБазцуsuccesslogin =
                    new Class_MODEL_synchronized(context).
                            wewillsetupanewPublicidaftersuccessfulsynchronizationSuccessLogin("successlogin",
                                    NewPublicWitnSussecLogin,ПолученинныйПубличныйIDДлчЗаписиВБАзу);




            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    "результатЗаписиНовогоПароляПользователявБазцуsuccesslogin " + результатЗаписиНовогоПароляПользователявБазцуsuccesslogin+
                    " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);

       // TODO: 29.12.2021  ВТОРАЯ ЗАПИСЬ В ДРУГУЮ ТАБЛИЦУ ТАБЛИЦА НАСТРОЕК ВТОРАЯ ЧАСТЬ ОПЕРАЦИИ
       NewPublicWitnSussecLogin.clear();
            // TODO: 08.10.2024  
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатЗаписиНовогоПароляПользователявБазцуsuccesslogin;
    }
}
