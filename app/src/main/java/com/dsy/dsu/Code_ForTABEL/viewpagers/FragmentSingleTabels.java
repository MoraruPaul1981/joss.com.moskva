/*
package com.dsy.dsu.Code_ForTABEL;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.CELLUPDATE.SubClassUpdatesCELL;
import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;


public class FragmentSingleTabels extends Fragment {
    private Spinner СпинерИгодИМесяц;/////спинеры для создание табеля
    private Spinner СпинерНазваниеЦФО;/////спинеры для создание табеля

    private  boolean РежимыПросмотраДанныхЭкрана;

    private  Activity activity;
    private ConstraintLayout ГлавныйВерхнийКонтейнер;
    private ProgressDialog progressDialogДляУдаления;
    private    String ФИОТекущегоСотрудника;
    private    String Профессия;
    private    Integer ПолученаяПрофесииdata_tabels;
    private    Integer ПолученаяПрофесииFio;
    private ImageButton imageButtonДвижениеПоСотрудникамВТАбеле,imageButtonНазадПоСотрудникамВТАбеле;


    private  Integer DigitalNameCFO=0;
    private  Configuration config;
    private   TextView НазваниеДанныхВТабелеСНИЛС;
    private   String НазваниеТабеля= "";
    private  String НазваниеЗагруженногТАбеля= "";
    private  Boolean    СтаттусТабеля= false;
    private   String ДробимДляТабеляГод,ДробимДляТебеляМесяц;
    private  View ГлавныйКонтентТабеляИнфлейтер;
    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  String МесяцДляЗагрузкиТабелей= "";

    private  String ПубличноеIDЗагрузкиТабелей= "";
    private   int МЕсяцДляКурсораТабелей;
    private   int ГодДляТабелей;
    private Button КнопкаНазад;
    private Button КнопкаЛеваяПередвиженияПоДанным;
    private Button КнопкаПраваяПередвиженияПоДанным;
    private  TextView textViewчасыsimgletabel;
    private   int КоличествоДнейвЗагружаемойМесяце;

    private  String ЛимитСоСмещениемДанных= "";
    private int ИндексДвижениеТабеляСкролл=0;
    private int ИндексДвижениеТабеляКнопки=0;
    private   Context context;
    private int МесяцТабеля;
    private    TextView СловоТабель;
    private HorizontalScrollView HorizontalScrollViewВТабелеОдинСотрудник;
    private Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private View view2Линия;
    private    String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//
    final private   String ИмяСлужбыОбщейСинхронизацииДляЗадачи = "WorkManager Synchronizasiy_Data";
    private  long CurrenrsСhildUUID =0l;
    private  long CurrenrsSelectFio =0l;
    private  long MainParentUUID =0l;
    private  String ФИО;
    private      Message message;
    private      Message messageRows;
    private Animation animationПрофессия400;

    private Animation animationПрофессия300;
    private Animation animationRows;
    private         Long ПолученыйUUIDФИОСледующий=0l;
    private   Animation animationRich;
    private   Animation animationLesft;
    private   Animation animationVibr1;
    private   Animation animationVibr2;
    private Integer ВсеСтрокиТабеля=0;
    private  TextView    КонтейнерКудаЗагружаетьсяФИО;


    private ProgressBar ProgressBarSingleTabel;


    private RecyclerView recycler_view_single_tabel;
    private SubClassSingleTabelRecycreView. MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassSingleTabelRecycreView. MyViewHolder myViewHolder;

    private  Integer PositionCustomer =0;

    private  String FullNameCFO = "";
    private  Integer ГодТабелей = 0;
    private  String ИмесяцвИГодСразу = "";
    private  Integer МЕсяцТабелей=0;
    private  Bundle bundleИзMainActitivy_List_Tables;

    private TextView TextViewФИОПрофессия;

    private    InputMethodManager imm;

    private  HorizontalScrollView horizontalScrollView_tabel_single;

    private  ConstraintLayout constraintLayoutsingletabel;

    private    SubClassSingleTabelRecycreView subClassSingleTabelRecycreView;

    private   String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
    private String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
    private   LifecycleOwner lifecycleOwner;
    private   LifecycleOwner  lifecycleOwnerОбщая;
    private    long startДляОбноразвовной;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника


    public static FragmentSingleTabels newInstance(String param1, String param2) {
        FragmentSingleTabels fragment = new FragmentSingleTabels();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            super.onCreate(savedInstanceState);
            ////todo запрещает поворот экрана
           // setContentView(R.layout.activity_main__tabel_four_colums);
            recycler_view_single_tabel =  (RecyclerView) findViewById(R.id.recycler_view_single_tabel);


            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new  PUBLIC_CONTENT(getApplicationContext());
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "Внешний вид ДЛЯ ВСЕХ ОСТАЛЬНЫХ "  +Build.BRAND.toString() );
            context =this;
            Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            ((Activity) context) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "  onCreate(Bundle savedInstanceState)   MainActivity_Tabel_Single_People  ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            config = getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);
            ((Activity) context) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            СпинерИгодИМесяц = (Spinner) findViewById(R.id.СпинерТабельМесяц);
            СпинерНазваниеЦФО = (Spinner) findViewById(R.id.СпинерТабельДепратамент);
            ProgressBarSingleTabel = (ProgressBar) findViewById(R.id.ProgressBarSingleTabel);
            //  Scrollviewsingletabel = (ScrollView) findViewById(R.id.scrollviewsingletabel);
            ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА

            КнопкаЛеваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеЛеваяСтрелка);
            КнопкаПраваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеТабельПраваяСтрелка);
            textViewчасыsimgletabel =(TextView) findViewById(R.id.textViewчасыsimgletabel);
            imageButtonДвижениеПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonДвижениеПоСотрудникамВТАбеле);
            imageButtonНазадПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonНазадПоСотрудникамВТАбеле);
            textViewчасыsimgletabel.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            textViewчасыsimgletabel.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
            textViewчасыsimgletabel.setPaintFlags( (textViewчасыsimgletabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG));
            СловоТабель=(TextView) findViewById(R.id.textView3СловоТабель);
            СловоТабель.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            ЛимитСоСмещениемДанных="0";
            КнопкаНазад=(Button) findViewById(R.id.imageViewСтрелкаВнутриТабеля);
            view2Линия=(View) findViewById(R.id.view2Линия);
            ProgressBarSingleTabel.setVisibility(View.VISIBLE);
            TextViewФИОПрофессия = (TextView)  findViewById(R.id.TextViewФИОПрофессия);

            constraintLayoutsingletabel= (ConstraintLayout)  findViewById(R.id.constraintLayoutsingletabel);

        */
