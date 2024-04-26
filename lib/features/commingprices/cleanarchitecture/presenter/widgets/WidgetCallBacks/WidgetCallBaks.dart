
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/getPing.dart';
import '../WidgetAfterData1C/WidgetSuccessData.dart';
import '../WidgetWaiting/GetWidgetWaitingDontConnections1C.dart';
import '../WidgetWaiting/GetWidgetWaitingErrors.dart';
import '../WidgetWaiting/GetWidgetWaitingPing.dart';
import '../WidgetWaiting/Intarface/IntarfaceWaiting.dart';
import 'Interface/IntarfaceCallBaks.dart';








//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetCallBaks   implements IntarfaceCallBaks {


  ///TODO  методы ожидания
  @override
  Widget getWidgetProccingWait({required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger}){
//TODO
  logger.i("starting  getWidgetProccingWait");
  IntarfaceWaiting  intarfaceWaiting= GetWidgetWaitingPing();
    ///TODO return
  return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.white,currentText:'Союз-Автодор');
  }




  ///TODO метод по умочанию
   @override
  Widget getWidgetProccingDefault( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger}){
//TODO
    logger.i("starting  getWidgetProccingDefault");
    IntarfaceWaiting  intarfaceWaiting= GetWidgetWaitingPing();
    ///TODO return
    return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.black,currentText:'Союз-Автодор');
  }


  ///TODO метод по умочанию
  @override
  Widget getWidgetProccingError( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger}){
//TODO
    logger.i("starting  getWidgetProccingError");
    IntarfaceWaiting  intarfaceWaiting  = GetWidgetWaitingErrors();

    return intarfaceWaiting.getWidgetWaitingPing(context: context,
        snapshot: snapshot,
        alwaysStop: Colors.red,
        currentText: snapshot.error.toString());
  }




  ///TODO метод есть данные
  @override
  Widget getWidgetProccingNasData( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger}){
//TODO

    var  getPingBack=snapshot.data as     String ;
    logger.i('getPingBack..${getPingBack}'+ " snapshot.hasData..$snapshot.hasData ");


    /*var  backPing=snapshot.data as     List<Map<String, List<Entities1CMap>>>  ;
    logger.i('backPing..${backPing}'+ " snapshot.hasData..$snapshot.hasData ");*/


    logger.i("starting  getWidgetProccingNasData");

    List<Map<String, List<Entities1CMap>>> listMapcallback1c =[];

    return  WidgetSuccessData().getWidgetScaffold(context:context,snapshot:snapshot,listMapcallback1c:listMapcallback1c);
  }



  ///TODO метод нет данных
  @override
  Widget getWidgetProccingDontData( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger}){
//TODO
    logger.i("starting  getWidgetProccingDontData");
    IntarfaceWaiting  intarfaceWaiting   = GetWidgetWaitingDontConnections1C();
    ///TODO return
    return     intarfaceWaiting.getWidgetWaitingPing(context: context,
        snapshot: snapshot,
        alwaysStop: Colors.red,
        currentText: 'Нет данных !!!');
  }



}



































