package com.dsy.dsu.CodeOrdersAnTransports.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.Date;

public class MainActivityOrdersTransports extends AppCompatActivity {
    // TODO: 25.04.2023 Переменные
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеЗаказаТранспорта;
    private LinearLayout linear_main_ordertransport;
    private BottomNavigationView BottomNavigationOrderTransport;

    private BottomNavigationItemView bottomNavigationItemViewвыход;

    private BottomNavigationItemView bottomNavigationItemView2создать;

    private BottomNavigationItemView bottomNavigationItemView3обновить;

    private ProgressBar progressBarСканирование;

    private HorizontalScrollView horizontalScrollViewOrderTransport;

    private   SubClassStartingFragmentOrderTran subClassStartingFragmentOrderTran;
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
            linear_main_ordertransport =  (LinearLayout) findViewById(R.id.linear_main_ordertransport);
            ViewGroup.LayoutParams params = linear_main_ordertransport.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            linear_main_ordertransport.setLayoutParams(params);
            BottomNavigationOrderTransport =  (BottomNavigationView) findViewById(R.id.BottomNavigationOrderTransport);
            BottomNavigationOrderTransport.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = (BottomNavigationItemView) BottomNavigationOrderTransport.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать =(BottomNavigationItemView) BottomNavigationOrderTransport.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(70);
            bottomNavigationItemView3обновить = (BottomNavigationItemView) BottomNavigationOrderTransport.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
            progressBarСканирование=  (ProgressBar) findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);

            horizontalScrollViewOrderTransport= (HorizontalScrollView)  findViewById(R.id.horizontalScrollViewOrderTransport);
            horizontalScrollViewOrderTransport.setFillViewport(true);
            horizontalScrollViewOrderTransport.fullScroll(HorizontalScrollView.FOCUS_FORWARD);
            horizontalScrollViewOrderTransport.setLeftEdgeEffectColor(Color.parseColor("#CB2377"));
            horizontalScrollViewOrderTransport.setRightEdgeEffectColor(Color.parseColor("#688DC4"));
            horizontalScrollViewOrderTransport.setSmoothScrollingEnabled(false);

            // ani = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row);
            // TODO: 26.04.2023 Запускаем Ордер Транпорта
            subClassStartingFragmentOrderTran=new SubClassStartingFragmentOrderTran();
            subClassStartingFragmentOrderTran.методЗапускаФрагментаОрдерТранспорта();
            subClassStartingFragmentOrderTran.методГоризонтальнаяПрокрутка();
            subClassStartingFragmentOrderTran.  методСлушательCallsBackFragmentManagers();
            // TODO: 11.05.2023 Горизотнтальная Прокрутка
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
            fragmentManager.clearBackStack(null);
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_СозданиеЗаказаТранспорта = new FragmentOrderTransportOneChane();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.add(R.id.linear_main_ordertransport, fragment_СозданиеЗаказаТранспорта).commit();//.layout.activity_for_fragemtb_history_tasks
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

        private void методСлушательCallsBackFragmentManagers() {
        try{
            fragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
                    super.onFragmentPreAttached(fm, f, context);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentStopped(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentDestroyed(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }

                @Override
                public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentDetached(fm, f);
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
                }
            },true);
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

        private void методГоризонтальнаяПрокрутка() {
            try {
                ViewTreeObserver viewTreeObserver = horizontalScrollViewOrderTransport.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver
                            .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    // interestedInView is ready for size and position
                                    // queries because it has been laid out
                                    horizontalScrollViewOrderTransport
                                            .fullScroll(   HorizontalScrollView.FOCUS_FORWARD);
                                    Log.d(getApplicationContext().getClass().getName(), "\n"
                                            + " время: " + new Date()+"\n+" +
                                            " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                }
                            });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void МетодДизайнПрограссБара() {
            progressBarСканирование.postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
                progressBarСканирование.setIndeterminate(true);
            },1000);
        }
}



}