/*    horizontalScrollView_tabel_single= (HorizontalScrollView)  findViewById(R.id.horizontalScrollView_tabel_single);
            horizontalScrollView_tabel_single.setLeftEdgeEffectColor(Color.parseColor("#00ACC1"));
            horizontalScrollView_tabel_single.setRightEdgeEffectColor(Color.parseColor("#00ACC1"));
            horizontalScrollView_tabel_single.setSmoothScrollingEnabled(true);
            horizontalScrollView_tabel_single.pageScroll(View.FOCUS_UP);*//*



            animationПрофессия400 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_rowsingletabel);
            animationПрофессия300 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row2);
            animationVibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable);
            animationVibr2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);
            animationRows = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row_scroll_for_singletabel);
            animationRich = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
            animationLesft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1
            startДляОбноразвовной= Calendar.getInstance().getTimeInMillis();

            // TODO: 29.03.2023  Метод обсуживаюшие
            методGETДанныеИзДругихАктивити();
            МетодGetmessage();
            методСпинерМесяцы();//запускаем метод создание табеля
            МетодСпинерДепартамент( );
            МетодОтработкиПоднятияКлавиатуры();
            МетодПриНАжатииНаКнопкуBACK();


            // TODO: 20.04.2023 Данные
            Cursor     cursorForRecycreView =    new SubClassGetCursor().МетодSwipesКурсор();

            // TODO: 29.03.2023  Метод RerecyView RerecyView RerecyView RerecyView RerecyView
            lifecycleOwner=this;
            lifecycleOwnerОбщая=this;
            subClassSingleTabelRecycreView=
                    new SubClassSingleTabelRecycreView(lifecycleOwner,lifecycleOwnerОбщая,activity,cursorForRecycreView);


            subClassSingleTabelRecycreView.МетодИнициализацииRecycreView();


            subClassSingleTabelRecycreView.МетодЗаполениеRecycleView( cursorForRecycreView );

            subClassSingleTabelRecycreView. методДляSimpeCallbacks( );
            // TODO: 14.04.2023 доделываем single tabel
            subClassSingleTabelRecycreView.МетодСлушательRecycleView();

            subClassSingleTabelRecycreView.   МетодСлушательКурсора(cursorForRecycreView );

            subClassSingleTabelRecycreView.   методWorkManagerLifecycleOwner();


            // TODO: 15.06.2023 Scroll
//      subClassSingleTabelRecycreView. методДляSimpeScroolsRecyreView( );

            subClassSingleTabelRecycreView.    методИницаллизацииКлавиаотуры();



// TODO: 25.04.2023 тест код
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_tabels, container, false);
    }





    private void методGETДанныеИзДругихАктивити() {
        try {
            Intent ИнтентMainActivity_List_Peoples =  getIntent();
            bundleИзMainActitivy_List_Tables=ИнтентMainActivity_List_Peoples.getExtras();
            // TODO: 10.04.2023
            if (bundleИзMainActitivy_List_Tables!=null) {
                MainParentUUID=    bundleИзMainActitivy_List_Tables.getLong("MainParentUUID", 0l);
                PositionCustomer=    bundleИзMainActitivy_List_Tables.getInt("Position", 0);
                ГодТабелей=  bundleИзMainActitivy_List_Tables.getInt("ГодТабелей", 0);
                МЕсяцТабелей=  bundleИзMainActitivy_List_Tables.getInt("МЕсяцТабелей",0);
                DigitalNameCFO=   bundleИзMainActitivy_List_Tables.getInt("DigitalNameCFO", 0);
                FullNameCFO=  bundleИзMainActitivy_List_Tables.getString("FullNameCFO", "").trim();
                ИмесяцвИГодСразу= bundleИзMainActitivy_List_Tables.getString("ИмесяцвИГодСразу", "").trim();
                CurrenrsСhildUUID= bundleИзMainActitivy_List_Tables.getLong("CurrenrsСhildUUID", 0l);
                ФИО= bundleИзMainActitivy_List_Tables.getString("ФИО", "").trim();
                CurrenrsSelectFio= bundleИзMainActitivy_List_Tables.getLong("CurrenrsSelectFio", 0l);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                    + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                    " PositionCustomer " +PositionCustomer+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }






    @Override
    protected void onStop() {
        super.onStop();
        try{
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).removeObservers(lifecycleOwnerОбщая);
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwner);
            WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая);

            // TODO: 17.08.2022  after peossesuinbg
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
        Log.d(this.getClass().getName(), " onStart() " );
        try{

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }



// TODO: 24.05.2021  метд обоработки поднятия и апускания клавиатуры

    private void МетодОтработкиПоднятияКлавиатуры() {
        try{
            KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
                @Override
                public void onVisibilityChanged(boolean isOpen) {
                    if (isOpen) {
                        Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                " ФИОДляТабеляНаАктивти " + ФИОТекущегоСотрудника);
                        методСпинерМесяцы( );
                    }else{
                        Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                " ФИОДляТабеляНаАктивти " + ФИОТекущегоСотрудника);
                        методСпинерМесяцы( );
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }

    //todo метод возврата к предыдущему активти
    private void МетодПриНАжатииНаКнопкуBACK() {
        try {
            КнопкаНазад.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  onDestroyView");
                    методПереходаMainActivity_List_Peoples();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



    //TODO хдесь мы запускаем метод создание и обработка самого табеля
    private void методСпинерМесяцы( ) {
        try{
            List<String>  МассивДляВыбораСпинераДаты=new ArrayList<>();
            МассивДляВыбораСпинераДаты=new ArrayList<>();
            МассивДляВыбораСпинераДаты.add(ИмесяцвИГодСразу.trim());
            Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораСпинераДаты.toString());
            ArrayAdapter<String> АдаптерИгодИМесяц = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, МассивДляВыбораСпинераДаты);
            АдаптерИгодИМесяц.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            СпинерИгодИМесяц.setAdapter(АдаптерИгодИМесяц);
            СпинерИгодИМесяц.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        if (view!=null) {
                            ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                            ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                            ИмесяцвИГодСразу= String.valueOf(((TextView) parent.getChildAt(0)).getText()).toString(); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                            Log.d(this.getClass().getName(), " СпинерИгодИМесяц  " +СпинерИгодИМесяц);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " );
                }
            });
            СпинерИгодИМесяц.setClickable(false);
            СпинерИгодИМесяц.setFocusable(false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодСпинерДепартамент( ) {
        // TODO: 20.11.2022 второй спинер департамент
        try {
            List<String> МассивДляВыбораВСпинерДепартамент=new ArrayList<>();
            МассивДляВыбораВСпинерДепартамент.add(FullNameCFO.trim());
            Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораВСпинерДепартамент.toString());
            ArrayAdapter<String> АдаптерНазваниеЦФО = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,МассивДляВыбораВСпинерДепартамент);
            АдаптерНазваниеЦФО.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            СпинерНазваниеЦФО.setAdapter(АдаптерНазваниеЦФО);
            СпинерНазваниеЦФО.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        if (view!=null) {
                            ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                            ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                            ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL |Gravity.CENTER_HORIZONTAL);
                            ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                            FullNameCFO= String.valueOf(((TextView) parent.getChildAt(0)).getText()); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                            Log.d(this.getClass().getName(), " FullNameCFO  " +FullNameCFO);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    СпинерНазваниеЦФО.setClickable(false);
                    СпинерНазваниеЦФО.setFocusable(false);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private void МетодЗаполениеЭкранНАзваниеФИоИПрофесиии() {
        try{
            textViewчасыsimgletabel.setText("");
            textViewчасыsimgletabel.setText(ФИОТекущегоСотрудника.trim() + "\n"+ "("+Профессия.trim()+ " )"); ///строго имя
            Log.d(this.getClass().getName(), " ФИО " + ФИОТекущегоСотрудника + " Профессия " +Профессия);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    //TODO получение проферсии ID
    private String МетодgetПрофесия(@NonNull Integer IDПрофесии) {
        String Професия = null;
        try{
            Bundle bundle=new Bundle();
            bundle.putString("СамЗапрос",  "SELECT name  FROM  prof WHERE _id=?   ");
            bundle.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(IDПрофесии)});
            bundle.putString("Таблица","prof");
            Cursor КурсорПрофессия=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundle);
            if (КурсорПрофессия.getCount()>0){
                КурсорПрофессия.moveToFirst();
                Професия=КурсорПрофессия.getString(0);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +  " КурсорПрофессия "+
                    КурсорПрофессия.getCount()  + " Професия " +Професия);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  Професия;

    }

    // TODO: 03.04.2023 Создание  Дней Недели Вс, Пон, Ср,Черт
    private  LinkedHashMap< String,String> методВсеДниЧерезКалендарь() throws ParseException,RuntimeException {
        LinkedHashMap< String,String> linkedHashMapДни=new LinkedHashMap<>();
        try {
            Integer ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодТабелей,    МЕсяцТабелей );
            IntStream.iterate(1, i -> i + 1).limit(ПолученоеКоличествоДнейНаКонкретныйМЕсяц ).forEachOrdered(new IntConsumer() {
                @Override
                public void accept(int ИндексДней) {
                    SimpleDateFormat СозданияВычисляемВыходные = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));
                    } else {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));
                    }
                    Date ДатаПосикаВыходныеДней = null;

                    try {
                        ДатаПосикаВыходныеДней = СозданияВычисляемВыходные.parse(ГодТабелей + "-" + МЕсяцТабелей + "-" + ИндексДней);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней);
                    Integer РезультатДатыДляКонктетногоТабеляТольокЧисло = Integer.parseInt(new SimpleDateFormat("dd", new Locale("ru")).format(ДатаПосикаВыходныеДней));
                    StringBuffer БуферРезультатСокращенноВставкиВТабель = new StringBuffer();
                    БуферРезультатСокращенноВставкиВТабель.append(РезультатДатыДляКонктетногоТабеляТолькоЗанвание).append(" ,").append(РезультатДатыДляКонктетногоТабеляТольокЧисло);
                    String СокращенныйДниМесяцаВТабеле = БуферРезультатСокращенноВставкиВТабель.substring(0, 1).toUpperCase()
                            + БуферРезультатСокращенноВставкиВТабель.substring(1, БуферРезультатСокращенноВставкиВТабель.length()).toLowerCase();

                    // TODO: 11.04.2023 празничные
                    Integer ИндексДнейФинал = (Integer) ИндексДней;
                    ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                    if(МЕсяцТабелей ==5 || МЕсяцТабелей==6|| МЕсяцТабелей ==11   ){
                        if (МЕсяцТабелей ==5 ) {
                            if(ИндексДней==1 || ИндексДней==9    ){
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim()+"###");
                            }else {
                                ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                            }
                        }
                        if (МЕсяцТабелей==6) {
                            if(  ИндексДней==12   ){
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim()+"###");
                            }else {
                                ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                            }
                        }
                        if (МЕсяцТабелей ==11) {
                            if(  ИндексДней==4 ){
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim()+"###");
                            }else {
                                ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                            }
                        }
                    }else {
                        ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                        linkedHashMapДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                    }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " linkedHashMapДни " + linkedHashMapДни
                            + " КоличествоДнейвЗагружаемойМесяце " + КоличествоДнейвЗагружаемойМесяце);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  linkedHashMapДни;
    }


    */
