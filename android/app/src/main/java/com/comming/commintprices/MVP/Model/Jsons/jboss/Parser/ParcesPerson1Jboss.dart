import 'dart:convert';

import '../../../Errors/ErrorsPrint.dart';

import '../Interfaces/InParserJsonJboss.dart';
import '../Polo/Person1jboss.dart';






class ParcesPerson1Jboss   implements InParserJsonJboss {

  //TODO
  @override
  List<Person1jboss> parserPerson(String responseBody) {
    // TODO: implement parserPerson
  var   person;
    try{
      List<dynamic> list=json.decode(responseBody)  ;
      List<Person1jboss>  person=list.map((model) => Person1jboss().fromJsondynamic(model )) .toList() as   List<Person1jboss>;
      print("person $person");
     Map<int,Person1jboss> map= person.asMap();
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










