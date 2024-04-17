
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';

import '../../../Jsons/One1C/Polo/Person1C.dart';
import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';





abstract  class InFuture1C {

  Future<String?> getPing1C({ required String   url }) ;

  Future<List<Person1Cspoler>?> getGettingJson1C({ required String   url, required int IdUser, required int UUID}) ;

  Future<List<Person1Cspoler>?> getSend1C({ required String   url, required int IdUser}) ;

}


