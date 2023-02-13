package com.sous.server.CONTROL;

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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;


import com.sous.server.MODEL.CREATE_DATABASEServer;
import com.sous.server.MODEL.MyFirebaseMessagingServiceServerScanners;
import com.sous.server.MODEL.SubClassErrors;
import com.sous.server.VIEW.MyLocationListener;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceControllerServer extends IntentService {
    private SQLiteDatabase sqLiteDatabase;
    private CREATE_DATABASEServer createDatabaseScanner;
    public LocalBinderСканнер binder = new LocalBinderСканнер();
    private Context context;
    private String TAG;
    private Handler handler;
    private ExecutorService executorServiceСканер;
    private BluetoothManager bluetoothManagerServer;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices = new HashSet<>();
    private BluetoothGattServer server;
    private Long version=0l;
    public ServiceControllerServer() {
        super("ServiceControllerServer");
    }
    private MutableLiveData<Bundle> mutableLiveDataGATTServer;

    private  List<Address> addressesgetGPS;
    private  Location lastLocation;
    private   UUID uuidКлючСервераGATTЧтениеЗапись;
    private  Bundle bundleСервер;
    private        LocationManager locationManager ;
    private  LocationListener locationListener;
    private  BluetoothGattCharacteristic characteristicsServerОтКлиента;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            this.createDatabaseScanner = new CREATE_DATABASEServer(context);
            this.sqLiteDatabase = createDatabaseScanner.getССылкаНаСозданнуюБазу();
            TAG = getClass().getName().toString();
            executorServiceСканер = Executors.newCachedThreadPool();
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();
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



    public class LocalBinderСканнер extends Binder {
        public ServiceControllerServer getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceControllerServer.this;
        }

        public void linkToDeath(DeathRecipient deathRecipient) {
            deathRecipient.binderDied();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
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
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                executorServiceСканер.shutdown();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
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
            this.context = getApplicationContext();
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
        this.context = newBase;
        super.attachBaseContext(newBase);
    }

    // TODO: 30.11.2022 сервер СКАНИРОВАНИЯ
    public void МетодГлавныйЗапускGattServer(@NonNull Handler handler, @NonNull Context context,
                                             @NonNull BluetoothManager bluetoothManager,
                                             @NonNull MutableLiveData<Bundle>mutableLiveDataGATTServer,
                                             @NonNull LocationManager locationManager ) {
        this.context = context;
        this.handler = handler;
        this.bluetoothManagerServer = bluetoothManager;
        this.mutableLiveDataGATTServer=mutableLiveDataGATTServer;
        this.locationManager=locationManager;
        // TODO: 08.12.2022 уснатавливаем настройки Bluetooth
        bundleСервер=new Bundle();
        Log.w(this.getClass().getName(), " SERVER  МетодГлавныйЗапускGattServer  bluetoothManager  " + bluetoothManager );
        try {
            // TODO: 13.02.2023  ИНИЦИАЛИЗАЦИИ GPS
            МетодИнициализацииGPS();
            Log.w(this.getClass().getName(), "   МетодГлавныйЗапускGattServer   ");
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
    private void МетодИнициализацииGPS() {
        try{
         ExecutorService executorService=Executors.newCachedThreadPool();
         executorService.submit(()->{
             handler.post(()->{
                 // TODO: 01.02.2023 Получение Новго Ключа Для Сканера
                 while (lastLocation==null) {
                     locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                     locationListener = new MyLocationListener(context);
                     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 90000, 0.0F, locationListener);
                     lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                     Log.d(TAG, "lastLocation takeWhile CONNECT FOR FOR FOR GPS LOCATION   "+lastLocation+ " время  : " +new Date().toLocaleString() );
                     Log.d(TAG, "lastLocation МетодИнициализацииGPS "+lastLocation );
                 }
                 // TODO: 13.02.2023
                 МетодПолучениеЛокацииGPS();
                 executorService.shutdown();
                 if(executorService.isShutdown()==true){
                     МетодЗапускаСервера();
                     Log.w(this.getClass().getName(), "   МетодГлавныйЗапускGattServer   ");
                 }
             });
         });
// create observable
            Log.d(TAG, "lastLocation takeWhile "+lastLocation );
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
    // TODO: 08.12.2022 Метод Сервер
    @SuppressLint("MissingPermission")
    public void МетодЗапускаСервера() {
        try {
            Message message = Message.obtain(handler);
            Bundle bundle = new Bundle();
            bundle.putString("КакоеДейтвие", "ВыключаемPrograssbar");
            message.setData(bundle);
            message.setAsynchronous(true);
            handler.sendMessageDelayed(message, 1000);
            Log.d(TAG, "МетодЗапускаСканированиеКлиент: Запускаем.... Метод Сканирования Для Android");
            Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  " + "\n+" +
                    "" + binder.isBinderAlive() + " date " + new Date().toString().toString() + "" +
                    "\n" + " POOL " + Thread.currentThread().getName() +
                    "\n" + " ALL POOLS  " + Thread.getAllStackTraces().entrySet().size());
            // TODO: 07.02.2023  иницилизирем Запуск GPS
                    handler.post(()->{
                        bundleСервер.clear();
                        bundleСервер.putString("Статус","SERVERGATTRUNNIGSTARTING");
                        mutableLiveDataGATTServer.setValue(bundleСервер);
                });

            // TODO: 26.01.2023 Сервер КОД
            server = bluetoothManagerServer.openGattServer(context, new BluetoothGattServerCallback() {
                @Override
                public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
                    super.onConnectionStateChange(device, status, newState);
                    try {
                        switch (newState) {
                            case BluetoothProfile.STATE_CONNECTED:
                                Log.i(TAG, "Connected to GATT server. BluetoothProfile.STATE_CONNECTED device.getAddress()  " +   device.getAddress().toString());
                                Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                handler.post(()->{
                                    bundleСервер.clear();
                                    bundleСервер.putString("Статус","SERVERGATTConnectiong");
                                    bundleСервер.putString("Дивайс",device.getName());
                                    mutableLiveDataGATTServer.setValue(bundleСервер);
                                });
                                server.connect(device,true);
                                device.createBond();
                                break;
                            case BluetoothProfile.STATE_DISCONNECTED:
                                Log.i(TAG, "Connected to GATT server. BluetoothProfile.STATE_CONNECTING   device.getAddress() "+  device.getAddress());
                                break;
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
                public void onServiceAdded(int status, BluetoothGattService service) {
                    super.onServiceAdded(status, service);
                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                }

                @SuppressLint("NewApi")
                @Override
                public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic,
                                                         boolean preparedWrite,
                                                         boolean responseNeeded, int offset, byte[] value) {
                    super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
                    try{
                        BluetoothGattService services = characteristic.getService();
                        if (services!=null) {
                            BluetoothGattCharacteristic characteristicsДляСерверОтКлиента = services.getCharacteristic(uuidКлючСервераGATTЧтениеЗапись);
                            if (characteristicsДляСерверОтКлиента!=null && characteristic!=null && value!=null ){
                                characteristicsServerОтКлиента=characteristic;
                                handler.post(()->{
                                    // TODO: 12.02.2023 ОТВЕТ КЛИЕНТУ
                                    МетодОтветаОТGATTСеврераКлиентуДанныеми(device, requestId,  offset, value);
                                    Log.i(TAG, "onCharacteristicWriteRequest to GATT server  characteristicsДляСерверОтКлиента"
                                            + characteristicsДляСерверОтКлиента+
                                            " characteristicsДляСерверОтКлиента.getUuid() " +characteristicsДляСерверОтКлиента.getUuid());
                                });
                            }
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

                @SuppressLint("NewApi")
                private void МетодОтветаОТGATTСеврераКлиентуДанныеми(@NonNull BluetoothDevice device,
                                                                     @NonNull int requestId,
                                                                     @NonNull  int offset,
                                                                     @NonNull   byte[] value) {
                    try {
                        final Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу = {0};
                        if (value != null) {
                            String ПришлиДанныеОтКлиентаЗапрос = new String(value);
                            Log.i(TAG, "Connected to GATT server  newValueПришлиДАнныеОтКлиента." + new String(value) +
                                    " value.length " + value.length );
                            // TODO: 07.02.2023  Записываем ВБАзу Данные
                            if (value.length > 0 &&   characteristicsServerОтКлиента != null) {
                                // TODO: 13.02.2023
                                String ДанныеСодранныеОтКлиента;
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
                                } else {
                                    ДанныеСодранныеОтКлиента = "Девайс отмечен..." + "\n" + device.getName().toString() +
                                            "\n" + device.getAddress().toString() +
                                            "\n" + new Date().toLocaleString()
                                            + "\n" + ПришлиДанныеОтКлиентаЗапрос;
                                }

                                // TODO: 13.02.2023  Метод Записи Девайса в базу
                                МетодЗаписиДевайсавБазу(device, РезультатЗаписиДанныхПИнгаДвайсаВБАзу, ПришлиДанныеОтКлиентаЗапрос, ДанныеСодранныеОтКлиента);
                                Log.i(TAG, "SERVER#SousAvtoSuccess" + " " + new Date().toLocaleString());
                                // TODO: 12.02.2023  ОТВЕТ !!!
                                server.notifyCharacteristicChanged(device, characteristicsServerОтКлиента, true);

                            } else {
                                Log.i(TAG, "SERVER#SousAvtoNULL" + " " + new Date().toLocaleString());
                                characteristicsServerОтКлиента.setValue("SERVER#SousAvtoNULL");
                                bundleСервер.clear();
                                bundleСервер.putString("Статус", "SERVER#SousAvtoNULL");
                                bundleСервер.putString("Дивайс", device.getName());
                                mutableLiveDataGATTServer.setValue(bundleСервер);
                            }
                        }
                        // TODO: 12.02.2023  ОТВЕТ !!!
                        server.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, new Date().toLocaleString().toString().getBytes(StandardCharsets.UTF_8));
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

                private void МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,  Integer[] РезультатЗаписиДанныхПИнгаДвайсаВБАзу, String ПришлиДанныеОтКлиентаЗапрос, String ДанныеСодранныеОтКлиента) {
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
                                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                    v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                                    if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу[0] > 0) {
                                        // TODO: 09.02.2023 сам статус дляОтвета;
                                        Log.i(TAG, "SERVER#SousAvtoSuccess РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " "
                                                + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoSuccess");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                                            bundleСервер.putString("Дивайс", device.getName());
                                            mutableLiveDataGATTServer.setValue(bundleСервер);
                                            characteristicsServerОтКлиента.setValue("SERVER#SousAvtoSuccess");
                                    } else {
                                        // TODO: 09.02.2023 сам статус дляОтвета;
                                            Log.i(TAG, "SERVER#SousAvtoERROR РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + " " +
                                                    "" + РезультатЗаписиДанныхПИнгаДвайсаВБАзу);
                                            bundleСервер.clear();
                                            bundleСервер.putString("Статус","SERVER#SousAvtoERROR");
                                            bundleСервер.putString("ОтветКлиентуВсатвкаВБАзу","Пинг прошел ," + "\n" +
                                                    "Без записи в базу !!!");
                                            bundleСервер.putString("Дивайс", device.getName());
                                            mutableLiveDataGATTServer.setValue(bundleСервер);
                                            characteristicsServerОтКлиента.setValue("SERVER#SousAvtoERROR");
                                    }
                                }
                            });
                    completableВставка.blockingSubscribe();
                }

                @NonNull
                private String МетодГенерацииUUID() {
                    String uuid=    UUID.randomUUID().toString().replace("-","").substring(0,20);
                    uuid=uuid.replaceAll("[a-zA-Z]","");
                    //uuid= CharMatcher.any().replaceFrom("[A-Za-z0-9]", "");
                    return uuid;
                }

                @Override
                public void onNotificationSent(BluetoothDevice device, int status) {
                    super.onNotificationSent(device, status);
                    try{
                        Log.i(TAG, "Connected to GATT server  onNotificationSent status ."+status);
                        server.sendResponse(device, status, BluetoothGatt.GATT_SUCCESS, status, "YOUR_RESPONSEonNotificationSent".getBytes(StandardCharsets.UTF_8));
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
                            BluetoothGattCharacteristic.PROPERTY_WRITE |
                            BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PROPERTY_INDICATE |
                            BluetoothGattCharacteristic.PROPERTY_BROADCAST |
                            BluetoothGattCharacteristic.PERMISSION_WRITE);
            characteristic.addDescriptor(new
                    BluetoothGattDescriptor(uuidКлючСервераGATTЧтениеЗапись,
                    BluetoothGattCharacteristic.PERMISSION_WRITE | BluetoothGattCharacteristic.PERMISSION_READ
                            | BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_WRITE |
                            BluetoothGattCharacteristic.PROPERTY_INDICATE |
                            BluetoothGattCharacteristic.PROPERTY_BROADCAST
                            | BluetoothGattCharacteristic.PROPERTY_NOTIFY));
            service.addCharacteristic(characteristic);
            // TODO: 12.02.2023 добавлев в сервер
            server.addService(service);
            Log.d(this.getClass().getName(), "\n" + " pairedDevices.size() " + pairedDevices.size());
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
    private void МетодПолучениеЛокацииGPS() {
        try{
                if (lastLocation != null) {
                    locationListener.onLocationChanged(lastLocation);
                    while (lastLocation.isComplete()==false);
                        Log.i(TAG, "MyLocationListener GPS longitude "+lastLocation);
                        String cityName = null;
                        Geocoder gcd = new Geocoder(context, Locale.getDefault());
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

                        }
                        Log.i(TAG, "MyLocationListener GPS addressesgetGPS "+addressesgetGPS);
                    Log.i(TAG, "locationListener"+ " " +locationListener);
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

    public String МетодПолучениеServerСканеарКлюча_OndeSignal(@NonNull String КлючДляFirebaseNotification) {
        final String[] ВозврящаетсяКлючScannerONESIGNAl = {null};
        try {
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable observableПолученияКлючаОтСервераOneSignal=  Observable.interval(20, TimeUnit.SECONDS)
                    .take(3,TimeUnit.MINUTES)
                    .subscribeOn(Schedulers.io())
                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            // TODO: 01.02.2023 Получение Новго Ключа Для Сканера
                            ВозврящаетсяКлючScannerONESIGNAl[0] =     МетодПолучениеКлючаОтСлужбыONESIGNALAndFirebase(КлючДляFirebaseNotification);
                            Log.d(context.getClass().getName(), "  Observable.interval    ВозврящаетсяКлючScannerONESIGNAl[0] " +   ВозврящаетсяКлючScannerONESIGNAl[0]);
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(context.getClass().getName(), "  doOnError МетодПолучениеНовгоКлюча_OndeSignal "  +"\n" +throwable.getMessage().toString());
                        }
                    })
                    .takeWhile(new Predicate<Object>() {
                        @Override
                        public boolean test(Object o) throws Throwable {
                            // TODO: 26.12.2021
                            Log.w(context.getClass().getName(), "   takeWhile ВозврящаетсяКлючScannerONESIGNAl " +
                                    "" +Thread.currentThread().getName()+ "  ВозврящаетсяКлючScannerONESIGNAl " + ВозврящаетсяКлючScannerONESIGNAl[0]);
                            if (   ВозврящаетсяКлючScannerONESIGNAl[0] !=null) {
                                Log.w(context.getClass().getName(), "  ДЛЯ ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ (телефона)Ключ ПришелОтСЕРВЕРА SUCEESSSSSS !!!@!  " +
                                        " takeWhile МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal САМ КЛЮЧ ::::"
                                        + ВозврящаетсяКлючScannerONESIGNAl[0] +"\n"+
                                        " Thread.currentThread().getName() " +Thread.currentThread().getName());
                                return false;
                            }else {
                                return true;
                            }
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(context.getClass().getName(), "  doOnError МетодПолучениеНовгоКлюча_OndeSignal "  +"\n" +throwable.getMessage().toString());
                            return false;
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.w(context.getClass().getName(), " doOnTerminate  ВозврящаетсяКлючScannerONESIGNAl" + ВозврящаетсяКлючScannerONESIGNAl[0]);
                            // TODO: 06.01.2022
                            // TODO: 06.01.2022
                            Log.w(context.getClass().getName(), "  onComplete МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                    " Thread.currentThread().getName() " +Thread.currentThread().getName());
                        }
                    });
// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данные
            observableПолученияКлючаОтСервераOneSignal.subscribe();
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

        return ВозврящаетсяКлючScannerONESIGNAl[0];
    }

    private String МетодПолучениеКлючаОтСлужбыONESIGNALAndFirebase(@NonNull String КлючДляFirebaseNotification) {
        String ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = null;
        try{
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ Scanner  OneSignal........ "+ КлючДляFirebaseNotification +"\n");
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            // todo OneSignal Initialization
            OneSignal.initWithContext(context);
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
    public Integer МетодЗаписиОтмечаногоСотрудникаВБАзу(@NonNull Context appContext) {
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
    public Integer МетодЗаписиОтмечаногоСотрудникаВБАзу(@NonNull ContentValues[] contentValues) {
        Integer РезульататЗАписиНовогоДивайса=0;
        try{
            Log.i(this.getClass().getName(), "запись сотрудника в базу"+ " linkedHashMapДанныеДляЗаписи) " + contentValues) ;
            String provider = "com.sous.server.providerserver";
            Uri uri = Uri.parse("content://"+provider+"/" +"scannerserversuccess" + "");
            ContentResolver resolver = context.getContentResolver();
            РезульататЗАписиНовогоДивайса=   resolver.bulkInsert(uri, contentValues);
            Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ insertData  РезульататЗАписиНовогоДивайса ЗНАЧЕНИЯ  " +  РезульататЗАписиНовогоДивайса.toString() );
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
    public Integer МетодПоискДАнныхПоБазе(@NonNull String СамЗапрос) {
        Integer   ВерсияДАнных = 0;
        try{
            Log.i(this.getClass().getName(), "запись сотрудника в базу"+ " linkedHashMapДанныеДляЗаписи) " + СамЗапрос) ;
            String provider = "com.sous.server.providerserver";
            Uri uri = Uri.parse("content://"+provider+"/" +"scannerserversuccess" + "");
            ContentResolver resolver = context.getContentResolver();
             Cursor cursorПолучаемДЛяСевреа=   resolver.query(uri,new String[]{СамЗапрос},null,null,null,null);
            cursorПолучаемДЛяСевреа.moveToFirst();
             if (cursorПолучаемДЛяСевреа.getCount()>0){
                 ВерсияДАнных=      cursorПолучаемДЛяСевреа.getInt(0);
                 Log.i(this.getClass().getName(), "ВерсияДАнных"+ ВерсияДАнных) ;
                 ВерсияДАнных++;
             }
            Log.w(context.getClass().getName(), " РЕЗУЛЬТАТ insertData  cursorПолучаемДЛяСевреа  " +  cursorПолучаемДЛяСевреа.toString() );
            cursorПолучаемДЛяСевреа.close();
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
