package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dsy.dsu.AllDatabases.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Clears_Tables;
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

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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
    private  CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;

    private SharedPreferences preferences;
    private   String ОшибкиПришлиПослеПингаОтСервера = null;
    private  View vКнопки;
    private  Message message;
    public static final int CAMERA_PERSSION_CODE=1;
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


            // TODO: 02.08.2023 БИЗНЕС КОД



        new  Class_Clears_Tables(getApplicationContext(),null, null). методОчисткаТаблицыSuccesslogin("successlogin",getApplicationContext());


            методДаемПраваНаCameraPermissions(this);
           // preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            // TODO: 12.04.2023  messageGet
            messageGet();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
                    vКнопки=v;
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

                    ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                    ПрогрессБарДляВходаСистему.refreshDrawableState();
                    ПрогрессБарДляВходаСистему.forceLayout();
                    // TODO: 25.04.2023 начинаем работак Асинхроном
                    message.getTarget().post(()->{
                    if (ПубличноеЛогин.length() > 0 &&  ПубличноеПароль.length() > 0) {

                        boolean РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию =
                                new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();

                        if (РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию == true) {
                            Boolean       РеальныйПингСервера = new Class_Connections_Server(getApplicationContext()).
                                    МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                                    Log.d(this.getClass().getName(), " РеальныйПингСервера "+ РеальныйПингСервера) ;
                            if (РеальныйПингСервера==true) {
                                //TODO запукаем метод аунтификции
                                //TODO запукаем метод Афторизаиция по ЛОГИНУ И ПАРОЛЮ
                                    StringBuffer  БуферПолученнниеДанныхПолученияIDотСервера=new Class_MODEL_synchronized(getApplicationContext()).
                                            методАвторизацииЛогинИПаполь(vКнопки,getApplicationContext(),preferences,ПубличноеЛогин,ПубличноеПароль);
                                    Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера "+
                                            БуферПолученнниеДанныхПолученияIDотСервера) ;
                                    if (БуферПолученнниеДанныхПолученияIDотСервера!=null
                                            && БуферПолученнниеДанныхПолученияIDотСервера.toString().length()>0) {
                                        Bundle bundleРезультатПарольЛогин=new Bundle();
                                        bundleРезультатПарольЛогин.putString("БуферПолученнниеДанныхПолученияIDотСервера",
                                             БуферПолученнниеДанныхПолученияIDотСервера.toString());
                                        message.setData(bundleРезультатПарольЛогин);
                                        message.getTarget().dispatchMessage(message);
                                    }else {
                                        Snackbar.make(vКнопки, "Логин/Пароль не подходят !!! ", Snackbar.LENGTH_LONG).show();
                                    }
                            }else {
                                Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                                Snackbar.make(vКнопки, "Нет связи с с сервером !!! ", Snackbar.LENGTH_LONG).show();

                            }
                        } else {
                            Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                            Snackbar.make(vКнопки, "Нет связи с с сервером !!! ", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d(this.getClass().getName(), " Вы не заполнили Логин/Пароль ") ;
                        Snackbar.make(v, " Вы не заполнили Логин/Пароль ", Snackbar.LENGTH_LONG).show();
                    }
                        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
                        ПрогрессБарДляВходаСистему.refreshDrawableState();
                        ПрогрессБарДляВходаСистему.forceLayout();
                    });
                    // TODO: 25.04.2023  end async pool
                }
            });

        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 12.04.2023
    void messageGet(){
        message= Message.obtain(new Handler(Looper.myLooper()),()->{
            try{
                Bundle bundle=   message.getData();
    String БуферПолученнниеДанныхПолученияIDотСервера=          bundle.getString("БуферПолученнниеДанныхПолученияIDотСервера","");
                // TODO: 12.04.2023 Как получаем ответ от сервра сообщаем это пользователю
                МетодПослеАунтификациисСервером(vКнопки,new StringBuffer(БуферПолученнниеДанныхПолученияIDотСервера));
                // TODO: 21.04.2023
                message.getTarget().removeCallbacksAndMessages(null);
                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString() + " bundle " +bundle );
                Log.i(this.getClass().getName(), "bundle " +bundle);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });
    }


    private void МетодПослеАунтификациисСервером(View v,@NonNull StringBuffer БуферОтветаLoginPassword)  {
        try{
        if (БуферОтветаLoginPassword.toString().length()  > 0) {
            if (БуферОтветаLoginPassword.toString().trim() .matches("(.*)[0-9](.*)") &&
            ! БуферОтветаLoginPassword.toString().trim() .matches("(.*)[Don't Login and Password](.*)")) {
                ПубличноеIDПолученныйИзСервлетаДляUUID = Integer.parseInt(БуферОтветаLoginPassword.toString()) ;
                Log.d(this.getClass().getName(), "  ПроверкаПришёлЛиОтветОтСервлетаДляАунтификацииПользователя "
                        + БуферОтветаLoginPassword + "  ID " +ПубличноеIDПолученныйИзСервлетаДляUUID);
                Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу=Integer.parseInt(БуферОтветаLoginPassword.toString());
                Log.d(this.getClass().getName(), " ПолученинныйПубличныйIDДлчЗаписиВБАзу " +ПолученинныйПубличныйIDДлчЗаписиВБАзу);
                // TODO: 11.03.2023 ПОСЛЕ УСПЕШНОГО ПЕРЕХОД НА АКТИВТИ
                МетодПослеУспешногоПолучениеДанныхОтСервераЗаписываемИх(ПолученинныйПубличныйIDДлчЗаписиВБАзу,v);
                //TODO не прошёл аунтификайию
            }else{
                //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                МетодВизуальногоОтображениеРаботыКоннекта("Логин и/или Пароль не правильный !!!" ,v);
            }
        }else {
            //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
            МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!",v);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    private void МетодПослеУспешногоПолучениеДанныхОтСервераЗаписываемИх(Integer ПолученинныйПубличныйIDДлчЗаписиВБАзу,View v )
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
                Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера "+
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
            // TODO: 01.12.2022 записываем режим синъронизации
            SharedPreferences.Editor editor = preferences.edit();
            Log.d(this.getClass().getName(), " stopLoading() asyncTaskLoaderАунтификацияПользователя ");
            Intent Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации = new Intent();
            if ( КакойРежимСинхрониазции==null) {
                Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
                editor.putString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");
            }else{
                Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("РежимЗапускаСинхронизации",КакойРежимСинхрониазции);
                editor.putString("РежимЗапускаСинхронизации",КакойРежимСинхрониазции);
            }
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ID",ПубличноеIDПолученныйИзСервлетаДляUUID);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеИмяПользовательДлСервлета", ПубличноеЛогин);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.putExtra("ПубличноеПарольДлСервлета", ПубличноеПароль);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setClass(getApplication(), MainActivity_Visible_Async.class);
            Интент_ЗапускСамогоПриложенияЕслиПользовательПослеУспешнойаунтификации.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);/// FLAG_ACTIVITY_SINGLE_TOP

            editor.putInt("ПубличноеID", ПубличноеIDПолученныйИзСервлетаДляUUID);
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
    private void МетодВизуальногоОтображениеРаботыКоннекта(String СтатусДляПользователя,@NonNull  View v) {
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
                    Log.d(this.getClass().getName(), " ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе ");
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
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
                class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНабор.put("СтолбцыОбработки", "mode_connection");
                SQLiteCursor КурсорУзнаемСохраненыйРежимРаботыССетью = (SQLiteCursor) class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsГлавныйСинхронизацииДанныхКлиентСервер.concurrentHashMapНабор,
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


    public  void методДаемПраваНаCameraPermissions(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.d("checkCameraPermissions", "No Camera Permissions");
            //////////////////////TODO SERVICE
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.READ_PHONE_STATE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.VIBRATE,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                    android.Manifest.permission.INSTALL_PACKAGES,
                    android.Manifest.permission.WRITE_SETTINGS,
                    android. Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(activity, permissions,CAMERA_PERSSION_CODE );


        }else{
            // Permission is not granted
            Log.d("checkCameraPermissions", "Success YRA  Camera Permissions  !!!!");
        }
    }

}








