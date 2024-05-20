

import 'dart:isolate';


import 'package:logger/logger.dart';

import '../../../../../../main.dart';
import 'InterfacegetError.dart';



class GetErros  implements InterfaceGetError {





  @override
 Logger  loggers() {
    // TODO: implement loggers
    late Logger logger;
    try{
      logger = Logger(
        printer: PrettyPrinter(
          lineLength: 50,
          colors: true,
          printEmojis: true,
          printTime: true,
        ),)  ;

      logger.i('logger ..  ');

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return logger;
  }

  @override
  Future<Logger> getFuturelogger() async {
    // TODO: implement getlogger
   late Logger  loggerfuture;
    // TODO: implement loggers
    try{
        loggerfuture = Logger(
          printer: PrettyPrinter(
            lineLength: 50,
            colors: true,
            printEmojis: true,
            printTime: true,
          ),);
        loggerfuture.i('logger ..$loggerfuture  ');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return    loggerfuture ;
  }


}




