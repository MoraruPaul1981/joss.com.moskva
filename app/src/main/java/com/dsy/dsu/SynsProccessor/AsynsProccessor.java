package com.dsy.dsu.SynsProccessor;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BootAndAsync.BlBootAsync.SendMainActivity;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.BusinessLogicAll.Class_Visible_Processing_Async;
import com.dsy.dsu.BusinessLogicAll.Jakson.GeneratorBinarySONSerializer;
import com.dsy.dsu.BusinessLogicAll.Jakson.GeneratorJSONSerializer;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.JbossAdrress.QualifierJbossServer3;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.SimpleFormatter;

import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AsynsProccessor extends Class_MODEL_synchronized {
    // TODO: 28.07.2022  переменые
    public Context context;
    private CopyOnWriteArrayList<String> ГлавныеТаблицыСинхронизации =new CopyOnWriteArrayList();

    private SQLiteDatabase sqLiteDatabase ;
    private PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации;
    private boolean ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА = false;
    private  String ФлагКакуюЧастьСинхронизацииЗапускаем =new String();

    private  Integer ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы=0;



    private  Integer МаксималноеКоличествоСтрочекJSON;
    private SharedPreferences preferences;
    private   int Проценты;
    private Integer ИндексВизуальнойДляPrograssBar=0;

    private Integer  ПубличныйIDДляФрагмента=0;


    private                 Integer ПозицияТекущейТаблицы=1;

    private     String НазваниеСервернойТаблицы=new String();


    private  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";


    private     CopyOnWriteArrayList<Long>  ЛистТаблицыОбмена;


    private   ObjectMapper jsonGenerator;

    private SSLSocketFactory getsslSocketFactory2;

    private LinkedHashMap<Integer,String> getHiltJbossDebug;
    private  LinkedHashMap<Integer,String> getHiltJbossReliz;



    // TODO: 28.07.2022
    public AsynsProccessor(@NotNull Context context,@NonNull  ObjectMapper jsonGenerator,
                           @NotNull  SSLSocketFactory getsslSocketFactory2,
                           @NonNull LinkedHashMap<Integer,String> getHiltJbossDebug,@NonNull  LinkedHashMap<Integer,String> getHiltJbossReliz) {
        super(context);
        this.context=context;
        this.   public_contentДатыДляГлавныхТаблицСинхронизации=new PUBLIC_CONTENT(context);
        this.   sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
        this.jsonGenerator=    jsonGenerator;
        this.getsslSocketFactory2=    getsslSocketFactory2;
        this.getHiltJbossDebug=    getHiltJbossDebug;
        this.getHiltJbossReliz=    getHiltJbossReliz;
        Log.w(context.getClass().getName(), "sqLiteDatabase" + sqLiteDatabase);
    }

    // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
    public Long МетодЗАпускаФоновойСинхронизации(@NotNull Context context, @NonNull Handler handlerAsync)   {
        Long      РезультатаСинхронизации = 0l;
        try{
            this.context=context;


            preferences=  context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            ГлавныеТаблицыСинхронизации = new PUBLIC_CONTENT(context).методCreatingMainTabels(context);
            Log.d(this.getClass().getName(), "  ГлавныеТаблицыСинхрониазции " + ГлавныеТаблицыСинхронизации.size());
            // TODO: 28.09.2022 запускам синхрогниазцию
            РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации();
            Log.w(this.getClass().getName(), " ФОНОВАЯ СИНХОРОНИЗАЦИИИ ИДЁТ... СЛУЖБА "+РезультатаСинхронизации);




            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатаСинхронизации;
    }
    // TODO: 25.09.2021 ВТОРАЯ ВЕСРИЯ ЗАПУСКА СИНХРОНМИАЗЦИИИ С ТАБЕЛЯ






    // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
    public Long МетодЗАпускаФоновойСинхронизации(@NotNull Context context )   {
        Long      РезультатаСинхронизации = 0l;
        try{
            this.context=context;
            preferences=  context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            ГлавныеТаблицыСинхронизации = new PUBLIC_CONTENT(context).методCreatingMainTabels(context);
            Log.d(this.getClass().getName(), "  ГлавныеТаблицыСинхрониазции " + ГлавныеТаблицыСинхронизации.size());
            // TODO: 28.09.2022 запускам синхрогниазцию
            РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации();
            Log.w(this.getClass().getName(), " ФОНОВАЯ СИНХОРОНИЗАЦИИИ ИДЁТ... СЛУЖБА "+РезультатаСинхронизации);




            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатаСинхронизации;
    }

// TODO: 19.08.2021  ДАННЫЙ МЕТОД ЗАПУСКАЕТ СИНХРОНИЗЦИ ДЛЯ ЧАТА

// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
    // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
    // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
















    ///TODO САМ ФОНОВЫЙ ПОТОК МЕТОД

    Long МетодСамогоФоновойСинхронизации( ) {
        Long ФинальныйРезультаФоновойСинхрониазции=0l;
        try {

            Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Synchronizasiy_Data " + new Date() +
                    "\n" + " Build.BRAND " + Build.BRAND.toString()+" СколькоСтрочекJSON " );
            //////TODO ШАГ ТРЕТИЙ  ЗАПУСКАЕМ САМУ СИНХРОНИЗАЦИЮ  сама синхронизация в фоне
            ФинальныйРезультаФоновойСинхрониазции =            МетодНачалоСихронизациивФоне(context); ////Получение Версии Данных Сервера для дальнейшего анализа

            Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                    "  ФинальныйРезультаФоновойСинхрониазции " +ФинальныйРезультаФоновойСинхрониазции);
            Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                    ФинальныйРезультаФоновойСинхрониазции);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ФинальныйРезультаФоновойСинхрониазции;
    }


























//////ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET

    Long МетодНачалоСихронизациивФоне(@NotNull Context context) throws InterruptedException, ExecutionException, TimeoutException, JSONException {
        Long результатСинхрониазции=0l;
        try {
            результатСинхрониазции=     МетодПолучениеIDотСервераДляГеренированиеUUID(); ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
            Log.d(this.getClass().getName(), " результатСинхрониазции " + результатСинхрониазции);
            Log.d(this.getClass().getName(), " результатСинхрониазции" + результатСинхрониазции);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    результатСинхрониазции;
    }













    // TODO: 13.10.2021 нАЧАЛО сИНХРОНИАЗЦИ
    Long МетодПолучениеIDотСервераДляГеренированиеUUID() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
        Long РезультатСинхрониазции=0l;
        try {
            String ДанныеПришёлЛиIDДЛяГенерацииUUID = new String();
            ID =0;
            Log.d(this.getClass().getName(), " public   void МетодПолучениеIDОтСервераДляГеренированиеUUID ()" + " ДанныеПришёлЛиIDДЛяГенерацииUUID "
                    + ДанныеПришёлЛиIDДЛяГенерацииUUID +
                    " ДанныеПришёлЛиIDДЛяГенерацииUUID.length()  " + ДанныеПришёлЛиIDДЛяГенерацииUUID.length());
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(context);
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.concurrentHashMapНабор.put("СтолбцыОбработки","id");
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.concurrentHashMapНабор.put("УсловиеЛимита","1");
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИ
            SQLiteCursor Курсор_ВычисляемПУбличныйID= (SQLiteCursor)  class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков, sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );
            if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                Курсор_ВычисляемПУбличныйID.moveToFirst();
                ID =Курсор_ВычисляемПУбличныйID.getInt(0);
                Log.w(this.getClass().getName(), "  ID " + ID);
            }
            Log.w(this.getClass().getName(), "  ID  " + ID);
            Курсор_ВычисляемПУбличныйID.close();
            // TODO: 09.09.2022  запускаем синхрониазцию
            if (ID > 0) {
                ////TODO создаем списко таблиц запускаем слуд.ющий метод получение версии базы данных
                РезультатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(ID);//получаем ID для генерирования UUID
                Log.d(this.getClass().getName(), " Результат  РезультатСинхрониазции  " + РезультатСинхрониазции);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатСинхрониазции;
    }

    ///////////////////метод получение ОТ СЕРВЕРА КОНКРЕТНЫЙ СПИСОК ТАДОИЦЦ ДЛЯ ОБМЕНА

























    ////////////МЕТОД ПОЛУЧЕНИЕ  ВЕРСИИ ДАННЫХ
    Long МетодПолучениеСпискаТаблицДляОбменаДанными(@NonNull Integer   ID)
            throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ

        Long РезультатСинхронизации=0l;
        try {
            Log.d(this.getClass().getName(), "   ID  ID" +   ID);
            String ДанныеПришлаСпискаТаблицДляОбмена = new String();
            StringBuffer BufferGetVersionData = new StringBuffer();

            preferences=  context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);


// TODO: 02.04.2024  Адресс и Порт Сервера Jboss 
            String   ИмяСерверИзХранилица = getHiltJbossDebug.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltJbossDebug.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                    " ПортСерверИзХранилица " +ПортСерверИзХранилица );


            // TODO: 10.11.2022 Получение Список Таблиц
            BufferGetVersionData = МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(
                    "modification_server",
                    "application/gzip",
                    "Хотим Получить Версию Данных Сервера",///"Хотим Получить Версию Данных Сервера" б //TODO "Хотим Получить Статус Блокировки Пользователя по ID"
                    0l,
                    ID,
                    ИмяСерверИзХранилица ,
                    ПортСерверИзХранилица,getsslSocketFactory2);
            Log.d(this.getClass().getName(), " BufferGetVersionData.toString().toCharArray().length "
                    + BufferGetVersionData.toString().toCharArray().length);
            // TODO: 03.09.2021
            if (BufferGetVersionData != null) {
                if (BufferGetVersionData.toString().toCharArray().length > 3
                        && ! BufferGetVersionData.toString().trim().matches("(.*)Server Running...... Don't Login(.*)")) {
                    Log.d(this.getClass().getName(), "  ID  " + this.ID +
                            " BufferGetVersionData " + BufferGetVersionData.toString());


                    //TODO БУфер JSON от Сервера
                    CopyOnWriteArrayList<Map<String, String>> БуферJsonОтСервераmodification_server =
                            jsonGenerator.readValue(BufferGetVersionData.toString(),
                            new TypeReference<CopyOnWriteArrayList<Map<String, String>>>() {
                            });


                    ///упаковываем в j
                    Log.d(this.getClass().getName(), "  БуферJsonОтСервераmodification_server  " + БуферJsonОтСервераmodification_server);
                    public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц = new LinkedHashMap<String, Long>();
                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.clear();
                    // TODO: 09.08.2023  бежим по данным версии сервера
                    Flowable.fromIterable(БуферJsonОтСервераmodification_server)
                            .onBackpressureBuffer()
                            .subscribeOn(Schedulers.trampoline())
                            .blockingIterable()
                            .forEach(new Consumer<Map<String, String>>() {
                                @Override
                                public void accept(Map<String, String> stringStringMap) {
                                    stringStringMap.forEach(new BiConsumer<String, String>() {
                                        @Override
                                        public void accept(String НазваниеТаблицыСервера, String ВерсияДанныхСервернойТаблицы) {
                                            if (НазваниеТаблицыСервера.trim().equalsIgnoreCase("name")) {
                                                public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.add(ВерсияДанныхСервернойТаблицы.trim());
                                                НазваниеСервернойТаблицы =ВерсияДанныхСервернойТаблицы.trim();
                                            }
                                            if (НазваниеТаблицыСервера.trim().equalsIgnoreCase("versionserverversion")) {
                                                public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.put(НазваниеСервернойТаблицы.trim(),
                                                        Long.valueOf(ВерсияДанныхСервернойТаблицы));

                                            }
                                            if (НазваниеТаблицыСервера.trim().equalsIgnoreCase("versionserver")) {

                                                // TODO: 09.08.2023  даты заполяем таблиц с серверар
                                          Date ДатаВерсииДанныхSQLServer=      formattingDateOnVersionSqlServer(ВерсияДанныхСервернойТаблицы);

                                                public_contentДатыДляГлавныхТаблицСинхронизации.  ВерсииДатыСерверныхТаблиц.put(НазваниеСервернойТаблицы.trim(), ДатаВерсииДанныхSQLServer );

                                            }



                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " НазваниеТаблицыСервера " + НазваниеТаблицыСервера + " ВерсияДанныхСервернойТаблицы " + ВерсияДанныхСервернойТаблицы+
                                                    " НазваниеСервернойТаблицы[0] " +НазваниеСервернойТаблицы);
                                        }
                                    });
                                }
                            });

                    РезультатСинхронизации   = МетодГлавныхЦиклТаблицДляСинхронизации(ID);


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатСинхронизации " + РезультатСинхронизации);

                    Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString() +
                            " ВерсииВсехСерверныхТаблиц " + public_contentДатыДляГлавныхТаблицСинхронизации.toString() +
                            "  ДанныеПришлаСпискаТаблицДляОбмена " + ДанныеПришлаСпискаТаблицДляОбмена);
                }
            }else {
                Log.i(this.getClass().getName(), " НЕт данных с сервера  BufferGetVersionData " + BufferGetVersionData );

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатСинхронизации;
    }

    private Date  formattingDateOnVersionSqlServer(String ВерсияДанныхСервернойТаблицы) {
        Date ДатаВерсииДанныхSQLServer = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS" );
              ДатаВерсииДанныхSQLServer = formatter.parse(ВерсияДанныхСервернойТаблицы);
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ДатаВерсииДанныхSQLServer " + ДатаВерсииДанныхSQLServer);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ДатаВерсииДанныхSQLServer;

    }

