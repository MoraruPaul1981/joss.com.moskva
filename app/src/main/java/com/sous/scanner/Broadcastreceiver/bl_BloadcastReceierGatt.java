package com.sous.scanner.Broadcastreceiver;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.sous.scanner.Errors.SubClassErrors;

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
