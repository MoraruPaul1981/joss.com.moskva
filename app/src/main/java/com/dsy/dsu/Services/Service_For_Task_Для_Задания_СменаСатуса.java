package com.dsy.dsu.Services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;


import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClass_ДляСменыСтатусаНаЗадачиВыполненыйОтказОтмененный;
import com.dsy.dsu.Tasks.MainActivity_Tasks;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;


public class Service_For_Task_Для_Задания_СменаСатуса extends IntentService {////Service

    ////////
    private String PROCESS_ID;
    private  String ИмяСлужбыУведомленияДляЗадача;
    // TODO: 07.02.2022
    private    HashMap<String, String> hashMapХэшДляЗапоминиялUUID = new HashMap();
    // TODO: 12.10.2021  Ссылка Менеджер Потоков
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    // TODO: 07.02.2022
    private  Long UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ;
    // TODO: 07.02.2022
    private Integer ПередаемСтатусзадачи;
    // TODO: 24.03.2022
    private  String ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу;
    // TODO: 24.03.2022
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    // TODO: 24.03.2022
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";

    private Service_For_Task_Для_Задания_СменаСатуса.LocalBinderДляСлужбыСменаСтатуса binder = new Service_For_Task_Для_Задания_СменаСатуса.LocalBinderДляСлужбыСменаСтатуса();
    private Context context;
    private SQLiteDatabase sqLiteDatabase ;
    public Service_For_Task_Для_Задания_СменаСатуса() {
        //TODO
        super("Service_For_Task_Для_Задания_СменаСатуса");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinderДляСлужбыСменаСтатуса extends Binder {
        public Service_For_Task_Для_Задания_СменаСатуса getService() {
            // Return this instance of LocalService so clients can call public methods
            return Service_For_Task_Для_Задания_СменаСатуса.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return   binder;

    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }
// TODO: 16.11.2021

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {

            this.context=getApplicationContext();
            Log.d(getApplicationContext().getClass().getName(), " onStartCommand СЛУЖБА Service_Notifocations_Для_ЗАДАЧА  "
                    + " время: "
                    + new Date() + intent+ " intent ");
// TODO: 26.03.2022 запуск код только при ЗАКРЫТИЕ
            МетодpЗапускСлужбыДляЗадач( getApplicationContext() ,intent);
            // TODO: 27.03.2022
            Log.i(getApplicationContext().getClass().getName(), "ЗапускСогласованияПришедшегоЗАДАНИЕ   " +
                    "........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " + new Date() + "\n" +
                    "  intent.getAction() " + intent.getAction());
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_Notifications_ДЛЯ ЧАТА  ДЛЯ ЧАТА onHandleWork Exception  PROCESS_ID   " + PROCESS_ID);

        }


      ///  return super.onStartCommand(intent, flags, startId);
    }



    public Boolean МетодpЗапускСлужбыДляЗадач(@NonNull Context context, @NonNull Intent intent) {
        Boolean РезультатСменыСтатусаНаОзнакомленный = false;
        try {
            this.context=context;
            Log.i(context.getClass().getName(), "ЗапускСогласованияПришедшегоЗАДАНИЕ   " +
                    "........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА"  + " время запуска  " + new Date() + "\n" +
                    "  intent.getAction() " + intent.getAction());
            Log.d(context.getClass().getName(), " onStartCommand СЛУЖБА bundleДляПришлиВСлужбу ЗАДАЧА  " +
                 " ntent.getAction() " + intent.getAction());
            // TODO: 24.03.2022
         Bundle   bundleДляПришлиВСлужбу = intent.getExtras();
            if (intent.getAction().equalsIgnoreCase("ЗапускаемИзмененияСатусазадачиВыполнил")) {
                // TODO: 07.02.2022
                PROCESS_ID = bundleДляПришлиВСлужбу.getString("PROCESS_ID_Задачи");
                Log.i(getApplicationContext().getClass().getName(), "" + " PROCESS_ID" + PROCESS_ID);
                // TODO: 24.03.2022
                ИмяСлужбыУведомленияДляЗадача = bundleДляПришлиВСлужбу.getString("ИмяСлужбыУведомленияДляЧата_Задачи");
                Log.i(getApplicationContext().getClass().getName(), "" + " ИмяСлужбыУведомленияДляЧата" + ИмяСлужбыУведомленияДляЗадача);
                // TODO: 24.03.2022
                UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ = bundleДляПришлиВСлужбу.getLong("UUIDПолучениейЗадачи", 0l);  // TODO: 24.03.2022
                Log.i(getApplicationContext().getClass().getName(), "" + " UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ " + UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ);
                // TODO: 24.03.2022
                ПередаемСтатусзадачи = bundleДляПришлиВСлужбу.getInt("ДляЗадачиПередаемФлагВыполненаЗадчаИлиОтказ", 0);  // TODO: 24.03.2022//
                Log.i(getApplicationContext().getClass().getName(), "" + " ПередаемСтатусзадачи " + ПередаемСтатусзадачи);
                // TODO: 24.03.2022
                ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу = bundleДляПришлиВСлужбу.getString("СамоПримечание");
                Log.i(context.getClass().getName(), "" + " ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу" + ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу);
                bundleДляПришлиВСлужбу.clear();




                // TODO: 27.03.2022  задача  запускаеми смены статуса
                РезультатСменыСтатусаНаОзнакомленный=   МетодСменаСтатусаЗадачи(context, intent);
                
                Log.i(getApplicationContext().getClass().getName(), "" + " ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу" + ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу+
                        " РезультатСменыСтатусаНаОзнакомленный " +РезультатСменыСтатусаНаОзнакомленный);
            }else{
                // TODO: 05.07.2022 ПРОСТО ЗАПУСКАЕМ ПРИЛОЖЕНИЕЯ ЗАДАЧИ
                if (intent.getAction().equals("ИзУведомленияЗадачаПереходимВАктивтиЗадача")) {
                    // TODO: 07.02.2022
                    NotificationManager notificationManager = (NotificationManager)
                            getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
// TODO: 26.03.2022  запуск активти из задания из уведомления
                    Intent notificationIntentДляЗапускаЗаданияИзУведомленияПослеКлика;
                    notificationIntentДляЗапускаЗаданияИзУведомленияПослеКлика = new Intent(getApplicationContext(), MainActivity_Tasks.class);
                    notificationIntentДляЗапускаЗаданияИзУведомленияПослеКлика.setAction(Intent.ACTION_SCREEN_ON);
                    notificationIntentДляЗапускаЗаданияИзУведомленияПослеКлика.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundleДЛяПереходаВСамоЗаданеиИзУведомления = intent.getExtras();
                    PROCESS_ID = bundleДЛяПереходаВСамоЗаданеиИзУведомления.getString("PROCESS_ID_УведомленияПлановая");
                    context.startActivity(notificationIntentДляЗапускаЗаданияИзУведомленияПослеКлика);
                    Log.i(context.getClass().getName(), "ЗапускСогласованияПришедшегоЗАДАНИЕ   " +
                            "........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " +
                            "  PROCESS_ID " + PROCESS_ID);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_Notifications_ДЛЯ ЧАТА  ДЛЯ ЧАТА onHandleWork Exception  PROCESS_ID   " + PROCESS_ID);

        }
     return  РезультатСменыСтатусаНаОзнакомленный;

    }

    private Boolean МетодСменаСтатусаЗадачи(@NonNull Context context, @NonNull Intent intent) {
        // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
       // TODO метоД СМЕНЫ СТАТУСА ПОЛЬЗОВАТЕЛЕМ КАК ОЗНАКОМЛЕННЫЙ
        Boolean РезультатСменыСтатусаНаОзнакомленный = false;
        try {
            // TODO: 26.03.2022

            Log.i(context.getClass().getName(), "ЗапускСогласованияПришедшегоЗАДАНИЕ   " +
                    "........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА" + (intent.getAction().toString()) + " время запуска  " + new Date() + "\n" +
                    "  intent.getAction() " + intent.getAction());
            // TODO: 18.04.2021 запувскает широковещатель

            Log.i(context.getClass().getName(), " ЗапускПаузы ........ СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА" +
                    (intent.getAction().toString()) + " время запуска  " + new Date() + "\n" +
                    "  intent.getAction() " + intent.getAction() +
                    " \n" + " UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ " + UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ);

            // TODO: 07.02.2022  после выполНЕНИЯ ЗАДАЕНИЕ ОДНУЛЯЕМ uuid ДЛЯ СМЕНЫ СТАТУСА
            // TODO: 29.03.2022  сама завпись
            // TODO: 29.03.2022
                РезультатСменыСтатусаНаОзнакомленный = new SubClass_ДляСменыСтатусаНаЗадачиВыполненыйОтказОтмененный().
                        МетодСменыСтатусаНаОзкомленныйЗадениеСамимПользователем(getApplicationContext(),
                                UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ
                                , ПередаемСтатусзадачи,
                                ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу);


            Log.w(context.getClass().getName(), " конец обоработки статсуса ознакомленый  РезультатСменыСтатусаНаОзнакомленный " +
                    "" + (РезультатСменыСтатусаНаОзнакомленный +
                    " \n" + " UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ " + UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ + "ПередаемСтатусзадачи " + ПередаемСтатусзадачи +
                    "  ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу "
                    + ПримечаниеВыполнилКлиентИлиНетЗадачуПришлиВСлужбу +" РезультатСменыСтатусаНаОзнакомленный " +РезультатСменыСтатусаНаОзнакомленный));

            // TODO: 20.06.2022 если успешаня смена статуса то запускаем сихронизацию и firebase
            if (РезультатСменыСтатусаНаОзнакомленный == true) {
                /////todo NOtofication
                Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v2.vibrate(50);
                }

                // TODO: 05.07.2022

                NotificationManager      notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                // notificationManager.cancelAll();
                notificationManager.cancel(Integer.parseInt(PROCESS_ID));

                stopForeground(true);

                ///   Toast.makeText(getApplicationContext(), " Статус изменен  !!!", Toast.LENGTH_LONG).show();

                // TODO: 05.07.2022  третье действие апускаем OneSIGNGNAL
                МетодЗапускаOneSignalandFirebaseПослеУсепшнойСменаСтутсаЗадачи(context);
            }

        } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_Notifications_ДЛЯ ЧАТА  ДЛЯ ЧАТА onHandleWork Exception  PROCESS_ID   "+PROCESS_ID);

    }
      return  РезультатСменыСтатусаНаОзнакомленный;

    }


    // TODO: 30.06.2022  пользовательский код







       private void МетодЗапускаOneSignalandFirebaseПослеУсепшнойСменаСтутсаЗадачи(@NonNull Context context) {
            try{
                Log.i(context.getClass().getName(), "SubClass_FindПоискIDОтКогоЗаданияДляКогоЗапускатFirebase"+new Date());
                Class_GRUD_SQL_Operations      class_grud_sql_operations = new Class_GRUD_SQL_Operations(getApplicationContext());
                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков=new PUBLIC_CONTENT(getApplicationContext());
                 class_grud_sql_operations.
                         concurrentHashMapНабор.put("ПодЗапросНомер1",
                                " SELECT  user_update FROM view_tasks   WHERE uuid  =" + UUIDДляЗапускСогласованияПришедшегоЗАДАНИЕ + " ;" );// current_table    ///   date_update current_table     ASC
                // TODO: 19.06.2022  ГЛАВНЫЙ КУРСОР ЧАТА
                SQLiteCursor      КурсорДанныеДлязаписиичтнияЧата=null;

                КурсорДанныеДлязаписиичтнияЧата = (SQLiteCursor) class_grud_sql_operations.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operations.
                                concurrentHashMapНабор,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        , sqLiteDatabase);
                if (КурсорДанныеДлязаписиичтнияЧата != null) {
                    if (КурсорДанныеДлязаписиичтнияЧата.getCount() > 0) {
                        КурсорДанныеДлязаписиичтнияЧата.moveToFirst();
                        // TODO: 20.06.2022
               Integer IDКтоСоздалзадачуИдЛЯКогоЗапускатьFirebase= КурсорДанныеДлязаписиичтнияЧата.getInt(0);
                        Log.i(context.getClass().getName(), "  IDКтоСоздалзадачуИдЛЯКогоЗапускатьFirebase  "+IDКтоСоздалзадачуИдЛЯКогоЗапускатьFirebase);

                        if (!WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                            // TODO: 20.06.2022  запускаем fibase для полдьзоываткля
                            WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                    WorkManager.getInstance(getApplicationContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                            if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) != 0) {

// TODO: 26.03.2023 start sync
                                // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
                                Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                                Data myDataSingleWorker = new Data.Builder()
                                        .putInt("ПубличныйID", ПубличныйIDДляАсих)
                                        .putBoolean("StartSingleWorker", true)
                                        .build();
                                // TODO: 02.08.2022
                                // TODO: 02.08.2022
                                new Class_Generator_One_WORK_MANAGER(getApplicationContext()).МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),myDataSingleWorker);
                                // TODO: 26.06.2022
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " ПубличныйIDДляАсих "+ПубличныйIDДляАсих );

                            }
                            Log.i(context.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  " + WorkInfoИнформацияОЗапущенойСлужбеОдноразовая+
                                    " IDКтоСоздалзадачуИдЛЯКогоЗапускатьFirebase " +IDКтоСоздалзадачуИдЛЯКогоЗапускатьFirebase);//todo super.onBind(intent)
                        }
                    }
                    Log.i(context.getClass().getName(), " void МетодПолучениеДанныхдляФрагментаЧитатьиПисатьЧат() throws ExecutionException, InterruptedException {" +
                            "    КурсорДанныеДлязаписиичтнияЧата    " +КурсорДанныеДлязаписиичтнияЧата);//todo super.onBind(intent)
                    КурсорДанныеДлязаписиичтнияЧата.close();

                }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_Notifications_ДЛЯ ЧАТА  ДЛЯ ЧАТА onHandleWork Exception  PROCESS_ID   "+PROCESS_ID);

        }
        }



    // TODO: 07.02.2022 класс смены статуса как ознакомленый


}