package com.dsy.dsu.BusinessLogicAll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;


import java.util.concurrent.ExecutionException;

public class Class_Begin_Update_End_Insert_Data_Project {

    Context context;
    //todo

    PUBLIC_CONTENT public_contentcГдеНаходитьсяМенеджерПотоков=null;

    private SQLiteDatabase sqLiteDatabase ;

    public Class_Begin_Update_End_Insert_Data_Project(Context context) {

        this.context = context;
        // TODO: 13.10.2021
        public_contentcГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(this.context);
        //

        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();

    }

    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    /////TODO МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
    public Object МетодСначалоПытаемсяОбновлитьЕслиНеВышлоВставляемДанныхПроекта(String ОбработываемаяТаблица,
                                                                                 ContentValues КонтейнерДляВставкиИлиОбвноления,
                                                                                 Integer ЗначениеДляОбновления,
                                                                                 String ПолеЧерезКотороеНужноОбновлять) {//"SuccessLogin"/////МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
        ///
        Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций = null;
        Object РезультатАунтификации = 0;
        try {
            String ДатаДЛяОчисткиИВстсвкиИмениИПароль=
                    new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций =
                    new Class_GRUD_SQL_Operations(context);
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ОбработываемаяТаблица );////"SuccessLogin"
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением", ПолеЧерезКотороеНужноОбновлять);//"id"
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("ЗначениеФлагОбновления", ЗначениеДляОбновления);
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("ЗнакФлагОбновления","="); //или =   или <   >
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиИлиОбвноления);

                ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
            РезультатАунтификации =
                        (Long) concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                        new InsertData(context)
                        .insertdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.concurrentHashMapНабор,
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        public_contentcГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);

                Log.d(this.getClass().getName(), "РезультатАунтификации "
                        + РезультатАунтификации);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатАунтификации;
        ////// конец записываем полученный имя и пароль во временную таблицу ДЛЯ ТОГО ЧТОБЫ ЕСЛИ НУЖНО ЧЕРЕЗ  7 ДНЕЙ ПОТРЕБУЕТЬСЯ
    }
}
