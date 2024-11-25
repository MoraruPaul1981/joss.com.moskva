package com.dsy.dsu.LayerBunessLogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

 
import com.dsy.dsu.LayerBunessLogic.DATE.Class_Generation_Data;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Hilt.Sqlitehilt.HiltInterfacesqlite;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.interfaces.GetHiltAllDateBaseOpersions;


import java.util.Date;

import dagger.hilt.EntryPoints;

public class SubClass_ДляСменыСтатусаНаЗадачиВыполненыйОтказОтмененный {
    // TODO: 07.02.2022
    private SQLiteDatabase sqLiteDatabase ;


    // TODO: 25.11.2024
    GetHiltAllDateBaseOpersions getHiltAllDateBaseOpersions;


    public Boolean МетодСменыСтатусаНаОзкомленныйЗадениеСамимПользователем(@NonNull Context context,
                                                                           @NonNull Long UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления,
                                                                           @NonNull Integer ПередаемСтатусзадачи,
                                                                           String ПримечанияОтКлинетаВыполнилИлиНетЗадачу) {

        // TODO: 07.02.2022
        Boolean РезультатСменыСтатусаНАОзнакомленый = false;
        try {

            sqLiteDatabase  = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
            // TODO: 25.11.2024
            getHiltAllDateBaseOpersions = EntryPoints.get(context, GetHiltAllDateBaseOpersions.class);

            ContentValues contentValuesДляОбновленияСтатусаОзнакомлненый = new ContentValues();
            // TODO: 07.02.2022
            String НазваниеТаблицыобработки = "data_notification";////notifications


            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника = new SubClassUpVersionDATA(context).upVersionCurentTable(    НазваниеТаблицыобработки,context);


            //TODO  конец курант ча
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);


            //TODO заполение КОНТЕНЕР для локального обновления--дАТА оПЕРАЦИИ

            ////TODO ДАТА
            String СгенерированованныйДатаДляВставки = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();


            contentValuesДляОбновленияСтатусаОзнакомлненый.put("date_update", СгенерированованныйДатаДляВставки);


// TODO: 07.02.2022  само заполение смены статуса
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("status_write", ПередаемСтатусзадачи);


            // TODO: 07.02.2022  само заполение примечания от КЛИЕНТ
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("callsback_note_task", ПримечанияОтКлинетаВыполнилИлиНетЗадачу);



            ///TODO ТОЛЬКО ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ НА ТАБЕЛЕ В АКТИВИТИ
            Integer РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ = getHiltAllDateBaseOpersions.updatingTabelWithWhere()
            .getupateTabelDataWithWhere(НазваниеТаблицыобработки,contentValuesДляОбновленияСтатусаОзнакомлненый,UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления);


            // TODO: 25.11.2024
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ "
                    +РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

        }
        return РезультатСменыСтатусаНАОзнакомленый;
    }
}
