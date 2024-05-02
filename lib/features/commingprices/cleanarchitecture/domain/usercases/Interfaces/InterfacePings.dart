

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../../../data/entities/Entities1CMap.dart';
import '../Paramets/getParaments.dart';

abstract  class InterfacePings {
  //TODO main metod
  Future<List<Map<String, List<Entities1CMap>>>>?  getResponse1c({ required BuildContext context, required Logger logger});

  //TODO  self data
  List<Map<String, List<Entities1CMap>>>   getComplitingResponseYoursData(  Response backresponsejboss);


  //TODO  ping
  String   getComplitingResponsePing(  Response? backresponsejboss);


}