package com.dsy.dsu.OneSignals;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;

import com.dsy.dsu.BusinessLogicAll.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.Firebase.MyFirebaseMessagingService;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.crypto.NoSuchPaddingException;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;



 public class ClassOneSingnalGenerator {
     private Context context;

     private  Class_Generator_One_WORK_MANAGER class_generator_one_work_manager;

     String НовыйКлючОтOneSingnal;

     private SQLiteDatabase sqLiteDatabase ;
    public ClassOneSingnalGenerator(@NonNull  Context context) {
        this.context=context;
        if (context!=null) {
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            class_generator_one_work_manager=new Class_Generator_One_WORK_MANAGER(context);
            Log.d(this.getClass().getName(), " ClassOneSingnalGenerator   context  "+context);
        }

    }
    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР СИНХРОГНИАЗУИЯ ДАННЫ



     public void МетодЗапускаУведомленияДляЗАДАЧТолькоПриСменеСтатусаОтказВыполнил() {
         try {
             // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
             Integer  ПубличныйIDДляАсих=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID( context);

             Data myDataSingleWorker = new Data.Builder()
                     .putInt("ПубличныйID", ПубличныйIDДляАсих)
                     .putBoolean("StartSingleWorker", true)
                     .build();
             // TODO: 02.08.2022
             new Class_Generator_One_WORK_MANAGER(context).МетодОдноразовыйЗапускВоерМенеджера(context,myDataSingleWorker);
             // TODO: 11.01.2022
         } catch (Exception e) {
             e.printStackTrace();
             Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                     + Thread.currentThread().getStackTrace()[2].getLineNumber());
             new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                     Thread.currentThread().getStackTrace()[2].getLineNumber());
         }
     }




    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
    public void МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(@NonNull String КлючДляFirebaseNotification,
                                                                     Integer ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника) {

        Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal=new Class_GRUD_SQL_Operations(context);
        PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);
        try {
            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable.interval(10,TimeUnit.SECONDS)
                  .take(2,TimeUnit.MINUTES)
                  .subscribeOn(Schedulers.io())
                  .doOnNext(new Consumer<Long>() {
                      @Override
                      public void accept(Long aLong) throws Throwable {
                          Log.w(context.getClass().getName(), "   Iterable<?> apply МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName());
                          // TODO: 05.01.2022
                          НовыйКлючОтOneSingnal=     МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(КлючДляFirebaseNotification);
                          // TODO: 06.01.2022
                          Log.w(context.getClass().getName(), "  onNext МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName()+"\n"  + " FIREBASE  НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal );
                      }
                  })
                  .doOnError(new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Throwable {
                          // TODO: 06.01.2022
                          Log.e(context.getClass().getName(), "  doOnError МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName());
                      }
                  })
                  .takeWhile(new Predicate<Object>() {
                      @Override
                      public boolean test(Object o) throws Throwable {
                          // TODO: 26.12.2021
                          Log.w(context.getClass().getName(), "   takeWhile МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName()+ "  o " +o);
                          if (   НовыйКлючОтOneSingnal !=null) {

                              Log.w(context.getClass().getName(), "  ДЛЯ ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ (телефона)Ключ ПришелОтСЕРВЕРА SUCEESSSSSS !!!@!  " +
                                      " takeWhile МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal САМ КЛЮЧ ::::" +
                                      "  "+"\n"
                                      + НовыйКлючОтOneSingnal +"\n"+
                                      " Thread.currentThread().getName() " +Thread.currentThread().getName());
                              // TODO: 04.01.2022
                              return false;
                          }else {
                              Log.w(context.getClass().getName(), "  ДЛЯ ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ (телефона)Ключ ПришелОтСЕРВЕРА SUCEESSSSSS !!!@!  " +
                                      " takeWhile МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal САМ КЛЮЧ ::::" +
                                      "  "+"\n"
                                      + НовыйКлючОтOneSingnal +"\n"+
                                      " Thread.currentThread().getName() " +Thread.currentThread().getName());
                              return true;
                          }
                      }
                  })
                  .doOnComplete(new Action() {
                      @Override
                      public void run() throws Throwable {
                          Log.w(context.getClass().getName(), " doOnTerminate  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal" + НовыйКлючОтOneSingnal);
                          // TODO: 06.01.2022
                          методЗаписиNewKeyOneSignal(class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal,
                                  ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника,
                                  public_contentменеджер);
                          // TODO: 06.01.2022
                          Log.w(context.getClass().getName(), "  onComplete МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName()); 
                      }
                  })
                  .observeOn(AndroidSchedulers.mainThread())
                    .toFlowable(BackpressureStrategy.BUFFER).subscribe();
// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данные
            // TODO: 05.01.2022  ДЕЛАЕМ ПОДПИСКУ НА ОСУЩЕСТВЛЛЕНУЮ ДАННЫХ
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }


    private void методЗаписиNewKeyOneSignal(
            Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal,
                          Integer ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника,
                           PUBLIC_CONTENT public_contentменеджер) {
        try{
            String СтарыйКлючОтOneSignal = null;
            // TODO: 23.12.2021 второй код получние данных ключа если они еть и записатьв базу
            if (НовыйКлючОтOneSingnal !=null) {
                Log.w("OneSignalExample", "РезультатЗаписиНовогоIDОтСервреаOneSignal "+  "РезультатЗаписиНовогоIDОтСервреаOneSignal   "
                        + НовыйКлючОтOneSingnal);
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","settings_tabels");
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("СтолбцыОбработки","onesignal");
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("ФорматПосика","  onesignal=? ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("УсловиеПоиска1",
                        НовыйКлючОтOneSingnal.trim());
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор.put("УсловиеЛимита","1");

                // TODO: 03.08.2023  вытаскиваем Данные
                SQLiteCursor     Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL = (SQLiteCursor)
                        class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков, sqLiteDatabase);

                Log.d(this.getClass().getName(), "Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL "
                        +Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL  );
                if(Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.getCount()>0){
                    // TODO: 22.12.2021
                    Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.moveToFirst();
                    СтарыйКлючОтOneSignal=Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.getString(0);
                }

                Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.close();
                
                Log.d(this.getClass().getName(), "СтарыйКлючОтOneSignal "+
                        СтарыйКлючОтOneSignal +"\n"+
                        " НовыйКлючОтOneSingnal " + НовыйКлючОтOneSingnal);
                // TODO: 10.12.2022 проверка условия сстоит записывать ключновый или нет от SINGONE FIREBASE
                if (СтарыйКлючОтOneSignal ==null ||
                        ! СтарыйКлючОтOneSignal.
                                equalsIgnoreCase(НовыйКлючОтOneSingnal)) {
                    // TODO: 04.01.2022  ПРИШЕЛ НОВЫЙ КЛЮЧ И ЕГО НАДО ЗАПИСАТЬ ДЛЯ ONESINGNAL
                    new writernewKeyOneSignal(context,
                            НовыйКлючОтOneSingnal,  sqLiteDatabase);
                    Log.w("OneSignalExample", " ВНИМАНИЕ !!!!!!  ЗАПИСЬ НОВОГО КЛЮЧА ДЛЯ ДАННОГО ПОЛЬЗОВАТЕЛЯ ONESIGNAL " +
                            "РезультатЗаписиНовогоIDОтСервреаOneSignal " + "\n"
                            + "РезультатЗаписиНовогоIDОтСервреаOneSignal   " + НовыйКлючОтOneSingnal +"\n"+
                            "СтарыйКлючОтOneSignal "+
                            СтарыйКлючОтOneSignal);
                }else{
                    Log.w("OneSignalExample", "СТАРЫЙ КЛЮЧ ДЕЙСТВУЕТ ДЛЯ КЛЮЧА ДЛЯ ДАННОГО ПОЛЬЗОВАТЕЛЯ ONESIGNAL  РезультатЗаписиНовогоIDОтСервреаOneSignal   "
                            +  "СтарыйКлючОтOneSignal[0]   "
                            + СтарыйКлючОтOneSignal
                            +  "РезультатЗаписиНовогоIDОтСервреаOneSignal   "
                            + НовыйКлючОтOneSingnal);
                }
                Log.d(this.getClass().getName(), "ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника "+ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника  );
                class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal=new Class_GRUD_SQL_Operations(context);
                // TODO: 10.12.2022 следующие действие после записеи поновго ключа от ONESINGANL
                if (ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника>0) {
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","view_onesignal");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("СтолбцыОбработки","onesignal");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("ФорматПосика","  user_update=?  AND onesignal IS NOT NULL");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("УсловиеПоиска1",ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника);
                    Log.w(this.getClass().getName(), "  СРАБОТАЛО ОТПРАВЛЯЕМ СООБЩЕНИЕ СТРОКО ПОЛЬЗОВАТЕЛЮ ............ SEND MESSAGE DSU1 ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника "+ОтправкаСообщенияТолькоСтрогоОдномуУказанномуСотрудника  );
                } else {
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","view_onesignal");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("СтолбцыОбработки","onesignal");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("ФорматПосика","  onesignal!=?  ");
                    class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                            concurrentHashMapНабор.put("УсловиеПоиска1", НовыйКлючОтOneSingnal);
                    Log.d(this.getClass().getName(), " НовыйКлючОтOneSingnal "+ НовыйКлючОтOneSingnal);
                    Log.d(this.getClass().getName(), "  СРАБОТАЛО ОТПРАВЛЯЕМ СООБЩЕНИЕ все мпользоватем кторые есть в базе  ............ SEND MESSAGE DSU1  НовыйКлючОтOneSingnal" +
                            ""+ НовыйКлючОтOneSingnal);
                }
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                SQLiteCursor     Курсор_ПолучаемВесьСписокIDДляONESIGNAL = (SQLiteCursor)  class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                        new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                                concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков,
                        sqLiteDatabase);
                Log.d(this.getClass().getName(), "Курсор_ПолучаемВесьСписокIDДляONESIGNAL "+Курсор_ПолучаемВесьСписокIDДляONESIGNAL  );
                // TODO: 15.12.2021
                if(Курсор_ПолучаемВесьСписокIDДляONESIGNAL.getCount()>0){
                    // TODO: 15.12.2021
                    Курсор_ПолучаемВесьСписокIDДляONESIGNAL.moveToFirst();
                    do{
                        String КлючТекущйщийПолученныйДляОбменаOneSignal=  Курсор_ПолучаемВесьСписокIDДляONESIGNAL.getString(0).trim();
                        Log.d(this.getClass().getName(), "КлючТекущйщийПолученныйДляОбменаOneSignal "+КлючТекущйщийПолученныйДляОбменаOneSignal  );
                        Log.d(this.getClass().getName(), " КлючТекущйщийПолученныйДляОбменаOneSignal"+ КлючТекущйщийПолученныйДляОбменаOneSignal+"\n"+
                                "ПоулчаемДляТекущегоПользователяIDОтOneSignal  "+ НовыйКлючОтOneSingnal);
                        Date Дата = null;
                        DateFormat dateFormat = null;//"yyyy-MM-dd HH:mm:ss.SSS"//"yyyy-MM-dd'T'HH:mm:ss'Z'"
                        Дата = Calendar.getInstance().getTime();
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
                        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                        String ДатаФинал= dateFormat.format(Дата);
                        try {
                            OneSignal.postNotification(new JSONObject("{" +
                                            " 'include_player_ids': ['" + КлючТекущйщийПолученныйДляОбменаOneSignal + "']," +
                                            " 'app_id': '2a1819db-60c8-4ca3-a752-1b6cd9cadfa1'," +
                                            " 'android_background_data': 'true'," +
                                            " 'content_available': 'true'," +
                                            " 'data': {'grp_msg': 'android'} } "),

                                /*    OneSignal.postNotification(new JSONObject("{" +
                                                    " 'include_player_ids': ['" + ТекущйUUIDКомуНужноСообщение + "']," +
                                                    " 'app_id': '2a1819db-60c8-4ca3-a752-1b6cd9cadfa1'," +
                                                    " 'android_background_data': 'true'," +
                                                    " 'content_available': 'true'," +
                                                    " 'data': {'grp_msg': 'android'} } "),*/
                                    new OneSignal.PostNotificationResponseHandler() {
                                        @Override
                                        public void onSuccess(JSONObject response) {
                                            // TODO: 13.01.2022  успешний пинг с пользователями которым нужноотпавить сообщения

                                            Log.d(this.getClass().getName(), "\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                                    + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " onSuccess  КлючТекущйщийПолученныйДляОбменаOneSignal " +КлючТекущйщийПолученныйДляОбменаOneSignal);
                                        }
                                        @Override
                                        public void onFailure(JSONObject response) {
                                            // TODO: 13.01.2022  НЕт пинга КЛЮЧ ИЛИ НЕТУ ИЛИ УСТАРЕЛЛ  пинг с пользователями которым нужноотпавить сообщения
                                            Log.d(this.getClass().getName(), "\n" + " class " +
                                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                                    + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " onFailure  КлючТекущйщийПолученныйДляОбменаOneSignal " +КлючТекущйщийПолученныйДляОбменаOneSignal);
                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }while (Курсор_ПолучаемВесьСписокIDДляONESIGNAL.moveToNext());
                    // TODO: 03.08.2023  clear
                    Курсор_ПолучаемВесьСписокIDДляONESIGNAL.close();
                }
            }else{
                Log.e(this.getClass().getName(), " НЕТ ПОКА КЛЮЧА  ДЛЯ , СКОРЕЙ ВСЕГО ПЕРВЫЫЙ ЩЗАПУСК ПОСЛЕ КЛЮЧ ДЛЯ  OneSignal..."+"\n"+
                        "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggerValueForKey("GT_PLAYER_ID")+
                        "     OneSignal.getTriggers() " +   OneSignal.getTriggers()+"\n"+
                        "    НовыйКлючОтOneSingnal " + НовыйКлючОтOneSingnal);
            }

        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    // TODO: 24.12.2021  МетодПолучение Статуса Ключа От Сервера OneSignal

    private String МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(@NonNull String КлючДляFirebaseNotification) {
        try{
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ  OneSignal........  2a1819db-60c8-4ca3-a752-1b6cd9cadfa1 "+ КлючДляFirebaseNotification +"\n");
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            //String КлючДляFirebaseNotification="2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
            // todo OneSignal Initialization
            OneSignal.initWithContext(context);
            ///////todo srating Google Notifications wits PUblic Key
            OneSignal.setAppId(КлючДляFirebaseNotification);
            OneSignal.disablePush(false);
            //TODO srating.......... firebase cloud --ПРИШЛО СООБЩЕНИЕ
            FirebaseMessagingService firebaseMessagingService =new MyFirebaseMessagingService();
            //TODO srating......  oneSignal
            Log.d(this.getClass().getName(), "  FirebaseMessagingService"  );
            // TODO: 07.12.2021
            firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");
            Log.d(this.getClass().getName(), "  КЛЮЧ ДЛЯ  КОНЕЦ  OneSignal........  2a1819db-60c8-4ca3-a752-1b6cd9cadfa1 " );
            Map<String, String> params = new HashMap<String, String>();
            OneSignal.sendTag("Authorization", "Basic 2a1819db-60c8-4ca3-a752-1b6cd9cadfa1");
            OneSignal.sendTag("Content-type", "application/json");
            OneSignal.sendTag("grp_msg", "android");
            OneSignal.sendTag("android_background_data", "true");
            OneSignal.sendTag("content_available", "true");
            //TODO srating......  oneSignal
        String ПушТОкен=    OneSignal.getDeviceState().getPushToken();
            НовыйКлючОтOneSingnal = OneSignal.getDeviceState().getUserId();
            // TODO: 15.12.2021
            Log.d(this.getClass().getName(), "  ПОСЛЕ КЛЮЧ ДЛЯ  OneSignal........  2a1819db-60c8-4ca3-a752-1b6cd9cadfa1 "+"\n"+

                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggerValueForKey("GT_PLAYER_ID")+
                    "     OneSignal.getTriggers() " +   OneSignal.getTriggers()+"\n"+
                    "    НовыйКлючОтOneSingnal ОТ СЕРВЕРА ::: " + НовыйКлючОтOneSingnal + "\n"+ПушТОкен+" BREMY  " +new Date().toLocaleString());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        // TODO: 11.05.2021 запись ошибок

    }
        return НовыйКлючОтOneSingnal;
    }


}


































// TODO: 15.12.2021 КЛАСС ЗАПИСЫВАЕТ ЕСЛИ ИЗМЕННЕНОЕ ЗНАЧЕНИЕ НА СЕРВЕРА ДЛЯ ТЕКУЩЕГО ПОЛЬЗВОАТЕЛЯ ИЗМЕНИЛОСЬ НА ONESIGNAL

   class writernewKeyOneSignal {
       private SQLiteDatabase sqLiteDatabase ;
       public writernewKeyOneSignal(Context context,
                                    String НовыйIdОТСервтераOneSignal,
                                    SQLiteDatabase sqLiteDatabase ) {
           Class_GRUD_SQL_Operations   class_grud_sql_operationsОбновлениеДляТаблицыOneSignal=new Class_GRUD_SQL_Operations(context);
           Class_GRUD_SQL_Operations        class_grud_sql_operationsПовышаемВерсиюДанныхДляOneSignal=new Class_GRUD_SQL_Operations(context);
           try{
               this.sqLiteDatabase=sqLiteDatabase;
// TODO: 22.12.2021  находими пуличный id
               // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
               // TODO: 30.09.2021 МЕТОД ЗАПУСКА СИНХРОНИЗАЦИИ ЧАТА ПО РАСПИСАНИЮ , НЕ ВЗАВИСИМОСТИ ОТ СОЗДАВАЛ ЛИ СООБЩЕНИЕ ИЛИ НЕТ
               Integer  ПубличныйIDДляОдноразовойСинхрониазции=
                       new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);
               Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента  ИЗ ВСЕХ ТАБЕЛЕЙ ПубличныйIDДляОдноразовойСинхрониазции "
                       + ПубличныйIDДляОдноразовойСинхрониазции);
               /// TODO ########################################################втоаря часть  settings_tabels    ПЕРВАЯ ОБРАБОТКА ТАБЛИЦА  settings_tabels
               Observable observableСменыКлючаИЗАписьНовогоOneSinglal=   Observable.fromArray("settings_tabels","view_onesignal")
                       .doOnNext(new Consumer<String>() {
                           @Override
                           public void accept(String ТаблицаДляПолучениеКлючаONESIGNAL) throws Throwable {
                               // TODO: 13.01.2022 САМА ВСТАВКА НОВОГО КЛЮЧА В ТАБЛИЦУ НАСТРОЙКИ СИСТЕМЫ
                               Integer   РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal=
                                       МетодПервыйОбоаботкиПервойТаблицыПриИзмененияКлюча_settings_tabelsОбаview_onesignal(context,
                                               НовыйIdОТСервтераOneSignal,
                                               class_grud_sql_operationsОбновлениеДляТаблицыOneSignal,
                                               class_grud_sql_operationsПовышаемВерсиюДанныхДляOneSignal,
                                               ПубличныйIDДляОдноразовойСинхрониазции
                                               ,ТаблицаДляПолучениеКлючаONESIGNAL);
                               Log.i(this.getClass().getName(), "  РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal   "
                                       + РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal);
                               ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT
                               /// TODO ########################################################ЧАСТЬ ТРЕТЬЯ УДАЛЕНИЯ ДАННЫХ В ТАБЛИЦАХ настройки системы
                               if (РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal>0) {
                                   // TODO: 29.12.2021 ЧАСТЬ ТЕРТЬЯ УДАЛАЕНИЯ ЛИШНЕХ КЛЮЧЕЙ СТАРЫХ ИЗ ДВУХ ТАБЛЦ ПО ТЕКУЩЕМУ ПОЛЬЗОВАТЕЛ
                                   Integer РезультатПосикаИУдалениявТаблицах_settings_tabels = МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(context,
                                           ТаблицаДляПолучениеКлючаONESIGNAL,
                                           ПубличныйIDДляОдноразовойСинхрониазции,
                                           НовыйIdОТСервтераOneSignal);
                                   // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                                   Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                           + РезультатПосикаИУдалениявТаблицах_settings_tabels+ "  ПубличныйIDДляФрагмента " +ПубличныйIDДляОдноразовойСинхрониазции+
                                           " НовыйIdОТСервтераOneSignal " +НовыйIdОТСервтераOneSignal+
                                           " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                           +ТаблицаДляПолучениеКлючаONESIGNAL);
                                   // TODO: 24.02.2022 увеличение данных после смены ключа
                                   if (ТаблицаДляПолучениеКлючаONESIGNAL.equalsIgnoreCase("settings_tabels")) {
                                       // TODO: 24.02.2022
                                       МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(context,
                                               ТаблицаДляПолучениеКлючаONESIGNAL,
                                               РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal);
                                       // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                                       Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                               + РезультатПосикаИУдалениявТаблицах_settings_tabels+ "  ПубличныйIDДляФрагмента " +ПубличныйIDДляОдноразовойСинхрониазции+
                                               " НовыйIdОТСервтераOneSignal " +НовыйIdОТСервтераOneSignal+
                                               " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                               +ТаблицаДляПолучениеКлючаONESIGNAL);
                                   }
                               }
                           }
                       })
                       .onErrorComplete(new Predicate<Throwable>() {
                           @Override
                           public boolean test(Throwable throwable) throws Throwable {
                               Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                       + Thread.currentThread().getStackTrace()[2].getLineNumber());
                               new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                       Thread.currentThread().getStackTrace()[2].getLineNumber());
                               return false;
                           }
                       })
                       .doOnComplete(new Action() {
                           @Override
                           public void run() throws Throwable {
                               Log.w(this.getClass().getName(), " doOnComplete РезультатПосикаИУдалениявТаблицах_settings_tabels  throwable ");
                           }
                       });
               observableСменыКлючаИЗАписьНовогоOneSinglal.blockingSubscribe(System.out::println);
               Object ФиналРЕзультатКЛЮЧНОВЫЙ  =observableСменыКлючаИЗАписьНовогоOneSinglal.blockingStream().findAny().get();


                   Log.d(this.getClass().getName(), " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   "
                           +  ФиналРЕзультатКЛЮЧНОВЫЙ+"\n"+
                           " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   "   +  ФиналРЕзультатКЛЮЧНОВЫЙ+"\n"+
                           " ONESIGNAL  КЛЮЧНОВЫЙ  ФиналРЕзультатКЛЮЧНОВЫЙ !!!   "   +  ФиналРЕзультатКЛЮЧНОВЫЙ+"\n");
           } catch (Exception e ) {
               e.printStackTrace();
               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                       + Thread.currentThread().getStackTrace()[2].getLineNumber());
               new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                       Thread.currentThread().getStackTrace()[2].getLineNumber());
           }

       }



       private void МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(Context context
       ,String ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL
       ,Integer РезультатОбновленияКлючаДляПервойТаблицыsettings_tabelssОбаview_onesignal)
               throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
           try{
           Class_GRUD_SQL_Operations class_grud_sql_operationsПовышаемВерсиюДанныхПриПолученииНовогоКлючаONESINGLE=new Class_GRUD_SQL_Operations(context);
               // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
               Long РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE=
                       new SubClassUpVersionDATA().МетодПовышаемВерсииMODIFITATION_Client(
                               ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL,context);
               Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE  " +
                       РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE);

           } catch (Exception e ) {
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                   + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
       }


       @NonNull
       private Integer МетодПервыйОбоаботкиПервойТаблицыПриИзмененияКлюча_settings_tabelsОбаview_onesignal(Context context,
                                  String НовыйIdОТСервтераOneSignal,
                                  Class_GRUD_SQL_Operations class_grud_sql_operationsОбновлениеДляТаблицыOneSignal,
                                  Class_GRUD_SQL_Operations class_grud_sql_operationsПовышаемВерсиюДанныхДляOneSignal,
                                    Integer ПубличныйIDДляФрагмента,
                                                                                          String ТаблицаОбрработкиВСдлужбеOneSignal)
               throws ExecutionException, InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
           Integer РезультатОбновленияКлючаOneSignal = 0;


           try{
           ContentValues АдаптерВставкиПолученогоПубличногоID = new ContentValues();

               // TODO: 29.12.2021 one table

           // TODO: 15.12.2021  запусываем новый Индефикатор для Работы OneSignal

           Log.d(this.getClass().getName(), "НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal+  " ТаблицаОбрработкиВСдлужбеOneSignal " +ТаблицаОбрработкиВСдлужбеOneSignal);


           АдаптерВставкиПолученогоПубличногоID.put("onesignal", НовыйIdОТСервтераOneSignal);





           ////TODO ДАТА
           String СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного=
                   new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();


           АдаптерВставкиПолученогоПубличногоID.put("date_update", СгенерированованныйДатаДляЗАписиПолученогоОтСервреаIDПубличного);

           // TODO: 29.12.2021

           АдаптерВставкиПолученогоПубличногоID.put("uuid", ПубличныйIDДляФрагмента);


               // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
               Long РезультатУвеличинаяВерсияДАныхЧата=
                       new SubClassUpVersionDATA().МетодПовышаемВерсииMODIFITATION_Client(    ТаблицаОбрработкиВСдлужбеOneSignal,context);
               Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);

               // TODO: 27.08.2021 само значние
               Log.w(context.getClass().getName(), "РезультатУвеличинаяВерсияДАныхЧата  получлили увеличиную верисю данных в чате новоом КЛЮЧЕ " + РезультатУвеличинаяВерсияДАныхЧата);
           //TODO  конец курант ча
           //////
           АдаптерВставкиПолученогоПубличногоID.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);

           /////////


           ///TODO ОБНОЛВЕНИЕ

           // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ ОБНОВЛЕНИЯ

           class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ТаблицаОбрработкиВСдлужбеOneSignal);
           //

           class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением","user_update");




           ///
               class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.concurrentHashMapНабор.put("ЗнакФлагОбновления","uuid=");


           //

           class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.concurrentHashMapНабор.put("ЗначениеФлагОбновления", ПубличныйIDДляФрагмента);
           ///

           //

           class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.
                   concurrentHashMapНабор.put("ЗнакФлагОбновления","=");

           ////TODO КОНТЕЙНЕР ДЛЯ ОБНОВЛЕНИЯ

           class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(АдаптерВставкиПолученогоПубличногоID);


           ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


           РезультатОбновленияКлючаOneSignal = 0;

           РезультатОбновленияКлючаOneSignal = (Integer)  class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.
                   new UpdateData(context).updatedata(class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.concurrentHashMapНабор,
                   class_grud_sql_operationsОбновлениеДляТаблицыOneSignal.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                   new PUBLIC_CONTENT(context). МенеджерПотоков,  sqLiteDatabase);


           // TODO: 15.12.2021  увеличиваем версию данных в таблице обшей модификацион клиенв
           Log.i(this.getClass().getName(), "  РезультатОбновленияКлючаOneSignal " +РезультатОбновленияКлючаOneSignal);
           ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT


       } catch (Exception e ) {
           //  Block of code to handle errors
           e.printStackTrace();
           ///метод запись ошибок в таблицу
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                   + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());

           // TODO: 11.05.2021 запись ошибок


       }
           return РезультатОбновленияКлючаOneSignal;
       }




























       // TODO: 29.12.2021
         protected  Integer МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(Context context,
                                                                                         String ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL,
                                                                                         Integer ПубличныйIDДляФрагмента
         ,String НовыйIdОТСервтераOneSignal) {

    Integer РЕзультаПосикаИУдаления=0;
           try{

               // TODO: 15.12.2021  увеличиваем версию данных в таблице обшей модификацион клиенв
               Log.i(this.getClass().getName(), "  пОИСК ИУДАЛЕНИЯ ДУБЛЕЙ В ТАБЛИЦАХ КЛЮЧЕЙ" +
                       "ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL  "+ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL+ " ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента+
                        "  НовыйIdОТСервтераOneSignal " +НовыйIdОТСервтераOneSignal);
               ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT




               Class_GRUD_SQL_Operations class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц=new Class_GRUD_SQL_Operations(context);

               // TODO: 06.09.2021  ПАРАМЕНТЫ ДЛЯ удаление данных

               class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                       concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы", ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL);
               //

               class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                       concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеУдаление", "onesignal <> ? AND  user_update =?");
            ///

             class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                       .concurrentHashMapНабор.put("ЗначениеФлагУдаление",НовыйIdОТСервтераOneSignal);
               class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц
                       .concurrentHashMapНабор.put("ЗначениеФлагУдалениеВторой", ПубличныйIDДляФрагмента);



               ///TODO РЕЗУЛЬТАТ ОБНОВЛЕНИЕ ДАННЫХ


               РЕзультаПосикаИУдаления = (Integer) class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                       new DeleteData(context).deletedata(class_grud_sql_operationclass_grud_sql_operationsОчисткаsОчистакаталиц.
                               concurrentHashMapНабор,
                      new PUBLIC_CONTENT(context). МенеджерПотоков, sqLiteDatabase);


               Log.i(this.getClass().getName(), "  РЕзультаПосикаИУдаления" +
                       РЕзультаПосикаИУдаления);






           } catch (Exception e ) {
               //  Block of code to handle errors
               e.printStackTrace();
               ///метод запись ошибок в таблицу
               Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                       + Thread.currentThread().getStackTrace()[2].getLineNumber());
               new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                       Thread.currentThread().getStackTrace()[2].getLineNumber());

               // TODO: 11.05.2021 запись ошибок


           }
           return РЕзультаПосикаИУдаления;
       }




   }
