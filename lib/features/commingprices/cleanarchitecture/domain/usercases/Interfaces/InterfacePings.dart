

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:logger/logger.dart';

abstract  class InterfacePings {
  //TODO
  Future<String>  getResponse1cPing({ required BuildContext context, required Logger logger});

  //TODO
  Future<String>  getComplitingResponse({required  Response response1C});

  //TODO
  String getResponseDecoder({required  Response response1C});
}