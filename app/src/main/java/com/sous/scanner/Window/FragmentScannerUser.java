package com.sous.scanner.Window;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicDouble;
import com.jakewharton.rxbinding4.view.RxView;
import com.sous.scanner.Services.ServiceClientBLE;
import com.sous.scanner.Errors.SubClassErrors;
import com.sous.scanner.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Unit;


public class FragmentScannerUser extends Fragment {
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private RecyclerView recyclerviewnewscanner;

    private FragmentManager fragmentManager;

    private Message handler;
    private  MaterialCardView cardView_scannerble_fragment;
    private  RelativeLayout recyclerviewsccanerble ;


    private String КлючДляFibaseOneSingnal;
    private Long version = 0l;
    private SharedPreferences preferences;


    private ServiceClientBLE.LocalBinderСканнер binderСканнер;


    private  TabLayout tabLayoutScanner;

    // TODO: 30.01.2023 ВТОРАЯ ЧАСТЬ ОТВЕТ ПРОВЕТ GATT SERVER/CLIENT
    private  LifecycleOwner lifecycleOwner ;

    private  String finalCallBackStateLiveData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            preferences = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            fragmentManager = getActivity().getSupportFragmentManager();
            КлючДляFibaseOneSingnal = "56bbe169-ea09-43de-a28c-9623058e43a2";

            // TODO: 08.02.2023  Биндинг службы
            getListerBuingindServiceFragmentScanner( );
            МетодHandler();
            settingGtLifeCyrcyleMutable();
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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

