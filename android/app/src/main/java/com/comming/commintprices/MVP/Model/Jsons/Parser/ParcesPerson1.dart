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


     // List<dynamic> list=json.decode(responseBody) as List<dynamic>;
      List<dynamic> list=json.decode(responseBody)  ;

      List<Person2>  person=list.map((model) => Person2().fromJsondynamic(model )) .toList() as   List<Person2>;

      print("person $person");

     Map<int,Person2> map= person.asMap();


        ///Map.from(map).entries.where(  (element) => element.key == 3 );

      var trueEntries = map.entries.where((MapEntry e) => e.value==3);


      for(Person2 p in map.values){
        print(Person2);

      }

      for(int p in map.keys){
        print(map.values.elementAt(p));

      }
      map.entries.forEach((      MapEntry<int, Person2> element) {
        print(element.key);
        print(element.value);
      });

      //map.keys.forEach((k,v) => print('${k}: ${v}') );

      // person=list.map((model) => Person2().fromJsonPerson2(model )) .toList() as   List<dynamic>;

      //list.where((e) => e["albumId"] == 3);

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










