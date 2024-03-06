package com.dsy.dsu.Tabels.viewpagers;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.DATE.SubClassCursorLoader;
import com.dsy.dsu.R;

import java.util.Locale;


public class MainActivity_Tabel_Single_PeopleOneSwipe extends AppCompatActivity  {
    private  SubClassBissnessLogicTableSingleWithViewPager singleWithViewPager;
    private  Integer    ГодТабелей=  0;
    private  Integer    МЕсяцТабелей=  0;
    private  Integer     DigitalNameCFO=0;
    private       Bundle bungleforFragment;

    private       Integer   PositionOffsetSingleTabel;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_single_tabel_oneswipe);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            Log.d(this.getClass().getName(), "  onCreate(Bundle savedInstanceState)   MainActivity_Tabel_Single_People  ");
            Locale locale = new Locale("rus");
            Locale.setDefault(locale);
            Configuration   config = getBaseContext().getResources().getConfiguration();
            config.setLocale(locale);
            createConfigurationContext(config);


            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // TODO: 20.06.2023 Инизиализация
            // TODO: 20.06.2023
            singleWithViewPager=  new SubClassBissnessLogicTableSingleWithViewPager( );

            // TODO: 29.03.2023  Метод обсуживаюшие
            singleWithViewPager.  методGETДанныеИзДругихАктивити();
            singleWithViewPager.методСозданиеФрагментаДляТабеляSingle( );

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    // TODO: 19.06.2023  Бизнес Класс Новой Активтив ТАбель Single Через ViewPager
    class  SubClassBissnessLogicTableSingleWithViewPager {
        public SubClassBissnessLogicTableSingleWithViewPager( ) {
        }
        private void методGETДанныеИзДругихАктивити() {
            try {
                Intent intent =  getIntent();
                  bungleforFragment =intent.getExtras();
                // TODO: 10.04.2023
                if (bungleforFragment !=null) {
                    Long    MainParentUUID=    bungleforFragment.getLong("MainParentUUID", 0l);
                      PositionOffsetSingleTabel=    bungleforFragment.getInt("Position", 0);
                      ГодТабелей=  bungleforFragment.getInt("ГодТабелей", 0);
                       МЕсяцТабелей=  bungleforFragment.getInt("МЕсяцТабелей",0);
                           DigitalNameCFO=   bungleforFragment.getInt("DigitalNameCFO", 0);
                    String      FullNameCFO=  bungleforFragment.getString("FullNameCFO", "").trim();
                    String    ИмесяцвИГодСразу= bungleforFragment.getString("ИмесяцвИГодСразу", "").trim();
                    Long       CurrenrsСhildUUID= bungleforFragment.getLong("CurrenrsСhildUUID", 0l);
                    String     ФИО= bungleforFragment.getString("ФИО", "").trim();
                    Long  CurrenrsSelectFio= bungleforFragment.getLong("CurrenrsSelectFio", 0l);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                            + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                            " PositionOffsetSingleTabel " +PositionOffsetSingleTabel+ " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методСозданиеФрагментаДляТабеляSingle(  ) {
            try{
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                Fragment      fragment_Single_Tabel_One_Swipe = new FragmentSingleTabelOneSwipe();
                fragment_Single_Tabel_One_Swipe.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragment_Single_Tabel_One_Swipe.setArguments(bungleforFragment);
                fragmentTransaction.add(R.id.linearLayout_single_tabel_one_swipe, fragment_Single_Tabel_One_Swipe);//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.commit();
                fragmentTransaction.show(fragment_Single_Tabel_One_Swipe);

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }
        // TODO: 20.06.2023 Класс получение данных CURSOR

      public  class SubClassGetCursor{
            Cursor          cursor = null;
            String  СамЗапрос;
            String[] УсловияВыборки;
            protected Cursor МетодSwipesКурсор() {
                try{
                    СамЗапрос=" SELECT  *   FROM viewtabel AS t" +
                            " WHERE t.cfo=? AND t.month_tabels  =?  AND t.year_tabels = ?  AND t.status_send !=?  AND t.fio IS NOT NULL  ORDER BY   t._id  " ;
                    УсловияВыборки=new String[]{String.valueOf(DigitalNameCFO),
                            String.valueOf(  МЕсяцТабелей),
                            String.valueOf(   ГодТабелей),
                            String.valueOf(  "Удаленная") };
                    //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                    Bundle bundleГлавныйКурсорMultiДанныеSwipes= new Bundle();
                    bundleГлавныйКурсорMultiДанныеSwipes.putString("СамЗапрос",СамЗапрос);
                    bundleГлавныйКурсорMultiДанныеSwipes.putStringArray("УсловияВыборки" ,УсловияВыборки);
                    bundleГлавныйКурсорMultiДанныеSwipes.putString("Таблица","viewtabel");
                    cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getApplicationContext(), bundleГлавныйКурсорMultiДанныеSwipes);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor " +cursor );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  cursor;
            }

        }


    }//TODO SubClassBissnessLogicTableSingleWithViewPager











}






















