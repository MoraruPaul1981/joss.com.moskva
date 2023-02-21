package com.sous.server.Services;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.GnssNavigationMessage;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;


import com.sous.server.Databases.CREATE_DATABASEServer;
import com.sous.server.Firebases.MyFirebaseMessagingServiceServerScanners;
import com.sous.server.Errors.SubClassErrors;
import com.sous.server.Locations.MyLocationListener;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;


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
    private BluetoothManager bluetoothManagerServer;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices = new HashSet<>();
    private BluetoothGattServer server;
    private Long version = 0l;
    public ServiceGattServer() {
        super("ServiceGattServer");
    }
    private MutableLiveData<Bundle> mutableLiveDataGATTServer;
    private List<Address> addressesgetGPS;
    private UUID uuidКлючСервераGATTЧтениеЗапись;
    private Bundle bundleСервер;
    private LocationManager locationManager;
    private String ВозврящаетсяКлючScannerONESIGNAl = new String();
    private String КлючДляServerFibaseOneSingnal = "220d6edf-2b29-453e-97a8-d2aefe4a9eb0";
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
            // TODO: 13.02.2023  ИНИЦИАЛИЗАЦИИ GPS через Google
            МетодИницилиазцииGpsGoogle();
            // TODO: 14.02.2023 Firebase
            МетодПолучениеServerСканеарКлюча_OndeSignal(КлючДляServerFibaseOneSingnal);
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
        if (server!=null) {
            server.close();
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
    public synchronized   void МетодГлавныйЗапускGattServer(@NonNull Handler handler, @NonNull Context context,
                                             @NonNull BluetoothManager bluetoothManager,
                                             @NonNull MutableLiveData<Bundle>mutableLiveDataGATTServer) {
        this.bluetoothManagerServer = bluetoothManager;
        this.mutableLiveDataGATTServer=mutableLiveDataGATTServer;
        // TODO: 08.12.2022 уснатавливаем настройки Bluetooth
        Log.w(this.getClass().getName(), " SERVER  МетодГлавныйЗапускGattServer  bluetoothManager  " + bluetoothManager + "server "+server);
        try {
            // TODO: 21.02.2023 Метод Перегрузки Сервера
            МетодПерегрузкиСервераGatt(mutableLiveDataGATTServer);
            // TODO: 14.02.2023    ЗарускаемСамСервер
            МетодЗапускаСервера();
            
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
            Log.d(TAG, "МетодЗапускаСканированиеКлиент: Запускаем.... Метод Сканирования Для Android");
            Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  " + "\n+" +
                    "" + binder.isBinderAlive() + " date " + new Date().toString().toString() + "" +
                    "\n" + " POOL " + Thread.currentThread().getName() +
                    "\n" + " ALL POOLS  " + Thread.getAllStackTraces().entrySet().size());
            // TODO: 07.02.2023  иницилизирем Запуск GPS
            // TODO: 26.01.2023 Сервер КОД
            server = bluetoothManagerServer.openGattServer(getApplicationContext(), new BluetoothGattServerCallback() {
                @Override
                public  void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
                    super.onConnectionStateChange(device, status, newState);
                    try {
                        МетодКоннектаДеконнектасКлиентамиGatt(device, status, newState);

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
                public  void onServiceAdded(int status, BluetoothGattService service) {
                    super.onServiceAdded(status, service);
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                    getApplicationContext().getMainExecutor().execute(()->{
                        bundleСервер.clear();
                        bundleСервер.putString("Статус","SERVERGATTRUNNIGSTARTING");
                        mutableLiveDataGATTServer.setValue(bundleСервер);
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                    });
                }

                @SuppressLint("NewApi")
                @Override
                public  void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic,
                                                         boolean preparedWrite,
                                                         boolean responseNeeded, int offset, byte[] value) {
                    super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
                    try{
                        МетодОтвечаемКлиентуGatt(device, requestId, characteristic, offset, value);
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

                // TODO: 21.02.2023 Ответ Клиенту
                private synchronized void МетодОтвечаемКлиентуGatt(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, int offset, byte[] value) {
                    try{
                    BluetoothGattService services = characteristic.getService();
                    if (services!=null) {
                        BluetoothGattCharacteristic characteristicsДляСерверОтКлиента = services.getCharacteristic(uuidКлючСервераGATTЧтениеЗапись);
                        if (characteristicsДляСерверОтКлиента!=null  && value !=null ){
                            // TODO: 20.02.2023
                                // TODO: 12.02.2023 ОТВЕТ КЛИЕНТУ
                                МетодОтветаОТGATTСеврераКлиентуДанныеми(device, requestId, offset, value,characteristicsДляСерверОтКлиента);
                                Log.i(TAG, "onCharacteristicWriteRequest to GATT server  characteristicsДляСерверОтКлиента"
                                        + characteristicsДляСерверОтКлиента+
                                        " characteristicsДляСерверОтКлиента.getUuid() " +characteristicsДляСерверОтКлиента.getUuid());
                        }
                    }
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

                @SuppressLint({"NewApi", "SuspiciousIndentation"})
                private  void МетодОтветаОТGATTСеврераКлиентуДанныеми(@NonNull BluetoothDevice device,
                                                                     @NonNull int requestId,
                                                                     @NonNull  int offset,
                                                                     @NonNull   byte[] value,
                                                                     @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента) {
                    String ПришлиДанныеОтКлиентаЗапрос = new String();
                    String ДанныеСодранныеОтКлиента = new String();
                    try {
                        final Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу = {0};
                        // TODO: 20.02.2023  Пришли ДАнные От Клиента
                        if (value.length > 0 ) {
                            ПришлиДанныеОтКлиентаЗапрос = new String(value);
                        Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() 
                                + " value " +value);
                        String[] sArr = ПришлиДанныеОтКлиентаЗапрос.split(",");
                        List<String> listПришлиДанныеОтКлиентаЗапрос = Arrays.asList(sArr);

                            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                                    " время " +new Date().toLocaleString() + " value " +value);
                            // TODO: 13.02.2023
                            МетодПолучениеЛокацииGPS();
                            // TODO: 07.02.2023  Записываем ВБАзу Данные{
                                // TODO: 13.02.2023
                                if (addressesgetGPS!=null) {
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
                                server.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset,characteristicsServerОтКлиента.toString().getBytes(StandardCharsets.UTF_8));
                                // TODO: 13.02.2023  Метод Записи Девайса в базу
                                Integer РезультатЗаписиВБАзу=0;
                                if (addressesgetGPS!=null) {
                                  РезультатЗаписиВБАзу=  МетодЗаписиДевайсавБазу(device,
                                          РезультатЗаписиДанныхПИнгаДвайсаВБАзу,
                                            ПришлиДанныеОтКлиентаЗапрос,
                                          ДанныеСодранныеОтКлиента,
                                          characteristicsServerОтКлиента,
                                          addressesgetGPS,listПришлиДанныеОтКлиентаЗапрос );
                                    Log.i(TAG, "addressesgetGPS " + " " + addressesgetGPS+ " РезультатЗаписиВБАзу "+РезультатЗаписиВБАзу);
                                }else {
                                    РезультатЗаписиВБАзу=  МетодЗаписиДевайсавБазу(device,
                                            РезультатЗаписиДанныхПИнгаДвайсаВБАзу,
                                            ПришлиДанныеОтКлиентаЗапрос,
                                            ДанныеСодранныеОтКлиента,
                                            characteristicsServerОтКлиента,
                                            listПришлиДанныеОтКлиентаЗапрос );
                                    Log.i(TAG, "addressesgetGPS " + " " + addressesgetGPS+ " РезультатЗаписиВБАзу "+РезультатЗаписиВБАзу);
                                }
                            }else{
                            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                                    +new Date().toLocaleString() + " value " +value);
                        }
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                                +new Date().toLocaleString() + " value " +value);
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

                private  Integer МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,
                                                                     Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу,
                                                                     String ПришлиДанныеОтКлиентаЗапрос,
                                                                     String ДанныеСодранныеОтКлиента,
                                                                     @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента,
                                                                     @NonNull   List<Address> addressesgetGPS ,
                                                                     @NonNull List<String> listПришлиДанныеОтКлиентаЗапрос) {
                    try{
                    ContentValues[] contentValuesВставкаДанных = new ContentValues[1];
                    // TODO: 08.02.2023 методы после успешного получение данных от клиента
                    contentValuesВставкаДанных[0] = new ContentValues();
                    contentValuesВставкаДанных[0].put("operations", "Девайс отмечен");
                    ПришлиДанныеОтКлиентаЗапрос = ПришлиДанныеОтКлиентаЗапрос.replaceAll("действие:", "");
                    contentValuesВставкаДанных[0].put("completedwork", ПришлиДанныеОтКлиентаЗапрос);
                    contentValuesВставкаДанных[0].put("macdevice", device.getAddress().toString());
                    contentValuesВставкаДанных[0].put("date_update", new Date().toLocaleString());
                    contentValuesВставкаДанных[0].put("city", addressesgetGPS.get(0).getLocality());
                    contentValuesВставкаДанных[0].put("adress", addressesgetGPS.get(0).getAddressLine(0));
                    contentValuesВставкаДанных[0].put("gps1", String.valueOf(addressesgetGPS.get(0).getLatitude()));
                    contentValuesВставкаДанных[0].put("gps2", String.valueOf(addressesgetGPS.get(0).getLongitude()));
                    contentValuesВставкаДанных[0].put("namedevice", device.getName().toString());
                    // TODO: 10.02.2023 версия данных
                    Integer current_table = МетодПоискДАнныхПоБазе("SELECT MAX ( current_table  ) AS MAX_R  FROM scannerserversuccess");
                    contentValuesВставкаДанных[0].put("current_table", current_table);
                    String uuid = МетодГенерацииUUID();
                    contentValuesВставкаДанных[0].put("uuid", uuid);
                    contentValuesВставкаДанных[0].put("date_update", new Date().toLocaleString());
                    Log.i(TAG, "contentValuesВставкаДанных.length" + contentValuesВставкаДанных.length);

                    Completable completableВставка = Completable.complete()
                            .fromSupplier(new Supplier<Integer>() {
                                @Override
                                public Integer get() throws Throwable {
                                    // TODO: 09.02.2023  запись в базу дивайса Отметка сотрдунка
                                    РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] = МетодЗаписиОтмечаногоСотрудникаВБАзу(contentValuesВставкаДанных);
                                    Log.i(TAG, " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] +
                                            " contentValuesВставкаДанных " + contentValuesВставкаДанных);
                                    return РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0];
                                }
                            })
                            .doOnError(new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {
                                    Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    ContentValues valuesЗаписываемОшибки = new ContentValues();
                                    valuesЗаписываемОшибки.put("Error", throwable.toString().toLowerCase());
                                    valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
                                    valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
                                    valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    final Object ТекущаяВерсияПрограммы = version;
                                    Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                                    valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
                                    new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                                }
                            })
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                                    if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] > 0) {
                                        // TODO: 09.02.2023 сам статус дляОтвета;
                                        Log.i(TAG, "SERVER#SousAvtoSuccess РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " "
                                                + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoSuccess");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                                            bundleСервер.putString("Дивайс", device.getName());
                                        getApplicationContext().getMainExecutor().execute(()->{
                                                mutableLiveDataGATTServer.setValue(bundleСервер);
                                            });
                                    } else {
                                        // TODO: 09.02.2023 сам статус дляОтвета;
                                            Log.i(TAG, "SERVER#SousAvtoERROR РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " " +
                                                    "" + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoERROR");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу","Пинг прошел ," + "\n" +
                                                    "Без записи в базу !!!");
                                            bundleСервер.putString("Дивайс", device.getName());
                                        getApplicationContext().getMainExecutor().execute(()->{
                                            mutableLiveDataGATTServer.setValue(bundleСервер);
                                                });
                                    }
                                }
                            });
                    completableВставка.subscribe();
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
                    return  РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0];
                }


                // TODO: 14.02.2023 Второй Метод БЕз GPS
                private  Integer МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,
                                                                     @NonNull   Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу,
                                                                     @NonNull  String ПришлиДанныеОтКлиентаЗапрос,
                                                                     @NonNull  String ДанныеСодранныеОтКлиента,
                                                                     @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента,
                                                                     @NonNull List<String>listПришлиДанныеОтКлиентаЗапрос) {
                    try{
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                                +new Date().toLocaleString()+  " listПришлиДанныеОтКлиентаЗапрос " +listПришлиДанныеОтКлиентаЗапрос );
                        ContentValues[] contentValuesВставкаДанных = new ContentValues[1];
                        // TODO: 08.02.2023 методы после успешного получение данных от клиента
                        contentValuesВставкаДанных[0] = new ContentValues();
                        contentValuesВставкаДанных[0].put("operations", "Девайс отмечен");
                        ПришлиДанныеОтКлиентаЗапрос = ПришлиДанныеОтКлиентаЗапрос.replaceAll("действие:", "");
                        contentValuesВставкаДанных[0].put("completedwork", ПришлиДанныеОтКлиентаЗапрос);
                        contentValuesВставкаДанных[0].put("macdevice", device.getAddress().toString());
                        contentValuesВставкаДанных[0].put("date_update", new Date().toLocaleString());
                        contentValuesВставкаДанных[0].put("city", "без gps");
                        contentValuesВставкаДанных[0].put("adress","без gps");
                        contentValuesВставкаДанных[0].put("gps1", "без gps");
                        contentValuesВставкаДанных[0].put("gps2", "без gps");
                        contentValuesВставкаДанных[0].put("namedevice", device.getName().toString());
                        // TODO: 20.02.2023 новые поля симки
                        contentValuesВставкаДанных[0].put("sim1", listПришлиДанныеОтКлиентаЗапрос.get(1));
                        contentValuesВставкаДанных[0].put("sim2", listПришлиДанныеОтКлиентаЗапрос.get(2));
                        contentValuesВставкаДанных[0].put("iemi", listПришлиДанныеОтКлиентаЗапрос.get(3));
                        // TODO: 10.02.2023 версия данных
                        Integer current_table = МетодПоискДАнныхПоБазе("SELECT MAX ( current_table  ) AS MAX_R  FROM scannerserversuccess");
                        contentValuesВставкаДанных[0].put("current_table", current_table);
                        String uuid = МетодГенерацииUUID();
                        contentValuesВставкаДанных[0].put("uuid", uuid);
                        contentValuesВставкаДанных[0].put("date_update", new Date().toLocaleString());
                        Log.i(TAG, "contentValuesВставкаДанных.length" + contentValuesВставкаДанных.length);

                        Completable completableВставка = Completable.complete()
                                .fromSupplier(new Supplier<Integer>() {
                                    @Override
                                    public Integer get() throws Throwable {
                                        // TODO: 09.02.2023  запись в базу дивайса Отметка сотрдунка
                                        РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] = МетодЗаписиОтмечаногоСотрудникаВБАзу(contentValuesВставкаДанных);
                                        Log.i(TAG, " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] +
                                                " contentValuesВставкаДанных " + contentValuesВставкаДанных);
                                        return РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0];
                                    }
                                })
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        ContentValues valuesЗаписываемОшибки = new ContentValues();
                                        valuesЗаписываемОшибки.put("Error", throwable.toString().toLowerCase());
                                        valuesЗаписываемОшибки.put("Klass", this.getClass().getName());
                                        valuesЗаписываемОшибки.put("Metod", Thread.currentThread().getStackTrace()[2].getMethodName());
                                        valuesЗаписываемОшибки.put("LineError", Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        final Object ТекущаяВерсияПрограммы = version;
                                        Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
                                        valuesЗаписываемОшибки.put("whose_error", ЛокальнаяВерсияПОСравнение);
                                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                                    }
                                })
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                                        if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] > 0) {
                                            // TODO: 09.02.2023 сам статус дляОтвета;
                                            Log.i(TAG, "SERVER#SousAvtoSuccess РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " "
                                                    + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoSuccess");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                                            bundleСервер.putString("Дивайс", device.getName());
                                            getApplicationContext().getMainExecutor().execute(()->{
                                                mutableLiveDataGATTServer.setValue(bundleСервер);
                                            });
                                        } else {
                                            // TODO: 09.02.2023 сам статус дляОтвета;
                                            Log.i(TAG, "SERVER#SousAvtoERROR РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " " +
                                                    "" + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoERROR");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу","Пинг прошел ," + "\n" +
                                                    "Без записи в базу !!!");
                                            bundleСервер.putString("Дивайс", device.getName());
                                            getApplicationContext().getMainExecutor().execute(()->{
                                                mutableLiveDataGATTServer.setValue(bundleСервер);
                                            });
                                        }
                                    }
                                });
                        completableВставка.subscribe();
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
                    return  РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0];
                }













                @NonNull
                private  String МетодГенерацииUUID() {
                    String uuid=    UUID.randomUUID().toString().replace("-","").substring(0,20);
                    uuid=uuid.replaceAll("[a-zA-Z]","");
                    //uuid= CharMatcher.any().replaceFrom("[A-Za-z0-9]", "");
                    return uuid;
                }

                @Override
                public  void onNotificationSent(BluetoothDevice device, int status) {
                    super.onNotificationSent(device, status);
                    try{
                        МетодПодтвержедиеЧтоОперацияУведомленияБыла(device, status);
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

                @Override
                public void onMtuChanged(BluetoothDevice device, int mtu) {
                    super.onMtuChanged(device, mtu);
                    Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                }

            });
            ///TODO  служебный xiaomi "BC:61:93:E6:F2:EB", МОЙ XIAOMI FC:19:99:79:D6:D4  //////      "BC:61:93:E6:E2:63","FF:19:99:79:D6:D4"
          UUID UuidГлавныйКлючСерверGATT =        ParcelUuid.fromString("10000000-0000-1000-8000-00805f9b34fb").getUuid();
            // TODO: 12.02.2023 Адреса серверов для Клиентна
            uuidКлючСервераGATTЧтениеЗапись =        ParcelUuid.fromString("20000000-0000-1000-8000-00805f9b34fb").getUuid();
            BluetoothGattService service= new BluetoothGattService(UuidГлавныйКлючСерверGATT, BluetoothGattService.SERVICE_TYPE_PRIMARY);
            // TODO: 12.02.2023 первый сервер
            BluetoothGattCharacteristic characteristic = new BluetoothGattCharacteristic(uuidКлючСервераGATTЧтениеЗапись,
                    BluetoothGattCharacteristic.PROPERTY_READ |
                            BluetoothGattCharacteristic.PROPERTY_WRITE|
                            BluetoothGattCharacteristic.PROPERTY_EXTENDED_PROPS|
                           BluetoothGattCharacteristic.PROPERTY_INDICATE |
                           BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT |
                            BluetoothGattCharacteristic.PROPERTY_NOTIFY |
                            BluetoothGattCharacteristic.PROPERTY_BROADCAST,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PERMISSION_WRITE |
                            BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED|
                            BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED );
            characteristic.addDescriptor(new
                    BluetoothGattDescriptor(uuidКлючСервераGATTЧтениеЗапись,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PERMISSION_WRITE |
                            BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED|
                            BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED ));
            service.addCharacteristic(characteristic);
            // TODO: 12.02.2023 добавлев в сервер
            server.addService(service);
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
        switch (newState) {
            case BluetoothProfile.STATE_CONNECTED:
                if (newState ==2) {
                    server.connect(device,false);
                }
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
                v2.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
                break;
        }
            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString());
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



// TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР

    public  String МетодПолучениеServerСканеарКлюча_OndeSignal(@NonNull String КлючДляFirebaseNotification) {
        try {
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            // TODO: 01.02.2023 Получение Новго Ключа Для Сканера
            if ( ВозврящаетсяКлючScannerONESIGNAl.length()==0) {
                ВозврящаетсяКлючScannerONESIGNAl =     МетодПолучениеКлючаОтСлужбыONESIGNALAndFirebase(КлючДляFirebaseNotification);
                Log.d(getApplicationContext().getClass().getName(), "  Observable.interval    ВозврящаетсяКлючScannerONESIGNAl[0] " +   ВозврящаетсяКлючScannerONESIGNAl);
            }
            Log.i(this.getClass().getName(),  "onStart() " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()
                    + " ВозврящаетсяКлючScannerONESIGNAl " +ВозврящаетсяКлючScannerONESIGNAl);
            // TODO: 05.01.2022  ДЕЛАЕМ ПОДПИСКУ НА ОСУЩЕСТВЛЛЕНУЮ ДАННЫХ
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return ВозврящаетсяКлючScannerONESIGNAl;
    }

    private   String МетодПолучениеКлючаОтСлужбыONESIGNALAndFirebase(@NonNull String КлючДляFirebaseNotification) {
        String ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = null;
        try{
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ Scanner  OneSignal........ "+ КлючДляFirebaseNotification +"\n");
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            // todo OneSignal Initialization
            OneSignal.initWithContext(getApplicationContext());
            ///////todo srating Google Notifications wits PUblic Key
            OneSignal.setAppId(КлючДляFirebaseNotification);
            OneSignal.disablePush(false);
            //TODO srating.......... firebase cloud --ПРИШЛО СООБЩЕНИЕ
            FirebaseMessagingService firebaseMessagingService =new MyFirebaseMessagingServiceServerScanners();
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  FirebaseMessagingService"  );
            // TODO: 07.12.2021
            firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ SERVER BLE КОНЕЦ  OneSignal........  220d6edf-2b29-453e-97a8-d2aefe4a9eb0 " );
            // TODO: 15.12.2021 настройки onesigmnal
            Map<String, String> params = new HashMap<String, String>();
            OneSignal.sendTag("Authorization", "Basic 220d6edf-2b29-453e-97a8-d2aefe4a9eb0");
            OneSignal.sendTag("Content-type", "application/json");
            OneSignal.sendTag("grp_msg", "serverscanners");
            OneSignal.sendTag("android_background_data", "true");
            OneSignal.sendTag("content_available", "true");
            // TODO: 14.02.2023 получаем uuid для onesingal
         String tokenOneSignal=   OneSignal.getDeviceState().getPushToken();
            tokenOneSignal=   OneSignal.getDeviceState().getPushToken();
            //TODO srating......  oneSignal
                ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = OneSignal.getDeviceState().getUserId();
            // TODO: 15.12.2021
            Log.d(this.getClass().getName(), "  ПОСЛЕ КЛЮЧ ДЛЯ SERVER SCANNER  OneSignal........  220d6edf-2b29-453e-97a8-d2aefe4a9eb0  "+"\n"+
                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggerValueForKey("GT_PLAYER_ID")+
                    "     OneSignal.getTriggers() " +   OneSignal.getTriggers()+"\n"+
                    "    ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal ОТ СЕРВЕРА ::: " + ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal);
            // TODO: 13.12.2021
            // TODO: 05.01.2022  ДЕЛАЕМ ПОДПИСКУ НА ОСУЩЕСТВЛЛЕНУЮ ДАННЫХ
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        return ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal;
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
    public  Integer МетодЗаписиОтмечаногоСотрудникаВБАзу(@NonNull ContentValues[] contentValues) {
        Integer РезульататЗАписиНовогоДивайса=0;
        try{
            Log.i(this.getClass().getName(), "запись сотрудника в базу"+ " linkedHashMapДанныеДляЗаписи) " + contentValues) ;
            String provider = "com.sous.server.providerserver";
            Uri uri = Uri.parse("content://"+provider+"/" +"scannerserversuccess" + "");
            ContentResolver resolver = getApplicationContext().getContentResolver();
            РезульататЗАписиНовогоДивайса=   resolver.bulkInsert(uri, contentValues);
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
