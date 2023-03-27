package com.dsy.dsu.Code_For_Services;
import android.app.IntentService;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;

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
    public Service_For_Public() {
        super("Service_For_Public");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца=new SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца();
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
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
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
        МетодГлавныйPublicPO(getApplicationContext(),intent);
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

            data = Parcel.obtain();
            reply = Parcel.obtain();
            data.writeString("fffff");
            return    reply.readBoolean();
        }
    }

    public Integer МетодГлавныйPublicPO(@NonNull Context context, @NonNull Intent intent) {
        Integer РезультатСинхрониазции=0;
        try{
            if( this.context==null){
                this.context=context;
            }
            switch (intent.getAction()) {
                case "ЗапускЗаполенеияИзПрошлыхМесяцев":
                    // TODO: 28.09.2022 Запуск Само Заполенеия Данных из Прошлого Месяца
                    sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца.    МетодЗапускЗаполенеияИзПрошлыхМесяцев(context, intent);
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
        return РезультатСинхрониазции;
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
        private void МетодЗапускЗаполенеияИзПрошлыхМесяцев(@NonNull Context context, @NonNull Intent intent) {
            try {
                SubClassCursorLoader cursorLoader=     new SubClassCursorLoader();
                Log.w(this.getClass().getName(), "   context  " + context);
                SQLiteDatabase sqLiteDatabaseДляЗаполенеяИзПрошлогоМесяца = new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу();
                Integer ПубличныйIDДляЗаполененияИзПрошлогоМесяца = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                Bundle bundleПолучаемДанных = intent.getExtras();
                Long UUIDПОискаПредыдущегоМЕсяцаТАбеля = bundleПолучаемДанных.getLong("UUIDРОДИТЕЛЬСКАЯУжеСозданогоТАбеля", 0l);
                Integer СФОУжеСозданогоТАбеля = bundleПолучаемДанных.getInt("СФО", 0);
                ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = bundleПолучаемДанных.getString("ДепартаментТабеляПослеПодбораBACK", "");
                //  Long ГенерироватьUUIDРОДИТЕЛЬСКАЯ = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
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
                final Integer[] РезультатВставкиИзПрошлогоМесяца = {0};
                // TODO: 15.02.2023 ГОД бежим по ЦИКЛЦУ
                GregorianCalendar gregorianCalendarИзПрошлыхМесяцев = new GregorianCalendar();
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.YEAR, ГодСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.MONTH,МесяцСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.DATE, 1);
                Log.d(this.getClass().getName(), " gregorianCalendarИзПрошлыхМесяцев" + gregorianCalendarИзПрошлыхМесяцев.getTime().toLocaleString());


                // TODO: 21.03.2023  Заполение из прошлого месяца
                Flowable.range(1,100)
                        .onBackpressureBuffer(true)
                        .repeatWhen(repeat->repeat.delay(500,TimeUnit.MILLISECONDS))
                        .takeWhile(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Throwable {
                                if (РезультатВставкиИзПрошлогоМесяца[0] >0) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " РезультатВставкиИзПрошлогоМесяца  " + РезультатВставкиИзПрошлогоМесяца[0]);
                                    return false;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " РезультатВставкиИзПрошлогоМесяца  " + РезультатВставкиИзПрошлогоМесяца[0]);
                                    return true;
                                }
                            }
                        })
                        .map(new Function<Integer, Object>() {
                            @Override
                            public Object apply(Integer integer) throws Throwable {
                                Log.d(this.getClass().getName(), " ДО  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)" +  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)
                                        + "   gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH) "+    gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH));
                                // TODO: 21.03.2023  уменяем месяц
                                gregorianCalendarИзПрошлыхМесяцев.add(Calendar.MONTH, -1);
                                return integer;
                            }
                        })
                        .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Throwable {
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
                                        String.valueOf( ФиналМесяцВставки),String.valueOf(СФОУжеСозданогоТАбеля),"Удаленная"});
                                bundle.putString("Таблица","viewtabel");
                                Cursor     Курсор_ВытаскиваемПоследнийМесяцТабеля=      (Cursor)    cursorLoader.  CursorLoaders(context, bundle);
                                Log.d(this.getClass().getName(), " Курсор_ВытаскиваемПоследнийМесяцТабеля" + Курсор_ВытаскиваемПоследнийМесяцТабеля);
                                if (Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() > 0 ) {
                                    Log.d(this.getClass().getName(), "Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() " + Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount());
                                    String ДатаОперации = new SubClassMONTHONLY(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                    // TODO: 16.02.2023 сама вставка
                                    РезультатВставкиИзПрошлогоМесяца[0] =
                                            МетодСозданиеТабеляИДАнныхПрошлогоМесяца(context, ГодСейчас, МесяцСейчас, Курсор_ВытаскиваемПоследнийМесяцТабеля,СФОУжеСозданогоТАбеля);
                                    Log.d(this.getClass().getName(), "  РезультатВставкиИзПрошлогоМесяца[0] " +  РезультатВставкиИзПрошлогоМесяца[0] );
                                }
                                Log.d(this.getClass().getName(), "  РезультатВставкиИзПрошлогоМесяца[0] " +  РезультатВставкиИзПрошлогоМесяца[0] );
                            }
                        })
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                                // TODO: 25.11.2022 выход
                                МетодОтображениеОперацииИзПрошлогоМЕсяца(context, "ВыходИзВставкиИзПрошлогоМесяца", РезультатВставкиИзПрошлогоМесяца[0]);
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
        }

        private Integer МетодСозданиеТабеляИДАнныхПрошлогоМесяца(@NonNull Context context,
                                                                 @NonNull  Integer ГодНазадДляЗаполнени,
                                                                 @NonNull   Integer МесяцИзПрошлогоМесяца,
                                                                 @NonNull    Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                                 @NonNull Integer СФОУжеСозданогоТАбеля) {
            ArrayList<Integer> integerArrayListВствавкаИзПрошлогоМесяц = null;
            try {
              integerArrayListВствавкаИзПрошлогоМесяц=new ArrayList<>();
                ReentrantLock reentrantLock = new ReentrantLock();
                Condition condition = reentrantLock.newCondition();
                Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToFirst();
                reentrantLock.lock();
                Long ParentUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                // TODO: 23.09.2022 ВСТАВЛЯЕМ ДАННЫЕ ВО ВТОРУЮ ТАБЛИЦЫ ДАТА_ТАБЕЛЬ
                Integer РезультатВставкиВверхнеюТаблицы = МетодВставкивТаблицуTaбель(context,
                        Курсор_ВытаскиваемПоследнийМесяцТабеля,
                        ГодНазадДляЗаполнени,
                        МесяцИзПрошлогоМесяца,
                        СФОУжеСозданогоТАбеля,
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
                        // TODO: 25.11.2022 выход
                        МетодОтображениеОперацииИзПрошлогоМЕсяца(context, "РезультатДобавенияСотрудникаИзПрошлогоМесяца",integerArrayListВствавкаИзПрошлогоМесяц.size());
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
                    }
                } while (Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToNext());//TODO конец цикла заполения даными
                Курсор_ВытаскиваемПоследнийМесяцТабеля.close();
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
                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                // TODO: 23.09.2022 сама вставка в таблиц ТАБЕЛЬ  #1
                Long ДляНовойЗаписиUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                contentValuesДляДатаТабель.put("uuid", ДляНовойЗаписиUUID);;
                contentValuesДляДатаТабель.put("uuid_tabel", ParentUUID);
                contentValuesДляДатаТабель.put("status_send", " ");
                contentValuesДляДатаТабель.put("status_carried_out", "False");
                contentValuesДляДатаТабель.putNull("_id");
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
                contentValuesДляТабель.putNull("_id");
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



        protected void МетодОтображениеОперацииИзПрошлогоМЕсяца(@NonNull Context context, String Статус, Integer Значение) {
            try {
                Intent intentЗапускаемИзСлужбыДляЛистТАбеля = new Intent();
                intentЗапускаемИзСлужбыДляЛистТАбеля.setAction("LocalBroadcastВставкаИзПрошлогоМесяцаТабель");
                Bundle bundleЗапускемBackДанные = new Bundle();
                bundleЗапускемBackДанные.putInt("Значения", Значение);
                bundleЗапускемBackДанные.putString("Статус", Статус);///"В процесс"
                bundleЗапускемBackДанные.putString("ДепартаментТабеляПослеПодбораBACK", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);///"В процесс"
                intentЗапускаемИзСлужбыДляЛистТАбеля.putExtras(bundleЗапускемBackДанные);
                Log.w(this.getClass().getName(), "   bundleЗапускемBackДанные  " + bundleЗапускемBackДанные);
                LocalBroadcastManager localBroadcastManagerИзСлужбыServiceForAllTabel = LocalBroadcastManager.getInstance(context);
                localBroadcastManagerИзСлужбыServiceForAllTabel.sendBroadcast(intentЗапускаемИзСлужбыДляЛистТАбеля);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
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
                Stream<String> streamУдалениеСтатусаУдаленный=Stream.of("data_tabels","tabel","get_materials_data");
                streamУдалениеСтатусаУдаленный.forEach(Таблица->{
                    Uri  uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                    ContentResolver resolver = context.getContentResolver();
                    Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"status_send=?",new String[]{"Удаленная"});
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