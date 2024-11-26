package com.dsy.dsu.LayerDatabase.binesslogiclayer.interfaces;


import com.dsy.dsu.LayerDatabase.binesslogiclayer.cursors.GetAllCursor;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.cursors.GetCursorSuccessLogin;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.cursors.GetSettingCursor;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.deleting.RemovingOnlyBlankTabel;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.inserts.GetDataBaseInsert;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.PublicidAfterSuccessLogin;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.PublicidAfterSuccessSynchronization;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.UpdatingDataSimple;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.UpdatingTabelWithWhere;
import com.dsy.dsu.LayerDatabase.binesslogiclayer.updates.WriteModeConnections;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface GetHiltAllDateBaseOpersions {

    // TODO: 25.11.2024


    // TODO: 25.11.2024 Public
    PublicidAfterSuccessSynchronization publicidAfterSuccessSynchronization();
    PublicidAfterSuccessLogin publicidAfterSuccessLogin();


    // TODO: 25.11.2024 Cursors 

    GetAllCursor getAllCursor();
    GetSettingCursor getSettingCursor();

    GetCursorSuccessLogin getCursorSuccessLogin();



    // TODO: 25.11.2024 Updates
    UpdatingDataSimple updatingDataSimple();
    UpdatingTabelWithWhere updatingTabelWithWhere();





    // TODO: 25.11.2024 Inserts 
    GetDataBaseInsert getbinessLogicDataBase();




    // TODO: 25.11.2024 Deleting
    RemovingOnlyBlankTabel deletingTabelWithWhere();

    RemovingOnlyBlankTabel removingOnlyBlankTabel();


    WriteModeConnections writeModeConnections();
    
    

}
