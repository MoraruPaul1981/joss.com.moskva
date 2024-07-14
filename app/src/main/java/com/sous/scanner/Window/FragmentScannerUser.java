package com.sous.scanner.Window;

import static android.telephony.SubscriptionManager.PHONE_NUMBER_SOURCE_CARRIER;
import static android.telephony.SubscriptionManager.PHONE_NUMBER_SOURCE_IMS;
import static android.telephony.SubscriptionManager.PHONE_NUMBER_SOURCE_UICC;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.Telephony;
import android.telecom.PhoneAccountHandle;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import android.telephony.emergency.EmergencyNumber;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;
import com.sous.scanner.Services.ServiceClientBLE;
import com.sous.scanner.Errors.SubClassErrors;
import com.sous.scanner.R;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;



import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;


public class FragmentScannerUser extends Fragment {
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private RecyclerView recyclerView;
    private LinearLayout linearLayou;
    private Animation animation;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private Message handler;
    private ServiceClientBLE.LocalBinderСканнер binderСканнер;



    private MutableLiveData<String> mediatorLiveDataGATT;
    private String КлючДляFibaseOneSingnal;
    private Long version = 0l;
    private SharedPreferences preferences;
    private String ДействиеДляСервераGATTОТКлиента;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @SuppressLint({"RestrictedApi", "MissingPermission"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Log.d(this.getClass().getName(), "  onViewCreated  Fragment1_One_Tasks view   " + view);
            recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerViewNewScanner);
            linearLayou = (LinearLayout) view.findViewById(R.id.fragment1scanner);
            Log.d(this.getClass().getName(), " recyclerView " + recyclerView);
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setPrimaryNavigationFragment(fragment);
            Bundle bundle = getArguments();
            КлючДляFibaseOneSingnal = "56bbe169-ea09-43de-a28c-9623058e43a2";
            mediatorLiveDataGATT = new MediatorLiveData();
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_vibrator2);
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            preferences = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

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
            view = inflater.inflate(R.layout.fragment_newscanner1, container, false);
            Log.d(this.getClass().getName(), " onCreateView  viewДляПервойКнопкиHome_Задания  Fragment1_One_Tasks  onCreateView " +
                    "" + view);
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


