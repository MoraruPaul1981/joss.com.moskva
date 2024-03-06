package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;


public class Class_Search_Changes_Data {
    Context context;
    ///////TODO
    private SQLiteDatabase sqLiteDatabase ;
    ////
    public Class_Search_Changes_Data(Context context) {
        this.context =context;
        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ


    public boolean МетодВычислемБылиИзменениВДанныхВДанныхПоДатам(String КакиеТаблицаОбработкиЧерезДату) {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        boolean РезультатБылиБЫИзмегнениевБазе = false;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель = new Class_GRUD_SQL_Operations(context);
        try {
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНабор.put("СтолбцыОбработки", "localversionandroid_version,versionserveraandroid_version");
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНабор.put("ФорматПосика", "name=?  ");
        ///"_id > ?   AND _id< ?"
        //////
        class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНабор.put("УсловиеПоиска1",КакиеТаблицаОбработкиЧерезДату.trim());
        ///
              /*      class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

        ////TODO другие поля

        ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            /*////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНабор.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");*/
        ////
        // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(context);
            SQLiteCursor    Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = (SQLiteCursor) class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.
                new GetData(context).getdata(class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабель.concurrentHashMapНабор,
                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
        Log.d(this.getClass().getName(), "GetData " + Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель);
        if (Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getCount() > 0) {
            Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.moveToFirst();
            Long ЛокальнаяДатаИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getLong(0);
            Long СервернаяДатаИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getLong(1);
            Log.d(this.getClass().getName(), "СервернаяДатаИзменений " + СервернаяДатаИзменений + "  ЛокальнаяДатаИзменений " + ЛокальнаяДатаИзменений);
            Log.d(this.getClass().getName(), "СервернаяДатаИзменений " + СервернаяДатаИзменений + "  ЛокальнаяДатаИзменений " + ЛокальнаяДатаИзменений);
            if (ЛокальнаяДатаИзменений > СервернаяДатаИзменений) {
                РезультатБылиБЫИзмегнениевБазе=true;
                Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " +РезультатБылиБЫИзмегнениевБазе);
            }else {
                РезультатБылиБЫИзмегнениевБазе=false;
                Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " + РезультатБылиБЫИзмегнениевБазе);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  РезультатБылиБЫИзмегнениевБазе;
    }








    // TODO: 26.09.2021  проверка если изменения через верисс

    boolean МетодВычислемБылиИзменениВДанныхВДанныхЧерезВерсииДанных(String КакиеТаблицаОбработкиЧерезДату) {
        // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
        boolean РезультатБылиБЫИзмегнениевБазе=false;

        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных = new Class_GRUD_SQL_Operations(context);

        try{


            ///
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "MODIFITATION_Client");
            ///////
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.
                    concurrentHashMapНабор.put("СтолбцыОбработки", "localversionandroid_version,versionserveraandroid_version");
            //
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНабор.put("ФорматПосика", "name=?  ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНабор.put("УсловиеПоиска1",КакиеТаблицаОбработкиЧерезДату.trim());
            ///
              /*      class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНабор.put("ПоляГрупировки",null);
            /*////
            //class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНабор.put("УсловиеСортировки","date_update");
            ////
            /// class_grud_sql_operations. concurrentHashMapНабор.put("УсловиеЛимита","1");*/
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            SQLiteCursor Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = null;



            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);

            ///

            Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель = (SQLiteCursor) class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.
                    new GetData(context).
                    getdata(class_grud_sql_operationsВычислемБылиЛиИзмененияВТаблицеТабелВерсияДанных.concurrentHashMapНабор,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
            //////////////


            Log.d(this.getClass().getName(), "GetData " + Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель);




/*

            // TODO: 09.09.2021  ___old

           Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель=
                    new Class_MODEL_synchronized(Контекст).КурсорУниверсальныйБазыДанных("SELECT localversionandroid,versionserveraandroid FROM MODIFITATION_Client WHERE name = 'tabels' ");


*/


///TODO РЕЗУЛЬТАТ
            if (Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getCount() > 0) {
                ///
                Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.moveToFirst();

                Integer ЛокальнаяВерсияИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getInt(0);
                //

                Integer СервернаяВерсияИзменений = Курсор_ВычислемБылиЛиИзмененияВТаблицеТабель.getInt(1);
                /////////////
                Log.d(this.getClass().getName(), "ЛокальнаяВерсияИзменений " + ЛокальнаяВерсияИзменений + "  СервернаяВерсияИзменений " + СервернаяВерсияИзменений);


                ////

                if (ЛокальнаяВерсияИзменений>СервернаяВерсияИзменений) {

                    РезультатБылиБЫИзмегнениевБазе=true;

                    /////////////
                    Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " +РезультатБылиБЫИзмегнениевБазе);
                }else {

                    РезультатБылиБЫИзмегнениевБазе=false;

                    /////////////
                    Log.d(this.getClass().getName(), "РезультатБылиБЫИзмегнениевБазе " + РезультатБылиБЫИзмегнениевБазе);


                }
            }


            //

            //////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            /////////
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

         //   Log.e(context.getClass().getName(), " Стоп СЛУЖБА MyWork_Async_Public из FaceApp в MyWork_Async_Public Exception  ошибка в классе MyWork_Async_Public" + e.toString());


        }
        return  РезультатБылиБЫИзмегнениевБазе;
    }

}
