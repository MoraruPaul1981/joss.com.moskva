package com.dsy.dsu.Tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.R;


public class MainActivity_Tasks extends FragmentActivity {

    // TODO: 28.02.2022
    private Activity activity;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private Fragment fragment_дляЗадачиПерваяКнопка;

   private Class_Generations_PUBLIC_CURRENT_ID class_generations_public_current_id;
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";


 //    private ServiceConnection connectionДляОдноразовойСинхронизации;
     private  SubClass_Only_ActivyMain_Buccess_Logic subClass_only_activyMain_buccess_logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            activity=this;
            subClass_only_activyMain_buccess_logic=new SubClass_Only_ActivyMain_Buccess_Logic(getApplicationContext(),activity);
            class_generations_public_current_id      =new Class_Generations_PUBLIC_CURRENT_ID();
            /*   setContentView(R.layout.activity_main_fragment1_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks*/
            setContentView(R.layout.activity_main_fisrt_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks
            // TODO: 27.04.2021 формируем внешний вид Чата через фрагменты
            activity = this;
            // TODO: 28.02.2022
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            // TODO: 27.07.2022
            if (!WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая=   WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get() .get(0);
            if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
// TODO: 02.03.2022
                Integer ПубличныйIDДляФрагмента = class_generations_public_current_id.ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                subClass_only_activyMain_buccess_logic. МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);

               // class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаетОДНОРАЗОВУЮСинхронизациюПослкеУспешнойПроведеннойОперации(ПубличныйIDДляФрагмента, getApplicationContext());

            }         }

// TODO: 03.03.2022  ЗАПУСКАЕМ БИЗНЕС ЛОГИКУ НА АКТИВТИ ДЛЯ ФРАГМЕНТОВ ДЛЯ ЗАДАНИЯ

          new SubClass_Only_ActivyMain_Buccess_Logic(getApplicationContext(),activity).МетодЗапускФрагментаЗадачиСАктивти();

            Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);

            // TODO: 01.03.2022*/
            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



// TODO: 10.03.2022
    private class SubClass_Only_ActivyMain_Buccess_Logic {
        public SubClass_Only_ActivyMain_Buccess_Logic(Context context,Activity activity) {
            // TODO: 10.03.2022
        }

        // TODO: 10.03.2022

    private void МетодЗапускФрагментаЗадачиСАктивти() {
        try{
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ");
            fragmentManagerДляЗадачи = getSupportFragmentManager();
            fragmentTransactionляЗадачи = fragmentManagerДляЗадачи.beginTransaction();
            // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляЗадачи  " + fragmentTransactionляЗадачи);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView   ");
            // TODO: 09.03.2022
            fragment_дляЗадачиПерваяКнопка = new Fragment1_One_Tasks();
            fragmentTransactionляЗадачи.replace(R.id.activity_main_fisrt_for_tasks, fragment_дляЗадачиПерваяКнопка);//.layout.activity_for_fragemtb_history_tasks
            // TODO: 10.03.2022
           // fragmentTransactionляЗадачи.addToBackStack(null);
            fragmentTransactionляЗадачи.commit();
            fragmentManagerДляЗадачи.executePendingTransactions();
            fragmentTransactionляЗадачи.show(fragment_дляЗадачиПерваяКнопка);
            Log.d(this.getClass().getName(), " fragmentTransactionляЗадачи " + fragmentTransactionляЗадачи);
            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




        // TODO: 02.08.2022
        void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
           try{
               // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
               // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
               Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

               Data myDataSingleWorker = new Data.Builder()
                       .putInt("ПубличныйID", ПубличныйIDДляАсих)
                       .putBoolean("StartSingleWorker", true)
                       .build();
               // TODO: 02.08.2022
               new Class_Generator_One_WORK_MANAGER(getApplicationContext()).
                       МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),myDataSingleWorker);
               // TODO: 26.06.2022
               Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                       + " ПубличныйIDДляАсих "+ПубличныйIDДляАсих );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
        }
        // TODO: 02.08.2022  metod start bing async
    }
}





    // TODO: 28.02.2022 конец класса

// TODO: 28.02.2022  old test code













