package com.sous.scanner.Broadcastreceiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.sous.scanner.Errors.SubClassErrors;

import java.lang.reflect.Method;

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
    public void getDevice(){
        BluetoothDevice bluetoothDevice = null;
        try{

     /*       bluetoothDevice.setPin("0000".getBytes(StandardCharsets.UTF_8));
            bluetoothDevice.setPairingConfirmation(true);
            bluetoothDevice.createBond();*/

        bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            byte[] pinBytes = "0000".getBytes();

            bluetoothDevice.setPin(pinBytes);



                Method m = bluetoothDevice.getClass().getMethod("setPin", byte[].class);
                m.invoke(bluetoothDevice, pinBytes);

                Log.d(this.getClass().getName(), "Success to add the PIN.");

            bluetoothDevice.createBond();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "PIN  ");


                //   bluetoothDevice.getClass().getMethod("setPairingConfirmation", boolean.class).invoke(bluetoothDevice, true);


/*

            byte[] pin = (byte[]) BluetoothDevice.class.getMethod("convertPinToBytes", String.class).invoke(BluetoothDevice.class, pinBytes);
            Method m2 = bluetoothDevice.getClass().getMethod("setPin", byte[].class);
            m2.invoke(bluetoothDevice, pin);
          //  bluetoothDevice.getClass().getMethod("setPairingConfirmation", boolean.class).invoke(bluetoothDevice, true);

*/


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  Success to setPairingConfirmation. "+bluetoothDevice.getName());

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
