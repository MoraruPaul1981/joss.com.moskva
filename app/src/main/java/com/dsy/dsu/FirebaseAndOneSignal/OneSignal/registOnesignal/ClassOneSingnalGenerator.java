package com.dsy.dsu.FirebaseAndOneSignal.OneSignal.registOnesignal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.FirebaseAndOneSignal.Firebase.MyFirebaseInstanceIDService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.onesignal.OneSignal;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ClassOneSingnalGenerator {
     private Context context;
     private SQLiteDatabase sqLiteDatabase ;
    public ClassOneSingnalGenerator(@NonNull  Context context) {
        this.context=context;
        try{
        if (context!=null) {
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            Log.d(this.getClass().getName(), "sqLiteDatabase"+sqLiteDatabase);
        }

        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

    }

    }
    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР СИНХРОГНИАЗУИЯ ДАННЫ








    // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
    @SuppressLint("SuspiciousIndentation")
    public void getGetRegistaziyNewKeyForOnoSignal(@NonNull String КлючДляFirebaseNotification) {

        try {
            Class_GRUD_SQL_Operations listIDДляOneSignal=new Class_GRUD_SQL_Operations(context);
            PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);

            // TODO: 23.12.2021 ЧЕТЫРЕ ПОПЫТКИ ПОДКЛЮЧЕНИЕ В СЕВРЕРУONESIGNAL
            Observable.interval(0, 25, TimeUnit.SECONDS, Schedulers.single())
                    .timeInterval()
                    .take(1,TimeUnit.MINUTES)
                  .doOnError(new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Throwable {
                          // TODO: 06.01.2022
                          throwable.printStackTrace();
                          Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                  + Thread.currentThread().getStackTrace()[2].getLineNumber());
                          new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                  Thread.currentThread().getStackTrace()[2].getLineNumber());
                      }
                  })
                  .takeWhile(new Predicate<Object>() {
                      @Override
                      public boolean test(Object o) throws Throwable {
                          // TODO: 28.03.2024
                     String     НовыйКлючОтOneSingnal =   getNewKeyTokernFronOneSignal(КлючДляFirebaseNotification );

                          // TODO: 26.12.2021
                          if (   НовыйКлючОтOneSingnal !=null) {

                              // TODO: 06.01.2022
                              методЗаписиNewKeyOneSignal(listIDДляOneSignal, public_contentменеджер,НовыйКлючОтOneSingnal );


                              Log.d(this.getClass().getName(),"\n"
                                      + " bremy: " + new Date()+"\n+"
                                      + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                      " УРА УРА !!!! НовыйКлючОтOneSingnal " +НовыйКлючОтOneSingnal);
                              // TODO: 04.01.2022
                              return false;
                          }else {
                              Log.d(this.getClass().getName(),"\n"
                                      + " bremy: " + new Date()+"\n+"
                                      + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                      " НЕТ ПОКА НовыйКлючОтOneSingnal  " +НовыйКлючОтOneSingnal);
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
                    .toFlowable(BackpressureStrategy.BUFFER).subscribe();

        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }



    private String getNewKeyTokernFronOneSignal(@NonNull String КлючДляFirebaseNotification) {
        String НовыйКлючОтOneSingnal = null;
        try{
        // TODO: 05.01.2022
         НовыйКлючОтOneSingnal =     МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(КлючДляFirebaseNotification);
        Log.d(this.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+"  " +
                "   НовыйКлючОтOneSingnal[0] " +    НовыйКлючОтOneSingnal);

    } catch (Exception e ) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
        return НовыйКлючОтOneSingnal;
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

    private String МетодПолучениеКлючаОтСервераONESIGNALЕслиОЕстьКОнечноВНЕСКОЛЬКОПОпыток(@NonNull String PublicKeyOneSignal) {
        String  НовыйКлючОтOneSingnal = null;
        try{
            // todo OneSignal Initialization


            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            // todo OneSignal Initialization
            OneSignal.initWithContext(context);
            OneSignal.setAppId(PublicKeyOneSignal);
            OneSignal.disablePush(false);
            // TODO: 28.03.2024 создаени сообщения
            FirebaseMessagingService firebaseMessagingService =new MyFirebaseInstanceIDService();
            firebaseMessagingService.onNewToken("Сообщения от Firebase Cloud Google ");

            /// firebaseMessagingService.onMessageSent("sgsggsgsgsg");
            ///   "+79158111806" "sousautodor@gmail.com"
            // TODO: 28.03.2024 полылаем ключ ОБЩИЙ
            Map<String, String> params = new HashMap<String, String>();
            OneSignal.sendTag("Authorization", "Basic "+  PublicKeyOneSignal.trim()  );
            OneSignal.sendTag("Content-type", "application/json");
            OneSignal.sendTag("grp_msg", "android");
            OneSignal.sendTag("android_background_data", "true");
            OneSignal.sendTag("content_available", "true");

            //TODO srating......  oneSignal
            String ПушТОкен=    OneSignal.getDeviceState().getPushToken();
            НовыйКлючОтOneSingnal = OneSignal.getDeviceState().getUserId();

           /// firebaseMessagingService.onMessageSent("sgsggsgsgsg");

            // TODO: 15.12.2021
            Log.d(this.getClass().getName(),"\n"
                    + " bremy: " + new Date()+"\n+"
                    + "  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " НовыйКлючОтOneSingnal "
                    + НовыйКлючОтOneSingnal + "\n"+ПушТОкен+" BREMY  " +new Date().toLocaleString()+
                    "   OneSignal.getTriggerValueForKey(\"GT_PLAYER_ID\"); " );

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

