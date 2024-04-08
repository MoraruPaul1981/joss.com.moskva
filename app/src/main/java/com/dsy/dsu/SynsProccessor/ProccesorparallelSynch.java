package com.dsy.dsu.SynsProccessor;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BootAndAsync.BlBootAsync.SendMainActivity;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.BusinessLogicAll.Class_Visible_Processing_Async;
import com.dsy.dsu.BusinessLogicAll.Jakson.GeneratorBinarySONSerializer;
import com.dsy.dsu.BusinessLogicAll.Jakson.GeneratorJSONSerializer;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProccesorparallelSynch   {



  Context context;
  ObjectMapper jsonGenerator;
     SSLSocketFactory getsslSocketFactory2;
     LinkedHashMap<Integer, String> getHiltJbossDebug;
    LinkedHashMap<Integer, String> getHiltJbossReliz;
    CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда;
     LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц ;
       LinkedHashMap<String, Date> ВерсииДатыСерверныхТаблиц;
    Integer PublicID;

    ArrayList<Long> SuccessInsertOrUpdates ;



    public ProccesorparallelSynch(@NonNull Context context,
                                  @NonNull ObjectMapper jsonGenerator,
                                  @NonNull SSLSocketFactory getsslSocketFactory2,
                                  @NonNull LinkedHashMap<Integer, String> getHiltJbossDebug,
                                  @NonNull LinkedHashMap<Integer, String> getHiltJbossReliz,

                                  @NonNull  CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда,
                                  @NonNull LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц ,
                                  @NonNull  LinkedHashMap<String, Date> ВерсииДатыСерверныхТаблиц,
                                  @NonNull Integer PublicID) {



        this. context=context;
        this.   jsonGenerator=jsonGenerator;
        this.   getsslSocketFactory2=getsslSocketFactory2;
        this.    getHiltJbossDebug=getHiltJbossDebug;
        this.  getHiltJbossReliz=getHiltJbossReliz;

        this.ИменаТаблицыОтАндройда=ИменаТаблицыОтАндройда;
        this.ВерсииВсехСерверныхТаблиц=ВерсииВсехСерверныхТаблиц;
        this.ВерсииДатыСерверныхТаблиц=ВерсииДатыСерверныхТаблиц;

        this.  PublicID=PublicID;

    }

    public Long startingAsyncParallels() {
        CopyOnWriteArrayList<Long> coutSucceessItemAsycnTables=new CopyOnWriteArrayList();
        try{
            SuccessInsertOrUpdates=new ArrayList<>();

            // TODO: 07.04.2024  
            ParallelFlowable     flowable= (ParallelFlowable)    Flowable.fromIterable( ВерсииВсехСерверныхТаблиц.keySet())
                  .filter(fil->!fil.toString().isEmpty())
                .parallel(3).runOn(Schedulers.computation());
            flowable.doOnNext(new io.reactivex.rxjava3.functions.Consumer<String>() {
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
                                    + " coutSucceessItemAsycnTables " +coutSucceessItemAsycnTables.size()+
                                     " coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum)" +coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum));
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
                    }).sequential().blockingSubscribe();

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
        return coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum);
    }

// TODO: 07.04.2024




    public Long getLooTablesPOSTANDGET(@NonNull String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
        Long   РезультатТаблицыОбмена=0l;
        try{
            // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
            Long     ВерсияДанныхОтSqlServer = ВерсииВсехСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);
            // TODO: 02.04.2024 верям данных
            Date ВремяВерсияОтSqlServer = ВерсииДатыСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхОтSqlServer " +ВерсияДанныхОтSqlServer+ " ИмяТаблицыоТВерсияДанныхОтSqlServer "
                    + ИмяТаблицыоТВерсияДанныхОтSqlServer +
                    "   ВремяВерсияОтSqlServer " + ВремяВерсияОтSqlServer);
            
            /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
            РезультатТаблицыОбмена=
                    analysVersionServerWithAnroidPOST(ИмяТаблицыоТВерсияДанныхОтSqlServer,
                            ВерсияДанныхОтSqlServer, PublicID,ВремяВерсияОтSqlServer);
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

        CopyOnWriteArrayList<Long> copyУспешнойОбработкиСинх=new CopyOnWriteArrayList<>();
        try  {
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
                        +" ВремяОтSqlServer " +ВремяОтSqlServer);



                Completable.complete().blockingSubscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                        Observable observablePost=      Observable.range(1,Integer.MAX_VALUE)
                                .take(1);

