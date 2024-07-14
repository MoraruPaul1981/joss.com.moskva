package com.sous.server.businesslayer.BI_Services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.sous.server.businesslayer.Services.ServiceGattServer;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class maincodeServiceGatt {

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

}
