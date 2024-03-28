package com.dsy.dsu.OneSignal.registOnesignal;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;

import com.dsy.dsu.BusinessLogicAll.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Firebase.MyFirebaseMessagingService;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.OneSignal.registOnesignal.WriterNewKeyOneSignal;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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








    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
    public void getGetRegistaziyNewKeyForOnoSignal(@NonNull String КлючДляFirebaseNotification) {

        final String[] НовыйКлючОтOneSingnal = new String[1];
        try {
            Class_GRUD_SQL_Operations listIDДляOneSignal=new Class_GRUD_SQL_Operations(context);
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);

            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable.interval(10,TimeUnit.SECONDS)
                  .take(10,TimeUnit.MINUTES)
                  .subscribeOn(Schedulers.io())
                  .doOnNext(new Consumer<Long>() {
                      @Override
                      public void accept(Long aLong) throws Throwable {
                          Log.w(context.getClass().getName(), "   Iterable<?> apply МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName());
                          // TODO: 05.01.2022
                       НовыйКлючОтOneSingnal[0] =     МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(КлючДляFirebaseNotification);
                          // TODO: 06.01.2022
                          Log.w(context.getClass().getName(), "  onNext МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal"  +"\n"+
                                  " Thread.currentThread().getName() " +Thread.currentThread().getName()+"\n"  + " FIREBASE  НовыйКлючОтOneSingnal " + НовыйКлючОтOneSingnal[0]);
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
                          if (   НовыйКлючОтOneSingnal[0] !=null) {

                              // TODO: 06.01.2022
                              методЗаписиNewKeyOneSignal(listIDДляOneSignal, public_contentменеджер,НовыйКлючОтOneSingnal[0]);


                              Log.d(this.getClass().getName(),"\n"
                                      + " bremy: " + new Date()+"\n+"
                                      + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НовыйКлючОтOneSingnal[0] " +НовыйКлючОтOneSingnal[0]);
                              // TODO: 04.01.2022
                              return false;
                          }else {
                              Log.w(context.getClass().getName(), "  ДЛЯ ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ (телефона)Ключ ПришелОтСЕРВЕРА SUCEESSSSSS !!!@!  " +
                                      " takeWhile МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal САМ КЛЮЧ ::::" +
                                      "  "+"\n"
                                      + НовыйКлючОтOneSingnal[0] +"\n"+
                                      " Thread.currentThread().getName() " +Thread.currentThread().getName());
                              return true;
                          }
                      }
                  })
                  .doOnComplete(new Action() {
                      @Override
                      public void run() throws Throwable {
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


    private void методЗаписиNewKeyOneSignal(@NonNull   Class_GRUD_SQL_Operations AllListIDДляOneSignal,
            @NonNull     PUBLIC_CONTENT public_contentменеджер,
            @NonNull String НовыйКлючОтOneSingnal) {
        try{


     String СтарыйКлючОтOneSignal=       getOldKeyOneSignal(  AllListIDДляOneSignal,public_contentменеджер,НовыйКлючОтOneSingnal);


            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                     " СтарыйКлючОтOneSignal " +СтарыйКлючОтOneSignal + " НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);

            setWriterNewKeyInSqlLite(НовыйКлючОтOneSingnal, СтарыйКлючОтOneSignal);

            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " СтарыйКлючОтOneSignal " +СтарыйКлючОтOneSignal);













        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

     private void setWriterNewKeyInSqlLite(@NonNull String НовыйКлючОтOneSingnal, String СтарыйКлючОтOneSignal) {
        try{
         // TODO: 10.12.2022 проверка условия сстоит записывать ключновый или нет от SINGONE FIREBASE
         if (НовыйКлючОтOneSingnal !=null  && СтарыйКлючОтOneSignal!=null) {
             if (  СтарыйКлючОтOneSignal.equalsIgnoreCase(НовыйКлючОтOneSingnal)) {
                 // TODO: 04.01.2022  ПРИШЕЛ НОВЫЙ КЛЮЧ И ЕГО НАДО ЗАПИСАТЬ ДЛЯ ONESINGNAL
                 new WriterNewKeyOneSignal(context, НовыйКлючОтOneSingnal,  sqLiteDatabase);

                 Log.d(this.getClass().getName(),"\n"
                         + " bremy: " + new Date()+"\n+"
                         + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                         " СтарыйКлючОтOneSignal " + СтарыйКлючОтOneSignal + " НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);
             }
         }
     } catch (Exception e ) {
         e.printStackTrace();
         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                 + Thread.currentThread().getStackTrace()[2].getLineNumber());
         new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                 Thread.currentThread().getStackTrace()[2].getLineNumber());
     }
     }

     private String getOldKeyOneSignal(@NonNull Class_GRUD_SQL_Operations class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal,
                                       @NonNull   PUBLIC_CONTENT public_contentменеджер,
                                       @NonNull String НовыйКлючОтOneSingnal) {
        String  СтарыйКлючОтOneSignal = null;
     try {

         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","settings_tabels");
         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("СтолбцыОбработки","onesignal");
         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("ФорматПосика","  onesignal=? ");
         ///"_id > ?   AND _id< ?"
         //////
         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("УсловиеПоиска1", НовыйКлючОтOneSingnal.trim());
         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");
         class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                 concurrentHashMapНабор.put("УсловиеЛимита","1");

         // TODO: 03.08.2023  вытаскиваем Данные
         SQLiteCursor     Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL = (SQLiteCursor)
                 class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                         new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйПолучениеВсегоСпискаIDДляOneSignal.
                         concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков, sqLiteDatabase);




         if(Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.getCount()>0){
             Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.moveToFirst();
             // TODO: 28.03.2024  получаем старй колюч
             СтарыйКлючОтOneSignal=Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.getString(0);
             Log.d(this.getClass().getName(),"\n"
                     + " bremy: " + new Date()+"\n+"
                     + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                     " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                     " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                     " СтарыйКлючОтOneSignal " +СтарыйКлючОтOneSignal);
         }

         Курсор_ПолучаемУжеЗагруженныйЕслиНОНЕИзменильсяIDДляONESIGNAL.close();
     } catch (Exception e ) {
         e.printStackTrace();
         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                 + Thread.currentThread().getStackTrace()[2].getLineNumber());
         new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                 Thread.currentThread().getStackTrace()[2].getLineNumber());
     }
       return   СтарыйКлючОтOneSignal;
     }


     // TODO: 24.12.2021  МетодПолучение Статуса Ключа От Сервера OneSignal

    private String МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(@NonNull String КлючДляFirebaseNotification) {
        String  НовыйКлючОтOneSingnal = null;
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
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " НовыйКлючОтOneSingnal "
                    + НовыйКлючОтOneSingnal + "\n"+ПушТОкен+" BREMY  " +new Date().toLocaleString()+
                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " + OneSignal.getTriggerValueForKey("GT_PLAYER_ID"));

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

