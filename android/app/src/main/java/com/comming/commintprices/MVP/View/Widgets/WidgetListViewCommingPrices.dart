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
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey1', UUIDKey: 1111),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey2', UUIDKey: 2222),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey3', UUIDKey: 3333),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey4', UUIDKey: 4444),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey5', UUIDKey: 5555),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey6', UUIDKey: 6666),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey7', UUIDKey: 7777),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey8', UUIDKey: 8888),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey9', UUIDKey: 9999),
    person1cListManual.loopGeneratorListPolo(CFOKey: 'CFOKey10', UUIDKey: 10000),
    ];




    return Scaffold(
        backgroundColor: Colors.grey[200],
        appBar:AppBar(
      backgroundColor: Colors.blue[300],
          title: Text('Выбор а локации'),
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
                title: Text(listManual[index].CFO.toString()),
              ),
            );
          },


      ),
    );
  }



}
