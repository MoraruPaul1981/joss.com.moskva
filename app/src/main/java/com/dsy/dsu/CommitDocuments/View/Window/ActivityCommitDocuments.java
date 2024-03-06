package com.dsy.dsu.CommitDocuments.View.Window;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dsy.dsu.CommitDocuments.View.ViewModel.ModelFactoryCommitDocuments;
import com.dsy.dsu.CommitDocuments.View.ViewModel.ViewModelCommitDocuments;
import com.dsy.dsu.Errors.Class_Generation_Errors;
import com.dsy.dsu.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class ActivityCommitDocuments extends AppCompatActivity {
    @Inject
    Integer getHiltPublicId;


   ViewModelCommitDocuments viewModelCommitDocuments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        setContentView(R.layout.activity_commit_documents);
        if (savedInstanceState == null) {


            viewModelCommitDocuments = new ViewModelProvider(this,  new ModelFactoryCommitDocuments(getHiltPublicId,this)).get(ViewModelCommitDocuments.class );




            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentCommitDocuments.newInstance())
                    .commitNow();
        }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}