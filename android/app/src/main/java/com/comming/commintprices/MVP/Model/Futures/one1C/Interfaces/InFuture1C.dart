
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:flutter/foundation.dart';



import 'package:http/http.dart' as http;
import 'package:http/http.dart';

import '../../../Jsons/1C/Polo/Person1C.dart';





abstract  class InFuture1C {

  Future<List<Person1C>?> getPing1C({ required String   url}) ;

  Future<String?> getStringPing1C({ required String   url}) ;

  Future<Json?> getJsonPing1C({ required String   url}) ;

}





abstract  class InGetComplete1C {

  void getComplete({ required Response response1C}) ;

  String getStringComplete({ required Response response1C}) ;


  Json getJsonComplete({ required Response response1C}) ;




}