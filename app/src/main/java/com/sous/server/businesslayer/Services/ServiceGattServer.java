package com.sous.server.businesslayer.Services;

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
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;


import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Eventbus.MessageScannerStartRecyreViewFragment;


import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceGattServer extends IntentService {
    protected SQLiteDatabase sqLiteDatabase;
    public LocalBinderСерверBLE binder = new LocalBinderСерверBLE();


    protected BluetoothGattServer getBluetoothGattServer;
    protected BluetoothManager bluetoothManagerServer;
    protected BluetoothAdapter bluetoothAdapter;
    protected Long version = 0l;
    protected ConcurrentHashMap<String, Bundle> concurrentHashMapDeviceBTE;


    protected List<Address> addressesgetGPS;
    protected UUID getPublicUUID;


    //TODO: Local
    protected FusedLocationProviderClient fusedLocationClientGoogle;

    protected LocationManager locationManager;
    protected  List<BluetoothDevice> getListGattServer ;


    public ServiceGattServer() {
        super("ServiceGattServer");
    }



    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());

            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            version = pInfo.getLongVersionCode();

            launchmanagerBLE();//TODO: запускаем Новый Манаджер BTE

            Log.d(getApplicationContext().getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }

    public class LocalBinderСерверBLE extends Binder {
        public ServiceGattServer getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceGattServer.this;
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
    protected void onHandleIntent(Intent intent) {
        try {


            Boolean getStatusEnableBlueadapter = enableBluetoothAdapter();

            callBackFromServiceToRecyreViewFragment(getStatusEnableBlueadapter);


            МетодИницилиазцииGpsGoogle();


            /*      //TODO:Тест Код запуск кода по расписанию
             *          */
            //testMetodShedule10Secynd();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  getStatusEnableBlueadapter " + getStatusEnableBlueadapter);


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

    private void testMetodShedule10Secynd() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" + "\n" +
                        " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " + "\n" +
                        " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " new Date()  " + new Date().toLocaleString());
            }
        }, 0, 10, TimeUnit.SECONDS);
    }


    @SuppressLint("MissingPermission")
    private void launchmanagerBLE() {
        try {
            concurrentHashMapDeviceBTE=new ConcurrentHashMap<>();
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            bluetoothManagerServer = (BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = (BluetoothAdapter) bluetoothManagerServer.getAdapter();
             getListGattServer = (List<BluetoothDevice>) bluetoothManagerServer.getConnectedDevices(BluetoothProfile.GATT_SERVER);
           // getListGattServer  = (List<BluetoothDevice>)   getBluetoothGattServer.getConnectedDevices();


            for (BluetoothDevice pairedDevice : getListGattServer )
            {
                Log.d("BT", "pairedDevice.getName(): " + pairedDevice.getName());
                Log.d("BT", "pairedDevice.getAddress(): " + pairedDevice.getAddress());
            }


            Log.d(getApplicationContext().getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }




//TODO: отвечам оюратно на фрагмент что включен Адаптер Blutooth
    private void callBackFromServiceToRecyreViewFragment(@NonNull    Boolean getStatusEnableBlueadapter) {
        try{
            MessageScannerStartRecyreViewFragment sendmessageScannerStartRecyreViewFragment= new MessageScannerStartRecyreViewFragment(getStatusEnableBlueadapter);
            //TODO: ответ на экран работает ообрубование или нет
                EventBus.getDefault().post(sendmessageScannerStartRecyreViewFragment);

            Log.d(getApplicationContext().getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                     " getStatusEnableBlueadapter "+getStatusEnableBlueadapter);

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






















    // TODO: 30.11.2022  НАчинаем КОД       сервер СКАНИРОВАНИЯ
    @SuppressLint("MissingPermission")
    private synchronized void МетодИницилиазцииGpsGoogle() {
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
                    //TODO:
                    if (task.isSuccessful()==true && task.isComplete() ==true) {
                        Location getlastLocation    =task.getResult();
                        Log.i(this.getClass().getName(),  "  " +Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString()+  "getlastLocation " +getlastLocation +
                                " task.isSuccessful() " +task.isSuccessful()+  "task.isComplete() "+task.isComplete());
                        // TODO: 21.02.2023 получаем Сами ДАнные от Location  полученого
                        МетодПолучениеЛокацииGPS(getlastLocation);


                        ///TODO: SuccessAddDevice
                        Bundle    bundleAddDeviceSuccess = new Bundle();
                        bundleAddDeviceSuccess.putString("Статус", "SERVER#SousAvtoStartingGPS");
                        concurrentHashMapDeviceBTE.put("SuccessAddDevice",bundleAddDeviceSuccess );


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






//TODO: Включаем адаптер is Enabled BLE
    @SuppressLint("MissingPermission")
    private Boolean enableBluetoothAdapter() {
        Boolean getStatusEnableBlueadapter=false;
        try{

            if (bluetoothAdapter!=null) {
                if (bluetoothAdapter.isEnabled() ==false) {
                    bluetoothAdapter.enable();
                }
                getStatusEnableBlueadapter=bluetoothAdapter.isEnabled();
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getStatusEnableBlueadapter  " +getStatusEnableBlueadapter);
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
        return  getStatusEnableBlueadapter ;
    }







    // TODO: Запускаем Сервер GATT
    @SuppressLint("MissingPermission")
    public  void mainstartingServerGatt() {
        try {

            Log.d(this.getClass().getName(), "1МетодЗапускаСканиваронияДляАндройд: Запускаем.... Метод Сканирования Для Android binder.isBinderAlive()  " + "\n+" +
                    "" + binder.isBinderAlive() + " date " + new Date().toString().toString() + "" +
                    "\n" + " POOL " + Thread.currentThread().getName() +
                    "\n" + " ALL POOLS  " + Thread.getAllStackTraces().entrySet().size());
            // TODO: 26.01.2023 Сервер КОД
            getBluetoothGattServer = bluetoothManagerServer.openGattServer(getApplicationContext(), new BluetoothGattServerCallback() {

                @Override
                public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
                    super.onConnectionStateChange(device, status, newState);
                    try {

                        МетодКоннектаДеконнектасКлиентамиGatt(device, status, newState);

                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );


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

                    ///TODO: SuccessAddDevice
                    Bundle    bundleAddDeviceSuccess = new Bundle();
                    bundleAddDeviceSuccess.putString("Статус", "SERVERGATTRUNNIGSTARTING");
                    concurrentHashMapDeviceBTE.put("SuccessAddDevice",bundleAddDeviceSuccess );

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );


                }

                @SuppressLint("NewApi")
                @Override
                public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic,
                                                         boolean preparedWrite,
                                                         boolean responseNeeded, int offset, byte[] value) {
                    super.onCharacteristicWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value);
                    try {
                        МетодОтвечаемКлиентуGatt(device, requestId, characteristic, offset, value);

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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }








                @Override
                public void onNotificationSent(BluetoothDevice device, int status) {
                    super.onNotificationSent(device, status);
                    try {
                    /*    TODo*/
                        МетодПодтвержедиеЧтоОперацияУведомленияБыла(device, status);

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
                        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
                    }
                }

                @Override
                public void onMtuChanged(BluetoothDevice device, int mtu) {
                    super.onMtuChanged(device, mtu);
                    Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
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
            //TODO:
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
    }


                //TODO:  метод после Запуска Сервер добавдяем к ниму  BluetoothGattService
            @SuppressLint("MissingPermission")
        void settingGattServerBluetoothGattService()  {
        try{
            ///TODO  служебный xiaomi "BC:61:93:E6:F2:EB", МОЙ XIAOMI FC:19:99:79:D6:D4  //////      "BC:61:93:E6:E2:63","FF:19:99:79:D6:D4"
            UUID UuidГлавныйКлючСерверGATT = ParcelUuid.fromString("10000000-0000-1000-8000-00805f9b34fb").getUuid();
            // TODO: 12.02.2023 Адреса серверов для Клиентна
            getPublicUUID = ParcelUuid.fromString("20000000-0000-1000-8000-00805f9b34fb").getUuid();
            BluetoothGattService service = new BluetoothGattService(UuidГлавныйКлючСерверGATT, BluetoothGattService.SERVICE_TYPE_PRIMARY);
            // TODO: 12.02.2023 первый сервер
            BluetoothGattCharacteristic characteristic = new BluetoothGattCharacteristic(getPublicUUID,
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

            //TODO: Desctpior
            characteristic.addDescriptor(new BluetoothGattDescriptor(getPublicUUID,
                    BluetoothGattCharacteristic.PERMISSION_READ |
                            BluetoothGattCharacteristic.PERMISSION_WRITE |
                            BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED |
                            BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED));
            service.addCharacteristic(characteristic);
            // TODO: 12.02.2023 добавлев в сервер
            getBluetoothGattServer.addService(service);


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " getBluetoothGattServer " +getBluetoothGattServer );

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
            //TODO:
            new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }
        }









    /////TODO: код Вытаскиваем из общего метоада

    @NonNull
    private String МетодГенерацииUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        uuid = uuid.replaceAll("[a-zA-Z]", "");
        //uuid= CharMatcher.any().replaceFrom("[A-Za-z0-9]", "");
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " uuid " +uuid );
        return uuid;
    }


    // TODO: 14.02.2023 Второй Метод БЕз GPS
    @SuppressLint("MissingPermission")
    private Integer МетодЗаписиДевайсавБазу(@NonNull BluetoothDevice device,
                                            @NonNull String ПришлиДанныеОтКлиентаЗапрос,
                                            @NonNull String ДанныеСодранныеОтКлиента,
                                            @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента,
                                            List<Address> addressesgetGPS,
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
            Log.i(this.getClass().getName(), "contentValuesВставкаДанных.length" + contentValuesВставкаДанных.size());

            // TODO: 09.02.2023  запись в базу дивайса Отметка сотрдунка
            РезультатЗаписиДанныхПИнгаДвайсаВБАзу = МетодЗаписиОтмечаногоСотрудникаВБАзу(contentValuesВставкаДанных);

            Log.i(this.getClass().getName(), " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " + РезультатЗаписиДанныхПИнгаДвайсаВБАзу +
                    " contentValuesВставкаДанных " + contentValuesВставкаДанных);

            if (РезультатЗаписиДанныхПИнгаДвайсаВБАзу > 0) {
                Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));

                ///TODO: Succenss AddDevice
                Bundle    bundleAddDeviceSuccess = new Bundle();
                bundleAddDeviceSuccess.putString("Статус", "SERVER#SousAvtoSuccess");
                bundleAddDeviceSuccess.putString("ОтветКлиентуВсатвкаВБАзу", ДанныеСодранныеОтКлиента);
                concurrentHashMapDeviceBTE.put("SuccessAddDevice",bundleAddDeviceSuccess );

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " +РезультатЗаписиДанныхПИнгаДвайсаВБАзу );


            } else {
                // TODO: 09.02.2023 сам статус дляОтвета;
                ///TODO: ErrorAddDevice
                Bundle    bundleAddDeviceSuccess = new Bundle();
                bundleAddDeviceSuccess.putString("Статус", "SERVER#SousAvtoERROR");
                bundleAddDeviceSuccess.putString("ОтветКлиентуВсатвкаВБАзу", "Пинг прошел ," + "\n" +
                        "Без записи в базу !!!");
                concurrentHashMapDeviceBTE.put("SuccessAddDevice",bundleAddDeviceSuccess );

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " РезультатЗаписиДанныхПИнгаДвайсаВБАзу " +РезультатЗаписиДанныхПИнгаДвайсаВБАзу );


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






    @SuppressLint({"NewApi", "SuspiciousIndentation", "MissingPermission"})
    private void МетодОтветаОТGATTСеврераКлиентуДанныеми(@NonNull BluetoothDevice device,
                                                         @NonNull int requestId,
                                                         @NonNull int offset,
                                                         @NonNull byte[] value,
                                                         @NonNull BluetoothGattCharacteristic characteristicsServerОтКлиента) {
        String ПришлиДанныеОтКлиентаЗапрос = new String();
        String ДанныеСодранныеОтКлиента = new String();
        try {
            // TODO: 20.02.2023  Пришли ДАнные От Клиента
            if (value.length > 0) {
                ПришлиДанныеОтКлиентаЗапрос = new String(value);
                Log.i(this.getClass().getName(), " " + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString()
                        + " value " + value);
                String[] sArr = ПришлиДанныеОтКлиентаЗапрос.split(",");
                List<String> listПришлиДанныеОтКлиентаЗапрос = Arrays.asList(sArr);

                Log.i(this.getClass().getName(), "  " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " время " + new Date().toLocaleString() + " value " + value);

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

                    Log.i(this.getClass().getName(), "SERVER#SousAvtoSuccess GPS" + " " + new Date().toLocaleString());
                } else {
                    ДанныеСодранныеОтКлиента = "Девайс отмечен..." + "\n" + device.getName().toString() +
                            "\n" + device.getAddress().toString() +
                            "\n" + new Date().toLocaleString()
                            + "\n" + ПришлиДанныеОтКлиентаЗапрос;
                    characteristicsServerОтКлиента.setValue("SERVER#SousAvtoSuccess");

                    Log.i(this.getClass().getName(), "SERVER#SousAvtoSuccess ---" + " " + new Date().toLocaleString());
                }


                // TODO: 12.02.2023  ОТВЕТ !!!
                // TODO: 12.02.2023  ОТВЕТ !!!
                getBluetoothGattServer.notifyCharacteristicChanged(device, characteristicsServerОтКлиента, true);
                getBluetoothGattServer.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, characteristicsServerОтКлиента.toString().getBytes(StandardCharsets.UTF_8));
                // TODO: 13.02.2023  Метод Записи Девайса в базу
                Integer РезультатЗаписиВБАзу = 0;
                if (addressesgetGPS != null) {
                    РезультатЗаписиВБАзу = МетодЗаписиДевайсавБазу(device,
                            ПришлиДанныеОтКлиентаЗапрос,
                            ДанныеСодранныеОтКлиента,
                            characteristicsServerОтКлиента,
                            addressesgetGPS, listПришлиДанныеОтКлиентаЗапрос);

                    Log.i(this.getClass().getName(), "addressesgetGPS " + " " + addressesgetGPS + " РезультатЗаписиВБАзу " + РезультатЗаписиВБАзу);

                } else {
                    РезультатЗаписиВБАзу = МетодЗаписиДевайсавБазу(device,
                            ПришлиДанныеОтКлиентаЗапрос,
                            ДанныеСодранныеОтКлиента,
                            characteristicsServerОтКлиента,
                            addressesgetGPS, listПришлиДанныеОтКлиентаЗапрос);

                    Log.i(this.getClass().getName(), "addressesgetGPS " + " " + addressesgetGPS + " РезультатЗаписиВБАзу " + РезультатЗаписиВБАзу);
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



    // TODO: 21.02.2023 Ответ Клиенту GATT send
    private synchronized void МетодОтвечаемКлиентуGatt(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, int offset, byte[] value) {
        try {
            BluetoothGattService services = characteristic.getService();
            if (services != null) {
                BluetoothGattCharacteristic characteristicsДляСерверОтКлиента = services.getCharacteristic(getPublicUUID);
                if (characteristicsДляСерверОтКлиента != null && value != null) {
                    // TODO: 20.02.2023
                    // TODO: 12.02.2023 ОТВЕТ КЛИЕНТУ
                    МетодОтветаОТGATTСеврераКлиентуДанныеми(device, requestId, offset, value, characteristicsДляСерверОтКлиента);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            "\n"+"characteristicsДляСерверОтКлиента" + characteristicsДляСерверОтКлиента +
                            " characteristicsДляСерверОтКлиента.getUuid() " + characteristicsДляСерверОтКлиента.getUuid() );

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







    @SuppressLint("MissingPermission")
    private synchronized void МетодПодтвержедиеЧтоОперацияУведомленияБыла(BluetoothDevice device, int status) {
        try{
        getBluetoothGattServer.sendResponse(device, status, BluetoothGatt.GATT_SUCCESS, status, "YOUR_RESPONSEonNotificationSent".getBytes(StandardCharsets.UTF_8));
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

    // TODO: 21.02.2023 метод превоночального коннекта с устройством
    @SuppressLint("MissingPermission")
    private synchronized void МетодКоннектаДеконнектасКлиентамиGatt(BluetoothDevice device, int status, int newState) {
        try{
        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            // TODO: 27.02.2023 Переопреляем Адамтер Bluetooth

        switch (newState) {
            case BluetoothProfile.STATE_CONNECTED:
                    getBluetoothGattServer.connect(device,false);

                ///TODO: SucceessAddDevice
                Bundle    bundleAddDeviceSuccess = new Bundle();
                bundleAddDeviceSuccess.putString("Статус","SERVERGATTConnectiong");
                bundleAddDeviceSuccess.putString("Дивайс", device.getName());
                bundleAddDeviceSuccess.putString("ОтветКлиентуВсатвкаВБАзу", "Пинг прошел ," + "\n" +
                        "Без записи в базу !!!");
                concurrentHashMapDeviceBTE.put("SuccessAddDevice",bundleAddDeviceSuccess );

                break;
            case BluetoothProfile.STATE_DISCONNECTED:
                Log.i(this.getClass().getName(), " onConnectionStateChange BluetoothProfile.STATE_DISCONNECTED "+  device.getAddress()+
                        "\n"+ "newState " + newState +  "status "+ status);
                break;
            case BluetoothGatt.GATT_CONNECTION_CONGESTED:
                Log.i(this.getClass().getName(), " onConnectionStateChange BluetoothProfile.STATE_DISCONNECTED "+  device.getAddress()+
                        "\n"+ "newState " + newState +  "status "+ status);
                break;
        }
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
        new SubClassErrors(getApplicationContext()).МетодЗаписиОшибок(valuesЗаписываемОшибки);
    }
    }

    @SuppressLint({"NewApi", "SuspiciousIndentation", "MissingPermission"})
    private  void МетодПолучениеЛокацииGPS(@NonNull Location getlastLocation) {
        try{
                if (getlastLocation != null) {
                    fusedLocationClientGoogle.flushLocations();

                    while (getlastLocation.isComplete()==false);

                        Log.i(this.getClass().getName(), "MyLocationListener GPS getlastLocation "+getlastLocation);
                        String cityName = null;
                        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                        Log.i(this.getClass().getName(), "MyLocationListener GPS gcd "+gcd);

                            addressesgetGPS = gcd.getFromLocation(getlastLocation.getLatitude(), getlastLocation.getLongitude(), 1);

                            Log.i(this.getClass().getName(), "MyLocationListener GPS addressesgetGPS "+addressesgetGPS);

                        if (addressesgetGPS.size() > 0) {
                            System.out.println(addressesgetGPS.get(0).getLocality());
                            cityName = addressesgetGPS.get(0).getLocality();
                            Log.i(this.getClass().getName(), "MyLocationListener GPS cityName "+cityName);
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
            Log.w(getApplicationContext().getClass().getName(), " РЕЗУЛЬТАТ insertData  РезульататЗАписиНовогоДивайса ЗНАЧЕНИЯ  "
                    +  РезульататЗАписиНовогоДивайса.toString() );
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
// TODO: 15.03.2023 синхрониазция КЛАсс


}
