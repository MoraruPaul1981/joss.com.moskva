package com.dsy.dsu.Business_logic_Only_Class.Jakson;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

public class GeneratorJSONSerializer extends JsonSerializer<Cursor> {
    Context context;
    public GeneratorJSONSerializer(Context context) {
        this.context=context;
    }

    @Override
    public void serialize(Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
//////////20.15
        try{
            jsonGenerator.writeStartObject();
        do {
         Long Key=   КурсорДляОтправкиДанныхНаСерверОтАндройда.getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid"));
            jsonGenerator.writeFieldId(Key);
            jsonGenerator.writeStartObject();
            // TODO: 14.03.2023  генериуем по столбцам
            for (int ИндексСтолбикаJson = 0; ИндексСтолбикаJson < КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount(); ИндексСтолбикаJson++) {
                String НазваниеСтолбикаJson = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnName(ИндексСтолбикаJson);// TODO: 14.03.2023 Название как текст столбика в JSON  NAme
                String СодержимоеСтолбикаJson= КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(ИндексСтолбикаJson) ;// TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                СодержимоеСтолбикаJson=Optional.ofNullable(СодержимоеСтолбикаJson).orElse("");
                switch (НазваниеСтолбикаJson.trim()){
                    case "_id":
                    case "id":
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +  " СодержимоеСтолбикаJson " +СодержимоеСтолбикаJson);
                        break;
                    case "uuid":
                    case "current_table":
                        Long UUIDandCurrenttableValue=  Long.parseLong(СодержимоеСтолбикаJson);
                        serializers.defaultSerializeField(НазваниеСтолбикаJson,UUIDandCurrenttableValue.toString(), jsonGenerator);
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson   " + НазваниеСтолбикаJson);
                        break;
                    default:
                        serializers.defaultSerializeField(НазваниеСтолбикаJson,СодержимоеСтолбикаJson.toString(), jsonGenerator);
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson  " + НазваниеСтолбикаJson);
                        break;
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " НазваниеСтолбикаJson " +НазваниеСтолбикаJson +"\n"+ " СодержимоеСтолбикаJson " +СодержимоеСтолбикаJson);
            }
            jsonGenerator.writeEndObject();
            Log.d(this.getClass().getName(), " jsonObjectWriter.toString()   " + jsonGenerator.toString());
        } while (КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToNext());////ДАННЫЕ КРУТИЯТЬСЯ ДО КОНЦА ДАННЫХ И ГЕНЕРИРУЮ JSON
            jsonGenerator.writeEndObject();
        jsonGenerator.flush();
        jsonGenerator.close();
        КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}
