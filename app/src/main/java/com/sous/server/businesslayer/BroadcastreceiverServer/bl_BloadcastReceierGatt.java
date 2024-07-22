package com.sous.server.businesslayer.BroadcastreceiverServer;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.sous.server.businesslayer.Errors.SubClassErrors;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class bl_BloadcastReceierGatt {

    private  Context context;


    private  Long version;

    public bl_BloadcastReceierGatt(Context context,   Long version) {
        this.context = context;

        this.version = version;
    }



    @SuppressLint({"MissingPermission", "NewApi"})
    public void getPairingANdBondingDeviceFromBroadcast(@NonNull  BluetoothDevice bluetoothDevice, int pinBytes ){

        try{
            byte[] pin = ByteBuffer.allocate(4).putInt(pinBytes).array();

//To avoid the popup notification:
           bluetoothDevice.getClass().getMethod("setPairingConfirmation", boolean.class).invoke(bluetoothDevice, true);
            bluetoothDevice.getClass().getMethod("cancelPairingUserInput", boolean.class).invoke(bluetoothDevice, true);

//int pinn = 1234;

//Entering pin programmatically:
            Method ms = bluetoothDevice.getClass().getMethod("setPin", byte[].class);
//Method ms = device.getClass().getMethod("setPasskey", int.class);
            ms.invoke(bluetoothDevice, pin);

//Bonding the device:
            Method mm = bluetoothDevice.getClass().getMethod("createBond", (Class[]) null);
            mm.invoke(bluetoothDevice, (Object[]) null);

            Log.d(this.getClass().getName(), "Success to add the PIN.");



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "PIN  ");




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


    @SuppressLint({"MissingPermission", "NewApi"})
    public void getPairingANdBondingDevice(@NonNull  BluetoothDevice bluetoothDevice, int pinBytes ){

        try{

     /*       bluetoothDevice.setPin("0000".getBytes(StandardCharsets.UTF_8));
            bluetoothDevice.setPairingConfirmation(true);
            bluetoothDevice.createBond();*/

            byte[] pin = ByteBuffer.allocate(4).putInt(pinBytes).array();




            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "PIN  ");

            bluetoothDevice.setPin(pin);
            bluetoothDevice.createBond();

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
