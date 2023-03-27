package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.Class_Type_Connenction_Tel;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassWriterPUBLICIDtoDatabase;
import com.dsy.dsu.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity_Tabels_Users_And_Passwords extends AppCompatActivity {
    ////todo аунтификация
   private int ПодсчетПолощительиОтрцательРезультатов =0; ////подсчитываем количество негативныйх попыток долеее 5 послываем программу в спячку
    private Button КнопкаВходавСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private ProgressBar ПрогрессБарДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private TextInputEditText ИмяДляВходаСистему,ПарольДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private   Configuration config;
    private Activity activity;
    private String КакойРежимСинхрониазции=new String();
    private Context КонтекстСинхроДляАунтификации;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private String ПубличноеЛогин =new String();
    private String ПубличноеПароль =new String();
    private   Integer ПубличноеIDПолученныйИзСервлетаДляUUID;
    private String СтрокаСвязиСсервером=new String();
    private  CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;
    private  StringBuffer   БуферПолученнниеДанныхПолученияIDотСервера=new StringBuffer();
    private SharedPreferences preferences;
    private   String ОшибкиПришлиПослеПингаОтСервера = null;
    private  View v;
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            КонтекстСинхроДляАунтификации = this;
            activity = this;
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new  PUBLIC_CONTENT(getApplicationContext());
            Intent ИнтентКакаяПоСчетуСинхронизация = getIntent();
             КакойРежимСинхрониазции = ИнтентКакаяПоСчетуСинхронизация.getStringExtra("РежимЗапускаСинхронизации");
            Log.d(this.getClass().getName(), " КакойРежимСинхрониазции "+КакойРежимСинхрониазции);
               Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            setContentView(R.layout.activity_main__authentication);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "   ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config =
                    getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);
            Log.d(getPackageName().getClass().getName(), " onCreate(Bundle savedInstanceState)  MainActivity_Tabels_Users_And_Passwords ");
            КнопкаВходавСистему = (Button) findViewById(R.id.КнопкаВходаВПриложение);/////кнопка входа на сервер
            КнопкаВходавСистему.setVisibility(View.VISIBLE);
            ПрогрессБарДляВходаСистему = (ProgressBar) findViewById(R.id.progressBarДляWIFI); ////програссбар при аунтификации при входе в системму
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// по умолчанию прогресс бар делаем не видеым
            ИмяДляВходаСистему = (TextInputEditText) findViewById(R.id.ИмяДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            ПарольДляВходаСистему = (TextInputEditText) findViewById(R.id.ПарольДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            String[] permissions = new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_MEDIA_LOCATION,
                    Manifest.permission.INSTALL_PACKAGES,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(this, permissions, 1);
           // preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
        } catch (Exception e) {
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() );
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //////TODO  данный код срабатывает когда произошда ошивка в базе

    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            МетодПодготовкиДляАунтификации(); ////МЕТОД ПРЕДВАРИТЕЛЬНОГО ПОДГОТОВКИ К АУНТИФИКАЦИИ ПОЛЬЗОВАТЛЕЯ
        } catch (Exception e) {
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    ////////TODO КОТОРЫЙ НАЧИНАЕМ ТОЛЬКО ЕСЛИ ЕСТЬ ИМЯ И ПАРОЛЬ НАЧИНАЕТЬСЯ ТОЛЬКО С НЯЖАТИЕ КНОПКИ ВХОД
    private void МетодПодготовкиДляАунтификации() {
        try {

            КнопкаВходавСистему.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v2.vibrate(50);
                    }
                    ПубличноеЛогин = ИмяДляВходаСистему.getText().toString().trim();///получаем из формы имя для того чтобы постучаться на сервер
                    Log.d(getPackageName().getClass().getName(), "ПубличноеИмяПользовательДлСервлета " + ПубличноеЛогин);
                 ПубличноеПароль = ПарольДляВходаСистему.getText().toString().trim();///////получаем из формы пароль для того чтобы постучаться на сервер
                    Log.d(getPackageName().getClass().getName(), "ПубличноеПарольДлСервлета " + ПубличноеПароль);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                    if (ПубличноеЛогин.length() > 0 &&  ПубличноеПароль.length() > 0) {

                        boolean РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию =
                                new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();

                        if (РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию == true) {
                            Boolean       РеальныйПингСервера = new Class_Connections_Server(getApplicationContext()).
                                    МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                                    Log.d(this.getClass().getName(), " РеальныйПингСервера "+ РеальныйПингСервера) ;
                            if (РеальныйПингСервера==true) {
                                ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                                ПрогрессБарДляВходаСистему.refreshDrawableState();
                                //TODO запукаем метод аунтификции
                                МетодАунтификациисСервером(v);//// данный метод в будущем будет запускаться с  кнопк
                                Log.d(this.getClass().getName(), " РеальныйПингСервера "+ РеальныйПингСервера) ;
                            }
                        } else {
                            Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                            ПрогрессБарДляВходаСистему.refreshDrawableState();
                            Snackbar.make(v, "Нет связи с с сервером !!! ", Snackbar.LENGTH_LONG).show();
                        }////end проверки если сеть или нет TRUE
                    } else {
                        Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                        ПрогрессБарДляВходаСистему.refreshDrawableState();
                        Snackbar.make(v, " Вы не заполнили Логин/Пароль ", Snackbar.LENGTH_LONG).show();

                    }
                }
            });

        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ////TODO САМ МЕТОД АУНТИФИКАЦИИ С СЕРВЕРОМ
    private void МетодАунтификациисСервером(View v) {
        Class_GRUD_SQL_Operations class_grud_sql_operationsАунтификация=new Class_GRUD_SQL_Operations(getApplicationContext());
        try {
            //////TODO Запуск асинхроного ЛОУДОРА ДЛЯ АУНТИФТИКАЦИИ ПОЛЬЗОВАТЕЛЯ
            this.v=v;
                PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(getApplicationContext());
                String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                String ИмменоКакойСерверПодкючения ="http://"+ИмяСерверИзХранилица+":"+ПортСерверИзХранилица+"/";
                //////TODO --операции
                СтрокаСвязиСсервером = ИмменоКакойСерверПодкючения +new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервераАунтификация()+ "?"
                        + "ЗаданиеДляСервлетаВнутриПотока=Хотим Получить ID для Генерации  UUID";
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
                                String ANDROID_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
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
                        }).connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS).build();
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
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), Class_MODEL_synchronized.class.getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 31.05.2022
                        dispatcherПроверкаЛогиниПароль.executorService().shutdown();
                        // TODO: 11.03.2023  ПОСЛЕ ПИНГА ПЕРЕХОДИМ
                        МетодПослеАунтификациисСервером(v);
                        //TODO закрываем п отоки
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try{
                        if (response.isSuccessful()) {
                            InputStream inputStreamОтПинга = response.body().source().inputStream();
                            GZIPInputStream GZIPПотокОтСЕРВЕРА = new GZIPInputStream(inputStreamОтПинга);
                            BufferedReader РидерОтСервераМетодаGET = new BufferedReader(new InputStreamReader(GZIPПотокОтСЕРВЕРА, StandardCharsets.UTF_16));//
                            БуферПолученнниеДанныхПолученияIDотСервера = РидерОтСервераМетодаGET.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                    StringBuffer::append);
                            Long РазмерПришедшегоПотока = Long.parseLong(   response.header("stream_size"));
                            Log.d(this.getClass().getName(), "БуферПолученнниеДанныхПолученияIDотСервера " + БуферПолученнниеДанныхПолученияIDотСервера +  " РазмерПришедшегоПотока " +РазмерПришедшегоПотока);
                            // TODO: 31.05.2022
                            dispatcherПроверкаЛогиниПароль.executorService().shutdown();
                            // TODO: 11.03.2023  ПОСЛЕ ПИНГА ПЕРЕХОДИМ
                            МетодПослеАунтификациисСервером(v);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });
                //TODO
             /*   dispatcherПроверкаЛогиниПароль.executorService().awaitTermination(1,TimeUnit.MINUTES);
                dispatcherПроверкаЛогиниПароль.cancelAll();*/
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодПослеАунтификациисСервером(View v)  {
        try{
        if (БуферПолученнниеДанныхПолученияIDотСервера.length() > 0) {
            if (!БуферПолученнниеДанныхПолученияIDотСервера.toString().trim() .matches("(.*)Don't Login and Password(.*)") ) {
                ПубличноеIDПолученныйИзСервлетаДляUUID =Integer.parseInt(БуферПолученнниеДанныхПолученияIDотСервера.toString());
                Log.d(this.getClass().getName(), "  ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя "
                        + БуферПолученнниеДанныхПолученияIDотСервера + "  ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);
                Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу=Integer.parseInt(БуферПолученнниеДанныхПолученияIDотСервера.toString());
                Log.d(this.getClass().getName(), " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                // TODO: 11.03.2023 ПОСЛЕ УСПЕШНОГО ПЕРЕХОД НА АКТИВТИ
                МетодПослеУспешногоПолучениеДанныхОтСервераЗаписываемИх(ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                //TODO не прошёл аунтификайию
            }else{
                //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                МетодВизуальногоОтображениеРаботыКоннекта("Логин и/или Пароль не правильный !!!" );
            }
        }else {
            //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
            МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!");
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    private void МетодПослеУспешногоПолучениеДанныхОтСервераЗаписываемИх(Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу )
            throws ExecutionException, InterruptedException {
        //// todo после успешного получение имени и пароля записываем их в базу ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
        try{
            //todo ЗАПИСЬ ="SUCCENLOGIN";
            Long результатЗаписиНовогоПароляПользователявБазцуsuccesslogin=
                    new SubClassWriterPUBLICIDtoDatabase().
                            МетодЗапипиВБАзуПубличногоID(getApplicationContext(),ПолученинныйПубличныйIDДлчЗаписиВБАзу, ПубличноеЛогин, ПубличноеПароль);
            if (результатЗаписиНовогоПароляПользователявБазцуsuccesslogin>0) {
                /// TODO: 22.02.2022 ЗАПИСЬ ="settings_tabels";
                Long РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels=
                        new Class_MODEL_synchronized(getApplicationContext()).  МетодЗАписиПолученогоОтСервреаIDПубличногоВТАблицу_settings_tabels(
                                ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ
                Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера "
                        +БуферПолученнниеДанныхПолученияIDотСервера.toString() +
                        " УСПЕШАЯ ЗАПИСЬ ПУБЛИЧНОГО id SUCCEESS !!!!  " +
                        "ТАБЛИЦА settings_tabels  РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels "
                        + РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels+
                        " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                МетодВходаВПриложениеПослеУспешногоЛогирования();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодВходаВПриложениеПослеУспешногоЛогирования() {
        try{
            Log.d(this.getClass().getName(), " stopLoading() asyncTaskLoaderАунтификацияПользователя ");
            Intent Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации = new Intent();
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеIDПолученныйИзСервлетаДляUUID",ПубличноеIDПолученныйИзСервлетаДляUUID);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеИмяПользовательДлСервлета", ПубличноеЛогин);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеПарольДлСервлета", ПубличноеПароль);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("СтрокаСвязиСсервером",СтрокаСвязиСсервером);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setClass(getApplication(), MainActivity_Visible_Async.class);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/// FLAG_ACTIVITY_SINGLE_TOP
            // TODO: 01.12.2022 записываем режим синъронизации
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
            editor.commit();
            startActivity(Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    ///todo метод визуализацци успешных и не успешных аунтифиуаци пользоватле
    private void МетодВизуальногоОтображениеРаботыКоннекта(String СтатусДляПользователя) {
       runOnUiThread(new Runnable() {
            public void run() {
                Log.d(this.getClass().getName(), " handlerВизуализацияАунтификации ");
                try {
                    Drawable icon;
                    ПодсчетПолощительиОтрцательРезультатов++;
                    ПрогрессБарДляВходаСистему.setIndeterminate(false);
                    ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                    ПрогрессБарДляВходаСистему.refreshDrawableState();
                    Snackbar snackbar = Snackbar.make(v, " " +СтатусДляПользователя+" ("+ПодсчетПолощительиОтрцательРезультатов+") " , Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Log.d(this.getClass().getName(), " ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе "
                            + " БуферПолученнниеДанныхПолученияIDотСервера" + БуферПолученнниеДанныхПолученияIDотСервера.length());
                        if (ПодсчетПолощительиОтрцательРезультатов > 4) {////ПОПЫТКИ НЕ УДАЧНОГО ВХОДА В ПРОГРАММУ СВЫШЕ 5  СООБШАЕМ ПОЛЬЗОВАТЛЮ ЧТО ЕГО ИММ ЯИ ИЛИ ПАРОЛЬ НЕ ПРАВИЛЬНЫЙ И ПРИЛОЖЕНИЕ ОПРАЫЛЕМ В СОН
                            ПодсчетПолощительиОтрцательРезультатов = 0;
                            ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                            Snackbar.make(v, " Сон на 10 секунд.....", Snackbar.LENGTH_LONG).show();
                            КнопкаВходавСистему.setEnabled(false);
                            КнопкаВходавСистему.setClickable(false);
                            КнопкаВходавСистему.setBackgroundColor(Color.GRAY);
                                    ПрогрессБарДляВходаСистему.postDelayed(() -> {
                                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
                                        КнопкаВходавСистему.setEnabled(true);
                                        КнопкаВходавСистему.setClickable(true);
                                        КнопкаВходавСистему.setBackgroundColor(Color.parseColor("#00ACC1"));
                                    }, 10000);// по умолчанию прогресс бар делаем не видеым

                                    // TODO: 13.10.2021
                        }
                } catch (Exception e) {
                    ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });
    }





    boolean МетодГлавныйСинхронизацииДанныхКлиентСервер(Context КонтекстКоторыйДляСинхронизации) throws ExecutionException, InterruptedException, TimeoutException {
        /////=----СИНХРОНИЗАЦИЯ
        ///TODO СИНХРОНИЗАЦИЯ ///TODO СИНХРОНИЗАЦИЯ при запуске прилиложения
//TODO ПРОВРЕМ WIFI ПОДКЛЮЧЕН ЛИ
        boolean РезультатПРоверкиПодключениеWIFI = false;
        String Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile = new String();
        try {
            /////todo тут МЫ ПОЛУЧАЕМ В КАКОЙ МОМЕНТ ТИП ПОДКЛЮЧЕНИЯ НА ТЕЛЕФОНЕ МОБИЛЯ ИЛИ  WIFI  И В ЗАВИСИМОСТИ ЧТОБЫ ПОНЯТЬ ЧЕ ЗА ДЕЛА
            Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile = new Class_Type_Connenction_Tel(getApplicationContext()).МетодОпределяемКакойТипПодключениеWIFIилиMobile();
            //////
            Log.d(this.getClass().getName(), "   РезутьтатПроверкиТипПодключениякИнтернету " + Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile);
                ////
          Class_GRUD_SQL_Operations       class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер = new Class_GRUD_SQL_Operations(getApplicationContext());
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "mode_connection");
                SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью = (SQLiteCursor) class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), "GetData "+КурсорУзнаемСохраненыйРежимРаботыССетью);
                String РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile = new String();
                Log.d(getApplicationContext().getClass().getName(), " КурсорУзнаемСохраненыйРежимРаботыССетью  " + "--" + КурсорУзнаемСохраненыйРежимРаботыССетью);/////
                //////
                if (КурсорУзнаемСохраненыйРежимРаботыССетью.getCount() > 0) {
                    КурсорУзнаемСохраненыйРежимРаботыССетью.moveToFirst();
                    РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile = КурсорУзнаемСохраненыйРежимРаботыССетью.getString(0);
                    Log.d(getApplicationContext().getClass().getName(), " РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile  " + "--" + РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile);/////
                }
                // TODO: 03.10.2021  результата
                if (  (Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.trim().equalsIgnoreCase(РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile.trim()))
                || Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.equalsIgnoreCase("Mobile")   || Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile.equalsIgnoreCase("WIFI")){
                    ///
                    Log.d(getApplicationContext().getClass().getName(), " Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile  " + "--" + Резутьтат_В_Настройки_УстановленныВТелефоне_WifiИлиMobile);/////
                    ////
                    РезультатПРоверкиПодключениеWIFI=true;
                }else {
                    ///
                    Log.d(getApplicationContext().getClass().getName(), " РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile  " + "--" + РезутьтатПроверкиВБазуЗаписаннаяНстройкаСетьWifiИлиMobile);/////
                    //
                    РезультатПРоверкиПодключениеWIFI=false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        //TODO ВОЗВРАЩЯЕМ НУЖНО ПОДКЛЮЧАТЬ АУНТИВИКАУИЮ ИЛИ НЕТ
        return РезультатПРоверкиПодключениеWIFI;
    }

}








