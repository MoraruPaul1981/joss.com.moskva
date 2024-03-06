package com.dsy.dsu.CnangeServers;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.SubClassCreatingMainAllTables;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

;


/////--------TODO В ДАННОМ КЛАССЕ СОБРАНЫ ВСЕ СТАТИЧЕСКИЕ ПЕРЕМЕННЫЕ ДЛЯ РАБОТЫ ВСЕГО ПРИЛОЖЕНИЯ DSU-1  ( И БОЛЬШНЕ В КЛАСЕ НИЧЕГО НЕТ )
public  class PUBLIC_CONTENT extends SubClassCreatingMainAllTables {
            //////////
            CompletableFuture completableFutureМенеджер;
                ScheduledFuture scheduledFuture;
                Context context;
                public CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда = new CopyOnWriteArrayList();
    public ArrayList<String> ИменаПроектовОтСервера = new ArrayList<String>(); ////список проектов
    ////ГЛАВНЫЙ СПИСОК ТАБЛИЦ ДЛЯ  ОБМЕНАМИ ДАННЫМИ ИЗ НЕГО БУДЕТ БРАТЬСЯ СПИСКО ТАБЛИЦ
    public LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц =  new LinkedHashMap<String, Long>();

    public LinkedHashMap<String, String> ВерсииДатыСерверныхТаблиц =  new LinkedHashMap<>();
           public    CompletionService МенеджерПотоков;
           public    CompletionService МенеджерМногоПотоков;
           public ExecutorService МенеджерПотоковСервис;
    public Handler.Callback callback;
    private   LinkedHashMap<Integer,String> МассивПортовСервера= new LinkedHashMap();
    // TODO: 10.11.2022
    public PUBLIC_CONTENT(Context context) {
        super(context);
        this.context=context;
      //////////todo  ГЛАВНЫЙ МЕНЕДЖЕР ПОТОКОВ ПРОЕКТА
        if (МенеджерПотоков==null) {
              //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
              // МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newWorkStealingPool());
        }
        if (МенеджерМногоПотоков==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
          //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newCachedThreadPool());
        }

        if (МенеджерПотоковСервис==null) {
            //  МенеджерПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newFixedThreadPool(1000));
            //  МенеджерМногоПотоков=  (CompletionService  )  new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());
            МенеджерПотоковСервис=  (ExecutorService  )  Executors.newSingleThreadExecutor();
        }
    }



    private   String  СсылкаНаРежимСервера;

    // TODO: 19.02.2024
    private   String  СсылкаСервераТип="jboss-1.0-SNAPSHOT";
   /// private   String  СсылкаСервераТип="jbossbackend-1.0-SNAPSHOT";//todo ТИР backend  Gradle



    public String getСсылкаНаРежимСервераТабель() {
    //   СсылкаНаРежимСервера="dsu1.glassfish.atomic";//TODO РЕЛИЗ
        //СсылкаНаРежимСервера="jboss-1.0-SNAPSHOT/dsu1.glassfish.atomic";//TODO РЕЛИЗ
        СсылкаНаРежимСервера=СсылкаСервераТип+"/sous.jboss.tabel";//TODO РЕЛИЗ
        return СсылкаНаРежимСервера.trim();
    }
    private   String  СсылкаНаРежимСервераОбновлениеПО;
    public String getСсылкаНаРежимСервераОбновлениеПО() {
        СсылкаНаРежимСервераОбновлениеПО= СсылкаСервераТип+"/sous.jboss.download";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераОбновлениеПО.trim();
    }

    private   String  СсылкаНаРежимСервераRuntime;
    public String getСсылкаНаРежимСервераRuntime() {
        //   СсылкаНаРежимСервера="dsu1.glassfish.atomic";//TODO РЕЛИЗ
        //СсылкаНаРежимСервера="jboss-1.0-SNAPSHOT/dsu1.glassfish.atomic";//TODO РЕЛИЗ
        СсылкаНаРежимСервераRuntime=СсылкаСервераТип+"/sous.jboss.runtimejboss";//TODO РЕЛИЗ
        return СсылкаНаРежимСервераRuntime.trim();
    }



    public LinkedHashMap<Integer,String> getМассивПортовСервера() {


        // TODO: 18.03.2023 московский сервер
        МассивПортовСервера.putIfAbsent(8888,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8889,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8890,"80.70.108.165");// TODO: 10.11.2022 РЕЛИЗ  Москвовский


      /*  // TODO: 18.03.2023 московский сервер ЧЕРЕЗ DNS
        МассивПортовСервера.putIfAbsent(8888,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8889,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский
        МассивПортовСервера.putIfAbsent(8890,"base.dsu1.ru");// TODO: 10.11.2022 РЕЛИЗ  Москвовский

        */




     /*   // TODO: 18.03.2023 ДЕБАГ  сервер
        МассивПортовСервера.putIfAbsent(8080,"192.168.3.4");// TODO: 10.11.2022 Debug
*/









        //todo//////////20.15*/
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ МассивПортовСервера.values());

        return МассивПортовСервера;
    }







}
///////-------------------------TODO    КЛАСС ВСТАВКИ ОШИБОК------------------




