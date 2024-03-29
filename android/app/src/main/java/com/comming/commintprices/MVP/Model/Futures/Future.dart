
import 'package:flutter/foundation.dart';

import '../ModelPojo/Polos.dart';
import '../ModelPojo/ParcesJson.dart';

import 'package:http/http.dart' as http;

class GetFutures {

  Future<List<Person>> fetchPerson()  async{
   // final Map<String, dynamic> data = new Map<String, dynamic>();
    final response=  await http.get('https://jsonplaceholder.typicode.com/photos' as Uri);
    if (response.statusCode==200) {
      print('response $response');
      return compute((message) =>ParserJson().parserPerson(message),response.body);
    } else {
      print('response $response');
     throw Exception('Request Api Error');
    }
  }



}