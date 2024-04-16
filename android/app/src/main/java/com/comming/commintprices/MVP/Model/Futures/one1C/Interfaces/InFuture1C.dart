
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:http/http.dart';

import '../../../Jsons/One1C/Polo/Person1C.dart';





abstract  class InFuture1C {

  Future<List<Person1C>?> getPing1C({ required String   url, required int IdUser}) ;


}





abstract  class InGetComplete1C {

  void getComplete({ required Response response1C}) ;

}


abstract  class InGeDecode1C {

  String Decode1CSting({ required Response response1C}) ;

  List<int> Decode1CByte({ required Response response1C}) ;



/*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;




}

abstract  class InGetListDynamic1C {

  List<dynamic> getList1cDynamic({ required Response response1C}) ;



/*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;




}