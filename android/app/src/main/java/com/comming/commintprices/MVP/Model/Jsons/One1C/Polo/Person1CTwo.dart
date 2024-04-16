

import '../Interfaces/InPolos1c.dart';
import '../Interfaces/InPolos2.dart';

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
  Person1CTwo fromJsondynamic({required Map<String, dynamic> json}) {
    var person1ctwo;
    try {
      var person1ctwo=  loopGwerenetorPolo(  json)  as Person1CTwo;
      print('person1ctwo $person1ctwo');
    } catch (e) {
      print(e);
    }
    return person1ctwo;
  }




  //TODO generator From JSON to POLO
    Person1CTwo loopGwerenetorPolo(Map<String, dynamic> json){
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

            //TODO вставляем полцученные даннфые в КЛАСС

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


/*   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }*/




}


///TODO  конец второго класса












