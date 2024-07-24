package com.sous.scanner.businesslayer.bl_forServices;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;

public class Bl_forServiceScan {


  public   void startintgServiceScan(@NonNull Intent intent, @NonNull Context context , @NonNull int flags,@NonNull  int startId){


        Flowable.fromAction(new Action() {
                    @Override
                    public void run() throws Throwable {



                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" System.currentTimeMillis() " + System.currentTimeMillis() );

                    }
                })
                .onBackpressureBuffer(true)
                .repeatWhen(repeat->repeat.delay(10, TimeUnit.SECONDS))
                .takeWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Throwable {
                        return false;
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }
                }).subscribe();



    }



}
