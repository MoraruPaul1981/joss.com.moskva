package com.sous.server.businesslayer.BI_Services;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.sous.server.businesslayer.Errors.SubClassErrors;
import com.sous.server.businesslayer.Services.ServiceGattServer;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Bl_forServiceGattServer {

    private SQLiteDatabase sqLiteDatabase;
    public ServiceGattServer.LocalBinderСерверBLE binder;
    private String TAG;

    private BluetoothGattServer getBluetoothGattServer;
    private BluetoothManager bluetoothManagerServer;
    private BluetoothAdapter bluetoothAdapter;
    private Long version = 0l;
    private ConcurrentHashMap<String, Bundle> concurrentHashMapDeviceBTE;


    private List<Address> addressesgetGPS;
    private UUID getPublicUUID;


    //TODO: Local
    private FusedLocationProviderClient fusedLocationClientGoogle;
    private Location lastLocation;
    private LocationManager locationManager;
    private  List<BluetoothDevice> getListGattServer ;



    private Context context;




























    //TODO: сам код для Слудбы GATT

    //TODO : инизиализация серврисов Goole
  /*  initGooleMapsLocation();*/



    // TODO: 30.11.2022  НАчинаем КОД       сервер СКАНИРОВАНИЯ
    @SuppressLint("MissingPermission")
    private synchronized void initGooleMapsLocation() {
        try {
            fusedLocationClientGoogle = LocationServices.getFusedLocationProviderClient(context);
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
                        concurrentHashMapDeviceBTE.putIfAbsent("SuccessAddDevice",bundleAddDeviceSuccess );


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
                    new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
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
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
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
                Geocoder gcd = new Geocoder(context, Locale.getDefault());
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
            new SubClassErrors(context).МетодЗаписиОшибок(valuesЗаписываемОшибки);
        }

    }


}
