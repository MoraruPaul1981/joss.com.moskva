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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentOrderTransportOneChane extends Fragment {
    private Integer ПубличныйID;
    LinearLayout linearLayout_orders_transport;
    private BottomNavigationView BottomNavigationOrderTransport;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentNewЗаказТранспорта;
    private  TextView TextViewHadler;
    long start;
    long startДляОбноразвовной;
    private  ServiceOrserTransportService.  LocalBinderOrderTransport localBinderOrderTransport;
    private ServiceConnection serviceConnection;
    private  Message message;
    private  Cursor cursorGroupByParent;
    private RecyclerView recyclerView_OrderTransport;

    private SubClassOrdersTransport subClassOrdersTransport;
    private  Animation animationvibr1;
    private LifecycleOwner lifecycleOwner  ;
    private LifecycleOwner lifecycleOwnerОбщая ;

    private   SimpleCursorAdapter АдаптерЗаказыТарнпорта;
    private    MaterialTextView       textViewHadler;
    private  String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
    private  String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
    private      SubClassOrdersTransport.SubClassAdapters  getsubClassAdapterMyRecyclerview;

    private SubClassOrdersTransport.SubClassAdapters.SubClassAdapterMyRecyclerview.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassOrdersTransport.SubClassAdapters.SubClassAdapterMyRecyclerview.MyViewHolder myViewHolder;

    private ScrollView scrollview_OrderTransport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта
         Bundle bundleBoundOrserTran=(Bundle)        getArguments();
         if(bundleBoundOrserTran!=null){
             localBinderOrderTransport= (ServiceOrserTransportService.  LocalBinderOrderTransport)    bundleBoundOrserTran.getBinder("binder");
         }
            // TODO: 29.05.2023  new Классов
            subClassOrdersTransport =new SubClassOrdersTransport(getActivity());
            getsubClassAdapterMyRecyclerview= subClassOrdersTransport.new SubClassAdapters() ;
            // TODO: 23.05.2023 Биндинг
            subClassOrdersTransport.   МетодБиндингOrderTransport();
            lifecycleOwner =this;
            lifecycleOwnerОбщая=this;
            // TODO: 04.05.2023
            ПубличныйID = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+ "  localBinderOrderTransport " +localBinderOrderTransport);
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
            view= inflater.inflate(R.layout.simple_for_root_orders_transport, container, false);
          // view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
           /// view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
            linearLayout_orders_transport =(LinearLayout)  view.findViewById(R.id.linearLayout_orders_transport);
            // TODO: 01.05.2023  Кнопки
            BottomNavigationOrderTransport=  (BottomNavigationView) view. findViewById(R.id.BottomNavigationOrderTransport);
            bottomNavigationItemViewвыход = BottomNavigationOrderTransport.findViewById(R.id.id_lback);
            bottomNavigationItemView2создать = BottomNavigationOrderTransport.findViewById(R.id.id_create);
            bottomNavigationItemView3обновить = BottomNavigationOrderTransport.findViewById(R.id.id_async);

            BottomNavigationOrderTransport.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать.setIconSize(70);
            bottomNavigationItemView3обновить.setIconSize(50);


            bottomNavigationItemViewвыход.setVisibility(View.VISIBLE);
            bottomNavigationItemView2создать.setVisibility(View.VISIBLE);
            bottomNavigationItemView3обновить.setVisibility(View.VISIBLE);

            progressBarСканирование=  (ProgressBar)  view. findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            // TODO: 01.05.2023
            recyclerView_OrderTransport =  (RecyclerView) view.findViewById(R.id.recyclerView_OrderTransport);
            TextViewHadler = (TextView) view.findViewById(R.id.TextViewHadler);
            animationvibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);//
            textViewHadler=(MaterialTextView)  view.findViewById(R.id.TextViewHadler);
            scrollview_OrderTransport=(ScrollView)  view.findViewById(R.id.scrollview_OrderTransport);
            // TODO: 11.05.2023 горизонтальеный Сколлл
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
            fragmentManager = getActivity().getSupportFragmentManager();
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();
            //todo запуск методов в фрагменте
            subClassOrdersTransport.   МетодHandlerCallBack();
            subClassOrdersTransport.   МетодВыходНаAppBack();
            // TODO: 04.05.2023 Анимация
       //todo recyrview
            subClassOrdersTransport.МетодИнициализацииRecycreView();
            subClassOrdersTransport.методАнимацииGridView();
            // TODO: 12.05.2023 слушатель
            //  subClassOrdersTransport.    методСлушателяWorkManager(lifecycleOwner,lifecycleOwnerОбщая);
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

    @Override
    public void onStop() {
        super.onStop();
        try{
            WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);

            WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизацииОбщая);

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

    @Override
    public void onStart() {
        super.onStart();
        try{
            getsubClassAdapterMyRecyclerview.new SubClassAdapterMyRecyclerview(getContext(),cursorGroupByParent).      методЗаполенияRecycleView();
            // TODO: 29.05.2023
            subClassOrdersTransport.   МетодКпопкиЗначков(cursorGroupByParent);
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



    // TODO: 27.04.2023  новый код Заказ Транспорта
    class SubClassOrdersTransport {
 private Activity activity;

        public SubClassOrdersTransport(Activity activity) {
            this.activity = activity;
        }

        // TODO: 28.04.2023
        private void методАнимацииGridView() {
            try{
                recyclerView_OrderTransport.startAnimation(animationvibr1);
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
        private void МетодИнициализацииRecycreView() {
            try{
                recyclerView_OrderTransport.setVisibility(View.VISIBLE);
                DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(activity,LinearLayoutManager.HORIZONTAL);
                dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin
                DividerItemDecoration dividerItemDecorationVer=
                        new DividerItemDecoration(activity,LinearLayoutManager.VERTICAL);
                recyclerView_OrderTransport.addItemDecoration(dividerItemDecorationHor);
                //recyclerView_OrderTransport.addItemDecoration(dividerItemDecorationVer);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                recyclerView_OrderTransport.setLayoutManager(linearLayoutManager);
                recyclerView_OrderTransport.startAnimation(animationvibr1);
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

                            методBackActivityOrderTranport();
                            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() + " message " +message );
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
                            message.getTarget().postDelayed(()->{ методNewOrderTransport();},500);
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
                            методЗапускаSingleWorkManager(ПубличныйIDДляФрагмента);
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

        private void методBackActivityOrderTranport( ) {
           try{
            Intent Интент_BackВозвращаемАктивти = getActivity().getIntent();
            Интент_BackВозвращаемАктивти.setClass(getContext(), MainActivity_Face_App.class); // Т
            Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Интент_BackВозвращаемАктивти);
               Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                       Thread.currentThread().getStackTrace()[2].getMethodName()+
                       " время " +new Date().toLocaleString() + " message " +message );
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
        void методСлушателяWorkManager(@NonNull  LifecycleOwner lifecycleOwnerSingle ,
                                       @NonNull LifecycleOwner lifecycleOwnerОбщая )
                throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                lifecycleOwnerSingle.getLifecycle().addObserver(new LifecycleEventObserver() {
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



     WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая)
                        .observe(lifecycleOwnerSingle, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                            try {
                                workInfos.forEach(new Consumer<WorkInfo>() {
                                    @Override
                                    public void accept(WorkInfo workInfoSingle) {
                                        if(workInfoSingle.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                                            Integer CallBaskОтWorkManager =
                                                    workInfoSingle.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                            long end = Calendar.getInstance().getTimeInMillis();
                                            long РазницаВоврмени=end-startДляОбноразвовной;
                                            if (РазницаВоврмени>5000) {
                                                if (CallBaskОтWorkManager>0) {
                                                    методGetCursorReboot();
                                                    // TODO: 23.05.2023  экран
                                                   // методGetRebootScreen();
                                                    // TODO: 12.05.2023
                                                    WorkManager.getInstance(getContext())
                                                            .getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwnerSingle);
                                                    // TODO: 23.05.2023
                                                }
                                            }
                                        }
                                    }
                                });
                                // TODO: 23.05.2023  програсс бар
                                методГазимПрогрессаБар();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                    }
                });
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                            try {
                                workInfos.forEach(new Consumer<WorkInfo>() {
                                    @Override
                                    public void accept(WorkInfo workInfoОбщая) {
                                        if(workInfoОбщая.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                                                || workInfoОбщая.getState().compareTo(WorkInfo.State.ENQUEUED) == 0) {
                                            Integer CallBaskОтWorkManager =
                                                    workInfoОбщая.
                                                            getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                            long end = Calendar.getInstance().getTimeInMillis();
                                            long РазницаВоврмени = end - startДляОбноразвовной;
                                            if (РазницаВоврмени > 10000) {
                                                if (CallBaskОтWorkManager >= 0) {
                                                    методGetCursorReboot();
                                                    // TODO: 23.05.2023  экран
                                                    //методGetRebootScreen();
                                                    WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).removeObservers(lifecycleOwnerОбщая);
                                                }
                                            }
                                        }
                                    }
                                });

                                // TODO: 23.05.2023  програсс бар
                                методГазимПрогрессаБар();
                                
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
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

        private void методГазимПрогрессаБар() {
            try {
            message.getTarget().postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
            },500);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
        private void методПерегрузкаReyreView() {
            try {
                BottomNavigationOrderTransport.forceLayout();
                BottomNavigationOrderTransport.refreshDrawableState();
                recyclerView_OrderTransport.scrollToPosition(0);
                recyclerView_OrderTransport.refreshDrawableState();
                recyclerView_OrderTransport.requestLayout();
                scrollview_OrderTransport.pageScroll(View.FOCUS_UP);
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
// TODO: 28.04.2023
// TODO: 28.04.2023

        private void МетодСлушательКурсора() {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                if (cursorGroupByParent !=null) {
                    cursorGroupByParent.registerDataSetObserver(new DataSetObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            Log.d(this.getClass().getName(), "recyclerView   " + cursorGroupByParent);
                        }

                        @Override
                        public void onInvalidated() {
                            super.onInvalidated();
                            Log.d(this.getClass().getName(), "recyclerView   " + cursorGroupByParent);
                        }
                    });
                    // TODO: 15.10.2022
                    cursorGroupByParent.registerContentObserver(new ContentObserver(message.getTarget()) {
                        @Override
                        public boolean deliverSelfNotifications() {
                            Log.d(this.getClass().getName(), "recyclerView   " + cursorGroupByParent);
                            return super.deliverSelfNotifications();
                        }

                        @Override
                        public void onChange(boolean selfChange) {
                            Log.d(this.getClass().getName(), "recyclerView   " + cursorGroupByParent);
                            super.onChange(selfChange);
                        }

                        @Override
                        public void onChange(boolean selfChange, @Nullable Uri uri) {
                            Log.d(this.getClass().getName(), "recyclerView   " + cursorGroupByParent);
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
        // TODO: 28.04.2023


        protected void методNewOrderTransport() {
            try{
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();
              //  fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentNewЗаказТранспорта = new FragmentNewOrderTransport();
                Bundle bundleNewOrderTransport=new Bundle();
                bundleNewOrderTransport.putBinder("binder", (ServiceOrserTransportService.  LocalBinderOrderTransport) localBinderOrderTransport);
                bundleNewOrderTransport.putInt("isalive",1);
                fragmentNewЗаказТранспорта.setArguments(bundleNewOrderTransport);
                fragmentTransaction.remove(fragmentManager.getFragments().get(0));
                fragmentTransaction.replace(R.id.linearLayout_root_activity_main, fragmentNewЗаказТранспорта).setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragmentNewЗаказТранспорта);
                linearLayout_orders_transport.refreshDrawableState();
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
        void методЗапускаSingleWorkManager(@NonNull  Integer ПубличныйIDДляФрагмента ){
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
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

                // TODO: 11.05.2021 запись ошибок
            }
        }
        // TODO: 28.04.2023


        // TODO: 02.08.2022
        protected   Cursor методGetCursor(@NonNull   HashMap<String,String> datasendMap ){
            Cursor cursorOrder = null;
            try{
                // TODO: 03.05.2023 тест код
                Map<String,Object>  mapRetry=       localBinderOrderTransport.методГлавныйTraffic(datasendMap);
                // TODO: 04.05.2023 результат
                 cursorOrder   =(Cursor) mapRetry.get("replyget1" );

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorOrder " + cursorOrder  + " ПубличныйID  "+ПубличныйID + " ФлагОперации " +
                         " mapRetry " +mapRetry+ " ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursorOrder;
        }


        // TODO: 02.08.2022
        protected   Cursor методGetGROUPBYCursor(@NonNull   HashMap<String,String> datasendMap ){
            try{
                // TODO: 03.05.2023 тест код
                Map<String,Object>  mapRetry=       localBinderOrderTransport.методГлавныйGrpuopByOrderTrasport(datasendMap);
                // TODO: 04.05.2023 результат
                cursorGroupByParent =(Cursor) mapRetry.get("replyget1" );
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorGroupByParent " + cursorGroupByParent + " ПубличныйID  "+ПубличныйID + " ФлагОперации " +
                        " mapRetry " +mapRetry+ " ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursorGroupByParent;
        }

        private void МетодДизайнПрограссБара() {
            progressBarСканирование.postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
                progressBarСканирование.setIndeterminate(true);
            },250);
        }
        // TODO: 28.04.2023
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
                                // TODO: 23.05.2023  даннеы
                                методGetCursorGROUPBYBounds(); //      методGetCursorBounds();
                                // TODO: 23.05.2023  экран
                                  onStart();
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                                        " localBinderOrderTransport " +localBinderOrderTransport
                                        + " cursorGroupByParent " + cursorGroupByParent
                                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
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
                if (localBinderOrderTransport==null) {
                    Boolean   isBound =    getContext(). bindService(intentЗапускOrserTransportService, serviceConnection , Context.BIND_AUTO_CREATE);
                }else {
// TODO: 24.05.2023 КОГДА УЖЕ БЫЛО ПОДКЛЮЧЕНИЕ
                    // TODO: 23.05.2023  даннеы
                    методGetCursorGROUPBYBounds(); //      методGetCursorBounds();
                    // TODO: 23.05.2023  экран
                    onStart();
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                            " localBinderOrderTransport " +localBinderOrderTransport
                            + " cursorGroupByParent " + cursorGroupByParent
                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                }
                getContext().registerComponentCallbacks(new ComponentCallbacks() {
                    @Override
                    public void onConfigurationChanged(@NonNull Configuration newConfig) {
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + "localBinderOrderTransport " +
                                localBinderOrderTransport);
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
        private void методGetCursorGROUPBYBounds() throws Exception {
            try{
                // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                HashMap<String,String> datasendMap=new HashMap();
                datasendMap.putIfAbsent("1","  SELECT  _id, dateorders, status,  strftime('%Y', dateorders)  AS Year, strftime('%m', dateorders)  AS Month," +
                        " strftime('%d', dateorders)  AS Day, COUNT(*) AS getcounts" +
                        "  FROM  view_ordertransport ");
                datasendMap.putIfAbsent("2"," WHERE dateorders  IS NOT NULL   ");//AND _id >?  AND status!=? ORDER BY dateorders
                datasendMap.putIfAbsent("3"," GROUP BY strftime('%Y', dateorders)  ," +
                        " strftime('%m', dateorders)   ," +
                        " strftime('%d', dateorders) ," +
                        " dateorders ");
                datasendMap.putIfAbsent("4"," HAVING        (COUNT(*) > 0)");
                datasendMap.putIfAbsent("5","view_ordertransport");///view_ordertransport
                datasendMap.putIfAbsent("6"," ORDER by  strftime('%Y', dateorders) DESC , strftime('%m', dateorders) DESC  ,strftime('%d', dateorders) DESC ");///view_ordertransport
              //  datasendMap.putIfAbsent("5"," view_ordertransport ");
                // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ ПЕРВЫЙ ЭТАП
                cursorGroupByParent =       subClassOrdersTransport.       методGetGROUPBYCursor( datasendMap);
                // TODO: 04.05.2023  перегружаем экран
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                        " localBinderOrderTransport " +localBinderOrderTransport
                        + " cursorGroupByParent " + cursorGroupByParent
                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 28.04.2023  ALL Adapters


        class SubClassAdapters{
            // TODO: 25.05.2023 Adapter Simple

            class SubClassSimpleCursorAdapter{
                private Context context; //context
                private  Cursor cursor;
                public SubClassSimpleCursorAdapter(@NonNull Context context , @NonNull Cursor cursor) {
                    this.context=context;
                    this.cursor=cursor;
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+ " cursor " +cursor);
                }

                void методSimpleCursorAdapters(@NonNull Integer Макет){
                    try{
                        // TODO: 26.05.2023  получение строчки Дочерней с Последующим Заполененим #2
                        АдаптерЗаказыТарнпорта=
                                new SimpleCursorAdapter(getContext(), Макет,
                                        cursor, new String[]{"_id","dateorders"},
                                        new int[]{android.R.id.text2,android.R.id.text1},
                                        0);  ///name
                        SimpleCursorAdapter.ViewBinder binding = new SimpleCursorAdapter.ViewBinder() {
                            private  CheckBox checkBoxot;
                            private    Bundle bundleGrpuopByOrder;
                            private   Boolean Результат;
                            @Override
                            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                try{
                                    switch (view.getId()) {
                                        case android.R.id.text1:
                                            // TODO: 25.05.2023  ГлавныйВненишнмий Вид
                                            LinearLayout    linearLayoutGroupBYЗаказыТранспорта = (LinearLayout) view.findViewById(android.R.id.text1);
                                            // TODO: 25.05.2023  для первого Grpou BY
                                            MaterialCardView     materialCardView= (MaterialCardView) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.materialCardView_single_ot);//cardview
                                            // TODO: 25.05.2023  Для родителькая
                                            TableLayout   tableLayoutРодительская=(TableLayout) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.tablelayoutot_groupby);//cardview
                                            // TODO: 25.05.2023  Для Оформление
                                       //     методОформеленияCheckBox(checkBoxot ,linearLayoutGroupBYЗаказыТранспорта);
                                            // TODO: 12.05.2023  Получаем Данные
                                                    // TODO: 12.05.2023  Получаем Данные Gropup By Первый Этап
                                                     bundleGrpuopByOrder=    методGroupByДанныеBungle(cursor);
                                                    // TODO: 25.05.2023 первая часть GROUP BY  #1
                                                    new SubClassGetDateOrderGroupBy().методGetDateOrderGroupBy(materialCardView,bundleGrpuopByOrder);

                                                    // TODO: 25.05.2023 вторая часть СФО #3
                                                    // TODO: 25.05.2023 вторая часть ЦФО get Cursor #4
                                                    Cursor cursorgetForCurrentCFO=    new SubClassGetCFOOrder( bundleGrpuopByOrder).методGetCursorCFO( );
                                                    // TODO: 26.05.2023 Заполнение ЦФО #5
                                                  new SubClassGetCFOOrder( bundleGrpuopByOrder).методЗаполениеДаннымиВЦиклеЦФО(cursorgetForCurrentCFO, tableLayoutРодительская );
                                                    // TODO: 25.05.2023 На Полученых
                                                  cursorgetForCurrentCFO.close();
                                                    Log.d(getContext().getClass().getName(), "\n"
                                                            + " время: " + new Date() + "\n+" +
                                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                                    // TODO: 15.05.2023  gruopby

                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                        "  cursorGroupByParent " +cursorGroupByParent  );
                                            // TODO: 23.05.2023  exit
                                            return true;
                                            }
                                            // TODO: 18.04.2023  Выход Из Цикла
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " cursorGroupByParent " + cursorGroupByParent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                return false;
                            }

                        };
                        АдаптерЗаказыТарнпорта.setViewBinder(binding);
                        АдаптерЗаказыТарнпорта.notifyDataSetChanged();
                        //gridViewOrderTransport.setAdapter(АдаптерЗаказыТарнпорта);
                        методПерегрузкаReyreView();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

            }

                // TODO: 25.05.2023 класс Первое Действие Заполения Gruop BY
class SubClassGetDateOrderGroupBy {
    // TODO: 25.05.2023  метод первый 
    void методGetDateOrderGroupBy(@NonNull  MaterialCardView materialCardViewGroupBy, @NonNull  Bundle bundleGrpuopByOrder) {
        try{
            MaterialTextView materialTextViewKeyДатаЗаказа= materialCardViewGroupBy.findViewById(R.id.ot_date_order_key);//ДАТА
            MaterialTextView materialTextViewДатаЗаказа=   materialCardViewGroupBy.findViewById(R.id.ot_date_order_value);//ДАТА
            String DateOrderGroupBy = (String) bundleGrpuopByOrder.get("dateorders");
            // TODO: 18.04.2023  Заполение Данными уже на экран
            методЗаполенияЗаказаТранспорта(bundleGrpuopByOrder, materialTextViewДатаЗаказа, DateOrderGroupBy);
            // TODO: 12.05.2023
            materialTextViewДатаЗаказа.startAnimation(animationvibr1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
}
                // TODO: 25.05.2023  вторая задача заполения ЦФО
            class SubClassGetCFOOrder {
                // TODO: 25.05.2023  метод для второй ЦФО Операции
                TableLayout tableLayoutot;
                 Bundle bundleGrpuopByOrder;
                    public SubClassGetCFOOrder(@NonNull  Bundle bundleGrpuopByOrder) {
                        this.tableLayoutot=tableLayoutot;
                        this.bundleGrpuopByOrder=bundleGrpuopByOrder;
                    }

                    private Cursor методGetCursorCFO(  ) throws Exception {
                        Cursor cursorOtGetCFO=null;
                        try{
                            String УсловиеПоискаЦФО = (String) bundleGrpuopByOrder.get("dateordersForCFO");
                            // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                            HashMap<String,String> datasendMap=new HashMap();
                            datasendMap.putIfAbsent("1","  SELECT  *  FROM  view_ordertransport  ");
                            datasendMap.putIfAbsent("2"," WHERE name  IS NOT NULL  AND dateorders = ?  AND status!=? ORDER BY dateorders ");
                            datasendMap.putIfAbsent("3",УсловиеПоискаЦФО);
                            datasendMap.putIfAbsent("4","5");
                            datasendMap.putIfAbsent("5"," view_ordertransport ");
                            // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                             cursorOtGetCFO =       subClassOrdersTransport.       методGetCursor( datasendMap);
                            // TODO: 04.05.2023  перегружаем экран
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                                    " localBinderOrderTransport " +localBinderOrderTransport
                                    + " cursorOtGetCFO " +cursorOtGetCFO
                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                            // TODO: 25.05.2023 ;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return cursorOtGetCFO;
                    }
                    // TODO: 28.05.2023 gosNomer
                    private Cursor методGetCursorGosNomer(   @NonNull String getDateOrders ) throws Exception {
                        Cursor cursorOtGetGosNomer=null;
                        try{
                            // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                            HashMap<String,String> datasendMap=new HashMap();
                            datasendMap.putIfAbsent("1","  SELECT  *  FROM  view_ordertransport  ");
                            datasendMap.putIfAbsent("2"," WHERE name  IS NOT NULL  AND id_cfo != ? AND dateorders='"+getDateOrders+"'    AND status!=? ORDER BY dateorders ");
                            datasendMap.putIfAbsent("3","0");
                            datasendMap.putIfAbsent("4","5");
                            datasendMap.putIfAbsent("5"," view_ordertransport ");
                            // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                            cursorOtGetGosNomer =       subClassOrdersTransport.       методGetCursor( datasendMap);
                            // TODO: 04.05.2023  перегружаем экран
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                                    " localBinderOrderTransport " +localBinderOrderTransport
                                    + " cursorOtGetGosNomer " +cursorOtGetGosNomer);
                            // TODO: 25.05.2023 ;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return cursorOtGetGosNomer;
                    }

                    // TODO: 26.05.2023 заполненеи данных через ЦИКЛ
                void методЗаполениеДаннымиВЦиклеЦФО(@NonNull   Cursor cursorgetForCurrentCFO,   @NonNull TableLayout tableLayoutРодительская ) {
                    try{
                        // TODO: 26.05.2023  цикл может сказать главный идем по СЦО
                        do{
                            // TODO: 26.05.2023  Элемент Для Данных
                                TableRow   tableRowДочерная=   методGetChildRow(   );
                                MaterialTextView    materialTextViewДанныеAddRow =  tableRowДочерная.findViewById(R.id.ot_date_order_singlevalue);
                                // TODO: 26.05.2023  Элемент Для Шабки
                                MaterialTextView    materialTextViewШабкаAddRow =  tableRowДочерная.findViewById(R.id.ot_key_order_singlevalue);
                                // TODO: 26.05.2023 Заполнение Данными СЦО
                                методSetDateCFO(cursorgetForCurrentCFO, materialTextViewДанныеAddRow,materialTextViewШабкаAddRow);

                                // TODO: 25.05.2023 ФИНАЛЬНОЕ ДЕЙСТВИЕ вСТАВКА СТРОКИ УЖЕ ЗАПОЛЕНО В tABLEPOUY
                                методAddtableRow(tableRowДочерная,tableLayoutРодительская);

                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " cursorgetCFO.getPosition() " +cursorgetForCurrentCFO.getPosition());

                                // TODO: 26.05.2023 НаЧИНАЕМ ТРЕТИЙ ЦИКЛ  Gos NOmer
                                // TODO: 28.05.2023 заполяем
                              ///  методSetпGosNomer( materialTextViewДанныеAddRow,materialTextViewШабкаAddRow,tableLayoutРодительская);
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " cursorgetCFO.getPosition() " +cursorgetForCurrentCFO.getPosition());
                        }while (cursorgetForCurrentCFO.moveToNext());
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorgetForCurrentCFO " +cursorgetForCurrentCFO);
                        // TODO: 12.05.2023
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

            }
                    private void методSetDateCFO(@NonNull Cursor cursorgetCFO,
                                                 @NonNull MaterialTextView materialTextViewДанныеAddRow,
                                                 @NonNull  MaterialTextView materialTextViewШабкаAddRow) {
                        try{
                        String dateordersCfo = (String) cursorgetCFO.getString(cursorgetCFO.getColumnIndex("cfo")).trim();
                        // TODO: 18.04.2023  Заполение Данными уже на экран
                        методЗаполенияЗаказаТранспорта(bundleGrpuopByOrder, materialTextViewДанныеAddRow, dateordersCfo);
                            // TODO: 26.05.2023 set Шабка Данных
                         materialTextViewШабкаAddRow.setText("Цфо");

                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorgetCFO.getPosition() " + cursorgetCFO.getPosition());
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

                    // TODO: 28.05.2023 третий цикл Гос.Номер
                    private void методSetпGosNomer(@NonNull MaterialTextView materialTextViewДанныеAddRow,
                                                 @NonNull  MaterialTextView materialTextViewШабкаAddRow,
                                                   @NonNull TableLayout tableLayoutРодительская ) {
                        try{
                            String getDateOrders = (String) bundleGrpuopByOrder.get("dateordersForCFO");
                            Cursor cursorgetGosNomer=методGetCursorGosNomer( getDateOrders);
                            do {

                            TableRow  tableRowДочерная=   методGetChildRow(   );
                            materialTextViewДанныеAddRow =  tableRowДочерная.findViewById(R.id.ot_date_order_singlevalue);
                            // TODO: 26.05.2023  Элемент Для Шабки
                            materialTextViewШабкаAddRow =  tableRowДочерная.findViewById(R.id.ot_key_order_singlevalue);

                            String gos_nomer = (String) cursorgetGosNomer.getString(cursorgetGosNomer.getColumnIndex("gosmomer")).trim();
                            // TODO: 18.04.2023  Заполение Данными уже на экран
                            методЗаполенияЗаказаТранспорта(bundleGrpuopByOrder, materialTextViewДанныеAddRow, gos_nomer);
                            // TODO: 26.05.2023 set Шабка Данных
                            materialTextViewШабкаAddRow.setText("Гос.номер");

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursorgetGosNomer.getPosition() " + cursorgetGosNomer.getPosition());


                                // TODO: 25.05.2023 ФИНАЛЬНОЕ ДЕЙСТВИЕ вСТАВКА СТРОКИ УЖЕ ЗАПОЛЕНО В tABLEPOUY
                                методAddtableRow(tableRowДочерная,tableLayoutРодительская);

                            }while (cursorgetGosNomer.moveToNext());
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursorgetGosNomer.getPosition() " + cursorgetGosNomer.getPosition());
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

                    // TODO: 25.05.2023  конец класс заполения ЦФО
            
}
                // TODO: 26.05.2023 заполение Строчки
                private void методAddtableRow(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        rowПервыеДанные.startAnimation(animationvibr1);
                        tableLayoutРодительская.removeView(rowПервыеДанные);
                        tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                        tableLayoutРодительская.addView(rowПервыеДанные);
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " +tableLayoutРодительская.getChildCount());
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
                // TODO: 26.05.2023 заполение Строчки
                private TableRow методGetChildRow( ) {
                    TableRow  tableRowДочерная = null;
                    try {
                        View   convertViewДочерния  = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_trasport_for_single_row,
                                null , false);;
                        TableLayout    tableLayoutДочерная= (TableLayout) convertViewДочерния.findViewById( R.id.tablelayout_singleotrow);
                         tableRowДочерная = (TableRow)   tableLayoutДочерная.findViewById(R.id.tableRowChildOt);
                        // TODO: 26.05.2023 достаем Дочерний Элемент
                            tableLayoutДочерная.recomputeViewAttributes(tableRowДочерная);
                            tableLayoutДочерная.removeViewInLayout(tableRowДочерная);
                            tableLayoutДочерная.removeView(tableRowДочерная);
                            tableRowДочерная.setId(new Random().nextInt());
                            tableLayoutДочерная.recomputeViewAttributes(tableRowДочерная);
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutДочерная " +tableLayoutДочерная.getChildCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return tableRowДочерная;
                }



















            // TODO: 29.05.2023  Class Adapter RecyreView New New Order Transport
            class SubClassAdapterMyRecyclerview{
                Cursor cursor;
                Context context;
                public SubClassAdapterMyRecyclerview(@NonNull Context context,@NonNull Cursor cursor) {
                    this.cursor=cursor;
                    this.context=context;
                }


                // TODO: 28.02.2022 начало  MyViewHolder для Заказы Материалов
                protected class MyViewHolder extends RecyclerView.ViewHolder {
                    private    LinearLayout    linearLayoutGroupBYЗаказыТранспорта ;
                    private    MaterialCardView     materialCardView ;
                    private   TableLayout   tableLayoutРодительская;
                    private  CheckBox checkBoxot;
                    public MyViewHolder(@NonNull View itemView) {
                        super(itemView);
                        try {
                            if ( cursor!=null  && cursor.getCount() >0) {
                                // TODO: 29.05.2023
                                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        "   itemView   " + itemView);
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
                    private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
                        try {
                         linearLayoutGroupBYЗаказыТранспорта = (LinearLayout) itemView.findViewById(android.R.id.text1);
                         materialCardView= (MaterialCardView) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.materialCardView_single_ot);//cardview
                         tableLayoutРодительская=(TableLayout) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.tablelayoutot_groupby);//cardview
                            Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   itemView   " + itemView);
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
                    // TODO: 29.05.2023  


                }

                class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
                    private Cursor cursor;
                    private    Bundle bundleGrpuopByOrder;
                    public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                        this.cursor = cursor;
                        if ( cursor!=null) {
                            if (cursor.getCount() > 0 ) {
                                Log.i(this.getClass().getName(), " cursor  " + cursor.getCount());
                            }
                        }
                    }

                    @Override
                    public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
                        try {
                            ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                            if (cursor!=null) {
                                if (cursor.getCount() > 0) {
                                    cursor.moveToPosition(position);
                                    // TODO: 12.05.2023  Получаем Данные Gropup By Первый Этап
                                    bundleGrpuopByOrder=    методGroupByДанныеBungle(cursor);
                                    // TODO: 29.05.2023
                                    Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+ "   position   " + position + " bundleGrpuopByOrder "
                                            +bundleGrpuopByOrder);
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
                    public void onViewRecycled(@NonNull  MyViewHolder holder) {
                        super.onViewRecycled(holder);
                    }

                    @Override
                    public boolean onFailedToRecycleView(@NonNull  MyViewHolder holder) {
                        return super.onFailedToRecycleView(holder);
                    }

                    @Override
                    public void onViewAttachedToWindow(@NonNull  MyViewHolder holder) {
                        super.onViewAttachedToWindow(holder);
                    }

                    @Override
                    public void onViewDetachedFromWindow(@NonNull  MyViewHolder holder) {
                        super.onViewDetachedFromWindow(holder);
                    }

                    @Override
                    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
                        try {
                        recyclerView.removeAllViews();
                        recyclerView.getRecycledViewPool().clear();
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
                            Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   position   " + position);
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
                    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View viewЗаказыТраспорта = null;
                        try {
                            if(   localBinderOrderTransport==null || cursor==null){
                                viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progressing_ordertransport, parent, false);//todo old simple_for_takst_cardview1
                                Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewЗаказыТраспорта);

                            }else {
                                if (cursor.getCount() > 0) {
                                    viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_trasport_groupby1, parent, false);//todo old  simple_for_assionamaterial
                                    Log.i(this.getClass().getName(), "   viewЗаказыТраспорта" + viewЗаказыТраспорта+ "  sqLiteCursor.getCount()  " + cursor.getCount());
                                    // TODO: 29.05.2023 програсс бар
                                    // TODO: 23.05.2023  програсс бар
                                    методГазимПрогрессаБар();
                                } else {
                                    viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_isnull_ordertransport, parent, false);//todo old simple_for_takst_cardview1
                                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewЗаказыТраспорта+ "  sqLiteCursor.getCount()  " + cursor.getCount() );
                                    // TODO: 29.05.2023
                                    // TODO: 23.05.2023  програсс бар
                                    методГазимПрогрессаБар();
                                }
                            }
                            // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                            myViewHolder = new  MyViewHolder(viewЗаказыТраспорта);

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   cursor   " + cursor +  "  myViewHolder  " +myViewHolder);
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


                    @Override
                    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
                        try {
                            if (cursor!=null && cursor.getCount() > 0) {
                                    // TODO: 29.05.2023 Главный Метод ЗАполнения Данными
                                    методГлавныйЗаполениеДанными(holder, cursor);


                                // TODO: 29.05.2023 Перегрузка Вида Экрана  После Заполенеия
                                методПерегрузкаReyreView();
                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   position   " + position + " cursor " +cursor);
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
                    private void методГлавныйЗаполениеДанными(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                        try {
                            if (cursor != null) {
                                // TODO: 29.05.2023  заполение   set ДАте
                                методForRecyreViewSetDate(holder,cursor);
                                // TODO: 29.05.2023  заполение   set ЦФО
                                методForRecyreViewSetCFO(holder,cursor);
                                // TODO: 29.05.2023  Обораюотываем Чек сверху справа
                                методОформеленияCheckBox(holder );

                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursor " +cursor);

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


                    private void методForRecyreViewSetDate(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                        try {
                            // TODO: 25.05.2023 первая часть GROUP BY  #1
                            new SubClassGetDateOrderGroupBy().методGetDateOrderGroupBy(holder.materialCardView,bundleGrpuopByOrder);
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   cursor   " + cursor);
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

                    private void методForRecyreViewSetCFO(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                        try {
                            // TODO: 25.05.2023 вторая часть ЦФО get Cursor #2
                            Cursor cursorgetForCurrentCFO=    new SubClassGetCFOOrder( bundleGrpuopByOrder).методGetCursorCFO( );

                            // TODO: 26.05.2023 Заполнение ЦФО #3
                            new SubClassGetCFOOrder( bundleGrpuopByOrder).методЗаполениеДаннымиВЦиклеЦФО(cursorgetForCurrentCFO, holder.tableLayoutРодительская );
                            // TODO: 25.05.2023 На Полученых
                            cursorgetForCurrentCFO.close();


                        /*    // TODO: 07.11.2022   ВТОРОЙ ЭТАП ПОЛУЧАЕМ НОМЕР ЦФО
                            if (binderДляПолучениеМатериалов!=null && cursor!=null && ТекущаяЦФО>0) {
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
                            }while (cursorНомерМатериала.moveToNext());*/
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "   cursor   " + cursor);
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
                    void методОформеленияCheckBox(@NonNull  MyViewHolder holder ) {
                        try{
                        CheckBox   checkBoxot= (CheckBox) holder.linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.checkBoxot);//cardview
                       Integer СтатусЗаказаТраспорта=     cursorGroupByParent.getInt(cursorGroupByParent.getColumnIndex("status"));
                            if(СтатусЗаказаТраспорта==0){
                                checkBoxot.setChecked(true);
                            }else {
                                checkBoxot.setChecked(false);
                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " СтатусЗаказаТраспорта " +СтатусЗаказаТраспорта);
                            //checkBoxot.toggle();
                   /*  checkBoxot.setBackgroundColor(Color.parseColor("#316E6E"));
                    Drawable drawablecheck = getResources().getDrawable(R.drawable.icon_dsu1_ordertransport4);
                    checkBoxot.setButtonDrawable(drawablecheck);
                     checkBoxot.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#316E6E")));*/
                            // TODO: 13.03.2022
                            //   materialCardView.toggle();
                            // TODO: 30.03.2022
                            /*  materialCardView.setCheckedIconTint(ColorStateList.valueOf(Color.parseColor("#1C9CA8")));*/
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
                            rowПервыеДанные.startAnimation(animationvibr1);
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
                                /*    if (bundleПереходДетализацию != null) {
                                        МетодЗапускаАнимацииКнопок((View) rowПервыеДанные);
                                        // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                                        fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                        Fragment              fragmentAdmissionMaterialsDetailing = new FragmentDetailingMaterials();
                                        bundleПереходДетализацию.putBinder("binder",binderДляПолучениеМатериалов);
                                        fragmentAdmissionMaterialsDetailing.setArguments(bundleПереходДетализацию);
                                        fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentAdmissionMaterialsDetailing);//.layout.activity_for_fragemtb_history_tasks
                                        fragmentTransaction.commit();
                                        fragmentTransaction.show(fragmentAdmissionMaterialsDetailing);
                                        Log.d(this.getClass().getName(), " fragmentAdmissionMaterialsDetailing " + fragmentAdmissionMaterialsDetailing);
                                        Log.d(this.getClass().getName(), "  v  " + v);
                                    }*/
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
                           // textViewНазваниеЦФО.setText(ТекущаяИмяЦФО.trim());
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
                                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor);
                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    "  cursor " +cursor);
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
                // TODO: 29.05.2023  Заполение RecyView
                void методЗаполенияRecycleView() {
                    try {
                        myRecycleViewAdapter = new MyRecycleViewAdapter(cursor);
                        //myRecycleViewAdapter.notifyDataSetChanged();
                        recyclerView_OrderTransport.setAdapter(myRecycleViewAdapter);
                        recyclerView_OrderTransport.getAdapter().notifyDataSetChanged();
                        recyclerView_OrderTransport.refreshDrawableState();
                        recyclerView_OrderTransport.forceLayout();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursor " +cursor +  "  myRecycleViewAdapter " +myRecycleViewAdapter);
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
        }




        private void методЗаполенияЗаказаТранспорта( @NonNull Bundle bundleOrderTransport,
                                                     @NonNull MaterialTextView materialTextView,
                                                     @NonNull Object ЗначениеВставки) {
            try{
                if (ЗначениеВставки==null){
                    materialTextView.setHint("не заполнено");
                    materialTextView.setText("");
                }else {
                    materialTextView.setText(ЗначениеВставки.toString());
                }
                materialTextView.setTag(bundleOrderTransport);
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  bundleOrderTransport " + bundleOrderTransport);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }



        // TODO: 05.05.2023  треьтий этап
        Bundle методGosNomersДанныеBungle(@NonNull Cursor cursor) {
            Bundle bundleOrderTransport=new Bundle();
            try {
                Long   uuid= Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l) ;
                String      name= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("name"))).orElse("нет");
                String dateorders= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("dateorders"))).orElse("нет");
                Integer _id=Optional.ofNullable( cursor.getInt(cursor.getColumnIndex("_id"))).orElse(0);
                String number_order=Optional.ofNullable( cursor.getString(cursor.getColumnIndex("number_order"))).orElse("нет");
                Integer user_update=Optional.ofNullable( cursor.getInt(cursor.getColumnIndex("user_update"))).orElse(0);
                String cfo= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("cfo"))).orElse("нет");
                Integer status= Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("status"))).orElse(0);
                String gosmomer= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("gosmomer"))).orElse("нет");
                // TODO: 12.05.2023 ПАРСИНГА  DATE
                dateorders = методПарсингаДатыЗаказа(dateorders);
                // TODO: 12.05.2023 ПАРСИНГА  СТАТУСА
                String Статус=методПарсингаСтатуса(status);
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleOrderTransport.putLong("uuid", uuid);
                bundleOrderTransport.putInt("position", cursor.getPosition());
                bundleOrderTransport.putString("name",name.trim() );
                bundleOrderTransport.putString("dateorders",  dateorders.trim());
                bundleOrderTransport.putInt("_id", _id);
                bundleOrderTransport.putString("number_order", number_order.trim());
                bundleOrderTransport.putInt("user_update", user_update);
                bundleOrderTransport.putString("cfo", cfo.trim());
                bundleOrderTransport.putString("status", Статус.trim());
                bundleOrderTransport.putString("fullname", gosmomer.trim());
                // TODO: 12.05.2023 ДАННЫЕ
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  cursor " + cursor  +  "bundleOrderTransport " +bundleOrderTransport);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
            return  bundleOrderTransport;
        }



        // TODO: 05.05.2023  первый этап
        Bundle методGroupByДанныеBungle(@NonNull Cursor cursor) {
            Bundle bundleGrpuopByOrder =new Bundle();
            try {
                Integer   Year= Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Year"))).orElse(0) ;
                Integer      Month= Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Month"))).orElse(0);
                Integer Day= Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Day"))).orElse(0);
                Integer getcounts=Optional.ofNullable( cursor.getInt(cursor.getColumnIndex("getcounts"))).orElse(0);
                String dateorders=Optional.ofNullable( cursor.getString(cursor.getColumnIndex("dateorders"))).orElse("");
              // TODO: 25.05.2023 Дата
                String        dateordersForCFO=dateorders;

                dateorders=          методПарсингаДатыЗаказа(dateorders);
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleGrpuopByOrder.putInt("Year", Year);
                bundleGrpuopByOrder.putInt("position", cursor.getPosition());
                bundleGrpuopByOrder.putInt("Month",Month);
                bundleGrpuopByOrder.putInt("Day",  Day);
                bundleGrpuopByOrder.putInt("getcounts", getcounts);
                bundleGrpuopByOrder.putString("dateorders", dateorders.trim());
                bundleGrpuopByOrder.putString("dateordersForCFO", dateordersForCFO.trim());
                // TODO: 12.05.2023 ДАННЫЕ
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  cursor " + cursor  +  "bundleGrpuopByOrder " +bundleGrpuopByOrder + " dateordersForCFO " +dateordersForCFO);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
            return  bundleGrpuopByOrder;
        }

        @NonNull
        private String методПарсингаДатыЗаказа(@NonNull  String DateOrder) throws ParseException {
            try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("ru"));
            Date breamy= simpleDateFormat.parse(DateOrder);
            DateOrder = simpleDateFormat.format(breamy);
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  DateOrder " + DateOrder  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
            return DateOrder;
        }
        private String методПарсингаСтатуса(Integer Status) throws ParseException {
            String Статус=null;
            try{
                switch (Status){
                    case 0:
                        Статус="Созданно";
                        break;
                    case 1:
                        Статус="В работе";
                        break;
                    case 2:
                        Статус="Закончено";
                        break;
                    case 3:
                        Статус="Отмена";
                        break;
                }

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  Status " + Status  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Статус;
        }

        private void методУдалениеТабеля( ) {
            recyclerView_OrderTransport.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    // TODO: 29.05.2023
                    recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            try{
                                LinearLayout linearLayoutЗаказыТранспорта= ( LinearLayout)
                                        v.findViewById(android.R.id.text1);
                                MaterialTextView materialTextView5  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvaluecfo);///ЦФО
                                MaterialTextView materialTextView1  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.   otvaluetypets);//ВИД ТС
                                MaterialTextView materialTextView2  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvaluedatesordert);//ДАТА
                                MaterialTextView materialTextView3  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvaluegosnomer);//ГОС.НОМЕР

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            return false;
                        }
                    });
                }
            });

        }

        // TODO: 12.05.2023
        void методGetCursorReboot(){
            try{
                методGetCursorGROUPBYBounds();
                // TODO: 24.05.2023  перегрузка Экрана
               методПерегрузкаReyreView();
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorGroupByParent " + cursorGroupByParent);
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



        private void МетодЗапускаАнимацииКнопок(View v) {
            v.animate().rotationX(-40l);
            message.getTarget() .postDelayed(()->{
                v.animate().rotationX(0);
            },300);
        }
        private void МетодКпопкиЗначков(@NonNull Cursor cursor) {
            try {
                if (cursor!=null && cursor.getCount()> 0) {
                        Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                }else {
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                }
                //TODO
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



// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport           //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport   //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport
    }
    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}