
import 'package:http/http.dart';
import '../../../data/entities/Entities1CMap.dart';




abstract  class InterfaceDecoding {

  //todo  dynamic Ping
  String getResponseDecoderPing({required  Response response1C});

  //todo  dynamic Sekf DatA
  List<Map<String, List<Entities1CMap>>> getResponseDecoderYoursData({required  Response response1C});


}