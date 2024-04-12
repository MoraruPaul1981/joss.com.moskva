package com.dsy.dsu.Settings.Model.BL;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class GetSettingTableSaves implements  INSetttingTabels {


    @Override
    public Long getWritingPasswordSetingTable(@NonNull Context context, @NonNull Integer PublicID) {
            Long ResultNewPuvlicIdFronSettingTable = 0l;
            try {

                ContentValues АдаптерВставкиПолученогоПубличногоID = new ContentValues();

                // TODO: 12.04.2024
                PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                String version = pInfo.versionName;//Version Name
                Integer verCode = pInfo.versionCode;//Version Code

                // TODO: 29.12.2021
                АдаптерВставкиПолученогоПубличногоID.put("user_update", PublicID);
                АдаптерВставкиПолученогоПубличногоID.put("uuid", PublicID);
                АдаптерВставкиПолученогоПубличногоID.put("version_dsu1",  Integer.parseInt(verCode.toString()));

                String NewUUIDForSettingYable= new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных() ;
                АдаптерВставкиПолученогоПубличногоID.put("date_update", NewUUIDForSettingYable);

                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long NewLongVrsion = new SubClassUpVersionDATA().upVersionCurentTable("settings_tabels",context);
                АдаптерВставкиПолученогоПубличногоID.put("current_table", NewLongVrsion);


                //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
                ResultNewPuvlicIdFronSettingTable =
                      new Class_MODEL_synchronized(context).
                ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера("settings_tabels",
                                АдаптерВставкиПолученогоПубличногоID);

                // TODO: 02.05.2021
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +  this.getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " ЗаписьВSettingTabel " +ResultNewPuvlicIdFronSettingTable);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ResultNewPuvlicIdFronSettingTable;
        }



    @Override
    public Long getWritingOneSingalSetingTable(@NonNull Context context,
                                               @NonNull String НовыйIdОТСервтераOneSignal,
                                               @NonNull Integer PublicID) {
        Long ResultNewPuvlicIdFronSettingTable = 0l;
        try {

            ContentValues АдаптерВставкиПолученогоПубличногоID = new ContentValues();

            // TODO: 12.04.2024
            PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
            Integer verCode = pInfo.versionCode;//Version Code

            // TODO: 29.12.2021
            АдаптерВставкиПолученогоПубличногоID.put("user_update", PublicID);
            АдаптерВставкиПолученогоПубличногоID.put("uuid", PublicID);
            АдаптерВставкиПолученогоПубличногоID.put("version_dsu1",  Integer.parseInt(verCode.toString()));

            String NewUUIDForSettingYable= new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных() ;
            АдаптерВставкиПолученогоПубличногоID.put("date_update", NewUUIDForSettingYable);

            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long NewLongVrsion = new SubClassUpVersionDATA().upVersionCurentTable("settings_tabels",context);
            АдаптерВставкиПолученогоПубличногоID.put("current_table", NewLongVrsion);

            // TODO: 12.04.2024 new ONeSignal NEw KEY
            АдаптерВставкиПолученогоПубличногоID.put("onesignal", НовыйIdОТСервтераOneSignal);




            //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
            ResultNewPuvlicIdFronSettingTable =
                    new Class_MODEL_synchronized(context).
                            ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера("settings_tabels",
                                    АдаптерВставкиПолученогоПубличногоID);

            // TODO: 02.05.2021
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЗаписьВSettingTabel " +ResultNewPuvlicIdFronSettingTable+
                     " НовыйIdОТСервтераOneSignal " +НовыйIdОТСервтераOneSignal);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ResultNewPuvlicIdFronSettingTable;
    }

}
