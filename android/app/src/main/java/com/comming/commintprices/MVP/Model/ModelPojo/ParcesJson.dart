
import 'dart:convert';
import 'Polos.dart';

class ParserJson {


  Map<String, dynamic> toJson(Map<String, dynamic> data ) {
/*    final Map<String, dynamic> data = new Map<String, dynamic>();*/
     print('data $data');
    return data;
  }

  List parserPerson(String responseBody) {
  var list=json.decode(responseBody) as List<dynamic>;
  List<Person> person=list.map((model) => Person.fromJson(model)).toList();
  print('person $person');
   return list;
  }


}










