

import '../../../Futures/one1C/Interfaces/InPolos1c.dart';
import 'Person1NestedList.dart';




class Person1Cspoler implements InPolos1cSpoler{

  String?   CFO    ;
  String?  Data;
  String?  StatyaDDS;
  String?  Nomenklatura;
  String?   EdIzm;
  int?  Cena;
  int?  Kolichestvo;
  String?  CFORaskhoda;
  int?   UUID;
  String?  NDoc;
  int?   NStr;
  String?   Kontragent  ;




  Person1Cspoler(
      {String?   CFO ,
      String?  Data,
      String?  StatyaDDS,
      String?  Nomenklatura,
      String?   EdIzm,
        int?  Cena,
        int?  Kolichestvo,
      String?  CFORaskhoda,
        int?   UUID,
      String?  NDoc,
        int?   NStr,
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
  Person1Cspoler fromJsondynamic({required Map<String, dynamic> json}) {
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


          // TODO: implement fromJson
          CFO = elementTwo.entries.elementAt(0).value as String  ;
          Data = elementTwo.entries.elementAt(1).value as String;
          StatyaDDS = elementTwo.entries.elementAt(2).value as String  ;
          Nomenklatura = elementTwo.entries.elementAt(3).value as String  ;
          EdIzm = elementTwo.entries.elementAt(4).value as String  ;
          //TODO cena
          var gettingcena=elementTwo.entries.elementAt(5).value as String;
          Cena =  int.tryParse( gettingcena.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          //TODO количество
          var gettingKolichestvo=elementTwo.entries.elementAt(6).value as String ;
          Kolichestvo =  int.tryParse(  gettingKolichestvo.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;;
          CFORaskhoda = elementTwo.entries.elementAt(7).value as String ;

          //TODO uuid
          var gettingUUID=elementTwo.entries.elementAt(8).value as String;
          UUID =  int.tryParse(   gettingUUID.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          NDoc = elementTwo.entries.elementAt(9).value as String ;

          var gettingNStr=elementTwo.entries.elementAt(10).value as String;
          NStr =  int.tryParse(   gettingNStr.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          Kontragent = elementTwo.entries.elementAt(11).value as String  ;
          print('Kontragent $Kontragent');

          person1c=Person1Cspoler(CFO: CFO, Data:Data, StatyaDDS:StatyaDDS, Nomenklatura:Nomenklatura,
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
  Person1Cspoler loopGwerenetorPolo(Map<String, dynamic> json) {
    // TODO: implement loopGwerenetorPolo
    // TODO: implement fromJsondynamic
    var person1;
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

          //TODO вставляем полцученные даннфые в КЛАСС

          person1=Person1Cspoler(CFO: elementTwo.entries.elementAt(0).value ,
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

    return  person1;
  }

  @override
  Map<String, Person1NestedList> fromJsonMapdynamic({required Map<String, dynamic> json}) {
    // TODO: implement fromJsonMapdynamic
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













