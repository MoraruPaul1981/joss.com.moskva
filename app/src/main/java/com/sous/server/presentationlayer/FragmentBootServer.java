package com.sous.server.presentationlayer;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.AsyncTaskLoader;

import com.sous.server.R;
import com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer.Bi_FragmentBootScannerServer;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Eventbus.MessageScannerServer;
import com.sous.server.businesslayer.Eventbus.ParamentsScannerServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FragmentBootServer extends Fragment {
    private Long version;
    private FragmentManager fragmentManager;
    private Handler handlerGatt  ;
    private AsyncTaskLoader asyncTaskLoaderGatt;
    private Bi_FragmentBootScannerServer biFragmentBootScannerServer;


    private ImageView imageviewbootscanner;

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view);

            imageviewbootscanner = (ImageView) view.findViewById(R.id.id_imageviewbootscanner);


            /////todo Пришли переменные
            fragmentManager = (FragmentManager) ((ActivityServerScanner) getActivity()).fragmentManager;
            version = (Long) ((ActivityServerScanner) getActivity()).version;
            handlerGatt = (Handler) ((ActivityServerScanner) getActivity()).handlerGatt;
            asyncTaskLoaderGatt = (AsyncTaskLoader) ((ActivityServerScanner) getActivity()).asyncTaskLoaderGatt;

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment_boot_scannerserver, container, false);
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        try{
        /*    //TODO:создаем подписку MessageScannerServer */
        EventBus.getDefault().register(this);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());




            /*    //TODO:создаем класс для бизнес логики */
        biFragmentBootScannerServer = new Bi_FragmentBootScannerServer(getContext(), fragmentManager, getActivity(),version);

       МетодЗапускаСервиса(  );

        Log.d(getContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        ContentValues valuesЗаписываемОшибки = new ContentValues();
        valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
        final Object ТекущаяВерсияПрограммы = version;
        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
        new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }


    @Override
    public void onStop() {
        super.onStop();
        try{
        /*    //TODO:создаем подписку MessageScannerServer */
        EventBus.getDefault().unregister(this);
        Log.d(getContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        ContentValues valuesЗаписываемОшибки = new ContentValues();
        valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
        final Object ТекущаяВерсияПрограммы = version;
        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
        new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageScannerServer event) {
        // Do something
        // Do something
        ParamentsScannerServer paramentsScannerServer=   event.paramentsScannerServer;
        Boolean getFladEnableApadaterBTEOtService= paramentsScannerServer.getФлагЗапускаФрагментRecyreView();
        String CurrentTask= paramentsScannerServer.getCurrentTask().trim();
        ConcurrentHashMap getFlagMapOtServiceBte=  paramentsScannerServer.getConcurrentHashMapGattBundle();

        if (CurrentTask.contentEquals("bluetootAdapterEnable")) {
////TODO: В зависимтсто какой результат прищели из службы то сообщаем пользоватю об этом , лии сразу переходим на новой  фрагмент RecyreView
            forwardOtServiceGattEventBus(getFladEnableApadaterBTEOtService);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " getFladEnableApadaterBTEOtService " +getFladEnableApadaterBTEOtService+
                    " getFlagMapOtServiceBte  "  +getFlagMapOtServiceBte);

        } else {
            if (CurrentTask.contentEquals("SuccessDeviceBluetoothAnServerGatt")) {

                //TODO: девай усешно записель состывокальс севреросм и мы его Показываем Пользователю  Фрагмент
                List<String> getListSuccessDerviceOtServerGatt= paramentsScannerServer.getList();

                forwardSuccessDiveciWriteServiceGattEventBus(getListSuccessDerviceOtServerGatt);



                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " getFladEnableApadaterBTEOtService " +getFladEnableApadaterBTEOtService+
                        " getFlagMapOtServiceBte  "  +getFlagMapOtServiceBte);
            }

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " getFladEnableApadaterBTEOtService " +getFladEnableApadaterBTEOtService+
                    " getFlagMapOtServiceBte  "  +getFlagMapOtServiceBte);

        }

        Log.d(getContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                " getFladEnableApadaterBTEOtService " +getFladEnableApadaterBTEOtService+
                 " getFlagMapOtServiceBte  "  +getFlagMapOtServiceBte);


    }


    @SuppressLint("ResourceType")
    private void forwardOtServiceGattEventBus(@NonNull Boolean getReslutOTServiceGatt ) {
        try{
            //TODO: Запускаем Фрагмент

            if (getReslutOTServiceGatt==true ) {

             //   DrawableCompat.setTint(imageviewbootscanner.getDrawable(), ContextCompat.getColor(getContext(), com.google.android.material.R.color.design_default_color_on_primary));
                //imageviewbootscanner.setColorFilter(Color.argb(255, 50, 150, 140));
                imageviewbootscanner.setColorFilter(Color.argb(255, 0, 0, 0));

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  getReslutOTServiceGatt " +getReslutOTServiceGatt);


            } else {

                Toast.makeText(getContext(),"Bluetooth не доступен !!!" ,Toast.LENGTH_LONG).show();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "  getReslutOTServiceGatt " +getReslutOTServiceGatt);

            }


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  getReslutOTServiceGatt " +getReslutOTServiceGatt);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }


    @SuppressLint("ResourceType")
    private void forwardSuccessDiveciWriteServiceGattEventBus(@NonNull List<String> getListSuccessDerviceOtServerGatt) {
        try{
            //TODO: Запускаем Фрагмент
            String getListOtAndroidGattClernt= getListSuccessDerviceOtServerGatt.get(0);


            getListOtAndroidGattClernt=getListOtAndroidGattClernt.replaceAll("^\\[|\\]$", "%%%%");

          List<String> stringStreamGetClientGatt= Stream.of(getListOtAndroidGattClernt.split(",")).collect(Collectors.toList());
            stringStreamGetClientGatt.add(getListSuccessDerviceOtServerGatt.get(1));
            stringStreamGetClientGatt.add(getListSuccessDerviceOtServerGatt.get(2));

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  getListSuccessDerviceOtServerGatt " +getListSuccessDerviceOtServerGatt);



            //Toast t =    Toast.makeText(getContext(),ОтветОтGaTTДляПОльзваотеля ,Toast.LENGTH_LONG) ;


        /*    // Inflate the custom view from XML
            Toast toast = Toast.makeText(getContext(),ОтветОтGaTTДляПОльзваотеля, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, toast.getXOffset() / 2, toast.getYOffset() / 2);

            TextView textView = new TextView(getContext());
            textView.setBackgroundColor(Color.DKGRAY);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(30);
            Typeface typeface = Typeface.create("serif", Typeface.BOLD);
            textView.setTypeface(typeface);
            textView.setPadding(10, 10, 10, 10);
            textView.setText(ОтветОтGaTTДляПОльзваотеля);*/

     /*       toast.setView(textView);
            toast.show();
*/


            //Toast.makeText(getContext(),ОтветОтGaTTДляПОльзваотеля ,Toast.LENGTH_LONG).show();

                //   DrawableCompat.setTint(imageviewbootscanner.getDrawable(), ContextCompat.getColor(getContext(), com.google.android.material.R.color.design_default_color_on_primary));
                //imageviewbootscanner.setColorFilter(Color.argb(255, 50, 150, 140));
               /// imageviewbootscanner.setColorFilter(Color.argb(255, 0, 0, 0));


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  getListSuccessDerviceOtServerGatt " +getListSuccessDerviceOtServerGatt);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }

























    private void МетодЗапускаBootFragment(  ) {
       try{
        //TODO: Запускаем Фрагмент

        handlerGatt.post(new Runnable() {
                             @Override
                             public void run() {
                                 biFragmentBootScannerServer.МетодЗапускаФрагментаСканирования(  new  FragmentServerbleRecyclerView ())    ;
                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                             }
                         }
        );


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        ContentValues valuesЗаписываемОшибки = new ContentValues();
        valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
        final Object ТекущаяВерсияПрограммы = version;
        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
        new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }



    private void МетодЗапускаСервиса(  ) {
        //TODO: Запускаем саму службу GATT  Сервер
        try{
        handlerGatt.post(new Runnable() {
                             @Override
                             public void run() {
                                 biFragmentBootScannerServer.МетодЗапускаСлужбыСканированияСервер();
                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                             }
                         }
        );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("Error", e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }



}




























