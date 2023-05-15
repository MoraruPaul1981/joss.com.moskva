package com.dsy.dsu.CodeOrdersAnTransports.Window;

import android.app.Activity;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private  MaterialButton bottomnewordertransport;
    private  SubClassSearchViews subClassSearchViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта
            subClassNewOrderTransport    =new SubClassNewOrderTransport(getActivity());
            // TODO: 15.05.2023
            subClassSearchViews=new SubClassSearchViews();
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
                                    subClassSearchViews.    методСправочникЦФО(родительскийCardView);
                                        // TODO: 15.05.2023  Вид ТС
                                        subClassSearchViews.     методСправочникВидТС(родительскийCardView);
                                        // TODO: 15.05.2023  ГОС Номер
                                        subClassSearchViews.    методСправочникГосНомер(родительскийCardView);
                                        // TODO: 15.05.2023  Дата Будущего Времени
                                        subClassSearchViews.      методСправочникДатаБудущая(родительскийCardView);
                                        // TODO: 15.05.2023  Кнопка Создания
                                        subClassSearchViews.      методСправочникКнопкаСоздания(родительскийCardView);

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
        private void методСлушательGridViews() {
            try {
                gridViewNewOrderTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        MaterialCardView linearLayoutЗаказыТранспорта= (MaterialCardView)
                                view.findViewById(android.R.id.text1);

                        MaterialTextView materialTextView1  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.valuecfo);//ВИД ТС
/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    }
                });
                // TODO: 14.05.2023
                gridViewNewOrderTransport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        MaterialCardView linearLayoutЗаказыТранспорта= (MaterialCardView)
                                view.findViewById(android.R.id.text1);

                        MaterialTextView materialTextView1  = (MaterialTextView) linearLayoutЗаказыТранспорта.findViewById(R.id.valuecfo);//ВИД ТС
/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );


                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date()+"\n+" +
                                " Класс в процессе... " +  getContext().getClass().getName()+"\n"+
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
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
        private void методСлушательКнопкиNewOrderTra() {
            try {
                bottomnewordertransport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ////TODO одинатрный клик для загрузки в этот табель всех сотрудников
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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
    class SubClassSearchViews{
        public SubClassSearchViews() {
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
    }//TODO END SubClassSearchViews TODO END SubClassSearchViews  TODO END SubClassSearchViews TODO END SubClassSearchViews
    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}