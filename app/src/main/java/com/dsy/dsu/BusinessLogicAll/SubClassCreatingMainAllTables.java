package com.dsy.dsu.BusinessLogicAll;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

public class SubClassCreatingMainAllTables {
    Context context;

    public SubClassCreatingMainAllTables(@NotNull Context context) {

    this.context=context;
    }

    public CopyOnWriteArrayList<String> методCreatingMainTabels(@NotNull Context context) throws InterruptedException {
        CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда = new CopyOnWriteArrayList();
        try {
            ИменаТаблицыОтАндройда.addIfAbsent("errordsu1");
            ИменаТаблицыОтАндройда.addIfAbsent("depatment");
            ИменаТаблицыОтАндройда.addIfAbsent("fio");
            ИменаТаблицыОтАндройда.addIfAbsent("region");
            ИменаТаблицыОтАндройда.addIfAbsent("cfo");
            ИменаТаблицыОтАндройда.addIfAbsent("settings_tabels");
            ИменаТаблицыОтАндройда.addIfAbsent("notifications");
            ИменаТаблицыОтАндройда.addIfAbsent("templates");
            ИменаТаблицыОтАндройда.addIfAbsent("fio_template");
            ИменаТаблицыОтАндройда.addIfAbsent("chat_users");
            ИменаТаблицыОтАндройда.addIfAbsent("chats");
            ИменаТаблицыОтАндройда.addIfAbsent("data_chat");
            ИменаТаблицыОтАндройда.addIfAbsent("tabel");
            ИменаТаблицыОтАндройда.addIfAbsent("data_tabels");
            ИменаТаблицыОтАндройда.addIfAbsent("view_onesignal");
            ИменаТаблицыОтАндройда.addIfAbsent("data_notification");
            ИменаТаблицыОтАндройда.addIfAbsent("nomen_vesov");
            ИменаТаблицыОтАндройда.addIfAbsent("type_materials");
            ИменаТаблицыОтАндройда.addIfAbsent("get_materials_data");
            ИменаТаблицыОтАндройда.addIfAbsent("company");
            ИменаТаблицыОтАндройда.addIfAbsent("track");
            ИменаТаблицыОтАндройда.addIfAbsent("prof");
            ИменаТаблицыОтАндройда.addIfAbsent("order_tc");
            ИменаТаблицыОтАндройда.addIfAbsent("vid_tc");
            ИменаТаблицыОтАндройда.addIfAbsent("materials_databinary");
            ИменаТаблицыОтАндройда.addIfAbsent("organization");

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"  ИменаТаблицыОтАндройда" + ИменаТаблицыОтАндройда);

            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ИменаТаблицыОтАндройда;
    }
}
