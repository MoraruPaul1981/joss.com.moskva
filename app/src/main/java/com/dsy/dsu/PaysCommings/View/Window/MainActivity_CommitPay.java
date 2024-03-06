package com.dsy.dsu.PaysCommings.View.Window;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.PaysCommings.Model.BinderService1cCommitPay;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity_CommitPay extends FragmentActivity  implements BinderService1cCommitPay {
    private Activity activity;
    private FragmentManager fragmentManagerДляСогласование;
    private FragmentTransaction fragmentTransactionляСогласование;
    private Fragment fragment_дляСогласованиеПерваяКнопка;
   public    Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;
    private FragmentManager fragmentManager;



    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            /*   setContentView(R.layout.activity_main_fragment1_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks*/
            setContentView(R.layout.activity_main_fisrt_for_commitpay);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks
            activity = this;
            fragmentManager =  getSupportFragmentManager();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


            BinderingServiseFor1c binderingServiseFor1c=new BinderingServiseFor1c();
            binderingServiseFor1c.bindderingservice1C();

            Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляСогласование);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 03.11.2022
        try{
        new SubClass_Only_ActivyMain_Commit_Buccess_Logic(getApplicationContext(),activity).МетодЗапускФрагментаСогласованиеСАктивти();
        Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляСогласование);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

}

    @Override
    public Service_Notificatios_Для_Согласования.LocalBinderДляСогласования
    bringBackString( ) {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + " binderСогласования1C " +binderСогласования1C);
        return binderСогласования1C;
    }

    private class SubClass_Only_ActivyMain_Commit_Buccess_Logic {
        //TODO
        public SubClass_Only_ActivyMain_Commit_Buccess_Logic(Context context, Activity activity) {
            // TODO: 10.03.2022
        }
    protected void МетодЗапускФрагментаСогласованиеСАктивти() {
        try{
         fragmentManagerДляСогласование = getSupportFragmentManager();
            fragmentTransactionляСогласование = fragmentManagerДляСогласование.beginTransaction();
            fragment_дляСогласованиеПерваяКнопка = new Fragment1_List_CommitPay();
            Bundle data=new Bundle();
            data.putBinder("binderСогласования1C",binderСогласования1C);
            fragment_дляСогласованиеПерваяКнопка.setArguments(data);
            fragmentTransactionляСогласование.replace(R.id.activity_main_fisrt_for_commitpays, fragment_дляСогласованиеПерваяКнопка).commit();//.layout.activity_for_fragemtb_history_tasks
            fragmentTransactionляСогласование.show(fragment_дляСогласованиеПерваяКнопка);
            Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляСогласование);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    }

    // TODO: 03.10.2023 class bizneslogica




    // TODO: 07.11.2023 класс buoundi service for 1c
    class BinderingServiseFor1c{

        void bindderingservice1C(){
            // TODO: 27.03.2023 BINDING#4

                try {
                    ServiceConnection connectionСогласования = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            try {
                                binderСогласования1C = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) service;
                              if (service.isBinderAlive()) {

                                  Bundle bundlebinder1ССлужба=new Bundle();
                                  bundlebinder1ССлужба.putBinder("binderСогласования1C",binderСогласования1C);
                                  fragmentManager.setFragmentResult("callbackbinderdashbord",    bundlebinder1ССлужба);

                                    Log.i(getApplicationContext().getClass().getName(), "    onServiceConnected  service.isBinderAlive()"
                                            + service.isBinderAlive());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            try {
                                Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()" + binderСогласования1C.isBinderAlive());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    };
                    Intent intentЗапускСлужюыыСинхрониазцииБиндинг1C = new Intent(getApplicationContext(), Service_Notificatios_Для_Согласования.class);
                    intentЗапускСлужюыыСинхрониазцииБиндинг1C.setAction("com.Service_Notificatios_Для_Согласования");
                    bindService(intentЗапускСлужюыыСинхрониазцииБиндинг1C , connectionСогласования, Context.BIND_AUTO_CREATE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

        }


    }





}

