

import 'dart:collection';


import 'package:commintprices/features/commingprices/cleanarchitecture/data/entities/Entities1CMap.dart';
import 'package:dart_json_mapper/dart_json_mapper.dart';

import '../../domain/usercases/Interfaces/InterfaceEntities1CMap.dart';
import 'Entities1CList.dart';



@JsonSerializable()
class Entities1CMapSerializable  {
  //TODO main varible
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




  Entities1CMapSerializable(
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

  //factory Entities1CMap.fromJson(Map<String, dynamic> json) => Entities1CMap(json);

//  Map<String, dynamic> toJson() => Entities1CMap(this);




}













