package com.dsy.dsu.CodeOrdersAnTransports.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.ArrayMap;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов.FragmentAdmissionMaterials;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.R;

import java.util.Date;
import java.util.Map;
import java.util.Random;

public class MainActivityOrdersTransports extends AppCompatActivity {
    // TODO: 25.04.2023 Переменные
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеЗаказаТранспорта;
    private LinearLayout activity_admissionmaterias_face ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_orders_transports);
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
            activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_mainface);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);


            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials");
            // TODO: 04.11.2022 test
            МетодЗапускФрагментаПриемМатериалов();
            ArrayMap arrayMap=new ArrayMap();
            ContentValues [] cccc=       ContentValues.CREATOR.newArray(500);
            Parcel obtain = Parcel.obtain();
            obtain.writeMap(arrayMap  );
            ContentValues cc= ContentValues.CREATOR.createFromParcel(obtain);
            cccc[0].put("ffff","ffffs");






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

    protected void МетодЗапускФрагментаПриемМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragment_СозданиеЗаказаТранспорта = new FragmentOrderTransportOneChane();
            fragmentTransaction.add(R.id.activity_ordertransports, fragment_СозданиеЗаказаТранспорта);//.layout.activity_for_fragemtb_history_tasks
            fragmentTransaction.commit();
            fragmentTransaction.show(fragment_СозданиеЗаказаТранспорта);
            Log.d(this.getClass().getName(), " fragment_СозданиеЗаказаТранспорта " + fragment_СозданиеЗаказаТранспорта);
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