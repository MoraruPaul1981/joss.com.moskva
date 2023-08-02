package com.dsy.dsu.Code_For_Services;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.AllDatabases.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_UUID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Weekend_For_Tabels;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassMONTHONLY;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassMONTHONLY_ТолькоАнализ;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassYEARONLY;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassYearHONLY_ТолькоАнализ;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;
import com.dsy.dsu.Code_ForTABEL.MainActivity_List_Peoples;
import com.dsy.dsu.Code_ForTABEL.MainActivity_List_Tabels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Service_For_Public extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    private String ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;
    public LocalBinderОбщий localBinderОбщий = new LocalBinderОбщий();
    protected         SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца;
    private Context context;
    private  Intent intentОтActivityListPeoples;
    public Service_For_Public() {
        super("Service_For_Public");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try{
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца=new SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца();

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        localBinderОбщий.setCallingWorkSourceUid(new Random().nextInt());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return localBinderОбщий;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=getApplicationContext();
        МетодГлавныйPublicPO(getApplicationContext(),intent,null);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }

    public class LocalBinderОбщий extends Binder {
        public Service_For_Public getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return Service_For_Public.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
          try{
            data = Parcel.obtain();
            reply = Parcel.obtain();
            data.writeString("fffff");
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return    reply.readBoolean();
        }
    }
    @BinderThread
    public Integer МетодГлавныйPublicPO(@NonNull Context context, @NonNull Intent intent,@NonNull ProgressDialog progressDialog) {
        Integer РезультатОперации=0;
        try{
            if( this.context==null){
                this.context=context;
            }
            this.intentОтActivityListPeoples=intent;
            switch (intent.getAction()) {
                case "ЗапускЗаполенеияИзПрошлыхМесяцев":
                    // TODO: 28.09.2022 Запуск Само Заполенеия Данных из Прошлого Месяца
                    РезультатОперации=         sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца.
                            МетодЗапускЗаполенеияИзПрошлыхМесяцев(context, intent,progressDialog);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 25.09.2022 удаление статуса удаленных строк
                case "ЗапускУдалениеСтатусаУдаленияСтрок":
                  new SubClassFromУдалениеСтатусУдаленный(context).МетодУдаленияСтатусаУдаленных(intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                default:
                    break;
            }
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return РезультатОперации;
    }
// TODO: 15.02.2023
public Cursor МетодПолучениеДанныхЧерезCursorLoader(@NonNull Context context, @NonNull Intent intent) {
    Cursor cursor=null;
    try{
        if( this.context==null){
            this.context=context;
        }
        Log.w(this.getClass().getName(), "   МетодПолучениеДанныхЧерезCursorLoader  " + context);
        if (intent.getAction().contentEquals("ДляУдаление")) {
            cursor=      (Cursor)     new SubClassCursorLoader(). CursorLoaders(context, intent.getExtras());
        }
        Log.w(this.getClass().getName(), "   cursor  " + cursor);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    return cursor;
}

    // TODO: 28.09.2022  класс заполнения из прошлых месяцев

    class SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца {
        Integer ГодСейчас = 0;
        Integer МесяцСейчас=0;
        ProgressDialog progressDialog;
        private Integer МетодЗапускЗаполенеияИзПрошлыхМесяцев(@NonNull Context context, @NonNull Intent intent,@NonNull ProgressDialog progressDialog) {
         ArrayList<Integer> ArrayListВставкиИзПрошлогоМесяца = new ArrayList();
            final Cursor[] Курсор_ВытаскиваемПоследнийМесяцТабеля = {null};
            try {
                this.progressDialog=progressDialog;
                // TODO: 21.04.2023  
                SubClassCursorLoader cursorLoader=     new SubClassCursorLoader();
                Log.w(this.getClass().getName(), "   context  " + context);
                SQLiteDatabase sqLiteDatabaseДляЗаполенеяИзПрошлогоМесяца = new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу();
                Integer ПубличныйIDДляЗаполененияИзПрошлогоМесяца = new Class_Generations_PUBLIC_CURRENT_ID()
                        .ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                 Bundle bundleПолучаемДанных =(Bundle)  intent.getExtras();
                 Long   MainParentUUID=    bundleПолучаемДанных.getLong("MainParentUUID", 0);
                 Integer    Position=    bundleПолучаемДанных.getInt("Position", 0);
                Integer  ГодТабелей=  bundleПолучаемДанных.getInt("ГодТабелей", 0);
                Integer   МЕсяцТабелей=  bundleПолучаемДанных.getInt("МЕсяцТабелей",0);
                Integer   DigitalNameCFO=   bundleПолучаемДанных.getInt("DigitalNameCFO", 0);
                 String   FullNameCFO=  bundleПолучаемДанных.getString("FullNameCFO", "");
                String   ИмесяцвИГодСразу= bundleПолучаемДанных.getString("ИмесяцвИГодСразу", "");
                Long     CurrenrsСhildUUID= bundleПолучаемДанных.getLong("CurrenrsСhildUUID", 0l);

                // TODO: 21.09.2022
                String ПолученяМесяцПростоАнализа = new SubClassMONTHONLY_ТолькоАнализ(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                 МесяцСейчас = Optional.ofNullable(ПолученяМесяцПростоАнализа).map(Integer::new).orElse(0);
                Log.d(this.getClass().getName(), "ПолученяМесяцПростоАнализа " + ПолученяМесяцПростоАнализа);
                switch (МесяцСейчас) {
                    case 12:
                        String   ПолученаяДатаТолькоГод12 = new SubClassYEARONLY(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                        ГодСейчас = Optional.ofNullable(ПолученаяДатаТолькоГод12).map(Integer::new).orElse(0);
                        break;
                    default:
                        String    ПолученаяДатаТолькоГод = new SubClassYearHONLY_ТолькоАнализ(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                        ГодСейчас = Optional.ofNullable(ПолученаяДатаТолькоГод).map(Integer::new).orElse(0);
                        break;
                }
                // TODO: 22.09.2022
                Log.d(this.getClass().getName(), "ГодСейчас " + ГодСейчас + " МесяцСейчас " + МесяцСейчас);
                //TODO ВЫЧИСЛЯЕМ ДАННЫЕ КОТОРЫЕ НА ВСТАВИТЬ
                // TODO: 15.02.2023 ГОД бежим по ЦИКЛЦУ
                GregorianCalendar gregorianCalendarИзПрошлыхМесяцев = new GregorianCalendar();
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.YEAR, ГодСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.MONTH,МесяцСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.DATE, 1);
                Log.d(this.getClass().getName(), " gregorianCalendarИзПрошлыхМесяцев" + gregorianCalendarИзПрошлыхМесяцев.getTime().toLocaleString());

                final Integer[] Количесво = {1};
                // TODO: 21.03.2023  Заполение из прошлого месяца
                Flowable.range(1,24)
                        .onBackpressureBuffer(24)
                        .subscribeOn(Schedulers.single())
                        .repeatWhen(repeat->repeat.delay(500,TimeUnit.MILLISECONDS))
                        .takeWhile(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Throwable {
                                if (ArrayListВставкиИзПрошлогоМесяца.size() >0 || Количесво[0] >24) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "ArrayListВставкиИзПрошлогоМесяца.size()  " + ArrayListВставкиИзПрошлогоМесяца.size()  +
                                            "  Количесво[0] " +Количесво[0]);
                                    return false;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "ArrayListВставкиИзПрошлогоМесяца.size() " + ArrayListВставкиИзПрошлогоМесяца.size());
                                    return true;
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Integer, Object>() {
                            @Override
                            public Object apply(Integer integer) throws Throwable {
                                Log.d(this.getClass().getName(), " ДО  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)" +  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)
                                        + "   gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH) "+    gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH));
                                // TODO: 21.03.2023  уменяем месяц
                                gregorianCalendarИзПрошлыхМесяцев.add(Calendar.MONTH, -1);

                                Integer ФиналМесяцВставки=       gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH);
                                Integer ФиналГодВставки=       gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR);
                                if(ФиналМесяцВставки==0){
                                    ФиналМесяцВставки=12;
                                    ФиналГодВставки--;
                                }
                                Log.d(this.getClass().getName(), " ПОСЛЕ ФиналГодВставки" +  ФиналГодВставки + "  ФиналМесяцВставки "+  ФиналМесяцВставки);
                                Bundle bundle=new Bundle();
                                bundle.putString("СамЗапрос","  SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?");
                                bundle.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ФиналГодВставки),
                                        String.valueOf( ФиналМесяцВставки),String.valueOf(DigitalNameCFO),"Удаленная"});
                                bundle.putString("Таблица","viewtabel");
                                Курсор_ВытаскиваемПоследнийМесяцТабеля[0] =      (Cursor)    cursorLoader.  CursorLoaders(context, bundle);
                                Log.d(this.getClass().getName(), " Курсор_ВытаскиваемПоследнийМесяцТабеля" + Курсор_ВытаскиваемПоследнийМесяцТабеля[0]);
                                if (Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount() > 0 ) {
                                    Log.d(this.getClass().getName(), "Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() " + Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount());
                                }
                                Log.d(this.getClass().getName(), "  ArrayListВставкиИзПрошлогоМесяца " +  ArrayListВставкиИзПрошлогоМесяца);

                                return integer;
                            }
                        })

                        .subscribeOn(Schedulers.single())
                        .doOnNext(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Throwable {
                                   try{
                                       try{
                                           Количесво[0]++;
                                           if (Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount() > 0 ) {
                                               Log.d(this.getClass().getName(), "Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() " + Курсор_ВытаскиваемПоследнийМесяцТабеля[0].getCount());
                                               String ДатаОперации = new SubClassMONTHONLY(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                               // TODO: 16.02.2023 сама вставка
                                             Integer РезультатВставкиИзПрошлогоМесяца =
                                                       МетодСозданиеТабеляИДАнныхПрошлогоМесяца(context, ГодСейчас, МесяцСейчас, Курсор_ВытаскиваемПоследнийМесяцТабеля[0],
                                                               DigitalNameCFO,  progressDialog);
                                               // TODO: 05.05.2023
                                               ArrayListВставкиИзПрошлогоМесяца.add(РезультатВставкиИзПрошлогоМесяца);
                                               Log.d(this.getClass().getName(), "  ArrayListВставкиИзПрошлогоМесяца" +  ArrayListВставкиИзПрошлогоМесяца);

                                               Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + "    РезультатВставкиИзПрошлогоМесяца[0] "
                                                       +  ArrayListВставкиИзПрошлогоМесяца);
                                               // TODO: 21.04.2023  stop service

                                           }
                                           Log.d(this.getClass().getName(), "  РезультатВставкиИзПрошлогоМесяца[0] " +  ArrayListВставкиИзПрошлогоМесяца );
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                   " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                           new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                   Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                           Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                                       }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                                }

                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                                return false;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                progressDialog.dismiss();
                                progressDialog.cancel();
                                Курсор_ВытаскиваемПоследнийМесяцТабеля[0].close();
                                    if ( ArrayListВставкиИзПрошлогоМесяца.size()==0) {
                                        Toast.makeText(context, "Нет создание!!!"
                                                +"\n"+" (Табель может быть уже создан)  ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        //Toast.makeText(context, "Успешное создание!!! ", Toast.LENGTH_SHORT).show();
                                        // TODO: 21.04.202 3 переъодим на активтив

                                        // TODO: 21.04.2023 после операции возврящемся на Activity List Peoples
                                        МетодПереходMainActivity_List_Peoples(intentОтActivityListPeoples);

                                        Toast.makeText(context, "Успешно"
                                                +"\n"+" Создан из прош.месяца ", Toast.LENGTH_SHORT).show();
                                    }




                                // TODO: 21.04.2023  stop service
                                stopSelf();
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                                          " ArrayListВставкиИзПрошлогоМесяца " +ArrayListВставкиИзПрошлогоМесяца.size());
                            }
                        })

                        .subscribe();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  ArrayListВставкиИзПрошлогоМесяца.size();
        }


        /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
        private void МетодПереходMainActivity_List_Peoples( @NonNull Intent intentОтActivityListPeoples) {
            try{
                Intent    ИнтентпереходВMainActivityList_Peoples=new Intent(getApplicationContext(), MainActivity_List_Tabels.class);
                ИнтентпереходВMainActivityList_Peoples      .putExtras(intentОтActivityListPeoples.getExtras());
                ИнтентпереходВMainActivityList_Peoples.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(ИнтентпереходВMainActivityList_Peoples);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " intentОтActivityListPeoples " +intentОтActivityListPeoples.getExtras());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





        private Integer МетодСозданиеТабеляИДАнныхПрошлогоМесяца(@NonNull Context context,
                                                                 @NonNull  Integer ГодНазадДляЗаполнени,
                                                                 @NonNull   Integer МесяцИзПрошлогоМесяца,
                                                                 @NonNull    Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                                 @NonNull Integer DigitalNameCFO,
                                                                 @NonNull ProgressDialog progressDialog) {
            ArrayList<Integer> integerArrayListВствавкаИзПрошлогоМесяц = null;
            try {
              integerArrayListВствавкаИзПрошлогоМесяц=new ArrayList<>();
                ReentrantLock reentrantLock = new ReentrantLock();
                Condition condition = reentrantLock.newCondition();
                Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToLast();
                progressDialog.setMax(Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount());
                reentrantLock.lock();
                Long ParentUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID();
                // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ
                Integer РезультатВставкиВверхнеюТаблицы = МетодВставкивТаблицуTaбель(context,
                        Курсор_ВытаскиваемПоследнийМесяцТабеля,
                        ГодНазадДляЗаполнени,
                        МесяцИзПрошлогоМесяца,
                        DigitalNameCFO,
                        ParentUUID);
                Log.d(this.getClass().getName(), " РезультатВставкиВверхнеюТаблицы" + РезультатВставкиВверхнеюТаблицы);
                condition.await(200, TimeUnit.MILLISECONDS);
                condition.signal();
                reentrantLock.unlock();
                do {
                    try {
                        reentrantLock.lock();
                        if (РезультатВставкиВверхнеюТаблицы > 0) {
                            integerArrayListВствавкаИзПрошлогоМесяц.add(РезультатВставкиВверхнеюТаблицы);
                            // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ
                            Integer РезультатВставкиВНИжнуюТаюблицу = МетодВставкивТаблицуДата_Табель(context,
                                    Курсор_ВытаскиваемПоследнийМесяцТабеля, ParentUUID, ГодНазадДляЗаполнени, МесяцИзПрошлогоМесяца);
                            Log.d(this.getClass().getName(), " Вторая Таблиуа Из Прошлого МЕссяца РезультатВставкиВНИжнуюТаюблицу"
                                    + РезультатВставкиВНИжнуюТаюблицу);
                            if (РезультатВставкиВНИжнуюТаюблицу > 0) {
                                integerArrayListВствавкаИзПрошлогоМесяц.add(РезультатВставкиВНИжнуюТаюблицу);

                            }
                            condition.await(200, TimeUnit.MILLISECONDS);
                            condition.signal();
                        }
                        Log.d(this.getClass().getName(), " Вторая Таблиуа Из Прошлого МЕссяца РезультатВставкиВНИжнуюТаюблицу");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                    } finally {
                        reentrantLock.unlock();
                        // TODO: 18.04.2023 прогресс бар
                     Integer УспешнаяОпеарция=   integerArrayListВствавкаИзПрошлогоМесяц.size();
                        // TODO: 18.04.2023
                        progressDialog.setProgress(УспешнаяОпеарция);
                        progressDialog.setMessage(УспешнаяОпеарция.toString());
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " integerArrayListВствавкаИзПрошлогоМесяц.size() "+integerArrayListВствавкаИзПрошлогоМесяц.size());
                    }
                } while (Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToPrevious());//TODO конец цикла заполения даными
                Курсор_ВытаскиваемПоследнийМесяцТабеля.close();
                progressDialog.dismiss();
                progressDialog.cancel();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  integerArrayListВствавкаИзПрошлогоМесяц.size();
        }


        private Integer МетодВставкивТаблицуДата_Табель(@NonNull Context context,
                                                        @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля
                ,@NonNull Long ParentUUID,
                                                        @NonNull Integer ПолученаяДатаТолькоГод,
                                                        @NonNull Integer  МесяцИзПрошлогоМесяца) {
            String ответОперцииВставки = null;
            try {
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ПолученаяДатаТолькоГод  " + ПолученаяДатаТолькоГод + "  МесяцИзПрошлогоМесяца " + МесяцИзПрошлогоМесяца + " Курсор_ВытаскиваемПоследнийМесяцТабеля " +Курсор_ВытаскиваемПоследнийМесяцТабеля);
                Class_GRUD_SQL_Operations       class_grud_sql_operationЗаполнениеИзПрошлогоМесяца = new Class_GRUD_SQL_Operations(getApplicationContext());
                String НазваниеОбрабоатываемойТаблицы = "data_tabels";
                ContentValues contentValuesДляДатаТабель = new ContentValues();
                int ИндексСтолбикаДляЗаполненияФИО = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio");
                contentValuesДляДатаТабель.put("fio", Курсор_ВытаскиваемПоследнийМесяцТабеля.getLong(ИндексСтолбикаДляЗаполненияФИО));
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляДатаТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));


              Integer Профессия     = Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("dt_prof"));

                if (Профессия==0) {
                    Профессия =  Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio_prof"));
                }

                contentValuesДляДатаТабель.put("prof", Профессия);


                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                // TODO: 23.09.2022 сама вставка в таблиц ТАБЕЛЬ  #1
                Long ДляНовойЗаписиUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID();
                contentValuesДляДатаТабель.put("uuid", ДляНовойЗаписиUUID);;
                contentValuesДляДатаТабель.put("uuid_tabel", ParentUUID);
                contentValuesДляДатаТабель.put("status_send", " ");
                contentValuesДляДатаТабель.put("status_carried_out", "False");
               // contentValuesДляДатаТабель.putNull("_id");
                // TODO: 22.09.2022 дополнительные параменты ДатаТабель
                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    НазваниеОбрабоатываемойТаблицы,getApplicationContext(),new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                contentValuesДляДатаТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                ContentResolver resolver = context.getContentResolver();
                Uri insertData = resolver.insert(uri, contentValuesДляДатаТабель);
                if (insertData!=null) {
                    ответОперцииВставки = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                    if (     Integer.parseInt(ответОперцииВставки)>0) {
                        Integer РезультатВставкаВыходныхДНей=
                                new Class_Generation_Weekend_For_Tabels(getApplicationContext())
                                        .МетодТретийАвтоматическаяВставкаВыходныхДней(ДляНовойЗаписиUUID,ПолученаяДатаТолькоГод,МесяцИзПрошлогоМесяца );
                        Log.d(this.getClass().getName(), "   РезультатВставкаВыходныхДНей  "+  РезультатВставкаВыходныхДНей);
                    }
                }else {
                    ответОперцииВставки="0";
                }
                Log.d(this.getClass().getName(), "insertData   " + insertData + "  ответОперцииВставки " + ответОперцииВставки);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Integer.parseInt(ответОперцииВставки);
        }

        private Integer МетодВставкивТаблицуTaбель(@NonNull Context context,
                                                        @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                        @NonNull Integer ГодНазадДляЗаполнени,
                                                        @NonNull Integer  МесяцИзПрошлогоМесяца,
                                                   @NonNull Integer   СФОУжеСозданогоТАбеля,
                                                   @NonNull Long ParentUUID) {
            String ответОперцииВставкиТабель = null;
            try {

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ГодНазадДляЗаполнени  " + ГодНазадДляЗаполнени + "  МесяцИзПрошлогоМесяца "
                        + МесяцИзПрошлогоМесяца + " СФОУжеСозданогоТАбеля " +СФОУжеСозданогоТАбеля);
                Class_GRUD_SQL_Operations       class_grud_sql_operationЗаполнениеИзПрошлогоМесяца = new Class_GRUD_SQL_Operations(getApplicationContext());
                String НазваниеОбрабоатываемойТаблицы = "tabel";
                ContentValues contentValuesДляТабель = new ContentValues();
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));
                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                contentValuesДляТабель.put("uuid", ParentUUID);
                contentValuesДляТабель.put("cfo",СФОУжеСозданогоТАбеля);
                contentValuesДляТабель.put("month_tabels", МесяцИзПрошлогоМесяца);
                contentValuesДляТабель.put("year_tabels",ГодНазадДляЗаполнени);
                contentValuesДляТабель.put("status_send", " ");
               // contentValuesДляТабель.putNull("_id");
                // TODO: 22.09.2022 дополнительные параменты ДатаТабель
                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    НазваниеОбрабоатываемойТаблицы,getApplicationContext(),
                                new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                contentValuesДляТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                ContentResolver resolver = context.getContentResolver();
                Uri insertData = resolver.insert(uri, contentValuesДляТабель);
                ответОперцииВставкиТабель = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();
                Log.d(this.getClass().getName(), "ответОперцииВставкиТабель   " + ответОперцииВставкиТабель );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Integer.parseInt(ответОперцииВставкиТабель);
        }


    }

    // TODO: 25.09.2022 класс удаление статуса удаленных записей
    class SubClassFromУдалениеСтатусУдаленный {
        Context context;
        public SubClassFromУдалениеСтатусУдаленный(Context context) {
            this.context=context;
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        }

        // TODO: 25.09.2022 запуск метода
        public void МетодУдаленияСтатусаУдаленных(@NonNull Intent intent){
            try{
                Stream<String> streamУдалениеСтатусаУдаленный=Stream.of("data_tabels","tabel","get_materials_data" );
                streamУдалениеСтатусаУдаленный.forEach(Таблица->{
                    Uri  uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                    ContentResolver resolver = context.getContentResolver();
                    Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"status_send=?  ",new String[]{"1СУдалено" });
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                             " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
                });

                Stream<String> streamУдалениеСтатусаУдаленныйВтрой=Stream.of( "order_tc");
                streamУдалениеСтатусаУдаленныйВтрой.forEach(Таблица->{
                    Uri  uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                    ContentResolver resolver = context.getContentResolver();
                    Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"  status=?",new String[]{ "6"});
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                            " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
                });


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }
        // TODO: 20.03.2023  метод смены статуса при удаление на СЕРВРЕРЕ





    }
}