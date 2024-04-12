import '../Polo/Person1.dart';
import 'Person2.dart';




abstract  class InPolos {



  //todo  Person2
  InPolos.fromJson(Map<String, Person2> json) ;

  Person2  fromJsonPerson2(Map<String, Person2> json) ;

  Map<String, Person2> toJsonPerson2() ;

  Map<String, Person2> toJson2Person2() ;





  //todo  dynamic
  Person2  fromJsondynamic(Map<String, dynamic> json) ;

  Map<String, dynamic> toJsondynamic() ;

  Map<String, dynamic> toJson2dynamic() ;





}




