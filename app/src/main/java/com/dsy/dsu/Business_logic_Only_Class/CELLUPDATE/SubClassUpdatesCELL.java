package com.dsy.dsu.Business_logic_Only_Class.CELLUPDATE;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.dsy.dsu.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

//TODO класс обновление Ячеек
public class SubClassUpdatesCELL {
    Context context;

    public SubClassUpdatesCELL(Context context) {
        this.context = context;
    }

    public Integer МетодВалидацияЯчеек(@NonNull View viewДанные) {
        Integer ОбновлениеЯчейки=0;
        try{
            if (viewДанные!=null) {
                Log.d(this.getClass().getName(), " viewДанные" +  viewДанные);
                 TextView editTextЯчейкаОбновление = null;
                if (viewДанные instanceof  EditText ) {
                    editTextЯчейкаОбновление = (TextView)    viewДанные;
                }
                if (viewДанные instanceof  MaterialTextView ) {
                    editTextЯчейкаОбновление = (MaterialTextView) viewДанные;
                }

                List<Integer> ЛистДопустимоеСодержание = new ArrayList();
                IntStream.iterate(1, i -> i + 1).limit(24).forEachOrdered(ЛистДопустимоеСодержание::add);
                String ЗначениеИзЯчейки=editTextЯчейкаОбновление.getText().toString().trim();
                boolean ФлагНовоеЗначение=        ЗначениеИзЯчейки.matches("(.*)[0-9](.*)");/////TODO   viewДанные.toString().matches("(.*)[^0-9](.*)");

                // TODO: 12.04.2023 ТОкльЧисло ОБновдение
                if(ФлагНовоеЗначение==true){
                    ЗначениеИзЯчейки=   ЗначениеИзЯчейки.replaceAll("[^0-9]","").trim();
                    if (   Integer.parseInt(ЗначениеИзЯчейки.trim())>24) {
                        Toast aa = Toast.makeText(context, "OPEN", Toast.LENGTH_LONG);
                        ImageView cc = new ImageView( context);
                        cc.setImageResource(R.drawable.icon_dsu1_add_organisazio_error);//icon_dsu1_synchronisazia_dsu1_success
                        aa.setView(cc);
                      //  aa.show();
                    }else {
                        editTextЯчейкаОбновление.setText(ЗначениеИзЯчейки.toString());
                        Bundle bundleперезаписьЯчейки= (Bundle)  editTextЯчейкаОбновление.getTag();
                        bundleперезаписьЯчейки.putString("ЗначениеДня",ЗначениеИзЯчейки);
                        // TODO: 11.04.2023 Обновление Ячейки через ПРовайдер
                        ОбновлениеЯчейки=    МетодСохранениеЯчейки(editTextЯчейкаОбновление,context);
                    }
                    // TODO: 12.04.2023 ЧИСЛО ОБНОВЛЕНИЕ
                }else {
                    ЗначениеИзЯчейки.replaceAll("[0-9]","");
                    editTextЯчейкаОбновление.setText(ЗначениеИзЯчейки.toString());
                    Bundle bundleперезаписьЯчейки= (Bundle)  editTextЯчейкаОбновление.getTag();
                    bundleперезаписьЯчейки.putString("ЗначениеДня",ЗначениеИзЯчейки);
                    // TODO: 11.04.2023 Обновление Ячейки через ПРовайдер
                    ОбновлениеЯчейки=      МетодСохранениеЯчейки(editTextЯчейкаОбновление,context);

                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " ФлагНовоеЗначение " +ФлагНовоеЗначение+
                        " editTextЯчейкаОбновление.toString()" +  editTextЯчейкаОбновление.toString() + " ОбновлениеЯчейки " +ОбновлениеЯчейки);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ ЧАТА   ДЛЯ ЧАТА onDestroy() время "+new Date());

        }
        return ОбновлениеЯчейки;
    }
    Integer МетодСохранениеЯчейки(@NonNull TextView viewЯчейка,@NonNull Context context){ //TODO метод записи СМЕНЫ ПРОФЕСИИ
        Integer ОбновлениеЯчейки=0;
        try{
            String ТаблицаОбработки="data_tabels";
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " searchViewДляНовогоПоиска "+viewЯчейка+ " ТаблицаОбработки "+ТаблицаОбработки);
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
            Bundle bundleОбновлениеЯчейки= (Bundle)  viewЯчейка.getTag();
            ContentValues contentValuesОбноленияЯчейкиSingleTanel=new ContentValues();
            String ЗначениеДня=bundleОбновлениеЯчейки.getString("ЗначениеДня","");
            String День=bundleОбновлениеЯчейки.getString("День","");
            Long  uuid=bundleОбновлениеЯчейки.getLong("uuid",0l);
            contentValuesОбноленияЯчейкиSingleTanel.put(День,ЗначениеДня);
            String Дата =     new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанныхДОП();
            contentValuesОбноленияЯчейкиSingleTanel.put("date_update", Дата);
            Long Версия = new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    ТаблицаОбработки
                    ,context,new CREATE_DATABASE(context).getССылкаНаСозданнуюБазу());
            contentValuesОбноленияЯчейкиSingleTanel.put("current_table", Версия);
            // TODO: 12.04.2023 отправялем в провайдеор
            ContentResolver contentResolver=context.getContentResolver();
            ОбновлениеЯчейки=  contentResolver.update(uri, contentValuesОбноленияЯчейкиSingleTanel,"uuid=?",new String[]{String.valueOf(uuid)});
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РЕЗУЛЬТАТ ОбновлениеЯчейки  " +  ОбновлениеЯчейки);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОбновлениеЯчейки;
    }

}
