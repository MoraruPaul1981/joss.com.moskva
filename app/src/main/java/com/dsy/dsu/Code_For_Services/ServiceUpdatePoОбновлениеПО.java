package com.dsy.dsu.Code_For_Services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class ServiceUpdatePoОбновлениеПО extends IntentService {////Service

    public ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО binder = new ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО();
    String ИмяСлужбыУведомленияДляОбновление = "WorkManager NOtofocationforUpdateSoft";
    private String PROCESS_IDSoftUpdate = "19";
    private  Integer ЛокальнаяВерсияПО = 0;
    private SharedPreferences preferences;
    private String ИмяПотока="binderupdatepo";
    private  Activity activity;
    private  Boolean РежимРаботыСлужбыОбновлениеПО=false;
    private      AlertDialog alertDialogАнализВерсииПО =null;

    private   AlertDialog alertDialogУстановкаПО=null;
    private    File FileAPK = null;

    private Message message;
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
            МетодГлавныйОбновленияПО(false,activity,message);
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


    public Boolean   МетодГлавныйОбновленияПО(@NonNull Boolean РежимРаботыСлужбыОбновлениеПО ,
                                              @NonNull Activity  activity,
                                              @NonNull Message message){
        Boolean ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=false;
        try {
            this.activity=activity;
            this.РежимРаботыСлужбыОбновлениеПО=РежимРаботыСлужбыОбновлениеПО;
            final Integer[] ВерсияПООтСервере = {0};
            this.message=message;

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
                // TODO: 18.02.2023 Анализ Версии
                ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=               МетодАнализВерсийЛокальнаяИСерверная(ВерсияПООтСервере[0],true);
                Log.d(getApplicationContext().getClass().getName(), "\n" + "   ФинальныйРезультатAsyncBackgroud ВерсияПООтСервере[0]"+ВерсияПООтСервере[0]+
                        " ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна " +ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна);
                // TODO: 24.04.2023 останаливаем службу

                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
            }else {
                Log.e(this.getClass().getName(), "неТ СВЯЗИ ДЛЯ ЗАГРУЗКИ ПО ТипПодключенияИнтернтаДляСлужбы " + РежимРаботыСети);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (РежимРаботыСлужбыОбновлениеПО == true) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM, 0, 40);
                            toast.show();
                            Log.i(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName() + " время " + new Date().toLocaleString());
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна;
    }

    public Boolean МетодГлавныйОбновленияПОДоAsync(@NonNull Boolean РежимРаботыСлужбыОбновлениеПО ,
                                                   @NonNull Activity  activity,
                                                   @NonNull Message message){
        Boolean ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=false;
        try {
            this.activity=activity;
            this.РежимРаботыСлужбыОбновлениеПО=РежимРаботыСлужбыОбновлениеПО;
            this.message=message;
            final Integer[] ВерсияПООтСервере = {0};
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


                // TODO: 18.02.2023 Анализ Версии
                ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=         МетодАнализВерсийЛокальнаяИСерверная(ВерсияПООтСервере[0],false);
                Log.d(getApplicationContext().getClass().getName(), "\n" + "   ФинальныйРезультатAsyncBackgroud ВерсияПООтСервере[0]"+ВерсияПООтСервере[0]+
                        " ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна " +ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна);

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
            Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() +
                    " ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна " +ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна;
    }
    private File МетодЗагрузкиAPK()  {
        try {
            Log.d(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName()+"Загружаем Файл APK."+new Date());
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
            // TODO: 08.01.2022 Полученм JSON File  для анализа
            FileAPK = new Class_MODEL_synchronized(getApplicationContext()).
                    МетодЗагрузкиОбновлениеПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервераОбновлениеПО(),
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица,
                            "FileAPKUpdatePO","update_dsu1.apk","application/octet-stream",500);

            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()
                    + Thread.currentThread().getName()+" FileAPK" + FileAPK);

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

            // TODO: 10.04.2022
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();

            for (int i = 0; i < Files.length; i++) {
                String ИмяФайла = Files[i].getName();
                // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                boolean ФайлУдаленияJson = ИмяФайла.matches("(.*)update_dsu1(.*)") ;//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                if(ФайлУдаленияJson==true){
                    Files[i].delete();
                    Log.d(this.getClass().getName(), "ФайлУдаленияJson" + ФайлУдаленияJson );
                }
            }
            Log.d(this.getClass().getName(), "Files" + Files);
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
    void МетодСообщениеЗапускАнализВерсииДанныхПО(@NonNull Integer СервернаяВерсияПОВнутри) {
        try {
        View  promptsViewАнализПО=   методЗагрузкиСвоегоВидаДлAliadDialod(R.layout.activity_insertdata);
            ProgressBar progressBar=promptsViewАнализПО.findViewById(R.id.prograssbarupdatepo);
            MaterialButton bottom_alaliz_and_dwonloadupdatepo=promptsViewАнализПО.findViewById(R.id.bottom_alaliz_and_dwonloadupdatepo);
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
            promptsViewАнализПО.forceLayout();
            promptsViewАнализПО.refreshDrawableState();


            bottom_alaliz_and_dwonloadupdatepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        // TODO: 18.02.2023 Загрузка Нового файла APK
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setIndeterminate(true);
                        bottom_alaliz_and_dwonloadupdatepo.setEnabled(false);
                        bottom_alaliz_and_dwonloadupdatepo.setBackgroundColor(Color.GRAY);
                        promptsViewАнализПО.forceLayout();
                        promptsViewАнализПО.refreshDrawableState();
                        // TODO: 06.05.2023 делаем кнопки не активныйе
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));

                        Log.i(this.getClass().getName(),  "Установщик ПО..." + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        // TODO: 29.07.2023 анаиз глаыйн метож
                        методАнализJSONВерсииПО(СервернаяВерсияПОВнутри,progressBar);
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

                    Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" );
                }
            });

                // TODO: 28.07.2023
            MaterialAlertDialogBuilder materialAlertDialogBuilderАнализПО = (MaterialAlertDialogBuilder) new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                       .setTitle("Обновление")
                    .setCancelable(false)
                       .setView(promptsViewАнализПО)
                       .setMessage("Обновление ПО"
                               + "\n" + "ООО Союз-Автодор"
                               + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                       .setIcon(R.drawable.icon_dsu1_update_success);
            // TODO: 29.07.2023 Запускем Анали ПО ДИалог
            if (alertDialogАнализВерсииПО==null) {
                alertDialogАнализВерсииПО = materialAlertDialogBuilderАнализПО.show();
            }else {
                if (!alertDialogАнализВерсииПО.isShowing()) {
                    alertDialogАнализВерсииПО = materialAlertDialogBuilderАнализПО.show();
                }
            }
            // TODO: 29.07.2023  переопределем расмер диалога
            методДизайнРазмераAliarDialog(alertDialogАнализВерсииПО);
                // TODO: 29.07.2023 запускам анализ версии ПО Диалог
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    View методЗагрузкиСвоегоВидаДлAliadDialod(@NonNull Integer Вид)  {
        View promptsView=null;
   try{
        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        promptsView = li.inflate(Вид, null);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  promptsView;
    }


    private void методАнализJSONВерсииПО(@NonNull Integer СервернаяВерсияПОВнутри,@NonNull ProgressBar progressBar) {
        message.getTarget().post(()-> {
            try{
                FileAPK = МетодЗагрузкиAPK();
                Log.w(getApplicationContext().getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО + " СервернаяВерсияПОВнутри  " + СервернаяВерсияПОВнутри + " POOLS" +
                        Thread.currentThread().getName() + " FileAPK " + FileAPK);

                progressBar.setIndeterminate(false);
                alertDialogАнализВерсииПО.dismiss();
                alertDialogАнализВерсииПО.cancel();
                if (FileAPK != null && FileAPK.length() > 0) {
                    // TODO: 29.07.2023 ЗапускаемАнализ ВЕРСИИ ПО
                    МетодУстановкиНовойВерсииПО(СервернаяВерсияПОВнутри, FileAPK, alertDialogАнализВерсииПО);


                } else {

                    Log.w(getApplicationContext().getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО +
                                    " СервернаяВерсияПОВнутри  " + СервернаяВерсияПОВнутри +
                                    " POOLS" + " FileAPK " + FileAPK);
                }
                Log.w(getApplicationContext().getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " ЛокальнаяВерсияПО " + ЛокальнаяВерсияПО +
                                " СервернаяВерсияПОВнутри  " + СервернаяВерсияПОВнутри +
                                " POOLS" + " FileAPK " + FileAPK);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        });
    }

    void методДизайнРазмераAliarDialog(@NonNull AlertDialog alertDialog){

try{
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(   alertDialog.getWindow().getAttributes());
        layoutParams.width =WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height =  WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
    alertDialog.getWindow().setAttributes(layoutParams);
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
                                             @NonNull File ЗагрузкиФайлаОбновенияПОДополнительный,
                                             @NonNull AlertDialog alertDialogЗагрущик){
        try {
            View  promptsViewУстановкаПО=   методЗагрузкиСвоегоВидаДлAliadDialod(R.layout.simple_download_newversii_po);
            ProgressBar progressBar=promptsViewУстановкаПО.findViewById(R.id.prograssbarupdatepo);
            MaterialButton bottom_install_and_dwonloadupdatepo=promptsViewУстановкаПО.findViewById(R.id.bottom_install_and_dwonloadupdatepo);
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
            promptsViewУстановкаПО.forceLayout();
            promptsViewУстановкаПО.refreshDrawableState();
            bottom_install_and_dwonloadupdatepo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        progressBar.setIndeterminate(true);
                        progressBar.setVisibility(View.VISIBLE);
                        bottom_install_and_dwonloadupdatepo.setEnabled(false);
                        bottom_install_and_dwonloadupdatepo.setBackgroundColor(Color.GRAY);
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
                        Log.i(this.getClass().getName(),  "Установка Обновления .APK СЛУЖБА "
                                + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                        String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                        if (Build.VERSION.SDK_INT >= 30) {
                            ФинальныйПутьДляЗагрузкиФайлаОбновения = activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/";  //null
                        } else {
                            ФинальныйПутьДляЗагрузкиФайлаОбновения = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                        }
                        Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА  ФинальныйПутьДляЗагрузкиФайлаОбновения " + ФинальныйПутьДляЗагрузкиФайлаОбновения);
                        String НазваниеФайлаОбновления = "update_dsu1.apk";
                        ФинальныйПутьДляЗагрузкиФайлаОбновения += НазваниеФайлаОбновления;
                        Uri URIПутиДляЗагрузкиФайловЧерезПровайдер = FileProvider.getUriForFile(getApplicationContext(),
                                activity.getPackageName() + ".provider",
                                ЗагрузкиФайлаОбновенияПОДополнительный);
                        Log.d(this.getClass().getName(), "Установка ЗагрузкиФайлаОбновенияПОДополнительный  "
                                + ЗагрузкиФайлаОбновенияПОДополнительный);
                        Intent intentОбновлениеПО = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        intentОбновлениеПО.setDataAndType(URIПутиДляЗагрузкиФайловЧерезПровайдер,
                                "application/vnd.android.package-archive");
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
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });

            // TODO: 29.07.2023 Установка ПО  ДИАЛОГ  
            MaterialAlertDialogBuilder materialAlertDialogBuilderУстановкаПО     = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Установщик")
                    .setCancelable(false)
                    .setView(promptsViewУстановкаПО)
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setIcon(R.drawable.icon_dowload_new_packagepo);

            // TODO: 29.07.2023 Запускем Анали ПО ДИалог
            if (alertDialogУстановкаПО==null) {
                alertDialogУстановкаПО = materialAlertDialogBuilderУстановкаПО.show();
            }else {
                if (!alertDialogУстановкаПО.isShowing()) {
                    alertDialogУстановкаПО = materialAlertDialogBuilderУстановкаПО.show();
                }
            }
            // TODO: 29.07.2023  переопределем расмер диалога
            методДизайнРазмераAliarDialog(alertDialogУстановкаПО);

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
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица,"FileJsonUpdatePO",
                            "update_dsu1.json","application/json",10);

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


                    Integer intversionCode=   БуферСамиДанныеОтСервера.toString().lastIndexOf( "versionCode");
                    Integer versionName=   БуферСамиДанныеОтСервера.toString().indexOf( "versionName");
                    String БуферВерсияПОСервер=     БуферСамиДанныеОтСервера.toString().substring(intversionCode,versionName).replaceAll("([^0-9])", "");
                    СервернаяВерсияПОВнутри= Integer.parseInt(БуферВерсияПОСервер) ;
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

    private Boolean МетодАнализВерсийЛокальнаяИСерверная(@NonNull Integer СервернаяВерсияПОВнутри,@NonNull Boolean ФлагПоказыватьИлиНЕтСообзение) {
        Boolean ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=false;
        try{

            PackageInfo    pInfo = getApplicationContext(). getPackageManager().getPackageInfo(getApplicationContext(). getPackageName(), 0);
            String version = pInfo.versionName;//Version Name
            Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code
            Log.d(this.getClass().getName(),  " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+
                    " СервернаяВерсияПОВнутри "+СервернаяВерсияПОВнутри);
            if (СервернаяВерсияПОВнутри >ЛокальнаяВерсияПО ) {
                // TODO: 29.07.2023 Запускаем Метод Анализа ПО  
                МетодСообщениеЗапускАнализВерсииДанныхПО(СервернаяВерсияПОВнутри);
                
                // TODO: 10.07.2023
                ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=false;
                Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
            }else{
                Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                        " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
                if (ФлагПоказыватьИлиНЕтСообзение==true) {
                    activity.runOnUiThread(()->{
                        if (РежимРаботыСлужбыОбновлениеПО==true) {
                            Toast toast = Toast.makeText(getApplicationContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM, 0, 40);
                            toast.show();
                        }
                    });
                }
                // TODO: 10.07.2023
                ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна=true;
                // TODO: 10.07.2023 запускаем синхрониазцуию
            }
            // TODO: 04.05.2023 выключаем
            ///stopSelf();
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName() +
                    " ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна "+ ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна);
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ФлагЗАпускатьСинхронизациюПотосучтоВерсияРавна;
    }
}