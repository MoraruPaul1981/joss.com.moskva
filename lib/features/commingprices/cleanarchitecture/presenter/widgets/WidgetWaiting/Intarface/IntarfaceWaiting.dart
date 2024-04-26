import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';





abstract  class IntarfaceWaiting {

  //TODO
  Widget getWidgetWaitingPing({required BuildContext context,
    required AsyncSnapshot<String> snapshot,required Color alwaysStop,required String currentText});


}


