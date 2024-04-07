package com.dsy.dsu.SynsProccessor;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;

public class ProccesorparallelSynch  extends  AsynsProccessor{


    public ProccesorparallelSynch(@NonNull Context context, @NonNull ObjectMapper jsonGenerator,
                                  @NonNull SSLSocketFactory getsslSocketFactory2, @NonNull LinkedHashMap<Integer,
            String> getHiltJbossDebug, @NonNull LinkedHashMap<Integer, String> getHiltJbossReliz) {
        super(context, jsonGenerator, getsslSocketFactory2, getHiltJbossDebug, getHiltJbossReliz);
    }

    public Long startingAsyncParallels() {
        CopyOnWriteArrayList<Long> coutSucceessItemAsycnTables=new CopyOnWriteArrayList();
        try{
            // TODO: 07.04.2024  
            Flowable.fromIterable( ГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.keySet())
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String ТаблицаОбработываемаяParallel) throws Throwable {
                            if(!ТаблицаОбработываемаяParallel.equalsIgnoreCase("errordsu1")){
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return ТаблицаОбработываемаяParallel;
                            }
                            return new String();
                        }
                    }).filter(fil->!fil.toString().isEmpty())
                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<String>() {
                        @Override
                        public void accept(String ТаблицаОбработываемаяParallel) throws Throwable {

                            // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                            coutSucceessItemAsycnTables.add(getLooTablesPOSTANDGET(ТаблицаОбработываемаяParallel))      ;

                            // TODO: 15.09.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    " ТаблицаОбработываемаяParallel"
                                    +ТаблицаОбработываемаяParallel
                                    + " coutSucceessItemAsycnTables " +coutSucceessItemAsycnTables.size());
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    }).blockingSubscribe();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber()  );
        }
        return coutSucceessItemAsycnTables.stream().mapToLong(l->l).count();
    }

// TODO: 07.04.2024




    public Long getLooTablesPOSTANDGET(@NonNull String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
        Long   РезультатТаблицыОбмена=0l;
        try{
            // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
            Long     ВерсияДанныхОтSqlServer = ГлавныхТаблицСинхронизации.
                    ВерсииВсехСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);
            // TODO: 02.04.2024 верям данных
            Date ВремяВерсияОтSqlServer =          ГлавныхТаблицСинхронизации.
                    ВерсииДатыСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхОтSqlServer " +ВерсияДанныхОтSqlServer+ " ИмяТаблицыоТВерсияДанныхОтSqlServer "
                    + ИмяТаблицыоТВерсияДанныхОтSqlServer +
                    "   ВремяВерсияОтSqlServer " + ВремяВерсияОтSqlServer);
            
            /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
            РезультатТаблицыОбмена=
                    analysVersionServerWithAnroidPOST(ИмяТаблицыоТВерсияДанныхОтSqlServer,
                            ВерсияДанныхОтSqlServer, ID,ВремяВерсияОтSqlServer);
            // TODO: 12.07.2023

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхОтSqlServer " +ВерсияДанныхОтSqlServer+ " ИмяТаблицыоТВерсияДанныхОтSqlServer "
                    + ИмяТаблицыоТВерсияДанныхОтSqlServer +
                    "   РезультатТаблицыОбмена " + РезультатТаблицыОбмена);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатТаблицыОбмена;
    }

