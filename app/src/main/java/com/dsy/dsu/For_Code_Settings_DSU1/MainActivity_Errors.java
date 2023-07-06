package com.dsy.dsu.For_Code_Settings_DSU1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dsy.dsu.AllDatabases.Error.CREATE_DATABASE_Error;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Sendiing_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Date;


//вывод данных на Автивити
public class MainActivity_Errors extends AppCompatActivity  {
   private CREATE_DATABASE_Error create_database_error;
    private  TextView КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения;

    private  MaterialButton materialButtonОтправкаОшибокНАпочту;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
                super.onCreate(savedInstanceState);
            setContentView(R.layout.activitymain_viewlogin); ///activitymain_viewlogin  /// fragment_dashboard
            КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения = (TextView) findViewById(R.id.textViewDATA);
             create_database_error=new CREATE_DATABASE_Error(getApplicationContext());
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                        materialButtonОтправкаОшибокНАпочту   = (MaterialButton) findViewById(R.id.button3SendErrorEmal);

            preferences=   getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);

            МетодПросмотраОшибокПриложения();

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    protected void МетодПросмотраОшибокПриложения() {
        try {
                ArrayList<EditText> editTextArrayList = new ArrayList<>();
                StringBuffer stringBuffer = new StringBuffer();
                Class_GRUD_SQL_Operations class_grud_sql_operationsПросмотраОшибокПриложения
                        =new Class_GRUD_SQL_Operations(getApplicationContext());
            class_grud_sql_operationsПросмотраОшибокПриложения.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("НазваниеОбрабоатываемойТаблицы","ErrorDSU1");
            class_grud_sql_operationsПросмотраОшибокПриложения.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("СтолбцыОбработки","*");
            class_grud_sql_operationsПросмотраОшибокПриложения.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеСортировки","ID_Table_ErrorDSU1 DESC");
            class_grud_sql_operationsПросмотраОшибокПриложения.
                    concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.put("УсловиеЛимита","50");
            // TODO: 12.10.2021  Ссылка Менеджер Потоков
            PUBLIC_CONTENT  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new PUBLIC_CONTENT(getApplicationContext());

            Cursor   Курсор_СамиДанные_Error= (SQLiteCursor)  new Class_GRUD_SQL_Operations(getApplicationContext()).
                    new GetData(getApplicationContext()).getdata(class_grud_sql_operationsПросмотраОшибокПриложения.
                            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций,
                    Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,create_database_error.getССылкаНаСозданнуюБазу());

            Log.d(this.getClass().getName(), "GetData "+Курсор_СамиДанные_Error  );


                        МетодЗапускаAsynTaskОшибки(Курсор_СамиДанные_Error.getCount(), Курсор_СамиДанные_Error);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }

    
    protected void МетодЗапускаAsynTaskОшибки(int естьСтроки, Cursor Курсор_СамиДанные_Error) {
        try {
            StringBuffer БуерДляОшибок =new StringBuffer();
            String ИнфоТелефон = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();
            if (естьСтроки > 0) {

                materialButtonОтправкаОшибокНАпочту.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO полывоаем ошибки на почту
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v2.vibrate(50);
                        }
                        // TODO: 06.07.2023  оправлем ощибку на почту 
                        МетодПосылаемОшибкиНапочту(БуерДляОшибок);
                    }
                });


                Курсор_СамиДанные_Error.moveToFirst();
                int КоличествоСтрочекКурсоре = Курсор_СамиДанные_Error.getCount();
                Log.d(this.getClass().getName(), "количество записей в курсоре : " + КоличествоСтрочекКурсоре);
                do {
                    Integer Столбик_ID_Table_ErrorDSU1 = Курсор_СамиДанные_Error.getInt(0);
                    String Столбик_Error = Курсор_СамиДанные_Error.getString(1);
                    String Столбик_Klass = Курсор_СамиДанные_Error.getString(2);
                    String Столбик_Metod = Курсор_СамиДанные_Error.getString(3);
                    Integer LineError_LineError = Курсор_СамиДанные_Error.getInt(4);
                    String LineError_Data_Operazii_E = Курсор_СамиДанные_Error.getString(5);
                    int СтолбикКтоСделалОшибку = Курсор_СамиДанные_Error.getInt(6);
                    //конец  получение полей из таблицы соотвертвующего типа данных
                    //вставляем в билде
                    БуерДляОшибок.append(" #---------Ошибки ПО Табельный Учёт--------------#" + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(0).toUpperCase() + "  : " + Столбик_ID_Table_ErrorDSU1 + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(1).toLowerCase() + "  : " + Столбик_Error.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(2).toLowerCase() + "  : " + Столбик_Klass.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(3).toLowerCase() + "  : " + Столбик_Metod.toLowerCase() + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(4).toLowerCase() + "  : " + LineError_LineError + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(5).toLowerCase() + "  : " + LineError_Data_Operazii_E + "\n" + "\n" +
                            "   " + Курсор_СамиДанные_Error.getColumnName(6).toLowerCase() + "  : " + СтолбикКтоСделалОшибку + "\n" + "\n" +
                            "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                            "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                            Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                                    "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                            "   " + "-----------------------------------------" + "\n"+  "\n" );
                } while (Курсор_СамиДанные_Error.moveToNext());
                // TODO: 06.07.2023 exit cursor
                Курсор_СамиДанные_Error.close();
            }else {
                БуерДляОшибок.append(    "---------------Ошибок Нет.-----------"+"\n"+"\n"+
                        "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                        "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                        Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                        "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                        "   " + "-----------------------------------------" + "\n"+  "\n" );

                Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());

            }
                //////////////////////
                КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения.setText(БуерДляОшибок.toString());
            Log.d(this.getClass().getName(), " БуерДляОшибок   " +БуерДляОшибок.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }


    //////ОБЩИЙ МЕТОД СОЗДАНИЕ КЛАССИЧЕСКОГО ДИАЛОГА С КНОПКОЙ ЗАКРЫТЬ
    @UiThread
    public void МетодСозданиеДиалогаКлассЛогин(String ШабкаДиалога, String СообщениеДиалога) {
        try {
///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
//////сам вид
            final AlertDialog DialogBoxsПростомрДанных = new MaterialAlertDialogBuilder(this)
                    .setTitle(ШабкаДиалога)
                    .setMessage(СообщениеДиалога)
                    .setPositiveButton("Закрыть", null)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateПростомрДанных = DialogBoxsПростомрДанных.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateПростомрДанных.setOnClickListener(new View.OnClickListener() {

                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
///запуск метода обновления через DIALOGBOX
                    try {
//удаляем с экрана Диалог
                        DialogBoxsПростомрДанных.dismiss();
//соообщение
// Toast.makeText(getApplicationContext(), "Запускаем обновление данных " , Toast.LENGTH_LONG).show();
///////запуск главного меню после того как поняли что в азе нет логинов
                        Intent Интент_Меню_ТолькоПростотДанных;
                        Интент_Меню_ТолькоПростотДанных = new Intent(getApplicationContext(), MainActivity_Face_App.class);
///// Toast.makeText(getApplicationContext(), "Выбран пунк меню Главный Экран" , Toast.LENGTH_LONG).show();
                        startActivity(Интент_Меню_ТолькоПростотДанных);
                       // finish();
////ПОСЛЕ ОПЕРАЦИИ ОБНОВЛЕНИЕ ЗАПУСКАМ ГЛАВНЦЮ ФОРМУ ПРОСМОТРА ДАННЫХ
////


//ловим ошибки

                    } catch (Exception e) {
                        e.printStackTrace();
///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                         // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

                    }

                }
            });

// /поймать ошибку
        } catch (Exception e) {
//  Block of code to handle errors
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    protected void МетодПосылаемОшибкиНапочту(@NonNull StringBuffer БуерДляОшибок) {
        try{
            Integer   ПубличноеID  = preferences.getInt("ПубличноеID",0);
                БуерДляОшибок.append("\n")
                        .append(" текущий пользователь : ").append("\n")
                        .append(ПубличноеID).append("\n")
                        .append(" время отправки: ").append("\n")
                        .append(new Date())
                        .append("\n");
            // TODO: 06.07.2023  оправлем ощибки на ПОЧТУ
            // TODO: 06.07.2023  оправлем ощибки на ПРЧТУ
                new Class_Sendiing_Errors(this)
                        .МетодПослываемОшибкиАдминистаторуПо(БуерДляОшибок,this,ПубличноеID,create_database_error.getССылкаНаСозданнуюБазу() );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }
}//конец public class MainActivity_Recyclerview extends AppCompatActivity {
