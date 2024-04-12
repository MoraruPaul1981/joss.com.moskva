
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import '../Errors/ErrorsPrint.dart';
import '../Jsons/Parser/ParcesPerson1.dart';
import '../Jsons/Polo/Person1.dart';
import 'InFuture.dart';




class GetFuturesSous  implements InGetFutures {


  //TODO
  @override
  Future<List<Person1>> fetchPerson( { required  String url }  ) async {
    dynamic JsonMap;
    try{
    final parsedUrl=Uri.parse(url) as Uri;



    final getresponse= await http.get(
        parsedUrl,
      headers: {
          'Content-Type , application/gzip ':'charset=UTF-8',
        'Accept-Encoding':'gzip,deflate,sdch',
        'Connection':'Keep-Alive',
        'Accept-Language':'ru-RU',
        'identifier':'moraru',
        'p_identifier':'moraru1981',
        'id_device_androis':'8cddcf623bdc9434',

      }
    ).whenComplete(
          () => print(' whenComplete  http.get '),
    )
        .catchError(
          (Object error) => print(' http.get Error: $error'),
    );




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
  }   catch (e) {
  PrintingErrors printingErrors= new PrintingErrors();
  printingErrors.printingError(e,'mainTextButton46.dart','main()');
  }
  return   JsonMap;
  }








}