package com.sous.server.presentationlayer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;

import com.sous.server.R;
import com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentBootScannerServer.BiFragmentBootScannerServer;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.eventbus.MessageScannerServer;
import com.sous.server.businesslayer.eventbus.ParamentsScannerServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;


public class FragmentBootServer extends Fragment {
    private Long version;
    private FragmentTransaction getfragmentTransaction;
    private Handler handlerGatt  ;
    private AsyncTaskLoader asyncTaskLoaderGatt;
    private     BiFragmentBootScannerServer biFragmentBootScannerServer;
    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view);
            getfragmentTransaction = (FragmentTransaction) ((ActivityServerScanner) getActivity()).getTransactionscanner;
            version = (Long) ((ActivityServerScanner) getActivity()).version;
            handlerGatt = (Handler) ((ActivityServerScanner) getActivity()).handlerGatt;
            asyncTaskLoaderGatt = (AsyncTaskLoader) ((ActivityServerScanner) getActivity()).asyncTaskLoaderGatt;
            Log.i(this.getClass().getName(), "fragmentTransaction " + getfragmentTransaction
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());

        /*    //TODO:создаем класс для бизнес логики */
            biFragmentBootScannerServer = new BiFragmentBootScannerServer(getContext(), getfragmentTransaction, getActivity(),version);

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
    public void onStart() {
        super.onStart();
        /*    //TODO:создаем подписку MessageScannerServer */
        EventBus.getDefault().register(this);

        МетодЗапускаBootFragment(  );

      ///  МетодЗапускаСервиса(  );


        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
    }


    @Override
    public void onStop() {
        super.onStop();
        /*    //TODO:создаем подписку MessageScannerServer */
        EventBus.getDefault().unregister(this);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
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



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageScannerServer event) {
        // Do something
        ParamentsScannerServer paramentsScannerServer=   event.paramentsScannerServer;
       String s= paramentsScannerServer.getS1();
       Integer i= paramentsScannerServer.getI1();
        Toast.makeText(getContext(),s.toString()+""+i.toString(),Toast.LENGTH_LONG).show();
    }






    private void МетодЗапускаBootFragment(  ) {
       try{
        //TODO: Запускаем Фрагмент

           //biFragmentBootScannerServer.МетодЗапускаФрагментаСканирования(   )    ;


/*
        handlerGatt.post(new Runnable() {
                             @Override
                             public void run() {
                                 biFragmentBootScannerServer.МетодЗапускаФрагментаСканирования(  getfragmentTransaction)    ;
                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                             }
                         }
        );
*/

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




























