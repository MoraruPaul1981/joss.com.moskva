
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';


import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';
import '../../../Jsons/One1C/Polo/Person1NestedList.dart';

abstract  class InGetComplete1C {
  //TODO
  Future<String?> getCompletePing({ required Response response1C}) ;


  //TODO
   void    getCompleteCallBackJson({ required Response response1C}) ;

   List<Person1Cspoler>?     getCompleteFutureCallBackJson({ required Response response1C}) ;

   List<Person1NestedList>?     getCompleteFutureMapCallBackJson({ required Response response1C}) ;



}