/**
     *
     *//*

    private  int МетодПолучениеСколькоДнейВКонкретномМесяце(int Год,int Месяц) {
        Date date = null;
        int КонктетныйМесяцВВидеЦифры;
        Calendar cal;
        cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.MONTH));
        // Create a calendar object and set year and month
        Calendar mycal = new GregorianCalendar(Год, Месяц, 0);
        // Get the number of days in that month
        int КоличествоДнейНаВыбраныйМесяц = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

        return  КоличествоДнейНаВыбраныйМесяц;
    }




    ///todo сообщение информация о ФИО
    @UiThread
    protected void СообщениеИнформацияОСотруднике(@NonNull String ШабкаДиалога,
                                                  @NonNull String СообщениеДиалога) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try{
//////сам вид
            final AlertDialog alertDialogДетализацияДАнныхСотрудника = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                TextViewФИОПрофессия.setBackgroundColor(Color.WHITE);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " TextViewФИОПрофессия  " + TextViewФИОПрофессия);
                                dialog.dismiss();
                                dialog.cancel();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    })
                    .setNegativeButton("Изменить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                TextViewФИОПрофессия.setBackgroundColor(Color.WHITE);
                                Bundle bundleПрофесии=new Bundle();
                                bundleПрофесии.putString("СамЗапрос","  SELECT * FROM  prof WHERE uuid!=? ");
                                bundleПрофесии.putStringArray("УсловияВыборки" ,new String[]{"0"});
                                bundleПрофесии.putString("Таблица","prof");
                                Cursor    КурсорТаблицаПрофесии=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleПрофесии);
                                Log.d(this.getClass().getName(), " КурсорТаблицаПрофесии" + КурсорТаблицаПрофесии);
                                // TODO: 27.03.2023 Новый ПОсик
                                new SubClassSearchProfessia().МетодСообщениеНовыйПоиска(activity,КурсорТаблицаПрофесии ,message,"prof", CurrenrsСhildUUID);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " textViewчасыsimgletabel  " + TextViewФИОПрофессия);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    })
                    .setIcon(R.drawable.icon_dsu1_info_customer)
                    .setCancelable(true)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    //TODO переход на метки табеля

    private void МетодПереходаНаМеткиТабеля(@NonNull EditText editTextЯчейка) {
        try {
            Intent intentПереХодНаМеткиТабеля = new Intent();
            intentПереХодНаМеткиТабеля.setClass(getApplicationContext(), MainActivity_Metki_Tabel.class);
            Bundle bundleToMainActitivyMetkiTabel= (Bundle) editTextЯчейка.getTag();
            intentПереХодНаМеткиТабеля.putExtras(bundleToMainActitivyMetkiTabel);
            message.getTarget().postDelayed(()->{
                // TODO: 10.04.2023  переход ИЗ MAINaCTITyTabelSingle Peolpe
                методBACKFromMainActivitySingleTabel(intentПереХодНаМеткиТабеля);
            },300);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " FullNameCFO " + FullNameCFO + " CurrenrsСhildUUID " + CurrenrsСhildUUID
                    + " ГодТабелей " + ГодТабелей + " МЕсяцТабелей " + МЕсяцТабелей + " DigitalNameCFO " + DigitalNameCFO +
                    " PositionCustomer " + PositionCustomer + " ИмесяцвИГодСразу " + ИмесяцвИГодСразу+ " editTextЯчейка " +editTextЯчейка);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методBACKFromMainActivitySingleTabel(Intent intentBack) {
        try {
            Bundle bundleИзMainActitivy_List_Tables= intentBack.getExtras();
            // TODO: 10.04.2023  ОТПРАВЛЯЕММ ПЕРЕМЕННЫЕ
            startActivity(intentBack);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                    + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                    " PositionCustomer " +PositionCustomer+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу + " bundleИзMainActitivy_List_Tables " +bundleИзMainActitivy_List_Tables);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    void методПереходаMainActivity_List_Peoples() {
        try{
            ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
            Intent Интент_ПереходаMainActivity_List_Peoples = new Intent();
            Интент_ПереходаMainActivity_List_Peoples.setClass(context, MainActivity_List_Peoples.class);
            Интент_ПереходаMainActivity_List_Peoples.putExtras(bundleИзMainActitivy_List_Tables);
            методBACKFromMainActivitySingleTabel(Интент_ПереходаMainActivity_List_Peoples);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                    + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                    " PositionCustomer " +PositionCustomer+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
            startActivity(Интент_ПереходаMainActivity_List_Peoples);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    //TODO метод делает callback с ответом на экран
    private  void  МетодGetmessage(){
        try{
            message=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    try{
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return true;
                }
            }).obtainMessage();

            messageRows=Message.obtain(new Handler(Looper.myLooper()),()->{
                try{
                    Bundle bundle=   messageRows.getData();
                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() + " bundle " +bundle );
                    Log.i(this.getClass().getName(), "bundle " +bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    class SubClassSearchProfessia {
        Cursor cursorДанные;
        MaterialButton   alertDialogНовыйПосикКнопкаЗакрыть;

        SearchView    searchViewДляНовогоПоиска;
        @UiThread
        private void МетодСообщениеНовыйПоиска(@NonNull Activity activity
                ,@NonNull Cursor cursorДанные,
                                               @NonNull Message message
                ,@NonNull String ТаблицаПосика,
                                               @NonNull Long РодительскийUUDТаблицыТабель) {
            try{
                this.cursorДанные=cursorДанные;
                final ListView[] listViewДляНовыйПосик = new ListView[1];
                AlertDialog      alertDialogНовыйПосик= new MaterialAlertDialogBuilder(activity){
                    @NonNull
                    @Override
                    public MaterialAlertDialogBuilder setView(View view) {
                        listViewДляНовыйПосик[0] =    (ListView) view.findViewById(R.id.SearchViewList);
                        listViewДляНовыйПосик[0].setTextFilterEnabled(true);
                        searchViewДляНовогоПоиска=    (SearchView) view.findViewById(R.id.searchview_newscanner);
                        searchViewДляНовогоПоиска.setQueryHint("Поиск");
                        // TODO: 14.12.2022
                        searchViewДляНовогоПоиска.setDrawingCacheBackgroundColor(Color.GRAY);
                        searchViewДляНовогоПоиска.setDrawingCacheEnabled(true);
                        int id = searchViewДляНовогоПоиска.getContext()
                                .getResources()
                                .getIdentifier("android:id/search_src_text", null, null);
                        TextView textView = (TextView) searchViewДляНовогоПоиска.findViewById(id);
                        textView.setTextColor(Color.rgb(0,102,102));
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        // TODO: 14.12.2022
                        alertDialogНовыйПосикКнопкаЗакрыть =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                        alertDialogНовыйПосикКнопкаЗакрыть.setText("Закрыть");
                        ///TODO ГЛАВНЫЙ АДАПТЕР чата
                        SimpleCursorAdapter simpleCursorAdapterЦФО= new SimpleCursorAdapter(getApplicationContext(),   R.layout.simple_newspinner_dwonload_newfiltersearch, cursorДанные,
                                new String[]{ "name","_id"},
                                new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//R.layout.simple_newspinner_dwonload_cfo2
                        SimpleCursorAdapter.ViewBinder БиндингДляНовогоПоиска = new SimpleCursorAdapter.ViewBinder(){
                            @Override
                            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        Log.d(this.getClass().getName()," position");
                                        if (cursor.getCount()>0) {
                                            try{
                                                Integer ИндексНазваниеЦФО = cursor.getColumnIndex("name");///user_update  --old/// uuid
                                                String НазваниеПрофесии = cursor.getString(ИндексНазваниеЦФО);
                                                // TODO: 13.12.2022  производим состыковку
                                                Integer ИндексНазваниеПрофесииID = cursor.getColumnIndex("_id");///user_update  --old/// uuid
                                                Integer ПолучаемIDПрофессии = cursor.getInt(ИндексНазваниеПрофесииID);
                                                if (ПолучаемIDПрофессии>0) {
                                                    Integer UUIDПрофессии = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                    Long UUIDПрофесиии = cursor.getLong(UUIDПрофессии);
                                                    Bundle bundle=new Bundle();
                                                    bundle.putInt("ПолучаемIDПрофессии",ПолучаемIDПрофессии);
                                                    bundle.putString("НазваниеПрофесии",НазваниеПрофесии);
                                                    bundle.putLong("UUIDПрофесиии",UUIDПрофесиии);
                                                    bundle.putLong("CurrenrsСhildUUID",РодительскийUUDТаблицыТабель);
                                                  */
