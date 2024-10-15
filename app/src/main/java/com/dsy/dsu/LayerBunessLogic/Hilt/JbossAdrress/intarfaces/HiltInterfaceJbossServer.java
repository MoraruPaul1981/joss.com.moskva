package com.dsy.dsu.LayerBunessLogic.Hilt.JbossAdrress.intarfaces;


import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface HiltInterfaceJbossServer {


   String getHiltJbossServer( );
}


