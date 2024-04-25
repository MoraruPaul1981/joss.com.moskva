

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
          //TODO строчки с обьектами
          Row(
            children: [
              Expanded(
                child: Padding(
                  padding: const EdgeInsets.all(2.0),
                  child: Container(
                    margin: new EdgeInsets.only( left: 5,top:2,right: 5,bottom: 2),
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
                          child: ConstrainedBox(
                            constraints: new BoxConstraints(
                                minHeight: 20.0,
                                maxHeight: 300.0,),
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
                              title:
                              SizedBox(
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
                        ),
                      ],
                    );
                  },
                ),
              ),
            ),
          ),



          //TODO  кенопка снизу

    Row(
      crossAxisAlignment: CrossAxisAlignment.end,
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
        child: Container(
          margin: new EdgeInsets.only( left: 5,top: 0,right: 5,bottom: 0),
          height: 60,
          clipBehavior: Clip.hardEdge,
          // color: Colors.red,
          decoration: BoxDecoration(
            // color: Theme.of(context).backgroundColor,
              color: Colors.white,
              border: Border.all(
                color: Colors.blue[300]!,
                width: 0.5,
              ),
              borderRadius: const BorderRadius.only(
                topRight: Radius.circular(20),
                topLeft: Radius.circular(20),
              ),
          ),

            child: NavigationBar(
              onDestinationSelected: (int index) {
              print('NavigationBar.. выбор index ..$index');
              },
              indicatorColor: Colors.red,
              selectedIndex: 1,
              destinations: const <Widget>[
                NavigationDestination(
                  selectedIcon: Icon(Icons.home),
                  icon: Icon(Icons.home_outlined),
                  label: 'Назад',
                ),
                NavigationDestination(
                  selectedIcon: Icon(Icons.data_usage ),
                  icon: Icon(Icons.data_usage_outlined),
                  label: 'Данные',
                ),
                NavigationDestination(
                  selectedIcon: Icon(Icons.maps_ugc),
                  icon: Icon(Icons.maps_ugc_outlined),
                  label: 'Расширения',
                ),
                NavigationDestination(
                  selectedIcon: Icon(Icons.accessibility_new),
                  icon: Icon(Icons.accessibility_new_sharp),
                  label: 'Начисления',
                ),
              ],
            ),

        ),
        ),
      ],
    ),


































        ],
      ),







/*

//TODO  кенопка снизу
      floatingActionButton:
      Expanded(
        child: Padding(
          padding: const EdgeInsets.all(3.0),
          child: FloatingActionButton.extended(
            elevation: 20.0,
            icon: const Icon(Icons.person_add),
            label: const Text('Цфо'),
            backgroundColor: Colors.blue[300],
            onPressed: () {
              *//* Navigator.push(
                context,
                MaterialPageRoute(builder: (_) => CameraScreen()),*//*
            //  );
            },
          ),
        ),
      ),*/





    );
  }








}