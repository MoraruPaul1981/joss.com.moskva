
import 'package:commintprices/commingprices/features/mvp/model/Jsons/jboss/Polo/Personjboss.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';






abstract  class InFutureJboss {

  Future<List<Personjboss>?> fetchPerson({ required String   url}) ;


/* final url='https://jsonplaceholder.typicode.com/photos' as String;
      // TODO: implement fetchPerson
      final url='https://jsonplaceholder.typicode.com/photos' as String;
      */


}


abstract  class InGetCompleteJboss {

  void getComplete({ required Response responsejboss}) ;




}