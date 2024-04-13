
import 'dart:convert';

import '../../Errors/ErrorsPrint.dart';
import '../Polo/Person1.dart';
import '../Polo/Person2.dart';
import 'InParcesJson.dart';





class ParserJson3   implements InterfaceParserJson {

  //TODO
  @override
  List<Person1> parserPerson(String responseBody) {
    // TODO: implement parserPerson
    var  list ;
    var   person;
    try{


      List<dynamic> list=json.decode(responseBody) as List<dynamic>;

      person=list.map((model) => Person2().fromJsonPerson2(model )) .toList() as   List<dynamic>;

      print('person $person');

      print('person $person');

      //TODO error
    }   catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return person ;
  }




}










