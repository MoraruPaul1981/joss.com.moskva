
import 'package:http/http.dart';
import 'package:logger/logger.dart';
import '../../../../entities/Entities1CMap.dart';





abstract  class InterfaceFutureResponse {
  //TODO
  Future<Response> getDownloadJsonMaps({ required  Uri   url, required int IdUser, required int UUID,required Logger logger}) ;


}






abstract  class InterfaceFutureSelfData {
  //TODO

  Future<List<Map<String, List<Entities1CMap>>>>?  getCompeteSelfData(  Response backresponsejboss, Logger logger);

  //TODO
  List<Map<String, List<Entities1CMap>>>?  getGeneratorMapCallBack({required  Response response1C,required Logger logger}) ;




}
