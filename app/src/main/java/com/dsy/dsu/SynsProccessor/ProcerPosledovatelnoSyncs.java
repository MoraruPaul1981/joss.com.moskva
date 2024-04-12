package com.dsy.dsu.SynsProccessor;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.SynsProccessor.PrograsBarAsync.GetPrograssbarChangeIndicator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;

public class ProcerPosledovatelnoSyncs extends  ProccesorparallelSynch {
    public ProcerPosledovatelnoSyncs(@NonNull Context context, @NonNull ObjectMapper jsonGenerator,
                                     @NonNull SSLSocketFactory getsslSocketFactory2, @NonNull LinkedHashMap<Integer,
            String> getHiltJbossDebug, @NonNull LinkedHashMap<Integer, String> getHiltJbossReliz,
                                     @NonNull CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда,
                                     @NonNull LinkedHashMap<String, Long> ВерсииВсехСерверныхТаблиц,
                                     @NonNull LinkedHashMap<String, Date> ВерсииДатыСерверныхТаблиц,
                                     @NonNull Integer PublicID) {
        super(context, jsonGenerator, getsslSocketFactory2, getHiltJbossDebug, getHiltJbossReliz,
                ИменаТаблицыОтАндройда, ВерсииВсехСерверныхТаблиц, ВерсииДатыСерверныхТаблиц, PublicID);
    }

    @Override
    public Long startingAsyncParallels() {
        CopyOnWriteArrayList<Long> coutSucceessItemAsycnTables=new CopyOnWriteArrayList();
        try{
            SuccessInsertOrUpdates=new ConcurrentSkipListSet<>();

            // TODO: 07.04.2024
            Flowable  flowable=     Flowable.fromIterable( VesionTableAsync.keySet())
                    .filter(fil->!fil.toString().isEmpty());


            flowable.doOnNext(new io.reactivex.rxjava3.functions.Consumer<String>() {
                        @Override
                        public void accept(String ТаблицаОбработываемаяPosledovatel) throws Throwable {


                            // TODO: 08.04.2024 Показываем пользовалю ПРоценты
                            new GetPrograssbarChangeIndicator(context).setAsyncrograssbarList( NameTableAsync,ТаблицаОбработываемаяPosledovatel);

                            // TODO: 06.12.2023  запуск синхризуции по таблице конктерной
                            coutSucceessItemAsycnTables.add(getLooTablesPOSTANDGET(ТаблицаОбработываемаяPosledovatel))      ;

                            // TODO: 15.09.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    " ТаблицаОбработываемаяPosledovatel"
                                    +ТаблицаОбработываемаяPosledovatel
                                    + " coutSucceessItemAsycnTables " +coutSucceessItemAsycnTables.size()+
                                    " coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum)"
                                    +coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum));
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    }).blockingSubscribe();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber()  );
        }
        return coutSucceessItemAsycnTables.stream().mapToLong(l->l)  .reduce(0, Long::sum);
    }
}
