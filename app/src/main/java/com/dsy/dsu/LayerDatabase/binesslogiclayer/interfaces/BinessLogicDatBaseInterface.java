package com.dsy.dsu.LayerDatabase.binesslogiclayer.interfaces;


import com.dsy.dsu.LayerDatabase.binesslogiclayer.BinessLogicDataBase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface  BinessLogicDatBaseInterface {

    // TODO: 25.11.2024
    BinessLogicDataBase getbinessLogicDataBase();

}
