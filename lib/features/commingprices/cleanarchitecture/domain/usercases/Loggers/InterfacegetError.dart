

//TODO  абстрантыный метод для Ошибок
import 'package:logger/logger.dart';

abstract class InterfaceGetError{

 Logger  loggers();

 Future<Logger>  getlogger();

}