/*  UUIDТекущегоВыбраногоСотрудника=      ГлавныйALLКурсорДанныеSwipes.getLong(ГлавныйALLКурсорДанныеSwipes.getColumnIndex("uuid"));
                                                    bundle.putLong("UUIDТекущегоВыбраногоСотрудника",UUIDТекущегоВыбраногоСотрудника);*//*

                                                    ((MaterialTextView)view).setTag(bundle);
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                            + " bundle"+bundle );
                                                }
                                                // TODO: 20.01.2022
                                                Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеПрофесии);
                                                boolean ДлинаСтрокивСпиноре = НазваниеПрофесии.length() >40;
                                                if (ДлинаСтрокивСпиноре) {
                                                    StringBuffer sb = new StringBuffer(НазваниеПрофесии.trim());
                                                    sb.insert(40, System.lineSeparator());
                                                    НазваниеПрофесии = sb.toString();
                                                    Log.d(getApplicationContext().getClass().getName(), " НазваниеПрофесии " + "--" + НазваниеПрофесии);/////
                                                }
                                                ((MaterialTextView)view).setText(НазваниеПрофесии);
                                                // TODO: 29.03.2023
                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " НазваниеПрофесии"+НазваниеПрофесии);
                                                // TODO: 13.12.2022 слушатель
                                                ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        ((MaterialTextView)view).startAnimation(animationПрофессия400);
                                                        Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                        Integer ПолучаемIDПрофессии=      bundle.getInt("ПолучаемIDПрофессии",0);
                                                        String НазваниеПрофесии=   bundle.getString("НазваниеПрофесии","");
                                                        Long UUIDПрофесиии =   bundle.getLong("UUIDПрофесиии",0l);
                                                        Long CurrenrsСhildUUID =   bundle.getLong("CurrenrsСhildUUID",0l);
                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " НазваниеЦФО " +НазваниеПрофесии + " UUIDПрофесиии " +UUIDПрофесиии+
                                                                " CurrenrsСhildUUID " +РодительскийUUDТаблицыТабель);
                                                        searchViewДляНовогоПоиска.setTag(bundle);
                                                        searchViewДляНовогоПоиска.setQueryHint("");
                                                        searchViewДляНовогоПоиска.setQuery(НазваниеПрофесии,true);
                                                        if (  searchViewДляНовогоПоиска.getQuery().toString().length()==0) {
                                                            Snackbar.make(view, " Вы не выбрали  !!! "
                                                                    , Snackbar.LENGTH_LONG).show();
                                                            ((MaterialTextView)view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                            ((MaterialTextView)view).setTextColor(Color.GRAY);
                                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                        } else {
                                                            ((MaterialTextView)view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                            ((MaterialTextView)view).setTextColor(Color.BLACK);
                                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                        }
                                                        searchViewДляНовогоПоиска.refreshDrawableState();
                                                        searchViewДляНовогоПоиска.forceLayout();
                                                        ((MaterialTextView)view).refreshDrawableState();
                                                        ((MaterialTextView)view).forceLayout();
                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + "  ((MaterialTextView)view) "+ ((MaterialTextView)view).getTag());
                                                    }
                                                });
                                                // TODO: 13.12.2022 филь
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                        this.getClass().getName(),
                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                            return true;
                                        } else {
                                            Log.d(this.getClass().getName()," position");
                                            return false;
                                        }
                                }
                                return false;
                            }
                        };
                        simpleCursorAdapterЦФО.setViewBinder(БиндингДляНовогоПоиска);
                        listViewДляНовыйПосик[0].setAdapter(simpleCursorAdapterЦФО);
                        simpleCursorAdapterЦФО.notifyDataSetChanged();
                        listViewДляНовыйПосик[0].startAnimation(animationПрофессия400);
                        listViewДляНовыйПосик[0].setSelection(0);
                        listViewДляНовыйПосик[0].forceLayout();

                        // TODO: 13.12.2022  Поиск и его слушель
                        МетодПоискаФильтрНовыйПосик(searchViewДляНовогоПоиска,simpleCursorAdapterЦФО,message,listViewДляНовыйПосик[0],ТаблицаПосика);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " listViewДляНовыйПосик[0] "+listViewДляНовыйПосик[0]+"\n+"+
                                " cursorДанные " +cursorДанные+"\n"+
                                " cursorДанные " +cursorДанные+"\n" );
                        return super.setView(view);
                    }
                }
                        .setTitle("Профессии")
                        .setCancelable(false)
                        .setIcon( R.drawable.icon_newscannertwo)
                        .setView(getLayoutInflater().inflate( R.layout.simple_for_new_spinner_searchview, null ))
                        .show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(   alertDialogНовыйПосик.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.gravity = Gravity.CENTER;
                alertDialogНовыйПосик.getWindow().setAttributes(layoutParams);
                // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
                alertDialogНовыйПосикКнопкаЗакрыть.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), " position");
                            Log.d(this.getClass().getName(), "МетодСозданиеТабеля  v " + v);
                            // TODO: 28.03.2023 ЗАПИСЫВАЕМ НОВУЮ ПРОФЕССИЮ В БАЗУ
                            if (  searchViewДляНовогоПоиска.getQuery().toString().length()>5 ) {
                                searchViewДляНовогоПоиска.setQuery("",true);
                                searchViewДляНовогоПоиска.refreshDrawableState();
                                Integer ПровйдерСменаПрофесии=     МетодЗаписиСменыПрофесии( (SearchView)  searchViewДляНовогоПоиска,getApplicationContext());
                                if (ПровйдерСменаПрофесии>0) {
                                    // TODO: 29.03.2023 Методы ПОсле усМешного Смены Професиии
                                    МетодПерегрузкаВидаПрофесии(searchViewДляНовогоПоиска);
                                }else {
                                    Toast.makeText(MainActivity_Tabel_Single_PeopleViewPager.this, "Профессия не сменилась !!! ", Toast.LENGTH_SHORT).show();
                                }
                            }
                            alertDialogНовыйПосикКнопкаЗакрыть.forceLayout();
                            alertDialogНовыйПосик.dismiss();
                            alertDialogНовыйПосик.cancel();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 27.03.2023  ВТОРОЙ МЕТОД ПОИСККА ПО БАЗЕ

        private void МетодПоискаФильтрНовыйПосик(@NonNull   SearchView searchViewДляНовогоЦФО,
                                                 @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                                 @NonNull Message message,
                                                 @NonNull ListView listViewДляНовыйПосик
                ,@NonNull String ТаблицаПосика) {
            try{
                searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.d(this.getClass().getName()," position");
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.d(this.getClass().getName()," position");
                        Filter filter= simpleCursorAdapterЦФО.getFilter();
                        filter.filter(newText);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " filter "+filter.toString() );
                        return true;
                    }
                });
                simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        Log.d(this.getClass().getName()," constraint"  +constraint);
                        try{
                            cursorДанные=      МетодКурсорДляНовогоПосика(ТаблицаПосика,constraint.toString());
                            message.getTarget().post(()->{
                                if (cursorДанные.getCount()>0 && constraint.length()>0) {
                                    simpleCursorAdapterЦФО.swapCursor(cursorДанные);
                                    listViewДляНовыйПосик.setSelection(0);
                                    alertDialogНовыйПосикКнопкаЗакрыть.setText("Сохранить");
                                }else {

                                    if (cursorДанные.getCount()==0) {
                                        alertDialogНовыйПосикКнопкаЗакрыть.setText("Закрыть");
                                        searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                        message.getTarget().postDelayed(() -> {
                                            searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                        }, 500);
                                    }
                                }
                                if ( constraint.length()==0) {
                                    simpleCursorAdapterЦФО.swapCursor(cursorДанные);
                                    listViewДляНовыйПосик.setSelection(0);
                                    alertDialogНовыйПосикКнопкаЗакрыть.setText("Закрыть");
                                }



                                simpleCursorAdapterЦФО.notifyDataSetChanged();
                                searchViewДляНовогоЦФО.refreshDrawableState();
                                listViewДляНовыйПосик.deferNotifyDataSetChanged();
                                listViewДляНовыйПосик.refreshDrawableState();
                                listViewДляНовыйПосик.forceLayout();
                            });
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorДанные " +cursorДанные+"\n" +" constraint"  +constraint);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return cursorДанные;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.08.2022
        protected  Cursor МетодКурсорДляНовогоПосика(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor КурсорТаблицаПрофесииLike = null;
            try{
                Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                        ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента + " Фильтр " +Фильтр);
                Bundle bundleНовыйПоиск=new Bundle();
                bundleНовыйПоиск.putString("СамЗапрос","  SELECT * FROM  prof WHERE name  LIKE  ?  ");
                bundleНовыйПоиск.putStringArray("УсловияВыборки" ,new String[]{"%"+Фильтр+"%"});
                bundleНовыйПоиск.putString("Таблица","prof");
                КурсорТаблицаПрофесииLike=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleНовыйПоиск);
                Log.d(this.getClass().getName(), " КурсорТаблицаПрофесииLike" + КурсорТаблицаПрофесииLike);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  КурсорТаблицаПрофесииLike;
        }
        // TODO: 12.04.2023 смена професси


        Integer МетодЗаписиСменыПрофесии(@NonNull View searchViewДляНовогоПоиска, @NonNull Context context){ //TODO метод записи СМЕНЫ ПРОФЕСИИ
            Integer ОбновлениеПрофесии=0;
            try{
                String ТаблицаОбработки="data_tabels";
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " searchViewДляНовогоПоиска "+searchViewДляНовогоПоиска+ " ТаблицаОбработки "+ТаблицаОбработки);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                Bundle bundleСменаПрофессии= (Bundle)  searchViewДляНовогоПоиска.getTag();
                ContentValues valuesСменаПрофесси=new ContentValues();
                Integer ПолучаемIDПрофессии=      bundleСменаПрофессии.getInt("ПолучаемIDПрофессии",0);
                valuesСменаПрофесси.put("prof",ПолучаемIDПрофессии);
                Long ВерсияДанныхUp = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(ТаблицаОбработки,getApplicationContext(),
                        new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу());
                valuesСменаПрофесси.put("current_table",ВерсияДанныхUp);
                String ДатаОбновления=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                valuesСменаПрофесси.put("date_update",ДатаОбновления);
                Long CurrenrsСhildUUID =   bundleСменаПрофессии.getLong("CurrenrsСhildUUID",0l);
                ContentResolver contentResolver=context.getContentResolver();
                ОбновлениеПрофесии=  contentResolver.update(uri, valuesСменаПрофесси,"uuid=?",new String[]{String.valueOf(CurrenrsСhildUUID)});


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РЕЗУЛЬТАТ ОбновлениеПрофесии  " +  ОбновлениеПрофесии);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ОбновлениеПрофесии;
        }



    }




    private void МетодПерегрузкаВидаПрофесии(@NonNull SearchView searchViewДляНовогоПоиска) {
        try {
            Bundle bundleПослеУспешнойСменыПрофесии=   (Bundle)     searchViewДляНовогоПоиска.getTag();
            String УспешнаяСменПрофессия=   bundleПослеУспешнойСменыПрофесии.getString("НазваниеПрофесии");
            TextViewФИОПрофессия.setText(ФИО.trim() + "\n"+ УспешнаяСменПрофессия);
            TextViewФИОПрофессия.startAnimation(animationПрофессия400) ;
            textViewчасыsimgletabel.startAnimation(animationПрофессия400);
            textViewчасыsimgletabel.refreshDrawableState();
            textViewчасыsimgletabel.forceLayout();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  УспешнаяСменПрофессия " +УспешнаяСменПрофессия);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    //TODO класс визуализации внешнего вида

    public class SubClassSingleTabelRecycreView  {
        private     Cursor cursor;
        private LinkedHashMap< String,String> ДниВыходные=new LinkedHashMap<>();

        public SubClassSingleTabelRecycreView(@NonNull  LifecycleOwner lifecycleOwner,
                                              @NonNull  LifecycleOwner  lifecycleOwnerОбщая,
                                              @NonNull Activity activity,
                                              @NonNull Cursor cursor) {
            this.cursor=cursor;
        }

        private void МетодИнициализацииRecycreView() {
            try{
                DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(activity,GridLayoutManager.VERTICAL);
                */
/*DividerItemDecoration dividerItemDecorationVer=
                        new DividerItemDecoration(activity,GridLayoutManager.VERTICAL);
                dividerItemDecorationHor.setDrawable(getDrawable(R.drawable.divider_for_single_tabel));///R.dimen.activity_horizontal_margin*//*


                GridLayoutManager layoutManager = new GridLayoutManager(activity, 4);


                */
/*    layoutManager.setOrientation(GridLayoutManager.VERTICAL);*//*

                recycler_view_single_tabel.addItemDecoration(dividerItemDecorationHor);
                //recycler_view_single_tabel.addItemDecoration(dividerItemDecorationVer);
                recycler_view_single_tabel.setLayoutManager(layoutManager);
                recycler_view_single_tabel.setHasFixedSize(true);
                recycler_view_single_tabel.setAnimation(animationVibr1);
                recycler_view_single_tabel.smoothScrollToPosition(0);
                layoutManager.setSmoothScrollbarEnabled(true);
                recycler_view_single_tabel.setHorizontalFadingEdgeEnabled(true);
                recycler_view_single_tabel.setHorizontalScrollBarEnabled(true);
                recycler_view_single_tabel.setNestedScrollingEnabled(true);
                // TODO: 12.05.2023 Клаиатура
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursor " +cursor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void методИницаллизацииКлавиаотуры(){
            try{
                imm = (InputMethodManager) recycler_view_single_tabel.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // imm.showSoftInput(recycler_view_single_tabel, InputMethodManager.SHOW_FORCED);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursor " +cursor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 15.06.2023 свайвы
        private void методДляSimpeCallbacks(   ) {
            try{

                // TODO: 11.05.2023 SWIPE:
                ItemTouchHelper.SimpleCallback simpleItemTouchCallbackRIGHT = new ItemTouchHelper.SimpleCallback(10,
                        ItemTouchHelper.RIGHT   ) {

                    @Override
                    public boolean isItemViewSwipeEnabled() {
                        return true;
                    }
                    @Override
                    public boolean isLongPressDragEnabled() {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        try{
                            ProgressBarSingleTabel.setVisibility(View.VISIBLE);
                            subClassSingleTabelRecycreView.       методЗакрываемКлавитатуру(recycler_view_single_tabel);
                            // TODO: 17.06.2023 сама свайп
                            SubClassReBornDataRecyreView subClassReBornDataRecyreView=new SubClassReBornDataRecyreView();
                            subClassReBornDataRecyreView.методПереРоденияRevireViewScroll();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                            @NonNull RecyclerView.ViewHolder viewHolder,
                                            float dX, float dY, int actionState, boolean isCurrentlyActive) {


                        методИзмененияЦветаSwipes(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                        //методАнимацияRecyreView(viewHolder);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor
                                + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО);
                    }

                    private void методИзмененияЦветаSwipes(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                                           @NonNull    RecyclerView.ViewHolder viewHolder,
                                                           float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try{
                            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                                // Get RecyclerView item from the ViewHolder
                                View itemView = viewHolder.itemView;

                                Paint p = new Paint();
                                if(dX > 0) {
                                    p.setColor(Color.parseColor("#1C9CA8")) ;
                                } else {
                                    p.setColor(Color.parseColor("#48D1CC")) ;
                                }
                                if (dX > 0) {
                                    */
/* Set your color for positive displacement *//*


                                    // Draw Rect with varying right side, equal to displacement dX
                                    c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                            (float) itemView.getBottom(), p);
                                } else {
                                    */
