

import 'dart:isolate';

import 'package:commintprices/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/src/logger.dart';

import '../../../data/entities/Entities1CListManual.dart';

class   WidgetScaffoldSuccessData {


  //TODO РАбоий Виджет
  Widget getWidgetScaffold(List<Entities1CListManual> listManual){
    ////TODO сам виджет
    return new Scaffold(
      backgroundColor: Colors.blue[200],
      appBar: AppBar(
        leading: new Icon(Icons.live_tv),
        backgroundColor: Colors.blue[300],
        title: Text('Выбор цфо'),
        centerTitle: true,
        elevation: 500,
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
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
              padding: const EdgeInsets.all(2.0),
              child: ConstrainedBox(
                constraints: new BoxConstraints(
                  minHeight: 550.0,
                  maxHeight: 700.0,
                ),

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
          ),
        ],
      ),



//TODO  кенопка снизу
      floatingActionButton: Expanded(
        child: Padding(
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
      ),
    );
  }








}