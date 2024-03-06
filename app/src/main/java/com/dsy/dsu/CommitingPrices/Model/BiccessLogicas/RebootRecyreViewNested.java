package com.dsy.dsu.CommitingPrices.Model.BiccessLogicas;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNested;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Hilt.Jakson.QualifierJakson;
import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;



@Module
@InstallIn(SingletonComponent.class)
public class RebootRecyreViewNested {

Context context;

    public  @Inject RebootRecyreViewNested(@ApplicationContext Context context) {
        //TODO сомо имя json
        this.context=context;
    }

    public void методRebootRecyreViewComminPrices(@NonNull JsonNode jsonNode1сСогласованияЦен
            ,@NonNull MyRecycleViewIsAdaptersNested myRecycleViewIsAdaptersNested,
                                                  @NotNull RecyclerView recycleview_comminingppricesNesteds) {

        try{
            myRecycleViewIsAdaptersNested.jsonNodeNested=jsonNode1сСогласованияЦен;
            myRecycleViewIsAdaptersNested.notifyDataSetChanged();
            RecyclerView.Adapter recyclerViewОбновление=         recycleview_comminingppricesNesteds.getAdapter();
            recycleview_comminingppricesNesteds.swapAdapter(recyclerViewОбновление,true);
            recycleview_comminingppricesNesteds.getAdapter().notifyDataSetChanged();
            // TODO: 24.01.2024
            recycleview_comminingppricesNesteds.requestLayout();
            recycleview_comminingppricesNesteds .refreshDrawableState();
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " jsonNode1сСогласованияЦен " +jsonNode1сСогласованияЦен);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



}
