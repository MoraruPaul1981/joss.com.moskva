package com.dsy.dsu.LayerApp.DocumentsComApp.Model.BL;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.LayerBunessLogic.Errors.Class_Generation_Errors;

public class BL {

    Context context;




    class SetliveDataCommitDoc{
        public void startliveDataCommitDoc( ) {
            try {
       /*         liveDataCommitDoc = viewModelCommitDocuments.getMutableLiveСommitDocuments();

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "MyRecycleViewCommitDocumentsIsNullAdapters  "
                        + myViewHoldersCommitDocumentsIsNull );*/
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }


}
