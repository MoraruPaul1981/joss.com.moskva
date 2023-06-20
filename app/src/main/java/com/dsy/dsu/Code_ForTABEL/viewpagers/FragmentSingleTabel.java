package com.dsy.dsu.Code_ForTABEL.viewpagers;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSingleTabel extends Fragment {
    // TODO: Rename and change types and number of parameters
    private Integer    ГодТабелей=0 ;
    private  Integer    МЕсяцТабелей=0 ;
    private  Integer     DigitalNameCFO=0;
    private  Integer     positionViewPager=0;
    private  Long    MainParentUUID=    0l;
    private  SubClassNewViewPagerSingleTabel singleTabel;

    public  static FragmentSingleTabel newInstance(@NonNull Bundle bundleNewViewPager,@NonNull Integer value) {
        FragmentSingleTabel fragment = new FragmentSingleTabel();
        bundleNewViewPager.putInt("value",value);
        fragment.setArguments(bundleNewViewPager);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleTabel=new   SubClassNewViewPagerSingleTabel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_tabel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          MainParentUUID=    getArguments().getLong("MainParentUUID", 0l);
        Integer   value =    getArguments().getInt("value", 0);

        ГодТабелей=   getArguments().getInt("ГодТабелей", 0);
        МЕсяцТабелей=   getArguments().getInt("МЕсяцТабелей",0);
        DigitalNameCFO=    getArguments().getInt("DigitalNameCFO", 0);
       // Cursor     cursorForViewPager =    singleTabel.new SubClassGetCursor() .МетодSwipesКурсор();
        TextView textfragnetviewpager = (TextView)  view.findViewById(R.id.textfragnetviewpager);
        textfragnetviewpager.setText(MainParentUUID.toString());
        // TODO: 20.06.2023
        TextView textfragnetviewpager2 = (TextView)  view.findViewById(R.id.textfragnetviewpager2);
        textfragnetviewpager2.setText(value.toString());

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
            + " cursorForViewPager.getPosition() " +  "value " +value);
    }


    // TODO: 20.06.2023 класс получение ДАнных вунтри фаргемента новго Order
    // TODO: 20.06.2023 Класс получение данных CURSOR
    class  SubClassNewViewPagerSingleTabel {
    class SubClassGetCursor{
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
                cursor =      (Cursor)    new SubClassCursorLoader(). CursorLoaders(getContext(), bundleГлавныйКурсорMultiДанныеSwipes);
                cursor.move(positionViewPager);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "cursor " +cursor );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  cursor;
        }
    }//TODO END   class SubClassGetCursor


    }//TODO     class  SubClassNewViewPagerSingleTabel
}