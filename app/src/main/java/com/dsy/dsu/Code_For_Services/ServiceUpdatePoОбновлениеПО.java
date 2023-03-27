package com.dsy.dsu.Code_For_Services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ServiceUpdatePoОбновлениеПО extends IntentService {////Service

    public ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО binder = new ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО();
    String ИмяСлужбыУведомленияДляОбновление = "WorkManager NOtofocationforUpdateSoft";
    private String PROCESS_IDSoftUpdate = "19";
    private  Integer ЛокальнаяВерсияПО = 0;
    private SharedPreferences preferences;
    private String ИмяПотока="binderupdatepo";
    private  Activity activity;
    private  Boolean РежимРаботыСлужбыОбновлениеПО=false;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ServiceUpdatePoОбновлениеПО() {
        super("Binder_UpdatePO");
    }

    public ServiceUpdatePoОбновлениеПО(String name) {

        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try{
        Log.d(getApplicationContext().getClass().getName(), "ServiceUpdatePoОбновлениеПО "
                + " время: "
                + new Date());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_ДЛЯ ОБНОВЛЕНИЯ ПО  ДЛЯ ЧАТА onHandleWork Exception ");

    }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
        Log.i(getApplicationContext().getClass().getName(), "Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ Обновление ПО  ДЛЯ ЧАТА onDestroy() время "+new Date());
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications   Обновление ПО  onDestroy() время " + new Date());

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return   binder;
    }
    public class localBinderОбновлениеПО extends Binder {
        public ServiceUpdatePoОбновлениеПО getService() {
            // Return this instance of LocalService so clients can call public methods
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            return ServiceUpdatePoОбновлениеПО.this;
        }
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try{
            МетодГлавныйОбновленияПО(false,activity);
            Log.i(getApplicationContext().getClass().getName(), " ServiceUpdatePoОбновлениеПО  МетодГлавныйЗапускаОбновлениеПО  " + " время запуска  " + new Date());
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications   Обновление ПО  onDestroy() время " + new Date());

    }
    }


    public void МетодГлавныйОбновленияПО(@NonNull Boolean РежимРаботыСлужбыОбновлениеПО ,
                                         @NonNull Activity  activity){
        try {
            this.activity=activity;
            this.РежимРаботыСлужбыОбновлениеПО=РежимРаботыСлужбыОбновлениеПО;
            final Integer[] ВерсияПООтСервере = {0};
            Completable.fromSupplier(new Supplier<Object>() {
                        @Override
                        public Object get() throws Throwable {
                            String  РежимРаботыСети = МетодУзнаемРежимСетиWIFiMobile(getApplicationContext());
                            preferences = getApplicationContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                            if (РежимРаботыСети.equals("WIFI")  || РежимРаботыСети.equals("Mobile")  ) {
                                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ "РежимРаботыСети " + РежимРаботыСети);
                                // TODO: 18.02.2023 удаление перед анализо файлов json И .apk
                                МетодДополнительногоУдалениеФайлов();
                                // TODO: 18.02.2023 удаление перед анализо файлов json И .apk
                                 ВерсияПООтСервере[0] =       МетодАнализаВерсииПОJSON();
                                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время "
                                        +new Date().toLocaleString() +  "ВерсияПООтСервере " + ВерсияПООтСервере[0]);

                                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                            }else {
                                Log.e(this.getClass().getName(), "неТ СВЯЗИ ДЛЯ ЗАГРУЗКИ ПО ТипПодключенияИнтернтаДляСлужбы "  + РежимРаботыСети);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (РежимРаботыСлужбыОбновлениеПО == true) {
                                            Toast toast = Toast.makeText(  getApplicationContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
                                            toast.setGravity(Gravity.BOTTOM, 0, 40);
                                            toast.show();
                                            Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                                        }
                                    }
                                });
                            }
                            return РежимРаботыСети;
                        }
                    })
                    .subscribeOn(Schedulers.single())
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 18.02.2023 Анализ Версии
                            МетодАнализВерсийЛокальнаяИСерверная(ВерсияПООтСервере[0]);
                            Log.d(getApplicationContext().getClass().getName(), "\n" + "   ФинальныйРезультатAsyncBackgroud ");
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                            return false;
                        }
                    }).subscribe();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private File МетодЗагрузкиAPK()  {
        File  FileAPK = null;
        try {
            Log.d(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName()+"Загружаем Файл APK."+new Date());
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
            // TODO: 08.01.2022 Полученм JSON File  для анализа
            FileAPK = new Class_MODEL_synchronized(getApplicationContext()).
                    МетодЗагрузкиОбновлениеПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервераОбновлениеПО(),
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица,"FileAPKUpdatePO","update_dsu1.apk");
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()
                    + Thread.currentThread().getName()+" FileAPK" + FileAPK);
            // TODO: 19.12.2021  загрузка файда  .apk    УниверсальныйБуферAPKФайлаПОсСервера("dsu1.glassfish/update_android_dsu1/app-release.apk", "update_dsu1.apk",
         /*   FileAPK = new Class_MODEL_synchronized(getApplicationContext()).
                    УниверсальныйБуферAPKФайлаПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).
                                    getСсылкаНаРежимСервера()+ "/update_android_dsu1/app-release.apk",
                            "update_dsu1.apk",
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица);
            Log.w(this.getClass().getName(), "FileAPK "+ FileAPK);*/

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return FileAPK;
    }











    private String МетодУзнаемРежимСетиWIFiMobile(Context КонтекстКоторыйДляСинхронизации) {
        String  РежимРаботыСети = new String();
        try{
            ConnectivityManager cm = (ConnectivityManager) КонтекстКоторыйДляСинхронизации.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if ( wifiInfo.isConnected()) {
                Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через wifi");
                return "WIFI";
            }else{
                NetworkInfo wifiInfoMObile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (wifiInfoMObile.isConnected()) {
                    Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через mobile");
                    return "Mobile";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РежимРаботыСети;
    }
    public Integer МетодДополнительногоУдалениеФайлов() {
        Integer РезультатУдаления = 0;
        try {
/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            File ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии;
            if (Build.VERSION.SDK_INT >= 30) {
                // TODO: 10.04.2022
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            } else {
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);
            }
            Log.d(this.getClass().getName(), "ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии" + ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии);
            // TODO: 10.04.2022
            Boolean[] ФайлУдаления = new Boolean[10];
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();
            if (Files != null) {
                int j;
                for (j = 0; j < Files.length; j++) {
                    String ИмяФайла = Files[j].getName();
                    // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[0] = ИмяФайла.matches("(.*)analysis_version(.*)");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[1] = ИмяФайла.matches("(.*)output-metadata.json(.*)");
                    ФайлУдаления[2] = ИмяФайла.matches("(.*)update_dsu1(.*)");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[3] = ИмяФайла.equalsIgnoreCase("update_dsu1.apk");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[4] = ИмяФайла.equalsIgnoreCase("update_dsu1.json");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    // TODO: 10.04.2022
                    Log.d(this.getClass().getName(), " СЛУЖБА  ТАКОГО ФАЙЛА БОЛЬШЕ НЕТ  .JSON АНАЛИЗ " + Files[j].length()
                            + "   путь файла " + Files[j].getAbsolutePath() + "   --- " + new Date() + " ИмяФайла " + ИмяФайла);
                    if (ФайлУдаления[0] = true || ФайлУдаления[1] == true ||
                            ФайлУдаления[2] == true || ФайлУдаления[3] == true  || ФайлУдаления[4] == true ) {
                        // TODO: 10.04.2022
                        if (Files[j].exists()) {
                            // TODO: 10.04.2022
                            Files[j].delete();
                            // TODO: 10.04.2022
                            Log.d(this.getClass().getName(), " СЛУЖБА  ТАКОГО ФАЙЛА БОЛЬШЕ НЕТ  .JSON АНАЛИЗ " + Files[j].length()
                                    + "   путь файла " + Files[j].getAbsolutePath() + "   --- " + new Date() + " ИмяФайла " + ИмяФайла);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), " ошибка  faceapp из меню МетодДополнительногоУдалениеФайлов Обновление ПО ");
        }
        return РезультатУдаления;
    }

    @UiThread
    void МетодСообщениеЗапускЗагрущикаПо(@NonNull Integer СервернаяВерсияПОВнутри) {
        try {
            final File[] FileAPK = new File[1];
            LayoutInflater li = LayoutInflater.from(getApplicationContext());
            View promptsView = li.inflate(R.layout.activity_insertdata, null);
            ProgressBar progressBar=promptsView.findViewById(R.id.prograssbarupdatepo);
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
            promptsView.forceLayout();
            promptsView.refreshDrawableState();
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Загрущик")
                    .setView(promptsView)
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setPositiveButton("Загрузить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_update_success)
                    .show();
/////////кнопка
            final Button MessageBoxЗагрущикПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Integer finalСервернаяВерсияПОВнутри = СервернаяВерсияПОВнутри;
            MessageBoxЗагрущикПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
// TODO: 18.02.2023 Загрузка Нового файла APK
            activity.runOnUiThread(()->{
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);
                ((Button)v).setEnabled(false);
                Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
            });
            Log.i(this.getClass().getName(),  "Установщик ПО..." + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        Flowable.fromCallable(new Callable<File>() {
                                    @Override
                                    public File call() throws Exception {
                                           FileAPK[0] =  МетодЗагрузкиAPK();
                                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" +
                                                Thread.currentThread().getName());
                                        return FileAPK[0];
                                    }
                                })
                                .subscribeOn(Schedulers.single())
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable+ " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                })
                                .onErrorComplete(new Predicate<Throwable>() {
                                    @Override
                                    public boolean test(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        return false;
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        progressBar.setIndeterminate(false);
                                        alertDialog.dismiss();
                                        alertDialog.cancel();
                                        МетодУстановкиНовойВерсииПО(СервернаяВерсияПОВнутри,FileAPK[0]);
                                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + " FileAPK[0] " +FileAPK[0] );
                                    }
                                })
                                .subscribe();
                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    alertDialog.cancel();
                    Log.i(this.getClass().getName(),  "Установщик ПО..." + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @UiThread
    private void МетодУстановкиНовойВерсииПО(@NonNull Integer СервернаяВерсияПОВнутри,
                                             @NonNull File ЗагрузкиФайлаОбновенияПОДополнительный) {
        try {
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS"
                    + "ФайлыДляОбновлениеВычисляемНомерВерсииПО " +ЗагрузкиФайлаОбновенияПОДополнительный.length() );
             PackageManager pm = getApplicationContext().getPackageManager();
            String apkName = "update_dsu1.apk";
            String fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            if (Build.VERSION.SDK_INT >= 30) {
                fullPath = Environment.getExternalStorageState() + "/" + apkName;
            } else {
                fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            }
            fullPath = Environment.DIRECTORY_DOWNLOADS + "/" + apkName;
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Установщик")
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setPositiveButton("Установить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_updates_po_success)
                    .show();
/////////кнопка
            final Button MessageBoxУстановкаНовогоПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУстановкаНовогоПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.runOnUiThread(()->{
                        ((Button)v).setEnabled(false);
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
                    });
                    Log.i(this.getClass().getName(),  "Установка Обновления .APK СЛУЖБА "+ Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    if (Build.VERSION.SDK_INT >= 30) {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/";  //null
                    } else {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                    }
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА  ФинальныйПутьДляЗагрузкиФайлаОбновения " + ФинальныйПутьДляЗагрузкиФайлаОбновения);
                    String НазваниеФайлаОбновления = "update_dsu1.apk";
                    ФинальныйПутьДляЗагрузкиФайлаОбновения += НазваниеФайлаОбновления;
                    Uri URIПутиДляЗагрузкиФайловЧерезПровайдер = FileProvider.getUriForFile(getApplicationContext(),
                            getApplicationContext().getPackageName() + ".provider",
                            ЗагрузкиФайлаОбновенияПОДополнительный);
                    Log.d(this.getClass().getName(), "Установка ЗагрузкиФайлаОбновенияПОДополнительный  " + ЗагрузкиФайлаОбновенияПОДополнительный);
                    Intent intentОбновлениеПО = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                    intentОбновлениеПО.setDataAndType(URIПутиДляЗагрузкиФайловЧерезПровайдер, "application/vnd.android.package-archive");
                    intentОбновлениеПО.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                            | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_STREAM, URIПутиДляЗагрузкиФайловЧерезПровайдер);
                    PackageManager МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт = activity.getPackageManager();
                    if (intentОбновлениеПО.resolveActivity(МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт) != null) {
                        activity.startActivity(intentОбновлениеПО);
                        activity.finishAndRemoveTask();

                    } else {

                        Log.i(this.getClass().getName(),  " "+ Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "MessageBoxUpdateНеуСтанавливатьПО  ОТМЕНА УСТАНВОКИ НОВГО ПО   dismiss ");
                    alertDialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 13.03.2023  Метод Анализа JSON файла
    Integer МетодАнализаВерсииПОJSON() {
         Integer СервернаяВерсияПОВнутри = 0;
        try {
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
            // TODO: 08.01.2022 Полученм JSON File  для анализа
            File ФайлJsonОтСервера = new Class_MODEL_synchronized(getApplicationContext()).
                    МетодЗагрузкиОбновлениеПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервераОбновлениеПО(),
                                    getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица,"FileJsonUpdatePO","update_dsu1.json");
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()
                    + Thread.currentThread().getName()+" ФайлJsonОтСервера" + ФайлJsonОтСервера);
            // TODO: 13.03.2023 Анализ JSON
            if(ФайлJsonОтСервера!=null && ФайлJsonОтСервера.isFile()){
                File ФайлJSonКоторыйУжеСохраненный = null;
                if (Build.VERSION.SDK_INT >= 30) {
                    ФайлJSonКоторыйУжеСохраненный = getApplicationContext().getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS+"/" + "update_dsu1.json");
                } else {
                    ФайлJSonКоторыйУжеСохраненный = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/" + "update_dsu1.json");
                }
                Reader reader = new InputStreamReader( new FileInputStream(ФайлJSonКоторыйУжеСохраненный), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = null;
                if (reader!=null && reader.ready() ) {
                    bufferedReader = new BufferedReader( reader );
                }
                List<String> БуферСамиДанныеОтСервера = null;
                if (bufferedReader!=null  && bufferedReader.ready()) {
                    БуферСамиДанныеОтСервера = bufferedReader.lines().collect(Collectors.toList());
                }
                if (БуферСамиДанныеОтСервера!=null) {
                    СервернаяВерсияПОВнутри= Integer.parseInt(БуферСамиДанныеОтСервера.get(13).replaceAll("([^0-9])", "")) ;
                    // TODO: 13.03.2023
                    Log.d(getApplicationContext().getClass().getName(),"\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ФайлJsonОтСервера " +ФайлJsonОтСервера.isFile());
                }
                Log.d(getApplicationContext().getClass().getName(),"\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
            }
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СервернаяВерсияПОВнутри;
    }

    private void МетодАнализВерсийЛокальнаяИСерверная(@NonNull Integer СервернаяВерсияПОВнутри) {
  try{

      PackageInfo    pInfo = getApplicationContext(). getPackageManager().getPackageInfo(getApplicationContext(). getPackageName(), 0);
        String version = pInfo.versionName;//Version Name
        Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code
      Log.d(this.getClass().getName(),  " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
              + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+
               " СервернаяВерсияПОВнутри "+СервернаяВерсияПОВнутри);
        if (СервернаяВерсияПОВнутри >ЛокальнаяВерсияПО ) {
            МетодСообщениеЗапускЗагрущикаПо(СервернаяВерсияПОВнутри);
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
        }else{
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
            activity.runOnUiThread(()->{
                if (РежимРаботыСлужбыОбновлениеПО==true) {
                    Toast toast = Toast.makeText(getApplicationContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 40);
                    toast.show();
                }
            });
        }
    } catch (Exception e ) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}