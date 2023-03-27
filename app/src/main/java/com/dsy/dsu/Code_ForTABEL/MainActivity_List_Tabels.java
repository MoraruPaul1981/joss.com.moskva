package com.dsy.dsu.Code_ForTABEL;

import static java.util.Locale.setDefault;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity_List_Tabels extends AppCompatActivity  {
    private  Spinner СпинерВыборДату;/////спинеры для создание табеляСпинерТабельДепратамент
    private   String ПолученноеЗначениеИзСпинераДата; ///результат полученный из спенров
    private   String КакойКонтекст;
    private  ScrollView ScrollНаАктивтиСозданныхТабелей;
    private  LinearLayout LinearLayoutСозданныхТабелей;
    private   ProgressDialog progressDialogДляУдаления;
    private  boolean РежимыПросмотраДанныхЭкрана;
    private  EditText ПрослойкаМеждуТабелей;
    private    Configuration config;
    private    String ПослеСозданиеовгоТабеляГОд= "";
    private  String ПослеСозданиеовгоТабеляМЕсяц= "";
    private   String ПослеСозданиеовгоТабеляВместеГодИМесяц= "";
    private   String ПолученноеЗначениеИзТолькоСпинераДата= "";
    private    String  ПослеСозданияНовогоТабеляЕгоUUID;
    private    String   ПослеСозданияНовогоТабеляЕгоПолноеНазвание= "";
    private   String ПубличноеИмяКнопкиТабеля;
    private  String МесяцВСпинореНижней;
    private   Button ТабелявВидеКнопок=null;
    private     String ПолученныйГодДляНовогоТабеля= "";
    private   String ФинальнаяМЕсяцДляНовогоТабеля= "";
    private    LinkedList<String> МассивДляВыбораВСпинерДата = new LinkedList<>(); //////АКАРЛИСТ ДЛЯ ПОЛУЧЕНЫЙ НОВЫХ ДАТ
    private   int МЕсяцВвидеЦифрыДляКурсора;
    private  int ГОДВвидеЦифрыДляКурсора;
    private   int ЦифровоеИмяНовгоТабеля;
    private   Context context;
    private  String  МесяцТабеляФиналИзВсехСотрудниковВТАбеле;
    private   String ГодТабеляФиналИзВсехСотрудниковВТАбел;
    private   Button    КнопкаНазадВсеТабеля;
    private  CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private    Long ПолученнаяUUIDНазванияОрганизации;
    private  TextView textViewКоличествоТабелей;
    private  FloatingActionButton КруглаяКнопкаСозданиеНовогоТабеля;
    private  int ПолучеаемЦифруСФО;
    private  Activity activity;
    private   Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;
    private Class_GRUD_SQL_Operations class_grud_sql_operationsДляАктивтиТабель ;
    private   PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков ;
    private Long   РодительскийUUDТаблицыТабель=0l;
    private SharedPreferences sharedPreferencesХранилище;
    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binder;
    private  Animation     animation;
  private    Cursor     Курсор_ДанныеДляСпинераДаты;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main__historytabely);
            activity=this;
            context =this;
            getSupportActionBar().hide(); ///скрывать тул бар
            class_grud_sql_operationsДляАктивтиТабель      = new Class_GRUD_SQL_Operations(getApplicationContext());
              Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ScrollНаАктивтиСозданныхТабелей = (ScrollView) findViewById(R.id.ScrollViewСамТабеля); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
        LinearLayoutСозданныхТабелей = (LinearLayout) findViewById(R.id.ГлавныйКонтейнерТабель); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
        //todo кнопка назад
        КнопкаНазадВсеТабеля= findViewById(R.id.КонопкаНазадСтрелкаВсеТабеля);
        textViewКоличествоТабелей= findViewById(R.id.textViewКоличествоТабелей);
        СпинерВыборДату=(Spinner) findViewById(R.id.СпинерТабельМесяцИсториииТабелей);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        Locale locale = new Locale("rus");
        setDefault(locale );
        config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
              // animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabel);
               animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabellist);


         КруглаяКнопкаСозданиеНовогоТабеля = findViewById(R.id.КруглаяКнопкаСамТабель);//////КНОПКА СОЗДАНИЕ НОВГО ТАБЕЛЯ ИЗ ИСТОРИИ ВТОРОЙ ШАГ СОЗДАНИЯ ТАБЕЛЯ СНАЧАЛА ИСТРОИЯ ПОТОМ НА БАЗЕ ЕГО СОЗЗДАНИЕ
            // TODO: 14.10.2022 настйрока хранилища
            sharedPreferencesХранилище=   getApplicationContext().getSharedPreferences("sharedPreferencesХранилище",
                    Context.MODE_MULTI_PROCESS);
            SharedPreferences.Editor editor = sharedPreferencesХранилище.edit();
            editor.putString( "sharedPreferencesХранилищеkey", "sharedPreferencesХранилищеvalue" );
            editor.commit();
            Bundle data=     getIntent().getExtras();
            if (data!=null) {
                binder=  (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            // TODO: 06.11.2022 методы после создание
            МетодКруглаяКнопка();
            МетодНазадBACKНААктивти();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }}

    private void МетодКруглаяКнопка() {
        КруглаяКнопкаСозданиеНовогоТабеля.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO СОЗДАНИЕ НОВОГО ТАБЕЛЯ
                try{
                    Log.d(this.getClass().getName()," создание нового сотрудника " );
                    ///TODO создание нового ТАБЕЛЯ
                    МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }}
        });
    }

    private void МетодНазадBACKНААктивти() {
        КнопкаНазадВсеТабеля.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
                    ///todo код которыц возврящет предыдущий актвитики кнопка back
                    Intent Интент_BackВозвращаемАктивти = new Intent();
                    Bundle data=new Bundle();
                    data.putBinder("binder", binder);
                    Интент_BackВозвращаемАктивти.putExtras(data);
                    Интент_BackВозвращаемАктивти.setClass(getApplication(), MainActivity_Face_App.class); // Т
                    Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( Интент_BackВозвращаемАктивти);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


        });
    }


    void МетодПолучениеДанныхДляДаногоАктивтиИсторияТАбеля() {
        try{
        Intent Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода = getIntent();
            if (МесяцВСпинореНижней ==null) {
                МесяцВСпинореНижней = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре");
            }
        ПослеСозданиеовгоТабеляГОд= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ГодВырезалиИзБуфераТабель");
        ПослеСозданиеовгоТабеляМЕсяц= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("МесяцВырезалиИзБуфераТабель");
        ПослеСозданиеовгоТабеляВместеГодИМесяц= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ПолученноеТекущееЗначениеСпинераДата");
        ПослеСозданияНовогоТабеляЕгоUUID= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("СгенерированныйUUIDДляНовогоТабеля");
        ПослеСозданияНовогоТабеляЕгоПолноеНазвание= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("СгенерированныйНазваниеНовогоТабеля");
            if (МесяцВСпинореНижней ==null) {
                МесяцВСпинореНижней = Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getStringExtra("ДепартаментТабеляПослеПодбораBACK");
            }
            РодительскийUUDТаблицыТабель= Интент_ПослеУспешноСозданогоНовогоТабеляПередаемСюдаЦифруМесяцаИЦифруГода.getLongExtra("РодительскийUUDТаблицыТабель",0l);

        Log.d(this.getClass().getName(), " ПослеСозданиеовгоТабеляГОд "+ПослеСозданиеовгоТабеляГОд+ " ПослеСозданиеовгоТабеляГОд " +ПослеСозданиеовгоТабеляГОд +
                " ПослеСозданиеовгоТабеляВместеГодИМесяц " +ПослеСозданиеовгоТабеляВместеГодИМесяц +
                "    ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + МесяцВСпинореНижней +
                " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре "+ МесяцВСпинореНижней +
                "  РодительскийUUDТаблицыТабель " +РодительскийUUDТаблицыТабель);
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
        // TODO: 01.09.2021 метод вызова
       this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



    @Override
    protected void onStart() {
        super.onStart();
        ///TODO попытка открыть экран как full screan
        ////////ЗАПОЛНЯЕМ АРАЙЛИСТ
        try {
            //TODO МЕТОД ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ ДАННОГО АКВТИВИ
            МетодПолучениеДанныхДляДаногоАктивтиИсторияТАбеля();
            ////todo заполение спинера
            МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля();////метод вызаваем все созжданные ТАБЕДЯ ИЗ БАПЗЫ И ДАЛЕЕ ИХ ЗАПИСЫВАЕМ В ОБМЕН

            МетодЗаполенияТабелямиАктивти();

            ////todo заполение спинера
            МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля();
            МетодДанныеСпинераДаты();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayoutСозданныхТабелей.forceLayout();
        LinearLayoutСозданныхТабелей.refreshDrawableState();
    }
///TODO СООБЩЕНИЕ О РЕЗУЛЬТАТОВ







































    ///todo  конец метода удаления третий обработчки нажатия
    ///////МЕТОД СОЗДАННИЕ СПИНЕРА

    ///todo сообщение
    @UiThread


    private Cursor МетодПолучениеДанныхДляИхУдаления(@NonNull Context context ,@NonNull Long СамоЗначениеUUID) {
        Cursor cursor=null;
        try{
            Bundle bundle=new Bundle();
            bundle.putString("СамЗапрос","  SELECT uuid FROM  data_tabels  WHERE uuid_tabel=?     AND status_send!=?");
            bundle.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(СамоЗначениеUUID),"Удаленная"});
            bundle.putString("Таблица","data_tabels");
            Intent intent=new Intent("ДляУдаление");
            intent.putExtras(bundle);
            Service_For_Public  service_for_public=new Service_For_Public();
        cursor=  service_for_public.МетодПолучениеДанныхЧерезCursorLoader(context,intent);
            Log.d(this.getClass().getName(), " cursor " + cursor);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursor;
    }


    ///todo сообщение
    @UiThread
    protected void СообщениеПослеУдаленияСамогоТАбеля(String ШабкаДиалога,  String СообщениеДиалога,boolean Статус, int НазваниеУдаляемогоТАбеляВЦифровомФормате) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ


//////сам вид
        int Значек;
        if (Статус){
            Значек  =R.drawable.icon_dsu1_tabel_info;
        }else{
            Значек  =R.drawable.icon_dsu1_delete_customer;
        }
        final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle(ШабкаДиалога)
                .setMessage(СообщениеДиалога)
                .setPositiveButton("ОК", null)
                .setIcon(Значек)
                .show();
/////////кнопка
        final Button MessageBoxUpdateСоздатьТабель = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
            ///MessageBoxUpdate метод CLICK для DIALOBOX

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //удаляем с экрана Диалог
                alertDialog.dismiss();
                Log.d(this.getClass().getName(), "  ФИНАЛ после удалание сотрудуника ");
                //////// todo если успешно удаление табеля то запускаем сообщение
                if (Статус) {
                    try {
                        onStart();
                        ScrollНаАктивтиСозданныхТабелей.forceLayout();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            }
        });
    }

    private void МетодСозданиеСпинераДляДатыНаАктивитиСозданиеИВыборТабеля() {
        try {
            ////TODO сортируем дату на ПОСЛЕДНКЮ
            Log.d(  getApplicationContext().getClass().getName(), " сработала ... ВСТАВКА МассивДляВыбораВСпинерДата В БАЗУ"+МассивДляВыбораВСпинерДата);
            if (Курсор_ДанныеДляСпинераДаты.getCount()>0) {/// && МассивДляВыбораВСпинерДата.size()>0
                if (МесяцВСпинореНижней !=null) {
                 Integer ИндексНахождение=   МассивДляВыбораВСпинерДата.indexOf(МесяцВСпинореНижней);
                    Log.d(  getApplicationContext().getClass().getName(), " ИндексНахождение "+ИндексНахождение);
                    if (ИндексНахождение>=0) {
                        Collections.swap(МассивДляВыбораВСпинерДата,0,ИндексНахождение);
                    }
                }

                Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " +ПолученноеЗначениеИзСпинераДата);
            }else{
                Log.d(this.getClass().getName(), " МассивДляВыбораВСпинерДата.size()  " + МассивДляВыбораВСпинерДата.size());
                // TODO: 21.09.2021  НЕТ ДАННЫХ  НЕТ ТАБЕЛЬ
                МассивДляВыбораВСпинерДата.add("Не созданно");
                МетодКогдаДанныхСамихТабелйНет(МассивДляВыбораВСпинерДата);
                ПолученноеЗначениеИзСпинераДата=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        /////МЕТОД ЗАГРУКЗИ КОЛИЧЕСТВО ТАБЕЛЕЙ ИЗ БАЗЫ\




    }

    private void МетодДанныеСпинераДаты( ) {
        try{
            ArrayAdapter<String>           АдаптерДляСпинераДата = new ArrayAdapter<String>(this, R.layout.simple_for_create_new_assintionmaterila_spinner_main, МассивДляВыбораВСпинерДата);
            АдаптерДляСпинераДата.setDropDownViewResource(R.layout.simple_for_create_new_assintionmaterila_spinner);
        СпинерВыборДату.setAdapter(АдаптерДляСпинераДата);
        СпинерВыборДату.setSelected(true);
        СпинерВыборДату.setSaveEnabled(true);
        СпинерВыборДату.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view!=null) {
                    if ((TextView) parent.getChildAt(0) != null) {
                        КакойКонтекст = Optional.ofNullable(String.valueOf(((TextView) parent.getChildAt(0)).getText())).map(String::new).orElse(" "); /////ОПРЕДЕЛЯЕМ ТЕКУЩЕЕ ЗНАЧЕНИЕ ВНУТИРИ СПЕНИРА
                        МесяцВСпинореНижней = КакойКонтекст;
                        //////TODO линия снизу самих табелей ЦВЕТ
                        if (КакойКонтекст != null) {
                            //((TextView) parent.getChildAt(0)).setBackgroundResource(R.drawable.textlines_tabel_row_color_green);
                            ((TextView) parent.getChildAt(0)).setTextSize(16);
                            ((TextView) parent.getChildAt(0)).startAnimation(animation);
                            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                            ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                            ((TextView) parent.getChildAt(0)).setTypeface(((TextView) parent.getChildAt(0)).getTypeface(), Typeface.BOLD);//////ВЫДЕЛЕМ ЖИРНЫМ ЦВЕТОМ ДАТЫ
                            Log.d(this.getClass().getName(), " ((TextView) parent.getChildAt(0)).getText()  " + LinearLayoutСозданныхТабелей.getChildAt(0));
                            ((TextView) parent.getChildAt(0)).setTag(РодительскийUUDТаблицыТабель);

                        }
                        Log.d(this.getClass().getName(), " КакойКонтекст" + КакойКонтекст + " ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре " + МесяцВСпинореНижней);
                        if (ПослеСозданиеовгоТабеляГОд != null && ПослеСозданиеовгоТабеляМЕсяц != null) {////заполянеться если новыйтабель создан и на при запуске встать на попределнный табель
                            ((TextView) parent.getChildAt(0)).setText(ПослеСозданиеовгоТабеляВместеГодИМесяц);//// ЗАПИСЫВАЕМ ЗНАЧЕНИЕ В СПИПЕР
                            ПослеСозданиеовгоТабеляГОд = null;
                            ПослеСозданиеовгоТабеляМЕсяц = null;
                        }
                    }
                }
                if (view!=null) {
                    ПолученноеЗначениеИзСпинераДата = (String) ((TextView) parent.getChildAt(0)).getText(); //ПОЛУЧАЕМ ЗНАЧЕНИЕ
                    ПолученноеЗначениеИзСпинераДата = (String) Optional.ofNullable(String.valueOf(((TextView) parent.getChildAt(0)).getText())).map(String::new).orElse(" ");
                    if(position==0){
                        ((TextView) parent.getChildAt(0)).setTypeface(null,Typeface.BOLD);
                    }
                }

               if (    ПолученноеЗначениеИзСпинераДата!=null && view !=null && Курсор_ДанныеДляСпинераДаты.getCount()>0) {
                   Log.d(this.getClass().getName(), " ((TextView) parent.getChildAt(0)).getText()  " + ((TextView) parent.getChildAt(0)).getText()+
                            " ПолученноеЗначениеИзСпинераДата "+ПолученноеЗначениеИзСпинераДата);
                       МетодаСозданиеТабеляИзБазы(); /////МЕТОД ЗАГРУЗКИ СОЗДАННЫХ ТАБЕЛЕЙ ИЗ БАЗ
                   }else{
                   ((TextView) parent.getChildAt(0)).setText("Не созданно");
                   ((TextView) parent.getChildAt(0)).forceLayout();
                   ((TextView) parent.getChildAt(0)).refreshDrawableState();
                   Log.d(this.getClass().getName(), " ((TextView) parent.getChildAt(0)).getText()  " + ((TextView) parent.getChildAt(0)).getText()+
                           " ПолученноеЗначениеИзСпинераДата "+ПолученноеЗначениеИзСпинераДата);

               }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата  " +ПолученноеЗначениеИзСпинераДата);
            }
        });
            if (Курсор_ДанныеДляСпинераДаты!=null) {
                if (МесяцВСпинореНижней ==null) {
                    СпинерВыборДату.setSelection(Курсор_ДанныеДляСпинераДаты.getCount()-1,true);
                }
            }
            СпинерВыборДату.refreshDrawableState();
            ScrollНаАктивтиСозданныхТабелей.forceLayout();
            ScrollНаАктивтиСозданныхТабелей.requestLayout();
            LinearLayoutСозданныхТабелей.requestLayout();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    ////TODO Метод Преоразует цифру в  названия месяца
    private String МетодДляПреоразванияЦифрыВНазванияМесяца(Cursor Курсор_ВЫводимМаксимальнуюДатуДляСпинера) {
        String  МаксимальнаяМесяцДляСпинера;
        String МаксимальнаяГодДляСпинера;
        String  МаксимальнаяНазваниеДляСпинера;
        String ПолученыеМесяцНеОбработанный = null;
        try{
            МаксимальнаяМесяцДляСпинера =Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(0);
            Log.i(this.getClass().getName(), "МаксимальнаяМесяцДляСпинера[0] " +   МаксимальнаяМесяцДляСпинера);
            МаксимальнаяГодДляСпинера =Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(1);
            Log.i(this.getClass().getName(), "МаксимальнаяГодДляСпинера[0] " + МаксимальнаяГодДляСпинера);
            МаксимальнаяНазваниеДляСпинера=Курсор_ВЫводимМаксимальнуюДатуДляСпинера.getString(2);
            Log.i(this.getClass().getName(), " МаксимальнаяНазваниеДляСпинера[0] " + МаксимальнаяНазваниеДляСпинера);
            DateFormat df = new SimpleDateFormat("MM/yyyy");
            Date date = df.parse(МаксимальнаяМесяцДляСпинера+"/"+МаксимальнаяГодДляСпинера);
            System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
                /*   SimpleDateFormat f = new SimpleDateFormat("MMM", new Locale("ru"));
                    SimpleDateFormat f1 = new SimpleDateFormat("LLL", new Locale("ru"));
                    SimpleDateFormat f2 = new SimpleDateFormat("MMMM", new Locale("ru"));*/
            SimpleDateFormat ПреобразованиеЦифраВНАзваниемесяца = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));
            ПолученыеМесяцНеОбработанный=  ПреобразованиеЦифраВНАзваниемесяца.format(date);
            System.out.println(ПолученыеМесяцНеОбработанный);
            ПолученыеМесяцНеОбработанный=(ПолученыеМесяцНеОбработанный.substring(0,1).toUpperCase()+
                    ПолученыеМесяцНеОбработанный.substring(1).toLowerCase());
            Log.i(this.getClass().getName(), " ПолученыеМесяцНеОбработанный " + ПолученыеМесяцНеОбработанный);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ПолученыеМесяцНеОбработанный;
    }

    private void МетодаСозданиеТабеляИзБазы()  {
        SQLiteCursor            Курсор_ПолучаемПубличныйID= null;
        SQLiteCursor Курсор_КоторыйЗагружаетГотовыеТабеля = null;
        SQLiteCursor         Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата= null;
      String МесяцМаскимальнаяДатавТабеляхПоМесецям = null;
        try{
            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
            if (ПолученноеЗначениеИзСпинераДата != null ) {
                try{
                    LinearLayoutСозданныхТабелей.removeAllViews();
                } catch (Exception e) {
                 /*   e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());*/
                }

                Log.d(this.getClass().getName(), " код загружает все созданные табеля из базы " + КакойКонтекст);
                //////TODO преобразовыываем оборатно из названиея месяц в цифру
                МЕсяцВвидеЦифрыДляКурсора= МетодПолучениниеКурсораМЕсяцДата(ПолученноеЗначениеИзСпинераДата);
                ////
                ГОДВвидеЦифрыДляКурсора=МетодПолучениниеКурсораГОДДата(ПолученноеЗначениеИзСпинераДата);
                /////
                Log.d(this.getClass().getName(), " МЕсяцВвидеЦифрыДляКурсора " + МЕсяцВвидеЦифрыДляКурсора +
                        "  ГОДВвидеЦифрыДляКурсора " + ГОДВвидеЦифрыДляКурсора);
                try {
                    Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                    if (ПубличноеIDПолученныйИзСервлетаДляUUID==0) {
                        class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
                        class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                                " SELECT id  FROM successlogin  ORDER BY date_update DESC ;");
                        // TODO: 12.10.2021  Ссылка Менеджер Потоков
                        Курсор_ПолучаемПубличныйID= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                        new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsДляАктивтиТабель.
                                concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                        Log.d(this.getClass().getName(), " Курсор_ПолучаемПубличныйID  " + Курсор_ПолучаемПубличныйID);
                        if(Курсор_ПолучаемПубличныйID.getCount()>0){
                            Курсор_ПолучаемПубличныйID.moveToFirst();
                            ПубличноеIDПолученныйИзСервлетаДляUUID=         Курсор_ПолучаемПубличныйID.getInt(0);
                            Log.d(this.getClass().getName(), " ПубличноеIDПолученныйИзСервлетаДляUUID  " + ПубличноеIDПолученныйИзСервлетаДляUUID);
                        }
                        if(Курсор_ПолучаемПубличныйID != null && !Курсор_ПолучаемПубличныйID.isClosed()) {
                            Курсор_ПолучаемПубличныйID.close();
                        }
                    }
                    Log.d(this.getClass().getName(), "ПолученнаяUUIDНазванияОрганизации "+ ПолученнаяUUIDНазванияОрганизации
                            + " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID
                            + " МЕсяцВвидеЦифрыДляКурсора " +МЕсяцВвидеЦифрыДляКурсора
                            + " ГОДВвидеЦифрыДляКурсора  "+ ГОДВвидеЦифрыДляКурсора);
                    ///TODO ГЛАВНЫЙ КУРСОР ЗАГРУЗКИ САМИХ ТАБЕЛЕЙ НА АКТИВТИИ
                    if ( МЕсяцВвидеЦифрыДляКурсора>0 && ГОДВвидеЦифрыДляКурсора>0 ) {
                        //////////
                        Log.d(this.getClass().getName(), "ПолученнаяUUIDНазванияОрганизации "+ ПолученнаяUUIDНазванияОрганизации
                                + " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID
                                + " МЕсяцВвидеЦифрыДляКурсора " +МЕсяцВвидеЦифрыДляКурсора
                                + " ГОДВвидеЦифрыДляКурсора  "+ ГОДВвидеЦифрыДляКурсора);
                        String    НазваниеТабеля;
                        try{
                               int  finalМЕсяцВвидеЦифрыДляКурсора=МЕсяцВвидеЦифрыДляКурсора;
                             int  finalГОДВвидеЦифрыДляКурсора=ГОДВвидеЦифрыДляКурсора;
                            class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","tabel");
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","month_tabels=? " +
                                    "AND year_tabels=? AND status_send!=? AND month_tabels IS NOT NULL  AND year_tabels IS NOT NULL");
                            ///"_id > ?   AND _id< ?"
                            //////
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalМЕсяцВвидеЦифрыДляКурсора);
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",finalГОДВвидеЦифрыДляКурсора);
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3","Удаленная");////УсловиеПоискаv4,........УсловиеПоискаv5 .......
                            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update");
                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                             Курсор_КоторыйЗагружаетГотовыеТабеля= (SQLiteCursor)  class_grud_sql_operationsДляАктивтиТабель.
                                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                                     Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                            Log.d(this.getClass().getName(), "GetData "+Курсор_КоторыйЗагружаетГотовыеТабеля  );
                                    //////todo работающий NULL в query
                                    Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеТабеля.getCount() " +    Курсор_КоторыйЗагружаетГотовыеТабеля.getCount());
                                    ////todo получаем значение ищем
                              Log.d(this.getClass().getName(), "ЗАГРУЗИЛИ ДАННЫЕ НА АКТИВТИ ИСТОРИЯ ТАБЕЛЯЕЙ  Курсор_КоторыйЗагружаетГотовыеТабеля.getCount() "
                                      + Курсор_КоторыйЗагружаетГотовыеТабеля.getCount());
                                  if (   Курсор_КоторыйЗагружаетГотовыеТабеля.getCount()>0) {
                                         textViewКоличествоТабелей.setText(" ("+СпинерВыборДату.getCount()+" м) "+ " ("+ String.valueOf(Курсор_КоторыйЗагружаетГотовыеТабеля.getCount())+" т)")  ;
                                     } else {
                                         textViewКоличествоТабелей.setText("("+"0"+")");
                                     }
                            Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата=null;
                            //TODO ГЛАВНЫЙ КУРСОР ЗАГРУЗКИ САМИХ ТАБЕЛЕЙ НА АКТИВТИИ"nametabel"
                            Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата = МетодКоторыйПоказываетМаксимальнуюДатуИзменения(ПолученнаяUUIDНазванияОрганизации);
                            // TODO: 02.09.2021 курсорсы
                            if (Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.getCount()>0) {
                                Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.moveToFirst();
                                Integer ИндексГлеНАходитьсяДата=Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.getColumnIndex("MAX_d");
                                String   МаскимальнаяДатавТабеляхПоМесецям = Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.getString(ИндексГлеНАходитьсяДата);
                                Log.d(this.getClass().getName(), " МаскимальнаяДатавТабеляхПоМесецям  " +    МаскимальнаяДатавТабеляхПоМесецям
                                        + " МесяцМаскимальнаяДатавТабеляхПоМесецям  " + МесяцМаскимальнаяДатавТабеляхПоМесецям +  "  Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.getCount() "
                                        +Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.getCount());
                            }else{
                                МесяцМаскимальнаяДатавТабеляхПоМесецям ="";
                            }
                            if(!Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.isClosed()) {
                                Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                              new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                             this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                           new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

                if (Курсор_КоторыйЗагружаетГотовыеТабеля.getCount() > 0) {/////ЕСЛИ ЕСТЬХОТЯБЫ ОДИН ТАБЕЛЬ
                    Курсор_КоторыйЗагружаетГотовыеТабеля.moveToFirst();
                    Log.d(this.getClass().getName(), " Курсор_КоторыйЗагружаетГотовыеТабеля " + Курсор_КоторыйЗагружаетГотовыеТабеля.getCount());
                    final int[] ИндексДляСозданныхОбьектовНаАктивитиТАбель = {0};
                    ///todo удалчем элемены перед новой вставкой обтьектов на активти
                    try{
                        LinearLayoutСозданныхТабелей.removeAllViews();/////удалем данные с актиывти
                        LinearLayoutСозданныхТабелей.forceLayout();
                    } catch (Exception e) {
                    }


                    // TODO: 15.12.2022  ПОЛУЧАЕМ ДАННЫМ ИДЕМ ПО ЦИКЛУ

                    do {
                        int ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ= Курсор_КоторыйЗагружаетГотовыеТабеля.getColumnIndex("cfo");
                        // TODO: 10.06.2021
                       ПолучеаемЦифруСФО= Курсор_КоторыйЗагружаетГотовыеТабеля.getInt(ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ);
                        Log.d(this.getClass().getName()," ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ  "+ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ+" ПолучеаемЦифруСФО " +ПолучеаемЦифруСФО);

                        String    НазваниеТабеля=        new Class_MODEL_synchronized(context).МетодПолучениеНазваниеТабеляНаОснованииСФО(context,ПолучеаемЦифруСФО);
                            Log.d(context.getClass().getName(), "    НазваниеТабеля[0] " +   НазваниеТабеля);


                            МесяцМаскимальнаяДатавТабеляхПоМесецям =     new Class_MODEL_synchronized(context).
                                    МетодПолучениеНазваниеТабеляНаОснованииСФО(context,ПолучеаемЦифруСФО);
                            Log.d(context.getClass().getName(), "   МесяцМаскимальнаяДатавТабеляхПоМесецям " +     ////////
                                    МесяцМаскимальнаяДатавТабеляхПоМесецям);

                        int ИндексДата= Курсор_КоторыйЗагружаетГотовыеТабеля.getColumnIndex("date_update");
                       String ДатаТабеляИзБАзы= Курсор_КоторыйЗагружаетГотовыеТабеля.getString(ИндексДата);
                        //todo перерводим в дату для СРАВНЕНИЯ
                        //TODO статус табеля
                        Integer   СамСтатусАтбеля =     МетодВЫчиляемСтатусТабеляПроведенИлиНет(МЕсяцВвидеЦифрыДляКурсора,ГОДВвидеЦифрыДляКурсора,ПолучеаемЦифруСФО);
                        Log.d(this.getClass().getName(), " НазваниеТабеля " + НазваниеТабеля + " ДатаТабеляИзБАзы " + ДатаТабеляИзБАзы + " СамСтатусАтбеля " +СамСтатусАтбеля);
                        ТабелявВидеКнопок = new Button(this);////СОЗДАЕМ НОВЫЕ КНОПКИ НА АКТИВТИ
                        ТабелявВидеКнопок.setAnimation(animation);
                        // TODO: 15.12.2022  UUID
                        int ИндексГотовогоUUID= Курсор_КоторыйЗагружаетГотовыеТабеля.getColumnIndex("uuid");
                        Long    НепостредственоеЗначениеUUIDСозданогоТАбеля = Курсор_КоторыйЗагружаетГотовыеТабеля.getLong(ИндексГотовогоUUID);
                        // TODO: 15.12.2022
                        ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ= Курсор_КоторыйЗагружаетГотовыеТабеля.getColumnIndex("cfo");
                        int НепостредственоеЗначениеИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ = 0;
                        ///////TODO вычисляем
                        if (ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ>=0){
                            НепостредственоеЗначениеИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ = Курсор_КоторыйЗагружаетГотовыеТабеля.getInt(ИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ);
                            ЦифровоеИмяНовгоТабеля=НепостредственоеЗначениеИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ;
                        }
                        ////todo когда данные в табелй есть  САМИ ДАННЫЕ ТАБЕЛЕЙ ЗАГРУЖАЮТЬСЯ
                        //ТабелявВидеКнопок.setHint(НепостредственоеЗначениеИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ);
                        ТабелявВидеКнопок.setTag(НепостредственоеЗначениеUUIDСозданогоТАбеля.toString());
                        ТабелявВидеКнопок.setId(НепостредственоеЗначениеИндексГдеНаходитьсяЭлектронноеИмяВТАБЕЛЕ);
                        ТабелявВидеКнопок.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        ТабелявВидеКнопок.setMinLines(8);
                        ТабелявВидеКнопок.setTextSize(13);
                        ТабелявВидеКнопок.setHintTextColor(Color.RED);
                        ТабелявВидеКнопок.setText(НазваниеТабеля);
                        ТабелявВидеКнопок.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        ТабелявВидеКнопок.setTextColor(Color.BLACK);
                        ТабелявВидеКнопок.setHintTextColor(Color.RED);

                       

    //TODO добвлем галочку
                        Log.d(this.getClass().getName(), " СамСтатусАтбеля "+СамСтатусАтбеля);
                            if (СамСтатусАтбеля>0){ ///"пр"
                                Drawable icon = getResources().getDrawable(R.mipmap.icon_dsu1_tabels_provedennye);
                                icon.setBounds(0, 2, 100, 100);
                              //  ТабелявВидеКнопок.setPadding (0,0, 0, 150);
                                ТабелявВидеКнопок.setCompoundDrawables(icon, null, null, null);

                            }
                        ТабелявВидеКнопок.setBackground(getApplication().getResources().getDrawable(R.drawable.textlines_tabel_row_color_green_mini));

                        if (  МесяцМаскимальнаяДатавТабеляхПоМесецям.trim().equals(НазваниеТабеля.trim())) {
                            LinearLayoutСозданныхТабелей.addView(ТабелявВидеКнопок, 0); /////СОЗДАЕМ НАКШИ КНОПКИ ВНУРИ СКРОЛБАР
                        }else{
                            //TODO  ОЧИЩАЕМ ПАМТЬ
                            LinearLayoutСозданныхТабелей.addView(ТабелявВидеКнопок, ИндексДляСозданныхОбьектовНаАктивитиТАбель[0]); /////СОЗДАЕМ НАКШИ КНОПКИ ВНУРИ СКРОЛБАР
                        }



                        // TODO: 16.02.2023 Слушатели Табелей
                        МетодыСлушателиТабелей();

                        ИндексДляСозданныхОбьектовНаАктивитиТАбель[0]++;///увеличиваем
                        /////odo ЦИКЛ ЗАГРУЗКИ ТОЛЬКО НАЗВАНИЙ ТАБЕЛЯ
                    } while (Курсор_КоторыйЗагружаетГотовыеТабеля.moveToNext());
                    /////toto ПОСЛЕ ВСТАВКИ ДАННЫХ ПЕРЕОПРЕДЕЛЯЕМ ВНЕГНИЙ ВИДЖ
                    if(!Курсор_КоторыйЗагружаетГотовыеТабеля.isClosed()) {
                        Курсор_КоторыйЗагружаетГотовыеТабеля.close();
                    }
///todo  когда в табеле нет сотрудников ПУСТОЙ ТАБЕЛЬ
                }
                ////TODO ПОСЛЕ ЗАПОЛЕНЕИЯ ТАБЕЛЯ В АКТИВИТИ
                LinearLayoutСозданныхТабелей.forceLayout();
                ScrollНаАктивтиСозданныхТабелей.forceLayout();
                ScrollНаАктивтиСозданныхТабелей.fullScroll(View.FOCUS_UP);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодыСлушателиТабелей() {
        ТабелявВидеКнопок.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try{
                    Log.d(this.getClass().getName(), "   ТабелявВидеКнопок.setOnLongClickListener "+v);
                     String UUIDТабеляВременный=v.getTag().toString();
                    Long СамUUIDТабеля= Long.parseLong(UUIDТабеляВременный);
                    String НазваниеУдаляемогоТабеля= ((TextView)v).getText().toString().trim();
                    Log.d(this.getClass().getName(), "   ТабелявВидеКнопок.setOnLongClickListener "+v);
                    ///
                    МетодУдалениеТАбеляСообщениеПередЭтим(СамUUIDТабеля, НазваниеУдаляемогоТабеля,v);

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
                return false;
            }
        });
        ////////
        ТабелявВидеКнопок.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Log.d(this.getClass().getName(), "     ТабелявВидеКнопок.setOnClickListener ");
                    ((Button) v).setBackgroundColor(Color.GRAY);
                    МетодПереходимВТабельСотрудники((Button) v);
/////TODO одинатрный клик для загрузки в этот табель всех сотрудников
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
    }

    /////TODO метод запуска кода при однократорм нажатии просто загузка сотрудников табель
    private void МетодПереходимВТабельСотрудники(Button v) {
        try{
            Object ПолучениеДанныеИзСпинера   =   СпинерВыборДату.getSelectedItem();
            TextView textView   =  (TextView) СпинерВыборДату.getChildAt(0);
            МесяцВСпинореНижней =textView.getText().toString();

            Intent ИнтентЗапускаемСуществующийТабель=new Intent(v.getContext(), MainActivity_List_Peoples.class);//getApplicationContext()
            String ПередаемСозданнуюДатуНовогоТабеля = (String) ((TextView) СпинерВыборДату.getChildAt(0)).getText();///дата нового табеля
            Button ИзКнопкиПолучаемНазваниеТабеля = v;
            String ПередаемСозданнуюНазваниеТабеля = ИзКнопкиПолучаемНазваниеТабеля.getText().toString();
            Log.d(this.getClass().getName(), " ПередаемСозданнуюНазваниеТабеля  " +ПередаемСозданнуюНазваниеТабеля+ textView.getTag());
            ///////todo ВЫТАСКИЕВАЕМ НАЗВАНИЕ ТАБЕЛЯ
            Object ПередаваемыйИзКнопкиПолучаемUUIDТабеля = ИзКнопкиПолучаемНазваниеТабеля.getTag();
            Log.d(this.getClass().getName(), " ПередаваемыйИзКнопкиПолучаемUUIDТабеля  " +ПередаваемыйИзКнопкиПолучаемUUIDТабеля);
            ПубличноеИмяКнопкиТабеля=  ТабелявВидеКнопок.getText().toString();
            Button ИзКнопкиПолучаемЦифровоеИмяТабеля = v;
            Object ПередаваемыйИзКнопкиПолучаемЦифровоеИмяТабеля = ИзКнопкиПолучаемНазваниеТабеля.getId();
            ЦифровоеИмяНовгоТабеля= Integer.parseInt(ПередаваемыйИзКнопкиПолучаемЦифровоеИмяТабеля.toString());
            Log.d(this.getClass().getName(), " ЦифровоеИмяНовгоТабеля  " +ЦифровоеИмяНовгоТабеля);
            int ПоискФлеша = ПередаемСозданнуюНазваниеТабеля.indexOf("\n") + 1;
            StringBuffer БуферДляПоискаФлешаДляГотовогоТабеля = new StringBuffer(ПередаемСозданнуюНазваниеТабеля);
            String ПередаемДепартаментФинал = БуферДляПоискаФлешаДляГотовогоТабеля.substring(ПоискФлеша, БуферДляПоискаФлешаДляГотовогоТабеля.length());
            МесяцТабеляФиналИзВсехСотрудниковВТАбеле  =МассивДляВыбораВСпинерДата.get(0);
            /////////todo отпраялем данные значения на активти где все сотрудникаи вконкретном табеле
            ИнтентЗапускаемСуществующийТабель.putExtra("ПередаемСозданнуюДатуНовогоТабеля", String.valueOf(ПередаемСозданнуюДатуНовогоТабеля));
            ИнтентЗапускаемСуществующийТабель.putExtra("ПередаемДепартаментФинал", ПередаемДепартаментФинал);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолноеНазваниеТабеляФинал", ПередаемСозданнуюНазваниеТабеля);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПередаваемыйИзКнопкиПолучаемUUIDТабеля", String.valueOf(ПередаваемыйИзКнопкиПолучаемUUIDТабеля));
            ИнтентЗапускаемСуществующийТабель.putExtra("РодительскийUUDТаблицыТабель", ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString());
            ИнтентЗапускаемСуществующийТабель.putExtra("UUIDТабеляФинал", ПередаваемыйИзКнопкиПолучаемUUIDТабеля.toString());
            ИнтентЗапускаемСуществующийТабель.putExtra("МесяцТабеляФиналИзВсехСотрудниковВТАбеле", String.valueOf( МесяцТабеляФиналИзВсехСотрудниковВТАбеле));
            ИнтентЗапускаемСуществующийТабель.putExtra("ГодТабеляФиналИзВсехСотрудниковВТАбеле", String.valueOf( ГОДВвидеЦифрыДляКурсора));
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолученнаяUUIDНазванияОрганизации",ПолученнаяUUIDНазванияОрганизации);
            ИнтентЗапускаемСуществующийТабель.putExtra("ЦифровоеИмяНовгоТабеля",ЦифровоеИмяНовгоТабеля);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", МесяцВСпинореНижней);
            ИнтентЗапускаемСуществующийТабель.putExtra("ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре", МесяцВСпинореНижней);
            Log.d(this.getClass().getName(), "  ПередаваемыйИзКнопкиПолучаемUUIDТабеля " + ПередаваемыйИзКнопкиПолучаемUUIDТабеля+
                    "  РодительскийUUDТаблицыТабель " + РодительскийUUDТаблицыТабель);
            ИнтентЗапускаемСуществующийТабель.putExtra("ДепартаментТабеляПослеПодбораBACK", МесяцВСпинореНижней);
            Log.d(this.getClass().getName(), "  ПолучениеДанныеИзСпинера " + ПолучениеДанныеИзСпинера+" textView "+textView.getText().toString());
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            ИнтентЗапускаемСуществующийТабель.putExtras(data);
            startActivity(ИнтентЗапускаемСуществующийТабель);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 17.06.2021 вычиляем статус табеля
    private int МетодВЫчиляемСтатусТабеляПроведенИлиНет(int finalМЕсяцВвидеЦифрыДляКурсора ,int finalГОДВвидеЦифрыДляКурсора ,int ПолучеаемЦифруСФО ) {
        Integer ПолученныйСтатусТабеля=0;
        SQLiteCursor Курсор_КоторыйЗагружаетСтатусТабеля = null;
        Integer ПолученныйСтатусВнутри = null;
   try{
       class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","viewtabel");//tabels
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","status_carried_out");//status_carried_out
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," month_tabels=? AND year_tabels=?  AND cfo=?  AND status_send!=?   ");//   AND status_send!=?   AND status_carried_out IS NOT NULL
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",finalМЕсяцВвидеЦифрыДляКурсора);
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска2",finalГОДВвидеЦифрыДляКурсора);
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска3",ПолучеаемЦифруСФО);////УсловиеПоискаv4,........УсловиеПоискаv5 .......
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска4","Удаленная");////УсловиеПоискаv4,........УсловиеПоискаv5 .......
       class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ПоляГрупировки","cfo");
       PUBLIC_CONTENT         Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT (getApplicationContext());
       // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
       Курсор_КоторыйЗагружаетСтатусТабеля= (SQLiteCursor)  class_grud_sql_operationsДляАктивтиТабель.
               new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
               Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
       Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйЗагружаетСтатусТабеля );
               if( Курсор_КоторыйЗагружаетСтатусТабеля.getCount()>0){
                   Курсор_КоторыйЗагружаетСтатусТабеля.moveToNext();
               ПолученныйСтатусВнутри=Курсор_КоторыйЗагружаетСтатусТабеля.getInt(0);
                   if(ПолученныйСтатусВнутри==null){
                       ПолученныйСтатусВнутри=0;
                   }
               }else{
                   ПолученныйСтатусВнутри=0;
               }
       Log.d(this.getClass().getName(), " ПолученныйСтатусВнутри  "+ПолученныйСтатусВнутри);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
   return ПолученныйСтатусТабеля;
    }












    //////TODO вычисляем максимальную дату для LISTVIEW

    SQLiteCursor МетодКоторыйПоказываетМаксимальнуюДатуИзменения(Long полученнаяUUIDНазванияОрганизации) throws ExecutionException, InterruptedException {
     SQLiteCursor Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата = null;
                try{
                    String ТаблицаДляТекущейООперации="tabel";
                    class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
                    class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СамFreeSQLКОд",
                            " SELECT MAX (   date_update  ) AS MAX_d  FROM  " + ТаблицаДляТекущейООперации + "   " +
                                    " WHERE  month_tabels = '" + МЕсяцВвидеЦифрыДляКурсора + "'   AND year_tabels = '" + ГОДВвидеЦифрыДляКурсора + "'  AND status_send  != 'Удаленная'   ORDER BY date_update   DESC  ;");

                    PUBLIC_CONTENT         Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT (getApplicationContext());
                    // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                    Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата=null;

                    // TODO: 26.10.2021



                Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                            new GetаFreeData(getApplicationContext()).getfreedata(class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());

                    Log.d(this.getClass().getName(), "GetаFreeData "  +Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата);



                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

        return Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДата;
    }

//////

















    //////TODO вычисляем максимальную дату для СПИНЕРА ДЛЯ ВАДАПТЕРА AAARYADAPTER

    SQLiteCursor МетодКоторыйПоказываетМаксимальнуюДатуИзмененияДляСпинера() throws ExecutionException, InterruptedException {
         SQLiteCursor  Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера = null;
                try{
                    // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
                    String ТаблицаНазваниеОбработки="tabel";
                    class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы",ТаблицаНазваниеОбработки);
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","month_tabels,year_tabels");
                    class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика"," status_send!=?");
                    class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1","Удаленная");
                    class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","current_table ");//DESC
                    ////
                    PUBLIC_CONTENT         Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT (getApplicationContext());
                    Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                            new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
                    Log.d(this.getClass().getName(), "GetData " +Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
        return Курсор_КоторыйЗагружаетГотовыеТабеляМаксимальнаяДатаДляСпинера;
    }
     void МетодКогдаДанныхСамихТабелйНет( @NotNull LinkedList<String> МассивДляВыбораВСпинерДата) {
        try{
            ТабелявВидеКнопок = new Button(this);////СОЗДАЕМ НОВЫЕ КНОПКИ НА АКТИВТИ
            try {
                LinearLayoutСозданныхТабелей.removeAllViews();
            } catch (Exception e) {
               // e.printStackTrace();
            }
                ТабелявВидеКнопок.setTag("*В этом месяце нет Табеля  " +"(создайте).*");
                ТабелявВидеКнопок.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                ТабелявВидеКнопок.setMinLines(10);
                ТабелявВидеКнопок.setTextSize(14);
                ТабелявВидеКнопок.setText("*В этом месяце нет Табеля  " +"(создайте).*");
                ТабелявВидеКнопок.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                ТабелявВидеКнопок.setTextColor(Color.GRAY);//parseColor("#00ACC1"));
                ТабелявВидеКнопок.setHintTextColor(Color.GRAY);
               // ТабелявВидеКнопок.setBackground(this.getResources().getDrawable(R.drawable.style_forpolymaterialb_cycle_createspinner));
            ТабелявВидеКнопок.setBackground(getApplication().getResources().getDrawable(R.drawable.textlines_tabel_row_color_green_mini));

            СпинерВыборДату=(Spinner) findViewById(R.id.СпинерТабельМесяцИсториииТабелей);
            ArrayAdapter<String> АдаптерДляСпинераДата =
                    new ArrayAdapter<String>(this, R.layout.simple_for_create_null_tabels_, МассивДляВыбораВСпинерДата);
            АдаптерДляСпинераДата.setDropDownViewResource(R.layout.simple_for_create_new_assintionmaterila_spinner);
            СпинерВыборДату.setAdapter(АдаптерДляСпинераДата);

            СпинерВыборДату.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    Log.d(this.getClass().getName()," parent " +parent+" month " +view+" dayOfMonth " +view  +  "  position " +position);

                    if ((TextView) parent.getChildAt(0)!=null) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                        ((TextView) parent.getChildAt(0)).setTypeface(((TextView) parent.getChildAt(0)).getTypeface(), Typeface.BOLD);
                        ((TextView) parent.getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            АдаптерДляСпинераДата.notifyDataSetChanged();
            LinearLayoutСозданныхТабелей.addView(ТабелявВидеКнопок);
            LinearLayoutСозданныхТабелей.forceLayout();
        } catch (Exception e) {
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }































    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ
    /////МЕТОД СОЗДАНИЕ ДАТЫ И КАЛЕНДАРЯ

    private void МетодСозданиеДиалогаКалендаряДаты() {///////метод создание календяря даты
/////TODO тут визуализикуеться КАЛЕНДАРЬ
        try{
            final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
            Calendar newDate = Calendar.getInstance();
            //TODODATA
            DatePickerDialog ДатаДляКалендаря =
                    new DatePickerDialog(this, android.R.style.Theme_Holo_InputMethod , new DatePickerDialog.OnDateSetListener() {////Theme_Holo_Dialog_MinWidth  //Theme_Holo_Panel
                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate.set(year, monthOfYear, dayOfMonth);
                    Log.d(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата + " view "+view);
                    try {
                        view.setBackgroundColor(Color.RED);
                        view.animate().rotationXBy(5);
                        view.animate().rotationX(10);
                    String ФинальныйПолученаяДата=DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(newDate.getTime());
                    Log.d(this.getClass().getName()," year " +year+" month " +monthOfYear+" dayOfMonth " +dayOfMonth  +  "  ФинальныйПолученаяДата " +ФинальныйПолученаяДата);
                    // TODO: 22.09.2021 после того как мы получил даты запускаме сомо приложения
                        String МесяцИзКолендаря = String.valueOf(monthOfYear + 1);////ТЕКУЩИЙ МЕСЯЦ ИЗ КАЛЕНДАРЯ
                        if (МесяцИзКолендаря.length() == 2) {

                            ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
                            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
                        } else {
                            ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + "0" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
                            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
                        }
                        Date ПрасингДаты = new Date();
                        if (ПолученноеЗначениеИзСпинераДата!=null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                ПрасингДаты = new android.icu.text.SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);
                            }else {
                                ПрасингДаты = new SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);
                            }
                            Log.d(this.getClass().getName()," ПрасингДаты " +ПрасингДаты.toString());
                            ///////получаем значение месца на руском через метод дата
                            ПолученноеЗначениеИзТолькоСпинераДата = МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(ПрасингДаты);
                            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата);
                            /////вАЖНО ЗАПИСЫВАЕМ ОБРАТНО В СПИНЕР НА РАБОЧИЙ СТОЛ АКТИВТИ НАПРИМЕР НОВЫЙ МЕСЯЦ  ОКТЯБРЬ 2020 ГОДА НАПРИМЕР
                            Log.d(this.getClass().getName(),"  ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
                        }
/////////////ТУТ КРУТИМ ВЕСЬ КУРСОР  И ПЫТАЕМСЯ НАЙТИ ЗНАЧЕНИЕ ВНЕМ  И ПО РЕЗУЛЬТАТ ЗАПОЛЯЕМ ЕГО В STRINGBUGGER
                        ////TODO ТУТ МЫ КРУТИМ ВЕСЬ СПИНЕР В КОТРЫЙ ИЗ БАЗЫ ЗАГРУЗИЛОСЬ ВСЕ СОЗДАННЫЕ МЕСЯЦА ИМЫ ПРОВЕРЕМ ЕЛСИ ТАКОМ МЕСЯЦ ЕЩН ИЛИ НЕТ
                        StringBuffer ИщемУжеСозданныйМЕсяц=new StringBuffer();
                        if (СпинерВыборДату!=null) {
                            for (int ИндексСуществуюЩимМесяц=1;ИндексСуществуюЩимМесяц<СпинерВыборДату.getCount();ИндексСуществуюЩимМесяц++){
                                ////todo ДА ПРОСТО ЗАПОЛЯНЕМ БУФЕР УЖЕ СОЗДАННЫМИ МЕСЯЦАМИ В СПИНЕРЕ
                                ИщемУжеСозданныйМЕсяц.append(СпинерВыборДату.getItemAtPosition(ИндексСуществуюЩимМесяц).toString()).append("\n");
                                Log.d(this.getClass().getName()," ИщемУжеСозданныйМЕсяц " +ИщемУжеСозданныйМЕсяц.toString()+"\n");
                            }
                        }else{
                               if(ПолученноеЗначениеИзТолькоСпинераДата!=null){
                                   ИщемУжеСозданныйМЕсяц.append(ПолученноеЗначениеИзТолькоСпинераДата)  ;
                               }else {
                                   Toast.makeText(getApplicationContext(), " Нет месяца для создание Табеля !!! ", Toast.LENGTH_LONG).show();
                               }
                        }
                        ///// todo ТУТ ВСТАВЛЯЕМ ММЕСЯЦА УКТОРГНО НЕТ ЕШЕ
                        Log.d(this.getClass().getName()," ИщемУжеСозданныйМЕсяц " +ИщемУжеСозданныйМЕсяц.toString()+"\n"+ " ПолученноеЗначениеИзТолькоСпинераДата " +ПолученноеЗначениеИзТолькоСпинераДата);
                        // TODO: 26.10.2021 метод создания новго табеля
                       МетодВставкиНовогоМесяцавТабельКоторогоНет(ИщемУжеСозданныйМЕсяц);
                    ////
                } catch (Exception e) {
                    e.printStackTrace();
                    ///метод запись ошибок в таблицу
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }

            }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            ДатаДляКалендаря.setTitle("Календарь");
            ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_POSITIVE, "Создать", ДатаДляКалендаря);
            ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Закрыть", ДатаДляКалендаря);
            ДатаДляКалендаря.show();
            ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
            ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
            ///ДатаДляКалендаря.getActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////
            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата);
        ////
    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



























    //////////
//TODO метод который и создает календарь после нажатие на кнопку ОК

/*
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        try{

           /// МетодСозданиеДиалогаКалендаряДаты();

       Calendar calendar=Calendar.getInstance();
       ///
            calendar.set(Calendar.YEAR,year);
            ///
            calendar.set(Calendar.MONTH,month);
            ///
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            /////
            String ФинальныйПолученаяДата=DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());


            Log.d(this.getClass().getName()," year " +year+" month " +month+" dayOfMonth " +dayOfMonth  +  "  ФинальныйПолученаяДата " +ФинальныйПолученаяДата);



        String МесяцИзКолендаря = String.valueOf(month + 1);////ТЕКУЩИЙ МЕСЯЦ ИЗ КАЛЕНДАРЯ


            ПолученноеЗначениеИзСпинераДата=null;



        if (МесяцИзКолендаря.length() == 2) {

            ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
        } else {
            ПолученноеЗначениеИзСпинераДата = dayOfMonth + "-" + "0" + МесяцИзКолендаря + "-" + year;////ДАННОЕ ЗНАЧЕНИЕ ПЕРЕДАЕМ НА ВСЕ ПРОГРАММУ В ДАЛЬНЕЙШЕМ
            Log.d(this.getClass().getName(), " ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
        }

        Date ПрасингДаты = new Date();



       //   TimeUnit.MILLISECONDS.sleep(200);


            if (ПолученноеЗначениеИзСпинераДата!=null) {
                ////////
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    ПрасингДаты = new android.icu.text.SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);

                }else {

                    ПрасингДаты = new SimpleDateFormat("dd-MM-yyyy", new Locale("ru")).parse(ПолученноеЗначениеИзСпинераДата);


                }
                Log.d(this.getClass().getName()," ПрасингДаты " +ПрасингДаты.toString());


                ///////получаем значение месца на руском через метод дата
                ПолученноеЗначениеИзТолькоСпинераДата = МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(ПрасингДаты);

                Log.d(this.getClass().getName(), " ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата);

                /////вАЖНО ЗАПИСЫВАЕМ ОБРАТНО В СПИНЕР НА РАБОЧИЙ СТОЛ АКТИВТИ НАПРИМЕР НОВЫЙ МЕСЯЦ  ОКТЯБРЬ 2020 ГОДА НАПРИМЕР

                Log.d(this.getClass().getName(),"  ПолученноеЗначениеИзСпинераДата" + ПолученноеЗначениеИзСпинераДата);
            }


/////////////ТУТ КРУТИМ ВЕСЬ КУРСОР  И ПЫТАЕМСЯ НАЙТИ ЗНАЧЕНИЕ ВНЕМ  И ПО РЕЗУЛЬТАТ ЗАПОЛЯЕМ ЕГО В STRINGBUGGER
        ////TODO ТУТ МЫ КРУТИМ ВЕСЬ СПИНЕР В КОТРЫЙ ИЗ БАЗЫ ЗАГРУЗИЛОСЬ ВСЕ СОЗДАННЫЕ МЕСЯЦА ИМЫ ПРОВЕРЕМ ЕЛСИ ТАКОМ МЕСЯЦ ЕЩН ИЛИ НЕТ



        StringBuffer ИщемУжеСозданныйМЕсяц=new StringBuffer();

        if (СпинерВыборДату!=null) {

            for (int ИндексСуществуюЩимМесяц=0;ИндексСуществуюЩимМесяц<СпинерВыборДату.getCount();ИндексСуществуюЩимМесяц++){

                ////todo ДА ПРОСТО ЗАПОЛЯНЕМ БУФЕР УЖЕ СОЗДАННЫМИ МЕСЯЦАМИ В СПИНЕРЕ
                ИщемУжеСозданныйМЕсяц.append(СпинерВыборДату.getItemAtPosition(ИндексСуществуюЩимМесяц).toString()).append("\n");

                Log.d(this.getClass().getName()," ИщемУжеСозданныйМЕсяц " +ИщемУжеСозданныйМЕсяц.toString()+"\n");

            }
        }




        ///// todo ТУТ ВСТАВЛЯЕМ ММЕСЯЦА УКТОРГНО НЕТ ЕШЕ



            МетодВставкиНовогоМесяцавТабельКоторогоНет(ИщемУжеСозданныйМЕсяц);








    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
*/






////TODO СОЗДАНИЯ КАЛЕНДАРЯ С ПОЛУЧЕННЫМИ УЖЕ ДАННЫМИ
    private void МетодВставкиНовогоМесяцавТабельКоторогоНет(StringBuffer ищемУжеСозданныйМЕсяц) throws ParseException {
        try{
        Log.d(this.getClass().getName()," ПолученноеЗначениеИзТолькоСпинераДата " +ПолученноеЗначениеИзТолькоСпинераДата);
        StringBuffer МЕсяцСЗакглавнойБуквы =new StringBuffer(ПолученноеЗначениеИзТолькоСпинераДата.toLowerCase());
        ПолученноеЗначениеИзТолькоСпинераДата= МЕсяцСЗакглавнойБуквы.substring(0, 1).toUpperCase() + МЕсяцСЗакглавнойБуквы .substring(1).toLowerCase();
        Log.d(this.getClass().getName()," МЕсяцСЗакглавнойБуквы " +ПолученноеЗначениеИзТолькоСпинераДата);

            ContentValues АдаптерВставкаНовогоМЕсяцаИзКалендаря = new ContentValues();////контрейнер для нового табеля
            int ДляВставкиНовогоМесяцаНазвание = МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(ФинальнаяМЕсяцДляНовогоТабеля);
            int ДляВставкиНовогоГодНазвание = МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(ПолученныйГодДляНовогоТабеля);
            ///TODO  ПОСЛЕ ВСТАКИ ПЕРЕХОДИМ НА АКТИВТИ С ВЫБОРО И СОЗДАНИЕМ САМОГО ТАБЕЛЯ НОВОГО
            Intent Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория = new Intent();
            Bundle data=new Bundle();
            data.putBinder("binder", binder);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtras(data);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.setClass(getApplicationContext(), MainActivity_New_Tabely.class); // ТУТ ЗАПВСКАЕТЬСЯ ВЫБОР ПРИЛОЖЕНИЯ
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ПолученноеЗначениеИзСпинераДата", ПолученноеЗначениеИзТолькоСпинераДата);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ПолученныйГодДляНовогоТабеля", ДляВставкиНовогоГодНазвание);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ФинальнаяМЕсяцДляНовогоТабеля",ДляВставкиНовогоМесяцаНазвание);
            Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.putExtra("ДепартаментТабеляПослеПодбораBACK", ПолученноеЗначениеИзТолькоСпинераДата);
            // /TODO перердаваемые три згначение в следующее активти // -- ФинальнаяМЕсяцДляНовогоТабеля // ПолученныйГодДляНовогоТабеля // -ПолученноеЗначениеИзТолькоСпинераДата
            Log.d(this.getClass().getName(), "  ПолученноеЗначениеИзТолькоСпинераДата " + ПолученноеЗначениеИзТолькоСпинераДата +
                    " ПолученныйГодДляНовогоТабеля " + ПолученныйГодДляНовогоТабеля
                    + " ФинальнаяМЕсяцДляНовогоТабеля " + ФинальнаяМЕсяцДляНовогоТабеля);
            ///TODO ПОСЛЕ ОТПРОВКИ ДАННЫХ ЧИСТИМ ПЕРЕМЕНЕЫ
        Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Интент_ЗапускСозданиеНовогоТабельногоУчетавТаблицуИстория);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }

        }






    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int month=0;
        try{
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
        month = calendar.get(Calendar.MONTH) + 1;



    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }


        return   month;
    }

    //TODO метод получени месяа для записи в одну колонку

    private int  МетодПолучениниеГОдДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int year=0;
        try{
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
            year  = calendar.get(Calendar.YEAR);


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   year ;
    }

////TODO МЕТОД ТОЛЬКО ДЛЯ ВСТВКИ НОВОГО МЕСЯЦА и ГОД НОВЫЙ

















    private int  МетодПолучениниеНовогоМесяцДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( " " + ДатаКоторуюНадоПеревестиИзТекставЦифру + " " +ДатаКоторуюНадоПеревестиИзТекставЦифру);
        int month=0;
        try{
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL");
        Date date = formatмесяц .parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH) + 1;


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   month;
    }

    private int  МетодПолучениниеНовыйГодДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        System.out.println( "ДатаКоторуюНадоПеревестиИзТекставЦифру " +ДатаКоторуюНадоПеревестиИзТекставЦифру);

        int year=0;
        try{
        SimpleDateFormat formatгод = new SimpleDateFormat("yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру);
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        Calendar calendar2 = new GregorianCalendar();
        calendar.setTime(date );
      year = calendar.get(Calendar.YEAR);


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
        return   year ;
    }





    //TODO метод получени года для записи в одну колонку

    private String МетодПолучениниеГодаДляЗАписивОднуКолонку(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        //
        String Год = null;
try{
        int ИщемЕслиПробелВДате=ДатаКоторуюНадоПеревестиИзТекставЦифру.indexOf(" ");

        StringBuffer ИщемГод=new StringBuffer(ДатаКоторуюНадоПеревестиИзТекставЦифру);

         Год=  ИщемГод.substring(ИщемЕслиПробелВДате,ИщемГод.length());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", new Locale("ru"));

        Date date = formatter.parse(Год);

        System.out.println(date);

        System.out.println(formatter.format(date));


    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return   Год;
    }



    // TODO вытасиваем даные из базы чтобы ЗАполнить спирер готовыми табелями Датами НАПРИМЕР ОКТЯРЬ 2020  ДЕКАБРЬ 2019
    private void МетодЗаполненияАлайЛИстаНовымМЕсцевНовогоТабеля() throws InterruptedException, ExecutionException, TimeoutException, ParseException {
        try{
           Курсор_ДанныеДляСпинераДаты = new Class_MODEL_synchronized(context).МетодДанныеДЛяСпинераТАбеля();
            Log.d(this.getClass().getName(), " Курсор_ДанныеДляСпинераДаты" +" время: " +Курсор_ДанныеДляСпинераДаты);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    private void МетодЗаполенияТабелямиАктивти()
            throws ExecutionException, InterruptedException, ParseException {
        try {
        if ( Курсор_ДанныеДляСпинераДаты.getCount()>0) {/////ЗАГРУЖАЕМ ДАННЫЕ ИЗ ТАБЛИЦЫ CFO ДЛЯ СПИНЕРА И СОЗДАНИЯ ТАБЕЛЯ
                Log.d(this.getClass().getName()," Курсор_ДанныеДляСпинераДаты.getCount() " + Курсор_ДанныеДляСпинераДаты.getCount());
            //////TODO ЗАПОЛЯЕМ СПИНЕР ЧЕРЕЗ АРАЙЛИСТ ПОСЛЕДНИМИ ДАТАМИ
            Курсор_ДанныеДляСпинераДаты.moveToLast();
            МассивДляВыбораВСпинерДата.clear();
            do{
                String     ЗнаениеИзБазыНовыеТабеляМесяц = Курсор_ДанныеДляСпинераДаты.getString(0).trim();
                String     ЗнаениеИзБазыНовыеТабеляГод = Курсор_ДанныеДляСпинераДаты.getString(1).trim();
                String     ЗнаениеИзБазыНовыеТабеляНазваниеТабеля = null;
                int ИндексСФО=Курсор_ДанныеДляСпинераДаты.getColumnIndex("cfo");
                int     ЗнаениеИзБазыНовыеТабеляIDСфо = Курсор_ДанныеДляСпинераДаты.getInt(ИндексСФО);
                ПолученнаяUUIDНазванияОрганизации=0l;
                ПолученнаяUUIDНазванияОрганизации=Long.parseLong(String.valueOf(ЗнаениеИзБазыНовыеТабеляIDСфо));
                Log.d(this.getClass().getName()," ЗнаениеИзБазыНовыеТабеляМесяц " +ЗнаениеИзБазыНовыеТабеляМесяц +"  ЗнаениеИзБазыНовыеТабеляГод " +ЗнаениеИзБазыНовыеТабеляГод
                        +" ЗнаениеИзБазыНовыеТабеляIDСфо" +ЗнаениеИзБазыНовыеТабеляIDСфо + " ПолученнаяUUIDНазванияОрганизации " +ПолученнаяUUIDНазванияОрганизации);
                Log.d(this.getClass().getName()," ЗнаениеИзБазыНовыеТабеляНазваниеТабеля " +ЗнаениеИзБазыНовыеТабеляНазваниеТабеля);
                ////todo ПРЕОБРАЗОВАЫВЕМ ЦИФРВЫ В ДАТУ ВВИДЕТ ТЕКСТА ИБЛЬ АВГУСТ 2020 2021
                SimpleDateFormat ПереводимЦифруВТЕкстМЕсяца = new SimpleDateFormat("mm", new Locale("rus") );
                Date ДатаДляПолученияМесяцаСловом = ПереводимЦифруВТЕкстМЕсяца.parse(ЗнаениеИзБазыНовыеТабеляМесяц);
                String ПреобразованоеИмяМесяца= ПереводимЦифруВТЕкстМЕсяца.format( ДатаДляПолученияМесяцаСловом );
                Log.d(this.getClass().getName()," ПреобразованоеИмяМесяца " +ПреобразованоеИмяМесяца);
                SimpleDateFormat formatмесяц = new SimpleDateFormat("MMyyyy", new Locale("ru"));
                Date date = formatмесяц.parse(ПреобразованоеИмяМесяца+ЗнаениеИзБазыНовыеТабеляГод);
                Calendar calendar = Calendar.getInstance(new Locale("ru"));
                calendar.setTime(date);
                System.out.println(calendar.get(Calendar.YEAR));
                System.out.println(calendar.get(Calendar.MONTH)+1);
                System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
                System.out.println(new SimpleDateFormat("LLLL").format(calendar.getTime()));
                ПреобразованоеИмяМесяца=new SimpleDateFormat("LLLL").format(calendar.getTime());
                StringBuffer stringBuffer=new StringBuffer(ПреобразованоеИмяМесяца);
                ПреобразованоеИмяМесяца=stringBuffer.substring(0,1).toUpperCase()+stringBuffer.substring(1,stringBuffer.length()).toLowerCase();
                //String месяцОбрантноТекстДляКурсора=   МетодПолучениеДатыИзЦифраВТекстДляКурсора(ЗнаениеИзБазыНовыеТабеляМесяц);
                String ФиналВставкаМЕсяцаИгода = "";
                ////TODO ПОКАЗЫВВАЕТ ПОЛЬЗОВАТЛЕЛЮ МЕСЯЦ И ГОДВ ВИДЕ СЛОВ ИЗ ЦИФРЫ 11 МЕНЯЕ НА НОЯБРЬ 2020 НАПРИМЕР
                ФиналВставкаМЕсяцаИгода=ПреобразованоеИмяМесяца+ "  "+ЗнаениеИзБазыНовыеТабеляГод;
                Log.d(this.getClass().getName()," ФиналВставкаМЕсяцаИгода "+ФиналВставкаМЕсяцаИгода);
                ///todo ТОЛЬКО ДЛЯ ПРОСМОТРА ДАННЫХ ПОЛЬЗОВТЕЛЯ ЗАГРУАЕТЬСЯ ИЗ БАЗЫ
                МассивДляВыбораВСпинерДата.add(ФиналВставкаМЕсяцаИгода);

                Log.d(this.getClass().getName(),"  МассивДляВыбораВСпинерДата " + МассивДляВыбораВСпинерДата.toString() +"  МассивДляВыбораВСпинерДата " +МассивДляВыбораВСпинерДата.size());
                ////////
            }while (Курсор_ДанныеДляСпинераДаты.moveToPrevious());
            // TODO: 14.11.2022
            Log.d(this.getClass().getName(),"    МассивДляВыбораВСпинерДата " +МассивДляВыбораВСпинерДата);
            Курсор_ДанныеДляСпинераДаты.close();
        }else {
            Log.d(this.getClass().getName(),"  Нет двнных    МассивДляВыбораВСпинерДата " +МассивДляВыбораВСпинерДата);
        }
        } catch (Exception e) {///////ошибки
            e.printStackTrace();
            Log.e(Class_MODEL_synchronized.class.getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    ////ВТОРАЯ ФУНКЦИЯ  ДАТЫ НА РУСКИЙ ЯЗЫК МЕСЯЦ
    //функция получающая время операции

    public String МетодПереводаНазваниеМесяцаСАнглискогоНаРУсский(Date ПрасингДаты) {
        SimpleDateFormat sdfmt = null;
        SimpleDateFormat sdfmtГод = null;
        String ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        String ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
        sdfmt = new SimpleDateFormat("LLLL", new Locale("ru"));
        ФинальнаяМЕсяцДляНовогоТабеля=sdfmt.format(ПрасингДаты);
        Log.d(this.getClass().getName(),"  ФинальнаяМЕсяцДляНовогоТабеля  "+ ФинальнаяМЕсяцДляНовогоТабеля);

        ///////МЕНЯЕМ АГЛИСКИЕ НА РУСКОЕ НАЗВАНИЕМЕСЯЦА
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев=sdfmt.format(ПрасингДаты);////ПЕРВОНОЧАЛЬНОЕ ВИД МЕСЯЦ НА АНГЛИСКОМ
        ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев= ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев;
        Log.d(this.getClass().getName(),"  ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев  "+ДатаДляСпинераИМеняемАнглискиеНаРУскиеНазваниеМесяцев);
        ///////Добалявем год
        sdfmtГод = new SimpleDateFormat("yyyy", new Locale("ru"));
        ПолученныйГодДляНовогоТабеля=sdfmtГод.format(ПрасингДаты);
        System.out.println("  операции время :  " + sdfmtГод.format(ПрасингДаты));
        ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт=ФинальнаяМЕсяцДляНовогоТабеля+"  "+ПолученныйГодДляНовогоТабеля;
        System.out.println("  ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт  " + ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт);
////////свич пробегаеться по названиям месяцев и перерделываем их с аглиского на русский
        return  ФинальнаяДатаДЛяОпределенияНовыйЭтоМесяцИлиНЕт;
    }



    //TODO метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--МЕСЯЦ

    private int  МетодПолучениниеКурсораМЕсяцДата(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        String[] ДелимМЕсяцИгод =ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");
        System.out.println( " " + ДелимМЕсяцИгод [0]);
        SimpleDateFormat formatмесяц = new SimpleDateFormat("LLLL  yyyy", new Locale("ru"));
        Date date = formatмесяц.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру.trim());
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("MMMM").format(calendar.getTime()));
        return   calendar.get(Calendar.MONTH)+1;
    }
    //TODO метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--ГОД
    private int  МетодПолучениниеКурсораГОДДата(String ДатаКоторуюНадоПеревестиИзТекставЦифру) throws ParseException {
        String[] ДелимМЕсяцИгод =ДатаКоторуюНадоПеревестиИзТекставЦифру.split(" ");
        System.out.println( " " + ДелимМЕсяцИгод [1]);
        SimpleDateFormat formatгод = new SimpleDateFormat("LLLL  yyyy");
        Date date = formatгод.parse(ДатаКоторуюНадоПеревестиИзТекставЦифру.trim());
        Calendar calendar = Calendar.getInstance(new Locale("ru"));
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("yyyy").format(calendar.getTime()));
        return   calendar.get(Calendar.YEAR);
    }
    //TODO  конец метод получени месяа для записи в одну колонку ОБРАБОТКА ДАТЫ ДЛЯ КУРСОРА НЕ НОВЫЕ ДАННЫЕ А УЖЕ СУЩЕТСВУЮЩИЕ--МЕСЯЦ








    ///todo сообщение на активти создание новго сотрудника спрашиваем нужно ли создать
    @UiThread
    protected void СообщениеСпрашиваемПользователяЧтоОнТОчноХочетьСоздатьНовыйТабель(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try{
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_new_tabel1)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateСоздатьТабель = alertDialog .getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateСоздатьТабель.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog .dismiss();
                    Log.d(this.getClass().getName()," создание нового сотрудника " );


                    ///TODO создание нового ТАБЕЛЯ
                    МетодСозданиеДиалогаКалендаряДаты();////ЗПАСУКАЕМ МЕТОД КОГДА НАДО ВЫБРВТЬ ДАТУ С КАЛЕНДАРКА


                }
            });

/////////кнопка
            final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog .dismiss();
///запуск метода обновления через DIALOGBOX
                }
            });
            /////
            //TODO шаблоны



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
       this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }










































































    void МетодУдалениеТАбеляСообщениеПередЭтим(  @NonNull Long СамUUIDТабеля,
                                               @NonNull String НазваниеУдаляемогоТАбеля,
                                                 @NonNull  View v) throws InterruptedException {

        try{
            Long СамUUIDТабеляКакLONG= Long.valueOf(СамUUIDТабеля);
            Boolean ФлагВыясняемПроведенныйТабельИлиНет = false;
            SQLiteCursor Курсор_ИщемПроведенЛиТАбельИлиНЕт = null;
            // TODO: 26.08.2021 НОВЫЙ ВЫЗОВ НОВОГО КЛАСС GRUD - ОПЕРАЦИИ
            class_grud_sql_operationsДляАктивтиТабель= new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","data_tabels");
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("ФорматПосика","uuid_tabel=?");
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеПоиска1",СамUUIDТабеляКакLONG.toString());
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита",1);
            class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","date_update DESC");
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT (getApplicationContext());
            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
            Курсор_ИщемПроведенЛиТАбельИлиНЕт= (SQLiteCursor) class_grud_sql_operationsДляАктивтиТабель.
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsДляАктивтиТабель. concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());
            Log.d(this.getClass().getName(), "GetData " +Курсор_ИщемПроведенЛиТАбельИлиНЕт );
            /////////
            if(Курсор_ИщемПроведенЛиТАбельИлиНЕт.getCount()>0){
                Курсор_ИщемПроведенЛиТАбельИлиНЕт.moveToFirst();
                Log.d(this.getClass().getName(), " Курсор_ИщемПУбличныйIDКогдаегоНетВстатике " + Курсор_ИщемПроведенЛиТАбельИлиНЕт.getCount());
                int ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике= Курсор_ИщемПроведенЛиТАбельИлиНЕт.getColumnIndex("status_carried_out");
                ФлагВыясняемПроведенныйТабельИлиНет =Boolean.parseBoolean( Курсор_ИщемПроведенЛиТАбельИлиНЕт.getString(ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике));
                Log.d(this.getClass().getName(), " ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике " + ИндексКурсор_ИщемПУбличныйIDКогдаегоНетВстатике+
                        "  ФлагВыясняемПроведенныйТабельИлиНет " +ФлагВыясняемПроведенныйТабельИлиНет);
            }
            Курсор_ИщемПроведенЛиТАбельИлиНЕт.close();

            // TODO: 16.09.2021  продолжаем удаление табеля табелья ЕСЛИ СТАРУС ТАБЕЛЬЯ НЕ ПРОВЕДЕННЫЙ
            if (ФлагВыясняемПроведенныйТабельИлиНет==false) {//todo МОЖНО УДАЛИТЬ ПОТОМУ ЧТО ЕЩЕ НЕ ПРЕДЕНЕ

                СообщениеВыборУдлалянияТабеляИзБазы(НазваниеУдаляемогоТАбеля,СамUUIDТабеля) ;

            }else if (ФлагВыясняемПроведенныйТабельИлиНет==true){

            Snackbar.make(v, "В Табеле присутстуют проведенный табель !!! ( удалить нельзя )",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }
//todo  конеч сообщение предупреждения удлаения табеля





















    ///todo сообщение
    @UiThread
    protected void СообщениеВыборУдлалянияТабеляИзБазы(String Сообщение, Long СамоЗначениеUUID) {
        ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
        try {
//////сам вид
            final AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                    .setTitle("Удаление Табеля...")
                    .setMessage(Сообщение)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_delete_customer)
                    .show();
/////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеля = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxУдалениеСотрудникаИзТабеля .setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "ИндификаторUUID " + " СамоЗначениеUUID " + СамоЗначениеUUID+"  "+ПолученноеЗначениеИзСпинераДата);
                    if (СамоЗначениеUUID>0) {
                        // TODO: 15.02.2023 получаем даннеы для удаления
                        Cursor cursorДляУдалениея=    МетодПолучениеДанныхДляИхУдаления(getApplicationContext(),СамоЗначениеUUID);
                        // TODO: 15.02.2023  само удаление по двум таблицам
                        МетодУдалениеСамогоТабеляИлиСотрудников(СамоЗначениеUUID,"data_tabels",cursorДляУдалениея);
                        Log.d(this.getClass().getName(), "  ФИНАЛ создание нового сотрудника " + "cursorДляУдалениея ");
                    }


                }
            });
            /////////кнопка
            final Button MessageBoxУдалениеСотрудникаИзТабеляОтмена = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxУдалениеСотрудникаИзТабеляОтмена.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }







    //todo метод удаление сотрудника из табеля
    private void МетодУдалениеСамогоТабеляИлиСотрудников(@NonNull Long СамоЗначениеUUID,
                                                         @NonNull  String ИзКакойТаблицыУдалять,
                                                         @NonNull Cursor cursor) {
        ArrayList<Integer> УдалениеintegerArrayList=new ArrayList<>();
        try{
            Log.d(this.getClass().getName()," ДляУдалениеUUID " +ИзКакойТаблицыУдалять);
            progressDialogДляУдаления = new ProgressDialog(activity);
            progressDialogДляУдаления.setTitle("Удаление Табеля");
            progressDialogДляУдаления.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляУдаления.setProgress(0);
            progressDialogДляУдаления.setCanceledOnTouchOutside(false);
            progressDialogДляУдаления.setMessage("Удаление...");
            progressDialogДляУдаления.show();
            Integer СтрочкиОбработки=cursor.getCount();
                        Observable.range(0,СтрочкиОбработки)
                                    .subscribeOn(Schedulers.single())
                                    .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), new BiFunction<Object, Long, Object>() {
                                        @Override
                                        public Object apply(Object o, Long aLong) throws Throwable {
                                            Log.d(this.getClass().getName(), " o " + o+ " aLong " +aLong);
                                            return aLong;
                                        }
                                    }).doOnNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        // TODO: 22.11.2022  первая часть
                                    Long    ДляУдалениеUUID=     cursor.getLong(0);
                                   Integer     Удаление = new Class_MODEL_synchronized(getApplicationContext()).УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(ИзКакойТаблицыУдалять,
                                                    "uuid", ДляУдалениеUUID);
                                            Log.d(this.getClass().getName(), " ДляУдалениеUUID " + ДляУдалениеUUID);
                                        if (Удаление>0) {
                                            УдалениеintegerArrayList.add(Удаление);
                                        }
                                    }
                                })
                                .doAfterNext(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Throwable {
                                        cursor.moveToNext();
                                        context.getMainExecutor().execute(()->{
                                            progressDialogДляУдаления.setMessage("Удалание..."+УдалениеintegerArrayList.size()+"("+СтрочкиОбработки+")");
                                        });
                                    }
                                })
                               .subscribeOn(AndroidSchedulers.mainThread())
                                    .doOnComplete(new Action() {
                                        @Override
                                        public void run() throws Throwable {
                                            Log.d(this.getClass().getName(), " УдалениеintegerArrayList.size() " +УдалениеintegerArrayList.size());
                                            МетодУдалениеСамогоТабеляИлиСотрудников(СамоЗначениеUUID,"tabel");
                                        }
                                    })
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Throwable {
                                            Log.d(this.getClass().getName(), " doOnError  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    })
                                    .onErrorComplete(new Predicate<Throwable>() {
                                        @Override
                                        public boolean test(Throwable throwable) throws Throwable {
                                            Log.d(this.getClass().getName(), " onErrorComplete  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                                            ///метод запись ошибок в таблицу
                                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            return false;
                                        }
                                    }).subscribe();;



        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }




    //todo ВТОРОЙ МЕТОД УДАЛЕНИЕ ДЛЯ ВЕРХНЕНЙ ТАБЛИЦЫ ТАБЕЛЬ
    private void МетодУдалениеСамогоТабеляИлиСотрудников(@NotNull  Long ДляУдалениеUUID,
                                                         @NonNull  String ИзКакойТаблицыУдалять) {
        ArrayList<Integer> УдалениеintegerArrayList=new ArrayList<>();
        try{
            Log.d(this.getClass().getName()," ДляУдалениеUUID " +ДляУдалениеUUID);
            Observable.range(0,1)
                    .subscribeOn(Schedulers.single())
                    .zipWith(Observable.interval(300, TimeUnit.MILLISECONDS), new BiFunction<Object, Long, Object>() {
                        @Override
                        public Object apply(Object o, Long aLong) throws Throwable {
                            Log.d(this.getClass().getName(), " o " + o+ " aLong " +aLong);
                            return aLong;
                        }
                    }).doOnNext(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Throwable {
                            // TODO: 22.11.2022  первая часть
                            Integer     Удаление = new Class_MODEL_synchronized(getApplicationContext()).УдалениеТолькоПустогоТабеляЧерезКонтейнерУниверсальная(ИзКакойТаблицыУдалять,
                                    "uuid", ДляУдалениеUUID);
                            Log.d(this.getClass().getName(), " ДляУдалениеUUID " + ДляУдалениеUUID);
                            if (Удаление>0) {
                                УдалениеintegerArrayList.add(Удаление);
                            }
                        }
                    })
                    .doAfterNext(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Throwable {
                            context.getMainExecutor().execute(()->{
                                progressDialogДляУдаления.setMessage("Удалание..."+УдалениеintegerArrayList.size()+"("+УдалениеintegerArrayList.size()+")");
                            });

                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            Log.d(this.getClass().getName(), " УдалениеintegerArrayList.size() " +УдалениеintegerArrayList.size());
                            context.getMainExecutor().execute(()->{
                                if ( УдалениеintegerArrayList.size()>0) {
                                    // TODO: 07.10.2022  СИНХронизация
                                    МетодЗапускаСинхрониазцииЕслиБыИзмененияВбАзе();
                                }
                                // TODO: 15.02.2023
                                progressDialogДляУдаления.dismiss();
                                progressDialogДляУдаления.cancel();;
                            });
                        }
                    })
                    .doOnTerminate(new Action() {
                        @Override
                        public void run() throws Throwable {
                            context.getMainExecutor().execute(()-> {
                                onStart();
                            });
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.d(this.getClass().getName(), " doOnError  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.d(this.getClass().getName(), " onErrorComplete  МетодУдалениеСамогоТабеля  throwable " +throwable.getMessage());
                            ///метод запись ошибок в таблицу
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    }).subscribe();
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        };
    }





    private void МетодЗапускаСинхрониазцииЕслиБыИзмененияВбАзе() {
        PUBLIC_CONTENT      cachedThreadPoolВизуальнаяСинхронизацияМенеджер=new PUBLIC_CONTENT(getApplicationContext());
        Integer РезультатЗапускВизуальнойСинхронизации =0;
        try{
            // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
            // TODO: 01.02.2022 заПУСКАЕМ сИНХРОНИАЗАЦИЮ С ВСЕХ ЛИСТ ТАБЕЛЕЙ
            Integer  ПубличныйIDДляФрагмента=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Bundle bundleДляПЕредачи=new Bundle();
            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляФрагмента);
            Intent  intentЗапускОднорworkanager=new Intent();
            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
            // TODO: 02.08.2022
            new Class_Generator_One_WORK_MANAGER(getApplicationContext()).
                    МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),intentЗапускОднорworkanager);
            // TODO: 26.06.2022
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ПубличныйIDДляОдноразовойСинхронПубличныйIDДляФрагментаиазции "+ПубличныйIDДляФрагмента );
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


}