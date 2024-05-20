

import 'dart:convert';
import 'dart:isolate';
import 'dart:typed_data';

import 'package:commintprices/features/commingprices/cleanarchitecture/data/entities/Entities1CMap.dart';

import 'package:http/src/response.dart';
import 'package:logger/logger.dart';

import '../Interfaces/InterfaceDecoding.dart';

class  getDecodingCallback implements InterfaceDecoding{


  //TODO decoce PING
  @override
  String getResponseDecoderPing({required Response? response1C}) {
    // TODO: implement getResponseDecoder
    var   getPing1C;
    try{
      final byteData = response1C?.bodyBytes.buffer.asByteData();
      final bugffer=   byteData?.buffer;
      Uint8List? list = bugffer?.asUint8List(byteData!.offsetInBytes, byteData.lengthInBytes) ;
      //TODO
      getPing1C=json.decode(utf8.decode(list as List<int>)) as String ;
      //TODO
      print('getPing1C $getPing1C');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getPing1C;
  }






//TODO  decoce Self Data
  @override
  List getResponseDecoderSelfData({required Response response1C, required Logger logger }) {
    // TODO: implement getResponseDecoderYoursData
    late var   getList1cdynamic;
    try{
      final byteData = response1C.bodyBytes.buffer.asByteData();
      final bugffer=   byteData.buffer;
      Uint8List list = bugffer.asUint8List(byteData.offsetInBytes, byteData.lengthInBytes) ;
      //TODO
      getList1cdynamic=json.decode(utf8.decode(list))  as List<dynamic> ;
      //TODO
      print('getList1cdynamic $getList1cdynamic');
      logger.i(' list ..  '+list.length.toString()+'Isolate.current.debugName'+Isolate.current.debugName.toString());
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return getList1cdynamic;

  }




}