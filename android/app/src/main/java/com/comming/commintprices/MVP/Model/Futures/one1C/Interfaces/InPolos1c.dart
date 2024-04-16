
import '../../../Jsons/One1C/Polo/Person1C.dart';
import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';

abstract  class InPolos1c {

  //todo  dynamic
 Person1C  fromJsondynamic({ required Map<String, dynamic> json}) ;

 Person1C loopGwerenetorPolo(Map<String, dynamic> json);


}


abstract  class InPolos1cSpoler {

 //todo  dynamic
 Person1Cspoler  fromJsondynamic({ required Map<String, dynamic> json}) ;

 Person1Cspoler loopGwerenetorPolo(Map<String, dynamic> json);


}



