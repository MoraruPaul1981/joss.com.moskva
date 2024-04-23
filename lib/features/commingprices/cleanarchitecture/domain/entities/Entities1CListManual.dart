





import '../usercases/Interfaces/InterfaceEntities1CListManual.dart';

class Entities1CListManual implements InterfaceEntities1CListManual {

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


  Entities1CListManual({String? CFO,
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
  Entities1CListManual loopGeneratorListPolo({required String CFOKey, required int UUIDKey }  ) {
    // TODO: implement fromJsondynamic
    var person1cListManual;
    try {
          //TODO ЗАполяем данные в класс
          person1cListManual = Entities1CListManual(CFO: CFOKey,
              Data: Data,
              StatyaDDS: StatyaDDS,
              Nomenklatura: Nomenklatura,
              EdIzm: EdIzm,
              Cena: Cena,
              Kolichestvo: Kolichestvo,
              CFORaskhoda: CFORaskhoda,
              UUID: UUIDKey,
              NDoc: NDoc,
              NStr: NStr,
              Kontragent: Kontragent);
          print('person....$person1cListManual');


      //TODO error
    } catch (e, stacktrace) {
      print(' get ERROR $e get stacktrace $stacktrace ');
    }

    return person1cListManual;
  }


}










