
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';

import '../../../Jsons/One1C/Polo/Person1C.dart';
import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';





abstract  class InFuture1C {

  Future<List<Person1Cspoler>?> getPing1C({ required String   url, required int IdUser}) ;

  Future<List<Person1Cspoler>?> getGettingJson1C({ required String   url, required int IdUser}) ;

  Future<List<Person1Cspoler>?> getSend1C({ required String   url, required int IdUser}) ;

}





abstract  class InGetComplete1C {
  //TODO
  void getComplete({ required Response response1C}) ;
}


abstract  class InGetGZip1C {
  //TODO
  String getGZipCSting({ required Response response1C}) ;
  //TODO
  List<int> getGZip1CList({ required Response response1C}) ;

/*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;

}

