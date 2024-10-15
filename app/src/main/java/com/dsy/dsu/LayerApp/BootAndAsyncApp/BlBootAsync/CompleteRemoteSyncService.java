package com.dsy.dsu.LayerApp.BootAndAsyncApp.BlBootAsync;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus.MessageEvensBusAyns;
import com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.LayerApp.BootAndAsyncApp.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.LayerApp.DashboardApp.Model.endingasynsdashboard.GetEndingAsyn;
import com.dsy.dsu.LayerBunessLogic.BroadcastRecievers.Bl.RegisterBroadcastForWorkManager;
import com.dsy.dsu.LayerBunessLogic.Class_Connections_Server;
import com.dsy.dsu.LayerBunessLogic.Class_Find_Setting_User_Network;
import com.dsy.dsu.LayerBunessLogic.CreateFolderBinatySave.ClassCreateFolderBinatyMatrilal;
import com.dsy.dsu.LayerBunessLogic.CreateFolderBinatySave.ClassCreateFolderCommitPays1C;
import com.dsy.dsu.LayerBunessLogic.Errors.ClassCreateFileForError;
import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;
import com.dsy.dsu.LayerBunessLogic.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.LayerBunessLogic.Services.Service_For_Remote_Async_Binary;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Module
@InstallIn(SingletonComponent.class)

@SuppressLint("Range")
public class CompleteRemoteSyncService {



    private Service_For_Remote_Async_Binary.LocalBinderAsync localBinderAsync;//TODO нова\
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO нова



    private ServiceConnection connectionОбновлениеПО;
    private ServiceConnection connectionAsync;



    private SharedPreferences preferences;

    private String РежимЗапускаСинхронизации = new String();


      private  Context context;

    private String success_users;
    private String success_login;
    private String date_update;

    private  Boolean СтатусРаботыСервера;
    private    Integer getHiltPublicId;





    String Режим;

    @Inject
    RegisterBroadcastForWorkManager registerBroadcastForWorkManager;





    private String ИмяСлужбыСинхронизацииОбщая="WorkManager Synchronizasiy_Data";

    public  @Inject CompleteRemoteSyncService(@ApplicationContext Context context) {
        //TODO сомо имя json
        this.context=context;
    }

