import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';





abstract  class IntarfaceCallBaks {

  //TODO
  Widget getWidgetProccingWait({required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger});
  //TODO
  Widget getWidgetProccingDefault( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger});
  //TODO
  Widget getWidgetProccingError( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger});
//TODO
  Widget getWidgetProccingNasData( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger});
  //TODO
  Widget getWidgetProccingDontData( {required BuildContext context, required AsyncSnapshot<String> snapshot,required  Logger logger});

}


