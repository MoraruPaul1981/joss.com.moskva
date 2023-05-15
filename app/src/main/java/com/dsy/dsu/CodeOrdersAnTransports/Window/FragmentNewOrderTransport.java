package com.dsy.dsu.CodeOrdersAnTransports.Window;

import android.app.Activity;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    private AlertDialog alertDialog;

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

            // TODO: 14.05.2023  методы получение ДАнных
            subClassNewOrderTransport .  методGetCFO();
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
        protected   Cursor методGetCursor(@NonNull   HashMap<String,String> datasendMap ){
            try{
                // TODO: 03.05.2023 тест код
                Map<String,Object>  mapRetry=       localBinderNewOrderTransport.методГлавныйTraffic(datasendMap);
                // TODO: 04.05.2023 результат
                cursorCfo =(Cursor) mapRetry.get("replyget1" );

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
            return cursorCfo;
        }

        private void МетодДизайнПрограссБара() {
            progressBarСканирование.postDelayed(()->{
                progressBarСканирование.setVisibility(View.INVISIBLE);
                progressBarСканирование.setIndeterminate(true);
            },250);
        }

        // TODO: 28.04.2023
      void методSimpleCFOGridView(@NonNull Integer Макет){
            try{
/*                SimpleCursorAdapter     simpleCursorAdapterCFO=
                            new SimpleCursorAdapter(getContext(), Макет,
                                    cursorCfo, new String[]{"_id","name"},
                                    new int[]{android.R.id.text1,android.R.id.text2},
                                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  ///name
                    SimpleCursorAdapter.ViewBinder binding = new SimpleCursorAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                            try{
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        MaterialCardView linearLayoutЗаказыТранспорта= (MaterialCardView)
                                                view.findViewById(android.R.id.text1);
                                        // TODO: 12.05.202
                                        MaterialTextView materialTextViewvalues     = (MaterialTextView)   linearLayoutЗаказыТранспорта.findViewById(R.id.cfo);//ВИД CFO

                                        методЗаполенияЦФО(    materialTextViewvalues);
                                        // TODO: 12.05.2023
                                        materialTextViewvalues.startAnimation(animationvibr1);

                                        // TODO: 18.04.2023  Внешниц вид
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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
                   simpleCursorAdapterCFO.setViewBinder(binding);
                   simpleCursorAdapterCFO.notifyDataSetChanged();
                gridViewNewOrderTransport.setAdapter(simpleCursorAdapterCFO);
                gridViewNewOrderTransport.refreshDrawableState();
                gridViewNewOrderTransport.requestLayout();
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "  cursorCfo " + cursorCfo);*/

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
        private void методGetCFO() {
            try{
                // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                HashMap<String,String> datasendMap=new HashMap();
                datasendMap.putIfAbsent("1","  SELECT  *  FROM  cfo  ");
                datasendMap.putIfAbsent("2"," WHERE name  IS NOT NULL  AND _id >?  ORDER BY _id ");
                datasendMap.putIfAbsent("3"," 0 ");
                datasendMap.putIfAbsent("4"," cfo ");
                // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                cursorCfo =         subClassNewOrderTransport.     методGetCursor( datasendMap);
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
                        МетодЗапускаАнимацииAllvalues(v);
                        searchViewForNewOT.методПоискаНовыйЗаказТранспорта( (MaterialTextView) v,cursorCfo,"name","cfo","ЦФО");

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
                        МетодЗапускаАнимацииAllvalues(v);
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
                        МетодЗапускаАнимацииAllvalues(v);
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
                        МетодЗапускаАнимацииAllvalues(v);
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

        private void методСправочникКнопкаСоздания(@NonNull  MaterialCardView родительскийCardView) {
            try{
                MaterialButton materialButton
                        = (MaterialButton)   родительскийCardView.findViewById(R.id.bottomnewordertransport);//ВИД CFO
                // TODO: 12.05.2023
                materialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        МетодЗапускаАнимацииAllvalues(v);
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
        private     MaterialButton materialButtonЗакрытьДиалог;
        private    ListView listViewForNewOrderTranport;
        private   Integer КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview;

        private  Cursor cursor;
        void методПоискаНовыйЗаказТранспорта(@NonNull MaterialTextView materialTextView,
                                             @NonNull Cursor cursor,
                                             @NonNull String Столбик,
                                             @NonNull String ТаблицаТекущая,
                                             @NonNull String Спровочник){

            this.   animation1= AnimationUtils.loadAnimation(materialTextView.getContext(),R.anim.slide_in_row_newscanner1);

            this.cursor=cursor;
            //КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview_kogda_net_group;
            // TODO: 28.12.2022 cursor
            alertDialog  = new MaterialAlertDialogBuilder(materialTextView.getContext()){
                @NonNull
                @Override
                public MaterialAlertDialogBuilder setView(View view) {
                    try{
                        // TODO: 14.12.2022
                        materialButtonЗакрытьДиалог =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                        // TODO: 19.12.2022 какой КУРСОР БУДЕТ ВЫПОЛНЯТЬСЯ
                        SearchView searchViewДляНовогоЦФО= null;
                        Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО " + cursor);
                        listViewForNewOrderTranport =    (ListView) view.findViewById(R.id.SearchViewList);
                        listViewForNewOrderTranport.setTextFilterEnabled(true);
                        searchViewДляНовогоЦФО = (SearchView) view.findViewById(R.id.searchview_newscanner);
                        // TODO: 14.12.2022
                        searchViewДляНовогоЦФО.setDrawingCacheBackgroundColor(Color.GRAY);
                        searchViewДляНовогоЦФО.setDrawingCacheEnabled(true);
                        int id = searchViewДляНовогоЦФО.getContext()
                                .getResources()
                                .getIdentifier("android:id/search_src_text", null, null);
                        TextView textView = (TextView) searchViewДляНовогоЦФО.findViewById(id);
                        textView.setTextColor(Color.rgb(0,102,102));
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


                        ///TODO ГЛАВНЫЙ АДАПТЕР чата
                        SimpleCursorAdapter simpleCursorAdapterЦФО=
                                new SimpleCursorAdapter(materialTextView.getContext(),
                                        R.layout.simple_newspinner_dwonload_newfiltersearch, cursor, new String[]{ Столбик,"_id"},
                                        new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                        SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder(){

                            @Override
                            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                switch (view.getId()) {
                                    case android.R.id.text1:
                                        Log.d(this.getClass().getName()," position");
                                        if (cursor.getCount()>0) {
                                            try{
                                                Integer ИндексНазваниеЦФО = cursor.getColumnIndex(Столбик);///user_update  --old/// uuid
                                                String  НазваниеЦФО = cursor.getString(ИндексНазваниеЦФО).trim();
                                                // TODO: 13.12.2022  производим состыковку
                                                Integer   ИндексНазваниеЦфоID = cursor.getColumnIndex("_id");///user_update  --old/// uuid
                                                Integer   ПолучаемIDЦфо = cursor.getInt(ИндексНазваниеЦфоID);
                                                if (ПолучаемIDЦфо>0) {
                                                    Integer UUIDНазваниеЦФО = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                    Long UUIDЦФО = cursor.getLong(UUIDНазваниеЦФО);
                                                    Bundle bundle=new Bundle();
                                                    bundle.putInt("ПолучаемIDЦфо",ПолучаемIDЦфо);
                                                    bundle.putString("НазваниеЦФО",НазваниеЦФО);
                                                    bundle.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
                                                    ((MaterialTextView)view).setTag(bundle);
                                                }
                                                Log.d(this.getClass().getName()," НазваниеЦФО"+ НазваниеЦФО+
                                                        " ПолучаемIDЦфо "+ПолучаемIDЦфо);
                                                // TODO: 20.01.2022
                                                Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеЦФО);
                                                boolean ДлинаСтрокивСпиноре = НазваниеЦФО.length() >40;
                                                if (ДлинаСтрокивСпиноре==true) {
                                                    StringBuffer sb = new StringBuffer(НазваниеЦФО);
                                                    sb.insert(40, System.lineSeparator());
                                                    НазваниеЦФО = sb.toString();
                                                    Log.d(materialTextView.getContext().getClass().getName(), " НазваниеЦФО " + "--" + НазваниеЦФО);/////
                                                }
                                                ((MaterialTextView)view).setText(НазваниеЦФО);
                                                // TODO: 13.12.2022 слушатель
                                                ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        try{
                                                            ((MaterialTextView)view).startAnimation(animation1);
                                                            Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                            Integer IDЦфоДЛяПередачи=      bundle.getInt("ПолучаемIDЦфо",0);
                                                            String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
                                                            Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
                                                            materialTextView.setTag(bundle);
                                                            materialTextView.setText(НазваниеЦФО);

                                                            // TODO: 15.05.2023 ели есть что вставить
                                                            if (    materialTextView.getText().toString().length()==0) {
                                                                Snackbar.make(view, " Вы не выбрали цфо !!! "
                                                                        , Snackbar.LENGTH_LONG).show();
                                                                materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                                materialTextView.setTextColor(Color.GRAY);
                                                                Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                            } else {
                                                                materialTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                                materialTextView.setTextColor(Color.BLACK);
                                                                alertDialog.dismiss();
                                                                alertDialog.cancel();
                                                                Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                            }
                                                            materialTextView.refreshDrawableState();
                                                            materialTextView.forceLayout();
                                                            Log.d(this.getClass().getName()," position");
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
                                                // TODO: 13.12.2022 филь
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                        simpleCursorAdapterЦФО.setViewBinder(БиндингДляЦФО);
                        simpleCursorAdapterЦФО.notifyDataSetChanged();
                        listViewForNewOrderTranport.setAdapter(simpleCursorAdapterЦФО);
                        listViewForNewOrderTranport.startAnimation(animationvibr1);
                        listViewForNewOrderTranport.setSelection(0);
                        listViewForNewOrderTranport.forceLayout();
                        listViewForNewOrderTranport.refreshDrawableState();

                        // TODO: 13.12.2022  Поиск и его слушель
                        МетодПоискаФильтр(searchViewДляНовогоЦФО,simpleCursorAdapterЦФО,materialTextView,ТаблицаТекущая);

                        // TODO: 15.05.2023 Слушатель Действия Кнопки Сохранить

                        методКликДейсвиеКнопкиСохранить(searchViewДляНовогоЦФО,materialButtonЗакрытьДиалог,materialTextView);

                        Log.d(materialTextView.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  materialTextView.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                    return super.setView(view);
                    // TODO: 20.12.2022  тут конец выбеленого
                }


            }
                    .setTitle(Спровочник)
                    .setCancelable(false)
                    .setIcon( R.drawable.icon_newscannertwo)
                    .setView(getLayoutInflater().inflate( КакойИмменоВидЗагружатьДляНовогоПосика, null )).show();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(    alertDialog.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.gravity = Gravity.CENTER;
            alertDialog.getWindow().setAttributes(layoutParams);
            // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
            //МетодЗакрытиеНовогоПосика(materialButtonЗакрытьДиалог);
        }

        private void МетодПоискаФильтр(@NonNull   SearchView searchViewДляНовогоЦФО,
                                       @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                       @NonNull MaterialTextView  materialTextView,
                                       @NonNull String ТаблицаДляФильтра) {
            try{

                searchViewДляНовогоЦФО.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                            Log.d(this.getClass().getName()," position");
                            if (hasFocus==true) {
                                ((SearchView) v).setQueryHint("Поиск цфо");
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

                searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                            Log.d(this.getClass().getName()," position");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                            Log.d(this.getClass().getName()," position");
                            Filter filter= simpleCursorAdapterЦФО.getFilter();
                            filter.filter(newText);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }
                });
                simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        Log.d(this.getClass().getName()," position");
                        try{
                            // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                            // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР   // TODO: 15.05.2023 ПЕРЕПОЛУЧАЕМ НОВЫЕ ДАННЫЕ КУРСОР
                            simpleCursorAdapterЦФО.swapCursor(cursor);
                            simpleCursorAdapterЦФО.notifyDataSetChanged();
                            listViewForNewOrderTranport.setSelection(0);
                            if (cursor.getCount()==0) {
                                searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                message.getTarget().postDelayed(() -> {
                                    searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                }, 250);
                            }

                            Log.d(materialTextView.getContext().getClass().getName(), "\n"
                                    + " время: " + new Date()+"\n+" +
                                    " Класс в процессе... " +  materialTextView.getContext().getClass().getName()+"\n"+
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return  cursor;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(materialTextView.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        private void методКликДейсвиеКнопкиСохранить(@NonNull   SearchView searchViewДляНовогоЦФО,
                                       @NonNull MaterialButton  materialButton,@NonNull MaterialTextView materialTextViewФильтр) {
            try{
                materialButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.cancel();
                        alertDialog.cancel();
                        Log.d(materialButton.getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  materialButton.getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " materialTextViewФильтр " +materialTextViewФильтр);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(materialTextViewФильтр.getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 15.05.2023 КОНЕЦ НОВОГО ПОСИКА
    }

    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}