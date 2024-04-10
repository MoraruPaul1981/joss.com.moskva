
import 'package:flutter/foundation.dart';

import '../ModelPojo/Polos.dart';
import '../ModelPojo/ParcesJson.dart';

import 'package:http/http.dart' as http;

class GetFutures {




  Future<List<Person>> fetchPerson()  async{
   // final Map<String, dynamic> data = new Map<String, dynamic>();
 final url='https://jsonplaceholder.typicode.com/photos' as String;
 final parsedUrl=Uri.parse(url) as Uri;
   final getresponse= await http.get(parsedUrl);

    if (getresponse.statusCode==200) {
      print('response $getresponse');

      final jsonString=getresponse.body;

      print('jsonString $jsonString');

      dynamic JsonMap=ParserJson().parserPerson(jsonString);


      print('JsonMap $JsonMap');

   final jsonString2= Person.fromJson(JsonMap) as String;

      print('jsonString2 $jsonString2');


      return JsonMap;
    } else {
      print('response $getresponse');
     throw Exception('Request Api Error');
    }
  }



}