

import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';
import '../../../Jsons/One1C/Polo/Person1NestedList.dart';





abstract  class InFuture1C {

  Future<String?> getPing1C({ required String   url }) ;

  Future<List<Person1Cspoler>?> getGettingJson1C({ required String   url, required int IdUser, required int UUID}) ;

  Future<List<Person1NestedList>?> getGettingMapJson1C({ required String   url, required int IdUser, required int UUID}) ;



}


