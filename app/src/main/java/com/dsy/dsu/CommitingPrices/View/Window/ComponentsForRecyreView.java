package com.dsy.dsu.CommitingPrices.View.Window;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.ProcceerClickArrow;
import com.dsy.dsu.CommitingPrices.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitingPrices.View.MyRecycleView.MyViewHoldersCommintPrices;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

public class ComponentsForRecyreView {

    private MyViewHoldersCommintPrices holder;
    private Context context;

    private  int position;


    private MaterialTextView mTV_commitingprices_value;

    private  MaterialButton  arrow_nested_receriview;
    private  ObjectMapper objectMapper;
    private Integer getHiltPublicId;
    private  String getHiltCommintgPrices;

    Animation    animationДляСогласованияЦены;
    GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;
    MutableLiveData<Intent> getHiltMutableLiveDataPay;
    private LifecycleOwner lifecycleOwner;
    public ComponentsForRecyreView(@NotNull MyViewHoldersCommintPrices holder,
                                   @NotNull  Context context,
                                   @NotNull  int position,
                                   @NotNull ObjectMapper objectMapper,
                                   @NotNull Integer getHiltPublicId,
                                   @NotNull String getHiltCommintgPrices,
                                   @NotNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                   @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                   @NotNull  LifecycleOwner lifecycleOwner) {
        try{
        this.holder = holder;
        this.context = context;
        this.position = position;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " position " +position);


      animationДляСогласованияЦены= AnimationUtils.loadAnimation(context, R.anim.slide_in_scrolls);//R.anim.layout_animal_commit

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    public MaterialTextView getmTV_commitingprices_value() {
        try{
            mTV_commitingprices_value=    holder.itemView.findViewById(R.id.mTV_commitingprices_value) ;
            JsonNode jsonNode=    holder.jsonNode.deepCopy();
            TextNode textNodeNameZFO=( TextNode)   jsonNode.findValue("CFO").deepCopy();
            mTV_commitingprices_value.setText(textNodeNameZFO.asText().trim());
           // mTV_commitingprices_value.setText(textNodeNameZFO.asText().trim()+new Date().toLocaleString().toString());

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " mTV_commitingprices_value " +mTV_commitingprices_value + " textNodeNameZFO " +textNodeNameZFO);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_commitingprices_value;
    }







    public MaterialButton getArrow_nested_receriview() {
        try{
            arrow_nested_receriview=    holder.itemView.findViewById(R.id.arrow_nested_receriview) ;


            RxView.clicks(  arrow_nested_receriview)
                    .throttleFirst(250, TimeUnit.MILLISECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, MaterialButton>() {
                        @Override
                        public MaterialButton apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                            return    arrow_nested_receriview;
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
                    .subscribe( Getarrow_nested_receriview-> {

                        ///todo отрыкавем или скрываем дополнительнае данные в Соглосование Цен
                        EventUpOrDownAdvaceDataCommingPrices(  Getarrow_nested_receriview);



                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" Getarrow_nested_receriview " +Getarrow_nested_receriview  );

                    });

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " arrow_nested_receriview " +arrow_nested_receriview);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return arrow_nested_receriview;
    }



    private void EventUpOrDownAdvaceDataCommingPrices(MaterialButton GetNameSingleAsync1c) {
        try{
            MaterialButton materialButton= GetNameSingleAsync1c;
            // TODO: 29.12.2023
            RecyclerView recycleview_nesters_comminingpprices=
                    holder.itemView.findViewById(R.id.recycleview_nesters_comminingpprices) ;

            ProgressBar    progressbar_comminingprices= holder.itemView.findViewById(R.id.progressbar_comminingprices) ;
// TODO: 29.12.2023  Класс Обоработки Нажатие на Кноппку Стрелочка

            // TODO: 29.12.2023

            // TODO: 29.12.2023  запускаем бизес лошику нажатие на Кнопку Arrow
            ProcceerClickArrow procceerClickArrow=      new ProcceerClickArrow(recycleview_nesters_comminingpprices,
                    materialButton,
                    progressbar_comminingprices,
                    holder,
                    context,position,objectMapper,getHiltPublicId,getHiltCommintgPrices,
                    getLiveDataForrecyreViewPrices,getHiltMutableLiveDataPay,lifecycleOwner) ;

// TODO: 30.12.2023 запускам при нажатии на Кнопку Arrow  внутрнеий



            procceerClickArrow.chnageStatusArrowData();


            AfterClickAntimationStaringZFO();


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
    }

    private void AfterClickAntimationStaringZFO() {
      try{
                    Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.slide_in_row_tabellist);
                    Animation  animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_scrolls);
                    mTV_commitingprices_value.startAnimation(animation2);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }




    }


    public void setArrow_nested_receriview(MaterialButton arrow_nested_receriview) {
        this.arrow_nested_receriview = arrow_nested_receriview;
        try{


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
    }





}






