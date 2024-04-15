

import '../Interfaces/InPolos1c.dart';



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


          elementTwo.entries.forEach((elementRow) {
            print('element   $elementRow.key');
            print('element  $elementRow.value');
          });


          elementTwo.values.forEach((element) {
            print('element$element');
            print('element$element');

          });
          print('element$elementTwo');
          print('element$elementTwo');

        });


      });







   /*     listisert.forEach((element) {
          final asMap =  element as Map<String, dynamic>;

          print('asMap$asMap');

          print('asMap$asMap');

        });*/








      // TODO: implement fromJson
      CFO = json['CFO']  ;
      Data = json['Data'] ;
      StatyaDDS = json['StatyaDDS']  ;
      Nomenklatura = json['Nomenklatura']  ;
      EdIzm = json['EdIzm']  ;
      Cena = json['Cena']  ;
      Kolichestvo = json['Kolichestvo']  ;
      CFORaskhoda = json['CFORaskhoda']  ;
      UUID = json['UUID']  ;
      NDoc = json['NDoc']  ;
      NStr = json['NStr']  ;
      Kontragent = json['Kontragent']  ;

      print('json &json');


      //TODO error
    }   catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return  Person1C(CFO: CFO, Data:Data, StatyaDDS:StatyaDDS, Nomenklatura:Nomenklatura,
      EdIzm:EdIzm, Cena:Cena, Kolichestvo:Kolichestvo, CFORaskhoda:CFORaskhoda,
        UUID:UUID, NDoc:NDoc, NStr:NStr,Kontragent:Kontragent );
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
