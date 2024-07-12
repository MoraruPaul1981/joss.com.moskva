package com.sous.server.presentationlayer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sous.server.businesslayer.BI_presentationlayer.BI_Activity_Fragment.BiMainActivityNewServerScanner;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.R;


import java.util.Date;


public class MainActivityNewServerScanner extends AppCompatActivity {
    protected FragmentManager fragmentManager;
    protected   FragmentTransaction getTransactionscanner;
    protected Long version;


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

            fragmentManager = getSupportFragmentManager();
            getTransactionscanner = fragmentManager.beginTransaction();

       /*     TODO: запускаем бизнес логику Активити Сканера Сервера
       *       */

            BiMainActivityNewServerScanner biMainActivityNewServerScanner=new BiMainActivityNewServerScanner(getApplicationContext(),getTransactionscanner,
                   fragmentManager,this);


            version=    biMainActivityNewServerScanner.  getversionCurrentPC();

            biMainActivityNewServerScanner.     МетодРАзрешенияBlurtooTКлиент();

            // TODO: 07.02.2023 запус самого СЕРВЕРА СКАНРРОВНИЕ..
            biMainActivityNewServerScanner.   МетодЗапускBootФрагмента(new FragmentBootScannerServer());//todo Запускам клиента или сервер фрагмент



            Log.i(this.getClass().getName(), "  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " время " + new Date().toLocaleString());
            Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() +
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







}



