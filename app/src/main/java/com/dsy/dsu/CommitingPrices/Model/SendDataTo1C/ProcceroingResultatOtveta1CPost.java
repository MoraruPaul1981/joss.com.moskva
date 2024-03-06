package com.dsy.dsu.CommitingPrices.Model.SendDataTo1C;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.RebootRecyreViewNested;
import com.dsy.dsu.CommitingPrices.Model.EvenBusPrices.MessageEvensBusPrices;
import com.dsy.dsu.CommitingPrices.Model.NestedDataGetAll.GetArrayNodeForNestedChildern;
import com.dsy.dsu.CommitingPrices.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNested;
import com.dsy.dsu.CommitingPrices.View.MyRecycleViewNested.MyViewHoldersNested;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;


// TODO: 11.01.2024  класс финально после получение ответ  переропределяем внезний вид  recyreview удаля теьбкущю плитку
public class ProcceroingResultatOtveta1CPost {
Context context;

    public ProcceroingResultatOtveta1CPost(@NonNull Context context) {
        this.context = context;
    }


   public void startingResultatOtveta1CPost(@NotNull StringBuffer  BufferOt1cCommintPricePost,
                                            @NotNull MaterialTextView mTV_commitingprices_count,
                                            @NotNull MyRecycleViewIsAdaptersNested myRecycleViewIsAdaptersNested,
                                            @NotNull int position,
                                            @NotNull MaterialCardView cardview_commingprices_neasted,
                                            @NotNull JsonNode jsonNodeNested,
                                            @NotNull MyViewHoldersNested holder,
                                            @NotNull RecyclerView recycleview_comminingppricesNesteds){
        try{
// TODO: 11.01.2024

            String ОтветОтСервера1сCommitnPricesPost=BufferOt1cCommintPricePost.toString()
                    .replaceAll("\"","\"\"")
                    .replaceAll("\"", "").trim();
            // TODO: 30.01.2024  
            if ( ОтветОтСервера1сCommitnPricesPost.length()>0 &&
                    ОтветОтСервера1сCommitnPricesPost.trim().equalsIgnoreCase("Согласование внесено в базу!")) {

                // TODO: 23.01.2024 анимация
                Animation  animationvibr1 = AnimationUtils.loadAnimation(context, R.anim.slide_in_row9);
                holder.itemView.startAnimation(animationvibr1);
                holder.itemView.refreshDrawableState();

                // TODO: 11.01.2024 перегрузка данных
                myRecycleViewIsAdaptersNested.notifyItemRemoved(position );

                myRecycleViewIsAdaptersNested.notifyItemChanged(position );

// TODO: 09.01.2024  класс получаем все дочерние элементы ArrayNoide
                GetArrayNodeForNestedChildern getArrayNodeForNestedChildern=new GetArrayNodeForNestedChildern(context,  jsonNodeNested,position );

                jsonNodeNested=    getArrayNodeForNestedChildern.remoteRowJsonPrices( );


                // TODO: 26.12.2023 нет  байты
                RebootRecyreViewNested rebootRecyreViewNested=new RebootRecyreViewNested(context);

                rebootRecyreViewNested.методRebootRecyreViewComminPrices(jsonNodeNested,myRecycleViewIsAdaptersNested,recycleview_comminingppricesNesteds);



                    Log.d(this.getClass().getName(), "\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

// TODO: 24.01.2024 собыьтие Отправляем что данные меньще 1  и родительсное ЦФО надо закрыть
                eventBusReactionSize(jsonNodeNested);


                Log.d(this.getClass().getName(), "\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());




                Log.d(this.getClass().getName(), "\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

            } else {
                Toast.makeText(context, "Не прошла операция !!!"
                        +"\n"+mTV_commitingprices_count.getText().toString(), Toast.LENGTH_LONG).show();

                Log.d(this.getClass().getName(), "\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+"Не прошла операция !!!");
            }
// TODO: 31.01.2024
            cardmatrialrotacidefault(cardview_commingprices_neasted);

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

    private   void cardmatrialrotacidefault(@NonNull MaterialCardView cardview_commingprices_neasted) {
        cardview_commingprices_neasted.animate().rotationXBy(-5);
    }


    private   void eventBusReactionSize(@NonNull JsonNode jsonNodeNested) {
        try{
            switch (jsonNodeNested.size()){
                case 0:
                        Intent intentPriceEventBud=new Intent();
                        Bundle Event=new Bundle();
                        intentPriceEventBud.setAction("ArrayNodeNested.size()");
                        Event.putInt("ArrayNodeNested.size()" , jsonNodeNested.size());
                        intentPriceEventBud.putExtras(Event);

                        EventBus.getDefault().post(new MessageEvensBusPrices(intentPriceEventBud));

                    break;

            }

        Log.d(this.getClass().getName(), "\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+" jsonNodeNested.size() " +jsonNodeNested.size());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


    }


}
