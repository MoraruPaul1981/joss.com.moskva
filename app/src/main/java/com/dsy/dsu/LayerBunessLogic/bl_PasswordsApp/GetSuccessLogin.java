package com.dsy.dsu.LayerBunessLogic.bl_PasswordsApp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;

public class GetSuccessLogin {

  private   Context context;

    public GetSuccessLogin(Context context) {
        this.context = context;
    }




    @NotNull
    public  Cursor gettingSuccessLogin(){
      // TODO: 14.10.2024
      Cursor cursorLoginAndPassword=null;
  try{
// TODO: 14.10.2024
      String sql=  " SELECT * FROM successLogin ORDER BY date_update DESC LIMIT 1  ";
      Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/" + "successLogin" + "");
      ContentResolver contentResolverPublicID = context.getContentResolver();
      cursorLoginAndPassword = contentResolverPublicID.query(uri, new String[]{},
              new String(sql),
              new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

      if (cursorLoginAndPassword.getCount() > 0) {
          cursorLoginAndPassword.moveToFirst();
      }

      Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorLoginAndPassword " +cursorLoginAndPassword);
  } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
   return cursorLoginAndPassword;
  }



    public   ConcurrentHashMap<String,String> getSuccessLoginAndPassword(@NotNull Cursor cursorLoginAndPassword){
        // TODO: 14.10.2024
        ConcurrentHashMap<String,String> getSuccessLoginAndPassword=new ConcurrentHashMap<>();
        try{
// TODO: 14.10.2024
            if (cursorLoginAndPassword!=null && cursorLoginAndPassword.getCount()>0) {

                int colsuccess_users=     cursorLoginAndPassword.getColumnIndex("success_users");
                getSuccessLoginAndPassword.putIfAbsent("success_users",cursorLoginAndPassword.getString(colsuccess_users));

                int colsuccess_login=     cursorLoginAndPassword.getColumnIndex("success_login");
                getSuccessLoginAndPassword.putIfAbsent("success_login",cursorLoginAndPassword.getString(colsuccess_login));


            }

            cursorLoginAndPassword.close();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorLoginAndPassword " +cursorLoginAndPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getSuccessLoginAndPassword;
    }



    public    Integer getSuccessPublicID(@NotNull Cursor cursorPublicID){
        // TODO: 14.10.2024
        Integer getSuccessPublicID=0;
        try{
// TODO: 14.10.2024
            if (cursorPublicID!=null) {
                int colpublicid=     cursorPublicID.getColumnIndex("publicid");
                getSuccessPublicID=   cursorPublicID.getInt(colpublicid);

            }

            cursorPublicID.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getSuccessPublicID " +getSuccessPublicID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getSuccessPublicID;
    }


    public    Integer getSuccessModeConnection(@NotNull Cursor cursorPublicID){
        // TODO: 14.10.2024
        Integer getSuccessPublicID=0;
        try{
// TODO: 14.10.2024
            if (cursorPublicID!=null) {
                int colpublicid=     cursorPublicID.getColumnIndex("mode_connection");
                getSuccessPublicID=   cursorPublicID.getInt(colpublicid);


            }

            cursorPublicID.close();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getSuccessPublicID " +getSuccessPublicID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getSuccessPublicID;
    }



    public    Integer getWriteModeConnection(@NotNull String getchangeMode, @NotNull Integer getuuidLocal, @NotNull String columsChange){
        // TODO: 14.10.2024
        Integer getmMode_Connection=0;
        try{
            String ИмяТаблицы= "successlogin";
            ContentValues contentValuesChangeModeSLL=new ContentValues();
            Uri uri = Uri.parse("content://com.dsy.dsu.providerforsystemtables/"+ИмяТаблицы+"");
            // TODO: 08.10.2024 Дополнительное добавление данных
            ContentResolver contentProvidermode_connection = context.getContentResolver();
            contentValuesChangeModeSLL.put(columsChange,getchangeMode);

            // TODO: 08.10.2024 Находим если такой  Пользователь
            contentValuesChangeModeSLL.put("publicid",getuuidLocal);
            // TODO: 12.04.2023 UPDATER PUBLIC ID
            if(getuuidLocal>0 ){
                // TODO: 12.04.2023 UPDATER model_ssl
                getmMode_Connection=  contentProvidermode_connection.update(uri, contentValuesChangeModeSLL,null,null);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ "  getmMode_Connection " +getmMode_Connection);
                // TODO: 08.10.2024 UNSERT PUBLIC ID
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getmMode_Connection " +getmMode_Connection);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getmMode_Connection;
    }







}
