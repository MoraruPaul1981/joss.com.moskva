

import '../Interfaces/InPolos1c.dart';
import '../Interfaces/InPolos2.dart';



class Person1C implements InPolos1c{

  String?   CFO    ;
  String?  Data;
  String?  StatyaDDS;
  String?  Nomenklatura;
  String?   EdIzm;
  String?  Cena;
  String?  Kolichestvo;
  String?  CFORaskhoda;
  String?   UUID;
  String?  NDoc;
  String?   NStr;
  String?   Kontragent  ;




  Person1C(
      {String?   CFO ,
      String?  Data,
      String?  StatyaDDS,
      String?  Nomenklatura,
      String?   EdIzm,
      String?  Cena,
      String?  Kolichestvo,
      String?  CFORaskhoda,
      String?   UUID,
      String?  NDoc,
      String?   NStr,
      String?   Kontragent}) {

//TODO

    this.  CFO =CFO;
    this.  Data =Data;
    this.  StatyaDDS =StatyaDDS;
    this.  Nomenklatura =Nomenklatura;
    this.  EdIzm =EdIzm;
    this.  Cena =Cena;
    this.  Kolichestvo =Kolichestvo;
    this.  CFORaskhoda =CFORaskhoda;
    this.  UUID =UUID;
    this.  NDoc =NDoc;
    this.  CFO =CFO;
    this.  NStr =NStr;
    this.  CFO =CFO;
    this.  Kontragent =Kontragent;

  }



