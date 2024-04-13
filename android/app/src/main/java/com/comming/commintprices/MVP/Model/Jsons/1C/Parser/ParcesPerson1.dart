import 'dart:convert';

import '../../../Errors/ErrorsPrint.dart';

import '../Interfaces/InParserJson1c.dart';
import '../Polo/Person1C.dart';





class ParserJson1   implements InParserJson1c {

  //TODO
  @override
  List<Person1C> parserPerson(String responseBody) {
    // TODO: implement parserPerson
  var   person;
    try{
      List<dynamic> list=json.decode(responseBody)  ;
      List<Person1C>  person=list.map((model) => Person1C().fromJsondynamic(model )) .toList() as   List<Person1C>;
      print("person $person");
     Map<int,Person1C> map= person.asMap();
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