    @SuppressLint("MissingPermission")
    @Override
    public void onStart() {
        super.onStart();
        try {
            МетодВизуализацииКнопокИБар();
            // TODO: 08.02.2023  Биндинг службы
            МетодБиндингаСканирование();
            МетодИнициализацииRecycleViewДляЗадач();
            МетодКпопкаВозвращениеBACK();
            МетодHandler();
            МетодКпопкаВозвращениеBACK();
            МетодЗаполенияRecycleViewДляЗадач();
            МетодСлушательObserverДляRecycleView();
            МетодПерегрузкаRecyceView();

            // TODO: 20.02.2023 ТЕКСТ КОД
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
        super.onDestroy();
        try {
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

    private void МетодВизуализацииКнопокИБар() {
        try {
            ((MainActivityNewScanner) getActivity()).МетодВидимыеПрограссБарИКнопки();
            Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
    private void МетодЗапускаемПолучениеКлючаOndeSignalFirebase(@NonNull String КлючДляFibaseOneSingnal) {
        try {
            binderСканнер.getService().МетодПолучениеНовогоДляСканеарКлюча_OndeSignal(КлючДляFibaseOneSingnal);
            Log.i(this.getClass().getName(), "   создание МетодЗаполенияФрагмента1 mediatorLiveDataGATT " + mediatorLiveDataGATT + " КлючДляFibaseOneSingnal " + КлючДляFibaseOneSingnal);
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

    void МетодСлушательObserverДляRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
        try {
            myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
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

    // TODO: 02.03.2022 выход

    private void МетодКпопкаВозвращениеBACK()
            throws ExecutionException, InterruptedException {
        try {
            Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеBACK");
            ((MainActivityNewScanner) getActivity()).МетодКнопкаBackExit(new Intent("fragment"));

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
           ArrayList<String> ArrayListСканер = new ArrayList();
            ArrayListСканер.add("Фрагмент Клиента");
            myRecycleViewAdapter = new MyRecycleViewAdapter(ArrayListСканер);
            recyclerView.setAdapter(myRecycleViewAdapter);
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
        private MaterialButton materialButtonКотрольПриход, materialButtonКотрольВыход;
        private MaterialTextView materialTextViewСтатусПоследнегоДействие;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                МетодИнициализацииСканера(itemView);
                Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
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

        private void МетодИнициализацииСканера(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                // TODO: 08.02.2023 кнопка ПРИХОДА с работы
                materialButtonКотрольПриход = itemView.findViewById(R.id.bottomcontrolfragmen1);
                materialButtonКотрольПриход.setText("На Работу");
                materialButtonКотрольПриход.setToggleCheckedStateOnClick(true);
                // TODO: 08.02.2023 кнопка выхода с работы
                materialButtonКотрольВыход = itemView.findViewById(R.id.bottomcontrolfragmen2);
                materialButtonКотрольВыход.setText("С работы");
                // TODO: 08.02.2023 СТАТУС послдная задача
                materialTextViewСтатусПоследнегоДействие = itemView.findViewById(R.id.textView5getstatus);
                // TODO: 09.02.2023 данные из хранилища
                String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT", "Последнее действие");
                ПоследнийСтатусСканера = ПоследнийСтатусСканера.toUpperCase();
                String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера + ": " + ПоследнаяДатаСканера);
                materialTextViewСтатусПоследнегоДействие.setPaintFlags(materialTextViewСтатусПоследнегоДействие.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Log.d(this.getClass().getName(), " отработоатл  МетодИнициализацииСканера materialButtonКотроль " + materialButtonКотрольПриход);
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

    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList ArrayListДанныеОтСканироваиниеДивайсов;

        public MyRecycleViewAdapter(@NotNull ArrayList ArrayListДанныеОтСканироваиниеДивайсов) {
            this.ArrayListДанныеОтСканироваиниеДивайсов = ArrayListДанныеОтСканироваиниеДивайсов;
            if (ArrayListДанныеОтСканироваиниеДивайсов.size() > 0) {
                Log.i(this.getClass().getName(), " ArrayListДанныеОтСканироваиниеДивайсов  " + ArrayListДанныеОтСканироваиниеДивайсов.size());
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
            Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                Log.d(this.getClass().getName(), " ArrayListДанныеОтСканироваиниеДивайсов " + ArrayListДанныеОтСканироваиниеДивайсов);
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
                МетодЗапускаемСлужбуGATTCleintКлик(holder);
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
                holder.materialButtonКотрольПриход.startAnimation(animation);
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
        private void МетодЗапускаемСлужбуGATTCleintКлик(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                        +new Date().toLocaleString() + " holder " +holder);
                // TODO: 19.02.2023 Второе Действие
                RxView.clicks(holder.materialButtonКотрольВыход)
                        .throttleFirst(20, TimeUnit.SECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new io.reactivex.rxjava3.core.Observer<Unit>() {
                                            @Override
                                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " время " +new Date().toLocaleString() );
                                            }
                                            @Override
                                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Unit unit) {

                                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " время " +new Date().toLocaleString() +
                                                        " disposable[0] " );
                                                    ДействиеДляСервераGATTОТКлиента = "с работы";
                                                    МетодЗапускаGattСервера(holder.materialButtonКотрольВыход, holder, holder.materialButtonКотрольВыход);
                                                    // TODO: 20.02.2023 Принудитльеное Разрыв Клиента с Сервом GATT
                                                //    МетодПринудительноРазрываемвязисGatt(ДействиеДляСервераGATTОТКлиента);
                                                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                                                    // TODO: 22.02.2023
                                            }
                                            @Override
                                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " время " +new Date().toLocaleString() );
                                            }
                                            @Override
                                            public void onComplete() {

                                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " время " +new Date().toLocaleString() );
                                            }
                                        });
                // TODO: 22.02.2023 для второй кнопки
                RxView.clicks(holder.materialButtonКотрольПриход)
                        .throttleFirst(20, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.rxjava3.core.Observer<Unit>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }
                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Unit unit) {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                                    ДействиеДляСервераGATTОТКлиента = "на работу";
                                    МетодЗапускаGattСервера(holder.materialButtonКотрольПриход, holder, holder.materialButtonКотрольПриход);
                                    // TODO: 20.02.2023 Принудитльеное Разрыв Клиента с Сервом GATT
                                  //  МетодПринудительноРазрываемвязисGatt(ДействиеДляСервераGATTОТКлиента);
                                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " время " + new Date().toLocaleString());
                            }
                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }

                            @Override
                            public void onComplete() {
                                Log.i(this.getClass().getName(),  "  RxView.clicks " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + " время " +new Date().toLocaleString() );
                            }
                        });
      /*          holder.materialButtonКотрольПриход.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ДействиеДляСервераGATTОТКлиента = "на работу";
                        МетодЗапускаGattСервера(v, holder, holder.materialButtonКотрольПриход);
                        // TODO: 20.02.2023 Принудитльеное Разрыв Клиента с Сервом GATT
                      ///  МетодПринудительноРазрываемвязисGatt(ДействиеДляСервераGATTОТКлиента);
                        Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                    }
                });*/
                Log.i(this.getClass().getName(), "   holder " + holder);
                // TODO: 19.02.2023 ВТорое действие 
        /*        holder.materialButtonКотрольВыход.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ДействиеДляСервераGATTОТКлиента = "с работы";
                        МетодЗапускаGattСервера(v, holder, holder.materialButtonКотрольВыход);
                        // TODO: 20.02.2023 Принудитльеное Разрыв Клиента с Сервом GATT
                        МетодПринудительноРазрываемвязисGatt(ДействиеДляСервераGATTОТКлиента);
                        Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                    }
                });*/
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

        private void МетодЗапускаGattСервера(@NonNull View v, @NonNull MyViewHolder holder, MaterialButton materialButton) {
            try {
                // TODO: 20.02.2023  слушатель Клиета GATT
                МетодBackСлушательGATTОтСервера(v, holder, ДействиеДляСервераGATTОТКлиента, materialButton);
                // TODO: 20.02.2023 Запуск Клиента Gatt Сервреа Чрез БИндинг
                МетодЗапускКлиентаGattЧерезБиндинг(ДействиеДляСервераGATTОТКлиента);
                Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() + " mediatorLiveDataGATT " + mediatorLiveDataGATT);
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

        private void МетодBackСлушательGATTОтСервера(@NonNull View v, @NonNull MyViewHolder holder
                , @NonNull String ДействиеДляСервераGATTОТКлиента, @NonNull MaterialButton materialButtonКакоеДействие) {
            try {
                v.startAnimation(animation);
                mediatorLiveDataGATT = new MutableLiveData<>();
                Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                // TODO: 30.01.2023 ВТОРАЯ ЧАСТЬ ОТВЕТ ПРОВЕТ GATT SERVER/CLIENT
                LifecycleOwner lifecycleOwner = getActivity();
                lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });
                mediatorLiveDataGATT.observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(@NonNull  String ОтветОтСерврера) {
                        if (mediatorLiveDataGATT.getValue() != null) {
                            Log.i(this.getClass().getName(), "   создание МетодЗаполенияФрагмента1 mediatorLiveDataGATT " + mediatorLiveDataGATT);
                            // TODO: 24.01.2023  показываем поозователю Статуса
                            Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            switch (mediatorLiveDataGATT.getValue().toString()) {
                                case "SERVER#SERVER#SouConnect":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Коннект...");
                                    });
                                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "SERVER#SousAvtoBOND_BONDING":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Сопрящение...");
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "GATTCLIENTProccessing":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("В процессе...");
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "SERVER#SERVER#SousAvtoReetBoot":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Соед. перегружено !!!");//
                                        Log.i(this.getClass().getName(), "   mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "SERVER#SousAvtoDONTDIVICE":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Нет  сопряжение !!!");
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "SERVER#SousAvtoDONTBLEManager":
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText("Нет Bluetooth  !!!");
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                case "SERVER#SousAvtoSuccess":
                                    // TODO: 07.02.2023 Успешный статус
                                    handler.getTarget().post(() -> {
                                        materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        materialButtonКакоеДействие.startAnimation(animation);
                                        materialButtonКакоеДействие.setText("Успешно !!!");
                                        v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
                                        // TODO: 07.02.2023  записываем смены статуса
                                        МетодЗаписываемСтатусРаботысGATT(ДействиеДляСервераGATTОТКлиента, new Date().toLocaleString());
                                        // TODO: 08.02.2023 показыввем клиент смененый статус
                                        String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT", "");
                                        String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                                        holder.materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера
                                                + "\n" + ПоследнаяДатаСканера);
                                        holder.materialTextViewСтатусПоследнегоДействие.setTextColor(Color.parseColor("#949796"));
                                        holder.materialTextViewСтатусПоследнегоДействие.forceLayout();
                                        holder.materialTextViewСтатусПоследнегоДействие.refreshDrawableState();
                                        // TODO: 11.02.2023  
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                                // TODO: 11.02.2023 ДРУГИЕ ОТВЕТЫ
                                case "SERVER#SousAvtoERROR":
                                    handler.getTarget().post(() -> {
                                        v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                        materialButtonКакоеДействие.setText("Не Успешно !!!");
                                        МетодЗаписываемСтатусРаботысGATT(ДействиеДляСервераGATTОТКлиента, new Date().toLocaleString());
                                        // TODO: 08.02.2023 показыввем клиент смененый статус
                                        String ПоследнийСтатусСканера = preferences.getString("СменаСтатусРАботыКлиентасGATT",
                                                "");
                                        String ПоследнаяДатаСканера = preferences.getString("СменаДАтаРАботыGATT", "");
                                        holder.materialTextViewСтатусПоследнегоДействие.setText(ПоследнийСтатусСканера
                                                + "\n" + ПоследнаяДатаСканера);
                                        holder.materialTextViewСтатусПоследнегоДействие.setTextColor(Color.RED);
                                        holder.materialTextViewСтатусПоследнегоДействие.forceLayout();
                                        holder.materialTextViewСтатусПоследнегоДействие.refreshDrawableState();
                                        handler.getTarget().postDelayed(() -> {
                                            materialButtonКакоеДействие.setText(ДействиеДляСервераGATTОТКлиента);
                                        }, 1500);
                                    });
                                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                            + new Date().toLocaleString() + " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue());
                                    break;
                            }
                        }
                    }
                });
                Log.i(this.getClass().getName(), "onStart() " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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

        private void МетодЗапускКлиентаGattЧерезБиндинг(@NonNull String ДействиеДляСервераGATTОТКлиента) {
            try {
            // TODO: 06.12.2022 запускаем сканирование клиента
            binderСканнер.getService().МетодКлиентЗапускСканера(handler.getTarget(), getActivity(),  mediatorLiveDataGATT, ДействиеДляСервераGATTОТКлиента);
                Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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

        private void МетодПринудительноРазрываемвязисGatt(@NonNull String ДействиеДляСервераGATTОТКлиента) {
            try {
                handler.getTarget().postDelayed(() -> {
                    binderСканнер.getService().МетодВыключениеКлиентаGatt();
                    Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString()
                            + "ДействиеДляСервераGATTОТКлиента " + ДействиеДляСервераGATTОТКлиента);
                }, 10000);
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

        private void МетодЗаписываемСтатусРаботысGATT(@NonNull String СтатусРаботыGatt, @NonNull String ДатаСменыСтатуса) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("СменаСтатусРАботыКлиентасGATT", СтатусРаботыGatt);
            editor.putString("СменаДАтаРАботыGATT", ДатаСменыСтатуса);
            editor.commit();
            Log.i(this.getClass().getName(), " смена статуса  создание МетодЗаписываемСтатусРаботысGATT " + mediatorLiveDataGATT);
            try {
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

    void МетодHandler() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), "msg " + msg);
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
                return true;
            }
        }).obtainMessage();
    }


    // TODO: 29.11.2022 служба сканирования
    private void МетодБиндингаСканирование() {
        try {
            ServiceConnection connectionСканирование = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        binderСканнер = (ServiceClientBLE.LocalBinderСканнер) service;
                        if (binderСканнер.isBinderAlive()) {
                            Log.i(getContext().getClass().getName(), "    onServiceConnected  binderСогласованияbinderМатериалы.isBinderAlive()"
                                    + binderСканнер.isBinderAlive());
                            binderСканнер.linkToDeath(new IBinder.DeathRecipient() {
                                @Override
                                public void binderDied() {
                                    Log.i(this.getClass().getName(), "linkToDeath" + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                                            " binderСканнер.isBinderAlive() " + binderСканнер.isBinderAlive());
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        ContentValues valuesЗаписываемОшибки = new ContentValues();
                        valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
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
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(getContext().getClass().getName(), "    onServiceDisconnected  binderСканнер.isBinderAlive()" + binderСканнер.isBinderAlive());
                        binderСканнер = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentБиндингсСлужбойСканирования = new Intent(getContext(), ServiceClientBLE.class);
            intentБиндингсСлужбойСканирования.setAction("com.scannerforble");
            getContext().bindService(intentБиндингсСлужбойСканирования, Context.BIND_AUTO_CREATE, Executors.newSingleThreadExecutor(), connectionСканирование);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки = new ContentValues();
            valuesЗаписываемОшибки.put("НазваниеОбрабоатываемойТаблицы", "ErrorDSU1");
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



    @SuppressLint("MissingPermission")
    private ArrayList<String> МетодЗаполенияДаннымиКлиентаДЛяGAtt() {
        final ArrayList<String>[] linkedHashMapДанныеКлиентаДляGATT = new ArrayList[]{null};
        try {
            SubscriptionManager tMgr = (SubscriptionManager) getActivity().getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            linkedHashMapДанныеКлиентаДляGATT[0] = new ArrayList<>();
            linkedHashMapДанныеКлиентаДляGATT[0].add(ДействиеДляСервераGATTОТКлиента);
            final List<SubscriptionInfo> activeSubscriptionInfoList = tMgr.getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList.size()>0) {
                activeSubscriptionInfoList.forEach(new Consumer<SubscriptionInfo>() {
                    @Override
                    public void accept(SubscriptionInfo subscriptionInfo) {
                        linkedHashMapДанныеКлиентаДляGATT[0].add(String.valueOf(subscriptionInfo.getCarrierId()));
                        linkedHashMapДанныеКлиентаДляGATT[0].add(String.valueOf(subscriptionInfo.getIconTint()));
                        Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                                + " subscriptionInfo " +subscriptionInfo);
                    }
                });
            }else {
                linkedHashMapДанныеКлиентаДляGATT[0].add(String.valueOf("Нет SIM -карты"));
                linkedHashMapДанныеКлиентаДляGATT[0].add(String.valueOf("Нет SIM -карты"));
                Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                        + " activeSubscriptionInfoList.size() " +activeSubscriptionInfoList.size());
            }
            Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                    + " linkedHashMapДанныеКлиентаДляGATT " + linkedHashMapДанныеКлиентаДляGATT[0] +  "activeSubscriptionInfoList " +activeSubscriptionInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            ContentValues valuesЗаписываемОшибки=new ContentValues();
            valuesЗаписываемОшибки.put("Error",e.toString().toLowerCase());
            valuesЗаписываемОшибки.put("Klass",this.getClass().getName());
            valuesЗаписываемОшибки.put("Metod",Thread.currentThread().getStackTrace()[2].getMethodName());
            valuesЗаписываемОшибки.put("LineError",   Thread.currentThread().getStackTrace()[2].getLineNumber());
            final Object ТекущаяВерсияПрограммы = version;
            Integer   ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            valuesЗаписываемОшибки.put("whose_error",ЛокальнаяВерсияПОСравнение);
            new SubClassErrors(getContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return linkedHashMapДанныеКлиентаДляGATT[0];
    }
}

//todo тест
class ТестКлассСканнер {



}































