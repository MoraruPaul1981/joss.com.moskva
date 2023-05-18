package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.FileUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.NoSuchPaddingException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


///////Универсальный Класс Обмена Данными  Два Стачичных Метода и Плюс Сттичный Курсор
 public class Class_MODEL_synchronized extends CREATE_DATABASE {
  public     Context context;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private Class_MODEL_synchronized ссылка_MODELsynchronized = null;
    private String ПубличноеЛогин =      new String();
    private  String ПубличноеПароль =   new String();
    ////шифрование
/*    public SecretKey ГлавныйКлючДляШифрованиеИРасшифровки;
    ////
    public Cipher ПолитикаШифрование;
    ///////
    public Cipher ПолитикаРасшифровки;*/
    public Integer ID = 0;
    public CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    public Class_MODEL_synchronized(  @NotNull Context context) {
        super(context);
       this. context=context;
        //TODO контроль потоков
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(context);
      Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(context);
    }








    //todo #GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET    //#GET


    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public StringBuffer МетодУниверсальныйДанныесСервера(String ИмяТаблицы,
                                                         String Тип,
                                                         String JobForServer,
                                                         Long Версия,
                                                         Integer ID,
                                                         String ИмяСервера,
                                                         Integer ИмяПорта) {

        final StringBuffer[] БуферСамиДанныеОтСервера = {new StringBuffer()};
        try {
            String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params  = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            OkHttpClient okHttpClientДанныеОтСервера = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Class_GRUD_SQL_Operations grudSqlOperations= new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            PUBLIC_CONTENT  classEngineSQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                        classEngineSQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,deflate,sdch")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(55, TimeUnit.SECONDS).build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                        if (РазмерПришедшегоПотока>0l) {
                            InputStream inputStreamОтПинга = response.body().source().inputStream();
                            GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                            BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                            БуферСамиДанныеОтСервера[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                    StringBuffer::append);
                            Log.d(this.getClass().getName(), "БуферСамиДанныеОтСервера " + БуферСамиДанныеОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                        }
                        Log.d(this.getClass().getName(), "БуферСамиДанныеОтСервера " + БуферСамиДанныеОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                        // TODO: 31.05.2022
                        dispatcherДанныеОтСервера.executorService().shutdown();
                    }
                }
            });
            //TODO
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.MINUTES);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
           String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return БуферСамиДанныеОтСервера[0];

    }

    // TODO: 04.08.2021 HEAD HEAD

    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public StringBuffer МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(String ИмяТаблицы,
                                                                              String Тип,
                                                                              String JobForServer,
                                                                              Long Версия,
                                                                              Integer ID,
                                                                              String ИмяСервера,
                                                                              Integer ИмяПорта) {

        final StringBuffer[] БуферСамиДанныеОтСервера = {new StringBuffer()};
        try {
            String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером "+  СтрокаСвязиСсервером);
            String Params = "?" + "NameTable= " + ИмяТаблицы.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""
                    + "&" + "IdUser=" + ID + ""
                    + "&" + "VersionData=" + Версия + "";
            СтрокаСвязиСсервером=   СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            OkHttpClient okHttpClientДанныеОтСервера = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Class_GRUD_SQL_Operations grudSqlOperations= new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            PUBLIC_CONTENT  classEngineSQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);
                            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                        classEngineSQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль =           Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль+" ANDROID_ID "+ANDROID_ID);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,deflate,sdch")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(55, TimeUnit.SECONDS).build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher dispatcherДанныеОтСервера = okHttpClientДанныеОтСервера.dispatcher();
            okHttpClientДанныеОтСервера.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherДанныеОтСервера.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                        if (РазмерПришедшегоПотока>0l) {
                            InputStream inputStreamОтПинга = response.body().source().inputStream();
                            GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                            BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                            БуферСамиДанныеОтСервера[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                    StringBuffer::append);
                            Log.d(this.getClass().getName(), "БуферСамиДанныеОтСервера " + БуферСамиДанныеОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                        }

                        Log.d(this.getClass().getName(), "БуферСамиДанныеОтСервера " + БуферСамиДанныеОтСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                        // TODO: 31.05.2022
                        dispatcherДанныеОтСервера.executorService().shutdown();
                    }
                }
            });
            //TODO
            dispatcherДанныеОтСервера.executorService().awaitTermination(1, TimeUnit.MINUTES);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            String ОшибкаТекущегоМетода = e.toString();
            if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        //// todo get ASYNtASK
        return БуферСамиДанныеОтСервера[0];

    }


    //todo #GET     //#GET  только для ПИНГА     //#GET  только для ПИНГА  //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА //#GET  только для ПИНГА
    ///МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА
    public Integer МетодУниверсальногоПинга(String NameTable,
                                            String Тип ,
                                            String JobForServer,
                                            Long VersionData,
                                            Integer IdUser,
                                            String ИмяСервера,
                                            Integer ИмяПорта) throws IOException,
            ExecutionException, InterruptedException, TimeoutException, NoSuchAlgorithmException,
            KeyManagementException, InvalidKeyException, NoSuchPaddingException {
        final Integer[] РазмерПришедшегоПотока = {0};
        try {
            StringBuffer БуферРезультатПингасСервером = null;
            String СтрокаСвязиСсервером = "http://" + ИмяСервера + ":" + ИмяПорта + "/"+ new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераRuntime();
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), "   СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            String Params  = "?" + "NameTable= " + NameTable.trim() +
                    "&" + "JobForServer=" + JobForServer.trim() + ""+
                    "&" + "IdUser=" + IdUser + ""
                    + "&" + "VersionData=" + VersionData + "";
            Log.d(this.getClass().getName(), " Params" + Params);
            СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            URL Adress = new URL(СтрокаСвязиСсервером);
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " + СтрокаСвязиСсервером);
            // TODO: 11.03.2023 новый тест код
            // MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient okHttpClientПинг = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                            Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                            grudSqlOperations.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                    " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                            // TODO: 12.10.2021  Ссылка Менеджер Потоков
                            PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                            SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                            try {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                        new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                        publicContent.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                            }
                            String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                            Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                    " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                            Request originalRequest = chain.request();
                            Request.Builder builder = originalRequest.newBuilder()
                                    .header("Content-Type", Тип + " ;charset=UTF-8")
                                    .header("Accept-Encoding", "gzip,deflate,sdch")
                                    .header("Connection", "Keep-Alive")
                                    .header("Accept-Language", "ru-RU")
                                    .header("identifier", ПубличноеЛогин)
                                    .header("p_identifier", ПубличноеПароль)
                                    .header("id_device_androis", ANDROID_ID);
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS).build();
            ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
            Request requestGET = new Request.Builder().get().url(Adress).build();
            Log.d(this.getClass().getName(), "  request  " + requestGET);
            // TODO  Call callGET = client.newCall(requestGET);
            Dispatcher  dispatcherПинг = okHttpClientПинг.dispatcher();
            okHttpClientПинг.newCall(requestGET).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 31.05.2022
                    dispatcherПинг.executorService().shutdown();
                    //TODO закрываем п отоки
                }
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                        StringBuffer БуферРезультатПингасСервером = null;
                        if (РазмерПришедшегоПотока>0l) {
                            InputStream inputStreamОтПинга = response.body().source().inputStream();
                            GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                            BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                            БуферРезультатПингасСервером = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                    StringBuffer::append);
                            Log.d(this.getClass().getName(), "БуферРезультатПингасСервером " + БуферРезультатПингасСервером +  " РазмерПришедшегоПотока[0] " +РазмерПришедшегоПотока);
                        }
                        Log.d(this.getClass().getName(), "БуферРезультатПингасСервером " + БуферРезультатПингасСервером +  " РазмерПришедшегоПотока[0] " +РазмерПришедшегоПотока);
                        // TODO: 31.05.2022
                        dispatcherПинг.executorService().shutdown();
                    }
                }
            });
            dispatcherПинг.executorService().awaitTermination(20,TimeUnit.SECONDS);
            Log.i(context.getClass().getName(), "БуферРезультатПингасСервером" + БуферРезультатПингасСервером);
        } catch (IOException ex) {
            ex.printStackTrace();
            String ОшибкаТекущегоМетода = ex.toString();
            if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                    &&
                    !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        return РазмерПришедшегоПотока[0];
    }


////////////////////////////////16.15  новый метод ПИНГА














































///todo #POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST///#POST


    ///////метод ОТПРАВКИ ДАННЫХ НА СЕРВЕР
    public StringBuffer УниверсальныйБуферОтправкиДанныхНаСервера(@NonNull String СгенерированыйФайлJSONДляОтправкиНаСервер,
                                                                  @NonNull Integer ID,
                                                                  @NonNull String Таблица,
                                                                  @NonNull  String JobForServer,
                                                                  @NonNull   String ИмяСервера,
                                                                  Integer ИмяПорта)  {

        final StringBuffer[] БуферCallsBackОтСеврера = {new StringBuffer()};
                try {
                    // TODO: 12.03.2023  метод POST()
                    String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/"+new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель();
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    String Params = "?" + "NameTable=" + Таблица.trim() + "&"
                            + "IdUser=" + ID +
                            "&" + "JobForServer=" + JobForServer.trim() + "";
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером + Params;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    URL Adress = new URL(СтрокаСвязиСсервером);
                    Log.d(this.getClass().getName(), " Adress  " + Adress);
                    OkHttpClient okHttpClientОтправкиДанныхНаСервер = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                    Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                                    grudSqlOperations.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                                    PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                                    SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                                    try {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                                new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                publicContent.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                                    } catch (ExecutionException e) {
                                        throw new RuntimeException(e);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                        ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                        ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                                    }
                                    String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("Content-Type", "application/json ;charset=UTF-8")
                                            .header("Accept-Encoding", "gzip,deflate,sdch")
                                            .header("Connection", "Keep-Alive")
                                            .header("Accept-Language", "ru-RU")
                                            .header("identifier", ПубличноеЛогин)
                                            .header("p_identifier", ПубличноеПароль)
                                            .header("id_device_androis", ANDROID_ID);
                                    Request newRequest = builder.build();
                                    return chain.proceed(newRequest);
                                }
                            }).connectTimeout(2, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS).build();
                    ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");

                    Log.i(context.getClass().getName(), "СгенерированыйФайлJSONДляОтправкиНаСервер.toString()" + СгенерированыйФайлJSONДляОтправкиНаСервер.toString());
                      MediaType JSON = MediaType.parse("application/json; charset=utf-16");





                   // RequestBody body = RequestBody.create(JSON, СгенерированыйФайлJSONДляОтправкиНаСервер.toString());

                    RequestBody requestBody = new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            try{
                                Log.d(this.getClass().getName(), " requestBody ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                            return JSON;
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                            try (  BufferedWriter БуферПосылаемМетодуPOSTJSONФайл = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(sink.outputStream()), StandardCharsets.UTF_16));){
                                БуферПосылаемМетодуPOSTJSONФайл.write(СгенерированыйФайлJSONДляОтправкиНаСервер.toString());/// ЗАПИСЫВАЕМ В ПОТОК
                                БуферПосылаемМетодуPOSTJSONФайл.flush();///ПРОТАЛКИВАЕМ О
                                Log.d(this.getClass().getName(), " requestBody  СгенерированыйФайлJSONДляОтправкиНаСервер.toString() "+СгенерированыйФайлJSONДляОтправкиНаСервер.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    };
                    Request requestPost = new Request.Builder().post(requestBody).url(Adress).build();
                    Log.d(this.getClass().getName(), "  requestPost  " + requestPost);
                    // TODO  Call callGET = client.newCall(requestGET);
                    Dispatcher  dispatcherCallsBackСервера = okHttpClientОтправкиДанныхНаСервер.dispatcher();
                    okHttpClientОтправкиДанныхНаСервер.newCall(requestPost).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 31.05.2022
                            dispatcherCallsBackСервера.executorService().shutdown();
                            //TODO закрываем п отоки
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                                if (РазмерПришедшегоПотока>0l) {
                                    InputStream inputStreamОтПинга = response.body().source().inputStream();
                                    GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                                    BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                                    БуферCallsBackОтСеврера[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                            StringBuffer::append);

                                    Log.d(this.getClass().getName(), " БуферCallsBackОтСеврера[0] " +  БуферCallsBackОтСеврера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                }

                                Log.d(this.getClass().getName(), " БуферCallsBackОтСеврера[0] " +  БуферCallsBackОтСеврера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                // TODO: 31.05.2022
                                dispatcherCallsBackСервера.executorService().shutdown();
                            }
                        }
                    });
                    dispatcherCallsBackСервера.executorService().awaitTermination(1,TimeUnit.MINUTES);
                    Log.i(context.getClass().getName(), "БуферCallsBackОтСеврера" + БуферCallsBackОтСеврера[0]);
                    // TODO: 12.03.2023  тест код конец
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    String ОшибкаТекущегоМетода=new String();
                    if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)")) {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
        return БуферCallsBackОтСеврера[0];
    }

    ////--- TODO ТУТ НАХОДЯТЬСЯ КОНТЕРЙНЕРЫ ДЛЯ ВСТАВКИ И ОБНОВЛЕНИИ ДАННЫХ  ЧЕРЕЗ КОНТЕЙНЕРЫ


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаВставляем,
                                                            ContentValues КонтейнерДляВставки,
                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                            boolean ФлагОбновлятьДатуВерсииДанных,
                                                            int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                            boolean СинхронизациюВизуализировать,
                                                            Context КонтекстСинхроДляКонтроллера,
                                                            @NotNull CompletionService МенеджерПотоков,
                                                            @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                            Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(context);
