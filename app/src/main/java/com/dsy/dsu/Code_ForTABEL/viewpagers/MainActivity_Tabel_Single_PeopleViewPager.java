package com.dsy.dsu.Code_ForTABEL.viewpagers;


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
import android.view.animation.Transformation;
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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.MarginPageTransformer;
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
import com.google.common.util.concurrent.AtomicDouble;
import com.jakewharton.rxbinding4.view.RxView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;


public class MainActivity_Tabel_Single_PeopleViewPager extends AppCompatActivity  implements Busable {
    private ViewPager viewPager ;
    private  SubClassBissnessLogicTableSingleWithViewPager singleWithViewPager;
    private  Integer    ГодТабелей=  0;
    private  Integer    МЕсяцТабелей=  0;
    private  Integer     DigitalNameCFO=0;
    private       Bundle   bundle_single_tabel_viewpagers;

    private     Cursor     cursorForViewPager;


    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_single_tabel_viewpager);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "  onCreate(Bundle savedInstanceState)   MainActivity_Tabel_Single_People  ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            Configuration   config = getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);
            // TODO: 20.06.2023 Инизиализация
            viewPager =  (ViewPager) findViewById(R.id.viewPager_single_tabel_viewpagers);
            // TODO: 20.06.2023
            singleWithViewPager=  new SubClassBissnessLogicTableSingleWithViewPager(viewPager);
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
        try {
            // TODO: 29.03.2023  Метод обсуживаюшие
            singleWithViewPager.  методGETДанныеИзДругихАктивити();
             cursorForViewPager =    singleWithViewPager.new SubClassGetCursor().МетодSwipesКурсор();
            singleWithViewPager.    методИницмализвцияViewAdapters(cursorForViewPager);
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
    public ViewPager viewPager() {
        return viewPager;
    }

    @Override
    public Cursor getcorsor() {
        //cursorForViewPager.moveToFirst();
        return cursorForViewPager;
    }

















    // TODO: 19.06.2023  Бизнес Класс Новой Активтив ТАбель Single Через ViewPager
    class  SubClassBissnessLogicTableSingleWithViewPager {
        public SubClassBissnessLogicTableSingleWithViewPager(@NonNull ViewPager viewPager) {
        }
        private void методGETДанныеИзДругихАктивити() {
            try {
                Intent intent =  getIntent();
                  bundle_single_tabel_viewpagers=intent.getExtras();
                // TODO: 10.04.2023
                if (bundle_single_tabel_viewpagers!=null) {
                    Long    MainParentUUID=    bundle_single_tabel_viewpagers.getLong("MainParentUUID", 0l);
                    Integer   PositionCustomer=    bundle_single_tabel_viewpagers.getInt("Position", 0);
                      ГодТабелей=  bundle_single_tabel_viewpagers.getInt("ГодТабелей", 0);
                       МЕсяцТабелей=  bundle_single_tabel_viewpagers.getInt("МЕсяцТабелей",0);
                           DigitalNameCFO=   bundle_single_tabel_viewpagers.getInt("DigitalNameCFO", 0);
                    String      FullNameCFO=  bundle_single_tabel_viewpagers.getString("FullNameCFO", "").trim();
                    String    ИмесяцвИГодСразу= bundle_single_tabel_viewpagers.getString("ИмесяцвИГодСразу", "").trim();
                    Long       CurrenrsСhildUUID= bundle_single_tabel_viewpagers.getLong("CurrenrsСhildUUID", 0l);
                    String     ФИО= bundle_single_tabel_viewpagers.getString("ФИО", "").trim();
                    Long  CurrenrsSelectFio= bundle_single_tabel_viewpagers.getLong("CurrenrsSelectFio", 0l);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                            + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                            " PositionCustomer " +PositionCustomer+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методИницмализвцияViewAdapters(@NonNull    Cursor     cursorForViewPager) {
            try {
                ViewAdapterModel viewAdapterДанные=new ViewAdapterModel(getSupportFragmentManager());
                CopyOnWriteArrayList<Fragment> copyOnWriteArrayListfragments=new CopyOnWriteArrayList<>();
                IntStream.iterate(0, i -> i + 1) .limit(cursorForViewPager.getCount() )
                        .forEachOrdered(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        try{
                            // TODO: 22.06.2023  метод генерируем будущие фрагменты  для Sinle Tabel
                            методГенерацииФрагментовДляSingleTabel(value, cursorForViewPager, copyOnWriteArrayListfragments);
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });
                viewAdapterДанные.setFragments(copyOnWriteArrayListfragments);
                // TODO: 20.06.2023  Заполеяем Адампетре
                viewPager.setAdapter(viewAdapterДанные);
                viewPager.setPageTransformer(true,View::setTranslationX,0 );
                viewPager.refreshDrawableState();
                viewPager.requestLayout() ;
                // TODO: 21.06.2023  clear
             ///   viewPager.getAdapter().notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private void методГенерацииФрагментовДляSingleTabel(int value, @NonNull Cursor cursorForViewPager,
                                                            CopyOnWriteArrayList<Fragment> copyOnWriteArrayListfragments) {
            try{
            bundle_single_tabel_viewpagers.putInt("value", value);
            if (value < cursorForViewPager.getCount()) {
                cursorForViewPager.move(value);
            Long    uuid=    cursorForViewPager.getLong(cursorForViewPager.getColumnIndex("uuid"));
        bundle_single_tabel_viewpagers.putLong("uuid",uuid);
        bundle_single_tabel_viewpagers.putInt("getpositioncursor", cursorForViewPager.getPosition());
        bundle_single_tabel_viewpagers.putInt("value", value);
            // TODO: 21.06.2023 перердаем параметры для создание нового фрагмента
        FragmentSingleTabel fragmentSingleTabel=FragmentSingleTabel.newInstance( bundle_single_tabel_viewpagers);
        copyOnWriteArrayListfragments.add(fragmentSingleTabel);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 20.06.2023 Класс получение данных CURSOR

      public  class SubClassGetCursor{
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
                    cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getApplicationContext(), bundleГлавныйКурсорMultiДанныеSwipes);

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


    }//TODO SubClassBissnessLogicTableSingleWithViewPager











}






















