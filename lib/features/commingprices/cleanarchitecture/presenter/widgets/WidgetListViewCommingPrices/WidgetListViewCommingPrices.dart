
import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';

import '../../../data/entities/Entities1CListManual.dart';





//TODO Виджет сотоящий из трех строк Телефон и Две Почты
class WidgetListViewCommingPrices extends State<StatefulWidgetCommingPrices> {
  Logger logger;
  //TODO json data

  late List<Entities1CListManual> listManual = getListmanual();

  WidgetListViewCommingPrices({ required this.logger } );

  @override
  Widget build(BuildContext context) {
    // TODO: Главный виджет
    print('listManual...  $listManual');

    return FutureBuilder<String>(
      future: downloadData(  logger: logger), // function where you call your api
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) { // AsyncSnapshot<Your object type>

        if (snapshot.connectionState == ConnectionState.waiting) {
            return     Scaffold(
              backgroundColor: Colors.black,
              body: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[




                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Container(
                      margin: new EdgeInsets.symmetric(vertical: 20.0),
                      height: 150,
                      width: 150,
                      // color: Colors.red,
                      decoration: BoxDecoration(
                        color: Colors.black, //assign either here or to the container
                        borderRadius: BorderRadius.circular(24),),
                      child: Padding(
                        padding: const EdgeInsets.all(2.0),
                        child:  Theme(
                          data: Theme.of(context).copyWith(hintColor: Colors.white),
                          child: CircularProgressIndicator(
                              strokeWidth: 10.0,
                              backgroundColor: Colors.grey,
                              valueColor: AlwaysStoppedAnimation<Color>(Colors.red)),
                        ),
                      ),
                    ),

                  ],
                ),




                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Container(
                          margin: new EdgeInsets.symmetric(vertical: 20.0),
                          height: 150,
                          width: 150,
                          // color: Colors.red,
                          decoration: BoxDecoration(
                            color: Colors.black, //assign either here or to the container
                            borderRadius: BorderRadius.circular(24),),
                          child: Padding(
                            padding: const EdgeInsets.all(2.0),
                            child: Theme(
                              data: Theme.of(context).copyWith(hintColor: Colors.white),
                              child: CircularProgressIndicator(
                                  strokeWidth: 5.0,
                                  backgroundColor: Colors.grey,
                                  valueColor: AlwaysStoppedAnimation<Color>(Colors.red)),
                            ),
                          ),
                        ),

                      ],
                    ),

                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Container(
                          margin: new EdgeInsets.symmetric(vertical: 20.0),
                          height: 150,
                          width: 150,
                          // color: Colors.red,
                          decoration: BoxDecoration(
                            color: Colors.black, //assign either here or to the container
                            borderRadius: BorderRadius.circular(24),),
                          child: Padding(
                            padding: const EdgeInsets.all(2.0),
                            child:
                               Text(
                              'Hello!',
                              style: TextStyle(
                                color: Colors.grey,
                                fontSize: 40,
                                fontWeight: FontWeight.w200,
                              ),
                              textAlign: TextAlign.center,
                            ),
                          ),
                        ),

                      ],

                    ),






                    Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Container(
                            margin: new EdgeInsets.symmetric(vertical: 20.0),
                          height: 80,
                          width: 80,
                          // color: Colors.red,
                          decoration: BoxDecoration(
                            color: Colors.black, //assign either here or to the container
                            borderRadius: BorderRadius.circular(24),),
                          child: Padding(
                            padding: const EdgeInsets.all(2.0),
                            child: AnimatedContainer(
                              duration: const Duration(milliseconds: 500),
                              decoration: BoxDecoration(
                                color: Colors.blue,
                                borderRadius: BorderRadius.circular(80)),
                              child: IconButton(
                                icon: Icon(Icons.add,
                                  color:Colors.red,
                                  size: 25.0,), onPressed: () {
                                  // Do something when the button is pressed
                                print('object;'); },
                                ),
                            ),





                            )
                          ),
                      ],
                    ),







                    Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Container(
                            margin: new EdgeInsets.symmetric(vertical: 20.0),
                            height: 50,
                            width: 50,
                            // color: Colors.red,
                            decoration: BoxDecoration(
                              color: Colors.black, //assign either here or to the container
                              borderRadius: BorderRadius.circular(24),),
                            child: Padding(
                              padding: const EdgeInsets.all(2.0),
                              child:
                              Tooltip(
                                message: 'Delete',
                                child: IconButton(
                                  icon: const Icon(Icons.delete),
                                  onPressed: () {
                                    print('Delete button pressed');
                                  },
                                ),
                              ),





                            )
                        ),
                      ],
                    ),





















                    //TODO END ROW

                  ],
              ),


          );





        }
        if (snapshot.connectionState == ConnectionState.done && snapshot.hasData) {
          return getWidgetScaffold(listManual);
        }

        if (snapshot.hasError){
          return Center(child: Text(' There was a problem Error: ${snapshot.error}'));
      }

        return new Scaffold(
          backgroundColor: Colors.grey[200],

            body: Column(
            children: <Widget>[
            Container(
            height: 150.0,
            width: 120.0,
            child: Center(child: new Text('Hey I am Mir')),
        ),
        ],
        ),
        );
      }
    );
  }

}




