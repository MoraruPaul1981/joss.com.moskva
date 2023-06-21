package com.dsy.dsu.Code_ForTABEL.viewpagers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabel extends Fragment {
    Busable mainActivity;
    private ViewPager viewPager ;

    private Cursor cursorForViewPager;
    private  SubClassBisscessFragmentSingleTabel fragmentSingleTabel;
    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabel newInstance(@NonNull Bundle bundle_single_tabel_viewpagers ) {
        FragmentSingleTabel fragment = new FragmentSingleTabel();
        Bundle args = new Bundle();
        args=bundle_single_tabel_viewpagers.deepCopy();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (Busable) context;
        viewPager=(ViewPager)  mainActivity.viewPager();
        cursorForViewPager=(Cursor)  mainActivity.getcorsor();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
            fragmentSingleTabel=new SubClassBisscessFragmentSingleTabel();
            fragmentSingleTabel.new SubClassListerViewPager().методСлушательViewPager();
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_tabel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
        super.onViewCreated(view, savedInstanceState);
            fragmentSingleTabel.new SubClassTestDataAnScreen().методТестовыйВЫгрузкаДанныхНаЭкран(view);
    
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }















    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel 
    class  SubClassBisscessFragmentSingleTabel{
        // TODO: 21.06.2023  Класс Тестовый По выгрузке Данныз НА экран
        class  SubClassTestDataAnScreen{
            void  методТестовыйВЫгрузкаДанныхНаЭкран(@NonNull View view){
                try{
                    TextView textfragnetviewpager = (TextView)  view.findViewById(R.id.textfragnetviewpager);
                    Integer value =getArguments().getInt("value");
                    textfragnetviewpager.setText(value.toString());
                    // TODO: 20.06.2023
                    TextView textfragnetviewpager2 = (TextView)  view.findViewById(R.id.textfragnetviewpager2);
                    Long uuid =getArguments().getLong("uuid");;
                    textfragnetviewpager2.setText(uuid.toString());
                    // TODO: 20.06.2023
                    TextView textfragnetviewpager3 = (TextView)  view.findViewById(R.id.textfragnetviewpager3);
                    Integer getpositioncursor =getArguments().getInt(   "getpositioncursor");;
                    textfragnetviewpager3.setText(getpositioncursor.toString());
                    // TODO: 21.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                
                
            }
            
            
            
        }
        
        
        
        

        // TODO: 21.06.2023 Класс Слушатель View Pager 
        class SubClassListerViewPager{
            private void методСлушательViewPager() {
                try{
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                        @Override
                        public void onPageSelected(int position) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    });
// TODO: 20.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            
            
            
        }
    }//TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel







}