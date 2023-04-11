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
import android.database.sqlite.SQLiteCursor;
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
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

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
import org.json.JSONException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import javax.crypto.NoSuchPaddingException;


public class MainActivity_Tabel_Single_People extends AppCompatActivity  {
    private Spinner СпинерТАбельМЕсяцФинал;/////спинеры для создание табеля
    private Spinner СпинерТАбельДепартаментФинал;/////спинеры для создание табеля
    private ScrollView ScrollСамогоТабеля;
    private  boolean РежимыПросмотраДанныхЭкрана;
    private LinearLayout ГлавныйКонтейнерТабель; ////главный linelayuout
    private  Activity activity;
    private ConstraintLayout ГлавныйВерхнийКонтейнер;
    private ProgressDialog progressDialogДляУдаления;
    private    String ФИОТекущегоСотрудника;
    private    String Профессия;
    private    Integer ПолученаяПрофесииdata_tabels;
    private    Integer ПолученаяПрофесииFio;
    private ImageButton imageButtonДвижениеПоСотрудникамВТАбеле,imageButtonНазадПоСотрудникамВТАбеле;
    private  Integer МЕсяцТабелей=0;

    private  Integer DigitalNameCFO=0;
    private  Configuration config;
    private  ArrayList<String> МассивДляВыбораСпинераДаты= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  ArrayList<String> МассивДляВыбораВСпинерДепартамент= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  String КонтентСпинераНаАктивтиТабель= "";
    private  String FullNameCFO = "";
    private  String ИмесяцвИГодСразу = "";
    private   TextView НазваниеДанныхВТабелеСНИЛС;
    private TextView TextViewНазваниеДанныхВТабелеФИО;

    private   String НазваниеТабеля= "";
    private  String НазваниеЗагруженногТАбеля= "";
    private  Boolean    СтаттусТабеля= false;
    private   String ДробимДляТабеляГод,ДробимДляТебеляМесяц;
    private  View ГлавныйКонтентТабеляИнфлейтер;
    private Map< Integer,String> ХЭШНазваниеДнейНедели = Collections.synchronizedMap(new LinkedHashMap<>());

    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  String МесяцДляЗагрузкиТабелей= "";
    private  Integer ГодТабелей = 0;
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
    private      Message message;
    private      Message messageRows;
    private Animation animationПрофессия;

    private Animation animationПрофессия300;
    private Animation animationRows;
    private Cursor ГлавныйALLКурсорДанныеSwipes;

    private Cursor  ГлавныйКурсорSingleДанные;
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
    String ЯчейкаДляЗаписиМетки;

