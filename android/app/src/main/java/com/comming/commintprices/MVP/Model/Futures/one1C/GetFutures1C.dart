
import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:typed_data';

import 'package:dart_json_mapper/src/model/annotations.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../Converts/GetConverts.dart';

import 'dart:io';


import '../../Jsons/One1C/Polo/Person1C.dart';
import '../../Jsons/One1C/Polo/Person1CTwo.dart';
import 'Interfaces/InFuture1C.dart';

import 'package:dart_json_mapper/dart_json_mapper.dart' show JsonMap, JsonMapper, JsonProperty, initializeJsonMapper, jsonSerializable;
import 'package:dart_json_mapper_flutter/dart_json_mapper_flutter.dart' show flutterAdapter;

import 'dart:typed_data' show Uint8List,Uint16List;

class GetFutures1C  implements InFuture1C,InGetComplete1C {


  //TODO
  @override
  Future<List<Person1C>?> getPing1C( { required  String url,required int IdUser }  ) async {
    List<Person1C>? JsonMap;
    try{
    final parsedUrl=Uri.parse(url) as Uri;


    BigInt Uuid=BigInt.parse('0')  ;


    //TODO base64
    String? basicAuth=     GetConverts().convertBase64(  user: 'dsu1Admin', password: 'dsu1Admin');

    print(' basicAuth  $basicAuth');

   //TODO главный запрос
     await http.get(
        parsedUrl,
      headers: {
        'user':IdUser.toString(),
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
            print(' get ERROR $error  ');
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







          final byteData = response1C.bodyBytes.buffer.asByteData();
       final bugffer=   byteData.buffer;

          Uint8List list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes) ;
        //  var list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes);
         String resul= utf8.decode(list);
          print('resul $resul');



          List<dynamic> listDynamic=json.decode(utf8.decode(list))  ;
          print('listDynamic $listDynamic');





        List<Person1C>  person=listDynamic.map((model) => Person1C().fromJsondynamic(  json:  model  )) .toList() as   List<Person1C>;

        List<Person1CTwo>  personTWO=listDynamic.map((model) => Person1CTwo().fromJsondynamic(  json:  model  )) .toList() as   List<Person1CTwo>;


          print('person $person');
          print('personTWO $personTWO');


        } else {
          //TODO
          print('response1C.statusCode $response1C.statusCode');
        }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

  }

  @override
  Json getJsonComplete({required http.Response response1C}) {
    // TODO: implement getJsonComplete
    throw UnimplementedError();
  }

  @override
  Future<Json?> getJsonPing1C({required String url,required int IdUser}) {
    // TODO: implement getJsonPing1C
    throw UnimplementedError();
  }

  @override
  String getStringComplete({required http.Response response1C}) {
    // TODO: implement getStringComplete
    throw UnimplementedError();
  }

  @override
  Future<String?> getStringPing1C({required String url,required int IdUser}) {
    // TODO: implement getStringPing1C
    throw UnimplementedError();
  }







}