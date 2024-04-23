
import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';

import '../../../domain/entities/Entities1CList.dart';
import '../../../domain/entities/Entities1CMap.dart';
import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/Interfaces/InFuture1C.dart';
import '../../../domain/usercases/Interfaces/InGetComplete1C.dart';
import '../../../domain/usercases/Interfaces/InParserJson1c.dart';





class GetFutures1C  implements InFuture1C,InGetComplete1C ,InParserJson1c {

  //TODO
  late List<Entities1CList> getPerson1CList;
  //TODO
  late List<Map<String, Entities1CMap>>?  getPerson1CMap;




  //TODO get Ping 1C
  @override
  Future<String?> getPing1C( { required  String url  }  ) async {
    //TODO CAll PING
    late var  getPing;
    try{
    final parsedUrl=Uri.parse(url) as Uri;
    BigInt Uuid=BigInt.parse('0')  ;
    int IdUser=0;
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
      //TODO ping worker server
         getPing=    getCompletePing( response1C: backresponsejboss) as Future<String?>,
         print(' then getPing $getPing'),
         print( ' backresponsejboss..$backresponsejboss'),
    })
        .whenComplete(
          () {
            print(' whenComplete  PINg' );
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

  return   getPing;
  }



//TODO get JSON
  @override
  Future<List<Entities1CList>> getDownloadJsonList({required String url, required int IdUser, required int UUID}) async {
    // TODO: implement getGettingJson1C
    late var getJson1cSucces;
    try{
      final parsedUrl=Uri.parse(url) as Uri;
      BigInt Uuid=BigInt.parse(UUID.toString())  ;
      //TODO Paramerts
      print('parsedUrl..$parsedUrl'+'Uuid..$Uuid'+ 'IdUser..$IdUser');

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

    //    getCompleteCallBackJson( response1C: backresponsejboss)    ,

        getJson1cSucces=  getGeneratorListCallBack(response1C: backresponsejboss)as   List<Entities1CList>  ,

        print(' then getJson1cSucces $getJson1cSucces'),

      })
          .whenComplete(
            () {
         // print(' whenComplete  getjson1C $getjson1C' );
        print(' whenComplete getJson1cSuccess ' );
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

    return   getJson1cSucces;
  }






















  @override
   List<Entities1CList>  getGeneratorListCallBack({required http.Response response1C}) {
    // TODO: implement getCompleteFutureCallBackJson
  late var getJson1cSucces;

// TODO: implement getCompleteCallBackJson
    // TODO: implement getComplete
    try{
      print('getComplete $response1C');
      //TODO
      print('response1C.statusCode $response1C.statusCode');
      if (response1C.statusCode==200) {

        //TODO
        print('response1C. contentLength....$response1C. contentLength');
        if (response1C. contentLength!>100) {

          List<dynamic>  listDynamic=  getList1cDynamic(response1C: response1C);
          print('listDynamic $listDynamic');

          if (listDynamic.isNotEmpty ) {


            //TODO получаем данные JSON
            print('listDynamic.isNotEmpty ..$listDynamic.isNotEmpty '+'listDynamic.contains(2) .. $listDynamic.contains(2)');

            ///TODO
           getJson1cSucces=listDynamic.map((model) => Entities1CList().loopGeneratorListPolo(  json:  model  )) .toList() as   List<Entities1CList?>;

            print('getJson1cSucces ..$getJson1cSucces');
            //TODO
            print('getJson1cSuccess..$getPerson1CList');

          }else{

            //TODO
            print('istDynamic.isEmpty..$listDynamic.isEmpty');
          }

        } else {
          print('response1C. contentLength..$response1C. contentLength');

          //TODO PING
          String?  getCallPing1c= getPingDynamicDontaunt(response1C: response1C) as  String?   ;
          print('getCallPing1c $getCallPing1c');

          List<dynamic>  listDynamic=   [];
          ///TODO
          getJson1cSucces=listDynamic.map((model) => Entities1CList().loopGeneratorListPolo(  json:  model  )) .toList() as   List<Entities1CList>;
          //TODO
          print('getJson1cSuccess..$getPerson1CList');
        }

        print('personSpoler $getPerson1CList');
      } else {
        //TODO
        print('response1C.statusCode $response1C.statusCode');
      }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return getJson1cSucces;
  }




















  @override
  List  getList1cDynamic({required http.Response response1C}) {
    var   getList1cdynamic;
    try{
    final byteData = response1C.bodyBytes.buffer.asByteData();
    final bugffer=   byteData.buffer;
    Uint8List list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes) ;
    //TODO
    getList1cdynamic=json.decode(utf8.decode(list))   ;
    //TODO
    print('getList1cdynamic $getList1cdynamic');
    //TODO error
  }   catch (e, stacktrace) {
  print(' get ERROR $e get stacktrace $stacktrace ');
}
return getList1cdynamic;
  }


  @override
  String getPingDynamicDontaunt({required http.Response response1C}) {
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























  @override
  void  getCompleteCallBackJson({required http.Response response1C}) {
    // TODO: implement getCompleteCallBackJson
    // TODO: implement getComplete
    try{
      print('getComplete $response1C');
      //TODO
      print('response1C.statusCode $response1C.statusCode');
      if (response1C.statusCode==200) {

        //TODO
        print('response1C. contentLength....$response1C. contentLength');
        if (response1C. contentLength!>100) {

          List<dynamic>  listDynamic=  getList1cDynamic(response1C: response1C);
          print('listDynamic $listDynamic');

          if (listDynamic.isNotEmpty  ) {

                    //TODO получаем данные JSON
            print('listDynamic.isNotEmpty ..$listDynamic.isNotEmpty '+'listDynamic.contains(2) .. $listDynamic.contains(2)');

                    ///List<Person1C>  person=listDynamic.map((model) => Person1C().fromJsondynamic(  json:  model  )) .toList() as   List<Person1C>;
                    ///TODO
                    getPerson1CList=listDynamic.map((model) => Entities1CList().loopGeneratorListPolo(  json:  model  )) .toList() as   List<Entities1CList>;
                 //TODO
                 print('getJson1cSuccess..$getPerson1CList');

                  }else{

            //TODO
            print('istDynamic.isEmpty..$listDynamic.isEmpty');
          }

        } else {
          print('response1C. contentLength..$response1C. contentLength');

          //TODO PING
        String?  getCallPing1c= getPingDynamicDontaunt(response1C: response1C) as  String?   ;
          print('getCallPing1c $getCallPing1c');

          List<dynamic>  listDynamic=   [];
          ///TODO
          getPerson1CList=listDynamic.map((model) => Entities1CList().loopGeneratorListPolo(  json:  model  )) .toList() as   List<Entities1CList>;
          //TODO
          print('getJson1cSuccess..$getPerson1CList');
        }

        print('personSpoler $getPerson1CList');
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
  Future<String?>    getCompletePing({required http.Response response1C}) async {
    // TODO: implement getCompletePing
    late  var  getCallPing1c;
    try{
      print('getComplete $response1C');
      //TODO
      print('response1C.statusCode $response1C.statusCode');
      if (response1C.statusCode==200) {
        //TODO realy ping
        print(' then backresponsejboss. contentLength $response1C.contentLength');
    //TODO PING
    getCallPing1c= getPingDynamicDontaunt(response1C: response1C) as  String?   ;

    print('getCallPing1c $getCallPing1c');

      } else {
        //TODO
        print('response1C.statusCode $response1C.statusCode');
      }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getCallPing1c;
  }

  @override
  Future<List<Entities1CMap>> getDownloadJsonMap({required String url, required int IdUser, required int UUID}) async {
    // TODO: implement getCompleteMapCallBackJson
    // TODO: implement getGettingJson1C
    late var getJson1cSucces;
    try{
      final parsedUrl=Uri.parse(url) as Uri;
      BigInt Uuid=BigInt.parse(UUID.toString())  ;
      //TODO Paramerts
      print('parsedUrl..$parsedUrl'+'Uuid..$Uuid'+ 'IdUser..$IdUser');

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

        //    getCompleteCallBackJson( response1C: backresponsejboss)    ,

        getJson1cSucces=  getGeneratorMapCallBack(response1C: backresponsejboss) as  List<Map<String, Entities1CMap>>  ,

        print(' then getJson1cSucces $getJson1cSucces'),

      })
          .whenComplete(
            () {
          // print(' whenComplete  getjson1C $getjson1C' );
          print(' whenComplete getJson1cSuccess ' );
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

    return   getJson1cSucces;
  }

  @override
  List<Map<String, Entities1CMap>> getGeneratorMapCallBack({required http.Response response1C}) {
    // TODO: implement getCompleteFutureMapCallBackJson
    throw UnimplementedError();
  }










}
