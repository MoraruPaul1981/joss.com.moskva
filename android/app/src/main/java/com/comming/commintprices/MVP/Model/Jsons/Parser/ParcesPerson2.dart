
import 'dart:convert';

import '../../Errors/ErrorsPrint.dart';
import '../Polo/Polos.dart';
import 'InParcesJson.dart';





class ParserJson2   implements InterfaceParserJson {

  //TODO
  @override
  List<Person> parserPerson(String responseBody) {
    // TODO: implement parserPerson
    var  list ;
    var   person;
    try{
      list =json.decode(responseBody) as List<dynamic>;

      print('list $list');
      person=list.map((model) =>  Person.fromJson((model)) ) as Person ;

      print('person $person');

      //TODO error
    } on Exception catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return person ;
  }




}










