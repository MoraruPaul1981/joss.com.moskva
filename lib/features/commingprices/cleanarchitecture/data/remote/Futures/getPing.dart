


import 'package:logger/logger.dart';

import '../../../../../../main.dart';
import '../../../domain/usercases/AdressJboss/getAdress.dart';
import '../../../domain/usercases/Interfaces/InterfacePings.dart';
import '../../../domain/usercases/Loggers/GetErrors.dart';
import 'GetFutures1C.dart';

class GetPing implements InterfacePings {


  @override
  Future<String?> getJson1cPing() async {
    // TODO: implement getJson1cPing
    late  var    ping1C;
    try{
      //TODO адрес пинга к серверу  Jboss Debug
      var adressCurrent1C=  GetAdress1CPrices().adress1C( ) as String;
      //TODO
      print('adressCurrent1C .. $adressCurrent1C');
      //TODO
      ping1C   = await GetFutures1C().getPing1C(url: adressCurrent1C ) as    String?  ;
      //TODO
      logger.i('ping1C  .. $ping1C '+'ping1C..$ping1C');
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return ping1C;
  }







}