Future<String> downloadData({ required  logger })async{

await Future.delayed(Duration(seconds: 1))
     .catchError(
         (Object error) {
       print(' get ERROR $error  ');
     }) ;
  //   var response =  await http.get('https://getProjectList');
  return  "Data download successfully"; // return your response
}












  //TODO РАбоий Виджет
  Widget getWidgetScaffold(List<Entities1CListManual> listManual){
    ////TODO сам виджет
    return new Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: AppBar(
        leading: new Icon(Icons.live_tv),
        backgroundColor: Colors.blue[300],
        title: Text('Выбор цфо'),
        centerTitle: true,
        elevation: 500,
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[



          Row(
            children: [
              Expanded(
              child: Padding(
                padding: const EdgeInsets.all(2.0),
                child: SizedBox(
                  height: 35,
                  child: SearchBar(
                    hintText: 'Поиск',
                  ),
                ),
              ),
              ),
            ],
          ),

          Row(
            children: [
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.all(2.0),
                  child: SizedBox(
                    height: 35,
                    child: SearchBar(
                      hintText: 'Поиск',
                    ),
                  ),
                ),
              ),
            ],
          ),

          Row(
            children: [
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.all(2.0),
                  child: SizedBox(
                    height: 35,
                    child: SearchBar(
                      hintText: 'Поиск',
                    ),
                  ),
                ),
              ),
            ],
          ),












          Expanded(
            child: Padding(
              padding: const EdgeInsets.all(3.0),
              child: ListView.builder(
                shrinkWrap: true,
                primary: false,
                scrollDirection:
                Axis.vertical, // Axis.horizontal for horizontal list view.
                itemCount: listManual.length,
                itemBuilder: (context, index) {
                  final user = listManual[index].CFO.toString().trim();
                  final UUID = listManual[index].UUID.toString().trim();
                  //TODO
                  return Column(
                    children: [
                      Card(
                        color: Colors.grey[100],
                        elevation: 1.0,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20)),
                        child: ListTile(
                          leading: const Icon(Icons.flight_sharp),
                          subtitle: Text(""),
                          trailing: Icon(Icons.more_vert),
                          onTap: () {
                            print('object');
                            ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                              content: Text(user.trim().toLowerCase() +
                                  "\n" +
                                  "uuid-> " +
                                  UUID),
                            ));
                          },
                          title: SizedBox(
                            height: 45,
                            child: Center(
                              child: Text(
                                listManual[index].CFO.toString().trim(),
                                style: TextStyle(
                                  height: 2,
                                  fontSize: 13,
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black.withOpacity(0.7),
                                  decoration: TextDecoration.underline,
                                  decorationColor: Colors.blue[300],
                                  decorationStyle: TextDecorationStyle.wavy,
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  );
                },
              ),
            ),
          ),
        ],
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.all(3.0),
        child: FloatingActionButton.extended(
          elevation: 20.0,
          icon: const Icon(Icons.person_add),
          label: const Text('Цфо'),
          backgroundColor: Colors.blue[300],
          onPressed: () {
            /* Navigator.push(
              context,
              MaterialPageRoute(builder: (_) => CameraScreen()),
            );*/
          },
        ),
      ),
    );
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



