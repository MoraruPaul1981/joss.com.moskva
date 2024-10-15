package com.dsy.dsu.LayerApp.SettingsApp.Model.bl_SettingsActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import java.util.Date;

public class SLLBenessLogicMode {

    Context context;

    public SLLBenessLogicMode(Context context) {
        this.context = context;
    }


    public   String   getModeSLL(){
        String getMode_ssl="http";
        try{

            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/successlogin ");
            ContentResolver contentResolverPublicID = context.getContentResolver();
            Cursor getCursorPublicID = contentResolverPublicID.query(uri, new String[]{},
                    new String("SELECT mode_ssl  FROM  successlogin "),//TODO " SELECT id FROM successLogin ORDER BY date_update DESC LIMIT 1  "
                    new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

            if (getCursorPublicID.getCount() > 0) {
                getCursorPublicID.moveToFirst();
                getMode_ssl    = getCursorPublicID.getString(0);
            }
            getCursorPublicID.close();

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  getMode_ssl;
    }



}
