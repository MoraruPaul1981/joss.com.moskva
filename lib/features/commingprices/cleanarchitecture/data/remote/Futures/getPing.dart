

import 'dart:convert';
import 'dart:isolate';
import 'dart:js_interop';
import 'dart:typed_data' show Uint8List,Uint16List;
import 'dart:typed_data';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import "dart:async";
import "dart:isolate";


import 'package:logger/logger.dart';

import '../../../../../../main.dart';
import '../../../domain/usercases/AdressJboss/getAdress.dart';
import '../../../domain/usercases/Converts/GetConverts.dart';
import '../../../domain/usercases/Interfaces/InterfacePings.dart';
import '../../../domain/usercases/Loggers/GetErrors.dart';
import '../../../domain/usercases/Paramets/getParaments.dart';
import '../../../domain/usercases/decoding/Decoding.dart';
import '../../entities/Entities1CMap.dart';
import 'GetFutures1C.dart';

class GetPing implements InterfacePings {
  //TODO
late Logger logger;

late  Response backresponsejboss;

  @override
  Future<List<Map<String, List<Entities1CMap>>>>? getResponse1c({ required BuildContext context, required Logger logger})  async {
    // TODO: implement getJson1cPing
    // Read some data.
   late Completer<List<Map<String, List<Entities1CMap>>>> completer=new Completer();

    try {
      this.logger=logger;
      //TODO адрес пинга к серверу  Jboss Debug
      var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
      //TODO
      print('adressCurrent1C .. $adressCurrent1C');

      final parsedUrl=Uri.parse(adressCurrent1C) as Uri;
      BigInt Uuid=BigInt.parse('0')  ;
      int IdUser=0;
      //TODO base64
      String? basicAuth=     GetConverts().convertBase64(  user: 'dsu1Admin', password: 'dsu1Admin');
      print(' basicAuth  $basicAuth');


      //TODO главный запрос PING

      Future<Response?>responsePing =    GetFutures1C().getDownloadJsonMaps(url:basicAuth.toString(),IdUser:IdUser ,UUID:Uuid.toInt() ,logger: logger);

      responsePing.catchError(
              (Object error) {
            logger.i(' catchError  ERROR $error  ');

          }).then((backresponsejboss)  {
        //TODO then
        logger.i('then backresponsejboss .. $backresponsejboss');

        //TODO Get PING
         getCompetePing(   backresponsejboss, logger)
            .catchError(
                (Object error) {
              logger.i(' catchError  ERROR $error  ');
            })

         //TODO PING
           .then((value) {
             //TODO PING
          logger.i('start value ..  '+value.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
          return value;
          })


         //TODO Self-data
             .then((value) {
               //TODO Self-data
         // Future<List<Map<String, List<Entities1CMap>>>>  list=GetFutures1C().getDownloadJsonMaps(basicAuth,IdUser,Uuid)  ;
           List<Map<String, List<Entities1CMap>>> list =[];

           completer.complete(list );

           logger.i('start list ..  '+list.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
           return list;

         });


        return backresponsejboss;
            });




      logger.i('start completer.future ..  '+completer.future.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    } catch (e) {
      print(e);
    }
    return   completer.future;
  }






/*
  //TODO  getCompetePing() compute
  Future<List<Map<String, List<Entities1CMap>>>>  getCompetePing(  Response backresponsejboss, Logger logger)   async {
    //TODO Read some data.
    return compute(getComplitingResponse ,backresponsejboss  );
  }*/

  //TODO  getCompetePing()
  Future<String>  getCompetePing(  Response backresponsejboss, Logger logger)   async {
    //TODO Read some data.
    return  getComplitingResponsePing(backresponsejboss) ;
  }




  //TODO  getCompeteSelfData()
  Future<List<Map<String, List<Entities1CMap>>>>  getCompeteSelfData(  Response backresponsejboss, Logger logger)   async {
    //TODO Read some data.
    return  getComplitingResponseYoursData(backresponsejboss) ;
  }





























//TODO PING
  @override
   String   getComplitingResponsePing(  Response backresponsejboss)   {
    // TODO: implement getCompletePing
    late  var  getCallPing1c;
    try{
      print('getComplete $backresponsejboss');
      //TODO
      print('response1C.statusCode $backresponsejboss.statusCode');
      if (backresponsejboss.statusCode==200) {
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




  ///TODO sel data

  @override
  List<Map<String, List<Entities1CMap>>> getComplitingResponseYoursData(http.Response backresponsejboss) {
    // TODO: implement getComplitingResponseYoursData

    //TODO
    print('response1C.statusCode $backresponsejboss.statusCode');
    throw UnimplementedError();
  }

///TODO END  class
}





