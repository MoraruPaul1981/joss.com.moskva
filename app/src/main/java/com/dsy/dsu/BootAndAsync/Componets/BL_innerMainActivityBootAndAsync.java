package com.dsy.dsu.BootAndAsync.Componets;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.BootAndAsync.BlBootAsync.Hilts.StartingEventAsyncOrUpdatePOUsers;
import com.dsy.dsu.BootAndAsync.DowloadUpdatePO.DownLoadPO;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusAyns;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.BootAndAsync.Service.IntentServiceBoot;
import com.dsy.dsu.BootAndAsync.Window.MainActivityBootAndAsync;
import com.dsy.dsu.BusinessLogicAll.Permissions.ClassPermissions;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.Errors.MainActivity_Errors;
import com.dsy.dsu.Passwords.MainActivityPasswords;
import com.dsy.dsu.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

// TODO: 19.01.2024  внутренаяя бизнес логика Активтив BootAndAsync
public class BL_innerMainActivityBootAndAsync extends MainActivityBootAndAsync {

    private LifecycleOwner lifecycleOwner  ;
    private SSLSocketFactory getsslSocketFactory2;
    private ProgressBar progressbarbootandasync;
    private Activity activity;
    private DrawerLayout drawerLayoutAsync;
    private NavigationView navigationViewAsyncApp;
    
    private  Context context;

    
    StartingEventAsyncOrUpdatePOUsers startingEventAsyncOrUpdatePOUsers;

    public BL_innerMainActivityBootAndAsync(@NonNull SSLSocketFactory getsslSocketFactory2,
                                            @NonNull  ProgressBar progressbarbootandasync,
                                            @NonNull Activity activity,
                                            @NonNull DrawerLayout drawerLayoutAsync,
                                            @NonNull  NavigationView navigationViewAsyncApp,
                                            @NonNull Context context,
                                            @NonNull LifecycleOwner lifecycleOwner) {

        this.lifecycleOwner = lifecycleOwner;
        this.getsslSocketFactory2 = getsslSocketFactory2;
        this.progressbarbootandasync = progressbarbootandasync;
        this.activity = activity;
        this.drawerLayoutAsync = drawerLayoutAsync;
        this.navigationViewAsyncApp = navigationViewAsyncApp;
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        // TODO: 24.01.2024  
        startingEventAsyncOrUpdatePOUsers=new StartingEventAsyncOrUpdatePOUsers(context);
    }