/* Set your color for negative displacement *//*


                                    // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                            (float) itemView.getRight(), (float) itemView.getBottom(), p);
                                }

                                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // move item in `fromPos` to `toPos` in adapter.
                        return true;// true if moved, false otherwise
                    }



                };
                ItemTouchHelper.SimpleCallback simpleItemTouchCallbackLEFT = new ItemTouchHelper.SimpleCallback(10,
                        ItemTouchHelper.LEFT  ) {

                    @Override
                    public boolean isItemViewSwipeEnabled() {
                        return true;
                    }
                    @Override
                    public boolean isLongPressDragEnabled() {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        try{
                            ProgressBarSingleTabel.setVisibility(View.VISIBLE);
                            subClassSingleTabelRecycreView.       методЗакрываемКлавитатуру(recycler_view_single_tabel);
                            // TODO: 17.06.2023 сама свайп
                            SubClassReBornDataRecyreView subClassReBornDataRecyreView=new SubClassReBornDataRecyreView();
                            subClassReBornDataRecyreView.методПереРоденияRevireViewScroll();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recycler_view_single_tabel   " + recycler_view_single_tabel + " cursor " +cursor);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
                        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        //  super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        методИзмененияЦветаSwipes(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        //  методАнимацияRecyreView(viewHolder);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor
                                + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО);
                    }
                    private void методИзмененияЦветаSwipes(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                                           @NonNull    RecyclerView.ViewHolder viewHolder,
                                                           float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try{
                            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                                // Get RecyclerView item from the ViewHolder
                                View itemView = viewHolder.itemView;

                                Paint p = new Paint();
                                if(dX > 0) {
                                    p.setColor(Color.parseColor("#48D1CC")) ;
                                } else {
                                    p.setColor(Color.parseColor("#1C9CA8")) ;
                                }
                                if (dX > 0) {
                                    */
/* Set your color for positive displacement *//*


                                    // Draw Rect with varying right side, equal to displacement dX
                                    c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                            (float) itemView.getBottom(), p);
                                } else {
                                    */
/* Set your color for negative displacement *//*


                                    // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                            (float) itemView.getRight(), (float) itemView.getBottom(), p);
                                }

                                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
                        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
                    }

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // move item in `fromPos` to `toPos` in adapter.
                        return true;// true if moved, false otherwise
                    }

                };

                ItemTouchHelper itemTouchHelperLEFT = new ItemTouchHelper(simpleItemTouchCallbackLEFT);
                itemTouchHelperLEFT.attachToRecyclerView(recycler_view_single_tabel);
                ItemTouchHelper itemTouchHelperRIGHT = new ItemTouchHelper(simpleItemTouchCallbackRIGHT);
                itemTouchHelperRIGHT.attachToRecyclerView(recycler_view_single_tabel);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recycler_view_single_tabel   " + recycler_view_single_tabel);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методДляSimpeScroolsRecyreView(   ) {
            try{
                recycler_view_single_tabel.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        recyclerView.setFocusable(false);
                        recyclerView.setClickable(false);

                        if(recyclerView.SCROLL_STATE_DRAGGING==newState){
                                             */
/*  SubClassReBornDataRecyreView subClassReBornDataRecyreView=new SubClassReBornDataRecyreView();

                        subClassReBornDataRecyreView.методПереРоденияRevireViewScroll();*//*


                            onScrolled(recyclerView,recyclerView.getScrollX(),recyclerView.getScrollY());

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                                    " oldScrollY ");
                        }
                        if(recyclerView.SCROLL_STATE_IDLE==newState){
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                                    " oldScrollY ");

                        }
                        if(recyclerView.SCROLL_STATE_SETTLING==newState){
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                                    " oldScrollY ");

                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                                " oldScrollY ");
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                                " oldScrollY ");
                    }
                });
                // TODO: 17.06.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor
                        + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }






        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private void методScrollsLeftRecyreView() {
            try {
                Integer Позиция=        myRecycleViewAdapter.cursor.getPosition();
                // TODO: 20.04.2023 Данные
                cursor =    new SubClassGetCursor().МетодSwipesКурсор();
                if (myRecycleViewAdapter.cursor.isLast()){
                    cursor.moveToFirst();
                }else {
                    Позиция=Позиция+1;
                    cursor.moveToPosition(Позиция);
                }
                CurrenrsСhildUUID=       cursor.getLong(cursor.getColumnIndex("uuid"));
                CurrenrsSelectFio=       cursor.getLong(cursor.getColumnIndex("fio"));
                ФИО=       cursor.getString(cursor.getColumnIndex("name"));

                myRecycleViewAdapter.cursor=cursor;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursor " +cursor.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private void методAlterSaveCellRecyreView(@NonNull View  v) {
            try {
                // TODO: 20.04.2023 Данные

                Integer Позиция=        myRecycleViewAdapter.cursor.getPosition();
                // TODO: 20.04.2023 Данные
                cursor =    new SubClassGetCursor().МетодSwipesКурсор();

                cursor.moveToPosition(Позиция);

                CurrenrsСhildUUID=       cursor.getLong(cursor.getColumnIndex("uuid"));
                CurrenrsSelectFio=       cursor.getLong(cursor.getColumnIndex("fio"));
                ФИО=       cursor.getString(cursor.getColumnIndex("name"));

                myRecycleViewAdapter.cursor=cursor;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+ " cursor " +cursor+
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursor " +cursor.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        public void методПослеОбновлениеЯчейкиСчитаемЧасы() {
            try{
                // TODO: 14.04.2023 пересчитываем часы
                class  SubClassGetCursorЧасы extends SubClassGetCursor {
                    @Override
                    protected Cursor МетодSwipesКурсор() {
                        try{
                            СамЗапрос=" SELECT  *   FROM viewtabel AS t" +
                                    " WHERE t.uuid=?   AND t.status_send !=?  AND t.fio IS NOT NULL  ORDER BY   t.date_update  " ;
                            УсловияВыборки=new String[]{ String.valueOf(CurrenrsСhildUUID),
                                    String.valueOf(  "Удаленная")};
                            //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                            Bundle bundleГлавныйКурсорMultiДанныеSwipes= new Bundle();
                            bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос",СамЗапрос);
                            bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,УсловияВыборки);
                            bundleГлавныйКурсорMultiДанныеSwipes.putString("Таблица","viewtabel");
                            cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleГлавныйКурсорMultiДанныеSwipes);
                            // TODO: 13.04.2023 делаем смещение по курсору
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return  cursor;
                    }
                }
                // TODO: 16.06.2023 само выполение
                Cursor    cursorДляЧасов =  new SubClassGetCursorЧасы().МетодSwipesКурсор();
                // TODO: 14.04.2023 пересчитываем часы
                методСчитаемЧасы(cursorДляЧасов,myViewHolder );
                МетодПерегрузкаЧасыSingletabel();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 04.03.2022 прозвомжность Заполения RecycleView
        void МетодЗаполениеRecycleView(  @NonNull Cursor cursor) {
            try {
                // remove item from adapter
                myRecycleViewAdapter = new  MyRecycleViewAdapter(cursor );
                recycler_view_single_tabel.setAdapter(myRecycleViewAdapter);
                // TODO: 16.06.2023  перегрузка экрана
                методПерегрузкиRecycreView();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "cursor  " + cursor );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void методАнимацияRecyreView(@NonNull  RecyclerView.ViewHolder viewHolder) {
            try{
                viewHolder.itemView.setBackgroundColor(Color.GRAY);
                viewHolder.itemView.refreshDrawableState();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {
            private TableLayout TableLayoutSingleTabel;
            private  TableRow rowName ;
            private  TableRow rowData;
            public MyViewHolder(@NonNull View itemView ) {
                super(itemView);
                try {
                    // TODO: 04.04.2023 Метод Иинициализации RecycreVIEW ALL ELEMENT
                    МетодИнициализацииRecycreViewSingleTabel(itemView);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " itemView "+itemView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            private void МетодИнициализацииRecycreViewSingleTabel(@NonNull View itemView) {
                try {
                    // TODO: 04.04.2023  Иниуциализация Комепонетов
                    TableLayoutSingleTabel = itemView.findViewById(R.id.TableLayoutSingleTabelOneRow);
                    if (TableLayoutSingleTabel!=null) {
                        // TODO: 04.04.2023   NAME
                        rowName= (TableRow) TableLayoutSingleTabel.findViewById(R.id.TableRow1Name);
                        // TODO: 04.04.2023   Data
                        rowData= (TableRow) TableLayoutSingleTabel.findViewById(R.id.TableData1Row);
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" TableLayoutSingleTabel   " + TableLayoutSingleTabel+
                            " TableLayoutSingleTabel " +TableLayoutSingleTabel);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

        }
        class MyRecycleViewAdapter extends RecyclerView.Adapter< MyViewHolder> {
            private Cursor cursor;
            public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                try{
                    this.cursor = cursor;
                    if ( cursor!=null) {
                        if (cursor.getCount() > 0 ) {
                            // TODO: 16.04.2023  празники и авходные
                            ДниВыходные=методВсеДниЧерезКалендарь();
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cursor  " + cursor.getCount()+ " ДниВыходные " +ДниВыходные);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
                try {
                    Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor);
                    if (cursor!=null) {
                        if (cursor.getCount() > 0 && holder.TableLayoutSingleTabel != null) {
                            МетодЗаполняемДаннымиRecycreViewSingleTable(holder, cursor);
                            // TODO: 07.04.2023 переопрелделния Вида Табеля
                            Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                            Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                        } else {
                            Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                        }
                        // TODO: 06.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor " +cursor +
                                " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                try{
                    recyclerView.removeAllViews();
                    recyclerView.getRecycledViewPool().clear();
                    super.onAttachedToRecyclerView(recyclerView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
                try {
                    Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return super.getItemViewType(position);
            }

            @NonNull
            @Override
            public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewSingleTabel = null;
                try {
                    viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov, parent, false);

                    if (cursor.getCount()>0) {
                        // viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm, parent, false);
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm_one_row, parent, false);
                        if (myViewHolder!=null) {
                            switch (   myViewHolder.getAbsoluteAdapterPosition()){
                                case 6:
                                    // TODO: 14.04.2023 ЧАСЫ
                                    методПослеОбновлениеЯчейкиСчитаемЧасы();

                                    //  методСчитаемЧасы(cursor );
                                    // TODO: 04.04.2023  ФИО
                                    new SubClassChanegeSetNameProffesio().    МетодЗаполняемФИОRow( cursor);
                                    // TODO: 16.04.2023 Професии Професии Професии Професии
                                    МетодаКликаTableRowФИО( );
                                    break;
                                // TODO: 18.06.2023
                */
