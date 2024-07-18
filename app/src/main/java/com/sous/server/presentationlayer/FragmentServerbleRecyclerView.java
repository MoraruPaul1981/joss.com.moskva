package com.sous.server.presentationlayer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentServerRecyreView.Bl_FragmentRecyreViewServer;
import com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentServerRecyreView.Bl_FragmentRecyreViewServerWithData;
import com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentServerRecyreView.InterfaceServerRecyreView;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.R;
import com.sous.server.businesslayer.Eventbus.MessageScannerServer;
import com.sous.server.businesslayer.Eventbus.ParamentsScannerServer;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;


public class FragmentServerbleRecyclerView extends Fragment {

    private FragmentManager fragmentManager;

    private  Long version;

    private  MaterialCardView  maincardView_server_ble_fragment;
    private  RelativeLayout  relativeLayout_server_ble;
    private TabLayout   tabLayout_server_ble;
    private MaterialCardView     card_server_ble_inner;
    private RecyclerView     recyclerview_server_ble;
    private ProgressBar     progressbar_server_ble;
    private  Animation animation;
    private Bl_FragmentRecyreViewServer getblFragmentRecyreViewServer;
    private Bl_FragmentRecyreViewServerWithData getblFragmentRecyreViewServerWithData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fragmentManager = getActivity().getSupportFragmentManager();
            animation  = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator2);

            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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


    @SuppressLint({"RestrictedApi", "MissingPermission"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            maincardView_server_ble_fragment = (MaterialCardView) view.findViewById(R.id.id_maincardView_server_ble_fragment);
            relativeLayout_server_ble    = (RelativeLayout) maincardView_server_ble_fragment.findViewById(R.id.id_relativeLayout_server_ble);
            tabLayout_server_ble = (TabLayout) relativeLayout_server_ble.findViewById(R.id.id_tabLayout_server_ble);
              card_server_ble_inner = (MaterialCardView) relativeLayout_server_ble.findViewById(R.id.id_card_server_ble_inner);
             recyclerview_server_ble = (RecyclerView) relativeLayout_server_ble.findViewById(R.id.id_recyclerview_server_ble);
             progressbar_server_ble = (ProgressBar) relativeLayout_server_ble.findViewById(R.id.id_progressbar_server_ble);
            //tabLayoutScanner = (TabLayout) ((MainActivityNewScanner) getActivity()).tabLayout;


            
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
            view = inflater.inflate(R.layout.fragment_main_server_bte, container, false);

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
        try {
            /*    //TODO:создаем подписку MessageScannerServer */
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            //todo Есди Данные Пришли ТО мы Их получаем от службы
            ConcurrentHashMap<String, ContentValues> mapReceivedFromBootFragmentGatta=        receiveddatafromBootFragmentServer(getArguments());
            // TODO: 18.07.2024  выбор какие даные загружать
            selectingwhichdatatodownload(mapReceivedFromBootFragmentGatta);


            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
    public void onDestroy() {
        try {
        /*    //TODO:создаем подписку MessageScannerServer */
        EventBus.getDefault().unregister(this);
        super.onDestroy();

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

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









    // TODO: 18.07.2024 выбор какие даные загружать

    private void selectingwhichdatatodownload(@NonNull ConcurrentHashMap<String, ContentValues> mapReceivedFromBootFragmentGatta) {
        try{
            if(mapReceivedFromBootFragmentGatta!=null && mapReceivedFromBootFragmentGatta.size()>0){

                // TODO: 17.07.2024 запуск БИзнес логики Fragment Scanner, Когда Есть Данные
                getblFragmentRecyreViewServerWithData=new Bl_FragmentRecyreViewServerWithData( fragmentManager,recyclerview_server_ble, mapReceivedFromBootFragmentGatta,
                        version,maincardView_server_ble_fragment,relativeLayout_server_ble,tabLayout_server_ble,card_server_ble_inner,recyclerview_server_ble,
                        progressbar_server_ble,animation,getContext(),getActivity());

                getblFragmentRecyreViewServerWithData.     getDISCOVERABLE_DURATIONs();
                getblFragmentRecyreViewServerWithData.     setManagerfromRecyclerView();
                getblFragmentRecyreViewServerWithData.     addAdapterServerforRecyreview(  mapReceivedFromBootFragmentGatta);
                getblFragmentRecyreViewServerWithData.     getObserverRecyreView();
                getblFragmentRecyreViewServerWithData.     reBootrecyclerView();


            }else {

                // TODO: 17.07.2024 запуск БИзнес логики Fragment Scanner, Когда Есть Данные НЕТ НЕТ !!!
                getblFragmentRecyreViewServer=new Bl_FragmentRecyreViewServer( fragmentManager,recyclerview_server_ble,
                        version,maincardView_server_ble_fragment,relativeLayout_server_ble,tabLayout_server_ble,card_server_ble_inner,recyclerview_server_ble,
                        progressbar_server_ble,animation,getContext(),getActivity());

                getblFragmentRecyreViewServer.     getDISCOVERABLE_DURATIONs();
                getblFragmentRecyreViewServer.     setManagerfromRecyclerView();
                getblFragmentRecyreViewServer.     addAdapterServerforRecyreview(  mapReceivedFromBootFragmentGatta);
                getblFragmentRecyreViewServer.     getObserverRecyreView();
                getblFragmentRecyreViewServer.     reBootrecyclerView();

            }


            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " mapReceivedFromBootFragmentGatta  " +mapReceivedFromBootFragmentGatta);
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
        try {
            // Do something
            ParamentsScannerServer paramentsScannerServer=   event.paramentsScannerServer;
            Boolean getFladEnableApadaterBTEOtService= paramentsScannerServer.getФлагЗапускаФрагментRecyreView();
            String CurrentTask= paramentsScannerServer.getCurrentTask().trim();
            ConcurrentHashMap getFlagMapOtServiceBte=  paramentsScannerServer.getConcurrentHashMapGattBundle();



            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " getFladEnableApadaterBTEOtService " +getFladEnableApadaterBTEOtService+
                    " getFlagMapOtServiceBte  "  +getFlagMapOtServiceBte);

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








    // TODO: 17.07.2024
    public  ConcurrentHashMap<String, ContentValues> receiveddatafromBootFragmentServer(@NonNull Bundle getArguments) {
        ConcurrentHashMap<String, ContentValues> mapReceivedFromBootFragmentGatta = null;
        try{
            if (getArguments!=null) {
                Bundle bundleFragmentBoottoServerFragment = getArguments;
                Serializable concurrentHashMapSucceesDataOtClient= (Serializable) bundleFragmentBoottoServerFragment.getSerializable("fromFragmentServer");
                mapReceivedFromBootFragmentGatta = (ConcurrentHashMap) concurrentHashMapSucceesDataOtClient;
            }

            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " getArguments " +getArguments+ " mapReceivedFromBootFragmentGatta " +mapReceivedFromBootFragmentGatta);
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
        return  mapReceivedFromBootFragmentGatta;
    }































}





























