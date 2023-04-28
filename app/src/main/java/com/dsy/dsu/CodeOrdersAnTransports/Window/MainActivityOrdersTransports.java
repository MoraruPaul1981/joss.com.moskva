package com.dsy.dsu.CodeOrdersAnTransports.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class MainActivityOrdersTransports extends AppCompatActivity {
    // TODO: 25.04.2023 Переменные
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеЗаказаТранспорта;
    private LinearLayout activity_main_order_transport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_order_transport);
        // TODO: 25.04.2023  ЗАКАЗ Транспорта
            activity=this;
            fragmentManager = getSupportFragmentManager();
            getSupportActionBar().hide(); ///скрывать тул бар
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            fragmentManager = getSupportFragmentManager();
            activity_main_order_transport =  (LinearLayout) findViewById(R.id.activity_main_order_transport);
            ViewGroup.LayoutParams params = activity_main_order_transport.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_main_order_transport.setLayoutParams(params);
            // TODO: 26.04.2023 Запускаем Ордер Транпорта
            SubClassStartingFragmentOrderTran subClassStartingFragmentOrderTran=new SubClassStartingFragmentOrderTran();
            subClassStartingFragmentOrderTran.методЗапускаФрагментаОрдерТранспорта();

            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


// TODO: 26.04.2023  класс для запуска
    class  SubClassStartingFragmentOrderTran{


    // TODO: 26.04.2023  класс для ЗАПУСКА ЗАКАЗАТЬ НОВЫЙ ТРАНСПОРТ

    protected void методЗапускаФрагментаОрдерТранспорта() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_СозданиеЗаказаТранспорта = new FragmentOrderTransportOneChane();
            fragmentTransaction.add(R.id.activity_main_order_transport, fragment_СозданиеЗаказаТранспорта).commit();//.layout.activity_for_fragemtb_history_tasks
            fragmentTransaction.show(fragment_СозданиеЗаказаТранспорта);
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



}

}