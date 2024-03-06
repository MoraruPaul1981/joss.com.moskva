package com.dsy.dsu.Providers;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClassCreatingMainAllTables;
import com.dsy.dsu.Hilt.Sqlitehilt.HiltInterfacesqlite;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import dagger.hilt.EntryPoints;

public class ContentProviderForDataBaseCurrentOperations extends ContentProvider  {
    private   UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;

    private  SQLiteDatabase sqlite;

    private  PUBLIC_CONTENT public_contentМенеджерПотоковМассвойОперацииВставки;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ТекущаяСтрокаПриДОбавлениииURL=0;
    private     ContentResolvers contentResolvers;
    public ContentProviderForDataBaseCurrentOperations() throws InterruptedException {
        try{

            CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда=
                    new SubClassCreatingMainAllTables(getContext()).
                            методCreatingMainTabels(getContext());
            Log.d(this.getClass().getName(), " ИменаТаблицыОтАндройда "+ИменаТаблицыОтАндройда );
            uriMatcherДЛяПровайдераКонтентБазаДанных=new UriMatcher(ИменаТаблицыОтАндройда.size());
            ИменаТаблицыОтАндройда.forEach(new Consumer<String>() {
                @Override
                public void accept(String ЭлементТаблица) {
                    uriMatcherДЛяПровайдераКонтентБазаДанных.addURI("com.dsy.dsu.providerdatabasecurrentoperations",ЭлементТаблица.toString(),ТекущаяСтрокаПриДОбавлениииURL);
                    Log.d(this.getClass().getName(), " ЭлементТаблица "+ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " +ТекущаяСтрокаПриДОбавлениииURL);
                    ТекущаяСтрокаПриДОбавлениииURL++;
                }
            });
            Log.d(this.getClass().getName(),  " uriMatcherДЛяПровайдераКонтентБазаДанных" +uriMatcherДЛяПровайдераКонтентБазаДанных );
            handler=          new Handler(Looper.getMainLooper(),new Handler.Callback(){
                @Override
                public boolean handleMessage(@NonNull android.os.Message  msg) {
                    try{
                        Log.d(this.getClass().getName(), " msg  "+msg);
                        Bundle bundle=        msg.getData();
                        Log.d(this.getClass().getName(), " bundle  "+bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
                        ///метод запись ошибок в таблицу
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return true;
                }
            });
            // TODO: 04.10.2022
            contentResolvers=new ContentResolvers(getContext());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    /*@Override
    public int delete(@NonNull Uri uri, @Nullable Bundle extras) {
        Integer РезультатУдалениеСтатуса=0;
        try{
            sqLiteDatabase=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
            if (!sqLiteDatabase.inTransaction()) {
                sqLiteDatabase.beginTransaction();
            }
      String selection =      extras.getString("selection");
      String[] selectionArgs =      extras.getStringArray("selectionArgs");
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  = sqLiteDatabase.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                    if (РезультатУдалениеСтатуса>0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (sqLiteDatabase.inTransaction()) {
                sqLiteDatabase.setTransactionSuccessful();
                sqLiteDatabase.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдалениеСтатуса;
    }*/



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдаления=0;
        try{
            CompletableFuture       completableFutureУдаление=         CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    Integer РезультатУдаления=0;
                    if (!sqlite.inTransaction()) {
                        sqlite.beginTransaction();
                    }
                    Log.d(this.getClass().getName(), " uri"+uri );
                    // TODO: 14.10.2022 метод определения текущней таблицы
                    String table = МетодОпределяемТаблицу(uri);
                    if (table!=null) {
                        РезультатУдаления  = sqlite.delete(table, selection, selectionArgs);
                        // TODO: 30.10.2021
                        Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                        Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                        String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                     Integer   РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                        if (РезультатУдаления> 0) {
                            getContext().getContentResolver().notifyChange(uri, null);
                        }
                    }else {
                        Log.w(getContext().getClass().getName(), " table  " + table);/////
                    }
                    if (sqlite.inTransaction()) {

                        sqlite.setTransactionSuccessful();
                    }
                    if (sqlite.inTransaction()) {
                        sqlite.endTransaction();
                    }
                    return РезультатУдаления;
                }
        }).exceptionally(e -> {
            System.out.println(e.getClass());
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            return null;
        });
       РезультатУдаления=  (Integer) completableFutureУдаление.get();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатУдаления;
    }


    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try{
            Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://com.dsy.dsu.providerdatabasecurrentoperations/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        final Uri[] ОтветВставкиДанных = {null};
        try {
       CompletableFuture<Uri>   completableFutureВстака=   CompletableFuture.supplyAsync(new Supplier<Uri>() {
                @Override
                public Uri get() {
                    //   sqLiteDatabase=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
                    if (!sqlite.inTransaction()) {
                        sqlite.beginTransaction();
                    }
                    Log.d(this.getClass().getName(), " uri"+uri );
                    // TODO: 14.10.2022 метод определения текущней таблицы
                    String table = МетодОпределяемТаблицу(uri);


                    Long   РезультатВставкиДанных  = sqlite.insert(table, null, values);
                    // TODO: 30.10.2021
                    Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////

                    ОтветВставкиДанных[0] = Uri.parse("content://"+РезультатВставкиДанных.toString());
                    if (РезультатВставкиДанных> 0) {
                        if (sqlite.inTransaction()) {

                            sqlite.setTransactionSuccessful();
                            // TODO: 22.09.2022 увеличивает версию данных
                        }
                    }
                    if (sqlite.inTransaction()) {
                        sqlite.endTransaction();
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    return ОтветВставкиДанных[0];
                }
            }).exceptionally(e->{
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                   + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
           return  null;
       });
            ОтветВставкиДанных[0]=   completableFutureВстака.get();
            // TODO: 30.10.2021
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОтветВставкиДанных[0];
    }

    // TODO: 22.11.2022 INSERT
    @SuppressLint("SuspiciousIndentation")
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        Integer РезультатМассовогоВсатвкиДанныхФинал=0;
        ArrayList<Integer> РезультатВнутренаяbulk = new ArrayList<>();
        try {
          //  sqLiteDatabase=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
            if (!sqlite.inTransaction()) {
                sqlite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            String table = МетодОпределяемТаблицу(uri);
             Stream.of(values)
                     .filter(emme->emme!=null)
                     .forEachOrdered(new Consumer<ContentValues>() {
                @Override
                public void accept(ContentValues ТекущаяСтрочкаИзМассо) {
                    Log.w(this.getClass().getName(), " Вставка массовая через burkInsert   ТекущаяСтрочкаИзМассо" +  ТекущаяСтрочкаИзМассо);
                    try{
                        Long     id  = 0l;
                        if (ТекущаяСтрочкаИзМассо.size()>0 ) {
                            id = sqlite.insertOrThrow(table, null, ТекущаяСтрочкаИзМассо);
                        }
                        Log.w(this.getClass().getName(), " Вставка массовая через burkInsert   id " +  id);
                        if (0 < id) РезультатВнутренаяbulk.add( Integer.parseInt(id.toString()) );
                        Log.w(this.getClass().getName(), "count  bulkInsert  РезультатВнутренаяbulk.size() "
                                + РезультатВнутренаяbulk.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                                "  isParallel isParallel isParallel" );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
            if (sqlite.inTransaction()) {

                sqlite.setTransactionSuccessful();
            }
            if (sqlite.inTransaction()) {
                sqlite.endTransaction();
            }
          РезультатМассовогоВсатвкиДанныхФинал=РезультатВнутренаяbulk.size();
            // TODO: 09.11.2022  получаем результаты
            Log.w(this.getClass().getName(), "count bulkInsert РезультатМассовогоВсатвкиДанныхФинал " + РезультатМассовогоВсатвкиДанныхФинал);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    РезультатМассовогоВсатвкиДанныхФинал;
    }




    @Override
    public boolean onCreate() {
        try{
            // TODO: 02.09.2023  CREATE get SQLITE
            sqlite = EntryPoints.get(getContext(), HiltInterfacesqlite.class).getHiltSqlite();


            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
   return  true;
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        try {
            Log.d(this.getClass().getName(), " uri"+uri  + "selection "+selection );
            String table = МетодОпределяемТаблицу(uri);
                        cursor=     sqlite.rawQuery(selection,selectionArgs);
                        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursor;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(authority, operations);
    }


    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        // TODO: 28.03.2023
        try{
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return extras;
    }



    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Integer РезультатUpdateCurrentPro=0;
        try{
            CompletableFuture<Integer>  completableFutureUpdate=
                    CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    if (!sqlite.inTransaction()) {
                        sqlite.beginTransaction();
                    }
                    Integer  РезультатUpdateCurrentPro=0;
                    Log.d(this.getClass().getName(), " uri"+uri );
                    // TODO: 14.10.2022 метод определения текущней таблицы
                    String table = МетодОпределяемТаблицу(uri);
                    if (table!=null) {
                        Integer РезультатУдаления  = sqlite.update(table,values, selection, selectionArgs);
                        // TODO: 30.10.2021
                        Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                        Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
                        String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter
                                .toString().replace("content://","")).get();
                         РезультатUpdateCurrentPro= Integer.parseInt(ответОперцииВставки);
                        if (РезультатУдаления> 0) {
                            getContext().getContentResolver().notifyChange(uri, null);
                        }
                    }else {
                        Log.w(getContext().getClass().getName(), " table  " + table);/////
                    }
                    if (sqlite.inTransaction()) {

                        sqlite.setTransactionSuccessful();
                    }
                    if (sqlite.inTransaction()) {
                        sqlite.endTransaction();
                    }
                    shutdown();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    return РезультатUpdateCurrentPro;
                }
            }).exceptionally(e -> {
                System.out.println(e.getClass());
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                return null;
            });
            РезультатUpdateCurrentPro=     completableFutureUpdate.get();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатUpdateCurrentPro;
    }

/*    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable Bundle extras) throws  NullPointerException{
        Integer РезультатСменыПрофесии=0;
        try{
       String table = МетодОпределяемТаблицу(uri);
            values=new ContentValues();
            Integer ПолучаемIDПрофессии=      extras.getInt("ПолучаемIDПрофессии",0);
            values.put("prof",ПолучаемIDПрофессии);
            String НазваниеПрофесии=   extras.getString("НазваниеПрофесии","");
            Long CurrenrsСhildUUID =   extras.getLong("CurrenrsСhildUUID",0l);
            Long ВерсияДанныхUp = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(table,getContext(),sqLiteDatabase);
            values.put("current_table",ВерсияДанныхUp);
            String ДатаОбновления=     new Class_Generation_Data(getContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
            values.put("date_update",ДатаОбновления);
            // TODO: 28.03.2023 Само Обновление Профессии
            РезультатСменыПрофесии  = sqLiteDatabase.update(table,values, "uuid=?", new String[]{CurrenrsСhildUUID.toString()});
       // TODO: 30.10.2021
       Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
               + " РезультатСменыПрофесии "+РезультатСменыПрофесии+ " table "+table );
    } catch (Exception e) {
        e.printStackTrace();
        sqLiteDatabase.endTransaction();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return РезультатСменыПрофесии;
    }
    */





















    class ContentResolvers extends  ContentResolver{

        /**
         * Note: passing a {@code null} context here could lead to unexpected behavior in certain
         * ContentResolver APIs so it is highly recommended to pass a non-null context here.
         *
         * @param context
         */
        public ContentResolvers(@Nullable Context context) {
            super(context);
        }

        @NonNull
        @Override
        public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException, RemoteException {
            return new ContentProviderResult[]{};
        }
    }
}

