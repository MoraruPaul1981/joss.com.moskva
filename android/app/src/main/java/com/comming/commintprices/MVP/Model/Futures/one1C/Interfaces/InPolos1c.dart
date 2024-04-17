
import 'dart:collection';


import '../../../Jsons/One1C/Polo/Person1Cspoler.dart';
import '../../../Jsons/One1C/Polo/Person1NestedList.dart';



abstract  class InPolos1cSpoler {

 //todo  dynamic
 Person1Cspoler  fromJsondynamic({ required Map<String, dynamic> json}) ;

 Map<String ,Person1NestedList> fromJsonMapdynamic({required Map<String, dynamic> json } );


}




