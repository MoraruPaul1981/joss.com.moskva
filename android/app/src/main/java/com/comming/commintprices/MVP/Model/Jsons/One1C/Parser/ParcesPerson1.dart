import 'dart:convert';



import '../Interfaces/InParserJson1c.dart';
import '../Polo/Person1C.dart';

import "dart:typed_data";

import "package:json_annotation/json_annotation.dart";



class ParserJson1   implements InParserJson1c {

  //TODO
  @override
  List<Person1C> parserPerson(String responseBody) {
    // TODO: implement parserPerson
  var   person;
    try{
      List<dynamic> list=json.decode(responseBody)  ;
      List<Person1C>  person=list.map((model) => Person1C().fromJsondynamic(model )) .toList() as   List<Person1C>;
      print("person $person");
     Map<int,Person1C> map= person.asMap();
     print("map $map");
      //TODO error
      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }
    return person ;
  }

}

///TODO Converts to and from [Uint8List] and [List]<[int]>.
class Uint8ListConverters implements JsonConverter<Uint8List?, List<int>?> {
  /// Create a new instance of [Uint8ListConverter].
  const Uint8ListConverters();


  @override
  Uint8List? fromJson(List<int>? json) {
    if (json == null) return null;

    return Uint8List.fromList(json);
  }

  @override
  List<int>? toJson(Uint8List? object) {
    if (object == null) return null;

    return object.toList();
  }


}