// TODO: 08.04.2024 через Retry Obsever множественое ображение  к серверу
                        copyУспешнойОбработкиСинх.add(  ObservableRetryGETAndPOST(ИмяТаблицы,
                                ВерсияДанныхсSqlServer,
                                PublicID,
                                ВремяОтSqlServer,"POST",observablePost));

                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                                " copyУспешнойОбработкиСинх " + copyУспешнойОбработкиСинх.size()+"POST");

                    }

                    @Override
                    public void onComplete() {


                        Observable observableGet=      Observable.range(1,Integer.MAX_VALUE)
                                .take(10,TimeUnit.MINUTES);

// TODO: 08.04.2024 через Retry Obsever множественое ображение  к серверу
                        copyУспешнойОбработкиСинх.add(  ObservableRetryGETAndPOST(ИмяТаблицы,
                                ВерсияДанныхсSqlServer,
                                PublicID,
                                ВремяОтSqlServer,"GET",observableGet));

                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                                " copyУспешнойОбработкиСинх " + copyУспешнойОбработкиСинх.size()+"GET");


                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                });

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+
                    " copyУспешнойОбработкиСинх" + copyУспешнойОбработкиСинх.stream().mapToLong(m->m).reduce(0,Long::sum));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  copyУспешнойОбработкиСинх.stream().mapToLong(m->m).reduce(0,Long::sum);
    }


    // TODO: 07.04.2024

    @SuppressLint("SuspiciousIndentation")
    private   Long ObservableRetryGETAndPOST(@NonNull String ИмяТаблицы,
                                             @NonNull Long ВерсияДанныхсSqlServer,
                                             @NonNull  Integer  PublicID,
                                             @NonNull Date   ВремяОтSqlServer,
                                             @NonNull String CooserGetandPost,
                                             @NonNull      Observable observable) {
        final Long[] РезультатУспешнойиПолучениеДанных = {0l};
        try{
            // TODO: 02.11.2023  ПРИНИМАЕМ ДАННЫЕ ОТ СЕРВЕРА ПО ЧАСТЯМ
            observable .forEachWhile(new Predicate<Integer>() {
                        @Override
                        public boolean test(Integer integer) throws Throwable {
                        try {

                            // TODO: 08.04.2024 выполения операции  GET ()
                            РезультатУспешнойиПолучениеДанных[0] =  getCursorWithVersion( ИмяТаблицы, ВерсияДанныхсSqlServer, PublicID, ВремяОтSqlServer,  CooserGetandPost);

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    "РезультатУспешнойиПолучениеДанных " + РезультатУспешнойиПолучениеДанных[0]);

                            if (РезультатУспешнойиПолучениеДанных[0] >0 ) {
                                // TODO: 07.04.2024 записываем рузультат успешной вставки или обновления
                                SuccessInsertOrUpdates.add(РезультатУспешнойиПолучениеДанных[0]);
                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    "SuccessInsertOrUpdates  " + SuccessInsertOrUpdates);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                            if (РезультатУспешнойиПолучениеДанных[0] >0 ) {
                              return  true;
                            }else {
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






























    @SuppressLint("Range")
    private Long getCursorWithVersion(@NonNull String ИмяТаблицы,
                                      @NonNull Long ВерсияДанныхсSqlServer,
                                      @NonNull  Integer  PublicID,
                                      @NonNull Date   ВремяОтSqlServer,
                                      @NonNull String CooserGetandPost) {
        // TODO: 08.04.2024 get and post
        Long ResultatAndGETANDPOST=0l;
     try (        Cursor КурсорДляАнализаВерсииДанныхАндройда = getCurcorForAllVersionDataAndroid(ИмяТаблицы); ){
        // TODO: 07.04.2024  получаем данные локалные лдля сравенния

        // TODO: 05.04.2024  получаем верисю данных андройд версия всехданныхс
        if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {
            КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();


            Long ВерсииНаАндройдеЛокальная =
                    КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда
                            .getColumnIndex("localversionandroid_version"));

            Long ВерсииНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(
                    КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));

            String ВремяДанныхSQliteНаАндройде = КурсорДляАнализаВерсииДанныхАндройда.getString(
                    КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid"));

            // TODO: 09.08.2023  даты заполяем таблиц с серверар
            Date ВремяДанныхНаАндройде = new FormattingVersionDastaSqlserver(context).formattingDateOnVersionSqlServer(ВремяДанныхSQliteНаАндройде);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "ВерсииНаАндройдеЛокальная  " + ВерсииНаАндройдеЛокальная
                    + "\n" +
                    "ВерсииНаАндройдеСерверная  " + ВерсииНаАндройдеСерверная
                    + "\n" +
                    "ВремяДанныхSQliteНаАндройде  " + ВремяДанныхSQliteНаАндройде
                    + "\n" +
                    "ВремяДанныхНаАндройде  " + ВремяДанныхНаАндройде);


            //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
            ResultatAndGETANDPOST = AceccssAndCoohceGetDatatingAndPostDating(
                    ИмяТаблицы,
                    ВерсияДанныхсSqlServer,
                    PublicID,
                    ВерсииНаАндройдеЛокальная,
                    ВерсииНаАндройдеСерверная,
                    ВремяДанныхНаАндройде,
                    ВремяОтSqlServer,
                      CooserGetandPost);
            // TODO: 07.04.2024
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "ResultatAndGETANDPOST " + ResultatAndGETANDPOST);


        }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
       return  ResultatAndGETANDPOST;
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



    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ   ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР




    Long AceccssAndCoohceGetDatatingAndPostDating(@NonNull String ИмяТаблицы,
                                                  @NonNull Long ВерсияДанныхсSqlServer,
                                                  @NonNull  Integer  PublicID,
                                                  @NonNull Long ВерсииНаАндройдеЛокальная,
                                                  @NonNull Long  ВерсииНаАндройдеСерверная,
                                                  @NonNull Date  ВремяДанныхНаАндройде,
                                                  @NonNull Date   ВремяОтSqlServer,
                                                  @NonNull String CooserGetandPost) {
       Long  ДанныеПосылаемИлиПринимаемНаСервер=0l;
        try {

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
                    +" ВремяОтSqlServer " +ВремяОтSqlServer+"\n");


            // TODO: 08.04.2024 get 
            if (CooserGetandPost.equalsIgnoreCase("GET")) {
                
                // TODO: 19.10.2021   GET()->
                if (ВерсияДанныхсSqlServer > ВерсииНаАндройдеСерверная ) {
                    // TODO: 05.04.2024
                    if ( !ВремяОтSqlServer.equals(ВремяДанныхНаАндройде)) {
                        // TODO: 05.04.2024
                        if (!ИмяТаблицы.equalsIgnoreCase("errordsu1")) {

                            ////// todo МЕТОД GET() в фоне    ////// todo МЕТОД GET
                            ДанныеПосылаемИлиПринимаемНаСервер = МетодДанныеПолучаемНаСервервФоне(ИмяТаблицы, ВерсииНаАндройдеСерверная, PublicID);

                            if (ДанныеПосылаемИлиПринимаемНаСервер>0) {
                                // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                                методПослеУспешногоПолученияПовышаемВерсию(ИмяТаблицы );
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ДанныеПосылаемИлиПринимаемНаСервер  " + ДанныеПосылаемИлиПринимаемНаСервер+
                                    "\n"+ " ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде+" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде);
                        }
                    }
                }
            }


            // TODO: 08.04.2024 post 
            if (CooserGetandPost.equalsIgnoreCase("POST")) {
                // TODO: 05.04.2024 post() sending
                // TODO: 05.10.2021  POST()-->
                if (ВерсииНаАндройдеЛокальная > ВерсииНаАндройдеСерверная) {
                    // TODO: 05.04.2024  отправлем только определенные таблицы
                    if (! ИмяТаблицы.equalsIgnoreCase("view_onesignal") &&
                            ! ИмяТаблицы.equalsIgnoreCase("chat_users") &&
                            ! ИмяТаблицы.equalsIgnoreCase("view_onesignal") &&
                            ! ИмяТаблицы.equalsIgnoreCase("prof") ) {

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
                                +" ВремяОтSqlServer " +ВремяОтSqlServer+"\n");

                        ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
                        ДанныеПосылаемИлиПринимаемНаСервер =     МетодПосылаемДанныеНаСервервФоне(ИмяТаблицы, ВерсииНаАндройдеСерверная);



                        if (ДанныеПосылаемИлиПринимаемНаСервер>0) {
                            // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                            методПослеУспешногоПолученияПовышаемВерсию(ИмяТаблицы );
                        }


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ДанныеПосылаемИлиПринимаемНаСервер  "+ДанныеПосылаемИлиПринимаемНаСервер);
                    }
                    
                    // TODO: 05.04.2024  метод GET()   ПОЛУЧАЕМ ДАННЫЕ !!!!!
                    // TODO: 05.04.2024  метод GET()   ПОЛУЧАЕМ ДАННЫЕ !!!!!
                }
            }



            // TODO: 05.04.2024  после обработки обоих методов post and get
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ДанныеПосылаемИлиПринимаемНаСервер;
    }

// TODO: 07.04.2024

    ///----------- ТУТ КОД УЖЕ ПОСЫЛАНИЕ ДАННЫХ НА СЕРВЕР МЕТОДУ POST (данные андройда посылаються на сервер)


    /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST
    Long МетодПосылаемДанныеНаСервервФоне(@NonNull String ИмяТаблицы,
                                          @NonNull Long ВерсииНаАндройдеСерверная) {

        Long РезультатСинхронизации=0l;
        try {
            // TODO: 15.02.2022  ДАННЫЕ ДЛЯ ОТПРАВКИ НА СЕРВЕР
            Cursor cursorForSendServer= методГлавныйGetDataForAsync(ИмяТаблицы ,ВерсииНаАндройдеСерверная ,PublicID);
            /////TODO результаты   количество отправляемой информации на сервера
            if (cursorForSendServer!=null && cursorForSendServer.getCount() > 0) {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorForSendServer "+cursorForSendServer.getCount() );

                //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                РезультатСинхронизации = МетодГенерацииJSON(cursorForSendServer, ИмяТаблицы );
                // TODO: 04.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " РезультатСинхронизации "+РезультатСинхронизации );

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатСинхронизации "+РезультатСинхронизации );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатСинхронизации;
    }


// TODO: 07.04.2024

    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА
    Long МетодДанныеПолучаемНаСервервФоне(@NonNull String ИмяТаблицы,
                                          @NonNull Long  ВерсииНаАндройдеСерверная,
                                          @NonNull Integer PublicID) {
        // TODO: 05.04.2024 get ()
        Long         ДанныесСервера = 0l;
        try {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ИмяТаблицы  "+ИмяТаблицы
                    + "\n"+" ВерсииНаАндройдеСерверная  "+ВерсииНаАндройдеСерверная
                    + "\n"+" PublicID  "+PublicID+"\n");


            // TODO: 05.04.2024   GET()-> получаем данные с сервера
            ДанныесСервера = МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсииНаАндройдеСерверная, ИмяТаблицы, PublicID);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ДанныесСервера  "+ДанныесСервера+ "\n"+" ИмяТаблицы  "+ИмяТаблицы
                    +"\n"+" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная);




            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ИмяТаблицы  "+ИмяТаблицы);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ДанныесСервера;
    }
// TODO: 07.04.2024


    // TODO: 07.09.2023 После Успешного ПОлучение и Успешной Отправки Выравниваем Версию  Данных AFTER

    private void методПослеУспешногоПолученияПовышаемВерсию(@NonNull String ИмяТаблицыОтАндройда_Локальноая) {
        try{
            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
            Integer РезультатПовышенииВерсииДанных =
                    new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  "+
                    " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
// TODO: 07.04.2024

    @NonNull
    private Long МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(@NonNull  Long ВерсияДанных,
                                                                  @NonNull String ИмяТаблицы,
                                                                  @NonNull  Integer ID) {
        Long  РезультатДанныесСервера=0l;
        try{
            Log.d(this.getClass().getName(), " ВерсияДанных" + ВерсияДанных+" ID "   + ID + "ИмяТаблицы"  + ИмяТаблицы);
            //////////TODO МЕТОД get
            РезультатДанныесСервера =
                    МетодПолучаемДаннныесСервера(ИмяТаблицы,
                            ID,
                            ВерсияДанных );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РезультатДанныесСервера  "+РезультатДанныесСервера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатДанныесСервера;
    }
// TODO: 07.04.2024






    // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
    /////МЕТОД КОГДА НА СЕРВЕРЕ ВЕРСИЯ ДАННЫХ ВЫШЕ И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВРА
    @SuppressLint("SuspiciousIndentation")
    Long МетодПолучаемДаннныесСервера( @NonNull String ИмяТаблицы,
                                       @NonNull Integer ID
            , @NonNull Long  ВерсияДанных) {

        Long РезультатФоновнойСинхронизации=0l;
        try {
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltJbossDebug.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltJbossDebug.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                    " ПортСерверИзХранилица " +ПортСерверИзХранилица );
            // TODO: 10.11.2022  Получение JSON-потока
            InputStream BufferGetData =new Class_MODEL_synchronized(context). методGetByteFromServerAsync(
                    ИмяТаблицы,
                    "application/gzip",
                    "Хотим Получить  JSON"
                    ,ВерсияДанных,
                    ID,
                    ИмяСерверИзХранилица
                    ,ПортСерверИзХранилица,getsslSocketFactory2);
            // TODO: 01.12.2023

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " BufferGetData " +BufferGetData );

            if (BufferGetData!=null) {
                //////TODO запускаем метод распарстивая JSON
                РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(BufferGetData, ИмяТаблицы);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РезультатФоновнойСинхронизации "+РезультатФоновнойСинхронизации+
                    " BufferGetData " +BufferGetData);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл
        }
        return РезультатФоновнойСинхронизации;
    }

// TODO: 07.04.2024

    /////// TODO МЕТОД ПАСРИНГА ПРИШЕДШЕГО  С СЕРВЕРА ВНУТРИ ASYNSTASK В ФОНЕ
    Long МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  InputStream БуферGetByteJson,
                                              @NonNull  String имяТаблицаAsync) throws InterruptedException, JSONException {
        // TODO: 05.07.2023 result suync
        Long  РезультСинхрониазции=0l;
        try {
            Log.d(this.getClass().getName(), " имяТаблицаAsync " + имяТаблицаAsync + " БуферПолученныйJSON " +БуферGetByteJson.available()  );
            //TODO БУфер JSON от Сервера
            //  ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();

            final JsonParser jsonParser= jsonGenerator.createParser(БуферGetByteJson);
            JsonNode jsonNodeParentMAP= jsonParser.readValueAsTree();
            if (jsonNodeParentMAP!=null && jsonNodeParentMAP.size()>0) {
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNodeParentMAP.size() " +jsonNodeParentMAP.size() );

                // TODO: 03.10.2023 все кроме байт
                РезультСинхрониазции=   методRowJsonRow(jsonNodeParentMAP,имяТаблицаAsync,ВерсииВсехСерверныхТаблиц);
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " БуферGetByteJson " +БуферGetByteJson );


            }
// TODO: 14.09.2023 exit
            БуферGetByteJson.close();

            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " РезультСинхрониазции " + РезультСинхрониазции);
            // TODO: 01.05.2023 clear
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return   РезультСинхрониазции ;
    }

// TODO: 07.04.2024


    // TODO: 13.09.2023   ROW
    Long методRowJsonRow(@NonNull  JsonNode jsonNodeParentMAP,
                         @NonNull String имяТаблицаAsync,
                         @NonNull  LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц){
        Long РезультСинхрониазции=0l;
        try{
        ArrayList<String> ВерсииВсехFindCurrentTable= (ArrayList<String>) ВерсииВсехСерверныхТаблиц.keySet().stream().collect(Collectors.toList());

            Integer ПозицияТекущейТаблицы=      ВерсииВсехFindCurrentTable.indexOf(имяТаблицаAsync);
            int Проценты = 0;
            if (jsonNodeParentMAP.size()>0) {
                // TODO: 11.10.2022 callback
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirrorbinary/" + имяТаблицаAsync + "");
                ContentResolver resolver = context.getContentResolver();
                Bundle bundle=new Bundle();
                bundle.putSerializable("jsonNodeParentMAP", (Serializable) jsonNodeParentMAP);
                bundle.putString("nametable",имяТаблицаAsync);
                Проценты = new Class_Visible_Processing_Async(context).
                        ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ИменаТаблицыОтАндройда.size());

                // TODO: 22.01.2024 текущее отобраение процентов

                методCallBackPrograssBars(  Проценты,имяТаблицаAsync,ПозицияТекущейТаблицы);

                Bundle bundleРезультатОбновлениеМассовой =resolver.call(uri,имяТаблицаAsync,new StringBuffer(имяТаблицаAsync).toString(),bundle);
                РезультСинхрониазции=bundleРезультатОбновлениеМассовой.getLong("completeasync",0l)   ;
            }
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " ПозицияТекущейТаблицы " +ПозицияТекущейТаблицы+
                    " Проценты " +Проценты);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультСинхрониазции;
    }
    // TODO: 07.04.2024



    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    Long МетодГенерацииJSON(@NonNull  Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда,
                            @NonNull String Таблицы) {
        Long ResultatSendingJsonJboss = 0l;
        try {
            if (КурсорДляОтправкиДанныхНаСерверОтАндройда!=null) {
                if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToFirst();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " КурсорДляОтправкиДанныхНаСерверОтАндройда "+КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() );

                    StringWriter stringWriterJSONAndroid=    new StringWriter();
                    //   ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();
                    SimpleModule module = new SimpleModule();
                    // TODO: 11.09.2023  какая текущапя таблица
                    if (Таблицы.equalsIgnoreCase("materials_databinary")
                            || Таблицы.equalsIgnoreCase("data_chat") ) {
                        module.addSerializer(Cursor.class, new GeneratorBinarySONSerializer(context));
                    } else {
                        module.addSerializer(Cursor.class, new GeneratorJSONSerializer(context));
                    }
                    jsonGenerator.registerModule(module);
                    jsonGenerator.getFactory().createGenerator( stringWriterJSONAndroid ).useDefaultPrettyPrinter();
                    byte[] BufferJsonForSendServer=  jsonGenerator.writeValueAsBytes(КурсорДляОтправкиДанныхНаСерверОтАндройда);
                    // TODO: 23.03.2023 ID ПРОФЕСИИ
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " BufferJsonForSendServer"+BufferJsonForSendServer );



                    // TODO: 14.03.2023 ПОСЫЛАЕМ ДАННЫЕ СГЕНЕРИРОНГО JSON НА СЕРВЕР ---->SERVER
                    ResultatSendingJsonJboss = new SendJsonCompliteToJboss().sendingJsonCompliteToJboss(context,BufferJsonForSendServer,
                            Таблицы,getHiltJbossDebug,PublicID,getsslSocketFactory2 );

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +ResultatSendingJsonJboss );
                }else{
                    Log.d(this.getClass().getName(), " НЕ т данных  "+"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +ResultatSendingJsonJboss +
                            " КурсорДляОтправкиДанныхНаСерверОтАндройда " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ResultatSendingJsonJboss;
    }
// TODO: 07.04.2024






    // TODO: 15.02.2022 синхрогниазции таблиц
    @NonNull
    private Cursor методГлавныйGetDataForAsync( @NonNull  String Таблица,
                                                @NonNull Long ВерсияДанныхДляСравения,
                                                @NonNull Integer PublicId) {
        Cursor  cursor=null;
        try{
          //  ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabaseonlyasync/" + Таблица.trim() + "");
            ContentResolver resolver = context.getContentResolver();
            Bundle data=null;

            switch (Таблица.trim()) {
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                case "settings_tabels":
                    data=new Bundle();
                    data.putString("query","" +
                            "  SELECT DISTINCT  * FROM settings_tabels   as gett  " +
                            " WHERE   gett.current_table >   "+ВерсияДанныхДляСравения+" " +
                            " AND gett.user_update = "+PublicId+" "+";" );

                    Log.d(this.getClass().getName(), " Таблица Все остальные  _id " + Таблица);
                    break;
                case "data_notification":
                    data=new Bundle();
                    data.putString("query"," SELECT DISTINCT  * FROM " +Таблица+" as gett" +
                            " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+""+";"  );
                    Log.d(this.getClass().getName(), " Таблица Все остальные  _id " + Таблица);
                    break;
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID   // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                default:
                    data=new Bundle();
                    data.putString("query"," SELECT DISTINCT  * FROM " +Таблица+" as gett" +
                            " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+
                            " AND gett.user_update = "+PublicId + ""+";" );
                    break;
            }
            // TODO: 08.08.2023 ГЛАВНОЕ ПОЛУЧЕНИЕ ДАННЫХ  ДЛя ОТПРАВКИ НА СЕРВЕР
            // TODO: 16.05.2023
            if (data.size()>0) {
                cursor = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
            }

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "cursor   " + cursor  + "  Таблица " +Таблица
                            + " data.size() " +data.size());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursor;
    }

// TODO: 07.04.2024



    //todo МЕТОД ВИЗУАЛЬНОГО ОТВЕТА ИЗ СЛУЖБЫ ОБРАБТНО В activity async
    public void методCallBackPrograssBars(@NonNull int Проценны, @NonNull String имяТаблицаAsync,
                                          @NonNull Integer ПозицияТекущейТаблицы)  {
        try {
            class SendUserДанныеДляPrograssbar extends SendMainActivity {

                public SendUserДанныеДляPrograssbar(Context context) {
                    super(context);
                }

                @Override
                public void startSendBroadSesiver() {
                    //  super.startSendBroadSesiver();
                    intentComunications.setAction("Broad_messageAsyncPrograssBar");
                    bundleComunications.putString("Статус" ,"AsyncPrograssBar");
                    bundleComunications.putInt("Проценны" ,Проценны);
                    bundleComunications.putString("имятаблицы" ,имяТаблицаAsync);
                    bundleComunications.putInt("maxtables" ,  ИменаТаблицыОтАндройда.size());
                    bundleComunications.putInt("currentposition" ,ПозицияТекущейТаблицы);
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusPrograssBar(intentComunications));;

      /*              // TODO: 22.01.2024 останавливаем службу
                    stopServiceBoot();*/

                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserДанныеДляPrograssbar(context).startSendBroadSesiver();


            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " Проценны " +Проценны);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}
