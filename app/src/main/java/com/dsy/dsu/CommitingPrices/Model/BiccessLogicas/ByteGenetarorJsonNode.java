package com.dsy.dsu.CommitingPrices.Model.BiccessLogicas;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ByteGenetarorJsonNode {



   public JsonNode genetarorJsonnode(@NotNull Context context,
                                     @NotNull ObjectMapper objectMapper,
                                     @NotNull  byte[] getbyteComminhgPrices){
       final JsonNode[] jsonNode1сСогласованиеЦен = {null};
       try{

        Single<JsonNode> jsonNodeSingle=  Single.fromCallable(()->{
            // TODO: 28.12.2023
                    final JsonParser jsonParser= objectMapper.createParser(getbyteComminhgPrices);
                    jsonNode1сСогласованиеЦен[0] = jsonParser.readValueAsTree();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"getbyteComminhgPrices  "
                    + getbyteComminhgPrices+ " jsonNode "+ jsonNode1сСогласованиеЦен[0]);
               return jsonNode1сСогласованиеЦен[0];
           }).subscribeOn(Schedulers.single())
                .doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                throwable.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });
           jsonNode1сСогласованиеЦен[0] = jsonNodeSingle.blockingGet();

       Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"getbyteComminhgPrices  "
               + getbyteComminhgPrices+ " jsonNode1сСогласованиеЦен "+ jsonNode1сСогласованиеЦен[0]);
   } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    return jsonNode1сСогласованиеЦен[0];
    }
}
