package com.dsy.dsu.CodeOrdersAnTransports.Window;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentNewOrderTransport extends Fragment {
    private Integer ПубличныйID;
    LinearLayout    linear_main_ordertransport;
    private BottomNavigationView BottomNavigationOrderTransport;
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    private BottomNavigationItemView bottomNavigationItemView2BackListOrderTransport;
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСканирование;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentBackListOrderTransport;
    private  TextView TextViewHadler;
    long start;
    long startДляОбноразвовной;
    private  Message message;

    private GridView gridViewNewOrderTransport;

    private     SubClassNewOrderTransport subClassNewOrderTransport;
    private  Animation animationvibr1;

    private  ServiceOrserTransportService.  LocalBinderOrderTransport localBinderNewOrderTransport;
    private HorizontalScrollView horizontalScrollViewOrderTransport;
    private    MaterialTextView       textViewHadler;
    private  Cursor cursorCfo;

    private  Cursor cursorTypeTS;

    private  Cursor cursorGosNomer;

    private SubClassSetAllSprabochnik subClassSetAllSprabochnik;

    private AlertDialog alertDialogNewOrderTranport;

    private SearchViewForNewOT searchViewForNewOT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта
            subClassNewOrderTransport    =new SubClassNewOrderTransport(getActivity());
            // TODO: 15.05.2023  Данные Новые Справочники
            subClassSetAllSprabochnik =new SubClassSetAllSprabochnik();
            // TODO: 15.05.2023  Сам Новый SearchView
            searchViewForNewOT =new SearchViewForNewOT();
            // TODO: 04.05.2023
            ПубличныйID = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());

            localBinderNewOrderTransport =  (ServiceOrserTransportService.  LocalBinderOrderTransport) getArguments().getBinder("binder");

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                     "  localBinderNewOrderTransport " +localBinderNewOrderTransport  + " ПубличныйID " +ПубличныйID);
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
            view= inflater.inflate(R.layout.fragment_ordertransport2, container, false);
          // view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
           /// view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
            linear_main_ordertransport=(LinearLayout)  container.findViewById(R.id.linear_main_ordertransport);
            // TODO: 01.05.2023  Кнопки
            BottomNavigationOrderTransport=  (BottomNavigationView) container. findViewById(R.id.BottomNavigationOrderTransport);
            bottomNavigationItemViewвыход = BottomNavigationOrderTransport.findViewById(R.id.id_lback);
            bottomNavigationItemView2BackListOrderTransport = BottomNavigationOrderTransport.findViewById(R.id.id_create);
            bottomNavigationItemView3обновить = BottomNavigationOrderTransport.findViewById(R.id.id_async);
            bottomNavigationItemView2BackListOrderTransport.setVisibility(View.GONE);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            BottomNavigationOrderTransport.refreshDrawableState();
            BottomNavigationOrderTransport.requestLayout();
            // TODO: 12.05.2023 progrssbar
            progressBarСканирование=  (ProgressBar)  container. findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.INVISIBLE);
            // TODO: 01.05.2023
            gridViewNewOrderTransport =  (GridView) container.findViewById(R.id.gridViewOrderTransport);
            TextViewHadler = (TextView) container.findViewById(R.id.TextViewHadler);
            animationvibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);//

            horizontalScrollViewOrderTransport= (HorizontalScrollView) container. findViewById(R.id.horizontalScrollViewOrderTransport);

            textViewHadler=(MaterialTextView)  container.findViewById(R.id.TextViewHadler);
            textViewHadler.setHint("Заказ транспорта");

            // TODO: 14.05.2023  методы получение ДАнных ЦФО
            cursorCfo=     subClassNewOrderTransport .методGetAll("cfo","name");
            // TODO: 14.05.2023  методы получение ДАнных Виды ТС
            cursorTypeTS=     subClassNewOrderTransport .методGetAll("vid_tc","name");
            // TODO: 14.05.2023  методы получение ДАнных ЦФО
            cursorGosNomer=   subClassNewOrderTransport .методGetAll("track","fullname");
            // TODO: 11.05.2023 горизонтальеный Сколлл
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " view " +view + " container " +container) ;
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
            subClassNewOrderTransport.   МетодHandlerCallBack();
            subClassNewOrderTransport.   МетодВыходНаAppBack();
            // TODO: 04.05.2023 Анимация
            subClassNewOrderTransport.методАнимацииGridView();
            subClassNewOrderTransport.  методScroollAttach();

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " gridViewNewOrderTransport " +gridViewNewOrderTransport);
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
            if( cursorCfo ==null) {
                subClassNewOrderTransport.методAdapterNewOrderTransportGridView(R.layout.list_item_progressing_newordertransport,
                        "Справочники...", R.drawable.icon_dsu1_ordertransport_down);
            }else {
                if( cursorCfo.getCount()>0) {
                    subClassNewOrderTransport.методAdapterNewOrderTransportGridView(R.layout.fragment_ordertransport2,
                            "Создаем заказ !!!", R.drawable.icon_rdertransport2);

                }else{
                    subClassNewOrderTransport.методAdapterNewOrderTransportGridView( R.layout.list_item_isnull_newordertransport,
                            "Нет справочников !!!", R.drawable.icon_rdertransport2);
                }
                // TODO: 04.05.2023 Получаем Данные что обработка данных закончена
                subClassNewOrderTransport.    МетодДизайнПрограссБара();
            }
            // TODO: 19.04.2023 слушаелти
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " cursorCfo " + cursorCfo);
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
    class   SubClassNewOrderTransport{
 private Activity activity;

        public SubClassNewOrderTransport(Activity activity) {
            this.activity = activity;
        }

        // TODO: 28.04.2023
        private void методАнимацииGridView() {
            try{
                gridViewNewOrderTransport.startAnimation(animationvibr1);
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorCfo " + cursorCfo);
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
                            МетодЗапускаАнимацииКнопок(v);
                            message.getTarget().postDelayed(()->{
                                // TODO: 14.05.2023
                                методBackOrdersTransport();
                            },300);
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorCfo " + cursorCfo);
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


// TODO: 28.04.2023
        private void МетодЗапускаАнимацииКнопок(View v) {
            v.animate().rotationX(-40l);
            message.getTarget() .postDelayed(()->{
                v.animate().rotationX(0);
            },200);
        }

        // TODO: 10.03.2022 БИЗНЕС-КОД для ФРАГМЕНТА ПОСТУПЛЕНИЯ МАТЕРИАЛА

        void методСлушательGridView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                gridViewNewOrderTransport.getAdapter().registerDataSetObserver(new DataSetObserver() {
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


        protected void методBackOrdersTransport() {
            try{
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentBackListOrderTransport = new FragmentOrderTransportOneChane();
                Bundle bundleNewOrderTransport=new Bundle();
                bundleNewOrderTransport.putBinder("binder", (ServiceOrserTransportService.  LocalBinderOrderTransport) localBinderNewOrderTransport);
                bundleNewOrderTransport.putInt("isalive",2);
                fragmentBackListOrderTransport.setArguments(bundleNewOrderTransport);
                fragmentTransaction.replace(R.id.linear_main_ordertransport, fragmentBackListOrderTransport).setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragmentBackListOrderTransport);
                linear_main_ordertransport.refreshDrawableState();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " localBinderNewOrderTransport " +localBinderNewOrderTransport);
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

        // TODO: 28.04.2023


        // TODO: 02.08.2022
        protected   Cursor методGetNewOTCursorметодGetCursor(@NonNull   HashMap<String,String> datasendMap,@NonNull String СтатусNewOrder ){
            Cursor cursor=null;
            try{
                Map<String,Object> mapRetry ;
                switch (СтатусNewOrder){
                    case "neworder":
                        // TODO: 03.05.2023 тест код
                        mapRetry=       localBinderNewOrderTransport.методГлавныйNeworderTranportTraffic(datasendMap);
                        // TODO: 04.05.2023 результат
                        cursor =(Cursor) mapRetry.get("replyget1" );
                        break;
                    case "like":
                          mapRetry=       localBinderNewOrderTransport.методГлавныйLikeNeworderTranportTraffic(datasendMap);
                        // TODO: 04.05.2023 результат
                        cursor =(Cursor) mapRetry.get("replyget1" );
                        break;
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorCfo " + cursorCfo + " ПубличныйID  "+ПубличныйID + " ФлагОперации " +
                        " "+  "СтатусNewOrder " +СтатусNewOrder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }

        // TODO: 02.08.2022
        protected   Cursor методGetCursor(@NonNull   HashMap<String,String> datasendMap ){
            Cursor cursor=null;
            try{
                // TODO: 03.05.2023 тест код
                Map<String,Object>  mapRetry=       localBinderNewOrderTransport.методГлавныйTraffic(datasendMap);
                // TODO: 04.05.2023 результат
                cursor =(Cursor) mapRetry.get("replyget1" );

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " cursorCfo " + cursorCfo + " ПубличныйID  "+ПубличныйID + " ФлагОперации " +
                        " mapRetry " +mapRetry+ " ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }

        private void МетодДизайнПрограссБара() {
            progressBarСканирование.postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
                progressBarСканирование.setIndeterminate(true);
            },250);
        }
        // TODO: 05.05.2023
        Bundle методДанныеCFOBungle(@NonNull Cursor cursor) {
            Bundle bundleCFO=new Bundle();
            try {
                Integer IdCfo= cursor.getInt(cursor.getColumnIndex("_id"));
                String      NameCFO= cursor.getString(cursor.getColumnIndex("name")).trim();
                Long      UuidCFO= cursor.getLong(cursor.getColumnIndex("uuid"));
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleCFO.putInt("IdCfo", IdCfo);
                bundleCFO.putInt("Position", cursor.getPosition());
                bundleCFO.putString("NameCFO",NameCFO );
                bundleCFO.putLong("UuidCFO",UuidCFO );

                // TODO: 12.05.2023 ДАННЫЕ
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  cursor " + cursor  +  "bundleCFO " +bundleCFO);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  bundleCFO;
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



        // TODO: 28.04.2023
        void методAdapterNewOrderTransportGridView(@NonNull Integer Макет ,
                                                   @NonNull String Сообщение,
                                                   @NonNull Integer Значек){///      R.layout.list_item_progressing_ordertransport
            try{
                    ArrayList<HashMap<String, Object>> ЛистНетданных= new ArrayList<HashMap<String, Object>> ();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("alldonttbels", Сообщение);
                    map.put("allimage", " dont");
                    ЛистНетданных.add(map);
                    SimpleAdapter simpleAdapterAllElement = new SimpleAdapter(getContext(),
                            ЛистНетданных,Макет,
                            new String[]{"alldonttbels","allimage"},
                            new int[]{android.R.id.text2,android.R.id.text1});

                    SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            try{
                                MaterialCardView родительскийCardView= (MaterialCardView)
                                        view.findViewById(android.R.id.text1);
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        // TODO: 15.05.2023  CFO
                                    subClassSetAllSprabochnik.    методСправочникЦФО(родительскийCardView);
                                        // TODO: 15.05.2023  Вид ТС
                                        subClassSetAllSprabochnik.     методСправочникВидТС(родительскийCardView);
                                        // TODO: 15.05.2023  ГОС Номер
                                        subClassSetAllSprabochnik.    методСправочникГосНомер(родительскийCardView);
                                        // TODO: 15.05.2023  Дата Будущего Времени
                                        subClassSetAllSprabochnik.      методСправочникДатаБудущая(родительскийCardView);
                                        // TODO: 15.05.2023  Кнопка Создания
                                        subClassSetAllSprabochnik.      методСправочникКнопкаСоздания(родительскийCardView);

                                        // TODO: 18.04.2023  Simple Adapter Кдик по Элементы
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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
                simpleAdapterAllElement.setViewBinder(БиндингКогдаНетДАнных);
                simpleAdapterAllElement.notifyDataSetChanged();
                gridViewNewOrderTransport.setAdapter(simpleAdapterAllElement);
                gridViewNewOrderTransport.refreshDrawableState();
                gridViewNewOrderTransport.requestLayout();
                // TODO: 14.05.2023 smmo
              //  gridViewNewOrderTransport.setSelection(0);
                gridViewNewOrderTransport.smoothScrollByOffset(0);
                    // TODO: 19.04.2023 слушаелти
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  cursorCfo  " + cursorCfo);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }




        private void методScroollAttach() {
            try {
                horizontalScrollViewOrderTransport.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        long end = Calendar.getInstance().getTimeInMillis();
                        long РазницаВоврмени=end-startДляОбноразвовной;
                        if (РазницаВоврмени>2000) {
                           Fragment fragmentisNeworderisAlive=       fragmentManager.getFragments().get(0);
                     Bundle bundle=(Bundle)       fragmentisNeworderisAlive.getArguments();
                         Integer ИндексТекущийФрагмент=   bundle.getInt("isalive")   ;
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date()+"\n+" +
                                        " Класс в процессе... " +  getContext().getClass().getName()+"\n"+
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " scrollX " +scrollX + " oldScrollX " +oldScrollX);

                            if(scrollX>2 || oldScrollX>2){
                                if (ИндексТекущийФрагмент==1) {
                                    методBackOrdersTransport();
                                }
                            }

                        }
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " scrollX " +scrollX + " oldScrollX " +oldScrollX);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 15.05.2023
        private Cursor методGetAll(@NonNull String ТекущеаяТаблица,@NonNull String ТекущейСтолбик) {
            Cursor cursor = null;
            try{
                // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                HashMap<String,String> datasendMap=new HashMap();
                datasendMap.putIfAbsent("1","  SELECT  *  FROM   "+ТекущеаяТаблица+"");
                datasendMap.putIfAbsent("2"," WHERE "+ТекущейСтолбик+"  IS NOT NULL  AND _id >?  ORDER BY _id ");
                datasendMap.putIfAbsent("3"," 0 ");
                datasendMap.putIfAbsent("4",""+ТекущеаяТаблица+"");
                // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                cursor =         subClassNewOrderTransport.     методGetNewOTCursorметодGetCursor( datasendMap,"neworder");
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                        "  cursorCfo " +cursorCfo);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }

        private Cursor методGetAllLike(@NonNull String ТекущеаяТаблица,@NonNull String ТекущейСтолбик,@NonNull String ValueForLike) {
            Cursor cursor = null;
            try{
                // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                HashMap<String,String> datasendMap=new HashMap();
                datasendMap.putIfAbsent("Таблица",ТекущеаяТаблица);
                datasendMap.putIfAbsent("ТекущийLike",ValueForLike);
                datasendMap.putIfAbsent("ТекущейСтолбик",ТекущейСтолбик);
                // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                cursor =         subClassNewOrderTransport.     методGetNewOTCursorметодGetCursor( datasendMap,"like");
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                        "  cursorCfo " +cursorCfo);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }



// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport           //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport   //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport


    }
    // TODO: 14.05.2023 НАЧАЛО КЛАСС ПОСИКА SEARCHVIEW
    class SubClassSetAllSprabochnik {
        public SubClassSetAllSprabochnik() {
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  +
                    "  cursorCfo " +cursorCfo);
        }
        private void методСправочникЦФО(@NonNull MaterialCardView родительскийCardView) {
            try{
                MaterialTextView materialText
                        = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuecfo);//ВИД CFO
                методЗаполенияСправочника(   materialText,cursorCfo,"выберете цфо");
                // TODO: 12.05.2023
                materialText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialButton materialButtonExitOrSave
                                = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            searchViewForNewOT.методПоискаНовыйЗаказТранспорта( (MaterialTextView) v,cursorCfo,"name","cfo","цфо",materialButtonExitOrSave);
                        },200);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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
        private void методСправочникВидТС(@NonNull  MaterialCardView родительскийCardView) {
            try{
                MaterialTextView materialText
                        = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuetypetc);//ВИД CFO
                методЗаполенияСправочника(   materialText,cursorTypeTS,"выберете вид тс");
                // TODO: 12.05.2023
                materialText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialButton materialButtonExitOrSave
                                = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            searchViewForNewOT.методПоискаНовыйЗаказТранспорта( (MaterialTextView) v,cursorTypeTS,"name","vid_tc","вид тс",materialButtonExitOrSave);
                        },200);

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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
        private void методСправочникГосНомер(@NonNull  MaterialCardView родительскийCardView) {
            try{
                MaterialTextView materialText
                        = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuegosnomer);//ВИД CFO
                методЗаполенияСправочника(   materialText,cursorGosNomer,"выберете гос.номер");
                // TODO: 12.05.2023
                materialText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialButton materialButtonExitOrSave
                                = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            searchViewForNewOT.методПоискаНовыйЗаказТранспорта( (MaterialTextView) v,cursorGosNomer,"fullname","track","гос.номер",materialButtonExitOrSave);
                        },200);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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

        private void методСправочникДатаБудущая(@NonNull  MaterialCardView родительскийCardView) {
            try{
                MaterialTextView materialText
                        = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuedate);//ВИД CFO
                методЗаполенияСправочникДата(   materialText,"выберете дату");
                // TODO: 12.05.2023
                materialText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 16.05.2023 создание Даты
                            СlassGetDateForNewOrderTranport classGetDateForNewOrderTranport=new СlassGetDateForNewOrderTranport();
                            classGetDateForNewOrderTranport.методGetDateForNewOrder(getActivity(),(MaterialTextView ) v);
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        },200);

                    }
                });
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методСправочникКнопкаСоздания(@NonNull  MaterialCardView родительскийCardView) {
            try{
                MaterialButton materialButton
                        = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);//ВИД CFO
                // TODO: 12.05.2023
                materialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 15.05.2023 закрывает или сохраняем
                        v.animate().rotationX(+40l);
                        message.getTarget() .postDelayed(()->{
                            v.animate().rotationX(0);
                            // TODO: 16.05.2023  CLICK SAVE or EXIT
                        MaterialTextView materialTextcfo
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuecfo);//ВИД CFO
                        MaterialTextView materialTexttypetc
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuetypetc);//ВИД type tc
                        MaterialTextView materialTextgosnumber
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuegosnomer);//ВИД gos numer
                        MaterialTextView materialTextdate
                                = (MaterialTextView)   родительскийCardView.findViewById(R.id.valuedate);//ВИД Dates
                        // TODO: 15.05.2023
                        if(materialTextcfo.getText().length()>0
                        &&  materialTexttypetc.getText().length()>0
                        &&  materialTextgosnumber.getText().length()>0
                        &&  materialTextdate.getText().length()>0) {
                            // TODO: 15.05.2023  Сохранчем Выбраные Данные
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        }else{
                            // TODO: 15.05.2023
                            Snackbar.make(v, "Вы не заполнили заказ !!! ",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                           /// subClassNewOrderTransport.   методBackOrdersTransport();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                        }
                            // TODO: 15.05.2023
                        },150);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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



        private void методЗаполенияСправочника(@NonNull MaterialTextView values,@NonNull Cursor cursor,@NonNull String ФлагНаличияСпра) {
            try{
                values.startAnimation(animationvibr1);
                if (cursor==null || cursor.getCount()==0) {
                    values.setHint( "нет справочника");
                }else {
                    Bundle bundle=new Bundle();
                    bundle.putInt("cursor",cursor.getCount());
                    values.setTag(bundle);
                    values.setHint(ФлагНаличияСпра);
                }
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        "cursor " +cursor);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методЗаполенияСправочникДата(@NonNull MaterialTextView values, @NonNull String ФлагНаличияСпра) {
            try{
                Bundle bundle=new Bundle();
                values.setTag(bundle);
                values.setHint(ФлагНаличияСпра);
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
        private void МетодЗапускаАнимацииAllvalues(View v) {
            v.animate().rotationX(+40l);
            message.getTarget() .postDelayed(()->{
                v.animate().rotationX(0);
            },200);
        }
}//TODO END SubClassGETVALUE


    //TODO SubClass SearchView
    class SearchViewForNewOT {
        private     Animation   animation1;
        private     MaterialButton materialButtonFilterЗакрытьДиалог;
        private    ListView ListViewForNewOrderTransport;
        private  String Столбик;
        private String ТаблицаТекущая;
        private SimpleCursorAdapter simpleCursorSeachViewNewOrderTranport;
        private   SearchView searchViewДляNewOrderTransport ;
        private  Cursor cursor;
        private  String Спровочник;
        void методПоискаНовыйЗаказТранспорта(@NonNull MaterialTextView materialTextViewТекущийСправочник,
                                             @NonNull Cursor cursor,
                                             @NonNull String Столбик,
                                             @NonNull String ТаблицаТекущая,
                                             @NonNull String Спровочник,
                                             @NonNull MaterialButton materialButtonExitOrSave){
            this.cursor=cursor;
            this.Столбик=Столбик;
            this.ТаблицаТекущая=ТаблицаТекущая;
            this.Спровочник=Спровочник;
            alertDialogNewOrderTranport = new MaterialAlertDialogBuilder(materialTextViewТекущийСправочник.getContext()){
                @NonNull
                @Override
                public MaterialAlertDialogBuilder setView(View view) {
                    try{
                        animation1= AnimationUtils.loadAnimation(materialTextViewТекущийСправочник.getContext(),R.anim.slide_in_row_newscanner1);
                        materialButtonFilterЗакрытьДиалог =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                        ListViewForNewOrderTransport =    (ListView) view.findViewById(R.id.ListViewForNewOrderTransport);
                        searchViewДляNewOrderTransport =    (SearchView) view.findViewById(R.id.searchview_newordertransport);
                        ListViewForNewOrderTransport.setTextFilterEnabled(true);
                        searchViewДляNewOrderTransport.setDrawingCacheBackgroundColor(Color.GRAY);
                        searchViewДляNewOrderTransport.setDrawingCacheEnabled(true);
                        int id = searchViewДляNewOrderTransport.getContext()
                                .getResources()
                                .getIdentifier("android:id/search_src_text", null, null);
                        TextView textView = (TextView) searchViewДляNewOrderTransport.findViewById(id);
                        textView.setTextColor(Color.rgb(0,102,102));
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


                        ///TODO ГЛАВНЫЙ АДАПТЕР чата
                        simpleCursorSeachViewNewOrderTranport=
                                new SimpleCursorAdapter(materialTextViewТекущийСправочник.getContext(),
                                        R.layout.simple_newspinner_dwonload_newfiltersearch, cursor, new String[]{ Столбик,"_id"},
                                        new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                        SimpleCursorAdapter.ViewBinder БиндингДляПоиск = new SimpleCursorAdapter.ViewBinder(){

                            @Override
                            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        Log.d(this.getClass().getName()," position");
                                        if (cursor.getCount()>0) {
                                            try{
                                                // TODO: 13.12.2022  производим состыковку
                                                Integer   getId = cursor.getInt(cursor.getColumnIndex("_id"));
                                                if (getId>0) {
                                                    Long UUIDGetFilter = cursor.getLong(cursor.getColumnIndex("uuid"));
                                                    String  getName = cursor.getString(cursor.getColumnIndex(Столбик)).trim();
                                                    Bundle bundle=new Bundle();
                                                    bundle.putInt("getId",getId);
                                                    bundle.putString("getName",getName);
                                                    bundle.putLong("getUUID",UUIDGetFilter);
                                                    // TODO: 16.05.2023 Элемент Заполяем данными  TAG
                                                    ((MaterialTextView)view).setTag(bundle);
                                                // TODO: 20.01.2022
                                                Log.d(this.getClass().getName()," getName "+getName + " getId " +getId  + " UUIDGetFilter " +UUIDGetFilter);
                                                boolean ДлинаСтрокивСпиноре = getName.length() >40;
                                                if (ДлинаСтрокивСпиноре==true) {
                                                    StringBuffer sb = new StringBuffer(getName);
                                                    sb.insert(40, System.lineSeparator());
                                                    getName = sb.toString();
                                                    Log.d(materialTextViewТекущийСправочник.getContext().getClass().getName(), " getName " + "--" + getName);/////
                                                }
                                                // TODO: 16.05.2023 Элемент Заполяем данными
                                                ((MaterialTextView)view).setText(getName);
                                                }
                                                Log.d(getContext().getClass().getName(), "\n"
                                                        + " время: " + new Date() + "\n+" +
                                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        "  ((MaterialTextView)view) " +  ((MaterialTextView)view));
                                                // TODO: 13.12.2022 филь
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new   Class_Generation_Errors(materialTextViewТекущийСправочник.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                        simpleCursorSeachViewNewOrderTranport.setViewBinder(БиндингДляПоиск);
                        simpleCursorSeachViewNewOrderTranport.notifyDataSetChanged();
                        ListViewForNewOrderTransport.setAdapter(simpleCursorSeachViewNewOrderTranport);
                        ListViewForNewOrderTransport.startAnimation(animationvibr1);
                        ListViewForNewOrderTransport.setSelection(0);
                        ListViewForNewOrderTransport.requestLayout();
                        ListViewForNewOrderTransport.refreshDrawableState();

                        // TODO: 13.12.2022  Поиск и его слушель
                        МетодПоискаФильтр(  ТаблицаТекущая);

                        // TODO: 15.05.2023 Слушатель Действия Кнопки Сохранить

                        методКликДейсвиеКнопкиСохранить(materialTextViewТекущийСправочник);

                        // TODO: 16.05.2023  КЛИК СЛУШАТЕЛЬ ПО ЕЛЕМЕНТУ
                        методКликПоЗаказуOrder();

                        Log.d(materialTextViewТекущийСправочник.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +
                                materialTextViewТекущийСправочник.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(materialTextViewТекущийСправочник.getContext())
                                .МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                    return super.setView(view);
                    // TODO: 20.12.2022  тут конец выбеленого
                }


                // TODO: 16.05.2023  КЛИК ПО ЕЛЕМЕНТУ
                private void методКликПоЗаказуOrder() {
                    try{
                    ListViewForNewOrderTransport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try{
                            MaterialTextView materialTextViewGetElement=(MaterialTextView)       parent.getAdapter().getView(position, view,parent );
                            if(materialTextViewGetElement!=null){
                                Bundle bundlePepoles= (Bundle) materialTextViewGetElement.getTag();
                                materialTextViewGetElement.startAnimation(animationvibr1);
                                // TODO: 16.05.2023 Из Выбраного Элемента Получаеним ДАнные
                                Integer getId=      bundlePepoles.getInt("getId",0);
                                String getName=   bundlePepoles.getString("getName","").trim();
                                Long getUUID =   bundlePepoles.getLong("getUUID",0l);
                                materialTextViewТекущийСправочник.setTag(bundlePepoles);
                                materialTextViewТекущийСправочник.setText(getName);
                                    // TODO: 15.05.2023 ЗАПОЛЕНИЕ ДАННЫМИ КЛИК
                                message.getTarget().postDelayed(()->{
                                    if (    materialTextViewТекущийСправочник.getText().toString().length()>0) {
                                        materialTextViewТекущийСправочник.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                        materialTextViewТекущийСправочник.setTextColor(Color.BLACK);
                                        materialButtonExitOrSave.setText("Сохранить");
                                        // TODO: 15.05.2023  ЗАКРЫВАЕТ
                                        // TODO: 15.05.2023 Закрываем
                                        alertDialogNewOrderTranport.cancel();
                                        alertDialogNewOrderTranport.dismiss();
                                    } else {
                                        materialTextViewТекущийСправочник.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                        materialTextViewТекущийСправочник.setTextColor(Color.GRAY);
                                        materialButtonExitOrSave.setText("Закрыть");
                                    }
                                    materialTextViewТекущийСправочник.refreshDrawableState();
                                    materialTextViewТекущийСправочник.requestLayout();
                                    materialButtonExitOrSave.refreshDrawableState();
                                    materialButtonExitOrSave.requestLayout();
                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            "materialTextViewТекущийСправочник " +materialTextViewТекущийСправочник);
                                },150);

                            }
                            Log.d(materialTextViewТекущийСправочник.getContext().getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +
                                    materialTextViewТекущийСправочник.getContext().getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(materialTextViewТекущийСправочник.getContext())
                                    .МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(materialTextViewТекущийСправочник.getContext())
                            .МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }


            }
                    .setTitle(Спровочник)
                    .setCancelable(false)
                    .setIcon( R.drawable.icon_newscannertwo)
                    .setView(getLayoutInflater().inflate( R.layout.simple_for_new_spinner_searchview_newordertransport2, null )).show();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(    alertDialogNewOrderTranport.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialogNewOrderTranport.getWindow().setAttributes(layoutParams);
            // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
        }

        private void МетодПоискаФильтр(@NonNull String ТаблицаДляФильтра) {
            try{

                searchViewДляNewOrderTransport.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                            Log.d(this.getClass().getName()," position");
                            if (hasFocus==true) {
                                ((SearchView) v).setQueryHint("Поиск "+Спровочник);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(v.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

                searchViewДляNewOrderTransport.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                            Log.d(this.getClass().getName()," position");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors( getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                            Log.d(this.getClass().getName()," position");
                                if (newText.length() > 0) {
                                    FilterQueryProvider filter = simpleCursorSeachViewNewOrderTranport.getFilterQueryProvider();
                                    filter.runQuery(newText);
                                    return true;
                                }else {
                                    // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                                    методПерезаполенияПоискаИзФилтра(cursor);
                                    // TODO: 16.05.2023
                                    materialButtonFilterЗакрытьДиалог.setText("Закрыть");
                                    materialButtonFilterЗакрытьДиалог.requestLayout();
                                    Log.d(getContext() .getClass().getName(), "\n"
                                            + " время: " + new Date()+"\n+" +
                                            " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursor "+ cursor);
                                    return true;
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext() ).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                });
                simpleCursorSeachViewNewOrderTranport.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        final Cursor[] cursorFilter = {null};
                        try{
                            message.getTarget().post(()->{
                               cursorFilter[0] =    simpleCursorSeachViewNewOrderTranport.getCursor();
                                cursorFilter[0] =          subClassNewOrderTransport
                                    .методGetAllLike(ТаблицаТекущая,Столбик,constraint.toString());

                                // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                                методПерезаполенияПоискаИзФилтра(cursorFilter[0]);
                                // TODO: 16.05.2023
                                if(cursorFilter[0] !=null && cursorFilter[0].getCount()>0) {
                                    materialButtonFilterЗакрытьДиалог.setText("Cохранить");
                                    materialButtonFilterЗакрытьДиалог.requestLayout();
                                }
                                Log.d(getContext() .getClass().getName(), "\n"
                                        + " время: " + new Date()+"\n+" +
                                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter[0]);

                            });
                            Log.d(getContext() .getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors( getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return cursorFilter[0];
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors( getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
        private void методПерезаполенияПоискаИзФилтра(Cursor cursorFilter) {
            try {
            simpleCursorSeachViewNewOrderTranport.swapCursor(cursorFilter);
            // TODO: 16.05.2023 reboot disain
            simpleCursorSeachViewNewOrderTranport.notifyDataSetChanged();
            // TODO: 16.05.2023 Если данные Естьв Фильтре
            if(cursorFilter !=null && cursorFilter.getCount()>0) {
                ListViewForNewOrderTransport.setSelection(0);
                View filter=       ListViewForNewOrderTransport.getChildAt(0);
                if(filter!=null){
                    ((MaterialTextView)filter).startAnimation(animationvibr1);
                }
                materialButtonFilterЗакрытьДиалог.setText("Закрыть");
            }else{
                searchViewДляNewOrderTransport.setBackgroundColor(Color.RED);
                message.getTarget().postDelayed(() -> {
                    searchViewДляNewOrderTransport.setBackgroundColor(Color.parseColor("#F2F5F5"));
                }, 500);
            }
            // TODO: 16.05.2023
            ListViewForNewOrderTransport.refreshDrawableState();
            ListViewForNewOrderTransport.requestLayout();
                Log.d(getContext() .getClass().getName(), "\n"
                        + " время: " + new Date()+"\n+" +
                        " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " cursorFilter "+ cursorFilter);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors( getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

        private void методКликДейсвиеКнопкиСохранить(@NonNull MaterialTextView materialTextViewДанныеДляСохранения) {
            try{
                materialButtonFilterЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialogNewOrderTranport.dismiss();
                        alertDialogNewOrderTranport.cancel();

                        Log.d(materialButtonFilterЗакрытьДиалог.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  materialButtonFilterЗакрытьДиалог.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                +  " materialTextViewДанныеДляСохранения " +materialTextViewДанныеДляСохранения);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(materialButtonFilterЗакрытьДиалог.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 15.05.2023 КОНЕЦ НОВОГО ПОСИКА
    }
    // TODO: 16.05.2023 Класс Для Порлучение Даты Для Новго Заказа
    class  СlassGetDateForNewOrderTranport{
        void  методGetDateForNewOrder(@NonNull Activity activity,@NonNull      MaterialTextView materialTextViewКликДата){
            try{
                final String[] FullNameCFO = new String[1];
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                Calendar newDate = Calendar.getInstance();
                //TODODATA
                DatePickerDialog ДатаДляКалендаря =
                        new DatePickerDialog(activity, android.R.style.Theme_Holo_InputMethod , new DatePickerDialog.OnDateSetListener() {////Theme_Holo_Dialog_MinWidth  //Theme_Holo_Panel
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                newDate.set(year, monthOfYear, dayOfMonth);
                                try {
                                    String ДатаДляНовогоЗаказаТраспорта= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(newDate.getTime());
                                    materialTextViewКликДата.setTag(ДатаДляНовогоЗаказаТраспорта);
                                    materialTextViewКликДата.setText(ДатаДляНовогоЗаказаТраспорта);
                                    Log.d(getContext() .getClass().getName(), "\n"
                                            + " время: " + new Date()+"\n+" +
                                            " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " ДатаДляНовогоЗаказаТраспорта "+ ДатаДляНовогоЗаказаТраспорта);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }

                        }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH) +1);
                ДатаДляКалендаря.setTitle("Календарь");
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_POSITIVE, "Создать", ДатаДляКалендаря);
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Закрыть", ДатаДляКалендаря);
                ДатаДляКалендаря.show();
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
                ////
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

    }

    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}