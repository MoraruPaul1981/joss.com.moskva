package com.dsy.dsu.CommitDocuments.View.ComponentsUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitDocuments.View.ViewModel.ViewModelCommitDocuments;
import com.dsy.dsu.CommitDocuments.View.Window.FragmentCommitDocuments;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesByte;
import com.dsy.dsu.CommitingPrices.ViewModel.ModelComminingPrisesString;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


/**
 * Компоненты Фрагмента Согласование Документов
 */
public class GetComponentCommitDocumentsUI extends FragmentCommitDocuments {
  protected View view;
  protected  Activity activity;
  protected Context context;
  protected ViewModelCommitDocuments viewModelCommitDocuments;
  protected ObjectMapper getHiltJaksonObjectMapper;
  protected LifecycleOwner lifecycleOwner;

  // TODO: 07.03.2024 компонеты согсалованеи документов 
  protected RecyclerView recycleview_comminingpprices;
  protected ProgressBar prograessbar_commintingprices ;
  protected androidx.appcompat.widget.SearchView searchview_commintingprices;
  protected BottomNavigationView bottomnavigationw_commintingprices;
  protected BottomNavigationItemView bottomNavigationBack;
  protected BottomNavigationItemView bottomNavigationAsync;
  protected BottomNavigationItemView bottomNavigationSearch;




  public GetComponentCommitDocumentsUI(View view,
                                       @NonNull Activity activity,
                                       @NonNull Context context,
                                       @NonNull ViewModelCommitDocuments viewModelCommitDocuments,
                                       @NonNull ObjectMapper getHiltJaksonObjectMapper,
                                       @NonNull LifecycleOwner lifecycleOwner) {
    this.view = view;
    this.activity = activity;
    this.context = context;
    this.viewModelCommitDocuments = viewModelCommitDocuments;
    this.getHiltJaksonObjectMapper = getHiltJaksonObjectMapper;
    this.lifecycleOwner = lifecycleOwner;
  }


  @SuppressLint("RestrictedApi")
  public void workerUI(){
try{

  recycleview_comminingpprices = view.findViewById(R.id.recycleview_comminingpprices);
  prograessbar_commintingprices= view.findViewById(R.id.prograessbar_commintingprices);
  searchview_commintingprices=   view.findViewById(R.id.searchview_commintingprices);


  bottomnavigationw_commintingprices = view.findViewById(R.id.bottomnavigationw_commintingprices);
  bottomnavigationw_commintingprices.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
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
            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
  } catch (Exception e) {
    e.printStackTrace();
    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
  }
  }
}
