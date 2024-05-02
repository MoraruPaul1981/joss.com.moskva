

import 'dart:convert';
import 'dart:isolate';
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
import 'GetFutures1C.dart';

class GetPing implements InterfacePings {


  @override
  Future<String> getResponse1c({ required BuildContext context, required Logger logger})  async {
    // TODO: implement getJson1cPing
    // Read some data.
    Completer<String> completer= new Completer<String> ();;
    try {
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


      //TODO главный запрос Идем в сеть
     await http.get(
          parsedUrl,
          headers: {
            'user':IdUser.toString(),
            'uuid':Uuid.toString(),
            'authorization':'$basicAuth',
          }
      ).catchError(
              (Object error) {
            logger.i(' catchError  ERROR $error  ');

          }).then((backresponsejboss) {
        //TODO then
        logger.i('then backresponsejboss .. $backresponsejboss');

        //TODO поулченый ответ от сеи парсим

        Future<String>  getPing=    getCompetePing(   backresponsejboss, logger)
            .catchError(
                (Object error) {
              logger.i(' catchError  ERROR $error  ');

            });
        logger.i('start getPing ..  '+getPing.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());

        completer.complete(getPing);

        return backresponsejboss;

      });
      logger.i('start completer.future ..  '+completer.future.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
    } catch (e) {
      print(e);
    }
    return   completer.future;
  }







  //TODO  getCompetePing()
  Future<String>  getCompetePing(  Response backresponsejboss, Logger logger)   async {
    //TODO Read some data.
    return compute(getComplitingResponse ,backresponsejboss  );
  }



































  @override
  String    getComplitingResponse(  Response backresponsejboss)   {
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
        getCallPing1c= getResponseDecoder(response1C: backresponsejboss) as  String   ;

        print('getCallPing1c $getCallPing1c');
    //logger.i('start getCallPing1c ..  '+getCallPing1c.toString()+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      } else {
        //TODO
        print('response1C.statusCode $backresponsejboss.statusCode');
      //  params.logger.i('response1C.statusCode $params.backresponsejboss.statusCode'+''+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      }

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getCallPing1c;
  }










  @override
  String getResponseDecoder({required  Response response1C}) {
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






///TODO END  class
}