///

/////
        try {
            //

                try {

                    // TODO: 06.09.2021 параметры для вствки
                    class_grud_sql_operationsВставка.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                    ////



                    // TODO: 06.09.2021 контейнер для вставки
                    class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставка. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            КонтейнерДляВставки,
                            МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                    //////
                    if (Результат_ВставкиДанных > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                        ///
                        КонтейнерДляВставки.clear();
                        ////
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                                +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  );

                        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                        ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                        //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                    }



                    /////
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ////// начало запись в файл

                }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }


    ///////// TODO УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ
    protected Long ВставкаДанныхЧерезКонтейнерУниверсальнаяЧерезContentResolver(String ТаблицаКудаВставляем,
                                                                                ContentValues КонтейнерДляВставки,
                                                                                String ИмяТаблицыОтАндройда_Локальноая,
                                                                                String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                boolean ФлагОбновлятьДатуВерсииДанных,
                                                                                int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                                boolean СинхронизациюВизуализировать,
                                                                                Context context,
                                                                                @NotNull CompletionService МенеджерПотоков,
                                                                                @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                                Integer СколькоСтрочекJSON,
                                                                                Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        ///////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ
        Long Результат_ВставкиДанных = 0l;
        //
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставка;
        //
        class_grud_sql_operationsВставка = new Class_GRUD_SQL_Operations(this.context);
///

/////
        try {
            //

            try {

                // TODO: 06.09.2021 параметры для вствки
                class_grud_sql_operationsВставка.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                ////



                // TODO: 06.09.2021 контейнер для вставки
                // class_grud_sql_operationsВставка.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставки);


                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанных= (Long)  class_grud_sql_operationsВставка.
                        new InsertData(this.context).insertdata(class_grud_sql_operationsВставка. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        КонтейнерДляВставки,
                        МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);

                Log.d(this.getClass().getName(), "Результат_ВставкиДанных   " + Результат_ВставкиДанных);
//////////////////////todo old



                //////
                if (Результат_ВставкиДанных > 0) {
                    ////успешная вставка данных
                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ



                    ///
                    КонтейнерДляВставки.clear();
                    ////
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанных   " + Результат_ВставкиДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице "
                            +              ИндексТекущейОперацииJSONДляВизуальнойОбработки  +  "  СколькоСтрочекJSON " +СколькоСтрочекJSON);

                    //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                    ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                    //////TODO ВАЖВАНО ПЕРВЫЙ ЗАПУСК БАЗЫ ВСЕГОДА FALSE ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС

                }



                /////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }

            ///////////TODO визуализация даных по строчно из НИЖНЕГО ПОТОКА ПОДМИНИМАЕМСЯ НА ВЕРХ ОПЕРЦИЯ ВСТАВКА

            // TODO: 07.09.2021 ССЫЛКА НА КЛАСС КОТОРЫЙ ЗАНИМАЕТЬСЯ ОТПОБРАЖЕНИЕМ ВИЗУАЛЬНЫЙ СИЕНХОООНИАХЦИИ   ВСТАВКА


  /*          //
        new Class_Visible_Processing_Async(contextСозданиеБАзы).ГенерируемПРОЦЕНТЫДляAsync(
                    ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                    СинхронизациюВизуализировать,
                    (Activity) ActivityДляСинхронизацииОбмена,
                ИндексТекущейОперацииJSONДляВизуальнойОбработки,
                СколькоСтрочекJSON,ФиналПроценты);
*/








            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ////// начало запись в файл

        }
        ////TODO метод вставки
        return Результат_ВставкиДанных;
    }

    //////TODO метод пребразует цифры из цикла в проценты


    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    protected Integer ОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                  ContentValues КонтейнерДляОбновления,
                                                                  String UUIDДляСостыковПриОбновления,
                                                                  int ДляСинхронизацииОбщееКоличествоСколькоСтрочекJSON,
                                                                  boolean СинхронизациюВизуализировать,
                                                                  Context КонтекстСинхроДляКонтроллера,
                                                                  String ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID,
                                                                  CompletionService МенеджерПотоков,
                                                                  SQLiteDatabase getБазаДанныхДЛяОперацийВнутри,
                                                                  Integer СколькоСтрочекJSON,
                                                                  Integer ИндексТекущейОперацииJSONДляВизуальнойОбработки)
            throws ExecutionException, InterruptedException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Integer Результат_ОбновлениеДанных = 0;
        int Результат_ПриписиИзменнийВерсииДанных = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsОбновление;
        try {
            class_grud_sql_operationsОбновление=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ИндификаторЧерезЧегоОбнолвяемсяUUIDИлиID);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
            class_grud_sql_operationsОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
            class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновления);
                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ
            Результат_ОбновлениеДанных= (Integer)  class_grud_sql_operationsОбновление.
                    new UpdateData(context).updatedata(class_grud_sql_operationsОбновление. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    class_grud_sql_operationsОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    МенеджерПотоков,getБазаДанныхДЛяОперацийВнутри);
            Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                        if (Результат_ОбновлениеДанных > 0) {
                            Log.d(this.getClass().getName(), " Результат_ВставкиДанных   "
                                    + Результат_ОбновлениеДанных + "   PUBLIC_CONTENT.СколькоСтрочекJSONПоКонкретнойТаблице " + ИндексТекущейОперацииJSONДляВизуальнойОбработки+
                                     "  СколькоСтрочекJSON " +СколькоСтрочекJSON);
                        }
            Log.d(this.getClass().getName(), "ИндексТекущейОперацииJSONДляВизуальнойОбработки   " +ИндексТекущейОперацииJSONДляВизуальнойОбработки+ " СколькоСтрочекJSON " +СколькоСтрочекJSON);
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ОбновлениеДанных;
    }




    ///-------TODO ДАННЫЕЙ МЕТОД ОБНОЛДЯЕТЬ ДАННЫЕ ТОЛЬКО ВЕРСИИ ДАННЫХ ИЗМЕНЯЕТЬ ПРОТО  ДАТЫ В MODIFICATION CLIENT



    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ






    // TODO: 11.08.2021  запись изменения ТОЛЬКО ДЛЯ ЧАТА СПИМИНЕНИЕМ ВЕРСИИ ДАННЫХ


    ///////// TODO  КОНЕЦ УНИВЕРСАЛЬНЫЙ МЕТОД ВСТАВКИ ДАННЫХ


    /////////TODO КОНТЕЙНЕР ЛОКАЛЬНОГО ОБНОВЛЕНИЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаКудаОбновляем,
                                                                        @NonNull ContentValues КонтейнерДляЛокальногоОбновления,
                                                                         Long UUIDДляСостыковПриОбновления,
                                                                         String ФлагЧерезЧегоОбновляетьсяIDилиUUID) throws ExecutionException, InterruptedException, TimeoutException {
        //////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_ЛокальногоОбновлениеДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");

        String ОшибкаТекущегоМетода;


        Class_GRUD_SQL_Operations class_grud_sql_operationsЛокальноеОбновление;

            try {



                    class_grud_sql_operationsЛокальноеОбновление=new Class_GRUD_SQL_Operations(context);


                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ФлагЧерезЧегоОбновляетьсяIDилиUUID);
                    /////

                    ///

                    class_grud_sql_operationsЛокальноеОбновление.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);

