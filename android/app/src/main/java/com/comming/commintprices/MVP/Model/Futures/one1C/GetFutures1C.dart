
import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../Converts/GetConverts.dart';
import '../../Errors/ErrorsPrint.dart';
import 'dart:io';

import '../../Jsons/1C/Polo/Person1C.dart';
import 'Interfaces/InFuture1C.dart';



class GetFutures1C  implements InFuture1C,InGetComplete1C {


  //TODO
  @override
  Future<List<Person1C>?> fetchPerson( { required  String url }  ) async {
    List<Person1C>? JsonMap;
    try{
    final parsedUrl=Uri.parse(url) as Uri;

  int PublicId=8;
    BigInt Uuid=0 as BigInt;


    //TODO base64
    String? credentials1C=     GetConverts().convertBase64(  user: 'dsu1Admin', password: 'dsu1Admin');
    print(' credentials1C  $credentials1C');


     await http.get(
        parsedUrl,
      headers: {
        'user':PublicId.toString(),
        'uuid':Uuid.toString(),
        'Authorization':'Basic  $credentials1C',
      }
    ).then(( Response backresponsejboss  ) => {

       getComplete( response1C: backresponsejboss),

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



  @override
  void getComplete({required http.Response response1C}) {
    // TODO: implement getComplete

    try{
      print('getComplete $response1C');

      String? stream_size =  response1C.headers["stream_size"] as String;

      print('stream_size $stream_size');

      if (stream_size !=null && !stream_size.isEmpty) {
        //TODO
        print('response1C.statusCode $response1C.statusCode');

        if (response1C.statusCode==200) {



          final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);

          print('d $d');

          var original=utf8.encode(response1C.body);
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
          print('response1C.statusCode $response1C.statusCode');
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