package com.dsy.dsu.LayerApp.PasswordsApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


 
import com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus.MessageEvensBusAyns;
import com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.LayerApp.BootAndAsyncApp.Window.MainActivityBootAndAsync;
import com.dsy.dsu.LayerBunessLogic.Class_Clears_Tables;
import com.dsy.dsu.LayerBunessLogic.Class_Connections_Server;
import com.dsy.dsu.LayerBunessLogic.Class_Find_Setting_User_Network;
import com.dsy.dsu.LayerBunessLogic.CreateFolderBinatySave.ClassCreateFolderCommitPays1C;
import com.dsy.dsu.LayerBunessLogic.CreateFolderBinatySave.ClassDeleteErrorFile;
import com.dsy.dsu.LayerBunessLogic.Errors.ClassCreateFileForError;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Class_MODEL_synchronized;
import com.dsy.dsu.LayerBunessLogic.CreateFolderBinatySave.ClassCreateFolderBinatyMatrilal;
import com.dsy.dsu.LayerBunessLogic.Hilt.Sqlitehilt.HiltInterfacesqlite;
import com.dsy.dsu.LayerBunessLogic.Permissions.ClassPermissions;
import com.dsy.dsu.LayerBunessLogic.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.LayerBunessLogic.SubClassWriterPUBLICIDtoDatabase;
import com.dsy.dsu.LayerBunessLogic.EventBus.EventBuss;
import com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.qualifiers.QualifierJbossServer3;
import com.dsy.dsu.R;
import com.dsy.dsu.LayerApp.SettingsApp.Model.bl_SettingsActivity.GetSettingTableSaves;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.jakewharton.rxbinding4.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.EntryPoints;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

@AndroidEntryPoint
public class MainActivityPasswords extends AppCompatActivity {
    ////todo аунтификация
    private int ПодсчетПолощительиОтрцательРезультатов = 0; ////подсчитываем количество негативныйх попыток долеее 5 послываем программу в спячку
    private Button КнопкаВходавСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private ProgressBar ПрогрессБарДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private TextInputEditText ИмяДляВходаСистему, ПарольДляВходаСистему;///КНОПКА ДЛЯ ВХОДЯ В СИСТЕМУ
    private Configuration config;
    private Activity activity;

    private Context КонтекстСинхроДляАунтификации;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    private String ПубличноеЛогин = new String();
    private String ПубличноеПароль = new String();
    private SQLiteDatabase sqLiteDatabase;
    private SharedPreferences preferences;
    private String ОшибкиПришлиПослеПингаОтСервера = null;
    private Message message;
    public static final int ALL_PERSSION_CODE=1;
    public static final int CAMERA_PERSSION_CODE=2;

    EventBuss eventBuss;

    @Inject
    @QualifierJbossServer3
    public LinkedHashMap<Integer,String> getHiltPortJboss;




    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main__authentication);

            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            КонтекстСинхроДляАунтификации = this;
            activity = this;
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) КонтекстСинхроДляАунтификации).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());


            // TODO: 04.10.2023 разрешения для всего
            new ClassPermissions(this,ALL_PERSSION_CODE,CAMERA_PERSSION_CODE);



            /* TODO: 06.09.2023 tabase */
            sqLiteDatabase  = EntryPoints.get(getApplicationContext(), HiltInterfacesqlite.class).getHiltSqlite();




            методHideКлавиатура();

            getSupportActionBar().hide(); ///скрывать тул бар

            // TODO: 29.09.2023 клавиатура

            Log.d(this.getClass().getName(), "   ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config =
                    getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);


            // TODO: 19.12.2023 удаляем файл ошибоок
            ClassDeleteErrorFile classDeleteErrorFile=new ClassDeleteErrorFile(getApplicationContext());
            classDeleteErrorFile.МетодDeleteFolders();