//

                    class_grud_sql_operationsЛокальноеОбновление.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");

                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ ЛОКАЛЬНОГОМ ОБНОВЛЕНИЯ

                    class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляЛокальногоОбновления);


                            ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ



                    Результат_ЛокальногоОбновлениеДанных= (Integer) class_grud_sql_operationsЛокальноеОбновление.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЛокальноеОбновление. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsЛокальноеОбновление.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " + Результат_ЛокальногоОбновлениеДанных);


                    if(Результат_ЛокальногоОбновлениеДанных==null){
                        ///
                        Результат_ЛокальногоОбновлениеДанных=0;
                    }





                    if (Результат_ЛокальногоОбновлениеДанных > 0) {
                        ///todo первое сохранение транзакции
                        //ССылкаНаСозданнуюБазу.
                        Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);
                    }

                    Log.d(this.getClass().getName(), "Результат_ЛокальногоОбновлениеДанных   " +Результат_ЛокальногоОбновлениеДанных);


                    ///


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                ОшибкаТекущегоМетода = e.toString();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }


        //   publishProgress(Результат_ПриписиИзменнийВерсииДанных);

        return Результат_ЛокальногоОбновлениеДанных;
    }
    //////метод записывает данные о времени в таблицу модификации ерсии данных


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиНовогоСотрудника)  {


        Long Результат_ВставкиДанныхТолькоДляСотрудникаНового = 0l;
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаСотрудника;
            try {
                Long ВытаскиваемПОвышенуюВерисюДанныхВнутриПоля_CurrenTable=КонтейнерДляВставкиНовогоСотрудника.getAsLong("current_table");
                    class_grud_sql_operationsВставкаСотрудника=new Class_GRUD_SQL_Operations(context);
                    /////TODO параменты для вставки
                    class_grud_sql_operationsВставкаСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
                    /////TODO контейнер для вставки
                    class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиНовогоСотрудника);
                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхТолькоДляСотрудникаНового= (Long)  class_grud_sql_operationsВставкаСотрудника.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    Log.d(this.getClass().getName(), " Результат_ВставкиДанныхТолькоДляСотрудникаНового  "+Результат_ВставкиДанныхТолькоДляСотрудникаНового);
                    if(Результат_ВставкиДанныхТолькоДляСотрудникаНового==null){
                        Результат_ВставкиДанныхТолькоДляСотрудникаНового=0l;
                    }
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_ВставкиДанныхТолькоДляСотрудникаНового;
    }


    // TODO: 10.07.2021  для чата


















  public Long ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНСообщенияДЛЯЧата(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиДляЧата,
                                                                            String ИмяТаблицыОтАндройда_Локальноая,
                                                                            String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                            boolean ФлагОбновлятьДатуВерсииДанных) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

      Long Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата = 0l;
        /////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" Вставка Данных Через Контейнер Универсальная");

        ////TOdo начинаем таранзакццию
      Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаЧата;



            try {
                try{

                    ////
                    class_grud_sql_operationsВставкаЧата=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАМЕТРЫ ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА

                    class_grud_sql_operationsВставкаЧата.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);



                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ ДАННЫХ ЧАТА


                    class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиДляЧата);

                    // TODO: 12.10.2021  Ссылка Менеджер Потоков



                    // TODO: 06.09.2021 САМА ВСТАВКА ДЛЯ ЧАТА ЧАТА

                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата= (Long)  class_grud_sql_operationsВставкаЧата.
                            new InsertData(context).insertdata(class_grud_sql_operationsВставкаЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаЧата.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//

                    Log.d(this.getClass().getName(), "Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);

         /*                  ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }else {

                            Результат_ВставкиДанныхТолькоДляСотрудникаНового =     ССылкаНаСозданнуюБазу.insert(ТаблицаКудаВставляем,null,КонтейнерДляВставки);

                        }

*/

                    //////////
                    if (Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата   " + Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата);
                    }
                    ///
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                }
                //////



        /*        //метод анализируем стоит ли вставлять дату сейчас в таблицу модификацию версия данных локального
                ////////вторая часть операции после успешной вствки изменяем данные на дабоавляем дату в таблицу модификаци  клиент
                //////ДЛЯ ПОНИМАНИЯ ЕСЛИ ЭТО НУЛЕВОЙ ЗАПУСК ТО НЕ НАДО В ТАБЛИЦУК МОДИФИКАФЕН КЛИЕНТ ВСТАЯЛТЬ ДАТУ СЕЙЧАС
                if (ФлагОбновлятьДатуВерсииДанных == true && Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата > 0) {/////ПРИ УКАЗАНОЙ ДАТЕ ПОДТВЕРЖДАТЬ ВРЕМЯ НЕ НАДО
                    /////запускаем втроую транзакцию


                    ///todo ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    ///ПослеУспешнойОперациии записать в табблицу версии данных на клиенте
                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    Class_GRUD_SQL_Operations  classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
                    // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                    //////
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата=new Class_GRUD_SQL_Operations(contextСозданиеБАзы);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ИмяТаблицыОтАндройда_Локальноая);
                    ///
                    classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");
                    ///

                    ///TODO РЕЗУЛЬТА изменения версии данных
                       Результат_ПриписиИзменнийВерсииДанных= (Integer) classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата.
                            new ChangesVesionData(contextСозданиеБАзы).changesvesiondata(classGrudSqlOperationsВставкиДанныхПриСозданииНСообщенияДЛЯЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций);
//

                    ////
                    if(Результат_ПриписиИзменнийВерсииДанных==null){
                        ////
                        Результат_ПриписиИзменнийВерсииДанных=0;
                    }
                    // TODO: 03.09.2021



                    ///todo  конец  ДАННЫЙ КОД ИЗМЕНЯЕТ ВЕРИСЮ ДАННЫХ

                    if (Результат_ПриписиИзменнийВерсииДанных > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.w(this.getClass().getName(), "  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }else{

                        Log.e(this.getClass().getName(), " ОШибка  Результат_ПриписиИзменнийВерсииДанных  ПОСЛЕ ОПЕРАЦИИ ПОВЫШАЕМ ВЕРИСЮ ДАННЫХ....   " +Результат_ПриписиИзменнийВерсииДанных );
                    }


                }*/


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///

            }

        //// todo get ASYNtASK
        return Результат_ВставкиДанныхПриСозданииНСообщенияДЛЯЧата;
    }











    // TODO: 10.07.2021  для чата


    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Long ВставкаДанныхЧерезКонтейнерОрганизацияДляТекущегоСотрудникаУниверсальная(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника,
                                                                                         String ИмяТаблицыОтАндройда_Локальноая,
                                                                                         String ПолученнаяДатаДляПониманияДатуСейчасВставляетьИлиНет,
                                                                                         boolean ФлагОбновлятьДатуВерсииДанных, int ПубличныйIDДляорганизацции,
                                                                                         String ДатаДляОбновлениеОргназации) throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

          Long Результат_ОбновленияДанныхОрганизация = 0l;
        ///
        Long Результат_ВставкиДанныхОрганизация = 0l;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;

        Class_GRUD_SQL_Operations class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации;

            try {
                //

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                    // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);

                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","user_update");


                    //
                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",ПубличныйIDДляорганизацции);


//

                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                    // TODO: 06.09.2021 КОНТЕЙНЕР ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ

                    class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиОрганизацияДляТекущегоСотрудника);


                    // TODO: 06.09.2021 САМА ОПРАЦЙИЯ ОБНОВЛЕНИЯ ПЕРЫЙ ШАГ ЕСЛИ ВЫЙДЕТ, ТО ПОСЛЕДНИЙ

                // TODO: 12.10.2021  Ссылка Менеджер Потоков




                    ///TODO РЕЗУЛЬТА изменения версии данных
                    Результат_ОбновленияДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                            new UpdateData(context).updatedata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                            Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);

           /*                ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхОрганизация  =             ПриписиИзменнийВерсииДанных.
                                update(ССылкаНаСозданнуюБазу,КонтейнерДляВставки, "user_update=?",
                                        new String[]{String.valueOf(ПубличныйIDДляорганизацции)});
                    }*/


                    ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                    Log.d(this.getClass().getName(), " Результат_ОбновленияДанныхОрганизация   " + Результат_ОбновленияДанныхОрганизация);


                    ///////TODO  ЕСЛИ ОПЕРАЦИЯ ОБНОВЛЕНИЯ НЕ ПРОШЛА ТО НИЖЕ ПРОИЗВОДИМ ВСТАВКИ УЖЕ ДАННЫХ -- ЭТО НУЖНО КОГДА ПЕРВЫЙ ЗАПЦУСК ПРОГРАММЫ И НЕТ ЫВООБЩЕ ДАННЫХ В БАЗЕ И ПРОИЗВХОДИТ РАЗНИЦА ВСТАВКА ИЛИ ОБНОВЛЕНИЕ
                    if (Результат_ОбновленияДанныхОрганизация > 0) {
                        ////успешная вставка данных
                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    } else {




                        ///TODO ВТОРАЯ ЧАТЬ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ ПРОИХВОДИМ УЖЕ ВСТАВКУ ПОСЛЕ НЕ УДАЧНОГО ОБНВЛЕНИЯ





                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации=new Class_GRUD_SQL_Operations(context);

                        // TODO: 06.09.2021 ПАРАРМЕТРЫ ДЛЯ ВСТАВКИ НОВОЙ ОРГАНИЗАЦИИ


                        class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);




                        Результат_ВставкиДанныхОрганизация= (Long)  class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.
                                new InsertData(context).insertdata(class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                class_grud_sql_operationsВставкаИлиОбвновлениеНовойОрганизации.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                        Log.d(this.getClass().getName(), "Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
/*
                               ВставкиДанных=new    ();
                           ВставкиДанных.setTables(ТаблицаКудаВставляем);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ВставкиДанныхОрганизация =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                        }
*/

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);
                    }


                    if (Результат_ВставкиДанныхОрганизация > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ


                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    }


                //////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу

                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //////

            }

        return Результат_ВставкиДанныхОрганизация;
    }




















