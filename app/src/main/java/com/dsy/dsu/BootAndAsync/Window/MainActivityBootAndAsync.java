package com.dsy.dsu.BootAndAsync.Window;import android.app.Activity;import android.content.pm.ActivityInfo;import android.graphics.Color;import android.graphics.drawable.Drawable;import android.os.Bundle;import android.util.Log;import android.view.WindowManager;import android.widget.ProgressBar;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.drawerlayout.widget.DrawerLayout;import androidx.lifecycle.LifecycleOwner;import com.dsy.dsu.BootAndAsync.BlBootAsync.Hilts.StartingEventAsyncOrUpdatePOUsers;import com.dsy.dsu.BootAndAsync.Componets.BL_innerMainActivityBootAndAsync;import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusAyns;import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusEndAync;import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusPrograssBar;import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusUpdatePO;import com.dsy.dsu.Errors.Class_Generation_Errors;import com.dsy.dsu.R;import com.google.android.material.navigation.NavigationView;import com.sous.appdocuments_data_domain.MyClassDomain;import com.sous.appdocuments_data_domains.MyDomains;import org.greenrobot.eventbus.EventBus;import org.greenrobot.eventbus.Subscribe;import org.greenrobot.eventbus.ThreadMode;import java.util.Date;import javax.inject.Inject;import javax.net.ssl.SSLSocketFactory;import dagger.hilt.android.AndroidEntryPoint;@AndroidEntryPointpublic class MainActivityBootAndAsync extends AppCompatActivity {    protected ProgressBar progressbarbootandasync;    protected Activity activity;    protected DrawerLayout drawerLayoutAsync;    protected NavigationView navigationViewAsyncApp;    public static final int ALL_PERSSION_CODE=1;    public static final int CAMERA_PERSSION_CODE=2;    protected boolean ФлагБылиИлиНЕбылПоворотЭкрана = false;    protected LifecycleOwner lifecycleOwner  ;    @Inject    public SSLSocketFactory getsslSocketFactory2;    @Inject    StartingEventAsyncOrUpdatePOUsers startingEventAsyncOrUpdatePOUsers;    protected   BL_innerMainActivityBootAndAsync blInnerMainActivityBootAndAsync;    @Override    protected void onCreate(Bundle savedInstanceState) {        try {            super.onCreate(savedInstanceState);            setContentView(R.layout.activity_main_bootandasync_prograssbar);            getSupportActionBar().hide(); ///скрывать тул бар            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);            progressbarbootandasync = (ProgressBar) findViewById(R.id.progressbarbootandasync); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА/            Drawable progressDrawable = progressbarbootandasync.getProgressDrawable().mutate();            progressDrawable.setColorFilter(Color.parseColor("#00574B"), android.graphics.PorterDuff.Mode.SRC_IN);            progressbarbootandasync.setProgressDrawable(progressDrawable);            drawerLayoutAsync = (DrawerLayout) findViewById(R.id.drawerLayout_async_prograsser); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА            drawerLayoutAsync.setBackgroundColor(Color.WHITE);         //TODO устанвливает цвета            drawerLayoutAsync.setDrawingCacheBackgroundColor(Color.RED);//todo            navigationViewAsyncApp    = (NavigationView) findViewById(R.id.navigator_asyncapp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА            activity = this;            lifecycleOwner=this;            registeEventBusFirst();            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() +                    " ФлагБылиИлиНЕбылПоворотЭкрана "                    + ФлагБылиИлиНЕбылПоворотЭкрана);        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    // TODO: 17.08.2023  запуск обновленея ПО и синхрониазции    @Override    protected void onDestroy() {        super.onDestroy();        try {            EventBus.getDefault().unregister(this);            blInnerMainActivityBootAndAsync.     stopServiceBootAndAsync();            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    @Override    protected void onRestart() {        super.onRestart();        try {            Log.d(getApplicationContext().getClass().getName(), "\n"                    + " время: " + new Date() + "\n+" +                    " Класс в процессе... " + this.getClass().getName() + "\n" +                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                    + "   starting... onRestart" + " starting... onRestart");        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    // TODO: 23.01.2024 EventBus for Async    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)    public void EventMessageEvensBusAyns(MessageEvensBusAyns messageEvensBusAyns){try{    blInnerMainActivityBootAndAsync  .getEventBusManagerAsync(messageEvensBusAyns);        Log.d(getApplicationContext().getClass().getName(), "\n"                + " время: " + new Date() + "\n+" +                " Класс в процессе... " + this.getClass().getName() + "\n" +                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                + "   starting... onRestart" + " starting... onRestart");    } catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                + Thread.currentThread().getStackTrace()[2].getLineNumber());        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                Thread.currentThread().getStackTrace()[2].getLineNumber());    }    }    // TODO: 23.01.2024 EventBus for Prograssbar    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)    public void EventMessageEvensBusPrograssBar(MessageEvensBusPrograssBar messageEvensBusPrograssBar){try{    blInnerMainActivityBootAndAsync  .getEventBusPrograssBar(messageEvensBusPrograssBar);        Log.d(getApplicationContext().getClass().getName(), "\n"                + " время: " + new Date() + "\n+" +                " Класс в процессе... " + this.getClass().getName() + "\n" +                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                + "   starting... onRestart" + " starting... onRestart");    } catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                + Thread.currentThread().getStackTrace()[2].getLineNumber());        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                Thread.currentThread().getStackTrace()[2].getLineNumber());    }    }    // TODO: 23.01.2024 EventBus for Update PO    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){        try{            blInnerMainActivityBootAndAsync   .getEventBusUpdatePo(messageEvensBusUpdatePO);        Log.d(getApplicationContext().getClass().getName(), "\n"                + " время: " + new Date() + "\n+" +                " Класс в процессе... " + this.getClass().getName() + "\n" +                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                + "   starting... onRestart" + " starting... onRestart");    } catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                + Thread.currentThread().getStackTrace()[2].getLineNumber());        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                Thread.currentThread().getStackTrace()[2].getLineNumber());    }    }    // TODO: 23.01.2024 EventBus END ASYNC    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)    public void EventMessageEvensBusEndAsync(MessageEvensBusEndAync messageEvensBusEndAync){        try{            blInnerMainActivityBootAndAsync   .getEventBusEndingAsync(messageEvensBusEndAync);            Log.d(getApplicationContext().getClass().getName(), "\n"                    + " время: " + new Date() + "\n+" +                    " Класс в процессе... " + this.getClass().getName() + "\n" +                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                    + "   starting... onRestart" + " starting... onRestart");        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    @Override    protected void onStart() {        super.onStart();//////TODO ловим поворот экрана  ДЛЯ СИНХРОНИЗАЦИИ        try {            blInnerMainActivityBootAndAsync=new BL_innerMainActivityBootAndAsync(getsslSocketFactory2,                    progressbarbootandasync, activity, drawerLayoutAsync,                    navigationViewAsyncApp,getApplicationContext(),  lifecycleOwner);            // TODO: 19.01.2024  запускаем бизнес логики автивити boot and async            blInnerMainActivityBootAndAsync.  startBl_inner();            blInnerMainActivityBootAndAsync .  МетодБоковаяПанельОткрытьЗАкрыть();            blInnerMainActivityBootAndAsync .  МетодДляСлушательБоковойПанелиAsyncApp();            // TODO: 22.03.2024  запускаем службу обвноеление и сихрониазции данных            startingEventAsyncOrUpdatePOUsers .startServiceBootAndAsync();            Log.d(getApplicationContext().getClass().getName(), "\n"                    + " время: " + new Date() + "\n+" +                    " Класс в процессе... " + this.getClass().getName() + "\n" +                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    private void registeEventBusFirst() {        EventBus.getDefault().register(this);    }    @Override     // TODO: 26.10.2022 сохраняет данные    protected void onSaveInstanceState(Bundle outState) {        try {            Log.i(this.getClass().getName(), "onSaveInstanceState ");            outState.putInt("progressBar4Cycle", progressbarbootandasync.getProgress());            outState.putInt("progressBar4CycleMAx", progressbarbootandasync.getMax());            // TODO: 23.08.203 статуст поворта экрана            outState.putBoolean("asyncTaskLoaderAsync.isStarted()", ФлагБылиИлиНЕбылПоворотЭкрана);            Log.d(getApplicationContext().getClass().getName(), "\n"                    + " время: " + new Date() + "\n+" +                    " Класс в процессе... " + this.getClass().getName() + "\n" +                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    "  ФлагБылиИлиНЕбылПоворотЭкрана " + ФлагБылиИлиНЕбылПоворотЭкрана);            super.onSaveInstanceState(outState);        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    // TODO: 26.10.2022 воставливает данные    protected void onRestoreInstanceState(Bundle savedInstanceState) {        super.onRestoreInstanceState(savedInstanceState);        try {            // TODO: 25.10.2022 востанавливаем данных после поворота экрана           МетодВостановлениеЭкранаПослеПоворота(savedInstanceState);            Log.d(getApplicationContext().getClass().getName(), "\n"                    + " время: " + new Date() + "\n+" +                    " Класс в процессе... " + this.getClass().getName() + "\n" +                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " onRestoreInstanceState");        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    private void МетодВостановлениеЭкранаПослеПоворота(@NonNull Bundle savedInstanceState) {        try {            if (progressbarbootandasync.isAttachedToWindow()) {                progressbarbootandasync.setProgress(savedInstanceState.getInt("progressBar4Cycle", progressbarbootandasync.getProgress()));                progressbarbootandasync.setMax(savedInstanceState.getInt("progressBar4CycleMAx", progressbarbootandasync.getMax()));                // TODO: 23.08.2023  Востаноавливем статус поврот экрана                ФлагБылиИлиНЕбылПоворотЭкрана = savedInstanceState.getBoolean("asyncTaskLoaderAsync.isStarted()");                progressbarbootandasync.requestLayout();                progressbarbootandasync.refreshDrawableState();                drawerLayoutAsync.refreshDrawableState();                Log.d(getApplicationContext().getClass().getName(), "\n"                        + " время: " + new Date() + "\n+" +                        " Класс в процессе... " + this.getClass().getName() + "\n" +                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()                        + "   savedInstanceState " + savedInstanceState + " ФлагБылиИлиНЕбылПоворотЭкрана " + ФлагБылиИлиНЕбылПоворотЭкрана);            }        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"                    + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                    Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    @Override    public void onBackPressed() {        super.onBackPressed();    }    // TODO: 22.01.2024 END}