package com.dsy.dsu.LayerDatabase.binesslogiclayer;

import android.content.Context;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class BinessLogicDataBase {
    
    Context context;
    
    Long version;


    public BinessLogicDataBase(Context context, Long version) {
        this.context = context;
        this.version = version;
    }






    // TODO: 25.11.2024 end class 
}
