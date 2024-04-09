package com.dsy.dsu.SynsProccessor.PrograsBarAsync;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BootAndAsync.BlBootAsync.SendMainActivity;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.BusinessLogicAll.Class_Visible_Processing_Async;
import com.dsy.dsu.Errors.Class_Generation_Errors;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class GetPrograssbarChangeIndicator implements  InPrograssBars {
Context context;

    public GetPrograssbarChangeIndicator(Context context) {
        this.context = context;
    }

    @Override
    public void setAsyncrograssbarMap(@NonNull LinkedHashMap<String, Long> linkedHashMap,
                                      @NonNull String имяТаблицаAsync) {
        try {
            int Проценты;
            ArrayList<String> ВерсииВсехFindCurrentTable= (ArrayList<String>) linkedHashMap.keySet().stream().collect(Collectors.toList());

            Integer ПозицияТекущейТаблицы=      ВерсииВсехFindCurrentTable.indexOf(имяТаблицаAsync)+1;
            Проценты = new Class_Visible_Processing_Async(context).
                    ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ВерсииВсехFindCurrentTable.size());


            // TODO: 22.01.2024 текущее отобраение процентов
            методCallBackPrograssBars(  Проценты,имяТаблицаAsync, ПозицияТекущейТаблицы,ВерсииВсехFindCurrentTable.size());

            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Проценты " +Проценты+" имяТаблицаAsync " +имяТаблицаAsync);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void setAsyncrograssbarList( @NonNull CopyOnWriteArrayList<String>   NameTableAsync,@NonNull String имяТаблицаAsync) {
        try {
            int Проценты;

            Integer ПозицияТекущейТаблицы=      NameTableAsync.indexOf(NameTableAsync)+1;
            Проценты = new Class_Visible_Processing_Async(context).
                    ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, NameTableAsync.size());

            // TODO: 22.01.2024 текущее отобраение процентов
            методCallBackPrograssBars(  Проценты,имяТаблицаAsync, ПозицияТекущейТаблицы,NameTableAsync.size());

            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " Проценты " +Проценты+" NameTableAsync " +NameTableAsync);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void методCallBackPrograssBars(@NonNull int Проценны,
                                          @NonNull String имяТаблицаAsync,
                                          @NonNull Integer ПозицияТекущейТаблицы,
                                          @NonNull int  maxAllCountRow) {


        try {
            class SendUserДанныеДляPrograssbar extends SendMainActivity {

                public SendUserДанныеДляPrograssbar(Context context) {
                    super(context);
                }

                @Override
                public void startSendBroadSesiver() {
                    //  super.startSendBroadSesiver();
                    intentComunications.setAction("Broad_messageAsyncPrograssBar");
                    bundleComunications.putString("Статус" ,"AsyncPrograssBar");
                    bundleComunications.putInt("Проценны" ,Проценны);
                    bundleComunications.putString("имятаблицы" ,имяТаблицаAsync);
                    bundleComunications.putInt("maxtables" ,  maxAllCountRow );
                    bundleComunications.putInt("currentposition" ,ПозицияТекущейТаблицы);
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusPrograssBar(intentComunications));;

      /*              // TODO: 22.01.2024 останавливаем службу
                    stopServiceBoot();*/

                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserДанныеДляPrograssbar(context).startSendBroadSesiver();


            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " Проценны " +Проценны);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }




}
