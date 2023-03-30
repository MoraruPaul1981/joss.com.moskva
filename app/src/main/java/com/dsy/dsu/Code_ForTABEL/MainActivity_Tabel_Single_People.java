package com.dsy.dsu.Code_ForTABEL;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
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
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
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
    private  String   ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;
    private  String  МесяцТабеляФинал;
    private  Configuration config;
    private  ArrayList<String> МассивДляВыбораСпинераДаты= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  ArrayList<String> МассивДляВыбораВСпинерДепартамент= new ArrayList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private  String КонтентСпинераНаАктивтиТабель= "";
    private  String ИмяТабеляФинал= "";
    private  String  ДепартаментТабеляФинал= "";
    private  String UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя;
    private   TextView НазваниеДанныхВТабелеДниНедели;
    private   TextView НазваниеДанныхВТабелеСНИЛС;
    private TextView НазваниеДанныхВТабелеФИО;
    private  EditText СамиДанныеТабеля;
    private int ПосикДня;
    private   String НазваниеТабеля= "";
    private  String НазваниеЗагруженногТАбеля= "";
    private  Boolean    СтаттусТабеля= false;
    private   String ДробимДляТабеляГод,ДробимДляТебеляМесяц;
    private  View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла;
    private Map< Integer,String> ХЭШНазваниеДнейНедели ;
    private String ДанныеДней;

    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private  String МесяцДляЗагрузкиТабелей= "";
    private  String ГодДляЗагрузкиТабелей= "";
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
    private  Integer ОбщееКоличествоЛюдейВТабелеТекущем;
    private   HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбика        =new HashMap<>();;
    private   String ПолучениеЗначениеДоИзменения;
    private   String СамоЗначениеЯчейкиТабеля;
    private   Context context;
    private    int IDЧьиДанныеДляСотрудников;
    private int ЦифровоеИмяНовгоТабеля;
    private int МесяцТабеля;
    private    TextView СловоТабель;
    private HorizontalScrollView HorizontalScrollViewВТабелеОдинСотрудник;
    private   String НазваниеТабеляПослеУспешногоСозданиеСотрудника= "";
    private  String    UUIDТабеляПослеУспешногоСозданиеСотрудника= "";

    private   String НазваниеТабеляПришелПослеСоздангоНового= "";
    private   String  ДепартаментПришелПослеСоздангоНового= "";
    private    String ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;
    private  String ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки;
    private    String UUIDТабеляКнопкаBACKУниверсальный= "";
    private   Boolean  ПроизошелЛиСфайпПоДаннымСингТабеля=false;
    private Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private  PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private View view2Линия;
    private    String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//
    final private   String ИмяСлужбыОбщейСинхронизацииДляЗадачи = "WorkManager Synchronizasiy_Data";
    private  long РодительскийUUDТаблицыТабель=0l;
    private      Message message;
    private Animation animationПрофессия;
    private    SQLiteCursor ГлавныйКурсорДанныеSwipes;

    private  Long UUIDТекущегоВыбраногоСотрудника=0l;
    private         Long ПолученыйUUIDФИОСледующий=0l;
    private   Animation animationRich;
    private   Animation animationLesft;
    private String  МесяциГодТабеляПолностью;
    private Integer ВсеСтрокиТабеля=0;

    private  TextWatcher  СлушательПолученияДанных;


    private   View.OnLongClickListener СлушательДляПереходаНаМеткиТабеля;

    private  View.OnClickListener СлушательДанныеДляОбновления;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            ////todo запрещает поворот экрана
            setContentView(R.layout.activity_main__tabel_four_colums);
            //     getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  );
            //////todo настрока экрана
        /*    getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
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
            try {
                ГлавныйКонтейнерТабель.removeAllViewsInLayout();
            } catch (Exception e) {
                //  e.printStackTrace();
            }
            /////TODO Скоролл Вид
            ScrollСамогоТабеля=(ScrollView) findViewById(R.id.ScrollViewСамТабеля);
            ScrollСамогоТабеля.setBackgroundResource(R.drawable.textlines_tabel_row_color_green_mini);
            ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА
            ГлавныйВерхнийКонтейнер=(ConstraintLayout) findViewById(R.id.ГлавныйВерхнийКонтейнер);
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
            animationПрофессия = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_row);
            animationRich = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
            animationLesft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1
            Bundle data=     getIntent().getExtras();
            // TODO: 29.03.2023  Метод Какая марка телфона из за этого загрудаем вид
            МетодВыбораВнешнегоВидаИзВидаТелефона();
            МетодПриИзмениеДанныхВБазеМенемВнешнийВидТабеляObserver();
            МетодПришлиПараметрыОтДругихАктивитиДляРаботыТабеля();
            // TODO: 30.03.2023 CЛУШАТЕЛИ ДВА ДАННЫХ
            МетодСлушательДанных();
            МетодСлушателиДляТекущегоТабеля();
            МетодGetmessage();
            // TODO: 30.03.2023 Курсор ALL Date
            МетодSwipeALLКурсор();
            //TODO #2
            МетодПриНАжатииНаКнопкуBACK();
            //TODO #4
            МетолСозданиеТабеляФинал(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);//запускаем метод создание табеля
            //TODO #5
            ВсеСтрокиТабеля=   МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(МесяцТабеляФинал);   ////раньше стояло 0
            // TODO: 29.03.2023
            ///TODO   #7  запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
            МетодСлушательСвайпов();
            ///TODO   #8 запускаем метод отработки поднятие клавиатур
            МетодОтработкиПоднятияКлавиатуры();
            // TODO: 29.01.2022 ПРИ ИЗМЕНЕНИ МЕНЯЕМ ДАННЫЕ В БАЗЕ В ТАБЕЛЕ
            МетодАнализДанныхSwipes( );
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

    private void МетодВыбораВнешнегоВидаИзВидаТелефона() {
        try{
            LayoutInflater МеханизмЗагрузкиОдногЛайАутавДругой = getLayoutInflater();
            //todo ВЫБИРАЕМ ВЗАВИСИСМОСТИ КАКОЙ ЭКРАН БУДЕТ ЗАГРУЖАТЬСЯ ПОЛЬЗОВАТЕЛЮ В ОДИНОЧНОМ тАБЕЛЕ
            if (Build.BRAND.toString().contains("Samsung") ||Build.BRAND.toString().contains("Galaxy")
                    || Build.BRAND.toString().contains("samsung") ||Build.BRAND.toString().contains("galaxy") ) {
                Log.d(this.getClass().getName(), "Внешний вид САМСУНГА  "  +Build.BRAND.toString() );
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_samsung,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();

                // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ НА HTC
            } else if (Build.BRAND.toString().contains("HTC") ){
                Log.d(this.getClass().getName(), "Внешний вид HTC "  +Build.BRAND.toString() );
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm_htc,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла =
                        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();
                // TODO: 24.05.2021 ВЫБОР КАКОЙ АКТИВТИ МАКЕТ ЗАГРУЗАТЬ ВСЕ ОСТАЛЬНЫЕ ТЕЛЕФОНЫ
            } else {
                Log.d(this.getClass().getName(), "Внешний вид ДЛЯ ВСЕХ ОСТАЛЬНЫХ "  +Build.BRAND.toString() );
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла =
                        МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns_in_mm,//activity_main_find_customer_for_tables // activity_main_grid_for_tables
                                ГлавныйКонтейнерТабель, false);
                ViewGroup.LayoutParams ПараментыКонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.getLayoutParams();
            }
            HorizontalScrollViewВТабелеОдинСотрудник =(HorizontalScrollView)     КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ГоризонтльнаяПрокруткаВнутриСамТабель);

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

    private void МетодSwipeALLКурсор() {
        try{
            ГлавныйКурсорДанныеSwipes =
                    new Class_MODEL_synchronized(getApplicationContext()).
                            МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(context,
                                    ЦифровоеИмяНовгоТабеля, МесяцТабеля, ГодДляТабелей);

            if (ПроизошелЛиСфайпПоДаннымСингТабеля==false) {
                if (ГлавныйКурсорДанныеSwipes.getCount() > 0) {
                    ГлавныйКурсорДанныеSwipes.moveToFirst();
                    ОбщееКоличествоЛюдейВТабелеТекущем= ГлавныйКурсорДанныеSwipes.getCount();
                }
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  ГлавныйКурсорДанныеSwipes " +ГлавныйКурсорДанныеSwipes+
                    " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля+
                    " ГодДляКурсораТабелей "+ ГодДляТабелей +
                    " МЕсяцДляКурсораТабелейДЛяПермещения " + МесяцТабеля+
                    " ГлавныйКурсорДанныеSwipes " +ГлавныйКурсорДанныеSwipes+
                    " ПроизошелЛиСфайпПоДаннымСингТабеля " +ПроизошелЛиСфайпПоДаннымСингТабеля +
                    " ОбщееКоличествоЛюдейВТабелеТекущем " +ОбщееКоличествоЛюдейВТабелеТекущем);

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

    @Override
    protected void onResume() {
        super.onResume();
        try {



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }

// TODO: 24.05.2021  метд обоработки поднятия и апускания клавиатуры

    private void МетодОтработкиПоднятияКлавиатуры() {
        try{
            KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
                @Override
                public void onVisibilityChanged(boolean isOpen) {
                    ///
                    if (isOpen) {
                  /*  Toast.makeText(getApplicationContext(),
                            " Клавиатур поднялась !!!!!!! "    , Toast.LENGTH_SHORT).show();
*/



                        ///////

                        Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника "+ПолноеИмяТабеляПослеСозданиеНовогоСотрудника+"\n"+
                                " ФИОДляТабеляНаАктивти " + ФИОТекущегоСотрудника);





                        МетолСозданиеТабеляФинал(ФИОТекущегоСотрудника);



                    }else{
                    /*Toast.makeText(getApplicationContext(),
                            " Клавиатур опусталась  "    , Toast.LENGTH_SHORT).show();*/




                        ///////

                        Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника "+ПолноеИмяТабеляПослеСозданиеНовогоСотрудника+"\n"+
                                " ФИОДляТабеляНаАктивти " + ФИОТекущегоСотрудника);

                        МетолСозданиеТабеляФинал(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);


                    }

                }
            });




            /////////
        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }

    // TODO: 28.04.2021 обработка свайпов
    private void МетодСлушательСвайпов() {
        try{
            HorizontalScrollViewВТабелеОдинСотрудник.setOnTouchListener(new OnTouchListener() {
                final float[] downy = {0l};
                final float[] downx = {0l};
                Long  getDownTime=0l;
                Long  getEventTime=0l;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d(this.getClass().getName(),"Свайп просто сфайп");
                    try{
                        if (downx[0]==0l){
                            downx[0]=event.getX();
                        }
                        if (downy[0]==0l){
                            downy[0]=event.getY();
                        }

                        if (getDownTime==0) {
                            getDownTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                        }
                        float     upy,upx;
                        float deltaY,deltax;
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: // нажатие
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_DOWN");
                                break;
                            case MotionEvent.ACTION_MOVE: // движение
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_MOVE");
                                break;
                            case MotionEvent.ACTION_UP: // отпускание
                            case MotionEvent.ACTION_CANCEL:
                                Log.d(this.getClass().getName(),"Movement occurred outside bounds " +
                                        "of current screen element ACTION_UP ACTION_CANCEL ");
                                upy=event.getX();
                                upx=event.getX();
                                deltax=upx-downx[0];
                                // TODO: 07.05.2021  формула
                                final int MIN_DISTANCEY=50;
                                final int MIN_DISTANCEX=50;
                                getEventTime = TimeUnit.MILLISECONDS.toSeconds( System.currentTimeMillis());
                                float duration = getEventTime - getDownTime;
                                StringBuffer БуферФиналВремениПрошлло=      new StringBuffer(String.valueOf(duration));
                                int ФиналСколькоПрошллоВремени=     Integer.parseInt(БуферФиналВремениПрошлло.substring(0,1));
                                // TODO: 19.05.2021 конец прокрутки
                                int КонецПрокрутки=      v.getRight();
                                int НачалоПрокрутки=      v.getLeft();

                                View lastChild = ScrollСамогоТабеля.getChildAt(ScrollСамогоТабеля.getChildCount() - 1);
                                int bottom = lastChild.getBottom() + ScrollСамогоТабеля.getPaddingBottom();
                                int sy = ScrollСамогоТабеля.getScrollX();
                                int sh = ScrollСамогоТабеля.getWidth();
                                int delta = bottom - (sy + sh);
                                // TODO: 08.05.2021  Y
                                if (Math.abs(deltax) > MIN_DISTANCEY ) {
                                    if ( deltax > 200 && delta>10 ) {
                                        int scrollX = HorizontalScrollViewВТабелеОдинСотрудник.getScrollX();
                                        HorizontalScrollViewВТабелеОдинСотрудник.scrollTo(scrollX,0);
                                        HorizontalScrollViewВТабелеОдинСотрудник.smoothScrollTo(scrollX,0);
                                        // TODO: 24.09.2021 двигаемся вперед
                                        ПроизошелЛиСфайпПоДаннымСингТабеля=true;
                                        ГлавныйКонтейнерТабель.setFocusable(false);
                                        ГлавныйКонтейнерТабель.setClickable(false);
                                        // TODO: 30.03.2023  перехехлд по ажнным  
                                        МетодСвайпНазаПоДанным();
                                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпНазаПоДанным ");/////
                                        // TODO: 09.05.2021  при успешном срабоатывании true
                                        return true;
                                    }else if  (  deltax<0 && delta>800) {
                                        int scrollX = HorizontalScrollViewВТабелеОдинСотрудник.getScrollX();
                                        HorizontalScrollViewВТабелеОдинСотрудник.scrollTo(scrollX,0);
                                        HorizontalScrollViewВТабелеОдинСотрудник.smoothScrollTo(scrollX,0);
                                        // TODO: 24.09.2021 двигаемся назад
                                        ПроизошелЛиСфайпПоДаннымСингТабеля=true;
                                        ГлавныйКонтейнерТабель.setFocusable(false);
                                        ГлавныйКонтейнерТабель.setClickable(false);
                                        // TODO: 30.03.2023  перехехлд по ажнным
                                        МетодСвайпВпередПоДАнным();
                                        Log.d(getApplicationContext().getClass().getName(), " МетодСвайпВпередПоДАнным ");/////
                                        // TODO: 07.05.2021  обработка горизонта X
                                        // TODO: 09.05.2021  при успешном срабоатывании true
                                        return true;
                                    }
                                }
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return false;
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













    // TODO: 29.01.2022
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






    private void МетодПришлиПараметрыОтДругихАктивитиДляРаботыТабеля() {
        try {
            Intent ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ =  getIntent();
            РодительскийUUDТаблицыТабель = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            Log.d(this.getClass().getName(), "РодительскийUUDТаблицыТабель" + РодительскийUUDТаблицыТабель);
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюДатуНовогоТабеля");
            }
            Log.d(this.getClass().getName(), " МесяцТабеляФинал :" + МесяцТабеляФинал);
            ИмяТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюНазваниеТабеля");
            Log.d(this.getClass().getName(), " ИмяТабеляФинал :" + ИмяТабеляФинал);
            ДепартаментТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемДепартаментФинал");
            Log.d(this.getClass().getName(), " ДепартаментТабеляФинал :" + ДепартаментТабеляФинал);
            if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеНазваниеТабеляФинал");
                Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            }
            ///TODO ПОЛНОЕ НАЗВАНИЕ ТАБЕЛЯ ПОСЛЕ УСПЕШНОЙ СОЗДАНОГО СОТРУДНИКА И ПЕРЕДВЕМ СЮДА ЕГО ИМЯ ТАБЕЛЯ ПОЛНОЕ
            ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника");
            Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки);
            String ПроверкаUUIDТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля");
            ////todo если не пришел uuid запускаем его с дгурим uuid
            UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля");
            Log.d(this.getClass().getName(), "UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя" + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            НазваниеТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("НазваниеТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " НазваниеТабеляПослеУспешногоСозданиеСотрудника " + НазваниеТабеляПослеУспешногоСозданиеСотрудника);
            UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " UUIDТабеляФинал:" + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            НазваниеТабеляПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("НазваниеМесяцаТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), " НазваниеТабеляПришелПослеСоздангоНового  " + НазваниеТабеляПришелПослеСоздангоНового);
            ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника");
            Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            String UUIDТабеляПослеПодбораУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбораУниверсальный");
            Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            ГодДляЗагрузкиТабелей = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ГодДляЗагрузкиТабелей");
            Log.d(this.getClass().getName(), " ГодТабеляПослеПодбораУниверсальный  " + ГодДляЗагрузкиТабелей);
            if(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляИзВсехСотрудниковВТАбеле");//ДепартаментПришелПослеСоздангоНового
                Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
            }
            if (UUIDТекущегоВыбраногоСотрудника==0l) {
                UUIDТекущегоВыбраногоСотрудника= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле", 0);
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника   " + UUIDТекущегоВыбраногоСотрудника  );
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле");
            }
            Log.d(this.getClass().getName(), "  МесяцТабеляФинал  " + МесяцТабеляФинал);
            if ( UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя==null){
                UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя= UUIDТабеляПослеПодбораУниверсальный;
            }
            if (МесяцТабеляФинал==null){
                МесяцТабеляФинал=НазваниеТабеляПришелПослеСоздангоНового;
            }
            //todo департамент
            if (ДепартаментТабеляФинал==null ){
                ДепартаментТабеляФинал=ДепартаментПришелПослеСоздангоНового;
            }
            if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника ==null){
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки ;
            }
            if (МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("МесяцТабеляПослеПодбора");
                Log.d(this.getClass().getName(), "  МесяцТабеляФинал " + МесяцТабеляФинал);
                ////
                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки==null) {
                    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.
                            getStringExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора");
                    Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки);
                }
                if (ДепартаментПришелПослеСоздангоНового==null) {
                    ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляПослеПодбора");
                    Log.d(this.getClass().getName(), "  ДепартаментПришелПослеСоздангоНового   " + ДепартаментПришелПослеСоздангоНового);
                }
                ////
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбораУниверсальный");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляПослеПодбора");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }
                if ( UUIDТабеляПослеУспешногоСозданиеСотрудника ==null) {
                    UUIDТабеляПослеУспешногоСозданиеСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле");
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                }

                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null) {
                    ПолноеИмяТабеляПослеСозданиеНовогоСотрудника= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.
                            getStringExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора");
                    Log.d(this.getClass().getName(), " ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
                }
///todo если имя табеля пустое полслен созадени нового сотрудника по присваемое ему имя от дургой переменой
                Log.d(this.getClass().getName(), "   МесяцТабеляФинал  " + МесяцТабеляФинал +
                        "    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки   " + ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки+
                        "  ДепартаментПришелПослеСоздангоНового " + ДепартаментПришелПослеСоздангоНового + "  UUIDТабеляПослеУспешногоСозданиеСотрудника  "
                        + UUIDТабеляПослеУспешногоСозданиеСотрудника );
            }

            if (UUIDТабеляПослеУспешногоСозданиеСотрудника==null){
                UUIDТабеляПослеУспешногоСозданиеСотрудника=UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя;
            }
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника  " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            if(UUIDТабеляКнопкаBACKУниверсальный!=null) {
                if (UUIDТабеляКнопкаBACKУниверсальный.length() < 1) {
                    UUIDТабеляКнопкаBACKУниверсальный = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("UUIDТабеляКнопкаBACKУниверсальный");
                    Log.d(this.getClass().getName(), "  UUIDТабеляКнопкаBACKУниверсальный  " + UUIDТабеляКнопкаBACKУниверсальный);
                }
            }
            if(ПолноеИмяТабеляПослеСозданиеНовогоСотрудника==null){
                ПолноеИмяТабеляПослеСозданиеНовогоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника");
                Log.d(this.getClass().getName(), "  ПолноеИмяТабеляПослеСозданиеНовогоСотрудника " +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            }
//////todo ПРИШЛО НА SINGLE TANEL ПО СОТРУДНИКУ
            IDЧьиДанныеДляСотрудников = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("IDЧьиДанныеДляСотрудников",0);
            Log.d(this.getClass().getName(), "  IDЧьиДанныеДляСотрудников  " +IDЧьиДанныеДляСотрудников);
            //////todo дополнтельные данные которые нужно перебросить далеее в другие актвити и ДАЛЕЕ МЕТКА ТАБЕЛЯ
            if(МесяцТабеляФинал==null) {
                МесяцТабеляФинал = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемСозданнуюДатуНовогоТабеля");
            }
            if( ДепартаментПришелПослеСоздангоНового==null) {
                ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПередаемДепартаментФинал");
            }
            if(ДепартаментПришелПослеСоздангоНового==null) {
                ДепартаментПришелПослеСоздангоНового = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолноеНазваниеТабеляФинал");
            }
            if (UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя ==null ) {
                UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra(("ПередаваемыйИзКнопкиПолучаемUUIDТабеля"));

            }
            UUIDТекущегоВыбраногоСотрудника =ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("UUIDТабеляПослеПодбораУниверсальный",0l);
            Log.d(this.getClass().getName(), " UUIDТекущегоВыбраногоСотрудника   "+UUIDТекущегоВыбраногоСотрудника );
            if (ГодДляЗагрузкиТабелей == null){
                ГодДляЗагрузкиТабелей = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ГодТабеляФиналИзВсехСотрудниковВТАбеле");
            }
            ///TODO цифровоеимя табеля
            if (ЦифровоеИмяНовгоТабеля==0) {
                ЦифровоеИмяНовгоТабеля= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("ЦифровоеИмяНовгоТабеля",0);
            }
            //////todo  RКОНЕЦ дополнтельные данные которые нужно перебросить далеее в другие актвити и ДАЛЕЕ МЕТКА ТАБЕЛЯ
            if (ЦифровоеИмяНовгоТабеля==0) {
                /////
                ЦифровоеИмяНовгоТабеля= ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("ЦифровоеИмяНовгоТабеляБольШИеБуквыАктивти",0);
            }

            if (МесяцТабеля==0) {
                МесяцТабеля = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("МЕсяцДляКурсораТабелей", 0);
            }
            if (ГодДляТабелей==0) {
                ГодДляТабелей   = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getIntExtra("ГодДляЗагрузкиТабелей", 0);
            }
            Log.d(this.getClass().getName(), " Конец Получение Данных  ЦифровоеИмяНовгоТабеля " +  ЦифровоеИмяНовгоТабеля + "  PUBLIC_CONTENT.ЦифровоеИмяНовгоТабеля "+
                    " ЦифровоеИмяНовгоТабеля " +ЦифровоеИмяНовгоТабеля +
                    " ГодДляТабелей " +ГодДляТабелей  + " МесяцТабеля " +МесяцТабеля);

            ПроизошелЛиСфайпПоДаннымСингТабеля = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getBooleanExtra("ПроизошелЛиСфайпПоДаннымСингТабеля",false);
            Log.d(this.getClass().getName(), "ПроизошелЛиСфайпПоДаннымСингТабеля " +  ПроизошелЛиСфайпПоДаннымСингТабеля);
            ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре");
            Log.d(this.getClass().getName(), "  ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            if (UUIDТекущегоВыбраногоСотрудника ==0l) {
                UUIDТекущегоВыбраногоСотрудника = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            }
            Log.d(this.getClass().getName(), " UUIDТекущегоВыбраногоСотрудника   " + UUIDТекущегоВыбраногоСотрудника );
            if ( РодительскийUUDТаблицыТабель==0l) {
                РодительскийUUDТаблицыТабель = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getLongExtra("РодительскийUUDТаблицыТабель", 0);
            }
            if (ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре==null) {
                ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре");
            }

            if (МесяциГодТабеляПолностью==null) {
                МесяциГодТабеляПолностью = ИнтентПришелПараметрыДЛяSingleТАБЕЛЬ.getStringExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле");
            }
            Log.d(this.getClass().getName(), "  UUIDТекущегоВыбраногоСотрудника  "
                    +  UUIDТекущегоВыбраногоСотрудника+
                    "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель+
                    " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " +ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре  + " МесяциГодТабеляПолностью " +МесяциГодТабеляПолностью);

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
                    МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника();
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
    private void МетолСозданиеТабеляФинал( @NonNull  String ВнутрениеЗначениеСФОилиПриСменеФИОсотрудника) {
        try{
            МассивДляВыбораСпинераДаты.clear();
            МассивДляВыбораСпинераДаты=new ArrayList<>();
            МассивДляВыбораСпинераДаты.add(МесяцТабеляФинал);
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
                        ///метод запись ошибок в таблицу
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

    private Integer МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля(String  МесяцТабеляФинал  ) {
        Integer ОбщееКоличествоСОтрудниковДляСкролаПотабелю=0;
        try{
            Log.d(this.getClass().getName(), " МесяцТабеляФинал " + МесяцТабеляФинал);
            МесяцДляЗагрузкиТабелей= String.valueOf(МетодПоказатьМесяцДляЗАписивОднуКолонку(МесяцТабеляФинал));
            ГодДляЗагрузкиТабелей= String.valueOf(МетодПоказатьГодДляЗАписивОднуКолонку(МесяцТабеляФинал));
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ= new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");
            PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());
            SQLiteCursor            Курсор_ПолучаемПубличныйID= (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                    new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            if(Курсор_ПолучаемПубличныйID.getCount()>0){
                Курсор_ПолучаемПубличныйID.moveToFirst();
                ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемПубличныйID.getInt(0);
                Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
            }
            ПубличноеIDЗагрузкиТабелей= String.valueOf(ПубличноеIDПолученныйИзСервлетаДляUUID);
            Log.d(this.getClass().getName(), " ПубличноеIDЗагрузкиТабелей  " + ПубличноеIDЗагрузкиТабелей);
            МЕсяцДляКурсораТабелей=МетодПолучениниеМесяцДляКурсора( МесяцТабеляФинал);
            ГодДляТабелей =МетодПолучениниеГодДляКурсора( МесяцТабеляФинал);
            Log.d(this.getClass().getName()," МЕсяцДляКурсораТабелей "+МЕсяцДляКурсораТабелей+" ГодДляКурсораТабелей " + ГодДляТабелей +
                    "   " + UUIDТекущегоВыбраногоСотрудника+" МЕсяцДляКурсораТабелейДЛяПермещения " + МесяцТабеля);
////todo загружем созданные табеля из базы после успешного добавления нового сотрдуника
            ////TODO ЕСДИ ДАННЫХ НЕТ НЕ И НЕЧЕГО НАЧИНАТЬ ВЫБОРКУ ДАННЫХ ИЗ БАЗЫЫ ДепартаментТабеляФинал
            if (МесяцТабеля ==0) {
                МесяцТабеля =МЕсяцДляКурсораТабелей;
            }
///TODO СЮДА ЗАХОДИМ ПРОСТО БЕЗ СОЗДАНИЕ НОВОГО ЗАГРУЖАЕТ УЖЕ СОЗДАННОЕ ТАБЕЛЯ В БАЗЕ "status_send", "Удаленная"
            SQLiteCursor Курсор_ПолучаемКоличествоСотрудниковВАтбеле=null;
            Курсор_ПолучаемКоличествоСотрудниковВАтбеле =
                    new Class_MODEL_synchronized(getApplicationContext())
                            .МетодЗагружетУжеготовыеТабеляДляСкролаПОТабелюТолькоКоличествоСТорочек(context,
                                    ЦифровоеИмяНовгоТабеля, МесяцТабеля, ГодДляТабелей);
            Log.d(this.getClass().getName(), "Курсор_ПолучаемКоличествоСотрудниковВАтбеле " +Курсор_ПолучаемКоличествоСотрудниковВАтбеле);
            if (  Курсор_ПолучаемКоличествоСотрудниковВАтбеле.getCount()>0) {
                Курсор_ПолучаемКоличествоСотрудниковВАтбеле.moveToFirst();
                ОбщееКоличествоСОтрудниковДляСкролаПотабелю=    Курсор_ПолучаемКоличествоСотрудниковВАтбеле.getCount();
                Log.d(this.getClass().getName(), "ОбщееКоличествоСОтрудниковДляСкролаПотабелю " + ОбщееКоличествоСОтрудниковДляСкролаПотабелю);
            }else {
                ОбщееКоличествоСОтрудниковДляСкролаПотабелю=0;
            }

            Log.d(this.getClass().getName(), "    " +   UUIDТекущегоВыбраногоСотрудника+
                    "  ПроизошелЛиСфайпПоДаннымСингТабеля " + ПроизошелЛиСфайпПоДаннымСингТабеля);
            Log.d(this.getClass().getName(), "  " +  UUIDТекущегоВыбраногоСотрудника+
                    " ПроизошелЛиСфайпПоДаннымСингТабеля " +ПроизошелЛиСфайпПоДаннымСингТабеля);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйКурсорДанныеSwipes "+ ГлавныйКурсорДанныеSwipes+ " ОбщееКоличествоСОтрудниковДляСкролаПотабелю " +ОбщееКоличествоСОтрудниковДляСкролаПотабелю);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОбщееКоличествоСОтрудниковДляСкролаПотабелю;
    }

    private void МетодАнализДанныхSwipes( )
            throws ParseException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ExecutionException, InterruptedException {
        try{
            if  (  ПроизошелЛиСфайпПоДаннымСингТабеля==true ){
                    if (  ГлавныйКурсорДанныеSwipes.getCount()>0) {
                        int ИНдексГдеНаходитсьяКолонкаUUIDТабеляПриСфайпе= ГлавныйКурсорДанныеSwipes.getColumnIndex("uuid");
                        UUIDТекущегоВыбраногоСотрудника=     ГлавныйКурсорДанныеSwipes.getLong(ИНдексГдеНаходитсьяКолонкаUUIDТабеляПриСфайпе);
                        Log.d(this.getClass().getName(), " UUIDТекущегоВыбраногоСотрудника  " +UUIDТекущегоВыбраногоСотрудника );
                        // TODO: 23.03.2023 заполяем
                        ///TODO   #6   запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
                        МетодСуммаЧасовВТабеле(ГлавныйКурсорДанныеSwipes);
                        МетодЗаполенияДаннымиСотрудника(  ЦифровоеИмяНовгоТабеля,ГлавныйКурсорДанныеSwipes);
                        МетодСлушательДанных();
                        МетодСлушательСвайпов();
                        МетодПолучениеФИОиПрофессия(ГлавныйКурсорДанныеSwipes );
                    }else{
                        МетодКогдаНетЗаписейВКурсоре();
                    }

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ГлавныйКурсорДанныеSwipes "+ ГлавныйКурсорДанныеSwipes + " ОбщееКоличествоСОтрудниковДляСкролаПотабелю " + ВсеСтрокиТабеля);
            }else{
                SQLiteCursor     ГлавныйКурсорSingleSwipe=null;
                if (UUIDТекущегоВыбраногоСотрудника ==0) {
                    ГлавныйКурсорДанныеSwipes =
                            new Class_MODEL_synchronized(getApplicationContext()).
                                    МетодЗагружетУжеготовыеТабеляПриСмещенииДанныхСкроллПоДАнным(context,
                                            ЦифровоеИмяНовгоТабеля, МесяцТабеля, ГодДляТабелей);
                }else{
                    ГлавныйКурсорSingleSwipe = new Class_MODEL_synchronized(getApplicationContext()).МетодЗагружетУжеготовыеТабеля(context,UUIDТекущегоВыбраногоСотрудника
                            , МесяцТабеля, ГодДляТабелей);
                }
                if(ГлавныйКурсорSingleSwipe.getCount()>0){
                    ГлавныйКурсорSingleSwipe.moveToFirst();
                }


                if (  ГлавныйКурсорSingleSwipe.getCount()>0) {
                    ///TODO   #6   запускаем метод ПОСЛЕ УСПЕШНОЙ ГЕНЕРАЦИИ ТАБЕЛЯ
                    МетодСуммаЧасовВТабеле(ГлавныйКурсорSingleSwipe);
                    МетодЗаполенияДаннымиСотрудника(   ЦифровоеИмяНовгоТабеля,ГлавныйКурсорSingleSwipe);
                    МетодСлушательДанных();
                    МетодСлушательСвайпов();
                    МетодПолучениеФИОиПрофессия(ГлавныйКурсорSingleSwipe );

                }else{
                    МетодКогдаНетЗаписейВКурсоре();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ГлавныйКурсорSingleSwipe "+ ГлавныйКурсорSingleSwipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void МетодЗаполенияДаннымиСотрудника(@NonNull   int ЦифровоеИмяНовгоТабеля,@NonNull SQLiteCursor sqLiteCursor)
            throws ParseException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ExecutionException, InterruptedException {
        try{
            if (sqLiteCursor.getCount()>0) {
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " sqLiteCursor "+sqLiteCursor.getCount() );
                if(ХЭШНазваниеДнейНедели!=null){
                    ХЭШНазваниеДнейНедели.clear();
                }
                ХЭШНазваниеДнейНедели = Collections.synchronizedMap(new LinkedHashMap<>());

                String УниверсальноеИмяТабеля= "";
                if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудника!=null) {
                    УниверсальноеИмяТабеля=ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;
                }else if (ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки!=null) {
                    УниверсальноеИмяТабеля=    ПолноеИмяТабеляПослеСозданиеНовогоСотрудникаПослеУспешнойВставки;
                } else if (ДепартаментПришелПослеСоздангоНового !=null){
                    УниверсальноеИмяТабеля=     ДепартаментПришелПослеСоздангоНового;
                }
                НазваниеЗагруженногТАбеля = new Class_MODEL_synchronized(this).  МетодПолучениеНазваниеТабеляНаОснованииСФО(this,ЦифровоеИмяНовгоТабеля);
                Log.d(this.getClass().getName(), " НазваниеЗагруженногТАбеля" + НазваниеЗагруженногТАбеля);
                int ИндексГдеНаходитьсяСтутсПроведенных = sqLiteCursor.getColumnIndex("status_carried_out");
                СтаттусТабеля =Boolean.parseBoolean( sqLiteCursor.getString(ИндексГдеНаходитьсяСтутсПроведенных)); ///строго имя
                Log.d(this.getClass().getName(), " СтаттусТабеля" + СтаттусТабеля);
                if (СтаттусТабеля==true) {
                    Toast.makeText(getApplicationContext(), " Табель Проведен !!!. " + "\n" + "(редактирование запрещено).", Toast.LENGTH_SHORT).show();
                }
                final int[] ИндексСтрокКомпонентовТабеля = {0};
                int ИндексКолонокКомпонентовТабеля = 0;
                МетодСозданиеМесяцыСокращенно();
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1) " + ХЭШНазваниеДнейНедели.get(1));
                /////TODO ТАБЕЛЬ ФИО создаем textview названия дней понелельник вторик среда четеварг
                НазваниеДанныхВТабелеФИО = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);
                НазваниеДанныхВТабелеФИО.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                ///toDO ПОСИК И ВСТАВКИ ИМСЯ ТАБЕЛЯ
                int РасположениеИмениСотркдника = sqLiteCursor.getColumnIndex("fio");//name
                ПолученыйUUIDФИОСледующий=     sqLiteCursor.getLong(РасположениеИмениСотркдника);////  String ФИОСледующий

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " РасположениеИмениСотркдника  " + РасположениеИмениСотркдника + "  НазваниеДанныхВТабелеФИО " + НазваниеДанныхВТабелеФИО  + " ПолученыйUUIDФИОСледующий " +ПолученыйUUIDФИОСледующий);

                // TODO: 24.03.2023  Получаем ФИо и ПРОФЕСИЮ
                МетодПолучениеФИОиПрофессия( sqLiteCursor);
                // TODO: 24.03.2023  Получаем ФИо и ПРОФЕСИЮ
                МетодЗаполениеЭкранНАзваниеФИоИПрофесиии();
                // TODO: 23.03.2023  метод заполенния данными по циклу после анализа swipe
                МетодПослеАнализаSwipesЗаполненияЦиклом(  ИндексСтрокКомпонентовТабеля,sqLiteCursor);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " sqLiteCursor " +sqLiteCursor.getPosition());
            }else{
                ///TODO В УКАЗАНОМ ТАБЕЛЕ НЕТ НИОДНОГО СОТРУДНИКА СТОРОЧЕК COUNT()* 0 НЕТ СТРОЧЕК В ТАБЕЛЕ
                МетодКогдаНетЗаписейВКурсоре();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодПолучениеФИОиПрофессия( @NonNull SQLiteCursor sqLiteCursor) {
        try{
            Log.d(this.getClass().getName(), "ПолученыйUUIDФИОСледующий " + ПолученыйUUIDФИОСледующий);
            Class_GRUD_SQL_Operations classGrudSqlOperations= new Class_GRUD_SQL_Operations(getApplicationContext());
            classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                    " SELECT name,prof  FROM fio  WHERE  uuid = '" + ПолученыйUUIDФИОСледующий + "' ;");
            ///////
            SQLiteCursor            Курсор_ПолучаемИмяСотрудникаИзТаблицыФИО= (SQLiteCursor) classGrudSqlOperations.
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

    private void МетодПослеАнализаSwipesЗаполненияЦиклом(@NonNull  int[] ИндексСтрокКомпонентовТабеля ,@NonNull SQLiteCursor sqLiteCursor ) {
        try{
                /////TODO     ПЕРВАЯ СТРОКА
                ///todo главный МЕТОД ОФОРМЛЕНИЯ ТАБЕЛЯ ДАННЫМИ И ДНЯМИ
                МетодГлавныеДанныеLayoutInflater(ИндексСтрокКомпонентовТабеля,
                        ХЭШНазваниеДнейНедели,
                        НазваниеДанныхВТабелеДниНедели,
                        СамиДанныеТабеля,
                        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла,sqLiteCursor );
                МетодПерегрузкаГлавногоLaouyout();
                // TODO: 29.04.2021 если то оди
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем );
                МетодПергрузкиДизайнаЭкрана();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодПерегрузкаГлавногоLaouyout() {
        try{
            if  (  ПроизошелЛиСфайпПоДаннымСингТабеля==false ) {
                /////TODO ФИНАЛ ЗАПОЛЕНИЕ ДАННЫМИ АКТИВИТИ ЧЕРЕЗ ДРУГОЕ АКТВИТИ
                ГлавныйКонтейнерТабель.addView(КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла);
            }else {
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.refreshDrawableState();
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.forceLayout();
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.requestLayout();
                КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.dispatchWindowVisibilityChanged(1);
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйКонтейнерТабель "+ ГлавныйКонтейнерТабель
                    + " КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла " +КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла);
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
            НазваниеДанныхВТабелеФИО.setText("");
            НазваниеДанныхВТабелеФИО.setText(ФИОТекущегоСотрудника.trim() + "\n"+ "( "+Профессия.trim()+ " )"); ///строго имя
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

    void МетодСуммаЧасовВТабеле(@NonNull SQLiteCursor sqLiteCursor) {
        try{
        Integer ПозицияКурсораДО=    sqLiteCursor.getPosition();
            Integer ПозицияВизуальция=    ПозицияКурсораДО+1;
            //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
         Integer   ЧасыТекущегоСОтрудника = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(sqLiteCursor);
            Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
            TextViewЧасовСотрудникаВТабелеСотудников.setText(" (" + " " + ЧасыТекущегоСОтрудника + "/часы)  "+ПозицияВизуальция+" из "+ГлавныйКурсорДанныеSwipes.getCount()+"");
            sqLiteCursor.moveToPosition(ПозицияКурсораДО);
            Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем + " sqLiteCursor " +sqLiteCursor.getPosition()  );
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
        //КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = new View(this);//activity_main_find_customer_for_tables // activity_main_grid_for_tables
        КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла = МеханизмЗагрузкиОдногЛайАутавДругой.inflate(R.layout.activity_main_grid_for_tables_four_columns,
                ГлавныйКонтейнерТабель, false);
        НазваниеДанныхВТабелеФИО = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.КонтейнерКудаЗагружаетьсяФИО);
        /////TODO ТАБЕЛЬ ФИО создаем textview названия дней понелельник вторик среда четеварг
        ///////// todo фио
        ///TODO ЕСЛИ ИМЕНИ НЕТ ПО НА АКТИВИТИ ПОКАЗЫВАЕМТ ЧТО ТАБЕЛЬ ПУСТОЙ
        НазваниеДанныхВТабелеФИО.setTextColor(Color.RED);
        НазваниеДанныхВТабелеФИО.setText("*Табель  пустой (заполните)*"); ///строго имя
        Log.d(this.getClass().getName(), " ФИО " + "*Пустой табель (заполните)*");

        /////TODO ФИНАЛ ЗАПОЛЕНИЕ ДАННЫМИ АКТИВИТИ ЧЕРЕЗ ДРУГОЕ АКТВИТИ
        МетодПерегрузкаГлавногоLaouyout();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    void МетодСлушателиДляТекущегоТабеля(){
        try{
            /////View/////TODO ТУТ ПЕРЕРХОДИМ НА МЕТКИ ТАБЕЛЯ
             СлушательДляПереходаНаМеткиТабеля= new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    /////todo ПЕРЕХОДИМ НА МЕТКИ ТАБЕЛЯ
                    Log.d(this.getClass().getName(), " View.OnLongClickListener ");
                    HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель= МетодЛовимЗначениеВТАбелеДляДобавленияСловВТабель(v);
                    Log.d(this.getClass().getName(), "ХэшЛовимUUIDIDНазваниеСтолбикаЛокальный  " + ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());
                    ((TextView) v).setBackgroundColor(Color.GRAY);
                    Log.d(this.getClass().getName(), "ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values()  " +ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());
                    МетодПереходаНаМеткиТабеля(ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель "+ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель);

                    return true;
                }
            };
            /////View/////TODO ТУТ ПОЛУЧАЕМ КУДА ОБНОВЛЯТЬ ДНИ  D1,D2,D3
            СлушательДанныеДляОбновления= new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////todo полученине данных uuid id
                    Log.d(this.getClass().getName(), " View.OnClickListener");
                    МетодЛовимЗначениеВТАбелеUUIDиIDиНазваниеСтоблиув(v);
                }
            };

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
















    /////View/////TODO метод uuid id названеи стоблика
    public  HashMap МетодЛовимЗначениеВТАбелеUUIDиIDиНазваниеСтоблиув(View v) {
        String СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = null;//
        String ПолучениеЗначениеСтолбикID= null;//;
        String ПолучениеЗначениеСтолбикUUID= null;//;
        try{
            Log.d(this.getClass().getName(), "  MotionEvent СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления ");
            EditText ВытаскиваемЗначениеСтолбикаТабеля=null;
            ВытаскиваемЗначениеСтолбикаТабеля = (EditText) v;
            String ВытаскиваемЗначениеСтолбикаТабеляДва=null;
            ВытаскиваемЗначениеСтолбикаТабеляДва = (String) ВытаскиваемЗначениеСтолбикаТабеля.getTag();
            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва);
            if (ВытаскиваемЗначениеСтолбикаТабеляДва.length()>0) {
                СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = ВытаскиваемЗначениеСтолбикаТабеляДва.trim();
            }
            Log.d(this.getClass().getName(), " charSequenceвторойстолбик: " + СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления);
            GridLayout GridRow = (GridLayout) v.getParent();
            Log.d(this.getClass().getName(), " GridRow" + GridRow);
            int КоличествоСтобиков = GridRow.getChildCount();
            for (int ИндексПоВытаскиваниюUUIDИID = 0; ИндексПоВытаскиваниюUUIDИID < КоличествоСтобиков; ИндексПоВытаскиваниюUUIDИID++) {
                TextView СтолбикGridRow = (TextView) GridRow.getChildAt(ИндексПоВытаскиваниюUUIDИID);
                String НазваниеКолонкиGridRow = null;
                НазваниеКолонкиGridRow = String.valueOf(СтолбикGridRow.getTag());
                Log.d(this.getClass().getName(), " НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);
                switch (НазваниеКолонкиGridRow.trim()) {
                    case "uuid":
                        Log.d(this.getClass().getName(), "  ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);
                        ПолучениеЗначениеСтолбикUUID = String.valueOf(СтолбикGridRow.getText());
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +"\n"
                                + " ВытаскиваемЗначениеСтолбикаТабеляДва " +ВытаскиваемЗначениеСтолбикаТабеляДва +
                                " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
                        if(ПолучениеЗначениеСтолбикUUID !=null  && ВытаскиваемЗначениеСтолбикаТабеляДва!=null) {
                            ХэшЛовимUUIDIDНазваниеСтолбика=new HashMap<>();
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикUUID));
                            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва+
                                    " ПолучениеЗначениеСтолбикUUID " +ПолучениеЗначениеСтолбикUUID);
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;
                            ПолучениеЗначениеСтолбикUUID=null;
                        }
                        break;
                    //todo id какой стодик
                    case "_id":
                        //TODO получение знчение ID
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        ПолучениеЗначениеСтолбикID = String.valueOf(СтолбикGridRow.getText());
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        // TODO: 21.05.2021 обновление только через ID
                        if (ПолучениеЗначениеСтолбикID!=null  && ВытаскиваемЗначениеСтолбикаТабеляДва!=null){
                            ХэшЛовимUUIDIDНазваниеСтолбика=new HashMap<>();
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикID));
                            Log.d(this.getClass().getName(), "ПолучениеЗначениеСтолбикUUID" + ПолучениеЗначениеСтолбикUUID
                                    + " ПолучениеЗначениеСтолбикID "+ПолучениеЗначениеСтолбикID);
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;
                            ПолучениеЗначениеСтолбикUUID=null;
///TODO как получили значение выходим BREAK;
                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика.values() " + ХэшЛовимUUIDIDНазваниеСтолбика.values()+"ХэшЛовимUUIDIDНазваниеСтолбика.keySet()  "
                                    +ХэшЛовимUUIDIDНазваниеСтолбика.keySet());
                        }
                        break;
                    default:
                        Log.d(this.getClass().getName(), " NULL значение НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);
                }
            }
            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ХэшЛовимUUIDIDНазваниеСтолбика;
    }
    /////View/////TODO метод uuid id названеи стоблика
    public  HashMap МетодЛовимЗначениеВТАбелеДляДобавленияСловВТабель(View v) {
        //////todo заполение
        String СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = null;//
        String ПолучениеЗначениеСтолбикID= null;//;
        String ПолучениеЗначениеСтолбикUUID= null;//;
        try{
            /////todo определяем хэшмап для uuid и знпчения для
            Log.d(this.getClass().getName(), "  MotionEvent СлушательТачПолученияНазваниеСтолбикаДляЛокальногоОбновления ");
            EditText ВытаскиваемЗначениеСтолбикаТабеля=null;
            ВытаскиваемЗначениеСтолбикаТабеля = (EditText) v;
            String ВытаскиваемЗначениеСтолбикаТабеляДва=null;
            /////todo писваесмиаем d1,d,2,d4
            ВытаскиваемЗначениеСтолбикаТабеляДва = (String) ВытаскиваемЗначениеСтолбикаТабеля.getTag();
            Log.d(this.getClass().getName(), " ВытаскиваемЗначениеСтолбикаТабеляДва " + ВытаскиваемЗначениеСтолбикаТабеляДва);
            СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления = ВытаскиваемЗначениеСтолбикаТабеляДва;
            Log.d(this.getClass().getName(), " charSequenceвторойстолбик: " + СамоЗначениеСтолбикаТабеляДляЛокальногоОбновления);
            GridLayout GridRow = (GridLayout) v.getParent();
            Log.d(this.getClass().getName(), " GridRow" + GridRow);
            int КоличествоСтобиков = GridRow.getChildCount();
            for (int ИндексПоВытаскиваниюUUIDИID = 0; ИндексПоВытаскиваниюUUIDИID < КоличествоСтобиков; ИндексПоВытаскиваниюUUIDИID++) {
                TextView СтолбикGridRow = (TextView) GridRow.getChildAt(ИндексПоВытаскиваниюUUIDИID);
                String НазваниеКолонкиGridRow = "";
                НазваниеКолонкиGridRow = String.valueOf(СтолбикGridRow.getTag());
                /////TODO ОПРЕДЕЛЯЕМ КАКАЯ СЕЙЧАС ЯЧЕЙКА В ОБОРАБТКИ ID ИЛИ UUID
                Log.d(this.getClass().getName(), " НазваниеКолонкиGridRow " + НазваниеКолонкиGridRow);
                //todo выбор
                switch (НазваниеКолонкиGridRow.trim()) {
                    case "_id":
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        ПолучениеЗначениеСтолбикID = String.valueOf(СтолбикGridRow.getText());
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
                        if (ВытаскиваемЗначениеСтолбикаТабеляДва!=null  && ПолучениеЗначениеСтолбикID!=null) {
                            Log.d(this.getClass().getName(), "      ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ
                            ХэшЛовимUUIDIDНазваниеСтолбика.clear();
                            /////TODO ЗАПОЛЯЕМ
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва, Long.parseLong(ПолучениеЗначениеСтолбикID));
                            //////todo обнуляем переменные
                            ВытаскиваемЗначениеСтолбикаТабеляДва = null;
                            ПолучениеЗначениеСтолбикID = null;
                            ///////
                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                        }
                        break;
                    //todo fio какой стодик
                    case "uuid":
                        //TODO получение знчение  UUID
                        Log.d(this.getClass().getName(), "  ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID);
                        ПолучениеЗначениеСтолбикUUID = String.valueOf(СтолбикGridRow.getText());
                        Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикUUID " + ПолучениеЗначениеСтолбикUUID +"\n"
                                + " ВытаскиваемЗначениеСтолбикаТабеляДва " +ВытаскиваемЗначениеСтолбикаТабеляДва +
                                " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
                        ///todo заполения Hasmap uuid and id
                        if (ВытаскиваемЗначениеСтолбикаТабеляДва!=null  && ПолучениеЗначениеСтолбикUUID!=null){
                            Log.d(this.getClass().getName(), "      ХэшЛовимUUIDIDНазваниеСтолбика" +    ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                            // TODO: 21.05.2021 перед вставка очищаем ХЭШ
                            ХэшЛовимUUIDIDНазваниеСтолбика.clear();
                            /////TODO ЗАПОЛЯЕМ
                            ХэшЛовимUUIDIDНазваниеСтолбика.put(ВытаскиваемЗначениеСтолбикаТабеляДва ,Long.parseLong(ПолучениеЗначениеСтолбикUUID ));
                            //////todo обнуляем переменные
                            ВытаскиваемЗначениеСтолбикаТабеляДва=null;
                            ПолучениеЗначениеСтолбикUUID=null;
                            Log.d(this.getClass().getName(), " ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                        }
                        break;
                }
            }
            Log.d(this.getClass().getName(), " ПолучениеЗначениеСтолбикID " + ПолучениеЗначениеСтолбикID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ХэшЛовимUUIDIDНазваниеСтолбика;
    }
    // TODO КОД СЛУШАТЕЛЬ ПРИ НАЖАТИИ НА ТАБЕЛЬ Создаем экземпляр TextWatcher:


    void МетодСлушательДанных(){
        СлушательПолученияДанных=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    Log.d(this.getClass().getName(), "  beforeTextChanged  " + s.toString());
                    ПолучениеЗначениеДоИзменения= "";
                    ПолучениеЗначениеДоИзменения=s.toString();
                    Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения  " +ПолучениеЗначениеДоИзменения);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                    Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    Log.d(this.getClass().getName(),"  onTextChanged  " +s.toString());
// TODO: 08.02.2022  получаем само значениеит новое которое и надо ВСАТВКИТЬ
                    Log.d(this.getClass().getName(),"  afterTextChanged " +s.toString());
                    СамоЗначениеЯчейкиТабеля= null;
                    СамоЗначениеЯчейкиТабеля= s.toString();
                    Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                    //TODO ОЦЕНКА ЧТО С ДАННЫМИ
                    if (ПолучениеЗначениеДоИзменения.matches("(.*)[^0-9](.*)" )) {
                        Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения " +ПолучениеЗначениеДоИзменения);
                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                        if (СамоЗначениеЯчейкиТабеля.matches("(.*)[0-9](.*)" )) {
                            СамоЗначениеЯчейкиТабеля = СамоЗначениеЯчейкиТабеля.replaceAll("[^0-9]", "");
                        }
                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                    }
                    Log.d(this.getClass().getName()," ПолучениеЗначениеДоИзменения " +ПолучениеЗначениеДоИзменения);
                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля.length()" +  СамоЗначениеЯчейкиТабеля.length());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                    Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //TODO ДАННЫЙ КОД НЕПОСТРЕДСТВЕННО ЗАРУСКАЕТ ОБНОВЛЕНИЕ ЛОКАЛЬНОЕ С АКТИВТИИ
                int СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов = 0;
                boolean ФлагЗапускатьллкальноеОбновлениеИлинет=false;
                try{
         /*      МетодГлавныйЛокальноеОбновлениеЯчейки(СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов, ФлагЗапускатьллкальноеОбновлениеИлинет);
                Log.d(this.getClass().getName(), " МетодГлавныйЛокальноеОбновлениеЯчейки () Save.. СамоЗначениеЯчейкиТабеля" +  СамоЗначениеЯчейкиТабеля + " s "+s.toString());*/
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
                    Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

                }
            }
        };

    }

    private void МетодГлавныйЛокальноеОбновлениеЯчейки(int СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов, boolean ФлагЗапускатьллкальноеОбновлениеИлинет) {
        try{
            Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" +  СамоЗначениеЯчейкиТабеля);
            if (СамоЗначениеЯчейкиТабеля!=null) {
                if (СамоЗначениеЯчейкиТабеля.length()>0 ) {
                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля.length()" +  СамоЗначениеЯчейкиТабеля.length());
                    /////////todo получем значение И СТАВИМ УСЛОВИЕ НЕ БОЕЛЕЕ 24 ЧИЛСА КАК КАК В СУТКА ТОЛЬКО 24 ЧАСА
                    boolean ПереводимВЦифруТОлькоЕслиИзначальноНетБУквыВнутри=         СамоЗначениеЯчейкиТабеля.matches("(.*)[^0-9](.*)");
                    if (ПереводимВЦифруТОлькоЕслиИзначальноНетБУквыВнутри==false) {
                        СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов = Integer.parseInt(СамоЗначениеЯчейкиТабеля);
                    }
                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов " + СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов);
                    if (СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов <= 24) {
                        Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                        ФлагЗапускатьллкальноеОбновлениеИлинет =true;
                        /////TODO  НЕ НАДЛО ЗАПУСКАТЬ ЛОКАЛЬНЕО ОБНОВЛЕНИЕ
                    } else {
                        ФлагЗапускатьллкальноеОбновлениеИлинет =false;
                        Toast aa = Toast.makeText(getBaseContext(), "OPEN", Toast.LENGTH_LONG);
                        ImageView cc = new ImageView(getBaseContext());
                        cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);//icon_dsu1_synchronisazia_dsu1_success
                        aa.setView(cc);
                        aa.show();
                        Toast.makeText(getBaseContext(), "Нет сохранилось"+
                                "\n"+" (Значение не может быть более 24 (часы).) :" +СамоЗначениеЯчейкиТабеля, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " +  СамоЗначениеЯчейкиТабеля);
                    ФлагЗапускатьллкальноеОбновлениеИлинет =true;
                }
            }
            Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" +СамоЗначениеЯчейкиТабеля + "  ФлагЗапускатьллкальноеОбновлениеИлинет " + ФлагЗапускатьллкальноеОбновлениеИлинет);
//TODO запускаем локальное обновдене если есть на это все условия
            if( ФлагЗапускатьллкальноеОбновлениеИлинет ==true){
                ФлагЗапускатьллкальноеОбновлениеИлинет =false;
                Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов" + СамоЗначениеЯчейкиТабеляЗначениеНЕБольше24Часов);
                ////// TODO: 20.01.2021 полученое занчение пытаемся обрезать
                Log.d(this.getClass().getName(),"  СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
                //TODO ограниваем количество символом не более 3 символом не сохраняет
                if (СамоЗначениеЯчейкиТабеля.length()>=3){
                    СамоЗначениеЯчейкиТабеля = СамоЗначениеЯчейкиТабеля.substring(0, 2);
                }
                Log.d(this.getClass().getName(),"  СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля + " ХэшЛовимUUIDIDНазваниеСтолбика.size() " +ХэшЛовимUUIDIDНазваниеСтолбика.values());
                ///todo получаем хэш с название стольика и uuid
                Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбика.size()  " + ХэшЛовимUUIDIDНазваниеСтолбика.size());
                Log.d(this.getClass().getName(), " Локальное Обновление ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика+
                        " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
                if (СамоЗначениеЯчейкиТабеля.length()==0 || СамоЗначениеЯчейкиТабеля==null) {
                    СамоЗначениеЯчейкиТабеля="0";
                }
                Log.d(this.getClass().getName(), " Локальное Обновление ХэшЛовимUUIDIDНазваниеСтолбика" + ХэшЛовимUUIDIDНазваниеСтолбика+
                        " СамоЗначениеЯчейкиТабеля " +СамоЗначениеЯчейкиТабеля);
// TODO: 03.06.2021  елси значение нул то мимы из него делаем ноль  ВАЖНО ТУТ УКАЗАНЫ УСЛОВИЯ ПО КОТОРЫМ ПРОИЗВХОДТИ ЛОКАЛЬНОЕ ОБНОВЛЕНИЕ
                ArrayList<Integer> ЛистСДопустипыЗначениямиДляЛокальноСохранения=new ArrayList();
                for (int ДниДопустимые=0;ДниДопустимые<=24;ДниДопустимые++) {
                    ЛистСДопустипыЗначениямиДляЛокальноСохранения.add(ДниДопустимые);
                }
                Log.d(this.getClass().getName(), " ЛистСДопустипыЗначениямиДляЛокальноСохранения" + ЛистСДопустипыЗначениямиДляЛокальноСохранения.toString());

                boolean      РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет=false;

                СамоЗначениеЯчейкиТабеля = СамоЗначениеЯчейкиТабеля.replace("\"", "");
                boolean ПроверяютЦифраИлиБуква=      СамоЗначениеЯчейкиТабеля.chars().allMatch( Character::isDigit );
                СамоЗначениеЯчейкиТабеля.chars().map(new IntUnaryOperator() {
                    @Override
                    public int applyAsInt(int operand) {
                        Log.d(this.getClass().getName(), " operand" + operand );
                        return 0;
                    }
                });

                if (ПроверяютЦифраИлиБуква=true  ) {
                    РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет = ЛистСДопустипыЗначениямиДляЛокальноСохранения.contains(Integer.parseInt(СамоЗначениеЯчейкиТабеля));
                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" + СамоЗначениеЯчейкиТабеля.toString()+
                            " РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет " +РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет);
                }else {
                    Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" + СамоЗначениеЯчейкиТабеля.toString()+
                            " РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет " +РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет);
                }
                Log.d(this.getClass().getName(), " РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет" + РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет);
                if (ХэшЛовимUUIDIDНазваниеСтолбика.size() ==1 && СамоЗначениеЯчейкиТабеля.length()>0
                        && СамоЗначениеЯчейкиТабеля.length() <= 2 && РезультатПроверкиЕслиТАкоеЧислоВАрайлистеВставлятьИлиНет==true) {

                    ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении = new     ContentValues();
                    Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбика " + ХэшЛовимUUIDIDНазваниеСтолбика.toString());
                    Iterator iterator = ХэшЛовимUUIDIDНазваниеСтолбика.entrySet().iterator();
                    // TODO: 20.05.2021  начинаем обновлять только по одну запись если более одной пропускаем
                    if (ХэшЛовимUUIDIDНазваниеСтолбика.size()==1) {
                        while (iterator.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            System.out.println(String.format("(%s, %s)", entry.getKey(), entry.getValue()));
                            String КлючПоляДляЛокальноОбновление=String.valueOf(entry.getKey());
                            /////todo заполегния контейнера
                            if (СамоЗначениеЯчейкиТабеля.length()>0) {
                                КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put(КлючПоляДляЛокальноОбновление, СамоЗначениеЯчейкиТабеля);
                                КлючПоляДляЛокальноОбновление=null;
                                СамоЗначениеЯчейкиТабеля=null;
                            }else if (СамоЗначениеЯчейкиТабеля.length()==0){
                                КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put(КлючПоляДляЛокальноОбновление, 0);
                                КлючПоляДляЛокальноОбновление=null;

                            }
                            Log.d(this.getClass().getName(), " СамоЗначениеЯчейкиТабеля" + СамоЗначениеЯчейкиТабеля+ " КонтейнерЗаполненияДаннымиПриЛокальномОбновлении " +КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.toString());
                            //TODO заполение КОНТЕНЕР для локального обновления--дАТА оПЕРАЦИИ
                            ////TODO ДАТА
                            String СгенерированованныйДатаДляВставки=     new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                            КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put("date_update", СгенерированованныйДатаДляВставки);
                            // TODO: 21.05.2021
                            Long РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника=0L;
                            Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДляЛокальногоОбволенияТабеля=new Class_GRUD_SQL_Operations(getApplicationContext());

                            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                            РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника =
                                    new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    "data_tabels",getApplicationContext(),Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника  " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                            КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.put("current_table", РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                            Log.d(this.getClass().getName(), "  РезультатУвеличинаяВерсияДАныхЧата " + РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                            System.out.println(String.format("(%s, %s)", entry.getKey(), entry.getValue()));
                            Log.d(this.getClass().getName(), "  СамоЗначениеЯчейкиТабеля " + СамоЗначениеЯчейкиТабеля+
                                    "  РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника " +РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);
                            String СодержимоеДляЛокальногоОбновленияВременный = String.valueOf(entry.getValue());
                            UUIDТекущегоВыбраногоСотрудника = Long.parseLong(СодержимоеДляЛокальногоОбновленияВременный);
                            Log.d(this.getClass().getName(), "  UUIDТекущегоВыбраногоСотрудника " + UUIDТекущегоВыбраногоСотрудника);
                            if (UUIDТекущегоВыбраногоСотрудника > 0){
                                try {
                                    Long РезультатЛокальногоОбновлениеНаТабеле= null;
                                    if (UUIDТекущегоВыбраногоСотрудника>0 && КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.size()>0 ) {
                                        // TODO: 13.01.2022  ГЛАВНЫЙ МЕТОД ЗАПУСКА ЛОКАЛЬНОГО ОБНОВЛЕНИЯ
                                        РезультатЛокальногоОбновлениеНаТабеле =
                                                МетодЛокальногоОбновлениеЧерезКликвТабеле(КонтейнерЗаполненияДаннымиПриЛокальномОбновлении, UUIDТекущегоВыбраногоСотрудника.toString());
                                    }
                                    Log.d(this.getClass().getName(), "  РезультатЛокальногоОбновлениеНаТабеле" + РезультатЛокальногоОбновлениеНаТабеле);
                                    if (РезультатЛокальногоОбновлениеНаТабеле>0) {
                                        КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                                        ПолучениеЗначениеДоИзменения= null;
                                        СамоЗначениеЯчейкиТабеля=null;
                                        Class_GRUD_SQL_Operations  classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля;
                                        classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля=new Class_GRUD_SQL_Operations(getApplicationContext());
                                        classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
                                        classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагТипИзменениеВерсииДанныхЛокальнаяСервернаяИлиОба","Локальное");///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                                        classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put(" " +
                                                        "ПередоваемоеЗначенияДляТаблицы_MODIFITATION_Client_КотороеНадоЗаписать",РезультатУвеличинаяВерсияВнутриСамогоТабелСтрудника);///  "ЛокальныйСерверныйОба"    ПОСЛЕ КАК ПРИШЛИ ВНЕШНИЕ ДАННЫЕ
                                        ///TODO РЕЗУЛЬТА изменения версии данных
                                        Integer        Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы=
                                                (Integer)  classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                        new ChangesVesionData(getApplicationContext()).
                                                        changesvesiondata(classGrudSqlOperationsПовышаемВерсиюДАнныхПриЛокальноОбновлениеииДанныхВнутриТабеля.
                                                                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                                                Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                                                                ,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
//
                                        Log.d(getApplicationContext().getClass().getName(), "Результат_ПриписиИзменнийВерсииДанныхВФонеПриСменеОрганизации "
                                                +Результат_ПриписиИзменнийВерсииДанныхВФонеПослеОбработкиТекущийТаблицы );
                                    }else {
                                        Toast.makeText(getApplicationContext(), " Ошибка  в добавление данных !!! " , Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),

                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        }//todo end while()
                    }
                    Log.d(getApplicationContext().getClass().getName(), "  Конец ЛОкальному ОБновлению  внутри табеля  " );
                }///TODO END IF  ПЕРЕД ЛОКАЛЬЫМ ОБНОВЛЕНИЕМ
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
    }
    View.OnClickListener СлушательИнформацияОСотрудника = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Class_GRUD_SQL_Operations class_grud_sql_operationsСлушательИнформацияОСотрудника=new Class_GRUD_SQL_Operations(getApplicationContext());
            try{
                НазваниеДанныхВТабелеФИО.setBackgroundColor(Color.GRAY);
                Long ФИО=0l;
                if (МыУжеВКодеУденияСотрудника==false) {
                    TextView ФИОДляУдаление = (TextView) v;
                    Log.d(this.getClass().getName(), " v " + v.getTag() + " ФИОДляУдаление.getText() " + ФИОДляУдаление.getText() +
                            "  ФИОДляУдаление.getTag() " +ФИОДляУдаление.getTag());
                    //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                    Long UUIDИзTabelView=Long.parseLong(ФИОДляУдаление.getTag().toString());
                    Bundle bundleTabelView=new Bundle();
                    bundleTabelView.putString("СамЗапрос","  SELECT fio FROM  viewtabel WHERE uuid=? ");
                    bundleTabelView.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(UUIDИзTabelView)});
                    bundleTabelView.putString("Таблица","viewtabel");
                    Cursor  КурсорТаблицаViewtabel=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleTabelView);
                    Log.d(this.getClass().getName(), " КурсорТаблицаViewtabel" + КурсорТаблицаViewtabel);
                    Log.d(this.getClass().getName(), "КурсорТаблицаViewtabel "  +КурсорТаблицаViewtabel);
                    //TODO  ОЧИЩАЕМ ПАМТЬ Результат Курсора
                    if (КурсорТаблицаViewtabel.getCount()>0){
                        КурсорТаблицаViewtabel.moveToFirst();
                        ФИО=      КурсорТаблицаViewtabel.getLong(0);
                        Log.d(this.getClass().getName(), "ФИО "  +ФИО);
                    }
                    Bundle bundleФио=new Bundle();
                    bundleФио.putString("СамЗапрос","  SELECT * FROM  fio WHERE uuid=? ");
                    bundleФио.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ФИО)});
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
               /* КурсорТаблицаФИО.close();
                КурсорТаблицаViewtabel.close();*/
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }};
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
                    ОбщееКоличествоЛюдейВТабелеТекущем = new Class_MODEL_synchronized(getApplicationContext()).МетодПосчётаЧасовПоСотруднику(ГлавныйКурсорДанныеSwipes);
                    Log.d(this.getClass().getName(), "  ОбщееКоличествоЧасовСотрудникаВТабелеСотудников " + TextViewЧасовСотрудникаВТабелеСотудников);
                    МетодСуммаЧасовВТабеле(ГлавныйКурсорДанныеSwipes);
                    // TODO: 07.05.2021 обнуляем UUID после созданеия подтчета часов
                    Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем );
                    МетодСуммаЧасовВТабеле(ГлавныйКурсорДанныеSwipes);
                    // TODO: 25.09.2021
                    Log.d(Class_MODEL_synchronized.class.getName()," ОбщееКоличествоЛюдейВТабелеТекущем  "+ОбщееКоличествоЛюдейВТабелеТекущем);
                    /////TODO ОБНУЛЯЕМ ЗНАЧЕНИЕ ID AND UUID ЧТОБЫ НЕ БЫЛО ПОВТОРОНОГО ОБНОЛЕНИЕ НЕ СВОЕГО ХОЗЯИНА UUID
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                    СамоЗначениеЯчейкиТабеля = null;
                    ПолучениеЗначениеДоИзменения = null;
                    ScrollСамогоТабеля.requestLayout();
                    ГлавныйКонтейнерТабель.requestLayout();
                } else {
                    Toast.makeText(getApplicationContext(), " Ошибка обновление ячейки не произошло !!!!! " , Toast.LENGTH_SHORT).show();
                    КонтейнерЗаполненияДаннымиПриЛокальномОбновлении.clear();
                    СамоЗначениеЯчейкиТабеля = null;
                    ПолучениеЗначениеДоИзменения = null;
                    ХэшЛовимUUIDIDНазваниеСтолбика.clear();
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











    ///todo Метод Который Содаенм НАзвание дней недели сокращенно Пн,01,ВТ,03
    private  void МетодСозданиеМесяцыСокращенно() throws ParseException {
        try {
            int МЕсяцДЛяПоказатаВТАбле=   МетодПоказатьМесяцДляЗАписивОднуКолонку(МесяцТабеляФинал);
            int ГодДЛяПоказатаВТАбле=    МетодПоказатьГодДляЗАписивОднуКолонку(МесяцТабеляФинал);
            Log.d(this.getClass().getName(), " МЕсяцДЛяПоказатаВТАбле " +МЕсяцДЛяПоказатаВТАбле +" ГодДЛяПоказатаВТАбле " +ГодДЛяПоказатаВТАбле);
            int ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодДЛяПоказатаВТАбле,МЕсяцДЛяПоказатаВТАбле);
            SimpleDateFormat СозданияВычисляемВыходные=null;
            ///////TODO сам цикл который заполняет месяцами
            for (int ИндексДней=1;ИндексДней<ПолученоеКоличествоДнейНаКонкретныйМЕсяц+1;ИндексДней++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }else {
                    СозданияВычисляемВыходные = new java.text.SimpleDateFormat("yyyy-MM-dd",new Locale("rus"));
                }
                Date   ДатаПосикаВыходныеДней       = СозданияВычисляемВыходные.parse (ГодДЛяПоказатаВТАбле+"-"+МЕсяцДЛяПоказатаВТАбле+"-"+ИндексДней );
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
            КоличествоДнейвЗагружаемойМесяце=МетодПолучениеКоличествоДнейвЗагружаемомМесяце(МЕсяцДЛяПоказатаВТАбле ,ГодДЛяПоказатаВТАбле);
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











    //////TODO СООБЩЕНИЕ ДЛЯ ВЫБОРА ЧТО ДЕЛАТЬ СОЗЖАВАТЬ НОВОГО ПОЛЬЗОВАТЕЛЕЯ ИЛИ ПОДОБРАТЬ УЖЕ СОЗДАННОГО СОТРУДНИКА
    @UiThread
    public void СообщениеДляВыбораСозданиеНовогоСотрудникаИлиЕгоПодобратьДляТабеля(String ШабкаДиалога,boolean Флаг,String UUIDТабеляФинал ,
                                                                                   String МесяцТабеляФинал, String ДепартаментТабеляФинал ) {//  ////MESSAGEBOX ДЛЯ ИНТЕРНЕТА  ПОДКЛЮЧЕНИЕ не успешное нет связи с итрнтнето и/или файлом json
        try{
            //super.MessageBoxs(ШабкаДиалога, СообщениеДиалога, Выбор);
            int ЦветЗначек;
            String ТекстПродолжениеОбновление;
            final AlertDialog DialogBox = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setPositiveButton( "Новый Сотрудник ?", null)
                    .setNegativeButton("Выбрать Сотрудника ?", null)
                    .setNeutralButton("Закрыть", null)
                    .setIcon(R.drawable.icon_dsu1_new_customer2)
                    .show();

            ///TODO КНОПКА выбор  ДОБАВЛЕНИЕ УЩЕ СУЩЕСТВУЮЩЕГО СОТРУДНИКА
            final Button КнопкаПодобратьНовогоСотрудникаДляТАбеля = DialogBox.getButton(AlertDialog.BUTTON_NEGATIVE);
            DialogBox.getButton(Dialog.BUTTON_NEGATIVE).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_NEGATIVE).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаПодобратьНовогоСотрудникаДляТАбеля.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка закрыть");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //TODO ПОДОБРАТЬ СОТРУДНИКА СУЩЕСТЮЩЕГО
                    Log.d(this.getClass().getName(), " " );

                    Intent Интент_ЗапускПодобратьУжеСуществующегоСотрудника = new Intent();

                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.setClass(getApplication(), MainActivity_Find_Customers.class);

                    // передача объекта с ключом "hello" и значением "Hello World"
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ДепартаментТабеляФинал", ДепартаментТабеляФинал);
                    Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + ДепартаментТабеляФинал);
                    //////////todo разница если новый сотрудник и ранее созданный
                    if (UUIDТабеляФинал!=null){
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляФинал);
                        Log.d(this.getClass().getName(), "UUIDТабеляФинал" + UUIDТабеляФинал);
                    }else if (UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя!=null){
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
                        Log.d(this.getClass().getName(), " UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя "
                                + UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
                    }else{
                        Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляПослеУспешногоСозданиеСотрудника);
                        Log.d(this.getClass().getName(), "UUIDТабеляПослеУспешногоСозданиеСотрудника" + UUIDТабеляПослеУспешногоСозданиеСотрудника);
                    }

                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
                    Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
                    Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
                    ////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("МЕсяцДляКурсораТабелей", МЕсяцДляКурсораТабелей);
                    Log.d(this.getClass().getName(), "  МЕсяцДляКурсораТабелей  " + МЕсяцДляКурсораТабелей);
                    /////
                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.putExtra("ГодДляКурсораТабелей", ГодДляТабелей);
                    Log.d(this.getClass().getName(), "ГодДляКурсораТабелей " + ГодДляТабелей);


                    Интент_ЗапускПодобратьУжеСуществующегоСотрудника.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    /////
                    startActivity(Интент_ЗапускПодобратьУжеСуществующегоСотрудника);


                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )
                }
            });








/////TODO КНОПКА СОЗДАНИЯ НОВОГО СОТРУДКНИКА
            final Button КнопкаСозданиеНовогоСотрудникаДляТАбеля = DialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
            DialogBox.getButton(Dialog.BUTTON_POSITIVE).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_POSITIVE).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаСозданиеНовогоСотрудникаДляТАбеля.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка добавить нового ");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //////TODO В ДАННОМ КОДЕ МЫ ДОБАВЛЯЕМ УЖЕ СУЩЕСТВУЮЩЕГО СОТРУДКА В ТАБЕЛЬ НОВОГО СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ
                    //////TODO В ДАННОМ КОДЕ МЫ СОЗДАЕМ НОВОГО СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ

                    ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
                    Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
                    Интент_ЗапускСозданиеНовогоСотрудника.setClass(getApplication(), MainActivity_New_Cusomers.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                    // передача объекта с ключом "hello" и значением "Hello World"
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляФинал", ДепартаментТабеляФинал);
                    Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + ДепартаментТабеляФинал);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляФинал", UUIDТабеляФинал);
                    Log.d(this.getClass().getName(), "UUIDТабеляФинал" + UUIDТабеляФинал);
                    /////
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
                    Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
                    /////
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
                    Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
                    Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
                    Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);

                    Интент_ЗапускСозданиеНовогоСотрудника.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


                    /////
                    startActivity(Интент_ЗапускСозданиеНовогоСотрудника);


                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )
                }
            });


            ///TODO КНОПКА ЗАКРЫТИЕ СООБЩЕНИЯ
            final Button КнопкаЗакрытьСообщкение = DialogBox.getButton(AlertDialog.BUTTON_NEUTRAL);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTextSize(12);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTextColor(Color.GRAY);
            DialogBox.getButton(Dialog.BUTTON_NEUTRAL).setTypeface(null, Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
            КнопкаЗакрытьСообщкение.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "  Кнопка добавить уже сущетсвующего сотрудника ");
                    //удаляем с экрана Диалог
                    DialogBox.dismiss();
                    //////TODO В ДАННОМ КОДЕ МЫ добавлем существующего СОТДУНИКА ДЛЯ ДОБАВЛЕНИЕ ЕГО В  ТАБЕЛЬ
                    ////--- ТУТ СТРАРТУЕТ НАЧАЛО ОБМЕНА МЕЖДУ АНДРОЙДОМ И СЕРВЕРОМ ( СИНХРОНИЗАЦИЯ ДАННЫХ  )

                }
            });


        } catch (Exception e) {
            //  Block of code to handle errors
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
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
                                НазваниеДанныхВТабелеФИО.setBackgroundColor(Color.WHITE);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " НазваниеДанныхВТабелеФИО  " + НазваниеДанныхВТабелеФИО);
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
                                НазваниеДанныхВТабелеФИО.setBackgroundColor(Color.WHITE);
                                Bundle bundleПрофесии=new Bundle();
                                bundleПрофесии.putString("СамЗапрос","  SELECT * FROM  prof WHERE uuid!=? ");
                                bundleПрофесии.putStringArray("УсловияВыборки" ,new String[]{"0"});
                                bundleПрофесии.putString("Таблица","prof");
                                Cursor    КурсорТаблицаПрофесии=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(context, bundleПрофесии);
                                Log.d(this.getClass().getName(), " КурсорТаблицаПрофесии" + КурсорТаблицаПрофесии);
                                // TODO: 27.03.2023 Новый ПОсик
                                new SubClassNewSearchAlertDialogНовыйПосик().МетодСообщениеНовыйПоиска(activity,КурсорТаблицаПрофесии ,message,"prof",РодительскийUUDТаблицыТабель);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        " НазваниеДанныхВТабелеФИО  " + НазваниеДанныхВТабелеФИО);
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




    ///TODOVTNJL ПЕРЕХОДА НА АКТИВТИ МЕТКИ ТАБЕЛЯ

    private void МетодПереходаНаМеткиТабеля(HashMap<String,Long> ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель) {
        Intent IntentЗапускаемСправочникДЛяСловТабеля=new Intent();
        try{
            IntentЗапускаемСправочникДЛяСловТабеля.setClass(context, MainActivity_Metki_Tabel.class); // Т
            Log.d(this.getClass().getName(), "  ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель " +ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель.values());
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("HashMap", ХэшЛовимUUIDIDНазваниеСтолбикаЛокальныйСловоВставитьТабель);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ДепартаментТабеляПослеПодбора", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляПослеПодбора", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляПослеПодбораУниверсальный",  UUIDCтарыйУжеСозданногоТабеляВКоторыйИНужноДобавитьНовгоПользователя);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("МесяцТабеляПослеПодбора", МесяцТабеляФинал);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолноеНазваниеЗагруженногТАбеляПослеПодбора",ПолноеИмяТабеляПослеСозданиеНовогоСотрудника );
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Log.d(this.getClass().getName(), "  UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("РодительскийUUDТаблицыТабель",UUIDТекущегоВыбраногоСотрудника  );
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " +UUIDТекущегоВыбраногоСотрудника  );
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляКнопкаBACKУниверсальный",UUIDТекущегоВыбраногоСотрудника  );
            Log.d(  this.getClass().getName(), " ПолноеНазваниеЗагруженногТАбеля" +ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            ///////TODO отправляем данные через интент в АКТИВТИ  МЕТКА ТАБЕЛЯ
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ДепартаментТабеляИзВсехСотрудниковВТАбеле", ПолноеИмяТабеляПослеСозданиеНовогоСотрудника);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("UUIDТабеляФиналПослеВыбораИзВсехСотрудниковВТАбеле",UUIDТекущегоВыбраногоСотрудника );
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле", МесяцТабеляФинал);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("IDЧьиДанныеДляСотрудников",IDЧьиДанныеДляСотрудников);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ГодФиналИзВсехСотрудниковВТАбеле", ГодДляЗагрузкиТабелей);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ЦифровоеИмяНовгоТабеля",    ЦифровоеИмяНовгоТабеля );
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("РодительскийUUDТаблицыТабель", РодительскийUUDТаблицыТабель);
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            // TODO: 20.10.2021 new Значение при движение по кругу месяц сохраняем
            IntentЗапускаемСправочникДЛяСловТабеля.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            IntentЗапускаемСправочникДЛяСловТабеля.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////todo запускаем активти
            Bundle data=new Bundle();
            IntentЗапускаемСправочникДЛяСловТабеля.putExtras(data);
            startActivity( IntentЗапускаемСправочникДЛяСловТабеля);
            ХэшЛовимUUIDIDНазваниеСтолбика=null;
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
                МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника();


                //todo
            }


        });
    }
