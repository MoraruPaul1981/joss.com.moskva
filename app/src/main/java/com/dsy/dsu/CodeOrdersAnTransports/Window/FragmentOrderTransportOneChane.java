package com.dsy.dsu.CodeOrdersAnTransports.Window;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;


import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentOrderTransportOneChane extends Fragment {
    private Integer ПубличныйID;
    private LinearLayout linearLayou;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private Animation ani;
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеНовогоМатериалов;
    private  TextView TextViewHadler;
    long start;
    long startДляОбноразвовной;
    private  ServiceOrserTransportService.  LocalBinderOrderTransport localBinderOrderTransport;
    private ServiceConnection serviceConnection;
    private  Message message;

    private  Cursor cursorOrderTransport;
    private GridView gridViewOrderTransport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта
            МетодБиндингOrderTransport();
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View    view=null;
        try {
            view= inflater.inflate(R.layout.fragment_ordertransport1, container, false);
            this.container=container;

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " view " +view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            gridViewOrderTransport = view.findViewById(R.id.gridViewOrderTransport);
            TextViewHadler = view.findViewById(R.id.TextViewHadler);
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(70);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
            ani = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();

            //todo запуск методов в фрагменте
            SubClassNewOrderTransport subClassNewOrderTransport=new SubClassNewOrderTransport(getActivity());



            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " gridViewOrderTransport " +gridViewOrderTransport);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        try{

                МетодСлушательКурсора();


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 27.04.2023  новый код Заказ Транспорта
    class   SubClassNewOrderTransport{
 private Activity activity;

        public SubClassNewOrderTransport(Activity activity) {
            this.activity = activity;
        }

        // TODO: 28.04.2023
        private void методЗаполенияGridView() {
            try{
                Log.d(this.getClass().getName(), " gridViewOrderTransport  "+gridViewOrderTransport);
                gridViewOrderTransport.setVisibility(View.VISIBLE);
                gridViewOrderTransport.startAnimation(ani);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 28.04.2023
        void МетодHandlerCallBack() {
            try{
            message=Message.obtain(new Handler(Looper.myLooper()),()->{
                Bundle bundle=   message.getData();
                Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                        Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " время " +new Date().toLocaleString() + " message " +message );
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 28.04.2023

        private void МетодВыходНаAppBack() {
            try {
                bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                            Intent Интент_BackВозвращаемАктивти = getActivity().getIntent();
                            Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_Face_App.class); // Т
                            Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Bundle gameData = new Bundle();
                            gameData.putString("ФлагСтатусИзФрагментаСканирования", "ЗакрываетИзСканирования");
                            gameData.putBinder("binder", localBinderOrderTransport);
                            Интент_BackВозвращаемАктивти.putExtras(gameData);
                            Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии");
                            message.getTarget().postDelayed(()->{ startActivity(Интент_BackВозвращаемАктивти); },500);
                            Log.d(this.getClass().getName(), "  v  " + v);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
                bottomNavigationItemView2создать.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            МетодЗапускаАнимацииКнопок(v);
                            message.getTarget().postDelayed(()->{ МетодЗапускСозданиНовгоМатериалов();},500);
                            Log.d(this.getClass().getName(), "  v  " + v);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
                bottomNavigationItemView3обновить.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            progressBarСканирование.setVisibility(View.VISIBLE);
                            МетодЗапускаАнимацииКнопок(v);
                            Integer ПубличныйIDДляФрагмента =
                                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
                            // TODO: 16.11.2022  запуск синхронизации однорозовая
                            МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);
                            Log.d(this.getClass().getName(), "  v  " + v);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }
        // TODO: 28.04.2023
        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        @SuppressLint("FragmentLiveDataObserve")
        void методСлушателяWorkManager(@NonNull  LifecycleOwner lifecycleOwner , @NonNull LifecycleOwner lifecycleOwnerОбщая ) throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
                String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
                lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });
                lifecycleOwnerОбщая.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });

                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            try {
                                if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                                    Long CallBaskОтWorkManagerОдноразового =
                                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                                    0l);
                                    long end = Calendar.getInstance().getTimeInMillis();
                                    long РазницаВоврмени=end-startДляОбноразвовной;
                                    if (РазницаВоврмени>5000) {
                                        if (CallBaskОтWorkManagerОдноразового>0) {
                                            onStart();
                                            onResume();
                                            // TODO: 21.11.2022  запускаем удаление
                                        }
                                    }
                                }
                                progressBarСканирование.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        });
                    }
                });
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            try {
                                if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                    long end = Calendar.getInstance().getTimeInMillis();
                                    Integer CallBaskОтWorkManageОбщая =
                                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWorkMananger", 0);
                                    Long РелультатОбщеегоWorkMAnger =
                                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("WorkManangerVipolil", 0l);
                                    long РазницаВоврмени=end-start;
                                    if (РазницаВоврмени>6000) {
                                        onStart();
                                        onResume();
                                    }
                                }
                                // WorkManager.getInstance(getContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая).getResult();
                                progressBarСканирование.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        });
                    }
                });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 28.04.2023
        //TODO метод делает callback с ответом на экран
        private void методПерегрузкаЭкрана() {
            try {
                bottomNavigationView.requestLayout();
                bottomNavigationView.forceLayout();
                gridViewOrderTransport.requestLayout();
                gridViewOrderTransport.refreshDrawableState();
                linearLayou.requestLayout();
                linearLayou.refreshDrawableState();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
// TODO: 28.04.2023

        private void МетодЗапускаАнимацииКнопок(View v) {
            v.animate().rotationX(-40l);
            message.getTarget() .postDelayed(()->{
                v.animate().rotationX(0);
            },300);
        }
        private void МетодКпопкиЗначков(@NonNull Cursor cursor) {
            try {
                if (cursor!=null) {
                    if (cursor.getCount()> 0) {
                        Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                        bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                    } else {
                        bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                    }
                }else
                {
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                }
                //TODO
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 28.04.2023



        // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

        void методСлушательGridView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                gridViewOrderTransport.getAdapter().registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }
                });
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



    }
    
    
    





    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
         //   fragment_СозданиеНовогоМатериалов = new FragmentMaretialNew();
            Bundle data=new Bundle();
            data.putBinder("binder",localBinderOrderTransport);
            fragment_СозданиеНовогоМатериалов.setArguments(data);
            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragment_СозданиеНовогоМатериалов).commit();//.layout.activity_for_fragemtb_history_task
            fragmentTransaction.show(fragment_СозданиеНовогоМатериалов);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " localBinderOrderTransport " +localBinderOrderTransport);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(),
                            Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 02.08.2022
    void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
        try{
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйID: " + ПубличныйIDДляФрагмента);
            // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
            Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляАсих);
            Intent  intentЗапускОднорworkanager=new Intent();
            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            new Class_Generator_One_WORK_MANAGER(getActivity()).
                    МетодОдноразовыйЗапускВоерМенеджера(getContext(),intentЗапускОднорworkanager);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ПубличныйIDДляОдноразовойСинхронПубличныйIDДляФрагментаиазции "+ПубличныйIDДляФрагмента );

        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

            // TODO: 11.05.2021 запись ошибок
        }
    }












    // TODO: 02.08.2022
    protected   void МетодПолучениеДанныхДЛяПолучениеМатериалов(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов,@NonNull Integer ТекущаяЦФО ){
        try{
            ПубличныйID = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйID: " + ПубличныйID);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                case "ПолучениеНомерМатериала":
                    bundleДляПЕредачи.putString("Таблица","view_taterials_group");//TODO сами данные
                    break;
            }
            bundleДляПЕредачи.putInt("ПубличныйID", ПубличныйID);
            bundleДляПЕредачи.putInt("ТекущаяЦФО",ТекущаяЦФО);
            bundleДляПЕредачи.putInt("ТекущаяНомерМатериала",ТекущаяНомерМатериала);
            bundleДляПЕредачи.putString("ФлагКакиеДанныеНужныПолучениеМатериалов",ФлагКакиеДанныеНужныПолучениеМатериалов);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакиеДанныеНужныПолучениеМатериалов);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйID "+ ПубличныйID);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
