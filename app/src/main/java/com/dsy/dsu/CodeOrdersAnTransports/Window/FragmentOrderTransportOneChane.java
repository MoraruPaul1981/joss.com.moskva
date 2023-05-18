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
import android.graphics.drawable.Drawable;
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
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
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
import com.google.android.material.textview.MaterialTextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;


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

    private  Cursor cursorOrderTransport;
    private GridView gridViewOrderTransport;

    private SubClassOrdersTransport subClassOrdersTransport;
    private  Animation animationvibr1;
    private LifecycleOwner lifecycleOwner  ;
    private LifecycleOwner lifecycleOwnerОбщая ;

    private   SimpleCursorAdapter АдаптерЗаказыТарнпорта;
    private    MaterialTextView       textViewHadler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта
         Bundle bundleBoundOrserTran=(Bundle)        getArguments();
         if(bundleBoundOrserTran!=null){
             localBinderOrderTransport= (ServiceOrserTransportService.  LocalBinderOrderTransport)    bundleBoundOrserTran.getBinder("binder");
         }
            subClassOrdersTransport =new SubClassOrdersTransport(getActivity());
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
            BottomNavigationOrderTransport.refreshDrawableState();
            BottomNavigationOrderTransport.forceLayout();











            progressBarСканирование=  (ProgressBar)  view. findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            // TODO: 01.05.2023
            gridViewOrderTransport =  (GridView) view.findViewById(R.id.gridViewOrderTransport);
            TextViewHadler = (TextView) view.findViewById(R.id.TextViewHadler);
            animationvibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);//
            textViewHadler=(MaterialTextView)  view.findViewById(R.id.TextViewHadler);



            if (localBinderOrderTransport!=null) {
                subClassOrdersTransport.  методGetCursorBounds();

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                        " localBinderOrderTransport " +localBinderOrderTransport
                        + " cursorOrderTransport " +cursorOrderTransport
                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
            }

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
            subClassOrdersTransport.методАнимацииGridView();

            // TODO: 12.05.2023 слушатель
            subClassOrdersTransport.    методСлушателяWorkManager(lifecycleOwner,lifecycleOwnerОбщая);

            subClassOrdersTransport.МетодСлушательКурсора();


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
            if( cursorOrderTransport==null) {
                subClassOrdersTransport.методПредварительнаяЗагрузкаGridView(R.layout.list_item_progressing_ordertransport,
                        "Заказы...", R.drawable.icon_dsu1_ordertransport_down);
            }else {
                if( cursorOrderTransport.getCount()>0) {
                    subClassOrdersTransport.методФиналЗагрузкиGridView(R.layout.fragment_ordertransport1);
                }else{
                    subClassOrdersTransport.методПредварительнаяЗагрузкаGridView( R.layout.list_item_isnull_ordertransport,
                            "Нет заказов !!!", R.drawable.icon_rdertransport2);
                }
                // TODO: 04.05.2023 Получаем Данные что обработка данных закончена
                subClassOrdersTransport.    МетодДизайнПрограссБара();
            }

            // TODO: 12.05.2023
            subClassOrdersTransport. МетодКпопкиЗначков(cursorOrderTransport);
            // TODO: 19.04.2023 слушаелти
            // TODO: 18.04.2023 Слушатель Удалание
            subClassOrdersTransport.    методУдалениеТабеля( );
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " cursorOrderTransport " +cursorOrderTransport);
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


    // TODO: 12.05.2023
    void  методПерегрузкаДанные(){
        try{
            // TODO: 04.05.2023  получаем первоночальыне Данные  #1
            HashMap<String,String> datasendMap=new HashMap();
            datasendMap.putIfAbsent("1","  SELECT  *  FROM  view_ordertransport  ");
            datasendMap.putIfAbsent("2"," WHERE name  IS NOT NULL  AND _id >?  AND status!=? ORDER BY _id,status ");
            datasendMap.putIfAbsent("3"," 0");
            datasendMap.putIfAbsent("4","5");
            datasendMap.putIfAbsent("5"," view_ordertransport ");
            // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
            cursorOrderTransport=       subClassOrdersTransport.       методGetCursor( datasendMap);

            // TODO: 12.05.2023 Adapter
            АдаптерЗаказыТарнпорта.changeCursor(cursorOrderTransport);
            АдаптерЗаказыТарнпорта.notifyDataSetChanged();
            gridViewOrderTransport.setAdapter(АдаптерЗаказыТарнпорта);
            gridViewOrderTransport.startAnimation(animationvibr1);
            gridViewOrderTransport.smoothScrollByOffset(0);
            gridViewOrderTransport.refreshDrawableState();
            gridViewOrderTransport.forceLayout();
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " cursorOrderTransport " +cursorOrderTransport);
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
                gridViewOrderTransport.startAnimation(animationvibr1);
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorOrderTransport " +cursorOrderTransport);
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
        void методСлушателяWorkManager(@NonNull  LifecycleOwner lifecycleOwner ,
                                       @NonNull LifecycleOwner lifecycleOwnerОбщая )
                throws ExecutionException, InterruptedException {
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

                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая)
                        .observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        workInfos.forEach((StatusWork) -> {
                            try {
                                if(StatusWork.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                                    Integer CallBaskОтWorkManager =
                                            StatusWork.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                    long end = Calendar.getInstance().getTimeInMillis();
                                    long РазницаВоврмени=end-startДляОбноразвовной;
                                    if (РазницаВоврмени>5000) {
                                        if (CallBaskОтWorkManager>0) {
                                            onStart();
                                            методПерегрузкаДанные();
                                            // TODO: 12.05.2023
                                            subClassOrdersTransport. МетодКпопкиЗначков(cursorOrderTransport);
                                            WorkManager.getInstance(getContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая) ;

                                            // TODO: 21.11.2022  запускаем удаление
                                        }
                                    }
                                }
                                message.getTarget().postDelayed(()->{
                                    progressBarСканирование.setVisibility(View.INVISIBLE);
                                },1000);
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
                        });
                    }
                });
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        workInfos.forEach((StatusWork) -> {
                            try {
                                if(StatusWork.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                                        || StatusWork.getState().compareTo(WorkInfo.State.ENQUEUED) == 0) {
                                    Integer CallBaskОтWorkManager =
                                            StatusWork.
                                                    getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                    if (CallBaskОтWorkManager>0) {
                                        onStart();
                                        методПерегрузкаДанные();
                                        // TODO: 12.05.2023
                                        subClassOrdersTransport. МетодКпопкиЗначков(cursorOrderTransport);
                                    }
                                }
                                message.getTarget().postDelayed(()->{
                                    progressBarСканирование.setVisibility(View.INVISIBLE);
                                },1000);
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
                BottomNavigationOrderTransport.forceLayout();
                BottomNavigationOrderTransport.refreshDrawableState();
                gridViewOrderTransport.forceLayout();
                gridViewOrderTransport.refreshDrawableState();
                linearLayout_orders_transport.forceLayout();
                linearLayout_orders_transport.refreshDrawableState();
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
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                    } else {
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                        BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                    }
                }else
                {
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
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

// TODO: 28.04.2023

        private void МетодСлушательКурсора() {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                if (cursorOrderTransport !=null) {
                    cursorOrderTransport.registerDataSetObserver(new DataSetObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            Log.d(this.getClass().getName(), "recyclerView   " + gridViewOrderTransport);
                        }

                        @Override
                        public void onInvalidated() {
                            super.onInvalidated();
                            Log.d(this.getClass().getName(), "recyclerView   " + gridViewOrderTransport);
                        }
                    });
                    // TODO: 15.10.2022
                    cursorOrderTransport.registerContentObserver(new ContentObserver(message.getTarget()) {
                        @Override
                        public boolean deliverSelfNotifications() {
                            Log.d(this.getClass().getName(), "recyclerView   " + gridViewOrderTransport);
                            return super.deliverSelfNotifications();
                        }

                        @Override
                        public void onChange(boolean selfChange) {
                            Log.d(this.getClass().getName(), "recyclerView   " + gridViewOrderTransport);
                            super.onChange(selfChange);
                        }

                        @Override
                        public void onChange(boolean selfChange, @Nullable Uri uri) {
                            Log.d(this.getClass().getName(), "recyclerView   " + gridViewOrderTransport);
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
                fragmentTransaction.addToBackStack(null);
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
            try{
                // TODO: 03.05.2023 тест код
                Map<String,Object>  mapRetry=       localBinderOrderTransport.методГлавныйTraffic(datasendMap);
                // TODO: 04.05.2023 результат
                cursorOrderTransport  =(Cursor) mapRetry.get("replyget1" );

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorOrderTransport " + cursorOrderTransport  + " ПубличныйID  "+ПубличныйID + " ФлагОперации " +
                         " mapRetry " +mapRetry+ " ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursorOrderTransport;
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

                                методGetCursorBounds();

                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                                        " localBinderOrderTransport " +localBinderOrderTransport
                                        + " cursorOrderTransport " +cursorOrderTransport
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

        private void методGetCursorBounds() throws Exception {
            try{
            // TODO: 04.05.2023  получаем первоночальыне Данные  #1
            HashMap<String,String> datasendMap=new HashMap();
            datasendMap.putIfAbsent("1","  SELECT  *  FROM  view_ordertransport  ");
            datasendMap.putIfAbsent("2"," WHERE name  IS NOT NULL  AND _id >?  AND status!=? ORDER BY _id,status ");
            datasendMap.putIfAbsent("3","0");
            datasendMap.putIfAbsent("4","5");
            datasendMap.putIfAbsent("5"," view_ordertransport ");
            // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
            cursorOrderTransport=       subClassOrdersTransport.       методGetCursor( datasendMap);
            // TODO: 04.05.2023  перегружаем экран

            onStart();
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()+
                    " localBinderOrderTransport " +localBinderOrderTransport
                    + " cursorOrderTransport " +cursorOrderTransport
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

        // TODO: 28.04.2023
      void методФиналЗагрузкиGridView(@NonNull Integer Макет){
            try{
                      АдаптерЗаказыТарнпорта=
                            new SimpleCursorAdapter(getContext(), Макет,
                                    cursorOrderTransport, new String[]{"_id","name"},
                                    new int[]{android.R.id.text1,android.R.id.text2},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  ///name
                    SimpleCursorAdapter.ViewBinder binding = new SimpleCursorAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                            try{
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        LinearLayout linearLayoutЗаказыТранспорта= ( LinearLayout)
                                                view.findViewById(android.R.id.text1);

                                        // TODO: 12.05.2023  Получаем Данные
                                        Bundle bundleOrderTransport=    методДанныеBungle(cursor);
                                        // TODO: 12.05.2023
                                        CopyOnWriteArrayList<MaterialTextView> materialTextViews=new CopyOnWriteArrayList<>();
                                        materialTextViews.add(   linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue2));//ВИД ТС
                                        materialTextViews.add(  linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue4));//ДАТА
                                        materialTextViews.add(   linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue3));//ГОС.НОМЕР
                                        materialTextViews.add(   linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue0));///номер заказа
                                        materialTextViews.add(   linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue1));///ЦФО
                                        materialTextViews.add(   linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue5));//СТАТУС

                                 ListIterator<MaterialTextView> listIterator= materialTextViews.listIterator();
                                        while (listIterator.hasNext()) {
                                            MaterialTextView  materialTextViewvalues = listIterator.next();
                                        Integer Индекс=    listIterator.nextIndex();
                                            Object ЗначениеВставки = null;
                                        switch (Индекс){
                                            case  1 :
                                            ЗначениеВставки=        bundleOrderTransport.get("NameOrder");
                                                break;
                                            case  2 :
                                                ЗначениеВставки=       bundleOrderTransport.get("DateOrder");
                                                break;
                                            case  3 :
                                                ЗначениеВставки=       bundleOrderTransport.get("GosNomer");
                                                break;
                                            case  4 :
                                                ЗначениеВставки=       bundleOrderTransport.get("NumberOrder");
                                                break;
                                            case  5 :
                                                ЗначениеВставки=        bundleOrderTransport.get("CFO");
                                                break;
                                            case  6 :
                                                ЗначениеВставки=        bundleOrderTransport.get("Status");
                                                break;

                                        }
                                            методЗаполенияЗаказаТранспорта( bundleOrderTransport,  materialTextViewvalues,ЗначениеВставки);
                                            // TODO: 12.05.2023
                                            materialTextViewvalues.startAnimation(animationvibr1);
                                            // TODO: 18.04.2023  Внешниц вид
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                    "  bundleOrderTransport " +bundleOrderTransport +  "materialTextViewvalues " +materialTextViewvalues + " ЗначениеВставки " +ЗначениеВставки);
                                        }
                                        // TODO: 18.04.2023  Внешниц вид
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                "  bundleOrderTransport " +bundleOrderTransport);
                                        return true;
                                }

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                return false;
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
                    gridViewOrderTransport.setAdapter(АдаптерЗаказыТарнпорта);
                gridViewOrderTransport.startAnimation(animationvibr1);
                gridViewOrderTransport.smoothScrollByOffset(0);
                    gridViewOrderTransport.refreshDrawableState();
                    gridViewOrderTransport.forceLayout();
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "  cursorOrderTransport " + cursorOrderTransport);

      } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        private void методЗаполенияЗаказаТранспорта( @NonNull Bundle bundleOrderTransport,
                                                     @NonNull MaterialTextView values,
                                                     @NonNull Object ЗначениеВставки) {
            try{
                if (ЗначениеВставки==null){
                    values.setHint("не заполнено");
                    values.setText("");
                }else {
                    values.setText(ЗначениеВставки.toString());
                }
                values.setTag(bundleOrderTransport);
                values.startAnimation(animationvibr1);
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

        // TODO: 05.05.2023
        Bundle методДанныеBungle(@NonNull Cursor cursor) {
            Bundle bundleOrderTransport=new Bundle();
            try {
                Long   MainParentUUID= Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l) ;
                String      NameOrder= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("name"))).orElse("нет");
                String DateOrder= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("dateorders"))).orElse("нет");
                Integer IdOrder=Optional.ofNullable( cursor.getInt(cursor.getColumnIndex("_id"))).orElse(0);
                String NumberOrder=Optional.ofNullable( cursor.getString(cursor.getColumnIndex("number_order"))).orElse("нет");
                Integer IDPublic=Optional.ofNullable( cursor.getInt(cursor.getColumnIndex("user_update"))).orElse(0);
                String CFO= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("cfo"))).orElse("нет");
                Integer Status= Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("status"))).orElse(0);
                String GosNomer= Optional.ofNullable(cursor.getString(cursor.getColumnIndex("fullname"))).orElse("нет");
                // TODO: 12.05.2023 ПАРСИНГА  DATE
                DateOrder = методПарсингаДатыЗаказа(DateOrder);
                // TODO: 12.05.2023 ПАРСИНГА  СТАТУСА
                String Статус=методПарсингаСтатуса(Status);
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleOrderTransport.putLong("MainParentUUID", MainParentUUID);
                bundleOrderTransport.putInt("Position", cursor.getPosition());
                bundleOrderTransport.putString("NameOrder",NameOrder.trim() );
                bundleOrderTransport.putString("DateOrder",  DateOrder.trim());
                bundleOrderTransport.putInt("IdOrder", IdOrder);
                bundleOrderTransport.putString("NumberOrder", NumberOrder.trim());
                bundleOrderTransport.putInt("IDPublic", IDPublic);
                bundleOrderTransport.putString("CFO", CFO.trim());
                bundleOrderTransport.putString("Status", Статус.trim());
                bundleOrderTransport.putString("GosNomer", GosNomer.trim());
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

        @NonNull
        private String методПарсингаДатыЗаказа(String DateOrder) throws ParseException {
            try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
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
            gridViewOrderTransport.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        LinearLayout linearLayoutЗаказыТранспорта= ( LinearLayout)
                                view.findViewById(android.R.id.text1);

                        MaterialTextView materialTextView1  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue2);//ВИД ТС
                        MaterialTextView materialTextView2  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue4);//ДАТА
                        MaterialTextView materialTextView3  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue3);//ГОС.НОМЕР
                        MaterialTextView materialTextView4  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue0);///номер заказа
                        MaterialTextView materialTextView5  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue1);///ЦФО
                        MaterialTextView materialTextView6  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.otvalue5);//СТАТУС

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
                    return true;
                }
            });

        }




        // TODO: 28.04.2023
        void методПредварительнаяЗагрузкаGridView(@NonNull Integer Макет , @NonNull String Сообщение, @NonNull Integer Значек){///      R.layout.list_item_progressing_ordertransport
            try{
                    ArrayList<HashMap<String, Object>> ЛистНетданных= new ArrayList<HashMap<String, Object>> ();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("alldonttbels", Сообщение);
                    map.put("allimage", " dont");
                    ЛистНетданных.add(map);
                    SimpleAdapter АдаптерКогдаНетданных = new SimpleAdapter(getContext(),
                            ЛистНетданных,Макет,
                            new String[]{"alldonttbels","allimage"},
                            new int[]{android.R.id.text2,android.R.id.text1});

                    SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            try{
                                switch (view.getId()) {
                                    case android.R.id.text2:
                                        // TODO: 09.04.2023  ВставлЯем Данные
                                        ((MaterialTextView) view).setText(data.toString());
                                        ((MaterialTextView) view).setTextColor(Color.GRAY);
                                        ((MaterialTextView) view).setTextSize(18l);

                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"  );
                                        return  true;
                                    case android.R.id.text1:
                                     Drawable icon2 = getResources().getDrawable(   Значек);
                                        ((ImageView) view).setImageDrawable(icon2);
                                        ((ImageView) view).setImageResource(Значек);

                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                                        return true;
                                }
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
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
                    АдаптерКогдаНетданных.setViewBinder(БиндингКогдаНетДАнных);
                    АдаптерКогдаНетданных.notifyDataSetChanged();
                    gridViewOrderTransport.setAdapter(АдаптерКогдаНетданных);
                gridViewOrderTransport.startAnimation(animationvibr1);
                gridViewOrderTransport.smoothScrollByOffset(0);
                    gridViewOrderTransport.refreshDrawableState();
                    gridViewOrderTransport.forceLayout();
                    // TODO: 19.04.2023 слушаелти
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  cursorOrderTransport  " + cursorOrderTransport);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }


// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport           //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport   //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport


    }
    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}