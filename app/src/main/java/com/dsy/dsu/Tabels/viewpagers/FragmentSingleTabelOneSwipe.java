package com.dsy.dsu.Tabels.viewpagers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicAll.CELLUPDATE.SubClassUpdatesCELL;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.Tabels.MainActivity_List_Peoples;
import com.dsy.dsu.Tabels.MainActivity_Metki_Tabel;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.widget.RxTextView;
import com.jakewharton.rxbinding4.widget.TextViewAfterTextChangeEvent;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * AHilt simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabelOneSwipe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabelOneSwipe extends Fragment {

    private MaterialTextView spinnerchasy,spinnermesazyear,spinnerdepartament;/////спинеры для создание табеля

    private    String Профессия;


    private  Integer DigitalNameCFO=0;



    private MaterialButton imageButtonbackotsingletabel;









    private  long CurrenrsСhildUUID =0l;
    private  long CurrenrsSelectFio =0l;
    private  long MainParentUUID =0l;
    private  String ФИО;
    private Message message;
    private      Message messageRows;


    private RecyclerView recycleviewsingletabel;
    private  SubClassSingleTabelRecycreView. MyRecycleViewAdapter myRecycleViewAdapter;
    private  SubClassSingleTabelRecycreView. MyViewHolder myViewHolder;
    private  Integer CurrentFragmentMaxItem =0;
    private  String FullNameCFO = "";
    private  Integer ГодТабелей = 0;
    private  String ИмесяцвИГодСразу = "";
    private  Integer МЕсяцТабелей=0;
    private InputMethodManager imm;



    private String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
    private LifecycleOwner lifecycleOwner;
    private   LifecycleOwner  lifecycleOwnerОбщая;
    private    long startДляОбноразвовной;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    private  SubClassBisscessFragmentSingleTabel fragmentSingleTabel;
    private  ViewGroup contGruop;
    private  SubClassSingleTabelRecycreView singleTabelRecycreView;


    private  Integer GetPosition;
  private      Animation animation1;

  private      Animation animationFromRecyReview;

  private  Disposable disposableAfterTextChangeEvent;
  private      Cursor    cursorForViewPager;

  private      Handler handlerМетодForCurcorHandlerCallBack;

  private  MaterialTextView  materialTextViewfio,materialTextViewprofession;

    private LinkedHashMap< String,String> ДниВыходные=new LinkedHashMap<>();
    private LinkedHashMap< String,String> ДниПразничные=new LinkedHashMap<>();



    private NestedScrollView nestedScrollView_singlet;



    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabelOneSwipe newInstance(@NonNull Bundle bundle_single_tabel_viewpagers ) {
        FragmentSingleTabelOneSwipe fragment = new FragmentSingleTabelOneSwipe();
        Bundle args = new Bundle();
        args=bundle_single_tabel_viewpagers.deepCopy();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            fragmentSingleTabel=new SubClassBisscessFragmentSingleTabel();
            fragmentSingleTabel.МетодGetmessage();
            // TODO: 29.03.2023  Метод RerecyView RerecyView RerecyView RerecyView RerecyView
            lifecycleOwner=this;
            lifecycleOwnerОбщая=this;
            // TODO: 22.06.2023
            singleTabelRecycreView= new SubClassSingleTabelRecycreView(lifecycleOwner,lifecycleOwnerОбщая,getActivity());

            fragmentSingleTabel.new SubClassBungleSingle().методGETДанныеИзДругихАктивити();

            singleTabelRecycreView.МетодForCurcorHandlerCallBack();



            // TODO: 16.04.2023  Выходыне Дни   празнечные ДНИ 
            metodGetsDaySetPrasnchih();

            // TODO: 16.11.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorForViewPager.getPosition() ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void metodGetsDaySetPrasnchih() throws ParseException {
        try{
            // TODO: 20.11.2023 Получение Празничных и Выходных Дней  
        GetDayFromKalendary getDayFromKalendary=new GetDayFromKalendary();

        ДниВыходные= getDayFromKalendary.методВыходныеДниИзКалендарь();
        // TODO: 26.06.2023 Празничные Дни
        ДниПразничные=getDayFromKalendary. методПразничныеДниИзКалендаря();
        // TODO: 16.11.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorForViewPager.getPosition() ");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewSingletabek=null;
        try{
           // viewSingletabek= inflater.inflate(R.layout.fragment_single_tabel_viewpager2, container, false);
            viewSingletabek= inflater.inflate(R.layout.simple_for_headers_fragmentsingle, container, false);
            // TODO: 21.06.2023
            contGruop=container;

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // Inflate the layout for this fragment
        return  viewSingletabek; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            // TODO: 21.06.2023 КОДЕ
            fragmentSingleTabel.new SubClassNewDataSingleTabel().методВнешнийВидФрагмента(view);

            singleTabelRecycreView.metodAddCurcorRecyreview(cursorForViewPager );

            singleTabelRecycreView.metodДизайнRecycreView();

            // TODO: 16.06.2023  перегрузка экрана
            singleTabelRecycreView.   методПерегрузкиRecycreView();



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        try{

            cursorForViewPager  =   singleTabelRecycreView.  new SubClassGetCursor().МетодSwipesКурсор();
            // TODO: 21.06.2023 Смещения Курсоора
            cursorForViewPager.moveToPosition(GetPosition);

            singleTabelRecycreView.   методWorkManagerLifecycleOwner();

            singleTabelRecycreView.МетодСлушательКурсора(cursorForViewPager);


            // TODO: 26.06.2023  созданнй CallBack
            singleTabelRecycreView.методДляSimpeCallbacks( );

            singleTabelRecycreView.МетодСлушательRecycleView();


            // TODO: 16.06.2023  перегрузка экрана
            singleTabelRecycreView.   методПерегрузкиRecycreView();

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public void onResume() {
        super.onResume();
        // TODO: 16.11.2023
                try{
                    if (cursorForViewPager!=null) {
                //singleTabelRecycreView.metodAddCurcorRecyreview(cursorForViewPager );
                  singleTabelRecycreView.  методRebootRecyreview(cursorForViewPager);
                // TODO: 16.06.2023  перегрузка экрана
                singleTabelRecycreView.   методПерегрузкиRecycreView();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

    }





    @Override
    public void onStop() {
        super.onStop();
        try{


            metodReebotDataForFragmentSingleTabel();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void metodReebotDataForFragmentSingleTabel() {
        try{
            if (myRecycleViewAdapter.cursor!=null) {
                singleTabelRecycreView.  методReeoBootCursorRecyreViewAlfterChangeProffesion();
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();
                // TODO: 26.06.2023  перегрузка
                singleTabelRecycreView.  методПерегрузкиRecycreView();
                // TODO: 10.08.2023
            }
            if (disposableAfterTextChangeEvent!=null) {
                disposableAfterTextChangeEvent.dispose();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }











// TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel



    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    @SuppressLint("Range")
    class  SubClassBisscessFragmentSingleTabel{
        // TODO: 21.06.2023  перенесоный метод из Activity Single Tabel
        class SubClassNewDataSingleTabel{
            void методВнешнийВидФрагмента(@NonNull View view){
                try{
                    spinnermesazyear = (MaterialTextView) view. findViewById(R.id.spinnermesazyear);
                    spinnerchasy = (MaterialTextView) view.findViewById(R.id.spinnerchasy);
                    spinnerdepartament = (MaterialTextView) view.findViewById(R.id.spinnerdepartament);

                    ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА
                    imageButtonbackotsingletabel =(MaterialButton)view. findViewById(R.id.imageButtonbackotsingletabel);

                    materialTextViewfio = (MaterialTextView)  view.findViewById(R.id.materialTextViewfio);
                    materialTextViewprofession = (MaterialTextView)  view.findViewById(R.id.materialTextViewprofession);

                    recycleviewsingletabel = (RecyclerView)  view.findViewById(R.id.recycleviewsingletabel);
                    recycleviewsingletabel.scrollToPosition(View.FOCUS_UP);
                    recycleviewsingletabel.setNestedScrollingEnabled(false);

                    // TODO: 30.11.2023
                    nestedScrollView_singlet= (NestedScrollView)  view.findViewById(R.id.nestedScrollView_singlet);




                    startДляОбноразвовной= Calendar.getInstance().getTimeInMillis();


                    fragmentSingleTabel.  методСпинерМесяцы( );
                    fragmentSingleTabel.    МетодСпинерДепартамент();
                    fragmentSingleTabel.    МетодОтработкиПоднятияКлавиатуры();
                    fragmentSingleTabel.    МетодПриНАжатииНаКнопкуBACK();


                    animation1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);
                    animationFromRecyReview= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
           /*     animationПрофессия300 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row2);
                animationVibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable);
                animationVibr2 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);
                animationRows = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_scroll_for_singletabel);
                animationRich = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
                animationLesft = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1*/

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " CurrentFragmentMaxItem " + CurrentFragmentMaxItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        }//TODO END   class SubClassNewDataSingleTabel

        class SubClassBungleSingle{
            private void методGETДанныеИзДругихАктивити() {
                try {
                    Bundle     bundleИзMainActitivy_List_Tables=(Bundle) getArguments();
                    // TODO: 10.04.2023
                    if (bundleИзMainActitivy_List_Tables!=null) {
                        // TODO: 23.06.2023  NEW
                        GetPosition =bundleИзMainActitivy_List_Tables.getInt("Position",0);
                        MainParentUUID=    bundleИзMainActitivy_List_Tables.getLong("MainParentUUID", 0l);
                        ГодТабелей=  bundleИзMainActitivy_List_Tables.getInt("ГодТабелей", 0);
                        МЕсяцТабелей=  bundleИзMainActitivy_List_Tables.getInt("МЕсяцТабелей",0);
                        DigitalNameCFO=   bundleИзMainActitivy_List_Tables.getInt("DigitalNameCFO", 0);
                        FullNameCFO=  bundleИзMainActitivy_List_Tables.getString("FullNameCFO", "").trim();
                        ИмесяцвИГодСразу= bundleИзMainActitivy_List_Tables.getString("ИмесяцвИГодСразу", "").trim();
                        CurrenrsСhildUUID= bundleИзMainActitivy_List_Tables.getLong("CurrenrsСhildUUID", 0l);
                        ФИО= bundleИзMainActitivy_List_Tables.getString("ФИО", "").trim();
                        CurrenrsSelectFio= bundleИзMainActitivy_List_Tables.getLong("CurrenrsSelectFio", 0l);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                                + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                                " CurrentFragmentMaxItem " + CurrentFragmentMaxItem
                                + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу
                                + "  cursorForViewPager " +  cursorForViewPager);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void методGETДанныеRunTimeИзCursor(@NonNull Cursor cursor ) {
                try {
                    // TODO: 10.04.2023
                    if (cursor!=null) {
                        MainParentUUID=    cursor.getLong(cursor.getColumnIndex("uuid_tabel"));
                        CurrentFragmentMaxItem =     myRecycleViewAdapter.cursor.getPosition();
                        ГодТабелей=  cursor.getInt(cursor.getColumnIndex("year_tabels"));
                        МЕсяцТабелей= cursor.getInt(cursor.getColumnIndex("month_tabels"));
                        DigitalNameCFO=   cursor.getInt(cursor.getColumnIndex("cfo"));
                        CurrenrsСhildUUID= cursor.getLong(cursor.getColumnIndex("uuid"));
                        ФИО= cursor.getString(cursor.getColumnIndex("name"));
                        CurrenrsSelectFio= cursor.getLong(cursor.getColumnIndex("fio"));
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                                + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                                " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу + "  cursor " +cursor);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
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
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        //TODO хдесь мы запускаем метод создание и обработка самого табеля
        private void методСпинерМесяцы( ) {
            try{
                // TODO: 20.11.2023  заполение часов
                class SpinerMoth extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerMoth().metodAddDataSpinersHasyAndMotch(spinnermesazyear,ИмесяцвИГодСразу);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
        private void МетодСпинерДепартамент( ) {
            // TODO: 20.11.2022 второй спинер департамент
            try {
                // TODO: 20.11.2023  заполение часов
                class SpinerДепартамент extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerДепартамент().metodAddDataSpinersHasyAndMotch(spinnerdepartament,FullNameCFO);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private void МетодОтработкиПоднятияКлавиатуры() {
            try{
                KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                    " FullNameCFO " + FullNameCFO);
                            методСпинерМесяцы( );
                        }else{
                            Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                    " FullNameCFO " + FullNameCFO);
                            методСпинерМесяцы( );
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }

        }

        private void МетодПриНАжатииНаКнопкуBACK() {
            try {
                imageButtonbackotsingletabel.setOnClickListener(new View.OnClickListener(){
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
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }

        void методПереходаMainActivity_List_Peoples() {
            try{
                ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
                Intent Интент_ПереходаMainActivity_List_Peoples = new Intent();
                Интент_ПереходаMainActivity_List_Peoples.setClass(getContext(), MainActivity_List_Peoples.class);
                Bundle bundleИзMainActitivy_List_Tables=getArguments();
                Интент_ПереходаMainActivity_List_Peoples.putExtras(bundleИзMainActitivy_List_Tables);
                // TODO: 28.06.2023 clear
                myRecycleViewAdapter.cursor.close();
                recycleviewsingletabel.removeAllViewsInLayout();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                        + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                        " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
                startActivity(Интент_ПереходаMainActivity_List_Peoples);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
    }//TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel
    //TODO класс визуализации внешнего вида





    // TODO: 21.06.2023  --- 15.25   Пересенный Класс из Activity Singe Tabel НА Fragment Single Tabel
    public class SubClassSingleTabelRecycreView  {
        private     Cursor cursorForViewPager;

        public SubClassSingleTabelRecycreView(@NonNull  LifecycleOwner lifecycleOwner,
                                              @NonNull  LifecycleOwner  lifecycleOwnerОбщая,
                                              @NonNull Activity activity) {
        }

        private void metodДизайнRecycreView() {
            try{

               /* DividerItemDecoration dividerItemDecorationVer=
                        new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL);
                dividerItemDecorationVer.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport2));///R.dimen.activity_horizontal_margin
                recycleviewsingletabel.addItemDecoration(dividerItemDecorationVer);*/

                DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
                dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport2));///R.dimen.activity_horizontal_margin
                recycleviewsingletabel.addItemDecoration(dividerItemDecorationHor);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL, true);
                recycleviewsingletabel.setLayoutManager(layoutManager);
                recycleviewsingletabel.setItemAnimator(new DefaultItemAnimator());
                recycleviewsingletabel.setHasFixedSize(true);
                // TODO: 12.05.2023 Клаиатура
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursorForViewPager " + cursorForViewPager);
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
        void методИницаллизацииКлавиаотурыЯчейка(@NonNull EditText editText){
            try{
                if (editText!=null) {
                    imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput( editText, InputMethodManager.SHOW_IMPLICIT);
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursorForViewPager " + cursorForViewPager);
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

        // TODO: 15.06.2023 свайвы
        // TODO: 15.06.2023 свайвы

















        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private Cursor методScrollsLeftRecyreView() {
            try {
                Integer Позиция=        myRecycleViewAdapter.cursor.getPosition();
                // TODO: 20.04.2023 Данные
                ///cursorForViewPager =    new  SubClassGetCursor().МетодSwipesКурсор();
                Cursor       cursorSwipeViewPager=   myRecycleViewAdapter.cursor;
                if (cursorSwipeViewPager.isLast()){
                    cursorSwipeViewPager.moveToFirst();
                }else {
                    Позиция=Позиция+1;
                    cursorSwipeViewPager.moveToPosition(Позиция);
                }
                myRecycleViewAdapter.cursor= cursorSwipeViewPager;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                // TODO: 18.06.2023
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem + " cursorForViewPager " + cursorForViewPager +
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursorSwipeViewPager " + cursorSwipeViewPager.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return   myRecycleViewAdapter.cursor;
        }


        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private void методAlterSaveCellRecyreView( ) {
            try {
                // TODO: 20.04.2023 Данные

                Integer Позиция=        myRecycleViewAdapter.cursor.getPosition();
                // TODO: 20.04.2023 Данные
                Cursor   cursorForViewPager =    new  SubClassGetCursor().МетодSwipesКурсор();

                cursorForViewPager.moveToPosition(Позиция);

                myRecycleViewAdapter.cursor= cursorForViewPager;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyItemChanged(0);
                recycleviewsingletabel.getAdapter().notifyItemChanged(0);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem + " cursorForViewPager " + cursorForViewPager +
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursorForViewPager " + cursorForViewPager.getPosition());

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
        void metodAddCurcorRecyreview(@NonNull Cursor cursorForViewPager) {
            try {
                this.cursorForViewPager=cursorForViewPager;
                // remove item from adapter
                myRecycleViewAdapter = new  MyRecycleViewAdapter(cursorForViewPager );
                myRecycleViewAdapter.notifyDataSetChanged();
                recycleviewsingletabel.setAdapter(myRecycleViewAdapter);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "cursorForViewPager  " + cursorForViewPager );
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



        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {
            private TableLayout tableLayout_single_tabel_one_swipe;
            private TableRow TableRow1Name,TableRow2Name ,TableRow3Name,TableRow4Name,TableRow5Name,TableRow6Name,TableRow7Name,TableRow8Name;
            private  TableRow TableData1Row,TableData2Row,TableData3Row,TableData4Row,TableData5Row,TableData6Row,TableData7Row,TableData8Row;
            private  CopyOnWriteArrayList<LinkedHashMap<TableRow,TableRow>> linkedHashMapsНазваниеиДанные =new CopyOnWriteArrayList<>();
            private    LinkedHashMap<TableRow,TableRow> linkedHashMapНазвание=new LinkedHashMap<>();
            public MyViewHolder(@NonNull View itemView ) {
                super(itemView);
                try {
                    // TODO: 04.04.2023 Метод Иинициализации RecycreVIEW ALL ELEMENT
                    методИнициализацииПеременыхRecureView(itemView);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " itemView "+itemView);
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
            private void методИнициализацииПеременыхRecureView(@NonNull View itemView) {
                try {
                    // TODO: 04.04.2023  Иниуциализация Комепонетов
                    tableLayout_single_tabel_one_swipe = itemView.findViewById(R.id.tableLayout_single_tabel_one_swipe);
                    if (tableLayout_single_tabel_one_swipe !=null) {
                        // TODO: 04.04.2023   NAME
                        TableRow1Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow1Name);
                        TableRow2Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow2Name);
                        TableRow3Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow3Name);
                        TableRow4Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow4Name);
                        TableRow5Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow5Name);
                        TableRow6Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow6Name);
                        TableRow7Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow7Name);
                        TableRow8Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow8Name);
                        // TODO: 26.06.2023
                        // TODO: 04.04.2023   Data
                        // TODO: 04.04.2023   Data
                        TableData1Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData1Row);
                        TableData2Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData2Row);
                        TableData3Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData3Row);
                        TableData4Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData4Row);
                        TableData5Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData5Row);
                        TableData6Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData6Row);
                        TableData7Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData7Row);
                        TableData8Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData8Row);
                        // TODO: 26.06.2023

                        linkedHashMapНазвание.put(TableRow1Name,TableData1Row);
                        linkedHashMapНазвание.put(TableRow2Name,TableData2Row);
                        linkedHashMapНазвание.put(TableRow3Name,TableData3Row);
                        linkedHashMapНазвание.put(TableRow4Name,TableData4Row);
                        linkedHashMapНазвание.put(TableRow5Name,TableData5Row);
                        linkedHashMapНазвание.put(TableRow6Name,TableData6Row);
                        linkedHashMapНазвание.put(TableRow7Name,TableData7Row);
                        linkedHashMapНазвание.put(TableRow8Name,TableData8Row);
                        // TODO: 26.06.2023
                        linkedHashMapsНазваниеиДанные.add(linkedHashMapНазвание);

                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" tableLayout_single_tabel_one_swipe   "
                            + tableLayout_single_tabel_one_swipe +
                            " tableLayout_single_tabel_one_swipe " + tableLayout_single_tabel_one_swipe);
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

        @SuppressLint("Range")
        class MyRecycleViewAdapter extends RecyclerView.Adapter< MyViewHolder> {
            private Cursor cursor;
            public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                try{
                    if (cursor!=null) {
                        this.cursor = cursor;
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cursor " +cursor );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
                try {
                    // TODO: 23.06.2023
                    if (cursor!=null && cursor.getCount()>0 && !cursor.isClosed()) {
                        // TODO: 16.11.2023  Считаем Дни календаря
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                                " ДниПразничные" +ДниПразничные  + "  ДниВыходные " +ДниВыходные);



                        // TODO: 22.06.2023 данные
                        МетодЗаполняемДаннымиRecycreViewSingleTable(holder, cursor);
                        // TODO: 06.04.2023


                        // TODO: 14.04.2023 ДОПОЛНИТЕЛЬНЫЕ МЕТОДЦ ПОСЛЕ ВСТАВКИ ЛДАННЫХ
                        //  Cursor     cursorForЧАсов=    new SubClassGetCursor().МетодSwipesКурсор();
                        методСчитаемЧасы(cursor);

                        // TODO: 04.04.2023  ФИО
                        new  SubClassChanegeSetNameProffesio().    МетодЗаполняемФИОRow( cursor);
                        // TODO: 16.04.2023 Професии Професии Професии Професии
                        МетодаКликаTableRowФИО( );

                        // TODO: 26.06.2023  перегрузка
                        методПерегрузкиRecycreView();

                    }

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                            " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
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
                try{
                    recyclerView.removeAllViews();
                    recyclerView.getRecycledViewPool().clear();
                    super.onAttachedToRecyclerView(recyclerView);
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
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
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
            @SuppressLint("Range")
            public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewSingleTabel = null;
                try {

                    if (cursor!=null) {
                        // TODO: 16.11.2023
                        if (cursor.getCount()>0) {
                            //  viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_mm_one_row, parent, false);
                            viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_one_single, parent, false);
                            // TODO: 23.06.2023
                            Log.d(this.getClass().getName(),"\n" + " НЕт ДАнных class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                        }else{
                            viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_single_tabel, parent, false);
                            // TODO: 23.06.2023
                            Log.d(this.getClass().getName(),"\n" + " НЕт ДАнных class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                        }
                    }else {
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_isnull_tabel, parent, false);
                    }

                    // TODO: 04.04.2023  Запускаем ПОлучений Вид View
                    myViewHolder = new  MyViewHolder(viewSingleTabel);
                    // TODO: 18.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder+
                            myViewHolder.getLayoutPosition()+ myViewHolder.getAbsoluteAdapterPosition());

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
                    holder.itemView.startAnimation(animationFromRecyReview);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                            " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
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
            private void МетодЗаполняемДаннымиRecycreViewSingleTable(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                try {
// TODO: 26.06.2023 Цикл Крутим Нахвание и Данные Экрана
                    Flowable.fromIterable(holder.linkedHashMapsНазваниеиДанные)
                            .onBackpressureBuffer()
                            .blockingIterable().forEach(new Consumer<LinkedHashMap<TableRow, TableRow>>() {
                                @Override
                                public void accept(LinkedHashMap<TableRow, TableRow> tableRowTableRowLinkedHashMap) {
                                    // TODO: 16.11.2023
                                    tableRowTableRowLinkedHashMap.forEach(new BiConsumer<TableRow, TableRow>() {
                                       Integer ПозицияДанныех = 0;
                                        @Override
                                        public void accept(TableRow tableRowНазвание, TableRow tableRowДанные) {
                                            // TODO: 26.06.2023 смешение на данных если TableRow
                                            Integer   ПозицияДляСмещениеДанных =      методСмещенияДляКурсораForTableRow(ПозицияДанныех);
                                            // TODO: 04.04.2023   DATA ROW
                                            МетодЗаполняемДаннымиTableRow(cursor ,holder ,tableRowДанные, ПозицияДляСмещениеДанных);
                                            // TODO: 04.04.2023   Name ROW
                                            МетодЗаполняеШабкаTableRow(cursor ,holder ,tableRowНазвание, ПозицияДляСмещениеДанных);
                                            // TODO: 26.06.2023  поднимае версию
                                            ПозицияДанныех = ПозицияДанныех+1;
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    +  "  ПозицияДляСмещениеДанных   "+ПозицияДляСмещениеДанных);
                                        }
                                    });

                                }
                            });

                    // TODO: 26.06.2023
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

            private Integer методСмещенияДляКурсораForTableRow( @NonNull Integer ПозицияДанныех) {
                switch (ПозицияДанныех){
                    case  0:
                        ПозицияДанныех=1;
                        break;
                    case  1:
                        ПозицияДанныех=5;
                        break;
                    case  2:
                        ПозицияДанныех=9;
                        break;
                    case  3:
                        ПозицияДанныех=13;
                        break;
                    case  4:
                        ПозицияДанныех=17;
                        break;
                    case  5:
                        ПозицияДанныех=21;
                        break;
                    case  6:
                        ПозицияДанныех=25;
                        break;
                    case  7:
                        ПозицияДанныех=29;
                        break;
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ПозицияДанныех " + ПозицияДанныех);
                return  ПозицияДанныех;
            }


            private void МетодЗаполняемДаннымиTableRow(@NonNull Cursor cursor,
                                                       @NonNull  MyViewHolder holder, @NonNull  TableRow tableRowДанные, @NonNull  Integer ПозицияДня) {
                try {

                    if (tableRowДанные!=null) {
                        // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                        for (int i = 0; i < tableRowДанные.getChildCount(); i++) {
                            EditText editTextRowКликПоДАнными = (EditText) tableRowДанные.getChildAt(i);
                            String ДнейСодержимое =            "d"+ПозицияДня;
                            // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                            if (ДниВыходные.containsKey(ДнейСодержимое.trim())) {
                                String ВыходныеИлиПразничные=    ДниВыходные.get(ДнейСодержимое.trim());
                                if (ВыходныеИлиПразничные!=null) {
                                    editTextRowКликПоДАнными.setVisibility(View.VISIBLE);
                                }
                                методЗаполениеСодеримомRowData(editTextRowКликПоДАнными, cursor, ДнейСодержимое);

                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " editTextRowКликПоДАнными " + editTextRowКликПоДАнными + " ДнейСодержимое " + ДнейСодержимое);
                                // TODO: 10.05.2023 Сохранение ДАнных Ячейки
                                МетодаСохранениеДанныхЯчейкиRow(editTextRowКликПоДАнными );
                                // TODO: 19.10.2022 Переход на Метки Табеля
                                методПереходНаМеткиТАбедяcRow(editTextRowКликПоДАнными);
                                // TODO: 05.04.2023 Иниуиализация Клавиаьтуры Поднятие для кажой Ячейки
                                методИницаллизацииКлавиаотурыЯчейка(editTextRowКликПоДАнными);
                                // TODO: 10.05.2023

                            }

                            // TODO: 26.06.2023
                            ПозицияДня=ПозицияДня+1;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПозицияДня  " + ПозицияДня);
                        }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                        // TODO: 10.05.2023  Слушатель ПО ЯЧЕЙКАМ
                        // TODO: 19.10.2022
                    }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
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



            private void МетодЗаполняеШабкаTableRow(@NonNull Cursor cursor,
                                                    @NonNull  MyViewHolder holder, @NonNull  TableRow tableRowНазвания, @NonNull  Integer ПозицияДня) {
                try {
                    if (tableRowНазвания!=null) {
                        // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                        for (int i = 0; i < tableRowНазвания.getChildCount(); i++) {
                            TextView textViewНазвание = (TextView) tableRowНазвания.getChildAt(i);
                            String ДнейНазвание =            "d"+ПозицияДня;
                            // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                            if (ДниВыходные.containsKey(ДнейНазвание.trim())) {
                                String ВыходныеИлиПразничные=    ДниВыходные.get(ДнейНазвание.trim());
                                if (ВыходныеИлиПразничные!=null) {
                                    textViewНазвание.setVisibility(View.VISIBLE);
                                }
                                ВыходныеИлиПразничные =          методЗаполениеНазванияRowData(textViewНазвание, ДнейНазвание);
                                // TODO: 26.06.2023
                                методЗаполениеНазванияЦвет(textViewНазвание,ВыходныеИлиПразничные);

                            }
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " textViewНазвание " + textViewНазвание + " ДнейНазвание " + ДнейНазвание);
                            // TODO: 26.06.2023
                            ПозицияДня=ПозицияДня+1;
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПозицияДня  " + ПозицияДня);
                        }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                        // TODO: 10.05.2023  Слушатель ПО ЯЧЕЙКАМ
                        // TODO: 19.10.2022
                    }


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
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
            private void методЗаполениеСодеримомRowData(@NonNull  EditText editTextRowКликПоДАнными,
                                                        @NonNull Cursor cursor,
                                                        @NonNull String НазваниеДляДень) {
                try {
                    Bundle dataRowData = null;
                    String День =  cursor.getString(cursor.getColumnIndex(НазваниеДляДень)) ;
                    if(День==null ||День.equalsIgnoreCase("0")) {
                        День=new String();
                    }
                    CurrenrsСhildUUID= Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l);
                    dataRowData = new Bundle();
                    dataRowData.putString("ЗначениеДня", День);
                    dataRowData.putLong("uuid", CurrenrsСhildUUID);
                    dataRowData.putString("День", НазваниеДляДень);
                    // TODO: 13.04.2023  дополнительные
                    dataRowData.putLong("MainParentUUID", MainParentUUID);

                    Integer ПолощениеДанных=myRecycleViewAdapter.cursor.getPosition();


                    dataRowData.putInt("Position",ПолощениеДанных );
                    dataRowData.putInt("ГодТабелей",   ГодТабелей);
                    dataRowData.putInt("МЕсяцТабелей", МЕсяцТабелей);
                    dataRowData.putInt("DigitalNameCFO", DigitalNameCFO);
                    dataRowData.putString("FullNameCFO", FullNameCFO);
                    dataRowData.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу);
                    dataRowData.putLong("CurrenrsСhildUUID", CurrenrsСhildUUID);
                    dataRowData.putString("ФИО", ФИО);
                    dataRowData.putLong("CurrenrsSelectFio", CurrenrsSelectFio);
                    editTextRowКликПоДАнными.setTag(dataRowData);
                    editTextRowКликПоДАнными.setText(День.trim());
                    // TODO: 07.06.2023
                    методИзменяемЦветСодержимоваЦифраИлиБуква(editTextRowКликПоДАнными, День);


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень "
                            + НазваниеДляДень + "dataRowData " + dataRowData);
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

            private void методИзменяемЦветСодержимоваЦифраИлиБуква(@NonNull EditText editTextRowКликПоДАнными,@NonNull String День) {
                try {
                    if (День.length()>0) {
                        char ЕслиБуквывДнеСодержимое=     День.charAt(0);
                        if(Character.isLetter( ЕслиБуквывДнеСодержимое )){
                            editTextRowКликПоДАнными.setTextColor(Color.GRAY);
                        }else {
                            editTextRowКликПоДАнными.setTextColor(Color.BLACK);
                        }
                    }else{
                        editTextRowКликПоДАнными.setTextColor(Color.BLACK);
                    }
                    editTextRowКликПоДАнными.refreshDrawableState();
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень ");
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

            private String методЗаполениеНазванияRowData(@NonNull  TextView TextViewRowКликПоНазваниям,String s) {
                String ВыходныеИлиПразничные = null;
                try {
                    // TODO: 11.04.2023 Ставим Дни
                    ВыходныеИлиПразничные=    ДниВыходные.get(s.trim());
                    TextViewRowКликПоНазваниям.setText( ВыходныеИлиПразничные);
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " s.trim() " +s.trim() + "  ДниВыходные.get(s.trim()) "
                            + ДниВыходные.get(s.trim()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return ВыходныеИлиПразничные;
            }
            // TODO: 07.06.2023 цвет
            private void методЗаполениеНазванияЦвет(@NonNull  TextView TextViewRowКликПоНазваниям,String ВыходныеИлиПразничные) {
                try {
                    // TODO: 11.04.2023 Ставим Дни
                    if ( ДниПразничные.containsValue(ВыходныеИлиПразничные.trim())==true) {
                        TextViewRowКликПоНазваниям.setTextColor(Color.parseColor("#DC143C"));
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                                + ВыходныеИлиПразничные);
                    } else {
                        TextViewRowКликПоНазваниям.setTextColor(Color.parseColor("#008080"));
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                                + ВыходныеИлиПразничные);
                    }










                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                            + ВыходныеИлиПразничные);
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


            // TODO: 08.11.2022 метод КЛИК ПО ДАННЫМ
            private void МетодаСохранениеДанныхЯчейкиRow(@NonNull   EditText editTextRowКликПоДАнными  ) {
                try{

                    if (editTextRowКликПоДАнными!=null) {
                        final String[] До = new String[1];
                                editTextRowКликПоДАнными.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                            @Override
                            public void onViewAttachedToWindow(View v) {

                                // TODO: 25.08.2023  тест код
                                disposableAfterTextChangeEvent=           RxTextView.afterTextChangeEvents( editTextRowКликПоДАнными)
                                        .skip(1)
                                        .debounce(300,TimeUnit.MILLISECONDS)///общее время event
                                        .filter(edit->edit.component1().getText().toString().length()<=2)
                                        .subscribeOn(Schedulers.single())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .throttleLatest(100,TimeUnit.MILLISECONDS)//из общего время  последних event
                                        .distinct().forEachWhile(new Predicate<TextViewAfterTextChangeEvent>() {
                                            @Override
                                            public boolean test(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Throwable {
                                                // TODO: 24.08.2023
                                                try{
                                                if(textViewAfterTextChangeEvent.component1().isInputMethodTarget()){

                                                    String   НовоеЗначенияДня   =(String) textViewAfterTextChangeEvent.component1().getText().toString();
                                                    if (!НовоеЗначенияДня.isEmpty()) {
                                                        НовоеЗначенияДня=   НовоеЗначенияДня.replaceAll("[^0-9]","").trim();
                                                    }


                                                    методListerAfterSaveNewDay (editTextRowКликПоДАнными,НовоеЗначенияДня);

                                                    // TODO: 24.08.2023
                                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                                                    return true;
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
                                                return false;
                                            }
                                        });
                            }

                            @Override
                            public void onViewDetachedFromWindow(View v) {

                            }
                        });

                    }
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

            // TODO: 24.08.2023 метод слушатель с последующим запись
            void  методListerAfterSaveNewDay (@NonNull EditText editTextRowКликПоДАнными,@NonNull String новоеЗначениеДня){
                try{
                        // TODO: 11.04.2023 Оперция Обновлнения ЯЧЕЕК
                        SubClassUpdatesCELL subClassUpdateSingletabel = new SubClassUpdatesCELL(getContext());
                        // TODO: 10.05.2023  ЗАВПИСЫАЕМ НОВЫЕ ДАННЫВЕ В БАЗУ
                        Integer РезультатОбновлениеЯчейки = subClassUpdateSingletabel.МетодВалидацияЯчеекSaveCell(editTextRowКликПоДАнными,новоеЗначениеДня);
                        // TODO: 10.05.2023 После операции Сохранение в Ячкейке
                        if (РезультатОбновлениеЯчейки > 0) {
                            // TODO: 06.07.2023 Считаем ЧАсы
                            методRefrefyGetDataRecycreView();
                            методСчитаемЧасы(myRecycleViewAdapter.cursor);
                                 message.getTarget().postDelayed(()->{
                                     editTextRowКликПоДАнными.startAnimation(animation1);
                                 },50);

                            // TODO: 19.06.2023 код когда данные в ячейке не сохранились
                        } else {
                            методКогдаДанныеНеСохранились(editTextRowКликПоДАнными, новоеЗначениеДня);

                        }
                        // TODO: 24.04.2023  после обновление ячейки Считаем Часы
                        методИзменяемЦветСодержимоваЦифраИлиБуква((editTextRowКликПоДАнными), новоеЗначениеДня);

                    Log.d(this.getClass().getName(), "\n" + " class " +
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



            private void методПереходНаМеткиТАбедяcRow(@NonNull EditText editTextRowКликПоДАнными) {
                try{
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

            // TODO: 21.04.2023  записб данных в ячейку


            private void методRefrefyGetDataRecycreView() {
                try{
                    Integer Полощения=  myRecycleViewAdapter.cursor.getPosition();
                    Cursor     cursorForЧАсов=     myRecycleViewAdapter.cursor;
                    cursorForЧАсов=    new SubClassGetCursor().МетодSwipesКурсор();
                    cursorForЧАсов.moveToPosition(Полощения);
                    myRecycleViewAdapter.cursor=cursorForЧАсов;
                    Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }



            private void методКогдаДанныеНеСохранились(@NonNull EditText editTextRowКликПоДАнными,
                                                       @NonNull String После) {
                try{
                    editTextRowКликПоДАнными.setError(После);
                    message.getTarget().postDelayed(()->{
                        editTextRowКликПоДАнными.setError(null);
                },1000);
                Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
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
                int КоличесвоСтрок=1;
                try {
                    ///КоличесвоСтрок =ДниВыходные.size();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " КоличесвоСтрок "+КоличесвоСтрок);
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
























        private void методЗакрываемКлавитатуру( ) {
            try{
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                recycleviewsingletabel.clearFocus();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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

        // TODO: 03.04.2023 перенесеный RecycreView
        private void МетодСлушательКурсора( @NonNull Cursor cursor) {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                cursor.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                    }
                });
                // TODO: 15.10.2022
                cursor.registerContentObserver(new ContentObserver(handlerМетодForCurcorHandlerCallBack) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        super.onChange(selfChange, uri);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        // TODO: 15.06.2023 свайвы
        private void методДляSimpeCallbacks(   ) {
            try {

                // TODO: 11.05.2023 SWIPE:
                ItemTouchHelper.SimpleCallback simpleItemTouchCallbackSwipe = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,
                        ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

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
                        try {
                            // TODO: 17.06.2023 сама свайп
                            SubClassReBornDataRecyreView subClassReBornDataRecyreView=new SubClassReBornDataRecyreView();
                            subClassReBornDataRecyreView.методПереРоденияRevireViewScroll();

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @SuppressLint("NewApi")
                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                            @NonNull RecyclerView.ViewHolder viewHolder,
                                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try{

                            // TODO: 16.11.2023 офрмлаяем дизацн при SWIPE
                            методИзмененияЦветаSwipes(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                            // TODO: 16.11.2023  после как переопределили вызыывем супер
                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "PositionCustomer   "
                                    + " CurrenrsСhildUUID " + CurrenrsСhildUUID + " CurrenrsSelectFio " + CurrenrsSelectFio + "  ФИО " + ФИО);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    private void методИзмененияЦветаSwipes(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                                           @NonNull RecyclerView.ViewHolder viewHolder,
                                                           float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try {
                            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                                // TODO: 18.06.2023 первый вариант
                                RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) viewHolder;
                                View itemView = vh.itemView;

                                Paint p = new Paint();
                                if(dX > 0) {
                                    p.setColor(Color.parseColor("#FFFFFF"));//828080 /// FCFAFA //F2F8F8
                                } else {
                                    p.setColor(Color.parseColor("#FFFFFF")); /// F2F8F8

                                }
                                c.drawRect(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom(), p);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                ItemTouchHelper itemTouchHelperLEFT = new ItemTouchHelper(simpleItemTouchCallbackSwipe);
                itemTouchHelperLEFT.attachToRecyclerView(recycleviewsingletabel);
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "recycleviewsingletabel   " + recycleviewsingletabel);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }





        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        @SuppressLint("FragmentLiveDataObserve")
        void методWorkManagerLifecycleOwner() {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                lifecycleOwnerОбщая.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });

                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая)
                        .observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfos) {
                                workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                    try {
                                        if(СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING)!=0) {
                                            long end = Calendar.getInstance().getTimeInMillis();
                                            Integer ReturnCallPublic = СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getInt("ReturnPublicAsyncWork", 0);

                                            // TODO: 17.06.2023  если прошло время нужное
                                            long РазницаВоврмени=end-startДляОбноразвовной;
                                            if (РазницаВоврмени>20000) {
                                                // TODO: 04.07.2023 обновление данных их WorkManager
                                                методОбновлениеДанныхИзWorkManager();

                                                // TODO: 16.06.2023  перегрузка экрана
                                                singleTabelRecycreView.   методПерегрузкиRecycreView();

                                                // TODO: 04.07.2023 перегрузка внешнего видаметодПерегрузкиRecycreView();
                                            }
                                        }
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методОбновлениеДанныхИзWorkManager() {
            try{
                Cursor    cursorForViewPager=        myRecycleViewAdapter.cursor;
                cursorForViewPager  =   singleTabelRecycreView.  new SubClassGetCursor().МетодSwipesКурсор();
                // TODO: 21.06.2023 Смещения Курсоора
                cursorForViewPager.moveToPosition(myRecycleViewAdapter.cursor.getPosition());
                myRecycleViewAdapter.cursor= cursorForViewPager;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                // TODO: 18.06.2023
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методRebootRecyreview(@NotNull   Cursor    cursorForRebootCursor) {
            try{
                // TODO: 21.06.2023 Смещения Курсоора
                myRecycleViewAdapter.cursor=cursorForRebootCursor;
                myRecycleViewAdapter.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewОбновление=         recycleviewsingletabel.getAdapter();
                recycleviewsingletabel.swapAdapter(recyclerViewОбновление,true);
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" cursorForRebootCursor " +cursorForRebootCursor );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        private void методПерегрузкиRecycreView() {
            try{
                recycleviewsingletabel.setFocusable(true);
                recycleviewsingletabel.setClickable(true);
                recycleviewsingletabel.smoothScrollToPosition(0);


                spinnerchasy.refreshDrawableState();
                spinnerchasy.requestLayout();


                recycleviewsingletabel.refreshDrawableState();
                recycleviewsingletabel.requestLayout();

                spinnerdepartament.refreshDrawableState();
                spinnerdepartament.requestLayout();

                spinnermesazyear.refreshDrawableState();
                spinnermesazyear.requestLayout();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
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
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorForViewPager "+ cursorForViewPager);
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
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorForViewPager "+ cursorForViewPager);
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




        void  МетодForCurcorHandlerCallBack(){

            try{

                Handler.Callback callback = new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull android.os.Message msg) {
                        Log.d(this.getClass().getName(), " " +
                                " SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " +
                                msg + " msg.getWhen() " + msg.what);
                        return true;
                    }
                };
                handlerМетодForCurcorHandlerCallBack = new Handler(callback);

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




        void методСчитаемЧасы(@NonNull Cursor cursor ) {
            try{
                Integer ПозицуияВыбраногоСОтрудника=      cursor.getPosition();
                ПозицуияВыбраногоСОтрудника=ПозицуияВыбраногоСОтрудника+1;
                //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                Integer   ЧасыТекущегоСОтрудника = new Class_MODEL_synchronized(getContext()).МетодПосчётаЧасовПоСотруднику(cursor);
                Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);

                String ЧасыТекущегоСотрудника="(" + ЧасыТекущегоСОтрудника + "/часы)  "
                        + ""+ ПозицуияВыбраногоСОтрудника+" из "+  cursor.getCount()+"";


                // TODO: 20.11.2023  заполение часов
                
                class SpinerЧасы extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerЧасы().metodAddDataSpinersHasyAndMotch(spinnerchasy,ЧасыТекущегоСотрудника);

         


                Log.d(Class_MODEL_synchronized.class.getName()," RowNumber  " + " cursorForViewPager " +cursor.getPosition()
                        +"myViewHolder.getLayoutPosition()   "+myViewHolder.getLayoutPosition() + "CurrentFragmentMaxItem  " + CurrentFragmentMaxItem);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }


        // TODO: 08.11.2022 КЛИК ПО ФИО
        @SuppressLint("Range")
        private void МетодаКликаTableRowФИО() {
            try{
                materialTextViewprofession.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                        if (bundleПереходДетализацию != null) {
                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                            try{
                                TextView TextViewФИОДляУдаление = (TextView) v;
                                Log.d(this.getClass().getName(), " v " + v.getTag() + " TextViewФИОДляУдаление.getText() " + TextViewФИОДляУдаление.getText() +
                                        "  TextViewФИОДляУдаление.getTag() " +TextViewФИОДляУдаление.getTag());
                                //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                                Bundle bundleTabelViewПрофессияФИО= (Bundle) TextViewФИОДляУдаление.getTag();
                                bundleTabelViewПрофессияФИО.putString("ФИО",  ФИО);
                                bundleTabelViewПрофессияФИО.putString("СамЗапрос","  SELECT * FROM  fio WHERE uuid=? ");
                                bundleTabelViewПрофессияФИО.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(CurrenrsSelectFio)});
                                bundleTabelViewПрофессияФИО.putString("Таблица","fio");
                                Cursor    КурсорТаблицаФИО=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleTabelViewПрофессияФИО);
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
                                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
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
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        ///todo сообщение информация о ФИО
        @UiThread
        protected void СообщениеИнформацияОСотруднике(@NonNull String ШабкаДиалога,
                                                      @NonNull String СообщениеДиалога) {
            ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
            try{
//////сам вид
                final AlertDialog alertDialogДетализацияДАнныхСотрудника = new MaterialAlertDialogBuilder(getActivity())
                        .setTitle(ШабкаДиалога)
                        .setMessage(СообщениеДиалога)
                        .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    materialTextViewprofession.setBackgroundColor(Color.WHITE);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " materialTextViewprofession  " + materialTextViewprofession);
                                    dialog.dismiss();
                                    dialog.cancel();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        })
                        .setNegativeButton("Изменить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    materialTextViewprofession.setBackgroundColor(Color.WHITE);
                                    Bundle bundleПрофесии=new Bundle();
                                    bundleПрофесии.putString("СамЗапрос","  SELECT * FROM  prof WHERE uuid!=? ");
                                    bundleПрофесии.putStringArray("УсловияВыборки" ,new String[]{"0"});
                                    bundleПрофесии.putString("Таблица","prof");
                                    Cursor    КурсорТаблицаПрофесии=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleПрофесии);
                                    Log.d(this.getClass().getName(), " КурсорТаблицаПрофесии" + КурсорТаблицаПрофесии);
                                    // TODO: 27.03.2023 Новый ПОсик
                                    new SubClassSearchProfessia().МетодСообщениеНовыйПоиска(getActivity(),КурсорТаблицаПрофесии ,message,"prof", CurrenrsСhildUUID);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " materialTextViewprofession  " + materialTextViewprofession);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




        //TODO Перерождения Данных recycreView Отдельный Класс
        public   class SubClassReBornDataRecyreView{
            void методПереРоденияRevireViewScroll () {
                try{
                    // TODO: 15.06.2023 Scroll Left RecyreView
                    singleTabelRecycreView. методЗакрываемКлавитатуру();
                    spinnerdepartament.setClickable(false);
                    spinnerdepartament.setFocusable(false);

// TODO: 16.06.2023  ПРОИЗВОДИМ САМ СВАЙП
                    message.getTarget().postDelayed(()->{
                        materialTextViewprofession.startAnimation(animation1);
                        materialTextViewfio.startAnimation(animation1);
                    },50);

                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(65, VibrationEffect.EFFECT_HEAVY_CLICK));

                        // TODO: 16.06.2023
                        Cursor cursorSwipeViewPager=      singleTabelRecycreView.      методScrollsLeftRecyreView();
                        // TODO: 22.06.2023
                        fragmentSingleTabel.new SubClassBungleSingle().методGETДанныеRunTimeИзCursor(cursorSwipeViewPager );
                        // TODO: 16.06.2023  после переполуение данныз перегрузка экрана
                        singleTabelRecycreView.     методПерегрузкиRecycreView();

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem +
                                " oldScrollY ");

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

            // TODO: 30.11.2023 class for swile








        }
        class SubClassSearchProfessia {
            private   Cursor cursorДанные;
            private  MaterialButton materialButtonЗакрываемПосик;

            private
            androidx.appcompat.widget.SearchView searchViewДляНовогоПоиска;

            private  AlertDialog      alertDialogНовыйПосик;

            private ListView listViewДляНовыйПосик;
            @UiThread
            private void МетодСообщениеНовыйПоиска(@NonNull Activity activity
                    ,@NonNull Cursor cursorДанные,
                                                   @NonNull Message message
                    ,@NonNull String ТаблицаПосика,
                                                   @NonNull Long РодительскийUUDТаблицыТабель) {
                try{
                    this.cursorДанные=cursorДанные;
                    alertDialogНовыйПосик= new MaterialAlertDialogBuilder(activity){
                        @NonNull
                        @Override
                        public MaterialAlertDialogBuilder setView(View view) {
                            listViewДляНовыйПосик =    (ListView) view.findViewById(R.id.SearchViewList);
                            listViewДляНовыйПосик.setTextFilterEnabled(true);
                            searchViewДляНовогоПоиска=    (androidx.appcompat.widget.SearchView) view.findViewById(R.id.searchview_newscanner);
                            searchViewДляНовогоПоиска.setQueryHint("Поиск");
                            // TODO: 14.12.2022
                            searchViewДляНовогоПоиска.setDrawingCacheBackgroundColor(Color.GRAY);
                            searchViewДляНовогоПоиска.setDrawingCacheEnabled(true);



                            TextView textViewСтрокаПосика = searchViewДляНовогоПоиска.findViewById(com.google.android.material.R.id.search_src_text);




                            textViewСтрокаПосика.setTextColor(Color.rgb(0,102,102));
                            textViewСтрокаПосика.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                            // TODO: 14.12.2022
                            materialButtonЗакрываемПосик =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                            materialButtonЗакрываемПосик.setText("Закрыть");
                            ///TODO ГЛАВНЫЙ АДАПТЕР чата
                            SimpleCursorAdapter simpleCursorAdapterЦФО= new SimpleCursorAdapter(getContext(),
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
                                                        Log.d(getContext().getClass().getName(), " НазваниеПрофесии " + "--" + НазваниеПрофесии);/////
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
                                                            try{
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
                                                                ((MaterialTextView)view).setTag(bundle);


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

                                                                    if (  searchViewДляНовогоПоиска.getQuery().toString().length()>5 ) {
                                                                        searchViewДляНовогоПоиска.setQuery("",true);
                                                                        searchViewДляНовогоПоиска.refreshDrawableState();
                                                                        // TODO: 21.07.2023  меняем Професию
                                                                        Integer ПровйдерСменаПрофесии=
                                                                                МетодЗаписиСменыПрофесии( (androidx.appcompat.widget.SearchView)  searchViewДляНовогоПоиска,getActivity());
                                                                        if (ПровйдерСменаПрофесии>0) {
                                                                            // TODO: 21.07.2023  после смены професии
                                                                            методReeoBootCursorRecyreViewAlfterChangeProffesion();


                                                                            new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии(НазваниеПрофесии , materialTextViewprofession);

                                                                            alertDialogНовыйПосик.dismiss();
                                                                            alertDialogНовыйПосик.cancel();

                                                                        }else {
                                                                            Toast.makeText(getActivity(), "Профессия не сменилась !!! ", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                            + " listViewДляНовыйПосик "+ listViewДляНовыйПосик+"\n+"+
                                                                            " cursorДанные " + cursorДанные +"\n"+
                                                                            " cursorДанные " + cursorДанные +"\n" );

                                                                }

                                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                        + "  ((MaterialTextView)view) "+ ((MaterialTextView)view).getTag());
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                                        this.getClass().getName(),
                                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            }
                                                        }

                                                    });
                                                    // TODO: 13.12.2022 филь
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                            simpleCursorAdapterЦФО.notifyDataSetChanged();
                            listViewДляНовыйПосик.setAdapter(simpleCursorAdapterЦФО);
                            listViewДляНовыйПосик.startAnimation(animation1);
                            listViewДляНовыйПосик.setSelection(0);
                            listViewДляНовыйПосик.forceLayout();

                            // TODO: 13.12.2022  Поиск и его слушель
                            МетодПоискаФильтрНовыйПосик(searchViewДляНовогоПоиска,simpleCursorAdapterЦФО,message,listViewДляНовыйПосик,ТаблицаПосика);
                            // TODO: 13.12.2022 Закрыаем Посик Профессий
                            методЗакрываемПосикПровфесиий(materialButtonЗакрываемПосик);
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " listViewДляНовыйПосик "+listViewДляНовыйПосик+"\n+"+
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
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " listViewДляНовыйПосик "+listViewДляНовыйПосик+"\n+"+
                            " cursorДанные " +cursorДанные+"\n"+
                            " cursorДанные " +cursorДанные+"\n" );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }



            private void методЗакрываемПосикПровфесиий(@NonNull MaterialButton materialButtonЗакрываемПосик) {
                // TODO: 21.07.2023
                materialButtonЗакрываемПосик.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), " position");
                            Log.d(this.getClass().getName(), "МетодСозданиеТабеля  v " + v);
                            // TODO: 28.03.2023 ЗАПИСЫВАЕМ НОВУЮ ПРОФЕССИЮ В БАЗУ
                            alertDialogНовыйПосик.dismiss();
                            alertDialogНовыйПосик.cancel();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            }
            // TODO: 27.03.2023  ВТОРОЙ МЕТОД ПОИСККА ПО БАЗЕ

            private void МетодПоискаФильтрНовыйПосик(@NonNull  androidx.appcompat.widget.SearchView searchViewДляНовогоЦФО,
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
                                    }else {
                                        if (cursorДанные.getCount()==0) {
                                            searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                            message.getTarget().postDelayed(() -> {
                                                searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                            }, 500);
                                        }
                                    }
                                    if ( constraint.length()==0) {
                                        simpleCursorAdapterЦФО.swapCursor(cursorДанные);
                                        listViewДляНовыйПосик.setSelection(0);
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
                                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 02.08.2022
            protected  Cursor МетодКурсорДляНовогоПосика(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
                Cursor КурсорТаблицаПрофесииLike = null;
                try{
                    Integer   ПубличныйIDДляФрагмента     = new Class_Generations_PUBLIC_CURRENT_ID().
                            ПолучениеПубличногоТекущегоПользователяID(getContext());
                    Log.d(getContext().getClass().getName(), "\n"
                            + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента + " Фильтр " +Фильтр);
                    Bundle bundleНовыйПоиск=new Bundle();
                    bundleНовыйПоиск.putString("СамЗапрос","  SELECT * FROM  prof WHERE name  LIKE  ?  ");
                    bundleНовыйПоиск.putStringArray("УсловияВыборки" ,new String[]{"%"+Фильтр+"%"});
                    bundleНовыйПоиск.putString("Таблица","prof");
                    КурсорТаблицаПрофесииLike=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleНовыйПоиск);
                    Log.d(this.getClass().getName(), " КурсорТаблицаПрофесииLike" + КурсорТаблицаПрофесииLike);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
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
                    Long ВерсияДанныхUp = new SubClassUpVersionDATA().upVersionCurentTable(ТаблицаОбработки,getContext());
                    valuesСменаПрофесси.put("current_table",ВерсияДанныхUp);
                    String ДатаОбновления=     new Class_Generation_Data(getContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
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




        @SuppressLint("Range")
        class SubClassChanegeSetNameProffesio{
            private String МетодЗаполняемФИОRow( @NonNull  Cursor   cursor  ) {
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
                    Cursor    КурсорПрофессия=      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleTabelViewПосикПрофессия);
                    Log.d(this.getClass().getName(), " КурсорПрофессия" + КурсорПрофессия);


                    // TODO: 20.11.2023  уставналиваем проыесиию и ФИО

                    metodSetProfesfionSingleTabel(КурсорПрофессия);


                    // TODO: 17.04.2023 Tag
                    bundleTabelViewПосикПрофессия.putString("ФИО",ФИО);
                    bundleTabelViewПосикПрофессия.putString("Профессия",Профессия);
                    bundleTabelViewПосикПрофессия.putInt("ПрофессияИзDatatabels",ПрофессияИзDatatabels);
                    bundleTabelViewПосикПрофессия.putInt("ПрофессияИзФИо",ПрофессияИзФИо);
                    materialTextViewprofession.setTag(bundleTabelViewПосикПрофессия);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  "
                            +cursor+ "materialTextViewprofession " + materialTextViewprofession);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  Профессия;
            }


            // TODO: 20.11.2023  устанвливаем фпросию
            private void metodSetProfesfionSingleTabel(@NotNull Cursor КурсорПрофессия) {
                try{
                if (КурсорПрофессия.getCount()>0) {
                    Профессия = КурсорПрофессия.getString(КурсорПрофессия.getColumnIndex("name"));
                    // TODO: 20.11.2023
                    if (   Профессия.length()> 0 && !Профессия.trim().matches("(.*)Должность не заполнена(.*)")) {
                        new SetFioAndProfessionDisayn(ФИО.length()).методПерегрузкаВидаПрофесии(ФИО.trim() , materialTextViewfio);
                        new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии("("+Профессия.trim()+")" , materialTextViewprofession);
                        // TODO: 20.11.2023  когда не професии
                    }else {
                        metodDontPresesssion();
                    }
                }else {
                    metodDontPresesssion();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  "
                        + "Профессия " + Профессия);
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

            private void metodDontPresesssion() {
                Профессия="нет должности";
                new SetFioAndProfessionDisayn(ФИО.length()).методПерегрузкаВидаПрофесии(ФИО.trim() , materialTextViewfio);
                new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии("("+Профессия.trim()+")" , materialTextViewprofession);
            }
        }
        private void МетодПереходаНаМеткиТабеля(@NonNull EditText editTextЯчейка) {
            try {
                Intent intentПереХодНаМеткиТабеля = new Intent();
                intentПереХодНаМеткиТабеля.setClass(getContext(), MainActivity_Metki_Tabel.class);
                Bundle bundleToMainActitivyMetkiTabel= (Bundle) editTextЯчейка.getTag();
                intentПереХодНаМеткиТабеля.putExtras(bundleToMainActitivyMetkiTabel);
                message.getTarget().postDelayed(()->{
                    // TODO: 10.04.2023  переход ИЗ MAINaCTITyTabelSingle Peolpe
                    // TODO: 10.04.2023  ОТПРАВЛЯЕММ ПЕРЕМЕННЫЕ
                    startActivity(intentПереХодНаМеткиТабеля);
                },100);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FullNameCFO " + FullNameCFO + " CurrenrsСhildUUID " + CurrenrsСhildUUID
                        + " ГодТабелей " + ГодТабелей + " МЕсяцТабелей " + МЕсяцТабелей + " DigitalNameCFO " + DigitalNameCFO +
                        " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " + ИмесяцвИГодСразу+ " editTextЯчейка " +editTextЯчейка);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        public    class SubClassGetCursor{
            Cursor          cursor = null;
            String  СамЗапрос;
            String[] УсловияВыборки;
            protected Cursor МетодSwipesКурсор() {
                try{
                    if (DigitalNameCFO>0 && МЕсяцТабелей>0  && ГодТабелей>0) {
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
                        cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleГлавныйКурсорMultiДанныеSwipes);
                    }else {
                        // TODO: 13.04.2023 делаем смещение по курсору
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "DigitalNameCFO " +DigitalNameCFO );
                    }
                    // TODO: 13.04.2023 делаем смещение по курсору
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursorForViewPager " +cursor );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  cursor;
            }

        }

        void методReeoBootCursorRecyreViewAlfterChangeProffesion() {
            try{
                if (myRecycleViewAdapter.cursor!=null) {
                    Cursor cursorПослеОбновлениеПрофесии=myRecycleViewAdapter.cursor;
                    Integer ПозицияКурсора= myRecycleViewAdapter.cursor.getPosition();
                    cursorПослеОбновлениеПрофесии  =   singleTabelRecycreView.  new SubClassGetCursor().МетодSwipesКурсор();
                    cursorПослеОбновлениеПрофесии.moveToPosition(ПозицияКурсора);
                    myRecycleViewAdapter.cursor=cursorПослеОбновлениеПрофесии;
                }
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



    }//TODO КОНЕЦ КЛАССА визуального оформление Recycreview


    // TODO: 20.11.2023  кллас зааполенени данными двух снименров часы и Мксяц
    class AddDataSpinersHasyAndMotch{
        protected void metodAddDataSpinersHasyAndMotch(@NotNull MaterialTextView materialTextView
                , @NotNull String string) {
            try{
                materialTextView.setText(string.trim());
                materialTextView.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                materialTextView.setTextColor(Color.BLACK);
                materialTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                materialTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                materialTextView.requestLayout();
                materialTextView.refreshDrawableState();
                materialTextView.setClickable(false);
                materialTextView.setFocusable(false);
                Log.d(this.getClass().getName(), " spinnerchasy  " +spinnerchasy);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }


    //TODO форматирование ФИО и или Проыесии
    class SetFioAndProfessionDisayn{
        Integer ДлинаПодчерникания=0;

        public SetFioAndProfessionDisayn(Integer длинаПодчерникания) {
            ДлинаПодчерникания = длинаПодчерникания;
        }

        protected void методПерегрузкаВидаПрофесии(@NonNull String Профессия,@NotNull MaterialTextView materialTextView) {
            try {

            SpannableString ss=new SpannableString(Профессия);
            ss.setSpan(new UnderlineSpan(), ДлинаПодчерникания, Профессия.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            materialTextView.setText(ss);

                materialTextView.startAnimation(animation1);
                materialTextView.refreshDrawableState();
                materialTextView.requestLayout();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  Профессия " +Профессия +  " ФИО " +ФИО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


    }

//TODO класс получение Каленалря ДНЕЙ и ПРАЗНИКОВ
    class GetDayFromKalendary{
    // TODO: 03.04.2023 Создание  Дней Недели Вс, Пон, Ср,Черт
    private  LinkedHashMap< String,String> методВыходныеДниИзКалендарь() throws ParseException,RuntimeException {
        LinkedHashMap< String,String> linkedHashMapВыходные=new LinkedHashMap<>();
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


                    // TODO: 26.06.2023  ТОЛЬКО  ВЫХОДНЫЕ
                    Integer ИндексДнейФинал = (Integer) ИндексДней;
                    ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                    linkedHashMapВыходные.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " linkedHashMapВыходные " + linkedHashMapВыходные);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  linkedHashMapВыходные;
    }

    // TODO: 03.04.2023 Создание  ПРАЗНИЧНЫЕ ДНИ
    private  LinkedHashMap< String,String> методПразничныеДниИзКалендаря() throws ParseException,RuntimeException {
        LinkedHashMap< String,String> linkedHashMapПраздничныеДни =new LinkedHashMap<>();
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

                    Integer integerСубота=   СокращенныйДниМесяцаВТабеле.trim().indexOf("Сб");
                    Integer integerВоск=   СокращенныйДниМесяцаВТабеле.trim().indexOf("Вс");

                    if (integerСубота==0  || integerВоск==0){
                        linkedHashMapПраздничныеДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " integerСубота " + integerСубота
                                + " integerВоск " + integerВоск);
                    }else {
                        if(МЕсяцТабелей ==5 || МЕсяцТабелей==6|| МЕсяцТабелей ==11   ){
                            if (МЕсяцТабелей ==5 ) {
                                if(ИндексДней==1 || ИндексДней==9    ){
                                    linkedHashMapПраздничныеДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                }
                            }
                            if (МЕсяцТабелей==6) {
                                if(  ИндексДней==12   ){
                                    linkedHashMapПраздничныеДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                }
                            }
                            if (МЕсяцТабелей ==11) {
                                if(  ИндексДней==4 ){
                                    linkedHashMapПраздничныеДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                                }
                            }
                        }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " linkedHashMapПраздничныеДни " + linkedHashMapПраздничныеДни
                                + " МЕсяцТабелей " + МЕсяцТабелей);
                    }

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " linkedHashMapПраздничныеДни " + linkedHashMapПраздничныеДни);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  linkedHashMapПраздничныеДни;
    }
    /**
     *
     */
    private  int МетодПолучениеСколькоДнейВКонкретномМесяце(int Год,int Месяц) {
        int КоличествоДнейНаВыбраныйМесяц=0;
        try{
            Date date = null;
            int КонктетныйМесяцВВидеЦифры;
            Calendar cal;
            cal = Calendar.getInstance();
            System.out.println(cal.get(Calendar.MONTH));
            // Create a calendar object and set year and month
            Calendar mycal = new GregorianCalendar(Год, Месяц, 0);
            // Get the number of days in that month
            КоличествоДнейНаВыбраныйМесяц = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  КоличествоДнейНаВыбраныйМесяц;
    }

    // TODO: 20.11.2023 END KLASS get DAY
}

// TODO: 30.11.2023  класс SWIPE


}