/*            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеЦФО":
                    cursorНомерЦФО = (Cursor) localBinderOrderTransport.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    if (cursorНомерЦФО.getCount() > 0) {
                        cursorНомерЦФО.moveToFirst();
                        // TODO: 15.10.2022
                        Log.d(this.getClass().getName(), "   cursorНомерЦФО " + cursorНомерЦФО);
                    }
                    break;
                case "ПолучениеНомерМатериала":
                    cursorНомерМатериала = (Cursor) localBinderOrderTransport.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    if (cursorНомерМатериала.getCount() > 0) {
                        cursorНомерМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериалаДляGroupBy " + cursorНомерМатериала);
                    }
                    break;
                case "ПолучениеСгрупированныеСамиДанные":
                    cursorСамиДанныеGroupBy = (Cursor) localBinderOrderTransport.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    if (cursorСамиДанныеGroupBy.getCount() > 0) {
                        cursorСамиДанныеGroupBy.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorСамиДанныеFace " + cursorСамиДанныеGroupBy);
                    }
                    break;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодДизайнПрограссБара() {
        progressBarСканирование.postDelayed(()->{
            progressBarСканирование.setVisibility(View.INVISIBLE);
            progressBarСканирование.setIndeterminate(true);
        },1000);
    }

    private void МетодСлушательКурсора() {
        // TODO: 15.10.2022  слушатиель для курсора
        try {
            if (cursorНомерЦФО !=null) {
                cursorНомерЦФО.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                    }
                });
                // TODO: 15.10.2022
                cursorНомерЦФО.registerContentObserver(new ContentObserver(handler) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        super.onChange(selfChange, uri);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private TableLayout tableLayoutМатериалРодительная;
        private MaterialCardView cardViewМатериалРодительная;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                Log.d(this.getClass().getName(), "   itemView   " + itemView);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                tableLayoutМатериалРодительная = itemView.findViewById(R.id.TableLayoutAdmissionLayoutInflater);
                cardViewМатериалРодительная = itemView.findViewById(R.id.CardviewassibAmaterial);
                Log.d(this.getClass().getName(), " cardViewМатериал   " + cardViewМатериалРодительная);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Cursor cursor;
        public MyRecycleViewAdapter(@NotNull Cursor cursor) {
            this.cursor = cursor;
            if ( cursor!=null) {
                if (cursor.getCount() > 0 ) {
                    Log.i(this.getClass().getName(), " cursor  " + cursor.getCount());
                }
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToPosition(position);
                        ТекущаяЦФО=        МетодВытаскиваемТекущийЦФО(cursor);    // TODO: 17.10.2022  метод который вытаскиваем Текущее Значение ЦФО для получение дальнейших данных
                        //  ХэшДааныеСтрока = (ConcurrentSkipListMap<String, String>) ArrayListДанныеОтСканироваиниеДивайсов.get(position);
                        Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " cursor " + cursor);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
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
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewПолучениеМатериалов = null;
            try {
                if(   localBinderOrderTransport==null || cursor==null){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);

                }else {
                    if (cursor.getCount() > 0) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  sqLiteCursor.getCount()  " + cursor.getCount());
                    } else {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_dm_materials, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  sqLiteCursor.getCount()  " + cursor.getCount() );
                    }
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder + "  localBinderOrderTransport " +localBinderOrderTransport);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolder;
        }

        private Integer МетодВытаскиваемТекущийЦФО(@NonNull Cursor cursor) {
            try{
                Integer индексТекущаяЦФО =cursor.getColumnIndex("cfo");
                ТекущаяЦФО =cursor.getInt(индексТекущаяЦФО);
                // TODO: 19.10.2022 название ЦФО
                Integer индексТекущаяНазваниеЦФО =cursor.getColumnIndex("name_cfo");
                ТекущаяИмяЦФО =cursor.getString(индексТекущаяНазваниеЦФО);
                Log.i(this.getClass().getName(),  "  ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяИмяЦФО " +ТекущаяИмяЦФО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ТекущаяЦФО;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor);
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        МетодЗаполняемДаннымиПолучениеМАтериалов(holder, cursor);
                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                    } else {
                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодАнимации(MyViewHolder holder) {
            try {
                holder.cardViewМатериалРодительная.startAnimation(ani);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                if (cursor != null && holder.cardViewМатериалРодительная != null) {
                    // TODO: 18.10.2022 заполеняем данныими
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayoutМатериалРодительная);
                }
                методПерегрузкаЭкрана();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void МетодДобавленеиЕлементоввRecycreView(@NonNull TableLayout tableLayoutРодительская) {
            try {
                // TODO: 07.11.2022   ВТОРОЙ ЭТАП ПОЛУЧАЕМ НОМЕР ЦФО
                if (localBinderOrderTransport!=null && cursor!=null && ТекущаяЦФО>0) {
                    // TODO: 03.11.2022 Второй Запрос Получем САМО Цифра Полученого Материла
                    МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеНомерМатериала",ТекущаяЦФО);
                }
                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursorНомерМатериала + " ТекущаяЦФО " +ТекущаяЦФО);

                // TODO: 18.10.2022 название ЦФО
                МетодДанныеНазваниеЦФО(tableLayoutРодительская);
                // TODO: 18.10.2022 дял линии
                МетодДанныеЛиния(tableLayoutРодительская);
                // TODO: 18.10.2022 Добавяем Названием Столбиков
                МетодДанныеНазваниеСтолбиков(tableLayoutРодительская);
                // TODO: 07.11.2022 сами данные
                do{
                    // TODO: 07.11.2022  ТРЕТИЙ ЭТАП ПОЛУЧАЕМ  НОМЕР ДОКУМЕНТА
                    Integer ИндексМатериала=  cursorНомерМатериала.getColumnIndex("nomenvesov_zifra");
                    // Integer ИндексМатериала=  cursorНомерМатериала.getColumnIndex("nomen_vesov");
                    ТекущаяНомерМатериала=      cursorНомерМатериала.getInt(ИндексМатериала);
                    Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursor
                            + " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);
                    // TODO: 07.11.2022 И ЗАПУСКАМ ФИЛЬНАЙ ТРЕИЙ ЭТАП ПОЛУЧЕНИЕ СГРУПИРОВАННЫХ ДАННЫХ
                    МетодПолучениеДанныхДЛяПолучениеМатериалов("ПолучениеСгрупированныеСамиДанные",ТекущаяЦФО);
                    Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorСамиДанныеGroupBy " + cursorСамиДанныеGroupBy
                            + " ТекущаяЦФО " +ТекущаяЦФО+ " ТекущаяНомерМатериала " +ТекущаяНомерМатериала);
                    // TODO: 18.10.2022 Добавяем Сами Данные Получение материалов
                    МетодДанныеПолучениеМатериалов(tableLayoutРодительская,cursorСамиДанныеGroupBy);
                    // TODO: 09.12.2022 делалем дополнительно движение
                }while (cursorНомерМатериала.moveToNext());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодДанныеПолучениеМатериалов(@NonNull TableLayout tableLayoutРодительская , @NonNull Cursor cursorСамиДанныеGroupBy) {
            // TODO: 18.10.2022  ДАННЫЕ
            try {
                TableLayout tableLayoutДочернная=new TableLayout(getContext());
                МетодВнутриСпинера(tableLayoutДочернная,tableLayoutРодительская,cursorСамиДанныеGroupBy);
                // TODO: 06.11.2022 данные цикл
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void МетодВнутриСпинера(@NonNull TableLayout tableLayout ,@NonNull TableLayout tableLayoutРодительская, @NonNull Cursor cursorСамиДанныеGroupBy) {
            try{
                Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии" + " cursorСамиДанныеGroupBy " +cursorСамиДанныеGroupBy);
                tableLayout= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
                // tableLayout.setAnimation(animation);
                TableRow rowПервыеДанные = (TableRow)   tableLayout.findViewById(R.id.TableData);
                TextView textView=  rowПервыеДанные.findViewById(R.id.textview1);
                String Материал= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("typematerial"))).orElse("");
                textView.setText(Материал.trim());
                TextView textView2=  rowПервыеДанные.findViewById(R.id.textview2);
                String Весовая= Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                textView2.setText(Весовая.trim());
                Float Сумма= Optional.ofNullable(cursorСамиДанныеGroupBy.getFloat(cursorСамиДанныеGroupBy.getColumnIndex("moneys"))).orElse(0f);
                TextView textView3=  rowПервыеДанные.findViewById(R.id.textview3);
                textView3.setText(Сумма.toString());
                // TODO: 08.11.2022  заполеним данными Строчку ДляДальнейшего Использование
                Bundle data=new Bundle();
                data.putString("Материал",Материал);
                Integer Цфо= Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("cfo"))).orElse(0);
                data.putInt("Цфо",Цфо);
                Integer НомерВыбраногоМатериала=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getInt(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov_zifra"))).orElse(0);
                data.putInt("НомерВыбраногоМатериала",НомерВыбраногоМатериала);
                String ВыбранныйМатериал=
                        Optional.ofNullable(cursorСамиДанныеGroupBy.getString(cursorСамиДанныеGroupBy.getColumnIndex("nomenvesov"))).orElse("");
                data.putString("ВыбранныйМатериал",ВыбранныйМатериал);
                data.putFloat("Сумма",Сумма);
                rowПервыеДанные.setTag(data);

                // TODO: 06.11.2022 удаление
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                tableLayout.removeViewInLayout(rowПервыеДанные);
                tableLayout.removeView(rowПервыеДанные);
                rowПервыеДанные.setId(new Random().nextInt());
                tableLayout.recomputeViewAttributes(rowПервыеДанные);
                rowПервыеДанные.startAnimation(ani);
                // TODO: 18.10.2022 добавляем  сами данные
                МетодДобаленияНовыхСтрокДанных(rowПервыеДанные, tableLayoutРодительская);
                // TODO: 19.10.2022
                // TODO: 08.11.2022 метод клика по строке Row
                Log.d(this.getClass().getName(), "МетодаКликаПоtableRow cursorСамиДанныеGroupBy  "+cursorСамиДанныеGroupBy );
                МетодаКликаПоtableRow(rowПервыеДанные);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 08.11.2022 метод перехода на дитализацию
        private void МетодаКликаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                        Log.d(this.getClass().getName(), "МетодаКликаПоtableRow v  " + v+ " bundleПереходДетализацию "+bundleПереходДетализацию);
                        if (bundleПереходДетализацию != null) {
                            МетодЗапускаАнимацииКнопок((View) rowПервыеДанные);
                            // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                           /* Fragment              fragmentAdmissionMaterialsDetailing = new FragmentDetailingMaterials();
                            bundleПереходДетализацию.putBinder("binder",binderДляПолучениеМатериалов);
                            fragmentAdmissionMaterialsDetailing.setArguments(bundleПереходДетализацию);
                            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentAdmissionMaterialsDetailing);//.layout.activity_for_fragemtb_history_tasks
                            fragmentTransaction.commit();
                            fragmentTransaction.show(fragmentAdmissionMaterialsDetailing);
                            Log.d(this.getClass().getName(), " fragmentAdmissionMaterialsDetailing " + fragmentAdmissionMaterialsDetailing);
                            Log.d(this.getClass().getName(), "  v  " + v);*/
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодДанныеНазваниеСтолбиков(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutНазваниеСтобликов= (TableLayout) LayoutInflater.from(getContext()).inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеНазваниеСтобликов = (TableRow)   tableLayoutНазваниеСтобликов.findViewById(R.id.TableHendlers);
                tableLayoutНазваниеСтобликов.recomputeViewAttributes(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeViewInLayout(rowПервыеНазваниеСтобликов);
                tableLayoutНазваниеСтобликов.removeView(rowПервыеНазваниеСтобликов);
                rowПервыеНазваниеСтобликов.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеСтобликов, tableLayoutРодительская);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодДанныеЛиния(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableLayout  tableLayoutЛиния= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);
                TableRow rowПервыеЛиния = (TableRow)   tableLayoutЛиния.findViewById(R.id.TableRowLine);
                tableLayoutЛиния.recomputeViewAttributes(rowПервыеЛиния);
                tableLayoutЛиния.removeViewInLayout(rowПервыеЛиния);
                tableLayoutЛиния.removeView(rowПервыеЛиния);
                rowПервыеЛиния.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(rowПервыеЛиния, tableLayoutРодительская);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодДанныеНазваниеЦФО(@NonNull TableLayout tableLayoutРодительская) {
            try{
                TableLayout  tableLayoutНазваниеЦФО= (TableLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_for_assionamaterial_row,null);//todo old  simple_for_assionamaterial
                TableRow rowПервыеНазваниеЦФО = (TableRow)   tableLayoutНазваниеЦФО.findViewById(R.id.TableRowNameCFO);
                TextView textViewНазваниеЦФО=  rowПервыеНазваниеЦФО.findViewById(R.id.textviewnameCFO);
                textViewНазваниеЦФО.setText(ТекущаяИмяЦФО.trim());
                tableLayoutНазваниеЦФО.recomputeViewAttributes(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeViewInLayout(rowПервыеНазваниеЦФО);
                tableLayoutНазваниеЦФО.removeView(rowПервыеНазваниеЦФО);
                rowПервыеНазваниеЦФО.setId(new Random().nextInt());
                // TODO: 18.10.2022 добавляем  название ЦФО
                МетодДобаленияНовыхСтрокДанных(rowПервыеНазваниеЦФО, tableLayoutРодительская);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодДобаленияНовыхСтрокДанных(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
            try {
                tableLayoutРодительская.removeView(rowПервыеДанные);
                tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                tableLayoutРодительская.addView(rowПервыеДанные);
                tableLayoutРодительская.requestLayout();
                tableLayoutРодительская.refreshDrawableState();
                tableLayoutРодительская.forceLayout();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
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
            int КоличесвоСтрок = 0;
            try {
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        КоличесвоСтрок = cursor.getCount();
                        Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                    } else {
                        КоличесвоСтрок = 1;
                        Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                    }
                }else {
                    КоличесвоСтрок = 1;
                    Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor+"  localBinderOrderTransport "+ localBinderOrderTransport);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return КоличесвоСтрок;
        }
    }
    public void МетодБиндингOrderTransport() {
        try {
            Intent intentЗапускOrserTransportService = new Intent(getContext(), ServiceOrserTransportService.class);
            intentЗапускOrserTransportService.setAction("intentЗапускOrserTransportService");
            serviceConnection=     new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        if (service.isBinderAlive()) {
                            localBinderOrderTransport = (ServiceOrserTransportService.  LocalBinderOrderTransport) service;
                            // TODO: 16.11.2022
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "   service.isBinderAlive()" + service.isBinderAlive());

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        localBinderOrderTransport = null;
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 11.05.2021 запись ошибок

                    }
                }
            };
            Boolean   isBound =    getContext(). bindService(intentЗапускOrserTransportService, serviceConnection , Context.BIND_AUTO_CREATE);
            getContext().registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }

                @Override
                public void onLowMemory() {
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 26.04.2023  КОЛНЕЦ Фрагмента
}