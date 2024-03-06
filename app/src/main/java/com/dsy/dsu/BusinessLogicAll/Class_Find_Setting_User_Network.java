package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;


public class Class_Find_Setting_User_Network {

    Context context;
    /////


    private SQLiteDatabase sqLiteDatabase ;

    public Class_Find_Setting_User_Network(Context context) {

        this.context =context;

        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ


    public boolean МетодПроветяетКакуюУстановкуВыбралПользовательСети() {
        boolean РезультатРежимСети = false;
        String РежимСетиВыбранныйПользователем = new String();
        try {
     /////todo тут МЫ ПОЛУЧАЕМ В КАКОЙ МОМЕНТ ТИП ПОДКЛЮЧЕНИЯ НА ТЕЛЕФОНЕ МОБИЛЯ ИЛИ  WIFI  И В ЗАВИСИМОСТИ ЧТОБЫ ПОНЯТЬ ЧЕ ЗА ДЕЛА
     РежимСетиВыбранныйПользователем
             = new Class_Type_Connenction_Tel(context).МетодОпределяемКакойТипПодключениеWIFIилиMobile();
     Log.d(this.getClass().getName(), "   РезутьтатПроверкиТипПодключениякИнтернету " + РежимСетиВыбранныйПользователем);
     Class_GRUD_SQL_Operations class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер = new Class_GRUD_SQL_Operations(context);

     class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
     ///////
     class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНабор.put("СтолбцыОбработки", "mode_connection");
         // TODO: 12.10.2021  Ссылка Менеджер Потоков
            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(context);
     // TODO: 02.09.2021 exe sql
     SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью = (SQLiteCursor) class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.
             new GetData(context).getdata(class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.
                     concurrentHashMapНабор,
             Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
     Log.d(this.getClass().getName(), "GetData " + КурсорУзнаемСохраненыйРежимРаботыССетью);
     // TODO: 29.09.2021
     String УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью = new String();
     if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
         Log.d(this.getClass().getName(), "GetData " + КурсорУзнаемСохраненыйРежимРаботыССетью);
         КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
         УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
         Log.d(this.getClass().getName(), "КурсорУзнаемСохраненыйРежимРаботыССетью " + КурсорУзнаемСохраненыйРежимРаботыССетью +
                 "  УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью "
                 + УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью);
         Log.d(this.getClass().getName(), "РежимСетиВыбранныйПользователем "
                 + РежимСетиВыбранныйПользователем +
                 " ПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью " + УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью);
         if (УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью.
                 equalsIgnoreCase(РежимСетиВыбранныйПользователем) ||
                 УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью.trim().equalsIgnoreCase("Mobile") ) {
             Log.d(this.getClass().getName(), "РежимСетиВыбранныйПользователем "
                     + РежимСетиВыбранныйПользователем +
                     " УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью "
                     + УстановленныйПользоватлемПолучениыыйВыбраныйПарментУстановленныйПользователемРедимПодключенияССетью);
             // TODO: 29.09.2021  надо синхрониазциии
             РезультатРежимСети=true;
// TODO: 29.09.2021  проверяем равны ли выбранный режим с режимо выбранным пользователм
         }else{
// TODO: 29.09.2021  проверяем равны ли выбранный режим с режимо выбранным пользователм
             Log.d(this.getClass().getName(), " СИНХРОНИАЗЦИЯ ОТМЕНЕНА ПОТОМУ ЧТО РЕЖИСЫ РАБОТЫ  ССЕТЬЮ  УСТАНОВЛЕННЕЫЕ ПОЛЬЗОВАТЕЛЕМ И РЕЖИМ УСТАНОВЕОЫЙ НА ТЕЛФОНОНЕ ПО СОВПАДАЮТ " +
                     "утьтатПроверкиТипПодключениякИнтернетуWIFIИЛИMOBILEиВзависимостиЧтоВыбралВНастройкаПользовательПринимаемРешение "
                     + РежимСетиВыбранныйПользователем );
             // TODO: 29.09.2021 не надо синхрониазциии
             РезультатРежимСети=false;

         }
     } else {
         Log.d(this.getClass().getName(), "КурсорУзнаемСохраненыйРежимРаботыССетью " + КурсорУзнаемСохраненыйРежимРаботыССетью);
       РезультатРежимСети=true;
     }

 } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return РезультатРежимСети;
    }

}
