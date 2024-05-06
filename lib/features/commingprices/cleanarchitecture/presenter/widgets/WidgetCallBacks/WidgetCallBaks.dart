
import 'dart:async';
import 'dart:isolate';
import 'dart:ui';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/FutureGetPing.dart';
import '../WidgetAfterData1C/WidgetSuccessData.dart';
import '../WidgetWaiting/GetWidgetDefault.dart';
import '../WidgetWaiting/GetWidgetWaitingDontConn.dart';
import '../WidgetWaiting/GetWidgetWaitingErrors.dart';
import '../WidgetWaiting/GetWidgetWaiting.dart';
import '../WidgetWaiting/Intarface/IntarfaceWaiting.dart';
import 'Interface/IntarfaceCallBaks.dart';








//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetCallBaks   implements IntarfaceCallBaks {


  ///TODO  методы ожидания
  @override
  Widget getWidgetProccerWaiting({required BuildContext context,
    required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot,required  Logger logger}){
//TODO
  logger.i("starting  getWidgetProccingWait");
  IntarfaceWaiting  intarfaceWaiting= GetWidgetWaiting();
    ///TODO return
  return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.black,currentText:'Союз-Автодор');
  }



  ///TODO метод по умочанию
   @override
  Widget getWidgetProccingDefault( {required BuildContext context,
     required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot,required  Logger logger}){
//TODO
    logger.i("starting  getWidgetProccingDefault");
    IntarfaceWaiting  intarfaceWaiting= GetWidgetDefault();
    ///TODO return
    return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.black,currentText:'Союз-Автодор');
  }


  ///TODO метод по умочанию
  @override
  Widget getWidgetProccingError( {required BuildContext context,
    required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot,required  Logger logger}){
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
  Widget getWidgetProccingNasData( {required BuildContext context,
    required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot,required  Logger logger}){
    //TODO
    logger.i('receiveddatafromC1CallBack ..  '+snapshot.data.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    //TODO передем на экрна полученные данные
    return  WidgetSuccessData().getWidgetScaffold(context:context,snapshot:snapshot,logger:logger);
  }






  ///TODO метод нет данных
  @override
  Widget getWidgetProccingDontData( {required BuildContext context,
    required AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot,required  Logger logger}){
//TODO
    logger.i("starting  getWidgetProccingDontData");
    IntarfaceWaiting  intarfaceWaiting   = GetWidgetWaitingDontData();
    ///TODO return
    return     intarfaceWaiting.getWidgetWaitingPing(context: context,
        snapshot: snapshot,
        alwaysStop: Colors.red,
        currentText: 'Нет данных !!!');
  }




}



































