package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Clears_Tables;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_ForTABEL.MainActivity_List_Tabels;
import com.dsy.dsu.Code_ForTABEL.MainActivity_New_Templates;
import com.dsy.dsu.Code_For_Commit_Payments_КодДля_Согласование.MainActivity_CommitPay;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Code_For_Services.Service_Async_1C;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/////////////////////////////////////////////////////////////////////////
public class MainActivity_Face_App extends AppCompatActivity {
    private ImageView imageView_ЗначекApp;
    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private LinearLayout LinearLayoutFaceApp;
    private ScrollView ScrollFaceAppСкорол;
    private Observer observerОдноразоваяFACEAPP;
    private Activity activity;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    private boolean РежимыПросмотраДанныхЭкрана;
    private Context context;
    // private MaterialCardView КнопкаЗадачи, КнопкаТабель, КнопкаЧат,КнопкаСогласование,КнопкаОтгрузкаМатериалов;
    private MaterialCardView КнопкаТабель, КнопкаСогласование, КнопкаПоступлениеМатериалов;
    // private ProgressBar progressBarTask, progressBarTabel, progressBarChat,progressCommitpay,progressShipment_of_Materials;
    private ProgressBar progressBarTabel, progressCommitpay;
    private Handler handlerFaceAPP;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//"WorkManager Synchronizasiy_Data";//  "WorkManager Synchronizasiy_Data"; ///"WorkManager Synchronizasiy_Data";
    private DrawerLayout drawerLayoutFaceApp;
    private NavigationView navigationViewFaceApp;
    private ConstraintLayout constraintLayoutFaceApp;
    @Inject
    private Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;
    private Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binderМатериалы;
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;
    private Animation animation;
    protected SharedPreferences preferences;
    private Service_Async_1C service_Async_СинхронизацияОБЩАЯ1С;

    private   Message message;

    // TODO: 03.11.2022 FaceApp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_face_app);
            Log.w(getPackageName().getClass().getName(), "Сработал  protected void onCreate(Bundle savedInstanceState)  в MainActivity_Face_App");
            LinearLayoutFaceApp = (LinearLayout) findViewById(R.id.LineLayFaceApp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            context = this;
            activity = this;
            getSupportActionBar().hide(); ///скрывать тул бар
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());
            Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(getApplicationContext());
            КнопкаТабель = (MaterialCardView) findViewById(R.id.cardview2_For_MainActivity); ///// TODO КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСогласование = (MaterialCardView) findViewById(R.id.cardviewCommitPay_For_MainActivity); ///// TODO КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПоступлениеМатериалов = (MaterialCardView) findViewById(R.id.cardviewControlAccess); /////TODO КОНТРОЛЬ ДОСТУПА
            Log.d(this.getClass().getName(), "КнопкаЧат " + " КнопкаЗадачи "
                    + " КнопкаТабель " + КнопкаТабель + " КнопкаСогласование " + КнопкаСогласование + " КнопкаКонтрольДоступа " + КнопкаПоступлениеМатериалов);
            imageView_ЗначекApp = (ImageView) findViewById(R.id.imageView_ЗначекApp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp111);///
            imageView_ЗначекApp.setImageDrawable(drawable);
            progressBarTabel = (ProgressBar) findViewById(R.id.prograessbarTabel_inner_ardview_forMainActivity); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            progressCommitpay = (ProgressBar) findViewById(R.id.prograessbarCommitPay_inner_ardview_forMainActivity4); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            drawerLayoutFaceApp = (DrawerLayout) findViewById(R.id.drawerLayout_faceapp_menu); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            constraintLayoutFaceApp = (ConstraintLayout) findViewById(R.id.constraintLayout_faceapp22); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            navigationViewFaceApp = (NavigationView) findViewById(R.id.navigator_faceapp_main); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            drawerLayoutFaceApp.setBackgroundColor(Color.WHITE);         //TODO устанвливает цвета
            constraintLayoutFaceApp.setBackgroundColor(Color.WHITE);
            drawerLayoutFaceApp.setBackgroundColor(Color.WHITE);
            drawerLayoutFaceApp.setDrawingCacheBackgroundColor(Color.RED);//todo
        //    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabel);//TODO animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_vibrator1);
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row);//TODO animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_vibrator1);
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            progressBarTabel.setVisibility(View.INVISIBLE);
            progressCommitpay.setVisibility(View.INVISIBLE);

         // TODO: 18.02.2023 инициализвции message
            МетодИнициализацииMessage();
            // TODO: 18.02.2023   Инициализация Хандлера
            HadlerИнициализация();
            // TODO: 18.02.2023 установки для Обновленеи ПО
            МЕтодУстанавливаемРазрешенияДляОновлениеПО();
            // TODO: 06.04.2022
            МетодДляСлушательБоковойПанелиFaceApp();
            // TODO: 03.11.2022 биндинг служб
              МетодБиндингМатериалы();
            МетодБиндингаСогласования();
           МетодБиндингаОбновлениеПО();
            // TODO: 16.11.2022  ПОСЛЕ УСТАНОВКИ РАБОТАЕТ ОДИН РАЗ ПРИ СТАРТЕ ЗАРУСК ОБЩЕГО WORK MANAGER
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаетОБЩУЮСинхронизацию();
            МетодFaceApp_СлушательПриНажатииНаКнопки();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
