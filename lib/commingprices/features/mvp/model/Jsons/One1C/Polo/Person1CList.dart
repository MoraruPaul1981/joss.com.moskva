






import '../../../Interfacess1C/InterfacePerson1CList.dart';

class Person1CList implements InterfacePerson1CList {

  String? CFO;

  String? Data;
  String? StatyaDDS;
  String? Nomenklatura;
  String? EdIzm;
  int? Cena;
  int? Kolichestvo;
  String? CFORaskhoda;
  int? UUID;
  String? NDoc;
  int? NStr;
  String? Kontragent;


  Person1CList({String? CFO,
    String? Data,
    String? StatyaDDS,
    String? Nomenklatura,
    String? EdIzm,
    int? Cena,
    int? Kolichestvo,
    String? CFORaskhoda,
    int? UUID,
    String? NDoc,
    int? NStr,
    String? Kontragent}) {
//TODO

    this.CFO = CFO;
    this.Data = Data;
    this.StatyaDDS = StatyaDDS;
    this.Nomenklatura = Nomenklatura;
    this.EdIzm = EdIzm;
    this.Cena = Cena;
    this.Kolichestvo = Kolichestvo;
    this.CFORaskhoda = CFORaskhoda;
    this.UUID = UUID;
    this.NDoc = NDoc;
    this.CFO = CFO;
    this.NStr = NStr;
    this.CFO = CFO;
    this.Kontragent = Kontragent;
  }


  @override
  Person1CList loopGeneratorListPolo({required Map<String, dynamic> json}) {
    // TODO: implement fromJsondynamic

    var person1cList;
    try {
      json.entries.forEach((elementList) {
        //TODO ROOT list
        try{
        print('element$elementList');
        print('element$elementList');


        elementList.value.forEach((element) {
          print('element$element');
          print('element$element');

          var elementTwo = element as Map<String, dynamic>;
          print('element$elementTwo');


          // TODO: implement fromJson
          CFO = elementTwo.entries
              .elementAt(0)
              .value as String;
          Data = elementTwo.entries
              .elementAt(1)
              .value as String;
          StatyaDDS = elementTwo.entries
              .elementAt(2)
              .value as String;
          Nomenklatura = elementTwo.entries
              .elementAt(3)
              .value as String;
          EdIzm = elementTwo.entries
              .elementAt(4)
              .value as String;
          //TODO cena
          var gettingcena = elementTwo.entries
              .elementAt(5)
              .value as String;
          Cena = int.tryParse(
              gettingcena.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          //TODO количество
          var gettingKolichestvo = elementTwo.entries
              .elementAt(6)
              .value as String;
          Kolichestvo = int.tryParse(
              gettingKolichestvo.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;
          ;
          CFORaskhoda = elementTwo.entries
              .elementAt(7)
              .value as String;

          //TODO uuid
          var gettingUUID = elementTwo.entries
              .elementAt(8)
              .value as String;
          UUID = int.tryParse(
              gettingUUID.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          NDoc = elementTwo.entries
              .elementAt(9)
              .value as String;

          var gettingNStr = elementTwo.entries
              .elementAt(10)
              .value as String;
          NStr = int.tryParse(
              gettingNStr.trim().replaceAll(RegExp(r'[^0-9]'), '')) ?? 0;

          Kontragent = elementTwo.entries
              .elementAt(11)
              .value as String;
          print('Kontragent $Kontragent');


          //TODO ЗАполяем данные в класс
          person1cList = Person1CList(CFO: CFO,
              Data: Data,
              StatyaDDS: StatyaDDS,
              Nomenklatura: Nomenklatura,
              EdIzm: EdIzm,
              Cena: Cena,
              Kolichestvo: Kolichestvo,
              CFORaskhoda: CFORaskhoda,
              UUID: UUID,
              NDoc: NDoc,
              NStr: NStr,
              Kontragent: Kontragent);


          print('person....$person1cList');


          print('element$elementTwo');

        });


        //TODO error
      }   catch (e, stacktrace) {
        print(' get ERROR $e get stacktrace $stacktrace ');
      }

      });

      print('person1cList$person1cList');
      //TODO error
    } catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return person1cList;
  }


}










