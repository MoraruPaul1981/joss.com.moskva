
import 'package:flutter/foundation.dart';



import 'package:http/http.dart' as http;

import '../Jsons/Polo/Polos.dart';

abstract  class InGetFutures {

  Future<List<Person>> fetchPerson({ required String   url}) ;


}