
import 'dart:async';
import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
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
 TODO  incoming stream*/
  @override
  Future<List<Map<String, List<Entities1CMap>>>> getGeneratorProcessSelfData({required  Response response1C,
    required Logger logger}) {
    // TODO: implement getGeneratorMapCallBack
    try{
      print('response1C.statusCode $response1C.statusCode');

      logger.i('Result esponse1C.statusCode ..  '+response1C.statusCode.toString()+
          ''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

      if (response1C.statusCode==200) {

        //TODO
        print('response1C. contentLength....$response1C. contentLength');



        List<dynamic>  getuint8listSelfCall=   new   getDecodingCallback().   getResponseDecoderSelfData(response1C:response1C);

        logger.i(' getuint8listSelfCall ..  '+getuint8listSelfCall.toString()+'Isolate.current.debugName'+Isolate.current.debugName.toString());

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

    return Future.value('fg' as FutureOr<List<Map<String, List<Entities1CMap>>>>);
  }


}




