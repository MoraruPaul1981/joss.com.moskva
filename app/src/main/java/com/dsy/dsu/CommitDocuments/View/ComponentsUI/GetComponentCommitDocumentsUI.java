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
  protected RecyclerView recycleview_commitdocument;
  protected ProgressBar prograessbar_commitdocument;
  protected androidx.appcompat.widget.SearchView searchview_commitdocument;
  protected BottomNavigationView bottomnavigationw_commitdocument;
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

  recycleview_commitdocument = view.findViewById(R.id.recycleview_commitdocument);
  prograessbar_commitdocument = view.findViewById(R.id.prograessbar_commitdocument);
  searchview_commitdocument =   view.findViewById(R.id.searchview_commitdocument);


  bottomnavigationw_commitdocument = view.findViewById(R.id.bottomnavigationw_commitdocument);
  bottomnavigationw_commitdocument.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
  bottomNavigationBack = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationBack);
  bottomNavigationBack.setTitle("Выйти");
  bottomNavigationAsync = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationAsync);
  bottomNavigationAsync.setTitle("Обновить");
  bottomNavigationSearch = bottomnavigationw_commitdocument.findViewById(R.id.bottomNavigationSearch);
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
