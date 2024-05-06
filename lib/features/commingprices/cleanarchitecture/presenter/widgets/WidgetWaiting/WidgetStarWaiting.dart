
import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';

import '../../../data/entities/Entities1CMap.dart';
import '../../../data/remote/Futures/FutureGetPing.dart';
import '../WidgetCallBacks/WidgetCallBaks.dart';







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
  FutureBuilder<List<Map<String, List<Entities1CMap>>>> getFutureBuilder() {

    ///TODO return Widget
   late Widget widgetWatingCallBack;




   ///TODO возращаем call back
    return FutureBuilder<List<Map<String, List<Entities1CMap>>>>(
      //TODO get JSON PING ot 1C
      future:   FutureGetPing(). getResponse1c(context:context, logger: logger),
      builder: (BuildContext context, AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot) {

   //TODO само обработка
        ////TODO В  waiting
        if (snapshot.connectionState == ConnectionState.waiting) {
          logger.i('napshot.connectionState$snapshot.connectionState');
          //TODO Возврат по умолчанию
          widgetWatingCallBack =WidgetCallBaks().   getWidgetProccerWaiting(  context:context,   snapshot:snapshot,logger:logger);
          //TODO return
          return widgetWatingCallBack;

        }

        ////TODO В  Сервер закончил Обработки
        if (snapshot.connectionState == ConnectionState.done) {
          logger.i('napshot.connectionState$snapshot.connectionState');
          ///
          var  isArray=snapshot.data as   List<Map<String, List<Entities1CMap>>>;
          ///TODO пришол ПИНГ УСпешный
          if (   snapshot.hasData && isArray.length>0) {

            logger.i('snapshot.hasData...$snapshot.hasData'+'isArray.length...$isArray.length'
                +'napshot.connectionState$snapshot.connectionState');

            //TODO ПРИШЛИ ДАННЫЕ
            widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingNasData(   context:context,   snapshot:snapshot,logger:logger);
            //TODO return ERROR
            return widgetWatingCallBack;


            //TODO ПИНГ Данные НЕТ
          } else {
            logger.i('napshot.connectionState$snapshot.connectionState');
            //TODO нет пришгли  данных
            widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingDontData(   context:context,   snapshot:snapshot,logger:logger);
            //TODO return ERROR
            return widgetWatingCallBack;

          }
        }


        ///TODO сгенерировальсь Error
        if (snapshot.hasError) {
          //TODO когда ест данные
          logger.e('napshot.connectionState$snapshot.connectionState'+'snapshot.error.toString()..'+snapshot.error.toString());
          //TODO Возврат по умолчанию
          widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingError( context:context,   snapshot:snapshot,logger:logger);
          //TODO return ERROR
          return widgetWatingCallBack;
        }




        //TODO DEFALUT
        logger.i('napshot.connectionState$snapshot.connectionState');
        //TODO нет пришгли  данных
        widgetWatingCallBack = WidgetCallBaks().  getWidgetProccingDefault(   context:context,   snapshot:snapshot,logger:logger);
        //TODO return ERROR
        return widgetWatingCallBack;


    }, // TODO метод который и делать пинг с сервером

    );
  }



}



































