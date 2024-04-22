

import 'dart:isolate';


import 'package:logger/logger.dart';

import 'InterfacegetError.dart';



class GetErros  implements InterfaceGetError {


  late Logger logger;


  @override
 Logger  loggers() {
    // TODO: implement loggers
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


}




