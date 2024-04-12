
import 'dart:convert';

import '../../Errors/ErrorsPrint.dart';
import '../Polo/Polos.dart';
import 'InParcesJson.dart';





class ParserJson1   implements InterfaceParserJson {

  //TODO
  @override
  List<Person> parserPerson(String responseBody) {
    // TODO: implement parserPerson
    List<dynamic>  list ;
    var   person;
    try{
      list =json.decode(responseBody) as  List<dynamic>  ;

      print('list $list');


      //Map<int, dynamic> map = list.asMap()   ;
      Map<int, dynamic> map2 ;  ;

      list.forEach((k,v) => map2.putIfAbsent(k, () => v);

      //person=list.map((model) =>  Person.fromJson((model))) as Person ;

      //list.asMap()

      print('person $person');

      //TODO error
    } on Exception catch (e) {
      print(e);
 /*     PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');*/
    }
    return person ;
  }




}










