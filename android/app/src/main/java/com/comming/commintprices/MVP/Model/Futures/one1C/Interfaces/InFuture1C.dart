
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
  void getComplete({ required Response response1C}) ;
}


abstract  class InGetDecode1C {
  String Decode1CSting({ required Response response1C}) ;
  List<int> Decode1CByte({ required Response response1C}) ;

/*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;

}

