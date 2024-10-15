package com.dsy.dsu.LayerBunessLogic.Hilt.Sqlitehilt;


import android.database.sqlite.SQLiteDatabase;

import javax.inject.Named;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfacesqlite {


    SQLiteDatabase getHiltSqlite( );
}


