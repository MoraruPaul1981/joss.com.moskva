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
      List<dynamic> list=json.decode(responseBody) as List<dynamic>;

       person=list.map((model) => Person2().fromJson(model)).toList() as   List<Person1>;

      print('person $person');


      //TODO error
    } on Exception catch (e) {
     PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'ParserJson1.class','main()');
    }
    return person ;
  }




}










