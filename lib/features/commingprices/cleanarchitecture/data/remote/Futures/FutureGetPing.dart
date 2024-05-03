

import "dart:async";
import 'dart:isolate';

import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../../../domain/usercases/AdressJboss/getAdress.dart';
import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/Interfaces/InterfacePings.dart';
import '../../../domain/usercases/decoding/Decoding.dart';
import '../../entities/Entities1CMap.dart';
import 'InterfacesFuture/InterfaceFutures/InterfaceFuture.dart';

class FutureGetPing implements InterfacePings ,InterfaceFutureResponse  {
  //TODO
late Logger logger;

late  Response backresponsejboss;



  @override
  Future<List<Map<String, List<Entities1CMap>>>>? getResponse1c({ required BuildContext context, required Logger logger})  async {
    // TODO: implement getJson1cPing
   late Completer<List<Map<String, List<Entities1CMap>>>> completer=new Completer () ;
    try {
      //TODO init LOGER
      this.logger=logger;
      //TODO адрес пинга к серверу  Jboss Debug
      var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
      //TODO
      print('adressCurrent1C .. $adressCurrent1C');

      final parsedUrl=Uri.parse(adressCurrent1C) as Uri;
      final  BigInt Uuid=BigInt.parse('0')  ;
      final int IdUser=0;

      //TODO главный запрос PING
      Future<Response?>responsePing =    getDownloadJsonMaps(url:parsedUrl ,IdUser:IdUser ,UUID:Uuid.toInt() ,logger: logger);
      responsePing.catchError(
              (Object error) {
            logger.i(' catchError  ERROR $error  ');
            //TODO оБРАБОТКА пинга
          }).then((backresponsejboss)  {
        //TODO then
        logger.i('then backresponsejboss .. $backresponsejboss');
        //TODO Get PING
        Future<String> getProcessingPing=    getCompetePing(   backresponsejboss, logger);
        //TODO  processing ping
        getProcessingPing.catchError(
                (Object error) {
              logger.i(' catchError  ERROR $error  ');
            })
         //TODO оБРАБОТКА даННЫХ
           .then((value) {
             //TODO PING
          logger.i('Result PINGs value ..  '+value.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
          return value;
          })



         //TODO Self-data
             .then((value ) {
               //TODO Self-data
           var  IspingOtServer=value as  String?;
           logger.i('Result Self Data  IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
           List<Map<String, List<Entities1CMap>>> SelfData =[];
           //TODO когад пришли данные
           if (IspingOtServer!.isNotEmpty) {



             logger.i('Result IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

           }else{
             logger.i('Result IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

           }

           //TODO закрвваем Compete
           completer.complete(SelfData );
           logger.i('Result completer.isCompleted ..  '+completer.isCompleted.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
           return SelfData;

         });
         //TODO CALL BACk
        return backresponsejboss;
            });
      logger.i('start completer.future ..  '+completer.future.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return   completer.future;
  }














































//TODO PING
  @override
   String   getComplitingResponsePing(    Response? backresponsejboss)   {
    // TODO: implement getCompletePing
    late  var  getCallPing1c;
    try{
      print('getComplete $backresponsejboss');
      //TODO
      print('response1C.statusCode $backresponsejboss.statusCode');
      if (backresponsejboss?.statusCode==200) {
        //TODO realy ping
        print(' then backresponsejboss. contentLength $backresponsejboss.contentLength');
        //TODO PING
        getCallPing1c= getDecodingCallback().getResponseDecoderPing(response1C: backresponsejboss) as  String   ;

        print('getCallPing1c $getCallPing1c');
    logger.i('start getCallPing1c ..  '+getCallPing1c.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      } else {
        //TODO
        print('response1C.statusCode $backresponsejboss.statusCode');
    logger.i('response1C.statusCode $backresponsejboss.statusCode'+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getCallPing1c;
  }



  @override
  Future<http.Response?> getDownloadJsonMaps({required Uri url, required int IdUser, required int UUID, required Logger logger}) async {
    // TODO: implement getDownloadJsonMaps
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








  @override
  Future<String> getCompetePing( Response? backresponsejboss, Logger logger) async {
    // TODO: implement getCompetePing
      return  getComplitingResponsePing(     backresponsejboss) ;///TODO    return compute(getComplitingResponse ,backresponsejboss  );
    return    compute(getComplitingResponsePing,backresponsejboss)  ;///TODO    return compute(getComplitingResponse ,backresponsejboss  );
  }


///TODO END  class
}





