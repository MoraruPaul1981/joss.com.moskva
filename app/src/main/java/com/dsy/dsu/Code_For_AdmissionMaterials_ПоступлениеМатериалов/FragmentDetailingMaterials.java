package com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов;

import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;


import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentDetailingMaterials extends Fragment {
    private Integer ПубличныйIDДляФрагмента;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2создать;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;
    private LayoutAnimationController layoutAnimationController;
    private Animation animation;
    private  Handler handler;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;
    private Integer ТекущаяЦФО=0;
    private Integer НомерВыбраногоМатериала =0;
    private Integer Количество =0;
    private String ВыбранныйМатериал=new String();
    private String Материал =new String();
    private  ViewGroup container;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_СозданиеНовогоМатериалов;

    private AsyncTaskLoader asyncTaskLoaderДетализация;
    private    Bundle data;
    long start;
    long startДляОбноразвовной;
    private Cursor  cursorДетализацияМатериала;

    // TODO: 27.09.2022 Фрагмент Получение Материалов
    public FragmentDetailingMaterials() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
             data=      getArguments();
            if (data!=null) {
                binderДляПолучениеМатериалов=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
                ТекущаяЦФО= data.getInt("Цфо");
                НомерВыбраногоМатериала = data.getInt("НомерВыбраногоМатериала");
                Материал =data.getString("Материал");
                Количество =data.getInt("Количество");
                ВыбранныйМатериал =data.getString("ВыбранныйМатериал");
                // TODO: 10.11.2022

            }
            start=     Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной=     Calendar.getInstance().getTimeInMillis();

            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "+binderДляПолучениеМатериалов+
                    " ТекущаяЦФО " +ТекущаяЦФО+ " НомерВыбраногоМатериала "+ НомерВыбраногоМатериала +
                    "ВыбранныйМатериал "+ВыбранныйМатериал+"Количество "+ Количество);
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

    // TODO: 18.07.2023  метод получение GET CURSOR
    private void методGetCursorForDetalizaa() {
        try{
            handler.post(()->{
                cursorДетализацияМатериала=
                        МетодПолучениеДанныхДЛяПолучениеМатериалов(
                                "ПолучениеНомерМатериалаДетализация"
                                ,ТекущаяЦФО, НомерВыбраногоМатериала);
                Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  "+binderДляПолучениеМатериалов+
                        " ТекущаяЦФО " +ТекущаяЦФО+ " НомерВыбраногоМатериала "+ НомерВыбраногоМатериала +
                        "ВыбранныйМатериал "+ВыбранныйМатериал+"Количество "+ Количество + " cursorДетализацияМатериала " +cursorДетализацияМатериала);

                if (cursorДетализацияМатериала!=null) {
                    onStart();
                    progressBarСканирование.setVisibility(View.INVISIBLE);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View    view=null;
        try {
            view= inflater.inflate(R.layout.fragment_admission_materials_detelizaziy, container, false);
            this.container=container;
            Log.d(this.getClass().getName(), " onCreateView FragmentDetailingMaterials" + view);
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
            recyclerView = view.findViewById(R.id.RecyclerView);
            fragmentManager = getActivity().getSupportFragmentManager();
            linearLayou = view.findViewById(R.id.fragmentadmissionmaterias);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setIconSize(60);
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setIconSize(50);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setIconSize(50);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
            progressBarСканирование=  view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            //todo запуск методов в фрагменте
            МетодИнициализацииRecycreView();
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            // TODO: 01.07.2023  ДАннеы
            МетодHandlerCallBack();
            МетодВыходНаAppBack();
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  recyclerView  "+recyclerView+
                    " linearLayou "+linearLayou+"  fragmentManager "+fragmentManager);
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
        try{// TODO: 03.11.2022  после получение данных перересует Экран
            if (cursorДетализацияМатериала!=null && cursorДетализацияМатериала.getCount()>=0) {
                МетодДизайнПрограссБара();
                МетодКпопкиЗначков(cursorДетализацияМатериала);
                МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
                МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager();
                МетодСлушательКурсора();
                МетодСлушательRecycleView();//todo создаем слушатель для recycreview для получение материалов
            } else {
                МетодКпопкиЗначков(cursorДетализацияМатериала);
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



    @Override
    public void onStop() {
        super.onStop();
        try{
            if (myRecycleViewAdapter.cursorДетализацияМатериала !=null) {
                myRecycleViewAdapter.cursorДетализацияМатериала.requery();
                myRecycleViewAdapter.notifyDataSetChanged();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    //TODO метод делает callback с ответом на экран
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.refreshDrawableState();
            bottomNavigationView.requestLayout();
            bottomNavigationView.forceLayout();
            recyclerView.refreshDrawableState();
            recyclerView.requestLayout();
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

    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            if(cursorДетализацияМатериала!=null){
                cursorДетализацияМатериала.moveToFirst();
            }
            myRecycleViewAdapter = new MyRecycleViewAdapter(cursorДетализацияМатериала);
            myRecycleViewAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
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
            Log.d(this.getClass().getName(), " recyclerView  "+recyclerView);

            DividerItemDecoration dividerItemDecorationHor=
                    new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
            /*            dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin*/
            recyclerView.addItemDecoration(dividerItemDecorationHor);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
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

    private void МетодВыходНаAppBack() {
        try {
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        Fragment      fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
                        fragmentTransaction = fragmentManager.beginTransaction();
                    //    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
                        fragmentTransaction.show(fragmentПолученыеМатериалов);
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
                        handler.postDelayed(()->{ МетодЗапускСозданиНовгоМатериалов();},500);
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
                        handler.postDelayed(()->{
                                    Integer ПубличныйIDДляФрагмента =
                                            new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
                                    // TODO: 16.11.2022  запуск синхронизации однорозовая
                                    МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(ПубличныйIDДляФрагмента);},
                                500);
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
    // TODO: 02.08.2022
    void МетодНепосредственногоЗапускаБиндингаОдноразовойСдлужбы(@NonNull  Integer ПубличныйIDДляФрагмента ){
        try{
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
            Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляАсих);
            Intent  intentЗапускОднорworkanager=new Intent();
            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            new Class_Generator_One_WORK_MANAGER(getContext()).
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
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    protected void МетодЗапускСозданиНовгоМатериалов() {
        try{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragment_СозданиеНовогоМатериалов = new FragmentMaretialNew();
            String FragmentNewImageName=   fragment_СозданиеНовогоМатериалов.getClass().getName();
            fragmentTransaction.addToBackStack(FragmentNewImageName);
            Bundle data=new Bundle();
            data.putBinder("binder",binderДляПолучениеМатериалов);
            fragment_СозданиеНовогоМатериалов.setArguments(data);
            fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragment_СозданиеНовогоМатериалов).commit();//.layout.activity_for_fragemtb_history_task
            fragmentTransaction.show(fragment_СозданиеНовогоМатериалов);
            Log.d(this.getClass().getName(), " fragment_СозданиеНовогоМатериалов " + fragment_СозданиеНовогоМатериалов);
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

    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетодКпопкиЗначков(@NonNull Cursor cursor)
            throws ExecutionException, InterruptedException {
        try {
            if (cursor!=null && cursor.getCount()> 0) {
                    Log.d(this.getClass().getName(), "  cursor" + cursor.getCount());
                   bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber( cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.parseColor("#15958A"));
                } else {
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    bottomNavigationView.getOrCreateBadge(R.id.id_lback).setBackgroundColor(Color.RED);
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

    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
    // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

    void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            if (myRecycleViewAdapter!=null) {
                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(this.getClass().getName(), "onChanged ");
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

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
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

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
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

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeInserted ");
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

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeRemoved ");
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

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "     onItemRangeMoved ");
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
                });
                recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
                    @Override
                    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
                        Log.d(this.getClass().getName(), "     holder "+holder);
                        //   ContentProviderOperation.Builder builder = null;
                    }
                });
                // TODO: 15.10.2022  дополнительные слушатели

                recyclerView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        return null;
                    }
                });
                // TODO: 17.10.2022 метод внешний вид нижних строчек
                recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {

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
                    public void onChildViewDetachedFromWindow(@NonNull View view) {
                        Log.d(this.getClass().getName(), "recyclerView   " + recyclerView);
                        try {
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

    // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
    void МетодСоздаенияСлушателяДляПолучениеМатериалаWorkMAnager() throws ExecutionException, InterruptedException {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
        try {
            LifecycleOwner lifecycleOwner =this ;;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            // TODO: 03.08.2022 методы работы work manager
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                            Long CallBaskОтWorkManagerОдноразового =
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                            0l);
                            if (CallBaskОтWorkManagerОдноразового>0) {
                                long end = Calendar.getInstance().getTimeInMillis();
                                long РазницаВоврмени=end-startДляОбноразвовной;
                                if (РазницаВоврмени>2000) {
                                    onStart();
                                    onResume();
                                }
                                // TODO: 21.11.2022  запускаем удаление
                            }

                        }
                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                        || СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.FAILED) == 0) {
                            МетодДизайнПрограссБара();
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

    // TODO: 02.08.2022
    protected   Cursor МетодПолучениеДанныхДЛяПолучениеМатериалов(@NonNull String  ФлагКакиеДанныеНужныПолучениеМатериалов
            ,@NonNull Integer ТекущаяЦФО
    , @NonNull Integer НомерВыбраногоМатериала ){
        Cursor cursorДетализацияМатериала = null;
        try{
            ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());
            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=new Bundle();
            switch (ФлагКакиеДанныеНужныПолучениеМатериалов){
                case "ПолучениеНомерМатериалаДетализация":
                    bundleДляПЕредачи.putString("Таблица","view_taterials");//TODO сами данные
                    break;
            }
            bundleДляПЕредачи.putInt("ПубличныйIDДляФрагмента",ПубличныйIDДляФрагмента);
            bundleДляПЕредачи.putInt("ТекущаяЦФО",ТекущаяЦФО);
            bundleДляПЕредачи.putInt("НомерВыбраногоМатериала",НомерВыбраногоМатериала);
            bundleДляПЕредачи.putString("ФлагКакиеДанныеНужныПолучениеМатериалов",ФлагКакиеДанныеНужныПолучениеМатериалов);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакиеДанныеНужныПолучениеМатериалов);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
                    cursorДетализацияМатериала = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
                    if (cursorДетализацияМатериала.getCount() > 0) {
                        cursorДетализацияМатериала.moveToFirst();
                        Log.d(this.getClass().getName(), "   cursorНомерМатериала " + cursorДетализацияМатериала);
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
        return  cursorДетализацияМатериала;
    }

    // TODO: 02.08.2022
    protected   Cursor МетодПолучениеДанныхФотографииImageДляМатериа(@NonNull Long  Paren_Image_UUID){
        Cursor cursorGetIamges= null;
        try{
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putString("Таблица","materials_databinary");//TODO сами данные
            bundleДляПЕредачи.putLong("Paren_Image_UUID",Paren_Image_UUID);
            Intent intentПолучениеМатериалов = new Intent(getContext(), Service_for_AdminissionMaterial.class);
            intentПолучениеМатериалов.setAction("GetImageFormaterial");
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            Log.d(this.getClass().getName(), "   ПубличныйIDДляФрагмента "+ ПубличныйIDДляФрагмента);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
            //TODO получение данных от Службы ДЛя Получение Материалов
            if (Paren_Image_UUID!=null) {
                cursorGetIamges = (Cursor) binderДляПолучениеМатериалов.getService()
                        .new SubClassGetDataAdmissionMaterial_Данные_ДляНовогоПоиска()
                        .МетодПолучениеДанныхForImage(getContext(), intentПолучениеМатериалов);
            }
            Log.d(this.getClass().getName(), "   cursorGetIamges " + cursorGetIamges  + " Paren_Image_UUID " +Paren_Image_UUID);
            if (cursorGetIamges.getCount() > 0) {
                cursorGetIamges.moveToFirst();
                Log.d(this.getClass().getName(), "   cursorGetIamges " + cursorGetIamges);
            }
            // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " cursorGetIamges " +cursorGetIamges);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursorGetIamges;
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
            if (cursorДетализацияМатериала !=null) {
                cursorДетализацияМатериала.registerDataSetObserver(new DataSetObserver() {
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
                cursorДетализацияМатериала.registerContentObserver(new ContentObserver(handler) {
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
        private TableLayout tableLayout_material_detalizaziy;
        private MaterialCardView materialcardView_for_detalizaziy;
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
                tableLayout_material_detalizaziy = itemView.findViewById(R.id.tableLayout_material_detalizaziy);
                // TODO: 29.06.2023
                materialcardView_for_detalizaziy = itemView.findViewById(R.id.materialcardView_for_detalizaziy);
                Log.d(this.getClass().getName(), " cardViewМатериал   " + materialcardView_for_detalizaziy);
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
        private Cursor  cursorДетализацияМатериала;
        public MyRecycleViewAdapter(@NotNull Cursor cursorДетализацияМатериала) {
            this.cursorДетализацияМатериала = cursorДетализацияМатериала;

        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursorДетализацияМатериала "+cursorДетализацияМатериала);
                if(cursorДетализацияМатериала!=null){
                    МетодЗаполняемДаннымиПолучениеМАтериалов(holder,cursorДетализацияМатериала);
                }
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position +
                        " cursorДетализацияМатериала "+cursorДетализацияМатериала);
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
                if(cursorДетализацияМатериала==null){
                    viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialovdetalizasia, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов);

                    методGetCursorForDetalizaa();
                }else {
                    if (cursorДетализацияМатериала.getCount() > 0 ) {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_assionamaterial_detalizay, parent, false);//todo old  simple_for_assionamaterial
                        Log.i(this.getClass().getName(), "   viewПолучениеМатериалов" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursorДетализацияМатериала.getCount());
                    } else   {
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisldetalizasia, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  cursorДетализацияМатериала.getCount()  " + cursorДетализацияМатериала.getCount() );
                    }
                }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                // TODO: 17.07.2023
                МетодПерегрузкаRecyceView();
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder + "  binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
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


        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
            try {
                    // TODO: 18.10.2022 заполеняем данныими
                    МетодДобавленеиЕлементоввRecycreView(holder.tableLayout_material_detalizaziy);
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
                Log.i(this.getClass().getName(), "  ТекущаяЦФО " + ТекущаяЦФО + " cursorЦФО " + cursorДетализацияМатериала + " ТекущаяЦФО " +ТекущаяЦФО);
                // TODO: 18.10.2022 название ЦФО
                if (tableLayoutРодительская!=null) {
                    // TODO: 18.10.2022 Добавяем Названием ЦФО
                    МетодНазваниеЦФОДетализация(tableLayoutРодительская);
                    // TODO: 18.10.2022 дял линии
                    МетодДанныеЛинияДетализации(tableLayoutРодительская);
                    // TODO: 18.10.2022 Добавяем Названием Столбиков
                    МетодНазваниеСтолбиковДетализация(tableLayoutРодительская);
                    // TODO: 18.10.2022 Добавяем Данные
                    МетодДанныеМатериалДетализация(tableLayoutРодительская );

                }
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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





        // TODO: 08.11.2022 метод Удаление материала
        private void МетодаКликаУдаленияМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        v.animate().rotationX(-60l);
                        handler .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 01.07.2023 удаление
                            Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                            Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                    +bundleПереходУдалениеМатериала);
                            if (bundleПереходУдалениеМатериала != null) {
                                Long UUIDДляУдаления= bundleПереходУдалениеМатериала.getLong("UUIDВыбраныйМатериал",0l);
                                String Материал= bundleПереходУдалениеМатериала.getString("Материал","");
                                Integer Количество= bundleПереходУдалениеМатериала.getInt("Количество",0);
                                bundleПереходУдалениеМатериала.putString("selection","uuid=?");
                                Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                Snackbar snackbar = Snackbar.make(v, "Text to display", Snackbar.LENGTH_LONG);
                                View view = snackbar .getView();
                                TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                                textView.setTextColor(Color.parseColor("#FF4500"));
                                textView.setText(Материал+" : "+Количество+"");
                                snackbar
                                        .setAction("Удалить ? ", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                try{

                                                    Intent intentДляУдалениеМатериалов=new Intent("УдалениеВыбранныеМатериалыДетализации");
                                                    intentДляУдалениеМатериалов.putExtras(bundleПереходУдалениеМатериала);
                                                    Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                                    Integer РезультатСменыСтатусаНАУдалнной=    binderДляПолучениеМатериалов.getService().МетодCлужбыУдалениеМатериалов(getContext(),intentДляУдалениеМатериалов);
                                                    Log.d(this.getClass().getName(), "  РезультатСменыСтатусаНАУдалнной  " + РезультатСменыСтатусаНАУдалнной);
                                                    if(РезультатСменыСтатусаНАУдалнной>0){
                                                        // TODO: 01.07.2023  метод после удланеи детализации
                                                        методПослеУдаленияЗаписиДетализации();
                                                    }
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
                                            }
                                        }).setActionTextColor(Color.WHITE)
                                        .setTextColor(Color.GRAY)
                                        .setDuration(3000)
                                        .show();
                            }
                            progressBarСканирование.setVisibility(View.INVISIBLE);
                        },150);

                        return true;
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

        private void МетодаКликаGetFotoМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBarСканирование.setVisibility(View.VISIBLE);
                        v.animate().rotationX(-60l);
                        handler .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 01.07.2023 удаление
                            Bundle bundleПереходGetImagesFormaterial=(Bundle) v.getTag();

                            методForfardForImages(v, bundleПереходGetImagesFormaterial);

                            // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                            Log.d(this.getClass().getName(),"\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            progressBarСканирование.setVisibility(View.INVISIBLE);
                        },250);
                        
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

        // TODO: 18.07.2023  метод  перехода на Image Binary  для материала
        private void методForfardForImages(@NonNull  View v, @NonNull Bundle bundleПереходGEtImages) {
            try{
            if (bundleПереходGEtImages != null) {
                Long UUIDДляУдаления= bundleПереходGEtImages.getLong("UUIDВыбраныйМатериал",0l);
                Log.d(this.getClass().getName(), "  v  " + v + " UUIDДляУдаления " +UUIDДляУдаления);
                // TODO: 18.07.2023 пололнительные параменты
                bundleПереходGEtImages.putInt("Цфо",  ТекущаяЦФО);
                bundleПереходGEtImages.putInt("НомерВыбраногоМатериала", НомерВыбраногоМатериала);
                bundleПереходGEtImages.putString("Материал", Материал);
                bundleПереходGEtImages.putInt("Количество", Количество);
                bundleПереходGEtImages.putString("ВыбранныйМатериал",ВыбранныйМатериал);
                bundleПереходGEtImages.putBinder("binder",binderДляПолучениеМатериалов);
                // TODO: 18.07.2023 переходят на Фрагмент Рисунков Binary Image
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations( android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                Fragment              fragmentImagesMaterials = new FragmentImagesMaterials();
                fragmentImagesMaterials.setArguments(bundleПереходGEtImages);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentImagesMaterials);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.commit();
                fragmentTransaction.show(fragmentImagesMaterials);
                // TODO: 18.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " UUIDДляУдаления " +UUIDДляУдаления);
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


        private void методПослеУдаленияЗаписиДетализации() {
            // TODO: 01.07.2023  метод после удаление
            try{

                методGetCursorForDetalizaa();

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorДетализацияМатериала " +cursorДетализацияМатериала);
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

        // TODO: 08.11.2022 метод редактирование
        private void МетодаКликаРедактированиеМатериалаПоtableRow(TableRow rowПервыеДанные) {
            try{
                rowПервыеДанные.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Bundle bundleПереходУдалениеМатериала=(Bundle) v.getTag();
                        Log.d(this.getClass().getName(), "МетодаКликаУдаленияМатериалаПоtableRow v  " + v+ " bundleПереходУдалениеМатериала "
                                +bundleПереходУдалениеМатериала);
                        if (bundleПереходУдалениеМатериала != null) {
                            Long UUIDДляУдаления= bundleПереходУдалениеМатериала.getLong("UUIDВыбраныйМатериал",0l);
                            String Материал= bundleПереходУдалениеМатериала.getString("Материал","");
                            Float Деньги= bundleПереходУдалениеМатериала.getFloat("Количество",0f);
                            Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                            Snackbar snackbar = Snackbar.make(v, "Text to display", Snackbar.LENGTH_LONG);
                            View view = snackbar .getView();
                            TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                            TextView viewСохранеие = (TextView) view.findViewById(R.id.snackbar_action);
                            textView.setTextColor(Color.parseColor("#FF4500"));
                            textView.setText(Деньги.toString());
                            viewСохранеие.setText("dddddfgg");
                            viewСохранеие.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                }
                            });
                            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                            snackbar
                                    .setAction("Сохранить ? ", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Log.d(this.getClass().getName(), "  v  " + v+ " UUIDДляУдаления " +UUIDДляУдаления);
                                            binderДляПолучениеМатериалов.getService().onCreate();
                                        }
                                    }).setActionTextColor(Color.WHITE)
                                    .setTextColor(Color.GRAY)
                                    .setDuration(6000000)
                                    .show();
                        }
                        return true;
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

        private void МетодНазваниеЦФОДетализация(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableRow RowName_ForColunt = методGetCFOTableRow();
                // TODO: 29.06.2023 тим Детализации
                TextView textview_dest_nameCFO=  RowName_ForColunt.findViewById(R.id.textviewname_detalis_cfo);
                // TODO: 10.11.2022  данные для название ЦФО
                String НазваниеЦФОДляДетализации= Optional.ofNullable(cursorДетализацияМатериала.getString(cursorДетализацияМатериала.
                        getColumnIndex("name_cfo"))).orElse("");
                textview_dest_nameCFO.setText(НазваниеЦФОДляДетализации.replace("\"", "")
                        .replace("\\n", "").trim());

                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForColunt, tableLayoutРодительская);
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        private void МетодНазваниеСтолбиковДетализация(@NonNull TableLayout tableLayoutРодительская) {
            try {

                TableRow RowName_ForColunt = методGetNameTableRow();
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForColunt, tableLayoutРодительская);
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодДанныеЛинияДетализации(@NonNull TableLayout tableLayoutРодительская) {
            try {
                TableRow RowName_ForLineNames = методGetLineTableRow();
                // TODO: 18.10.2022 добавляем  Линию
                МетодДобаленияНовыхСтрокДанных(RowName_ForLineNames, tableLayoutРодительская);

                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодДанныеМатериалДетализация(@NonNull TableLayout tableLayoutРодительская ) {
            try{
                do{
                    TableRow RowData_for_detalisaziy = методGetDataTableRow();
                    // TODO: 29.06.2023 тим Детализации
                TextView textview_det_type=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_type);
                // TODO: 10.11.2022  данные для название ЦФО
                String типДеталиазции= Optional.ofNullable(cursorДетализацияМатериала.getString(cursorДетализацияМатериала.
                        getColumnIndex("typematerial"))).orElse("");
                textview_det_type.setText(типДеталиазции.trim());
                // TODO: 29.06.2023 Материалоа Детализации
                TextView textview_det_material=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_material);
                // TODO: 10.11.2022  данные для название ЦФО
                String nomenvesovДетадизации= Optional.ofNullable(cursorДетализацияМатериала.getString(cursorДетализацияМатериала.
                        getColumnIndex("nomenvesov"))).orElse("");
                textview_det_material.setText(nomenvesovДетадизации.trim());
                // TODO: 29.06.2023 Материалоа Детализации
                TextView textview_det_kilichestvo=  RowData_for_detalisaziy.findViewById(R.id.textview_data_det_kilichestvo);
                // TODO: 10.11.2022  данные для название ЦФО
                Integer КоличествоДетадизации= Optional.ofNullable(cursorДетализацияМатериала.getInt(cursorДетализацияМатериала.
                        getColumnIndex("count"))).orElse(0);
                textview_det_kilichestvo.setText(КоличествоДетадизации.toString() );


                    методSetRowBungle(cursorДетализацияМатериала, RowData_for_detalisaziy, типДеталиазции, nomenvesovДетадизации, КоличествоДетадизации);


                    // TODO: 16.11.2022  слушатель Удаление строк
                    МетодаКликаУдаленияМатериалаПоtableRow(RowData_for_detalisaziy);
                    // TODO: 19.10.2022 Клик получение ФОтографии
                    МетодаКликаGetFotoМатериалаПоtableRow(RowData_for_detalisaziy);
                    // TODO: 18.10.2022 добавляем  Линию
                    МетодДобаленияНовыхСтрокДанных(RowData_for_detalisaziy, tableLayoutРодительская);

                }while (cursorДетализацияМатериала.moveToNext());
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодДобаленияНовыхСтрокДанных(TableRow rowПервыеДанные, @NonNull TableLayout tableLayoutРодительская) {
            try {
                if (tableLayoutРодительская!=null) {
                    tableLayoutРодительская.removeView(rowПервыеДанные);
                    tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
                    tableLayoutРодительская.addView(rowПервыеДанные);
                    tableLayoutРодительская.requestLayout();
                    tableLayoutРодительская.refreshDrawableState();
                    tableLayoutРодительская.forceLayout();
                }
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            Integer getCountRow=1;
            try {
                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return getCountRow;
        }
    }

    private void методSetRowBungle(@NonNull Cursor cursorДетализацияМатериала, TableRow RowData_for_detalisaziy, String типДеталиазции, String nomenvesovДетадизации, Integer КоличествоДетадизации) {
        try {
        // TODO: 29.06.2023 Данные ДЛя Текущего Row Bungle
        Long UUIDВыбраныйМатериал= Optional.ofNullable(cursorДетализацияМатериала.getLong(cursorДетализацияМатериала.
                getColumnIndex("uuid"))).orElse(0l);
        // TODO: 10.11.2022
        Bundle bundleДанныеДляСтрочки=new Bundle();
        bundleДанныеДляСтрочки.putLong("UUIDВыбраныйМатериал",UUIDВыбраныйМатериал);
        bundleДанныеДляСтрочки.putString("Материал", nomenvesovДетадизации);
        bundleДанныеДляСтрочки.putString("Тип", типДеталиазции);
        bundleДанныеДляСтрочки.putInt("Количество", КоличествоДетадизации);
        RowData_for_detalisaziy.setTag(bundleДанныеДляСтрочки);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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


    // TODO: 29.06.2023  метод получение GET Row
    @NonNull
    private TableRow методGetDataTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
        TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
          RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tableData_for_detalisaziy);
        // TODO: 29.06.2023  удаление данных из PARENT
        tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
        tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
        tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
        RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return RowData_for_detalisaziy;
    }
    // TODO: 29.06.2023  метод получение GET Row CFO
    @NonNull
    private TableRow методGetCFOTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tablerowCFO_detals);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
            RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return RowData_for_detalisaziy;
    }
    // TODO: 29.06.2023  метод получение GET Row NAME
    @NonNull
    private TableRow методGetNameTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tableName_for_detalisaziy);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
            RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return RowData_for_detalisaziy;
    }
    // TODO: 29.06.2023  метод получение GET Row Line
    @NonNull
    private TableRow методGetLineTableRow() {
        TableRow RowData_for_detalisaziy = null;
        try{
            TableLayout  tableLayoutДеталицация= (TableLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.simple_for_assionamaterial_detelizaziy_row,null);//todo old  simple_for_assionamaterial
            RowData_for_detalisaziy = (TableRow)   tableLayoutДеталицация.findViewById(R.id.tablerowline_detaliz);
            // TODO: 29.06.2023  удаление данных из PARENT
            tableLayoutДеталицация.recomputeViewAttributes(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeViewInLayout(RowData_for_detalisaziy);
            tableLayoutДеталицация.removeView(RowData_for_detalisaziy);
            RowData_for_detalisaziy.setId(new Random().nextInt());
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " RowData_for_detalisaziy " +RowData_for_detalisaziy);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return RowData_for_detalisaziy;
    }
}