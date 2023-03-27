package com.dsy.dsu.Code_For_Chats_КодДля_Чата;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.SubClassGET_FIO;
import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


public class Fragment_Messages_СообщенияЧата extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    private View viewДляСообщений = null;
    private  ListView ЛистВьюДляСообщенийЧата = null;
    private Cursor КурсорДанныеДляСообщенийЧата = null;
    private Cursor Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате = null;
    private TextView ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных = null;
    private  FloatingActionButton floatingActionButtonФрагментСообщение;
     private Long ПолученыйIDДляЧата;
    private Long ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески;
    private TextView textViewФрагментСообщенияНазваниеЧАты;
    private View viewФрагментСообщенияНазваниеЧАты;
    private   PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;///
    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private Integer ПубличноеIDПолученныйИзСервлетаДляUUID = 0;
    private   String ФИОдляпервогоФрагмента;
    private   Long UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты;
    private   HashMap<String, Object> ХэщЗапусАктивтиИзФрагмента;
    private  SimpleCursorAdapter АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей = null;
   final private   String ИмяСлужбыОбщейСинхронизацииДляЗадачи = "WorkManager Synchronizasiy_Data";
  final  private  String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    private   AccessibilityNodeInfo AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID;
    private  AccessibilityNodeInfo AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID;
    private  List<String> ЛистДополнительныеПрарментыID;
    private   List<String> ЛистДополнительныеПрарменты_UUID;
    private   Bundle bundleомпараменты=new Bundle();
    private Handler handlerДляФрагментаMeaasageЧАТ;
    // TODO: 29.06.2022
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    // TODO: 29.06.2022
    private ProgressBar progressBarДляЧатаСообщения;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            // Inflate the layout for this fragment
            viewДляСообщений = inflater.inflate(R.layout.fragment1_layout, container, false);
            ЛистВьюДляСообщенийЧата = (ListView) viewДляСообщений.findViewById(R.id.list);
            ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных = (TextView) viewДляСообщений.findViewById(R.id.TextviewКогдаНетДанных);
            ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных.setVisibility(View.GONE);
            floatingActionButtonФрагментСообщение = (FloatingActionButton) viewДляСообщений.findViewById(R.id.floatingActionButtonФрагментСообщение);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getActivity());///
            Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(getContext());
            textViewФрагментСообщенияНазваниеЧАты = (TextView) viewДляСообщений.findViewById(R.id.textViewФрагментСообщенияНазваниеЧАты);
            viewФрагментСообщенияНазваниеЧАты = (View) viewДляСообщений.findViewById(R.id.viewФрагментСообщенияНазваниеЧАты);

            progressBarДляЧатаСообщения= (ProgressBar) viewДляСообщений.findViewById(R.id.prograessbaк_for_chatswrite);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
        return viewДляСообщений;
    }







    ////////////////////////////////МОДЕЛЬ MVC ////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onStart() {
        super.onStart();

        try {

            ХэщЗапусАктивтиИзФрагмента = new HashMap<>();

            // TODO  ////////////////////////////МОДЕЛЬ MVC ////////////////////////////////////////////////////////////////////////////////////////////////




            new CONTROLLER(getActivity()).  МетодРегистрацииУстройсвоНАFirebaseAndOneSignal();

            Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID   " +ПубличноеIDПолученныйИзСервлетаДляUUID);



            //  viewФрагментСообщенияНазваниеЧАты.getBackground().setTint(Color.parseColor("#212121"));

            MODEL modelФрагментСообщениия =new MODEL(getActivity());

            modelФрагментСообщениия.МетодПолученияПубличногоIDДляСообщенийЧата();

            modelФрагментСообщениия =new MODEL(getActivity());



            Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID   " +ПубличноеIDПолученныйИзСервлетаДляUUID);


            // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL

            modelФрагментСообщениия.МетодПодписываемсяОдноразовыйСлушателяСлужбыОбюмена();

            Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID   " +ПубличноеIDПолученныйИзСервлетаДляUUID);

            modelФрагментСообщениия.МетодЗапускаОБЩЕЙСлушателяОбщегоWorkManager();

            Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID   " + ПубличноеIDПолученныйИзСервлетаДляUUID);


            new Fragment_Messages_СообщенияЧата.MODEL(getActivity());


            new Fragment_Messages_СообщенияЧата.VIEW(getActivity());


            new Fragment_Messages_СообщенияЧата.CONTROLLER(getActivity());

            modelФрагментСообщениия. МетодИнициализациHandlerCallBack();




        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///


        }

    }

    @Override
    public void onPause() {
        super.onPause();

        КурсорДанныеДляСообщенийЧата.requery();


    }

    @Override
    public void onStop() {
        super.onStop();
        try {


     /*   if (КурсорДанныеДляСообщенийЧата!=null && dataSetObserverФрагментСообшенияДЛяКУРСОРА!=null  ) {

            КурсорДанныеДляСообщенийЧата.unregisterDataSetObserver(dataSetObserverФрагментСообшенияДЛяКУРСОРА);
        }*/


          /*  if (observerОдноразоваяДляФрагментаСообщенияЧата != null) {
                // TODO: 30.12.2021   --ОТПИСЫВАЕМСЯ
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObserver(observerОдноразоваяДляФрагментаСообщенияЧата);
            }*/


            // TODO: 04.03.2022

       /*     if (observerОбщейДляWORKMANAGER != null && ИмяСлужбыОбщейСинхронизацииДляЗадачи != null) {
                // TODO: 30.12.2021   --ОТПИСЫВАЕМСЯ
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыОбщейСинхронизацииДляЗадачи).removeObserver(observerОбщейДляWORKMANAGER);
            }*/
        //    WorkManager.getInstance(getContext()).cancelAllWorkByTag(ИмяСлужбыСинхронизацииОдноразовая);

        } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }



    }






    // TODO: 18.06.2021  класс вью
    private class VIEW {

        public VIEW(Activity activity) {


            try {


                Log.d(this.getClass().getName(), "  VIEW   ");

                if (КурсорДанныеДляСообщенийЧата.getCount() > 0) {
                    Log.d(this.getClass().getName(), "  КурсорДанныеДляСообщенийЧата   " + КурсорДанныеДляСообщенийЧата.getCount());


                    // TODO: 28.06.2021 мето заполения даными ФРАГМЕНТА СООБЩЕНИЯ


                    МетодЗаполенияПервогоФрагментаДаннымиСообщения();


                } else {

                    ///

                    // TODO: 28.06.2021 данный код когда нет вообще данных в курсоре и нет сообдений
                    ЛистВьюДляСообщенийЧата.setVisibility(View.GONE);


                    ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

                    // ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных.setPadding(30,0,0,0);

                    // TODO: 29.06.2021 когда данныхе нет
                    StringBuffer БуферКогдаНетСообщенийВооюоще = new StringBuffer("* Нет  чатов !!! *");
                    ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных.setVisibility(View.VISIBLE);


                    // TODO: 21.12.2021

                    Log.d(this.getClass().getName(), "  БуферКогдаНетСообщенийВооюоще.toString()   " + БуферКогдаНетСообщенийВооюоще.toString());


                    ТекстВиюВьюДляСообщенийЧатаКогдаНетДанных.setText(БуферКогдаНетСообщенийВооюоще.toString());
                }


                ///


                ///


                ////


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }

        private void МетодЗаполенияПервогоФрагментаДаннымиСообщения() throws ExecutionException, InterruptedException {
            // КурсорДанныеДляСообщенийЧата

            SubClassGET_FIO subClassGET_fio=       new SubClassGET_FIO(getContext());
            // ID
            ///
            Class_GRUD_SQL_Operations class_grud_sql_operationsЗаполенияПервогоФрагментаДаннымиСообщенияЧастьПервая = new Class_GRUD_SQL_Operations(getActivity());

            // TODO: 11.02.2022
            Class_GRUD_SQL_Operations       class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате=new Class_GRUD_SQL_Operations(getContext());

            // TODO: 11.02.2022
            PUBLIC_CONTENT public_contentCompletionService=new PUBLIC_CONTENT(getContext());

            // TODO: 11.02.2022

            CREATE_DATABASE create_databaseSQLiteDatabase=new CREATE_DATABASE(getContext());

            // TODO: 11.02.2022


            // TODO: 18.02.2022

            ЛистДополнительныеПрарментыID=new ArrayList();
            // TODO: 18.02.2022
            ЛистДополнительныеПрарменты_UUID=new ArrayList();

          АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей = null;
            try {

                /////

                АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей = new SimpleCursorAdapter(getContext(), R.layout.simple_for_chats_messages, КурсорДанныеДляСообщенийЧата,
                        new String[]{"user_update", "uuid"}, ///
                        new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

                // TODO: 21.12.2021


                //
                SimpleCursorAdapter.ViewBinder БиндингДляСообщенийЧата = new SimpleCursorAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                        // TODO: 22.06.2021 выравниваем тексвид  ДЛЯ ВСЕХ

                        ((TextView) view).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                        /////
                        ((TextView) view).setVisibility(View.VISIBLE);


                        ////
                        if (cursor.getCount() > 0) {
                            ///
                            switch (view.getId()) {

                                case android.R.id.text1:


                               Log.d(this.getClass().getName(), " ClassActitytyClassActityty  view.getId() " + view.getId()+ " columnIndex "+ columnIndex  + " cursor " +cursor);

                                    AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID=view.createAccessibilityNodeInfo();
                                    // TODO: 18.02.2022
                                    AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID=view.createAccessibilityNodeInfo();



                                    Log.d(this.getClass().getName(), " ClassActitytyClassActityty  view.getId() " + view.getId()+ " columnIndex "+ columnIndex  + " cursor " +cursor);

                                   // TODO: 28.06.2021  главное ДЕЙСТВИЕ ЗАПОЛЯНЕМ ДАННЫМИ НАШ SIMPLECURSORADAPTER




                                       МетодГлавныйЗаполняемДаннымиНашSimpleCursorAdapter((TextView) view,
                                            cursor,subClassGET_fio,ПубличноеIDПолученныйИзСервлетаДляUUID
                                               ,public_contentCompletionService,create_databaseSQLiteDatabase);
                                    ///
                                    // TODO: 21.12.2021

                                    Log.d(this.getClass().getName(), " I DдляпервогоФрагментаДляСообщенияTag " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+ " view " +view);

///todo КОНЕЦ ОБРАБОТКИ ПЕРВОГО ВЕРНЕГО TEXT1

                                    return true;














                             // TODO: 28.06.2021 заполения ВТОРОГОЕ ТЕКС ФИЮ АКТИВТИ ПЕРВОГО С СООБЕЩНИЯСИМ

                                case android.R.id.text2:

                      /*              Log.d(this.getClass().getName(), " ClassActitytyClassActityty  view.getId() " + view.getId());


                                    МетодВторостипенныйЗаоленетИОформляетВторойНижнийText2НашегоSimpleCursorAdaper((TextView) view, cursor);

                                    ///todo КОНЕЦ ОБРАБОТКИ ВТОРОГО НИЖНЕГО TEXT2
                                    // TODO: 21.12.2021

                                    Log.d(this.getClass().getName(), "text2 I DдляпервогоФрагментаДляСообщенияTag " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты);
                                    // TODO: 20.01.2022 Положительный ответ для SimpleCursorAdapter*/

                                    return true;


                            }





                            // TODO: 21.12.2021 кодга нет данных
                        } else {


                            МетодНашегоSimpleCursorAdapterКогдаВообщеНетДанныхВКурсоре((TextView) view);

                            // TODO: 21.12.2021  КОНЕЦ МЕТОДА КОГДА ВООБЩЕ НЕТ ДАННЫХ ДЛЯ НАШЕГО ФРАГМЕНТА СООБЕЩНИЯ ОТ ДРУГИХ ПОЛЬЗОВАТЕЛЕЙ

                            Log.d(this.getClass().getName(), "   if (cursor.getCount() > 0) {      return false;  " );

                            // TODO: 20.01.2022 Отрицательный  ответ для SimpleCursorAdapter

                            return false;
                        }


                        //////////////////
                        Log.d(this.getClass().getName(), "   if (cursor.getCount() > 0) {  return true;  " );
                        // TODO: 20.01.2022 Положительный ответ для SimpleCursorAdapter

                        return true;
                    }



                    // TODO: 11.02.2022 начало пересеног методов  sub class #1






                    // TODO: 11.02.2022 начало пересеног методов  sub class #1


                    protected  View МетодГлавныйЗаполняемДаннымиНашSimpleCursorAdapter(TextView view, Cursor cursor,
                                                                                       SubClassGET_FIO subClassGET_fio,
                                                                                       Integer ПубличноеIDПолученныйИзСервлетаДляUUID,
                                                                                       PUBLIC_CONTENT public_contentCompletionService,
                                                                                       CREATE_DATABASE create_databaseSQLiteDatabase) {




                        TextView viewCalssBaskTExt1 = null;


                        int ИндексДляФИОпервогоФрагмента;// вывшый user_for


                        try {

/*
     do{

                 Integer Индекс=cursor.getColumnIndex("id_user");

                 Integer чтовкурсоре=cursor.getInt(Индекс);

                 Log.d(this.getClass().getName(), "чтовкурсоре "  + чтовкурсоре);

             }while (cursor.moveToNext());
                            cursor.moveToFirst();*/


                           // Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsФрагментМСообщения= class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.new GetData(getContext());





                            ИндексДляФИОпервогоФрагмента = cursor.getColumnIndex("id_user");//user_update


                            ////
                            int IDдляпервогоФрагментаПользовательКоторыйНаписал = cursor.getInt(ИндексДляФИОпервогоФрагмента);

                            Log.d(this.getClass().getName(), "  IDдляпервогоФрагментаПользовательКоторыйНаписал  "
                                    + IDдляпервогоФрагментаПользовательКоторыйНаписал +
                                    " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);



                            if (ПубличноеIDПолученныйИзСервлетаДляUUID.compareTo(IDдляпервогоФрагментаПользовательКоторыйНаписал) !=0) {


                                Log.d(this.getClass().getName(), "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал +
                                        " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


                                //TODO ПЕРВОЕ ДЕЙСТВИЕ  Я НАПИСАЛ  ДЕЙСТВИЕ МНЕ НАПИСАЛИ ДРУГИЕ УЧАСТИК ЧАТА   #1


                                Log.d(this.getClass().getName(), "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал);

                                МетодЗаполняемИнформациеДляФрагментаСообщениОтСебИДругихПользователейЧата(view, cursor, IDдляпервогоФрагментаПользовательКоторыйНаписал);

                                Log.d(this.getClass().getName(), "  ФИОдляпервогоФрагмента  " + ФИОдляпервогоФрагмента
                                        + " IDдляпервогоФрагментаПользовательКоторыйНаписал "
                                        + IDдляпервогоФрагментаПользовательКоторыйНаписал +
                                        " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


                                //TODO ПЕРВОЕ ДЕЙСТВИЕ  Я НАПИСАЛ  ДЕЙСТВИЕ МНЕ НАПИСАЛИ ДРУГИЕ УЧАСТИК ЧАТА   #1

                                // TODO: 21.12.2021

                                Log.d(this.getClass().getName(), " I DдляпервогоФрагментаДляСообщенияTag " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+
                                        " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);

                                //TODO ОпеределяемСтарусЕслиХотьОднаНЕпрочитаное Союощение ДяТекущегоПользователя У каждого кто ему НАПИСАЛ

                                МетодВычисляемЕслиХотьОдноСообщениеНеПрочитаноДляТекущегоПользоватеялСтрочки(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты,ПубличноеIDПолученныйИзСервлетаДляUUID
                                        , public_contentCompletionService,create_databaseSQLiteDatabase);



                                // TODO: 21.12.2021

                                Log.d(this.getClass().getName(), " I DдляпервогоФрагментаДляСообщенияTag " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты);


                                // TODO: 22.12.2021 ВЫХОД САМО СООБЕЩЕНИ ПРОЧИТАНОЕ ИЛ НЕТ ЖИРНОЕ ИЛИ НЕТ

                                МетодПослеВычисенияЕстьПрочитанныеИлиНЕтСообщенияДляПользователяОтДругихТоОформлаяяемНашФрагмент((TextView) view,getContext());

                                // TODO: 21.12.2021

                                Log.d(this.getClass().getName(), " I DдляпервогоФрагментаДляСообщенияTag " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты);


                                //TODO ВТОРОЕ ДЕЙСТВИЕ МНЕ НАПИСАЛИ ДРУГИЕ УЧАСТИК ЧАТА   #2
                                ///
                            }



                        } catch (
                                Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 01.09.2021 метод вызова
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());

                        }

                        return  viewCalssBaskTExt1;
                    }

                    // TODO: 11.02.2022 второй метод переноса


                    private void МетодЗаполняемИнформациеДляФрагментаСообщениОтСебИДругихПользователейЧата
                            (TextView view,
                             Cursor cursor,
                             int IDдляпервогоФрагментаПользовательКоторыйНаписал) {



                        try{


                            Log.d(this.getClass().getName(), "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал +
                                    " ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);



                            // TODO: 21.12.2021


                            // TODO: 21.12.2021  get UUID
                            UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты = 0l;
                            // TODO: 21.12.2021


                            Integer ИндексДляUUIDпервогоФрагмента = cursor.getColumnIndex("uuid_parent");///user_update  --old/// uuid

                            UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты = cursor.getLong(ИндексДляUUIDпервогоФрагмента);
                            // TODO: 20.01.2022


                            Log.d(this.getClass().getName(), "  UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты  " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+"\n");


                            // TODO: 22.12.2021


                            if (UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты>0) {
                                //// todo очень ВАЖНО ТУТ МЫ ПРИСВАЕМЫВАЕМ UUID КОНКРЕТНОЙ СТРОКЕ И ДАЛЕЕ ЭТОБУЕДТ СКОМ МЫ ПЕРЕРПИСЫВАЕМСЯ
                                view.setTag(String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты));

                                // TODO: 14.02.2022

                                // TODO: 14.02.2022
                              //  view.setError(String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты));
                                // TODO: 14.02.2022
                                ЛистДополнительныеПрарменты_UUID.add((String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты)));

                                AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID.setAvailableExtraData(ЛистДополнительныеПрарменты_UUID);

                                // TODO: 18.02.2022
                                Log.d(this.getClass().getName(), "" +
                                        "  AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID  "
                                        + AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID.getAvailableExtraData()+"\n");

                            }


                            Log.d(this.getClass().getName(), "  ((TextView) view).getText() UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты  " + view.getTag());


                            //МетодТЕСТОВЫЙВТОРОТОМНАХОДИТЬСяЗАпускПрогресбраИБезАктивтиИТестовыйAxceessДополемтеольныйНААкждуюСтрочкуДанныеACCESSINFo(view, IDдляпервогоФрагментаПользовательКоторыйНаписал);


                            if (IDдляпервогоФрагментаПользовательКоторыйНаписал>0) {



                                // TODO: 14.02.2022
                                ЛистДополнительныеПрарментыID.add(String.valueOf(IDдляпервогоФрагментаПользовательКоторыйНаписал));

                                AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID.setAvailableExtraData(ЛистДополнительныеПрарментыID);

                                // TODO: 18.02.2022
                                // TODO: 14.02.2022
                                Log.d(this.getClass().getName(), "  AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID  "
                                        +AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID.getAvailableExtraData());


                                // TODO: 15.02.2022
                                bundleомпараменты.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"5555555");
                                // TODO: 15.02.2022


                            //    view.setFontFeatureSettings(String.valueOf(IDдляпервогоФрагментаПользовательКоторыйНаписал));

                                // TODO: 14.02.2022
                                Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID + " \n" +
                                        "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал+ " ЛистДополнительныеПрарментыID " +
                                        ЛистДополнительныеПрарментыID.toArray().toString()
                                        + "ЛистДополнительныеПрарменты_UUID.toArray().toString()  " +ЛистДополнительныеПрарменты_UUID.toArray().toString() );
                            }


                            Log.d(this.getClass().getName(), "  ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID + " \n" +
                                    "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал);

                            // TODO: 21.12.2021

                            ФИОдляпервогоФрагмента = new String();

                            // TODO: 11.02.2022



                            //
                            ФИОдляпервогоФрагмента = subClassGET_fio.МетодПолучениеФИОНАОснованииIDВыбранногоСотрудника(IDдляпервогоФрагментаПользовательКоторыйНаписал);

                            Log.d(this.getClass().getName(), "  ФИОдляпервогоФрагмента  " + ФИОдляпервогоФрагмента);

                            //



                            ///

                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            ///


                        }
                    }

                    private void МетодТЕСТОВЫЙВТОРОТОМНАХОДИТЬСяЗАпускПрогресбраИБезАктивтиИТестовыйAxceessДополемтеольныйНААкждуюСтрочкуДанныеACCESSINFo(TextView view, int IDдляпервогоФрагментаПользовательКоторыйНаписал) {
    /*                LinkedHashMap<String,Object> linkedHashMap=new LinkedHashMap();

     // TODO: 14.02.2022
     linkedHashMap.put("bugle",String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты));
     linkedHashMap.put("bugle1",String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+1));
     linkedHashMap.put("bugle2",String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+2));
     linkedHashMap.put("bugle3",String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты+3));


   accessibilityNodeInfo1дуль =view.createAccessibilityNodeInfo();

     List<String>list=new ArrayList<String>();

     list.add(0,linkedHashMap.toString());
     list.add(1,linkedHashMap.values().toArray().toString());
     list.add(2,linkedHashMap.keySet().toArray().toString());

     accessibilityNodeInfo1дуль.setAvailableExtraData(list);*/
                        // TODO: 14.02.2022

                        //  accessibilityNodeInfo1дуль.setContentDescription("ContentDisrerere");


                        //  accessibilityNodeInfo1дуль.setHintText(String.valueOf(UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты));


                        // TODO: 14.02.2022
                        //  List lisss=accessibilityNodeInfo1дуль.getAvailableExtraData();

                        // TODO: 14.02.2022

                        //   accessibilityNodeInfo1дуль.getExtras();


                        //  Log.d(this.getClass().getName(), "  lisss  " + lisss);


     /*                   View rootView = (View )((Activity)getContext()).getWindow().getDecorView().findViewById(R.id.viewФрагментСообщенияНазваниеЧАты);
                        // TODO: 22.12.2021


                        TextView textView333 = (TextView ) view.getRootView().findViewById(  android.R.id.text1);
                        // TODO: 22.12.2021

                        Log.d(this.getClass().getName(), "  IDдляпервогоФрагментаПользовательКоторыйНаписал  " + IDдляпервогоФрагментаПользовательКоторыйНаписал);


                        // TODO: 14.02.2022
                        LayoutInflater inflater = (LayoutInflater) getContext()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = inflater.inflate(R.layout.fragment1_layout, null);

                        ListView tvnetwork = (ListView) v.findViewById(R.id.list);*/
                    }
                    // TODO: 11.02.2022 третий преесенный метод в нижний классsub для ТЕКС1

                    private void МетодВычисляемЕслиХотьОдноСообщениеНеПрочитаноДляТекущегоПользоватеялСтрочки(Long ПолученныйUUIDСтчрокиКтоМнеНАписал,
                                                                                                              Integer ПубличноеIDПолученныйИзСервлетаДляUUID

                            ,PUBLIC_CONTENT public_contentCompletionService,
                                                                                                              CREATE_DATABASE create_databaseSQLiteDatabase ) {
                        ///
// TODO: 11.02.2022

                        try{

                            Log.d(this.getClass().getName(), "ПолученныйUUIDСтчрокиКтоМнеНАписал " + ПолученныйUUIDСтчрокиКтоМнеНАписал  + " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);


                            Class_GRUD_SQL_Operations       class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате=new Class_GRUD_SQL_Operations(getContext());


                            Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsФрагментМСообщения= class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.new GetData(getContext());




                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "data_chat");
                            ///////
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "*");
                            //
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика", " user_update  <>  ?   AND status_write=?  AND chat_uuid=? ");//todo old_    id_user
                            ///"_id > ?   AND _id< ?"
                            //////
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1", ПубличноеIDПолученныйИзСервлетаДляUUID);
                            //////
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2", 0);
                            //////
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",ПолученныйUUIDСтчрокиКтоМнеНАписал);
                            ///
      /*             class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......*/

                            ////TODO другие поля

                            ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                            ////
                            //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                            ////
                            class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                            ////
                            /// class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                            ////

                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                            Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате = null;
// TODO: 11.02.2022


                            /*       Class_GRUD_SQL_Operations.GetData class_grud_sql_operations= class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.new GetData(getActivity());*/

                            //////
                            ///////
                            Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате = (SQLiteCursor) class_grud_sql_operationsФрагментМСообщения
                                    .getdata(class_grud_sql_operationsCтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.
                                                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                            public_contentCompletionService.МенеджерПотоков,
                                            create_databaseSQLiteDatabase.getССылкаНаСозданнуюБазу());




                            Log.d(this.getClass().getName(), "GetData " + Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате);



                            if (Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount() > 0) {

                                // TODO: 28.12.2021
                                Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.moveToFirst();
                                // TODO: 28.12.2021
                                Log.d(this.getClass().getName(), "Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount() "
                                        + Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount());

                            }



                            /////////////
                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }



                    }
// TODO: 11.02.2022 четвертый метод переноса


                    private void МетодПослеВычисенияЕстьПрочитанныеИлиНЕтСообщенияДляПользователяОтДругихТоОформлаяяемНашФрагмент(TextView view,Context context) {


                        try {
                            // TODO: 22.06.2021 добавляем значёк


                            Drawable icon = null;
                            icon =context. getResources().getDrawable(R.drawable.icon_dsu1_for_fragment1_chat2);
                            icon.setBounds(20, 0, 110, 90);

                            ((TextView) view).setPadding(5, 5, 5, 5);

                            ((TextView) view).setCompoundDrawables(icon, null, null, null);


                            ((TextView) view).setBackgroundResource(R.drawable.sltyle_messege_view_contact_for_text2);


                     /*       ((TextView) view).setPadding(0, 20, 0, 20);*/


                            // TODO: 28.06.2021  заполняем id кому и скем чат



                            /////
                            view.setVisibility(View.VISIBLE);

                            view.setBackgroundResource(R.drawable.sltyle_messege_view_contact_for_text2);

                            if (Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount() > 0)  {

                                // TODO: 11.02.2022

                                // TODO: 21.12.2021
                                Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.moveToFirst();



                                Log.d(this.getClass().getName(), "Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount()  "
                                        + Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount()  + " ФИОдляпервогоФрагмента " +ФИОдляпервогоФрагмента );


                                view.setText(ФИОдляпервогоФрагмента + "  +(" + Курсор_ИщемСтатусХотьестьОДинНольНЕПрочттаноеСообщениевЧате.getCount() + ")");


                                Log.d(this.getClass().getName(), " ФИОдляпервогоФрагмента " +ФИОдляпервогоФрагмента  + "  view.getError()) " +view.getText());

                                view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

                                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);

                                Log.d(this.getClass().getName(), " ФИОдляпервогоФрагмента " +ФИОдляпервогоФрагмента  + " view.getText())" +view.getText()+"view.getError()) "+view.getError());
                            } else {

                                view.setText(ФИОдляпервогоФрагмента.trim());

                                view.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);

                                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                                Log.d(this.getClass().getName(), " ФИОдляпервогоФрагмента " +ФИОдляпервогоФрагмента  + " view.getText())" +view.getText()+"view.getError()) "+view.getError());

                            }
                            //


                        /*     АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.notifyDataSetChanged();


                            АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.notifyDataSetInvalidated();

                            // TODO: 11.02.2022





                            Log.d(this.getClass().getName(), " АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.getItem(0) "
                                    + АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.getItem(0)+
                                    " АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.getItem(1) "
                                    + АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.getItem(1));

                        ////
                        ЛистВьюДляСообщенийЧата.refreshDrawableState();
                        ////

                        ЛистВьюДляСообщенийЧата.deferNotifyDataSetChanged();
                        //
                        ЛистВьюДляСообщенийЧата.invalidateViews();
*/




                        } catch (Exception e) {
                            e.printStackTrace();
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            ///


                        }


                    }

// TODO: 11.02.2022 конец   пересеног методов  sub class #1












                       // / TODO: 11.02.2022 конец   пересеног методов  sub class #1






















                    private void МетодНашегоSimpleCursorAdapterКогдаВообщеНетДанныхВКурсоре(TextView view) {

try {
                        view.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                        view.setVisibility(View.GONE);

                        StringBuffer БуферКогдаНетСообщенийВооюоще = new StringBuffer("* Нет сообщений в чате !!! *");

                        // TODO: 28.06.2021 данный код когда нет вообще данных в курсоре и нет сообдений
                        view.setText(БуферКогдаНетСообщенийВооюоще.toString());

                        // TODO: 21.12.2021
                        Log.d(this.getClass().getName(), "  НЕТ НЕ ООДНОГО УОНТАКТА СИ ПЕРЕРПИСКЕ   " );

                    } catch (
                    Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }
                    }





                    private void МетодВторостипенныйЗаоленетИОформляетВторойНижнийText2НашегоSimpleCursorAdaper(TextView view, Cursor cursor) {

                        try{
                        //return true;
                        view.setBackgroundResource(R.drawable.sltyle_messege_view_contact);

                        view.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

                        // ((TextView) view).setPadding(100,0,0,0);
                        view.setTextColor(Color.parseColor("#696969"));//olor.parseColor("##008080")



                            view.setVisibility(View.VISIBLE);


                            view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);





                            String ФиналСтатусдляпервогоФрагмента = new String();


                        //
                        int ИндексДляДатыпервогоФрагмента = cursor.getColumnIndex("date_update");
                        ////
                        String ДатадляпервогоФрагмента = cursor.getString(ИндексДляДатыпервогоФрагмента);

                        Log.d(this.getClass().getName(), "  ДатадляпервогоФрагмента  " + ДатадляпервогоФрагмента);


                        Log.d(this.getClass().getName(), "  ФИОдляпервогоФрагмента  " + ФИОдляпервогоФрагмента + "  UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты " + UUIDдляпервогоФрагментаДляСообщенияTagДляПередачиВоДругиеФрагменты);

                        // TODO: 28.06.2021 даты обработка

                        Date date = null;
                        // TODO: 28.06.2021 даты обработка
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));


                        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                            date = dateFormat.parse(ДатадляпервогоФрагмента);


                            // TODO: 02.08.2021
                            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("ru"));

                            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                            // TODO: 02.08.2021

                                date = dateFormat.parse(ДатадляпервогоФрагмента);






                        String ФиналДата = null;

                        /////


                        ///
                        Log.d(this.getClass().getName(), "  date  " + date.toString());
                        /////////

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
                        //
                        simpleDateFormat.applyPattern("dd MMMM yyyy");//dd-MM-yyyy
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

                        // TODO: 21.12.2021

                        ФиналДата = null;
                        //////
                        ФиналДата = simpleDateFormat.format(date);

                        Log.d(this.getClass().getName(), "  ФиналДата  " + ФиналДата);



                        // TODO: 09.07.2021  HIDE
                        // TODO: 28.06.2021  заполняем id кому и скем чат

                        ///
                        view.setText(ФиналДата);

                        // TODO: 21.12.2021
                        Log.d(this.getClass().getName(), "  ((TextView) view).getText()  " + view.getText());



                    } catch (
                    Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }


                    }






































                };




                ////
                АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.setViewBinder(БиндингДляСообщенийЧата);
                ///
                ЛистВьюДляСообщенийЧата.setAdapter(АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей);



            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }
    }


    private class CONTROLLER implements AdapterView.OnItemClickListener {
        /////

        private String ПолученыйФИОIDДляЧата;

        public CONTROLLER(Activity activity) {
            try {

                Log.d(this.getClass().getName(), "  CONTROLLER  ");

                ЛистВьюДляСообщенийЧата.setOnItemClickListener(this);
                ///
                viewДляСообщений.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Log.d(this.getClass().getName(), "  onClick  MyWork_Update_ОбновлениеПО СЛУЖБА  " + viewДляСообщений.getId());
                    }
                });
                //  Курсор_ДляЗагрузкиСотрудников.close();


