package com.sous.scanner.Services;

import static java.util.stream.Collectors.groupingBy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;
import com.sous.scanner.Database.CREATE_DATABASEScanner;
import com.sous.scanner.Firebase.MyFirebaseMessagingServiceScanner;
import com.sous.scanner.Errors.SubClassErrors;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceClientBLE extends IntentService {
    private SQLiteDatabase sqLiteDatabase;
    private CREATE_DATABASEScanner createDatabaseScanner;
    public LocalBinderСканнер binder = new LocalBinderСканнер();
    private Context context;
    private Activity activity;
    private String TAG;
    private Handler handler;

    private BluetoothManager bluetoothManagerServer;
    private BluetoothAdapter bluetoothAdapterPhoneClient;
    private BluetoothAdapter bluetoothAdapterGATT;
    protected LocationManager locationManager;

    public ServiceClientBLE() {
        super("ServiceClientBLE");
    }

    private MutableLiveData<String> mediatorLiveDataGATT;
    private Long version = 0l;
    private String getWorkerStateClient;
    private UUID getPublicUUID;
    private BluetoothGatt gatt;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 07.02.2023 клиент сервер
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            bluetoothManagerServer = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);

            bluetoothAdapterPhoneClient = (BluetoothAdapter) bluetoothManagerServer.getAdapter();

            setingEnableApapterBLE();

            getListDeviceForBluApdapter();

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            this.createDatabaseScanner = new CREATE_DATABASEScanner(context);
            this.sqLiteDatabase = createDatabaseScanner.getССылкаНаСозданнуюБазу();
            TAG = getClass().getName().toString();
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
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
        public ServiceClientBLE getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceClientBLE.this;
        }

        public void linkToDeath(DeathRecipient deathRecipient) {
            Log.i(this.getClass().getName(), "deathRecipient " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
    public void МетодКлиентЗапускСканера(@NonNull Handler handler, @NonNull Activity activity,
                                         @NonNull MutableLiveData<String> mediatorLiveDatagatt,
                                         @NonNull String ДействиеДляСервераGATTОТКлиента) {
        this.context = activity;
        this.activity = activity;
        this.handler = handler;
        this.mediatorLiveDataGATT = mediatorLiveDatagatt;
        this.getWorkerStateClient = ДействиеДляСервераGATTОТКлиента;
        // TODO: 08.12.2022 уснатавливаем настройки Bluetooth
        try {

            // TODO: 27.02.2023 Переплучние Bluettoth
            МетодЗапускаСканированиеКлиент();

            Log.w(this.getClass().getName(), "   bluetoothManager  " + bluetoothManagerServer + " bluetoothAdapter "
                    + bluetoothAdapterPhoneClient + "mediatorLiveDataGATT " + mediatorLiveDataGATT);

            Log.w(this.getClass().getName(), "   bluetoothManager  " + bluetoothManagerServer + " bluetoothAdapter "
                    + bluetoothAdapterPhoneClient + "mediatorLiveDataGATT " + mediatorLiveDataGATT);
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


    @SuppressLint({"MissingPermission"})
    private void МетодЗапускаСканированиеКлиент() {
        try {
            Log.d(TAG, "МетодЗапускаСканированиеКлиент: Запускаем.... Метод Сканирования Для Android");
            mediatorLiveDataGATT.setValue("GATTCLIENTProccessing");
            Log.d(TAG, "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  " + "\n+" +
                    "" + binder.isBinderAlive() + " date " + new Date().toString().toString() + "" +
                    "\n" + " POOL " + Thread.currentThread().getName() +
                    "\n" + " ALL POOLS  " + Thread.getAllStackTraces().entrySet().size());//
            // TODO: 12.02.2023 адреса разыне колиентов
            getPublicUUID = ParcelUuid.fromString("10000000-0000-1000-8000-00805f9b34fb").getUuid();

            LinkedHashMap<String, UUID> BluetoothСерверов = new LinkedHashMap<>();
            // TODO: 11.02.2023 СПИСОК СЕРВЕРОВ
            BluetoothСерверов.put(getPublicUUID.toString(), getPublicUUID);

            Log.d(this.getClass().getName(), "\n" + " pairedDevices.size() " + BluetoothСерверов.size());
            // TODO: 26.01.2023 начало сервера GATT
            Flowable flowableЦиклСервера = Flowable.fromIterable(BluetoothСерверов.entrySet())
                    .onBackpressureBuffer(true)
                    .subscribeOn(Schedulers.newThread())
                    .throttleFirst(250,TimeUnit.MICROSECONDS)
                    .repeatWhen(repeat -> repeat.delay(5, TimeUnit.SECONDS))
                    .takeWhile(new Predicate<Map.Entry<String, UUID>>() {
                        @Override
                        public boolean test(Map.Entry<String, UUID> stringUUIDEntry) throws Throwable {
                            if (mediatorLiveDataGATT.getValue().equalsIgnoreCase("SERVER#SousAvtoSuccess")
                                    || mediatorLiveDataGATT.getValue().equalsIgnoreCase("SERVER#SousAvtoDONTDIVICE")
                                    || mediatorLiveDataGATT.getValue().equalsIgnoreCase("SERVER#SousAvtoERROR")) {


                                Log.i(TAG, " mediatorLiveDataGATT.getValue() " + mediatorLiveDataGATT.getValue() + new Date().toLocaleString());
                                МетодВыключениеКлиентаGatt();

                                return false;
                            } else {
                                Log.i(TAG, " mediatorLiveDataGATT.getValue()  " + mediatorLiveDataGATT.getValue() + new Date().toLocaleString());
                                return true;
                            }
                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .take(5, TimeUnit.MINUTES)
                    .map(new Function<Map.Entry<String, UUID>, Object>() {
                        @Override
                        public Object apply(Map.Entry<String, UUID> stringUUIDEntry) throws Throwable {
                            if (bluetoothAdapterGATT != null && bluetoothAdapterGATT.isEnabled() == true) {
                                //TODO:
                                Set<BluetoothDevice> deviceClientGattEnable = bluetoothAdapterGATT.getBondedDevices();

                                if (deviceClientGattEnable.size() > 0) {
                                    ///TODO: Когда есть реальные Девайсы BLE
                                    loopAllDiveceFotConnecting(stringUUIDEntry, deviceClientGattEnable);

                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                            " deviceClientGattEnable " + deviceClientGattEnable);
                                } else {

                                    ///TODO:Довавляем Зарание созданные Адреса Сервера Gatt
                                    BluetoothDevice bluetoothDevice = bluetoothAdapterPhoneClient.getRemoteDevice("98:2F:F8:19:BC:F7");//TODO: HUAWEI MatePad SE


                                    // TODO: 16.07.2024 Если НЕ бонгинг не прошел Ну сопрязение то пытаемся его Провести
                                    scanStateBorningDevice(bluetoothDevice);

                                    ///TODO: когда ессть сами устройста Manual
                                    manualDiveceFotConnecting(stringUUIDEntry, bluetoothDevice);

                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "\n" +
                                            " deviceClientGattEnable " + deviceClientGattEnable);

                                }


                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            }
                            return stringUUIDEntry;
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
                            Log.d(TAG, "  МетодЗапускаЦиклаСерверовGATT()... uuidКлючСервераGATTЧтениеЗапись " + getPublicUUID);
                        }
                    });
            flowableЦиклСервера.subscribe();
            Log.i(TAG, " ОтветОтGattServer  " + new Date().toLocaleString());
            /// mediatorLiveDataGATT
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


    //TODO запускаем Когда Адапетр BLE РЕАЛЬНЫЕ
    @SuppressLint("MissingPermission")
    private void loopAllDiveceFotConnecting(Map.Entry<String, UUID> stringUUIDEntry, Set<BluetoothDevice> deviceClientGatt) {
        ///TODO: оБработка реальный адресов BLE
        try{
        deviceClientGatt.forEach(new java.util.function.Consumer<BluetoothDevice>() {

            @Override
            public void accept(BluetoothDevice bluetoothDevice) {

                Log.d(this.getClass().getName(), " bluetoothDevice " + bluetoothDevice);

                // TODO: 16.07.2024 Если НЕ бонгинг не прошел Ну сопрязение то пытаемся его Провести
                scanStateBorningDevice(bluetoothDevice);

                Log.d("BT", "bluetoothDevice.getName(): " + bluetoothDevice.getName());
                Log.d("BT", "bluetoothDevice.getAddress(): " + bluetoothDevice.getAddress());

                // TODO: 12.02.2023  запускаем задачу в потоке
                BluetoothGattCallback bluetoothGattCallback =
                        МетодРаботыСТекущийСерверомGATT(bluetoothDevice, stringUUIDEntry.getValue());

                // TODO: 26.01.2023  конец сервера GATT
                МетодЗапускаGATTКлиента(bluetoothDevice, bluetoothGattCallback);

                Log.d(TAG, "  МетодЗапускаЦиклаСерверовGATT().... "
                        + "uuidКлючСервераGATTЧтениеЗапись " + getPublicUUID + " bluetoothGattCallback " + bluetoothGattCallback);
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }


    @SuppressLint("MissingPermission")
    private void scanStateBorningDevice(@NonNull BluetoothDevice bluetoothDevice) {
try{

       if(bluetoothDevice.getType()==0 || bluetoothDevice.getType()==10){
           bluetoothDevice.createBond();

           Log.d(this.getClass().getName(), "\n" + " class " +
                   Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                   " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                   " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                   " bluetoothDevice.getType() " + bluetoothDevice.getType());
       }
       else{

           Log.d(this.getClass().getName(), "\n" + " class " +
                   Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                   " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                   " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                   " bluetoothDevice.getType() " + bluetoothDevice.getType());
       }



        Log.d(this.getClass().getName(), "\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " bluetoothDevice.getType() " + bluetoothDevice.getType());
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





    //TODO запускаем Когда Адапетр BLE ИСКУСТВЕННЫЕ АДРЕС сервера
    @SuppressLint("MissingPermission")
    private void manualDiveceFotConnecting(Map.Entry<String, UUID> stringUUIDEntry,BluetoothDevice deviceClientGatt) {
        ///TODO: оБработка реальный адресов BLE


                Log.d(this.getClass().getName()," bluetoothDevice " +deviceClientGatt  );

                Log.d("BT", "bluetoothDevice.getName(): " + deviceClientGatt.getName());
                Log.d("BT", "bluetoothDevice.getAddress(): " + deviceClientGatt.getAddress());




                // TODO: 12.02.2023  запускаем задачу в потоке
                BluetoothGattCallback bluetoothGattCallback=
                        МетодРаботыСТекущийСерверомGATT(deviceClientGatt, stringUUIDEntry.getValue());

                // TODO: 26.01.2023  конец сервера GATT
                МетодЗапускаGATTКлиента(deviceClientGatt, bluetoothGattCallback);

                Log.d(TAG, "  МетодЗапускаЦиклаСерверовGATT().... "
                        +"uuidКлючСервераGATTЧтениеЗапись " + getPublicUUID + " bluetoothGattCallback " +bluetoothGattCallback);

    }































    @SuppressLint("MissingPermission")
    private void setingEnableApapterBLE() {
        try{
                if (bluetoothAdapterPhoneClient !=null) {
                    if (bluetoothAdapterPhoneClient.isEnabled()==false){
                        bluetoothAdapterPhoneClient.enable();
                    }





                    Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+
                             " bluetoothAdapter  "+ bluetoothAdapterPhoneClient);
                }

            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+
                     " bluetoothAdapter " + bluetoothAdapterPhoneClient);
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
    }


    @SuppressLint("MissingPermission")
    private void getListDeviceForBluApdapter() {
        try{
            bluetoothAdapterGATT=  bluetoothManagerServer.getAdapter() ;
            bluetoothAdapterGATT.getBondedDevices().forEach(new java.util.function.Consumer<BluetoothDevice>() {
                @Override
                public void accept(BluetoothDevice bluetoothDevice) {
                    Log.d("BT", "bluetoothDevice.getName(): " + bluetoothDevice.getName());
                    Log.d("BT", "bluetoothDevice.getAddress(): " + bluetoothDevice.getAddress());

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+
                    " bluetoothAdapter " + bluetoothAdapterPhoneClient);
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
    }




    @SuppressLint("MissingPermission")
                private BluetoothGattCallback МетодРаботыСТекущийСерверомGATT(@NonNull  BluetoothDevice bluetoothDevice,@NonNull  UUID UuidГлавныйКлючСерверGATT) {
                    // TODO: 25.01.2023 ПЕРВЫЙ ВАРИАНТ СЕРВЕР gatt
           BluetoothGattCallback       bluetoothGattCallback = null;
        try{

                                bluetoothGattCallback = new BluetoothGattCallback() {
                        @Override
                        public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                            int newState) {
                            try{
                                switch (newState){
                                    case BluetoothProfile.STATE_CONNECTED :
                                        Log.i(TAG, "Connected to GATT client. BluetoothProfile.STATE_CONNECTED ###1 onConnectionStateChange  " +
                                                ""+new Date().toLocaleString());
                                        handler.post(()->{
                                            mediatorLiveDataGATT.setValue("SERVER#SERVER#SouConnect");
                                        });
                                         Boolean ДанныеОТGATTССевромGATT=         gatt.discoverServices();
                                        Log.d(TAG, "Trying to ДанныеОТGATTССевромGATT " + ДанныеОТGATTССевромGATT + " newState " +newState);
                                        break;
                                    case BluetoothProfile.STATE_DISCONNECTED :
                                    case BluetoothGatt.GATT_FAILURE:
                                        Log.d(TAG, "Trying to ДанныеОТGATTССевромGATT "  + " newState " +newState);
                                        break;
                                    case 133 :

                                        // TODO: 16.07.2024 когда ошивка разрываем сообщение  
                                        МетодВыключениеКлиентаGatt();
                                        Log.d(TAG, "Trying to ДанныеОТGATTССевромGATT "  + " newState " +newState);
                                        break;
                                    case BluetoothGatt.GATT_CONNECTION_CONGESTED :
                                        Log.d(TAG, "Trying to ДанныеОТGATTССевромGATT "  + " newState " +newState);
                                        handler.post(()->{
                                            mediatorLiveDataGATT.setValue("SERVER#SERVER#SousAvtoReetBoot");
                                        });

                                        break;
                                }
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
                        }
                        // TODO: 26.01.2023
                        @Override
                        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                            super.onServicesDiscovered(gatt, status);
                            try{
                                if (status == BluetoothGatt.GATT_SUCCESS) {
                                    BluetoothGattService services = gatt.getService(UuidГлавныйКлючСерверGATT);
                                    if (services!=null) {
                                        Boolean КоннектССевромGATT = gatt.connect();

                                       gatt.beginReliableWrite();

                                        Log.d(TAG, "Trying КоннектССевромGATT " + КоннектССевромGATT);
                                        BluetoothGattCharacteristic characteristics = services.getCharacteristic(getPublicUUID);
                                        gatt.setCharacteristicNotification(characteristics, true);
                                        characteristics.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
                                        if (characteristics != null) {
                                            characteristics.setValue("действие:" + getWorkerStateClient);
                                            // TODO: 20.02.2023  заполняем данными  клиента
                                            ConcurrentSkipListSet<String> linkedHashMapДанныеКлиентаДляGATT = МетодЗаполенияДаннымиКлиентаДЛяGAtt();
                                            //TODO add Data For GATT Client
                                            characteristics.setValue(linkedHashMapДанныеКлиентаДляGATT.toString());
                                            // TODO: 20.02.2023  послылвем Сервреу Данные
                                            Boolean successОтправка = gatt.writeCharacteristic(characteristics);

                                            if (successОтправка) {
                                                mediatorLiveDataGATT.setValue("SERVER#SousAvtoSuccess");
                                                gatt.executeReliableWrite();
                                            }else {
                                                gatt.abortReliableWrite();
                                            }

                                            Log.i(TAG, "characteristics" + new Date().toLocaleString()+  " characteristics "
                                                    +characteristics+ " successОтправка " +successОтправка+
                                                    " ДействиеДляСервераGATTОТКлиента "+ getWorkerStateClient);
                                        }
                                    }else {
                                        mediatorLiveDataGATT.setValue("SERVER#SousAvtoERROR");
                                        Log.i(TAG, "GATT CLIENT Proccessing from GATT server.GATTCLIENTProccessing " + new Date().toLocaleString());

                                    }
                                }else{
                                    mediatorLiveDataGATT.setValue("SERVER#SousAvtoERROR");
                                    Log.i(TAG, "GATT CLIENT Proccessing from GATT server.GATTCLIENTProccessing " + new Date().toLocaleString());
                                }
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
                        }

                        // TODO: 20.02.2023 Метод Вытаскиеваем ДАнные Симки пользователя  
                        @NonNull
                        private ConcurrentSkipListSet<String> МетодЗаполенияДаннымиКлиентаДЛяGAtt() {
                            ConcurrentSkipListSet<String> linkedHashMapДанныеКлиентаДляGATT = new ConcurrentSkipListSet<>();
                            try {
                                //TODO :  отправлдяем данные
                               linkedHashMapДанныеКлиентаДляGATT.add(   getWorkerStateClient +"\n");
                                String getIMEI = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                                linkedHashMapДанныеКлиентаДляGATT.add(  getIMEI+"\n");
                                linkedHashMapДанныеКлиентаДляGATT.add(  new Date().toLocaleString()+"\n");

                                Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " время " +new Date().toLocaleString() +
                                        getIMEI + " getIMEI "+ " linkedHashMapДанныеКлиентаДляGATT " +linkedHashMapДанныеКлиентаДляGATT);

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
                            return linkedHashMapДанныеКлиентаДляGATT;
                        }

                        @Override
                        public void onCharacteristicRead(@NonNull BluetoothGatt gatt, @NonNull BluetoothGattCharacteristic characteristic, @NonNull byte[] value, int status) {
                            super.onCharacteristicRead(gatt, characteristic, value, status);
                            byte[] newValueПришлиДАнныеОтКлиента= characteristic.getValue();
                            Log.i(TAG, "Connected to GATT server  newValueПришлиДАнныеОтКлиента."+new String(newValueПришлиДАнныеОтКлиента));
                        }

                        @Override
                        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                            super.onCharacteristicChanged(gatt, characteristic);
                            try{
                                if (characteristic!=null) {
                                    byte[] newValueОтветОтСервера = characteristic.getValue();
                                    if (newValueОтветОтСервера!=null) {
                                        String ОтветОтСервераОбратно=new String(newValueОтветОтСервера);
                                        Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + " время " +new Date().toLocaleString()+ " ОтветОтСервераОбратно "+ОтветОтСервераОбратно );
                                        // TODO: 30.01.2023  ПОСЫЛАЕМ ОТВЕТ ОТ СЕРВЕРА СТАТУСА
                                        handler.post(()->{
                                            mediatorLiveDataGATT.setValue(ОтветОтСервераОбратно);
                                        });
                                        // TODO: 20.02.2023 закрыаем сесию ссервром

                                    }
                                    Log.i(this.getClass().getName(),  " " +Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " время " +new Date().toLocaleString()+ " characteristic "+characteristic );
                                }
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
                        }

                        @Override
                        public void onServiceChanged(@NonNull BluetoothGatt gatt) {
                            super.onServiceChanged(gatt);
                            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() + " gatt " +gatt);
                        }
                    };
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
                    return  bluetoothGattCallback;
                }












    @SuppressLint("MissingPermission")
                private void МетодЗапускаGATTКлиента(@NonNull BluetoothDevice bluetoothDevice,
                                                     BluetoothGattCallback bluetoothGattCallback) {
                    try{
                    gatt =      bluetoothDevice.connectGatt(context, false,
                            bluetoothGattCallback,
                            BluetoothDevice.TRANSPORT_AUTO,
                            BluetoothDevice.PHY_OPTION_S8,handler);
                    Log.d(this.getClass().getName(), "\n" + " bluetoothDevice" + bluetoothDevice);
                    gatt.requestConnectionPriority(BluetoothGatt.CONNECTION_PRIORITY_HIGH);
                    //gatt.setPreferredPhy(BluetoothDevice.PHY_LE_2M_MASK,BluetoothDevice.PHY_LE_2M_MASK,BluetoothDevice.PHY_OPTION_S2);
                    int bondstate = bluetoothDevice.getBondState();
                    Log.d(TAG, "Trying to write characteristic..., first bondstate " + bondstate);
                    switch (bondstate) {
                        
                        case BluetoothDevice.DEVICE_TYPE_UNKNOWN:
                        case BluetoothDevice.BOND_NONE:
                            
                            handler.post(()->{
                                mediatorLiveDataGATT.setValue("SERVER#SousAvtoDONTDIVICE");
                            });
                            bluetoothDevice.createBond();
                            Log.i(TAG, "BluetoothDevice.BOND_NONE" + bondstate);//Указывает, что удаленное устройство не связано (сопряжено).
                            break;
                            
                        case BluetoothDevice.BOND_BONDING:
                            handler.post(()->{
                                mediatorLiveDataGATT.setValue("SERVER#SousAvtoBOND_BONDING");
                            });
                            Log.i(TAG, "BluetoothDevice.BOND_BONDING" + bondstate);//Указывает, что удаленное устройство не связано (сопряжено).
                            break;

                        case BluetoothDevice.BOND_BONDED:
                            handler.post(()->{
                                mediatorLiveDataGATT.setValue("SERVER#SousAvtoBOND_BONDING");
                            });
                            Log.i(TAG, "BluetoothDevice.BOND_BONDING" + bondstate);//Указывает, что удаленное устройство не связано (сопряжено).
                            break;
                    }

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
                }

                @SuppressLint("MissingPermission")
                public void МетодВыключениеКлиентаGatt() {
        try{
                        if (gatt!=null) {
                            gatt.disconnect();
                            Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() + " gatt " +gatt);}
                        Log.i(TAG, "GATT CLIENT Proccessing from GATT server.SERVER#SousAvtoEXIT " +
                                new Date().toLocaleString() + getWorkerStateClient
                                + " gatt "+gatt);
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
                }



    // TODO: 01.02.2023  класс Запуск OneSignala



    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР

    public String МетодПолучениеНовогоДляСканеарКлюча_OndeSignal(@NonNull String КлючДляFirebaseNotification) {
        final String[] ВозврящаетсяКлючScannerONESIGNAl = {null};
        try {
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable observableПолученияКлючаОтСервераOneSignal=  Observable.interval(5, TimeUnit.SECONDS)
                    .delay(3,TimeUnit.SECONDS)
                    .take(3,TimeUnit.MINUTES)
                    .subscribeOn(Schedulers.single())
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            // TODO: 01.02.2023 Получение Новго Ключа Для Сканера
                            ВозврящаетсяКлючScannerONESIGNAl[0] =     МетодПолучениеКлючаОтСлужбыONESIGNALAndFirebase(КлючДляFirebaseNotification);
                            Log.d(context.getClass().getName(), "  Observable.interval    ВозврящаетсяКлючScannerONESIGNAl[0] " +   ВозврящаетсяКлючScannerONESIGNAl[0]);
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
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
            FirebaseMessagingService firebaseMessagingService =new MyFirebaseMessagingServiceScanner();
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  FirebaseMessagingService"  );
            // TODO: 07.12.2021
            firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ  КОНЕЦ  OneSignal........  56bbe169-ea09-43de-a28c-9623058e43a2 " );
            // TODO: 15.12.2021 настройки onesigmnal
            Map<String, String> params = new HashMap<String, String>();
            OneSignal.sendTag("Authorization", "Basic 56bbe169-ea09-43de-a28c-9623058e43a2");
            OneSignal.sendTag("Content-type", "application/json");
            OneSignal.sendTag("grp_msg", "scanner");
            OneSignal.sendTag("android_background_data", "true");
            OneSignal.sendTag("content_available", "true");
            //TODO srating......  oneSignal
          String  PushОнеСигнала = OneSignal.getDeviceState().getPushToken();

            ПоулчаемДляТекущегоПользователяIDОтСЕРВРЕРАOneSignal = OneSignal.getDeviceState().getUserId();
            // TODO: 15.12.2021
            Log.d(this.getClass().getName(), "  ПОСЛЕ КЛЮЧ ДЛЯ  OneSignal........  56bbe169-ea09-43de-a28c-9623058e43a2 "+"\n"+
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

}