////todo конец фильаного сообщения о удалени самого табеля


    void МетодЗапускаетСотрудниковПослеУспешногоУдалениеСотрудника() {
        try{
            ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
            Intent Интент_ЗапускСозданиеНовогоСотрудника = new Intent();
            Интент_ЗапускСозданиеНовогоСотрудника.setClass(context, MainActivity_List_Peoples.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляФинал", НазваниеЗагруженногТАбеля);
            Log.d(this.getClass().getName(), " ДепартаментТабеляФинал " + НазваниеЗагруженногТАбеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МесяцТабеляФинал", МесяцТабеляФинал);
            Log.d(this.getClass().getName(), "МесяцТабеляФинал" + МесяцТабеляФинал);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ДепартаментТабеляВКоторомИНадоСоздатьНовогоСотрудника", НазваниеЗагруженногТАбеля);
            Log.d(this.getClass().getName(), "  НазваниеЗагруженногТАбеля  " + НазваниеЗагруженногТАбеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляПослеУспешногоСозданиеСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляГлавыйРодительскгоТабеля",UUIDТекущегоВыбраногоСотрудника  );
            Log.d(this.getClass().getName(), "  UUIDТабеляПослеУспешногоСозданиеСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника", UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Log.d(this.getClass().getName(), "  UUIDТабеляВКоторомИНадоСоздатьНовогоСотрудника " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("РодительскийUUDТаблицыТабель",UUIDТекущегоВыбраногоСотрудника  );
            Log.d(this.getClass().getName(), "  РодительскийUUDТаблицыТабель " + UUIDТабеляПослеУспешногоСозданиеСотрудника);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ЦифровоеИмяНовгоТабеляSingle", ЦифровоеИмяНовгоТабеля);
            Log.d(this.getClass().getName(), "  ЦифровоеИмяНовгоТабеля " + ЦифровоеИмяНовгоТабеля);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре",ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Log.d(this.getClass().getName(), "  ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);
            Интент_ЗапускСозданиеНовогоСотрудника.putExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле",МесяциГодТабеляПолностью);
            Log.d(this.getClass().getName(), "  МесяциГодТабеляПолностью " + МесяциГодТабеляПолностью);
            Интент_ЗапускСозданиеНовогоСотрудника.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle data=new Bundle();
            Интент_ЗапускСозданиеНовогоСотрудника.putExtras(data);
            startActivity(Интент_ЗапускСозданиеНовогоСотрудника);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }










































    private void МетодСвайпВпередПоДАнным() {
        try {
            if (ГлавныйКурсорДанныеSwipes.getCount() >0) {
                    if (ГлавныйКурсорДанныеSwipes.getPosition()<ГлавныйКурсорДанныеSwipes.getCount()-1) {
                        ГлавныйКурсорДанныеSwipes.moveToNext();
                    }else {
                        ГлавныйКурсорДанныеSwipes.moveToFirst();
                    }
                ScrollСамогоТабеля.startAnimation(animationRich);
                НазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);
                МетодАнализДанныхSwipes( );
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйКурсорДанныеSwipes.getCount() "+ГлавныйКурсорДанныеSwipes.getCount()+
                    "ГлавныйКурсорДанныеSwipes.getPosition()  " + ГлавныйКурсорДанныеSwipes.getPosition() +
                     " ПроизошелЛиСфайпПоДаннымСингТабеля " +ПроизошелЛиСфайпПоДаннымСингТабеля);
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
            if (ГлавныйКурсорДанныеSwipes.getCount() >0) {
                if (ГлавныйКурсорДанныеSwipes.getPosition() == 0) {
                    ГлавныйКурсорДанныеSwipes.moveToLast();
                }else{
                    ГлавныйКурсорДанныеSwipes.moveToPrevious();
                }
                ScrollСамогоТабеля.startAnimation(animationLesft);
                НазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);
                МетодАнализДанныхSwipes( );
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйКурсорДанныеSwipes.getCount() "+ГлавныйКурсорДанныеSwipes.getCount()+
                     "ГлавныйКурсорДанныеSwipes.getPosition()  " + ГлавныйКурсорДанныеSwipes.getPosition()+
                     " ПроизошелЛиСфайпПоДаннымСингТабеля " +ПроизошелЛиСфайпПоДаннымСингТабеля);
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


    private void МетодГлавныеДанныеLayoutInflater(@NonNull int[] ИндексСтрокКомпонентовТабеля
            , @NonNull Map<Integer, String> ХЭШНазваниеДнейНедели,
                                                  @NonNull  TextView НазваниеДанныхВТабелеДниНедели,
                                                  @NonNull  EditText СамиДанныеТабеля
            , @NonNull View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла
            , @NonNull SQLiteCursor sqLiteCursor) {
        /////todo ПЕРВАЯ СТРОКА НАЗВАНИЯ
        String НазваниеДней= "";
        Bundle bundleДляОбновление=new Bundle();
        try{

            List<Integer> ЛистСДопустипыЗначениямиДляЛокальноСохранения = new ArrayList();
            ЛистСДопустипыЗначениямиДляЛокальноСохранения.add(R.id.ПерваяСтрочкаНазваниеДень1);
/*
            IntStream.iterate(1, i -> i + 1).limit(24).forEachOrdered(ЛистСДопустипыЗначениямиДляЛокальноСохранения::add);
IntStream intStreamШабка=IntStream.of(
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень2,R.id.ПерваяСтрочкаНазваниеДень3,R.id.ПерваяСтрочкаНазваниеДень4,
        R.id.ПерваяСтрочкаНазваниеДень5,R.id.ПерваяСтрочкаНазваниеДень6,R.id.ПерваяСтрочкаНазваниеДень7,R.id.ПерваяСтрочкаНазваниеДень8,
        R.id.ПерваяСтрочкаНазваниеДень9,R.id.ПерваяСтрочкаНазваниеДень10,R.id.ПерваяСтрочкаНазваниеДень11,R.id.ПерваяСтрочкаНазваниеДень12,
        R.id.ПерваяСтрочкаНазваниеДень13,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень16,R.id.ПерваяСтрочкаНазваниеДень17,
        R.id.ПерваяСтрочкаНазваниеДень18,R.id.ПерваяСтрочкаНазваниеДень19,R.id.ПерваяСтрочкаНазваниеДень20,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
        R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,R.id.ПерваяСтрочкаНазваниеДень1,
);*/

            LinearLayout lr =(LinearLayout)    КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла;
            GridLayout cr =(GridLayout)   lr.findViewById(R.id.КонтейнерДляТабеляГрид);
            GridView cr2 =(GridView)   lr.findViewById(R.id.GridLayoutВнутриСамТабель);

            cr2.set
            int count = cr .getChildCount();
            for(int i = 0 ; i <count ; i++){
                View child = cr.getChildAt(i);
                Log.d(this.getClass().getName(), " child " + child);
            }

            for(int i = 0 ; i <cr2 .getRowCount() ; i++) {
                TableRow child2 = (TableRow) cr2.getChildAt(i);
                for (int j = 0; j < child2.getChildCount(); j++) {
                 View view=   child2.getChildAt(j);
                        Log.d(this.getClass().getName(), " view " + view);
                        Log.d(this.getClass().getName(), " child " + child2);
                }
            }

            ///TODO ГЛАВНЫЙ АДАПТЕР чата
            SimpleCursorAdapter simpleCursorAdapterЦФО= new SimpleCursorAdapter(getApplicationContext(),
                    R.layout.simple_newspinner_dwonload_newfiltersearch, cursorДанные,
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
                                        bundle.putLong("РодительскийUUDТаблицыТабель",РодительскийUUDТаблицыТабель);
                                        UUIDТекущегоВыбраногоСотрудника=      ГлавныйКурсорДанныеSwipes.getLong(ГлавныйКурсорДанныеSwipes.getColumnIndex("uuid"));
                                        bundle.putLong("UUIDТекущегоВыбраногоСотрудника",UUIDТекущегоВыбраногоСотрудника);
                                        ((MaterialTextView)view).setTag(bundle);
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                + " bundle"+bundle  + " UUIDТекущегоВыбраногоСотрудника " +UUIDТекущегоВыбраногоСотрудника);
                                    }
                                    Log.d(this.getClass().getName()," НазваниеПрофесии"+ НазваниеПрофесии+
                                            " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " ХэшЛовимUUIDIDНазваниеСтолбика.size() " +ХэшЛовимUUIDIDНазваниеСтолбика.size());
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
                                            Long РодительскийUUDТаблицыТабель =   bundle.getLong("РодительскийUUDТаблицыТабель",0l);
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " НазваниеЦФО " +НазваниеПрофесии + " UUIDПрофесиии " +UUIDПрофесиии+
                                                    " РодительскийUUDТаблицыТабель " +РодительскийUUDТаблицыТабель);
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











            /////TODO ДЕНЬ ПЕРВЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень1);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(1));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            String regex = "Сб ,(.*)";
            String regex1 = "Вс ,(.*)";
            if (ХЭШНазваниеДнейНедели.get(1).matches(regex) || ХЭШНазваниеДнейНедели.get(1).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ВТОРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень2);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(2));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(2));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(2));
            if (ХЭШНазваниеДнейНедели.get(2).matches(regex) || ХЭШНазваниеДнейНедели.get(2).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СРЕДА
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень3);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(3));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(3).matches(regex) || ХЭШНазваниеДнейНедели.get(3).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТВЕРЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень4);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(4));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(4).matches(regex) || ХЭШНазваниеДнейНедели.get(4).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень5);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(5));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(5).matches(regex) || ХЭШНазваниеДнейНедели.get(5).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень6);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(6));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(6).matches(regex) || ХЭШНазваниеДнейНедели.get(6).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СЕДЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень7);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(7));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(7).matches(regex) || ХЭШНазваниеДнейНедели.get(7).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ВОСЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень8);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(8));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(8).matches(regex) || ХЭШНазваниеДнейНедели.get(8).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕВЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень9);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(9));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(9).matches(regex) || ХЭШНазваниеДнейНедели.get(9).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕСЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень10);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(10));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(10).matches(regex) || ХЭШНазваниеДнейНедели.get(10).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ОДИНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень11);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(11));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(11).matches(regex) || ХЭШНазваниеДнейНедели.get(11).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДВЕНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень12);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(12));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(12).matches(regex) || ХЭШНазваниеДнейНедели.get(12).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ТРЕНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень13);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(13));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(13).matches(regex) || ХЭШНазваниеДнейНедели.get(13).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТЫРНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень14);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(14));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(14).matches(regex) || ХЭШНазваниеДнейНедели.get(14).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЕТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень15);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(15));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(15).matches(regex) || ХЭШНазваниеДнейНедели.get(15).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаНазваниеДень16);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(16));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(16).matches(regex) || ХЭШНазваниеДнейНедели.get(16).matches(regex1)) {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            } else {
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            ///TODO  КОНЕЦ ПЕРВОЙ СТРОКИ НАЗВАНИЯ ДАННЫХ (вТ,01,Ср,02)


            ///TODO  НАЧАЛО САМИ ДАННЫЕ ПЕРВОЙ СТРОКИ(1,2,3,4,6,7,8,9)

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень1);
            ПосикДня = sqLiteCursor.getColumnIndex("d1");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            // TODO: 30.03.2023 новый код сохраняем в сам обьект uuid и день
            Integer ID = sqLiteCursor.getInt(sqLiteCursor.getColumnIndex("_id"));
            Long UUID = sqLiteCursor.getLong(sqLiteCursor.getColumnIndex("uuid_tabel"));
            Long UUID_Tabel = sqLiteCursor.getLong(sqLiteCursor.getColumnIndex("uuid"));
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            bundleДляОбновление.putInt("ID",ID );
            bundleДляОбновление.putLong("UUID",UUID);
            bundleДляОбновление.putLong("UUID_Tabel",UUID_Tabel);
            bundleДляОбновление.putInt("ПосикДня",ПосикДня);
            bundleДляОбновление.putString("ДанныеДней", ДанныеДней);
            bundleДляОбновление.putString("НазваниеДней",НазваниеДней);
            this.СамиДанныеТабеля.setTag(bundleДляОбновление);
            /////todo обработка значения для дней
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней != null) {
                ДанныеДней = ДанныеДней.replaceAll("\\s+", "");
                // ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")) {
                    ДанныеДней = "";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }


            }
            // TODO: 24.05.2021 событие при поднятии клавиатуры


            //////

            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener(СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            /////TODO ТАБЕЛЬ вторая строчка вторник
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень2);
            ПосикДня = sqLiteCursor.getColumnIndex("d2");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }

                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }




            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка среда
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень3);
            ПосикДня = sqLiteCursor.getColumnIndex("d3");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка четверг

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень4);
            ПосикДня = sqLiteCursor.getColumnIndex("d4");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }

                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ третья строчка пятница
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень5);
            ПосикДня = sqLiteCursor.getColumnIndex("d5");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления

            /////TODO ТАБЕЛЬ третья строчка суббота
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень6);
            ПосикДня = sqLiteCursor.getColumnIndex("d6");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления

            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень7);
            ПосикДня = sqLiteCursor.getColumnIndex("d7");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень8);
            ПосикДня = sqLiteCursor.getColumnIndex("d8");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень9);
            ПосикДня = sqLiteCursor.getColumnIndex("d9");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень10);
            ПосикДня = sqLiteCursor.getColumnIndex("d10");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            // TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень11);
            ПосикДня = sqLiteCursor.getColumnIndex("d11");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень12);
            ПосикДня = sqLiteCursor.getColumnIndex("d12");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень13);
            ПосикДня = sqLiteCursor.getColumnIndex("d13");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней

            ////////////////////////
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень14);
            ПосикДня = sqLiteCursor.getColumnIndex("d14");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ////////////////////////
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования


            if(СтаттусТабеля==true){


                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);

                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень15);
            ПосикДня = sqLiteCursor.getColumnIndex("d15");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ////////////////////////
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ПерваяСтрочкаДанныеДень16);
            ПосикДня = sqLiteCursor.getColumnIndex("d16");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            //TODO  КОНЕЦ Сами Данных Табеля (1,2,3,4,5,6)-->


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO КОНЕЦ ПЕРВОЙ СТРОЧКИ


            ///TODO НАЧАЛО ВТОРОЙ СТРОКИ

            ///TODO НАЗВАНИЕ ДНЕЙ ВТОРОЙ СТРОКИ
            /////TODO ДЕНЬ 17
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень17);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(17));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(17).matches(regex) || ХЭШНазваниеДнейНедели.get(17).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ВТОРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень18);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(18));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(18).matches(regex) || ХЭШНазваниеДнейНедели.get(18).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СРЕДА
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень19);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(19));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(19).matches(regex) || ХЭШНазваниеДнейНедели.get(19).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ЧЕТВЕРЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень20);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(20));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(20).matches(regex) || ХЭШНазваниеДнейНедели.get(20).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ПЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень21);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(21));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(21).matches(regex) || ХЭШНазваниеДнейНедели.get(21).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ШЕСТРОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень22);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(22));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(22).matches(regex) || ХЭШНазваниеДнейНедели.get(22).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ СЕДЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень23);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(23));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(23).matches(regex) || ХЭШНазваниеДнейНедели.get(23).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ВОСЬМОЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень24);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(24));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(24).matches(regex) || ХЭШНазваниеДнейНедели.get(24).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕВЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень25);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(25));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(25).matches(regex) || ХЭШНазваниеДнейНедели.get(25).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДЕСЯТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень26);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(26));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(26).matches(regex) || ХЭШНазваниеДнейНедели.get(26).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ОДИНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень27);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(27));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(27).matches(regex) || ХЭШНазваниеДнейНедели.get(27).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            /////TODO ДЕНЬ ДВЕНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень28);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(28));
            ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
            if (ХЭШНазваниеДнейНедели.get(28).matches(regex) || ХЭШНазваниеДнейНедели.get(28).matches(regex1)){
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
            }else{
                this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
            }
            ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ

            /////TODO ДЕНЬ ТРЕНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень29);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(29));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<29) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else{
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(29).matches(regex) || ХЭШНазваниеДнейНедели.get(29).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            /////TODO ДЕНЬ ЧЕТЫРНАЦАТЫЕ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень30);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(30));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<30) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else{
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(30).matches(regex) || ХЭШНазваниеДнейНедели.get(30).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            ///todo загружем если данные дни есть в календаре
            /////TODO ДЕНЬ ПЕТНАЦАТЫЙ
            this.НазваниеДанныхВТабелеДниНедели = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаНазваниеДень31);
            this.НазваниеДанныхВТабелеДниНедели.setText(ХЭШНазваниеДнейНедели.get(31));

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<31) {
                this.НазваниеДанныхВТабелеДниНедели.setVisibility(View.INVISIBLE);
            }else {
                ////////TODO ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
                Log.d(this.getClass().getName(), " ХЭШНазваниеДнейНедели.get(1)  " + ХЭШНазваниеДнейНедели.get(1));
                if (ХЭШНазваниеДнейНедели.get(31).matches(regex) || ХЭШНазваниеДнейНедели.get(31).matches(regex1)){
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#DC143C"));
                }else{
                    this.НазваниеДанныхВТабелеДниНедели.setBackgroundResource(R.drawable.textlines_tabel_row_name_value);
                    this.НазваниеДанныхВТабелеДниНедели.setTextColor(Color.parseColor("#008080"));
                }
                ////////TODO  конец ВЫХОДНЫЕ ДНИ ДЕЛАЕМ ДРГИМ ДИЗАЙНОМ
            }
            ///TODO  КОНЕЦ ВТОРОЙ СТРОКИ НАЗВАНИЯ ДАННЫХ (вТ,01,Ср,02)


            ///TODO  НАЧАЛО САМИ ДАННЫЕ ВТОРОЙ СТРОКИ(10,11,12,13,14,15)

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень17);
            ПосикДня = sqLiteCursor.getColumnIndex("d17");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ вторая строчка вторник
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень18);
            ПосикДня = sqLiteCursor.getColumnIndex("d18");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////


            ////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ вторая строчка среда
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень19);
            ПосикДня = sqLiteCursor.getColumnIndex("d19");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ вторая строчка четверг

            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень20);
            ПосикДня = sqLiteCursor.getColumnIndex("d20");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            /////TODO ТАБЕЛЬ третья строчка пятница
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень21);
            ПосикДня = sqLiteCursor.getColumnIndex("d21");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);
            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ третья строчка суббота
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень22);
            ПосикДня = sqLiteCursor.getColumnIndex("d22");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень23);
            ПосикДня = sqLiteCursor.getColumnIndex("d23");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            /////TODO ТАБЕЛЬ третья строчка воскресенье
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень24);
            ПосикДня = sqLiteCursor.getColumnIndex("d24");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень25);
            ПосикДня = sqLiteCursor.getColumnIndex("d25");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень26);
            ПосикДня = sqLiteCursor.getColumnIndex("d26");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень27);
            ПосикДня = sqLiteCursor.getColumnIndex("d27");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }


            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень28);
            ПосикДня = sqLiteCursor.getColumnIndex("d28");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);

            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень29);
            ПосикДня = sqLiteCursor.getColumnIndex("d29");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////


            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<29) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }
            ////////////////////////
            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень30);
            ПосикДня = sqLiteCursor.getColumnIndex("d30");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели
            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////



            ////todo клик который переходит на выбор слов для вставки в  Метка табель
            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            //////
            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<30) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }
            ////////////////////////
            ///todo загружем если данные дни есть в календаре
            ///TODO заполняем массив данных из табеля из вертуальныйх данных запоминаем их в масиив для дальнейшего о брашени для обновления
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.ВтораяСтрочкаДанныеДень31);
            ПосикДня = sqLiteCursor.getColumnIndex("d31");
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            /////todo обработка значения для дней
            ДанныеДней = "";
            ДанныеДней = sqLiteCursor.getString(ПосикДня);
            Log.d(this.getClass().getName(), " ДанныеДней" + ДанныеДней);
            if (ДанныеДней !=null){
                ДанныеДней =    ДанныеДней.replaceAll("\\s+","");
                ////  ДанныеДней=    ДанныеДней.replaceAll("[^0-9]","");
                ////TODO МЕНЯЕМ ЦВЕТ ЕСЛИ НОЛЬ ДАННЫЕ
                if (ДанныеДней.equals("0")){
                    ДанныеДней ="";
                }
                // TODO: 19.05.2021 для букв окраиваем в сервый цвет

                if (ДанныеДней.matches("[А-Я]")) {
                    this.СамиДанныеТабеля.setTextColor(Color.parseColor("#2F4F4F"));
                }
            }

            //////
            this.СамиДанныеТабеля.setText(ДанныеДней); ///replace("[^0-9]"));
            /////todo обработка значения для дней
            //TODO подключаем слушатели

            ///TODO слушатели ячеек
            this.СамиДанныеТабеля.addTextChangedListener(СлушательПолученияДанных);

            this.СамиДанныеТабеля.setOnClickListener( СлушательДанныеДляОбновления);
            ////////////////////////

            ///todo  УБИРАЕМ С ЭКРАНА ЕСЛИ ДАННОГО ДНЯ НЕТ В КАЛЕНДАРЕ (ЯРКИЙ ПРИМЕР МЕСЯЦ ФЕВРАЛЬ)
            if (КоличествоДнейвЗагружаемойМесяце<31) {
                this.СамиДанныеТабеля.setVisibility(View.INVISIBLE);
            }
            ///////////////////////
            ////todo клик который переходит на выбор слов для вставки в  Метка табель

            this.СамиДанныеТабеля.setOnLongClickListener(   СлушательДляПереходаНаМеткиТабеля);


            ///todo  установка только чтение данных с табеля без редактирования

            if(СтаттусТабеля==true){
                this.СамиДанныеТабеля.setInputType(InputType.TYPE_NULL);
                this.СамиДанныеТабеля.setLongClickable(false);

            }

            //TODO  КОНЕЦ Сами Данных Табеля (1,2,3,4,5,6)-->

            //TODO ТЕХНИЧЕСКИЙ  БЛОК ВЕРХНЕМ СТРОЧКИ ID И UUID-->
            ///TODO uuid не видивая EDITTEXT понадобиться для обновления UUID
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.СтрочкаНевидимаяUUID);
            ПосикДня = sqLiteCursor.getColumnIndex("uuid"); ////TODO СЮДА ПОЛЕ UUID
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            this.СамиДанныеТабеля.setText(sqLiteCursor.getString(ПосикДня));
            Log.d(this.getClass().getName(), " ВтораяСтрочкаНевидимаяUUID " + sqLiteCursor.getString(ПосикДня));
            this.СамиДанныеТабеля.setVisibility(View.GONE);
            ///TODO вытаскиваем UUID
            ///TODO uuid не видивая EDITTEXT понадобиться для обновления ID
            this.СамиДанныеТабеля = КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦикла.findViewById(R.id.СтрочкаНевидимаяID);
            ПосикДня = sqLiteCursor.getColumnIndex("_id"); ////TODO СЮДА ПОЛЕ ID
            НазваниеДней = sqLiteCursor.getColumnName(ПосикДня);
            this.СамиДанныеТабеля.setTag(НазваниеДней);
            this.СамиДанныеТабеля.setText(sqLiteCursor.getString(ПосикДня));
            Log.d(this.getClass().getName(), " ВтораяСтрочкаНевидимаяID " + sqLiteCursor.getString(ПосикДня));
            this.СамиДанныеТабеля.setVisibility(View.GONE);

            Log.d(this.getClass().getName(), " ИндексСтрокКомпонентовТабеля " + ИндексСтрокКомпонентовТабеля[0]);
            /////TODO увеличиваем шаг  по строчкам
            ИндексСтрокКомпонентовТабеля[0]++;
            //////TODO заврешаем уикл загрузки данных на ктивити табель
            //TODO  ОЧИЩАЕМ ПАМТЬ

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }









    //TODO метод делает callback с ответом на экран
    private  void  МетодGetmessage(){
        try{
            message=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    return true;
                }
            }).obtainMessage();
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
                                                    bundle.putLong("РодительскийUUDТаблицыТабель",РодительскийUUDТаблицыТабель);
                                                    UUIDТекущегоВыбраногоСотрудника=      ГлавныйКурсорДанныеSwipes.getLong(ГлавныйКурсорДанныеSwipes.getColumnIndex("uuid"));
                                                    bundle.putLong("UUIDТекущегоВыбраногоСотрудника",UUIDТекущегоВыбраногоСотрудника);
                                                    ((MaterialTextView)view).setTag(bundle);
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                            + " bundle"+bundle  + " UUIDТекущегоВыбраногоСотрудника " +UUIDТекущегоВыбраногоСотрудника);
                                                }
                                                Log.d(this.getClass().getName()," НазваниеПрофесии"+ НазваниеПрофесии+
                                                        " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " ХэшЛовимUUIDIDНазваниеСтолбика.size() " +ХэшЛовимUUIDIDНазваниеСтолбика.size());
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
                                                        Long РодительскийUUDТаблицыТабель =   bundle.getLong("РодительскийUUDТаблицыТабель",0l);
                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " НазваниеЦФО " +НазваниеПрофесии + " UUIDПрофесиии " +UUIDПрофесиии+
                                                                " РодительскийUUDТаблицыТабель " +РодительскийUUDТаблицыТабель);
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
                                Integer ПровйдерСменаПрофесии=      МетодЗаписиСменыПрофесии(searchViewДляНовогоПоиска);
                                if (ПровйдерСменаПрофесии>0) {
                                    ПроизошелЛиСфайпПоДаннымСингТабеля=true;
                                    // TODO: 30.03.2023 Курсор ALL Date
                                     Integer ПолощениеДАнных=            ГлавныйКурсорДанныеSwipes.getPosition();
                                    МетодSwipeALLКурсор();
                                         ГлавныйКурсорДанныеSwipes.moveToPosition(ПолощениеДАнных);
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
            МетодАнализДанныхSwipes( );
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

    private   Integer МетодЗаписиСменыПрофесии(@NonNull SearchView searchViewДляНовогоПоиска){ //TODO метод записи СМЕНЫ ПРОФЕСИИ
        Integer ПровйдерСменаПрофесии=0;
        try{
            String ТаблицаОбработки="data_tabels";
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " searchViewДляНовогоПоиска "+searchViewДляНовогоПоиска+ " ТаблицаОбработки "+ТаблицаОбработки);
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
            Bundle bundleСменаПрофессии= (Bundle) searchViewДляНовогоПоиска.getTag();
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

    private void МетодПерегрузкаВидаЭкрана() {
        try {
            НазваниеДанныхВТабелеФИО.startAnimation(animationПрофессия);
            НазваниеДанныхВТабелеФИО.refreshDrawableState();
            НазваниеДанныхВТабелеФИО.forceLayout();
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
}






















