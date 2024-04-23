

import 'package:http/http.dart';

abstract  class InterfacePings {
  //TODO
  Future<String?>  getJson1cPing();

  //TODO
  Future<String?>  getCompletePing({required  Response response1C});

  //TODO
  String getPingDynamicDontaunt({required  Response response1C});
}