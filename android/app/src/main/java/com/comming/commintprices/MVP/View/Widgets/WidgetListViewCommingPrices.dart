import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../../Model/Jsons/One1C/Polo/Person1CListManual.dart';
import '../mainFuture.dart';

//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetListViewCommingPrices extends State<StatefulWidgetCommingPrices>  {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    Person1CListManual person1cListManual=Person1CListManual();


    List<Person1CListManual> listManual=[
    //TODO
    person1cListManual.loopGeneratorListPolo(CFOKey: '(Закрыт) Ремонт а/д Ковров-Шуя-Кинешма (1этап)', UUIDKey: 1111),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(закрыт) Воссст. изн-х слоев а/д Иваново-Родники (45+338 - 46+003) №909 от 12.09.2018               ', UUIDKey: 2222),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(закрыт) Ганантийный ремонт Белино-Михайловское (объект с того года)                                ', UUIDKey: 3333),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(Закрыт) Механизированная уборка и содержание дорог г.Фурманов №1 от 1.01.2018                      ', UUIDKey: 4444),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(Закрыт) Работы по устройству слоёв износа а/д М5"Урал" Челябинская обл. №101 от 25.06.2018         ', UUIDKey: 5555),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(Закрыт) Ремонт а/д Устюжно-Валдай 741751 от 07.08.17                                               ', UUIDKey: 6666),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(закрыт) Содержание 2018-2019 Лежневского и Ивановского р-нов (Григорьев Алексей)                   ', UUIDKey: 7777),
    person1cListManual.loopGeneratorListPolo(CFOKey: '(Закрыт) Содержание а/д Р600 Кострома-Иваново №450 от 19.12.16 (с ноября закрыт)                    ', UUIDKey: 8888),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'Уборка города в рамках подготовки к выборам (Благотворительность)                                   ', UUIDKey: 9999),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'АБЗ первый, второй (Михалевский АБЗ) ПЕРЕВОЗКА МАТЕРИАЛОВ                                           ', UUIDKey: 10000),
    ];




    return Scaffold(
        backgroundColor: Colors.grey[200],
        appBar:AppBar(
      backgroundColor: Colors.blue[300],
          title: Text('Выбор цфо'),
          centerTitle: true,
          elevation: 500,
    ),
      body: ListView.builder(
          itemCount: listManual.length,
          itemBuilder: (context,index){
            //TODO
            return Card(
              child: ListTile (
                onTap: () {},
                title: Text(listManual[index].CFO.toString().trim(),
                  style: TextStyle(height:2,fontSize: 13,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'Hind',
                      color: Colors.black.withOpacity(0.7),
                    decoration: TextDecoration.underline,
                    decorationColor: Colors .blue[300],
                    decorationStyle: TextDecorationStyle.wavy,) ,
                ),
              ),
            );
          },


      ),
    );
  }



}
