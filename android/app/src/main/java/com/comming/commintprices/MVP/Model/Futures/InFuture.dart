
import 'package:flutter/foundation.dart';



import 'package:http/http.dart' as http;

import '../Jsons/Polo/Person1.dart';

abstract  class InGetFutures {

  Future<List<Person1>> fetchPerson({ required String   url}) ;


/* final url='https://jsonplaceholder.typicode.com/photos' as String;
      // TODO: implement fetchPerson
      final url='https://jsonplaceholder.typicode.com/photos' as String;
      */


}