// TODO: 30.06.2021 ФЛот
                МетодКруглаяКнопкаНаФрагментеЧитатьПисать();


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }


        ///todo данный КОД РЕАГИРУЕТ КОГДА  ВСООБЩЕНИЯХ МЫ ВЫБРАЛИ КОНКРЕТНОГО ПОЛЬЗОВАТЕЛЯ КОТОРЫЙ НАМ НАПИСАЛ   В ЧАТ АГА ТУТ ВСЕ ПОЛЬЗОВАТЕЛИ СОБИРАЮТЬСЯ
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            try {
                Log.d(this.getClass().getName(), " onItemClick MyWork_Update_ОбновлениеПО СЛУЖБА  " + view.getId());


               /// ((TextView) view).setBackgroundColor(Color.GRAY);


                //  Snackbar.make(v, "Here",Snackbar.LENGTH_LONG).setAction("Action",null).show();


///
                Object ХэшДанныеВВидеОбьекта = parent.getItemAtPosition(position);

                ///
                TextView textViewВытаскиваемФрагментСообщения = view.findViewById(android.R.id.text1);

                //////
                textViewВытаскиваемФрагментСообщения.setBackgroundResource(R.drawable.style_for_chat_for_fragmaent_contact_for_click);

                ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески = 0l;



             String   ПолучкнныйКакПримерUUID=  AccessibilityNodeInfoДополнительнаяПередачаДанныхдляUUID.getAvailableExtraData().stream().collect(Collectors.toList()).get(position);

                // TODO: 15.02.2022
                Log.d(this.getClass().getName(), "  textView.getTag() " + textViewВытаскиваемФрагментСообщения.getTag() + " ПолучкнныйКакПримерUUID " + ПолучкнныйКакПримерUUID+
                        "  ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески " +ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);

                ///////////////////////////////TODO получения  ID
                Long ПолученныйНАКогоКликнулиUUID=Long.parseLong(ПолучкнныйКакПримерUUID);//todo view.getTag().toString()
                // TODO: 19.01.2022
                ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески = ПолученныйНАКогоКликнулиUUID;


                Log.d(this.getClass().getName(), "  textView.getTag() " + textViewВытаскиваемФрагментСообщения.getTag() + " ПолученыйIDДляЧата " + ПолученыйIDДляЧата+
                         "  ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески " +ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);


                ///ФИО
                ПолученыйФИОIDДляЧата = new String();
                //
                ПолученыйФИОIDДляЧата = textViewВытаскиваемФрагментСообщения.getText().toString().trim();

                Log.d(this.getClass().getName(), " ПолученыйФИОIDДляЧата " + ПолученыйФИОIDДляЧата);


                ///


                // TODO: 02.07.2021 полылаем значния с  сообщения


                Intent intentЗапускСообщенияИзФрагмента = new Intent();

                //////
                // intentЗапускЧата.setClass(getApplicationContext(), MainActivity_history_chat_test.class);

                intentЗапускСообщенияИзФрагмента.setClass(getActivity(), MainActivity_List_Chats.class);


                //
                ХэщЗапусАктивтиИзФрагмента.put("ЗапускАктивтиЧатИзФрагмента", "Повторный Запск Активти  Для Третьего Фрагмента");


                // TODO: 21.12.2021

                if (ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески > 0) {

                    // TODO: 29.06.2021 второй парамент передают ID
                    ХэщЗапусАктивтиИзФрагмента.put("ПолученыйUUIDУжеСуществующийПерепискиПользоватлейДляЧата", ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);
                }

                ///

                // TODO: 30.06.2021 ПолучаемКто написла чеоез ЗАПРОС


                // TODO: 29.06.2021 второй парамент передают ID
                if (ПолученыйФИОIDДляЧата.length() > 0) {
                    //////
                    ХэщЗапусАктивтиИзФрагмента.put("ПолученыйФИОIDДляЧата", ПолученыйФИОIDДляЧата);
                }



                String ПолучкнныйКакПримерID = null;
                //
                    // TODO: 14.02.2022

                    ПолучкнныйКакПримерID=   AccessibilityNodeInfoДополнительнаяПередачаДанныхДЛЯID.getAvailableExtraData().stream().collect(Collectors.toList()).get(position);

     Log.d(this.getClass().getName(), " ПолучкнныйКакПримерID " + ПолучкнныйКакПримерID);



                Log.d(this.getClass().getName(), " ПолучкнныйКакПримерID " + ПолучкнныйКакПримерID);

                // TODO: 29.06.2021 второй парамент передают ID
                if (ПолучкнныйКакПримерID.length() > 0) {
                    //////
                    long ПолученныйТекущийПубличныйIDКонтактаНАкоторыйНажали  = Long.parseLong(ПолучкнныйКакПримерID);

                    // TODO: 20.01.2022

                    Log.d(this.getClass().getName(), " ПолученныйТекущийПубличныйIDКонтактаНАкоторыйНажали " + ПолученныйТекущийПубличныйIDКонтактаНАкоторыйНажали);


                    ХэщЗапусАктивтиИзФрагмента.put("ПолученыйIDДляЧата", ПолученныйТекущийПубличныйIDКонтактаНАкоторыйНажали);
                }

                Log.d(this.getClass().getName(), " ХэщЗапусАктивтиИзФрагмента.values() " + ХэщЗапусАктивтиИзФрагмента.values()+
                         "\n" + " ХэщЗапусАктивтиИзФрагмента.keySet() " + ХэщЗапусАктивтиИзФрагмента.keySet());



                //
                intentЗапускСообщенияИзФрагмента.putExtra("ОбменМеждуАктивти", ХэщЗапусАктивтиИзФрагмента);


                intentЗапускСообщенияИзФрагмента.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intentЗапускСообщенияИзФрагмента);


                ///


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }


        private void МетодКруглаяКнопкаНаФрагментеЧитатьПисать() {


            //////////////////////////////
            floatingActionButtonФрагментСообщение.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(this.getClass().getName(), " ");
                    //////конец второго лушателя спинера
                    //  floatingActionButtonВФагментеReadandWrite.setBackgroundResource(R.drawable.icon_dsu1_fragment_read_write_down);  ////@drawable/icon_dsu1_singlescroll_forfart


                            КурсорДанныеДляСообщенийЧата.requery();




                    //TODO ТУТ КОД БУДЕТ ЗАПУСКАТЬСЯ СОЗДАЕНИЕ НОВОГО ИЛИ ДОБАВЛЕНИЕ ДЕЙСТВУЕЮЩЕГО СОТРУДНИКА В ТАБЕЛЬ
                    floatingActionButtonФрагментСообщение.setImageResource(R.drawable.icon_dsu1_message_add_kontact);

                    //  Snackbar.make(v, "Here",Snackbar.LENGTH_LONG).setAction("Action",null).show();


                    Handler handler = new Handler();
                    /////
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            floatingActionButtonФрагментСообщение.setImageResource(R.drawable.icon_dsu1_message_add_toback_fragment);





                            Intent intentЗапускЧатаИзФрагмента = new Intent();
                            ///


                            intentЗапускЧатаИзФрагмента.setClass(getActivity(), MainActivity_List_Chats.class);

                            HashMap<String, Object> ХэщЗапусАктивтиИзФрагмента = new HashMap<>();
                            //
                            ХэщЗапусАктивтиИзФрагмента.put("ЗапускАктивтиЧатИзФрагмента", "Повторный Запск Фрагмента Контактов");


                            // TODO: 30.06.2021 ПолучаемКто написла чеоез ЗАПРОС


                            //
                            intentЗапускЧатаИзФрагмента.putExtra("ОбменМеждуАктивти", ХэщЗапусАктивтиИзФрагмента);


                            intentЗапускЧатаИзФрагмента.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intentЗапускЧатаИзФрагмента);


                        }
                    }, 200);


                }


            });


        }
