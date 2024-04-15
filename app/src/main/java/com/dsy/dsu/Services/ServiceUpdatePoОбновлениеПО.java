package com.dsy.dsu.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.BootAndAsync.BlBootAsync.SendMainActivity;
import com.dsy.dsu.BootAndAsync.EventsBus.MessageEvensBusAyns;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.CnangeServers.PUBLIC_CONTENT;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class ServiceUpdatePoОбновлениеПО extends IntentService {////Service

    public ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО binder = new ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО();
    String ИмяСлужбыУведомленияДляОбновление = "WorkManager NOtofocationforUpdateSoft";
    private String PROCESS_IDSoftUpdate = "19";
    private  Integer ЛокальнаяВерсияПО = 0;
    private SharedPreferences preferences;
    private String ИмяПотока="binderupdatepo";
    private  Context context;
    private  Boolean РежимРаботыСлужбыОбновлениеПО=false;


    private   AlertDialog alertDialogУстановкаПО=null;
    private    File FileAPK = null;
    @Inject
    SSLSocketFactory getsslSocketFactory2;

    private   Integer  ВерсияПООтСервере = 0;

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




    private   void metodNetWorkResimDOnt( ) {

        try{
            class SendUserРежимСетиВыбранПользователем extends SendMainActivity {

                public SendUserРежимСетиВыбранПользователем(Context context) {
                    super(context);
                }

                @Override
                public void startSendBroadSesiver() {
                    //  super.startSendBroadSesiver();
                    intentComunications.setAction("Broad_messageAsyncOrUpdateAsync");
                    bundleComunications.putString("Статус",  "Режим Сети не допустим !!! ");///"В процесс"
                    bundleComunications.putString("Действие",   "Режим Сети не допустим !!! ");///"В процесс"
                    intentComunications.putExtras(bundleComunications);

                    EventBus.getDefault().post(new MessageEvensBusAyns(intentComunications));

                    // TODO: 22.01.2024 останавливаем службу
                    stopServiceBoot();


                }
            }
            // TODO: 22.01.2024 когда режим офлайн
            new SendUserРежимСетиВыбранПользователем(context).startSendBroadSesiver();
            // TODO: 03.10.2023
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }

    @SuppressLint("NewApi")
    public Integer МетодГлавныйОбновленияПОДоAsync(@NonNull Boolean РежимРаботыСлужбыОбновлениеПО ,
                                                   @NonNull Context  context,
                                                   @NonNull LinkedHashMap<Integer,String> getHiltJbossDebug,
                                                   @NonNull LinkedHashMap<Integer,String> getHiltJbossReliz ){

        try {
            this.context=context;
            this.РежимРаботыСлужбыОбновлениеПО=РежимРаботыСлужбыОбновлениеПО;
            String  РежимРаботыСети = МетодУзнаемРежимСетиWIFiMobile(getApplicationContext());
            preferences = getApplicationContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            if (РежимРаботыСети.equals("WIFI")  || РежимРаботыСети.equals("Mobile")  ) {
                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ "РежимРаботыСети " + РежимРаботыСети);

                // TODO: 18.02.2023 удаление перед анализо файлов json И .apk

                МетодДополнительногоУдалениеФайлов();

                // TODO: 18.02.2023 удаление перед анализо файлов json И .apk

                ВерсияПООтСервере  =       МетодАнализаВерсииПОJSON(getHiltJbossDebug,getHiltJbossReliz);

                PackageInfo    pInfo = getApplicationContext(). getPackageManager().getPackageInfo(getApplicationContext(). getPackageName(), 0);
                String version = pInfo.versionName;//Version Name
                Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code

                if (ВерсияПООтСервере <=ЛокальнаяВерсияПО ) {
                    ВерсияПООтСервере=0;
                }




                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ВерсияПООтСервере " +ВерсияПООтСервере);


            }else {

                metodNetWorkResimDOnt( );

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");



            }
            Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() +
                    " ВерсияПООтСервере " +ВерсияПООтСервере);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ВерсияПООтСервере;
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

     Integer МетодДополнительногоУдалениеФайлов() {
        Integer РезультатУдаления = 0;
        try {
            String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";
/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            File ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии;
            if (Build.VERSION.SDK_INT >= 30) {
                // TODO: 10.04.2022
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии =
                        getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            } else {
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS+ File.separator + PatchDeleteJsonAnalitic);
            }

            // TODO: 10.04.2022
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();

            if (Files!=null) {
                for (int i = 0; i < Files.length; i++) {
                    String ИмяФайла = Files[i].getName();
                    // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    boolean ФайлУдаленияJson = ИмяФайла.trim().matches("(.*)update_dsu1(.*)") &&
                            ИмяФайла.trim().matches("(.*)json(.*)") ;//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    if(ФайлУдаленияJson==true){
                        Files[i].delete();
                        Log.d(this.getClass().getName(), "ФайлУдаленияJson" + ФайлУдаленияJson );
                    }
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















    // TODO: 13.03.2023  Метод Анализа JSON файла
    Integer МетодАнализаВерсииПОJSON(  @NonNull LinkedHashMap<Integer,String> getHiltJbossDebug,
                                       @NonNull LinkedHashMap<Integer,String> getHiltJbossReliz ) {
        Integer СервернаяВерсияПОВнутри = 0;
        try {
            String PatchDeleteJsonAnalitic="SousAvtoFile/UpdatePO";

            // TODO: 02.04.2024  Адресс и Порт Сервера Jboss
            String   ИмяСерверИзХранилица = getHiltJbossDebug.values().stream().map(m->String.valueOf(m)).findFirst().get();
            Integer    ПортСерверИзХранилица = getHiltJbossDebug.keySet().stream().mapToInt(m->m).findFirst().getAsInt();

            // TODO: 08.01.2022 Полученм JSON File  для анализа
            File ФайлJsonОтСервера = new Class_MODEL_synchronized(getApplicationContext()).
                    МетодЗагрузкиОбновлениеПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).getСсылкаНаРежимСервераОбновлениеПО(),
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица,"FileJsonUpdatePO",
                            "update_dsu1.json","application/json",10,getsslSocketFactory2);

            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()
                    + Thread.currentThread().getName()+" ФайлJsonОтСервера" + ФайлJsonОтСервера);
            // TODO: 13.03.2023 Анализ JSON
            if(ФайлJsonОтСервера!=null && ФайлJsonОтСервера.isFile()){
                File ФайлJSonКоторыйУжеСохраненный = null;
                if (Build.VERSION.SDK_INT >= 30) {
                    ФайлJSonКоторыйУжеСохраненный =
                            getApplicationContext().getExternalFilesDir( Environment.DIRECTORY_DOWNLOADS
                                    + File.separator + PatchDeleteJsonAnalitic  +File.separator + "update_dsu1.json");
                } else {
                    ФайлJSonКоторыйУжеСохраненный =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS
                                    + File.separator + PatchDeleteJsonAnalitic+File.separator + "update_dsu1.json");
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


}