package com.dsy.dsu.TestsBusinessLogic.HiltTests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public  class GetTestHilt {
    Logger LOGGER2Hilt2 = LoggerFactory.getLogger(this.getClass());



    public @Inject GetTestHilt() {
        LOGGER2Hilt2.trace("  proccedorHiltTest()");
    }


    public void proccer(){
        LOGGER2Hilt2.trace("  proccedorHiltTest()");

    }




}