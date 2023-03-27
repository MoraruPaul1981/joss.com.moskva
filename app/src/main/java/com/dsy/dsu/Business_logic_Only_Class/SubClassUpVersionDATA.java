package com.dsy.dsu.Business_logic_Only_Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Build;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;

import org.jetbrains.annotations.NotNull;

public class SubClassUpVersionDATA {

    // TODO: 19.11.2022  метод коптрует верстию данныхизтекущей таблицы в системныную табллицу
    public Integer МетодVesrionUPMODIFITATION_Client(@NotNull String Таблица,@NotNull Context context, @NotNull SQLiteDatabase getССылкаНаСозданнуюБазу) {
        Integer Результат_ПовышенаяВерсия = 0;
        try {
            String ТаблицаСистемная = "MODIFITATION_Client";
            SQLiteQueryBuilder      SQLBuilderВерсияДанныхСистемнаяТАблицы = new SQLiteQueryBuilder();
            Long ВерсияДанныхПослеСинхрониазацииДляЗаписи = МетодАнализаВерсииCurrentTable(Таблица, context,getССылкаНаСозданнуюБазу );
            // TODO: 22.11.2021  ПОСЛЕ УСПЕШНОЙ ОПЕРАЦИИ ПОДТВЕРЖДАЕМ ТРАНЗАУЙИЮ
            Log.d(this.getClass().getName(), "  ВерсияДанныхПослеСинхрониазацииДляЗаписи   " + ВерсияДанныхПослеСинхрониазацииДляЗаписи + " Таблица " + Таблица);
            String СгенерированованныйДата = new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            ContentValues contentValuesДляПоднятияВерсии = new ContentValues();

            contentValuesДляПоднятияВерсии.put("versionserveraandroid", СгенерированованныйДата);
            contentValuesДляПоднятияВерсии.put("versionserveraandroid_version", ВерсияДанныхПослеСинхрониазацииДляЗаписи);

            SQLBuilderВерсияДанныхСистемнаяТАблицы.setTables(ТаблицаСистемная);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Результат_ПовышенаяВерсия = SQLBuilderВерсияДанныхСистемнаяТАблицы.
                        update(getССылкаНаСозданнуюБазу, contentValuesДляПоднятияВерсии, "name=?", new String[]{Таблица.toLowerCase()});
            } else {
                Результат_ПовышенаяВерсия = getССылкаНаСозданнуюБазу.update(ТаблицаСистемная, contentValuesДляПоднятияВерсии,
                        "name=?", new String[]{Таблица.toLowerCase()});
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  Результат_ПовышенаяВерсия   " + Результат_ПовышенаяВерсия);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return Результат_ПовышенаяВерсия;

    }


    // TODO:MAX cURRENT table
    public Long МетодАнализаВерсииCurrentTable( @NotNull String Текущаятаблицы,
                                                @NotNull Context context,
                                                @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) {
        Long  АнализВерсииMAXCurrentTable=0l;
        try   ( SQLiteCursor КурсоАнализVersionCurrentTable=
                        (SQLiteCursor) getБазаДанныхДЛяОперацийВнутри.rawQuery(" SELECT MAX ( current_table  ) AS MAX_R  FROM " +  Текущаятаблицы.trim()+"" , null);) {
/*         Курсор_КоторыйПолучаетМаксимальюнуВерсиюДанных= (SQLiteCursor)
                        getБазаДанныхДЛяОперацийВнутри.rawQuery(" SELECT MAX ( " +ТекущаяяКолонкаТаблицы.trim() + "  ) AS MAX_MODIFITATION_Client  FROM " + уже запомнитовано провренно
                                "  MODIFITATION_Client  WHERE  name = '" + Текущаятаблицы.trim().toLowerCase() +"'  ;", null);*/
            if(КурсоАнализVersionCurrentTable.getCount()>0){
                КурсоАнализVersionCurrentTable.moveToFirst();
                Integer  ИндексГдеСтолбикМах=КурсоАнализVersionCurrentTable.getColumnIndex("MAX_R");
                АнализВерсииMAXCurrentTable=КурсоАнализVersionCurrentTable.getLong(ИндексГдеСтолбикМах);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " АнализВерсииMAXCurrentTable  " + АнализВерсииMAXCurrentTable + "  Текущаятаблицы" + Текущаятаблицы);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  АнализВерсииMAXCurrentTable;
    }



    // TODO:MAX cURRENT table
    public Long МетодПовышаемВерсииCurrentTable( @NotNull String Текущаятаблицы,
                                                 @NotNull Context context,
                                                 @NotNull SQLiteDatabase getБазаДанныхДЛяОперацийВнутри) {
        Long  ПовышенняВерсияMAXCurrentTable=0l;
        try  (   SQLiteCursor  КурсорMAXVersionCurrentTable=
                         (SQLiteCursor) getБазаДанныхДЛяОперацийВнутри.rawQuery(" SELECT MAX ( current_table  ) AS MAX_R  FROM " +  Текущаятаблицы.trim()+"" , null);

                 SQLiteCursor         Курсор_АнализMODIFITATION_Client= (SQLiteCursor)
                         getБазаДанныхДЛяОперацийВнутри.rawQuery(" SELECT  localversionandroid_version  FROM " +
                                 "  MODIFITATION_Client  WHERE  name = ? ", new String[]{ Текущаятаблицы.trim().toLowerCase()});) {
            if(КурсорMAXVersionCurrentTable.getCount()>0){
                КурсорMAXVersionCurrentTable.moveToFirst();
                Integer  ИндексГдеСтолбикМах=КурсорMAXVersionCurrentTable.getColumnIndex("MAX_R");
                ПовышенняВерсияMAXCurrentTable=КурсорMAXVersionCurrentTable.getLong(ИндексГдеСтолбикМах);
                // TODO: 22.11.2022
                Long ПолучениеВерсииАнализаИзMODIFITATION_Client=0l;
                if(Курсор_АнализMODIFITATION_Client.getCount()>0){
                    Курсор_АнализMODIFITATION_Client.moveToFirst();
                    Integer  ИндексГMODIFITATION_Client=Курсор_АнализMODIFITATION_Client.getColumnIndex("localversionandroid_version");
                    ПолучениеВерсииАнализаИзMODIFITATION_Client=    Курсор_АнализMODIFITATION_Client.getLong(ИндексГMODIFITATION_Client);
                }
                if(ПовышенняВерсияMAXCurrentTable>=ПолучениеВерсииАнализаИзMODIFITATION_Client){
                    ПовышенняВерсияMAXCurrentTable++;
                }else{
                    ПовышенняВерсияMAXCurrentTable=    ПолучениеВерсииАнализаИзMODIFITATION_Client;
                    ПовышенняВерсияMAXCurrentTable++;
                }


            }else {
                ПовышенняВерсияMAXCurrentTable=1l;
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ПовышенняВерсияMAXCurrentTable  " + ПовышенняВерсияMAXCurrentTable + "  Текущаятаблицы" + Текущаятаблицы);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ПовышенняВерсияMAXCurrentTable;
    }

}
