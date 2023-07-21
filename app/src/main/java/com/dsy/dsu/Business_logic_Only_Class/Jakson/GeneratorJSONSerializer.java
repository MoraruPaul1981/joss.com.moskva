package com.dsy.dsu.Business_logic_Only_Class.Jakson;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Blob;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

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
            // TODO: 20.07.2023 Строки 
            Flowable.range(0,КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount())
                    .onBackpressureBuffer(КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount(),true)
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {

                            Long Key=   КурсорДляОтправкиДанныхНаСерверОтАндройда.getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid"));
                            jsonGenerator.writeFieldId(Key);
                            jsonGenerator.writeStartObject();

                            // TODO: 20.07.2023 Колонки
                            Flowable.range(0,КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount())
                                    .onBackpressureBuffer(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount(),true)
                                    .doOnNext(new Consumer<Integer>() {
                                        @Override
                                        public void accept(Integer ИндексСтолбикаJson) throws Throwable {


                                            String НазваниеСтолбикаJson = КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                    .getColumnName(ИндексСтолбикаJson).trim();// TODO: 14.03.2023 Название как текст столбика в JSON  NAme
                                            switch (НазваниеСтолбикаJson.trim()){
                                                case "_id":
                                                case "id":
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson);
                                                    break;
                                                case "uuid":
                                                case "current_table":
                                                case "uuid_tabel":
                                                case "parent_uuid":
                                                case "fio":
                                                    Long UUIDandCurrenttableValue= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    UUIDandCurrenttableValue=Optional.ofNullable(UUIDandCurrenttableValue).orElse(0l);
                                                    // TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,UUIDandCurrenttableValue, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " UUIDandCurrenttableValue " +UUIDandCurrenttableValue);
                                                    break;
                                                case "user_update":
                                                case "prof":
                                                    Integer Getuser_update= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getInt(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    Getuser_update=Optional.ofNullable(Getuser_update).orElse(0);// TODO: 14.03.2023
                                                    // Само Полученое содеожимое столбика Value
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,Getuser_update, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " Getuser_update " +Getuser_update);
                                                    break;
                                                case "image":
                                                case "file":
                                                    byte[] GetBlobImage= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getBlob(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    GetBlobImage=Optional.ofNullable(GetBlobImage).orElse(new String().getBytes());// TODO: 14.03.2023
                                                    // Само Полученое содеожимое столбика Value
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,GetBlobImage, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " GetBlobImage " +GetBlobImage);
                                                    break;
                                                default:
                                                    String СодержимоеСтолбикаJson= КурсорДляОтправкиДанныхНаСерверОтАндройда
                                                            .getString(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                                                    // TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                                                    СодержимоеСтолбикаJson=Optional.ofNullable(СодержимоеСтолбикаJson).orElse("");
                                                    // TODO: 17.05.2023
                                                    serializers.defaultSerializeField(НазваниеСтолбикаJson,СодержимоеСтолбикаJson, jsonGenerator);
                                                    Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +
                                                            " СодержимоеСтолбикаJson " +СодержимоеСтолбикаJson);
                                                    break;
                                            }

                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " getColumnCount " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount() +
                                                    "ИндексСтолбикаJson" + ИндексСтолбикаJson);
                                        }
                                    }).blockingSubscribe();
                        }
                    })
                    .doAfterNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {

                            jsonGenerator.writeEndObject();
                            Log.d(this.getClass().getName(), " jsonObjectWriter.toString()   " + jsonGenerator.toString());

                            КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToNext();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " getPosition " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getPosition());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            jsonGenerator.writeEndObject();
                            jsonGenerator.flush();
                            jsonGenerator.close();
                            КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .blockingSubscribe();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

  /*          // TODO: 20.07.2023
        do {
         Long Key=   КурсорДляОтправкиДанныхНаСерверОтАндройда.getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex("uuid"));
            jsonGenerator.writeFieldId(Key);
            jsonGenerator.writeStartObject();
            // TODO: 14.03.2023  генериуем по столбцам
            for (int ИндексСтолбикаJson = 0; ИндексСтолбикаJson < КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnCount(); ИндексСтолбикаJson++) {
                String НазваниеСтолбикаJson = КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnName(ИндексСтолбикаJson).trim();// TODO: 14.03.2023 Название как текст столбика в JSON  NAme
                switch (НазваниеСтолбикаJson.trim()){
                    case "_id":
                    case "id":
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson);
                        break;
                    case "uuid":
                    case "current_table":
                        Long UUIDandCurrenttableValue= КурсорДляОтправкиДанныхНаСерверОтАндройда.getLong(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                        UUIDandCurrenttableValue=Optional.ofNullable(UUIDandCurrenttableValue).orElse(0l);// TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                        serializers.defaultSerializeField(НазваниеСтолбикаJson,UUIDandCurrenttableValue.toString(), jsonGenerator);
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +  " UUIDandCurrenttableValue " +UUIDandCurrenttableValue);
                        break;
                    case "user_update":
                        Integer Getuser_update= КурсорДляОтправкиДанныхНаСерверОтАндройда.getInt(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;
                        Getuser_update=Optional.ofNullable(Getuser_update).orElse(0);// TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                        serializers.defaultSerializeField(НазваниеСтолбикаJson,Getuser_update.toString(), jsonGenerator);
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +  " Getuser_update " +Getuser_update);
                        break;
                    default:
                        String СодержимоеСтолбикаJson= КурсорДляОтправкиДанныхНаСерверОтАндройда.getString(КурсорДляОтправкиДанныхНаСерверОтАндройда.getColumnIndex(НазваниеСтолбикаJson)) ;// TODO: 14.03.2023  Само Полученое содеожимое столбика Value
                        СодержимоеСтолбикаJson=Optional.ofNullable(СодержимоеСтолбикаJson).orElse("");
                        // TODO: 17.05.2023
                        serializers.defaultSerializeField(НазваниеСтолбикаJson,СодержимоеСтолбикаJson.toString(), jsonGenerator);
                        Log.d(this.getClass().getName(), " НазваниеСтолбикаJson ::    " + НазваниеСтолбикаJson +  " СодержимоеСтолбикаJson " +СодержимоеСтолбикаJson);
                        break;
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " НазваниеСтолбикаJson " +НазваниеСтолбикаJson +"\n");
            }
            jsonGenerator.writeEndObject();
            Log.d(this.getClass().getName(), " jsonObjectWriter.toString()   " + jsonGenerator.toString());
        } while (КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToNext());////ДАННЫЕ КРУТИЯТЬСЯ ДО КОНЦА ДАННЫХ И ГЕНЕРИРУЮ JSON
            jsonGenerator.writeEndObject();
        jsonGenerator.flush();
        jsonGenerator.close();
        КурсорДляОтправкиДанныхНаСерверОтАндройда.close();*/
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