// TODO: 10.07.2021  только для чата






    /////////КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера(String ТаблицаКудаВставляем, ContentValues КонтейнерДляВставкиПубличныйID)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        ///////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        long Результат_ВставкиДанныхОрганизация = 0;
        ///////
        int Результат_ПриписиИзменнийВерсииДанных = 0;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолученныйПубличныйID;



        try {
            //////
            class_grud_sql_operationsПолученныйПубличныйID=new Class_GRUD_SQL_Operations(context);


            // TODO: 06.09.2021  ПАРАМЕТРЫ ВСТАВКИ ПУБЛИЧНОГО  ID
            class_grud_sql_operationsПолученныйПубличныйID.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);
            /////////////



            // TODO: 06.09.2021  КОНТЕЙНЕР  ВСТАВКИ ПУБЛИЧНОГО  ID

                    ///////
            class_grud_sql_operationsПолученныйПубличныйID.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиПубличныйID);


            // TODO: 06.09.2021  сама вствка данных
            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            ///TODO РЕЗУЛЬТА изменения версии данных
            Результат_ВставкиДанныхОрганизация= (Long)  class_grud_sql_operationsПолученныйПубличныйID.
                    new InsertData(context).insertdata(class_grud_sql_operationsПолученныйПубличныйID. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    class_grud_sql_operationsПолученныйПубличныйID.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                    Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


            Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

/*

                           ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхОрганизация =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                    }
*/



                    if (Результат_ВставкиДанныхОрганизация > 0) {

                        ///TODO ПЕРОВЕ ТРАНЗАКЦИЯ ВСТАВКИ ДАННЫХ
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхОрганизация   " + Результат_ВставкиДанныхОрганизация);

                    }


            //////
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            ///метод запись ошибок в таблицу

            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //////
        }
        return Результат_ВставкиДанныхОрганизация;
    }





































    /////////TODO  ОБНОВЛЕНИЕ КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ
    Long ОбновлениеДанныхЧерезКонтейнерТолькоПриСозданииСУниверсальная(String ТаблицаКудаОбновляем,
                                                                                      ContentValues КонтейнерДляОбновленияСозданииНовогоСотрудника,
                                                                                      String UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        long Результат_ОбновлениеДанныхОбновлениеСозданииНового = 0;
        ///////
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ОбновлениеДанныхЧерезКонтейнерУниверсальная ");
//
Class_GRUD_SQL_Operations class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника;
            try {
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника=new Class_GRUD_SQL_Operations(context);


// TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаОбновляем);
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","uuid");
                ///
                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                ///
//

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                // TODO: 06.09.2021  КОНТЕ  НЕР ДЛЯ ОБНОВЛЕНИЯ

                class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляОбновленияСозданииНовогоСотрудника);


                // TODO: 12.10.2021  Ссылка Менеджер Потоков


                // TODO: 06.09.2021 CАМО ОБНОВЛЕНИЕ


                Результат_ОбновлениеДанныхОбновлениеСозданииНового= (Long)  class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.
                        new UpdateData(context).updatedata(class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        class_grud_sql_operationsОбвовлениеСозданииНовогоСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ОбновлениеДанныхОбновлениеСозданииНового   " + Результат_ОбновлениеДанныхОбновлениеСозданииНового);
/*
                           ПриписиИзменнийВерсииДанных=new    ();
                       ПриписиИзменнийВерсииДанных.setTables(ТаблицаКудаОбновляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =             ПриписиИзменнийВерсииДанных.
                                update(contextСозданиеБАзы, КонтейнерДляОбновления, "uuid= ?",
                                        new String[]{UUIDДляСостыковПриОбновления});
                    }else{

                        Результат_ОбновлениеДанныхОбновлениеСозданииНового  =        ССылкаНаСозданнуюБазу.update(ТаблицаКудаОбновляем, КонтейнерДляОбновления, "uuid= ?",
                                new String[]{UUIDДляСостыковПриОбновления});

                    }*/


                    Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);




                    ///////
                    if (Результат_ОбновлениеДанныхОбновлениеСозданииНового > 0) {
                        ///TODO ПЕРВАЯ ТРАНЗАКЦИЯ
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), "   Результат_ОбновлениеДанныхОбновлениеСозданииНового "+Результат_ОбновлениеДанныхОбновлениеСозданииНового);
                    }


            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            ////
        return Результат_ОбновлениеДанныхОбновлениеСозданииНового;
    }
































    ///////// todo КОНТЕЙНЕР ВСТВКИ ДАННЫХ УНИВЕРСАЛЬНЫЙ ТОЛЬКО ДЛЯ ЗАПИСИ ОШИБКИ
    Long ВставкаДанныхЧерезКонтейнерУниверсальнаяТолькоДляЗаписиОшибки(String ТаблицаКудаВставляем,
                                                                       ContentValues КонтейнерДляВставкиЗаписиОшибки)
            throws ExecutionException, InterruptedException, TimeoutException {
        ////////////////////////////////////////////////////////////////////////

        long Результат_ВставкиДанныхДляЗаписиОшибки = 0;
        ///
        int Результат_ПриписиИзменнийВерсииДанных = 0;


        System.out.println(" ВставкаДанныхЧерезКонтейнерУниверсальная");

        Class_GRUD_SQL_Operations class_grud_sql_operationsДляВставкиОшибок=new Class_GRUD_SQL_Operations(context);


            try {
                // TODO: 06.09.2021  ПАРАСМЕТИРЫ ВСТАВКИ ОШИБКИ

                class_grud_sql_operationsДляВставкиОшибок.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКудаВставляем);


                // TODO: 06.09.2021  КОНТЕЙНЕР ВСТАВКИ ОШИБКИ
                class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиЗаписиОшибки);


                // TODO: 06.09.2021 сама операция ВСТАВКИ ОШИБКИ
                // TODO: 12.10.2021  Ссылка Менеджер Потоков



                ///TODO РЕЗУЛЬТА изменения версии данных
                Результат_ВставкиДанныхДляЗаписиОшибки= (Long)  class_grud_sql_operationsДляВставкиОшибок.
                        new InsertData(context).insertdata(class_grud_sql_operationsДляВставкиОшибок. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        class_grud_sql_operationsДляВставкиОшибок.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                        Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "Результат_ВставкиДанныхДляЗаписиОшибки   " + Результат_ВставкиДанныхДляЗаписиОшибки);
              /*      ///////

                           ВставкиДанных=new    ();
                       ВставкиДанных.setTables(ТаблицаКудаВставляем);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        Результат_ВставкиДанныхДляЗаписиОшибки =             ВставкиДанных.insert(ССылкаНаСозданнуюБазу, КонтейнерДляВставки);
                    }

*/

                    if (Результат_ВставкиДанныхДляЗаписиОшибки > 0) {
                        // ССылкаНаСозданнуюБазу.

                        Log.d(this.getClass().getName(), " Результат_ВставкиДанныхДляЗаписиОшибки  " + Результат_ВставкиДанныхДляЗаписиОшибки);

                    }
                //////
                /////НАЗВАНИЕ ПОТОКА
                Log.i(this.getClass().getName(), "НАЗВАНИЕ ПОТОКА В aSYNSTASK " + Thread.currentThread().getName().toUpperCase());
                /////
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ВставкиДанныхДляЗаписиОшибки;
    }


    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеДанныхЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                             String ЧерезКакоеПолеУдлаяемФлаг,
                                                             Long UUIDДляСостыковПриОбновления,
                                                             String ПолеКудаИзменятьСтатус,
                                                             String ЗапоЗначенияУсменыСтатуса) throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_УдалениеДанных = 0;
