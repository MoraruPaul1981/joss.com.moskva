
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';

import '../../../data/entities/Entities1CListManual.dart';
import 'GetWidgetErrors.dart';
import 'GetWidgetHasData.dart';
import 'GetWidgetWaitingPing.dart';
import 'IntrafaceNasDataError/InterfaceNasDataError.dart';
import 'WidgetSuccessData.dart';





//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetStartingCommingPrices extends State<StatefulWidgetCommingPrices> {
  Logger logger;
  //TODO json data

  late List<Entities1CListManual> listManual = getListmanual();

  WidgetStartingCommingPrices({ required this.logger } );

  @override
  Widget build(BuildContext context) {
    // TODO: Главный виджет
    print('listManual...  $listManual');

    IntarfaceNasDataError intarfaceNasDataError;


    return FutureBuilder<String>(
      future: downloadData(  logger: logger), // function where you call your api
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) { // AsyncSnapshot<Your object type>


        ////TODO Принимаем Решение что делать показывать пользователю   или продолжить  ожидание
        if (snapshot.connectionState == ConnectionState.waiting) {

              logger.i('napshot.connectionState$snapshot.connectionState');

              //TODO виджет когда мы ожидаем
              GetWidgetWaitingPing(). getWidgetWaitingPing(context:context, snapshot:snapshot);

        }
        if (snapshot.connectionState == ConnectionState.done && snapshot.hasData) {
          //TODO когда ест данные
          intarfaceNasDataError=    GetWidgetHasData();
          return   intarfaceNasDataError .getWidgeterrorOrhas(context: context, snapshot: snapshot);

        }

        if (snapshot.hasError){
          //TODO когда ест данные
          intarfaceNasDataError=    GetWidgetErrors();
          return   intarfaceNasDataError .getWidgeterrorOrhas(context: context, snapshot: snapshot);

      }
      //TODO нет данных
        intarfaceNasDataError=    GetWidgetHasData();
        return   intarfaceNasDataError .getWidgeterrorOrhas(context: context, snapshot: snapshot);
      }
    );
  }

}







Future<String> downloadData({ required  logger })async{

await Future.delayed(Duration(seconds: 2))
     .catchError(
         (Object error) {
       print(' get ERROR $error  ');
     }) ;
  //   var response =  await http.get('https://getProjectList');
  return  "Data download successfully"; // return your response
}
































  List<Entities1CListManual> getListmanual() {
    Entities1CListManual person1cListManual = Entities1CListManual();

    List<Entities1CListManual> listManual = [
      //TODO
      person1cListManual.loopGeneratorListPolo(
          CFOKey: '(Закрыт) Ремонт а/д Ковров-Шуя-Кинешма (1этап)',
          UUIDKey: 1111),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(закрыт) Воссст. изн-х слоев а/д Иваново-Родники (45+338 - 46+003) №909 от 12.09.2018               ',
          UUIDKey: 2222),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(закрыт) Ганантийный ремонт Белино-Михайловское (объект с того года)                                ',
          UUIDKey: 3333),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(Закрыт) Механизированная уборка и содержание дорог г.Фурманов №1 от 1.01.2018                      ',
          UUIDKey: 4444),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(Закрыт) Работы по устройству слоёв износа а/д М5"Урал" Челябинская обл. №101 от 25.06.2018         ',
          UUIDKey: 5555),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(Закрыт) Ремонт а/д Устюжно-Валдай 741751 от 07.08.17                                               ',
          UUIDKey: 6666),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(закрыт) Содержание 2018-2019 Лежневского и Ивановского р-нов (Григорьев Алексей)                   ',
          UUIDKey: 7777),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              '(Закрыт) Содержание а/д Р600 Кострома-Иваново №450 от 19.12.16 (с ноября закрыт)                    ',
          UUIDKey: 8888),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              'Уборка города в рамках подготовки к выборам (Благотворительность)                                   ',
          UUIDKey: 9999),
      person1cListManual.loopGeneratorListPolo(
          CFOKey:
              'АБЗ первый, второй (Михалевский АБЗ) ПЕРЕВОЗКА МАТЕРИАЛОВ                                           ',
          UUIDKey: 10000)
    ];
    return listManual;
  }



