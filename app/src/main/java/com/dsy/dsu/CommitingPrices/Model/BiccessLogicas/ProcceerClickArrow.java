package com.dsy.dsu.CommitingPrices.Model.BiccessLogicas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.LiveData.CallBacksLiveDataNested;
import com.dsy.dsu.CommitingPrices.Model.EvenBusPrices.MessageEvensBusPrices;
import com.dsy.dsu.CommitingPrices.Model.EvenBusPrices.MessageEventBusItemCount;
import com.dsy.dsu.CommitingPrices.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitingPrices.View.MyRecycleView.MyViewHoldersCommintPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class ProcceerClickArrow {

    private   RecyclerView recycleview_nesters_comminingpprices;
    private    MaterialButton materialButton;
    private   ProgressBar  progressbar_comminingprices;
    private MyViewHoldersCommintPrices holder;
    private  Context context;

    private  Integer position;
    Handler handlerProgbar;
    Handler handlerBut;
      ObjectMapper objectMapper;

     Integer getHiltPublicId;
     private  String getHiltCommintgPrices;

    GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;

    MutableLiveData<Intent> getHiltMutableLiveDataPay;
    private LifecycleOwner lifecycleOwner;

    public ProcceerClickArrow( @NonNull  RecyclerView recycleview_nesters_comminingpprices,
                               @NonNull MaterialButton materialButton,
                               @NonNull  ProgressBar progressbar_comminingprices,
                               @NonNull MyViewHoldersCommintPrices holder,
                               @NonNull  Context context,
                               @NonNull Integer position,
                               @NotNull ObjectMapper objectMapper,
                               @NotNull Integer getHiltPublicId,
                               @NotNull String getHiltCommintgPrices,
                               @NonNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                               @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                               @NonNull    LifecycleOwner lifecycleOwner) {


        this.recycleview_nesters_comminingpprices = recycleview_nesters_comminingpprices;
        this.materialButton = materialButton;
        this.progressbar_comminingprices = progressbar_comminingprices;
        this.holder = holder;
        this.context = context;
        this.position = position;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;



        // TODO: 10.01.2024
        handlerBut=  materialButton.getHandler();
        handlerProgbar=  progressbar_comminingprices.getHandler();
    }


   public Integer startProccerotNested(){
        try{

            Completable.fromSupplier(new Supplier<Object>() {
                @Override
                public Object get() throws Throwable {
                    // TODO: 30.12.2023
                    handlerBut.post(new Runnable() {
                        @Override
                        public void run() {
                            try{

                            // TODO: 29.12.2023  включаем вложеный RecyreView
                                initNedsterRecyreView();
                                // TODO: 30.12.2023 запус каем ккод заполнения  NESTED
                                Log.d(this.getClass().getName(), "\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        }



                    });

                    return handlerBut;
                }







                    })
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            // TODO: 30.12.20
                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        @Override
                        public void onComplete() {
                            // TODO: 30.12.2023
                            // TODO: 29.12.2023
                            handlerProgbar.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressbar_comminingprices.setVisibility(View.GONE);
                                }
                            },1000);

                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e
                                    + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    });

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return 0;
    }





    private void initNedsterRecyreView() {
        try{
            CallBacksLiveDataNested callBacksLiveDataNested =
                    new CallBacksLiveDataNested(holder, context, recycleview_nesters_comminingpprices,
                            holder.jsonNode, position,objectMapper,
                            getHiltPublicId,getHiltCommintgPrices,
                            getLiveDataForrecyreViewPrices,
                            getHiltMutableLiveDataPay,
                            lifecycleOwner);

            // TODO: 30.12.2023 запукскаем
            callBacksLiveDataNested.callbackLiveData();

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }







    public  Integer chnageStatusArrowData(){
        try{

            handlerBut.post(new Runnable() {
                @Override
                public void run() {
                    try{

                    if (recycleview_nesters_comminingpprices.getVisibility()==View.VISIBLE) {
                        // TODO: 29.12.2023
                        Drawable drawabledown=context.getDrawable(R.drawable.icon_for_commingpricesdown2);
                        materialButton.setIcon(drawabledown);
                        // TODO: 29.12.2023

                        recycleview_nesters_comminingpprices.setVisibility(View.GONE);
                        // TODO: 29.12.2023
                        progressbar_comminingprices.setVisibility(View.GONE);




                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    } else {

                        Drawable drawableup=context.getDrawable(R.drawable.icon_for_commingpricesup);
                        materialButton.setIcon(drawableup);

                        recycleview_nesters_comminingpprices.setVisibility(View.VISIBLE);
                        // TODO: 29.12.2023
                        progressbar_comminingprices.setVisibility(View.VISIBLE);


                        // TODO: 30.12.2023  метод смой загрузки данных
                      startProccerotNested();




                        // TODO: 29.12.2023
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    // TODO: 29.12.2023
                    materialButton.requestLayout();
                    materialButton.refreshDrawableState();
                    // TODO: 29.12.2023
                    recycleview_nesters_comminingpprices.requestLayout();
                    recycleview_nesters_comminingpprices.refreshDrawableState();


                    progressbar_comminingprices.requestLayout();
                    progressbar_comminingprices.refreshDrawableState();


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            } );

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return 0;
    }

}
