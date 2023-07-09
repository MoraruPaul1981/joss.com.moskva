package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.CREATE_DATABASE;
import com.dsy.dsu.BuildConfig;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.AllDatabases.Error.CREATE_DATABASE_Error;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class Class_Generation_Errors {

    private Context context;
    private Class_GRUD_SQL_Operations classGrudSqlOperationsОшибки;
    private Integer ПубличноеIDПолученныйИзСервлетаДляUUID = 0;

    private CREATE_DATABASE_Error create_database_error;

    public Class_Generation_Errors(@NonNull Context context) {
        this.context = context;
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    // TODO: 05.07.2021

    //// второй метод с современный
    public void МетодЗаписиВЖурналНовойОшибки(@NonNull  String ТекстОшибки,
                                              @NonNull String КлассГнерацииОшибки,
                                              @NonNull String МетодаОшибки,
                                              @NonNull  Integer ЛинияОшибки) {

        Long PезультатВставкиНовойОшибки = 0l;
        try {
            if (context != null) {
             SQLiteDatabase create_database_error = new CREATE_DATABASE_Error(context).getССылкаНаСозданнуюБазу();
             SQLiteDatabase create_database = new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу();
                Long Версия = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    "errordsu1"
                        ,context,create_database);
                Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(context);
                classGrudSqlOperationsОшибки = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsОшибки.concurrentHashMapНабор.clear();
                classGrudSqlOperationsОшибки = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsОшибки.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Error", ТекстОшибки.toLowerCase());
                Log.d(this.getClass().getName(), " ТекстОшибки.toLowerCase()  " + ТекстОшибки.toLowerCase());
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Klass", КлассГнерацииОшибки.toUpperCase());
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Metod", МетодаОшибки.toUpperCase());
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("LineError", ЛинияОшибки);
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("IdUser", ПубличныйIDДляАсих);
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("current_table", Версия);
                final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("whose_error", ЛокальнаяВерсияПОСравнение);
                String СгенерированованныйДатаДляВставки = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Data_Operazii_E", СгенерированованныйДатаДляВставки);
                ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
                if (!ТекстОшибки.equalsIgnoreCase("java.net.SocketTimeoutException: timeout")
                        && !ТекстОшибки.equalsIgnoreCase("java.lang.NumberFormatException: null")
                        && !ТекстОшибки.matches("(.*)java.net.UnknownHostException: Unable to resolve host(.*)")
                        && !ТекстОшибки.matches("(.*)java.net.SocketTimeoutException: failed to connect to tabel.dsu1.ru/80.66.149.58 (port 8888)(.*)")) {
                    // TODO: 20.02.2022

                    // TODO: 20.12.2022  дополнительный механизм записи ошибкок
                    ArrayList<String> arrayListОшибкиДляЗаписивФайл=new ArrayList();
                    arrayListОшибкиДляЗаписивФайл.add(ТекстОшибки);
                    arrayListОшибкиДляЗаписивФайл.add(КлассГнерацииОшибки);
                    arrayListОшибкиДляЗаписивФайл.add(МетодаОшибки);
                    arrayListОшибкиДляЗаписивФайл.add(String.valueOf(ЛинияОшибки));
                    // TODO: 09.07.2023 запись ошибки в файл  .txt
                   new SubClassWriteErrorFile(context,arrayListОшибкиДляЗаписивФайл).МетодЗаписьДополенительеноОшибвкивФайл();

// TODO: 21.12.2022  главная запись ощибки в табелицу ErrorDSU1
                    PезультатВставкиНовойОшибки = (Long) classGrudSqlOperationsОшибки.
                            new InsertData(context).insertdata(classGrudSqlOperationsОшибки.concurrentHashMapНабор,
                            classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            new PUBLIC_CONTENT(context).МенеджерПотоков,
                            create_database_error);
                    Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " PезультатВставкиНовойОшибки " +PезультатВставкиНовойОшибки);

                    // TODO: 21.12.2022  главная запись ощибки в табелицу ErrorDSU1
                    PезультатВставкиНовойОшибки = (Long) classGrudSqlOperationsОшибки.
                            new InsertData(context).insertdata(classGrudSqlOperationsОшибки.concurrentHashMapНабор,
                            classGrudSqlOperationsОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            new PUBLIC_CONTENT(context).МенеджерПотоков,
                            create_database);
                    Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " PезультатВставкиНовойОшибки " +PезультатВставкиНовойОшибки);
                }
                Log.d(this.getClass().getName(), "PезультатВставкиНовойОшибки " + PезультатВставкиНовойОшибки);

                // TODO: 09.07.2023 clear
                classGrudSqlOperationsОшибки.concurrentHashMapНабор.clear();
                if(create_database.inTransaction()){
                    create_database.endTransaction();
                }
                if(create_database_error.inTransaction()){
                    create_database_error.endTransaction();
                }

            } else {
                System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
            }
            Log.d(this.getClass().getName(), "PезультатВставкиНовойОшибки " + PезультатВставкиНовойОшибки);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
            Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    // TODO: 20.12.2022  дополнительный клас Заппси ОШИБКИВ ФАЙЛ
    private class SubClassWriteErrorFile {
        // TODO: 14.12.2022 тест метод
        Context context;
        ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи;
        public SubClassWriteErrorFile(@NonNull Context context, @NonNull ArrayList linkedBlockingQueueВскеОшибкиДляЗаписи) {
            this.context = context;
            this.linkedBlockingQueueВскеОшибкиДляЗаписи=linkedBlockingQueueВскеОшибкиДляЗаписи;
        }
        void  МетодЗаписьДополенительеноОшибвкивФайл  (){
            String fileName = "ErrorsAllSousAuto.txt";
            try (            FileOutputStream outputStream=context. openFileOutput(fileName , Context.MODE_APPEND);
                             OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream, StandardCharsets.UTF_16);) {
                outputStreamWriter.write("\n");
                String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                outputStreamWriter.write(СгенерированованныйДатаВремениСейчаcДляУдаления);
            outputStreamWriter.write("\n");
            outputStreamWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString());
            outputStreamWriter.write("\n");
                outputStreamWriter.write("\n");
                outputStreamWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(1).toString());
                outputStreamWriter.write("\n");
                outputStreamWriter.write("\n");
                outputStreamWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(2).toString());
                outputStreamWriter.write("\n");
                outputStreamWriter.write("\n");
                String СтрокаОшибки= linkedBlockingQueueВскеОшибкиДляЗаписи.get(3);
                Integer СтрокаОшибкиФинал=Integer.parseInt(СтрокаОшибки);
                outputStreamWriter.write(СтрокаОшибкиФинал);
                outputStreamWriter.write("\n");

            outputStreamWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("  Ошибка в    дополнительном модуле записи ошиьки в файл SubClassWriteErrorFile ");
            Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

    }
}