  @override
  Person1C fromJsondynamic( Map<String, dynamic> json) {
    // TODO: implement fromJsondynamic

    var person1c;
    try {


      json.entries.forEach((element) {

        print('element$element');
        print('element$element');


        element.value.forEach((element) {
          print('element$element');
          print('element$element');

           var elementTwo=element as Map<String ,dynamic>;
          print('element$elementTwo');
          print('element$elementTwo');


          print(elementTwo.entries.elementAt(0));
          print(elementTwo.entries.elementAt(1));
          print(elementTwo.entries);



          // TODO: implement fromJson
          CFO = elementTwo.entries.elementAt(0).value as String  ;
          Data = elementTwo.entries.elementAt(1).value as String;
          StatyaDDS = elementTwo.entries.elementAt(2).value as String  ;
          Nomenklatura = elementTwo.entries.elementAt(3).value as String  ;
          EdIzm = elementTwo.entries.elementAt(4).value as String  ;
          Cena =elementTwo.entries.elementAt(5).value as String  ;
          Kolichestvo = elementTwo.entries.elementAt(6).value as String ;
          CFORaskhoda = elementTwo.entries.elementAt(7).value as String ;
          UUID = elementTwo.entries.elementAt(8).value as String ;
          NDoc = elementTwo.entries.elementAt(9).value as String ;
          NStr = elementTwo.entries.elementAt(10).value as String ;
          Kontragent = elementTwo.entries.elementAt(11).value as String  ;

          print('json &json');

          person1c=Person1C(CFO: CFO, Data:Data, StatyaDDS:StatyaDDS, Nomenklatura:Nomenklatura,
              EdIzm:EdIzm, Cena:Cena, Kolichestvo:Kolichestvo, CFORaskhoda:CFORaskhoda,
              UUID:UUID, NDoc:NDoc, NStr:NStr,Kontragent:Kontragent );


          print('person1c &person1c');


          print('element$elementTwo');
          print('element$elementTwo');

        });


      });








      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  person1c;
  }

































  @override
  Map<String, Person1C> toJson2Person2() {
    // TODO: implement toJson2Person2
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJson2dynamic() {
    // TODO: implement toJson2dynamic
    throw UnimplementedError();
  }

  @override
  Map<String, Person1C> toJsonPerson2() {
    // TODO: implement toJsonPerson2
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJsondynamic() {
    // TODO: implement toJsondynamic
    throw UnimplementedError();
  }

  @override
  Person1C fromJsondynamicList(List listDynamic) {
    // TODO: implement fromJsondynamicList
    try{
 
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  Person1C( );
  }

  @override
  Person1C fromJsonPerson2(Map<String, Person1C> json) {
    // TODO: implement fromJsonPerson2
    throw UnimplementedError();
  }







 




/*   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }*/




}


///TODO  конец первого класса










///TODO  начало второга класса




class Person1CTwo implements InPolos2{

  String?   CFO    ;
  String?  Data;
  String?  StatyaDDS;
  String?  Nomenklatura;
  String?   EdIzm;
  String?  Cena;
  String?  Kolichestvo;
  String?  CFORaskhoda;
  String?   UUID;
  String?  NDoc;
  String?   NStr;
  String?   Kontragent  ;




  Person1CTwo(
      {String?   CFO ,
        String?  Data,
        String?  StatyaDDS,
        String?  Nomenklatura,
        String?   EdIzm,
        String?  Cena,
        String?  Kolichestvo,
        String?  CFORaskhoda,
        String?   UUID,
        String?  NDoc,
        String?   NStr,
        String?   Kontragent}) {

//TODO

    this.  CFO =CFO;
    this.  Data =Data;
    this.  StatyaDDS =StatyaDDS;
    this.  Nomenklatura =Nomenklatura;
    this.  EdIzm =EdIzm;
    this.  Cena =Cena;
    this.  Kolichestvo =Kolichestvo;
    this.  CFORaskhoda =CFORaskhoda;
    this.  UUID =UUID;
    this.  NDoc =NDoc;
    this.  CFO =CFO;
    this.  NStr =NStr;
    this.  CFO =CFO;
    this.  Kontragent =Kontragent;

  }



  @override
  Person1CTwo fromJsondynamic( Map<String, dynamic> json) {
    // TODO: implement fromJsondynamic

    var person1ctwo;
    try {


      json.entries.forEach((element) {

        print('element$element');
        print('element$element');


        element.value.forEach((element) {
          print('element$element');
          print('element$element');

          var elementTwo=element as Map<String ,dynamic>;
          print('element$elementTwo');
          print('element$elementTwo');

          elementTwo.forEach((key, value) {
            print(' elementTwo.entries.elementAt(0) $elementTwo.entries.elementAt(0)');


          });





          print('json &json');

          person1ctwo=Person1CTwo(CFO: elementTwo.entries.elementAt(0).value ,
              Data:elementTwo.entries.elementAt(1).value ,
              StatyaDDS:elementTwo.entries.elementAt(2).value ,
              Nomenklatura:elementTwo.entries.elementAt(3).value ,
              EdIzm:elementTwo.entries.elementAt(4).value ,
              Cena:elementTwo.entries.elementAt(5).value ,
              Kolichestvo:elementTwo.entries.elementAt(6).value ,
              CFORaskhoda:elementTwo.entries.elementAt(7).value ,
              UUID:elementTwo.entries.elementAt(8).value ,
              NDoc:elementTwo.entries.elementAt(9).value ,
              NStr:elementTwo.entries.elementAt(10).value ,
              Kontragent:elementTwo.entries.elementAt(11).value  );


          print('person1c &person1c');


          print('element$elementTwo');
          print('element$elementTwo');

        });


      });








      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  person1ctwo;
  }

































  @override
  Map<String, Person1C> toJson2Person2() {
    // TODO: implement toJson2Person2
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJson2dynamic() {
    // TODO: implement toJson2dynamic
    throw UnimplementedError();
  }

  @override
  Map<String, Person1C> toJsonPerson2() {
    // TODO: implement toJsonPerson2
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJsondynamic() {
    // TODO: implement toJsondynamic
    throw UnimplementedError();
  }

  @override
  Person1C fromJsondynamicList(List listDynamic) {
    // TODO: implement fromJsondynamicList
    try{

    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  Person1C( );
  }

  @override
  Person1C fromJsonPerson2(Map<String, Person1C> json) {
    // TODO: implement fromJsonPerson2
    throw UnimplementedError();
  }












/*   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }*/




}


///TODO  конец второго класса












