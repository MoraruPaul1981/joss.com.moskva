package com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.InitRecyreviews;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.DizaynRecyreView.LeftDividerItemDecorator;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

public class InizializayRecyreViews {

    private RecyclerView recycleview_comminingpprices;
    private Context context;
    private Animation animationДляСогласования;

    public InizializayRecyreViews(RecyclerView recycleview_comminingpprices, Context context ) {
        this.recycleview_comminingpprices = recycleview_comminingpprices;
        this.context = context;
    }

    public void startInitRecyreview() {
        try {










/*
            DividerItemDecoration itemDecoration =
                    new DividerItemDecoration(recycleview_comminingpprices.getContext(), DividerItemDecoration.HORIZONTAL);*/





            recycleview_comminingpprices.setHasFixedSize(true);
            recycleview_comminingpprices.addItemDecoration(new LeftDividerItemDecorator(context));
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycleview_comminingpprices.setLayoutManager(linearLayoutManager);
            recycleview_comminingpprices.requestLayout();
            recycleview_comminingpprices.refreshDrawableState();
            // TODO: 28.02.2022
            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

}