Class_GRUD_SQL_Operations classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная;
            try {
                //
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА ПолеКудаИзменятьСтатус  "
                        + ПолеКудаИзменятьСтатус + " ЗапоЗначенияУсменыСтатуса " + ЗапоЗначенияУсменыСтатуса);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная = new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная
                        . concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","="); //или =   или <   >
                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций
                        .put(ПолеКудаИзменятьСтатус, ЗапоЗначенияУсменыСтатуса);//todo status_write   status_send
                ///
                Log.w(this.getClass().getName(), "РЕЗУЛЬТАТ УДАЛДЕНИЕ ОДНОГО СОТРУДНИКА РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника  "
                        + " ТаблицаОткудаУдлаяемЗапись " + ТаблицаОткудаУдлаяемЗапись);
                // TODO: 30.01.2022

                // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияДАныхЧата = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(ТаблицаОткудаУдлаяемЗапись,context,getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

                classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("current_table",
                        РезультатУвеличинаяВерсияДАныхЧата);
                ///TODO РЕЗУЛЬТА ОБНОВЛЕНИЯ
                Результат_УдалениеДанных = (Integer) classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                        new UpdateData(context).updatedata(classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        classGrudSqlOperationsУдалениеДанныхЧерезКонтейнерУниверсальная.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс
                                .getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "Результат_УдалениеДанных " + Результат_УдалениеДанных);
                } catch (Exception e) {///////ошибки
                    e.printStackTrace();
                    Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Результат_УдалениеДанных;
    }






    /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ
    public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                String ПолучениеЗначениеСтолбикUUID,
                                                Context КонтексДляЛокальногоОбновления,
                                                String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
        Integer результатОбновлениеЧерезКонтрейнер = 0;
        try {
            ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
            результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                    Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
            Log.d(this.getClass().getName(),
                    "  результатОбновлениеЧерезКонтрейнер[0] " + результатОбновлениеЧерезКонтрейнер);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

    }




















    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                          String ЧерезКакоеПолеУдлаяемФлаг,
                                                                          Long UUIDДляСостыковПриОбновления)
            throws ExecutionException,
            InterruptedException, TimeoutException {
        Integer Результат_ОбновлениеДанных = 0;
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;
        // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
        Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
        // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕ
            try {
                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеСнаДанных",ЧерезКакоеПолеУдлаяемФлаг);
                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагСнаДанных",UUIDДляСостыковПриОбновления);
                // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДанныхПриСозданеииИзШаблонаСотрудника=new Class_GRUD_SQL_Operations(context);


                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияПриудалениеСотрудника = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(ТаблицаОткудаУдлаяемЗапись,context,getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияПриудалениеСотрудника  " + РезультатУвеличинаяВерсияПриудалениеСотрудника);

                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияПриудалениеСотрудника);
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);
                Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления );
                    if (UUIDДляСостыковПриОбновления > 0) {
                        Результат_ОбновлениеДанных= (Integer)  classGrudSqlOperationsДляУдаленияСотрудника.
                                new SleepData(context).sleepdata(classGrudSqlOperationsДляУдаленияСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                        Log.d(this.getClass().getName(), "Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
                    }
                    Log.d(this.getClass().getName(), " Результат_ОбновлениеДанных   " + Результат_ОбновлениеДанных);
            } catch (Exception e) {///////ошибки
                e.printStackTrace();
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        return Результат_ОбновлениеДанных;
    }
























    /////////TODO КОНТЕЙНЕР УДАЛЕНИЕ СОТРУДНИКА ИЗ ТАБЕЛЯ  ДАННЫХ УНИВЕРСАЛЬНЫЙ
    public Integer УдалениеТолькоШАблонЧерезКонтейнерУниверсальная(String ТаблицаОткудаУдлаяемЗапись,
                                                                   String ЧерезКакоеПолеУдлаяемФлаг,
                                                                   String UUIDДляСостыковПриОбновления) throws ExecutionException,
            InterruptedException, TimeoutException {
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////ПОПЫТКА ПОДКЛЮЧЧЕНИЕ К ИНТРЕНТУ

        Integer Результат_УдалениеТолькоШАблон = 0;
        ////////
        Integer Результат_ПриписиИзменнийВерсииДанных = 0;

        System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

        ///todo начинаем транзакцию

            try {
                ///
                /////
                System.out.println(" УдалениеДанныхЧерезКонтейнерУниверсальная ");

                // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                Class_GRUD_SQL_Operations  classGrudSqlOperationsДляУдаленияСотрудника;
                // TODO: 30.08.2021    КОД ОБНОВЛЕНИЕ   ДАННЫХ   ЧЕРЕЗ
                //////

                    // TODO: 03.09.2021  получение ПО НОВОМУ ДВИЖКУ
                    classGrudSqlOperationsДляУдаленияСотрудника=new Class_GRUD_SQL_Operations(context);


                    ///todo ПРИ УДАЛЕНИ И СОТРУДНИКА ОЧИЩАЕМ ПОЛЯ ЧТОБЫ НЕБЛОЫ СВЯКЗКИ С ТАБЕЛЕМ

                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ УДАЛЕНИЯ

                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОткудаУдлаяемЗапись);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением",ЧерезКакоеПолеУдлаяемФлаг);
                    /////////
                    classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления",UUIDДляСостыковПриОбновления);
                    /////////

                classGrudSqlOperationsДляУдаленияСотрудника.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","=");
                /////////



                    // TODO: 06.09.2021  КОНТЕЙНЕР ДЛЯ УДАЛЕНИЯ
                    ContentValues АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная=new ContentValues();
                    //
                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.put("status_send", "Удаленная");///ПОКА НЕ ОТКЛЮЧИЛИ
                    //////
                    ////TODO ДАТА
                    String СгенерированованныйДатаВремениСейчаcДляУдаления=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();

                    АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("date_update", СгенерированованныйДатаВремениСейчаcДляУдаления);///ПОКА НЕ ОТКЛЮЧИЛИ
                    ///
               // АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.putNull("id");///ПОКА НЕ ОТКЛЮЧИЛИ


                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличинаяВерсияДАныхЧата = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(ТаблицаОткудаУдлаяемЗапись,context,getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

                //TODO  конец курант ча
                АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);

                ///

                    classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная);


                    Log.d(this.getClass().getName(), "UUIDДляСостыковПриОбновления   " +UUIDДляСостыковПриОбновления  + " РезультатУвеличинаяВерсияДАныхЧата " +РезультатУвеличинаяВерсияДАныхЧата );


                    if (Long.parseLong(UUIDДляСостыковПриОбновления) > 0) {


                        // TODO: 06.09.2021 сама операция обновления через новый движок  удаление пустого табеля
                        // TODO: 12.10.2021  Ссылка Менеджер Потоков



                        ///TODO РЕЗУЛЬТА изменения версии данных
                        Результат_УдалениеТолькоШАблон= (Integer)   classGrudSqlOperationsДляУдаленияСотрудника.
                                new UpdateData(context).updatedata(classGrudSqlOperationsДляУдаленияСотрудника. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                classGrudSqlOperationsДляУдаленияСотрудника.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                        Log.d(this.getClass().getName(), "Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
//////////////////////////////////////////////////////////////////////

                        if(Результат_УдалениеТолькоШАблон==null){

                            //
                            Результат_УдалениеТолькоШАблон=0;
                        }



             /*                  ПриписиИзменнийВерсииДанных=new    ();
                           ПриписиИзменнийВерсииДанных.setTables(ТаблицаОткудаУдлаяемЗапись);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            Результат_ОбновлениеДанных  =             ПриписиИзменнийВерсииДанных.
                                    update(ССылкаНаСозданнуюБазу,АдаптерУстанавливаемФлагНазАписьЧтоОнаУдаленная,
                                            ЧерезКакоеПолеУдлаяемФлаг + "= ?", new String[]{String.valueOf(UUIDДляСостыковПриОбновления)});
                        }
*/

                    }
                    Log.d(this.getClass().getName(), " Результат_УдалениеТолькоШАблон   " + Результат_УдалениеТолькоШАблон);
                } catch (Exception e) {///////ошибки
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e.toString() + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        return Результат_УдалениеТолькоШАблон;
    }






///todo записываем выбраную  ОРГАНИЗАЦИЮ В БАЗУ

    public Integer МетодКоторыйЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobile(String ПередаваемыйРежимИнтрентета,
                                                                               Context КонтекстWIFI,
                                                                               String Таблица,
                                                                               String Поля) {
/////todo КОД ЗАПОЛЕНЕИЯ ДАННЫМИ В СПИНЕР ЦФО ДЕПАРТАМЕНТ МЕСЯЦ
        Integer РезультатОбновлениеЧерезКонтрейнер = 0;
//
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil;

                try {

                    Log.d(this.getClass().getName(), " ПередаваемыйРежимИнтрентета  " + ПередаваемыйРежимИнтрентета);

                    ////TODO ОБНУЛЯЕМ КАКУЮ ОРГАНИЗАЦИЮ ВЫБРАЛИ ОЧИЩАЕМ ВСТАВЛЕМ ППАРАМЕТР WIF-FI


                    ///////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);


                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(context);
                    ///
                    class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


                    // TODO: 12.10.2021  Ссылка Менеджер Потоков

                    PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (context);


                    ///////
                    SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                            new GetаFreeData(context).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    if(Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount()>0){
                        //
                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

                        /////
                        ID =         Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getInt(0);


                        Log.d(this.getClass().getName(), " ID  " + ID);
                    }



                    // TODO: 06.09.2021 ВТОРОЕ ДЕЙСТИВЕ ПОСЛЕ ОЧИСТКИ ВСТАВЛЯЕМ  ПОЛУЧЕНЫЙ СТАТУС   ДЛЯ WIFI MOBILE

                    ///////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil=new Class_GRUD_SQL_Operations(context);


                    // TODO: 06.09.2021  ПАРАМЕТРЫ ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ

                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",Таблица);

                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("Флаг_ЧерезКакоеПолеОбновлением","id");
                    ////////
                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗначениеФлагОбновления", ID);

//

                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ЗнакФлагОбновления","="); //или =   или <   >

                    // TODO: 06.09.2021  КОНТЕРЙНЕР ДЛЯ ПЕРВОГО ДЕЙСТИЯ ОЧИЩЕНИЯ

                    ContentValues ВставляемВБАзуВыбранныйРежимИнтренета=new ContentValues();

            ВставляемВБАзуВыбранныйРежимИнтренета = new ContentValues();





                    ВставляемВБАзуВыбранныйРежимИнтренета.put("id", ID);

                    ВставляемВБАзуВыбранныйРежимИнтренета.put(Поля, ПередаваемыйРежимИнтрентета);


                    ////TODO ДАТА
                    String СгенерированованныйДатаДляДаннойОперации=     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();


                    ВставляемВБАзуВыбранныйРежимИнтренета.put("date_update", СгенерированованныйДатаДляДаннойОперации);

                    class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(ВставляемВБАзуВыбранныйРежимИнтренета);


                    // TODO: 06.09.2021  САМА ОПАРАЦИИЯ ОБНОВЛЕНИЯ СТАТУ WIFI ИЛИ MOBILE
                    // TODO: 12.10.2021  Ссылка Менеджер Потоков


                    ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


                    РезультатОбновлениеЧерезКонтрейнер= (Integer)  class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.
                            new UpdateData(context).updatedata(class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            class_grud_sql_operationsЗаписываемВыбранныйРежимИнтрернетаWifiИлиMobil.contentValuesДляSQLBuilder_Для_GRUD_Операций ,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                    Log.d(this.getClass().getName(), "РезультатОбновлениеЧерезКонтрейнер   " + РезультатОбновлениеЧерезКонтрейнер);

                    ///поймать ошибку
                } catch (Exception e) {
                    //  Block of code to handle errors
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ///////
                }

        return РезультатОбновлениеЧерезКонтрейнер;
    }
























    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеИмяСистемыДляСменыПользователя(Context КонтекстДляРежимаИнтрента) {
        //
        String ИмяУспешноВошедегоПользователья = new String();
        ///

        SQLiteCursor Курсор_ПолучениеИмяСистемы = null;
        //
        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучениеИмяСистемы;
        ////
        try {

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsПолучениеИмяСистемы=new Class_GRUD_SQL_Operations(context);

            ///
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","success_users");
            //
            /*        class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=?    AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
                    ///"_id > ?   AND _id< ?"
                    //////
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
            ////
           /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ПолучениеИмяСистемы= (SQLiteCursor)  class_grud_sql_operationsПолучениеИмяСистемы.
                    new GetData(context).getdata(class_grud_sql_operationsПолучениеИмяСистемы. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  +Курсор_ПолучениеИмяСистемы);


/*

            Cursor Курсор_ЗагружаетДанныеПриСозданииТабеля = new Class_MODEL_synchronized(contextСозданиеБАзы).КурсорУниверсальныйДляБазыДанных("SuccessLogin", new String[]
                            {"success_users"}, null,
                    null, null, null, "date_update", null);///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
*/

            //////todo ПОЛУЧЕНИЕ ДАННЫХ
            if (Курсор_ПолучениеИмяСистемы.getCount() > 0) {
                //////
                Курсор_ПолучениеИмяСистемы.moveToFirst();

                //
                ИмяУспешноВошедегоПользователья = Курсор_ПолучениеИмяСистемы.getString(0);
                /////
                Log.d(this.getClass().getName(), "ИмяУспешноВошедегоПользователья  " + ИмяУспешноВошедегоПользователья);

            }

            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }
      return  ИмяУспешноВошедегоПользователья;

    }





















    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеля(int IDЧьиДанныеДляСотрудников, Long полученнаяUUIDНазванияОрганизации, String finalУниверсальноеИмяТабеля, Context контекстLIstView,
                                                   int МЕсяцДляКурсораТабелей, int ГодДляКурсораТабелей, String ЦифровоеИмяНовгоТабеля) {
        ////


        SQLiteCursor Курсор_ДляЗагрузкиСотрудниковНепостредственнов = null;

         Class_GRUD_SQL_Operations class_grud_sql_operationsСотрудниковListViewТабел;

        try {
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            class_grud_sql_operationsСотрудниковListViewТабел=new Class_GRUD_SQL_Operations(контекстLIstView);

            ///
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name,uuid,BirthDate,snils,_id,status_carried_out");
            //
            ///////
            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update= ?  AND  month_tabels=?  AND year_tabels=?" +
                    " AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL");
            ////
            // TODO: 06.09.2021  значнеия для where

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",IDЧьиДанныеДляСотрудников);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",МЕсяцДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",ГодДляКурсораТабелей);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4", finalУниверсальноеИмяТабеля.trim());

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска5",полученнаяUUIDНазванияОрганизации);

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска6","Удаленная");

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска7",ЦифровоеИмяНовгоТабеля);

            // TODO: 06.09.2021 УСЛОВИЕ ДЛЯ СОРТИРОВКИ

            class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");

            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ДляЗагрузкиСотрудниковНепостредственнов= (SQLiteCursor)  class_grud_sql_operationsСотрудниковListViewТабел.
                    new GetData(контекстLIstView).getdata(class_grud_sql_operationsСотрудниковListViewТабел. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  );

            ////


/*
            Курсор_ДляЗагрузкиСотрудниковНепостредственнов = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"name,uuid,BirthDate,snils,_id,status_carried_out"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    " user_update= ?  AND  month_tabels=?  AND year_tabels=? AND nametabel=? AND organizations=? AND status_send!=?  AND nametabel_typename=? AND name IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{String.valueOf(IDЧьиДанныеДляСотрудников), String.valueOf(МЕсяцДляКурсораТабелей), String.valueOf(ГодДляКурсораТабелей),
                            finalУниверсальноеИмяТабеля.trim(), String.valueOf(полученнаяUUIDНазванияОрганизации), "Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля)}, null, null, "date_update DESC", null);



*/




            //////todo полученный
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount() > 0) {
                ////////
                Курсор_ДляЗагрузкиСотрудниковНепостредственнов.moveToFirst();




                Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственнов.getCount());
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE


        return Курсор_ДляЗагрузкиСотрудниковНепостредственнов;

    }












// TODO: 12.03.2021 Метод который получает данные при возврате из ШАБЛОНОВ

/*    //////todo метод ДЛЯ ТАБЕЛЯ  ЗАГРУЖАЕТ СОТРУДНИКОВ В КОНТЕРТНЫЙ ТАБЕЛЬ
    Cursor МетодЗагружаетСотрудниковListViewТабеляПриВозвратеИЗШаблона(Context контекстLIstView, String ЦифровоеИмяНовгоТабеля, int месяцДляПермещенияПоТабелю, int годДляПермещенияПоТабелю) {
        ////




// TODO: 07.05.2021  ГЛАВНЫЙ КУРСОР СОГРУЗКИ СОТУРДНИКОВ В ТАБЕЛЬ

        Cursor Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = null;
        try {
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND cfo=? AND fio !=?  AND month_tabels=? AND  year_tabels =?  AND fio IS NOT NULL AND name IS NOT NULL",//  nametabel_typename  AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{"Удаленная", String.valueOf(ЦифровоеИмяНовгоТабеля), "", String.valueOf(месяцДляПермещенияПоТабелю), String.valueOf(годДляПермещенияПоТабелю)},
                    "name", null, "name", null);

            // TODO: 07.05.2021  данный курсор с датой показывает какой сотрудника изменили такой и сверху
*//*
            Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона = new Class_MODEL_synchronized(контекстLIstView).КурсорУниверсальныйДляБазыДанных("viewtabel",
                    new String[]{"*"},//     new String[]{"name,id,uuid,BirthDate,snils},
                    "status_send!=?  AND nametabel_typename=? AND uuid !=? AND uuid IS NOT NULL AND name IS NOT NULL",// AND nametabel IS NOT NULL",//AND status_send IS NULL//"Удаленная" //AND status_send!=?" /AND status_send IS NULL AND  name IS NOT NULL AND fio IS NOT NULL
                    new String[]{ "Удаленная",String.valueOf(ЦифровоеИмяНовгоТабеля),""},
                    "name", null, "date_update DESC", null);*//*

            //////
            if (Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount() > 0) {
                Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.moveToFirst();
            }

            /////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // new String[] { filter+"%" }, // new String[] {"%"+ filter+ "%" }, n
        //todo КУРСОР ЧЕРЕЗ ПОИСК LIKE
        Log.i(this.getClass().getName(), " Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблонаListView.getCount() " + Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона.getCount());

        return Курсор_ДляЗагрузкиСотрудниковНепостредственновИзШаблона;

    }*/

//TODO МЕТОД ЗАГРУЗНИ НОВОГО СОТРУДНИКА
    public Cursor МетодДанныеДЛяСпинераТАбеля() {
                    SQLiteCursor            КурсорДляСпинераСамиМЕсяцы = null;
                    try {
                        Class_GRUD_SQL_Operations    class_grud_sql_operationsЗначенияНовгоСотрудник=new Class_GRUD_SQL_Operations(context);
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","tabel");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","month_tabels,year_tabels,cfo,uuid");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","status_send!=?  " +
                                    " AND month_tabels IS NOT NULL  AND year_tabels IS NOT NULL");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","Удаленная");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","month_tabels,year_tabels");
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","year_tabels DESC " +
                                    ",month_tabels DESC " );
                            class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","6");


                            КурсорДляСпинераСамиМЕсяцы= (SQLiteCursor)  class_grud_sql_operationsЗначенияНовгоСотрудник.new GetData(context).getdata(class_grud_sql_operationsЗначенияНовгоСотрудник.
                                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                            Log.d(this.getClass().getName(), "КурсорДляСпинераСамиМЕсяцы " +КурсорДляСпинераСамиМЕсяцы );

                } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
        return КурсорДляСпинераСамиМЕсяцы;
    }

















