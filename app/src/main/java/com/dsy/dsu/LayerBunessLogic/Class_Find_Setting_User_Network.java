package com.dsy.dsu.LayerBunessLogic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import androidx.annotation.NonNull;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.bl_PasswordsApp.GetSuccessLogin;


public class Class_Find_Setting_User_Network {

     Context context;
     SQLiteDatabase sqLiteDatabase ;

    public Class_Find_Setting_User_Network(@NonNull Context context,@NonNull  SQLiteDatabase sqLiteDatabase ) {

        this.context =context;
        this.sqLiteDatabase =sqLiteDatabase;

      // sqLiteDatabase  = EntryPoints.get(context, HiltInterfacesqlite.class).getHiltSqlite();
    }
    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ


    public boolean МетодПроветяетКакуюУстановкуВыбралПользовательСети() {
        boolean getUsersingNeworktype = false;
        String РежимСетиВыбранныйПользователем = new String();
        try {

     РежимСетиВыбранныйПользователем = new Class_Type_Connenction_Tel(context).МетодОпределяемКакойТипПодключениеWIFIилиMobile();

            GetSuccessLogin getSuccessLogin   =  new GetSuccessLogin(context);
            Cursor getCursorNetworkUser= getSuccessLogin.gettingSuccessLogin();



     String setingNwtorkUser = new String();

            // TODO: 08.10.2024
            if (getCursorNetworkUser!=null) {
                if (getCursorNetworkUser.getCount() > 0) {
                    getCursorNetworkUser.moveToFirst();
                    setingNwtorkUser = getCursorNetworkUser.getString(0);

                    if (setingNwtorkUser.
                            equalsIgnoreCase(РежимСетиВыбранныйПользователем) ||
                            setingNwtorkUser.trim().equalsIgnoreCase("Mobile") ) {
                        // TODO: 29.09.2021  надо синхрониазциии
                        getUsersingNeworktype=true;
                    }else{
                        getUsersingNeworktype=false;
                    }
                } else {
                    Log.d(this.getClass().getName(), "getCursorNetworkUser " + getCursorNetworkUser);
                  getUsersingNeworktype=true;
                }
                }else {

                Log.d(this.getClass().getName(), "getCursorNetworkUser " + getCursorNetworkUser);
                getUsersingNeworktype=true;
            }

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return getUsersingNeworktype;
    }

}
