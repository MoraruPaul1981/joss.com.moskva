
import 'dart:async';
import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:dart_json_mapper/dart_json_mapper.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';


import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/decoding/getDecodingCallback.dart';
import '../../entities/Entities1CMap.dart';
import 'InterfacesFuture/InterfaceFutures/InterfaceFuture.dart';



class FuturesGetSelfData  implements InterfaceFutureResponse,InterfaceFutureSelfData  {

  //TODO получаем данные self-data
  @override
  Future<Response> getDownloadJsonMaps({required Uri url, required int IdUser, required int UUID,required Logger logger}) async {
    // TODO: implement getDownloadJsonMaps
    late  Response  getResponse;
    try{
      //TODO Paramerts
      print('url..$url'+'IdUser..$IdUser'+ 'UUID..$UUID');
      //TODO base64
      final   String? basicAuth=     GetConverts().convertBase64(  user: 'dsu1Admin', password: 'dsu1Admin');

      print(' basicAuth  $basicAuth');

      //TODO главный запрос
      getResponse=  await http.get(
          url,
          headers: {
            'user':IdUser.toString(),
            'uuid':UUID.toString(),
            'authorization':'$basicAuth',
          }
      ).catchError(
              (Object error) {
            print(' get ERROR $error  ');
          });

      logger.i('start getResponse ..  '+getResponse.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getResponse;
  }



 /* TODO  generator process
 TODO  of processing the
 TODO  incoming stream */
  @override
  Future<List<Map<String, List<Entities1CMap>>>> getGeneratorProcessSelfData({required  Response response1C,
    required Logger logger}) {
    // TODO: implement getGeneratorMapCallBack
   late  List<Map<String, List<Entities1CMap>>> getJson1cSucces;
    try{
      print('response1C.statusCode $response1C.statusCode');

      logger.i('Result esponse1C.statusCode ..  '+response1C.statusCode.toString()+
          ''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

      if (response1C.statusCode==200) {
        //TODO
        print('response1C.statusCode....$response1C.statusCode');

      //TODO  первая операция ДИнамик
        List<dynamic>  getListSeflData=   new   getDecodingCallback().   getResponseDecoderSelfData(response1C:response1C,logger: logger);
        logger.i(' getListSeflData ..  '+getListSeflData.toString()+'Isolate.current.debugName'+Isolate.current.debugName.toString());


        if (getListSeflData.isNotEmpty ) {
          //TODO получаем данные JSON
          logger.i('getListSeflData.isNotEmpty ..$getListSeflData.isNotEmpty'+'Isolate.current.debugName'+Isolate.current.debugName.toString());
          ///TODO
          getJson1cSucces=getListSeflData.map((model) => Entities1CMap().loopGeneratorMapPolo(  json:  model  )) .toList()   ;// TODO as List<Map<String, List<Entities1CMap>>> as List<Map<String, List<Entities1CMap>>>

          print('get ..$get');
        }

        logger.i('getListSeflData.isNotEmpty ..$getListSeflData.isNotEmpty'+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      } else {
        //TODO
        logger.i(' response1C ..  '+response1C.toString()+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      }
      //TODO
      logger.i(' response1C ..  '+response1C.toString()+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return Future.value('fg' as FutureOr<List<Map<String, List<Entities1CMap>>>>);
  }


}




