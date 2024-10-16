package com.dsy.dsu.LayerBunessLogic.GrudOpersions;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

public class GetSettingCursor {


    Context context;

    public GetSettingCursor(Context context) {
        this.context = context;
    }




    public Cursor getSettingCursor(@NotNull  String sql,@NotNull  String tableoperations,@NotNull  String[] getWhrere){
        // TODO: 14.10.2024
        Cursor getAllCursor=null;
        try{
            //  GetVersionUp getVersionUp=new GetVersionUp(context);
// TODO: 14.10.2024
           // String sql=  " SELECT * FROM successLogin ORDER BY date_update DESC LIMIT 1  ";
            //String sql=  " SELECT * FROM view_tasks  WHERE    user_update=? AND status_write<>? AND message IS NOT NULL  ORDER BY status_write, date_update DESC   ";
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + tableoperations + "");
            ContentResolver contentResolverPublicID = context.getContentResolver();
            getAllCursor = contentResolverPublicID.query(uri, new String[]{},
                    new String(sql),
                    getWhrere, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

            if (getAllCursor.getCount() > 0) {
                getAllCursor.moveToFirst();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getAllCursor " +getAllCursor);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getAllCursor;
    }




}
