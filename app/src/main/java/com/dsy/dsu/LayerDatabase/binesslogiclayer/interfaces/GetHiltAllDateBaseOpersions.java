package com.dsy.dsu.LayerDatabase.binesslogiclayer.interfaces;


import com.dsy.dsu.LayerDatabase.binesslogiclayer.cursors.GetAllCursor;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.cursors.GetSettingCursor;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.inserts.GetDataBaseInsert;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.PublicidAfterSuccessLogin;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.PublicidAfterSuccessSynchronization;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface GetHiltAllDateBaseOpersions {

    // TODO: 25.11.2024
    GetDataBaseInsert getbinessLogicDataBase();

    PublicidAfterSuccessSynchronization publicidAfterSuccessSynchronization();

    GetAllCursor getAllCursor();

    GetSettingCursor getSettingCursor();

    PublicidAfterSuccessLogin publicidAfterSuccessLogin();

}
