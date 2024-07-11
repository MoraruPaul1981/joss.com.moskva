package com.sous.scanner.Window;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.sous.scanner.Errors.SubClassErrors;
import com.sous.scanner.R;

import java.util.Date;


public class FragmentBootScanner extends Fragment {
    Long version=0l;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Handler handler;
    private ImageView materialTextViewToolBar;
    private NavigationBarView bottomNavigationView;
    private RelativeLayout relativeLayout;
    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            try {
                PackageInfo     pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                 version = pInfo.getLongVersionCode();
                 View   viewАктивтиСканивраония = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_newscanner, null, false);
                relativeLayout = (RelativeLayout) viewАктивтиСканивраония.findViewById(R.id.activitynain_for_Taskslinelayoutrela3);
                // TODO: 19.02.2023  Методы Для Запуска Сканивраоние Фргамента
                МетодЗапускаВторогоФрагментаСканивавания();
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view+ " pInfo " +pInfo);
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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            МетодИницмиализацияHandker();
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " );
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
        new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);

    }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.activity_main_activit_snanner_start, container, false);
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                    "" + view);
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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return view;
    }





    private void МетодЗапускаВторогоФрагментаСканивавания() {
        try {
            fragment= fragmentManager.getFragments().get(0);
            fragment.onDetach();
            fragmentTransaction.remove(fragment);
            fragment=new FragmentScannerUser();
            handler.postDelayed(()->{
                View   viewАктивтиСканивраония = LayoutInflater.from(getContext()).inflate(R.layout.toolbar_laoytscanner, null, false);
                 TextView textViewСканерИмя=viewАктивтиСканивраония.findViewById(R.id.text_scanner_work);
                View   viewАктивтиСканивраонияПрограсс = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_activit_snanner_start, null, false);
                ProgressBar progressBarСканера=viewАктивтиСканивраонияПрограсс.findViewById(R.id.progressBarFace);
                textViewСканерИмя.setVisibility(View.INVISIBLE);
                progressBarСканера.setVisibility(View.INVISIBLE);
                progressBarСканера.refreshDrawableState();
                textViewСканерИмя.refreshDrawableState();
                relativeLayout.forceLayout();
                relativeLayout.refreshDrawableState();
                //fragmentTransaction.add(R.id.framelauoutScanner, fragment.getClass(),bundle);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.add(R.id.framelauoutScanner, fragment).setPrimaryNavigationFragment(fragment);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.show(fragment);
                Log.i(this.getClass().getName(),  "МетодЗапускКлиентаИлиСервера " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
            },1000);

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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);

        }
    }

    public void МетодИницмиализацияHandker() {
        try {
            handler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    return true;
                }
            });
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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }









    }































