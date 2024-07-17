package com.sous.server.businesslayer.BI_presentationlayer.bl_FragmentServerRecyreView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;
import com.sous.server.R;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.presentationlayer.FragmentServerbleRecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Bl_FragmentRecyreViewServer {

    private FragmentManager fragmentManager;
    private  MyRecycleViewAdapterServer myRecycleViewAdapterServer;
    private   MyViewHolder myViewHolder;
    private RecyclerView recyclerView;
    // TODO: 17.07.2024
    private ConcurrentHashMap<String, ContentValues> concurrentHashMapReceivedFromBootFragmentGatta;
    private  Long version;

    private MaterialCardView maincardView_server_ble_fragment;
    private RelativeLayout relativeLayout_server_ble;
    private TabLayout tabLayout_server_ble;
    private MaterialCardView     card_server_ble_inner;
    private RecyclerView     recyclerview_server_ble;
    private ProgressBar progressbar_server_ble;
    private Animation animation;
    private Context context;

    private Activity activity;

    public Bl_FragmentRecyreViewServer(FragmentManager fragmentManager,
                                       RecyclerView recyclerView,
                                       ConcurrentHashMap<String, ContentValues> concurrentHashMapReceivedFromBootFragmentGatta,
                                       Long version,
                                       MaterialCardView maincardView_server_ble_fragment,
                                       RelativeLayout relativeLayout_server_ble,
                                       TabLayout tabLayout_server_ble,
                                       MaterialCardView card_server_ble_inner,
                                       RecyclerView recyclerview_server_ble,
                                       ProgressBar progressbar_server_ble,
                                       Animation animation,
                                       Context context,
                                       Activity activity) {
        // TODO: 17.07.2024  
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.concurrentHashMapReceivedFromBootFragmentGatta = concurrentHashMapReceivedFromBootFragmentGatta;
        this.version = version;
        this.maincardView_server_ble_fragment = maincardView_server_ble_fragment;
        this.relativeLayout_server_ble = relativeLayout_server_ble;
        this.tabLayout_server_ble = tabLayout_server_ble;
        this.card_server_ble_inner = card_server_ble_inner;
        this.recyclerview_server_ble = recyclerview_server_ble;
        this.progressbar_server_ble = progressbar_server_ble;
        this.animation = animation;
        this.context = context;
        this.activity = activity;
    }


    // TODO: 17.07.2024
    public void receiveddatafromBootFragmentServer(@NonNull Bundle getArguments) {
        try{
            Bundle bundleFragmentBoottoServerFragment = getArguments;
            Serializable concurrentHashMapSucceesDataOtClient= (Serializable) bundleFragmentBoottoServerFragment.getSerializable("fromFragmentServer");
            concurrentHashMapReceivedFromBootFragmentGatta= (ConcurrentHashMap) concurrentHashMapSucceesDataOtClient;

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }
    @SuppressLint("MissingPermission")
    public void getDISCOVERABLE_DURATIONs() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
       activity. startActivity(discoverableIntent);
        // TODO: 17.07.2024
        Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }
    void getObserverRecyreView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            myRecycleViewAdapterServer.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    try {
                        Log.d(this.getClass().getName(), "onChanged ");
                        //TODO
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
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    super.onItemRangeChanged(positionStart, itemCount);
                    // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        //TODO
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
                public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                    super.onItemRangeChanged(positionStart, itemCount, payload);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeChanged ");
                        //TODO
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
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeInserted ");
                        //TODO
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
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    super.onItemRangeRemoved(positionStart, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "onItemRangeRemoved ");
                        //TODO
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
                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                    try {
                        Log.d(this.getClass().getName(), "     onItemRangeMoved ");
                        //TODO
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
            });
            //TODO
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

    // TODO: 04.03.2022 прозвомжность Заполения RecycleView
    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  ARRAYJSONПолучениеДанныхОт1с  ");
            myRecycleViewAdapterServer = new FragmentServerbleRecyclerView.MyRecycleViewAdapterServer(concurrentHashMapReceivedFromBootFragmentGatta);
            recyclerView.setAdapter(myRecycleViewAdapterServer);
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerView);
            //TODO
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




    // TODO: 04.03.2022 прозвомжность инициализации RecycleView
    void МетодИнициализацииRecycleViewДляЗадач() {
        try {
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext())
            // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 recyclerView   " + recyclerView);
            //TODO
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
    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialButton materialButtonСервер;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                initServerBle(itemView);

                Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "  itemView " +itemView);

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

        private void initServerBle(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                materialButtonСервер = itemView.findViewById(R.id.bottomcontrolfragmen1);
                Log.d(this.getClass().getName(), " отработоатл  МетодИнициализацииСканера materialButtonКотроль " + materialButtonСервер);
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

    // TODO: 17.07.2024


    class MyRecycleViewAdapterServer extends RecyclerView.Adapter<MyViewHolder> {
        private ConcurrentHashMap<String,ContentValues> concurrentHashMapReceivedFromBootFragmentGatta;

        public MyRecycleViewAdapterServer(@NotNull ConcurrentHashMap<String,ContentValues>  concurrentHashMapReceivedFromBootFragmentGatta) {
            this.concurrentHashMapReceivedFromBootFragmentGatta = concurrentHashMapReceivedFromBootFragmentGatta;
            //TODO
            Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
            try {
                Log.d(getContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " concurrentHashMapReceivedFromBootFragmentGatta " + concurrentHashMapReceivedFromBootFragmentGatta);


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
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
            return super.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

            recyclerView.removeAllViews();

            recyclerView.getRecycledViewPool().clear();
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
            try {
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
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
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = null;
            try {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_scannerbuetoot_fragment1_bottom, parent, false);//todo old simple_for_takst_cardview1
                myViewHolder = new MyViewHolder(view);
                Log.i(this.getClass().getName(), "   myViewHolder" + myViewHolder);
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
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder);
                МетодКликGattServer(holder);
                МетодLiveData(holder);
                МетодАнимации(holder);
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

        private void МетодАнимации(MyViewHolder holder) {
            try {
                holder.materialButtonСервер.startAnimation(animationServer);
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder);
                //TODO
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


        ///todo первый метод #1
        private void МетодКликGattServer(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "   holder " + holder);///*/
                holder.materialButtonСервер.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(this.getClass().getName(), "   запуск сервера МетодЗапускGattServer " + v);
                        // Snackbar.make(v, "Сервер  Bluetooth ....",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                        Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));

                    }
                });
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

        ///todo первый метод #3
        private void МетодLiveData(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "   holder    МетодLiveData  " + holder);
                LifecycleOwner lifecycleOwner = getActivity();
                lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });
                mutableLiveDataGATTServer.observe(lifecycleOwner, new Observer<Bundle>() {
                    @Override
                    public void onChanged(Bundle ОтветОтСерврера) {
                        if (mutableLiveDataGATTServer.getValue() != null) {
                            Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            Log.i(this.getClass().getName(), "   создание МетодЗаполенияФрагмента1 mutableLiveDataGATTServer " + mutableLiveDataGATTServer);
                            Bundle bundleОтСервера = mutableLiveDataGATTServer.getValue();
                            String СтатусОтСервера = bundleОтСервера.getString("Статус");
                            // TODO: 24.01.2023  показываем поозователю Статуса
                            switch (СтатусОтСервера) {
                                case "SERVERGATTConnectiong":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.setText("Коннект...");
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                                        Log.i(this.getClass().getName(), "  Коннект... на сервере ответ КЛИЕНТУ  ");//
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                    });
                                    break;
                                case "SERVERGATTRUNNIGSTARTING":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.startAnimation(animationServer);
                                        holder.materialButtonСервер.setText("Работает..."
                                                + "\n"
                                                + "\n" + "пинг: " + linkedКолПодкСерверу.size());
                                        Log.i(this.getClass().getName(), "  Работает... на сервере ответ КЛИЕНТУ  "
                                                + "\n" + "пинги: " + linkedКолПодкСерверу.size());
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                        handler.postDelayed(()->{
                                            progressBarДЛяСервера.setVisibility( View.VISIBLE);;
                                        },1500);

                                    });
                                    break;
                                case "SERVER#SousAvtoStartingGPS":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.startAnimation(animationServer);
                                        holder.materialButtonСервер.setText("Включение GPS..."
                                                + "\n"
                                                + "\n" + "пинг: " + linkedКолПодкСерверу.size());
                                        Log.i(this.getClass().getName(), "  Работает GPS ... на сервере ответ КЛИЕНТУ  "
                                                + "\n" + "пинги: " + linkedКолПодкСерверу.size());
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        handler.postDelayed(() -> {
                                            holder.materialButtonСервер.setText("Работает..."
                                                    + "\n"
                                                    + "\n" + "пинг: " + linkedКолПодкСерверу.size());
                                        },1500 );
                                    });
                                    break;
                                case "SERVERGATTRUNNIGERRORS":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.setText("Не работает !!!");
                                        Log.i(this.getClass().getName(), "   Не работает на сервере ответ КЛИЕНТУ  ");
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                        progressBarДЛяСервера.setVisibility( View.INVISIBLE);
                                    });
                                    break;
                                case "SERVER#SousAvtoNULL":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.setText("Ошибка нет данных !!!");
                                        Log.i(this.getClass().getName(), "    Ошибка нет данных !!! на сервере ответ КЛИЕНТУ  ");
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                    });
                                    break;
                                case "SERVERGATTRUNNIGReBOOT":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.setText("Перезапуск...");
                                        Log.i(this.getClass().getName(), "   Перезапуск на сервере ответ КЛИЕНТУ  ");
                                        holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                        progressBarДЛяСервера.setVisibility( View.INVISIBLE);
                                    });
                                    break;
                                case "SERVER#SousAvtoERROR":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.startAnimation(animationServer);
                                        handler.post(() -> {
                                            holder.materialButtonСервер.setText(bundleОтСервера.getString("ОтветКлиентуВсатвкаВБАзу") +
                                                    "\n" +
                                                    "Девайс: " +
                                                    "\n" + bundleОтСервера.getString("Дивайс"));
                                            // TODO: 09.02.2023 пинг
                                            linkedКолПодкСерверу.add(bundleОтСервера.getString("ОтветКлиентуВсатвкаВБАзу"));
                                            linkedКолПодкСерверу = linkedКолПодкСерверу.stream().distinct().collect(Collectors.toList());
                                            Log.i(this.getClass().getName(), "  Ошибка на ответ КЛИЕНТУ  ");
                                            v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                                            holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        });
                                        handler.postDelayed(() -> {
                                            holder.materialButtonСервер.setText("Работает..."
                                                    + "\n"
                                                    + "\n" + "пинг: " + linkedКолПодкСерверу.size());
                                        }, 1500);
                                    });
                                    break;
                                case "SERVER#SousAvtoSuccess":
                                    handler.post(() -> {
                                        holder.materialButtonСервер.startAnimation(animationServer);
                                        handler.post(() -> {
                                            holder.materialButtonСервер.setText(bundleОтСервера.getString("ОтветКлиентуВсатвкаВБАзу"));
                                            // TODO: 09.02.2023 пинг
                                            linkedКолПодкСерверу.add(bundleОтСервера.getString("ОтветКлиентуВсатвкаВБАзу"));
                                            linkedКолПодкСерверу = linkedКолПодкСерверу.stream().distinct().collect(Collectors.toList());
                                            Log.i(this.getClass().getName(), "   Успех на сервере ответ КЛИЕНТУ  ");
                                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                            holder.materialButtonСервер.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#03A9F4")));
                                        });
                                        handler.postDelayed(() -> {
                                            holder.materialButtonСервер.setText("Работает..."
                                                    + "\n"
                                                    + "\n" + "пинг: " + linkedКолПодкСерверу.size());
                                        }, 1500);
                                    });
                                    break;
                            }
                            relativeLayoutСервер.forceLayout();
                            relativeLayoutСервер.refreshDrawableState();
                        }
                    }
                });

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
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);

        }

        @Override
        public int getItemCount() {
            try {
                Log.d(this.getClass().getName(), "ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
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
            return ArrayListДанныеОтСканироваиниеДивайсов.size();
        }
    }



    //TODO метод делает callback с ответом на экран
    private void МетодПерегрузкаRecyceView() {
        try {
            recyclerView.requestLayout();
            recyclerView.forceLayout();
            recyclerView.refreshDrawableState();
            linearLayou.requestLayout();
            linearLayou.forceLayout();
            linearLayou.refreshDrawableState();
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