// TODO: 06.06.2021 ЗАПУСК ТРЕХ СЛУЖБ
            Log.w(getPackageName().getClass().getName(), "drawerLayoutFaceApp    " + drawerLayoutFaceApp +
                    "  navigationViewFaceApp " + navigationViewFaceApp);/////////
            МетодПовторныйЗапускУведомений();
            МетодБоковаяПанельОткрытьЗАкрыть();
            // TODO: 17.02.2023 ЗапускАнализа Наличитие Новой Версии ПО
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            КнопкаТабель.setAnimation(animation);
            КнопкаСогласование.setAnimation(animation);
            КнопкаПоступлениеМатериалов.setAnimation(animation);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодБоковаяПанельОткрытьЗАкрыть() {
        try {
            if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 03.04.2022
    private void МетодДляСлушательБоковойПанелиFaceApp() {
        // TODO: 06.04.2022
        try {
            imageView_ЗначекApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navigationViewFaceApp.isShown()) {
                        navigationViewFaceApp.setVisibility(View.GONE);
                        if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                            drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
                        }
                    } else {
                        navigationViewFaceApp.setVisibility(View.VISIBLE);
                        if (!drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                            drawerLayoutFaceApp.openDrawer(Gravity.LEFT);
                        }
                    }
                }
            });
            drawerLayoutFaceApp.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp_close222);///
                    imageView_ЗначекApp.setImageDrawable(drawable);
                    navigationViewFaceApp.setVisibility(View.VISIBLE);
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp111);///
                    imageView_ЗначекApp.setImageDrawable(drawable);
                    navigationViewFaceApp.setVisibility(View.GONE);
                    super.onDrawerClosed(drawerView);
                }
            });
            navigationViewFaceApp.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        // TODO: 06.04.2022
                        case R.id.one:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Посмотреть ошибки   " + item.getItemId() + "\n");//////////
                            try {
                                Intent Интент_Меню = new Intent(getApplication(), MainActivity_Errors.class);
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                Log.d(this.getClass().getName(), "" +
                                        "                     case R.id.ПунктМенюПервый:");
                                startActivity(Интент_Меню);
                            } catch (Exception e) {
                                //  Block of code to handle errors
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.two:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Хотите перерйти в натройки    " + item.getItemId() + "\n");/////////
                            try {
                                МетодЗапускаИзМенюНастроек();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.tree:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Сменить пользователя и смена данных    " + item.getItemId() + "\n");/////////
                            try {
                                Boolean ЕслиСвязьсСервером =
                                        new Class_Connections_Server(getApplicationContext()).МетодПингаСервераРаботаетИлиНет(getApplicationContext());

                                if (ЕслиСвязьсСервером == true) {
                                    String ПолученыйТекущееИмяПользователя = new Class_MODEL_synchronized(getApplicationContext())
                                            .МетодПолучениеИмяСистемыДляСменыПользователя(getApplicationContext());
                                    Log.d(this.getClass().getName(), "  ПолученыйТекущееИмяПользователя " +
                                            ПолученыйТекущееИмяПользователя);
                                    МетодДиалогаДляМеню("Пользователи Системы", "При смене пользователя,"
                                            + "\n" + " поменяються и данные системы." + "\n"
                                            + "Поменять пользователя ?" + "\n"
                                            + " (текущий пользователь : ) " + ПолученыйТекущееИмяПользователя.toUpperCase());
                                } else {
                                    Toast.makeText(getApplicationContext(), "Для смены данных, нужно подключение к серверу !!! "
                                            + "\n", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.four:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId()  Синхронизация Данных с Web-сервера ДСУ-1  " + item.getItemId() + "\n");/////////
                            try {
                                Boolean ЕслиСвязьсСервером =
                                        new Class_Connections_Server(getApplicationContext()).МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                                      if(ЕслиСвязьсСервером==true){
                                          // TODO: 28.09.2022 ЗАпускаем синхронизацию
                                          МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP();
                                          Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                  + " ЕслиСвязьсСервером "+ЕслиСвязьсСервером );
                                      }else {
                                          activity.runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                      Toast toast = Toast.makeText(  getApplicationContext(), "Нет связи c Cервером !!!", Toast.LENGTH_LONG);
                                                      toast.setGravity(Gravity.BOTTOM, 0, 40);
                                                      toast.show();
                                                      Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                                              }
                                          });
                                          Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                  + " ЕслиСвязьсСервером "+ЕслиСвязьсСервером );
                                      }
                                Log.d(this.getClass().getName(), "Отработала синх.. Из Меню Активти FACEAPP Синхронизация Данных с Web-сервера ДСУ-1 ?");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.shestoy:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId()  Шаблоны из меню  " + item.getItemId() + "\n");/////////
                            try {
                                Intent Интент_BackВозвращаемАктивти = new Intent();
                                Интент_BackВозвращаемАктивти.setClass(getApplicationContext(), MainActivity_New_Templates.class); // Т
                                Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Интент_BackВозвращаемАктивти.putExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", true);
                                startActivity(Интент_BackВозвращаемАктивти);
                            } catch (Exception e) {
                                //  Block of code to handle errors
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.sedmoy:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() МЕНЮ ОБНОВЛЕНИЕ ПО    " + item.getItemId() + "\n"+item);/////////
                            try {
                                // localBinderОбновлениеПО.getService().  МетодЗапускАнализаПО(false,0,activity);
                                localBinderОбновлениеПО.getService().МетодГлавныйОбновленияПО(true,activity);
                                Log.i(this.getClass().getName(),  " Из меню установкаОбновление ПО "+ Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;

                        default:
                            // TODO: 06.04.2022
                            return false;
                    }
                    if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                        drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 04.04.2022
    }


    // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
    public void МетодБиндингМатериалы() {
        try {
            ServiceConnection    serviceConnectionМатериалы = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            binderМатериалы = (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;
                            if (binderМатериалы.isBinderAlive()) {
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "    onServiceDisconnected  Service_for_AdminissionMaterial" + " binderСогласованияbinderМатериалы.isBinderAlive() "
                                        + binderМатериалы.isBinderAlive());
                                ;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder" + binderМатериалы);
                            binderМатериалы = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок
                        }
                    }
                };

               /*    Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(context, Service_for_AdminissionMaterial.class);
                    context. bindService(intentЗапускСлужюыыСинхрониазцииБиндинг, serviceConnection, Context.BIND_AUTO_CREATE |
                            Context.BIND_ALLOW_OOM_MANAGEMENT |
                            Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_IMPORTANT );*/
            Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
            intentЗапускСлужюыыСинхрониазцииБиндинг.setAction("com.Service_for_AdminissionMaterial");
            bindService(intentЗапускСлужюыыСинхрониазцииБиндинг,Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT | Context.BIND_INCLUDE_CAPABILITIES
                    , Executors.newCachedThreadPool(), serviceConnectionМатериалы);



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 03.11.2022  1c
    // TODO: 29.03.2022  метод регмстарцмии локального брод кастера доля смен задачи
    private void МетодБиндингаОбновлениеПО() {
        try {
            ServiceConnection   connectionСогласования = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                      localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;
                        if (localBinderОбновлениеПО.isBinderAlive()) {
                            Log.i(getApplicationContext().getClass().getName(), "    onServiceConnected  localBinderОбновлениеПО)"
                                    + localBinderОбновлениеПО.isBinderAlive());
                            Bundle bundle=new Bundle();
                            bundle.putBinder("localBinderОбновлениеПО",localBinderОбновлениеПО);
                            message.setData(bundle);
                            message.sendToTarget();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()" + binderСогласования1C.isBinderAlive());
                        localBinderОбновлениеПО = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
            /*Intent intentЗапускСлужбыОбновлениеПО = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
            bindService(intentЗапускСлужбыОбновлениеПО, connectionСогласования,Context.BIND_AUTO_CREATE );*/
            Intent intentЗапускСлужбыОбновлениеПО = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
            intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");
            bindService(intentЗапускСлужбыОбновлениеПО,Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT
                    | Context.BIND_INCLUDE_CAPABILITIES,Executors.newCachedThreadPool(), connectionСогласования );


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодИнициализацииMessage() {
        try{
        message=Message.obtain(new Handler(Looper.myLooper()),()->{
            Bundle bundle=   message.getData();
            // localBinderОбновлениеПО.getService().  МетодЗапускАнализаПО(false,0,activity);
            localBinderОбновлениеПО.getService().МетодГлавныйОбновленияПО(false,activity);
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+ Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString() );
            Log.i(this.getClass().getName(), "bundle " +bundle);
        });
        message.setAsynchronous(true);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    private void МетодБиндингаСогласования() {
        try {
            ServiceConnection       connectionСогласования = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            binderСогласования1C = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) service;
                            if (binderСогласования1C.isBinderAlive()) {
                                Log.i(getApplicationContext().getClass().getName(), "    onServiceConnected  binderСогласованияbinderМатериалы.isBinderAlive()"
                                        + binderСогласования1C.isBinderAlive());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()" + binderСогласования1C.isBinderAlive());
                            binderСогласования1C = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускСлужюыыСинхрониазцииБиндинг1C = new Intent(getApplicationContext(), Service_Notificatios_Для_Согласования.class);
                intentЗапускСлужюыыСинхрониазцииБиндинг1C.setAction("com.Service_Notificatios_Для_Согласования");
                bindService(intentЗапускСлужюыыСинхрониазцииБиндинг1C, Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT
                        | Context.BIND_INCLUDE_CAPABILITIES,Executors.newCachedThreadPool(), connectionСогласования);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодПовторныйЗапускУведомений() {

        try {
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаУведомленияДляЗАДАЧ();
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаУведомленияЧАТА();
            Log.w(getPackageName().getClass().getName(), "  " +
                    " new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодПовторногоЗапускаУведомленияДляОдноразовойСинхрониазации()");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    
    void МетодFaceApp_СлушательПриНажатииНаКнопки() {
        try {
            КнопкаТабель.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // TODO: 23.03.2022
                        progressBarTabel.setVisibility(View.VISIBLE);
                        // TODO: 23.03.2022
                        КнопкаТабель.setBackgroundColor(Color.GRAY);
                        Intent Интент_ЗапускТабельногоУчётаПервыйШаг = new Intent();
                        Bundle data = new Bundle();
                        data.putBinder("binder", binderМатериалы);
                        Интент_ЗапускТабельногоУчётаПервыйШаг.putExtras(data);
                        Интент_ЗапускТабельногоУчётаПервыйШаг.setClass(getApplication(), MainActivity_List_Tabels.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                        Интент_ЗапускТабельногоУчётаПервыйШаг.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d(this.getClass().getName(), "" + "    КнопкаТабельныйУчёт.setOnClickListener(new View.OnClickListener() {");
                        startActivity(Интент_ЗапускТабельногоУчётаПервыйШаг);
                        handlerFaceAPP.postDelayed(() -> {
                            progressBarTabel.setVisibility(View.INVISIBLE);
                            КнопкаТабель.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        }, 3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            });

            КнопкаСогласование.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        progressCommitpay.setVisibility(View.VISIBLE);
                        КнопкаСогласование.setBackgroundColor(Color.GRAY);
                        Log.d(this.getClass().getName(), "Запускает Согласния   ");
                        Intent intentЗапускСогласования1C = new Intent();
                        Bundle data = new Bundle();
                        data.putBinder("binderСогласования1C", binderСогласования1C);
                        intentЗапускСогласования1C.putExtras(data);
                        intentЗапускСогласования1C.setClass(getApplicationContext(), MainActivity_CommitPay.class);//рабочий
                        intentЗапускСогласования1C.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentЗапускСогласования1C);
                        handlerFaceAPP.postDelayed(() -> {
                            progressCommitpay.setVisibility(View.INVISIBLE);
                            КнопкаСогласование.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        }, 3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
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


    private void МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP() {
        try {
            final Integer[] ФинальныйРезультатФоновойСинхронизации = {0};
            ProgressDialog progressDialogДляСинхронизации;
            progressDialogДляСинхронизации = new ProgressDialog(activity);
            progressDialogДляСинхронизации.setTitle("Синхронизация");
            progressDialogДляСинхронизации.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляСинхронизации.setProgress(0);
            progressDialogДляСинхронизации.setCanceledOnTouchOutside(false);
            progressDialogДляСинхронизации.setMessage("Обмен данными ....");
            progressDialogДляСинхронизации.show();

                            handlerFaceAPP.post(()->{
                                boolean СтатусСетиВыбранныйПользователем =
                                        new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                                Log.d(this.getClass().getName(), "  РезультатПроВеркиУстановкиПользователяРежимРаботыСети "
                                        + СтатусСетиВыбранныйПользователем);
                                Class_Connections_Server class_connections_serverПингаСерераИзАктивтиМеню = new Class_Connections_Server(getApplicationContext());
                                PUBLIC_CONTENT public_contentЗапусСинхрониазцииИМеню = new PUBLIC_CONTENT(getApplicationContext());

                                if (СтатусСетиВыбранныйПользователем == true) {
                                   Boolean СтатусСервераСоюзаВключенИлиНЕт =
                                           class_connections_serverПингаСерераИзАктивтиМеню.МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                                    if (СтатусСервераСоюзаВключенИлиНЕт== true) {
                                        Integer ПубличныйIDДляОдноразовойСинхрониазции =
                                                new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                                        Bundle bundleДляПЕредачи=new Bundle();
                                        bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляОдноразовойСинхрониазции);
                                        bundleДляПЕредачи.putBoolean("StatusOneWokManagers", true);
                                        Intent  intentЗапускОднорworkanager=new Intent();
                                        intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
                                        // TODO: 02.08.2022
                                        new Class_Generator_One_WORK_MANAGER(getApplicationContext()).
                                                МетодИзFaceAppОдноразовыйЗапускВоерМенеджера(getApplicationContext(),intentЗапускОднорworkanager);
                                        // TODO: 26.06.2022
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                + " ПубличныйIDДляОдноразовойСинхрониазции "+ПубличныйIDДляОдноразовойСинхрониазции );
                                        handlerFaceAPP.postDelayed(() -> {
                                            progressDialogДляСинхронизации.dismiss();
                                            progressDialogДляСинхронизации.cancel();
                                        }, 3000);


                                    }else {
                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast toast = Toast.makeText(getApplicationContext(), "Сервер выкл. !!!", Toast.LENGTH_LONG);
                                                toast.setGravity(Gravity.BOTTOM, 0, 40);
                                                toast.show();
                                            }
                                        });
                                    }
                                }
                            });


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }

    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ
    @UiThread
    protected void МетодДиалогаДляМеню(String ШаблонСообщения, String Самообщение) {
        try {
//////сам вид
            final AlertDialog DialogBox = new MaterialAlertDialogBuilder(activity)
                    .setTitle(ШаблонСообщения)
                    .setMessage(Самообщение)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_web_success)
                    .show();
            final Button MessageBox = DialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBox.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    try {
                        DialogBox.dismiss();
                        Intent Интент_Меню = new Intent();
                        switch (ШаблонСообщения.trim()) {
                            case "Ошибки системы":
                                try {
                                    Интент_Меню = new Intent(getApplication(), MainActivity_Errors.class);
                                    Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                    startActivity(Интент_Меню);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;
                            case "Данных системы":
                                try {
                                    Integer ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                                    if (ПубличныйIDДляФрагмента == null) {
                                        ПубличныйIDДляФрагмента = 0;
                                    }
                                   /* new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                                            МетодЗапускаетОДНОРАЗОВУЮСинхронизациюВнутриWorkManager(getApplicationContext(),
                                                    Integer.parseInt(ПубличныйIDДляФрагмента.toString()));*/
                                    Log.d(this.getClass().getName(), "    case \"Данных системы\": запуск синхрониазции из активти Одноразвой Службой  " + ПубличныйIDДляФрагмента);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;
                            case "Пользователи Системы":
                                Integer РезультатОчистикТАблицИДобалениеДаты = 0;
                                try {
                                    Handler handlerУдалениеТаблицПринудительно = new Handler();
                                    PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(activity);
                                    РезультатОчистикТАблицИДобалениеДаты =
                                            new Class_Clears_Tables(activity, handlerУдалениеТаблицПринудительно)
                                                    .ОчисткаТаблицДляПользователяЗапусксFaceApp(activity,
                                                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                                            activity);
                                    Log.d(this.getClass().getName(), "   ЗАПУСК ФОНРезультатОчистикТАблицИДобалениеДаты " +
                                            РезультатОчистикТАблицИДобалениеДаты);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }


                                ////
                                break;
                            ///////
                            case "Настройка системы":
                                /////данные с потока
                                /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
                                Интент_Меню.setClass(getApplication(), MainActivity_Settings.class); //MainActivity_Visible_Async //MainActivity_Face_App
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                startActivity(Интент_Меню);
                                ////TODO ДАННАЯ КОМАНДА ПЕРЕКРЫВАЕТ НЕ ЗАПУСКАЕМОЕ АКТИВТИ А АКТИВТИ КОТОРЕ ЕГО ЗАПУСТИЛО
                                finish();
                                break;
                        }

//ловим ошибки
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // конец запись в файл
                    }

                }


            });

        } catch (Exception e) {
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР

    }

    void ВиндингСлужбы1С() {
        try {
            ServiceConnection connectionОБЩЕЙ1СGet = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        Service_Async_1C.LocalBinderGET1С binder = (Service_Async_1C.LocalBinderGET1С) service;
                        if (binder.isBinderAlive() == true) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  " +
                                    "service_Async_СинхронизацияОБЩАЯ1С" + service_Async_СинхронизацияОБЩАЯ1С);
                            service_Async_СинхронизацияОБЩАЯ1С = binder.getService();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  " +
                                "service_Async_СинхронизацияОБЩАЯ1С" + service_Async_СинхронизацияОБЩАЯ1С);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    protected void МетодЗапускаИзМенюНастроек() {
        try{
            Intent      Интент_Меню = new Intent(context, MainActivity_Settings.class);
            Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
            Log.d(this.getClass().getName(), "" +
                    "          case R.id.ПунктМенюВторой:");
            context.      startActivity(Интент_Меню);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void МЕтодУстанавливаемРазрешенияДляОновлениеПО() {
        try {
            //////////////////////TODO SERVICE
            String[] permissions = new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_MEDIA_LOCATION,
                    Manifest.permission.INSTALL_PACKAGES,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(activity, permissions, 1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }

    void HadlerИнициализация(){
        handlerFaceAPP=new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                return true;
            }
        }){

        };
    }

}


