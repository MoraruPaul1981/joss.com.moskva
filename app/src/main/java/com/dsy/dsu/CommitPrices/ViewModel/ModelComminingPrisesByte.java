package com.dsy.dsu.CommitPrices.ViewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dsy.dsu.CommitPrices.Model.GetDataOt1C.GetJsonOt1cComminhgPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ModelComminingPrisesByte  extends ViewModel {

    // TODO: 25.12.2023
      MutableLiveData<Bundle> mutableLiveData= null;
      MutableLiveData<Bundle> mutableLiveDataEventBus= new MutableLiveData<>();
    Context context;
    Long PublicId;


    public ModelComminingPrisesByte(Long id, Context context) {
        try{
            this.context = context;
            this.PublicId = id;
            Log.d(context.getClass().getName(),"\n"
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

    @Override
    public void addCloseable(@NonNull Closeable closeable) {
        super.addCloseable(closeable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        try{
            Log.d(context.getClass().getName(),"\n"
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


    public LiveData<Bundle> livedatastartGetJsonByte() {
        try{
            mutableLiveData= new MutableLiveData<>();
            Log.d(context.getClass().getName(),"\n"
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
        return mutableLiveData;
    }

    public LiveData<Bundle> livedatastartGetJsonByteEventBus() {
        try{
            mutableLiveDataEventBus= new MutableLiveData<>();
            Log.d(context.getClass().getName(),"\n"
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
        return mutableLiveDataEventBus;
    }


    // TODO: 25.12.2023  полочения данных от 1с в виде Byte
    public  void livedatastartSetJsonByte(@NotNull String adress,
                                          @NotNull ObjectMapper getHiltJaksonObjectMapper,
                                          @NotNull ProgressBar prograessbar_commintingprices){
        // TODO: 25.12.2023 set
        try{
            prograessbar_commintingprices.setVisibility(View.VISIBLE);

            Bundle bundleByte = new Bundle();
            Completable.fromSupplier(new Supplier<Bundle>() {
                        @Override
                        public Bundle get() throws Throwable {
                            String validadress=  Objects.requireNonNullElse(adress,"");
                            if(!validadress.isEmpty()) {
                                // TODO: 25.12.2023  get PUBLIC id
                              /*  Integer ПубличныйID =
                                        new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);*/
                                // TODO: 09.01.2024
                            //   PublicId=8l;
                                // TODO: 25.12.2023  Запускаем получее данных на сервеи 1с  byte
                                byte[] getbyteComminhgPrices = new GetJsonOt1cComminhgPrices().getByteComminhgPrices(context,
                                        adress, PublicId.intValue(), getHiltJaksonObjectMapper);

                                // TODO: 26.12.2023 добавление byte для согласование цен
                                if (getbyteComminhgPrices!=null && getbyteComminhgPrices.length>0) {
                                    // TODO: 26.12.2023
                                    bundleByte.putByteArray("getbyteComminhgPrices", getbyteComminhgPrices);
                                }else {
                                    // TODO: 26.12.2023
                                    // TODO: 26.12.2023
                                    bundleByte.putByteArray("getbyteComminhgPrices","".getBytes());
                                }

                                Log.d(this.getClass().getName(),"\n"
                                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +" PublicId " +PublicId);



                            }
                            Log.d(this.getClass().getName(),"\n"
                                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " bundleByte " +bundleByte);


                            return bundleByte;
                        }
                    }).subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(()->{

                        // TODO: 25.12.2023  полученый результат обнолвяем экран
                        mutableLiveData.postValue(bundleByte);
                        // TODO: 30.12.2023
                        Log.d(this.getClass().getName(),"\n"
                                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }).onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    }).subscribe();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

















}