    public void getEventBusUpdatePo(@NonNull MessageEvensBusUpdatePO messageEvensBusUpdatePO){

        try{
            Bundle bundleGetOtServiceUpdatePO =(Bundle)         messageEvensBusUpdatePO.mess.getExtras();
            String Статус=   bundleGetOtServiceUpdatePO.getString("Статус");
            Integer СервернаяВерсия=   bundleGetOtServiceUpdatePO.getInt("СервернаяВерсия");


                    if(Статус.contains( "У вас последная версия ПО !!!")){


                        Toast.makeText(context,
                                "Последная версия ПО !!! "+ СервернаяВерсия    , Toast.LENGTH_LONG).show();

// TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                    }else {
                        if(Статус.contains( "Запускаем Обновление ПО !!!!")) {
                            // TODO: 22.01.2024
                            DownLoadPO downLoadPO=new DownLoadPO(activity,context,СервернаяВерсия,getsslSocketFactory2);

                            downLoadPO.МетодСообщениеАнализПО( );
                            // TODO: 26.12.2022  конец основгого кода
                            Log.d(context.getClass().getName(), "\n" + " class "
                                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    public void getEventBusManagerAsync(@NonNull MessageEvensBusAyns messageEvensBusAyns){

        try{

            Bundle bundleGetOtServicePrograssBar =(Bundle)         messageEvensBusAyns.mess.getExtras();
            String Статус=   bundleGetOtServicePrograssBar.getString("Статус");


                    if(Статус.contains("Логин и/или пароль неправильный !!!!")){
                        методПереходНаActivityPassword();
                        Toast.makeText(context, "Логин и/или пароль неправильный !!!!"    , Toast.LENGTH_LONG).show();
                    }




                        if(Статус.contains("Вы заблокирован   !!!!")){
                            // TODO: 22.01.2024  заблокирован
                            методПереходНаActivityPassword();
                            Toast.makeText(context, "Вы заблокирован   !!!! "    , Toast.LENGTH_LONG).show();

                        }

            if(Статус.contains("Первый запуск  !!!!")){
                // TODO: 22.01.2024  заблокирован
                методПереходНаActivityPassword();
              //  Toast.makeText(context, "Вы заблокирован   !!!! "    , Toast.LENGTH_LONG).show();

            }




            if(Статус.contains(    "Сервер выкл.!!!")){
                Toast.makeText(context,     "Сервер выкл.!!!"    , Toast.LENGTH_LONG).show();

            }







                    // TODO: 26.12.2022  конец основгого кода
                    Log.d(context.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Статус " +Статус);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    public void getEventBusPrograssBar(MessageEvensBusPrograssBar messageEvensBusPrograssBar){

        try{
                    Bundle bundleGetOtServicePrograssBar =(Bundle)         messageEvensBusPrograssBar.mess.getExtras();
                    String Статус=   bundleGetOtServicePrograssBar.getString("Статус");

                    if (Статус.contains("PrograssBarVisible")) {

                        progressbarbootandasync.setIndeterminate(true);
                        progressbarbootandasync.requestLayout();
                        progressbarbootandasync.refreshDrawableState();

                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    }


                    if (Статус.contains("AsyncPrograssBar")) {

                        МетодВизуализацииСинхронизации(bundleGetOtServicePrograssBar);

                        progressbarbootandasync.requestLayout();

                        progressbarbootandasync.refreshDrawableState();


                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }else {








                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(context.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }





            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





































    private void методПереходНаActivityPassword() {
        try {
            Intent Интент_ЗапускаетFaceApp=new Intent();
            Интент_ЗапускаетFaceApp.setClass(context, MainActivityPasswords.class);
            Интент_ЗапускаетFaceApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Интент_ЗапускаетFaceApp.setAction("MainActivityPasswords.class");
           activity.startActivity(Интент_ЗапускаетFaceApp);//tso
           activity. finish();

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
    // TODO: 19.01.2024





    public void stopServiceBootAndAsync(){

        try{

            Intent intentstartServiceOneSignal=new Intent(context, IntentServiceBoot.class);
            intentstartServiceOneSignal.setAction("IntentServiceBootAsync.com");
            activity.stopService(intentstartServiceOneSignal);
            // TODO: 24.01.2024
            activity.finishAffinity();


            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










    public void МетодБоковаяПанельОткрытьЗАкрыть() {
        try {
            if (drawerLayoutAsync.isDrawerOpen(Gravity.LEFT)) {
                drawerLayoutAsync.closeDrawer(Gravity.LEFT);
            }

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодДополнительнойНастрокиАвтоЗапуска() {
        try {
            final Intent[] AUTO_START_INTENTS = {
                    new Intent().setComponent(new ComponentName("com.samsung.android.lool",
                            "com.samsung.android.sm.ui.battery.BatteryActivity")),
                    new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
                    new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
                    new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
                    new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
                    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
                    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
                    new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
                    new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
                    new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(
                            Uri.parse("mobilemanager://function/entry/AutoStart"))
            };
            for (Intent intent : AUTO_START_INTENTS) {
                if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    context.startActivity(intent);
                    // break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private void МетодВизуализацииСинхронизации(@NonNull Bundle bundleCallsBackAsynsService) {
        try {
            if (progressbarbootandasync.isAttachedToWindow()) {

                Integer ПроцентыNew = bundleCallsBackAsynsService.getInt("Проценны", 0);
                String ИмяТаблица = bundleCallsBackAsynsService.getString("имятаблицы", "");
                Integer MaxКоличествоСТрочеек = bundleCallsBackAsynsService.getInt("maxtables", 0);
                Integer CurrentPotionСТрочеек = bundleCallsBackAsynsService.getInt("currentposition", 0);

                if (CurrentPotionСТрочеек > 0) {
                    progressbarbootandasync.setIndeterminate(false);
                }

                progressbarbootandasync.setMax(MaxКоличествоСТрочеек);
                CurrentPotionСТрочеек = CurrentPotionСТрочеек + 1;
                if (CurrentPotionСТрочеек >= progressbarbootandasync.getProgress()) {
                    progressbarbootandasync.setProgress(CurrentPotionСТрочеек, true);
                    progressbarbootandasync.setProgressTintMode(PorterDuff.Mode.DARKEN);
                    // TODO: 14.08.2023
                    Integer secondaryProgres = CurrentPotionСТрочеек;
                    secondaryProgres++;
                    secondaryProgres++;
                    progressbarbootandasync.setSecondaryProgress(secondaryProgres);
                    progressbarbootandasync.setSecondaryProgressTintMode(PorterDuff.Mode.DARKEN);


                }

                progressbarbootandasync.requestLayout();
                progressbarbootandasync.refreshDrawableState();

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " progressbarbootandasync" + progressbarbootandasync.getProgress());

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




      public void startBl_inner(){

        try{

            new ClassPermissions(activity,ALL_PERSSION_CODE,CAMERA_PERSSION_CODE);

            Log.d(context.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодСитемныйНастройкиЭкран() {
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            //////todo настрока экрана
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(null);

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            // TODO: 25.03.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }








    // TODO: 04.10.2023
    public void МетодДляСлушательБоковойПанелиAsyncApp() {
        // TODO: 06.04.2022
        try {
            drawerLayoutAsync.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    try {
                    Drawable drawable =context. getResources().getDrawable(R.mipmap.icon_dsu1_for_asyncapp_close_naviga);///

                    navigationViewAsyncApp.setVisibility(View.VISIBLE);

                    super.onDrawerOpened(drawerView);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onDrawerClosed(View drawerView) {
                    try {
                    Drawable drawable =context. getResources().getDrawable(R.drawable.icon_dsu1_async_asynprograssbar);///

                    navigationViewAsyncApp.setVisibility(View.GONE);

                    super.onDrawerClosed(drawerView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }

            });
            navigationViewAsyncApp.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    try{
                    switch (item.getItemId()) {
                        // TODO: 06.04.2022 Запускаем ОШибки
                        case R.id.one:
                            try {
                            item.setChecked(true);
                                Intent Интент_Меню = new Intent(activity, MainActivity_Errors.class);
                                Интент_Меню.setAction("com.CallBackBootAndAsync");
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP

                               context. startActivity(Интент_Меню);

                                Log.d(context.getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " intent.getAction() "  );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                            break;
                        // TODO: 06.04.2022 ЗапускаемОбновление ПО
                        case R.id.item_async_updatepo:
                            item.setChecked(true);
                            try {
// TODO: 10.07.2023  запуск обновление ПО
                                startingEventAsyncOrUpdatePOUsers.  startServiceUpdatePO();

                                Log.d(context.getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        " intent.getAction() "  );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(context)
                                        .МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;

                    }
                    if (drawerLayoutAsync.isDrawerOpen(Gravity.LEFT)) {
                        drawerLayoutAsync.closeDrawer(Gravity.LEFT);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 04.04.2022
    }






































































































































}


