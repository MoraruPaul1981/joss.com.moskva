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
            ИменаТаблицыОтАндройда.add("errordsu1");
            ИменаТаблицыОтАндройда.add("depatment");
            ИменаТаблицыОтАндройда.add("fio");
            ИменаТаблицыОтАндройда.add("region");
            ИменаТаблицыОтАндройда.add("cfo");
            ИменаТаблицыОтАндройда.add("settings_tabels");
            ИменаТаблицыОтАндройда.add("notifications");
            ИменаТаблицыОтАндройда.add("templates");
            ИменаТаблицыОтАндройда.add("fio_template");
            ИменаТаблицыОтАндройда.add("chat_users");
            ИменаТаблицыОтАндройда.add("chats");
            ИменаТаблицыОтАндройда.add("data_chat");
            ИменаТаблицыОтАндройда.add("tabel");
            ИменаТаблицыОтАндройда.add("data_tabels");
            ИменаТаблицыОтАндройда.add("view_onesignal");
            ИменаТаблицыОтАндройда.add("data_notification");
            ИменаТаблицыОтАндройда.add("nomen_vesov");
            ИменаТаблицыОтАндройда.add("type_materials");
            ИменаТаблицыОтАндройда.add("get_materials_data");
            ИменаТаблицыОтАндройда.add("company");
            ИменаТаблицыОтАндройда.add("track");
            ИменаТаблицыОтАндройда.add("prof");
            ИменаТаблицыОтАндройда.add("order_tc");
            ИменаТаблицыОтАндройда.add("vid_tc");
            ИменаТаблицыОтАндройда.add("materials_databinary");
            ИменаТаблицыОтАндройда.add("organization");
            Log.d(this.getClass().getName(), "  ИменаТаблицыОтАндройда" + ИменаТаблицыОтАндройда);
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
