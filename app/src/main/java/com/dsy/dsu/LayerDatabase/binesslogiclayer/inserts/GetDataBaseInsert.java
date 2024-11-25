package com.dsy.dsu.LayerDatabase.binesslogiclayer.inserts;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class GetDataBaseInsert {
    
    Context context;
    
    Long version;


    public  @Inject  GetDataBaseInsert(@ApplicationContext Context hiltcontext) {
        this.context = hiltcontext;

    }




    // TODO: 25.11.2024 end class 
}
