

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart';
import 'package:logger/logger.dart';

import '../../../data/entities/Entities1CMap.dart';
import '../Paramets/getParaments.dart';

abstract  class InterfacePings {
  //TODO main metod
  Future<List<Map<String, List<Entities1CMap>>>>?  getResponse1c({ required BuildContext context, required Logger logger});



  //TODO  ping
   String   getComplitingResponsePing( Response backresponsejboss);


  //TODO
   Future<String>   getCompetePing(  Response backresponsejboss, Logger logger);

}