package com.dsy.dsu.CommitPrices.Model.NestedDataGetAll;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.function.Consumer;


// TODO: 09.01.2024  данные класс оплучаеем все дочерниуе едементы для ArrayNode от Согласование Цены
public class GetArrayNodeForNestedChildern implements  InForChilderArrayJSon  {
   private Context context;
    private JsonNode jsonNodeNested;
    private  int position;

    public GetArrayNodeForNestedChildern(Context context, JsonNode jsonNodeNested, int position) {
        this.context = context;
        this.jsonNodeNested = jsonNodeNested;
        this.position = position;
    }





    public JsonNode remoteRowJsonPrices( ) {
        try {


            jsonNodeNested.elements().forEachRemaining(new Consumer<JsonNode>() {
                @Override
                public void accept(JsonNode jsonNode) {
                    // TODO: 19.03.2024
                    Iterator<JsonNode> elements = jsonNode.iterator();

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " jsonNode1сСогласования.size() " + jsonNodeNested.size());

                }
            });


         /*   Iterator<JsonNode> elements = jsonNodeNested.iterator();
            Integer sum = 0;
            while (elements.hasNext()) {
                elements.next();
                if (sum == position) {
                    elements.remove();
                    break;
                }
                sum++;

            }*/
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " jsonNode1сСогласования.size() " + jsonNodeNested.size());


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return jsonNodeNested;
    }
}