    String ЯчейкаСодержимоеДляЗаписиМетки;


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
            СпинерТАбельМЕсяцФинал = (Spinner) findViewById(R.id.СпинерТабельМесяц);
            СпинерТАбельДепартаментФинал = (Spinner) findViewById(R.id.СпинерТабельДепратамент);
            ГлавныйКонтейнерТабель = (LinearLayout) findViewById(R.id.ГлавныйКонтейнерТабель);
            ProgressBarSingleTabel = (ProgressBar) findViewById(R.id.ProgressBarSingleTabel);

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
            animationПрофессия = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row);
            animationПрофессия300 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row2);
            animationVibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable);
            animationVibr2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);
            animationRows = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row_scroll_for_singletabel);
            animationRich = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
            animationLesft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1
            // TODO: 29.03.2023  Метод Какая марка телфона из за этого загрудаем вид
            методGETДанныеИзДругихАктивити();
            МетодGetmessage();

            МетодSwipesКурсор();


         LifecycleOwner lifecycleOwner=this;
         LifecycleOwner  lifecycleOwnerОбщая=this;


                    SubClassSingleTabelRecycreView subClassSingleTabelRecycreView=
                            new SubClassSingleTabelRecycreView(lifecycleOwner,lifecycleOwnerОбщая,activity);

                    subClassSingleTabelRecycreView.МетодИнициализацииRecycreView();

                    subClassSingleTabelRecycreView.МетодЗаполениеRecycleView(ГлавныйКурсорSingleДанные);

                  subClassSingleTabelRecycreView. методДляSimpeCallbacks();



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
            Bundle bundleИзMainActitivy_List_Tables=ИнтентMainActivity_List_Peoples.getExtras();
            // TODO: 10.04.2023
            Position=    bundleИзMainActitivy_List_Tables.getInt("Position", 0);
            ГодТабелей=  bundleИзMainActitivy_List_Tables.getInt("ГодТабелей", 0);
            МЕсяцТабелей=  bundleИзMainActitivy_List_Tables.getInt("МЕсяцТабелей",0);
            DigitalNameCFO=   bundleИзMainActitivy_List_Tables.getInt("DigitalNameCFO", 0);
            FullNameCFO=  bundleИзMainActitivy_List_Tables.getString("FullNameCFO", "");
            ИмесяцвИГодСразу= bundleИзMainActitivy_List_Tables.getString("ИмесяцвИГодСразу", "");
            CurrenrsСhildUUID= bundleИзMainActitivy_List_Tables.getLong("CurrenrsСhildUUID", 0l);
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







    private void МетодВыбораВнешнегоВидаИзВидаТелефона() {
        try{
            LayoutInflater МеханизмЗагрузкиОдногЛайАутавДругой = getLayoutInflater();
            //todo ВЫБИРАЕМ ВЗАВИСИСМОСТИ КАКОЙ ЭКРАН БУДЕТ ЗАГРУЖАТЬСЯ ПОЛЬЗОВАТЕЛЮ В ОДИНОЧНОМ тАБЕЛЕ
            if (Build.BRAND.toString().contains("Samsung") ||Build.BRAND.toString().contains("Galaxy")
                    || Build.BRAND.toString().contains("samsung") ||Build.BRAND.toString().contains("galaxy") ) {
                Log.d(this.getClass().getName(), "Внешний вид САМСУНГА  "  +Build.BRAND.toString() );
                ГлавныйКонтентТабеляИнфлейтер =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_samsung,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = ГлавныйКонтентТабеляИнфлейтер.getLayoutParams();

                // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ НА HTC
            } else if (Build.BRAND.toString().contains("HTC") ){
                Log.d(this.getClass().getName(), "Внешний вид HTC "  +Build.BRAND.toString() );
                ГлавныйКонтентТабеляИнфлейтер =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm_htc,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла =
                        ГлавныйКонтентТабеляИнфлейтер.getLayoutParams();
                // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ ВСЕ ОСТАЛЬНЫЕ ТЕЛЕФОНЫ
            } else {
                Log.d(this.getClass().getName(), "Внешний вид ДЛЯ ВСЕХ ОСТАЛЬНЫХ "  +Build.BRAND.toString() );
                ГлавныйКонтентТабеляИнфлейтер =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = ГлавныйКонтентТабеляИнфлейтер.getLayoutParams();
            }
            HorizontalScrollViewВТабелеОдинСотрудник =(HorizontalScrollView)     ГлавныйКонтентТабеляИнфлейтер.findViewById(R.id.ГоризонтльнаяПрокруткаВнутриСамТабель);


         //   КонтейнерКудаЗагружаетьсяФИО =(TextView)     ГлавныйКонтентТабеляИнфлейтер.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);

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

    private void МетодSwipesКурсор() {
        try{
            //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
            Bundle bundleГлавныйКурсорMultiДанныеSwipes= new Bundle();
           /* bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос","  SELECT * FROM viewtabel WHERE cfo=? " +
                    "AND month_tabels  =?  AND year_tabels = ?  AND status_send !=?  AND fio IS NOT NULL  ORDER BY uuid " );*/
            bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос"," SELECT  *   FROM viewtabel AS t" +
                    " WHERE t.cfo=? AND t.month_tabels  =?  AND t.year_tabels = ?  AND t.status_send !=?  AND t.fio IS NOT NULL  ORDER BY   t._id  " );
           // bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ЦифровоеИмяНовгоТабеля), String.valueOf(МесяцТабеля),String.valueOf(ГодДляТабелей),"Удаленная" });
            bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,
                    new String[]{String.valueOf(428),
                            String.valueOf(3),
                            String.valueOf(2023),"Удаленная" });
            bundleГлавныйКурсорMultiДанныеSwipes.putString("Таблица","viewtabel");
            ГлавныйALLКурсорДанныеSwipes =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleГлавныйКурсорMultiДанныеSwipes);

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
                        методСпинерМесяцы(ФИОТекущегоСотрудника);
                    }else{
                        Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                " ФИОДляТабеляНаАктивти " + ФИОТекущегоСотрудника);
                        методСпинерМесяцы(FullNameCFO );
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
    // TODO: 22.12.2021
    private void МетодПриИзмениеДанныхВБазеМенемВнешнийВидТабеляObserver() {
        try{
            LifecycleOwner lifecycleOwner =this ;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            // TODO: 29.01.2022
            // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая)
                    .observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                        @Override
                        public void onChanged(List<WorkInfo> workInfos) {
                            workInfos.stream().filter(statee->statee.getState().compareTo(WorkInfo.State.SUCCEEDED)==0).forEachOrdered(new Consumer<WorkInfo>() {
                                @Override
                                public void accept(WorkInfo workInfo) {
                                    Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  workInfo " + workInfos.get(0).getState().name());
                                    Long CallBaskОтWorkManagerОдноразового = workInfos.get(0).getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                    Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " + CallBaskОтWorkManagerОдноразового);
                                    onResume();

                                }
                            });}
                    });


            // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыОбщейСинхронизацииДляЗадачи)
                    .observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                        @Override
                        public void onChanged(List<WorkInfo> workInfos) {
                            workInfos.stream().filter(statee->statee.getState().compareTo(WorkInfo.State.ENQUEUED)==0).forEachOrdered(new Consumer<WorkInfo>() {
                                @Override
                                public void accept(WorkInfo workInfo) {
                                    Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  workInfo " + workInfos.get(0).getState().name());
                                    Long CallBaskОтWorkManagerОдноразового = workInfos.get(0).getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                    Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " + CallBaskОтWorkManagerОдноразового);
                                    onResume();
                                }
                            });}
                    });



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
// TODO: 29.01.2022




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
    private void методСпинерМесяцы(@NonNull  String ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника) {
        try{
            МассивДляВыбораСпинераДаты.clear();
            МассивДляВыбораСпинераДаты=new ArrayList<>();
            МассивДляВыбораСпинераДаты.add(МЕсяцТабелей.toString());
            Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораСпинераДаты.toString());
            ArrayAdapter<String> АдаптерДляСпинераТабельФинал = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, МассивДляВыбораСпинераДаты);
            АдаптерДляСпинераТабельФинал.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            СпинерТАбельМЕсяцФинал.setAdapter(АдаптерДляСпинераТабельФинал);
            СпинерТАбельМЕсяцФинал.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //////меняем цвет спинера
                    try {
                        if (view!=null) {
                            for (int i=0;i<СпинерТАбельМЕсяцФинал.getCount();i++) {
                                ////
                                ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                                ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                                ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                                //////
                                //////todo полученое выбраное ЗНАЧЕНИЕ ИЗ СПИНЕРПА ПЕРЕДАЕМ ДАЛЬШЕ
                                КонтентСпинераНаАктивтиТабель= String.valueOf(((TextView) parent.getChildAt(0)).getText()); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                                Log.d(this.getClass().getName(), " КонтентСпинераНаАктивтиТабель  " +КонтентСпинераНаАктивтиТабель);
                            }
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

            МетодСпинерДепартамент(ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника, АдаптерДляСпинераТабельФинал);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодСпинерДепартамент(@NonNull String ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника, ArrayAdapter<String> АдаптерДляСпинераТабельФинал) {
        // TODO: 20.11.2022 второй спинер департамент
        try {
            МассивДляВыбораВСпинерДепартамент.clear();
            МассивДляВыбораВСпинерДепартамент=new ArrayList<>();
            МассивДляВыбораВСпинерДепартамент.add(ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника);
            Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерТабельФинал " +МассивДляВыбораВСпинерДепартамент.toString());
            ArrayAdapter<String> АдаптерДляСпинераТабельФиналДепартамент = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,МассивДляВыбораВСпинерДепартамент);
            АдаптерДляСпинераТабельФинал.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            СпинерТАбельДепартаментФинал.setAdapter(АдаптерДляСпинераТабельФиналДепартамент);
            СпинерТАбельДепартаментФинал.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //////меняем цвет спинера
                    try {
                        if (view!=null) {
                            for (int i=0;i<СпинерТАбельДепартаментФинал.getCount();i++) {
                                ///////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#708090"));
                                ((TextView) parent.getChildAt(0)).setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                                ((TextView) parent.getChildAt(0)).setPaintFlags( ((TextView) parent.getChildAt(0)).getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                                ((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel);
                                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                                ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_VERTICAL |Gravity.CENTER_HORIZONTAL);
                                ((TextView) parent.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                                КонтентСпинераНаАктивтиТабель= String.valueOf(((TextView) parent.getChildAt(0)).getText()); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                                Log.d(this.getClass().getName(), " КонтентСпинераНаАктивтиТабель  " +КонтентСпинераНаАктивтиТабель);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    СпинерТАбельДепартаментФинал.setClickable(false);
                    СпинерТАбельДепартаментФинал.setFocusable(false);
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




    private void МетодПолучениеФИОиПрофессия( @NonNull  Cursor sqLiteCursor) {
        try{
            Log.d(this.getClass().getName(), "ПолученыйUUIDФИОСледующий " + ПолученыйUUIDФИОСледующий);
            Class_GRUD_SQL_Operations classGrudSqlOperations= new Class_GRUD_SQL_Operations(getApplicationContext());
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT name,prof  FROM fio  WHERE  uuid = '" + ПолученыйUUIDФИОСледующий + "' ;");
            ///////
             Cursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) classGrudSqlOperations.
                    new GetаFreeData(getApplicationContext()).getfreedata(classGrudSqlOperations.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО "  +Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getCount());
            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.moveToFirst();

            ФИОТекущегоСотрудника = Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО.getString(0);
            Log.d(  this.getClass().getName(), "ФИОСледующий " +"uuid"+ ФИОТекущегоСотрудника);
            int ИндексПрофесииdata_tabels = sqLiteCursor.getColumnIndex("dt_prof");//name
            ПолученаяПрофесииdata_tabels=     sqLiteCursor.getInt(ИндексПрофесииdata_tabels);////  String ФИОСледующий
            Log.d(this.getClass().getName(), " ПолученаяПрофесииdata_tabels " + ПолученаяПрофесииdata_tabels);
            // TODO: 23.03.2023 по таблиуе ФИо
            int ИндексПрофесииFio = sqLiteCursor.getColumnIndex("fio_prof");//name
            ПолученаяПрофесииFio=     sqLiteCursor.getInt(ИндексПрофесииFio);////  String ФИОСледующий
            Log.d(this.getClass().getName(), " ПолученаяПрофесииFio " + ПолученаяПрофесииFio);
            // TODO: 24.03.2023 ВЫЧИСЛЯКЕМ ТЕКУЩАЮ ПРОФЕСИЮ
            if (ПолученаяПрофесииdata_tabels>0) {
                Профессия=МетодgetПрофесия(ПолученаяПрофесииdata_tabels);
            } else {
                if (ПолученаяПрофесииFio>0) {
                    Профессия=МетодgetПрофесия(ПолученаяПрофесииFio);
                    Log.d(this.getClass().getName(), " ПолученаяПрофесииdata_tabels " + ПолученаяПрофесииdata_tabels
                            + " ПолученаяПрофесииFio " +ПолученаяПрофесииFio);
                }else {
                    Профессия= "Должность";
                    Log.d(this.getClass().getName(), " ПолученаяПрофесииdata_tabels " + ПолученаяПрофесииdata_tabels
                            + " ПолученаяПрофесииFio " +ПолученаяПрофесииFio);
                }
            }
            Log.d(this.getClass().getName(), "Профессия" + Профессия);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    private void МетодПергрузкиДизайнаЭкрана() {
    try{
          message.getTarget().postDelayed(()->{
              ГлавныйКонтейнерТабель.setFocusable(true);
              ГлавныйКонтейнерТабель.setClickable(true);
          },1000);
        ScrollСамогоТабеля.requestLayout();
        ScrollСамогоТабеля.pageScroll(View.FOCUS_UP);
        ScrollСамогоТабеля.refreshDrawableState();
        ГлавныйКонтейнерТабель.requestLayout();
        ГлавныйКонтейнерТабель.forceLayout();
        ГлавныйКонтейнерТабель.refreshDrawableState();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
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
            TextViewЧасовСотрудникаВТабелеСотудников.setText(" (" + " " + ЧасыТекущегоСОтрудника + "/часы)  "+" из "+ ГлавныйALLКурсорДанныеSwipes.getCount()+"");
            Log.d(Class_MODEL_synchronized.class.getName()," RowNumber  " + " sqLiteCursor " +sqLiteCursor.getPosition()  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    ///TODO Когда в  КУРСОРЕ ВООБЩЕ НЕТ ДАННЫХ НА УКАЗАННЫЙ МЕСЯЦ
    private void МетодКогдаНетЗаписейВКурсоре() {
        try{
        /////TODO создавние строк из linerlouyto в табеле сколько сткром в базе данных андройда столлько на активити строк
        LayoutInflater МеханизмЗагрузкиОдногЛайАутавДругой = getLayoutInflater();
        ГлавныйКонтентТабеляИнфлейтер = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns,
                ГлавныйКонтейнерТабель, false);
        TextViewНазваниеДанныхВТабелеФИО = ГлавныйКонтентТабеляИнфлейтер.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);
        /////TODO ТАБЕЛЬ ФИО создаем textview названия дней понелельник вторик среда четеварг
        ///////// todo фио
        ///TODO ЕСЛИ ИМЕНИ НЕТ ПО НА АКТИВИТИ ПОКАЗЫВАЕМТ ЧТО ТАБЕЛЬ ПУСТОЙ
        TextViewНазваниеДанныхВТабелеФИО.setTextColor(Color.RED);
        TextViewНазваниеДанныхВТабелеФИО.setText("*Табель  пустой (заполните)*"); ///строго имя
        Log.d(this.getClass().getName(), " ФИО " + "*Пустой табель (заполните)*");


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    /////View/////TODO метод uuid id названеи стоблика
    public  void МетодПередНаМеткиТАбеля(View v) {

try{
    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    ///todo  конец метода удаления третий обработчки нажатия
    //////TODO локального обнвлени с Активити в базу
    private Long МетодЛокальногоОбновлениеЧерезКликвТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении ,String ПолучениеЗначениеСтолбикUUID)
            throws InterruptedException, ExecutionException,
            NoSuchMethodException, TimeoutException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, JSONException {
        String таблицаДляЛокальногоОбонвления="data_tabels";
        Long РезультатЛокальногоОбновления = null;
        try {
            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +
                    " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении  " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении);
            ///TOdo универсальное значение или uuid или id КАКОЕ НЕ NULL
            String УниверсальныйИнлификаторЛокальногоОбновлениеиIDиUUID = "";
////TODO сам метод запуска обновления
            if (КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.size() > 0) {///если в контенер заполнилься то начинаем обновление
                //TODO принимеем решение через чего БУДЕМ ОБНОВЛЯТЬ ЧЕРЕЗ ID ИЛИ UUID, ПРИОРИТЕТ ID ,НО ЕЛСИ ЕГО НЕТ ТО UUID
                /////TODO  локальное ОБНОВЛЕНИЕ ЧЕРЕЗ UUID ПОЛЕ
                if (ПолучениеЗначениеСтолбикUUID != null) {
                    Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);
                    boolean РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет =
                            МетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет(ПолучениеЗначениеСтолбикUUID, "uuid");
                    Log.d(this.getClass().getName(), " РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет " + РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет +
                            " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " + КонтейнерЗаполненияДаннымиПриЛокальномОбновлении);

                    ///todo ПРОИЗВОДИМ ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ ,ЕСЛИ В СТАТУСЕ ОТПРАВИК НЕ СТОИТЬ СТАТУС УДАЛЕННЫЙ
                    //////todo производим локальное обновлени когда статус false --, это значит статуса Уданненый нет
                    try {
                        // TODO: 22.03.2021  вставка только если false запись не Имеет статус Удаленная
                        if (РузультатМетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет == false) {
                            ///TODO ТОЛЬКО ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ НА ТАБЕЛЕ В АКТИВИТИ
                            РезультатЛокальногоОбновления = new Class_MODEL_synchronized(getApplicationContext()).
                                    МетодЛокальноеОбновлениеВТабеле(КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                            ПолучениеЗначениеСтолбикUUID,
                                            getApplicationContext(),таблицаДляЛокальногоОбонвления);
                            Log.d(this.getClass().getName(), " РезультатЛокальногоОбновления " + РезультатЛокальногоОбновления);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                /// //TODO РЕЗУЛЬТАТ ФИНАЛЬНЫЙ ПОСЛЕ ЛОКАЛЬНОГО ОБНОВЛЕНИЕ ЕСОИ ВСЕ ХОРОШО ТО ОБНУЛЯЕМ ПЕРЕМЕННЫЕ И НЕТ СООБЩЕНИЙ
                Log.d(this.getClass().getName(), " РезультатЛокальногоОбновления" + РезультатЛокальногоОбновления);
                //TODO ЕЛИ РЕЗУЛЬТАТ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ СРАБОТАЛ ТО И ПОКАЗЫВАЕМ ИЗМЕННЕЯ ЧАСЫ
                if (РезультатЛокальногоОбновления > 0 ) {
                    ///todo после заполнения табелями обнуляем куросры
                    ////////// TODO ПОДСЧЁТ ЧАСОВ ПОСЛЕ ЛОКАЛЬНОГО ОБНОВЛЕНИЯ
                 Integer   Часысотрудника = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(ГлавныйALLКурсорДанныеSwipes);
                    Log.d(this.getClass().getName(), "  ОбщееКоличествоЧасовСотрудникаВТабелеСотудников " + TextViewЧасовСотрудникаВТабелеСотудников);
                    МетодСуммаЧасовВТабеле(ГлавныйALLКурсорДанныеSwipes);
                    // TODO: 07.05.2021 обнуляем UUID после созданеия подтчета часов
                    Log.d(Class_MODEL_synchronized.class.getName()," Часысотрудника  "+ Часысотрудника);
                    МетодСуммаЧасовВТабеле(ГлавныйALLКурсорДанныеSwipes);
                    // TODO: 25.09.2021
                    Log.d(Class_MODEL_synchronized.class.getName()," Часысотрудника  "+ Часысотрудника);
                    /////TODO ОБНУЛЯЕМ ЗНАЧЕНИЕ ID AND UUID ЧТОБЫ НЕ БЫЛО ПОВТОРОНОГО ОБНОЛЕНИЕ НЕ СВОЕГО ХОЗЯИНА UUID
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                    ScrollСамогоТабеля.requestLayout();
                    ГлавныйКонтейнерТабель.requestLayout();
                } else {
                    Toast.makeText(getApplicationContext(), " Ошибка обновление ячейки не произошло !!!!! " , Toast.LENGTH_SHORT).show();
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатЛокальногоОбновления;

    }




    //todo метод орпреедгния статутс записи обнолвемой удленнва я или нет
    private boolean МетодОпределяемуЗаписиКакойСтатусУдаленныйИлиНет( String ПолучениеЗначениеСтолбикUUID,String ИндификаторСтатус) {

        boolean Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=false;
        //////
        SQLiteCursor Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет = null;
        ///////
        Class_GRUD_SQL_Operations class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет=new Class_GRUD_SQL_Operations(getApplicationContext());

        try{
            Log.d(this.getClass().getName(), "  МетодЗапускаЛокальногоОбновлениеЧерезКликвТабеле : ");


//////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            ///
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
            ///////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","status_send");
            //
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика",""+ИндификаторСтатус+"=? AND status_send=?");
            ///"_id > ?   AND _id< ?"
            //////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",ПолучениеЗначениеСтолбикUUID);
            ///
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
            ///
/*                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

            ////TODO другие поля

            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
            ////
            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
            ////
            class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update desc");
            ////
            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
            ////

            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=null;

            ////

            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsОпределяемуЗаписиКакойСтатусУдаленныйИлиНет. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                    ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "  +Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет);










            /////////////TODO результат
            if (Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет.getCount() > 0) {
                /////
                // TODO: 08.09.2021  полчжительный

                Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=true;

            }else{

                // TODO: 08.09.2021  отрицательный

                Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет=false;
            }

///TODO ЗАПУСКАЕМ  ПуллПамяти
            Курсор_УзнаемСтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет.close();


            //////////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        /////
        return Результат_СтатусЗаписиДляЛокальногоОбновленияУдаленноеИлиНет;
    }





    //TODO метод получени месяа для записи в одну колонку

    protected int  МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
//
        Calendar calendar =null;
        //////////
        try{
            ///
            String[] ДелимМЕсяцИгод =ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");

            System.out.println( " " + ДелимМЕсяцИгод [0] + " " +ДелимМЕсяцИгод [1]);

            SimpleDateFormat formatмесяц = new SimpleDateFormat("MMMM");

            SimpleDateFormat formatгод = new SimpleDateFormat("yyyy");

            Date date = formatмесяц.parse(ДелимМЕсяцИгод [0]);

            calendar = Calendar.getInstance(new Locale("ru"));

            calendar.setTime(date);

            System.out.println(calendar.get(Calendar.YEAR));

            System.out.println(calendar.get(Calendar.MONTH)+1);

            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

            System.out.println(new SimpleDateFormat("MMMM").format(calendar.getTime()));


            //////////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 08.09.2021   resultat
        return   calendar.get(Calendar.MONTH)+1;
    }




    //TODO метод который обрабатывает дни
    String МетодСопоставлениеДнейТАбеляСКалендарем(String ДатаДляКадендаря){

        Calendar c = GregorianCalendar.getInstance();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyy");

        return null;
    }











    // TODO: 03.04.2023 Создание  Дней Недели Вс, Пон, Ср,Черт
    private  void методПолучениеДнейНеделиЧерезКалендарь() throws ParseException {
        try {

            Integer ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодТабелей,    МЕсяцТабелей );
            SimpleDateFormat СозданияВычисляемВыходные=null;
            ///////TODO сам цикл который заполняет месяцами
            for (int ИндексДней=1;ИндексДней<ПолученоеКоличествоДнейНаКонкретныйМЕсяц+1;ИндексДней++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }else {
                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }
                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (ГодТабелей+"-"+МЕсяцТабелей+"-"+ИндексДней );
                String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней );
                Integer РезультатДатыДляКонктетногоТабеляТольокЧисло = Integer.parseInt(new SimpleDateFormat("dd", new Locale("ru")).format(ДатаПосикаВыходныеДней ));
                Log.d(this.getClass().getName()," РезультатДатыДляКонктетногоТабеляТолькоЗанвание " +РезультатДатыДляКонктетногоТабеляТолькоЗанвание+
                        " РезультатДатыДляКонктетногоТабеляТольокЧисло " +РезультатДатыДляКонктетногоТабеляТольокЧисло);
                StringBuffer БуферРезультатСокращенноВставкиВТабель=new StringBuffer();
                // TODO: 29.01.2022 append date for tabels
                БуферРезультатСокращенноВставкиВТабель.append(РезультатДатыДляКонктетногоТабеляТолькоЗанвание).append(" ,").append(РезультатДатыДляКонктетногоТабеляТольокЧисло);
                Log.d(this.getClass().getName()," РезультатСокращенноВставкиВТабель " +БуферРезультатСокращенноВставкиВТабель.toString());
                //TODO ДНИ НЕДЕЛИ С ЗАГЛАВНОЙ БУКВЫ
                String  СокращенныйДниМесяцаВТабеле=   БуферРезультатСокращенноВставкиВТабель.substring(0,1).toUpperCase()
                        +БуферРезультатСокращенноВставкиВТабель.substring(1,БуферРезультатСокращенноВставкиВТабель.length()).toLowerCase();
                ///////
                Log.d(this.getClass().getName()," ИндексДней " +ИндексДней+
                        " СокращенныйДниМесяцаВТабеле " +СокращенныйДниМесяцаВТабеле);
                //TODO ЗАПОЛЕНЯЕМ ПОЛУЧЕННЫЕ ДНИ НЕДЕЛИ ЧТОБЫ ВСТАВИИТЬ ЕГО В АКТИВТИ
                ХЭШНазваниеДнейНедели.put(ИндексДней, СокращенныйДниМесяцаВТабеле);
                Log.d(this.getClass().getName()," ХЭШНазваниеДнейНедели " +ХЭШНазваниеДнейНедели.toString()+
                        " ХЭШНазваниеДнейНедели.size()  " +ХЭШНазваниеДнейНедели.size());
            }
            ///todo вычисляем сколько дней в месяце текущем
            КоличествоДнейвЗагружаемойМесяце=МетодПолучениеКоличествоДнейвЗагружаемомМесяце(МЕсяцТабелей ,ГодТабелей);
            Log.d(this.getClass().getName()," КоличествоДнейвЗагружаемойМесяце " +КоличествоДнейвЗагружаемойМесяце);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
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






////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГОД НОВЫЙ




    private int МетодПоказатьМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        calendar.setTime(date );
        int month = calendar.get(Calendar.MONTH) + 1;
        return   month;
    }

    private int  МетодПоказатьГодДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        calendar.setTime(date );
        int year = calendar.get(Calendar.YEAR);
        return   year ;
    }

    private int  МетодПолучениниеМесяцДляКурсора(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
        int month = calendar.get(Calendar.MONTH) + 1;
        return   month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеГодДляКурсора(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return   year ;
    }





    public String ГлавнаяДатаИВремяДляТабеля() {
        Date Дата = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
        //dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-03:00"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Log.d(this.getClass().getName(), " ГЛАВНАЯ ДАТА ПРОГРАММЫ ДСУ-1 : " + dateFormat.format(Дата));
        return dateFormat.format(Дата);
    }
    ///todo ОПРЕДЕЛЯЕМ КОЛИЧЕСТВО ДНЕЙ ЗАГРУЖАЕМОЙ МЕСЯЦ
    protected int МетодПолучениеКоличествоДнейвЗагружаемомМесяце(int Месяц ,int месяцДляСокращенно) {
        int daysInMonth = 0;
        try{
            int КонктетныйМесяцВВидеЦифры;
            Date  date;
            String МесяцПлюсНоль=String.valueOf(Месяц);
            if (МесяцПлюсНоль.length() == 1){
                МесяцПлюсНоль="0"+МесяцПлюсНоль;
            }
            date = new SimpleDateFormat("MMyyyy",new Locale("rus")).parse(МесяцПлюсНоль+ месяцДляСокращенно);
            System.out.println(" date " +date);
            Calendar  cal = Calendar.getInstance();
            cal.setTime(date);
            КонктетныйМесяцВВидеЦифры=cal.get(Calendar.MONTH);
            System.out.println(cal.get(Calendar.MONTH));
            daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " daysInMonth  " + daysInMonth );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  daysInMonth;
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
                                new SubClassNewSearchAlertDialogНовыйПосик().МетодСообщениеНовыйПоиска(activity,КурсорТаблицаПрофесии ,message,"prof", CurrenrsСhildUUID);
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

    private void МетодПереходаНаМеткиТабеля( ) {
        try{
            Intent IntentПереХодНаМеткиТабеля  =new Intent();
             IntentПереХодНаМеткиТабеля.setClass(getApplicationContext(), MainActivity_Metki_Tabel.class);
            // TODO: 10.04.2023  переход ИЗ MAINaCTITyTabelSingle Peolpe
            методBACKFromMainActivitySingleTabel(IntentПереХодНаМеткиТабеля);
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
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void методBACKFromMainActivitySingleTabel(Intent intentBack) {
        try {
        Bundle bundleИзMainActitivy_List_Tables= intentBack.getExtras();
        // TODO: 10.04.2023  ОТПРАВЛЯЕММ ПЕРЕМЕННЫЕ
        bundleИзMainActitivy_List_Tables.putLong("CurrenrsСhildUUID", CurrenrsСhildUUID);
        bundleИзMainActitivy_List_Tables.putInt("Position", Position);
        bundleИзMainActitivy_List_Tables.putInt("ГодТабелей", ГодТабелей);
        bundleИзMainActitivy_List_Tables.putInt("МЕсяцТабелей",МЕсяцТабелей);
        bundleИзMainActitivy_List_Tables.putInt("DigitalNameCFO", DigitalNameCFO);
        bundleИзMainActitivy_List_Tables.getString("FullNameCFO", FullNameCFO);
        bundleИзMainActitivy_List_Tables.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу.trim());
        bundleИзMainActitivy_List_Tables.putString("ЯчейкаДляЗаписиМетки", ЯчейкаДляЗаписиМетки);;
        bundleИзMainActitivy_List_Tables.putString("ЯчейкаСодержимоеДляЗаписиМетки", ЯчейкаСодержимоеДляЗаписиМетки);;
        startActivity(intentBack);
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
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСотрудникаИзТабеля(String ШабкаДиалога,  String СообщениеДиалога,boolean Статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
        int Значек;
        if (Статус){
            Значек  =R.drawable.icon_dsu1_tabel_info;
        }else{
            Значек  =R.drawable.icon_dsu1_delete_customer;
        }
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(Значек)
                .show();
/////////кнопка
        final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");
                ///TODO запускаем возврат на предыдущее активывти после успешного удаление сотрудника
                методПереходаMainActivity_List_Peoples();


                //todo
            }


        });
    }
////todo конец фильаного сообщения о удалени самого табеля


    void методПереходаMainActivity_List_Peoples() {
        try{
            ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
            Intent Интент_ПереходаMainActivity_List_Peoples = new Intent();
            Интент_ПереходаMainActivity_List_Peoples.setClass(context, MainActivity_List_Peoples.class);
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










































    private void МетодСвайпВпередПоДАнным() {
        try {
            if (ГлавныйALLКурсорДанныеSwipes.getCount() >0) {
                    if (ГлавныйALLКурсорДанныеSwipes.getPosition()< ГлавныйALLКурсорДанныеSwipes.getCount()-1) {
                        ГлавныйALLКурсорДанныеSwipes.moveToNext();
                    }else {
                        ГлавныйALLКурсорДанныеSwipes.moveToFirst();
                    }
                ScrollСамогоТабеля.startAnimation(animationRich);
                TextViewНазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйALLКурсорДанныеSwipes.getCount() "+ ГлавныйALLКурсорДанныеSwipes.getCount()+
                    "ГлавныйALLКурсорДанныеSwipes.getPosition()  " + ГлавныйALLКурсорДанныеSwipes.getPosition() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());


        }
    }

    void МетодСвайпНазаПоДанным() {
        try{
            if (ГлавныйALLКурсорДанныеSwipes.getCount() >0) {
                if (ГлавныйALLКурсорДанныеSwipes.getPosition() == 0) {
                    ГлавныйALLКурсорДанныеSwipes.moveToLast();
                }else{
                    ГлавныйALLКурсорДанныеSwipes.moveToPrevious();
                }
                ScrollСамогоТабеля.startAnimation(animationLesft);
                TextViewНазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);

            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйALLКурсорДанныеSwipes.getCount() "+ ГлавныйALLКурсорДанныеSwipes.getCount()+
                     "ГлавныйALLКурсорДанныеSwipes.getPosition()  " + ГлавныйALLКурсорДанныеSwipes.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());


        }
    }



    // TODO: 06.05.2021  созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов   созадение тача для свайпов


//TODO SUB CLASS с ГЛАВНЫМ МЕПТОДОМ ОФОРМЛЕНИЯ ТАБЕЛЯ


    private void МетодГлавныеДанныеLayoutInflater(@NonNull Map<Integer, String> ХЭШНазваниеДнейНедели
            , @NonNull View КонтентТабеляИнфлейтер
            , @NonNull  Cursor sqLiteCursor) {
        /////todo ПЕРВАЯ СТРОКА НАЗВАНИЯ
        String НазваниеДней= "";
        Bundle bundleДляОбновление=null;
          String regex = "Сб ,(.*)";
          String regex1 = "Вс ,(.*)";
        try{
            final Integer[] ИндексНазвания = {0};
            final Integer[] ИндексЗначения = {0};
            // TODO: 31.03.2023 НАЗВАНИЕ ROW 1
      message.getTarget().post(()->{
          IntStream intStreamOneName =IntStream.of(
                  R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень2,R.id.ПерваяСтрочкаНазваниеДень3,R.id.ПерваяСтрочкаНазваниеДень4,
                  R.id.ПерваяСтрочкаНазваниеДень5,R.id.ПерваяСтрочкаНазваниеДень6,R.id.ПерваяСтрочкаНазваниеДень7,R.id.ПерваяСтрочкаНазваниеДень8,
                  R.id.ПерваяСтрочкаНазваниеДень9,R.id.ПерваяСтрочкаНазваниеДень10,R.id.ПерваяСтрочкаНазваниеДень11,R.id.ПерваяСтрочкаНазваниеДень12,
                  R.id.ПерваяСтрочкаНазваниеДень13,R.id.ПерваяСтрочкаНазваниеДень14,R.id.ПерваяСтрочкаНазваниеДень15,R.id.ПерваяСтрочкаНазваниеДень16);
          ИндексНазвания[0]=
                  МетодНазваниеЯчеек(ХЭШНазваниеДнейНедели, КонтентТабеляИнфлейтер, regex, regex1,intStreamOneName, ИндексНазвания[0]);
          // TODO: 31.03.2023 НАЗВАНИЕ ROW 2
          IntStream intStreamOneValues=IntStream.of(
                  R.id.ВтораяСтрочкаНазваниеДень17, R.id.ВтораяСтрочкаНазваниеДень18, R.id.ВтораяСтрочкаНазваниеДень19, R.id.ВтораяСтрочкаНазваниеДень20
                  , R.id.ВтораяСтрочкаНазваниеДень21, R.id.ВтораяСтрочкаНазваниеДень22, R.id.ВтораяСтрочкаНазваниеДень23, R.id.ВтораяСтрочкаНазваниеДень24
                  , R.id.ВтораяСтрочкаНазваниеДень25, R.id.ВтораяСтрочкаНазваниеДень26, R.id.ВтораяСтрочкаНазваниеДень27, R.id.ВтораяСтрочкаНазваниеДень28
                  , R.id.ВтораяСтрочкаНазваниеДень29 , R.id.ВтораяСтрочкаНазваниеДень30, R.id.ВтораяСтрочкаНазваниеДень31);
          ИндексНазвания[0]=
                  МетодНазваниеЯчеек(ХЭШНазваниеДнейНедели, КонтентТабеляИнфлейтер, regex, regex1,intStreamOneValues, ИндексНазвания[0]);
      });
            // TODO: 31.03.2023 ДАННЫЕ ROW 1
            message.getTarget().post(()->{
          IntStream intStreamTwoName=IntStream.of(
                  R.id.ПерваяСтрочкаДанныеДень1,  R.id.ПерваяСтрочкаДанныеДень2,  R.id.ПерваяСтрочкаДанныеДень3,  R.id.ПерваяСтрочкаДанныеДень4,
                  R.id.ПерваяСтрочкаДанныеДень5,  R.id.ПерваяСтрочкаДанныеДень6,  R.id.ПерваяСтрочкаДанныеДень7,  R.id.ПерваяСтрочкаДанныеДень8,
                  R.id.ПерваяСтрочкаДанныеДень9, R.id.ПерваяСтрочкаДанныеДень10,  R.id.ПерваяСтрочкаДанныеДень11,  R.id.ПерваяСтрочкаДанныеДень12,  R.id.ПерваяСтрочкаДанныеДень13,
                  R.id.ПерваяСтрочкаДанныеДень14,  R.id.ПерваяСтрочкаДанныеДень15,  R.id.ПерваяСтрочкаДанныеДень16);
          ИндексЗначения[0] =
                  МетодДанныеЯчеек(ХЭШНазваниеДнейНедели, КонтентТабеляИнфлейтер, sqLiteCursor,intStreamTwoName, ИндексЗначения[0]);
            // TODO: 31.03.2023 ДАННЫЕ ROW 2
                IntStream intStreamTwoValues=IntStream.of(
                        R.id.ВтораяСтрочкаДанныеДень17,R.id.ВтораяСтрочкаДанныеДень18,R.id.ВтораяСтрочкаДанныеДень19,R.id.ВтораяСтрочкаДанныеДень20,
                        R.id.ВтораяСтрочкаДанныеДень21,R.id.ВтораяСтрочкаДанныеДень22,R.id.ВтораяСтрочкаДанныеДень23,R.id.ВтораяСтрочкаДанныеДень24,
                        R.id.ВтораяСтрочкаДанныеДень25,R.id.ВтораяСтрочкаДанныеДень26,R.id.ВтораяСтрочкаДанныеДень27,R.id.ВтораяСтрочкаДанныеДень28
                ,R.id.ВтораяСтрочкаДанныеДень29,R.id.ВтораяСтрочкаДанныеДень30,R.id.ВтораяСтрочкаДанныеДень31);
                ИндексЗначения[0] =
                        МетодДанныеЯчеек(ХЭШНазваниеДнейНедели, КонтентТабеляИнфлейтер, sqLiteCursor,intStreamTwoValues, ИндексЗначения[0]);
            });
            // TODO: 31.03.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "  ИндексНазвания[0] "+  ИндексНазвания[0]+ "  ИндексЗначения[0] " +    ИндексЗначения[0]);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private Integer МетодДанныеЯчеек(@NonNull Map<Integer, String> ХЭШНазваниеДнейНедели,
                                     @NonNull View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла,
                                     @NonNull  Cursor sqLiteCursor,
                                     @NonNull    IntStream intStreamПерваяСтрочкаСамиДанные,
                                     @NonNull      Integer ИндексИтерации) {
        try{
        Bundle bundleДляОбновление;
        String НазваниеДней;
        EditText  СамиДанныеТабеля = null;
// TODO: 31.03.2023 Данные Перво1 СТрочки
        PrimitiveIterator.OfInt iteratorIteratorПерваяСтрочкаСамиДанные= intStreamПерваяСтрочкаСамиДанные.iterator();
        bundleДляОбновление=new Bundle();
        while (iteratorIteratorПерваяСтрочкаСамиДанные.hasNext()) {
            // TODO: 31.03.2023 поднимаем версию
            ИндексИтерации++;
            /////TODO НАЧАЛО САМИ ДАННЫЕ ПЕРВОЙ СТРОКИ
            Integer ТекущийЭлементДанные=     iteratorIteratorПерваяСтрочкаСамиДанные.nextInt();
           СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(ТекущийЭлементДанные);
           Integer  ПосикДня = sqLiteCursor.getColumnIndex("d"+ИндексИтерации.toString());
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            // TODO: 30.03.2023 новый код сохраняем в сам обьект uuid и день
        /*    Integer ID = sqLiteCursor.getInt(sqLiteCursor.getColumnIndex("_id"));
            Long UUID = sqLiteCursor.getLong(sqLiteCursor.getColumnIndex("uuid_tabel"));
            Long UUID_Tabel = sqLiteCursor.getLong(sqLiteCursor.getColumnIndex("uuid"));
            Long FIO = sqLiteCursor.getLong(sqLiteCursor.getColumnIndex("fio"));*/
            String ДанныеДней = sqLiteCursor.getString(ПосикДня);
/*            bundleДляОбновление.putInt("ID",ID );
            bundleДляОбновление.putLong("UUID",UUID);
            bundleДляОбновление.putLong("FIO",FIO);
            bundleДляОбновление.putLong("UUID_Tabel",UUID_Tabel);
            bundleДляОбновление.putInt("ПосикДня",ПосикДня);
            bundleДляОбновление.putString("ДанныеДней", ДанныеДней);
            bundleДляОбновление.putString("НазваниеДней",НазваниеДней);*/
            // TODO: 31.03.2023 ЗАПОМИНАЕМ ДАнные Д1
             СамиДанныеТабеля.setTag(bundleДляОбновление);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней + " СамиДанныеТабеля " +СамиДанныеТабеля  + " TextViewНазваниеДанныхВТабелеФИО " + TextViewНазваниеДанныхВТабелеФИО);
            if (ДанныеДней != null) {
                ДанныеДней = ДанныеДней.replaceAll("\\s+", "");
                if (ДанныеДней.equals("0")) {
                    ДанныеДней = "";
                }
                if (ДанныеДней.matches("[А-Я]")) {
                    СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }
              СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));



              СамиДанныеТабеля.addOnUnhandledKeyEventListener(new View.OnUnhandledKeyEventListener() {
                  @Override
                  public boolean onUnhandledKeyEvent(View v, KeyEvent event) {
                     if (event.getAction()==KeyEvent.ACTION_UP) {


                         // TODO: 31.03.2023
                         class SubClassОбнолениеЯчеек extends SubClassUpdateSingletabel{
                             @Override
                             Integer МетодЗаписиСменыПрофесии(@NonNull View searchViewДляНовогоПоиска, @NonNull Context context) {
                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                 return super.МетодЗаписиСменыПрофесии(searchViewДляНовогоПоиска, context);
                             }
                         }
                         Integer ОбновлениеЯчеекДанных=        new SubClassОбнолениеЯчеек().МетодЗаписиСменыПрофесии(v,getApplicationContext());
                         Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                 " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                 " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                   + "ОбновлениеЯчеекДанных " +ОбновлениеЯчеекДанных);
                         return true;
                      }
                      return false;
                  }
              });
             СамиДанныеТабеля.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     МетодПереходаНаМеткиТабеля();
                     Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                     return false;
                 }
             });
            if(СтаттусТабеля==true){
                 СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                СамиДанныеТабеля.setLongClickable(false);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ХЭШНазваниеДнейНедели.get(ИндексИтерации) "+ ХЭШНазваниеДнейНедели.get(ИндексИтерации)+
                    "  СамиДанныеТабеля " + СамиДанныеТабеля.getText().toString()+ " ТекущийЭлементДанные " +ТекущийЭлементДанные);
            // TODO: 31.03.2023  анимация для данных
            СамиДанныеТабеля.startAnimation(animationПрофессия);
        }
            // TODO: 31.03.2023  Запоминаем ФИО
          //  TextViewНазваниеДанныхВТабелеФИО.setTag(bundleДляОбновление);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());


    }
        return  ИндексИтерации;
    }


    //TODO Первый МЕТОД заполения строчки
    private Integer МетодНазваниеЯчеек(@NonNull Map<Integer, String> ХЭШНазваниеДнейНедели,
                                       @NonNull View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла,
                                       @NonNull String regex, @NonNull String regex1,
                                       @NonNull   IntStream intStreamИтератор,
                                       @NonNull      Integer ИндексИтерации) {
        try {
            TextView    НазваниеДанныхВТабелеДниНедели;
        // TODO: 31.03.2023 Первая СТРОЧКА данных НА ЭКРАНЕ SIGLE
        PrimitiveIterator.OfInt iteratorIteratorПерваяСтрочкаНазвание= intStreamИтератор.iterator();
        while (iteratorIteratorПерваяСтрочкаНазвание.hasNext()){
            // TODO: 31.03.2023 поднимаем версию
            ИндексИтерации++;
            /////TODO ДЕНЬ ПЕРВЫЙ
        Integer ТекущийЭлементНазвания=     iteratorIteratorПерваяСтрочкаНазвание.nextInt();
             НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(ТекущийЭлементНазвания);
            НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(ТекущийЭлементНазвания);
            НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(ИндексИтерации).trim());
            if (ХЭШНазваниеДнейНедели.get(ИндексИтерации).matches(regex) || ХЭШНазваниеДнейНедели.get(ИндексИтерации).matches(regex1)) {
                 НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                 НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                 НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                 НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ХЭШНазваниеДнейНедели.get(ИндексИтерации) "+ ХЭШНазваниеДнейНедели.get(ИндексИтерации)+
                    "  this.НазваниеДанныхВТабелеДниНедели " +  НазваниеДанныхВТабелеДниНедели.getText().toString()+ " ТекущийЭлементНазвания " +ТекущийЭлементНазвания);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  ИндексИтерации;
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



    class SubClassNewSearchAlertDialogНовыйПосик{
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
                                                        ((MaterialTextView)view).startAnimation(animationПрофессия);
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
                        listViewДляНовыйПосик[0].startAnimation(animationПрофессия);
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
                                Integer ПровйдерСменаПрофесии=   new SubClassUpdateSingletabel().   МетодЗаписиСменыПрофесии( (SearchView)  searchViewДляНовогоПоиска,getApplicationContext());
                                if (ПровйдерСменаПрофесии>0) {

                                    // TODO: 30.03.2023 Курсор ALL Date
                                     Integer ПолощениеДАнных=            ГлавныйALLКурсорДанныеSwipes.getPosition();
                                    //МетодSwipeALLКурсор();
                                         ГлавныйALLКурсорДанныеSwipes.moveToPosition(ПолощениеДАнных);
                                    // TODO: 29.03.2023 Методы ПОсле усМешного Смены Професиии
                                    МетодПереопределенияНазваниеПрофесии();
                                    МетодПерегрузкаВидаЭкрана();
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


    private void МетодПерегрузкаВидаЭкрана() {
        try {
            TextViewНазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);
            TextViewНазваниеДанныхВТабелеФИО.refreshDrawableState();
            TextViewНазваниеДанныхВТабелеФИО.forceLayout();
            ГлавныйКонтейнерТабель.refreshDrawableState();
            ГлавныйКонтейнерТабель.forceLayout();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //TODO класс обновление Ячеек
    class SubClassUpdateSingletabel{
        Integer МетодЗаписиСменыПрофесии(@NonNull View searchViewДляНовогоПоиска,@NonNull Context context){ //TODO метод записи СМЕНЫ ПРОФЕСИИ
            Integer ПровйдерСменаПрофесии=0;
            try{
                String ТаблицаОбработки="data_tabels";
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " searchViewДляНовогоПоиска "+searchViewДляНовогоПоиска+ " ТаблицаОбработки "+ТаблицаОбработки);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                Bundle bundleСменаПрофессии= (Bundle)  searchViewДляНовогоПоиска.getTag();
                ContentResolver contentResolver=getContentResolver();
                Bundle bundleОбновлениеПрофесии=  contentResolver.call(uri,ТаблицаОбработки,ТаблицаОбработки,bundleСменаПрофессии);
                ПровйдерСменаПрофесии=  bundleОбновлениеПрофесии.getInt(    "СтатусОбновления",0);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РЕЗУЛЬТАТ ПровйдерСменаПрофесии  " +  ПровйдерСменаПрофесии);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ПровйдерСменаПрофесии;
        }

        private View МетодОбновлениеЯчеек(View viewДанные) {
            try{
                if (viewДанные!=null) {
                    Log.d(this.getClass().getName(), " viewДанные" +  viewДанные);
                    TextView textViewЯчейкаОбновление=(TextView)    viewДанные;

                    List<Integer> ЛистДопустимоеСодержание = new ArrayList();
                    IntStream.iterate(1, i -> i + 1).limit(24).forEachOrdered(ЛистДопустимоеСодержание::add);
                    boolean ФлагНовоеЗначение=         textViewЯчейкаОбновление.toString().matches("(.*)[0-9](.*)");/////TODO   viewДанные.toString().matches("(.*)[^0-9](.*)");
                    String ЗначениеДляВставкиФинальное =null;
                    if(ФлагНовоеЗначение==true){
                        viewДанные.toString().replaceAll("[^0-9]","");
                        ЗначениеДляВставкиФинальное =  textViewЯчейкаОбновление.toString().substring(0, 1);
                        if (   Integer.parseInt(textViewЯчейкаОбновление.getText().toString())>24) {
                            Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_LONG);
                            ImageView cc = new ImageView(getBaseContext());
                            cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);//icon_dsu1_synchronisazia_dsu1_success
                            aa.setView(cc);
                            aa.show();
                            Toast.makeText(getBaseContext(), "Нет сохранилось !!!"+
                                    "\n"+" (Часы больше 24 ) :" +textViewЯчейкаОбновление.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        viewДанные.toString().replaceAll("[0-9]","");
                        ЗначениеДляВставкиФинальное =  textViewЯчейкаОбновление.toString().substring(0, 2);
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " ФлагНовоеЗначение " +ФлагНовоеЗначение+
                            " textViewЯчейкаОбновление.toString()" +  textViewЯчейкаОбновление.toString());

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

            }
            return null;
        }


    }



    //TODO класс визуализации внешнего вида

    class SubClassSingleTabelRecycreView  {
         private     Cursor cursor;
        private   LifecycleOwner lifecycleOwner;
        private   LifecycleOwner  lifecycleOwnerОбщая;
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
                recyclerView.setItemAnimator(new DefaultItemAnimator());
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

        private void методДляSimpeCallbacks() {
            try{
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(10,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT  | ItemTouchHelper.DOWN | ItemTouchHelper.END) {
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    Integer posio= myViewHolder.getAbsoluteAdapterPosition();
                    // remove item from adapter
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"recyclerView   " + recyclerView+ " cursor " +cursor);
/*
                    myRecycleViewAdapter = new  MyRecycleViewAdapter(cursor);
                    recyclerView.setAdapter(myRecycleViewAdapter);

                    myRecycleViewAdapter.onBindViewHolder(myViewHolder,3,new ArrayList<>());
                  //  recyclerView.setAdapter(myRecycleViewAdapter);
                    myRecycleViewAdapter.notifyDataSetChanged();
                    recyclerView.requestLayout();*/
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
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);


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
        void МетодЗаполениеRecycleView(@NonNull Cursor cursor) {
            try {
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
        }









// TODO: 03.04.2023 перенесеный RecycreView

        private void МетодСлушательКурсора() {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                if (cursor !=null) {
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
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {
            private TableLayout TableLayoutSingleTabel;
            private   TableRow rowФИО;

            // TODO: 04.04.2023   NAME
            private CopyOnWriteArrayList<TableRow> rowName =new CopyOnWriteArrayList<>();
            // TODO: 04.04.2023   Data
            private CopyOnWriteArrayList<TableRow> rowData=new CopyOnWriteArrayList<>();
            // TODO: 05.04.2023 Слушатель Long
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

        // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

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
        void методWorkMAnagerReycreVIew() {
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
                                Long CallBaskОтWorkManagerОдноразового=0l;
                                if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)         {
                                   CallBaskОтWorkManagerОдноразового =
                                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                    if (CallBaskОтWorkManagerОдноразового>0) {
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        recyclerView.requestLayout();
                                    }
                                }
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "CallBaskОтWorkManagerОдноразового " +CallBaskОтWorkManagerОдноразового);
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
                                Integer CallBaskОтWorkManageОбщая=0;
                                if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                    long end = Calendar.getInstance().getTimeInMillis();
                                     CallBaskОтWorkManageОбщая =
                                            СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWorkMananger", 0);
                                    if (CallBaskОтWorkManageОбщая>0) {
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        recyclerView.requestLayout();
                                    }
                                }
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "CallBaskОтWorkManageОбщая " +CallBaskОтWorkManageОбщая);
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

        class MyRecycleViewAdapter extends RecyclerView.Adapter< MyViewHolder> {
            private Cursor cursor;
            public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                try{
                this.cursor = cursor;

                if ( cursor!=null) {
                    if (cursor.getCount() > 0 ) {
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cursor  " + cursor.getCount());
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
                    ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                            Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor);
                                if (cursor!=null) {
                                    if (cursor.getCount() > 0 && holder.TableLayoutSingleTabel != null) {
                                        // TODO: 04.04.2023 переходим на данне через Смежение
                                        cursor.moveToPosition(position);
                                        // TODO: 04.04.2023 ЗАПОЛЕНИЯ НОВЫМИ ДАННЫМИ ПОСЛЕ СМЕЩЕНИЕ ДАННЫХ ВНУТРИ
                                        МетодЗаполняемДаннымиRecycreViewSingleTable(holder, cursor);
                                        // TODO: 04.04.2023 перегрузкка внешнего вида RecyrView
                                        Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " sqLiteCursor " + cursor.getCount());
                                        // TODO: 07.04.2023 переопрелделния Вида Табеля
                                        МетодПерегрузкаSingletabel();
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
                    messageRows.getTarget().postDelayed(()-> {
                        ProgressBarSingleTabel.setVisibility(View.INVISIBLE);
                        recyclerView.requestLayout();
                    },1000);
                    recyclerView.refreshDrawableState();
                    recyclerView.forceLayout();
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
                   viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov, parent, false);//todo old simple_for_takst_cardview1
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewSingleTabel);
                    if (Build.BRAND.toString().contains("Samsung") ||Build.BRAND.toString().contains("Galaxy")
                            || Build.BRAND.toString().contains("samsung") ||Build.BRAND.toString().contains("galaxy") ) {
                        // TODO: 03.04.2023  для Sansung
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_grid_for_tables_four_columns_samsung, parent, false);
                    }  else {
                        // TODO: 03.04.2023 для MM
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm, parent, false);
                       // viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm, parent, false);
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
                            textViewФИо.startAnimation(animationПрофессия) ;
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
                        Bundle data = new Bundle();
                        data.putString("ЗначениеДня", День);
                        data.putLong("uuid", uuid);
                        data.putString("День", НазваниеДляДень);
                        editTextRowКликПоДАнными.setTag(data);
                       editTextRowКликПоДАнными.setVisibility(View.VISIBLE);
                        editTextRowКликПоДАнными.setText(День.trim());
                        // TODO: 19.10.2022
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень " + НазваниеДляДень + "data " + data);
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
                    messageRows.getTarget().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextViewRowКликПоНазваниям.startAnimation(animationПрофессия300) ;
                            TextViewRowКликПоНазваниям.setVisibility(View.VISIBLE);
                            TextViewRowКликПоНазваниям.setText(s.trim());
                        }
                    },150);
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
                                    // TODO: 19.10.2022
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
                                            .subscribe(new io.reactivex.rxjava3.functions.Consumer<Boolean>() {
                                                @Override
                                                public void accept(Boolean aBoolean) throws Throwable {
                                                    if(aBoolean==false){
                                          Bundle bundleДанныеTag=        (Bundle)      v.getTag();
                                          String ЗначениеДняTag=       bundleДанныеTag.getString("ЗначениеДня").trim();
                                        String EditTextДАнные=       ((EditText) v).getText().toString().trim();
                   // TODO: 06.04.2023 Принимаем Решение Если ДАные РАзные ЗАпускаем Обновление
                    if (      !EditTextДАнные.equalsIgnoreCase(ЗначениеДняTag)) {
                        Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" hasFocus " +hasFocus + " v"+v+
                                " bundleДанныеTag " +bundleДанныеTag + " EditTextДАнные " +EditTextДАнные+  "ЗначениеДняTag " +ЗначениеДняTag);
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
                    ((EditText) v).setText("0");
                    ((EditText) v).setTextColor(Color.parseColor("#00ACC1"));
                    ((EditText) v).refreshDrawableState();
                    ((EditText) v).forceLayout();
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

    }//TODO КОНЕЦ КЛАССА визуального оформление Recycreview

}






















