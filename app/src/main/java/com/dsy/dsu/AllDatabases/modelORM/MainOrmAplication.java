package com.dsy.dsu.AllDatabases.modelORM;

import android.app.Application;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;
import com.orm.SugarTransactionHelper;


public class MainOrmAplication  extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarDb sugarDb=new SugarDb(getApplicationContext());
       // sugarDb.
    }
}

