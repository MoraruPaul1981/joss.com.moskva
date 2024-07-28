package com.sous.scanner.businesslayer.Broadcastreceiver;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.sous.scanner.businesslayer.Errors.SubClassErrors;

import java.util.Date;

public class BroadcastReceiverGattClient extends BroadcastReceiver {

    private bl_BloadcastReceierGatt blBloadcastReceierGatt;
    Long version;
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        try{
            BluetoothDevice bluetoothDeviceClient = null;
            // TODO: 22.07.2024  Код Брадкаста ресивера
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
               version = pInfo.getLongVersionCode();
            if (intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST") ||
                    intent.getAction().equals("android.bluetooth.device.action.PAIRING_CANCEL") ) {
                bluetoothDeviceClient = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            /*    blBloadcastReceierGatt = new bl_BloadcastReceierGatt(context, version);
                blBloadcastReceierGatt.unpairDevice(bluetoothDeviceClient);*/

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        "Bintent.getAction() "+intent.getAction() + " bluetoothDeviceClient " +bluetoothDeviceClient);

            }else {

                // TODO: 24.07.2024
                bluetoothDeviceClient = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);


                if (bluetoothDeviceClient.getName()!=null) {

                    bl_BloadcastReceierGatt  blBloadcastReceierGatt = new bl_BloadcastReceierGatt(context, version);
                    blBloadcastReceierGatt.unpairDevice(bluetoothDeviceClient);


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            "Bintent.getAction() "+intent.getAction() + " bluetoothDeviceClient " +bluetoothDeviceClient.getName()
                            +"\n"+ " intent.getAction() " +intent.getAction() + "  +   new Date().toLocaleString() " +   new Date().toLocaleString());
                }
            }

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                "Bintent.getAction() "+intent.getAction() + " bluetoothDeviceClient " +bluetoothDeviceClient);

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