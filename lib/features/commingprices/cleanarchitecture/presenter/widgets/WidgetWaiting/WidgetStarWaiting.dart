
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';


import '../../../data/entities/Entities1CListManual.dart';
import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/getPing.dart';
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

    ///TODO
   late  IntarfaceWaiting intarfaceWaiting;

    return FutureBuilder<String>(
      future:GetPing(). getResponse1cPing(context:context, logger: logger), // TODO метод который и делать пинг с сервером
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) { // AsyncSnapshot<Your object type>


        ////TODO В  ожидание
        if (snapshot.connectionState == ConnectionState.waiting) {
          logger.i('napshot.connectionState$snapshot.connectionState');

          //TODO виджет когда мы ожидаем
          intarfaceWaiting= GetWidgetWaitingPing();
          ///TODO return
          return intarfaceWaiting.
          getWidgetWaitingPing(context:context, snapshot:snapshot,
              alwaysStop:Colors.white,currentText:'Союз-Автодор');
        }



        ////TODO В  Сервер закончил Обработки
/*        if (snapshot.connectionState == ConnectionState.done) {
          logger.i('napshot.connectionState$snapshot.connectionState');

          ///TODO пришли данные
          if (  snapshot.hasData) {
            logger.i('snapshot.hasData$snapshot.hasData');

            intarfaceWaiting= GetWidgetWaitingPing();
            //TODO
            return intarfaceWaiting.
            getWidgetWaitingPing(context:context, snapshot:snapshot,
                alwaysStop:Colors.red,currentText:'Союз-Автодор');


            var  getPingBack=snapshot.data as     String ;
            logger.i('getPingBack..${getPingBack}'+ " snapshot.hasData..$snapshot.hasData ");



           var  listMapcallback1c=snapshot.data as     List<Map<String, List<Entities1CMap>>>  ;
            logger.i('listMapcallback1c..${listMapcallback1c}'+ " snapshot.hasData..$snapshot.hasData ");

            //TODO когда ест данные
           // return   WidgetSuccessData().getWidgetScaffold(context:context, snapshot:snapshot,listMapcallback1c:  listMapcallback1c );

          } else {
            //TODO нет пришгли  данных
            intarfaceWaiting= GetWidgetWaitingDontConnections1C();
            ///TODO return
            return     intarfaceWaiting.getWidgetWaitingPing(context: context,
                snapshot: snapshot,
                alwaysStop: Colors.red,
                currentText: 'выкл. Сервер !!!');

          }
        }*/



        ///TODO сгенерировальсь Error
        if (snapshot.hasError) {
          //TODO когда ест данные
          logger.i('napshot.connectionState$snapshot.connectionState');
          //TODO Возврат по умолчанию
          intarfaceWaiting = GetWidgetWaitingErrors();

          ///TODO return
          return intarfaceWaiting.getWidgetWaitingPing(context: context,
              snapshot: snapshot,
              alwaysStop: Colors.red,
              currentText: snapshot.error.toString());
        }


        //TODO Возврат по умолчанию
        intarfaceWaiting= GetWidgetWaitingPing();
        ///TODO return
        return intarfaceWaiting.
        getWidgetWaitingPing(context:context, snapshot:snapshot,
            alwaysStop:Colors.black,currentText:'Союз-Автодор');

      }
    );
  }

}




































