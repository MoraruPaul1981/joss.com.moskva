import 'dart:convert';



import 'package:commintprices/commingprices/features/mvp/model/Jsons/jboss/Polo/Personjboss.dart';

import '../Interfaces/InParserJsonJboss.dart';





class ParcesPerson1Jboss   implements InParserJsonJboss {

  //TODO
  @override
  List<Personjboss> parserPerson(String responseBody) {
    // TODO: implement parserPerson
  var   person;
    try{
      List<dynamic> list=json.decode(responseBody)  ;
      List<Personjboss>  person=list.map((model) => Personjboss().fromJsondynamic(model )) .toList() as   List<Personjboss>;
      print("person $person");
     Map<int,Personjboss> map= person.asMap();
     print("map $map");
      //TODO error
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return person ;
  }




}