// TODO: 14.02.2022 передод еще один метод




        // TODO: 26.12.2021  метод регистации на СЕРВЕРА ONESIGNAL

        private void МетодРегистрацииУстройсвоНАFirebaseAndOneSignal() {

            try{
                ///
                String КлючДляFirebaseNotification="2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
                // TODO: 17.12.2021
                Integer  ПубличныйIDДляФрагмента=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());

                new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getContext()).
                        МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,ПубличныйIDДляФрагмента);


                //TODO ФУТУРЕ ЗАВЕРШАЕМ
                Log.d(this.getClass().getName(), "  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,0); " +
                        " РезультатЗаписиНовогоIDОтСервреаOneSignal  " );


                //
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в ВсеСтрокиJSONДляВставкиОтСервера
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }
        }



    }


    class MODEL {
        /////
        public MODEL(Activity activity) {

            ///
            SQLiteCursor Курсор_ВычисляемПУбличныйID = null;

            Class_GRUD_SQL_Operations class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть = new Class_GRUD_SQL_Operations(getActivity());

            try {


                // TODO: 05.07.2021  получаем публичный ID


                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ


                ///
                class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы", "SuccessLogin");
                ///////
                class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки", "id");
                //
            /*        class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=?    AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
                    ///"_id > ?   AND _id< ?"
                    //////
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalПолученныйUUID);
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
                    ///
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);
                    //
                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
*/
                ////TODO другие поля

                ///classGrudSqlOperations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки",null);
                ////
                //class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки",null);
                ////
                class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки", "date_update DESC");
                ////
                class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита", "1");
                ////

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ


                Курсор_ВычисляемПУбличныйID = null;
                //////////////

                Курсор_ВычисляемПУбличныйID = (SQLiteCursor) class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.
                        new GetData(getActivity()).getdata(class_grud_sql_operationsВычисляемПУбличныйIDВтораяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                Log.d(this.getClass().getName(), "GetData " + Курсор_ВычисляемПУбличныйID);




       /*         // TODO: 08.09.2021  _old
                Курсор_ВычисляемПУбличныйID=new Class_MODEL_synchronized(getContext()).КурсорУниверсальныйБазыДанных("SELECT id FROM SuccessLogin LIMIT 1 ");
                /////
*/
                // TODO: 08.09.2021 resultat

                if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                    //////////
                    Курсор_ВычисляемПУбличныйID.moveToFirst();
                    //////////////
                    ПубличноеIDПолученныйИзСервлетаДляUUID = Курсор_ВычисляемПУбличныйID.getInt(0);
                    //////
                    Log.d(this.getClass().getName(), "ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);

                }

                Курсор_ВычисляемПУбличныйID.close();
                // TODO: 28.06.2021  обнуялем курсор перед получением данных


                //  TimeUnit.MILLISECONDS.sleep(100);

                Log.d(this.getClass().getName(), "  MODEL  ПубличноеIDПолученныйИзСервлетаДляUUID " + ПубличноеIDПолученныйИзСервлетаДляUUID);


                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ

                Class_GRUD_SQL_Operations class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть = new Class_GRUD_SQL_Operations(getActivity());
                ///
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewchat");
//                ///////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
//                //
//      /*          class_grud_sql_operations_ДанныеДляСообщенийЧата. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid=?    " +
//                        "AND status_send !=? AND month_tabels=? AND  year_tabels =? AND fio IS NOT NULL ");
//                    ///"_id > ?   AND _id< ?"*/
//                    //////
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФлагНепотораяемостиСтрок",true);
//                    ///
//            /*      class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2","Удаленная");
//                    ///
//                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",МЕсяцДляКурсораТабелей);//ФлагНепотораяемостиСтрок
//                    //
//                    class_grud_sql_operations. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4",ГодДляКурсораТабелей);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
//*/
//                ////TODO другие поля*/
//
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","id_user");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеГрупировки"," COUNT(*) >= 1");
//                ////
//                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
//                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
//                ////
//               /// class_grud_sql_operationsMODEL. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","1");
                ////
                // TODO: 30.09.2021 МЕТОД ЗАПУСКА СИНХРОНИЗАЦИИ ЧАТА ПО РАСПИСАНИЮ , НЕ ВЗАВИСИМОСТИ ОТ СОЗДАВАЛ ЛИ СООБЩЕНИЕ ИЛИ НЕТ

                Integer ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getContext());


                Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);


                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                // class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",111);
                ///
                class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",///viewchat

         /*               " SELECT  * FROM viewchat  WHERE     id_user   =" + ПубличноеIDПолученныйИзСервлетаДляUUID +
                                " ORDER BY  date_update  ASC ;");
*/
                        // " SELECT  * FROM chats WHERE id_user  = "+ПубличныйIDДляФрагмента +"  ORDER BY  date_update  ASC ;");


                        " select cc.*," +
                                " (select id_user " +
                                " from chats " +
                                " where id_user=" + ПубличныйIDДляФрагмента + "" +
                                " ) as prev_date" +
                                "  from chats cc " +
                                " where cc.id_user <>" + ПубличныйIDДляФрагмента + "" + ";");

