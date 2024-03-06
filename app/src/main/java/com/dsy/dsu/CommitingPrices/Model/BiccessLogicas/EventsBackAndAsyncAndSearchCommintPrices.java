package com.dsy.dsu.CommitingPrices.Model.BiccessLogicas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.dsy.dsu.Dashboard.MainActivity_Dashboard;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

public class EventsBackAndAsyncAndSearchCommintPrices {
    private Context context;

    private BottomNavigationView bottomnavigationw_commintingprices;




    private BottomNavigationItemView bottomNavigationBackCommingPrices;
    private BottomNavigationItemView bottomNavigationAsyncCommingPrices;
    private BottomNavigationItemView bottomNavigationSearchCommingPrices;

    public EventsBackAndAsyncAndSearchCommintPrices(@NonNull  Context context,
                                                    @NonNull   BottomNavigationView bottomnavigationw_commintingprices,
                                                    @NonNull   BottomNavigationItemView bottomNavigationBackCommingPrices,
                                                    @NonNull   BottomNavigationItemView bottomNavigationAsyncCommingPrices,
                                                    @NonNull   BottomNavigationItemView bottomNavigationSearchCommingPrices) {
        this.context = context;
        this.bottomnavigationw_commintingprices = bottomnavigationw_commintingprices;
        this.bottomNavigationBackCommingPrices = bottomNavigationBackCommingPrices;
        this.bottomNavigationAsyncCommingPrices = bottomNavigationAsyncCommingPrices;
        this.bottomNavigationSearchCommingPrices = bottomNavigationSearchCommingPrices;
    }


    public class EventsBack  {

        public  void eventsBack  (){
            try{
// TODO: 16.01.2024 возврат назад
                RxView.clicks(  bottomNavigationBackCommingPrices)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                return    bottomNavigationBackCommingPrices;
                            }
                        })
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribe( getbottomNavigationBack-> {
                            ///todo revboot
                            try{
                            Intent Интент_BackВозвращаемАктивти = new Intent();
                            Bundle data1C = new Bundle();
                            Интент_BackВозвращаемАктивти.putExtras(data1C);
                            Интент_BackВозвращаемАктивти.setClass(context, MainActivity_Dashboard.class); // Т
                            Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Log.d(this.getClass().getName(), "  выходим из задания МетодКпопкаВозвращениеНазадИзСогласованиии");
                            context. startActivity(Интент_BackВозвращаемАктивти);

                            // TODO: 17.04.2023 LOG
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getbottomNavigationBack " +getbottomNavigationBack );
                        } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ///
                }

                        });
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///
        }
        }


    }



    public  class EventsAsync   {

        public    void eventsAsync  (@NotNull BLFragmentCommintingPrices BLFragmentCommintingPrices, @NonNull ProgressBar prograessbar_commintingprices){
            try{

                RxView.clicks(  bottomNavigationAsyncCommingPrices)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                // TODO: 30.12.2023
                                prograessbar_commintingprices.setVisibility(View.VISIBLE);
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                return    bottomNavigationAsyncCommingPrices;
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribe( getbottomNavigationAsync-> {
                            ///todo revboot
                            BLFragmentCommintingPrices.getmodelByte();


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getbottomNavigationAsync " +getbottomNavigationAsync );

                        });

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

        public    void eventsSearchsetNumber(@NonNull JsonNode jsonNode ) {
            try {
                // TODO: 09.03.2022
                if (jsonNode!=null) {
                    if (  jsonNode.size()>0) {
                        bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.parseColor("#008080"));
                        bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(jsonNode.size());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    } else {
                        bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                        bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    }
                }else {
                    bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setBackgroundColor(Color.RED)        ;
                    bottomnavigationw_commintingprices.getOrCreateBadge(R.id.bottomNavigationAsync).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                }
                bottomnavigationw_commintingprices.requestLayout();
                bottomnavigationw_commintingprices.refreshDrawableState();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

    }


    public class EventsSearch  {

        public     void eventsSearch  (@NotNull BottomNavigationItemView bottomNavigationSearchCommitPrices,
                                       @NotNull androidx.appcompat.widget.SearchView searchview_commintingprices){
            try{
                RxView.clicks(  bottomNavigationSearchCommitPrices)
                        .throttleFirst(1, TimeUnit.SECONDS)
                        .filter(s -> !s.toString().isEmpty())
                        .map(new Function<Unit, Object>() {
                            @Override
                            public Object apply(Unit unit) throws Throwable {
                                // TODO: 30.12.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                                return    bottomNavigationSearchCommitPrices;
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                throwable.printStackTrace();
                                Log.e(context.getClass().getName(),
                                        "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .subscribe( getbottomNavigationSearch-> {
                            ///todo revboot
                     try{



                         if (searchview_commintingprices.getVisibility()==View.VISIBLE) {
                             searchview_commintingprices.setVisibility(View.GONE);
                             searchview_commintingprices.setEnabled(false);
                             ViewGroup.LayoutParams params= searchview_commintingprices.getLayoutParams();
                             params.height=0;
                             searchview_commintingprices.setLayoutParams(params);

                             Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                         }else {
                             searchview_commintingprices.setVisibility(View.VISIBLE);
                             searchview_commintingprices.setEnabled(true);
                             ViewGroup.LayoutParams params= searchview_commintingprices.getLayoutParams();
                             params.height=80;
                             searchview_commintingprices.setLayoutParams(params);

                             // TODO: 21.11.2023 Enadble Filter

                            // new Fragment1_List_CommitPay.AdapterSerachViewPay(searchview_commitpay).setAdapterSerachViewPay();

                             Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                         }


                         searchview_commintingprices.requestLayout();
                         searchview_commintingprices.refreshDrawableState();









                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " getbottomNavigationSearch " +getbottomNavigationSearch );
                        } catch (Exception e) {
                           e.printStackTrace();
                           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                         new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    ///
                }

                        });



                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///
            }
        }

    }








}
