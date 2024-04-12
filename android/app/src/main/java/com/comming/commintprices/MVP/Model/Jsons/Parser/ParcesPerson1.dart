
import 'dart:convert';

import '../../Errors/ErrorsPrint.dart';
import '../Polo/Polos.dart';
import 'InParcesJson.dart';





class ParserJson1   implements InterfaceParserJson {

  //TODO
  @override
  List<Person> parserPerson(String responseBody) {
    // TODO: implement parserPerson
    var  list ;
    var   person;
    try{
      list =json.decode(responseBody) as List<dynamic>;

      print('list $list');
      Person person=new Person();
      person=list.map((model) =>  person.fromJson((model))?.toList()) as Person ;

      print('person $person');

      //TODO error
    } on Exception catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return person ;
  }




}










