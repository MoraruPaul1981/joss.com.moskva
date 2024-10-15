package com.dsy.dsu.LayerBunessLogic.Hilt.RoomHilt;


import com.dsy.dsu.LayerDatabase.ROOM.ROOMDatabase;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceRoom {


    ROOMDatabase getHiltRoom( );
}


