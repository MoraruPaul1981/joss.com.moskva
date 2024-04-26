
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/getPing.dart';
import '../WidgetAfterData1C/WidgetSuccessData.dart';
import 'GetWidgetWaitingDontConnections1C.dart';
import 'GetWidgetWaitingErrors.dart';
import 'GetWidgetWaitingPing.dart';
import 'Intarface/IntarfaceWaiting.dart';







//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetStarWaiting extends State<StatefulWidgetCommingPrices> {
  Logger logger;
  //TODO json data
  WidgetStarWaiting({ required this.logger } );

  @override
  Widget build(BuildContext context) {
    //TODO starting Future
    logger.i('  stating Ping FutureBuilder  ');
    return getFutureBuilder();
  }

  //TODO метод получени пинга сервер аи в будущем получени еданных 1С
  FutureBuilder<String> getFutureBuilder() {

    ///TODO return Widget
   late Widget widgetWatingCallBack;

    return FutureBuilder<String>(
      future:GetPing(). getResponse1cPing(context:context, logger: logger), // TODO метод который и делать пинг с сервером
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) { // AsyncSnapshot<Your object type>


        ////TODO В  ожидание
        if (snapshot.connectionState == ConnectionState.waiting) {
          logger.i('napshot.connectionState$snapshot.connectionState');

          //TODO виджет когда мы ожидаем
          widgetWatingCallBack =  getWidgetProccingWait(    context,   snapshot);
          //TODO return
          return widgetWatingCallBack;
        }



        ////TODO В  Сервер закончил Обработки
        if (snapshot.connectionState == ConnectionState.done) {
          logger.i('napshot.connectionState$snapshot.connectionState');

          ///TODO пришли данные
          if (  snapshot.hasData) {
            logger.i('snapshot.hasData$snapshot.hasData'+'napshot.connectionState$snapshot.connectionState');
            //TODO нет пришгли  данных
            widgetWatingCallBack =  getWidgetProccingNasData(   context,   snapshot);
            //TODO return ERROR
            return widgetWatingCallBack;

          } else {
            logger.i('napshot.connectionState$snapshot.connectionState');
            //TODO нет пришгли  данных
            widgetWatingCallBack =  getWidgetProccingDontData(   context,   snapshot);
            //TODO return ERROR
            return widgetWatingCallBack;

          }
        }



        ///TODO сгенерировальсь Error
        if (snapshot.hasError) {
          //TODO когда ест данные
          logger.e('napshot.connectionState$snapshot.connectionState'+'snapshot.error.toString()..'+snapshot.error.toString());
          //TODO Возврат по умолчанию

          widgetWatingCallBack =  getWidgetProccingError(   context,   snapshot);
          //TODO return ERROR
          return widgetWatingCallBack;
        }





        //TODO DEFALUT
        logger.i('napshot.connectionState$snapshot.connectionState');
        //TODO Возврат по умолчанию
        widgetWatingCallBack =  getWidgetProccingDefault(   context,   snapshot);
        //TODO return
        return widgetWatingCallBack;

      }
    );
  }





  ///TODO  методы ожидания
  Widget getWidgetProccingWait( BuildContext context, AsyncSnapshot<String> snapshot){
//TODO
  logger.i("starting  getWidgetProccingWait");
  IntarfaceWaiting  intarfaceWaiting= GetWidgetWaitingPing();
    ///TODO return
  return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.white,currentText:'Союз-Автодор');
  }




  ///TODO метод по умочанию
  Widget getWidgetProccingDefault( BuildContext context, AsyncSnapshot<String> snapshot){
//TODO
    logger.i("starting  getWidgetProccingDefault");
    IntarfaceWaiting  intarfaceWaiting= GetWidgetWaitingPing();
    ///TODO return
    return   intarfaceWaiting.
    getWidgetWaitingPing(context:context, snapshot:snapshot,
        alwaysStop:Colors.black,currentText:'Союз-Автодор');
  }


  ///TODO метод по умочанию
  Widget getWidgetProccingError( BuildContext context, AsyncSnapshot<String> snapshot){
//TODO
    logger.i("starting  getWidgetProccingError");
    IntarfaceWaiting  intarfaceWaiting  = GetWidgetWaitingErrors();

    return intarfaceWaiting.getWidgetWaitingPing(context: context,
        snapshot: snapshot,
        alwaysStop: Colors.red,
        currentText: snapshot.error.toString());
  }




  ///TODO метод есть данные
  Widget getWidgetProccingNasData( BuildContext context, AsyncSnapshot<String> snapshot){
//TODO


    var  getPingBack=snapshot.data as     String ;
    logger.i('getPingBack..${getPingBack}'+ " snapshot.hasData..$snapshot.hasData ");



    var  listMapcallback1c=snapshot.data as     List<Map<String, List<Entities1CMap>>>  ;
    logger.i('listMapcallback1c..${listMapcallback1c}'+ " snapshot.hasData..$snapshot.hasData ");


    logger.i("starting  getWidgetProccingNasData");

    List<Map<String, List<Entities1CMap>>> listMapcallback1c =[];

    return  WidgetSuccessData().getWidgetScaffold(context:context,snapshot:snapshot,listMapcallback1c:listMapcallback1c);
  }



  ///TODO метод нет данных
  Widget getWidgetProccingDontData( BuildContext context, AsyncSnapshot<String> snapshot){
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



































