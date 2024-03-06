package com.dsy.dsu.PaysCommings.Model.BI_RecyreView.FindJson;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class FindFromJsonNode  implements  InFindPostion {
    Context context;


    public FindFromJsonNode(Context context) {
        this.context = context;
    }

    @Nullable
    @NonNull
    @Override
    public Integer startPostionJsonNode(@NonNull JsonNode jsonNode1сСогласованияAll, @NonNull String NumberDoc) {
        Integer intStreamFindPosiontion=0;
try{
    IntStream intStreamFind=    IntStream.range(0,jsonNode1сСогласованияAll.size())
                .limit(jsonNode1сСогласованияAll.size())
                 .parallel()
                .filter(new IntPredicate() {
                    @Override
                    public boolean test(int value) {

                        JsonNode jsonNodeSearch=      jsonNode1сСогласованияAll.get(value) ;
                        JsonNode  jsonNodeForDeleteSearchParentPath=      jsonNodeSearch.findPath("Ndoc") ;
                        String IntegerNdoc=   jsonNodeForDeleteSearchParentPath.asText().trim();
                        if (IntegerNdoc.equalsIgnoreCase(NumberDoc)) {

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            return true;
                        }else {

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            return false;
                        }
                    }
                });
    intStreamFindPosiontion=intStreamFind.parallel().findFirst().getAsInt();
    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
            + " intStreamFindPosiontion " +intStreamFindPosiontion);


} catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return intStreamFindPosiontion;
    }
}