/*    case 29:
                    case 30:
                    case 31:
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm_last_row, parent, false);
                        // TODO: 16.06.2023
                        break;*//*

                            }
                        }
                    }else{
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_single_tabel, parent, false);
                        Log.d(this.getClass().getName(),"\n" + " НЕт ДАнных class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                    }

                    // TODO: 04.04.2023  Запускаем ПОлучений Вид View
                    myViewHolder = new  MyViewHolder(viewSingleTabel);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder+
                            myViewHolder.getLayoutPosition()+ myViewHolder.getAbsoluteAdapterPosition());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return myViewHolder;
            }


            @Override
            public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
                try {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor " +cursor +
                            " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void МетодАнимации(@NonNull  TableRow tableRow) {
                try {
                    tableRow.startAnimation(animationПрофессия300);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor " +cursor +
                            " animationПрофессия300 " +animationПрофессия300);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            ///todo первый метод #1
            private void МетодЗаполняемДаннымиRecycreViewSingleTable(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                try {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+holder.getLayoutPosition()+  " "+myViewHolder.getLayoutPosition());
                    // TODO: 04.04.2023   DATA
                    МетодЗаполняемДаннымиTableRow(cursor ,holder  );
                    // TODO: 04.04.2023   Name
                    МетодЗаполняеШабкаTableRow(cursor ,holder);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void МетодЗаполняемДаннымиTableRow(@NonNull Cursor cursor,
                                                       @NonNull  MyViewHolder holder) {
                try {
                    //ListIterator<TableRow> listIterator = holder.rowData.listIterator();
                    // TODO: 06.04.2023
                    //  Integer  ИндексСтрочкиOffSet = getИндексСтрочкиДней(holder);
                    TableRow tableRowДАнные = holder.rowData;
                    // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                    EditText editTextRowКликПоДАнными = (EditText) tableRowДАнные.getChildAt(0);
                    Integer ПозицияДня=holder.getLayoutPosition();
                    ПозицияДня=ПозицияДня+1;
                    String ДнейСодержимое =            "d"+ПозицияДня;

                    // TODO: 06.04.2023  НАЗВАНИЕ ROW
                    if (editTextRowКликПоДАнными != null) {
                        // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                        if (ДниВыходные.containsKey(ДнейСодержимое.trim())) {

                            String ВыходныеИлиПразничные=    ДниВыходные.get(ДнейСодержимое.trim());
                            if (ВыходныеИлиПразничные!=null) {
                                editTextRowКликПоДАнными.setVisibility(View.VISIBLE);
                            }

                            методЗаполениеСодеримомRowData(editTextRowКликПоДАнными, cursor, ДнейСодержимое);
                        }

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " editTextRowКликПоДАнными " + editTextRowКликПоДАнными + " ДнейСодержимое " + ДнейСодержимое);
                        // TODO: 20.04.2023
                        // TODO: 10.05.2023 Зепляем Слушатель
                        // TODO: 05.04.2023 Вешаем на Ячекку ДАнных Слушатель
                        МетодаКликаПоtableRow(editTextRowКликПоДАнными );
                        // TODO: 19.10.2022 ScroolView
                        // TODO: 10.05.2023
                        МетодаScrollViewEdittext(editTextRowКликПоДАнными);
                    }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor  " + cursor);
                    // TODO: 10.05.2023  Слушатель ПО ЯЧЕЙКАМ
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor  " + cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private int getИндексСтрочкиДней(@NonNull MyViewHolder holder) {
                Integer ИндексСтрочкиДней=0;
                try {
                    switch (holder.getLayoutPosition()){
                        case 0:
                            ИндексСтрочкиДней =   1;
                            break;
                        case 1:
                            ИндексСтрочкиДней =  5;
                            break;
                        case 2:
                            ИндексСтрочкиДней =   9;
                            break;
                        case 3:
                            ИндексСтрочкиДней =  13;
                            break;
                        case 4:
                            ИндексСтрочкиДней =  17;
                            break;
                        case 5:
                            ИндексСтрочкиДней =  21;
                            break;
                        case 6:
                            ИндексСтрочкиДней =  25;
                            break;
                        case 7:
                            ИндексСтрочкиДней =  29;
                            break;


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return ИндексСтрочкиДней;
            }

            private void МетодЗаполняеШабкаTableRow(@NonNull Cursor cursor,
                                                    @NonNull  MyViewHolder holder) {
                try {
                    //ListIterator<TableRow> listIterator = holder.rowName.listIterator();
                    // TODO: 06.04.2023
                    //Integer  ИндексСтрочкиOffSet = getИндексСтрочкиДней(holder);
                    TableRow tableRowШабка = holder.rowName;
                    // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                    TextView viewtextRowКликПоШабка = (TextView) tableRowШабка.getChildAt(0);
                    //String ДнейНазвание = Optional.ofNullable(viewtextRowКликПоШабка.getHint()).map(Objects::toString).orElse("");
                    Integer ПозицияДня=holder.getLayoutPosition();
                    ПозицияДня=ПозицияДня+1;
                    String ДнейНазвание = "d" + ПозицияДня;
                    // TODO: 06.04.2023  НАЗВАНИЕ ROW
                    if (viewtextRowКликПоШабка != null) {
                        // TODO: 06.04.2023 Названия
                        if (ДниВыходные.containsKey(ДнейНазвание.trim())) {
                            TableRow tableRowДАнные = holder.rowData;
                            // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                            EditText editTextRowКликПоДАнными = (EditText) tableRowДАнные.getChildAt(0);
                            viewtextRowКликПоШабка.setVisibility(View.VISIBLE);
                            editTextRowКликПоДАнными.setVisibility(View.VISIBLE);

                            методЗаполениеНазванияRowData(viewtextRowКликПоШабка, ДнейНазвание);
                        }
                        // TODO: 16.04.2023  анимация
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " editTextRowКликПоШабка " + viewtextRowКликПоШабка + " ДнейНазвание " + ДнейНазвание);
                    }

                    // TODO: 11.04.2023  оформление вида
                    МетодАнимации(tableRowШабка);
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor  " + cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            private void методЗаполениеСодеримомRowData(@NonNull  EditText editTextRowКликПоДАнными,
                                                        @NonNull Cursor cursor,
                                                        @NonNull String НазваниеДляДень) {
                try {
                    Bundle dataRowData = null;
                    String День = Optional.ofNullable(cursor.getString(cursor.getColumnIndex(НазваниеДляДень))).orElse("0");
                    if(День.length()==0){День="0";}
                    CurrenrsСhildUUID= Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l);
                    dataRowData = new Bundle();
                    dataRowData.putString("ЗначениеДня", День);
                    dataRowData.putLong("uuid", CurrenrsСhildUUID);
                    dataRowData.putString("День", НазваниеДляДень);
                    // TODO: 13.04.2023  дополнительные
                    dataRowData.putLong("MainParentUUID", MainParentUUID);
                    dataRowData.putInt("Position", PositionCustomer);
                    dataRowData.putInt("ГодТабелей",   ГодТабелей);
                    dataRowData.putInt("МЕсяцТабелей", МЕсяцТабелей);
                    dataRowData.putInt("DigitalNameCFO", DigitalNameCFO);
                    dataRowData.putString("FullNameCFO", FullNameCFO);
                    dataRowData.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу);
                    dataRowData.putLong("CurrenrsСhildUUID", CurrenrsСhildUUID);
                    dataRowData.putString("ФИО", ФИО);
                    dataRowData.putLong("CurrenrsSelectFio", CurrenrsSelectFio);
                    editTextRowКликПоДАнными.setTag(dataRowData);
                    editTextRowКликПоДАнными.setVisibility(View.VISIBLE);
                    editTextRowКликПоДАнными.setText(День.trim());
                    // TODO: 07.06.2023
                    методИзменяемЦветСодержимоваЦифраИлиБуква(editTextRowКликПоДАнными, День);

                    editTextRowКликПоДАнными.startAnimation(animationRows);

                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень "
                            + НазваниеДляДень + "dataRowData " + dataRowData);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void методИзменяемЦветСодержимоваЦифраИлиБуква(@NonNull EditText editTextRowКликПоДАнными,@NonNull String День) {
                try {
                    char ЕслиБуквывДнеСодержимое=     День.charAt(0);
                    if(Character.isLetter( ЕслиБуквывДнеСодержимое )){
                        editTextRowКликПоДАнными.setTextColor(Color.GRAY);
                    }else {
                        editTextRowКликПоДАнными.setTextColor(Color.BLACK);
                    }
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень ");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void методЗаполениеНазванияRowData(@NonNull  TextView TextViewRowКликПоНазваниям,String s) {
                try {
                    // TODO: 11.04.2023 Ставим Дни
                    String ВыходныеИлиПразничные=    ДниВыходные.get(s.trim());
                    TextViewRowКликПоНазваниям.setText( ВыходныеИлиПразничные);
                    if (ВыходныеИлиПразничные!=null) {
                        TextViewRowКликПоНазваниям.setVisibility(View.VISIBLE);
                    }else {
                        TextViewRowКликПоНазваниям.setVisibility(View.INVISIBLE);
                    }
                    TextViewRowКликПоНазваниям.startAnimation(animationVibr1);
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " s.trim() " +s.trim() + "  ДниВыходные.get(s.trim()) "
                            + ДниВыходные.get(s.trim()));

                    TextViewRowКликПоНазваниям.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {
                            try{
                                // TODO: 19.10.202
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " ДниВыходные " +ДниВыходные);
                                // TODO: 11.04.2023 меняем Цвет и Убираем *** если празничные


                                if (ВыходныеИлиПразничные!=null) {
                                    Bundle bundleВыходныеИПразничные=(Bundle)        ((MaterialTextView)v).getTag();
                                    // TODO: 07.06.2023
                                    методОбработкиВыходныхиПразничные(ВыходныеИлиПразничные,((MaterialTextView)v));


                        */
