
//////КНАЧАЛО  КЛАССА ПО СОЗДАНИЮ СХЕМЫ ДАННЫХ
package com.dsy.dsu.AllDatabases.Error;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.SubClassCreatingMainAllTables;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

//этот класс создает базу данных SQLite
public class CREATE_DATABASE_Error extends SQLiteOpenHelper{ ///SQLiteOpenHelper
     static final int VERSION =             6;//ПРИ ЛЮБОМ ИЗМЕНЕНИЕ В СТРУКТУРЕ БАЗЫ ДАННЫХ НУЖНО ДОБАВИТЬ ПЛЮС ОДНУ ЦИФРУ К ВЕРСИИ 1=1+1=2 ИТД.1
   private   Context context;
    private      SQLiteDatabase ССылкаНаСозданнуюБазу;

    public SQLiteDatabase getССылкаНаСозданнуюБазу() {
        Log.d(this.getClass().getName()," get () БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫТА ССылкаНаСозданнуюБазу.isOpen()  " +ССылкаНаСозданнуюБазу);
        return ССылкаНаСозданнуюБазу;
    }
    ///////КОНСТРУКТОР главного класса по созданию базы данных
    public CREATE_DATABASE_Error(@NotNull Context context) {/////КОНСТРУКТОР КЛАССА ПО СОЗДАНИЮ БАЗЫ ДАННЫХ
        super(context, "Database DSU-1-Error.db", null, VERSION ); // определяем имя базы данных  и ее версию
        try{
            this.context =context;

            //context.deleteDatabase(nameDAtBase  );

            if (ССылкаНаСозданнуюБазу == null ) {
                ССылкаНаСозданнуюБазу = this.getWritableDatabase(); //ссылка на схему базы данных;//ссылка на схему базы данных ГЛАВНАЯ ВСТАВКА НА БАЗУ ДСУ-1
                Log.d(this.getClass().getName()," БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫВАЕМ  ССылкаНаСозданнуюБазу==null   "
                        +ССылкаНаСозданнуюБазу.isOpen());
            }else{
                //TODO connection  else is onen false
                if (!ССылкаНаСозданнуюБазу.isOpen()    )  {
                    ССылкаНаСозданнуюБазу = this.getWritableDatabase(); //ссылка на схему базы данных;//ссылка на схему базы данных ГЛАВНАЯ ВСТАВКА НА БАЗУ ДСУ-1
                    Log.d(this.getClass().getName()," БАЗА  ДАННЫХ   ДСУ-1 ОТКРЫВАЕМ  ССылкаНаСозданнуюБазу.isOpen()  "
                            +ССылкаНаСозданнуюБазу.isOpen());
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    //  Cоздание ТАблиц
    @Override
    public void onCreate(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try {
            Log.d(this.getClass().getName(), "сработала ... НАЧАЛО  СОЗДАНИЯ ТАЛИЦ ");
            // TODO: 12.10.2022  СИСТЕМНЫЕ ТАБЛИЦЫ
            МетодСозданиеТаблицыОшибок(ССылкаНаСозданнуюБазу);
            Log.d(this.getClass().getName(), " сработала ... КОНЕЦ СОЗДАНИЯ ТАБЛИЦ " +new Date().toGMTString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }}

    private void МетодСозданиеТаблицыОшибок(SQLiteDatabase ССылкаНаСозданнуюБазу) {
        try{
            ССылкаНаСозданнуюБазу.execSQL("drop table  if exists errordsu1 ");//ТАБЛИЦА ГЕНЕРАЦИИ ОШИБОК
            ССылкаНаСозданнуюБазу.execSQL("Create table if not exists errordsu1 (" +
                    "ID_Table_ErrorDSU1 INTEGER PRIMARY KEY AUTOINCREMENT  ," +
                    " Error TEXT    NOT NULL  ," +
                    "Klass TEXT NOT NULL ," +
                    "Metod TEXT NOT NULL," +
                    "LineError INTEGER NOT NULL ," +
                    "Data_Operazii_E NUMERIC NOT NULL ,"+
                    "IdUser   INTEGER ,"+
                    "current_table   NUMERIC ,"+
                    "whose_error INTEGER NOT NULL )");
            Log.d(this.getClass().getName(), " сработала ...  создание таблицы ErrorDSU1 ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase ССылкаНаСозданнуюБазу, int oldVersion, int newVersion) {
        try{
            Log.d(this.getClass().getName(), " до СЛУЖБА  содание базы newVersion==  652   (например)  " +
                    " " + new Date()+  " newVersion " +newVersion);



            if (newVersion > oldVersion) {
                // TODO: 08.06.2021 создание Базы Данных  ЧИСТАЯ УСТАНОВКА
                onCreate(ССылкаНаСозданнуюБазу);
                Log.d(this.getClass().getName(), " СЛУЖБА  содание базы newVersion > oldVersion   " + new Date());

             }
            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "  сработала ... КОНЕЦ СОЗДАНИЯ ТАБЛИЦ ВИЮ ТРИГЕР ");

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase ССылкаНаСозданнуюБазу, int oldVersion, int newVersion) {
        onCreate(ССылкаНаСозданнуюБазу);
    }



}// конец public class CREATE_DATABASE extends SQLiteOpenHelper





































































































