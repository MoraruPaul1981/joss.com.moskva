package com.dsy.dsu.Code_For_Services;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.Class_Visible_Processing_Async;
import com.dsy.dsu.Business_logic_Only_Class.Class__Generation_Genetal_Tables;
import com.dsy.dsu.Business_logic_Only_Class.Jakson.GeneratorJSONSerializer;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.dsy.dsu.Business_logic_Only_Class.SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicDouble;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Service_For_Remote_Async extends IntentService {
    public LocalBinderДляТабеля binderBinderRemoteAsync = new LocalBinderДляТабеля();
    private Context context;
    private    Messenger messengerCallBacks;
    private  Integer МаксималноеКоличествоСтрочекJSON;
    private SharedPreferences preferences;
    private   String Проценты;
    private Integer ИндексВизуальнойДляPrograssBar=0;
    private      Integer ПубличныйIDДляФрагмента=0;

    private Service_For_Public.LocalBinderОбщий localBinderОбщий;

    ArrayList<ContentValues> АдаптерДляВставкиИОбновления;
    public Service_For_Remote_Async() {
        super("Service_For_Remote_Async");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        try{
       ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish().МетодПолучениеяПубличногоID(context);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента);
     context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            try{
                  Bundle data =msg.getData();
                  messengerCallBacks=msg.replyTo;
                  Intent intentДляФоновойСинхронизации=new Intent("ЗапусAsyncBackground");
                  intentДляФоновойСинхронизации.putExtras(data);
                  onHandleIntent(intentДляФоновойСинхронизации);
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        }
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
   public class LocalBinderДляТабеля extends Binder {
        public Service_For_Remote_Async getService() {
            // Return this instance of LocalService so clients can call public methods
            //   return Service_For_Remote_Async.this;
            return Service_For_Remote_Async.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
            super.linkToDeath(recipient, flags);
        }

        @Override
        public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
            return super.unlinkToDeath(recipient, flags);
        }
    }
    final Messenger messenger = new Messenger(new IncomingHandler());
/*
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return binder;
    }*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
  try{
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return messenger.getBinder();
    }
    @Override
    public void onDestroy() {
        try{
        super.onDestroy();
        stopSelf();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context =getApplicationContext();
            // TODO: 25.03.2023 ДОПОЛНИТЕОТНЕ УДЛАНИЕ СТАТУСА УДАЛЕНИЕ ПОСЛЕ СИНХРОНИАЗЦИИ
            МетодБиндинuCлужбыPublic();
        CompletableFuture.supplyAsync(new Supplier<Object>() {
            @Override
            public Object get() {
                // TODO: 28.09.2022  запускаем службу табелей
                МетодAsyncИзСлужбы(context);
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                return null;
            }
        }).exceptionally(e -> {
                    System.out.println(e.getClass());
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                    return e;
                }).complete(this.getClass().getName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
// TODO: 30.06.2022 сама не постредствено запуск метода
    }



    public Integer МетодAsyncИзСлужбы(@NonNull Context context) {
        Integer ФинальныйРезультатAsyncBackgroud=0;
        try {
            if( this.context==null){
                this.context=context;
            }
            МетодБиндинuCлужбыPublic();
            // TODO: 16.11.2022
            ФинальныйРезультатAsyncBackgroud  = new Class_Engine_SQL(context).МетодЗАпускаФоновойСинхронизации(context);
            Log.d(context.getClass().getName(), "\n"
                    + "   ФинальныйРезультатAsyncBackgroud " + ФинальныйРезультатAsyncBackgroud);
            МетодПослеAsyncTaskЗавершающий( context);
            // TODO: 26.03.2023 дополнительное удаление после Удаление статсу удалнеенон
            if (ФинальныйРезультатAsyncBackgroud>0) {
                МетодПослеСинхрониазцииУдалениеСтатусаУдаленный();
            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "    ФинальныйРезультатAsyncBackgroud "+ФинальныйРезультатAsyncBackgroud);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return ФинальныйРезультатAsyncBackgroud;
    }

    private void МетодПослеСинхрониазцииУдалениеСтатусаУдаленный() {
        try {
            Intent intentПослеСинхроницииРегламентаняРаботаУдалениеДанных=new Intent();
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setClass(context, Service_For_Public.class);
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
            while (localBinderОбщий==null);
            // TODO: 25.03.2023 дополнительное удаление после синхрониазции статус Удаленныц
            localBinderОбщий.getService().МетодГлавныйPublicPO(context,intentПослеСинхроницииРегламентаняРаботаУдалениеДанных);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +   "  localBinderОбщий  " +localBinderОбщий);

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    private void МетодПослеAsyncTaskЗавершающий( @NonNull Context context) {
        try{
        // TODO: 05.11.2022  после  ВЫПОЛЕНИЯ СИНЗХРОНИАЗИИ СООБЩАЕМ ОБ ОКОНЧАТИИ СИХРОНИАЗЦИИ ВИЗУАЛЬТА
        new Class_Engine_SQL(context).МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,МаксималноеКоличествоСтрочекJSON,
                Проценты,null,"ФинишВыходИзAsyncBackground",false,false);
        Log.d(context.getClass().getName(), "\n" + " МаксималноеКоличествоСтрочекJSON: " +МаксималноеКоличествоСтрочекJSON );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


































// TODO: 10.10.2022 КЛАСС ГЛАВНОЙ СИНХРОНИАЗЦИИИ

//////-------TODO  ЭТО ПЕРВЫЙ КОНТРОЛЛЕР КОТОРЫЙ ВИЗУАЛИЗИРУЕТ СИНХРОНИЗАЦИЮ С ПРОГРАССБАРОМ---------------------------------------------------------------


///TODO------------------------------------------------------ ЭТО  ВТОРОЙ КОНТРОЛЛЕР КОТОРЫЙ ЗАПУСКАЕТ СИНХРОНИЗАЦИЮ В ФОНЕ  (ВНУТРИ ПРИЛОЖЕНИЕ)---------------------------------------------------


    protected class Class_Engine_SQL extends Class_MODEL_synchronized {
        // TODO: 28.07.2022  переменые
        public    Context context;
        private    Integer ПубличныйРезультатОтветаОтСерврераУспешно=0;
        private CopyOnWriteArrayList<String> ГлавныеТаблицыСинхронизации =new CopyOnWriteArrayList();
        private  SQLiteDatabase СсылкаНаБазуSqlite =null;
        private  PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации;
        private boolean ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА = false;
        private  String ФлагКакуюЧастьСинхронизацииЗапускаем =new String();
        private   ContentValues ТекущийАдаптерДляВсего =null;
        private  Integer ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы=0;
        // TODO: 28.07.2022
        public Class_Engine_SQL(@NotNull Context context)
                throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            super(context);
            this.context=context;
            public_contentДатыДляГлавныхТаблицСинхронизации=new PUBLIC_CONTENT(context);
            СсылкаНаБазуSqlite =new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу();
            Log.w(context.getClass().getName(), "Create_Database_СамаБАзаSQLite_КЛОННастоящейБазы" + СсылкаНаБазуSqlite);
        }

        // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
        public Integer МетодЗАпускаФоновойСинхронизации(@NotNull Context context) throws InterruptedException {
            Integer      РезультатаСинхронизации = 0;
           
            try{this.context=context;
                ГлавныеТаблицыСинхронизации = 
                        new Class__Generation_Genetal_Tables(context).МетодЗаполеннияТаблицДЛяРаботыиСинхрониазции();
                Log.d(this.getClass().getName(), "  ГлавныеТаблицыСинхрониазции " + ГлавныеТаблицыСинхронизации.size());
                // TODO: 28.09.2022 запускам синхрогниазцию
                РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации(ГлавныеТаблицыСинхронизации);
                Log.w(this.getClass().getName(), " ФОНОВАЯ СИНХОРОНИЗАЦИИИ ИДЁТ... СЛУЖБА "+РезультатаСинхронизации);

                if (РезультатаСинхронизации==0){
                    РезультатаСинхронизации=ПубличныйРезультатОтветаОтСерврераУспешно;
                }
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


// TODO: 19.08.2021  ДАННЫЙ МЕТОД ЗАПУСКАЕТ СИНХРОНИЗЦИ ДЛЯ ЧАТА

// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
















        ///TODO САМ ФОНОВЫЙ ПОТОК МЕТОД

        Integer МетодСамогоФоновойСинхронизации(@NonNull  CopyOnWriteArrayList ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат) {
            String ТекущаяТаблицаДляОБменаДанными = null;
            Integer ФинальныйРезультаФоновойСинхрониазции=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsМетодСамогоФоновойСинхронизации=new Class_GRUD_SQL_Operations(context);
            try {
                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Synchronizasiy_Data " + new Date() +
                        "\n" + " Build.BRAND " + Build.BRAND.toString()+" СколькоСтрочекJSON " );
                //////TODO ШАГ ТРЕТИЙ  ЗАПУСКАЕМ САМУ СИНХРОНИЗАЦИЮ  сама синхронизация в фоне
                ФинальныйРезультаФоновойСинхрониазции =            МетодНачалоСихронизациивФоне(context); ////Получение Версии Данных Сервера для дальнейшего анализа

                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                        ПубличныйРезультатОтветаОтСерврераУспешно +  "  ФинальныйРезультаФоновойСинхрониазции " +ФинальныйРезультаФоновойСинхрониазции);
                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                        ФинальныйРезультаФоновойСинхрониазции);
                if(ФинальныйРезультаФоновойСинхрониазции==0)
                {
                    ФинальныйРезультаФоновойСинхрониазции=   ПубличныйРезультатОтветаОтСерврераУспешно;
                }
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

        Integer МетодНачалоСихронизациивФоне(@NotNull Context context) throws InterruptedException, ExecutionException, TimeoutException, JSONException {
            Integer результатСинхрониазции=0;
            try {
                результатСинхрониазции=     МетодПолучениеIDотСервераДляГеренированиеUUID(); ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
                Log.d(this.getClass().getName(), " результатСинхрониазции " + результатСинхрониазции);
                if(результатСинхрониазции==null){
                    результатСинхрониазции=0;
                }
                if (результатСинхрониазции==0){
                    результатСинхрониазции=      ПубличныйРезультатОтветаОтСерврераУспешно;
                }
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
        Integer МетодПолучениеIDотСервераДляГеренированиеUUID() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
            String ДанныеПришёлЛиIDДЛяГенерацииUUID = new String();
            Integer РезультатСинхрониазции=0;
            ПубличноеIDПолученныйИзСервлетаДляUUID=0;
            try {
                Log.d(this.getClass().getName(), " public   void МетодПолучениеIDОтСервераДляГеренированиеUUID ()" + " ДанныеПришёлЛиIDДЛяГенерацииUUID "
                        + ДанныеПришёлЛиIDДЛяГенерацииUUID +
                        " ДанныеПришёлЛиIDДЛяГенерацииUUID.length()  " + ДанныеПришёлЛиIDДЛяГенерацииUUID.length());
                Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(context);
                PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","id");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИ
                SQLiteCursor     Курсор_ВычисляемПУбличныйID= (SQLiteCursor)  class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,public_contentменеджер.МенеджерПотоков, СсылкаНаБазуSqlite);
                Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );
                StringBuffer БуферПолучениеДанных = new StringBuffer();
                if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                    Курсор_ВычисляемПУбличныйID.moveToFirst();
                    ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);
                    Log.w(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                }
                Log.w(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                Курсор_ВычисляемПУбличныйID.close();
                // TODO: 09.09.2022  запускаем синхрониазцию
                if (ПубличноеIDПолученныйИзСервлетаДляUUID > 0) {
                    ////TODO создаем списко таблиц запускаем слуд.ющий метод получение версии базы данных
                    РезультатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID));//получаем ID для генерирования UUID
                    if (РезультатСинхрониазции == null) {
                        РезультатСинхрониазции = 0;
                    }
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
        Integer МетодПолучениеСпискаТаблицДляОбменаДанными( String ДанныеПришёлЛиIDДЛяГенерацииUUID)
                throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ

            Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID" + ДанныеПришёлЛиIDДЛяГенерацииUUID);
            String ДанныеПришлаСпискаТаблицДляОбмена = new String();
            StringBuffer БуферModification_server = new StringBuffer();
            Integer РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=0;
            try {
                preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                if (preferences==null) {
                    Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных =
                            new Class_Connections_Server(context).         МетодПингаСервераРаботаетИлиНет(context);
                    preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                }
                PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                // TODO: 10.11.2022 Получение Список Таблиц
                БуферModification_server = МетодУниверсальныйДанныесСервера("view_data_modification",
                        "", "",
                        "application/gzip",//application/json
                        "Хотим Получить Версию Данных Сервера",
                        0l,
                        ДанныеПришёлЛиIDДЛяГенерацииUUID,
                        0l,
                        ИмяСерверИзХранилица ,ПортСерверИзХранилица,new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераАунтификация());   //// БуферПолученнниеДанныхОтМетодаGET.mark(1000); // save the data we are about to readБуферПолученнниеДанныхОтМетодаGET.reset(); // jump back to the marked position
                Log.d(this.getClass().getName(), " БуферModification_server.toString().toCharArray().length "
                        + БуферModification_server.toString().toCharArray().length);
                // TODO: 03.09.2021
                if (БуферModification_server != null) {
                    if (БуферModification_server.toString().toCharArray().length > 3) {
                        Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID +
                                " БуферModification_server " + БуферModification_server.toString());
                        JSONObject ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента = new JSONObject(БуферModification_server.toString());///упаковываем в j
                        Log.d(this.getClass().getName(), "  ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента  " +
                                ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента);
                        JSONArray МассивJSONТаблиц = ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.names();
                        String НазваниеИзПришедшихТаблицДляКлиента;
                        String СодержимоеИзПришедшихТаблицДляКлиента;
                        String JSONСтрочка;
                        String JSONНазваниеСтолбика;
                        String JSONСодержимоеСтолика;
                        Long JSONСодержимоеСтоликаДляХэша=0l;
                        public_contentДатыДляГлавныхТаблицСинхронизации.     ДатыТаблицыВерсииДанныхОтСервера= Collections.synchronizedMap(new LinkedHashMap<String, Long>());
                        public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.clear();
                        for (int ИндексТаблицыДляДанногоКлиента = 0; ИндексТаблицыДляДанногоКлиента < ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.names().length(); ИндексТаблицыДляДанногоКлиента++) {
                            НазваниеИзПришедшихТаблицДляКлиента = МассивJSONТаблиц.getString(ИндексТаблицыДляДанногоКлиента);
                            СодержимоеИзПришедшихТаблицДляКлиента = ОбьектыJSONТаблицыПришлиКонктетоНаЭтогоКлиента.getString(НазваниеИзПришедшихТаблицДляКлиента); // Here's
                            JSONObject ОбьектJSON = new JSONObject(СодержимоеИзПришедшихТаблицДляКлиента);
                            JSONСтрочка = String.valueOf(ОбьектJSON.names());
                            /////ЦИКЛ КОТРЫЙ БЕЖИТ ПО СТОЛБЦАМ  ПРИГЕДШЕГО JSON ФАЙЛА И НАХОДИМ НАЩШИ ТАЮЛИЦЫ ДЛЯ УКАЗАННОГО ПОЛЬЗОВАТСЯ
                            for (int ИндексТаблицыДляДанногоКлиентаСтолбцы = 0; ИндексТаблицыДляДанногоКлиентаСтолбцы < ОбьектJSON.length(); ИндексТаблицыДляДанногоКлиентаСтолбцы++) {
                                JSONНазваниеСтолбика = String.valueOf(ОбьектJSON.names().get(ИндексТаблицыДляДанногоКлиентаСтолбцы));
                                JSONСодержимоеСтолика = ОбьектJSON.getString(JSONНазваниеСтолбика);
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ИМЯ ИЗ МОДИФИКАЦИИ СЕРВЕР")) {////&& !JSONСодержимоеСтолика.equalsIgnoreCase("fio")///&& !JSONСодержимоеСтолика.equalsIgnoreCase("fio")
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.add(JSONСодержимоеСтолика); //////ЗАПОЛЯНЕМ АРАЙЛИСТ НАЗВАНИЕМ ТОЛЬКО ТАБЛИЦ КОТОРЫ ПРИШИ ДЛЯ КОНКТНОГО ПОЛЬЗОВАТЕЛЯ
                                    Log.d(this.getClass().getName(), " JSONСодержимоеСтолика " + JSONСодержимоеСтолика);
                                }
                                /////А ТУТ МЫ ПРОСТО ЗАПОМИНАЕМ НАЗВАНИЕ ТАБЛИЦ С СЕРВЕРА  И ПЛЮС ИХ ДАТЫ ПОСЛЕДНЕГО ИЗМЕНЕНИЕ ДАННЫХ НА ДАННЫХ ТАБЛИЦАХ НА СЕРВЕРЕ
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ИМЯ ИЗ МОДИФИКАЦИИ СЕРВЕР")) {
                                    JSONСодержимоеСтоликаДляХэша = ОбьектJSON.getLong("ТЕКУЩАЯ ВЕРСИЯ  ТАБЛИЦЫ");/////ТОЛЬКО ДЛЯ HSMAP///"ДАТА ВЕРСИИ СЕРВЕРА"
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.put(JSONСодержимоеСтолика,JSONСодержимоеСтоликаДляХэша); ///// ЗАПОЛНЯЕМ ХЭШМАП ДЛЯ КРНКРЕТНОГО ПОЛЬЗОВАТЕЛЯ ТАБЛИЦ ДЛЯ ТОЛЬКО СЕСИИ
                                    Log.d(this.getClass().getName(), " JSONСодержимоеСтолика " + JSONСодержимоеСтолика + "  JSONСодержимоеСтоликаДляХэша  " + JSONСодержимоеСтоликаДляХэша+
                                            "   PUBLIC_CONTENT.ДатыТаблицыВерсииДанныхОтСервера.size() " + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size());
                                }
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ПРОЕКТЫ")) {
                                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.add(JSONСодержимоеСтолика); //////ЗАПОЛЯНЕМ АРАЙЛИСТ НАЗВАНИЕМ ТОЛЬКО ТАБЛИЦ КОТОРЫ ПРИШИ ДЛЯ КОНКТНОГО ПОЛЬЗОВАТЕЛЯ
                                    Log.d(this.getClass().getName(), " ИменаПроектовОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.toString());
                                }
                                if (JSONНазваниеСтолбика.equalsIgnoreCase("ТЕКУЩАЯ ВЕРСИЯ  ТАБЛИЦЫ")) {
                                    Log.d(this.getClass().getName(), " ИменаПроектовОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаПроектовОтСервера.toString()+  "  JSONНазваниеСтолбика " +JSONНазваниеСтолбика);
                                }
                            }
                        }
                    } else {////ОШИБКА В ПОЛУЧЕНИИ С СЕРВЕРА ТАБЛИУЦЫ МОДИФИКАЦИИ ДАННЫХ СЕРВЕРА
                        Log.d(this.getClass().getName(), " Данных нет c сервера  ");
                    }
                }
                Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                        + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString() +
                        " ДатыТаблицыВерсииДанныхОтСервера " +public_contentДатыДляГлавныхТаблицСинхронизации.toString() +
                        "  ДанныеПришлаСпискаТаблицДляОбмена " +ДанныеПришлаСпискаТаблицДляОбмена);
                if ( public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size() > 0
                        && public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size()>0) {//ЕСЛИ МЫ ПОЛУЧИЛИ ID  и СОЗДАЛИ НА ЕГО БАЗЕ UUID ТО ПРОХОДИИМ К СЛЕДУЮЩЕМУ КОДУ ПОЛУЧАЕМ ВЕРСИЮ ДАННЫХ СЕРВВЕРА
                    //// TODO запускам если ОТ СЕРВЕРА ПРИШЛИ  ДАННЫЕ СПИСОК ТАБЛИЦ ДЛЯ СОЗДАНИЕ СПИСК ДЛЯ ПОЛЬЗОВАТЕЯД
                    Log.i(this.getClass().getName(), " ДанныеПришлаСпискаТаблицДляОбмена " + ДанныеПришлаСпискаТаблицДляОбмена+ "  PUBLIC_CONTENT.ДатыТаблицыВерсииДанныхОтСервера.size() " +
                            public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.size());
                    Log.i(this.getClass().getName(), "  ГЛАВНЫЙ ЦИКЛ НАЧИНАЕТСЯ.............. РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
                    ////TODO ТОЛЬКО НЕ ДЛЯ АКТИВТИ АНОНИМНЫЙ ОБМЕН БЕЗ ВИЗУАЛИЗАЦИИ СИНХРОНИЗАЦИИ
                    РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ= МетодГлавныхЦиклТаблицДляСинхронизации(ДанныеПришёлЛиIDДЛяГенерацииUUID);

                    if(РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ==null){
                        РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ=0;
                    }
                    Log.i(this.getClass().getName(), "  ГЛАВНЫЙ ЦИКЛ ПРОШЕЛ .............. РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String ОшибкаКоторуюПропускам=e.fillInStackTrace().getMessage().toString();
                if (!ОшибкаКоторуюПропускам.equalsIgnoreCase("null")) {
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            Log.i(this.getClass().getName(), " РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);
            return  РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ;
        }

// TODO: 10.09.2021  запускаем метод обработки по таблицам
        Integer МетодЗапускаСинхрониазцииПоАТблицам(String данныеПришёлЛиIDДЛяГенерацииUUID,
                                                    String текущаяТаблицаДляОБменаДанными,
                                                    CompletionService МенеджерПотоковВнутрений,
                                                    PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации) {
            Log.d(this.getClass().getName(), " ТекущаяТаблицаДляОБменаДанными " + текущаяТаблицаДляОБменаДанными);
            boolean ОтветЕслиТакаяТаблицаВнутриОбработкиДляПринятияРешениеНачинатьОбрабткуИлиНет = false;
            Integer   РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
            try {
                //////TODO метод обрабтки п таюлицам
                РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=
                        МетодДляАнализаВерсийДанныхПолучаемДатыСервера(текущаяТаблицаДляОБменаДанными, данныеПришёлЛиIDДЛяГенерацииUUID,
                                МенеджерПотоковВнутрений,public_contentДатыДляГлавныхТаблицСинхронизации); ////Получение Версии Данных Сервера для дальнейшего анализа
                if(РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера==null){
                    РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера=0;
                }
                Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера "
                        + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера;
        }























        /////////////////////ИЩЕМ ДАТУ СЕРВЕРВА
        Integer МетодДляАнализаВерсийДанныхПолучаемДатыСервера(@NonNull  String ТекущаяТаблицаДляОБменаДанными,
                                                               @NonNull  String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                               @NonNull  CompletionService МенеджерПотоковВнутрений,
                                                               @NonNull   PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации)
                throws JSONException, InterruptedException, ExecutionException, TimeoutException {
            final Integer[] РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера = {0};
///TODO принудительн устанвливаем редим работы синхронизации
            Log.d(this.getClass().getName(), " ДанныеПришёлЛиIDДЛяГенерацииUUID  " + ДанныеПришёлЛиIDДЛяГенерацииUUID + " ТекущаяТаблицаДляОБменаДанными "
                    + ТекущаяТаблицаДляОБменаДанными +
                    " public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера " + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера);
            try {
/////ТУТ -- КОД АНАЛИЗА ДАННЫХ SQL SERVER  ПРИШЕДШЕЙ ТЕКУЩЕЙ ТАБЛИЦЕ ПОЛУЧАЕМ НАЗВАНИЕ БАЗЫ И К НЕЙ ПОЛУЧАЕМ ДАТУ Е НЕЙ
                public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.entrySet().forEach(new Consumer<Map.Entry<String, Long>>() {
                    @Override
                    public void accept(Map.Entry<String, Long> ХэшДляАнализаТекущейТаблицыВерсииДанных) {
                        try{
                        Long Полученная_ВерсияДанныхсSqlServer = 0l;
                        JSONObject ОбьектыJSONФайлJSONсСервераВерсияSQlserver = new JSONObject();
                        String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = "";
                        String ИмитацияВремяДляПроверки;
                        Date ИмитациДатыДляПроверки = null;
                        String ТесктДатыSqlServer = null;
                        System.out.println(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey() + " - " + ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                        if (ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey().equalsIgnoreCase(ТекущаяТаблицаДляОБменаДанными)) {///ищем в текущей строчке текущуе название таблицы например CFO==CFO
                            ОбьектыJSONФайлJSONсСервераВерсияSQlserver.put(ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey(), ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue());
                            Log.d(this.getClass().getName(), " ОбьектыJSONФайлJSONсСервераВерсияSQlserver " + ОбьектыJSONФайлJSONсСервераВерсияSQlserver.toString());
                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных = ХэшДляАнализаТекущейТаблицыВерсииДанных.getKey();
                            Log.d(this.getClass().getName(), " ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных" + ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных);
                            Полученная_ВерсияДанныхсSqlServer = ХэшДляАнализаТекущейТаблицыВерсииДанных.getValue();
                            if (Полученная_ВерсияДанныхсSqlServer == null) {
                                Полученная_ВерсияДанныхсSqlServer = 0l;
                            }
                            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer   " + Полученная_ВерсияДанныхсSqlServer);
                            Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                    "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                            /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
                            //////////метод анализа данных
                            РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0]=
                                    МетодАнализаВресииДАнныхКлиента(ТекущаяТаблицаДляОБменаДанными,
                                            Полученная_ВерсияДанныхсSqlServer,
                                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных
                                            , ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                            МенеджерПотоковВнутрений);
                            /////////
                            Log.i(this.getClass().getName(), "  РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0]  " +  РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0]);



                            //
                            Log.d(this.getClass().getName(), " РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера " + РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0] +
                                    "  Полученная_ВерсияДанныхсSqlServer " + Полученная_ВерсияДанныхсSqlServer);
                        }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультаУспешнойВсатвкиИлиобвнлденияДаннымиССервера[0];
        }


        ////////////////////////////ДАННЫЙ МЕТОД ПОСЛЕ ВЫШЕ СТОЯШЕГО ВЫРАВНИЯНИЯ НАЗВАНИЙ ТАБЛИЦ ПРИСТУПАЕТ К САМОМУ АНАЛИЗУ ДАННЫХ ВЕРСИИ ДАННЫХ НАХОДЯЩИХСЯ НА АНДРОЙДЕ
        Integer МетодАнализаВресииДАнныхКлиента(String ИмяТаблицыОтАндройда_Локальноая,
                                                Long Полученная_ВерсияДанныхсSqlServer,
                                                String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                CompletionService МенеджерПотоковВнутрений) {

            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
            SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = null;
            Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная = 0l;
            Long ВерсииДанныхНаАндройдеСерверная = 0l;
            Integer РезультатУспешнойВсатвкиИлиОбвовлениясСервера=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаВресииДАнныхКлиента;
            try {
                class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);
                Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","name=? ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsgetdata
                        .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,МенеджерПотоковВнутрений,
                                СсылкаНаБазуSqlite);
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
                // TODO: 05.10.2021  КОГДА ВСЕ ДАННЫЕ ЕСТЬ ТРИ ПЕРЕМЕННЫЕ ПОЛУЧЕНИЕ ПЕРЕХОИМ ДАЛЬШЕ ПОЛЯ ЛОКАЛЬНАЯ ВЕРСИЯ ДАННЫХ, СЕРВЕНАЯ ВЕРСИЯ ДАННЫХ, И ТЕРТЬЯ ВЕРИСЯ С СЕРВЕРА ПО ДАННОЙ ТАБЕЛИЦВ
                Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+
                        "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                        +"   Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
                // TODO: 05.10.2021 ПРИ НАЛИЧИИ ВСЕХ ТРЕХ ПОЗИЦИЙ ЛОКАЛЬНАЯ ВЕРСИЯ С АНДРОЙДА   И СЕРВРНАЯ ВЕРСИЯ С АНДРОЙДА И  ПРИШЕДШЕЯ ВЕРСИЯ С СЕРВЕРА
                ///
                if (ВерсииДанныхНаАндройдеЛокальнаяЛокальная !=null  && ВерсииДанныхНаАндройдеСерверная!=null && Полученная_ВерсияДанныхсSqlServer!=null) {
                    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       МетодПринятияРешенияПолучитьДанныесСервераИлиОтправитьДанныесКлиента(
                            ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                            ВерсииДанныхНаАндройдеСерверная,
                            Полученная_ВерсияДанныхсSqlServer,
                            ИмяТаблицыОтАндройда_Локальноая,
                            ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                            МенеджерПотоковВнутрений);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    Log.d(this.getClass().getName(), "   РезультатУспешнойВсатвкиИлиОбвовлениясСервера " +РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                }else{

                    // TODO: 15.02.2022  НЕТ ДАННЫ Х ДЛЯ ОДМЕНА ПО ТАБЛИЦЫЕ ТЕКУЩЕЙ

                    new Handler(     context.getMainLooper()).post(()->{

                        Toast.makeText(context, "Нет данных для обмена текущие таблицы:  "+ИмяТаблицыОтАндройда_Локальноая , Toast.LENGTH_LONG).show();


                    });

                    Log.e(this.getClass().getName(), "   Нет данных для обмена текущие таблицы " +ИмяТаблицыОтАндройда_Локальноая);


                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }
            return  РезультатУспешнойВсатвкиИлиОбвовлениясСервера;
        }














        //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
        Integer МетодПринятияРешенияПолучитьДанныесСервераИлиОтправитьДанныесКлиента(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                                     Long ВерсииДанныхНаАндройдеСерверная,
                                                                                     Long ВерсияДанныхсСамогоSqlServer,
                                                                                     String ИмяТаблицыОтАндройда_Локальноая,
                                                                                     String ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных,
                                                                                     String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                                     CompletionService МенеджерПотоковВнутрений) {
            try {
                SubClassUpVersionDATA subClassUpVersionDATA=     new SubClassUpVersionDATA();
                if (ИмяТаблицыНаSqlServerИзТаблицыВерсииДанных.equalsIgnoreCase(ИмяТаблицыОтАндройда_Локальноая)) {//////ОБЯЗАТОЛЬНОЕ УСЛОВИЕ НАЗВАНИЕ ТАБЛИЦ ДОЛЖНО БЫТЬ ОДИНАКОВЫМ НАПРИМЕР  CFO==CFO
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

                    // TODO: 17.03.2023 Вресия Данных с Самого Сервера
                    ВерсияДанныхсСамогоSqlServer =      Optional.ofNullable(ВерсияДанныхсСамогоSqlServer).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(), " ВерсияДанныхсСамогоSqlServer" + ВерсияДанныхсСамогоSqlServer);
                    // TODO: 05.10.2021  POST()-->
                    if (ВерсияДанныхЛокальноЛокальная > ВерсияДанныхЛокальнаяСерверная &&
                            !ИмяТаблицыОтАндройда_Локальноая.matches("(.*)view(.*)")) {
                        Log.d(this.getClass().getName(),
                                " ВерсияДанныхЛокальноЛокальная  " + ВерсияДанныхЛокальноЛокальная +
                                        "  ВерсияДанныхЛокальнаяСерверная " + ВерсияДанныхЛокальнаяСерверная
                                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая "
                                        +ИмяТаблицыОтАндройда_Локальноая);
                        // TODO: 30.06.2022  конец встаялеммого кода с задержкой
                        Integer    ДанныеПосылаемНаСервер = МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(ИмяТаблицыОтАндройда_Локальноая,
                                МенеджерПотоковВнутрений, ВерсияДанныхЛокальнаяСерверная);

                        // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                "  ВерсияДанныхЛокальнаяСерверная "
                                + ВерсияДанныхЛокальнаяСерверная
                                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);

                        if(ДанныеПосылаемНаСервер>0 ){
                            ПубличныйРезультатОтветаОтСерврераУспешно=ДанныеПосылаемНаСервер;///"Серверный"
                            // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                          Integer РезультатПовышенииВерсииДанных = subClassUpVersionDATA.МетодVesrionUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context,getССылкаНаСозданнуюБазу());
                            Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                        }

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                "  ВерсияДанныхЛокальнаяСерверная "
                                + ВерсияДанныхЛокальнаяСерверная
                                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);
                        // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                    } else {
                        // TODO: 19.10.2021   GET()->
                        if (ВерсияДанныхсСамогоSqlServer > ВерсияДанныхЛокальнаяСерверная) {
                            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                            Integer         ДанныесСервера = МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                    ИмяТаблицыОтАндройда_Локальноая, ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                    МенеджерПотоковВнутрений,
                                    ВерсияДанныхЛокальнаяСерверная );
                            // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                    "  ВерсияДанныхЛокальнаяСерверная "
                                    + ВерсияДанныхЛокальнаяСерверная
                                    + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                    "  ДанныесСервера " +ДанныесСервера);
                            if(ДанныесСервера>0 ){
                                ПубличныйРезультатОтветаОтСерврераУспешно=ДанныесСервера;///"Серверный"
                                // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                                Integer РезультатПовышенииВерсииДанных = subClassUpVersionDATA.МетодVesrionUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context,getССылкаНаСозданнуюБазу());
                                Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                                // TODO: 27.03.2023  Удаление И Смена Статуса Когда НА сервере Поментили Удаление
                                МетодСерверноеУдаление(ИмяТаблицыОтАндройда_Локальноая);
                            }


                        }
                    }
                    // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                            ИндексВизуальнойДляPrograssBar,ИмяТаблицыОтАндройда_Локальноая,
                            Проценты,"ПроцессеAsyncBackground",
                            true,false);
                    // TODO: 20.03.2023  Запуск Метода Смены Статуса Удаление на Сервера
                    МетодОчисткаПеременныхПослеСинх();
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
            return  ПубличныйРезультатОтветаОтСерврераУспешно;
        }

        private void МетодОчисткаПеременныхПослеСинх() {
            try{
            if (ТекущийАдаптерДляВсего!=null) {
                ТекущийАдаптерДляВсего.clear();
                ТекущийАдаптерДляВсего=null;
            }
            if (АдаптерДляВставкиИОбновления!=null){
                АдаптерДляВставкиИОбновления.clear();
                ТекущийАдаптерДляВсего=null;
            }
                // TODO: 26.03.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }


        // TODO: 20.03.2023  метод смены статуса при удаление на СЕРВРЕРЕ
        private void МетодСерверноеУдаление(@NonNull String имяТаблицыОтАндройда_локальноая ) {
            Long результат_ОбновлениенымисСервераСменаСтатусаУдаления=0l;
            try{
                switch (имяТаблицыОтАндройда_локальноая) {
                    case "data_tabels":
                    case "tabel":
                    case  "get_materials_data" :
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatachangedeleting/" + имяТаблицыОтАндройда_локальноая + "");
                        //   Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + имяТаблицыОтАндройда_локальноая + "");
                        if (АдаптерДляВставкиИОбновления.size()>0) {
                            //TODO  ПОСЛЕ ОБРАБОТКИ ВСЕЙ ТАБЛИЦЫ ТЕСТОВО ЗАПУСКАЕМ ЕТОД МАССОВОЙ ВСТАВКИ ЧЕРЕЗ КОНТЕНТ ПРОВАЙДЕР МЕТОД BurkInset
                            Log.w(context.getClass().getName(), " АдаптерДляВставкиИОбновления.size()  " + АдаптерДляВставкиИОбновления.size()+
                                    "\n" + " АдаптерПриОбновленияДанныхсСервера.size()  " + ТекущийАдаптерДляВсего.size()+" uri  " + uri);/////
                            ContentResolver contentResolver  = context.getContentResolver();

                            ContentValues[] contentValuesМассив=new ContentValues[АдаптерДляВставкиИОбновления.size()];
                            contentValuesМассив=АдаптерДляВставкиИОбновления.toArray(contentValuesМассив);
                            int РезультатОбновлениеМассовой = contentResolver.bulkInsert(uri, contentValuesМассив);
                            // TODO: 27.10.2021
                            результат_ОбновлениенымисСервераСменаСтатусаУдаления = Long.valueOf(РезультатОбновлениеМассовой);
                            Log.d(this.getClass().getName(), " РезультатОбновлениеМассовой :::  "
                                    + РезультатОбновлениеМассовой+"\n"+
                                    "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая);
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        


        private Integer МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(String ИмяТаблицыОтАндройда_Локальноая,
                                                                       CompletionService МенеджерПотоковВнутрений
                , Long ВерсияДанныхПришлаПослеУспешнойСинхронизации) {
            Integer РезультатОтправкиДанныхНаСервер=0;
            try{
                Log.d(this.getClass().getName(), "  ВерсияДанныхПришлаПослеУспешнойСинхронизации   " + ВерсияДанныхПришлаПослеУспешнойСинхронизации );
                ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
                РезультатОтправкиДанныхНаСервер =
                        МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, ВерсияДанныхПришлаПослеУспешнойСинхронизации,
                                МенеджерПотоковВнутрений);
                Log.i(this.getClass().getName(), "   РезультатОтправкиДанныхНаСервер" + РезультатОтправкиДанныхНаСервер+
                        " ВерсияДанныхПришлаПослеУспешнойСинхронизации "+ВерсияДанныхПришлаПослеУспешнойСинхронизации);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОтправкиДанныхНаСервер;
        }

        @NonNull
        private Integer МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                                                         String ИмяТаблицыОтАндройда_Локальноая,
                                                                         String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                                         CompletionService МенеджерПотоковВнутрений,
                                                                         Long РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера) {
            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
            Integer  Результат_СсервераПолучаем_Сервер=0;
            try{
                Log.d(this.getClass().getName(), " НА SQL SERVER  ДАТА больше версия" +
                        "  ЛОКАЛЬНАЯ ВЕРСИЯ (последнего серверного обновления) ЧАТ  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                        + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера +
                        " и  ТЕКУЩАЯ СЕРВЕРНАЯ ВЕРСИЯ  ЧАТ РезультатВерсииДанныхЧатаНаСервере "  + ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 19.08.2021 уменьшаемм для повторгого повторной отправки
                //////////TODO МЕТОД get
                  Результат_СсервераПолучаем_Сервер =
                        МетодПолучаемДаннныесСервера(ИмяТаблицыОтАндройда_Локальноая,
                                РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                ВерсииДанныхНаАндройдеЛокальнаяЛокальная,
                                РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                МенеджерПотоковВнутрений);/// ЗАПУСКАМ МЕТОД ПОЛУЧЕНИЕ ДАННЫХ С СЕРВЕРА    МЕТОД GET


                Log.d(this.getClass().getName(), " ПОСЛЕ УСПЕШНОЙ ОТПАРВКИ ДАННЫХ НА СЕРВЕР" +
                        " Результат_СсервераПолучаем_Сервер " + Результат_СсервераПолучаем_Сервер
                        + "  ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая + "\n" +
                        "  РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера "
                        + РезультаПолученаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера+
                        " ВерсииДанныхНаАндройдеЛокальнаяЛокальная " +
                        ВерсииДанныхНаАндройдеЛокальнаяЛокальная);
                /////В ДАНОМ СЛУЧАЕ ДАННЫЕ СИНХРОНИЗИРОВАТЬ НЕ НАДО ВЕСРИЯ ДАННЫХ НА СЕРВРЕР И НА КЛИЕНТЕ ОДИНАКОВЫ
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Результат_СсервераПолучаем_Сервер;
        }

        // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
        /////МЕТОД КОГДА НА СЕРВЕРЕ ВЕРСИЯ ДАННЫХ ВЫШЕ И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВРА
        Integer МетодПолучаемДаннныесСервера(String имяТаблицыОтАндройда_локальноая,
                                             Long ВерсииДанныхНаАндройдеСерверная,
                                             String ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                             Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                ,Long  РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                                             CompletionService МенеджерПотоковВнутрений ) {

            Integer РезультатФоновнойСинхронизации=0;
            StringBuffer БуферПолученныйJSON = null;
            try {
                Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  имяТаблицыОтАндройда_локальноая" + имяТаблицыОтАндройда_локальноая);
                StringBuffer БуферПолучениеДанных = new StringBuffer();
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                            ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                            Проценты,"ПроцессеAsyncBackground",false,false);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 10.11.2022  Получение JSON-потока
                    БуферПолучениеДанных = МетодУниверсальныйДанныесСервера(имяТаблицыОтАндройда_локальноая, "",
                            "", "application/gzip", "Хотим Получить  JSON"
                            ,ВерсииДанныхНаАндройдеСерверная,//    ВерсииДанныхНаАндройдеСерверная,//37262l
                            ДанныеПришёлЛиIDДЛяГенерацииUUID,
                            РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер,
                            ИмяСерверИзХранилица
                            ,ПортСерверИзХранилица,
                            new PUBLIC_CONTENT(context).getСсылкаНаРежимСервераТабель());//TODO "http://192.168.254.40:8080/"      /      // TODO     "http://tabel.dsu1.ru:8888/"   original     "tabel.dsu1.ru", 8888);
                    Log.d(this.getClass().getName(), "  БУФЕР получаем даннные БуферПолучениеДанных.toString() " + БуферПолучениеДанных.toString());
                    if(БуферПолучениеДанных==null){
                        БуферПолучениеДанных = new StringBuffer();
                    }
                    Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  БуферПолучениеДанных" + БуферПолучениеДанных.toString()+"\n"
                            + "  БуферПолучениеДанных.length()" + БуферПолучениеДанных.length());

                if ( БуферПолучениеДанных.toString().toCharArray().length > 3) {
                    БуферПолученныйJSON = new StringBuffer();
                    Log.d(this.getClass().getName(), "  БуферПолучениеДанных.toString()) " + БуферПолучениеДанных.toString());

                    БуферПолученныйJSON.append(БуферПолучениеДанных.toString());
                    ////////Присылаем количестов строчек обработанных на сервлете
                    Log.d(this.getClass().getName(), " БуферПолученныйJSON.length()  " + БуферПолученныйJSON.length());
                    int Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы = 0;
                    Log.i(this.getClass().getName(), "   Результат_ПриписиИзменнийВерсииДанныхВФоне:"
                            + Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы + " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    //////TODO запускаем метод распарстивая JSON
                    РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(БуферПолученныйJSON, имяТаблицыОтАндройда_локальноая);
                    Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации  "  +РезультатФоновнойСинхронизации);
                } else {////ОШИБКА В ПОЛУЧЕНИИ С СЕРВЕРА ТАБЛИУЦЫ МОДИФИКАЦИИ ДАННЫХ СЕРВЕРА
                    Log.d(this.getClass().getName(), " Данных нет c сервера сам файл JSON   пришел от сервера БуферПолучениеДанных   "+БуферПолучениеДанных);
                }
                Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации "+РезультатФоновнойСинхронизации);
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
        Integer МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  StringBuffer БуферПолученныйJSON,
                                                   @NonNull  String имяТаблицыОтАндройда_локальноая) throws InterruptedException, JSONException {
            final Long[] РезультатРаботыСинхрониазциии = {0l};
            try {
                Log.d(this.getClass().getName(), " БуферПолученныйJSON " + БуферПолученныйJSON.toString());
              //  JsonObject     БуферJsonОтСервера = new PUBLIC_CONTENT(context).gson.fromJson(БуферПолученныйJSON.toString(), JsonObject.class);
        /*        JsonArray     БуферJsonОтСервера = new PUBLIC_CONTENT(context).gson.fromJson(БуферПолученныйJSON.toString(), JsonArray.class);
                        МаксималноеКоличествоСтрочекJSON = БуферJsonОтСервера.size();*/
            /*    Log.d(this.getClass().getName(), " МаксималноеКоличествоСтрочекJSON:::  "
                        + МаксималноеКоличествоСтрочекJSON+ " БуферJsonОтСервера " +БуферJsonОтСервера);*/
/*

                //TODO БУфер JSON от Сервера
                if (имяТаблицыОтАндройда_локальноая.equalsIgnoreCase("fio")) {
                    CopyOnWriteArrayList<?>  БуферJsonОтСервера2= new PUBLIC_CONTENT(context).getGeneratorJackson().readValue(БуферПолученныйJSON.toString(),
                            new TypeReference<   CopyOnWriteArrayList<Fio>>() {});
                    Log.d(this.getClass().getName(), " БуферJsonОтСервера2:::  " + БуферJsonОтСервера2+ " БуферJsonОтСервера " +БуферJsonОтСервера);
                }
*/

                    //TODO БУфер JSON от Сервера
                CopyOnWriteArrayList<Map<String,String>>  БуферJsonОтСервера= new PUBLIC_CONTENT(context).getGeneratorJackson().readValue(БуферПолученныйJSON.toString(),
                                new TypeReference<   CopyOnWriteArrayList<Map<String,String>>>() {});
                // TODO: 26.03.2023  Количество Максимальное СТРОК
                МаксималноеКоличествоСтрочекJSON = БуферJsonОтСервера.size();
                Log.d(this.getClass().getName(), " МаксималноеКоличествоСтрочекJSON:::  " + МаксималноеКоличествоСтрочекJSON+ " БуферJsonОтСервера " +БуферJsonОтСервера);
                
                // TODO: 11.10.2022 callback метод обратно в актвити #1
                МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON, ИндексВизуальнойДляPrograssBar, имяТаблицыОтАндройда_локальноая,
                        Проценты, "ПроцессеAsyncBackground",false,false);
                ИндексВизуальнойДляPrograssBar=0;
                // TODO: 04.12.2022 ПАРСИНГ ПО НОВОМУ ОТ JAKSON
            Flowable.fromIterable(БуферJsonОтСервера)
                        .onBackpressureBuffer(true)
                        .buffer(500)
                                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).doOnNext(new io.reactivex.rxjava3.functions.Consumer<List<Map<String, String>>>() {
                        @Override
                        public void accept(List<Map<String, String>> maps) throws Throwable {
                            // TODO: 13.01.2023  ОБРАБОТКА ИЗ БУФЕРА
                            АдаптерДляВставкиИОбновления = new ArrayList<>();//JSON_ПерваяЧасть.names().length()
                            // TODO: 26.03.2023 Строчки JSON
                            Flowable.fromIterable(maps)
                                    .onBackpressureBuffer(true)
                                    .subscribeOn(Schedulers.computation())
                                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Map<String, String>>() {
                                        @Override
                                        public void accept(Map<String, String> stringStringMap) throws Throwable {
                                            try {
                                                ТекущийАдаптерДляВсего = new ContentValues();
                                                // TODO: 06.10.2022  ВНУТрений СТрочка обработки данных сами Столбикки
                                                Log.d(this.getClass().getName(),  " stringStringMap  "  +stringStringMap   +new Date().toGMTString().toString());
                                                // TODO: 14.11.2022  ОБРАБОТКА ВТОРОЙ ДАНЫХ САМИ СТОЛБИКИ
                                                Flowable.fromIterable(stringStringMap.entrySet())
                                                        .onBackpressureBuffer( true)
                                                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                                            @Override
                                                            public void accept(Throwable throwable) throws Throwable {
                                                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            }
                                                        })
                                                        .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Map.Entry<String, String>>() {
                                                            @Override
                                                            public void accept(Map.Entry<String, String> stringStringEntry) throws Throwable {
                                                                String ПолеОтJSONKEY = stringStringEntry.getKey().toString().trim();
                                                                switch (имяТаблицыОтАндройда_локальноая.trim().toLowerCase()) {
                                                                    case "tabels":
                                                                    case "chats":
                                                                    case "data_chat":
                                                                    case "chat_users":
                                                                    case "fio":
                                                                    case "tabel":
                                                                    case "cfo":
                                                                    case "data_tabels":
                                                                    case "nomen_vesov":
                                                                    case "type_materials":
                                                                    case "company":
                                                                    case "track":
                                                                    case "prof":
                                                                        System.out.println("  ПолеОтJSONKEY  " + ПолеОтJSONKEY);
                                                                        if (stringStringEntry.getKey().contentEquals("id") == true) {
                                                                            ПолеОтJSONKEY = "_id";
                                                                        }
                                                                        break;
                                                                }
                                                                // TODO: 27.10.2022 Дополнительна Обработка
                                                                String ПолеЗначениеJson = stringStringEntry.getValue().toString()
                                                                        .replace("\"", "").replace("\\n", "")
                                                                        .replace("\\r", "").replace("\\", "")
                                                                        .replace("\\t", "").trim();//todo .replaceAll("[^A-Za-zА-Яа-я0-9]", "")
                                                                if (ПолеОтJSONKEY.equalsIgnoreCase("status_carried_out") ||
                                                                        ПолеОтJSONKEY.equalsIgnoreCase("closed") ||
                                                                        ПолеОтJSONKEY.equalsIgnoreCase("locked")) {
                                                                    if (ПолеЗначениеJson.equalsIgnoreCase("false") ||
                                                                            ПолеЗначениеJson.equalsIgnoreCase("0")) {
                                                                        ПолеЗначениеJson = "False";
                                                                    }
                                                                    if (ПолеЗначениеJson.equalsIgnoreCase("true") ||
                                                                            ПолеЗначениеJson.equalsIgnoreCase("1")) {
                                                                        ПолеЗначениеJson = "True";
                                                                    }
                                                                }
                                                                Log.d(this.getClass().getName(), " ПолеОтJSONKEY " + ПолеОтJSONKEY + " ПолеЗначениеJson" + ПолеЗначениеJson);
                                                                // TODO: 27.10.2022  UUID есть Обновление
                                                                ТекущийАдаптерДляВсего.put(ПолеОтJSONKEY, ПолеЗначениеJson);//
                                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                        + " ТекущийАдаптерДляВсего "+ТекущийАдаптерДляВсего.size());

                                                            }
                                                        })
                                                        .onErrorComplete(new Predicate<Throwable>() {
                                                            @Override
                                                            public boolean test(Throwable throwable) throws Throwable {
                                                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                return false;
                                                            }
                                                        })
                                                        .doOnComplete(new Action() {
                                                            @Override
                                                            public void run() throws Throwable {
                                                                // TODO: 14.11.2022  Заполем  Обновление ЧЕРЕЗ КОНТЕКТ ПРОВАЙДЕР
                                                                МетодЗаполненияContextValues(context,  имяТаблицыОтАндройда_локальноая);
                                                                Log.d(this.getClass().getName(), " массова вставка МетодContentProvoderForUpdateJrInsert  АдаптерПриОбновленияДанныхсСервера.size() :::  "
                                                                        + ТекущийАдаптерДляВсего.size());
                                                            }
                                                        }).blockingSubscribe();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                        }
                                    }).blockingSubscribe();
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doAfterNext(new io.reactivex.rxjava3.functions.Consumer<List<Map<String, String>>>() {
                        @Override
                        public void accept(List<Map<String, String>> maps) throws Throwable {
                            // TODO: 09.11.2022 ПОСЛЕ ОБРАБОТКИ НАЧИНАЕМ ВСТАКУ ДАННЫХ ЧЕРЕЗ BULK INSERT
                            РезультатРаботыСинхрониазциии[0] =            МетодBulkUPDATE(имяТаблицыОтАндройда_локальноая, context );
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + имяТаблицыОтАндройда_локальноая+" АдаптерДляВставкиИОбновления.size() "
                                    + АдаптерДляВставкиИОбновления.size() + " РезультатРаботыСинхрониазциии "+РезультатРаботыСинхрониазциии[0]);

                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 11.10.2022 ПОСЛЕ ОПЕРАЦИИ ВИЗАУЛИЗИРУЕМ КОНЕЦ ОПЕРАЦИИ ПОЛЬЗОВАТЕЛЮ
                            МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                                    Проценты,"ПроцессеAsyncBackground",false,false);
                            Log.d(this.getClass().getName(), " Конец  ПАРСИНГА ОБРАБОАТЫВАЕМОМЙ ТАБЛИЦЫ МетодBulkUPDATE   ::::: "
                                    + имяТаблицыОтАндройда_локальноая+" АдаптерДляВставкиИОбновления.size() "
                                    + АдаптерДляВставкиИОбновления.size() + " РезультатРаботыСинхрониазциии "+РезультатРаботыСинхрониазциии[0]);

                        }
                    }).blockingSubscribe();
                Log.d(this.getClass().getName(),  " date "+ "jsonObjects "  +new Date().toGMTString().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            Log.d(this.getClass().getName(), "   РезультатРаботыСинхрониазциии[0] " +  РезультатРаботыСинхрониазциии[0]);
            return  РезультатРаботыСинхрониазциии[0].intValue();
        }

