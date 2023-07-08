package com.dsy.dsu.Business_logic_Only_Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BuildConfig;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.AllDatabases.Error.CREATE_DATABASE_Error;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class Class_Generation_Errors {

    private Context context;
    private Class_GRUD_SQL_Operations classGrudSqlOperationsДляЗаписиНовойОшибки;
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
                Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(context);
                classGrudSqlOperationsДляЗаписиНовойОшибки = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsДляЗаписиНовойОшибки.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.clear();
                classGrudSqlOperationsДляЗаписиНовойОшибки = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsДляЗаписиНовойОшибки.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Error", ТекстОшибки.toLowerCase());
                Log.d(this.getClass().getName(), " ТекстОшибки.toLowerCase()  " + ТекстОшибки.toLowerCase());
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Klass", КлассГнерацииОшибки.toUpperCase());
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Metod", МетодаОшибки.toUpperCase());
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("LineError", ЛинияОшибки);
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("IdUser", ПубличныйIDДляАсих);
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("current_table", new Random().nextInt());
                final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("whose_error", ЛокальнаяВерсияПОСравнение);
                String СгенерированованныйДатаДляВставки = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("Data_Operazii_E", СгенерированованныйДатаДляВставки);
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
                   new SubClassWriteErrorFile(context,arrayListОшибкиДляЗаписивФайл).МетодЗаписьДополенительеноОшибвкивФайл();


// TODO: 21.12.2022  главная запись ощибки в табелицу ErrorDSU1
                    PезультатВставкиНовойОшибки = (Long) classGrudSqlOperationsДляЗаписиНовойОшибки.
                            new InsertData(context).insertdata(classGrudSqlOperationsДляЗаписиНовойОшибки.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            classGrudSqlOperationsДляЗаписиНовойОшибки.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            new PUBLIC_CONTENT(context).МенеджерПотоков,
                             new CREATE_DATABASE_Error(context).getССылкаНаСозданнуюБазу());
                }
                Log.d(this.getClass().getName(), "PезультатВставкиНовойОшибки " + PезультатВставкиНовойОшибки);
                if (PезультатВставкиНовойОшибки == null) {
                    PезультатВставкиНовойОшибки = 0l;
                }
                if (PезультатВставкиНовойОшибки > 0) {
                    Log.d(this.getClass().getName(), "результатВставки Ошибки ERROR " + PезультатВставкиНовойОшибки);
                }
                classGrudSqlOperationsДляЗаписиНовойОшибки.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.clear();
            } else {
                System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
            }
            
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
