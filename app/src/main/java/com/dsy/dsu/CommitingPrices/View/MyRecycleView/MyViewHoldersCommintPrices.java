package com.dsy.dsu.CommitingPrices.View.MyRecycleView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

// TODO: 28.02.2022 начало  MyViewHolderДляЧата
public class MyViewHoldersCommintPrices extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
    // TODO: 28.02.2022
    private TextView textView1, textView2, textView3, textView4, textView5Намеклатура, textorganizationvalue, textvalueSUM;
    private      TextView textViewКонтрагент,textViewЦФО,textViewДДС,TextViewНамелклатура;
    private MaterialCardView cardviewmatireacommitpay;
    private MaterialButton КнопкаСогласованиеОтказ,КнопкаУспешноеСогласования;
    private TableLayout tableLayoutcommitpayfiles,tableLayoutcommitpay;
    private ProgressBar progressbarfilepay;

    public Context context;
    public View itemView;
    public   JsonNode jsonNode;
    public   int getAbsoluteAdapterPosition;

    // TODO: 02.03.2022
    public MyViewHoldersCommintPrices(@NonNull View itemView,
                                      @NotNull Context context,
                                      @NotNull int getAbsoluteAdapterPosition) {
        super(itemView);
        try{
            this.itemView=itemView;
            this.context=context;
            this.getAbsoluteAdapterPosition=getAbsoluteAdapterPosition;
            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+ " jsonNode " +jsonNode);
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