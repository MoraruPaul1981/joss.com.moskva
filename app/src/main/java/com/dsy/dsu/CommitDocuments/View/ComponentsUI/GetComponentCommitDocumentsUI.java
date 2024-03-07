package com.dsy.dsu.CommitDocuments.View.ComponentsUI;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.dsy.dsu.CommitDocuments.View.ViewModel.ViewModelCommitDocuments;
import com.dsy.dsu.CommitDocuments.View.Window.FragmentCommitDocuments;
import com.dsy.dsu.Errors.Class_Generation_Errors;


/**
 * Компоненты Фрагмента Согласование Документов
 */
public class GetComponentCommitDocumentsUI extends FragmentCommitDocuments {


  public GetComponentCommitDocumentsUI(@NonNull View view,
                                       @NonNull Activity activity,
                                       @NonNull Context context,
                                       @NonNull ViewModelCommitDocuments viewModelCommitDocuments) {



  }


  public void workerUI(){
try{

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
