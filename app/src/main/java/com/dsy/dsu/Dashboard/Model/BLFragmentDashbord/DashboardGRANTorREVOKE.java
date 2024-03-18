package com.dsy.dsu.Dashboard.Model.BLFragmentDashbord;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.GrantsRights.InGRANTs;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


@QualifierDashboards
@Module
@InstallIn(SingletonComponent.class)
public class DashboardGRANTorREVOKE implements InGRANTs {

    public @Inject DashboardGRANTorREVOKE() {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    
    @SuppressLint("Range")
    @Override
    public Integer getGrantRemote(@NonNull Context context,@NonNull Integer getHiltPublicId ) {
        // TODO: 11.01.2024
        Integer getGrantRemote=0;
        try{
            Single<Integer> jsonNodeSingle=  Single.fromCallable(()->{
                        // TODO: 28.12.2023
                        Integer  getGrant=0;
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "chat_users" + "");
                        ContentResolver contentResolver=context. getContentResolver();
                        Cursor Курсор_Права =      contentResolver.query(uri,new String[]{},
                                new String(" SELECT *  FROM    chat_users WHERE _id="+getHiltPublicId.toString()+"    ORDER BY _id  LIMIT   1  "),
                                new String[]{},null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

                    if (Курсор_Права.getCount()>0) {
                        Курсор_Права.moveToFirst();

                            // TODO: 11.01.2024
                         getGrant=Курсор_Права.getInt(Курсор_Права.getColumnIndex("rights"));
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getGrant " +getGrant);

                    }

                        Курсор_Права.close();
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"Курсор_Права  "+ Курсор_Права);
                        return getGrant;
                    }).subscribeOn(Schedulers.single())
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    });
            getGrantRemote= jsonNodeSingle.blockingGet();

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getGrantRemote " +getGrantRemote);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return getGrantRemote;
    }

    @Override
    public void setGrantRemote(@NonNull Context context, MaterialButton КнопкаЗаявкаНаТранспорт,
                                  MaterialButton КнопкаСогласование,
                                  MaterialButton КнопкаСогласЦен,
                                  MaterialButton КнопкаПоступлениеМатериалов,
                               MaterialButton КнопкаТабель,
                               Integer getGrantRemote) {
        // TODO: 11.01.2024
        try{

            
            switch (getGrantRemote){
                // TODO: 11.01.2024  Согласование 6 7 режим только
                case 6:
                case 7:
                    // TODO: 11.01.2024  

                    КнопкаПоступлениеМатериалов.setVisibility(View.GONE);
                    КнопкаПоступлениеМатериалов.requestLayout();
                    КнопкаПоступлениеМатериалов.refreshDrawableState();

                    КнопкаТабель.setVisibility(View.GONE);
                    КнопкаТабель.refreshDrawableState();
                    КнопкаТабель.requestLayout();

                    КнопкаЗаявкаНаТранспорт.setVisibility(View.GONE);
                    КнопкаЗаявкаНаТранспорт.refreshDrawableState();
                    КнопкаЗаявкаНаТранспорт.requestLayout();









                    // TODO: 11.01.2024

                    КнопкаСогласование.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            КнопкаСогласование.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            КнопкаСогласование.setHeight( 300);
                            КнопкаСогласование.requestLayout();
                            КнопкаСогласование.forceLayout();
                            КнопкаСогласование.refreshDrawableState();
                        }
                    });

                    КнопкаСогласЦен.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            КнопкаСогласЦен.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            КнопкаСогласЦен.setHeight( 300);
                            КнопкаСогласЦен.requestLayout();
                            КнопкаСогласЦен.forceLayout();
                            КнопкаСогласЦен.refreshDrawableState();
                        }
                    });

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " ПРАВА getGrantRemote " +getGrantRemote);
                break;

                // TODO: 11.01.2024  режим 2  табель 
                default:
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " ПРАВА getGrantRemote " +getGrantRemote);
                    break;
                
                
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " ПРАВА getGrantRemote " +getGrantRemote);


        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }


}
