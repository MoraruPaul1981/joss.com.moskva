

import '../Polo/Person1.dart';
import 'Person2.dart';

abstract  class InPolos {


  InPolos.fromJson(Map<String, dynamic> json) ;

    Person2  fromJson(Map<String, dynamic> json) ;

  Map<String, dynamic> toJson() ;

  Map<String, dynamic> toJson2() ;
}