// TODO: 10.09.2021  запускаем метод обработки по таблицам

























    ////////////////////////////ДАННЫЙ МЕТОД ПОСЛЕ ВЫШЕ СТОЯШЕГО ВЫРАВНИЯНИЯ НАЗВАНИЙ ТАБЛИЦ ПРИСТУПАЕТ К САМОМУ АНАЛИЗУ ДАННЫХ ВЕРСИИ ДАННЫХ НАХОДЯЩИХСЯ НА АНДРОЙДЕ
    Long analysVersionServerWithAnroidGET(@NonNull String ИмяТаблицыОтАндройда_Локальноая,
                                          @NonNull  Long Полученная_ВерсияДанныхсSqlServer,
                                          @NonNull  Integer ID) {
        ArrayList<Long> ЛистУспешнойОбработкиСинх=new ArrayList<>();
        try {
            Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаВресииДАнныхКлиента;
            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
            SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = null;
            Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная = 0l;
            Long ВерсииДанныхНаАндройдеСерверная = 0l;

            class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);
            Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);
            class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
            class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
            class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("ФорматПосика","name=? ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsgetdata
                    .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор,new PUBLIC_CONTENT(context).МенеджерПотоков,
                            sqLiteDatabase);
            Log.d(this.getClass().getName(), "GetData "+КурсорДляАнализаВерсииДанныхАндройда  );
            /////
            if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {////ВЫЖНОЕ УСЛОВИЕ ЕСЛИ КУРСОР ВЕРНУЛ БОЛЬШЕ НУЛЯ  ДАННАЕ ТОЛЬКО ТОГДА НАЧИНАЕМ АНАЛИЗ ВЕРСИИ ДАННЫХ НА АНДРОЙДЕ
                КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();
                Log.d(this.getClass().getName(), "  Курсор_УзнаемВерсиюБазыНаАдройде.getCount() " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                // TODO: 05.10.2021  получаем верию данных лолькано    --- локльную
                ВерсииДанныхНаАндройдеЛокальнаяЛокальная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));
                Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная+" ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 05.10.2021  получаем верию данных лолькано  - ерверную
                ВерсииДанныхНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));
                Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+"  ИмяТаблицыОтАндройда_Локальноая  "+ИмяТаблицыОтАндройда_Локальноая);
                ///////////ОПРЕДЕЛЯЕМ ДАТУ АНДРОЙДА ДЛЯ СОСТЫКОВКИ С ДАТОЙ SQ; SERVER//// ПОЛУЧАЕМ ДАТУ НА АНДРОЙДЕ ПОЛСЕДНЕЕ ИЗМЕНЕНИЯ ПРИШЕДЩИЕ ДАННЫЕ С СЕРВЕРА
            } else {
                Log.d(this.getClass().getName(), "  НЕт такой таблицы и нет Данных КурсорДляАнализаВерсииДанныхАндройда.getCount()" + КурсорДляАнализаВерсииДанныхАндройда.getCount());
            }
            // TODO: 06.07.2023 clear
            if(КурсорДляАнализаВерсииДанныхАндройда!=null){
                // TODO: 06.07.2023 clear
                КурсорДляАнализаВерсииДанныхАндройда.close();
            }
            // TODO: 05.10.2021  КОГДА ВСЕ ДАННЫЕ ЕСТЬ ТРИ ПЕРЕМЕННЫЕ ПОЛУЧЕНИЕ ПЕРЕХОИМ ДАЛЬШЕ ПОЛЯ ЛОКАЛЬНАЯ ВЕРСИЯ ДАННЫХ, СЕРВЕНАЯ ВЕРСИЯ ДАННЫХ, И ТЕРТЬЯ ВЕРИСЯ С СЕРВЕРА ПО ДАННОЙ ТАБЕЛИЦВ
            Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+
                    "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                    +"   Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
            // TODO: 05.10.2021 ПРИ НАЛИЧИИ ВСЕХ ТРЕХ ПОЗИЦИЙ ЛОКАЛЬНАЯ ВЕРСИЯ С АНДРОЙДА   И СЕРВРНАЯ ВЕРСИЯ С АНДРОЙДА И  ПРИШЕДШЕЯ ВЕРСИЯ С СЕРВЕРА
            ///
            ЛистУспешнойОбработкиСинх=   ObservableRetryGet(ИмяТаблицыОтАндройда_Локальноая, Полученная_ВерсияДанныхсSqlServer, ID);

            // TODO: 05.10.2021  КОГДА ВСЕ ДАННЫЕ ЕСТЬ ТРИ ПЕРЕМЕННЫЕ ПОЛУЧЕНИЕ ПЕРЕХОИМ ДАЛЬШЕ ПОЛЯ ЛОКАЛЬНАЯ ВЕРСИЯ ДАННЫХ, СЕРВЕНАЯ ВЕРСИЯ ДАННЫХ, И ТЕРТЬЯ ВЕРИСЯ С СЕРВЕРА ПО ДАННОЙ ТАБЕЛИЦВ
            Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+
                    "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                    +"   ЛистУспешнойОбработкиСинх " +ЛистУспешнойОбработкиСинх);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЛистУспешнойОбработкиСинх.stream().reduce(0l, (a, b) -> a + b).longValue();
    }

    ////////////////////////////ДАННЫЙ МЕТОД ПОСЛЕ ВЫШЕ СТОЯШЕГО ВЫРАВНИЯНИЯ НАЗВАНИЙ ТАБЛИЦ ПРИСТУПАЕТ К САМОМУ АНАЛИЗУ ДАННЫХ ВЕРСИИ ДАННЫХ НАХОДЯЩИХСЯ НА АНДРОЙДЕ
    Long analysVersionServerWithAnroidPOST(@NonNull String ИмяТаблицы,
                                          @NonNull  Long ВерсияДанныхсSqlServer,
                                          @NonNull  Integer PublicID,
                                           @NonNull Date     ВремяОтSqlServer) {
        ArrayList<Long> ЛистУспешнойОбработкиСинх=new ArrayList<>();

        try  (    SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = getCurcorForAllVersionDataAndroid(ИмяТаблицы);){
            // TODO: 05.04.2024  получаем верисю данных андройд версия всехданных
            if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {////ВЫЖНОЕ УСЛОВИЕ ЕСЛИ КУРСОР ВЕРНУЛ БОЛЬШЕ НУЛЯ  ДАННАЕ ТОЛЬКО ТОГДА НАЧИНАЕМ АНАЛИЗ ВЕРСИИ ДАННЫХ НА АНДРОЙДЕ
                КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();

                  Long   ВерсииНаАндройдеЛокальная     =
                        КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));

                Long ВерсииНаАндройдеСерверная    =  КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));

                String ВремяДанныхSQliteНаАндройде    =  КурсорДляАнализаВерсииДанныхАндройда.getString(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));

                // TODO: 09.08.2023  даты заполяем таблиц с серверар
                Date ВремяДанныхНаАндройде=      formattingDateOnVersionSqlServer(ВремяДанныхSQliteНаАндройде);



                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                        +" ВерсииНаАндройдеЛокальная " +ВерсииНаАндройдеЛокальная
                        + "\n"
                        +" ВерсииНаАндройдеСерверная " +ВерсииНаАндройдеСерверная
                        + "\n"
                        +" ВремяДанныхНаАндройде " +ВремяДанныхНаАндройде
                        + "\n"
                        +" ВремяДанныхНаАндройде " +ВерсияДанныхсSqlServer+ "\n"
                        +" ИмяТаблицы " +ИмяТаблицы);



            ЛистУспешнойОбработкиСинх=   ObservableRetryPost(ИмяТаблицы,
                    ВерсияДанныхсSqlServer, ID,
                    ВерсииНаАндройдеСерверная,
                    ВерсииНаАндройдеСерверная,
                    ВремяДанныхНаАндройде,
                    ВремяОтSqlServer);

            }

            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"+" ЛистУспешнойОбработкиСинх " +ЛистУспешнойОбработкиСинх);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЛистУспешнойОбработкиСинх.stream().reduce(0l, (a, b) -> a + b).longValue();
    }




    // TODO: 05.04.2024 курсор получчаем весрию всех жанных на андройде для дальншего сопоствалвения
    private SQLiteCursor getCurcorForAllVersionDataAndroid(@NonNull String ИмяТаблицыОтАндройда_Локальноая) throws ExecutionException, InterruptedException {
        SQLiteCursor cursorAllVersionDataFromAndorid = null;
        try{
        Class_GRUD_SQL_Operations  class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);
        Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);
        class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
        class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
        class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("ФорматПосика","name=? ");
        class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("УсловиеПоиска1", ИмяТаблицыОтАндройда_Локальноая);
            // TODO: 05.04.2024 get Cursor all version data android
          cursorAllVersionDataFromAndorid= (SQLiteCursor)  class_grud_sql_operationsgetdata
                .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор,new PUBLIC_CONTENT(context).МенеджерПотоков,
                        sqLiteDatabase);

        Log.d(this.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursorAllVersionDataFromAndorid;
    }


    // TODO: 31.10.2023 после анализва запускаемт синхрониазциию по таблице
    private  ArrayList<Long>   ObservableRetryGet(@NonNull String ИмяТаблицыОтАндройда_Локальноая,
                                                                       @NonNull Long Полученная_ВерсияДанныхсSqlServer,
                                                                       @NonNull Integer ID) {
        ArrayList<Long> ЛистУспешнойОбработкиСинх=new ArrayList<>();
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
                                Long РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       AccessGetDataFromServer(
                                        Полученная_ВерсияДанныхсSqlServer,
                                        ИмяТаблицыОтАндройда_Локальноая,
                                        ID);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                                Log.d(this.getClass().getName(), "   РезультатУспешнойВсатвкиИлиОбвовлениясСервера " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                // TODO: 30.10.2023  записываем результат
                                ЛистУспешнойОбработкиСинх.add(РезультатУспешнойВсатвкиИлиОбвовлениясСервера);

                                if (РезультатУспешнойВсатвкиИлиОбвовлениясСервера >0 ) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "РезультатУспешнойВсатвкиИлиОбвовлениясСервера  " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                    return true;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "РезультатУспешнойВсатвкиИлиОбвовлениясСервера " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                    return false;
                                }
                            }
                        });



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        return ЛистУспешнойОбработкиСинх;
    }
    private  ArrayList<Long>   ObservableRetryPost(@NonNull String ИмяТаблицыОтАндройда_Локальноая,
                                                  @NonNull Long Полученная_ВерсияДанныхсSqlServer,
                                                  @NonNull Integer ID, Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                   @NonNull Long ВерсииДанныхНаАндройдеСерверная,
                                                   @NonNull Date ВремяДанныхНаАндройде) {
        ArrayList<Long> ЛистУспешнойОбработкиСинх=new ArrayList<>();
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
                                Long РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       AccessPostDataFromClient(ИмяТаблицыОтАндройда_Локальноая,
                                        ID);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                                Log.d(this.getClass().getName(), "   РезультатУспешнойВсатвкиИлиОбвовлениясСервера " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                // TODO: 30.10.2023  записываем результат
                                ЛистУспешнойОбработкиСинх.add(РезультатУспешнойВсатвкиИлиОбвовлениясСервера);

                                if (РезультатУспешнойВсатвкиИлиОбвовлениясСервера >0 ) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "РезультатУспешнойВсатвкиИлиОбвовлениясСервера  " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                    return true;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "РезультатУспешнойВсатвкиИлиОбвовлениясСервера " + РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                                    return false;
                                }
                            }
                        });

                Log.e(this.getClass().getName(), "   Нет данных для обмена текущие таблицы "
                        + ИмяТаблицыОтАндройда_Локальноая + " ЛистУспешнойОбработкиСинх " + ЛистУспешнойОбработкиСинх.size());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        return ЛистУспешнойОбработкиСинх;
    }

    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА
    Long AccessGetDataFromServer(Long ВерсияДанныхсСамогоSqlServer,
                                 String ИмяТаблицыОтАндройда_Локальноая,
                                 Integer ID) {
        CopyOnWriteArrayList<Long> copyOnWriteArrayListРезультатСинх=new CopyOnWriteArrayList<>();
        try {
            Log.d(this.getClass().getName(), " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА"
                    + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА
                    + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
            // TODO: 12.08.2021 СЕРВЕРАНАЯ ДАТА ЛОКАЛЬНАЯ
            Long    ВерсияДанныхЛокальнаяСерверная =
                    МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "versionserveraandroid_version",
                            context, ИмяТаблицыОтАндройда_Локальноая);
            ВерсияДанныхЛокальнаяСерверная=      Optional.ofNullable(ВерсияДанныхЛокальнаяСерверная).map(Long::new).orElse(0l);
            Log.d(this.getClass().getName(),
                    " ВерсияДанныхЛокальнаяСерверная" + ВерсияДанныхЛокальнаяСерверная);
            // TODO: 12.08.2021 ЛОКАЛЬНАЯ ДАТА ЛОКАЛЬНАЯ


            // TODO: 17.03.2023 Вресия Данных с Самого Сервера
            ВерсияДанныхсСамогоSqlServer =      Optional.ofNullable(ВерсияДанныхсСамогоSqlServer).map(Long::new).orElse(0l);
            Log.d(this.getClass().getName(), " ВерсияДанныхсСамогоSqlServer" + ВерсияДанныхсСамогоSqlServer);
            // TODO: 05.10.2021  POST()-->

                // TODO: 19.10.2021   GET()->
                if (Math.abs(ВерсияДанныхсСамогоSqlServer-ВерсияДанныхЛокальнаяСерверная)>=1  &&
                        ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("errordsu1") ) {
                    // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ

                        Long         ДанныесСервера =
                                МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсияДанныхЛокальнаяСерверная,
                                        ИмяТаблицыОтАндройда_Локальноая, ID);

                    if (ДанныесСервера>0) {
                        copyOnWriteArrayListРезультатСинх.add(ДанныесСервера);
                    }


                    // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                            "  ВерсияДанныхЛокальнаяСерверная "
                            + ВерсияДанныхЛокальнаяСерверная
                            + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                            "  ДанныесСервера " +ДанныесСервера);

                }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer
                    + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  copyOnWriteArrayListРезультатСинх.stream().mapToLong(m->m).findFirst().orElse(0l);
    }










    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ   ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР




    Long AccessPostDataFromClient(String ИмяТаблицыОтАндройда_Локальноая,
                                 Integer ID) {
        CopyOnWriteArrayList<Long> copyOnWriteArrayListРезультатСинх=new CopyOnWriteArrayList<>();
        try {
            Log.d(this.getClass().getName(), " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА"
                    + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА
                    + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
            // TODO: 12.08.2021 СЕРВЕРАНАЯ ДАТА ЛОКАЛЬНАЯ
            Long    ВерсияДанныхЛокальнаяСерверная =
                    МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "versionserveraandroid_version",
                            context, ИмяТаблицыОтАндройда_Локальноая);
            ВерсияДанныхЛокальнаяСерверная=      Optional.ofNullable(ВерсияДанныхЛокальнаяСерверная).map(Long::new).orElse(0l);
            Log.d(this.getClass().getName(),
                    " ВерсияДанныхЛокальнаяСерверная" + ВерсияДанныхЛокальнаяСерверная);
            // TODO: 12.08.2021 ЛОКАЛЬНАЯ ДАТА ЛОКАЛЬНАЯ
            Long          ВерсияДанныхЛокальноЛокальная =
                    МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "localversionandroid_version",
                            context, ИмяТаблицыОтАндройда_Локальноая);
            ВерсияДанныхЛокальноЛокальная=      Optional.ofNullable(ВерсияДанныхЛокальноЛокальная).map(Long::new).orElse(0l);
            Log.d(this.getClass().getName(), " РезультаПолученаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее"
                    + ВерсияДанныхЛокальноЛокальная+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);

            // TODO: 05.10.2021  POST()-->
            if (ВерсияДанныхЛокальноЛокальная > ВерсияДанныхЛокальнаяСерверная &&
                    ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("view_onesignal") &&
                    ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("chat_users") &&
                    ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("view_onesignal") &&
                    ! ИмяТаблицыОтАндройда_Локальноая.equalsIgnoreCase("prof") ) {

                Log.d(this.getClass().getName(),
                        " ВерсияДанныхЛокальноЛокальная  " + ВерсияДанныхЛокальноЛокальная +
                                "  ВерсияДанныхЛокальнаяСерверная " + ВерсияДанныхЛокальнаяСерверная
                                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая "
                                +ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 30.06.2022  конец встаялеммого кода с задержкой
                Long    ДанныеПосылаемНаСервер = МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(ИмяТаблицыОтАндройда_Локальноая,
                        ВерсияДанныхЛокальнаяСерверная);

                // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "  ВерсияДанныхЛокальнаяСерверная "
                        + ВерсияДанныхЛокальнаяСерверная
                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                        "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);



                if (ДанныеПосылаемНаСервер>0) {
                    copyOnWriteArrayListРезультатСинх.add(ДанныеПосылаемНаСервер);
                    // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                    методПослеУспешногоПолученияПовышаемВерсию(ИмяТаблицыОтАндройда_Локальноая );
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "  ВерсияДанныхЛокальнаяСерверная "
                        + ВерсияДанныхЛокальнаяСерверная+
                        "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);

                // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  copyOnWriteArrayListРезультатСинх.stream().mapToLong(m->m).findFirst().orElse(0l);
    }

    private Long МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(String ИмяТаблицыОтАндройда_Локальноая, Long ВерсияДанныхПришлаПослеУспешнойСинхронизации) {
        Long РезультатСинхронизации=0l;
        try{
            ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
            РезультатСинхронизации =
                    МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, ВерсияДанныхПришлаПослеУспешнойСинхронизации);

            Log.i(this.getClass().getName(), "   РезультатСинхронизации" + РезультатСинхронизации+
                    " ВерсияДанныхПришлаПослеУспешнойСинхронизации "+ВерсияДанныхПришлаПослеУспешнойСинхронизации);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатСинхронизации;
    }

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
            InputStream BufferGetData = методGetByteFromServerAsync(
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
    /////// TODO МЕТОД ПАСРИНГА ПРИШЕДШЕГО  С СЕРВЕРА ВНУТРИ ASYNSTASK В ФОНЕ
    Long МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  InputStream БуферGetByteJson,
                                              @NonNull  String имяТаблицаAsync) throws InterruptedException, JSONException {
        // TODO: 05.07.2023 result suync
        ArrayList <Long> РезультСинхрониазции=new ArrayList<>();
        try {
            Log.d(this.getClass().getName(), " имяТаблицаAsync " + имяТаблицаAsync + " БуферПолученныйJSON " +БуферGetByteJson.available()  );
            //TODO БУфер JSON от Сервера
          //  ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();

         final    JsonParser jsonParser= jsonGenerator.createParser(БуферGetByteJson);
            JsonNode jsonNodeParentMAP= jsonParser.readValueAsTree();
            if (jsonNodeParentMAP!=null && jsonNodeParentMAP.size()>0) {
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNodeParentMAP.size() " +jsonNodeParentMAP.size() );

                // TODO: 03.10.2023 все кроме байт
                РезультСинхрониазции.add(   методRowJsonRow(jsonNodeParentMAP,имяТаблицаAsync));
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
        return   РезультСинхрониазции.stream().reduce(0l, (a, b) -> a + b).longValue();
    }

    // TODO: 13.09.2023   ROW
    Long методRowJsonRow(@NonNull  JsonNode jsonNodeParentMAP,
                         @NonNull String имяТаблицаAsync){
        Long РезультСинхрониазции=0l;
        try{
            if (jsonNodeParentMAP.size()>0) {
                // TODO: 11.10.2022 callback метод обратно в актвити #1
                ИндексВизуальнойДляPrograssBar=0;
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirrorbinary/" + имяТаблицаAsync + "");
                ContentResolver resolver = context.getContentResolver();
                Bundle bundle=new Bundle();
                bundle.putSerializable("jsonNodeParentMAP", (Serializable) jsonNodeParentMAP);
                bundle.putString("nametable",имяТаблицаAsync);
                Проценты = new Class_Visible_Processing_Async(context).
                        ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ГлавныеТаблицыСинхронизации.size());

                // TODO: 22.01.2024 текущее отобраение процентов

                методCallBackPrograssBars(  Проценты,имяТаблицаAsync,ПозицияТекущейТаблицы);

                Bundle bundleРезультатОбновлениеМассовой =resolver.call(uri,имяТаблицаAsync,new StringBuffer(имяТаблицаAsync).toString(),bundle);
                РезультСинхрониазции=bundleРезультатОбновлениеМассовой.getLong("completeasync",0l)   ;
            }
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

            // TODO: 01.05.2023 clear
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультСинхрониазции;
    }




    // TODO: 07.09.2023 После Успешного ПОлучение и Успешной Отправки Выравниваем Версию  Данных AFTER

    private void методПослеУспешногоПолученияПовышаемВерсию(@NonNull String ИмяТаблицыОтАндройда_Локальноая) {
        try{
            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
            Integer РезультатПовышенииВерсииДанных =
                    new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context);
            Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void методПослеУспешногоОтправкиПовышаемВерсию(@NonNull String ИмяТаблицыОтАндройда_Локальноая,
                                                           @NonNull Long ВерсияДанныхсСервера) {
        try{
            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
            Integer РезультатПовышенииВерсииДанных =
                    new SubClassUpVersionDATA().МетодVesrionFromSqlServerUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context,ВерсияДанныхсСервера);
            Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }















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
                    bundleComunications.putInt("maxtables" ,public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size());
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





    ///----------- ТУТ КОД УЖЕ ПОСЫЛАНИЕ ДАННЫХ НА СЕРВЕР МЕТОДУ POST (данные андройда посылаються на сервер)


    /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST
    Long МетодПосылаемДанныеНаСервервФоне(String имяТаблицыОтАндройда_локальноая,
                                          Long ВерсияДанныхОсноваСозданиеДанныхОтправки) {

        Long РезультатСинхронизации=0l;
        try {
            // TODO: 15.02.2022  ДАННЫЕ ДЛЯ ОТПРАВКИ НА СЕРВЕР
            Cursor cursorForSendServer= методГлавныйGetDataForAsync(имяТаблицыОтАндройда_локальноая ,ВерсияДанныхОсноваСозданиеДанныхОтправки );
            /////TODO результаты   количество отправляемой информации на сервера
            if (cursorForSendServer!=null && cursorForSendServer.getCount() > 0) {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorForSendServer "+cursorForSendServer.getCount() );

                //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                РезультатСинхронизации = МетодГенерацииJSON(cursorForSendServer, имяТаблицыОтАндройда_локальноая );
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


    // TODO: 15.02.2022 синхрогниазции таблиц
    @NonNull
    private Cursor методГлавныйGetDataForAsync( @NonNull  String имяТаблицыОтАндройда_локальноая,
                                                @NonNull Long ВерсияДанныхДляСравения) {
        Cursor  cursor=null;
        try{
            ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabaseonlyasync/" + имяТаблицыОтАндройда_локальноая.trim() + "");
            ContentResolver resolver = context.getContentResolver();
            Bundle data=null;

            switch (имяТаблицыОтАндройда_локальноая.trim()) {
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                case "settings_tabels":
                    data=new Bundle();
                    data.putString("query","" +
                            "  SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+"   as gett " +
                            "  WHERE   gett.current_table > "+ВерсияДанныхДляСравения+" AND gett. onesignal IS NOT NULL " +
                            " AND gett.user_update IN ( SELECT DISTINCT getin.user_update " +
                            "  FROM " +имяТаблицыОтАндройда_локальноая+" as getin " +
                            " WHERE   getin.user_update="+ПубличныйIDДляФрагмента+" ) "+"" );

                    Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                    break;
                case "data_notification":
                    data=new Bundle();
                    data.putString("query"," SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+" as gett" +
                            " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+"" );
                    Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                    break;
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID   // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                default:
                    data=new Bundle();
                    data.putString("query"," SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+" as gett" +
                            " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+
                            " AND gett.user_update IN ( SELECT DISTINCT getin.user_update " +
                            " FROM " +имяТаблицыОтАндройда_локальноая+"  as getin " +
                            "WHERE   getin.user_update="+ПубличныйIDДляФрагмента+" ) "+"" );
                    break;
            }
            // TODO: 08.08.2023 ГЛАВНОЕ ПОЛУЧЕНИЕ ДАННЫХ  ДЛя ОТПРАВКИ НА СЕРВЕР
            // TODO: 16.05.2023
            if (data.size()>0) {
                cursor = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
            }
            Log.d(this.getClass().getName(), "cursor   " + cursor  + "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая
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



    // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР






















    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
    ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

    Long МетодГенерацииJSON(@NonNull  Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда,
                            @NonNull String имяТаблицыОтАндройда_локальноая) {
        Long РезультатСинхронизации = 0l;
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
                    if (имяТаблицыОтАндройда_локальноая.equalsIgnoreCase("materials_databinary")
                            || имяТаблицыОтАндройда_локальноая.equalsIgnoreCase("data_chat") ) {
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
                    РезультатСинхронизации = new SubClass_SendToServer(context)
                            .МетодПосылаетНаСерверСозданныйJSONФайлвФоне(BufferJsonForSendServer, имяТаблицыОтАндройда_локальноая ); ////СГЕНЕРИРОВАНЫЙ JSON ФАЙЛ ЕСЛИ БОЛЬШЕ 2 ССИМВОЛОМ В НЕМ ТО ОТПРАВЛЯЕМ
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатСинхронизации );
                }else{
                    Log.d(this.getClass().getName(), " НЕ т данных  "+"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатСинхронизации +
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
        return  РезультатСинхронизации;
    }





    //////ТУТ БУДЕТ ЗАПИСЫВАТЬСЯ УСПЕШНОЕ ОБНЛВДЕНИ И ВСТАВКИ ДАННЫХ НА СЕРВЕРЕ ДЛЯ КЛИЕНТА



    //todo  ПОД КЛАСС  С ГЛАВНМ ЦИКЛОМ ОБМЕНА ДАННЫМИ ТАБЛИ
    Long МетодГлавныхЦиклТаблицДляСинхронизации(@NonNull Integer ID)
            throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
        ЛистТаблицыОбмена = new CopyOnWriteArrayList<>();
        try {
            Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                    + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString()
                    + " ВерсииВсехСерверныхТаблиц "
                    + public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.toString());


// TODO: 21.08.2023 ГЛАВНЫЙ ЦИКЛ СИХРОНИАЗЦИИ--многопоточный
            Completable.complete().blockingSubscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    // TODO: 14.12.2023  топль POSt
                    metofstartingPostSingle();

                    Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b).intValue()"
                            + ЛистТаблицыОбмена.stream().reduce(0l, (a, b) -> a + b).longValue()+ " sqLiteDatabase.isOpen() " +sqLiteDatabase.isOpen());



                }

                @Override
                public void onComplete() {
                    // TODO: 21.08.2023  только GET
                    metofstartingGetParallel(  );

                    Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b).intValue()"
                            + ЛистТаблицыОбмена.stream().reduce(0l, (a, b) -> a + b).longValue()+ " sqLiteDatabase.isOpen() " +sqLiteDatabase.isOpen());
                }

                @Override
                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                }
            });
            // TODO: 15.09.2023
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
        return ЛистТаблицыОбмена.stream().reduce(0l, (a, b) -> a + b).longValue();
    }

    private void metofstartingPostSingle() {
        try{
        Flowable.fromIterable( public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.keySet())
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.trampoline())
                .blockingIterable().forEach(new Consumer<String>() {
                    @Override
                    public void accept(String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
                        // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                        Long   РезультатТаблицыОбмена   =        getLooTablesPOST(ИмяТаблицыоТВерсияДанныхОтSqlServer);

                        // TODO: 15.09.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " ИмяТаблицыоТВерсияДанныхОтSqlServer"
                                +ИмяТаблицыоТВерсияДанныхОтSqlServer + " РезультатТаблицыОбмена " +РезультатТаблицыОбмена );
                    }
                });
            // TODO: 15.09.2023
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
    }



    private void metofstartingGetParallel () {
        try{
            String      РежимПерваяЗапускПослеPasswordИлиПовторная = preferences.getString("РежимЗапускаСинхронизации","");
            ///   if(РежимПерваяЗапускПослеPasswordИлиПовторная.equalsIgnoreCase( "ПовторныйЗапускСинхронизации")){
           /*   if(РежимПерваяЗапускПослеPasswordИлиПовторная.equalsIgnoreCase( "ПовторныйЗапускСинхронизации")){
                  // TODO: 07.12.2023
                  КоличествоПотоков=1;
              }*/



        ParallelFlowable parallelFlowableAsync
                = Flowable.fromIterable( public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.keySet())
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Throwable {
                        if(!s.equalsIgnoreCase("errordsu1")){
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return s;
                        }
                        return new String();
                    }
                }).filter(fil->!fil.toString().isEmpty())
                .parallel(   ).runOn(Schedulers.from(Executors.newFixedThreadPool(3)));

        parallelFlowableAsync.doOnNext(new io.reactivex.rxjava3.functions.Consumer<String>() {
                                @Override
                                public void accept(String ИмяТаблицыоТВерсияДанныхОтSqlServer) throws Throwable {

                                    // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                               final      Long   РезультатТаблицыОбмена   =        getLooTablesGET(ИмяТаблицыоТВерсияДанныхОтSqlServer);
                            // //  final      Long   РезультатТаблицыОбмена   =        getLooTablesGET("materials_databinary" );

                                    // TODO: 15.09.2023
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " ИмяТаблицыоТВерсияДанныхОтSqlServer"
                                            +ИмяТаблицыоТВерсияДанныхОтSqlServer + " РезультатТаблицыОбмена " +РезультатТаблицыОбмена );
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
    }
    // TODO: 22.03.2022  ДЛЯ ОТПРАВКИ ДАННЫХ НА СЕРВЕР









    private Long getLooTablesGET(String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
        Long   РезультатТаблицыОбмена=0l;
        try{
            // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
            Long     ВерсияДанныхОтSqlServer = public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);

            /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
             РезультатТаблицыОбмена=
                    analysVersionServerWithAnroidGET(ИмяТаблицыоТВерсияДанныхОтSqlServer,
                            ВерсияДанныхОтSqlServer, ID);



            Проценты = new Class_Visible_Processing_Async(context).ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ГлавныеТаблицыСинхронизации.size());
            // TODO: 02.05.2023  Ответ Обратно ПрограссБару
            методCallBackPrograssBars(   Проценты, ИмяТаблицыоТВерсияДанныхОтSqlServer,ПозицияТекущейТаблицы);

            ЛистТаблицыОбмена.add(РезультатТаблицыОбмена);
            // TODO: 12.07.2023
            ПозицияТекущейТаблицы++;
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

    private Long getLooTablesPOST(String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
        Long   РезультатТаблицыОбмена=0l;
        try{
            // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
            Long     ВерсияДанныхОтSqlServer = public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);
            // TODO: 02.04.2024 верям данных
            Date     ВремяВерсияОтSqlServer =          public_contentДатыДляГлавныхТаблицСинхронизации.  ВерсииДатыСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);


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


            ЛистТаблицыОбмена.add(РезультатТаблицыОбмена);
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


    private class SubClass_SendToServer  {
        public SubClass_SendToServer(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        }
        // TODO: 22.03.2022

        //////todo МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST
        Long МетодПосылаетНаСерверСозданныйJSONФайлвФоне(@NonNull    byte[] ГенерацияJSONОтAndroid,
                                                         @NonNull String Таблицы) {
            /////
            Long РезультатCallBacksОтСервера = 0l;

            try {
                Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
                // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
                Log.d(this.getClass().getName(), " ГенерацияJSONОтAndroida.toString() "
                        + ГенерацияJSONОтAndroid.toString() +
                        " ГенерацияJSONОтAndroida.toString().toCharArray().length  "
                        + ГенерацияJSONОтAndroid.toString().toCharArray().length +
                        " Таблицы " + Таблицы);
                // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
                // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
                String   ИмяСерверИзХранилица = getHiltJbossDebug.values().stream().map(m->String.valueOf(m)).findFirst().get();
                Integer    ПортСерверИзХранилица = getHiltJbossDebug.keySet().stream().mapToInt(m->m).findFirst().getAsInt();


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ИмяСерверИзХранилица " + ИмяСерверИзХранилица+
                        " ПортСерверИзХранилица " +ПортСерверИзХранилица );
                // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
                StringBuffer    BufferSendDataServer = методSendByteToAsync(
                        ГенерацияJSONОтAndroid,
                        ID,
                        Таблицы,
                        "Получение JSON файла от Андройда",
                        ИмяСерверИзХранилица ,ПортСерверИзХранилица,getsslSocketFactory2);

                ///БУФЕР ОТПРАВКИ ДАННЫХ НА СЕРВЕР  //TODO original "tabel.dsu1.ru", 8888        //TODO "192.168.254.40", 8080
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" BufferSendDataServer "+BufferSendDataServer.toString());
                if (BufferSendDataServer.length() > 0) {
                    if (!BufferSendDataServer.toString().trim().matches("(.*)[Don't Login and Password](.*)")){
                        РезультатCallBacksОтСервера=Long.parseLong(BufferSendDataServer.toString());
                    }
                }
                Log.d(this.getClass().getName(), "BufferSendDataServer.length() " + BufferSendDataServer.length()+
                        " РезультатCallBacksОтСервера " +РезультатCallBacksОтСервера);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатCallBacksОтСервера;
        }

    }
}