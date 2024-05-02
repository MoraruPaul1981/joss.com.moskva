

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../Paramets/getParaments.dart';

abstract  class InterfacePings {
  //TODO
  Future<String>  getResponse1cPing({ required BuildContext context, required Logger logger});

  //TODO
  String   getComplitingResponse(  Response backresponsejboss);

  //TODO
  String getResponseDecoder({required  Response response1C});
}