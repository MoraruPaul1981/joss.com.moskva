
import 'dart:convert';
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../Errors/ErrorsPrint.dart';
import '../../Jsons/Parser/ParcesPerson1Jboss.dart';
import '../../Jsons/Polo/Person1jboss.dart';
import '../InFuture.dart';
import 'dart:io';



class GetFutures1C  implements InGetFutures,InGetComplete {


  //TODO
  @override
  Future<List<Person1>?> fetchPerson( { required  String url }  ) async {
    List<Person1>? JsonMap;
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





    print(' end  value  ');


    //TODO error
  }   catch (e) {
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



          final decoded_data = GZipCodec().decode(responsejboss.bodyBytes);
        String d=  utf8.decode(decoded_data, allowMalformed: true);

          print('d $d');

          var original=utf8.encode(responsejboss.body);
          var compressed=gzip.encode(original);
          var decompressed=gzip.decode(compressed);
          print('decompressed $decompressed');

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