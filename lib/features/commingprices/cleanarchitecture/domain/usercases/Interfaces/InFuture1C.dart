






import '../../entities/Entities1CList.dart';
import '../../entities/Entities1CMap.dart';

abstract  class InFuture1C {

  Future<String?> getPing1C({ required String   url }) ;

  Future<List<Entities1CList>?> getDownloadJsonList({ required String   url, required int IdUser, required int UUID}) ;

  Future<List<Entities1CMap>?> getDownloadJsonMap({ required String   url, required int IdUser, required int UUID}) ;



}


