

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:logger/logger.dart';

abstract  class InterfacePings {
  //TODO
  Future<String>  getJson1cPing({ required BuildContext context, required Logger logger});

  //TODO
  Future<String>  getCompletePing({required  Response response1C});

  //TODO
  String getPingDynamicDontaunt({required  Response response1C});
}