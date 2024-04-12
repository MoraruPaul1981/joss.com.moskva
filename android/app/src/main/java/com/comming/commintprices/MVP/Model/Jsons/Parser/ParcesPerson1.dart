
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
    List<dynamic>  list ;
    var   person;
    try{


      var list=json.decode(responseBody) as List<dynamic>;
    //  List<Person1> person=list.map((model) => Person1.fromJson(model)).toList();
     // List<Person2> person=list.map((model) => Person2.fromJson(model)).toList();

      Person2 person2=new Person2( );

      
      List person=list.map((model) => person2.fromJson(model)).toList();
      print('person $person');


    /*  list =json.decode(responseBody) as  List<dynamic>  ;

      print('list $list');


      //Map<int, dynamic> map = list.asMap()   ;
      Map<int, dynamic> map2 ;  ;

      list.forEach((k,v) => map2.putIfAbsent(k, () => v);

      print('person $person');*/

      //TODO error
    } on Exception catch (e) {
      print(e);
 /*     PrintingErrors printingErrors= new PrintingErrors();
      printingErrors.printingError(e,'mainTextButton46.dart','main()');*/
    }
    return person ;
  }




}










