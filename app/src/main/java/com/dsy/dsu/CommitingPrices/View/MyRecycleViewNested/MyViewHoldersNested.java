package com.dsy.dsu.CommitingPrices.View.MyRecycleViewNested;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

// TODO: 28.02.2022 начало  MyViewHolderДляЧата
public class MyViewHoldersNested extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
    // TODO: 28.02.2022
    public Context context;
    public View itemView;
    public JsonNode jsonNodeNestedRow;
    public  int getAbsoluteAdapterPosition;
    public  MaterialCardView  cardview_commingprices_neasted;

    // TODO: 02.03.2022
    public MyViewHoldersNested(@NonNull View itemView,
                               @NotNull Context context,
                               @NotNull int getAbsoluteAdapterPosition,
                               @NotNull JsonNode jsonNodeNestedRow) {
        super(itemView);
        try{
            this.itemView=itemView;
            this.context=context;
            this.getAbsoluteAdapterPosition=getAbsoluteAdapterPosition;
            this.jsonNodeNestedRow = jsonNodeNestedRow;

            // TODO: 02.03.2022
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " ArrayNode " + jsonNodeNestedRow);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

} // TODO: 28.02.2022 конец  MyViewHolderДляЧата