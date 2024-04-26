
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/getPing.dart';
import '../WidgetAfterData1C/WidgetSuccessData.dart';
import '../WidgetCallBacks/WidgetCallBaks.dart';
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
      //TODO get JSON PING ot 1C
      future:GetPing(). getResponse1cPing(context:context, logger: logger), // TODO метод который и делать пинг с сервером
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) { // AsyncSnapshot<Your object type>


        ////TODO В  ожидание
        if (snapshot.connectionState == ConnectionState.waiting) {
          logger.i('napshot.connectionState$snapshot.connectionState');

          //TODO виджет когда мы ожидаем
          widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingWait(    context:context,   snapshot:snapshot,logger:logger);
          //TODO return
          return widgetWatingCallBack;
        }


/*
        ////TODO В  Сервер закончил Обработки
        if (snapshot.connectionState == ConnectionState.done) {
          logger.i('napshot.connectionState$snapshot.connectionState');


          ///TODO пришли данные
          if (  !snapshot.hasData) {
            logger.i('snapshot.hasData$snapshot.hasData'+'napshot.connectionState$snapshot.connectionState');
            //TODO нет пришгли  данных
            widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingNasData(   context:context,   snapshot:snapshot,logger:logger);
            //TODO return ERROR
            return widgetWatingCallBack;


            //TODO нет пришгли  данных
            //TODO нет пришгли  данных
          } else {
            logger.i('napshot.connectionState$snapshot.connectionState');
            //TODO нет пришгли  данных
            widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingDontData(   context:context,   snapshot:snapshot,logger:logger);
            //TODO return ERROR
            return widgetWatingCallBack;

          }
        }*/


        ///TODO сгенерировальсь Error
        if (!snapshot.hasError) {
          //TODO когда ест данные
          logger.e('napshot.connectionState$snapshot.connectionState'+'snapshot.error.toString()..'+snapshot.error.toString());
          //TODO Возврат по умолчанию

          widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingError( context:context,   snapshot:snapshot,logger:logger);
          //TODO return ERROR
          return widgetWatingCallBack;
        }





        //TODO DEFALUT
        logger.i('napshot.connectionState$snapshot.connectionState');
        //TODO Возврат по умолчанию
        widgetWatingCallBack =WidgetCallBaks().   getWidgetProccingDefault(  context:context,   snapshot:snapshot,logger:logger);
        //TODO return
        return widgetWatingCallBack;

      }
    );
  }



}



































