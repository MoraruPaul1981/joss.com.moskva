

import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../../../data/entities/Entities1CMap.dart';



abstract  class InFuture1C {
  //TODO
  Future<Response?> getDownloadJsonMaps({ required String   url, required int IdUser, required int UUID,required Logger logger}) ;



  //TODO
  List<Map<String, List<Entities1CMap>>>?  getGeneratorMapCallBack({required  Response response1C,required Logger logger}) ;


}


