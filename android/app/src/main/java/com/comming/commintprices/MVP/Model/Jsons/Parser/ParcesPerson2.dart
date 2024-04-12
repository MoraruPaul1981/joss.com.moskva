
import 'dart:convert';

import '../../Errors/ErrorsPrint.dart';
import '../Polo/Person1.dart';
import 'InParcesJson.dart';





class ParserJson2   implements InterfaceParserJson {

  //TODO
  @override
  List<Person1> parserPerson(String responseBody) {
    // TODO: implement parserPerson
    var  list ;
    var   person;
    try{
      list =json.decode(responseBody) as List<dynamic>;

      print('list $list');
      person=list.map((model) =>  Person1.fromJson((model)) ) as Person1 ;

      print('person $person');

      //TODO error
    } on Exception catch (e) {
      PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');
    }
    return person ;
  }




}










