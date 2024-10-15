package com.dsy.dsu.LayerBunessLogic.Errors;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.multidex.BuildConfig;


 

import com.dsy.dsu.LayerBunessLogic.Class_Generation_UUID;
import com.dsy.dsu.LayerBunessLogic.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.LayerBunessLogic.DATE.Class_Generation_Data;
import com.dsy.dsu.LayerBunessLogic.Hilt.Sqlitehilt.HiltInterfacesqlite;
import com.dsy.dsu.LayerBunessLogic.SubClassUpVersionDATA;
import com.dsy.dsu.LayerBunessLogic.CnangeServers.PUBLIC_CONTENT;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import dagger.hilt.EntryPoints;

public class Class_Generation_Errors {

    private Context context;

    private String fileName = "Sous-Avtodor-ERROR.txt";

    private   String patchFileName="SousAvtoFile";
    private SQLiteDatabase sqLiteDatabase ;
    public Class_Generation_Errors(@NonNull Context context) {

        this.context = context;
        sqLiteDatabase  = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
    }

    // TODO: 05.07.2021

    //// второй метод с современный
    public void МетодЗаписиВЖурналНовойОшибки(@NonNull String ТекстОшибки,
                                              @NonNull String КлассГнерацииОшибки,
                                              @NonNull String МетодаОшибки,
                                              @NonNull Integer ЛинияОшибки) {

        Long PезультатВставкиНовойОшибки = 0l;
        try {
            if (context != null) {
                Long ВерсияДанных = new SubClassUpVersionDATA(context).upVersionCurentTable("errordsu1"
                        , context );


                ContentValues contentValuesError=new ContentValues();
                GetWriteErrors  getWriteErrors=new GetWriteErrors(context);
                // TODO: 15.10.2024
                contentValuesError.put("Error", ТекстОшибки.toLowerCase());
                contentValuesError.put("Klass", КлассГнерацииОшибки.toUpperCase());
                contentValuesError.put("Metod", МетодаОшибки.toUpperCase());
                contentValuesError.put("LineError", ЛинияОшибки);



                Integer ПубличныйIDДляАсих = new Class_Generations_PUBLIC_CURRENT_ID(context).
                        getPublicIDAllApp(context);
                contentValuesError.put("user_update", ПубличныйIDДляАсих);


                Long UUID = (Long)
                        new Class_Generation_UUID(context).МетодГенерацииUUID();
                contentValuesError.put("UUID", UUID);
                contentValuesError.put("current_table", ВерсияДанных);


                final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
                Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());

                contentValuesError.put("whose_error", ЛокальнаяВерсияПОСравнение);
                String СгенерированованныйДатаДляВставки = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesError.put("date_update", СгенерированованныйДатаДляВставки);

                ///TODO Записываем ошибки только определного сорта
                if (!ТекстОшибки.trim().matches("(.*)UnknownHostException(.*)")
                        && !ТекстОшибки.trim().matches("(.*)SocketTimeoutException(.*)")
                        && !ТекстОшибки.trim().matches("(.*)ConnectException(.*)")) {

                    // TODO: 15.10.2024
                    Integer getwiteNeError=  getWriteErrors.writeErrors(contentValuesError);

                    // TODO: 20.12.2022  дополнительный механизм записи ошибкок
                    методЗаписиОшибкиВФайлErrorDSU1txt(ТекстОшибки, КлассГнерацииОшибки, МетодаОшибки, ЛинияОшибки);
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" getwiteNeError " +getwiteNeError);

                }
                Log.d(this.getClass().getName(), "PезультатВставкиНовойОшибки " + PезультатВставкиНовойОшибки);

            } else {
                System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
            }
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            // TODO: 09.07.2023 clear
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
            Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void методЗаписиОшибкиВФайлErrorDSU1txt(@NonNull String ТекстОшибки, @NonNull String КлассГнерацииОшибки, @NonNull String МетодаОшибки, @NonNull Integer ЛинияОшибки) {
        try{
        ArrayList<String> arrayListОшибкиДляЗаписивФайл = new ArrayList();
        arrayListОшибкиДляЗаписивФайл.add(ТекстОшибки);
        arrayListОшибкиДляЗаписивФайл.add(КлассГнерацииОшибки);
        arrayListОшибкиДляЗаписивФайл.add(МетодаОшибки);
        arrayListОшибкиДляЗаписивФайл.add(String.valueOf(ЛинияОшибки));
        // TODO: 09.07.2023 запись ошибки в файл  .txt
        new SubClassWriteErrorFile(context, arrayListОшибкиДляЗаписивФайл).МетодЗаписьДополенительеноОшибвкивФайл();
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
            this.linkedBlockingQueueВскеОшибкиДляЗаписи = linkedBlockingQueueВскеОшибкиДляЗаписи;
        }

        void МетодЗаписьДополенительеноОшибвкивФайл() {
            try {

                методСозданиеИЗАписиОшибки(linkedBlockingQueueВскеОшибкиДляЗаписи);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("  Ошибка в    дополнительном модуле записи ошиьки в файл SubClassWriteErrorFile ");
                Log.e(context.getClass().getName(), "Ошибка в самом классе создание ОШИБКИ (записи новой ошибки) ERROR  inse ERROR" + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }


    public void методСозданиеИЗАписиОшибки(@NonNull  ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи) {
        try {
            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator + fileName);
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator+patchFileName +File.separator+ fileName);
            if (file.isFile()) {
                BufferedWriter bufferedWriter =  Files.newBufferedWriter(Paths.get(file.getPath()), StandardCharsets.UTF_16,
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);



                String    СамаОшибка=null;
                boolean ДлинаСтрокивСпиноре = linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString().length() > 40;
                if (ДлинаСтрокивСпиноре) {
                    StringBuffer sb = new StringBuffer(linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString());
                    sb.insert(40, System.lineSeparator());
                 СамаОшибка = sb.toString();
                }else {
                    СамаОшибка =linkedBlockingQueueВскеОшибкиДляЗаписи.get(0).toString();
                }






                bufferedWriter.write("\n");
                bufferedWriter.write("##### ERROR #####");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                bufferedWriter.write(СгенерированованныйДатаВремениСейчаcДляУдаления);
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("error");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write(СамаОшибка);
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("Class");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(1).toString());
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("Metod");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(2).toString());
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("Line");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                bufferedWriter.write(linkedBlockingQueueВскеОшибкиДляЗаписи.get(3).toString());
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");


                bufferedWriter.flush();
                bufferedWriter.close();
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}

// TODO: 07.10.2023  class Create FILE for Error


