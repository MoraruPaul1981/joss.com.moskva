package com.dsy.dsu.Hilt.OkhhtpBuilder;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

public class GetOkhhtpBuilder implements  InGetOkhhtpBuilder {
    Context context;

    public GetOkhhtpBuilder(@NotNull Context context, @NotNull SSLSocketFactory getsslSocketFactory) {
        this.context = context;
    }

    @Override
    public OkHttpClient.Builder getOkhhtpBuilder() {
        OkHttpClient.Builder builder=null;
        try{
            builder=     new OkHttpClient().newBuilder();
            builder.connectionPool(new ConnectionPool(100,5, TimeUnit.SECONDS));
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return builder;
    }
    }

