
import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:typed_data';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../Converts/GetConverts.dart';

import 'dart:io';

import '../../Jsons/1C/Polo/Person1C.dart';
import 'Interfaces/InFuture1C.dart';

import 'package:dart_json_mapper/dart_json_mapper.dart' show JsonMapper, jsonSerializable,initializeJsonMapper, JsonProperty;
import 'package:dart_json_mapper_flutter/dart_json_mapper_flutter.dart' show flutterAdapter;


class GetFutures1C  implements InFuture1C,InGetComplete1C {


  //TODO
  @override
  Future<List<Person1C>?> fetchPerson( { required  String url }  ) async {
    List<Person1C>? JsonMap;
    try{
    final parsedUrl=Uri.parse(url) as Uri;

    int PublicId=5;
    BigInt Uuid=BigInt.parse('0')  ;


    //TODO base64
    String? basicAuth=     GetConverts().convertBase64(  user: 'dsu1Admin', password: 'dsu1Admin');

    print(' basicAuth  $basicAuth');

   //TODO главный запрос
     await http.get(
        parsedUrl,
      headers: {
        'user':PublicId.toString(),
        'uuid':Uuid.toString(),
        'authorization':'$basicAuth',
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
            print(' get ERROR $e get stacktrace $stacktrace ');
          });





    print(' end  value  ');


      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

  return   JsonMap;
  }



  @override
  void getComplete({required http.Response response1C}) {
    // TODO: implement getComplete

    try{
      print('getComplete $response1C');

        //TODO
        print('response1C.statusCode $response1C.statusCode');

        if (response1C.statusCode==200) {
       /*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/

        //  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;
         var resiljson=JsonMapper.serialize(response1C.bodyBytes);

         print('resiljson $resiljson');

      Map<String, dynamic>? map=    JsonMapper.toMap(response1C.bodyBytes );

         print('map $map');
          //TODO processing String

          var convertDataToJson = response1C.bodyBytes.buffer   ;

      Map<String, dynamic>? map2=    JsonMapper.toMap(convertDataToJson);

          print('convertDataToJson $convertDataToJson');

       //   var stringJson=     response1C.body as String  ;


          print('convertDataToJson $convertDataToJson');


          //TODO proccerig byte


  /*        final  Uint8List uint8listget1CPrices=     response1C.bodyBytes  ;
          final  ByteBuffer buffer2=     response1C.bodyBytes.buffer  ;
          var codes = Uint16List.view(buffer2);
          var content = String.fromCharCodes(codes);
          print('content $content');
          print('uint8listget1CPrices $uint8listget1CPrices');
          final  ByteBuffer byteBuffer=     response1C.bodyBytes.buffer   ;
          print('byteBuffer $byteBuffer');
          var uint16list=   byteBuffer.asInt16List(0) ;
          print('uint16list $uint16list');
          String s1 = new String.fromCharCodes(uint16list);
          print('s1 $s1');




          print('stringJson $stringJson');



     //  Uint16List uint16list=   Uint8List.fromList(uint8listget1CPrices).buffer.asUint16List();


         // String s1 = new String.fromCharCodes(uint16list);
          //print('s $s1');
          String s2 = new String.fromCharCodes(uint8listget1CPrices);
          print('s $s2');

    var outputAsUint8List = new Uint8List.fromList(uint8listget1CPrices);

          print('outputAsUint8List $outputAsUint8List');

          var original=utf8.encode(response1C.body);
          var compressed=gzip.encode(original);
          var decompressed=gzip.decode(compressed);
          print('decompressed $decompressed');*/

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

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

  }







}