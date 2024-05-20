import 'package:commintprices/features/commingprices/cleanarchitecture/presenter/widgets/WidgetWaiting/WidgetStarWaiting.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import 'features/commingprices/cleanarchitecture/domain/usercases/Loggers/GetErrors.dart';
import 'dart:isolate';




  void  main()    {
  try {
    print('Start  void  main() ');

    //TODO starting .....
  startingCommintPrices();

    print('END  void  main() ');
    //TODO error
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }
}







//TODO метод запуска coming prices
    void startingCommintPrices()  {
    try {
      late Logger   logger;
       //TODO int LOGGER
       GetErros().getFuturelogger()
           .then((value) {
         //TODO then
         logger=  value;
         logger.i('start value .. ');


         //TODO starting UI
         runApp(  startingwidgetCommingPrices(  logger: logger));

         logger.i('start value ..  '+'Isolate.current.debugName'+Isolate.current.debugName.toString());
         return logger;

       });
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }


  }




































































































///TODO UI
class startingwidgetCommingPrices extends StatelessWidget {
//TODO log
  Logger logger;
//TODO cunstructor
     startingwidgetCommingPrices( { required this.logger,super.key});



  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),

      debugShowCheckedModeBanner: false,
      home:   StatefulWidgetCommingPrices(logger),
    );
  }

}

class StatefulWidgetCommingPrices extends StatefulWidget {
  Logger logger;
    StatefulWidgetCommingPrices(this. logger,{super.key});
  @override

/*  //TODO widget ROW
  State<StatefulWidgetCommingPrices> createState() => WidgetRow();*////TODO

/*//TODO widget Mail Phone
  State<StatefulWidgetCommingPrices> createState() => WidgetPhoneMail();*/////

  //TODO Comming Prices
  State<StatefulWidgetCommingPrices> createState() => WidgetStarWaiting( logger: logger);//TODO//
  //TODO

  //TODO ENDING widget
}






