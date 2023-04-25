package com.dsy.dsu.CodeOrdersAnTransports.Window;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.CodeOrdersAnTransports.Background.ServiceOrserTransportService;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.R;

import java.util.Date;
import java.util.Map;
import java.util.Random;

public class MainActivityOrdersTransports extends AppCompatActivity {
    // TODO: 25.04.2023 Переменные
    private  ServiceOrserTransportService.  LocalBinderOrderTransport localBinderOrderTransport;
    private    ServiceConnection serviceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_orders_transports);
        // TODO: 25.04.2023  ЗАКАЗ МАТЕРИАЛОВ
        МетодБиндингOrderTransport();
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  getApplicationContext().getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        // TODO: 01.09.2021 метод вызова
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    public void МетодБиндингOrderTransport() {
        try {
            Intent intentЗапускOrserTransportService = new Intent(getApplicationContext(), ServiceOrserTransportService.class);
            intentЗапускOrserTransportService.setAction("intentЗапускOrserTransportService");
            if (localBinderOrderTransport==null) {
                  serviceConnection=     new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                localBinderOrderTransport = (ServiceOrserTransportService.  LocalBinderOrderTransport) service;
                                // TODO: 16.11.2022
                                Parcel    data = Parcel.obtain();
                                Parcel      reply = Parcel.obtain();
                                data.writeString("fffff");

                         Boolean  Otve=       localBinderOrderTransport.transact(new Random().nextInt(),data,reply,new Random().nextInt());
                         String retry=       reply.readString();
                         Integer retry2=       reply.readInt();
                         Map map=       reply.readHashMap(null);
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "   service.pingBinder() " + service.pingBinder());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            localBinderOrderTransport = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок

                        }
                    }
                };
            }
           bindService(intentЗапускOrserTransportService, serviceConnection , Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
}