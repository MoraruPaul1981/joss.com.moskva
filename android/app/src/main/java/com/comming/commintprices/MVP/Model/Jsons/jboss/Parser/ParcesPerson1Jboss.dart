import 'dart:convert';

import '../../../Errors/ErrorsPrint.dart';

import '../Interfaces/InParserJsonJboss.dart';
import '../Polo/Personjboss.dart';






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
    }  catch (e) {
     PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'ParserJson1.class','main()');
    }finally {
      print('finally');
    }
    return person ;
  }




}










