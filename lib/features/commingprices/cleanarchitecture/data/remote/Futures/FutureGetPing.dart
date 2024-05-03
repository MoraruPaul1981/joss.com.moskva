

import "dart:async";
import 'dart:isolate';

import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../../../domain/usercases/AdressJboss/getAdress.dart';
import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/Interfaces/InterfacePings.dart';
import '../../../domain/usercases/decoding/Decoding.dart';
import '../../entities/Entities1CMap.dart';
import 'FuturesGetSelfData.dart';
import 'InterfacesFuture/InterfaceFutures/InterfaceFuture.dart';

class FutureGetPing implements InterfacePings ,InterfaceFutureResponse  {
  //TODO
late Logger logger;



  @override
  Future<List<Map<String, List<Entities1CMap>>>>? getResponse1c({ required BuildContext context, required Logger logger})  async {
    // TODO: implement getJson1cPing
   late Completer<List<Map<String, List<Entities1CMap>>>> completer=Completer.sync();
   late List<Map<String, List<Entities1CMap>>> SelfData =[];
    try {
      //TODO init LOGER
      this.logger=logger;
      //TODO адрес пинга к серверу
      var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
      print('adressCurrent1C .. $adressCurrent1C');
      final parsedUrl=Uri.parse(adressCurrent1C) as Uri;
      print('parsedUrl .. $parsedUrl');


      //TODO главный запрос PING
      String IspingOtServer = await wegetthefinalPing(parsedUrl,  logger);
      logger.i('Result IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());




      //TODO когад пришли данные
      final  BigInt Uuid=BigInt.parse('0')  ;
      final int IdUser=8;

      List<Map<String, List<Entities1CMap>>> getSelfDataCallBack=await  getthefinalSelfData(IspingOtServer, logger,IdUser,Uuid);
      logger.i('Result getSelfDataCallBack ..  '+getSelfDataCallBack.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());





      //TODO закрвваем Compete после все отработынных операций
      completer.complete(SelfData );
      logger.i('Result completer.isCompleted ..  '+completer.isCompleted.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      return SelfData;
           //TODO END   CALL BACK
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
      //TODO
      //TODO закрвваем Compete
      completer.completeError(SelfData );
    }
    return   completer.future;
  }






  //TODO ping
  Future<String> wegetthefinalPing(Uri parsedUrl,   Logger logger ) async {
    late String getpingCallBack;
    try {
      //TODO только для пинга
      final  BigInt Uuid=BigInt.parse('0')  ;
      final int IdUser=0;

      late  Response backresponsejboss;
      Future<Response>responsePing =     getDownloadJsonMaps(url:parsedUrl ,IdUser:IdUser ,UUID:Uuid.toInt() ,logger: logger);
      //TODO первый Этам получаем данные из СЕТИ
      responsePing.catchError(
                  (Object error) {
                logger.i(' catchError  ERROR $error  ');
                //TODO оБРАБОТКА пинга
              });
      //TODO получаем Responce
      backresponsejboss=await  responsePing;
      //TODO then
      logger.i('then backresponsejboss .. $backresponsejboss');

      //TODO Get PING
      Future<String> getProcessingPing=  getCompetePing(   backresponsejboss, logger);
      //TODO  processing ping
      getProcessingPing.catchError(
                    (Object error) {
                  logger.i(' catchError  ERROR $error  ');
                });
      //TODO Self-data
      getpingCallBack=await getProcessingPing;
      logger.i('Result  getpingCallBack ..  '+getpingCallBack.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return getpingCallBack;
  }

//TODO self-data
Future<List<Map<String, List<Entities1CMap>>>> getthefinalSelfData(String? IspingOtServer, Logger logger,int IdUser,BigInt Uuid) async {
  //TODO
  late List<Map<String, List<Entities1CMap>>>      getSelfDataCallBack=[];
  try {
    //TODO когад пришли данные
    if (IspingOtServer!.isNotEmpty) {
      FuturesGetSelfData futuresGetSelfData=new FuturesGetSelfData();

      //TODO адрес пинга к серверу
      var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
      final parsedUrl=Uri.parse(adressCurrent1C) as Uri;


      Future<Response>responseSelfDataFuture =     getDownloadJsonMaps(url:parsedUrl ,IdUser:IdUser ,UUID:Uuid.toInt() ,logger: logger);
      //TODO первый Этам получаем данные из СЕТИ
      responseSelfDataFuture.catchError(
              (Object error) {
            logger.i(' catchError  ERROR $error  ');
            //TODO оБРАБОТКА пинга
          });
      //TODO получаем Responce
      Response  responseSelfDataCallBack=await  responseSelfDataFuture;
      //TODO then
      logger.i(' responseSelfDataCallBack .. $responseSelfDataCallBack');


      logger.i('Result IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    }else{
      logger.i('Result IspingOtServer ..  '+IspingOtServer.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    }
    //TODO end Call bACK
    logger.i('Result getSelfDataCallBack ..  '+getSelfDataCallBack.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    //TODO
  }   catch (e, stacktrace) {
    print(' get ERROR $e get stacktrace $stacktrace ');
  }

  return  getSelfDataCallBack;

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


//TODO sendResponce
  @override
  Future<Response> getDownloadJsonMaps({required Uri url, required int IdUser, required int UUID, required Logger logger}) async {
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
   Future<String>  getCompetePing( Response backresponsejboss, Logger logger)  async   {
    // TODO: implement getCompetePing
    logger.i('start backresponsejboss ..  '+backresponsejboss.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      return await getComplitingResponsePing(     backresponsejboss) ;///TODO    return compute(getComplitingResponse ,backresponsejboss  );
    //return     compute(getComplitingResponsePing,backresponsejboss)  ;///TODO    return compute(getComplitingResponse ,backresponsejboss  );
  }

///TODO END  class
}





