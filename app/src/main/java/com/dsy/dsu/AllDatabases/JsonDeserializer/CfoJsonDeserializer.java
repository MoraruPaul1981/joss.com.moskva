package com.dsy.dsu.AllDatabases.JsonDeserializer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class CfoJsonDeserializer extends JsonMainDeseirialzer {
    private Context context;

    // TODO: 04.07.2023  Главный метод Парсин таблицы ОРганизация
    public Integer методOrganizationJsonDeserializer(@NonNull  JsonNode jsonNodeParentMAP,
                                                  @NonNull Context context,
                                                  @NonNull  String  имяТаблицаAsync ,
                                                  @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite
                                                  , @NonNull  String ФлагКакойСинхронизацияПерваяИлиНет  ) {
        CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
 try {
this.context=context;
     Flowable.fromIterable(jsonNodeParentMAP)
             .onBackpressureBuffer(jsonNodeParentMAP.size(),true)
                     .buffer(500)
                             .doOnNext(new Consumer<List<JsonNode>>() {
                                 @Override
                                 public void accept(List<JsonNode> jsonNodes) throws Throwable {
                                     // TODO: 04.07.2023 ЗАпускаем ТРАНЗАКЦИЮ
                                     if (!Create_Database_СамаБАзаSQLite.inTransaction()) {
                                         Create_Database_СамаБАзаSQLite.beginTransaction();
                                     }
                                     jsonNodes.forEach(new java.util.function.Consumer<JsonNode>() {
                                         @Override
                                         public void accept(JsonNode jsonNode) {
                                             // TODO: 26.04.2023 Insert
                                             Integer ОперацияUpdate=0;

                                             if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                                                     имяТаблицаAsync.equalsIgnoreCase("settings_tabels") ||
                                                     имяТаблицаAsync.equalsIgnoreCase("view_onesignal")) {
                                                 // TODO: 04.07.2023  Обновление
                                                 ОперацияUpdate = ОбновлениеДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);

                                                 if (ОперацияUpdate>0) {
                                                     РезультатОперацииBurkUPDATE.add(ОперацияUpdate);

                                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                             + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate   );
                                             }else {

                                                 // TODO: 04.07.2023  Вставка  ПОСЛЕ ОБНОВЛЕНИЯ ЕСЛИ ОНО НЕ ПРОШЛО
                                                     Long ОперацияInsert = null;
                                                     if (ОперацияUpdate<0) {
                                                         ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);
                                                         if (ОперацияInsert>0) {
                                                             РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                                         }
                                                     }
                                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                                         Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                         + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate + " ОперацияInsert " +ОперацияInsert  );
                                             }

// TODO: 04.07.2023 ТОЛЬКО ВСТАВКА   // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА  // TODO: 04.07.2023 ТОЛЬКО ВСТАВКА
                                             }else {

                                                     // TODO: 04.07.2023  ТОЛЬКО ВСТАВКА
                                                     Long ОперацияInsert = ВставкаДанных(context, имяТаблицаAsync, Create_Database_СамаБАзаSQLite, jsonNode);
                                                     if (ОперацияInsert>0) {
                                                         РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                                     }
                                                     Log.d(this.getClass().getName(), "\n" + " class " +
                                                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                             + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate + " ОперацияInsert " +ОперацияInsert  );


                                             }
                                             Log.d(this.getClass().getName(), "\n" + " class " +
                                                     Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                     + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate  + " РезультатОперацииBurkUPDATE " +РезультатОперацииBurkUPDATE);
                                         }
                                     });

                                 }
                             })
             .doAfterNext(new Consumer<List<JsonNode>>() {
                 @Override
                 public void accept(List<JsonNode> jsonNodes) throws Throwable {
                     // TODO: 04.07.2023  ОПЕРАЦИИ ПОСЛЕ УСПЕШНОЙ ОБРАБОТКИ 500 ЗАписей
                     if ( РезультатОперацииBurkUPDATE.size()>0) {
                         // TODO: 04.07.2023 После Успешной Операции Повышаем Версию ДАнных Для Данной Тваблицы  
                         Integer РезультатПовышенииВерсииДанных =
                                 new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(имяТаблицаAsync, context, Create_Database_СамаБАзаSQLite);
                         Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                         // TODO: 04.07.2023 ЗАВЕРШАЕТ ТРНЗАКЦИЮ НА 50 СТРОЧКЕ
                         Create_Database_СамаБАзаSQLite.setTransactionSuccessful();
                         if (Create_Database_СамаБАзаSQLite.inTransaction()) {
                             Create_Database_СамаБАзаSQLite.endTransaction();
                         }
                     }
                     Log.d(this.getClass().getName(), "\n" + " class " +
                             Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNodeParentMAP " +jsonNodeParentMAP);
                 }
             })
             .doOnComplete(new Action() {
                 @Override
                 public void run() throws Throwable {
                     Log.e(this.getClass().getName(), "doOnComplete "  + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                 }
             })
             .onErrorComplete(new Predicate<Throwable>() {
                 @Override
                 public boolean test(Throwable throwable) throws Throwable {
                     throwable.printStackTrace();
                     Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                     new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                             Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                     return false;
                 }
             })
             .doOnError(new Consumer<Throwable>() {
                 @Override
                 public void accept(Throwable throwable) throws Throwable {
                     throwable.printStackTrace();
                     Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                             " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                     new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                             Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                 }
             })
             .blockingSubscribe();

        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " jsonNodeParentMAP " +jsonNodeParentMAP);
} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
 return  РезультатОперацииBurkUPDATE.size();
    }
    
    
    
    
    
    
    
    
    
    // TODO: 04.07.2023 ВСТАВКА
    Long ВставкаДанных(@NonNull Context context,
                       @NonNull String имяТаблицаAsync,
                       @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                       @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        Long ОперацияInsert=0l;
        try{
            this.context=context;
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                String  SQlOperInsert=  "INSERT INTO "+имяТаблицаAsync+" VALUES(?,?,?,?,?,?,?,?,?,?,?);";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForInsert(Create_Database_СамаБАзаSQLite, SQlOperInsert,jsonNodeParentMAP);

            // TODO: 04.07.2023  INSERT  Organization
             ОперацияInsert=      sqLiteStatementInsert.executeInsert();
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);

                    // TODO: 27.04.2023  повышаем верисю данных
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + имяТаблицаAsync  +
                            " ОперацияInsert " +ОперацияInsert);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОперацияInsert;
    }
    // TODO: 04.07.2023 ОБНОВЛЕНИЕ
    Integer ОбновлениеДанных(@NonNull Context context,
                             @NonNull String имяТаблицаAsync,
                             @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                             @NonNull JsonNode jsonNodeParentMAP) {
        Integer ОперацияUpdate=0;
        try{
            this.context=context;
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            String  SQlOperUpdate=  " UPDATE "+имяТаблицаAsync+" SET  id=?,name=?,  " +
                    " date_update=?,user_update=?  , current_table=?,uuid=?    WHERE  uuid=?  ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatementForUpdate(Create_Database_СамаБАзаSQLite, SQlOperUpdate,jsonNodeParentMAP);

            // TODO: 04.07.2023  UPDARE Organization
            ОперацияUpdate=      sqLiteStatementInsert.executeUpdateDelete();
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            if (ОперацияUpdate>0) {
                // TODO: 27.04.2023  повышаем верисю данных
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync  + " ОперацияUpdate " +ОперацияUpdate  +"  sqLiteStatementInsert " +sqLiteStatementInsert);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОперацияUpdate;
    }



    @NonNull
    private SQLiteStatement методGetSqliteStatementForInsert(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                    String SQlOperInsert,
                                                    @NonNull JsonNode jsonNodeParentMAP) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert = Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("id").intValue());//"id"
            sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("name").asText().trim());//"name"
            sqLiteStatementInsert.bindLong(3, jsonNodeParentMAP.get("region").intValue());//"name"
            sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("boss").intValue());//"name"
            sqLiteStatementInsert.bindString(5, jsonNodeParentMAP.get("kod").asText());//"name"
            sqLiteStatementInsert.bindString(6, jsonNodeParentMAP.get("date_update").asText());//"date_update"
            sqLiteStatementInsert.bindLong(7, jsonNodeParentMAP.get("user_update").intValue());//"user_update"
            sqLiteStatementInsert.bindLong(8, jsonNodeParentMAP.get("closed").intValue());//"name"
            sqLiteStatementInsert.bindLong(9, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(10, jsonNodeParentMAP.get("organization").intValue());//"name"
            sqLiteStatementInsert.bindLong(11, jsonNodeParentMAP.get("uuid").longValue());//"uuid"

        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + sqLiteStatementInsert  + "sqLiteStatementInsert");
} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sqLiteStatementInsert;
    }
    @NonNull
    private SQLiteStatement методGetSqliteStatementForUpdate(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                             String SQlOperInsert,
                                                             @NonNull JsonNode jsonNodeParentMAP) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
            sqLiteStatementInsert= Create_Database_СамаБАзаSQLite.compileStatement(SQlOperInsert);
            sqLiteStatementInsert.clearBindings();
            // TODO: 04.07.2023 цикл данных
            sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("id").intValue());//"id"
            sqLiteStatementInsert.bindString(2, jsonNodeParentMAP.get("name").asText().trim());//"name"
            sqLiteStatementInsert.bindLong(3, jsonNodeParentMAP.get("region").intValue());//"name"
            sqLiteStatementInsert.bindLong(4, jsonNodeParentMAP.get("boss").intValue());//"name"
            sqLiteStatementInsert.bindString(5, jsonNodeParentMAP.get("kod").asText());//"name"
            sqLiteStatementInsert.bindString(6, jsonNodeParentMAP.get("date_update").asText());//"date_update"
            sqLiteStatementInsert.bindLong(7, jsonNodeParentMAP.get("user_update").intValue());//"user_update"
            sqLiteStatementInsert.bindLong(8, jsonNodeParentMAP.get("closed").intValue());//"name"
            sqLiteStatementInsert.bindLong(9, jsonNodeParentMAP.get("current_table").longValue());//"current_table"
            sqLiteStatementInsert.bindLong(10, jsonNodeParentMAP.get("organization").intValue());//"name"
            sqLiteStatementInsert.bindLong(11, jsonNodeParentMAP.get("uuid").longValue());//"uuid"

            // TODO: 05.07.2023  Для Состыковки
            sqLiteStatementInsert.bindLong(12,jsonNodeParentMAP.get("uuid").longValue());//"uuid уже для UUID"
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + sqLiteStatementInsert  + "sqLiteStatementInsert");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return sqLiteStatementInsert;
    }

}
