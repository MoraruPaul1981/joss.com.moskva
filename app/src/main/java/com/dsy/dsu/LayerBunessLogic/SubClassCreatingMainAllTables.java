package com.dsy.dsu.LayerBunessLogic;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

public class SubClassCreatingMainAllTables {
    Context context;

    public SubClassCreatingMainAllTables(@NotNull Context context) {

    this.context=context;
    }

    public CopyOnWriteArrayList<String> методCreatingMainTabels(@NotNull Context context)  {
        CopyOnWriteArrayList<String> getMainListTable = new CopyOnWriteArrayList();
        try {
            getMainListTable.addIfAbsent("errordsu1");
            getMainListTable.addIfAbsent("depatment");
            getMainListTable.addIfAbsent("fio");
            getMainListTable.addIfAbsent("region");
            getMainListTable.addIfAbsent("cfo");
            getMainListTable.addIfAbsent("settings_tabels");
            getMainListTable.addIfAbsent("notifications");
            getMainListTable.addIfAbsent("templates");
            getMainListTable.addIfAbsent("fio_template");
            getMainListTable.addIfAbsent("chat_users");
            getMainListTable.addIfAbsent("chats");
            getMainListTable.addIfAbsent("data_chat");
            getMainListTable.addIfAbsent("tabel");
            getMainListTable.addIfAbsent("data_tabels");
            getMainListTable.addIfAbsent("view_onesignal");
            getMainListTable.addIfAbsent("data_notification");
            getMainListTable.addIfAbsent("nomen_vesov");
            getMainListTable.addIfAbsent("type_materials");
            getMainListTable.addIfAbsent("get_materials_data");
            getMainListTable.addIfAbsent("company");
            getMainListTable.addIfAbsent("track");
            getMainListTable.addIfAbsent("prof");
            getMainListTable.addIfAbsent("order_tc");
            getMainListTable.addIfAbsent("vid_tc");
            getMainListTable.addIfAbsent("materials_databinary");
            getMainListTable.addIfAbsent("organization");

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"  getMainListTable" + getMainListTable);

            ///todo публикум название таблицы или цифру его
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getMainListTable;
    }
}