    public void startServiceAsybc(@NonNull Context context,
                                 @NonNull Integer getHiltPublicId,@NonNull String Режим ,
                                  @NonNull Uri uri,
                                  @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            this.Режим=Режим;
            // TODO: 14.08.2023 вызов кода ПОльзовательский
            preferences =context. getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            РежимЗапускаСинхронизации = preferences.getString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");

            // TODO: 22.01.2024
           this. getHiltPublicId=getHiltPublicId;



            // TODO: 14.08.2023 методЗапукска Синхрониазйиии
          МетодБиндингаRemoteAsync(uri,   getHiltPortJboss);


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public void startServiceUpdatePO(@NonNull Context context,
                                  @NonNull Integer getHiltPublicId,
                                     @NonNull String Режим ,
                                  @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            this.Режим=Режим;
            // TODO: 14.08.2023 вызов кода ПОльзовательский
            preferences =context. getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            РежимЗапускаСинхронизации = preferences.getString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");

            // TODO: 22.01.2024
            this. getHiltPublicId=getHiltPublicId;;

            // TODO: 14.08.2023 методЗапукска Синхрониазйиии
            МетодБиндингаОбновлениеПО(Uri.EMPTY,getHiltPortJboss);


            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private void WorkerUpdatePOAndAsync(@NonNull Uri uri, @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {

        try{


            Maybe.fromAction(()->{
                        // TODO: 15.10.2024 ro worker


                        // TODO: 22.01.2024  создание журнала ошщибок
                        методCreateJornalErrorApp();

                        // TODO: 14.08.2023 создаем папку для BinaryFile Save
                        new ClassCreateFolderBinatyMatrilal(context).МетодCreateFoldersBinaty();

                        // TODO: 14.08.2023 создаем папку для BinaryFile CommitPay1C Соласования
                        new ClassCreateFolderCommitPays1C(context).МетодCreateFoldersBinaty();

                        // TODO: 14.08.2023 создаем папку для Обновления ПО
                        new ClassCreateFolderUpdatePO (context).МетодCreateFoldersBinaty();



                        // TODO: 14.08.2023  Запускаем Код До Сиинхрониазщции
                        Integer     ФиналПолучаемРазницуМеждуДатами=   МетодОпределениеКогдаПоследнийРазЗаходилПользователь();

                        СтатусРаботыСервера=  МетодПингаКСереруЗапущенЛиСерерИлиНет(   getHiltPortJboss);

                        // TODO: 22.01.2024  если false значит сервер выключен

                        // TODO: 22.01.2024 сеть включена
                        metodDontNetwork();
                        // TODO: 22.01.2024 сеть выключена
                        metodSucceessNetwork();


                        if (СтатусРаботыСервера) {
                            // TODO: 01.04.2024  запускаем саму синхрониазцию через запуск Work Manamger
                            metodВыполняетсяГлавнаяWork(ФиналПолучаемРазницуМеждуДатами,  uri, getHiltPortJboss);//todo Main Code Sercice
                        }else {

                            getSendDOntWorkDashBornElseSucceessAyntifica(ФиналПолучаемРазницуМеждуДатами);


                        }


                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " РежимЗапускаСинхронизации " +РежимЗапускаСинхронизации+
                                "  ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);

            }).doOnSuccess(s->{
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


            }).doOnError(e->{
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());


            }).doOnComplete(()->{

                        // TODO: 23.01.2024
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            }).subscribeOn(Schedulers.single())
                            .subscribe();

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

    }



    private void getSendDOntWorkDashBornElseSucceessAyntifica(@NonNull   Integer     ФиналПолучаемРазницуМеждуДатами) {
        try {
            // TODO: 23.01.2024
            if (      date_update != null && success_users != null && success_login != null
                    && ФиналПолучаемРазницуМеждуДатами < 40 ) {

                // TODO: 01.04.2024
                new GetEndingAsyn().   metoEndingAsynsDashboard(context,localBinderОбновлениеПО);
            }else {

                // TODO: 28.04.2023 НЕт Анутифтикации Пароль
                // TODO: 28.04.2023 НЕт Анутифтикации Пароль
                startFirstApp();

                Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами
                        + " date_update " + date_update + " СтатусРаботыСервера " + СтатусРаботыСервера);

            }

            // TODO: 26.12.2022  конец основгого кода
            Log.d(context.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }


    }
























    private void методCreateJornalErrorApp() {
        try{

            // TODO: 07.10.2023  create file for ERROR
            ClassCreateFileForError classCreateFileForError=new ClassCreateFileForError();

            classCreateFileForError.metodCreateFileForError(context);


            Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    private void metodВыполняетсяГлавнаяWork(@NonNull Integer ФиналПолучаемРазницуМеждуДатами,
                                             @NonNull Uri uri,
                                             @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        try{
        if (      date_update != null && success_users != null && success_login != null
                && ФиналПолучаемРазницуМеждуДатами < 40 ) {

            // TODO: 22.01.2024  запускаеми службу обновление ПО
            new SuccessAsynsStartingUpdatrPO().startingAsyncForUpSoft( uri, getHiltPortJboss);


            Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " localBinderОбновлениеПО.isBinderAlive() " + localBinderОбновлениеПО.isBinderAlive()+
                    " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);

        }else {

            // TODO: 28.04.2023 НЕт Анутифтикации Пароль
            // TODO: 28.04.2023 НЕт Анутифтикации Пароль
            startFirstApp();

            Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами
                    + " date_update " + date_update + " СтатусРаботыСервера " + СтатусРаботыСервера);

        }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    private void startFirstApp() {
        class SendUserFirstApp extends      SendMainActivity{

            public SendUserFirstApp(Context context) {
                super(context);
            }

            @Override
            public void startSendBroadSesiver() {
                //super.startSendBroadSesiver();
                intentComunications.setAction("Broad_messageAsyncOrUpdateAsync");
                bundleComunications.putString("Статус",  "Первый запуск  !!!!");///"В процесс"
                bundleComunications.putString("Действие",   "Первый запуск  !!!!");///"В процесс"
                intentComunications.putExtras(bundleComunications);

                // TODO: 23.01.2024 SEND event bus
                EventBus.getDefault().post(new MessageEvensBusAyns(intentComunications));

            }
        }
        // TODO: 22.01.2024 когда режим офлайн
        new SendUserFirstApp(context).startSendBroadSesiver();
    }
    
    
    
    
    
    private void metodDontNetwork() {
        class SendUserСерверВыключен extends      SendMainActivity{

            public SendUserСерверВыключен(Context context) {
                super(context);
            }

            @Override
            public void startSendBroadSesiver() {
                // super.startSendBroadSesiver();
                if (СтатусРаботыСервера==false) {
                    intentComunications.setAction("Broad_messageAsyncOrUpdateAsync");
                    bundleComunications.putString("Статус",  "Сервер выкл.!!!");///"В процесс"
                    bundleComunications.putString("Действие",  "Сервер выкл.!!!");///"В процесс"
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusAyns(intentComunications));

                    // TODO: 22.01.2024 просто сеть рабоатет  переделаем програсс бару
                }
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        }
        // TODO: 22.01.2024 когда режим офлайн
        new SendUserСерверВыключен(context).startSendBroadSesiver();
    }





    private void metodSucceessNetwork() {
        class SendUserСерверВыключен extends      SendMainActivity{

            public SendUserСерверВыключен(Context context) {
                super(context);
            }

            @Override
            public void startSendBroadSesiver() {
                // super.startSendBroadSesiver();
          
                    if (СтатусРаботыСервера==true) {
                        intentComunications.setAction("Broad_messageAsyncPrograssBar");
                        bundleComunications.putString("Статус",  "PrograssBarVisible");///"В процесс"
                        bundleComunications.putString("Действие",  "PrograssBarVisible");///"В процесс"
                        intentComunications.putExtras(bundleComunications);


                        // TODO: 25.09.2024 call back AN Screnn User Boot Activity
                       EventBus.getDefault().post(new MessageEvensBusPrograssBar(intentComunications));;
                        //EventBus.getDefault().postSticky(new MessageEvensBusPrograssBar(intentComunications));
                      //  MessageEvensBusPrograssBar messageEvensBusPrograssBar  =     new MessageEvensBusPrograssBar(intentComunications);
                        // TODO: 25.09.2024  запускаем слушателя обратного отоброжкнк Prograsbar
                        //new PrograssBarManagerBloadcastReciever().setBroadcastManagerPrograssBar(context,intentComunications);


                    }
                Log.d(context.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        }
        // TODO: 22.01.2024 когда режим офлайн
        new SendUserСерверВыключен(context).startSendBroadSesiver();
    }






















    //TODO succeess
    class SuccessAsynsStartingUpdatrPO{
        void  startingAsyncForUpSoft(@NonNull Uri uri,   @NonNull LinkedHashMap<Integer,String> getHiltPortJboss ){
            try{

                // TODO: 22.01.2024 true запускаем Анализ По
                    Integer    СервернаяВерсия=        metodAsyncUpdatePO(getHiltPortJboss);


                // TODO: 24.09.2024   Локальная Версия Программернр Обеспечения табель
                PackageInfo    pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                String version = pInfo.versionName;//Version Name
                Integer ЛокальнаяВерсияПО = pInfo.versionCode;
                // TODO: 03.10.2023
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " СервернаяВерсия "+СервернаяВерсия);

                
                // TODO: 22.01.2024 true запускаем синхронизации
                    if (СервернаяВерсия<=ЛокальнаяВерсияПО) {
                        
                        
                        if (Режим.contains("IntentServiceBootAsync.com")) {

                            startingBindingAsyncWithJbossSerer(  uri);
                            
                            // TODO: 03.10.2023
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " Режим " +Режим);
                        }else {
                            // TODO: 24.09.2024 Запускаем Обновленеи ПО
                            metodAfterVersionEst(СервернаяВерсия);
                            // TODO: 03.10.2023
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                    " Режим " +Режим);
                            
                        }
                        // TODO: 03.10.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " Режим " +Режим);






                        // TODO: 22.01.2024  запускаем обновдение ПО
                    }else {
                        // TODO: 22.01.2024 запускаю обновление ПО
                        metodStartUpdatePoMessgeUser(СервернаяВерсия);

                        // TODO: 03.10.2023
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
                    }







                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " localBinderAsync ");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        Long startingBindingAsyncWithJbossSerer(@NonNull Uri uri){
         Long ФинальныйРезультатAsyncBackgroud = 0l;
            try{
                // TODO: 03.10.2023 Запуск Синхронизации



                boolean ВыбранныйРежимСети = new Class_Find_Setting_User_Network(context).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                        + ВыбранныйРежимСети);

                if (ВыбранныйРежимСети == true  ) {

                    ФинальныйРезультатAsyncBackgroud = localBinderAsync.getService().metodStartingSync(context);

                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                        " localBinderAsync "+" ФинальныйРезультатAsyncBackgroud[0] "+ФинальныйРезультатAsyncBackgroud);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return ФинальныйРезультатAsyncBackgroud;

        }

        private void metodStartUpdatePoMessgeUser(@NonNull Integer СервернаяВерсия) {
            class SendUserProssecingUpdatePo extends      SendMainActivity{

                public SendUserProssecingUpdatePo(Context context) {
                    super(context);
                }

                public void startSendBroadSesiver( ) {
                    //  super.startSendBroadSesiver();
                    intentComunications.setAction("Broad_messageAsyncOrUpdatePO");
                    bundleComunications.putString("Статус",  "Запускаем Обновление ПО !!!!");///"В процесс"
                    bundleComunications.putString("Действие",  "Заблакирован пользователь !!!!");///"В процесс"
                    bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusUpdatePO(intentComunications));

              /*      // TODO: 22.01.2024 останавливаем службу
                    stopServiceBoot();*/


                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserProssecingUpdatePo(context).startSendBroadSesiver();
        }
        private void metodAfterVersionEst(@NonNull Integer СервернаяВерсия) {
            class SendUserAfterVersionPO extends      SendMainActivity{

                public SendUserAfterVersionPO(Context context) {
                    super(context);
                }

                public void startSendBroadSesiver( ) {
                    try{

                    PackageInfo pInfo = context. getPackageManager().getPackageInfo(context. getPackageName(), 0);
                    String version = pInfo.versionName;//Version Name
                    Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code

                        if(СервернаяВерсия==0){
                            bundleComunications.putInt("СервернаяВерсия",  ЛокальнаяВерсияПО);///"В процесс"
                        }else {
                            bundleComunications.putInt("СервернаяВерсия",  СервернаяВерсия);///"В процесс"
                        }

                    intentComunications.setAction("Broad_messageAsyncOrUpdatePO");
                    bundleComunications.putString("Статус",   "У вас последная версия ПО !!!");///"В процесс"
                    bundleComunications.putString("Действие",   "У вас последная версия ПО !!!");///"В процесс"

                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusUpdatePO(intentComunications));

              /*      // TODO: 22.01.2024 останавливаем службу
                    stopServiceBoot();*/


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserAfterVersionPO(context).startSendBroadSesiver();
        }






        private void metodПользовательЗаблокирован() {
            class SendUserПользовательЗаблокирован extends      SendMainActivity{

                public SendUserПользовательЗаблокирован(Context context) {
                    super(context);
                }

                @Override
                public void startSendBroadSesiver() {
                  //  super.startSendBroadSesiver();
                    intentComunications.setAction("Broad_messageAsyncOrUpdateAsync");
                    bundleComunications.putString("Статус",  "Вы заблокирован   !!!!");///"В процесс"
                    bundleComunications.putString("Действие",  "Вы заблокирован   !!!!");///"В процесс"
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusAyns(intentComunications));


                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserПользовательЗаблокирован(context).startSendBroadSesiver();
        }


    }//TODO  end SuccessAsynsStartingUpdatrPO














    // TODO: 19.01.2024   /////// МЕТОД КОГДА ЗАХОДИЛ ПОСЛЬДНИЙ РАЗ ПОЛЬЗОВАТЛЬ


    public Integer МетодОпределениеКогдаПоследнийРазЗаходилПользователь() {
        Cursor Курсор_7ДнейЗаходаПользователя = null;
        Integer  ФиналПолучаемРазницуМеждуДатами=0;
        try {
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "successlogin" + "");
            ContentResolver contentResolver=context. getContentResolver();
            Курсор_7ДнейЗаходаПользователя =      contentResolver.query(uri,new String[]{},
                    new String(" SELECT *  FROM    successlogin   ORDER BY id  LIMIT   1  "),
                    new String[]{},null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?
            Log.d(this.getClass().getName(), "  Курсор_7ДнейЗаходаПользователя " +  Курсор_7ДнейЗаходаПользователя);


            if (Курсор_7ДнейЗаходаПользователя.getCount() > 0) {/////ПРОВЕРЯЕМ ЕСЛИ ПО ДАННОМУ ID UUID ЗАПОЛНЕ ЛИ ОН
                Курсор_7ДнейЗаходаПользователя.moveToFirst();
                success_users =
                        Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("success_users")).trim();
                success_login =
                        Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("success_login")).trim();
                date_update =
                        Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("date_update")).trim();

                Integer   ПолученныйПубличныйID= Курсор_7ДнейЗаходаПользователя.getInt(Курсор_7ДнейЗаходаПользователя.getColumnIndex("id"));

                Log.d(this.getClass().getName(), "  success_users  " + success_users + "  " +
                        "    success_login  " + success_login + " date_update " + date_update);

                // TODO: 13.08.2023 дата из табции
                Date ДатаSucceslogin =
                        new android.icu.text.SimpleDateFormat("yyyy-MM-dd",
                                new Locale("ru")).parse(date_update);//TODO "2023-08-01 19:00:59.781"

                Log.d(this.getClass().getName(), "  ДатаSucceslogin  " + ДатаSucceslogin);


                // TODO: 13.08.2023 Дата NOW !!!!!
                Date ДатаNOW = Calendar.getInstance().getTime();
                DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));//"yyyy-MM-dd'T'HH:mm:ss'Z'
                String ДатСегодняДатаNOW = dateFormat.format(ДатаNOW);
                ДатаNOW = dateFormat.parse(ДатСегодняДатаNOW);
                Log.d(this.getClass().getName(), "  ДатаNOW  " + ДатаNOW);


                ////TODO само сравнивание дат на 7 дней назад
                long РазницаМеждуДатамиNowИДатыИзБазы =
                        ДатаNOW.getTime()
                                - ДатаSucceslogin.getTime(); //локальное сравнение дата из базы андройда и дат сегодня
                ///////////
                ФиналПолучаемРазницуМеждуДатами = Integer.parseInt("" + (TimeUnit.DAYS.convert(РазницаМеждуДатамиNowИДатыИзБазы, TimeUnit.MILLISECONDS)));

                Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);

            }
            // TODO: 13.08.2023
            if (Курсор_7ДнейЗаходаПользователя != null) {
                Курсор_7ДнейЗаходаПользователя.close();///
            }
            Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    return  ФиналПолучаемРазницуМеждуДатами;

    }

    ///////todo ФИНАЛЬНЫЙ МЕТОД КТО ВХОДИЛ ДО 7 ДНЕЙ ИЛИ ПОСЫЛАЕМ НА АУНТИФИКАЦИЮ
    Integer metodAsyncUpdatePO(@NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        Integer СервернаяВерсия=0;
        try {
            СервернаяВерсия = localBinderОбновлениеПО.getService().МетодГлавныйОбновленияПОДоAsync(true,
                    context,getHiltPortJboss );

            Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
            Log.i(this.getClass().getName(), "R.id.item_async_updatepo  "
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString() +
                    "СервернаяВерсия " + СервернаяВерсия);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  СервернаяВерсия;
    }




    @SuppressLint("SuspiciousIndentation")
    public Boolean МетодПингаКСереруЗапущенЛиСерерИлиНет(    @NonNull LinkedHashMap<Integer,String> getHiltPortJboss  ) {
        try {
            // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
            СтатусРаботыСервера =
                    new Class_Connections_Server(context). МетодПингаСервераРаботаетИлиНет(context,getHiltPortJboss);

   Log.d(this.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  СтатусРаботыСервера " +СтатусРаботыСервера);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СтатусРаботыСервера;
    }



















































    @SuppressLint("Range")
    Boolean getBlockCurrentUser() {
        Boolean СтатусБлокировкиПользотеляТекущего = true;
        try {

            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "chat_users" + "");
            ContentResolver contentResolver=context. getContentResolver();
            Cursor  суксорЗаблокированыхПользотель =      contentResolver.query(uri,new String[]{},
                    new String(" SELECT locked  FROM chat_users  WHERE _id= ?  ORDER BY date_update DESC "),
                    new String[]{String.valueOf(getHiltPublicId)},null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?
            Log.d(this.getClass().getName(), "  суксорЗаблокированыхПользотель " +  суксорЗаблокированыхПользотель);


            Log.d(this.getClass().getName(), "суксорЗаблокированыхПользотель " + суксорЗаблокированыхПользотель);



            if (суксорЗаблокированыхПользотель.getCount() > 0) {
                суксорЗаблокированыхПользотель.moveToFirst();
                String СтатусБлокировки = суксорЗаблокированыхПользотель.getString(суксорЗаблокированыхПользотель.getColumnIndex("locked"));
                СтатусБлокировкиПользотеляТекущего = Boolean.parseBoolean(СтатусБлокировки.toString());

            }
            if (суксорЗаблокированыхПользотель != null) {
                суксорЗаблокированыхПользотель.close();
            }
            // TODO: 28.07.2022
            Log.d(this.getClass().getName(), " СтатусБлокировкиПользотеляТекущего " + СтатусБлокировкиПользотеляТекущего);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  СтатусБлокировкиПользотеляТекущего;
    }








    @SuppressLint("NewApi")
    public void МетодБиндингаRemoteAsync(@NonNull Uri uri, @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            // TODO: 28.04.2023  запускаем Гланвную Синхрониазцию

                //  Intent intentОбноразоваяСинхронизациия = new Intent(context, Service_For_Remote_Async.class);
                Intent intentAsync = new Intent(context, Service_For_Remote_Async_Binary.class);
                intentAsync.setAction("com.StartingAsyncMainBackgroud");
                connectionAsync = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 29.09.2023
                                localBinderAsync = (Service_For_Remote_Async_Binary.LocalBinderAsync) service;

                                МетодБиндингаОбновлениеПО( uri,getHiltPortJboss);


                                // TODO: 25.03.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        localBinderAsync = null;
                        Log.d(context.getClass().getName().toString(), "\n"
                                + "onServiceConnected  одноразовая  messengerActivity  ");
                    }

                    @Override
                    public void onBindingDied(ComponentName name) {
                        ServiceConnection.super.onBindingDied(name);
                    }

                    @Override
                    public void onNullBinding(ComponentName name) {
                        ServiceConnection.super.onNullBinding(name);
                    }
                };


           context. bindService(intentAsync ,connectionAsync ,Context.BIND_AUTO_CREATE);


            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }





    @SuppressLint("NewApi")
    public void МетодБиндингаОбновлениеПО(@NonNull Uri uri, @NonNull LinkedHashMap<Integer,String> getHiltPortJboss) {
        try {
            connectionОбновлениеПО = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        if (service.isBinderAlive()) {
                            // TODO: 28.07.2023  Update
                            localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;

                            // TODO: 23.01.2024 stating .... Main Code
                                WorkerUpdatePOAndAsync(  uri,getHiltPortJboss);

                            // TODO: 25.03.2023
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

                        }

                        Log.d(context.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n"
                                + "localBinderОбновлениеПО " + localBinderОбновлениеПО);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        localBinderОбновлениеПО = null;
                        Log.i(context.getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            Intent intentЗапускСлужбыОбновлениеПО = new Intent(context, ServiceUpdatePoОбновлениеПО.class);
            intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");

      context. bindService(intentЗапускСлужбыОбновлениеПО ,connectionОбновлениеПО,Context.BIND_AUTO_CREATE );

            // TODO: 28.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }

    }





    // TODO: 29.09.2023  метод зарцска синхронизации ВИЗУАЛЬНОЙ











































    // TODO: 19.01.2024 END CLASS
}