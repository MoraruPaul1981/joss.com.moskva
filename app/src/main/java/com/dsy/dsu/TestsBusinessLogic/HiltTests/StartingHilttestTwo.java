package com.dsy.dsu.TestsBusinessLogic.HiltTests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


public class StartingHilttestTwo implements  IFunc2 {


    Logger LOGGER2Hilt = LoggerFactory.getLogger(this.getClass());


    @Override
    public void proccedorHiltTest() {
        LOGGER2Hilt.trace("  proccedorHiltTest()");
        LOGGER2Hilt.warn("   ");
    }
}



