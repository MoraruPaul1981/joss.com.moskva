package com.dsy.dsu.CommitingPrices.View.Window;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.BLFragmentCommintingPrices;
import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.EventsBackAndAsyncAndSearchCommintPrices;
import com.dsy.dsu.CommitingPrices.Model.BiccessLogicas.InitRecyreviews.InizializayRecyreViews;
import com.dsy.dsu.CommitingPrices.Model.EvenBusPrices.MessageEvensBusPrices;
import com.dsy.dsu.CommitingPrices.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentCommingPrices extends Fragment {

    @Inject
    ObjectMapper getHiltJaksonObjectMapper;

    @Inject
    Integer getHiltPublicId;

    @Inject
    StringBuffer getHiltCommintgPrices;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LifecycleOwner lifecycleOwner;
    private RecyclerView recycleview_comminingpprices;


    private ProgressBar prograessbar_commintingprices ;

    private  ModelComminingPrisesString modelComminingPrisesString;
    private  ModelComminingPrisesByte modelComminingPrisesByte;

    private androidx.appcompat.widget.SearchView searchview_commintingprices;





    private BottomNavigationView bottomnavigationw_commintingprices;
    private BottomNavigationItemView bottomNavigationBack;
    private BottomNavigationItemView bottomNavigationAsync;
    private BottomNavigationItemView bottomNavigationSearch;
    private     EventsBackAndAsyncAndSearchCommintPrices eventsBackAndAsyncAndSearchCommintPrices;



   private BLFragmentCommintingPrices bucesslogicFragmentCommintingPrices;

    @Inject
    GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;

    @Inject
    MutableLiveData<Intent> getHiltMutableLiveDataPrices;

    public FragmentCommingPrices() {
        // Required empty public constructor
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

           modelComminingPrisesString =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesString;
           modelComminingPrisesByte =((MainActivityCommitingPrices)getActivity()).modelComminingPrisesByte;


            Bundle data=      getArguments();
            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            lifecycleOwner=this;
            Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewCommintPrices=null;
        try{
                viewCommintPrices = inflater.inflate(R.layout.activity_main_fragmentcommitprices, container, false);

        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_comming_prices, container, false);
        return viewCommintPrices;
    }



    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            recycleview_comminingpprices = view.findViewById(R.id.recycleview_comminingpprices);
            prograessbar_commintingprices= view.findViewById(R.id.prograessbar_commintingprices);
            searchview_commintingprices=   view.findViewById(R.id.searchview_commintingprices);


            bottomnavigationw_commintingprices = view.findViewById(R.id.bottomnavigationw_commintingprices);
            bottomnavigationw_commintingprices.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationBack = bottomnavigationw_commintingprices.findViewById(R.id.bottomNavigationBack);
            bottomNavigationBack.setTitle("Выйти");
            bottomNavigationAsync = bottomnavigationw_commintingprices.findViewById(R.id.bottomNavigationAsync);
            bottomNavigationAsync.setTitle("Обновить");
            bottomNavigationSearch = bottomnavigationw_commintingprices.findViewById(R.id.bottomNavigationSearch);
            bottomNavigationSearch.setTitle("Поиск");

            bottomNavigationSearch.setEnabled(false);
            bottomNavigationSearch.setClickable(false);




            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " recycleview_comminingpprices " +recycleview_comminingpprices);


    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
    public void onStart() {
        super.onStart();
 try{
     EventBus.getDefault().register(this);
     // TODO: 24.01.2024 Bisnes COde
     // TODO: 30.12.2023 код бизнес логики ддля Трех кнопок снизу ВЫход , СИнхрозауия, Посик
     eventsBackAndAsyncAndSearchCommintPrices= new EventsBackAndAsyncAndSearchCommintPrices(getContext(),bottomnavigationw_commintingprices,
             bottomNavigationBack,bottomNavigationAsync,bottomNavigationSearch );

// TODO: 30.12.2023 запускаем первоночальную оценку количество записей
     eventsBackAndAsyncAndSearchCommintPrices.new EventsAsync().eventsSearchsetNumber(null);


     // TODO: 30.12.2023 инициализируем посик SearchView
     eventsBackAndAsyncAndSearchCommintPrices.new EventsSearch().eventsSearch(bottomNavigationSearch,  searchview_commintingprices);



     // TODO: 30.12.2023  кнопка back обратно на все приложения faceApp
     eventsBackAndAsyncAndSearchCommintPrices.new EventsBack().eventsBack( );


     // TODO: 28.12.2023  запускаем класс бизнес логики для Фрагметна Commint Prices
     bucesslogicFragmentCommintingPrices =new BLFragmentCommintingPrices(getActivity(),getContext()
             ,getHiltJaksonObjectMapper,modelComminingPrisesString,
             modelComminingPrisesByte ,recycleview_comminingpprices
             ,prograessbar_commintingprices,lifecycleOwner ,searchview_commintingprices,
             eventsBackAndAsyncAndSearchCommintPrices,
             getHiltPublicId,getHiltCommintgPrices.toString(),
             getLiveDataForrecyreViewPrices,
             getHiltMutableLiveDataPrices,bottomNavigationSearch);


     // TODO: 28.12.2023 инизилащитция recyreview

     new InizializayRecyreViews(recycleview_comminingpprices,getContext()).startInitRecyreview();


     // TODO: 27.12.2023  начинаем запуск is null
     bucesslogicFragmentCommintingPrices.startIsNullRecyreView( );



     // TODO: 26.12.2023 запускаем получение данных при NULL
     bucesslogicFragmentCommintingPrices.getmodelByte( );


     // TODO: 30.12.2023  иницилизиуем кнопку запуска ASyncсинхрониазции
     eventsBackAndAsyncAndSearchCommintPrices.new EventsAsync( ).eventsAsync(bucesslogicFragmentCommintingPrices,prograessbar_commintingprices);


        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " recycleview_comminingpprices " +recycleview_comminingpprices);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 26.12.2023  бизнес логика самого Фрагмента CommintPrices


    @Override
    public void onStop() {
        super.onStop();
        try{
            EventBus.getDefault().unregister(this);
            Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " recycleview_comminingpprices " +recycleview_comminingpprices);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        // TODO: 09.01.202
    }



    // TODO: 23.01.2024 EventBus for Async
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusAyns(MessageEvensBusPrices messageEvensBusPrices){
try{
        // TODO: 26.12.2023 запускаем получение данных при NULL

    // TODO: 26.12.2023 запускаем получение данных при NULL
    bucesslogicFragmentCommintingPrices.getmodelByte( );


        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " messageEvensBusPrices " +messageEvensBusPrices.mess);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }





}