

import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';


import '../../../data/entities/Entities1CMap.dart';







abstract  class InGetComplete1C {
  //TODO
  Future<String?> getCompletePing({ required Response response1C}) ;
  //TODO
   void    getCompleteCallBackJson({ required Response response1C}) ;

  //TODO
   List<Map<String, Entities1CMap>>     getGeneratorMapCallBack({ required Response response1C}) ;



}




