package com.dsy.dsu.Business_logic_Only_Class.DATE;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

public class SubClassCursorLoader {
    // TODO: 25.11.2022 новы метод получение данных для всех
    public Cursor CursorLoaders(@NonNull Context context, @NonNull Bundle bundle) {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
            String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecursorloader/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
            cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
}