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

import androidx.annotation.NonNull;

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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AsynsProccessor extends Class_MODEL_synchronized {
    // TODO: 28.07.2022  переменые
    public Context context;
    public CopyOnWriteArrayList<String> ГлавныеТаблицыСинхронизации =new CopyOnWriteArrayList();


    CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда = new CopyOnWriteArrayList();
    ArrayList<String> ИменаПроектовОтСервера = new ArrayList<String>();
    public LinkedHashMap<String, Date> ВерсииДатыСерверныхТаблиц =  new LinkedHashMap<>();
    LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц =  new LinkedHashMap<String, Long>();
    public SQLiteDatabase sqLiteDatabase ;
    public SharedPreferences preferences;
    public Integer  ПубличныйIDДляФрагмента=0;
    public     String НазваниеСервернойТаблицы=new String();
    public  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";

    public   ObjectMapper jsonGenerator;

    public SSLSocketFactory getsslSocketFactory2;

    public LinkedHashMap<Integer,String> getHiltJbossDebug;
    public  LinkedHashMap<Integer,String> getHiltJbossReliz;



    // TODO: 28.07.2022
    public AsynsProccessor(@NotNull Context context,@NonNull  ObjectMapper jsonGenerator,
                           @NotNull  SSLSocketFactory getsslSocketFactory2,
                           @NonNull LinkedHashMap<Integer,String> getHiltJbossDebug,@NonNull  LinkedHashMap<Integer, String> getHiltJbossReliz) {
        super(context);
        this.context=context;
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
                    ВерсииВсехСерверныхТаблиц = new LinkedHashMap<String, Long>();
                     ИменаТаблицыОтАндройда.clear();
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
                                               ИменаТаблицыОтАндройда.add(ВерсияДанныхСервернойТаблицы.trim());
                                                НазваниеСервернойТаблицы =ВерсияДанныхСервернойТаблицы.trim();

                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " ВерсияДанныхСервернойТаблицы " + ВерсияДанныхСервернойТаблицы );
                                            }
                                            if (НазваниеТаблицыСервера.trim().equalsIgnoreCase("versionserverversion")) {
                                              ВерсииВсехСерверныхТаблиц.putIfAbsent(НазваниеСервернойТаблицы.trim(),
                                                        Long.valueOf(ВерсияДанныхСервернойТаблицы));

                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " ВерсияДанныхСервернойТаблицы " + ВерсияДанныхСервернойТаблицы );

                                            }
                                            if (НазваниеТаблицыСервера.trim().equalsIgnoreCase("versionserver")) {

                                                // TODO: 09.08.2023  даты заполяем таблиц с серверар
                                                Date ДатаВерсииДанныхSQLServer=    new FormattingVersionDastaSqlserver(context).formattingDateOnVersionSqlServer(ВерсияДанныхСервернойТаблицы);
                                                ВерсииДатыСерверныхТаблиц.putIfAbsent(НазваниеСервернойТаблицы.trim(), ДатаВерсииДанныхSQLServer );


                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " ДатаВерсииДанныхSQLServer " + ДатаВерсииДанныхSQLServer );


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
                            +  ИменаТаблицыОтАндройда.toString() +
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



// TODO: 10.09.2021  запускаем метод обработки по таблицам

































    
    
    
    
    
    
    

























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
























    // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР


























    @SuppressLint("SuspiciousIndentation")
    Long МетодГлавныхЦиклТаблицДляСинхронизации(@NonNull Integer PublicID)
            throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
        // TODO: 07.04.2024
        final Long[] ResultatSync = {0l};
        try {
            Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                    +  ИменаТаблицыОтАндройда.toString()
                    + " ВерсииВсехСерверныхТаблиц "
                    +  ВерсииВсехСерверныхТаблиц.toString()
                    + " ВерсииДатыСерверныхТаблиц "
                    + ВерсииДатыСерверныхТаблиц.toString());


// TODO: 21.08.2023 ГЛАВНЫЙ ЦИКЛ СИХРОНИАЗЦИИ--многопоточный
            Completable.complete().blockingSubscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {


            /*         // TODO: 14.12.2023  топль POSt () Последовательно
                 ResultatSync[0] =   new  ProccesorSecerialynch( context,  jsonGenerator,
                         getsslSocketFactory2,  getHiltJbossDebug,  getHiltJbossReliz).startingAsyncposledovatelno();

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " sequential  "+" ResultatSync " + ResultatSync[0]);*/



                }


                @Override
                public void onComplete() {

                    // TODO: 21.08.2023  только GET() Параллено
           ResultatSync[0] =        new ProccesorparallelSynch( context,
                   jsonGenerator,
                      getsslSocketFactory2,
                   getHiltJbossDebug,
                   getHiltJbossReliz ,
                  ИменаТаблицыОтАндройда,
                   ВерсииВсехСерверныхТаблиц,
                   ВерсииДатыСерверныхТаблиц,
                   PublicID).startingAsyncParallels();


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "parallel"+" ResultatSync " + ResultatSync[0]);
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
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    "  ResultatSync[0] " + ResultatSync[0]);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber()  );
        }
        return  ResultatSync[0] ;
    }




}