

import 'dart:collection';

import '../../../Futures/one1C/Interfaces/InPolos1c.dart';
import 'Person1Cspoler.dart';




class Person1NestedList implements InPolos1cSpoler{

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




Person1NestedList(
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
   Person1Cspoler  fromJsondynamic({required Map<String, dynamic> json}) {
    // TODO: implement fromJsondynamic

    var person1c;
    try {


      json.entries.forEach((elementOne) {

        print('elementOne$elementOne');
        print('elementOne$elementOne');


        elementOne.value.forEach((elementeTwo) {
          print('elementeTwo$elementeTwo');
          print('elementeTwo$elementeTwo');

           var elementTtree=elementeTwo as Map<String ,dynamic>;
          print('elementTtree$elementTtree');


          // TODO: implement fromJson
          CFO = elementTtree.entries.elementAt(0).value as String  ;
          Data = elementTtree.entries.elementAt(1).value as String;
          StatyaDDS = elementTtree.entries.elementAt(2).value as String  ;
          Nomenklatura = elementTtree.entries.elementAt(3).value as String  ;
          EdIzm = elementTtree.entries.elementAt(4).value as String  ;
          //TODO cena
          var gettingcena=elementTtree.entries.elementAt(5).value as String;
          Cena =  int.tryParse( gettingcena.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          //TODO количество
          var gettingKolichestvo=elementTtree.entries.elementAt(6).value as String ;
          Kolichestvo =  int.tryParse(  gettingKolichestvo.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;;
          CFORaskhoda = elementTtree.entries.elementAt(7).value as String ;

          //TODO uuid
          var gettingUUID=elementTtree.entries.elementAt(8).value as String;
          UUID =  int.tryParse(   gettingUUID.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          NDoc = elementTtree.entries.elementAt(9).value as String ;

          var gettingNStr=elementTtree.entries.elementAt(10).value as String;
          NStr =  int.tryParse(   gettingNStr.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          Kontragent = elementTtree.entries.elementAt(11).value as String  ;
          print('Kontragent $Kontragent');

          //TODO add Class POLO

          person1c=Person1NestedList(CFO: CFO, Data:Data, StatyaDDS:StatyaDDS, Nomenklatura:Nomenklatura,
              EdIzm:EdIzm, Cena:Cena, Kolichestvo:Kolichestvo, CFORaskhoda:CFORaskhoda,
              UUID:UUID, NDoc:NDoc, NStr:NStr,Kontragent:Kontragent );


          print('elementTtree$elementTtree');
          print('person1c$person1c');

          print('person1c &person1c');

        });


      });








      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  person1c;
  }

  @override
  Map<String, Person1NestedList> fromJsonMapdynamic({required Map<String, dynamic> json}) {
    // TODO: implement fromJsonMapdynamic
    // TODO: implement fromJsonMapdynamic
    // TODO: implement fromJsondynamic
    var person1;
    Map<String, Person1NestedList>  map=new Map();
    try {


      json.entries.forEach((elementOne) {

        print('elementOne$elementOne');
        print('elementOne$elementOne');


        elementOne.value.forEach((elementTWO) {
          print('elementTWO$elementTWO');
          print('elementTWO$elementTWO');

          var elementTree=elementTWO as Map<String ,dynamic>;
          print('elementTree$elementTree');
          print('elementTree$elementTree');

          //TODO вставляем полцученные даннфые в КЛАСС
          person1=Person1NestedList(CFO: elementTree.entries.elementAt(0).value ,
              Data:elementTree.entries.elementAt(1).value ,
              StatyaDDS:elementTree.entries.elementAt(2).value ,
              Nomenklatura:elementTree.entries.elementAt(3).value ,
              EdIzm:elementTree.entries.elementAt(4).value ,
              Cena:elementTree.entries.elementAt(5).value ,
              Kolichestvo:elementTree.entries.elementAt(6).value ,
              CFORaskhoda:elementTree.entries.elementAt(7).value ,
              UUID:elementTree.entries.elementAt(8).value ,
              NDoc:elementTree.entries.elementAt(9).value ,
              NStr:elementTree.entries.elementAt(10).value ,
              Kontragent:elementTree.entries.elementAt(11).value  );

          //TODO


          map = {
            elementOne.key.toString(): person1,
          };

          print('map $map');
          print('element$elementTree');
          print('element$person1');

        });


      });

      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  person1;
  }








/*   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }*/


}













