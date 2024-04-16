
import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:typed_data';

import 'package:dart_json_mapper/src/model/annotations.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import '../../../View/mainFuture.dart';
import '../../Converts/GetConverts.dart';

import 'dart:io';

import 'Interfaces/InGetComplete1C.dart';
import 'Interfaces/InGetGZip1C.dart';
import 'Interfaces/InParserJson1c.dart';
import '../../Jsons/One1C/Polo/Person1C.dart';
import '../../Jsons/One1C/Polo/Person1Cspoler.dart';
import 'Interfaces/InFuture1C.dart';

import 'package:dart_json_mapper/dart_json_mapper.dart' show JsonMap, JsonMapper, JsonProperty, initializeJsonMapper, jsonSerializable;
import 'package:dart_json_mapper_flutter/dart_json_mapper_flutter.dart' show flutterAdapter;

import 'dart:typed_data' show Uint8List,Uint16List;

class GetFutures1C  implements InFuture1C,InGetComplete1C ,InParserJson1c ,InGetGZip1C{

  late Future<List<Person1Cspoler>?> personSpoler;
  late  Future<String?>  getCallPing1c;

  //TODO get Ping 1C
  @override
  Future<String?> getPing1C( { required  String url,required int IdUser }  ) async {
    //TODO CAll PING
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

      //TODO PING
       getCompletePing( response1C: backresponsejboss),

      print(' then then $backresponsejboss'),

    })
        .whenComplete(
          () {
            print(' whenComplete  personSpoler $personSpoler' );
          },
    )
        .catchError(
          (Object error) {
            print(' get ERROR $error  ');
          });


      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

  return   getCallPing1c;
  }



//TODO get JSON
  @override
  Future<List<Person1Cspoler>?> getGettingJson1C({required String url, required int IdUser, required int UUID}) async {
    // TODO: implement getGettingJson1C
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

        getCompleteCallBackJson( response1C: backresponsejboss),

        print(' then then $backresponsejboss'),

      })
          .whenComplete(
            () {
          print(' whenComplete  personSpoler $personSpoler' );
        },
      )
          .catchError(
              (Object error) {
            print(' get ERROR $error  ');
          });


      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return   personSpoler;
  }














  @override
  List<dynamic>  getList1cDynamic({required http.Response response1C}) {
    var   getList1cdynamic;
    try{
    final byteData = response1C.bodyBytes.buffer.asByteData();
    final bugffer=   byteData.buffer;
    Uint8List list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes) ;
    //TODO
    getList1cdynamic=json.decode(utf8.decode(list)) as List<dynamic>  ;
    //TODO
    print('getList1cdynamic $getList1cdynamic');
    //TODO error
  }   catch (e, stacktrace) {
  print(' get ERROR $e get stacktrace $stacktrace ');
}
return getList1cdynamic;
  }


  @override
  String getPingDynamic({required http.Response response1C}) {
    // TODO: implement getStringcDynamic
    var   getPing1C;
    try{
      final byteData = response1C.bodyBytes.buffer.asByteData();
      final bugffer=   byteData.buffer;
      Uint8List list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes) ;
      //TODO
      getPing1C=json.decode(utf8.decode(list)) as String ;
      //TODO
      print('getPing1C $getPing1C');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getPing1C;
  }














  //TODO Пока не используются но скоро будет
  @override
  List<int> getGZip1CList({required http.Response response1C}) {
   late List<int> uint8listget1CPrices;
    try{
    // TODO: implement Decode1CByte
        final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          uint8listget1CPrices=     response1C.bodyBytes;
        /*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;
        print('uint8listget1CPrices $uint8listget1CPrices');
        //TODO error
      }   catch (e, stacktrace) {
        print(' get ERROR $e get stacktrace $stacktrace ');
      }
      return uint8listget1CPrices;

}

  @override
  String getGZipCSting({required http.Response response1C}) {
    // TODO: implement Decode1CSting
    // TODO: implement Decode1CSting
    late String getExplorDEcor;
    try{
      final decoded_data = GZipCodec().decode(response1C.bodyBytes);
      getExplorDEcor=  utf8.decode(decoded_data, allowMalformed: true);
      /*   final decoded_data = GZipCodec().decode(response1C.bodyBytes);
          String d=  utf8.decode(decoded_data, allowMalformed: true);*/
//  final  List<int> uint8listget1CPrices=     response1C.bodyBytes  ;
      print('getExplorDEcor $getExplorDEcor');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getExplorDEcor;
  }







  @override
  void getCompleteCallBackJson({required http.Response response1C}) {
    // TODO: implement getCompleteCallBackJson
    try{
      print('getComplete $response1C');
      //TODO
      print('response1C.statusCode $response1C.statusCode');
      if (response1C.statusCode==200) {
        List<dynamic>  listDynamic=  getList1cDynamic(response1C: response1C);
        print('listDynamic $listDynamic');

        //TODO получаем данные JSON
        ///List<Person1C>  person=listDynamic.map((model) => Person1C().fromJsondynamic(  json:  model  )) .toList() as   List<Person1C>;
        personSpoler=listDynamic.map((model) => Person1Cspoler().fromJsondynamic(  json:  model  )) .toList() as   Future<List<Person1Cspoler>?>;
        // print('person $person');
        print('personSpoler $personSpoler');
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
   void  getCompletePing({required http.Response response1C}) async {
    // TODO: implement getCompletePing
    try{
      print('getComplete $response1C');
      //TODO
      print('response1C.statusCode $response1C.statusCode');
      if (response1C.statusCode==200) {
        //TODO realy ping
        getCallPing1c=  getPingDynamic(response1C: response1C) as Future<String?> ;
        print('getCallPing1c $getCallPing1c');

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
  Future<List<Person1Cspoler>?> getSend1C({required String url, required int IdUser}) {
    // TODO: implement getSend1C
    throw UnimplementedError();
  }






}
