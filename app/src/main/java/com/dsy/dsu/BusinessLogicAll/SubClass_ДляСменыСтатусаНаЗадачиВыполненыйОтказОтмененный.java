package com.dsy.dsu.BusinessLogicAll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.Errors.Class_Generation_Errors;


import java.util.Date;

public class SubClass_ДляСменыСтатусаНаЗадачиВыполненыйОтказОтмененный {
    // TODO: 07.02.2022
    private SQLiteDatabase sqLiteDatabase ;
    public Boolean МетодСменыСтатусаНаОзкомленныйЗадениеСамимПользователем(
            @NonNull Context context,
   @NonNull Long UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления,
   @NonNull Integer ПередаемСтатусзадачи
, String ПримечанияОтКлинетаВыполнилИлиНетЗадачу) {
        // TODO: 07.02.2022
        Boolean РезультатСменыСтатусаНАОзнакомленый = false;
        try {

            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();


            Log.d(context.getClass().getName(), "ПримечанияОтКлинетаВыполнилИлиНетЗадачу "
                    + ПримечанияОтКлинетаВыполнилИлиНетЗадачу);


            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника = 0l;

            Class_GRUD_SQL_Operations class_grud_sql_operationsПовышаемВерсиюДляЛокальногоОбволенияТабеля =
                    new Class_GRUD_SQL_Operations(context);


            ContentValues contentValuesДляОбновленияСтатусаОзнакомлненый = new ContentValues();
            // TODO: 07.02.2022
            String НазваниеТаблицыобработки = "data_notification";////notifications

// TODO: 07.02.2022  увеличиваем верисю данных
            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияДАныхЧата =
                    new SubClassUpVersionDATA().МетодПовышаемВерсииMODIFITATION_Client(    НазваниеТаблицыобработки,context);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

            //TODO  конец курант ча
            contentValuesДляОбновленияСтатусаОзнакомлненый.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
            Log.d(this.getClass().getName(), "  РезультатУвеличинаяВерсияДАныхЧата " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
            //TODO заполение КОНТЕНЕР для локального обновления--дАТА оПЕРАЦИИ
            ////TODO ДАТА
            String СгенерированованныйДатаДляВставки = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();


            contentValuesДляОбновленияСтатусаОзнакомлненый.put("date_update", СгенерированованныйДатаДляВставки);


// TODO: 07.02.2022  само заполение смены статуса

            contentValuesДляОбновленияСтатусаОзнакомлненый.put("status_write", ПередаемСтатусзадачи);


            // TODO: 07.02.2022  само заполение примечания от КЛИЕНТ

            contentValuesДляОбновленияСтатусаОзнакомлненый.put("callsback_note_task", ПримечанияОтКлинетаВыполнилИлиНетЗадачу);


            ///TODO ТОЛЬКО ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ НА ТАБЕЛЕ В АКТИВИТИ
            Long РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ = new Class_MODEL_synchronized(context).
                    МетодЛокальноеОбновлениеВТабеле(contentValuesДляОбновленияСтатусаОзнакомлненый,
                            String.valueOf(UUID_ПоКоторомуМыИИщменимСтатусОзнакомлнныйВТаблицыУведомления),
                            context, НазваниеТаблицыобработки);
            Log.d(this.getClass().getName(), "  РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ " + РезультатЛокальногоОбновления_ОбновлениеСтатусОЗНАКОМЛЕННЫЙ);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            Log.e(context.getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время " + new Date());

        }
        return РезультатСменыСтатусаНАОзнакомленый;
    }
}