    private void settingGtLifeCyrcyleMutable() {
        try{

        lifecycleOwner = getActivity();
        lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                source.getLifecycle().getCurrentState();
                event.getTargetState().name();
            }
        });

        Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint({"RestrictedApi", "MissingPermission"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            cardView_scannerble_fragment = (MaterialCardView) view.findViewById(R.id.id_cardView_scannerble_fragment);
            recyclerviewsccanerble    = (RelativeLayout) cardView_scannerble_fragment.findViewById(R.id.recyclerviewsccanerble);
            recyclerviewnewscanner = (RecyclerView) recyclerviewsccanerble.findViewById(R.id.recyclerviewnewscanner);
            tabLayoutScanner = (TabLayout) ((MainActivityNewScanner) getActivity()).tabLayout;

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

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
            view = inflater.inflate(R.layout.fragment_scanner_recyreview, container, false);
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" view " +view);
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




    @SuppressLint("MissingPermission")
    @Override
    public void onStart() {
        super.onStart();
        try {
            visilingscannerTaylaut();
            МетодЗаполенияRecycleViewДляЗадач();
            МетодИнициализацииRecycleViewДляЗадач();
            МетодСлушательObserverДляRecycleView();
            МетодПерегрузкаRecyceView();

            // TODO: 20.02.2023 ТЕКСТ КОД
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
    public void onDestroy() {
        super.onDestroy();
        try {
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

    private void МетодВизуализацииКнопокИБар() {
        try {
            Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
    private void МетодЗапускаемПолучениеКлючаOndeSignalFirebase(@NonNull String КлючДляFibaseOneSingnal) {
        try {
            binderСканнер.getService().МетодПолучениеНовогоДляСканеарКлюча_OndeSignal(КлючДляFibaseOneSingnal);
            Log.i(this.getClass().getName(), "   создание МетодЗаполенияФрагмента1 mediatorLiveDataGATT " +  " КлючДляFibaseOneSingnal " + КлючДляFibaseOneSingnal);
            //TODO
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

    void МетодСлушательObserverДляRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    try {
                        Log.d(this.getClass().getName(), "onChanged ");
                        //TODO
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
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    super.onItemRangeChanged(positionStart, itemCount);
                    // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        //TODO
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
                public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                    super.onItemRangeChanged(positionStart, itemCount, payload);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        //TODO
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
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeInserted ");
                        //TODO
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
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    super.onItemRangeRemoved(positionStart, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                        //TODO
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
                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "     onItemRangeMoved ");
                        //TODO
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
            });
            //TODO
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

    // TODO: 02.03.2022 выход

    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
           ArrayList<String> ArrayListСканер = new ArrayList();
            ArrayListСканер.add("Фрагмент Клиента");
            myRecycleViewAdapter = new MyRecycleViewAdapter(ArrayListСканер);
            recyclerviewnewscanner.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerviewnewscanner);
            //TODO
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


    // TODO: 04.03.2022 прозвомжность инициализации RecycleView
    void МетодИнициализацииRecycleViewДляЗадач() {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewnewscanner.setLayoutManager(linearLayoutManager);
            recyclerviewnewscanner.setNestedScrollingEnabled(false);
            recyclerviewnewscanner.setHasFixedSize(false);//TODO new LinearLayoutManager(getContext())
            recyclerviewnewscanner.getAdapter().notifyDataSetChanged();
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialButton materialButtonКотрольПриход, materialButtonКотрольВыход;
        private MaterialTextView materialTextViewСтатусПоследнегоДействие;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииСканера(itemView);
                Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
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

        private void МетодИнициализацииСканера(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                // TODO: 08.02.2023 кнопка ПРИХОДА с работы
                materialButtonКотрольПриход = itemView.findViewById(R.id.bottomcontrolfragmen1);
                materialButtonКотрольПриход.setText("На Работу");
                materialButtonКотрольПриход.setToggleCheckedStateOnClick(true);
                // TODO: 08.02.2023 кнопка выхода с работы
                materialButtonКотрольВыход = itemView.findViewById(R.id.bottomcontrolfragmen2);
                materialButtonКотрольВыход.setText("С работы");
                // TODO: 08.02.2023 СТАТУС послдная задача
                materialTextViewСтатусПоследнегоДействие = itemView.findViewById(R.id.textView5getstatus);
                // TODO: 09.02.2023 данные из хранилища
                String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT", "Последнее действие");
                ПоследнийСтатусСканера = ПоследнийСтатусСканера.toUpperCase();
                String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера + ": " + ПоследнаяДатаСканера);
                materialTextViewСтатусПоследнегоДействие.setPaintFlags(materialTextViewСтатусПоследнегоДействие.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                // TODO: 17.07.2024

                Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
                new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
            }
        }
    }

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList ArrayListДанныеОтСканироваиниеДивайсов;

        public MyRecycleViewAdapter(@NotNull ArrayList ArrayListДанныеОтСканироваиниеДивайсов) {
            this.ArrayListДанныеОтСканироваиниеДивайсов = ArrayListДанныеОтСканироваиниеДивайсов;
            if (ArrayListДанныеОтСканироваиниеДивайсов.size() > 0) {
                Log.i(this.getClass().getName(), " ArrayListДанныеОтСканироваиниеДивайсов  " + ArrayListДанныеОтСканироваиниеДивайсов.size());
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
            Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                Log.d(this.getClass().getName(), " ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
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
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
            return super.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

            recyclerView.removeAllViews();

            recyclerView.getRecycledViewPool().clear();
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
            try {
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
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
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            try {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_scannerbuetoot_fragment1_bottom, parent, false);//todo old simple_for_takst_cardview1
                myViewHolder = new MyViewHolder(view);
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder);
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
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {

// TODO: 17.07.2024  Главные кнопки Кнопка на Работу

                МетодАнимацииButtonКотрольПриход(holder);

                eventButtonmaterialButtonКотрольПриход(holder);



         // TODO: 17.07.2024  Главнаня кнопка с Работы

                МетодАнимацииButtonКотрольВыход(holder);

                eventButtonmaterialButtonКотрольВыход(holder);



                Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

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

        private void МетодАнимацииButtonКотрольВыход( @NonNull MyViewHolder holder) {
            try {
             Animation   animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator2);
                animation.setDuration(100l);
                holder.materialButtonКотрольВыход.startAnimation(animation);
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder);
                //TODO
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


        private void МетодАнимацииButtonКотрольПриход( @NonNull MyViewHolder holder) {
            try {
                Animation   animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator2);
                animation2.setDuration(200l);
                holder.materialButtonКотрольПриход.startAnimation(animation2);
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder);
                //TODO
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

        ///todo первый метод #1
        private void eventButtonmaterialButtonКотрольПриход(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                        +new Date().toLocaleString() + " holder " +holder);
                // TODO: 19.02.2023 Второе Действие
                final String[] ДействиеДляСервераGATTОКотрольПриход = new String[1];
                // TODO: 22.02.2023 для второй кнопки
                RxView.clicks(holder.materialButtonКотрольПриход)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.rxjava3.core.Observer<Unit>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Unit unit) {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );


                                //TODO лимит на подключение к серверу
                                limitAnConnectionSessionGattaWithServer( holder.materialButtonКотрольПриход);



                             final    MutableLiveData<String> mediatorLiveDataGATTПриход = new MediatorLiveData();

                                    ДействиеДляСервераGATTОКотрольПриход[0] = "на работу";

                                    МетодЗапускаGattСервера(holder.materialButtonКотрольПриход, holder, holder.materialButtonКотрольПриход,ДействиеДляСервераGATTОКотрольПриход[0] ,mediatorLiveDataGATTПриход);

                                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " время " + new Date().toLocaleString());
                            }
                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }

                            @Override
                            public void onComplete() {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
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

        private void limitAnConnectionSessionGattaWithServer(@NonNull MaterialButton materialButtonGattClient) {
            try{
                materialButtonGattClient.getHandler().postDelayed(()->{
                    // TODO: 17.07.2024
                      if(!finalCallBackStateLiveData.contentEquals("SERVER#SousAvtoSuccess")) {
                          // TODO: 17.07.2024
                          binderСканнер.getService().МетодВыключениеКлиентаGatt();
                          // TODO: 17.07.2024
                          onStart();

                          //      Toast.makeText(getContext(), "Время сессии закончилась !!! ", Toast.LENGTH_SHORT).show();


                          Snackbar snackbar = Snackbar.make(recyclerviewnewscanner.getRootView(), "Время сессии закончилась !!!", Snackbar.LENGTH_LONG);
                          final FrameLayout snackBarView = (FrameLayout) snackbar.getView();
                          FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackBarView.getChildAt(0).getLayoutParams();
                          params.setMargins(20, 0, 20, 50);
                          params.gravity = Gravity.BOTTOM;
                          snackBarView.setBackgroundColor(Color.GRAY);
                          snackBarView.setLayoutParams(params);
                          snackbar.show();
                      }

                    Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            },20000);
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


        private void eventButtonmaterialButtonКотрольВыход(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                        +new Date().toLocaleString() + " holder " +holder);
                // TODO: 19.02.2023 Второе Действие
                final String[] ДействиеДляСервераGATTКотрольВыход = new String[1];

                RxView.clicks(holder.materialButtonКотрольВыход)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.rxjava3.core.Observer<Unit>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Unit unit) {

                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() +
                                        " disposable[0] " );

                                //TODO лимит на подключение к серверу
                                limitAnConnectionSessionGattaWithServer( holder.materialButtonКотрольПриход);



                                ДействиеДляСервераGATTКотрольВыход[0] = "с работы";


                              final   MutableLiveData<String> mediatorLiveDataGATTВыход = new MediatorLiveData();

                                МетодЗапускаGattСервера(holder.materialButtonКотрольВыход, holder, holder.materialButtonКотрольВыход,  ДействиеДляСервераGATTКотрольВыход[0],mediatorLiveDataGATTВыход);

                                Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                                // TODO: 22.02.2023
                            }
                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }
                            @Override
                            public void onComplete() {

                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
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


        private void МетодЗапускаGattСервера(@NonNull View v, @NonNull MyViewHolder holder,
                                             MaterialButton materialButton,
                                             @NonNull String ДействиеДляСервераGATTОТКлиента,
                                             @NonNull MutableLiveData<String> mediatorLiveDataGATTПриход) {
            try {
                // TODO: 20.02.2023  слушатель Клиета GATT
                МетодBackСлушательGATTОтСервера(v, holder, ДействиеДляСервераGATTОТКлиента, materialButton, mediatorLiveDataGATTПриход);

                // TODO: 20.02.2023 Запуск Клиента Gatt Сервреа Чрез БИндинг
                МетодЗапускКлиентаGattЧерезБиндинг(ДействиеДляСервераGATTОТКлиента,mediatorLiveDataGATTПриход);

                Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " время " + new Date().toLocaleString() );
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

        private void МетодBackСлушательGATTОтСервера(@NonNull View v,
                                                     @NonNull MyViewHolder holder
                , @NonNull String ДействиеДляСервераGATTОТКлиента,
                                                     @NonNull MaterialButton materialButtonКакоеДействие,
                                                     MutableLiveData<String> mediatorLiveDataGATTClient  ) {
            try {

                if (mediatorLiveDataGATTClient .hasActiveObservers()) {
                    mediatorLiveDataGATTClient .removeObservers(lifecycleOwner);
                }


                mediatorLiveDataGATTClient .observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(@NonNull  String ОтветОтСерврера) {
                        if (mediatorLiveDataGATTClient .getValue() != null) {
                            //todo  get State Ot Gatt Server
                            finalCallBackStateLiveData=mediatorLiveDataGATTClient .getValue();

                            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                     " finalCallBackStateLiveData    " +finalCallBackStateLiveData + " mediatorLiveDataGATTClient .getValue(  " +mediatorLiveDataGATTClient .getValue() );


                            // TODO: 24.01.2023  State get ot Gatt Server
                            switch (mediatorLiveDataGATTClient .getValue().toString()) {
                                case "SERVER#SERVER#SouConnect":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Коннект...");
                                    });
                                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "SERVER#SousAvtoBOND_BONDING":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Сопрящение...");
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "GATTCLIENTProccessing":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("В процессе...");
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "SERVER#SERVER#SousAvtoReetBoot":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Соед. перегружено !!!");//
                                        Log.i(this.getClass().getName(), "   mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "SERVER#SousAvtoDONTDIVICE":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Нет  сопряжение !!!");
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "SERVER#SousAvtoDONTBLEManager":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Нет Bluetooth  !!!");
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                case "SERVER#SousAvtoSuccess":
                                    // TODO: 07.02.2023 Успешный статус
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        // TODO: 17.07.2024
                                        Animation   animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator2);
                                        animation.setDuration(100l);
                                        v.startAnimation(animation);
                                        
                                        
                                        materialButtonКакоеДействие.setText("Успешно !!!");
                                        Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                        v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));

                                        // TODO: 07.02.2023  записываем смены статуса
                                        writertoSharedPreferencesStatusOtGattServer(ДействиеДляСервераGATTОТКлиента, new Date().toLocaleString());

                                        // TODO: 08.02.2023 показыввем клиент смененый статус
                                        String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT", "");
                                        String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                                        holder.materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера
                                                + "\n" + ПоследнаяДатаСканера);
                                        holder.materialTextViewСтатусПоследнегоДействие.setTextColor(Color.parseColor("#949796"));
                                        holder.materialTextViewСтатусПоследнегоДействие.forceLayout();
                                        holder.materialTextViewСтатусПоследнегоДействие.refreshDrawableState();
                                        // TODO: 11.02.2023  
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                                // TODO: 11.02.2023 ДРУГИЕ ОТВЕТЫ
                                case "SERVER#SousAvtoERROR":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Не Успешно !!!");

                                        writertoSharedPreferencesStatusOtGattServer(ДействиеДляСервераGATTОТКлиента, new Date().toLocaleString());

                                        // TODO: 08.02.2023 показыввем клиент смененый статус
                                        String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT",
                                                "");
                                        String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                                        holder.materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера
                                                + "\n" + ПоследнаяДатаСканера);
                                        holder.materialTextViewСтатусПоследнегоДействие.setTextColor(Color.RED);
                                        holder.materialTextViewСтатусПоследнегоДействие.forceLayout();
                                        holder.materialTextViewСтатусПоследнегоДействие.refreshDrawableState();
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                                    break;
                            }


                            //TODO КОгда от сервер GATT  нет Ответа
                        }else {
                            //TODO КОгда от сервер GATT  нет Ответа
                            serverGattDOntVevice(materialButtonКакоеДействие,ДействиеДляСервераGATTОТКлиента,mediatorLiveDataGATTClient );


                            Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                    + new Date().toLocaleString() + " mediatorLiveDataGATTClient .getValue() " + mediatorLiveDataGATTClient .getValue());
                        }


                    }


                });
                Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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





        private void serverGattDOntVevice(@NonNull MaterialButton materialButtonКакоеДействие,
                                          @NonNull String ДействиеДляСервераGATTОТКлиента,
                                          @NonNull MutableLiveData<String> mediatorLiveDataGATT) {

            try{
            mediatorLiveDataGATT.setValue( "SERVER#SousAvtoDONTDIVICE");
            handler.getTarget().post(() -> {
                materialButtonКакоеДействие.setText("Нет  сопряжение !!!");
                handler.getTarget().postDelayed(() -> {
                    materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                }, 1500);
            });
            Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                    + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());

            Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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


        private void МетодЗапускКлиентаGattЧерезБиндинг(@NonNull String ДействиеДляСервераGATTОТКлиента, @NonNull MutableLiveData<String> mediatorLiveDataGATT) {
            try {
            // TODO: 06.12.2022 запускаем сканирование клиента
            binderСканнер.getService().МетодКлиентЗапускСканера(handler.getTarget(), getActivity(),  mediatorLiveDataGATT, ДействиеДляСервераGATTОТКлиента);
                Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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



        private void writertoSharedPreferencesStatusOtGattServer(@NonNull String СтатусРаботыGatt, @NonNull String ДатаСменыСтатуса) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("СменаСтатусРАботыКлиентасGATT", СтатусРаботыGatt);
            editor.putString("СменаДАтаРАботыGATT", ДатаСменыСтатуса);
            editor.commit();
            try {
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
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);

        }

        @Override
        public int getItemCount() {
            try {

                Log.d(this.getClass().getName(), "ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
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
            return ArrayListДанныеОтСканироваиниеДивайсов.size();
        }
    }

    //TODO метод делает callback с ответом на экран
    private void МетодПерегрузкаRecyceView() {
        try {
            recyclerviewnewscanner.getAdapter().notifyDataSetChanged();
            recyclerviewnewscanner.requestLayout();
            recyclerviewnewscanner.forceLayout();
            recyclerviewnewscanner.refreshDrawableState();
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

    void МетодHandler() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), "msg " + msg);
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
                return true;
            }
        }).obtainMessage();
    }








    public void getListerBuingindServiceFragmentScanner( ) {
        try {
            getParentFragmentManager().setFragmentResultListener("requestKeyScannerBindindService", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                    // We use a String here, but any type that can be put in a Bundle is supported.
                    binderСканнер= (ServiceClientBLE.LocalBinderСканнер) bundle.getBinder("bundleKey");
                    // Do something with the result.
                    Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " binderСканнер " +binderСканнер);
                }
            });
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );


            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }


    public void visilingscannerTaylaut() {
        try{
            tabLayoutScanner.setVisibility(View.VISIBLE);

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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































