

import '../../../Jsons/One1C/Polo/Person1CList.dart';
import '../../../Jsons/One1C/Polo/Person1CMap.dart';





abstract  class InFuture1C {

  Future<String?> getPing1C({ required String   url }) ;

  Future<List<Person1CList>?> getDownloadJsonList({ required String   url, required int IdUser, required int UUID}) ;

  Future<List<Person1CMap>?> getDownloadJsonMap({ required String   url, required int IdUser, required int UUID}) ;



}


