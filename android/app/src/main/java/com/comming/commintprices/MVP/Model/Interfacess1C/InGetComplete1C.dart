
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';

import '../Jsons/One1C/Polo/Person1CList.dart';
import '../Jsons/One1C/Polo/Person1CMap.dart';



abstract  class InGetComplete1C {
  //TODO
  Future<String?> getCompletePing({ required Response response1C}) ;


  //TODO
   void    getCompleteCallBackJson({ required Response response1C}) ;

   List<Person1CList>     getGeneratorListCallBack({ required Response response1C}) ;

   List<Map<String, Person1CMap>>     getGeneratorMapCallBack({ required Response response1C}) ;



}




