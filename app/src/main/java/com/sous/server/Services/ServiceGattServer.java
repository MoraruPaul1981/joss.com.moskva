package com.sous.server.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;


import com.sous.server.Databases.CREATE_DATABASEServer;
import com.sous.server.Errors.SubClassErrors;


import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableWindow;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceGattServer extends IntentService {
    private SQLiteDatabase sqLiteDatabase;
    private CREATE_DATABASEServer createDatabaseScanner;
    public LocalBinderСерверBLE binder = new LocalBinderСерверBLE();

    private String TAG;

    private BluetoothGattServer server;
    private Long version = 0l;
    private  Activity activity;

    private  BluetoothManager bluetoothManagerServer;
    private  BluetoothAdapter bluetoothAdapter ;
    public ServiceGattServer() {
        super(
                "ServiceGattServer");
    }
    private MutableLiveData<Bundle> mutableLiveDataGATTServer;
    private List<Address> addressesgetGPS;
    private UUID uuidКлючСервераGATTЧтениеЗапись;
    private Bundle bundleСервер;

    private FusedLocationProviderClient fusedLocationClientGoogle;
    private Location lastLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            this.createDatabaseScanner = new CREATE_DATABASEServer(getApplicationContext());
            this.sqLiteDatabase = createDatabaseScanner.getССылкаНаСозданнуюБазу();
            TAG = getClass().getName().toString();
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
            bundleСервер = new Bundle();
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

    @SuppressLint("MissingPermission")
    private void МетодИницилиазцииGpsGoogle() {
        try {
            fusedLocationClientGoogle = LocationServices.getFusedLocationProviderClient(this);
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                Task<Location> locationResult = fusedLocationClientGoogle.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
                    @NonNull
                    @Override
                    public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        return null;
                    }

                    @Override
                    public boolean isCancellationRequested() {
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        return false;
                    }
                });
            locationResult.addOnCompleteListener( new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()==true && task.isComplete() ==true) {
                          lastLocation=task.getResult();
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+  "lastLocation " +lastLocation +
                                " task.isSuccessful() " +task.isSuccessful()+  "task.isComplete() "+task.isComplete());
                        // TODO: 21.02.2023 получаем Сами ДАнные от Location  полученого
                        МетодПолучениеЛокацииGPS();
                        bundleСервер.clear();
                        bundleСервер.putString("Статус", "SERVER#SousAvtoStartingGPS");
                        getApplicationContext().getMainExecutor().execute (()->{
                            mutableLiveDataGATTServer.setValue(bundleСервер);
                        }); ;
                    }
                    Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                }
            });

            locationResult.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
                    new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                }
            });

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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }


    public class LocalBinderСерверBLE extends Binder {
        public ServiceGattServer getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceGattServer.this;
        }

        public void linkToDeath(DeathRecipient deathRecipient) {
            deathRecipient.binderDied();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return binder;

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        try {
            if (rootIntent.getAction().equalsIgnoreCase("КлиентЗакрываетСлужбу")) {
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                stopSelf();
            }
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
        Log.d(getApplicationContext().getClass().getName(), "\n"
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" + "\n" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " + "\n" +
                    " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            // МетодЗапускаОбщиеКоды(getApplicationContext(),intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.attachBaseContext(newBase);
    }

    // TODO: 30.11.2022 сервер СКАНИРОВАНИЯ
    @SuppressLint({"MissingPermission", "NewApi"})
    public synchronized   void МетодГлавныйЗапускGattServer(@NonNull Handler handler,
                                                            @NonNull Activity activity,
                                             @NonNull MutableLiveData<Bundle>mutableLiveDataGATTServer) {
        this.mutableLiveDataGATTServer=mutableLiveDataGATTServer;
        this.activity=activity;
        // TODO: 08.12.2022 уснатавливаем настройки Bluetooth
        Log.w(this.getClass().getName(), " SERVER  МетодГлавныйЗапускGattServer  bluetoothManager  " + "server "+server);
        try {
            МетодПереполучениеBlutoochМенеджера();
            if (bluetoothAdapter!=null) {
                // TODO: 21.02.2023 Метод Перегрузки Сервера
                МетодПерегрузкиСервераGatt(mutableLiveDataGATTServer);
                // TODO: 14.02.2023    ЗарускаемСамСервер
                МетодЗапускаСервера();
                Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+
                         " bluetoothManagerServer " +bluetoothManagerServer);
            }else
            {
                Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() +
                         "bluetoothManagerServer " +bluetoothManagerServer);
            }
            Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

    @SuppressLint("MissingPermission")
    private void МетодПереполучениеBlutoochМенеджера() {
        try{
        bluetoothManagerServer = (BluetoothManager) activity.getSystemService(Context.BLUETOOTH_SERVICE);
            if (bluetoothManagerServer!=null) {
                bluetoothAdapter = bluetoothManagerServer.getAdapter();
                Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
            }
            if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled() == false) {
                bluetoothAdapter.enable();
            }

                // TODO: 13.02.2023  ИНИЦИАЛИЗАЦИИ GPS через Google
                МетодИницилиазцииGpsGoogle();
                List<BluetoothDevice>   bluetoothDeviceListGattServer=    bluetoothManagerServer.getConnectedDevices(BluetoothProfile.GATT_SERVER);
                Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() +
                        " bluetoothDeviceListGattServer "+bluetoothDeviceListGattServer);
        }
            Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() +
                     " bluetoothAdapter "+bluetoothAdapter);
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }

    @SuppressLint("MissingPermission")
    private void МетодПерегрузкиСервераGatt(@NonNull MutableLiveData<Bundle> mutableLiveDataGATTServer) {
        try{
        if(server!=null){
            getApplicationContext().getMainExecutor().execute(()->{
                bundleСервер.clear();
                bundleСервер.putString("Статус","SERVERGATTRUNNIGReBOOT");
                mutableLiveDataGATTServer.setValue(bundleСервер);
                Log.i(this.getClass().getName(),  " МетодГлавныйЗапускGattServer SERVERGATTRUNNIGReBOOT" +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );

            });
            server.close();
        }
        Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }


    // TODO: 08.12.2022 Метод Сервер
    @SuppressLint("MissingPermission")
    public  void МетодЗапускаСервера() {
        try {

            Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  " + "\n+" +
                    "" + binder.isBinderAlive() + " date " + new Date().toString().toString() + "" +
                    "\n" + " POOL " + Thread.currentThread().getName() +
                    "\n" + " ALL POOLS  " + Thread.getAllStackTraces().entrySet().size());
            // TODO: 26.01.2023 Сервер КОД
            server = bluetoothManagerServer.openGattServer(getApplicationContext(), new BluetoothGattServerCallback() {
                @Override
                public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
                    super.onConnectionStateChange(device, status, newState);
                    try {
                        МетодКоннектаДеконнектасКлиентамиGatt(device, status, newState);
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                @Override
                public void onServiceAdded(int status, BluetoothGattService service) {
                    super.onServiceAdded(status, service);
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                    getApplicationContext().getMainExecutor().execute(() -> {
                        bundleСервер.clear();
                        bundleСервер.putString("Статус", "SERVERGATTRUNNIGSTARTING");
                        mutableLiveDataGATTServer.setValue(bundleСервер);
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                    });
                }

                @SuppressLint("NewApi")
                @Override
                public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic,
                                                         boolean preparedWrite,
                                                         boolean responseNeeded, int offset, byte[] value) {
                    super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
                    try {
                        МетодОтвечаемКлиентуGatt(device, requestId, characteristic, offset, value);
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                // TODO: 21.02.2023 Ответ Клиенту
                private synchronized void МетодОтвечаемКлиентуGatt(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, int offset, byte[] value) {
                    try {
                        BluetoothGattService services = characteristic.getService();
                        if (services != null) {
                            BluetoothGattCharacteristic characteristicsДляСерверОтКлиента = services.getCharacteristic(uuidКлючСервераGATTЧтениеЗапись);
                            if (characteristicsДляСерверОтКлиента != null && value != null) {
                                // TODO: 20.02.2023
                                // TODO: 12.02.2023 ОТВЕТ КЛИЕНТУ
                                МетодОтветаОТGATTСеврераКлиентуДанныеми(device, requestId, offset, value, characteristicsДляСерверОтКлиента);
                                Log.i(TAG, "onCharacteristicWriteRequest to GATT server  characteristicsДляСерверОтКлиента"
                                        + characteristicsДляСерверОтКлиента +
                                        " characteristicsДляСерверОтКлиента.getUuid() " + characteristicsДляСерверОтКлиента.getUuid());
                            }
                        }
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                @SuppressLint({"NewApi", "SuspiciousIndentation"})
                private void МетодОтветаОТGATTСеврераКлиентуДанныеми(@NonNull BluetoothDevice device,
                                                                     @NonNull int requestId,
                                                                     @NonNull int offset,
                                                                     @NonNull byte[] value,
                                                                     @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента) {
                    String ПришлиДанныеОтКлиентаЗапрос = new String();
                    String ДанныеСодранныеОтКлиента = new String();
                    try {
                        final Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу = {0};
                        // TODO: 20.02.2023  Пришли ДАнные От Клиента
                        if (value.length > 0) {
                            ПришлиДанныеОтКлиентаЗапрос = new String(value);
                            Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString()
                                    + " value " + value);
                            String[] sArr = ПришлиДанныеОтКлиентаЗапрос.split(",");
                            List<String> listПришлиДанныеОтКлиентаЗапрос = Arrays.asList(sArr);

                            Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " время " + new Date().toLocaleString() + " value " + value);
                            // TODO: 13.02.2023
                            МетодПолучениеЛокацииGPS();
                            // TODO: 07.02.2023  Записываем ВБАзу Данные{
                            // TODO: 13.02.2023
                            if (addressesgetGPS != null) {
                                ДанныеСодранныеОтКлиента = "Девайс отмечен..." + "\n" + device.getName().toString() +
                                        "\n" + device.getAddress().toString() +
                                        "\n" + new Date().toLocaleString()
                                        + "\n" + ПришлиДанныеОтКлиентаЗапрос
                                        + "\n" + "GPS"
                                        + "\n" + "город: " + addressesgetGPS.get(0).getLocality()
                                        + "\n" + "адрес: " + addressesgetGPS.get(0).getAddressLine(0)
                                        + "\n" + "(корд1) " + addressesgetGPS.get(0).getLatitude()
                                        + "\n" + "(корд2) " + addressesgetGPS.get(0).getLongitude();
                                characteristicsServerОтКлиента.setValue("SERVER#SousAvtoSuccess");
                                Log.i(TAG, "SERVER#SousAvtoSuccess GPS" + " " + new Date().toLocaleString());
                            } else {
                                ДанныеСодранныеОтКлиента = "Девайс отмечен..." + "\n" + device.getName().toString() +
                                        "\n" + device.getAddress().toString() +
                                        "\n" + new Date().toLocaleString()
                                        + "\n" + ПришлиДанныеОтКлиентаЗапрос;
                                characteristicsServerОтКлиента.setValue("SERVER#SousAvtoSuccess");
                                Log.i(TAG, "SERVER#SousAvtoSuccess ---" + " " + new Date().toLocaleString());
                            }


                            // TODO: 12.02.2023  ОТВЕТ !!!
                            // TODO: 12.02.2023  ОТВЕТ !!!
                            server.notifyCharacteristicChanged(device, characteristicsServerОтКлиента, true);
                            server.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, characteristicsServerОтКлиента.toString().getBytes(StandardCharsets.UTF_8));
                            // TODO: 13.02.2023  Метод Записи Девайса в базу
                            Integer РезультатЗаписиВБАзу = 0;
                            if (addressesgetGPS != null) {
                                РезультатЗаписиВБАзу = МетодЗаписиДевайсавБазу(device,
                                        ПришлиДанныеОтКлиентаЗапрос,
                                        ДанныеСодранныеОтКлиента,
                                        characteristicsServerОтКлиента,
                                        addressesgetGPS, listПришлиДанныеОтКлиентаЗапрос);
                                Log.i(TAG, "addressesgetGPS " + " " + addressesgetGPS + " РезультатЗаписиВБАзу " + РезультатЗаписиВБАзу);
                            } else {
                                РезультатЗаписиВБАзу = МетодЗаписиДевайсавБазу(device,
                                        ПришлиДанныеОтКлиентаЗапрос,
                                        ДанныеСодранныеОтКлиента,
                                        characteristicsServerОтКлиента,
                                        listПришлиДанныеОтКлиентаЗапрос);
                                Log.i(TAG, "addressesgetGPS " + " " + addressesgetGPS + " РезультатЗаписиВБАзу " + РезультатЗаписиВБАзу);
                            }
                        } else {
                            Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                    + new Date().toLocaleString() + " value " + value);
                        }
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                + new Date().toLocaleString() + " value " + value);
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                private Integer МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,
                                                        String ПришлиДанныеОтКлиентаЗапрос,
                                                        String ДанныеСодранныеОтКлиента,
                                                        @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента,
                                                        @NonNull List<Address> addressesgetGPS,
                                                        @NonNull List<String> listПришлиДанныеОтКлиентаЗапрос) {
                    Integer РезультатЗаписиДанныхПИнгаДвайсаВБАзу=0;
                    try {
                        ContentValues contentValuesВставкаДанных = new ContentValues();
                        // TODO: 08.02.2023 методы после успешного получение данных от клиента
                        contentValuesВставкаДанных = new ContentValues();
                        contentValuesВставкаДанных.put("operations", "Девайс отмечен");
                        ПришлиДанныеОтКлиентаЗапрос = listПришлиДанныеОтКлиентаЗапрос.get(0).substring(1, listПришлиДанныеОтКлиентаЗапрос.get(0).length());
                        contentValuesВставкаДанных.put("completedwork", ПришлиДанныеОтКлиентаЗапрос);
                        contentValuesВставкаДанных.put("macdevice", device.getAddress().toString());
                        contentValuesВставкаДанных.put("date_update", new Date().toLocaleString());
                        contentValuesВставкаДанных.put("city", addressesgetGPS.get(0).getLocality());
                        contentValuesВставкаДанных.put("adress", addressesgetGPS.get(0).getAddressLine(0));
                        contentValuesВставкаДанных.put("gps1", String.valueOf(addressesgetGPS.get(0).getLatitude()));
                        contentValuesВставкаДанных.put("gps2", String.valueOf(addressesgetGPS.get(0).getLongitude()));
                        contentValuesВставкаДанных.put("namedevice", device.getName().toString());
                        contentValuesВставкаДанных.put("iemi", listПришлиДанныеОтКлиентаЗапрос.get(1).substring(0, listПришлиДанныеОтКлиентаЗапрос.get(1).length() - 1));
                        // TODO: 10.02.2023 версия данных
                        Integer current_table = МетодПоискДАнныхПоБазе("SELECT MAX ( current_table  ) AS MAX_R  FROM scannerserversuccess");
                        contentValuesВставкаДанных.put("current_table", current_table);
                        String uuid = МетодГенерацииUUID();
                        contentValuesВставкаДанных.put("uuid", uuid);
                        contentValuesВставкаДанных.put("date_update", new Date().toLocaleString());
                        Log.i(TAG, "contentValuesВставкаДанных.length" + contentValuesВставкаДанных.size());
                        // TODO: 27.02.2023 ЗАпись данные

                        // TODO: 09.02.2023  запись в базу дивайса Отметка сотрдунка
                        РезультатЗаписиДанныхПИнгаДвайсаВБАзу = МетодЗаписиОтмечаногоСотрудникаВБАзу(contentValuesВставкаДанных);
                        Log.i(TAG, " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + РезультатЗаписиДанныхПИнгаДвайсаВБАзу +
                                " contentValuesВставкаДанных " + contentValuesВставкаДанных);


                        if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу > 0) {
                            Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                            // TODO: 09.02.2023 сам статус дляОтвета;
                            Log.i(TAG, "SERVER#SousAvtoSuccess РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " "
                                    + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                            bundleСервер.clear();
                            bundleСервер.putString("Статус", "SERVER#SousAvtoSuccess");
                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                            bundleСервер.putString("Дивайс", device.getName());
                            getApplicationContext().getMainExecutor().execute(() -> {
                                mutableLiveDataGATTServer.setValue(bundleСервер);
                            });
                        } else {
                            // TODO: 09.02.2023 сам статус дляОтвета;
                            Log.i(TAG, "SERVER#SousAvtoERROR РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " " +
                                    "" + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                            bundleСервер.clear();
                            bundleСервер.putString("Статус", "SERVER#SousAvtoERROR");
                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", "Пинг прошел ," + "\n" +
                                    "Без записи в базу !!!");
                            bundleСервер.putString("Дивайс", device.getName());
                            getApplicationContext().getMainExecutor().execute(() -> {
                                mutableLiveDataGATTServer.setValue(bundleСервер);
                            });
                        }
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                    return РезультатЗаписиДанныхПИнгаДвайсаВБАзу;
                }


                // TODO: 14.02.2023 Второй Метод БЕз GPS
                private Integer МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,
                                                        @NonNull String ПришлиДанныеОтКлиентаЗапрос,
                                                        @NonNull String ДанныеСодранныеОтКлиента,
                                                        @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента,
                                                        @NonNull List<String> listПришлиДанныеОтКлиентаЗапрос) {
                    Integer РезультатЗаписиДанныхПИнгаДвайсаВБАзу=0;
                    try {
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время "
                                + new Date().toLocaleString() + " listПришлиДанныеОтКлиентаЗапрос " + listПришлиДанныеОтКлиентаЗапрос);
                        // TODO: 08.02.2023 методы после успешного получение данных от клиента
                        ContentValues   contentValuesВставкаДанных = new ContentValues();
                        contentValuesВставкаДанных.put("operations", "Девайс отмечен");
                        ПришлиДанныеОтКлиентаЗапрос = listПришлиДанныеОтКлиентаЗапрос.get(0).substring(1, listПришлиДанныеОтКлиентаЗапрос.get(0).length());
                        contentValuesВставкаДанных.put("completedwork", ПришлиДанныеОтКлиентаЗапрос);
                        contentValuesВставкаДанных.put("macdevice", device.getAddress().toString());
                        contentValuesВставкаДанных.put("date_update", new Date().toLocaleString());
                        contentValuesВставкаДанных.put("city", "без gps");
                        contentValuesВставкаДанных.put("adress", "без gps");
                        contentValuesВставкаДанных.put("gps1", "без gps");
                        contentValuesВставкаДанных.put("gps2", "без gps");
                        contentValuesВставкаДанных.put("namedevice", device.getName().toString());
                        contentValuesВставкаДанных.put("iemi", listПришлиДанныеОтКлиентаЗапрос.get(1).substring(0, listПришлиДанныеОтКлиентаЗапрос.get(1).length() - 1));
                        // TODO: 10.02.2023 версия данных
                        // TODO: 10.02.2023 версия данных
                        Integer current_table = МетодПоискДАнныхПоБазе("SELECT MAX ( current_table  ) AS MAX_R  FROM scannerserversuccess");
                        contentValuesВставкаДанных.put("current_table", current_table);
                        String uuid = МетодГенерацииUUID();
                        contentValuesВставкаДанных.put("uuid", uuid);
                        contentValuesВставкаДанных.put("date_update", new Date().toLocaleString());
                        Log.i(TAG, "contentValuesВставкаДанных.length" + contentValuesВставкаДанных.size());
                        // TODO: 09.02.2023  запись в базу дивайса Отметка сотрдунка
                        РезультатЗаписиДанныхПИнгаДвайсаВБАзу = МетодЗаписиОтмечаногоСотрудникаВБАзу(contentValuesВставкаДанных);
                        Log.i(TAG, " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + РезультатЗаписиДанныхПИнгаДвайсаВБАзу +
                                " contentValuesВставкаДанных " + contentValuesВставкаДанных);
                        if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу > 0) {
                            Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                            // TODO: 09.02.2023 сам статус дляОтвета;
                            Log.i(TAG, "SERVER#SousAvtoSuccess РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " "
                                    + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                            bundleСервер.clear();
                            bundleСервер.putString("Статус", "SERVER#SousAvtoSuccess");
                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                            bundleСервер.putString("Дивайс", device.getName());
                            getApplicationContext().getMainExecutor().execute(() -> {
                                mutableLiveDataGATTServer.setValue(bundleСервер);
                            });
                        } else {
                            // TODO: 09.02.2023 сам статус дляОтвета;
                            Log.i(TAG, "SERVER#SousAvtoERROR РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " " +
                                    "" + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                            bundleСервер.clear();
                            bundleСервер.putString("Статус", "SERVER#SousAvtoERROR");
                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", "Пинг прошел ," + "\n" +
                                    "Без записи в базу !!!");
                            bundleСервер.putString("Дивайс", device.getName());
                            getApplicationContext().getMainExecutor().execute(() -> {
                                mutableLiveDataGATTServer.setValue(bundleСервер);
                            });
                        }
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                    return РезультатЗаписиДанныхПИнгаДвайсаВБАзу;
                }


                @NonNull
                private String МетодГенерацииUUID() {
                    String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
                    uuid = uuid.replaceAll("[a-zA-Z]", "");
                    //uuid= CharMatcher.any().replaceFrom("[A-Za-z0-9]", "");
                    return uuid;
                }

                @Override
                public void onNotificationSent(BluetoothDevice device, int status) {
                    super.onNotificationSent(device, status);
                    try {
                        МетодПодтвержедиеЧтоОперацияУведомленияБыла(device, status);
                        Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                @Override
                public void onMtuChanged(BluetoothDevice device, int mtu) {
                    super.onMtuChanged(device, mtu);
                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                }

            });
            ///TODO  служебный xiaomi "BC:61:93:E6:F2:EB", МОЙ XIAOMI FC:19:99:79:D6:D4  //////      "BC:61:93:E6:E2:63","FF:19:99:79:D6:D4"
            UUID UuidГлавныйКлючСерверGATT = ParcelUuid.fromString("10000000-0000-1000-8000-00805f9b34fb").getUuid();
            // TODO: 12.02.2023 Адреса серверов для Клиентна
            uuidКлючСервераGATTЧтениеЗапись = ParcelUuid.fromString("20000000-0000-1000-8000-00805f9b34fb").getUuid();
            BluetoothGattService service = new BluetoothGattService(UuidГлавныйКлючСерверGATT, BluetoothGattService.SERVICE_TYPE_PRIMARY);
            // TODO: 12.02.2023 первый сервер
            BluetoothGattCharacteristic characteristic = new BluetoothGattCharacteristic(uuidКлючСервераGATTЧтениеЗапись,
                    BluetoothGattCharacteristic.PROPERTY_READ |
                            BluetoothGattCharacteristic.PROPERTY_WRITE |
                            BluetoothGattCharacteristic.PROPERTY_EXTENDED_PROPS |
                            BluetoothGattCharacteristic.PROPERTY_INDICATE |
                            BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT |
                            BluetoothGattCharacteristic.PROPERTY_NOTIFY |
                            BluetoothGattCharacteristic.PROPERTY_BROADCAST,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PERMISSION_WRITE |
                            BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED |
                            BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED);
            characteristic.addDescriptor(new
                    BluetoothGattDescriptor(uuidКлючСервераGATTЧтениеЗапись,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PERMISSION_WRITE |
                            BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED |
                            BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED));
            service.addCharacteristic(characteristic);
            // TODO: 12.02.2023 добавлев в сервер
            server.addService(service);
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
            bundleСервер.clear();
            bundleСервер.putString("Статус","SERVERGATTRUNNIGERRORS");
            mutableLiveDataGATTServer.setValue(bundleСервер);
        }
    }

    @SuppressLint("MissingPermission")
    private synchronized void МетодПодтвержедиеЧтоОперацияУведомленияБыла(BluetoothDevice device, int status) {
        try{
        server.sendResponse(device, status, BluetoothGatt.GATT_SUCCESS, status, "YOUR_RESPONSEonNotificationSent".getBytes(StandardCharsets.UTF_8));
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        bundleСервер.clear();
        bundleСервер.putString("Статус","SERVERGATTRUNNIGERRORS");
        mutableLiveDataGATTServer.setValue(bundleСервер);
    }
    }

    // TODO: 21.02.2023 метод превоночального коннекта с устройством
    @SuppressLint("MissingPermission")
    private synchronized void МетодКоннектаДеконнектасКлиентамиGatt(BluetoothDevice device, int status, int newState) {
        try{
        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            // TODO: 27.02.2023 Переопреляем Адамтер Bluetooth
            List<BluetoothDevice> bluetoothDeviceListGattServer=   server.getConnectedDevices();
        switch (newState) {
            case BluetoothProfile.STATE_CONNECTED:
                    server.connect(device,false);
                Log.i(TAG, " onConnectionStateChange BluetoothProfile.STATE_CONNECTED " +   device.getAddress().toString()+
                        "\n"+ "newState " + newState +  "status "+ status);
                v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                getApplicationContext().getMainExecutor().execute(()->{
                    bundleСервер.clear();
                    bundleСервер.putString("Статус","SERVERGATTConnectiong");
                    bundleСервер.putString("Дивайс", device.getName());
                    mutableLiveDataGATTServer.setValue(bundleСервер);
                });
                break;
            case BluetoothProfile.STATE_DISCONNECTED:
                Log.i(TAG, " onConnectionStateChange BluetoothProfile.STATE_DISCONNECTED "+  device.getAddress()+
                        "\n"+ "newState " + newState +  "status "+ status);
               // server.cancelConnection(device);
                break;
            case BluetoothGatt.GATT_CONNECTION_CONGESTED:
                Log.i(TAG, " onConnectionStateChange BluetoothProfile.STATE_DISCONNECTED "+  device.getAddress()+
                        "\n"+ "newState " + newState +  "status "+ status);
                // server.cancelConnection(device);
                break;
        }
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+
                    "  bluetoothDeviceListGattServer " +bluetoothDeviceListGattServer.size());
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }

    @SuppressLint({"NewApi", "SuspiciousIndentation", "MissingPermission"})
    private  void МетодПолучениеЛокацииGPS() {
        try{
                if (lastLocation != null) {
                    fusedLocationClientGoogle.flushLocations();
                    while (lastLocation.isComplete()==false);
                        Log.i(TAG, "MyLocationListener GPS longitude "+lastLocation);
                        String cityName = null;
                        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                        Log.i(TAG, "MyLocationListener GPS gcd "+gcd);
                        try {
                            addressesgetGPS = gcd.getFromLocation(lastLocation.getLatitude(),
                                    lastLocation.getLongitude(), 1);
                            Log.i(TAG, "MyLocationListener GPS addressesgetGPS "+addressesgetGPS);
                        } catch (IOException e) {
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
                            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                        }
                        if (addressesgetGPS.size() > 0) {
                            System.out.println(addressesgetGPS.get(0).getLocality());
                            cityName = addressesgetGPS.get(0).getLocality();
                            Log.i(TAG, "MyLocationListener GPS cityName "+cityName);
                            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() + "cityName " +cityName );
                        }
                    Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() + "addressesgetGPS " +addressesgetGPS );
                }
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }







    public  Integer МетодЗаписиОтмечаногоСотрудникаВБАзу(@NonNull Context appContext) {
        Integer РезульататЗАписиНовогоДивайса=0;
        try{
          Log.i(appContext.getClass().getName(), "запись сотрудника в базу"+ " " );

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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return  0;
    }
    public  Integer МетодЗаписиОтмечаногоСотрудникаВБАзу(@NonNull ContentValues contentValues) {
        Integer РезульататЗАписиНовогоДивайса=0;
        try{
            ContentValues[] contentValuesForProvider=new ContentValues[0];
            contentValuesForProvider[0].putAll(contentValues);
            Log.i(this.getClass().getName(), "запись сотрудника в базу"+ " linkedHashMapДанныеДляЗаписи) "
                    + contentValues+ " contentValuesForProvider " +contentValuesForProvider) ;
            String provider = "com.sous.server.providerserver";
            Uri uri = Uri.parse("content://"+provider+"/" +"scannerserversuccess" + "");
            ContentResolver resolver = getApplicationContext().getContentResolver();
            РезульататЗАписиНовогоДивайса=   resolver.bulkInsert(uri, contentValuesForProvider);
            Log.w(getApplicationContext().getClass().getName(), " РЕЗУЛЬТАТ insertData  РезульататЗАписиНовогоДивайса ЗНАЧЕНИЯ  " +  РезульататЗАписиНовогоДивайса.toString() );
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return  РезульататЗАписиНовогоДивайса;
    }

    // TODO: 10.02.2023 МЕТОД ВЫБОР ДАННЫХ
    public  Integer МетодПоискДАнныхПоБазе(@NonNull String СамЗапрос) {
        Integer   ВерсияДАнных = 0;
        try{
            Log.i(this.getClass().getName(), "запись сотрудника в базу"+ " linkedHashMapДанныеДляЗаписи) " + СамЗапрос) ;
            String provider = "com.sous.server.providerserver";
            Uri uri = Uri.parse("content://"+provider+"/" +"scannerserversuccess" + "");
            ContentResolver resolver = getApplicationContext().getContentResolver();
             Cursor cursorПолучаемДЛяСевреа=   resolver.query(uri,new String[]{СамЗапрос},null,null,null,null);
            cursorПолучаемДЛяСевреа.moveToFirst();
             if (cursorПолучаемДЛяСевреа.getCount()>0){
                 ВерсияДАнных=      cursorПолучаемДЛяСевреа.getInt(0);
                 Log.i(this.getClass().getName(), "ВерсияДАнных"+ ВерсияДАнных) ;
                 ВерсияДАнных++;
             }
            Log.w(getApplicationContext().getClass().getName(), " РЕЗУЛЬТАТ insertData  cursorПолучаемДЛяСевреа  " +  cursorПолучаемДЛяСевреа.toString() );
            cursorПолучаемДЛяСевреа.close();
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return  ВерсияДАнных;
    }


}