/*        if (ВыходныеИлиПразничные.matches("(.*)Вс(.*)")
                                        || ВыходныеИлиПразничные.matches("(.*)Сб(.*)")
                                        && ! ВыходныеИлиПразничные.matches("(.*)###(.*)")) {
                                    ЗначениеДней = ((MaterialTextView)v).getText() .toString();
                                    // TODO: 07.06.2023
                                    методОбработкиВыходныхиПразничные(ЗначениеДней,((MaterialTextView)v));
                                }else {
                                Integer ЗначениеДнейУжеЗаполнено=    bundleВыходныеИПразничные.getInt("StatusCell");
                                    методОбработкиВыходныхиПразничные(ЗначениеДнейУжеЗаполнено,((MaterialTextView)v));
                                }*//*

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " TextViewRowКликПоНазваниям " +TextViewRowКликПоНазваниям+
                                            "  ВыходныеИлиПразничные " +ВыходныеИлиПразничные + " bundleВыходныеИПразничные " +bundleВыходныеИПразничные);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getApplicationContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        @Override
                        public void onViewDetachedFromWindow(View v) {
                            // TODO: 19.10.2022
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " TextViewRowКликПоНазваниям " +TextViewRowКликПоНазваниям);
                        }
                    });

                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " TextViewRowКликПоНазваниям " +TextViewRowКликПоНазваниям);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void методОбработкиВыходныхиПразничные(@NonNull String ЗначениеДней,@NonNull MaterialTextView materialTextView) {
                try{
                    if (ЗначениеДней.matches("(.*)Вс(.*)")
                            || ЗначениеДней.matches("(.*)Сб(.*)")
                            && ! ЗначениеДней.matches("(.*)###(.*)")) {
                        materialTextView.setTextColor(Color.parseColor("#DC143C"));
                        Bundle bundleВыходной=new Bundle();
                        bundleВыходной.putInt("StatusCell",2);
                        materialTextView.setText(ЗначениеДней);
                        materialTextView.setTag(bundleВыходной);
                    }else{
                        if (ЗначениеДней.matches("(.*)###(.*)") ) {
                            ЗначениеДней=           ЗначениеДней.replaceAll("###","");
                            materialTextView.setText(ЗначениеДней);
                            materialTextView.setTextColor(Color.parseColor("#9C112D"));
                            Bundle bundleПразничныйДень=new Bundle();
                            bundleПразничныйДень.putInt("StatusCell",3);
                            materialTextView.setTag(bundleПразничныйДень);
                        }else{
                            materialTextView.setTextColor(Color.parseColor("#008080"));
                            Bundle bundleОбычныйДень=new Bundle();
                            bundleОбычныйДень.putInt("StatusCell",1);
                            materialTextView.setText(ЗначениеДней);
                            materialTextView.setTag(bundleОбычныйДень);
                        }
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 07.06.2023 2
            private void методОбработкиВыходныхиПразничные(@NonNull Integer ЗначениеДней,@NonNull MaterialTextView materialTextView) {
                try{
                    if (ЗначениеДней==1) {
                        materialTextView.setTextColor(Color.parseColor("#008080"));
                        Bundle bundleОбычныйДень = new Bundle();
                        bundleОбычныйДень.putInt("StatusCell", 1);
                        materialTextView.setTag(bundleОбычныйДень);

                    }
                    if (ЗначениеДней==2){
                        Bundle bundleВыходной=new Bundle();
                        materialTextView.setTextColor(Color.parseColor("#DC143C"));
                        bundleВыходной.putInt("StatusCell",2);
                        materialTextView.setTag(bundleВыходной);
                    }

                    if (ЗначениеДней==3) {
                        materialTextView.setTextColor(Color.parseColor("#9C112D"));
                        Bundle bundleПразничныйДень=new Bundle();
                        bundleПразничныйДень.putInt("StatusCell",3);
                        materialTextView.setTag(bundleПразничныйДень);
                    }

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            // TODO: 08.11.2022 метод КЛИК ПО ДАННЫМ
            private void МетодаКликаПоtableRow(@NonNull   EditText editTextRowКликПоДАнными  ) {
                final String[] ЗначениеДоЗаполениясОшибкой = {null};
                try{
                    if (editTextRowКликПоДАнными!=null) {
                        // TODO: 19.10.2022  ЛОНГ КЛИК
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        editTextRowКликПоДАнными.addTextChangedListener(new TextWatcher() {

                            public void afterTextChanged(Editable s) {
                                try {
                                    // TODO: 10.05.2023  ГЛАВНЫЙ МЕТОД ЗАПИСИ ДАННЫХ update
                                    методЗаписьЯчейкиRxView(editTextRowКликПоДАнными,   ЗначениеДоЗаполениясОшибкой[0]);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(getApplicationContext().getClass().getName(),
                                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }

                            public void beforeTextChanged(CharSequence s, int start,
                                                          int count, int after) {
                                ЗначениеДоЗаполениясОшибкой[0] =s.toString();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                        + "\n"  + " ЗначениеДоЗаполениясОшибкой[0]"+ЗначениеДоЗаполениясОшибкой[0]);
                            }

                            public void onTextChanged(CharSequence s, int start,
                                                      int before, int count) {
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            }
                        });

                        editTextRowКликПоДАнными.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                try{
                                    // TODO: 19.10.2022  переход на метки табеля
                                    МетодПереходаНаМеткиТабеля( (EditText) v);
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(getApplicationContext().getClass().getName(),
                                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                return true;
                            }
                        });
                        // TODO: 10.05.2023 Просток CLICK

                    }

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 08.11.2022 метод КЛИК ПО ДАННЫМ
            private void МетодаScrollViewEdittext(@NonNull   EditText editTextRowКликПоДАнными  ) {
                try{
                    editTextRowКликПоДАнными.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            try{
                                EditText editText=(EditText) v;
                                if (hasFocus) {
                                    editText.requestFocus();
                                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                                }else {
                                    message.getTarget().postDelayed(() -> {
                                        imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                                    }, 2000);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getApplicationContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }




            // TODO: 21.04.2023  записб данных в ячейку
            private void методЗаписьЯчейкиRxView(@NonNull View v,@NonNull String    ЗначениеДоЗаполениясОшибкой) {
                try {
                    RxView.focusChanges(v)
                            .throttleLast(2, TimeUnit.SECONDS)
                            .doOnError(new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    throwable.printStackTrace();
                                    Log.e(getApplicationContext().getClass().getName(),
                                            "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            })
                            .doOnNext(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Throwable {

                                    Bundle bundleДанныеTag=        (Bundle)      v.getTag();
                                    String ЗначениеДняTag=       bundleДанныеTag.getString("ЗначениеДня").trim();
                                    String EditTextДАнные=       ((EditText) v).getText().toString().trim();
                                    // TODO: 06.04.2023 Принимаем Решение Если ДАные РАзные ЗАпускаем Обновление
                                    if (      !EditTextДАнные.equalsIgnoreCase(ЗначениеДняTag) && v.hasFocus()==true) {
                                        // TODO: 11.04.2023 Оперция Обновлнения ЯЧЕЕК
                                        SubClassUpdatesCELL subClassUpdateSingletabel=new SubClassUpdatesCELL(getApplicationContext());
                                        context.getMainExecutor().execute(()->{
                                            // TODO: 10.05.2023  ЗАВПИСЫАЕМ НОВЫЕ ДАННЫВЕ В БАЗУ
                                            Integer   РезультатОбновлениеЯчейки =   subClassUpdateSingletabel.МетодВалидацияЯчеек(v);
                                            // TODO: 10.05.2023
                                            if (РезультатОбновлениеЯчейки>0) {
                                                // TODO: 24.04.2023  после обновление ячейки Считаем Часы
                                                message.getTarget().post(()->{
                                                    ((EditText) v).startAnimation(animationVibr2);
                                                });

                                                message.getTarget().postDelayed(()->{
                                                    // TODO: 16.06.2023 После Сохранение CELL
                                                    // subClassSingleTabelRecycreView.      методAlterSaveCellRecyreView(v);
                                                    // subClassSingleTabelRecycreView.       методЗакрываемКлавитатуру(v);
                                                    // TODO: 17.06.2023 подсчет часов
                                                    subClassSingleTabelRecycreView.   методПослеОбновлениеЯчейкиСчитаемЧасы();
                                                    // TODO: 16.06.2023  после переполуение данныз перегрузка экрана
                                                    //subClassSingleTabelRecycreView.       методЗакрываемКлавитатуру(v);
                                                    // TODO: 20.04.2023 Данные

                                                    // subClassSingleTabelRecycreView.     методПерегрузкиRecycreView();
                                                },300);


                                                Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v +
                                                        " РезультатОбновлениеЯчейки " +РезультатОбновлениеЯчейки );
                                            }else {
                                                ((EditText) v).setBackgroundColor(Color.RED);
                                                message.getTarget().postDelayed(() -> {
                                                    ((EditText) v).setBackgroundColor(Color.WHITE);
                                                    ((EditText) v).setText(   ЗначениеДоЗаполениясОшибкой);
                                                    // TODO: 17.06.2023 КОГДА ОШИБКА ПРМ ЗАПОДЛЕН ЗНАЧЕНИМЕМ НЕ ПОРАВИЛЬНМ
                                                    Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v +
                                                            " ЗначениеДоЗаполениясОшибкой " +ЗначениеДоЗаполениясОшибкой);

                                                }, 500);
                                            }
                                            // TODO: 10.05.2023 clear
                                            v.clearFocus();
                                        });

                                        Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v +
                                                " bundleДанныеTag " +bundleДанныеTag + " EditTextДАнные " +EditTextДАнные+  "ЗначениеДняTag " +ЗначениеДняTag);
                                    } else {
                                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v +
                                                " bundleДанныеTag " +bundleДанныеTag + " EditTextДАнные " +EditTextДАнные);
                                    }
                                    Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v +
                                            " aBoolean " +aBoolean );
                                }
                            })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    return false;
                                }
                            })
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " v"+ v);
                                }
                            })
                            .subscribe(e->System.out.println( "RxView--> "+e.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void методЛовимПустоеЗначениеEditTExt(View v) {
                try{
                    String EditTextДАнныеФокус=       ((EditText) v).getText().toString().trim();
                    if (EditTextДАнныеФокус.length()==0) {
                        ((EditText) v).setHint("0");
                        ((EditText) v).setTextColor(Color.parseColor("#00ACC1"));
                        ((EditText) v).refreshDrawableState();
                        ((EditText) v).forceLayout();
                    }else{
                        ((EditText) v).setTextColor(Color.BLACK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                int КоличесвоСтрок=0;
                try {
                    КоличесвоСтрок =ДниВыходные.size();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " КоличесвоСтрок "+КоличесвоСтрок);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getApplicationContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return КоличесвоСтрок;
            }
        }

        private void методЗакрываемКлавитатуру(@NonNull View v) {
            try{
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                imm.hideSoftInputFromWindow(recycler_view_single_tabel.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 03.04.2023 перенесеный RecycreView
        private void МетодСлушательКурсора( @NonNull Cursor cursor) {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                cursor.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recycler_view_single_tabel   " + recycler_view_single_tabel);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recycler_view_single_tabel   " + recycler_view_single_tabel);
                    }
                });
                // TODO: 15.10.2022
                cursor.registerContentObserver(new ContentObserver(message.getTarget()) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recycler_view_single_tabel   " + recycler_view_single_tabel);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recycler_view_single_tabel   " + recycler_view_single_tabel);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recycler_view_single_tabel   " + recycler_view_single_tabel);
                        super.onChange(selfChange, uri);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        @SuppressLint("FragmentLiveDataObserve")
        void методWorkManagerLifecycleOwner() {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
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

                WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая)
                        .observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfos) {
                                workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                    try {
                                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.ENQUEUED) == 0 ||
                                                СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0) {
                                            long end = Calendar.getInstance().getTimeInMillis();
                                            Integer ReturnCallPublic = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWork", 0);

                                            // TODO: 17.06.2023  если прошло время нужное
                                            long РазницаВоврмени=end-startДляОбноразвовной;
                                            if (РазницаВоврмени>20000) {
                                                методПерегрузкиRecycreView();
                                            }
                                        }
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                });
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методПерегрузкиRecycreView() {
            try{
                recycler_view_single_tabel.getAdapter().notifyDataSetChanged();
                recycler_view_single_tabel.smoothScrollToPosition(0);
                ProgressBarSingleTabel.setVisibility(View.INVISIBLE);
                recycler_view_single_tabel.setClickable(true);
                recycler_view_single_tabel.setFocusable(true);
                recycler_view_single_tabel.setBackgroundColor(Color.parseColor("#FFFFFF"));
                recycler_view_single_tabel.requestLayout();
                recycler_view_single_tabel.refreshDrawableState();

                constraintLayoutsingletabel.requestLayout();
                constraintLayoutsingletabel.refreshDrawableState();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 14.04.2023  слушатель Recycreviwe
        void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            //  cursor =    new SubClassGetCursor().МетодSwipesКурсор();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursor "+cursor );
                            //TODO
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursor "+cursor );
                            //TODO
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "onItemRangeInserted ");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try {
                            Log.d(this.getClass().getName(), "     onItemRangeMoved ");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getApplicationContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void методСчитаемЧасы(@NonNull Cursor cursor ) {
            try{
                Integer ПозицуияВыбраногоСОтрудника=      cursor.getPosition();
                ПозицуияВыбраногоСОтрудника=ПозицуияВыбраногоСОтрудника+1;
                //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                Integer   ЧасыТекущегоСОтрудника = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(cursor);
                Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
                textViewчасыsimgletabel.setText("(" + ЧасыТекущегоСОтрудника + "/часы)  "
                        + ""+ ПозицуияВыбраногоСОтрудника+" из "+  cursor.getCount()+"");
                Log.d(Class_MODEL_synchronized.class.getName()," RowNumber  " + " cursor " +cursor.getPosition()
                        +"myViewHolder.getLayoutPosition()   "+myViewHolder.getLayoutPosition() + "PositionCustomer  " + PositionCustomer);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
        void методСчитаемЧасы(@NonNull Cursor cursorЧасы,@NonNull MyViewHolder holder ) {
            try{
                Integer позицияЧасы=myRecycleViewAdapter.cursor.getPosition();
                позицияЧасы=позицияЧасы+1;
                //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                Integer   ЧасыТекущегоСОтрудника = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(cursorЧасы);
                Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
                textViewчасыsimgletabel.setText("("+ ЧасыТекущегоСОтрудника + "/часы)"
                        + " "+ позицияЧасы+" из "+  myRecycleViewAdapter.cursor.getCount()+"");
                Log.d(Class_MODEL_synchronized.class.getName()," RowNumber  " + " cursor " +cursor.getPosition()
                        +"myViewHolder.getLayoutPosition()   "+holder.getLayoutPosition() + "cursorЧасы  " + cursorЧасы + " cursor  "+cursor);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
        // TODO: 08.11.2022 КЛИК ПО ФИО
        private void МетодаКликаTableRowФИО() {
            try{
                TextViewФИОПрофессия.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                        if (bundleПереходДетализацию != null) {
                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                            try{
                                TextViewФИОПрофессия.setBackgroundColor(Color.GRAY);
                                TextView TextViewФИОДляУдаление = (TextView) v;
                                Log.d(this.getClass().getName(), " v " + v.getTag() + " TextViewФИОДляУдаление.getText() " + TextViewФИОДляУдаление.getText() +
                                        "  TextViewФИОДляУдаление.getTag() " +TextViewФИОДляУдаление.getTag());
                                //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                                Bundle bundleTabelViewПрофессияФИО= (Bundle) TextViewФИОДляУдаление.getTag();
                                bundleTabelViewПрофессияФИО.putString("ФИО",  ФИО);
                                bundleTabelViewПрофессияФИО.putString("СамЗапрос","  SELECT * FROM  fio WHERE uuid=? ");
                                bundleTabelViewПрофессияФИО.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(CurrenrsSelectFio)});
                                bundleTabelViewПрофессияФИО.putString("Таблица","fio");
                                Cursor    КурсорТаблицаФИО=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleTabelViewПрофессияФИО);
                                Log.d(this.getClass().getName(), " КурсорТаблицаФИО" + КурсорТаблицаФИО);
                                if (КурсорТаблицаФИО.getCount()>0) {
                                    String ФИОИнфо= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("name"))).orElse("");
                                    String ДеньРОжденияИНФО= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("BirthDate"))).orElse("");
                                    Long СНИЛСИНфо= КурсорТаблицаФИО.getLong(КурсорТаблицаФИО.getColumnIndex("snils"));
                                    String ПрофессияИзФИо= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("prof"))).orElse("Должность");
                                    // TODO: 20.03.2023  ПОказываем Данные Для Обзора
                                    СообщениеИнформацияОСотруднике("Данные",  "ФИО: " +ФИОИнфо+
                                            "\n"+"День рождения: " +ДеньРОжденияИНФО+
                                            "\n"+"СНИЛС: " +СНИЛСИНфо+
                                            "\n" +"Должость: " + "("+bundleTabelViewПрофессияФИО.getString("Профессия").trim()+ " )");

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " КурсорТаблицаФИО "+КурсорТаблицаФИО.getCount() );
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " v "+v );
                    }
                });
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private void МетодПерегрузкаЧасыSingletabel() {
            try {
                textViewчасыsimgletabel.refreshDrawableState();
                textViewчасыsimgletabel.requestLayout();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }//TODO КОНЕЦ КЛАССА визуального оформление Recycreview





    class SubClassGetCursor{
        Cursor          cursor = null;
        String  СамЗапрос;
        String[] УсловияВыборки;
        protected Cursor МетодSwipesКурсор() {
            try{
                СамЗапрос=" SELECT  *   FROM viewtabel AS t" +
                        " WHERE t.cfo=? AND t.month_tabels  =?  AND t.year_tabels = ?  AND t.status_send !=?  AND t.fio IS NOT NULL  ORDER BY   t._id  " ;
                УсловияВыборки=new String[]{String.valueOf(DigitalNameCFO),
                        String.valueOf(  МЕсяцТабелей),
                        String.valueOf(   ГодТабелей),
                        String.valueOf(  "Удаленная") };
                //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                Bundle bundleГлавныйКурсорMultiДанныеSwipes= new Bundle();
                bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос",СамЗапрос);
                bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,УсловияВыборки);
                bundleГлавныйКурсорMultiДанныеSwipes.putString("Таблица","viewtabel");
                cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleГлавныйКурсорMultiДанныеSwipes);
                // TODO: 13.04.2023 делаем смещение по курсору
                cursor.move(PositionCustomer);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor " +cursor );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }

    }

    class SubClassChanegeSetNameProffesio{


        private void МетодЗаполняемФИОRow( @NonNull  Cursor   cursor  ) {
            try {
                // TODO: 16.04.2023  посик по ФИО
                Integer ПрофессияИзФИо = cursor.getInt(cursor.getColumnIndex("fio_prof"));
                // TODO: 16.04.2023  посик по Data_Tabels
                Integer ПрофессияИзDatatabels = cursor.getInt(cursor.getColumnIndex("dt_prof"));
                //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                Bundle bundleTabelViewПосикПрофессия= new Bundle();
                bundleTabelViewПосикПрофессия.putString("СамЗапрос","  SELECT name FROM  prof WHERE _id=? ");
                if (ПрофессияИзDatatabels>0) {
                    bundleTabelViewПосикПрофессия.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ПрофессияИзDatatabels)});
                } else {
                    bundleTabelViewПосикПрофессия.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ПрофессияИзФИо)});
                }
                bundleTabelViewПосикПрофессия.putString("Таблица","prof");
                Cursor    КурсорПрофессия=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleTabelViewПосикПрофессия);
                Log.d(this.getClass().getName(), " КурсорПрофессия" + КурсорПрофессия);
                if (КурсорПрофессия.getCount()>0) {
                    Профессия = КурсорПрофессия.getString(КурсорПрофессия.getColumnIndex("name"));
                    if ( Профессия!=null && Профессия.length()> 0) {
                        TextViewФИОПрофессия.setText(ФИО.trim()+ "\n"+"("+Профессия.trim()+")");
                    }
                }else {
                    Профессия="(Должность)";
                    TextViewФИОПрофессия.setText(ФИО.trim() + "\n"+ Профессия);
                }
                TextViewФИОПрофессия.startAnimation(animationПрофессия400) ;
                // TODO: 17.04.2023 Tag
                bundleTabelViewПосикПрофессия.putString("ФИО",ФИО);
                bundleTabelViewПосикПрофессия.putString("Профессия",Профессия);
                bundleTabelViewПосикПрофессия.putInt("ПрофессияИзDatatabels",ПрофессияИзDatatabels);
                bundleTabelViewПосикПрофессия.putInt("ПрофессияИзФИо",ПрофессияИзФИо);
                TextViewФИОПрофессия.setTag(bundleTabelViewПосикПрофессия);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor  "
                        +cursor+ "TextViewФИОПрофессия " +TextViewФИОПрофессия);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }


    //TODO Перерождения Данных recycreView Отдельный Класс
    public   class SubClassReBornDataRecyreView{
        void методПереРоденияRevireViewScroll () {
            try{
                // TODO: 15.06.2023 Scroll Left RecyreView
                ProgressBarSingleTabel.setVisibility(View.VISIBLE);
                recycler_view_single_tabel.setClickable(false);
                recycler_view_single_tabel.setFocusable(false);
                imm.hideSoftInputFromWindow(recycler_view_single_tabel.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                recycler_view_single_tabel.clearFocus();
// TODO: 16.06.2023  ПРОИЗВОДИМ САМ СВАЙП
                message.getTarget().post(()->{
                    // TODO: 16.06.2023
                    subClassSingleTabelRecycreView.      методScrollsLeftRecyreView();

                    subClassSingleTabelRecycreView.   методПослеОбновлениеЯчейкиСчитаемЧасы();

                    // TODO: 16.06.2023  после переполуение данныз перегрузка экрана
                    subClassSingleTabelRecycreView.     методПерегрузкиRecycreView();

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"PositionCustomer   " + PositionCustomer+
                            " oldScrollY ");
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

    }



}






















*/