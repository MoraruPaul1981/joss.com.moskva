package com.dsy.dsu.AllDatabases.JsonSerializerAndDeserializer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.dsy.dsu.AllDatabases.ROOM.ROOMDatabase;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;

public class FindEmptyUUID {
    long методПосикаUUIDDeseializer(@NonNull Context context,
                                    @NonNull String имяТаблицаAsync,
                                    @NonNull SQLiteDatabase Create_Database_СамаБАзаSQLite
            ,@NonNull JsonNode jsonNodeParentMAP){
        long ЕслиИлиНЕтUUID=0l;
        try{
            Long UUIDForFind= null;
            if (jsonNodeParentMAP.has("uuid")) {
                UUIDForFind = jsonNodeParentMAP.get("uuid").longValue();

                String sql = "SELECT COUNT(*) FROM " + имяТаблицаAsync+" WHERE uuid='"+UUIDForFind.toString()+"'";
                SQLiteStatement statement = Create_Database_СамаБАзаSQLite.compileStatement(sql);
                ЕслиИлиНЕтUUID = statement.simpleQueryForLong();
                // TODO: 06.07.2023 exit
                statement.close();
                // TODO: 27.04.2023  повышаем верисю данных
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ЕслиИлиНЕтUUID " +ЕслиИлиНЕтUUID + " UUIDForFind "+ UUIDForFind);


            }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return ЕслиИлиНЕтUUID;
    }
// TODO: 07.09.2023 ROOM
long методПосикаROOMUUIDDeseializer(@NonNull Context context,
                                @NonNull String имяТаблицаAsync,
                                @NonNull ROOMDatabase GetROOM
        ,@NonNull JsonNode jsonNodeParentMAP){
    long ЕслиИлиНЕтUUID=0l;
    try{
        Long UUIDForFind= jsonNodeParentMAP.get("uuid").longValue();
        String sql = "SELECT COUNT(*) FROM " + имяТаблицаAsync+" WHERE uuid='"+UUIDForFind.toString()+"'";
        SupportSQLiteStatement statement = GetROOM.compileStatement(sql);
        ЕслиИлиНЕтUUID = statement.simpleQueryForLong();
        // TODO: 06.07.2023 exit
        statement.close();
        // TODO: 27.04.2023  повышаем верисю данных
        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " ЕслиИлиНЕтUUID " +ЕслиИлиНЕтUUID + " UUIDForFind "+ UUIDForFind);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    return ЕслиИлиНЕтUUID;
}
}
