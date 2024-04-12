
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import '../Errors/ErrorsPrint.dart';
import '../Jsons/Parser/ParcesPerson1.dart';
import '../Jsons/Polo/Polos.dart';
import 'InFuture.dart';




class GetFutures2  implements InGetFutures {


  //TODO
  @override
  Future<List<Person>> fetchPerson( { required  String url }  ) async {
    dynamic JsonMap;
    try{
    // TODO: implement fetchPerson
    final url='https://jsonplaceholder.typicode.com/photos' as String;
    final parsedUrl=Uri.parse(url) as Uri;
    final getresponse= await http.get(parsedUrl);

    if (getresponse.statusCode==200) {
      print('response $getresponse');

      final jsonString=getresponse.body;

      print('jsonString $jsonString');

       JsonMap=ParserJson1().parserPerson(jsonString);


      print('JsonMap $JsonMap');
      return JsonMap;

    } else {
      print('response $getresponse');
    }
    //TODO error
  } on Exception catch (e) {
  PrintingErrors printingErrors= new PrintingErrors();
  printingErrors.printingError(e,'mainTextButton46.dart','main()');
  }
  return   JsonMap;
  }








}