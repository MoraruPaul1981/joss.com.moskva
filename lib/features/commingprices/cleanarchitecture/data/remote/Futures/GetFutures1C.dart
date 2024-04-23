
import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';


import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/Interfaces/InFuture1C.dart';
import '../../entities/Entities1CMap.dart';



class GetFutures1C  implements InFuture1C  {
  //TODO
  late List<Map<String, List<Entities1CMap>>> getmap;



  //TODO json get MAP
  @override
  Future<List<Map<String, List<Entities1CMap>>>> getDownloadJsonMaps({required String url,
    required int IdUser, required int UUID}) async {
    // TODO: implement getDownloadJsonMaps
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

//TODO Пришел succees ot server 1C
      getmap=  getGeneratorMapCallBack(response1C: backresponsejboss) as List<Map<String, List<Entities1CMap>>>,

      print(' then backresponsejboss  $backresponsejboss' ),

          print(' then getJson1cSucces getJson1cSucces'),

      })
          .whenComplete(
            () {
              print(' whenComplete getmap  $getmap' );

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
    return       getmap;

  }




  @override
  List<Map<String, List<Entities1CMap>>> getGeneratorMapCallBack({required  Response response1C}) {
    // TODO: implement getGeneratorMapCallBack
    try{
      print('response1C.statusCode $response1C.statusCode');

      if (response1C.statusCode==200) {

        //TODO
        print('response1C. contentLength....$response1C. contentLength');
      /*  if (response1C. contentLength!>100) {

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
          getJson1cSucces=listDynamic.map((model) => Entities1CList().loopGeneratorListPolo(  json:  model  )) .toList()
          as   List<Entities1CList>;
          //TODO
          print('getJson1cSuccess..$getPerson1CList');
        }*/

        print('personSpoler');
      } else {
        //TODO
        print('response1C.statusCode $response1C.statusCode');
      }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return getmap;
  }

  }