// TODO: 19.12.2023 создаем  файл ошибоок
            // TODO: 07.10.2023  create file for ERROR
            ClassCreateFileForError classCreateFileForError=new ClassCreateFileForError();
            classCreateFileForError.metodCreateFileForError(this);


            КнопкаВходавСистему = (Button) findViewById(R.id.КнопкаВходаВПриложение);/////кнопка входа на сервер
            КнопкаВходавСистему.setVisibility(View.VISIBLE);
            ПрогрессБарДляВходаСистему = (ProgressBar) findViewById(R.id.progressBarДляWIFI); ////програссбар при аунтификации при входе в системму
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// по умолчанию прогресс бар делаем не видеым
            ИмяДляВходаСистему = (TextInputEditText) findViewById(R.id.ИмяДляВходавПрограмму); ////програссбар при аунтификации при входе в системму
            ПарольДляВходаСистему = (TextInputEditText) findViewById(R.id.ПарольДляВходавПрограмму); ////програссбар при аунтификации при входе в системму


            // TODO: 02.08.2023 БИЗНЕС КОД
            методЗаписываемПервыйЭтапСинхрогниазции();
// TODO: 13.09.2023 очистка ТАБЛИЦ с паролями  
            new Class_Clears_Tables(getApplicationContext(), null, null)
                    .методОчисткаТаблицыSuccesslogin("successlogin", getApplicationContext());

            // TODO: 13.09.2023 очистка ТАБЛИЦ с паролями
            new Class_Clears_Tables(getApplicationContext(), null, null)
                    .методОчисткаТаблицыSuccesslogin("settings_tabels", getApplicationContext());



            // TODO: 12.04.2023  messageGet
            messageGet();

            // TODO: 14.08.2023 создаем папку для BinaryFile Save
            new ClassCreateFolderBinatyMatrilal(getApplicationContext()).МетодCreateFoldersBinaty();


            // TODO: 14.08.2023 создаем папку для BinaryFile CommitPay1C Соласования
            new ClassCreateFolderCommitPays1C(getApplicationContext()).МетодCreateFoldersBinaty();

            eventBuss=new EventBuss(activity,getApplicationContext());


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
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
    protected void onStop() {
        super.onStop();
        try{

        EventBus.getDefault().unregister(this);
    } catch (Exception e) {
        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    protected void onStart() {
        super.onStart();try{

            EventBus.getDefault().register(this);
        } catch (Exception e) {
            ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);// при нажатии делаем видимый програсссбар
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

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

            // TODO: 29.09.2023 Класс Сохранение и Аунтификации
            ClassSavePassword classSavePassword=new ClassSavePassword();

            classSavePassword. metodSavePassword();

            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName());
                            // TODO: 25.04.2023  end async pool
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    private void методGetПарольОбработка(View v) {
        try{
        Integer PublicID;
        //TODO запукаем метод Афторизаиция по ЛОГИНУ И ПАРОЛЮ
        PublicID = new Class_MODEL_synchronized(getApplicationContext()).
                методАвторизацииЛогинИПаполь(getApplicationContext(), preferences, ПубличноеЛогин, ПубличноеПароль, getHiltPortJboss);
        Log.d(this.getClass().getName(), " PublicID " + PublicID);

        // TODO: 24.08.2023 УСПЕШНЫЙ КОД ЛОГИРОВАНИЕ И ПАРОЛЬ
        switch (PublicID){
            case 0:
                МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!", v);
                break;
            case -1:
                //TODO ПОСЛЕ ПИНГА ВИЗУАЛИЗАЦИЯ
                МетодВизуальногоОтображениеРаботыКоннекта("Логин и пароль не правильные !!!", v);
                break;
            // TODO: 15.09.2023  успещнно SUCCEESS
            default:
                // TODO: 15.09.2023  Успешно  ПЕРЕХОД В САМУ ПРОГРАММУ

                ClassAfterForfardFaseApp classAfterForfardFaseApp=new ClassAfterForfardFaseApp();

                classAfterForfardFaseApp.ClassAfterForfardFaseApp( PublicID, v);

                Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                        " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()+ " PublicID " +PublicID);

                break;
        }
            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()+ " PublicID " +PublicID);
    } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
    
    
    
    
    
    // TODO: 12.04.2023
    void messageGet() {
        message = Message.obtain(new Handler(Looper.myLooper()), () -> {
            try {
                Bundle bundle = message.getData();
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


    private  Integer   методЗаписьВSuccesLogin(Integer ПолученинныйПубличныйID, View v) {
        Integer ЗаписьВSuccesLogin=0;
        //// todo после успешного получение имени и пароля записываем их в базу ЗАПУСК МЕТОДА ВСТАВКИ ИМЕНИ И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ БОЛЕЕ 7 ДНЕЙ
        try {
            //todo ЗАПИСЬ ="SUCCENLOGIN";
            ЗаписьВSuccesLogin =
                    new SubClassWriterPUBLICIDtoDatabase().
                            aftersuccessfulsynchronizationWritedownthepublicidSuccessLogin(getApplicationContext(), ПолученинныйПубличныйID, ПубличноеЛогин, ПубличноеПароль);
            // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ
            Log.d(this.getClass().getName(), " БуферПолученнниеДанныхПолученияIDотСервера " +
                    " УСПЕШАЯ ЗАПИСЬ ПУБЛИЧНОГО id SUCCEESS !!!!  " +
                    "ТАБЛИЦА settings_tabels  РезультатЗаписиНовгоIDБАзуВТаблицеНАСТРОЕКПОЛЬЗОВТЕЛЯ_ДЛяЗАПИСИВТаблицу_settings_tabels "
                    + ЗаписьВSuccesLogin);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЗаписьВSuccesLogin;

    }


    private  Integer   методЗаписьВSettingTabel(Integer ПолученинныйПубличныйID, View v) {
        Integer ЗаписьВSettingTabel=0;
        try {
                /// TODO: 22.02.2022 ЗАПИСЬ ="settings_tabels";
                ЗаписьВSettingTabel =new GetSettingTableSaves().getWritingPasswordSetingTable(getApplicationContext(),ПолученинныйПубличныйID);

                // TODO: 10.09.2021  РЕЗУЛЬТАТ ЗАПИСИ СОТРУДНИКА ЗАПИСИ В БАЗУ

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЗаписьВSettingTabel " +ЗаписьВSettingTabel);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ЗаписьВSettingTabel;

    }


    private void методВходВFaceApp(@NonNull Integer PublicID) {
        try {
            // TODO: 01.12.2022 записываем режим синъронизации
                Intent IntentStartFaceApp = new Intent();
                IntentStartFaceApp.putExtra("ID", PublicID);
                IntentStartFaceApp.putExtra("ПубличноеИмяПользовательДлСервлета", ПубличноеЛогин);
                IntentStartFaceApp.putExtra("ПубличноеПарольДлСервлета", ПубличноеПароль);
                IntentStartFaceApp.setClass(getApplication(), MainActivityBootAndAsync.class);
                IntentStartFaceApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);/// FLAG_ACTIVITY_SINGLE_TOP

                startActivity(IntentStartFaceApp);
                finish();

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " PublicID " +PublicID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void методЗаписываемПубличныйID(@NonNull Integer ПубличноеID) {
        try {
            // TODO: 02.08.2023 БИЗНЕС КОД
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("ПубличноеID", ПубличноеID);
            editor.apply();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ПубличноеID " +ПубличноеID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void методЗаписываемПервыйЭтапСинхрогниазции( ) {
        try {
            // TODO: 02.08.2023 БИЗНЕС КОД
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("РежимЗапускаСинхронизации", "СамыйПервыйЗапускСинхронизации");
            editor.apply();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    ///todo метод визуализацци успешных и не успешных аунтифиуаци пользоватле
    private void МетодВизуальногоОтображениеРаботыКоннекта(String СтатусДляПользователя, @NonNull View v) {
        runOnUiThread(new Runnable() {
            public void run() {
                Log.d(this.getClass().getName(), " handlerВизуализацияАунтификации ");
                try {
                    методHideКлавиатура();
                    Drawable icon;
                    ПодсчетПолощительиОтрцательРезультатов++;
                    // TODO: 29.09.2023 Програсс бар
                    методПрограссБАр();

                    Snackbar snackbar = Snackbar.make(v, " " + СтатусДляПользователя + " (" + ПодсчетПолощительиОтрцательРезультатов + ") ", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Log.d(this.getClass().getName(), " ОшибкаПриПодключениекСерверуДляАунтификацииПользователяПриВходе ");
                    if (ПодсчетПолощительиОтрцательРезультатов > 4) {////ПОПЫТКИ НЕ УДАЧНОГО ВХОДА В ПРОГРАММУ СВЫШЕ 5  СООБШАЕМ ПОЛЬЗОВАТЛЮ ЧТО ЕГО ИММ ЯИ ИЛИ ПАРОЛЬ НЕ ПРАВИЛЬНЫЙ И ПРИЛОЖЕНИЕ ОПРАЫЛЕМ В СОН
                        ПодсчетПолощительиОтрцательРезультатов = 0;
                        ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                        Snackbar.make(v, " Сон на 10 секунд.....", Snackbar.LENGTH_LONG).show();
                        КнопкаВходавСистему.setEnabled(false);
                        КнопкаВходавСистему.setClickable(false);
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

    private void методHideКлавиатура() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void методПрограссБАр() {
        try{
        ПрогрессБарДляВходаСистему.setIndeterminate(false);
        ПрогрессБарДляВходаСистему.setVisibility(View.INVISIBLE);
        ПрогрессБарДляВходаСистему.refreshDrawableState();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }







    // TODO: 29.09.2023  Класс Сохранение Пароля
    class ClassSavePassword{
        // TODO: 29.09.2023  пароль
      private   Disposable disposableSave;
    @SuppressLint("CheckResult")
    void metodSavePassword(){
        // TODO: 29.09.2023 Save and Ayntifization
  try{
        RxView.clicks(  КнопкаВходавСистему)
                .throttleFirst(3,TimeUnit.SECONDS)
                .filter(s -> !s.toString().isEmpty())
                .map(new Function<Unit, Object>() {
                    @Override
                    public Object apply(Unit unit) throws Throwable {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        return    КнопкаВходавСистему;
                    }
                })
                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getApplicationContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                })
                .onErrorComplete(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Log.e(getApplicationContext().getClass().getName(),
                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        return false;
                    }
                })
                .subscribe( GetPasswordServer-> {
                    ///todo revboot

                    Completable.fromSupplier(()->GetPasswordServers())
                            .subscribeOn(Schedulers.single())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                    // TODO: 29.09.2023 Запустился
                                    ПрогрессБарДляВходаСистему.setVisibility(View.VISIBLE);// при нажатии делаем видимый програсссбар
                                    ПрогрессБарДляВходаСистему.refreshDrawableState();
                                    ПрогрессБарДляВходаСистему.forceLayout();
                                }

                                @Override
                                public void onComplete() {
                                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        //deprecated in API 26
                                        v2.vibrate(50);
                                    }
                                    // TODO: 29.09.2023 Програсс бар
                                    методПрограссБАр();
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + GetPasswordServer );


                });
    } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }

    }


    Boolean GetPasswordServers(){
        Boolean ФлагЕслиРАзрешенияКамераИлиНет=false;
        try{
// TODO: 29.09.2023 ВТОРОЙ Observavle самой операции аунтификации и сохрание пароля

            ПубличноеЛогин = ИмяДляВходаСистему.getText().toString().trim();///получаем из формы имя для того чтобы постучаться на сервер
            Log.d(getPackageName().getClass().getName(), "ПубличноеИмяПользовательДлСервлета " + ПубличноеЛогин);

            ПубличноеПароль = ПарольДляВходаСистему.getText().toString().trim();///////получаем из формы пароль для того чтобы постучаться на сервер
            Log.d(getPackageName().getClass().getName(), "ПубличноеПарольДлСервлета " + ПубличноеПароль);

            // TODO: 29.09.2023 пароль и логин
            if (ПубличноеЛогин.length() > 3 && ПубличноеПароль.length() > 3) {
                boolean ПроверкаНАстройкиСети =
                        new Class_Find_Setting_User_Network(getApplicationContext(),sqLiteDatabase).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                if (ПроверкаНАстройкиСети == true) {
                    Boolean РеальныйПингСервера =
                            new Class_Connections_Server(getApplicationContext()).МетодПингаСервераРаботаетИлиНет(getApplicationContext(),   getHiltPortJboss);
                    // TODO: 07.10.2023 пинг сервера
                    if (РеальныйПингСервера == true) {
                            // TODO: 15.09.2023 ОБРАБОТКА ПАРОЛИ
                            методGetПарольОбработка(КнопкаВходавСистему);
                            Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                                    " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName() +
                                    "  РеальныйПингСервера " +РеальныйПингСервера + " ФлагЕслиРАзрешенияКамераИлиНет " +ФлагЕслиРАзрешенияКамераИлиНет);

                        // TODO: 15.09.2023 end password
                    } else {
                        МетодВизуальногоОтображениеРаботыКоннекта("Сервер выкл !!!", КнопкаВходавСистему);
                    }
                } else {
                    МетодВизуальногоОтображениеРаботыКоннекта("Интернет выкл !!!",КнопкаВходавСистему);
                }
            } else {
                МетодВизуальногоОтображениеРаботыКоннекта(" Повторите Логин и пароль ", КнопкаВходавСистему);
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ФлагЕслиРАзрешенияКамераИлиНет );
    } catch (Exception e) {
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  ФлагЕслиРАзрешенияКамераИлиНет;
    }






    // TODO: 29.09.2023 Классс после УСПЕШНО уантификайции переходимм в саму программу  faceapp

    class  ClassAfterForfardFaseApp{
        // TODO: 29.09.2023 метод перехода после успепешно аунификации и переход в саму программа
     void       ClassAfterForfardFaseApp(@NonNull   Integer PublicID,@NonNull View v){
         try{

             Integer ЗаписьВSuccesLogin=       методЗаписьВSuccesLogin(PublicID, v);//Integer ПолученинныйПубличныйID
         // TODO: 29.09.2023 SuccesLogin
             if (  ЗаписьВSuccesLogin>0  ) {

                 Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                         " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()   + " ЗаписьВSuccesLogin " +ЗаписьВSuccesLogin);


                 Integer ЗаписьВSettingTabel=       методЗаписьВSettingTabel(PublicID, v);//Integer ПолученинныйПубличныйID
                 // TODO: 29.09.2023 SettingTabel


                 if (ЗаписьВSettingTabel>0) {//TODO после  успешного получение от сервер Public ID для текущего проекта

                     методЗаписываемПубличныйID(PublicID);

                     методВходВFaceApp(PublicID);
                 }

             }else {

                 Toast.makeText(getApplicationContext(), "Ошибка в системных таблицах !!!", Toast.LENGTH_SHORT).show();
             }
         Log.d(this.getClass().getName(), " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                 " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                 " Класс  :" + Thread.currentThread().getStackTrace()[2].getClassName()   + " ЗаписьВSuccesLogin " +ЗаписьВSuccesLogin);

     } catch (Exception e) {
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }

    }

// TODO: 07.10.2023  Class дополнительно проверки камеры и место располежения


    // TODO: 23.01.2024 EventBus for Async
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusAyns(MessageEvensBusAyns messageEvensBusAyns){
        try{

            eventBuss  .getEventBusManagerAsync(messageEvensBusAyns);



            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    // TODO: 23.01.2024 EventBus for Update PO
    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){
        try{

            eventBuss. getEventBusUpdatePo(messageEvensBusUpdatePO);

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



}








