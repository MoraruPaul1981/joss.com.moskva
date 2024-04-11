
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import '../Jsons/Polo/Polos.dart';
import 'InFuture.dart';




class GetFutures  implements InGetFutures {

  //TODO
  @override
  Future<List<Person>> fetchPerson( { required  String url }  ) async {
    // TODO: implement fetchPerson
    final url='https://jsonplaceholder.typicode.com/photos' as String;
    final parsedUrl=Uri.parse(url) as Uri;
    final getresponse= await http.get(parsedUrl);

    if (getresponse.statusCode==200) {
      print('response $getresponse');

      final jsonString=getresponse.body;

      print('jsonString $jsonString');

      dynamic JsonMap=ParserJson().parserPerson(jsonString);


      print('JsonMap $JsonMap');
      return JsonMap;

    } else {
      print('response $getresponse');
      throw Exception('Request Api Error');
    }
  }








}