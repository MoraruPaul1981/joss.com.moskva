
import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../Errors/ErrorsPrint.dart';
import 'dart:io';
import '../../Jsons/jboss/Polo/Personjboss.dart';
import 'Interfaces/InFutureJboss.dart';

import 'package:logger/logger.dart';

class GetFuturesJbossDebug  implements InFutureJboss,InGetCompleteJboss {

  var logger = Logger(
    printer: PrettyPrinter(
      lineLength: 50,
      colors: true,
      printEmojis: true,
      printTime: true,
  ),);

  //TODO
  @override
  Future<List<Personjboss>?> fetchPerson( { required  String url }  ) async {
    List<Personjboss>? JsonMap;
    try{
    final parsedUrl=Uri.parse(url) as Uri;



     await http.get(
        parsedUrl,
      headers: {
        'Content-Type':'application/gzip ;charset=UTF-8',
        'Accept-Encoding':'gzip,deflate,sdch',
        'Connection':'Keep-Alive',
        'Accept-Language':'ru-RU',
        'identifier':'moraru',
        'p_identifier':'moraru1981',
        'id_device_androis':'8cddcf623bdc9434',

      }
    ).then(( Response backresponsejboss  ) => {

       getComplete( responsejboss: backresponsejboss),

      print(' then value $backresponsejboss'),
    })

        .whenComplete(
          () {
            print(' whenComplete  http.get ');
            print(' whenComplete  http.get ');
          },
    )
        .catchError(
          (Object error) {
            print(' catchError  $error');
            PrintingErrors printingErrors= new PrintingErrors();
            printingErrors.printingError(error,'mainTextButton46.dart','main()');
          });


    logger.i("end fetchPerson");

    //TODO  // Your code goes here
  }   catch (e, stacktrace) {
      logger.e("Got an error here!", error: e ,stackTrace: stacktrace);
  PrintingErrors printingErrors= new PrintingErrors();
  printingErrors.printingError(e,'mainTextButton46.dart','main()');
  }
  return   JsonMap;
  }



  //TODO ответ от сервер отвечаем
  @override
  void getComplete({required http.Response responsejboss}) {
    // TODO: implement getComplete

    try{
      print('getComplete $responsejboss');

      String? stream_size =  responsejboss.headers["stream_size"] as String;

      print('stream_size $stream_size');

      if (stream_size !=null && !stream_size.isEmpty) {
    //TODO
        print('responsejboss.statusCode $responsejboss.statusCode');

        if (responsejboss.statusCode==200) {
          //TODO получаем байты от сервера
          final decoded_data = GZipCodec().decode(responsejboss.bodyBytes);

          //TODO переводим из Gzip в реальнфые данные
        String getPublicId=  utf8.decode(decoded_data, allowMalformed: true);
          print('getPublicId $getPublicId');

   /*       var original=utf8.encode(responsejboss.body);
          var compressed=gzip.encode(original);
          var decompressed=gzip.decode(compressed);
          print('decompressed $decompressed');
*/
/*          List<int> stringBytes = utf8.encode(responsejboss.body);
          List<int> gzipBytes = new  GZipCodec().encode(stringBytes);
          String compressedString = utf8.decode(gzipBytes, allowMalformed: true);

          print('compressedString $compressedString');*/

      /*      Uint8List getuint8list=responsejboss.bodyBytes;

          print('getuint8list $getuint8list');


            GZipCodec gZipCodec=    GZipCodec();
      var byterr=       GZipCodec().encode(getuint8list);

            print('byterr $byterr');*/



        //  List<Person1>? JsonMap=ParserJson1().parserPerson(jsonString);

         // print('JsonMap $JsonMap');

        } else {
          //TODO
          print('responsejboss.statusCode $responsejboss.statusCode');
        }
      }
     // TODO
      print('stream_size $stream_size');
    }   catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }

  }



}