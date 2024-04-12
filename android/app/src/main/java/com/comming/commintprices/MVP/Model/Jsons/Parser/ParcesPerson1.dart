import 'dart:convert';
import '../../Errors/ErrorsPrint.dart';
import '../Polo/Person1.dart';
import '../Polo/Person2.dart';
import 'InParcesJson.dart';



class ParserJson1   implements InterfaceParserJson {

  //TODO
  @override
  List<Person1> parserPerson(String responseBody) {
    // TODO: implement parserPerson
  var   person;
    try{
      List<dynamic> list=json.decode(responseBody)  ;
      List<Person2>  person=list.map((model) => Person2().fromJsondynamic(model )) .toList() as   List<Person2>;
      print("person $person");
     Map<int,Person2> map= person.asMap();
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