//todo МЕТОД ВИЗУАЛЬНОГО ОТВЕТА ИЗ СЛУЖБЫ ОБРАБТНО В activity async
        private void МетодCallBasksВизуальноИзСлужбы(Integer МаксималноеКоличествоСтрочекJSON,
                                                     Integer ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара,
                                                     String имяТаблицыОтАндройда_локальноая,
                                                     String ПроцентыВерхнегоПрограссбара,
                                                     String СтатусAsyncBackground,
                                                     Boolean РеальнаяРаботаВставкаОбновиеContProver,
                                                     Boolean ЧтоДелаемПолучаемДанныеИлиОтправляем)  {
            try {
            Message lMsg = new Message();
            Bundle bundleОтправкаОбратноActivity=new Bundle();
                if (МаксималноеКоличествоСтрочекJSON!=null) {
                    bundleОтправкаОбратноActivity.putInt("МаксималноеКоличествоСтрочекJSON",МаксималноеКоличествоСтрочекJSON);
                }
                if (ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара!=null) {
                    bundleОтправкаОбратноActivity.putInt("ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара",ИндексТекущейОперацииJSONДляВизуальнойОбработкиНижнегоПрограссБара);
                }
                if (имяТаблицыОтАндройда_локальноая!=null) {
                    bundleОтправкаОбратноActivity.putString("имяТаблицыОтАндройда_локальноая",имяТаблицыОтАндройда_локальноая);
                }
                if (ПроцентыВерхнегоПрограссбара!=null) {
                    bundleОтправкаОбратноActivity.putString("ПроцентыВерхнегоПрограссбара",ПроцентыВерхнегоПрограссбара);
                }
                if (СтатусAsyncBackground!=null) {
                    bundleОтправкаОбратноActivity.putString("СтатусРаботыСлужбыСинхронизации",СтатусAsyncBackground);
                }
                if (РеальнаяРаботаВставкаОбновиеContProver!=null) {
                    bundleОтправкаОбратноActivity.putBoolean("РеальнаяРаботаВставкаОбновиеContProver",РеальнаяРаботаВставкаОбновиеContProver);
                }
                if (ЧтоДелаемПолучаемДанныеИлиОтправляем!=null) {
                    bundleОтправкаОбратноActivity.putBoolean("ЧтоДелаемПолучаемДанныеИлиОтправляем",ЧтоДелаемПолучаемДанныеИлиОтправляем);
                }
                lMsg.setData(bundleОтправкаОбратноActivity);

                if (messengerCallBacks!=null) {
                    messengerCallBacks.send(lMsg);
                }
            } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        private Long МетодBulkUPDATE(@NonNull String имяТаблицыОтАндройда_локальноая,@NonNull Context context) {
            Long результат_ОбновлениенымисСервера=0l;
            try{
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirror/" + имяТаблицыОтАндройда_локальноая + "");
                //   Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + имяТаблицыОтАндройда_локальноая + "");
                    if (АдаптерДляВставкиИОбновления.size()>0) {
                        //TODO  ПОСЛЕ ОБРАБОТКИ ВСЕЙ ТАБЛИЦЫ ТЕСТОВО ЗАПУСКАЕМ ЕТОД МАССОВОЙ ВСТАВКИ ЧЕРЕЗ КОНТЕНТ ПРОВАЙДЕР МЕТОД BurkInset
                        Log.w(context.getClass().getName(), " АдаптерДляВставкиИОбновления.size()  " + АдаптерДляВставкиИОбновления.size()+
                                "\n" + " АдаптерПриОбновленияДанныхсСервера.size()  " + ТекущийАдаптерДляВсего.size()+" uri  " + uri);/////
                        ContentResolver contentResolver  = context.getContentResolver();
                        // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                        МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                                ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                                Проценты,"ПроцессеAsyncBackground",
                                true,false);
                        ContentValues[] contentValuesМассив=new ContentValues[АдаптерДляВставкиИОбновления.size()];
                        contentValuesМассив=АдаптерДляВставкиИОбновления.toArray(contentValuesМассив);
                        int РезультатОбновлениеМассовой = contentResolver.bulkInsert(uri, contentValuesМассив);
                        // TODO: 27.10.2021
                              if (РезультатОбновлениеМассовой>0) {
                            ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы++;
                        }
                        результат_ОбновлениенымисСервера = Long.valueOf(РезультатОбновлениеМассовой);
                        Log.d(this.getClass().getName(), " РезультатОбновлениеМассовой :::  "
                                + РезультатОбновлениеМассовой+"\n"+
                                "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return результат_ОбновлениенымисСервера;
        }

        private void МетодЗаполненияContextValues(Context context, String имяТаблицыОтАндройда_локальноая) {
            try{
                if (ТекущийАдаптерДляВсего.size() > 0) {
                    АдаптерДляВставкиИОбновления.add(ТекущийАдаптерДляВсего);
                    Log.d(this.getClass().getName(), " АдаптерДляВставкиИОбновления  " + АдаптерДляВставкиИОбновления.size()+
                            " ИндексВизуальнойДляPrograssBar " +ИндексВизуальнойДляPrograssBar);
                        ИндексВизуальнойДляPrograssBar++;
                }
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
        Integer МетодПосылаемДанныеНаСервервФоне(String имяТаблицыОтАндройда_локальноая,
                                                 Long ВерсияДанныхОсноваСозданиеДанныхОтправки,
                                                 CompletionService МенеджерПотоковВнутрений) {

            Integer РезультатОтправкиДанныхНасервер=0;
            try {
                Class_GRUD_SQL_Operations  sql_operations = new Class_GRUD_SQL_Operations(context);
                sql_operations = МетодДанныеДляОтправкиCursor(имяТаблицыОтАндройда_локальноая, ВерсияДанныхОсноваСозданиеДанныхОтправки, ПубличныйIDДляФрагмента);
                Class_GRUD_SQL_Operations.GetData  getData=sql_operations.new GetData(context);
                // TODO: 15.02.2022  ПолучаемдАннык На ОТправку На сервер
            Cursor    КурсорДляОтправкиДанныхНаСервер = (SQLiteCursor) getData
                        .getdata(sql_operations.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                МенеджерПотоковВнутрений, СсылкаНаБазуSqlite);
                /////TODO результаты   количество отправляемой информации на сервера
                if (КурсорДляОтправкиДанныхНаСервер.getCount() > 0) {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " КурсорДляОтправкиДанныхНаСервер.getCount() "+КурсорДляОтправкиДанныхНаСервер.getCount() );

                    //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                        РезультатОтправкиДанныхНасервер =
                                МетодГенерацииJSON(КурсорДляОтправкиДанныхНаСервер, имяТаблицыОтАндройда_локальноая,МенеджерПотоковВнутрений);
                        Log.d(this.getClass().getName(), "РезультатОтправкиДанныхНасервер " + РезультатОтправкиДанныхНасервер);

                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " РезультатОтправкиДанныхНасервер "+РезультатОтправкиДанныхНасервер );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтправкиДанныхНасервер;
        }


        // TODO: 15.02.2022 синхрогниазции таблиц
        @NonNull
        private Class_GRUD_SQL_Operations МетодДанныеДляОтправкиCursor(String имяТаблицыОтАндройда_локальноая,
                                                                       Long ВерсияДанныхДляСравения,
                                                                       Integer ПубличныйIDДляФрагмента) {
            Class_GRUD_SQL_Operations class_grud_sql_operationsГенерируемКурсорДляОтправки = null;

            try{
                ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
                Log.w(this.getClass().getName(), "имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                switch (имяТаблицыОтАндройда_локальноая.trim()) {
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                    case "tabels":
                    case "fio":
                    case "tabel":
                    case "data_tabels":
                    case "prof":
                    case "chats":
                    case "data_chat":
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для tabels  chat_users  fio  tabel  data_tabels  " + имяТаблицыОтАндройда_локальноая);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки = new Class_GRUD_SQL_Operations(context);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);

                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ВерсияДанныхДляСравения);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ПубличныйIDДляФрагмента);

                        class_grud_sql_operationsГенерируемКурсорДляОтправки
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер1"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ ВерсияДанныхДляСравения );
                        class_grud_sql_operationsГенерируемКурсорДляОтправки
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер2","  SELECT * FROM " + имяТаблицыОтАндройда_локальноая +" " +
                                        "  WHERE user_update=" + ПубличныйIDДляФрагмента  + " AND _id IS NULL " );
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                        break;
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID   // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                    default:
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для tabels  chat_users  fio  tabel  data_tabels  " + имяТаблицыОтАндройда_локальноая);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки = new Class_GRUD_SQL_Operations(context);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", имяТаблицыОтАндройда_локальноая);

                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ВерсияДанныхДляСравения);
                        class_grud_sql_operationsГенерируемКурсорДляОтправки.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", ПубличныйIDДляФрагмента);

                        class_grud_sql_operationsГенерируемКурсорДляОтправки
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер1"," SELECT * FROM " + имяТаблицыОтАндройда_локальноая +
                                        " WHERE current_table > "+ ВерсияДанныхДляСравения );
                        class_grud_sql_operationsГенерируемКурсорДляОтправки
                                .concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПодЗапросНомер2","  SELECT * FROM " + имяТаблицыОтАндройда_локальноая +" " +
                                        "  WHERE user_update=" + ПубличныйIDДляФрагмента  + " AND id IS NULL " );
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return class_grud_sql_operationsГенерируемКурсорДляОтправки;
        }

        private Integer getInteger(String имяТаблицыОтАндройда_локальноая, Long РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера, Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне) {
            Integer ПубличныйIDДляФрагмента;
            // TODO: 11.01.2022 ПУБЛИЧНЫЙ ID ТЕКУЩЕГО ПОЛЬЗОВТЕЛЯ

//////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА  old version

            ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

            Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",ПубличныйIDДляФрагмента);
            return ПубличныйIDДляФрагмента;
        }


        // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР






















        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        Integer МетодГенерацииJSON(@NonNull  Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда,
                                   @NonNull String имяТаблицыОтАндройда_локальноая,
                                   @NonNull  CompletionService МенеджерПотоковВнутрений) {
            Integer РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = 0;
            try {
                if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToFirst();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " КурсорДляОтправкиДанныхНаСерверОтАндройда "+КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() );
                    StringWriter stringWriterJSONAndroid=    new StringWriter();
                    ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();
                    SimpleModule module = new SimpleModule();
                    module.addSerializer(Cursor.class, new GeneratorJSONSerializer(context));
                    jsonGenerator.registerModule(module);
                    jsonGenerator.getFactory().createGenerator( stringWriterJSONAndroid).useDefaultPrettyPrinter();
                 String БуферJSONДлСервера=  jsonGenerator.writeValueAsString(КурсорДляОтправкиДанныхНаСерверОтАндройда);
                    // TODO: 23.03.2023 ID ПРОФЕСИИ
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " БуферJSONДлСервера"+БуферJSONДлСервера );

                    // TODO: 14.03.2023 ПОСЫЛАЕМ ДАННЫЕ СГЕНЕРИРОНГО JSON НА СЕРВЕР ---->SERVER
                    РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = new SubClass_SendToServer(context)
                                    .МетодПосылаетНаСерверСозданныйJSONФайлвФоне(БуферJSONДлСервера, имяТаблицыОтАндройда_локальноая, МенеджерПотоковВнутрений); ////СГЕНЕРИРОВАНЫЙ JSON ФАЙЛ ЕСЛИ БОЛЬШЕ 2 ССИМВОЛОМ В НЕМ ТО ОТПРАВЛЯЕМ
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления );
                }else{
                    Log.d(this.getClass().getName(), " НЕ т данных  "+"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления +
                             " КурсорДляОтправкиДанныхНаСерверОтАндройда " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления;
        }





        //////ТУТ БУДЕТ ЗАПИСЫВАТЬСЯ УСПЕШНОЕ ОБНЛВДЕНИ И ВСТАВКИ ДАННЫХ НА СЕРВЕРЕ ДЛЯ КЛИЕНТА


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

//todo  ПОД КЛАСС  С ГЛАВНМ ЦИКЛОМ ОБМЕНА ДАННЫМИ ТАБЛИ
            Integer МетодГлавныхЦиклТаблицДляСинхронизации(String ДанныеПришёлЛиIDДЛяГенерацииUUID)
                    throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
                final Integer[] РезультатТаблицыОбмена = {0};
                try {
                    ArrayList<Integer>  ЛистТаблицыОбмена = new ArrayList<>();
                    Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString()
                            + " ДатыТаблицыВерсииДанныхОтСервера "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ДатыТаблицыВерсииДанныхОтСервера.toString());
                    ПубличныйРезультатОтветаОтСерврераУспешно = 0;
                    // TODO: 01.12.2022  еще один тест
                    // TODO: 09.11.2022  НАЧИНАЕМ ГЛАВНЫЙ ЦИКЛ СИНХРОНИАЩЗЦИИ ПО ТАБЛИЦАМ
                    Log.d(this.getClass().getName(), " 1ТекущаяТаблицаИзПотока "  + " date " +new Date().toString());
                    Log.d(this.getClass().getName(), " 3ТекущаяТаблицаИзПотока " +" date " +new Date().toString());
                    // TODO: 01.12.2022

                    Flowable.fromIterable(public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда)
                            .onBackpressureBuffer(true)
                                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Throwable {
                                            throwable.printStackTrace();
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            // TODO: 01.09.2021 метод вызова
                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    })
                                            .map(new Function<String, Object>() {
                                                @Override
                                                public Object apply(String ТекущаяТаблицаИзПотока) throws Throwable {
                                                    ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы = 0;
                                                    Log.d(this.getClass().getName(), " ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока +"  " + new Date().toGMTString());
                                                    // TODO: 06.10.2022  вычисляем какую таблицу нужно отоброзить в верхнем ПрограссбАре
                                                    Integer ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения = ГлавныеТаблицыСинхронизации.indexOf(ТекущаяТаблицаИзПотока);
                                                    Проценты = new Class_Visible_Processing_Async(context).
                                                            ГенерируемПРОЦЕНТЫДляAsync(ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения + 1,
                                                                    ГлавныеТаблицыСинхронизации.size());
                                                    Log.d(this.getClass().getName(), "  ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения " +
                                                            ИндексТекущееТаллицыДляПониманияВизуальнойОтобрабжения + " Проценты " + Проценты);
                                                    // TODO: 09.11.2022 визуальна часть синхрониазции по таблице
                                                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                                                            ИндексВизуальнойДляPrograssBar,ТекущаяТаблицаИзПотока,
                                                            Проценты,"ПроцессеAsyncBackground",false,false);
                                                    // TODO: 24.01.2022 сама операция синхрониазции по таблице
                                                    ПубличныйРезультатОтветаОтСерврераУспешно= МетодЗапускаСинхрониазцииПоАТблицам(ДанныеПришёлЛиIDДЛяГенерацииUUID,
                                                            ТекущаяТаблицаИзПотока,
                                                            public_contentДатыДляГлавныхТаблицСинхронизации.МенеджерПотоков ,public_contentДатыДляГлавныхТаблицСинхронизации);
                                                    Log.d(this.getClass().getName(), " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                            " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                            " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                            " ТАБЛИЦА СИНХРОНИЗАЦИИ ГЛАВНОГО ЦИКЛА "+"\n"+
                                                            " ТАБЛИЦА ТекущаяТаблицаИзПотока " + ТекущаяТаблицаИзПотока +"\n"+
                                                            " +ПубличныйРезультатОтветаОтСерврераУспешно" +ПубличныйРезультатОтветаОтСерврераУспешно+ "\n"+
                                                            " время" +new Date().toGMTString());
                                                    ЛистТаблицыОбмена.add(ПубличныйРезультатОтветаОтСерврераУспешно);
                                                    return ТекущаяТаблицаИзПотока;
                                                }
                                            })
                                                    .doOnComplete(new Action() {
                                                        @Override
                                                        public void run() throws Throwable {
                                                            if (СсылкаНаБазуSqlite.isOpen()) {
                                                                СсылкаНаБазуSqlite.close();
                                                            }
                                                            РезультатТаблицыОбмена[0] = ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b);
                                                            Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЗАВЫЕРШИЛАСЬ В ГЛАВНОМ ЦИКЛЕ ПО ТАБЛИЦАМ В ОБМЕНЕ РезультатТаблицыОбмена"
                                                                    + РезультатТаблицыОбмена[0] + " СсылкаНаБазуSqlite.isOpen() " +СсылкаНаБазуSqlite.isOpen());
                                                        }
                                                    })
                                                            .onErrorComplete(new Predicate<Throwable>() {
                                                                @Override
                                                                public boolean test(Throwable throwable) throws Throwable {
                                                                    throwable.printStackTrace();
                                                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                    // TODO: 01.09.2021 метод вызова
                                                                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                    return false;
                                                                }
                                                            }).subscribe();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатТаблицыОбмена[0];
            }
        // TODO: 22.03.2022  ДЛЯ ОТПРАВКИ ДАННЫХ НА СЕРВЕР
        private class SubClass_SendToServer  {
            public SubClass_SendToServer(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            }
            // TODO: 22.03.2022

            //////todo МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST
            Integer МетодПосылаетНаСерверСозданныйJSONФайлвФоне(@NonNull String ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда, @NonNull String имяТаблицыОтАндройда_локальноая,
                                                                CompletionService МенеджерПотоковВнутрений) {
                /////
                Integer РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = 0;
                String ДанныеПришёлВОтветОтМетодаPOST = new String();
                StringBuffer БуферДанныхНаСервер = new StringBuffer();
                Class_GRUD_SQL_Operations class_grud_sql_operations;
                class_grud_sql_operations = new Class_GRUD_SQL_Operations(context);
                try {
                    МетодCallBasksВизуальноИзСлужбы(МаксималноеКоличествоСтрочекJSON,
                            ИндексВизуальнойДляPrograssBar,имяТаблицыОтАндройда_локальноая,
                            Проценты,"ПроцессеAsyncBackground",false,true);
                    Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
                    // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
                    Log.d(this.getClass().getName(), " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() "
                            + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString() +
                            " ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length  "
                            + ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда.toString().toCharArray().length +
                            " имяТаблицыОтАндройда_локальноая " + имяТаблицыОтАндройда_локальноая);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
                    БуферДанныхНаСервер = УниверсальныйБуферОтправкиДанныхНаСервера(ГенерацияJSONполейФиналДляОтправкиНаСеврерОтАндройда,
                            ПубличноеIDПолученныйИзСервлетаДляUUID, имяТаблицыОтАндройда_локальноая,
                            "Получение JSON файла от Андройда",
                            60000,  ИмяСерверИзХранилица ,ПортСерверИзХранилица);
                    ///БУФЕР ОТПРАВКИ ДАННЫХ НА СЕРВЕР  //TODO original "tabel.dsu1.ru", 8888        //TODO "192.168.254.40", 8080
                    Log.d(this.getClass().getName(), "  СЛУЖБА ВЕРНУЛЬСЯ ОТВЕТ ОТ СЕРВЕРА ОБРАТНО АНДРОЙДУ  БуферОтправкаДанных.toString() " + БуферДанныхНаСервер.toString());
                    if (БуферДанныхНаСервер == null) {
                        БуферДанныхНаСервер = new StringBuffer();
                    }
                    if (БуферДанныхНаСервер.length() > 0) {
                        ПубличныйРезультатОтветаОтСерврераУспешно = 0;
                        ПубличныйРезультатОтветаОтСерврераУспешно = БуферДанныхНаСервер.length();
                    }
                    Log.d(this.getClass().getName(), "БуферДанныхНаСервер.length() " + БуферДанныхНаСервер.length() +
                            " БуферДанныхНаСервер " + БуферДанныхНаСервер.toString() );
                    ////TODO  ОТВЕТ ОТ СЕРВЕРА ПОСЛЕ ОТПРАВКИ ДАННЫХ НА СЕРВЕР
                    if (БуферДанныхНаСервер != null) {
                        if (БуферДанныхНаСервер.length() > 0) {
                            Log.d(this.getClass().getName(), "  БуферДанныхНаСервер.toString()  " + БуферДанныхНаСервер.toString());
                            ДанныеПришёлВОтветОтМетодаPOST = БуферДанныхНаСервер.toString();
                            Log.d(this.getClass().getName(), "  ДанныеПришёлВОтветОтМетодаPOST  " + ДанныеПришёлВОтветОтМетодаPOST);

                            ////todo дОПОЛНИТЕЛЬНЫЙ КОД ПОСИКА ДВННЫХ ИЗ ОТВЕТА ОТ СЕРВЕРА
                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = МетодАнализОтветаОтСервера(БуферДанныхНаСервер);
                        }
                        ////TODO ответ от сервера РЕЗУЛЬТАТ
                        Log.d(this.getClass().getName(), "Успешный Ответ от сервера ДанныеПришёлВОтветОтМетодаPOST в фоне " + ДанныеПришёлВОтветОтМетодаPOST+"" +
                                " РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера " +РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера);
                        if (ДанныеПришёлВОтветОтМетодаPOST.length() > 5) {
                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера ;//TODO ДанныеПришёлВОтветОтМетодаPOST.length();

                            Log.d(this.getClass().getName(), " СЛУЖБА УСПЕШНЫЙ ОТВКЕТ ОТ СЕРВЕРА ОТВЕТ CALBACKS  ДанныеПришёлВОтветОтМетодаPOST.length()  "
                                    + ДанныеПришёлВОтветОтМетодаPOST.length() + " ДанныеПришёлВОтветОтМетодаPOST " + ДанныеПришёлВОтветОтМетодаPOST.toString()+
                                    " РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера " +РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера);
                        } else {
                            Log.d(this.getClass().getName(), " NULL НОЛЬ ОБНОВЛЕНИЙ ИЛИ ВСТАВОК С СЕРВЕРА  СЛУЖБА УСПЕШНЫЙ ОТВКЕТ ОТ СЕРВЕРА ОТВЕТ CALBACKS  ДанныеПришёлВОтветОтМетодаPOST.length() ");
                        }
                    } else {
                        Log.d(this.getClass().getName(), " Данных нет c сервера  БуферОтправкаДанных.length() в фоне " + БуферДанныхНаСервер.length());
                    }
                    Log.d(this.getClass().getName(), " ДанныеПришёлВОтветОтМетодаPOST " + ДанныеПришёлВОтветОтМетодаPOST);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера;
            }




            //TODO get max versrsion data server

            @NonNull
            private Integer МетодАнализОтветаОтСервера(StringBuffer БуферОтправкаДанныхвФоне) {
                Long  РезультатУспешнойВставкиИли=0l;
                try{
                    String ПолучениееыкОтветыОтСервераSQlServerАнализ= БуферОтправкаДанныхвФоне.toString();
                    StringBuffer stringBufferРезульата;
                    ArrayList<Long> ФинальныСписокЦифр=new ArrayList();
                    String[] words = ПолучениееыкОтветыОтСервераSQlServerАнализ.split("таблица");
                    for (String word : words) {
                        System.out.println(word);
                        Integer ЕслиТакойПоискаОригинальноВерсииДанныхОтСервера = word.indexOf("OriginalVesion :::");
                        if (ЕслиТакойПоискаОригинальноВерсииДанныхОтСервера>0) {
                            //TODO
                            Integer КонецПоискаОригинальноВерсииДанныхОтСервера = word.lastIndexOf(":::")+3;
                            stringBufferРезульата=new StringBuffer();
                            stringBufferРезульата.append(word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, word.length()).replace(" ","")  );
                            ФинальныСписокЦифр.add(Long.parseLong(stringBufferРезульата.toString()));
                            ////TODO ответ от сервера РЕЗУЛЬТАТ
                            Log.d(this.getClass().getName(), " word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, КонецПоискаОригинальноВерсииДанныхОтСервера) "
                                    +  word.substring(КонецПоискаОригинальноВерсииДанныхОтСервера, КонецПоискаОригинальноВерсииДанныхОтСервера));
                        }
                    }
                 РезультатУспешнойВставкиИли = ФинальныСписокЦифр
                            .stream()
                            .mapToLong(v -> v)
                            .max().orElse(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатУспешнойВставкиИли.intValue();
            }

        }
    }

    private Integer МетодIdПрофесии(@NonNull Context context) {
        Integer IDПрофесии=0;
        try{
        Class_GRUD_SQL_Operations         class_grud_sql_operationsПрофесии=new Class_GRUD_SQL_Operations(context);
        class_grud_sql_operationsПрофесии. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","prof");
        class_grud_sql_operationsПрофесии. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","_id");
        class_grud_sql_operationsПрофесии.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", "   name=? " );
        class_grud_sql_operationsПрофесии.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", "Выбрать должность".trim());
        // TODO: 12.10.2021  Ссылка Менеджер Потоков
        SQLiteCursor Курсор_Професии= (SQLiteCursor) class_grud_sql_operationsПрофесии.
                new GetData(context).getdata(class_grud_sql_operationsПрофесии. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                new PUBLIC_CONTENT(context).МенеджерПотоков,new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу());
        if (Курсор_Професии.getCount()>0){
            Курсор_Професии.moveToFirst();
            IDПрофесии=Курсор_Професии.getInt(0);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " IDПрофесии "+IDПрофесии );
        }
        Курсор_Професии.close();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  IDПрофесии;
    }

    public void МетодБиндинuCлужбыPublic() {
        try {
            Intent intentЗапускPublicService = new Intent(context, Service_For_Public.class);
            intentЗапускPublicService.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
            if (localBinderОбщий==null) {
                context.bindService(intentЗапускPublicService,  new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            localBinderОбщий = (Service_For_Public.LocalBinderОбщий) service;
                            if (service.isBinderAlive()) {
                                // TODO: 16.11.2022
                                Log.d(context.getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "   service.pingBinder() " + service.pingBinder());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            localBinderОбщий = null;
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  localBinderОбщий" + localBinderОбщий);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок

                        }
                    }
                    },Context.BIND_AUTO_CREATE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}