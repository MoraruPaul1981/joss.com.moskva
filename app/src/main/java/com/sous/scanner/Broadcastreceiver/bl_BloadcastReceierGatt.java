package com.sous.scanner.Broadcastreceiver;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.sous.scanner.Errors.SubClassErrors;

import java.nio.charset.StandardCharsets;

public class bl_BloadcastReceierGatt {

    private  Context context;

    private  Intent intent;
    private  Long version;

    public bl_BloadcastReceierGatt(Context context, Intent intent, Long version) {
        this.context = context;
        this.intent = intent;
        this.version = version;
    }



    @SuppressLint({"MissingPermission", "NewApi"})
    public BluetoothDevice getDevice(){
        BluetoothDevice bluetoothDevice = null;
        try{
        bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            Integer type =intent.getIntExtra(BluetoothDevice.EXTRA_PAIRING_VARIANT, BluetoothDevice.ERROR);

            if (type == BluetoothDevice.PAIRING_VARIANT_PIN) {

        Toast.makeText(context, "Connected to "+bluetoothDevice.getName(),
                Toast.LENGTH_SHORT).show();

            int pin=intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", 0);
//the pin in case you need to accept for an specific pin
            Log.d("PIN", " " + intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY",0));
            //maybe you look for a name or address
            Log.d("Bonded", bluetoothDevice.getName());

            bluetoothDevice.setPin("1234".getBytes(StandardCharsets.UTF_8));
            //setPairing confirmation if neeeded
            bluetoothDevice.setPairingConfirmation(true);

            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "BroadcastActions"+ "Connected to "+bluetoothDevice.getName());

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
        return  bluetoothDevice;
    }





}
