package com.dsy.dsu.WorkManagers.BL_WorkMangers;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.util.List;

public class ClassAnalyasStartingForWorkManager {

private Context context;
    public ClassAnalyasStartingForWorkManager(@NonNull  Context context) {
        this.context=context;
    }

    public Boolean metodAnalyzaStaringPublicWorkManger() {
        Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
        try{
            ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                    (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
            String АктивностьЕслиЕстьTOP =null;
            List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
            if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
                КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
                if (КоличествоЗапущенныйПроуессы.size() > 0) {
                    for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                        if (ТекущаяАктивти!=null) {
                            Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);
                            // TODO: 20.02.2022
                            if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                                АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                            }
                            Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                    " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                        }
                        if (АктивностьЕслиЕстьTOP!=null ) {
                            switch (АктивностьЕслиЕстьTOP) {// case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App_OLd" :
                                //     case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                                case "com.dsy.dsu.BootAndAsync.Window.MainActivityBootAndAsync":
                                case "com.dsy.dsu.Dashboard.MainActivity_Dashboard":
                                case "com.dsy.dsu.Passwords.MainActivityPasswords":
                                    Log.i(context.getClass().getName(), " ВЫХОД  .....ТекущаяАктивтиАктивностьЕслиЕстьTOP" + ТекущаяАктивти +
                                            " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                                    // TODO: 26.03.2023
                                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое);
                                    break;
                                // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                                default:
                                    // TODO: 26.03.2023
                                    // TODO: 26.03.2023
                                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                                    break;
                            }
                        }
                    }
                }else {
                    // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                }
            }else {
                // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                        + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(),
                    " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
        }
        return РешениеЗапускатьWorkManagerИлиНетАктивтиКакое;
    }

    // TODO: 07.10.2023 single
    public Boolean metodAnalyzaStaringSinleWorkManger() {
        Boolean РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
        try{
            ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации =
                    (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
            String АктивностьЕслиЕстьTOP =null;
            List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы=null;
            if (ЗапущенныйПроуессыДляОбщейСинхрониазации!=null) {
                КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
                if (КоличествоЗапущенныйПроуессы.size() > 0) {
                    for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                        if (ТекущаяАктивти!=null) {
                            Log.i(context.getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);
                            // TODO: 20.02.2022
                            if (ТекущаяАктивти.getTaskInfo().numActivities>0) {
                                АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                            }
                            Log.i(context.getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                    " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                        }
                        if (АктивностьЕслиЕстьTOP!=null ) {
                            switch (АктивностьЕслиЕстьTOP) {// case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App_OLd" :
                                //     case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                                case "com.dsy.dsu.BootAndAsync.Window.MainActivityBootAndAsync":
                             //  case "com.dsy.dsu.Dashboard.MainActivity_Dashboard"://todo только для Single Workmnger
                                case "com.dsy.dsu.Passwords.MainActivityPasswords":
                                    Log.i(context.getClass().getName(), " ВЫХОД  .....ТекущаяАктивтиАктивностьЕслиЕстьTOP" + ТекущаяАктивти +
                                            " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                                    // TODO: 26.03.2023
                                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=false;
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое);
                                    break;
                                // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                                default:
                                    // TODO: 26.03.2023
                                    // TODO: 26.03.2023
                                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                                    break;
                            }
                        }
                    }
                }else {
                    // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                    РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                            + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
                }
            }else {
                // TODO: 26.03.2023  только для ОБЩЕЙ Work Manager
                РешениеЗапускатьWorkManagerИлиНетАктивтиКакое=true;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " АктивностьЕслиЕстьTOP "+АктивностьЕслиЕстьTOP
                        + " РешениеЗапускатьWorkManagerИлиНетАктивтиКакое " +РешениеЗапускатьWorkManagerИлиНетАктивтиКакое );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(),
                    " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Single из FaceApp в  MyWork_Async_Синхронизация_Single Exception  ошибка в классе  MyWork_Async_Синхронизация_Single" + e.toString());
        }
        return РешениеЗапускатьWorkManagerИлиНетАктивтиКакое;
    }

}

class Analr extends ClassAnalyasStartingForWorkManager{

    public Analr(@NonNull Context context) {
        super(context);
    }

    @Override
    public Boolean metodAnalyzaStaringPublicWorkManger() {
        return super.metodAnalyzaStaringPublicWorkManger();
    }

    @Override
    public Boolean metodAnalyzaStaringSinleWorkManger() {
        return super.metodAnalyzaStaringSinleWorkManger();
    }
}
