package com.dsy.dsu.Code_ForTABEL.viewpagers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_ForTABEL.MainActivity_Tabel_Single_People;
import com.dsy.dsu.R;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabel extends Fragment {

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
    private Configuration config;
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
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;
    private View view2Линия;
    private    String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//
    final private   String ИмяСлужбыОбщейСинхронизацииДляЗадачи = "WorkManager Synchronizasiy_Data";
    private  long CurrenrsСhildUUID =0l;
    private  long CurrenrsSelectFio =0l;
    private  long MainParentUUID =0l;
    private  String ФИО;
    private Message message;
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
    private MainActivity_Tabel_Single_People.SubClassSingleTabelRecycreView. MyRecycleViewAdapter myRecycleViewAdapter;
    private MainActivity_Tabel_Single_People.SubClassSingleTabelRecycreView. MyViewHolder myViewHolder;

    private  Integer PositionCustomer =0;

    private  String FullNameCFO = "";
    private  Integer ГодТабелей = 0;
    private  String ИмесяцвИГодСразу = "";
    private  Integer МЕсяцТабелей=0;
    private  Bundle bundleИзMainActitivy_List_Tables;

    private TextView TextViewФИОПрофессия;
    private InputMethodManager imm;

    private  HorizontalScrollView horizontalScrollView_tabel_single;
    private MainActivity_Tabel_Single_People.SubClassSingleTabelRecycreView subClassSingleTabelRecycreView;

    private   String ИмяСлужбыСинхронизациОдноразовая="WorkManager Synchronizasiy_Data Disposable";
    private String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";
    private LifecycleOwner lifecycleOwner;
    private   LifecycleOwner  lifecycleOwnerОбщая;
    private    long startДляОбноразвовной;

    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    private  Busable busable;
    private ViewPager viewPager ;

    private Cursor cursorForViewPager;
    private  SubClassBisscessFragmentSingleTabel fragmentSingleTabel;
    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabel newInstance(@NonNull Bundle bundle_single_tabel_viewpagers ) {
        FragmentSingleTabel fragment = new FragmentSingleTabel();
        Bundle args = new Bundle();
        args=bundle_single_tabel_viewpagers.deepCopy();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        try {
        super.onAttach(context);
        busable = (Busable) context;
        viewPager=(ViewPager)  busable.viewPager();
        cursorForViewPager=(Cursor)  busable.getcorsor();
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
            fragmentSingleTabel=new SubClassBisscessFragmentSingleTabel();
            fragmentSingleTabel.new SubClassListerViewPager().методСлушательViewPager();
            fragmentSingleTabel.new SubClassBungleSingle().методGETДанныеИзДругихАктивити();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewSingletabek=null;
        try{
            viewSingletabek= inflater.inflate(R.layout.fragment_admission_materials, container, false);

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
            fragmentSingleTabel.new SubClassNewDataSingleTabel().методВнешнийВидФрагмента(view);
           // fragmentSingleTabel.new SubClassTestDataAnScreen().методТестовыйВЫгрузкаДанныхНаЭкран(view);

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
    class  SubClassBisscessFragmentSingleTabel{
        // TODO: 21.06.2023  перенесоный метод из Activity Single Tabel
        class SubClassNewDataSingleTabel{
            void методВнешнийВидФрагмента(@NonNull View view){
                try{
                СпинерИгодИМесяц = (Spinner) view. findViewById(R.id.СпинерТабельМесяц);
                СпинерНазваниеЦФО = (Spinner) view.findViewById(R.id.СпинерТабельДепратамент);
                ProgressBarSingleTabel = (ProgressBar) view.findViewById(R.id.ProgressBarSingleTabel);
                ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА
                КнопкаЛеваяПередвиженияПоДанным=(Button)view. findViewById(R.id.imageViewВСамомТабелеЛеваяСтрелка);
                КнопкаПраваяПередвиженияПоДанным=(Button)view. findViewById(R.id.imageViewВСамомТабелеТабельПраваяСтрелка);
                textViewчасыsimgletabel =(TextView)view. findViewById(R.id.textViewчасыsimgletabel);
                imageButtonДвижениеПоСотрудникамВТАбеле=(ImageButton)view. findViewById(R.id.imageButtonДвижениеПоСотрудникамВТАбеле);
                imageButtonНазадПоСотрудникамВТАбеле=(ImageButton) view.findViewById(R.id.imageButtonНазадПоСотрудникамВТАбеле);
                textViewчасыsimgletabel.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                textViewчасыsimgletabel.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                textViewчасыsimgletabel.setPaintFlags( (textViewчасыsimgletabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG));
                СловоТабель=(TextView)view. findViewById(R.id.textView3СловоТабель);
                СловоТабель.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                ЛимитСоСмещениемДанных="0";
                КнопкаНазад=(Button)view. findViewById(R.id.imageViewСтрелкаВнутриТабеля);
                view2Линия=(View)view. findViewById(R.id.view2Линия);
                ProgressBarSingleTabel.setVisibility(View.VISIBLE);
                TextViewФИОПрофессия = (TextView)  view.findViewById(R.id.TextViewФИОПрофессия);


                animationПрофессия400 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_rowsingletabel);
                animationПрофессия300 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row2);
                animationVibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable);
                animationVibr2 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);
                animationRows = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_scroll_for_singletabel);
                animationRich = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
                animationLesft = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1
                startДляОбноразвовной= Calendar.getInstance().getTimeInMillis();
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
        }//TODO END   class SubClassNewDataSingleTabel

      class SubClassBungleSingle{
          private void методGETДанныеИзДругихАктивити() {
              try {
                  bundleИзMainActitivy_List_Tables=(Bundle) getArguments();
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
                  new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                          Thread.currentThread().getStackTrace()[2].getMethodName(),
                          Thread.currentThread().getStackTrace()[2].getLineNumber());
              }
          }




      }












        // TODO: 21.06.2023  Класс Тестовый По выгрузке Данныз НА экран
        class  SubClassTestDataAnScreen{
            void  методТестовыйВЫгрузкаДанныхНаЭкран(@NonNull View view){
                try{
                    TextView textfragnetviewpager = (TextView)  view.findViewById(R.id.textfragnetviewpager);
                    Integer value =getArguments().getInt("value");
                    textfragnetviewpager.setText(value.toString());
                    // TODO: 20.06.2023
                    TextView textfragnetviewpager2 = (TextView)  view.findViewById(R.id.textfragnetviewpager2);
                   // Long uuid =getArguments().getLong("uuid");;
                    if (value<cursorForViewPager.getCount()) {
                        cursorForViewPager.moveToPosition(value);
                    }
                    Long uuid =cursorForViewPager.getLong(cursorForViewPager.getColumnIndex("uuid"));
                    textfragnetviewpager2.setText(uuid.toString());
                    // TODO: 20.06.2023
                    TextView textfragnetviewpager3 = (TextView)  view.findViewById(R.id.textfragnetviewpager3);
                    Integer getpositioncursor =getArguments().getInt(   "getpositioncursor");;
                    textfragnetviewpager3.setText(getpositioncursor.toString());
                    // TODO: 21.06.2023
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
        }
        
        
        

        // TODO: 21.06.2023 Класс Слушатель View Pager 
        class SubClassListerViewPager{
            private void методСлушательViewPager() {
                try{
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                        @Override
                        public void onPageSelected(int position) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    });
// TODO: 20.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            
            
            
        }//TODO class SubClassListerViewPager class SubClassListerViewPager
    }//TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel







}