/*              //  "DROP TABLE IF EXISTS my_chats; "+
                "CREATE TEMPORARY TABLE my_chats AS "+
                "SELECT * FROM chats WHERE id_user = "+ПубличныйIDДляФрагмента+"; "+
                "SELECT  chats.* FROM chats 	inner join my_chats on chats.uuid_parent=my_chats.uuid_parent WHERE	chats.id_user <> "+ПубличныйIDДляФрагмента+";");*/

                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ  вторая операиця
                //////
                КурсорДанныеДляСообщенийЧата = null;

                ////////
                КурсорДанныеДляСообщенийЧата = (SQLiteCursor) class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.
                        new GetаFreeData(getActivity()).getfreedata(class_grud_sql_operations_ДанныеДляСообщенийЧатаТретьяЧасть.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков, Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());


                // TODO: 10.02.2022


                Log.d(this.getClass().getName(), "КурсорДанныеДлязаписиичтнияЧата " + КурсорДанныеДляСообщенийЧата);


                if (КурсорДанныеДляСообщенийЧата != null) {


                    if (КурсорДанныеДляСообщенийЧата.getCount() > 0) {


                        // TODO: 10.02.2022
                        /// КурсорДанныеДляСообщенийЧата.moveToFirst();


// TODO: 28.06.2021 мето заполения даными ФРАГМЕНТА СООБЩЕНИЯ


                        new VIEW(getActivity()).МетодЗаполенияПервогоФрагментаДаннымиСообщения();

                        Log.d(this.getClass().getName(), "КурсорДанныеДлязаписиичтнияЧата " + КурсорДанныеДляСообщенийЧата.getCount());


                        // TODO: 10.02.2022 иницализация слушателя


                        МеодПодключениеСлушателяКурсораOBSERVER();

                        Log.d(this.getClass().getName(), "КурсорДанныеДлязаписиичтнияЧата " + КурсорДанныеДляСообщенийЧата.getCount());
                    }
                }





         /*       // TODO: 28.09.2021
                КурсорДанныеДляСообщенийЧата.moveToFirst();
                if (КурсорДанныеДляСообщенийЧата.getCount() > 0) {
                    // TODO: 20.12.2021



                    Log.d(this.getClass().getName(), "GetData " + КурсорДанныеДляСообщенийЧата.getCount());

                    // TODO: 21.12.2021 просто проверка если вообще записи

*//*              do {


                        String ПРОверка = КурсорДанныеДляСообщенийЧата.getString(1);

                        Log.d(this.getClass().getName(), "GetData  КурсорДанныеДляСообщенийЧата КТО НАПИСАЛ " + ПРОверка);

                        String ПРОверкаUUIDКонкретонойТекущейЗапсии = КурсорДанныеДляСообщенийЧата.getString(3);

                        Log.d(this.getClass().getName(), "GetData  КурсорДанныеДляСообщенийЧата КТО ПРОверкаUUIDКонкретонойТекущейЗапсии " + ПРОверкаUUIDКонкретонойТекущейЗапсии);


                    } while (КурсорДанныеДляСообщенийЧата.moveToNext());
// TODO: 21.12.2021
                   *//*




                }*/





      /*          // TODO: 08.09.2021    _old

                КурсорДанныеДляСообщенийЧата= МетодПолучениеДанныхДляФрагментаСообщенияЧата("SELECT " +
                        " DISTINCT  * FROM viewchat   GROUP BY id_user HAVING COUNT(*) >= 1   ORDER BY date_update DESC");//бышвий user_for
                ////*/
                /*КурсорДанныеДляСообщенийЧата= МетодПолучениеДанныхДляФрагментаСообщенияЧата("SELECT * FROM ViewChat WHERE user_update ="+PUBLIC_CONTENT.ПУбличныйДанныеПришёлЛиIDДЛяГенерацииUUID+
                        " OR user_update="+PUBLIC_CONTENT.ПУбличныйДанныеПришёлЛиIDДЛяГенерацииUUID+"   GROUP BY user_update ORDER BY date_update DESC");*/

                //////

                Log.d(this.getClass().getName(), "  КурсорДанныеДляСообщенийЧата  " + КурсорДанныеДляСообщенийЧата);


                // TODO: 06.07.2021 вычислеме если хоть одно не прочитаное сообщение тогда статк ирный устанвливаем на переписку


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

        private void МеодПодключениеСлушателяКурсораOBSERVER() {
            try {
                DataSetObserver      dataSetObserverФрагментСообшенияДЛяКУРСОРА = new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        if (АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей != null && КурсорДанныеДляСообщенийЧата!=null) {
                            // TODO: 10.02.2022
                            АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.changeCursor(КурсорДанныеДляСообщенийЧата);
                            АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.notifyDataSetInvalidated();
                            АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.notifyDataSetChanged();
                            // TODO: 10.02.2022
                            if (ЛистВьюДляСообщенийЧата != null) {
                                // TODO: 10.02.2022
                                ЛистВьюДляСообщенийЧата.deferNotifyDataSetChanged();
                                // TODO: 10.02.2022
                                ЛистВьюДляСообщенийЧата.requestLayout();
                                ЛистВьюДляСообщенийЧата.refreshDrawableState();
                                ЛистВьюДляСообщенийЧата.forceLayout();
                            }
                        }
                        Log.d(this.getClass().getName(), "  старботал наблюдатель за данными  КурсорДанныеДляСообщенийЧата.registerDataSetObserver ");
                    }
                };
                // TODO: 20.01.2022
                if (КурсорДанныеДляСообщенийЧата!=null ) {
                    // TODO: 18.02.2022
                    КурсорДанныеДляСообщенийЧата.registerDataSetObserver(dataSetObserverФрагментСообшенияДЛяКУРСОРА);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //  Block of code to handle errors
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 08.09.2021  Метод Получает ФИО на основании выбраного сотрудника


        public void МетодПолученияПубличногоIDДляСообщенийЧата() {
            try {

                // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ = new Class_GRUD_SQL_Operations(getContext());
                ///
                class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                        " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");


                // TODO: 12.10.2021  Ссылка Менеджер Потоков

                PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getContext());


                ///////
                SQLiteCursor Курсор_ПолучаемПубличныйID = (SQLiteCursor) class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.
                        new GetаFreeData(getContext()).getfreedata(class_grud_sql_operationsПолучаемНаБазуUUIDфиоПолучаемИзТаблицыФИОИМЯ.concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков
                        , Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                if (Курсор_ПолучаемПубличныйID.getCount() > 0) {
////
                    Курсор_ПолучаемПубличныйID.moveToFirst();

                    /////
                    ПубличноеIDПолученныйИзСервлетаДляUUID = Курсор_ПолучаемПубличныйID.getInt(0);
///


                    Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);


                }

                /////////

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }

        }



        private void МетодПодписываемсяОдноразовыйСлушателяСлужбыОбюмена() {
            try{
            Observer      observerОдноразоваяMEssageChtas = new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfosОдноразовая) {
                        // TODO: 23.12.2021
                        workInfosОдноразовая.stream()
                                .filter(workInfoДляФрагментаСообщенияОтЛюдей -> workInfoДляФрагментаСообщенияОтЛюдей.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)
                                .forEachOrdered((workInfoДляФрагментаСообщенияОтЛюдей)->{
                                    //todo
                                    try{
                                    Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  workInfo "
                                            + workInfoДляФрагментаСообщенияОтЛюдей.getState().name());

                                    Long     CallBaskОтWorkManagerОдноразового =
                                            workInfoДляФрагментаСообщенияОтЛюдей.getOutputData()
                                                    .getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);

                                    Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " + CallBaskОтWorkManagerОдноразового);
                                    // TODO: 18.02.2022

                                        if (CallBaskОтWorkManagerОдноразового > 0) {
                                            Message message = new Message();
                                            Bundle bundle=new Bundle();
                                            bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                                            message.setData(bundle);
                                            handlerДляФрагментаMeaasageЧАТ.sendMessage(message);

                                        }


                                    // TODO: 29.09.2021  конец синхрониазции по раписанию
                                } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка  observerОдноразоваяДляФрагментаСообщенияЧата = new Observer<List<WorkInfo>>() {" + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                                });
                }
            };
            // TODO: 18.02.2022
                WorkManager.getInstance(getContext()).
                        getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObserver(observerОдноразоваяMEssageChtas);
                WorkManager.getInstance(getContext()).
                        getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).observeForever(observerОдноразоваяMEssageChtas);
            // TODO: 29.09.2021  конец синхрониазции по раписанию
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка  observerОдноразоваяДляФрагментаСообщенияЧата = new Observer<List<WorkInfo>>() {" + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }








        // TODO: 31.03.2022  ВТОРОЙ СЛУШАТЕЛЬ  ОБШЕЙ СИНХРОНИАЗАЦИИ


        // TODO: 18.10.2021  СИНХРОНИАЗЦИЯ ЧАТА ПО РАСПИСАНИЮ ЧАТ
        private void МетодЗапускаОБЩЕЙСлушателяОбщегоWorkManager() throws ExecutionException, InterruptedException {
            ///
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления

            try {

                // TODO: 27.10.2021


                // TODO: 16.12.2021  --ОДНОРАЗОВАЯ СИНХРОНИАЗЦИЯ СЛУШАТЕЛЬ

                Observer      observerОбщейMessageCHat = new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfОБЩАЯдляЧата) {


                        // TODO: 23.12.2021
                        workInfОБЩАЯдляЧата.stream()
                                .filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                                        СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0)
                                .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                    // TODO: 18.02.2022
                                    try {
                                        Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  СтастусWorkMangerЧата " + СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().name());

                                        Log.d(this.getClass().getName(), " CallBaskОтWorkManagerОдноразового " +
                                                "  СтастусWorkMangerДляФрагментаЧитатьИПисать " + СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().name().toString());


                                        Integer  CallBaskОтWorkManagerОБЩАЯ = СтастусWorkMangerДляФрагментаЧитатьИПисать
                                                .getOutputData().getInt("ReturnPublicAsyncWorkMananger",0);



                                            // TODO: 23.12.2021  ЗАПУСКАЕМ ПОВТРОНУЮ СИНХРОНИАЗУИБЮ
                                            Message message = new Message();
                                            Bundle bundle=new Bundle();
                                            bundle.putString("ОперациЯПрошлаЧат","Успешный обмен данными !!!");
                                            message.setData(bundle);
                                            handlerДляФрагментаMeaasageЧАТ.sendMessage(message);



                                        // TODO: 29.09.2021  конец синхрониазции по раписанию
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        ///метод запись ошибок в таблицу
                                        Log.e(this.getClass().getName(), "Ошибка  Фрагмент Читать и Писать   observerОдноразоваяДляWORKMANAGER = new Observer<List<WorkInfo>>() {" +
                                                " МетодЗапускаСинхрониазцииПоРАсписаниювНезависимостиОтВставкиНовгоСообщения  " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                });


                    }
                };
                    // TODO: 20.02.2022
                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыОбщейСинхронизацииДляЗадачи).removeObserver(observerОбщейMessageCHat);
                    WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыОбщейСинхронизацииДляЗадачи).observeForever(observerОбщейMessageCHat);

                Log.d(this.getClass().getName(), " WorkInfoИнформацияОЗапущенойСлужбеОдноразовая  СтастусWorkMangerЧата " + " CallBaskОтWorkManagerОдноразового ");

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }
// TODO: 29.06.2022 CALL Header
        // TODO: 19.06.2022

        private void  МетодИнициализациHandlerCallBack(){

            try{

                Handler.Callback callback = new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull android.os.Message msg) {
                        Log.d(this.getClass().getName(), " " +
                                " SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " +
                                msg + " msg.getWhen() " + msg.what);


                        progressBarДляЧатаСообщения.setVisibility(View.VISIBLE);

                        // TODO: 23.12.2021  ЗАПУСКАЕМ ПОВТРОНУЮ СИНХРОНИАЗУИБЮ

                        if (КурсорДанныеДляСообщенийЧата != null) {
                            // TODO: 10.02.2022

                            // TODO: 27.12.2021

                            КурсорДанныеДляСообщенийЧата.requery();
                        }


                        if (ЛистВьюДляСообщенийЧата != null) {

                            ЛистВьюДляСообщенийЧата.requestLayout();

                            ЛистВьюДляСообщенийЧата.deferNotifyDataSetChanged();

                            ЛистВьюДляСообщенийЧата.forceLayout();


                            ////
                            if (АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей != null ) {
                                // TODO: 19.02.2022
                                АдаптерДляСообщенийДляФрагментаСообщенияОтРазныхЛюдей.notifyDataSetChanged();
                            }
                        }

                        Bundle bundle=      msg.getData();

                        String ОперацияДанныВЧате=bundle.getString("ОперациЯПрошлаЧат","");

                        msg.getTarget().postDelayed(()->{

                            progressBarДляЧатаСообщения.setVisibility(View.INVISIBLE);
                        },1000);


                        msg.getTarget().removeMessages(1);


                        return true;
                    }
                };
                handlerДляФрагментаMeaasageЧАТ = new Handler(callback);


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


    }
}









