package com.dsy.dsu.Code_ForTABEL;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
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
import android.widget.ScrollView;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.CELLUPDATE.SubClassUpdatesCELL;
import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
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
import java.util.ListIterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.functions.Consumer;


public class MainActivity_Tabel_Single_People extends AppCompatActivity  {
    private Spinner СпинерИгодИМесяц;/////спинеры для создание табеля
    private Spinner СпинерНазваниеЦФО;/////спинеры для создание табеля
    private ScrollView Scrollviewsingletabel;
    private  boolean РежимыПросмотраДанныхЭкрана;
    private ConstraintLayout constraintLayoutsingletabel; ////главный linelayuout
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
    private TextView TextViewНазваниеДанныхВТабелеФИО;

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
    private  TextView TextViewЧасовСотрудникаВТабелеСотудников;
    private   int КоличествоДнейвЗагружаемойМесяце;
    private boolean МыУжеВКодеУденияСотрудника=false;
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


    private RecyclerView recyclerView;
    private SubClassSingleTabelRecycreView. MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassSingleTabelRecycreView. MyViewHolder myViewHolder;
    private  Integer Position =0;
    private  Cursor ГлавныйALLКурсорДанныеSwipes;
    private  String FullNameCFO = "";
    private  Integer ГодТабелей = 0;
    private  String ИмесяцвИГодСразу = "";
    private  Integer МЕсяцТабелей=0;
    private  Bundle bundleИзMainActitivy_List_Tables;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            ////todo запрещает поворот экрана
            setContentView(R.layout.activity_main__tabel_four_colums);
            recyclerView =  (RecyclerView) findViewById(R.id.RecyclerViewSingleTabel);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            activity=this;
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
            constraintLayoutsingletabel = (ConstraintLayout) findViewById(R.id.constraintLayoutsingletabel);
            ProgressBarSingleTabel = (ProgressBar) findViewById(R.id.ProgressBarSingleTabel);
            Scrollviewsingletabel = (ScrollView) findViewById(R.id.scrollviewsingletabel);
            ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА

            КнопкаЛеваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеЛеваяСтрелка);
            КнопкаПраваяПередвиженияПоДанным=(Button) findViewById(R.id.imageViewВСамомТабелеТабельПраваяСтрелка);
            TextViewЧасовСотрудникаВТабелеСотудников =(TextView) findViewById(R.id.textViewJОбщееКоличествоСотрудниковВТабеле);
            imageButtonДвижениеПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonДвижениеПоСотрудникамВТАбеле);
            imageButtonНазадПоСотрудникамВТАбеле=(ImageButton) findViewById(R.id.imageButtonНазадПоСотрудникамВТАбеле);
            TextViewЧасовСотрудникаВТабелеСотудников.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            TextViewЧасовСотрудникаВТабелеСотудников.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
            TextViewЧасовСотрудникаВТабелеСотудников.setPaintFlags( (TextViewЧасовСотрудникаВТабелеСотудников.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG));
            СловоТабель=(TextView) findViewById(R.id.textView3СловоТабель);
            СловоТабель.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            ЛимитСоСмещениемДанных="0";
            КнопкаНазад=(Button) findViewById(R.id.imageViewСтрелкаВнутриТабеля);
            view2Линия=(View) findViewById(R.id.view2Линия);
            ProgressBarSingleTabel.setVisibility(View.VISIBLE);
            animationПрофессия400 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_rowsingletabel);
            animationПрофессия300 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row2);
            animationVibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable);
            animationVibr2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);
            animationRows = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row_scroll_for_singletabel);
            animationRich = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
            animationLesft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1
            // TODO: 29.03.2023  Метод Какая марка телфона из за этого загрудаем вид
            методGETДанныеИзДругихАктивити();
            МетодGetmessage();

         LifecycleOwner lifecycleOwner=this;
         LifecycleOwner  lifecycleOwnerОбщая=this;


                    SubClassSingleTabelRecycreView subClassSingleTabelRecycreView=
                            new SubClassSingleTabelRecycreView(lifecycleOwner,lifecycleOwnerОбщая,activity);

                    subClassSingleTabelRecycreView.МетодИнициализацииRecycreView();

                 Cursor cursor=   subClassSingleTabelRecycreView.МетодЗаполениеRecycleView( );

                    subClassSingleTabelRecycreView. методДляSimpeCallbacks(cursor);

                     МетодПриНАжатииНаКнопкуBACK();

            // TODO: 14.04.2023 доделываем single tabel
                  методСпинерМесяцы();//запускаем метод создание табеля

                МетодСпинерДепартамент( );

            МетодОтработкиПоднятияКлавиатуры();
        
            // TODO: 12.04.2023 Вторастипенные методы
 /*           message.getTarget().postDelayed(()->{
              //  subClassSingleTabelRecycreView. МетодСлушательКурсора(cursor);
             //   subClassSingleTabelRecycreView.  методWorkManagerLifecycleOwner();
               // subClassSingleTabelRecycreView.МетодСлушательRecycleView();
            },2000);*/

               /*     message.getTarget().postDelayed(()->{
                        subClassSingleTabelRecycreView.  МетодСлушательRecycleView();
                        subClassSingleTabelRecycreView.  МетодСлушательКурсора();
                        subClassSingleTabelRecycreView.  методWorkMAnagerReycreVIew();
                    },2000);*/


            // TODO: 30.03.2023 Новые Методы
      /*      МетодSwipeALLКурсор();
            МетодВыбораВнешнегоВидаИзВидаТелефона();

           // МетодОбновлениеПрофесиии();

            МетодПриИзмениеДанныхВБазеМенемВнешнийВидТабеляObserver();

            методДанныеИзДругихАктивити();
            // TODO: 30.03.2023 CЛУШАТЕЛИ ДВА ДАННЫХ
            МетодGetmessage();

            //TODO #2
            МетодПриНАжатииНаКнопкуBACK();
            //TODO #4
            методСпинерМесяцы(FullNameCFO );//запускаем метод создание табеля
            //TODO #5
            ВсеСтрокиТабеля=   МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(МЕсяцТабелей);   ////раньше стояло 0
            // TODO: 29.03.2023
            ///TODO   #7  запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
            МетодСлушательСвайпов();
            ///TODO   #8 запускаем метод отработки поднятие клавиатур
            МетодОтработкиПоднятияКлавиатуры();
            // TODO: 29.01.2022 ПРИ ИЗМЕНЕНИ МЕНЯЕМ ДАННЫЕ В БАЗЕ В ТАБЕЛЕ
            МетодАнализДанныхSwipes( );
            // TODO: 03.04.2023 Создание  Дней Недели Вс, Пон, Ср,Черт
            методПолучениеДнейНеделиЧерезКалендарь();*/
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





    private void методGETДанныеИзДругихАктивити() {
        try {
            Intent ИнтентMainActivity_List_Peoples =  getIntent();
              bundleИзMainActitivy_List_Tables=ИнтентMainActivity_List_Peoples.getExtras();
            // TODO: 10.04.2023
            if (bundleИзMainActitivy_List_Tables!=null) {
                MainParentUUID=    bundleИзMainActitivy_List_Tables.getLong("MainParentUUID", 0l);
                Position=    bundleИзMainActitivy_List_Tables.getInt("Position", 0);
                ГодТабелей=  bundleИзMainActitivy_List_Tables.getInt("ГодТабелей", 0);
                МЕсяцТабелей=  bundleИзMainActitivy_List_Tables.getInt("МЕсяцТабелей",0);
                DigitalNameCFO=   bundleИзMainActitivy_List_Tables.getInt("DigitalNameCFO", 0);
                FullNameCFO=  bundleИзMainActitivy_List_Tables.getString("FullNameCFO", "");
                ИмесяцвИГодСразу= bundleИзMainActitivy_List_Tables.getString("ИмесяцвИГодСразу", "");
                CurrenrsСhildUUID= bundleИзMainActitivy_List_Tables.getLong("CurrenrsСhildUUID", 0l);
                ФИО= bundleИзMainActitivy_List_Tables.getString("ФИО", "");
                CurrenrsSelectFio= bundleИзMainActitivy_List_Tables.getLong("CurrenrsSelectFio", 0l);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                    + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                    " Position " +Position+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодОбновлениеПрофесиии() {
        КонтейнерКудаЗагружаетьсяФИО.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsСлушательИнформацияОСотрудника=new Class_GRUD_SQL_Operations(getApplicationContext());
                try{
                    TextViewНазваниеДанныхВТабелеФИО.setBackgroundColor(Color.GRAY);
                    Long ФИО=0l;
                    if (МыУжеВКодеУденияСотрудника==false) {
                        TextView ФИОДляУдаление = (TextView) v;
                        Log.d(this.getClass().getName(), " v " + v.getTag() + " ФИОДляУдаление.getText() " + ФИОДляУдаление.getText() +
                                "  ФИОДляУдаление.getTag() " +ФИОДляУдаление.getTag());
                        //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                        Bundle bundleTabelView= (Bundle) ФИОДляУдаление.getTag();
                        Long UUIDИзTabelView=bundleTabelView.getLong("UUID",0l);
                        Long FIOИзTabelView=bundleTabelView.getLong("FIO",0l);
                        Bundle bundleФио=new Bundle();
                        bundleФио.putString("СамЗапрос","  SELECT * FROM  fio WHERE uuid=? ");
                        bundleФио.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(FIOИзTabelView)});
                        bundleФио.putString("Таблица","fio");
                        Cursor    КурсорТаблицаФИО=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleФио);
                        Log.d(this.getClass().getName(), " КурсорТаблицаФИО" + КурсорТаблицаФИО);
                        if (КурсорТаблицаФИО.getCount()>0) {
                            String ФИОИнфо= КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("name"));
                            String ДеньРОжденияИНФО= КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("BirthDate"));
                            Long СНИЛСИНфо= КурсорТаблицаФИО.getLong(КурсорТаблицаФИО.getColumnIndex("snils"));
                            String ПрофессияИзФИо= КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("prof"));
                            // TODO: 20.03.2023  ПОказываем Данные Для Обзора
                            СообщениеИнформацияОСотруднике("Данные",  "ФИО: " +ФИОИнфо+
                                    "\n"+"День рождения: " +ДеньРОжденияИНФО+
                                    "\n"+"СНИЛС: " +СНИЛСИНфо+
                                    "\n" +"Должость: " + "( "+Профессия.trim()+ " )");

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " КурсорТаблицаФИО "+КурсорТаблицаФИО.getCount() );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }});

    }

    private Cursor МетодSwipesКурсор() {
        Cursor          cursor = null;
        try{
            //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
            Bundle bundleГлавныйКурсорMultiДанныеSwipes= new Bundle();
            bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос"," SELECT  *   FROM viewtabel AS t" +
                    " WHERE t.cfo=? AND t.month_tabels  =?  AND t.year_tabels = ?  AND t.status_send !=?  AND t.fio IS NOT NULL  ORDER BY   t.date_update  " );
            bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,
                    new String[]{String.valueOf(DigitalNameCFO),
                            String.valueOf(  МЕсяцТабелей),
                            String.valueOf(   ГодТабелей),
                            String.valueOf(  "Удаленная") });
            bundleГлавныйКурсорMultiДанныеSwipes.putString("Таблица","viewtabel");
            cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleГлавныйКурсорMultiДанныеSwipes);
            // TODO: 13.04.2023 делаем смещение по курсору
            cursor.moveToPosition(Position);
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  ГлавныйALLКурсорДанныеSwipes " + ГлавныйALLКурсорДанныеSwipes );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObservers(null);
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


    //TODO заполения табеля из базы через элемент TableLauy

    private Integer МетодКоличествоСотрудниковВАтбеле(String  МесяцТабеляФинал  ) {
        Integer КоличествоСтотрубниковВТАбеле=0;
        try{
            // TODO: 09.04.2023  курсор самим создаваемых табеляПОСИК ДАННЫХ ЧЕРЕЗ UUID
            Bundle bundleКоличествоСотрудниковВтАбеле=new Bundle();
            bundleКоличествоСотрудниковВтАбеле.putString("СамЗапрос","  SELECT * FROM  viewtabel WHERE status_send!=? AND uuid=?" +
                    "  AND month_tabels IS NOT NULL " +
                    " AND year_tabels IS NOT NULL ");
            bundleКоличествоСотрудниковВтАбеле.putStringArray("УсловияВыборки" ,new String[]{String.valueOf("Удаленная"),String.valueOf(CurrenrsСhildUUID)});
            bundleКоличествоСотрудниковВтАбеле.putString("Таблица","viewtabel");
           Cursor Курсор_КоличествоСотрудниковВтАбеле=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleКоличествоСотрудниковВтАбеле);
            КоличествоСтотрубниковВТАбеле=     Курсор_КоличествоСотрудниковВтАбеле.getCount();
            Log.d(this.getClass().getName(), "Курсор_КоличествоСотрудниковВтАбеле "+Курсор_КоличествоСотрудниковВтАбеле  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return КоличествоСтотрубниковВТАбеле;
    }




    private void МетодЗаполениеЭкранНАзваниеФИоИПрофесиии() {
        try{
            TextViewНазваниеДанныхВТабелеФИО.setText("");
            TextViewНазваниеДанныхВТабелеФИО.setText(ФИОТекущегоСотрудника.trim() + "\n"+ "( "+Профессия.trim()+ " )"); ///строго имя
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

    void МетодСуммаЧасовВТабеле(@NonNull  Cursor sqLiteCursor) {
        try{
            //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
         Integer   ЧасыТекущегоСОтрудника = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(sqLiteCursor);
            Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
            TextViewЧасовСотрудникаВТабелеСотудников.setText(" (" + " " + ЧасыТекущегоСОтрудника + "/часы)  "+" из "+ recyclerView.getAdapter().getItemCount()+"");
            Log.d(Class_MODEL_synchronized.class.getName()," RowNumber  " + " sqLiteCursor " +sqLiteCursor.getPosition()  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

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
                        СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("rus"));
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


    /**
     *
     */
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
                                TextViewНазваниеДанныхВТабелеФИО.setBackgroundColor(Color.WHITE);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " TextViewНазваниеДанныхВТабелеФИО  " + TextViewНазваниеДанныхВТабелеФИО);
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
                                TextViewНазваниеДанныхВТабелеФИО.setBackgroundColor(Color.WHITE);
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
                                        " TextViewНазваниеДанныхВТабелеФИО  " + TextViewНазваниеДанныхВТабелеФИО);
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
                    " Position " + Position + " ИмесяцвИГодСразу " + ИмесяцвИГодСразу+ " editTextЯчейка " +editTextЯчейка);
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
                " Position " +Position+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу + " bundleИзMainActitivy_List_Tables " +bundleИзMainActitivy_List_Tables);
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
                    " Position " +Position+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
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
                                                  /*  UUIDТекущегоВыбраногоСотрудника=      ГлавныйALLКурсорДанныеSwipes.getLong(ГлавныйALLКурсорДанныеSwipes.getColumnIndex("uuid"));
                                                    bundle.putLong("UUIDТекущегоВыбраногоСотрудника",UUIDТекущегоВыбраногоСотрудника);*/
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
                                                        Long РодительскийUUDТаблицыТабель =   bundle.getLong("CurrenrsСhildUUID",0l);
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

                                    // TODO: 30.03.2023 Курсор ALL Date
                                     Integer ПолощениеДАнных=            ГлавныйALLКурсорДанныеSwipes.getPosition();
                                    //МетодSwipeALLКурсор();
                                         ГлавныйALLКурсорДанныеSwipes.moveToPosition(ПолощениеДАнных);
                                    // TODO: 29.03.2023 Методы ПОсле усМешного Смены Професиии
                                    МетодПереопределенияНазваниеПрофесии();
                                    МетодПерегрузкаВидаПрофесии();
                                }else {
                                    Toast.makeText(MainActivity_Tabel_Single_People.this, "Профессия не сменилась !!! ", Toast.LENGTH_SHORT).show();
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
                                if (cursorДанные.getCount()>0 && cursorДанные!=null) {
                                    simpleCursorAdapterЦФО.swapCursor(cursorДанные);
                                    listViewДляНовыйПосик.setSelection(0);
                                    alertDialogНовыйПосикКнопкаЗакрыть.setText("Сохранить");
                                }
                                if (cursorДанные.getCount()==0 && cursorДанные!=null) {
                                    alertDialogНовыйПосикКнопкаЗакрыть.setText("Закрыть");
                                    searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                    message.getTarget().postDelayed(() -> {
                                        searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                    }, 500);
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
            Integer ПровйдерСменаПрофесии=0;
            try{
                String ТаблицаОбработки="data_tabels";
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " searchViewДляНовогоПоиска "+searchViewДляНовогоПоиска+ " ТаблицаОбработки "+ТаблицаОбработки);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                Bundle bundleСменаПрофессии= (Bundle)  searchViewДляНовогоПоиска.getTag();
                ContentResolver contentResolver=context.getContentResolver();
                Bundle bundleОбновлениеПрофесии=  contentResolver.call(uri,ТаблицаОбработки,ТаблицаОбработки,bundleСменаПрофессии);
                ПровйдерСменаПрофесии=  bundleОбновлениеПрофесии.getInt(    "СтатусОбновления",0);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РЕЗУЛЬТАТ ПровйдерСменаПрофесии  " +  ПровйдерСменаПрофесии);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ПровйдерСменаПрофесии;
        }



    }

    private void МетодПереопределенияНазваниеПрофесии() {
        try {

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


    private void МетодПерегрузкаВидаПрофесии() {
        try {
            TextViewНазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия400);
            TextViewНазваниеДанныхВТабелеФИО.refreshDrawableState();
            TextViewНазваниеДанныхВТабелеФИО.forceLayout();
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

    class SubClassSingleTabelRecycreView  {
         private     Cursor cursor;
        private   LifecycleOwner lifecycleOwner;
        private   LifecycleOwner  lifecycleOwnerОбщая;

        private LinkedHashMap< String,String> ДниВыходные=new LinkedHashMap<>();

        public SubClassSingleTabelRecycreView(@NonNull  LifecycleOwner lifecycleOwner,
                                              @NonNull  LifecycleOwner  lifecycleOwnerОбщая,
                                              @NonNull Activity activity) {
            this.lifecycleOwner=lifecycleOwner;
            this.lifecycleOwnerОбщая=lifecycleOwnerОбщая;
        }

        private void МетодИнициализацииRecycreView() {
            try{
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                DividerItemDecoration dividerItemDecoration=
                        new DividerItemDecoration(activity,StaggeredGridLayoutManager.HORIZONTAL);
                dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider_for_single_tabel));
               recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
               recyclerView.setHasFixedSize(true);
                recyclerView.startAnimation(animationПрофессия400);
                staggeredGridLayoutManager.    invalidateSpanAssignments();
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
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

        // TODO: 11.04.2023 метод SWIPES лева и право
        private void методДляSimpeCallbacks(@NonNull   Cursor cursor) {
            try{
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
                        message.getTarget().post(()->{
                    Integer posio= myViewHolder.getAbsoluteAdapterPosition();
                        if (Position>0) {
                            Position=Position-1;
                            cursor.moveToPosition(Position);
                        }else {
                            cursor.moveToFirst();
                        }
                        recyclerView.getAdapter().notifyDataSetChanged();
                        myRecycleViewAdapter.notifyDataSetChanged();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"Position   " + Position+ " cursor " +cursor+
                                    " posio " +posio);
                        });
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"Position   " + Position+ " cursor " +cursor);
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
                            message.getTarget().post(()->{
                                Integer posio= myViewHolder.getAbsoluteAdapterPosition();
                                if (Position<cursor.getCount()-1) {
                                    Position=Position+1;
                                    cursor.moveToPosition(Position);
                                }else {
                                    cursor.moveToLast();
                                }
                                recyclerView.getAdapter().notifyDataSetChanged();
                                myRecycleViewAdapter.notifyDataSetChanged();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"Position   " + Position+ " cursor " +cursor+
                                        " posio " +posio);
                            });


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recyclerView   " + recyclerView+ " cursor " +cursor);

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
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // move item in `fromPos` to `toPos` in adapter.
                        return true;// true if moved, false otherwise
                    }

                };
                ItemTouchHelper.SimpleCallback simpleItemTouchCallbackAll = new ItemTouchHelper.SimpleCallback(10,
                        ItemTouchHelper.ACTION_STATE_IDLE ) {

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
                        Integer posio= myViewHolder.getAbsoluteAdapterPosition();
                        // remove item from adapter
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recyclerView   " + recyclerView+ " cursor " +cursor);
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
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // move item in `fromPos` to `toPos` in adapter.
                        return true;// true if moved, false otherwise
                    }

                };
                ItemTouchHelper itemTouchHelperLEFT = new ItemTouchHelper(simpleItemTouchCallbackLEFT);
                itemTouchHelperLEFT.attachToRecyclerView(recyclerView);
                ItemTouchHelper itemTouchHelperRIGHT = new ItemTouchHelper(simpleItemTouchCallbackRIGHT);
                itemTouchHelperRIGHT.attachToRecyclerView(recyclerView);
                ItemTouchHelper itemTouchHelperAll = new ItemTouchHelper(simpleItemTouchCallbackAll);
                itemTouchHelperAll.attachToRecyclerView(recyclerView);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recyclerView   " + recyclerView);
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


        // TODO: 04.03.2022 прозвомжность Заполения RecycleView
        Cursor МетодЗаполениеRecycleView( ) {
            Cursor cursor = null;
            try {
            cursor =    МетодSwipesКурсор();
                // remove item from adapter
                myRecycleViewAdapter = new  MyRecycleViewAdapter(cursor );
                recyclerView.setAdapter(myRecycleViewAdapter);
                recyclerView.requestLayout();
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
            return  cursor;
        }


        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {
            private TableLayout TableLayoutSingleTabel;
            private   TableRow rowФИО;
            private CopyOnWriteArrayList<TableRow> rowName =new CopyOnWriteArrayList<>();
            private CopyOnWriteArrayList<TableRow> rowData=new CopyOnWriteArrayList<>();
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
                    TableLayoutSingleTabel = itemView.findViewById(R.id.TableLayoutSingleTabel);
                    rowФИО = (TableRow)  TableLayoutSingleTabel.findViewById(R.id.TableRowsFIO);
                    // TODO: 04.04.2023   NAME
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow1Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow2Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow2Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow3Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow4Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow5Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow6Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow7Name));
                    rowName.add(TableLayoutSingleTabel.findViewById(R.id.TableRow8Name));
                    // TODO: 04.04.2023   Data
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData1Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData2Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData3Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData4Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData5Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData6Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData7Row));
                    rowData.add(TableLayoutSingleTabel.findViewById(R.id.TableData8Row));
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
                                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                                        // TODO: 07.04.2023 переопрелделния Вида Табеля
                                        МетодПерегрузкаSingletabel();
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


            private void МетодПерегрузкаSingletabel() {
                try {
                    message.getTarget().postDelayed(()->{
                        ProgressBarSingleTabel.setVisibility(View.INVISIBLE);
                    },500);
                        recyclerView.requestLayout();
                        recyclerView.refreshDrawableState();
                        Scrollviewsingletabel.pageScroll(View.FOCUS_UP);
                        Scrollviewsingletabel.refreshDrawableState();
                        constraintLayoutsingletabel.refreshDrawableState();
                        constraintLayoutsingletabel.requestLayout();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " constraintLayoutsingletabel "+ constraintLayoutsingletabel);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
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
                        if (Build.BRAND.toString().contains("Samsung") ||Build.BRAND.toString().contains("Galaxy")
                                || Build.BRAND.toString().contains("samsung") ||Build.BRAND.toString().contains("galaxy") ) {
                            viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_grid_for_tables_four_columns_samsung, parent, false);
                        }  else {
                            viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm, parent, false);

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
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                
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

            private void МетодПерегурзкиВнешнегоВида(@NonNull  MyViewHolder holder) {
                try{
                    recyclerView.requestLayout();
                    recyclerView.forceLayout();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " holder.getLayoutPosition() " +holder.getLayoutPosition()
                            +  " holder.getAbsoluteAdapterPosition() " +holder.getAbsoluteAdapterPosition());
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

            private void МетодАнимации( MyViewHolder holder) {
                try {
                    //   holder.cardViewМатериалРодительная.startAnimation(animation);
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
                    // TODO: 04.04.2023  ФИО 
                        МетодЗаполняемФИОRow(holder.rowФИО);
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
            private void МетодЗаполняемФИОRow( @NonNull  TableRow tableRowФио) {
                try {
                    messageRows.getTarget().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView textViewФИо = (TextView) tableRowФио.findViewById(R.id.RowКонтейнерКудаЗагружаетьсяФИО);
                            String ФИОСодержимое =                Optional.ofNullable(textViewФИо.getHint()).map(Objects::toString).orElse("");
                            textViewФИо.startAnimation(animationПрофессия400) ;
                            textViewФИо.setVisibility(View.VISIBLE);
                            textViewФИо.setText("Новая Должность !!! ");

                        }
                    },150);
                        // TODO: 04.04.20223 КЛИК ПО ДАННЫМ
                        МетодаКликаTableRowФИО(tableRowФио);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursor  " +cursor+ "tableRowФио " +tableRowФио);
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
                        ListIterator<TableRow> listIterator = holder.rowData.listIterator();
                        while (listIterator.hasNext()) {
                            // TODO: 06.04.2023
                            TableRow tableRowДАнные = listIterator.next();
                            for (int ИндексСтрочкиДней = 0; ИндексСтрочкиДней < tableRowДАнные.getChildCount(); ИндексСтрочкиДней++) {
                                // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                                EditText editTextRowКликПоДАнными = (EditText) tableRowДАнные.getChildAt(ИндексСтрочкиДней);
                                String ДнейСодержимое =            Optional.ofNullable(editTextRowКликПоДАнными.getHint()).map(Objects::toString).orElse("");
                                // TODO: 06.04.2023  НАЗВАНИЕ ROW
                                if (ДнейСодержимое != null) {
                                    // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                                    методЗаполениеСодеримомRowData(editTextRowКликПоДАнными, cursor, ДнейСодержимое);
                                    // TODO: 05.04.2023 Вешаем на Ячекку ДАнных Слушатель
                                    МетодаКликаПоtableRow(editTextRowКликПоДАнными, cursor, holder);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " editTextRowКликПоДАнными " + editTextRowКликПоДАнными + " ДнейСодержимое " + ДнейСодержимое);
                                }
                            }
                        }
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

            private void МетодЗаполняеШабкаTableRow(@NonNull Cursor cursor,
                                                 @NonNull  MyViewHolder holder) {
                try {
                        ListIterator<TableRow> listIterator = holder.rowName.listIterator();
                        while (listIterator.hasNext()) {
                            // TODO: 06.04.2023
                            TableRow tableRowДАнные = listIterator.next();
                            for (int ИндексСтрочкиДней = 0; ИндексСтрочкиДней < tableRowДАнные.getChildCount(); ИндексСтрочкиДней++) {
                                // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                                TextView viewtextRowКликПоШабка = (TextView) tableRowДАнные.getChildAt(ИндексСтрочкиДней);
                                String ДнейНазвание = Optional.ofNullable(viewtextRowКликПоШабка.getHint()).map(Objects::toString).orElse("");
                                // TODO: 06.04.2023  НАЗВАНИЕ ROW
                                if (ДнейНазвание != null) {
                                    // TODO: 06.04.2023 Названия
                                    методЗаполениеНазванияRowData(viewtextRowКликПоШабка, ДнейНазвание);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " editTextRowКликПоШабка " + viewtextRowКликПоШабка + " ДнейНазвание " + ДнейНазвание);
                                }
                            }
                        }
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
                        String День = Optional.ofNullable(cursor.getString(cursor.getColumnIndex(НазваниеДляДень))).orElse("0");
                        Long uuid = Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l);
                        Bundle dataRowData = new Bundle();
                        dataRowData.putString("ЗначениеДня", День);
                        dataRowData.putLong("uuid", uuid);
                        dataRowData.putString("День", НазваниеДляДень);
                    // TODO: 13.04.2023  дополнительные
                    dataRowData.putLong("MainParentUUID", MainParentUUID);
                    dataRowData.putInt("Position", Position);
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
            private void методЗаполениеНазванияRowData(@NonNull  TextView TextViewRowКликПоНазваниям,String s) {
                try {
                    // TODO: 11.04.2023 Ставим Дни
                    TextViewRowКликПоНазваниям.setText( ДниВыходные.get(s.trim()));
                    TextViewRowКликПоНазваниям.setVisibility(View.VISIBLE);
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " s.trim() " +s.trim() + "  ДниВыходные.get(s.trim()) " + ДниВыходные.get(s.trim()));
                    
                    TextViewRowКликПоНазваниям.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        @Override
                        public void onViewAttachedToWindow(View v) {
                            // TODO: 19.10.202
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " ДниВыходные " +ДниВыходные);
                            // TODO: 11.04.2023  оформление вида
                            v.startAnimation(animationПрофессия300);
                            // TODO: 11.04.2023 меняем Цвет и Убираем *** если празничные


    if(       ((TextView)v).getText().toString().matches("(.*)Вс(.*)")  || ((TextView)v).getText().toString().matches("(.*)Сб(.*)")) {
        ((TextView)v).setTextColor(Color.parseColor("#DC143C"));
    }else {

        if ( ((TextView)v).getText().toString().matches("(.*)###(.*)")){
            String УдаляемДляПразничныхДней=     ((TextView)v).getText().toString();
            УдаляемДляПразничныхДней=           УдаляемДляПразничныхДней.replaceAll("###","");
            ((TextView)v).setText(УдаляемДляПразничныхДней);
            ((TextView)v).setTextColor(Color.parseColor("#9C112D"));
        }  else {
            ((TextView)v).setTextColor(Color.parseColor("#008080"));
        }
        }

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " TextViewRowКликПоНазваниям " +TextViewRowКликПоНазваниям);
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

            // TODO: 08.11.2022 метод КЛИК ПО ДАННЫМ
            private void МетодаКликаПоtableRow(@NonNull   EditText editTextRowКликПоДАнными, @NonNull Cursor cursor,@NonNull  MyViewHolder holder) {
                try{
                 //EditText editTextD1=   tableRowКликПоДАнными.findViewById(R.id.v1);
                        if (editTextRowКликПоДАнными!=null) {
                            // TODO: 19.10.2022  ЛОНГ КЛИК
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            editTextRowКликПоДАнными.addTextChangedListener(new TextWatcher() {

                                public void afterTextChanged(Editable s) {

                                    методЛовимПустоеЗначениеEditTExt(editTextRowКликПоДАнными);
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " s " +s);
                                }

                                public void beforeTextChanged(CharSequence s, int start,
                                                              int count, int after) {
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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

                            // TODO: 05.04.2023 ПРОСТОЙ КЛИК
                            editTextRowКликПоДАнными.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View v, boolean hasFocus) {
                                    // TODO: 19.10.2022
                                    RxView.focusChanges(v)
                                            .throttleLast(250, TimeUnit.MILLISECONDS)
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
                                            .subscribe(new io.reactivex.rxjava3.functions.Consumer<Boolean>() {
                                                @Override
                                                public void accept(Boolean aBoolean) throws Throwable {
                                                    if(aBoolean==false){
                                          Bundle bundleДанныеTag=        (Bundle)      v.getTag();
                                          String ЗначениеДняTag=       bundleДанныеTag.getString("ЗначениеДня").trim();
                                        String EditTextДАнные=       ((EditText) v).getText().toString().trim();
                   // TODO: 06.04.2023 Принимаем Решение Если ДАные РАзные ЗАпускаем Обновление
                    if (      !EditTextДАнные.equalsIgnoreCase(ЗначениеДняTag)) {
                        // TODO: 11.04.2023 Оперция Обновлнения ЯЧЕЕК
                        SubClassUpdatesCELL subClassUpdateSingletabel=new SubClassUpdatesCELL(getApplicationContext());

                     Integer РезультатОбновлениеЯчейки=   subClassUpdateSingletabel.МетодВалидацияЯчеек(v);
                     if (РезультатОбновлениеЯчейки>0){
                         message.getTarget().postDelayed(()->{
                         ((EditText) v).startAnimation(animationVibr2);
                         },150);
                     }
                        Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" hasFocus " +hasFocus + " v"+v+
                                " bundleДанныеTag " +bundleДанныеTag + " EditTextДАнные " +EditTextДАнные+  "ЗначениеДняTag " +ЗначениеДняTag+
                                " РезультатОбновлениеЯчейки " +РезультатОбновлениеЯчейки);
                        
                        
                    } else {
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" hasFocus " +hasFocus + " v"+v+
                                " bundleДанныеTag " +bundleДанныеTag + " EditTextДАнные " +EditTextДАнные);
                    }
                                                    }else{

                                                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" hasFocus " +hasFocus + " v"+v);
                                                    }

                                                }
                                            });
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }

                            });

                            // TODO: 19.10.2022  ПРОСТОЙ КЛИК
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
            // TODO: 08.11.2022 КЛИК ПО ФИО
            private void МетодаКликаTableRowФИО(TableRow tableRowКликПоДАнными) {
                try{
                        TextView editTextRowКликПоФио=(TextView) tableRowКликПоДАнными.findViewById(R.id.RowКонтейнерКудаЗагружаетьсяФИО);
                        if (editTextRowКликПоФио!=null) {
                            editTextRowКликПоФио.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                                    if (bundleПереходДетализацию != null) {
                                    }
                                    Toast.makeText(activity, "onClick МетодаКликаTableRowФИО ", Toast.LENGTH_SHORT).show();
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " v "+v );
                                }
                            });
                            editTextRowКликПоФио.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                                    if (bundleПереходДетализацию != null) {
                                    }
                                    Toast.makeText(activity, " onLongClick МетодаКликаTableRowФИО ", Toast.LENGTH_SHORT).show();
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " v "+v );
                                    return false;
                                }
                            });
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
            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022
                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
                return super.getItemId(position);
            }
            @Override
            public int getItemCount() {
                int КоличесвоСтрок = 1;
                try {
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

        // TODO: 03.04.2023 перенесеный RecycreView
        private void МетодСлушательКурсора(@NonNull Cursor cursor) {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                    cursor.registerDataSetObserver(new DataSetObserver() {
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
                    cursor.registerContentObserver(new ContentObserver(message.getTarget()) {
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
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
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
                            Log.d(this.getClass().getName(), "onItemRangeChanged ");
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
        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        @SuppressLint("FragmentLiveDataObserve")
        void методWorkManagerLifecycleOwner() {
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

                WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                            try {
                                if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                                    Integer     ReturnCallSingle = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                    if (ReturnCallSingle>0) {
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        recyclerView.requestLayout();
                                        recyclerView.refreshDrawableState();
                                    }
                                    WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизациОдноразовая);
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
                WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая)
                        .observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfos) {
                                workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                    try {
                                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                            long end = Calendar.getInstance().getTimeInMillis();
                                            Integer ReturnCallPublic = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWork", 0);
                                            if (ReturnCallPublic>0) {
                                                recyclerView.getAdapter().notifyDataSetChanged();
                                                recyclerView.requestLayout();
                                                recyclerView.refreshDrawableState();
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
    }//TODO КОНЕЦ КЛАССА визуального оформление Recycreview

}






















