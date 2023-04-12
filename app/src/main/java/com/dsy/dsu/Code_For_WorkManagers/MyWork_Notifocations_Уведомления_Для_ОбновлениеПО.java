package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteCursor;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MyWork_Notifocations_Уведомления_Для_ОбновлениеПО extends Worker {
   private WorkerParameters workerParams;
    private   NotificationManager mNotificationManagerДляОбновлен=null;
    private  Integer СервернаяВерсияПОВнутри=0;
    private Integer ЛокальнаяВерсияПО=0;
    private  String ИмяСлужбыУведомленияДляОбновлениеПО = "WorkManager NOtofocationforUpdateSoft";
    private Integer ОбщееКоличествоНЕпрочитанныхСтрок = 0;
    private NotificationCompat.Builder builder_СлужбаОбновлениеПо = null;
    private  CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  Class_GRUD_SQL_Operations class_grud_sql_operationsIDпользоввателяДляСлужб;
    private SimpleDateFormat ФоорматДат ;
    private  StringBuffer БуферСамиУведомленияЛинкСамиУведомления;
    private  int     ID_ТаблицаУвендомлений;
    private  Intent ИнтентДляЗапускаСлужбыПолсеАнализа;
    private  Boolean РезультатНужноЗапускатьУведомленияИлиНет=false;
    private String PROCESS_ID_UpdateSoft="19";
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private SharedPreferences preferences;

    public MyWork_Notifocations_Уведомления_Для_ОбновлениеПО(@NonNull Context context, @NonNull WorkerParameters workerParamsвнутри) {
        super(context, workerParamsвнутри);
        workerParams=workerParamsвнутри;
        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new     PUBLIC_CONTENT(context);
        class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(context);
        //preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
  }
    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(this.getClass().getName(), " onStopped ()  ");
    }
    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(getApplicationContext().getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }

    // TODO: 17.11.2021  ГЛАВНЫЙ МЕТОД КЛАССА WORK MANEGER  ДЛЯ УВЕЛОДОМЛЕНИЯ ТОЛЬКО ДЛЯ ЧАТА
    @NonNull
    @Override
    public Result doWork() {
        try {
            Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            МетодЗапукаВоркМенеджераБезАктивити();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка  MyWork_MyWork_Notifocations_Уведомления_Для_ОбновлениеПО  public Result doWork()    ДЛЯ ЧАТА ");
        }
        if (СервернаяВерсияПОВнутри >ЛокальнаяВерсияПО ) {
            return Result.success();
        } else {
            // TODO: 24.11.2021
            return Result.failure();
        }

    }

    private void МетодЗапукаВоркМенеджераБезАктивити() throws ExecutionException, InterruptedException {
  try {
      // TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ ОБНОВЛЕНИЕ ПО
      Log.i(this.getClass().getName(), "       МетодДополнительногоУдалениеJSONФайлов();   удалаение JSON файла АНАЛИЗ ВЕРСИИЯ КАКАЯ ВЕРСИЯ ы");
      Log.w(getApplicationContext().getClass().getName(), "              " +
              "new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодПовторногоЗапускаВсехWorkManagerДляОбновленияПО();" +
              "\n" + "СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
    } catch (Exception e ) {
        e.printStackTrace();
      Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
              + Thread.currentThread().getStackTrace()[2].getLineNumber());
      new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
              Thread.currentThread().getStackTrace()[2].getLineNumber());
  }}








    /////////////////


    private void МетодЗарускаСозданиеУведомленийОбновлениеПо( ) {
        try{
                Log.i(getApplicationContext().getClass().getName(),
                        " СервернаяВерсияПОВнутри  WORK manager "+ СервернаяВерсияПОВнутри);
            ////TODO получаем и записываем локальную верисю ПО
            PackageInfo pInfo = null;
            try {
                pInfo = getApplicationContext(). getPackageManager().getPackageInfo(getApplicationContext(). getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String version = pInfo.versionName;//Version Name
            Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code

           // final Object versio = BuildConfig.VERSION_CODE;
        //   Integer ЛокальнаяВерсияПО = Integer.parseInt(versio.toString());
            Log.d(this.getClass().getName(), "\n"+"##################################"+"\n"+
                    "ПОЛУЧАЕМ ДВЕ ВЕРСИИ ПРОГРАММ ЛОКАЛЬНО И СЕРВЕРНОЙ  SOFT "+"\n"+
                            " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО+
                    "\n"+  "  СервернаяВерсияПОВнутри " +СервернаяВерсияПОВнутри);

///////////TODO ПРИСТУПАЕМ К ЗАПУСКУ ОБНОВЛЕНИЕ ФАЙЛА . APK ТОЛЬКО КОГДА ВЕРСИЯ ДАННЫХ НА СЕРВЕРЕ БОЛЬШЕ ЧЕМ НА КЛИЕНТЕ (Android)
            if (СервернаяВерсияПОВнутри >ЛокальнаяВерсияПО ) {
                getApplicationContext().getMainExecutor().execute(()->{
                    Boolean ПринудительныйЗапросОбновлениеПО=   getInputData().getBoolean("ПринудительныйЗапросОбновлениеПО",false);
                    if (ПринудительныйЗапросОбновлениеПО) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Поиск ПО...", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM, 0, 40);
                        toast.show();
                    }
                });
                    //////TODO МЕТОД КОТОРЫЙ ЗАПУСКАЕТ УВЕДОМЛЕНИЯ ПОСЛЕ АНАЛИЗА ДАТ
                    МетодКоторыйЗапускаетУвеломленияПослеАнализа(СервернаяВерсияПОВнутри);//  //ФлагКтоЗапустилСлужбу
                // TODO: 04.01.2022
                    Log.d(this.getClass().getName(),"\n"+"##################################"+"\n"+
                            "ПОЛУЧАЕМ ДВЕ ВЕРСИИ ПРОГРАММ ЛОКАЛЬНО И СЕРВЕРНОЙ   SOFT"+"\n"+
                            " НАДО СКАЧАТЬ ...ЗАПУСК ПОСЛЕ АНАЛИЗА ДАТ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ  СЛУЖБА  Синхронизация   " + " ВРЕМЯ " + new Date()
                            + "\n" + " РезультатНужноЗапускатьУведомленияИлиНет " + РезультатНужноЗапускатьУведомленияИлиНет +
                            "\n" +  " СервернаяВерсияПОВнутри " +СервернаяВерсияПОВнутри+
                            " \n"+
                             " ЛокальнаяВерсияПО " +ЛокальнаяВерсияПО + "\n"+"##################################"+"\n");
                }else{
                Log.d(this.getClass().getName(), " НЕ НАДО ВЕСИЯ СЕРВЕРНАЯ НИЖЕ ИЛИ РАВНА СКАЧАТЬ ...ЗАПУСК ПОСЛЕ АНАЛИЗА ДАТ ЗАПУСКАЕМ УВЕДОМЛЕНИЯ  СЛУЖБА  Синхронизация   "
                        + " ВРЕМЯ " + new Date()
                        + "\n" + " РезультатНужноЗапускатьУведомленияИлиНет " + РезультатНужноЗапускатьУведомленияИлиНет +  " СервернаяВерсияПОВнутри " +СервернаяВерсияПОВнутри+
                        " \n"+
                        " ЛокальнаяВерсияПО " +ЛокальнаяВерсияПО );
                    getApplicationContext().getMainExecutor().execute(()->{
                        Boolean ПринудительныйЗапросОбновлениеПО=   getInputData().getBoolean("ПринудительныйЗапросОбновлениеПО",false);
                        if (ПринудительныйЗапросОбновлениеПО) {
                            Toast toast = Toast.makeText(getApplicationContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM, 0, 40);
                            toast.show();
                        }
                    });
                }
                Log.d(getApplicationContext().getClass().getName(), " Определили Результат НужноЗапускать Уведомления Или Нет  СЛУЖБА"
                        + "--" + РезультатНужноЗапускатьУведомленияИлиНет);/////
            Log.d(this.getClass().getName(), "AsyncРезультатНужноЗапускатьУведомленияИлиНет " );
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА onDestroy() Exception ");

        }
    }

    private void МетодКоторыйЗапускаетУвеломленияПослеАнализа(Integer СервернаяВерсияПОВнутри) {
        try{
        Log.d(this.getClass().getName(), "Результат Нужно Запускать Уведомления Или Нет СЛУЖБА  true and false :: СервернаяВерсияПОВнутри  " +
                СервернаяВерсияПОВнутри);
        ActivityManager ЗапущенныйПроуессыДляУведомленийОбновлениеПО = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        if (ЗапущенныйПроуессыДляУведомленийОбновлениеПО!=null) {
            МетодНотификайшенОбнолвениеПО();
            Log.d(getApplicationContext().getClass().getName(), " СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
        }
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА onDestroy() Exception ");

    }



    }

/////////TODO запуск нновую нотификашенс устанолвка
























































    private void МетодНотификайшенОбнолвениеПО() {
        try {
            Log.d(getApplicationContext().getClass().getName(), " Создание Уведомлеения СЛУЖБА СЛУЖБА Service_Notificatios_Уведомления_ОбновлениеПО ");
            PackageManager pm = getApplicationContext().getPackageManager();
            builder_СлужбаОбновлениеПо = null;
            PendingIntent ЗапускаемОбновлениеПо = МетодЗапускаОбновленияПОИзУведомления(pm);
            PendingIntent ЗапускЗакрываемУведомлениеПоОбновление = ЗакрываемУведомленияПоОбновлениПО(pm);
            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК MyWork_Notifocations_Уведомления_ДляОбновлнение ПО  СЛУЖБА     " +
                    "           Service_Notifocations_Для_Чата.enqueueWork(getApplicationContext(),intentСлужбаУведомленийДЛЯЧата);;");
            Log.d(this.getClass().getName()," СервернаяВерсияПОВнутри"+ СервернаяВерсияПОВнутри);
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

            Boolean СтатустУведомленияЧистаяОбновлениПО=false;
                    StatusBarNotification[] statusBarNotificationОбновлениПО=      notificationManager.getActiveNotifications();
            // TODO: 21.12.2021
            for(StatusBarNotification statusBarNotification1: statusBarNotificationОбновлениПО){
                // TODO: 21.12.2021
              if (statusBarNotification1.getId()==Integer.parseInt(PROCESS_ID_UpdateSoft)){
                  // TODO: 21.12.2021
                СтатустУведомленияЧистаяОбновлениПО=   statusBarNotification1.isClearable();
                  // TODO: 21.12.2021
                  Log.d(this.getClass().getName()," СтатустУведомленияЧистаяОбновлениПО"+ СтатустУведомленияЧистаяОбновлениПО);
              }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");
        }
    }

    @androidx.annotation.Nullable
    private PendingIntent МетодЗапускаОбновленияПОИзУведомления(PackageManager pm) {
        // TODO: 17.11.2021 БЛОК КОДА РЕАЛИЗАЦИЯ БУДУШЕГО ЗАПУСКА ПРИ НАЖАТИИ НА УВЕДОСЛЕНИЕ ИЛИ НА КНОПКИ ЗАПУСКАЕТ С УВЕДОМЛЕНИЯ РАЗЛИЧНЫЕ ДЕЙСТВИЯ
        PendingIntent ЗапускаемОбновлениеПо = null;


        try {

            Intent notificationIntentДляУведомленийОбновлениеПоЗагрузить;
            notificationIntentДляУведомленийОбновлениеПоЗагрузить = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
            notificationIntentДляУведомленийОбновлениеПоЗагрузить.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            notificationIntentДляУведомленийОбновлениеПоЗагрузить.setAction("ЗагрузитьНовоеПо");
            Log.i(getApplicationContext().getClass().getName(), "СервернаяВерсияПОВнутри  " + СервернаяВерсияПОВнутри);
            if (СервернаяВерсияПОВнутри > 0) {
                notificationIntentДляУведомленийОбновлениеПоЗагрузить.putExtra("НоваяВерсияСерверногоПОПОслеУспешнойЗагрузки", СервернаяВерсияПОВнутри);
            }
            if (notificationIntentДляУведомленийОбновлениеПоЗагрузить.resolveActivity(pm) != null) {
                ЗапускаемОбновлениеПо = PendingIntent.getService(getApplicationContext(),
                        8, notificationIntentДляУведомленийОбновлениеПоЗагрузить,
                        PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT); //PendingIntent.FLAG_UPDATE_CURRENT
                // TODO: 17.11.2021
                // TODO: 17.11.2021
                Log.i(getApplicationContext().getClass().getName(), " Загружаем   СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //    mNotificationManagerДляОбновлен.cancel(1);///.cancelAll();

            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");

        }

///TODO
        return ЗапускаемОбновлениеПо;
    }

    @androidx.annotation.Nullable
    private PendingIntent ЗакрываемУведомленияПоОбновлениПО(PackageManager pm) {
        ////TODO

        PendingIntent ЗапускЗакрываемУведомлениеПоОбновление = null;

        try {
            Intent notificationIntentДляУведомленийОбновлениеЗакрываем;
            // TODO: 17.11.2021
            notificationIntentДляУведомленийОбновлениеЗакрываем = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
            notificationIntentДляУведомленийОбновлениеЗакрываем.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //notificationIntentЗакрыть.addCategory(Intent.CATEGORY_LAUNCHER);
            notificationIntentДляУведомленийОбновлениеЗакрываем.setAction("ЗакрываемУведомлениеоНовомПО");
            ///////

            if (notificationIntentДляУведомленийОбновлениеЗакрываем.resolveActivity(pm) != null) {
                ЗапускЗакрываемУведомлениеПоОбновление = PendingIntent.getService(getApplicationContext(),
                        9, notificationIntentДляУведомленийОбновлениеЗакрываем,
                        PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT); //PendingIntent.FLAG_UPDATE_CURRENT
                // TODO: 17.11.2021
                Log.i(getApplicationContext().getClass().getName(), " Закрываем   СНАРУЖИ Broadcatrecever (intent.getAction()   СЛУЖБА");
                // Service_Notifocations_Для_Чата.enqueueWork(getApplicationContext(),notificationIntentДляУведомленийЗакрываем);
            }


        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            //    mNotificationManagerДляОбновлен.cancel(1);///.cancelAll();

            Log.d(getApplicationContext().getClass().getName(), " Стоп СЛУЖБА СЛУЖБАService_Notifications ДЛЯ ЧАТА  onDestroy() Exception ");

        }
///TODO
        return ЗапускЗакрываемУведомлениеПоОбновление;
    }


    ///TODO метод определяем стоит запускать и создвать службу напоминаний или нет

    private boolean МетодВычисляемСтоитСоздаватьИЗапускатьСлужбуНапоминаний() {
        // final boolean[] РезультатВнутриЗапускатьУведомленияИлиНет = {false};
        try {



            ////

            Log.d(this.getClass().getName(), "  СЛУЖБА..... ДЛЯ проверяем нужно ли создвать и запускать службу нпоминаний работа с датами");

            /////todo если не изместен локальная версия являеться null перед проверкой то еще раз применяем выясняем локальную версиюПО для проверки

            long РезультатОбновлениеЛокальнойВерсииПО = 0;
            /////////
            /////TODO когда локальное ПО версию не нашли вытаемся ее увидить в базе

            int ПолучениеПравДляТаблицыПрава=0;

            Integer ПубличноеIDПолученныйИзСервлетаДляUUID=0;

            // TODO: 26.05.2021  перед обработкой уведомлений функция удаления Уведомлениц Сообщений не относящиеся к Пользователю
            ///
            Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(getApplicationContext());
            // TODO: 03.11.2021
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(getApplicationContext());

            ///
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
            ///////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
            //

            ////
            class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////
            SQLiteCursor Курсор_ВычисляемПУбличныйID=null;
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ

            Курсор_ВычисляемПУбличныйID= (SQLiteCursor)
                    class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                            new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,public_contentменеджер.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );

            Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );


            /////
            if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                //////////
                Курсор_ВычисляемПУбличныйID.moveToFirst();

                ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);

                // TODO: 03.11.2021



                Log.w(this.getClass().getName(), "  ID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            }


            // TODO: 26.05.2021 данные по высичлению ID



            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

            ///
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_chat");
            ///////
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","status_write=?  AND id_user=? " +
                    " AND message IS NOT NULL    AND chat_uuid IS NOT NULL    ");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",0);
            //
            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",  ПубличноеIDПолученныйИзСервлетаДляUUID);




            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
            ////
            // class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            ///
            SQLiteCursor   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата=null;


            Курсор_ДляСлужбыУведомлений_ТолькоДляЧата= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );


            ////////

            Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "  +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);





            // TODO: 26.05.2021 данные по высичлению ID


/*
            // TODO: 09.09.2021   ____old
             Курсор_IDпользоввателяДляСлужб=
                                    CREATE_DATABASE.ССылкаНаСозданнуюБазу.rawQuery("SELECT *  From notification", null);

*/


            ОбщееКоличествоНЕпрочитанныхСтрок=0;


            // TODO: 09.09.2021  resltat

            if ( Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount()>0) {
                //////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ТолькоДляЧат количествро сттрочек  :::  "
                        +Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount());


                Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToFirst();


                РезультатНужноЗапускатьУведомленияИлиНет=true;

                ОбщееКоличествоНЕпрочитанныхСтрок=       Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getCount();

                Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                // TODO: 19.11.2021
                if(ОбщееКоличествоНЕпрочитанныхСтрок>0){
                    // TODO: 19.11.2021
                    ОбщееКоличествоНЕпрочитанныхСтрок=ОбщееКоличествоНЕпрочитанныхСтрок-1;
                    // TODO: 19.11.2021

                    Log.w(this.getClass().getName(), "  ОбщееКоличествоНЕпрочитанныхСтрок  " +ОбщееКоличествоНЕпрочитанныхСтрок);
                }
                // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника

                МетодПолучениеДатыПослеПолучениеПравСотрудникаДляУведомления(Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);




            } else {
                Log.w(this.getClass().getName(), "  СЛУЖБА...  НЕТ СООБЩЕНИ ДЛЯ ОТОБРАЖЕНИЯ В ЧАТЕ НЕТ ПРОПУСКАЕМ ХОЛОСТОЙ ХОД СЛУЖБЫ УВЕДОМЛЕНИЯ ЧАТ " +ПолучениеПравДляТаблицыПрава);

                // TODO: 15.11.2021
                РезультатНужноЗапускатьУведомленияИлиНет=false;

            }




        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);


        }

        // TODO: 20.05.2021  результат
        return РезультатНужноЗапускатьУведомленияИлиНет ;
    }











    // TODO: 20.05.2021  продолжение уведомления определяем даты после того как получили права на сотрудника



    private void МетодПолучениеДатыПослеПолучениеПравСотрудникаДляУведомления(SQLiteCursor  Курсор_ДляСлужбыУведомлений_ТолькоДляЧата) throws ParseException {
        // TODO: 20.05.2021 действие вьторое

        try{

            Log.d(this.getClass().getName(), "   Курсор_ДляСлужбыУведомлений_ТолькоДляЧата "+Курсор_ДляСлужбыУведомлений_ТолькоДляЧата);


            ////TODO start do

            //TODO перед созданеим
            БуферСамиУведомленияЛинкСамиУведомления=null;

            БуферСамиУведомленияЛинкСамиУведомления=new StringBuffer();

            do {
                ////
                /////

                /////
                Integer КтоНаписалСообщениеID  = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getInt(6);
                //
                String КтоНаписалСообщениеФИО=new String();

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеID " + КтоНаписалСообщениеID);


                // TODO: 16.11.2021 find FIO
                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб=new Class_GRUD_SQL_Operations(getApplicationContext());

                ///
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","chat_users");
                ///////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name");
                //
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","_id=?   AND name IS NOT NULL ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",КтоНаписалСообщениеID);

    /*            class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
                ////
                class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","5");*/

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                ///
                SQLiteCursor   Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал=null;


                Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал= (SQLiteCursor)  class_grud_sql_operationsIDпользоввателяДляСлужб.
                        new GetData(getApplicationContext()).getdata(class_grud_sql_operationsIDпользоввателяДляСлужб. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу() );


                ////////

                Log.d(this.getClass().getName(), "Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал "  +Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал);

                if (Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getCount()>0) {
                    //TODO

                    Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.moveToFirst();


                    КтоНаписалСообщениеФИО=      Курсор_ДляСлужбыУведомлений_ВычисляемНстоящееФИОКтоНаписал.getString(0).trim();
                }

                ////////

                Log.d(this.getClass().getName(), "КтоНаписалСообщениеФИО "  +КтоНаписалСообщениеФИО);





                // TODO: 16.11.2021  само сообщение
                /////
                String СамиУведомленияВЧатеНеПрочитанный = Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(2).trim();

                Log.d(this.getClass().getName(), "СамиУведомленияВЧатеНеПрочитанный " + СамиУведомленияВЧатеНеПрочитанный);

                // TODO: 15.11.2021

                String ВремНеПРочитаногоСообщения= Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.getString(7);

                Integer ИндексПоследнейТОчки=    ВремНеПРочитаногоСообщения.lastIndexOf(".");

                ВремНеПРочитаногоСообщения=ВремНеПРочитаногоСообщения.substring(0,ВремНеПРочитаногоСообщения.lastIndexOf(":"));

                Log.d(this.getClass().getName(), " ВремНеПРочитаногоСообщения  " + ВремНеПРочитаногоСообщения);
                // TODO: 15.11.2021

                Log.d(this.getClass().getName(), " ВремНеПРочитаногоСообщения  " + ВремНеПРочитаногоСообщения);


                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("ru"));
                //


                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                Date date = null;
                date = simpleDateFormat.parse(ВремНеПРочитаногоСообщения);

                simpleDateFormat.applyPattern(" dd EEEE yyyy HH:mm");//dd-MM-yyyy  // EEEE yyyy HH:mm"

                String ФиналВремНеПРочитаногоСообщения = simpleDateFormat.format(date);

                Log.d(this.getClass().getName(), "ФиналВремНеПРочитаногоСообщения " + ФиналВремНеПРочитаногоСообщения+"\n"+
                        "  СамиУведомленияВЧатеНеПрочитанный  " +СамиУведомленияВЧатеНеПрочитанный);

                ///TODO ДАТА ОТ СЕРВЕРА
                //////todoСамиУведомленияВЧатеНеПрочитанный.matches("[а-яА-Я]")///[m-nM-N]
                if (СамиУведомленияВЧатеНеПрочитанный.matches(("(.*)[а-яА-Я](.*)"))) {

                    БуферСамиУведомленияЛинкСамиУведомления.append("\n").
                            append("от: ").append(КтоНаписалСообщениеФИО).append("\n")
                            .append( "сообщение: " ).append(СамиУведомленияВЧатеНеПрочитанный).append("\n")
                            .append("время : ").append(ФиналВремНеПРочитаногоСообщения).append("\n")
                            .append("(").append("+").append(ОбщееКоличествоНЕпрочитанныхСтрок) .append(")").append(" сообщений.");
                }

                Log.d(this.getClass().getName(), "СамиУведомления " + БуферСамиУведомленияЛинкСамиУведомления.toString() +
                        " Дата_Начала_ТаблицаУвендомленийТекст " + БуферСамиУведомленияЛинкСамиУведомления.length());

                // TODO: 19.11.2021  после обработки одно записи выходим

                break;

                //todo while
            } while (Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.moveToNext());


            // TODO: 19.11.2021 после обработки добавляем

            // БуферСамиУведомленияЛинкСамиУведомления.append(" +").append("(").append(ОбщееКоличествоНЕпрочитанныхСтрок) .append(")").append(" сообщений.") ;
            // TODO: 19.11.2021
            Log.d(this.getClass().getName(), "СамиУведомления " + БуферСамиУведомленияЛинкСамиУведомления.toString() +
                    " Дата_Начала_ТаблицаУвендомленийТекст " + БуферСамиУведомленияЛинкСамиУведомления.length());


            ////TODO закрываем курсор

            if (!Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.isClosed()) {
                Курсор_ДляСлужбыУведомлений_ТолькоДляЧата.close();
            }




            // TODO: 19.05.2021  ошибки
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            NotificationManager notificationManager = (NotificationManager)
                    getApplicationContext().getSystemService(NOTIFICATION_SERVICE);


        }




    }











    // TODO: 17.11.2021  end classs worl manager

}






























