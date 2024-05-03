
import 'dart:typed_data';

import 'package:http/http.dart';
import '../../../data/entities/Entities1CMap.dart';




abstract  class InterfaceDecoding {

  //todo  dynamic Ping
  String getResponseDecoderPing({required  Response response1C});

  //todo  dynamic Sekf DatA
  List  getResponseDecoderSelfData({required  Response response1C});


}