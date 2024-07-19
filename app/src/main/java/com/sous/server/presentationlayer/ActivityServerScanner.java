package com.sous.server.presentationlayer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.AsyncTaskLoader;

import com.sous.server.businesslayer.BI_presentationlayer.bl_MainActivityNewServerScanner.Bi_MainActivityNewServerScanner;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.R;


import java.util.Date;


public class ActivityServerScanner extends AppCompatActivity {
    protected FragmentManager fragmentManager;

    protected Long version;
    protected Message messageGattServer;
    protected AsyncTaskLoader asyncTaskLoaderGatt  ;
    protected  SQLiteDatabase Create_Database_СамаБАзаSQLite;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_boot_scannerserver);

            getSupportActionBar().hide(); ///скрывать тул бар

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

              /////TODO создание Мэнеджера Фрагмент
            fragmentManager = getSupportFragmentManager();
            /////TODO создание Мэнеджера Фрагмент
            Log.i(this.getClass().getName(), "  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " время " + new Date().toLocaleString());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        try{
          /*  //TODO: ссылка на класс бизнес логики Сервер Сканирование
          *      */
        Bi_MainActivityNewServerScanner biMainActivityNewServerScanner=new Bi_MainActivityNewServerScanner(getApplicationContext(), fragmentManager,this);

        version=    biMainActivityNewServerScanner.  getversionCurrentPC();

        biMainActivityNewServerScanner.     МетодРАзрешенияBlurtooTКлиент();

            /*  //TODO:Иниицилизуем БАз ДАнных */
            Create_Database_СамаБАзаSQLite=    biMainActivityNewServerScanner.   МетодInitDataBase();

            getmessageGattServer();


        biMainActivityNewServerScanner.   МетодЗапускBootФрагмента(new FragmentBootServer());//todo Запускам клиента или сервер фрагмент



            Log.i(this.getClass().getName(), "  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " время " + new Date().toLocaleString());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        ContentValues valuesЗаписываемОшибки = new ContentValues();
        valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
        final Object ТекущаяВерсияПрограммы = version;
        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }


    }


    public void getmessageGattServer() {

        try {

            messageGattServer =new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " msg " +msg );
                    return false;
                }
            }).obtainMessage();
            messageGattServer.setAsynchronous(true);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

}



