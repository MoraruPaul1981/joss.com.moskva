
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';

import '../../../Jsons/One1C/Polo/Person1C.dart';





abstract  class InFuture1C {

  Future<List<Person1C>?> getPing1C({ required String   url, required int IdUser}) ;

  Future<String?> getStringPing1C({ required String   url,required  int IdUser}) ;

  Future<Json?> getJsonPing1C({ required String   url, required int IdUser}) ;

}





abstract  class InGetComplete1C {

  void getComplete({ required Response response1C}) ;

  String getStringComplete({ required Response response1C}) ;

  Json getJsonComplete({ required Response response1C}) ;




}