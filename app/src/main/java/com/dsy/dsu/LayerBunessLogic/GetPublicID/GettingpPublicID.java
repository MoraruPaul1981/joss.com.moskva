package com.dsy.dsu.LayerBunessLogic.GetPublicID;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.bl_PasswordsApp.GetSuccessLogin;

import org.jetbrains.annotations.NotNull;

public class GettingpPublicID {




    public Integer gettingpPublicID(@NotNull Context context) {
        // TODO: 26.11.2024
        Integer getPublicID=0;
        try {

            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            // TODO: 14.10.2020 Получаем ЛОгин и Пароль
            GetSuccessLogin getSuccessLogin   =  new GetSuccessLogin(context);
            Cursor cursorLoginAndPassword= getSuccessLogin.gettingSuccessLogin();
            getPublicID=getSuccessLogin.getSuccessPublicID(cursorLoginAndPassword);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " getPublicID " +getPublicID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
    return  getPublicID;
    }


}
