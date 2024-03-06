package com.dsy.dsu.TestsBusinessLogic.HiltTests;


import org.jetbrains.annotations.TestOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartingHilttest {



    Logger LOGGER2Hilt = LoggerFactory.getLogger(this.getClass());

    @TestOnly
    public void Test() {
        LOGGER2Hilt.trace("  proccedorHiltTest()");

        IFunc2 iFunc2= new StartingHilttestTwo();
      //  IFunc2 iFunc2= new StartingHilttestTree();


        iFunc2. proccedorHiltTest();
    }
}