//TODO МЕТОД ЗАГРУЗНИ НОВОГО шаблона

    public Cursor МетодЗагружаетЗначенияШаблонов(int полученнаяUUIDОрганизациидДляКурсораСпинераДаты, Context КонтекстДЛяСотрудника) {
     /*   Cursor asyncTaskLoader= (Cursor) new AsyncTaskLoader(КонтекстДЛяСотрудника) {
            @Override
            public Object loadInBackground() {*/

        SQLiteCursor Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsЗначенияШаблонов;
        try {
// TODO: 06.09.2021

            class_grud_sql_operationsЗначенияШаблонов=new Class_GRUD_SQL_Operations(context);


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","Templates");
            ///////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update=?");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",полученнаяUUIDОрганизациидДляКурсораСпинераДаты);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            //class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","month_tabels,year_tabels");
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","date_update DESC");
            ////
            class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
            ////
            /// class_grud_sql_operationsЗначенияНовгоСотрудник. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            // TODO: 12.10.2021  Ссылка Менеджер Потоков


            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри= (SQLiteCursor)  class_grud_sql_operationsЗначенияШаблонов.
                    new GetData(context).getdata(class_grud_sql_operationsЗначенияШаблонов. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри );


        /*    // TODO: 06.09.2021  old
            Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри = new Class_MODEL_synchronized(КонтекстДЛяСотрудника).КурсорУниверсальныйДляБазыДанных("Templates", new String[]
                            {"*"}, "user_update=?",
                    new String[]{String.valueOf(полученнаяUUIDОрганизациидДляКурсораСпинераДаты)},
                    null, null, "date_update DESC", null);
            ////
*/

            ///
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return Курсор_ЗагружаетАрайдистЗначенийНовогоШаблонаВнутри;
    }


    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеля(Context КонтекстДляЗагружемыхТАбелей,
                                                      Long UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников,
                                                      int месяцДляПермещенияПоТабелю,
                                                      int годДляПермещенияПоТабелю) {


        //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////
        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        //////
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеля;
        try {

            class_grud_sql_operationsУжеготовыеТабеля = new Class_GRUD_SQL_Operations(context);


                /*    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = new Class_MODEL_synchronized(КонтекстДляРежимаИнтрента).КурсорУниверсальныйДляБазыДанных("cfo", new String[]
                                    {"name"}, "id=?",
                            new String[]{String.valueOf(ТекущееСФО)}, null, null, "date_update DESC", "1");///"SELECT name  FROM MODIFITATION_Client WHERE name=?",НазваниеТаблицНаСервере
                    // TODO: 02.09.2021
*/


            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," uuid=?    " +
                    "AND status_send !=?" +
                    " AND month_tabels=?" +
                    " AND  year_tabels =? " +
                    "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников);
            ///
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","date_update DESC");
            ////
        class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","uuid");//date_update
            ////
          class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеля.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеля. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );
            ////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }










    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(Context КонтекстДляЗагружемыхТАбелей,
                                                                                     int ЦифровоеИмяНовгоТабеля,
                                                                                     int месяцДляПермещенияПоТабелю,
                                                                                     int годДляПермещенияПоТабелю) {
        /*Cursor asyncTaskLoaderЗагружаемТабеляСозданный = (Cursor) new AsyncTaskLoader(КонтекстДляЗагружемыхТАбелей) {
            @Override
            public Object loadInBackground() {*/
//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
        ////

        SQLiteCursor Курсор_ЗагружаемТабеляСозданныйВнутрений = null;
        ///
        Class_GRUD_SQL_Operations class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю;
        try {


            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю=new Class_GRUD_SQL_Operations(context);




            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            ///////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","cfo=?  " +
                    "AND status_send !=? " +
                    " AND month_tabels=?  " +
                    " AND  year_tabels=?" +
                    " AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            ///
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            //
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            ///
             /*       concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","12");
                    //
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","13");////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            /////classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            ///  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки","fio");
            ////
            class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","uuid");
            ////
     ////  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////


            // TODO: 12.10.2021  Ссылка Менеджер Потоков



            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


            Курсор_ЗагружаемТабеляСозданныйВнутрений= (SQLiteCursor)  class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю.
                    new GetData(context).getdata(class_grud_sql_operationsУжеготовыеТабеляДляСкролаПОТабелю. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_ЗагружаемТабеляСозданныйВнутрений );


            ///////todo\
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

///TODO ЗАПУСКАЕМ  ПуллПамяти
        return Курсор_ЗагружаемТабеляСозданныйВнутрений;

    }














    //todo загружет уже готовые созданные табеля
    public SQLiteCursor МетодЗагружетУжеготовыеТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек(Context КонтекстДляЗагружемыхТАбелей,
                                                                                               int ЦифровоеИмяНовгоТабеля,
                                                                                               int месяцДляПермещенияПоТабелю,
                                                                                               int годДляПермещенияПоТабелю) {

        SQLiteCursor Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти;
        try {
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти=new Class_GRUD_SQL_Operations(context);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",
                    "cfo=? " +
                    "AND status_send !=? AND" +
                    " month_tabels=? AND" +
                    "  year_tabels=? " +
                            "AND fio IS NOT NULL");
            ///"_id > ?   AND _id< ?"
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ЦифровоеИмяНовгоТабеля);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",месяцДляПермещенияПоТабелю);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",годДляПермещенияПоТабелю);
            class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update ");
            Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти= (SQLiteCursor)
                    class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                    new GetData(context).getdata(class_grud_sql_operationsТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек_ПервыйЗапускаПриЗАгрузкеАктивти.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData "+Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Курсор_ЗагружаемТабеляСозданный_ПервыйКурсорКоторыйСамЗагружаетьсяКогадМыЗаходимНААктивти;
    }



















    public Long МетодЗАписиПолученогоОтСервреаIDПубличногоВТАблицу_settings_tabels(@NonNull Integer ПолученныйотСервреаПубличныйID) throws ExecutionException, InterruptedException {
    long РезультатИзмененияВерсииДанныхПриЗаписиОрганизации = 0l;
        // TODO: 20.04.2021 определяем ели UUID или нет
        SQLiteCursor Курсор_УзнаемЕслиUUIDВТАблицеОрганизация = null;
                try {
                    String ТаблицаКоторуюнадоИзменитьВерсиюДанных="settings_tabels";
                        ///TODO ВСТАВКА НОВГО ТАБЕЛЯ В ТАБЛИЦУ
                        if (ПолученныйотСервреаПубличныйID > 0) {
                            ////////
      Class_GRUD_SQL_Operations class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного=new Class_GRUD_SQL_Operations(context);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКоторуюнадоИзменитьВерсиюДанных);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","user_update");
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного
                                    . concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","user_update=?   AND user_update IS NOT NULL ");
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолученныйотСервреаПубличныйID);
                            class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
                            Курсор_УзнаемЕслиUUIDВТАблицеОрганизация= (SQLiteCursor)  class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного.
                                    new GetData(context).getdata(class_grud_sql_operationsЗАписиПолученогоОтСервреаIDПубличного. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                            Log.d(this.getClass().getName(), "GetData " +Курсор_УзнаемЕслиUUIDВТАблицеОрганизация );
                            int UUIDдлянастроуки = 0;
                                if (Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getCount() > 0) {
                                    Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.moveToFirst();
                                    //////todo А ТУТУ КОГДА ЕСТЬ ДАННЫЕ
                                    int IndexUUID = Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getColumnIndex("user_update");
                                    int UUIDОрншанизации = Курсор_УзнаемЕслиUUIDВТАблицеОрганизация.getInt(IndexUUID);
                                    Log.d(this.getClass().getName(), "IndexUUID " + IndexUUID);
                                    // TODO: 06.09.2021  когда данные не были получены
                                } else {
                                    ContentValues АдаптерВставкиПолученогоПубличногоID = new ContentValues();
                                    АдаптерВставкиПолученогоПубличногоID.put("user_update", ПолученныйотСервреаПубличныйID);
                                    //TODOверсия программы самой например 601
                                    //Object ВрсияПрограммы=BuildConfig.VERSION_CODE;
                                    PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                                    String version = pInfo.versionName;//Version Name
                                    Integer verCode = pInfo.versionCode;//Version Code
                                    // TODO: 29.12.2021
                                    АдаптерВставкиПолученогоПубличногоID.put("version_dsu1",  Integer.parseInt(verCode.toString()));
                                    Log.d(this.getClass().getName(), " uildConfig.VERSION_CODE ВрсияПрограммы  "
                                            + verCode.toString());
                                    ////TODO ДАТА
                                    String СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного=
                                            new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                    АдаптерВставкиПолученогоПубличногоID.put("date_update", СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного);
                                    АдаптерВставкиПолученогоПубличногоID.put("uuid", ПолученныйотСервреаПубличныйID);
                                    /////////

                                    // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                                    Long РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ =
                                            new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(ТаблицаКоторуюнадоИзменитьВерсиюДанных,context,getССылкаНаСозданнуюБазу());
                                    Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ  " + РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ);

                                    АдаптерВставкиПолученогоПубличногоID.put("current_table", РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ);
                                    //////todo САМА НЕ ПОСТРЕДВСТВЕНА ЗАПИС ДАННЫХ В ТАБЛИЦУ НАСТЙКИ СИТЕМЫ
                                    Long    Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels =
                                                ВставкаДанныхЧерезКонтейнерПервыйолученныйПубличныйIDотСервера(ТаблицаКоторуюнадоИзменитьВерсиюДанных,
                                                АдаптерВставкиПолученогоПубличногоID);
                                    Log.d(this.getClass().getName(), "Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels "
                                            + Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels);
                                    // TODO: 02.05.2021

                                    if (Результат_ЗаписиНовгоЗначениевТАБЛИЦУ_settings_tabels>0 ) {
                                        Class_GRUD_SQL_Operations             classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels =
                                        new Class_GRUD_SQL_Operations(context);
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаКоторуюнадоИзменитьВерсиюДанных);
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");
                                        Log.d(context.getClass().getName(), "РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ "
                                                +РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ );
                                        classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",
                                                РезультатУвеличинаяВерсияДЛяТАБЛИЦЫНАСТРОЙКИ);
                                        ///TODO РЕЗУЛЬТА изменения версии данных
                                Integer    Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels = (Integer)
                                            classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                new ChangesVesionData(context)
                                                    .changesvesiondata(classGrudSqlOperationsПриписиИзменнийВерсииДанныхВФонеАунтификацииИЗАписьВsettings_tabels.
                                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                                        Log.d(this.getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels "
                                                +Результат_ПриписиИзменнийВерсииДанныхВТАБЛИЦУ_settings_tabels );
                                    }
                                }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }


        return РезультатИзмененияВерсииДанныхПриЗаписиОрганизации;
    }






















    // TODO: 09.04.2021 Метод Обновление Получение ПО с Сервера
    public File МетодЗагрузкиОбновлениеПОсСервера(@NonNull String АдресЗагрузки,
                                                  @NonNull Context context,
                                                  @NonNull String ИмяСервера,
                                                  @NonNull Integer ИмяПорта,
                                                  @NonNull String ЗаданиеЗагрузки,
                                                  @NonNull String ИмяФайлаЗагрузки ,
                                                  @NonNull String ВозвращяемыйТип) {
        final File[] СамФайлJsonandApk = {null};
                try {
                    String СтрокаСвязиСсервером ="http://"+ИмяСервера+":"+ИмяПорта+"/";;
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером + АдресЗагрузки; /////"dsu1.glassfish/update_android_dsu1/output-metadata.json";
                    СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
                    URL    Adress = new URL(СтрокаСвязиСсервером);
                    OkHttpClient okHttpClientЗагрузкаНовогоПО = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                    Class_GRUD_SQL_Operations grudSqlOperations = new Class_GRUD_SQL_Operations(context);
                                    grudSqlOperations.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                            " SELECT success_users,success_login  FROM successlogin  ORDER BY date_update DESC ;");
                                    // TODO: 12.10.2021  Ссылка Менеджер Потоков
                                    PUBLIC_CONTENT publicContent = new PUBLIC_CONTENT(context);
                                    SQLiteCursor Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = null;
                                    try {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО = (SQLiteCursor) grudSqlOperations.
                                                new GetаFreeData(context).getfreedata(grudSqlOperations.
                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                publicContent.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                                    } catch (ExecutionException e) {
                                        throw new RuntimeException(e);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    if (Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount() > 0) {
                                        Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();
                                        ПубличноеЛогин = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0).trim();
                                        ПубличноеПароль = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(1).trim();
                                    }
                                    String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                                    Log.d(this.getClass().getName(), "  PUBLIC_CONTENT.ПубличноеИмяПользовательДлСервлета  " + ПубличноеЛогин +
                                            " PUBLIC_CONTENT.ПубличноеПарольДлСервлета " + ПубличноеПароль);
                                    Request originalRequest = chain.request();
                                    Request.Builder builder = originalRequest.newBuilder()
                                            .header("Content-Type", ВозвращяемыйТип)
                                            .header("Accept-Encoding", "gzip,deflate,sdch")
                                            .header("Connection", "Keep-Alive")
                                            .header("Accept-Language", "ru-RU")
                                            .header("identifier", ПубличноеЛогин)
                                            .header("p_identifier", ПубличноеПароль)
                                            .header("task_downlonupdatepo", ЗаданиеЗагрузки)
                                            .header("id_device_androis", ANDROID_ID);
                                    Request newRequest = builder.build();
                                    return chain.proceed(newRequest);
                                }
                            }).connectTimeout(2, TimeUnit.SECONDS)
                            .readTimeout(40, TimeUnit.MINUTES).build();
                    ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                    Request requestGET = new Request.Builder().get().url(Adress).build();
                    Log.d(this.getClass().getName(), "  request  " + requestGET);
                    Dispatcher  dispatcherЗагрузкаПО = okHttpClientЗагрузкаНовогоПО.dispatcher();
                    okHttpClientЗагрузкаНовогоПО.newCall(requestGET).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            try{
                            Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 31.05.2022
                        } catch (Exception ex) {
                                ex.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + ex + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                            dispatcherЗагрузкаПО.executorService().shutdown();
                            //TODO закрываем п отоки
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try{
                            if (response.isSuccessful()) {
                                Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                                // TODO: 06.05.2023  если ПОТОК ЕСТЬ СОДЕРЖИВАЕМ ПАРСИМ
                                                    if(РазмерПришедшегоПотока>0){
                                                        InputStream inputStreamОтПинга = response.body().source().inputStream();
                                                        File ПутькФайлу = null;
                                                        if (Build.VERSION.SDK_INT >= 30) {
                                                            ПутькФайлу = context.getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS);
                                                        } else {
                                                            ПутькФайлу = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                                        }
                                                        СамФайлJsonandApk[0] = new File(ПутькФайлу, "/" + ИмяФайлаЗагрузки );
                                                        if (!СамФайлJsonandApk[0].getParentFile().mkdirs() ) {
                                                            СамФайлJsonandApk[0].getParentFile().mkdirs();
                                                            СамФайлJsonandApk[0].setWritable(true);
                                                            СамФайлJsonandApk[0].setExecutable(true);
                                                            // TODO: 20.03.2023 само создание файла
                                                            if (СамФайлJsonandApk[0].createNewFile()) {
                                                                Log.d(context.getClass().getName(), "Будущий файл успешно создалься , далее запись на диск новго APk файла ");
                                                                FileUtils.copyInputStreamToFile(inputStreamОтПинга, СамФайлJsonandApk[0]);
                                                                Log.d(context.getClass().getName(), "FileUtils.copyInputStreamToFile СамФайлJsonandApk"+
                                                                        СамФайлJsonandApk[0]);
                                                            } else {
                                                                Log.e(context.getClass().getName(), "Ошибка не создалься Будущий файл успешно создалься , далее запись на диск новго APk файла  СЛУЖБА ");
                                                            }
                                                        }
                                                        // TODO: 20.03.2023 ответ от сервреа если нет цифры значит не и файла
                                                    }
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                                // TODO: 06.05.2023 exit
                                response.close();
                                dispatcherЗагрузкаПО.executorService().shutdown();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    });
                    dispatcherЗагрузкаПО.executorService().awaitTermination(50,TimeUnit.MINUTES);
                    Log.i(context.getClass().getName(), "СамФайлJsonandApk" + СамФайлJsonandApk[0]);
                    // TODO: 13.03.2023  конец загрузки файла по новому FILE
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                  String  ОшибкаТекущегоМетода = ex.toString();
                    if (!ОшибкаТекущегоМетода.toString().matches("(.*)java.io.EOFException(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.sockettimeoutexception(.*)")
                            &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)SocketTimeout(.*)") &&
                            !ОшибкаТекущегоМетода.toString().matches("(.*)java.net.ConnectException(.*)")
                            && !ОшибкаТекущегоМетода.toString().matches("(.*)FileNotFoundException(.*)"))  {
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + ОшибкаТекущегоМетода + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + ОшибкаТекущегоМетода.toString());
                        new Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(ex.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }
                }
        return СамФайлJsonandApk[0];

    }






    ////TODO ДАННЫЙ МЕТОД ВЫЧИСЛЯЕТ НУЖНО ЛИ ЗАПОЛЯНТЬ ВЫХОДНИЕ ДНИ БУКВОЙ Б
    public ContentValues МетодВычисляемВыходныеДниПриСозданииНовогоТабеляАвтоРЕжим(@NonNull Context КонтекстДляРежимаИнтрента,
                                                                                   @NonNull Integer Месяц ,
                                                                                    @NonNull Integer Год ) {

        ContentValues РезультатВычисленияВыходныхДней = new ContentValues();

        try {


// create a Calendar for the 1st of the required month
            ///
            Log.d(КонтекстДляРежимаИнтрента.getClass().getName(), " Год  " + "--" + Год + " Месяц " + Месяц);/////



            Calendar calendar1 = Calendar.getInstance(new Locale("ru"));
            //TODO

      //   calendar.set(Calendar.DAY_OF_MONTH,calendar.);
            YearMonth yearMonthObject = YearMonth.of(Год, Месяц);

            int daysInMonth = yearMonthObject.lengthOfMonth()+1; //28

            SimpleDateFormat СозданияВычисляемВыходные=null;
            // TODO: 29.04.2022  int ИндексДней
            int ИндексДней;

            ///////TODO сам цикл который заполняет месяцами
            for ( ИндексДней=1;ИндексДней<daysInMonth;ИндексДней++) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                } else {

                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));

                }


                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (Год +"-"+Месяц+"-"+ИндексДней );

                // Then get the day of week from the Date based on specific locale.

            // TODO: 29.01.2022  отдельно только название дня
            String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );


                if (РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("сб")  ||
                        РезультатДатыДляКонктетногоТабеляТолькоЗанвание.equalsIgnoreCase("вс")) {
                    System.out.println("выходные дни при созадни тбалея полльзователь разрешмил авторежим" + ИндексДней);

                    String ОбьединяемДеньсЦифровдЛЯвСТАВКИ = "d" + String.valueOf(ИндексДней);

                    РезультатВычисленияВыходныхДней.put(ОбьединяемДеньсЦифровдЛЯвСТАВКИ, "В");
                }

            }
// stop when we reach the start of the next month


            Log.d(this.getClass().getName(), "   РезультатВычисленияВыходныхДней  " + РезультатВычисленияВыходныхДней.valueSet() + " daysInMonth " + daysInMonth);


            ///поймать ошибку
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///////
        }

        return РезультатВычисленияВыходныхДней;
    }













    ////TODO КОТОТРЫЙ УЗНАЕТ ИЗ БАЗЫ КАКОЙ РЕЖИМ РАБОТЫ ИНТРЕНТА WIFI AND MOBILE
    public String МетодПолучениеНазваниеТабеляНаОснованииСФО(Context КонтекстДляРежимаИнтрента, Integer ТекущееСФО) throws InterruptedException {
        //
                String ПолученоеНазваниеТабеляНаОснованииСФО = null;
                SQLiteCursor Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО = null;
                Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО;
                try {
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО=new Class_GRUD_SQL_Operations(КонтекстДляРежимаИнтрента);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","cfo");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","_id = ?");
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ТекущееСФО);
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                    Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО= (SQLiteCursor)  concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО.
                            new GetData(КонтекстДляРежимаИнтрента).getdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_ОперацийпОЛУЧЕНИЯнАЗВАНИЕСФО. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    Log.d(this.getClass().getName(), "GetData "  +Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО);
                    if (Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getCount() > 0) {
                        Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.moveToFirst();
                        ПолученоеНазваниеТабеляНаОснованииСФО = Курсор_ЗагружаетНазваниеТабеляНАОснованииСФО.getString(0).trim();
                        Log.d(this.getClass().getName(), " ПолученоеНазваниеТабеляНаОснованииСФО  " + ПолученоеНазваниеТабеляНаОснованииСФО);
                    }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученоеНазваниеТабеляНаОснованииСФО;
    }




















    // TODO: 15.06.2021 метод вычислчет дни недели  в потоке для отправки и принятии d1,d2,d3

    boolean МетодКоторыйВычисляетЕслиДНИвПотоке(String ПараметрИмяТаблицыОтАндройдаPostВнутриДляПоиска, JSONObject БуферСтолбикиДляВставкиВнутриВнутриДляПосика) {


        boolean ЕслиТакойДень = false;
        try{
        ////
        StringBuffer БУферИзJSONВБУФЕРАНАЛИЗА = new StringBuffer(БуферСтолбикиДляВставкиВнутриВнутриДляПосика.toString());

        ArrayList<String> АрайЛистДниТабеля = new ArrayList<String>(Arrays.asList("d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13", "d14"
                , "d15", "d16", "d17", "d18", "d19", "d20", "d21", "d22", "d23", "d24", "d25", "d26", "d27", "d28", "d29", "d30", "d31"));


            //////////
            String ЗначенияИБуфераДляПосикаДней = БУферИзJSONВБУФЕРАНАЛИЗА.toString();


            // TODO: 15.06.2021

            for (String КлючИзАрайЛиста : АрайЛистДниТабеля) {

                System.out.println(КлючИзАрайЛиста);

                //
                ЕслиТакойДень = ЗначенияИБуфераДляПосикаДней.contains(КлючИзАрайЛиста);
                ///TODO ЕСЛИ СТРАБОТАЛО ТО ВЫХОДИМ ИЗ ЦИКЛА
                if (ЕслиТакойДень == true) {

                    System.out.println("   ЕслиТакойДень " + ЕслиТакойДень);
                    /////////////
                    break;

                }


            }


            ///
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return ЕслиТакойДень;
    }

// TODO: 11.08.2021  ПОЛУЧЕНИЕ ЛОКАЛЬНОЙ ВЕРИСИ ДАНННЫХ ЧАТА
    // TODO: 10.08.2021  получение УВЕЛИЧИНОЙ ВЕРСИИ ДАННЫХ ДЛЯ ЧАТА
    protected Long МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер(String Текущаятаблицы, String ТекущаяяКолонкаТаблицы, Context context, String ИмяТаблицыОтАндройда_Локальноая) {
        Long ЗначениеДляПовышениеВерсии = 1l;
        SQLiteCursor Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных = null;
        Class_GRUD_SQL_Operations class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер;
        try{
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер=new Class_GRUD_SQL_Operations(this.context);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",Текущаятаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки",ТекущаяяКолонкаТаблицы);
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","name=? ");
            class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);

            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных= (SQLiteCursor)  class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер.
                    new GetData(this.context).getdata(class_grud_sql_operationsВерсииДаныхЧатаДляОтправкиЕгоНАСервер. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных );
            if(Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getCount()>0){
                Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.moveToFirst();
                ЗначениеДляПовышениеВерсии=Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.getLong(0);
                Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            }
            Log.d(this.getClass().getName(), "ЗначениеДляПовышениеВерсии "+ЗначениеДляПовышениеВерсии);
            Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных.close();
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы Data_Chat TRIGGER"+ЗначениеДляПовышениеВерсии);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        // TODO: 06.09.2021 результат 
        return  ЗначениеДляПовышениеВерсии;
    }


    public Integer МетодПосчётаЧасовПоСотруднику(Cursor курсор_ЗагружаемТабеляСозданный) {
        Integer СуммаЧасов = 0;
        try{
            for (int ИндексДляИзмененияДней = 1; ИндексДляИзмененияДней < 32; ИндексДляИзмененияДней++) {
                int ИндексЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getColumnIndex("d" + ИндексДляИзмененияДней);
                if (  курсор_ЗагружаемТабеляСозданный.getType(ИндексЧассыСотрудника)==Cursor.FIELD_TYPE_INTEGER) {
                    int ЧассыСотрудника = курсор_ЗагружаемТабеляСозданный.getInt(ИндексЧассыСотрудника);
                    СуммаЧасов = СуммаЧасов + ЧассыСотрудника;
                    Log.d(this.getClass().getName(), "    СуммаЧасов " + СуммаЧасов);
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " СуммаЧасов "+СуммаЧасов );
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return СуммаЧасов;
    }







    ///todo являеться ли весь текст числом
    public boolean МетодОпределениеВселиЦифрыВстроке(String ВселиЦифрыВтексе) {
        boolean Результат = false;
        try {
            Long.parseLong(ВселиЦифрыВтексе);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }



    ////TODO САМ МЕТОД АУНТИФИКАЦИИ С СЕРВЕРОМ
    public StringBuffer методАвторизацииЛогинИПаполь(@NonNull View v,
                                                     @NonNull Context context,
                                                     @NonNull SharedPreferences preferences,
                                                     @NonNull String ПубличноеЛогин,
                                                     @NonNull String ПубличноеПароль) {


        final StringBuffer[] БуферПолученнниеДанныхПолученияIDотСервера = {new StringBuffer()};
        try {
            Class_GRUD_SQL_Operations class_grud_sql_operationsАунтификация=new Class_GRUD_SQL_Operations(context);
            PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
            String ИмменоКакойСерверПодкючения ="http://"+ИмяСерверИзХранилица+":"+ПортСерверИзХранилица+"/";
          String  СтрокаСвязиСсервером = ИмменоКакойСерверПодкючения +new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель()+ "?"
                     + "JobForServer=" + "Хотим Получить ID для Генерации  UUID" + ""+
                  "&" + "IdUser=" + ПубличноеЛогин + "";
            СтрокаСвязиСсервером = СтрокаСвязиСсервером.replace(" ", "%20");
            Log.d(this.getClass().getName(), " СтрокаСвязиСсервером " +СтрокаСвязиСсервером);
            URL Adress = new URL(СтрокаСвязиСсервером); //
            Log.d(this.getClass().getName(),  "СтрокаСвязиСсервером "+СтрокаСвязиСсервером+
                    "ПубличноеПароль "+ПубличноеПароль+
                    "СтрокаСвязиСсервером "+СтрокаСвязиСсервером
                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName());

            // TODO: 11.03.2023  текст код
            if (ПубличноеЛогин.length()>0 && ПубличноеПароль.length()>0 && СтрокаСвязиСсервером.length()>0) {
                OkHttpClient okHttpClientИмяиПароль = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                                Log.d(this.getClass().getName(), "  ПубличноеЛогин " + ПубличноеЛогин + " ПубличноеПароль " + ПубличноеПароль);
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder()
                                        .header("Content-Type", "application/text; charset=UTF-8")
                                        .header("Accept-Encoding", "gzip,deflate,sdch")
                                        .header("Connection", "Keep-Alive")
                                        .header("Accept-Language", "ru-RU")
                                        .header("identifier", ПубличноеЛогин)
                                        .header("p_identifier", ПубличноеПароль)
                                        .header("id_device_androis", ANDROID_ID);
                                Request newRequest = builder.build();
                                return chain.proceed(newRequest);
                            }
                        }).connectTimeout(2, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS).build();
                ///  MediaType JSON = MediaType.parse("application/json; charset=utf-16");
                Request requestGET = new Request.Builder().get().url(Adress).build();
                Log.d(this.getClass().getName(), "  request  " + requestGET);
                // TODO  Call callGET = client.newCall(requestGET);
                Dispatcher dispatcherПроверкаЛогиниПароль = okHttpClientИмяиПароль.dispatcher();
                okHttpClientИмяиПароль.newCall(requestGET).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e(this.getClass().getName(), "  ERROR call  " + call + "  e" + e.toString());
                        Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ОшибкаТекущегоМетода " + e.getMessage());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                        dispatcherПроверкаЛогиниПароль.executorService().shutdown();

                        //TODO закрываем п отоки
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try{
                            if (response.isSuccessful()) {
                                Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                                if(РазмерПришедшегоПотока>0l){
                                    InputStream inputStreamОтПинга = response.body().source().inputStream();
                                    GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                                    BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                                    БуферПолученнниеДанныхПолученияIDотСервера[0] = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                            StringBuffer::append);
                                    Log.d(this.getClass().getName(), "БуферПолученнниеДанныхПолученияIDотСервера " + БуферПолученнниеДанныхПолученияIDотСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                    // TODO: 31.05.2022
                                }
                                Log.d(this.getClass().getName(), "БуферПолученнниеДанныхПолученияIDотСервера " + БуферПолученнниеДанныхПолученияIDотСервера[0] +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                                // TODO: 31.05.2022

                                dispatcherПроверкаЛогиниПароль.executorService().shutdown();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
                dispatcherПроверкаЛогиниПароль.executorService().awaitTermination(1,TimeUnit.MINUTES);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    БуферПолученнниеДанныхПолученияIDотСервера[0] ;
    }
}