// TODO: 07.04.2024



    @SuppressLint("Range")
    Long analysVersionServerWithAnroidPOST(@NonNull String ИмяТаблицы,
                                           @NonNull  Long ВерсияДанныхсSqlServer,
                                           @NonNull  Integer PublicID,
                                           @NonNull Date     ВремяОтSqlServer) {
     Long УспешнойОбработкиСинх = 0l;

        try  (Cursor КурсорДляАнализаВерсииДанныхАндройда = getCurcorForAllVersionDataAndroid(ИмяТаблицы);){
            // TODO: 05.04.2024  получаем верисю данных андройд версия всехданных
            if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {
                КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();

                Long   ВерсииНаАндройдеЛокальная     =
                        КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));

                Long ВерсииНаАндройдеСерверная    =  КурсорДляАнализаВерсииДанныхАндройда.getLong(
                        КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));

                String ВремяДанныхSQliteНаАндройде    =  КурсорДляАнализаВерсииДанныхАндройда.getString(
                        КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid"));

                // TODO: 09.08.2023  даты заполяем таблиц с серверар
                Date ВремяДанныхНаАндройде=    new FormattingVersionDastaSqlserver(context).formattingDateOnVersionSqlServer(ВремяДанныхSQliteНаАндройде);



                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                        +" ИмяТаблицы " +ИмяТаблицы
                        + "\n"
                        +" ВерсияДанныхсSqlServer " +ВерсияДанныхсSqlServer
                        + "\n"
                        +" PublicID " +PublicID
                        + "\n"
                        +" ВерсииНаАндройдеЛокальная " +ВерсииНаАндройдеЛокальная+ "\n"
                        +" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная
                        + "\n"
                        +" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде
                        + "\n"
                        +" ВремяОтSqlServer " +ВремяОтSqlServer);



                УспешнойОбработкиСинх=   ObservableRetryPostAndGet(ИмяТаблицы,
                        ВерсияДанныхсSqlServer,
                        PublicID,
                        ВерсииНаАндройдеЛокальная,
                        ВерсииНаАндройдеСерверная,
                        ВремяДанныхНаАндройде,
                        ВремяОтSqlServer);


                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                        " УспешнойОбработкиСинх " +УспешнойОбработкиСинх);

            }

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                    " УспешнойОбработкиСинх " +УспешнойОбработкиСинх);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return УспешнойОбработкиСинх;
    }


    // TODO: 07.04.2024


    private   Long  ObservableRetryPostAndGet(@NonNull String ИмяТаблицы,
                                                       @NonNull Long ВерсияДанныхсSqlServer,
                                                       @NonNull  Integer  PublicID,
                                                       @NonNull Long ВерсииНаАндройдеЛокальная,
                                                       @NonNull Long  ВерсииНаАндройдеСерверная,
                                                       @NonNull Date  ВремяДанныхНаАндройде,
                                                       @NonNull Date   ВремяОтSqlServer) {
      ArrayList<Long> SuccessInsertOrUpdates=new ArrayList<>();
        try{


            // TODO: 02.11.2023  ПРИНИМАЕМ ДАННЫЕ ОТ СЕРВЕРА ПО ЧАСТЯМ
            Observable.range(1,Integer.MAX_VALUE)
                    .take(1, TimeUnit.HOURS)
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Exception e=new Exception(throwable);
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })//.concatMap(i -> Observable.just(i).delay(300, TimeUnit.MILLISECONDS))
                    .forEachWhile(new Predicate<Integer>() {
                        @Override
                        public boolean test(Integer integer) throws Throwable {


                            //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                      Long     РезультатУспешной =       AceccssAndCoohceGetDatatingAndPostDating(
                                    ИмяТаблицы,
                                    ВерсияДанныхсSqlServer,
                                    PublicID,
                                    ВерсииНаАндройдеЛокальная,
                                    ВерсииНаАндройдеСерверная,
                                    ВремяДанныхНаАндройде,
                                    ВремяОтSqlServer);
                            // TODO: 07.04.2024

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    "SuccessInsertOrUpdates " + SuccessInsertOrUpdates);

                            if (РезультатУспешной >0 ) {
                                // TODO: 07.04.2024 записываем рузультат успешной вставки или обновления
                                SuccessInsertOrUpdates.add(РезультатУспешной);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "SuccessInsertOrUpdates  " + SuccessInsertOrUpdates);
                                return true;
                            } else {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "SuccessInsertOrUpdates " + SuccessInsertOrUpdates);
                                return false;
                            }
                        }
                    });



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "SuccessInsertOrUpdates " + SuccessInsertOrUpdates+"\n"+
                     " SuccessInsertOrUpdates.stream().mapToLong(m->m).count() " +SuccessInsertOrUpdates.stream().mapToLong(m->m).count());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        return SuccessInsertOrUpdates.stream().mapToLong(m->m).count();
    }
// TODO: 07.04.2024



    // TODO: 05.04.2024 курсор получчаем весрию всех жанных на андройде для дальншего сопоствалвения
    private Cursor getCurcorForAllVersionDataAndroid(@NonNull String ИмяТаблицыОтАндройда_Локальноая) throws ExecutionException, InterruptedException {
        Cursor AllVersionAndroidLocalSQlite = null;
        try{
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "MODIFITATION_Client" + "");
            ContentResolver contentResolver=context. getContentResolver();
            // TODO: 05.04.2024 get Curcour all  version local Android Sqlite
            AllVersionAndroidLocalSQlite =      contentResolver.query(uri,new String[]{},
                    new String(" SELECT *  FROM    MODIFITATION_Client   where name = ?    "),
                    new String[]{String.valueOf(ИмяТаблицыОтАндройда_Локальноая)},null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                    " AllVersionAndroidLocalSQlite " +AllVersionAndroidLocalSQlite);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return AllVersionAndroidLocalSQlite;
    }
// TODO: 07.04.2024


}
