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
class Uint8ListConverters implements JsonConverter<List<dynamic>,List<Person1C>> {
  /// Create a new instance of [Uint8ListConverter].
  const Uint8ListConverters();

  @override
  List fromJson(List<Person1C> json) {
    // TODO: implement fromJson
    throw UnimplementedError();
  }

  @override
  List<Person1C> toJson(List object) {
    // TODO: implement toJson
    var listpersol;
   Map <int,dynamic> map= object.asMap();
   print("map $map");
   return listpersol ;
   /// throw UnimplementedError();
  }





}






