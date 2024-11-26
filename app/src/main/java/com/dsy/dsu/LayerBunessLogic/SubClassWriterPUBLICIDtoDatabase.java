package com.dsy.dsu.LayerBunessLogic;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.LayerBunessLogic.DATE.Class_Generation_Data;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.interfaces.GetHiltAllDateBaseOpersions;

import java.util.Date;

import dagger.hilt.EntryPoints;

public class SubClassWriterPUBLICIDtoDatabase {

    Context context;


    // TODO: 25.11.2024
    GetHiltAllDateBaseOpersions getHiltAllDateBaseOpersions;

    public SubClassWriterPUBLICIDtoDatabase() {
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    public Integer aftersuccessfulsynchronizationWritedownthepublicidSuccessLogin(@NonNull  Context context,
                                                                               @NonNull  Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу,
                                                                               @NonNull  String ПубличноеИмяПользовательДлСервлета,
                                                                               @NonNull  String ПубличноеПарольДлСервлета) {


        Integer результатЗаписиНовогоПароляПользователявБазцуsuccesslogin = 0;
        try{

            // TODO: 26.11.2024
            getHiltAllDateBaseOpersions = EntryPoints.get(context, GetHiltAllDateBaseOpersions.class);

            ContentValues contentValuesNewPublicWitnSussecLogin=new ContentValues();
            contentValuesNewPublicWitnSussecLogin.put("publicid", ПолученинныйПубличныйIDДлчЗаписиВБАзу);
            contentValuesNewPublicWitnSussecLogin.put("success_users", ПубличноеИмяПользовательДлСервлета);
       ///
            contentValuesNewPublicWitnSussecLogin.put("success_login",ПубличноеПарольДлСервлета);
       Log.d(this.getClass().getName(), " ПубличноеИмяПользовательДлСервлета "
               + ПолученинныйПубличныйIDДлчЗаписиВБАзу +
               " ПубличноеПарольДлСервлета" + ПолученинныйПубличныйIDДлчЗаписиВБАзу);
       ////TODO ДАТ
       String ДатаДЛяОчисткиИВстсвкиИмениИПароль=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            contentValuesNewPublicWitnSussecLogin.put("date_update", ДатаДЛяОчисткиИВстсвкиИмениИПароль);

            // TODO: 08.10.2024 Update or Insert  In table SuccessLogin PUBLIC ID
            результатЗаписиНовогоПароляПользователявБазцуsuccesslogin =getHiltAllDateBaseOpersions.insertNewEmployeeFromATemplate()
                    .insertingaNewEmployeeFromATemplate("successlogin",
                            contentValuesNewPublicWitnSussecLogin);

            // TODO: 25.11.2024
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ВставкиСотрудниковИзШаблона  "
                    +результатЗаписиНовогоПароляПользователявБазцуsuccesslogin);


            // TODO: 08.10.2024  
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return результатЗаписиНовогоПароляПользователявБазцуsuccesslogin;
    }
}
