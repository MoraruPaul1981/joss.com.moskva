package com.dsy.dsu.WorkManagers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyWork_ScannerBluetooth_ДляПосикаДевайсов extends Worker {
    private Context Контекст;
    private String ИмяСлужбыСинхронизации = "WorkManager ScannerBluetooth";
    private  String MACАдрес;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothServerSocket bluetoothServerSocket;
    private  BluetoothDevice bluetoothDevice;
    public MyWork_ScannerBluetooth_ДляПосикаДевайсов(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.Контекст = context;
        Log.i(Контекст.getClass().getName(), " public MyWork_Async_Public(@NonNull Context context, @NonNull WorkerParameters workerParams) {  Контекст " + "\n" + Контекст);
    }
    @Override
    public void onStopped() {
        super.onStopped();
    }

    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
// TODO: 16.06.2022 old super.getBackgroundExecutor()
        Log.i(Контекст.getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return Executors.newSingleThreadExecutor();
    }


    @NonNull
    @Override
    public Result doWork() {
        Data myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = null;
        try {
        /*    // TODO: 20.09.2022 вторая часть сканирование
            UUID uuid=        ParcelUuid.fromString("00000000-0000-1000-8000-00805f9b34fb").getUuid();
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter.isEnabled() && mBluetoothAdapter.isDiscovering()) {
                Log.d(this.getClass().getName()," mBluetoothAdapter " +mBluetoothAdapter  + "uuid "+uuid );
                bluetoothServerSocket=   mBluetoothAdapter.listenUsingRfcommWithServiceRecord(String.valueOf(uuid),uuid);
                // TODO: 20.09.2022 СЛУЩАЕМ УСТРОЙСТВА
                BluetoothSocket bluetoothSocket1=bluetoothServerSocket.accept();
                Log.d(this.getClass().getName()," bluetoothSocket1 " +bluetoothSocket1);
                ;
                bluetoothDevice=bluetoothSocket1.getRemoteDevice();
                String ПришелУдаленныйДевайсНаСервер= Optional.ofNullable( bluetoothDevice.toString()).map(String::new).orElse("")    ;

                getApplicationContext().getMainExecutor().execute(()->{
                    Toast toast=       Toast.makeText(getApplicationContext(),
                            "  isSuccess  BluetoothSocket bluetoothSocket1=bluetoothServerSocket.accept() "+ПришелУдаленныйДевайсНаСервер, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,40);
                    toast.show();
                    Log.i(Контекст.getClass().getName(), "   isSuccess BluetoothSocket bluetoothSocket1=bluetoothServerSocket.accept() "+ПришелУдаленныйДевайсНаСервер);
                });
                bluetoothSocket1.close();
                myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = new Data.Builder()
                        .putString("MACАдрес", ПришелУдаленныйДевайсНаСервер)
                        .build();
            }
            Log.i(Контекст.getClass().getName(), "  MACАдрес " + MACАдрес+  " bluetoothDevice " +bluetoothDevice);///         Set<BluetoothDevice> d= mBluetoothAdapter.getBondedDevices();*/
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(Контекст).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(Контекст.getClass().getName(),
                    " MyWork_ScannerBluetooth_ДляПосикаДевайсов "
                            + e.toString());
            try {
                bluetoothServerSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
     /*   if (MACАдрес.length() > 0) {
            return Result.success(myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы);
        } else {
            return Result.retry();

        }*/
        // TODO: 20.09.2022
        return Result.success();
    }
}































