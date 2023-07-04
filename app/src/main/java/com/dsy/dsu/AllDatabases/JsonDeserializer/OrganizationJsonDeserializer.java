package com.dsy.dsu.AllDatabases.JsonDeserializer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.util.concurrent.AtomicDouble;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.core.Flowable;

public class OrganizationJsonDeserializer extends JsonMainDeseirialzer {

    private Context context;
    // TODO: 04.07.2023 ВСТАВКА
   CopyOnWriteArrayList<Long> OrganizationВставкаДанных(@NonNull Context context,
                                 @NonNull String имяТаблицаAsync,
                                 @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                        @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        CopyOnWriteArrayList<Long> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
        try{
            this.context=context;
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                String  SQlOperInsert=  "INSERT INTO "+имяТаблицаAsync+" VALUES(?,?,?,?,?,?,?,?,?,? );";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatement(Create_Database_СамаБАзаSQLite, SQlOperInsert);

            // TODO: 04.07.2023  INSERT  Organization
            Long ОперацияInsert=      sqLiteStatementInsert.executeInsert();
                // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                if (ОперацияInsert>0) {
                    РезультатОперацииBurkUPDATE.add(ОперацияInsert);
                    // TODO: 27.04.2023  повышаем верисю данных
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + имяТаблицаAsync  + " РезультатОперацииBurkUPDATE "
                            + РезультатОперацииBurkUPDATE +
                            " ОперацияInsert " +ОперацияInsert);
                }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатОперацииBurkUPDATE;
    }
    // TODO: 04.07.2023 ОБНОВЛЕНИЕ
    CopyOnWriteArrayList<Integer> OrganizationОбновлениеДанных(@NonNull Context context,
                                                         @NonNull String имяТаблицаAsync,
                                                         @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite,
                                                         @NonNull JsonNode jsonNodeParentMAP) {
        // TODO: 26.04.2023 Insert
        CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
        try{
            this.context=context;
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            String  SQlOperUpdate=  " UPDATE "+имяТаблицаAsync+" SET  id=?     WHERE  uuid=?  ;";

            SQLiteStatement sqLiteStatementInsert = методGetSqliteStatement(Create_Database_СамаБАзаSQLite, SQlOperUpdate);

            // TODO: 04.07.2023  UPDARE Organization

            Integer ОперацияUpdate=      sqLiteStatementInsert.executeUpdateDelete();
            // ОперацияInsert = Create_Database_СамаБАзаSQLite.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
            if (ОперацияUpdate>0) {
                РезультатОперацииBurkUPDATE.add(ОперацияUpdate);
                // TODO: 27.04.2023  повышаем верисю данных
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync  + " РезультатОперацииBurkUPDATE "
                        + РезультатОперацииBurkUPDATE +
                        " ОперацияUpdate " +ОперацияUpdate  +"  sqLiteStatementInsert " +sqLiteStatementInsert);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  РезультатОперацииBurkUPDATE;
    }



    @NonNull
    private SQLiteStatement методGetSqliteStatement(@NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite, String SQlOperUpdate) {
        SQLiteStatement sqLiteStatementInsert = null;
        try{
        sqLiteStatementInsert= Create_Database_СамаБАзаSQLite.compileStatement(SQlOperUpdate);
        sqLiteStatementInsert.clearBindings();
        sqLiteStatementInsert.bindLong(1,new Random().nextInt(888));//"id"
        sqLiteStatementInsert.bindString(2,"name2");//"name"
        sqLiteStatementInsert.bindString(3,"fullname3");//"fullname"
        sqLiteStatementInsert.bindString(4,"inn4");//"inn"
        String ddbyter="Морару Морару  Привет";
        byte[] data =ddbyter.getBytes(StandardCharsets.UTF_16) ;
        sqLiteStatementInsert.bindBlob(5,data);//"kpp"

        sqLiteStatementInsert.bindString(6,"2022-11-27 22:54:19.150");//"date_update"
        sqLiteStatementInsert.bindLong(7,5);//"user_update"
        sqLiteStatementInsert.bindDouble(8,new Random().nextDouble());//"chosen_organization"
        sqLiteStatementInsert.bindLong(9,new Random().nextInt(777));//"current_table"
        sqLiteStatementInsert.bindLong(10,new Random().nextInt(999));//"uuid"
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
